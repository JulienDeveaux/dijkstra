set terminal png size 800, 600
set title "Implementation of Dijkstra Time Usage with a degree of 15"
set xlabel 'Average Time Usage'
set ylabel 'Number of Nodes'
set output 'resultMoyGraph15.png'
set xrange [0:11000]
set yrange [0:10000]
set key outside

plot 'resultMoyenne15.dat' using 1:4 smooth csplines title 'moyenne graphStream', \
	'resultMoyenne15.dat' using 1:3 smooth csplines title 'moyenne moi'