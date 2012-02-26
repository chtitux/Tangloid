#!/bin/bash

OLD_DIR="$( pwd )"
DIR="$( cd "$( dirname "$0" )" && pwd )"/../assets/dico
JSON_FILE=tree.json.png
mkdir -p $DIR

cd $DIR
echo -n "Splitting dico in multiple files... "
cat ../../words/words | awk 'BEGIN { i=0; wordsByFile = 5000 } { i++; print $1 > int(i/wordsByFile) ".txt.png" }'
echo "done"

echo "Generating new tree.json"

echo " { \"dico\" :  ["  > $( echo $JSON_FILE )

echo "Generating dico"

FILES=$(ls *.txt.png | sort -n )
LAST=$(ls *.txt.png | sort -n | tail -n 1)

for i in $FILES; do

  echo -n " { \"start\" : "\"$(head -n 1 $i)"\", \"stop\" : \""$(tail -n 1 $i)"\" , \"file\" : \"$i\" } " >> $( echo $JSON_FILE )

  if [[ "$i" != "$LAST" ]];
    then
    echo "," >> $( echo $JSON_FILE )
  else
    echo "" >> $( echo $JSON_FILE )
  fi;

done

echo " ] } "  >> $( echo $JSON_FILE )

cd $OLD_DIR
