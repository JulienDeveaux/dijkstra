set terminal png size 800, 600
set title "Implementation of Dijkstra Time Usage with a degree of 5"
set xlabel 'Average Time Usage'
set ylabel 'Number of Nodes'
set output 'resultMoyGraph5.png'
set xrange [0:11000]
set yrange [0:3000]
set key outside

plot 'resultMoyenne5.dat' using 1:4 smooth csplines title 'moyenne graphStream', \
	'resultMoyenne5.dat' using 1:3 smooth csplines title 'moyenne moi'