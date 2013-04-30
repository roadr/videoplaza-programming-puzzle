The environment specs are: ant 1.7.1, java 1.7.0_04.
You can build using 'ant clean dist' and run using java -jar on the jar file.

Regarding the solution, I've basically reduced the linear programming problem that the puzzle constitutes to a knapsack problem, and optimized with respect to a few assumptions I've made:
I assume that the problem you gave me constitutes a non edge case when it comes to the number of campaigns to choose from and the number of impression involved in the campaigns and the monthly limit. Regarding this last aspect, I assume that they have a large common divisor such as 4000 in the problem you gave me, since it allows me to a factor of the computational complexity of the algorithm solving the knapsack problem by a factor equal to the greatest common divider. It is also good when the ratio of the monthly impression limit over the campaign impression numbers is contained.

Relating to code design, I honestly didn't pay too much attention to this considering the task, and assuming that you are more interested in the algorithm. Coming to the algorithm, the knapsack problem is solved in polynomial time in relation to its input. If the assumptions I made are valid in practical scenario, than this computational cost is valid for the entire problem. I'll be willing to expand on this over the phone, as you mentioned that there would be a following interview to discuss the solution.

The solution it returns for the data set you gave me is:

Lorem,8,28000000,3200
Amet,2,3000000,320
Mauris,1,1000000,100
32000000,3620


The code does not do a good job at validating input.
