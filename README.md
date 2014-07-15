Videoplaza programming puzzle
=============================

You are in charge of selling online video advertising at a large media company. Your job is to make sure that you make as much money as possible from ads running on your site. You are very good at what you do and can sell virtually an infinite amount of impressions (a display of an ad to a user) to a multitude of advertisers.
However, your boss isn't happy. He claims that you cannot show more than 3 ads before each video clip on the site, or no one will visit the site. Although you are very skeptical about this, you must do what your boss tells you. This upcoming month you therefore have a total ad inventory of 32 356 000 impressions available 
(meaning that you can display ads a maximum of 32 356 000 times).
To further complicate matters, you cannot sell an arbitrary number of impressions to a customer. Your customers buy impressions in chunks of different sizes called campaigns. For instance the advertiser Acme Inc. always buys campaigns of size 2 000 000 impressions that are to be delivered during one month. If you cannot deliver the full 2 000 000 impressions in time, Acme won't pay you. On the other hand, since you are such a good sales person, you are able to sell each customer an arbitrary amount of campaigns, as long as you can deliver all impressions in each campaign.
Your job will be to choose how many campaigns to sell to what customers in order to maximize the revenue.
For the upcoming month, your options are as follows:

#### Customers, Impressions per campaign, Revenue per campaign
- Acme, 2 000 000, 200
- Lorem, 3 500 000, 400
- Ipsum, 2 300 000,  210
- Dolor, 8 000 000, 730
- SIT, 10 000 000, 1 000
- Amet, 1 500 000, 160
- Mauris, 1 000 000, 100


Since you are not only a good sales person, but also an excellent programmer, you decide to write a program that will help you find the best possible mix of campaigns. And since you hate repeating yourself, you make sure you write the program so you can re-use it next month as well.
Write a java program that takes a single argument on the command line. This argument must be a file name, which contains the input data.

The input file will always be formatted as follows:
[monthly inventory]
[customer],[impressions per campaign],[price per campaign]
...
[customer],[impressions per campaign],[price per campaign]

The monthly inventory will be an integer.
The customer name will contain no spaces.
The cost will be an integer.
The values will be separated by commas.

I.e. the file contents for the upcoming month would be
32356000
Acme,2000000,200
Lorem,3500000,400
Ipsum,2300000,210
Dolor,8000000,730
SIT,10000000,1000
Amet,1500000,160
Mauris,1000000,100

The program should output to standard out the best possible mix of campaigns to sell in the following form:
<customer>,<number of campaigns to sell>,<total impressions for customer>,<total 
revenue for customer>
...
<total number of impressions>,<total revenue>


Solution observations
---------------------

The environment specs are: ant 1.7.1, java 1.7.0_04.
You can build using 'ant clean dist' and run using java -jar on the jar file.

Regarding the solution, the linear programming problem that the puzzle constitutes has been reduced to a knapsack problem, and optimized with respect to a few assumptions:
It is assumed that the problem input given me constitutes a non edge case when it comes to the number of campaigns to choose from and the number of impression involved in the campaigns and the monthly limit. Regarding this last aspect, it is assumed that they have a large common divisor such as 4000 for the given problem input, since this allow to reduce the computational complexity of the algorithm solving the knapsack problem by a factor equal to the greatest common divider. It is also good when the ratio of the monthly impression limit over the campaign impression numbers is contained.

Relating to code design, no attention was payed to code design, the focus was layed on the algorithm itself. Coming to the algorithm, the knapsack problem is solved in polynomial time in relation to its input. If the assumptions made are valid in a practical scenario, then this computational cost is valid for the entire problem.

The solution returns for the given problen input is:

Lorem,8,28000000,3200
Amet,2,3000000,320
Mauris,1,1000000,100
32000000,3620


The code does not do a good job at validating input.