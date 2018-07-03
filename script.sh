#!/bin/sh


for file in configurations/*;
do
	echo "Running $file..."
	./opt4j-3.1.4/bin/opt4j -s $file &> /dev/null
	echo "Done"
done



