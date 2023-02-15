# Mars Rover Application

Mars Rover is an application models a vehicle on the surface of Mars. 

The surface of Mars is modeled on a 2D plane and different types of vehicle can navigate around the 
plateau of Mars using a simple input String.

there are 2 types of plateaus to choose from, a Rectangle field and a Circle field. 

- The full space of the rectangle field is available for the vehicle to move around in.

- The circle field has some spaces marked out of bounds that the vehicle will not be allowed to move to. 

There are 2 vehicles to choose from, the Mars Rover and Knight Rover. 

- The original Mars Rover that can move 1 space forward in the direction it is facing. 

- And the Knight Rover than can move 2  spaces forward and then 1 space to the left.

## Installation
change into the home directory for Mars Rover 

```bash
mvn clean package 
```

After successful installation you can execute the jar with the command below
```bash 
java -jar target/MarsRover-1.0-SNAPSHOT-shaded.jar
```
## input and output
### Acceptable inputs 
Choosing a field type, the following are the only accepted inputs
    
    - Rectangle
    - Circle

Choosing a length and height for the field which are 2 integer separated by a space 
minimum length for both dimensions is 3 and maximum length is 300. example input below 

    5 4

Choosing the Vehicle starting position, this is in the format of 2 integers with the initial of a cardinal position. 
Must be within the confines of the field and no placed in spot marked out of bound. example input below

    3 3 N

Choosing a Vehicle type: Mars Rover or Knight Rover. example input below

    - Mars Rover
    - Knight Rover


### Outputs
Will output the field as a 2d array of strings in the console, the following symbols represent
- -1 represents an out of bound area in the boundaries of the field, the vehicle is blocked from moving to this square.
- 0 represents an in bound area which is acceptable for the vehicle to move to.
- 1 represents the starting vehicle location
- 2 represents the final vehicle location

It will also print the coordinates of the vehicle location after moving as well as its current facing cardinal position 
as an initial 

    4 4 W

## Usage
Run the java application and enter the valid inputs into the command line.


## Assumptions
Tried to make a modular program by using the MVC design pattern. 

The field would have a minimum size of 3 x 3 
and a maximum of 1000 x 1000

A vehicle that would go out of bounds of the field or land on a out of bound marked spot will stay in the same spot

A Field is an abstract class that has a 2d Array representing a Rectangle that when initialized, 
has all elements in 2d array marked out of bounds 
A concrete class extends this abstract class, and overrides a method to mark the positions that are in bounds
for example the rectangleField class will mark the whole field as in bounds as it shares the same height and length as its parent
and the same shape.

A problem occurs where on setup you place a vehicle in a field with a position that is in the field, but is marked as out of bound.
There is error handling in place to fix this, but it is in the Main method which is not great. 
The assumption that vehicles can be placed anywhere in the field when I started has caused this problem.

I regret choose -1 as the out of bound marker. To make printing the 2d array print evenly all single characters must have a space prefixing it.
I should have chosen a single character now I have spaces before all constants that represent a single character.
It makes testing more troublesome


## Attribution 
Bresenham ellipse algorithm was taken from 
http://members.chello.at/~easyfilter/bresenham.html
