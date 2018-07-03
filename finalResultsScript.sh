echo "List1" >> finalResults/results0508.csv
echo "List2" >> finalResults/results05042.csv
echo "List3" >> finalResults/results13708.csv
echo "List4" >> finalResults/results137042.csv

for s in {1..30}
do
	for file in outputs/*_$s.tsv
	do
		if [[ $file == outputs/output0508_$s.tsv ]]; then
			tail -1 $file | cut -f 4 -d$'\t' >> finalResults/results0508.csv
			echo "Generating results for first selection"
		elif [[ $file == outputs/output05042_$s.tsv ]]; then
			tail -1 $file | cut -f 4 -d$'\t' >> finalResults/results05042.csv
			echo "Generating results for second selection"
		elif [[ $file == outputs/output13708_$s.tsv ]]; then
			tail -1 $file | cut -f 4 -d$'\t' >> finalResults/results13708.csv
			echo "Generating results for third selection"
		elif [[ $file == outputs/output137042_$s.tsv ]]; then 
			tail -1 $file | cut -f 4 -d$'\t' >> finalResults/results137042.csv
			echo "Generating results for final selection"

		fi
	done
done


echo "Selections for the best fitness(es) have been made "