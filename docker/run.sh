#!/bin/sh
#echo "Running omnitracker2erd..."
java -jar /app/omnitracker2erd.jar > database.er

#echo "Running erd for svg..."
erd -i database.er -o database.svg

#echo "Running erd for pdf..."
#erd -i database.er -o database.pdf

cat database.svg