1) Run MoveToy.java 
2) During initial run it allows you to place toy on the table.
3) An initial invalid PLACE command will discarded and prompts to place it again.
4) Then write a sequence commands to be run.
5) REPORT command has to be executed at the end to report the current position .
6) Program will exit after REPORT command.
7) Any invalid commands or invalid placements(in the middle of sequence of commands) will be ignored .
8) Below is the sample output :
     Place the toy on the table using place command for eg: PLACE 0,0,NORTH( x and y cordinates should between 0-5)
	PLACE 0,0,NORTH
	Toy has been placed
	Enter sequence of commands to move toy over the table, valid commands are 
	 PLACE, MOVE, RIGHT, LEFT, REPORT 
	MOVE
	MOVE
	RIGHT
	REPORT

	Output :0,2,EAST

Improvements if given more time
1) I have just concentrated on solving problem, I would love to make it flexible by using factory design pattern and look like more object oriented.
2) I would have created more testcases and validations using Junit.
