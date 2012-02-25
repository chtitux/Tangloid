#!/bin/bash

OLD_DIR="$( pwd )"
DIR="$( cd "$( dirname "$0" )" && pwd )"/../assets/dico

mkdir -p $DIR

cd $DIR
echo -n "Splitting dico in multiple files... "
cat ../../words/words | awk 'BEGIN { i=0; wordsByFile = 5000 } { i++; print $1 > int(i/wordsByFile) ".png" }'
echo "done"

echo "Generating new tree.json"

echo " { \"dico\" :  ["  > tree.json

echo "Generating dico"

FILES=$(ls *.demo | sort -n )
LAST=$(ls *.demo | sort -n | tail -n 1)

for i in $FILES; do
  echo -n " { \"start\" : "\"$(head -n 1 $i)"\", \"stop\" : \""$(tail -n 1 $i)"\" , \"file\" : \"$i\" } " >> tree.json

  if [[ "$i" != "$LAST" ]];
    then
    echo "," >> tree.json
  else
    echo "" >> tree.json
  fi;
done

echo " ] } "  >> tree.json

cd $OLD_DIR
