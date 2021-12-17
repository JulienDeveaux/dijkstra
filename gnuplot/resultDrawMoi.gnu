set terminal png size 800, 600
set title "My Implementation of Dijkstra Time Usage"
set xlabel 'Number of Nodes'
set ylabel 'Time in Milliseconds'
set output 'graphResultatMoi.png'
set yrange [0:35000]
set xrange [0:11000]
set key outside


plot 'resultTests.dat' index 0 using 1:3 title '10000 nodes degre 5', \
     'resultTests.dat' index 1 using 1:3 title '10000 nodes degre 15', \
     'resultTests.dat' index 2 using 1:3 title '8500 nodes degre 5', \
     'resultTests.dat' index 3 using 1:3 title '8500 nodes degre 15', \
     'resultTests.dat' index 4 using 1:3 title '7000 nodes degre 5', \
     'resultTests.dat' index 5 using 1:3 title '7000 nodes degre 15', \
     'resultTests.dat' index 6 using 1:3 title '5000 nodes degre 5', \
     'resultTests.dat' index 7 using 1:3 title '5000 nodes degre 15', \
     'resultTests.dat' index 8 using 1:3 title '3000 nodes degre 5', \
     'resultTests.dat' index 9 using 1:3 title '3000 nodes degre 15', \
     'resultTests.dat' index 10 using 1:3 title '1000 nodes degre 5', \
     'resultTests.dat' index 11 using 1:3 title '1000 nodes degre 15', \
     'resultTests.dat' index 12 using 1:3 title '500 nodes degre 5', \
     'resultTests.dat' index 13 using 1:3 title '500 nodes degre 15'