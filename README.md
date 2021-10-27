**<u>Game of life - TDD</u>**

**Assignment**

Implement the following program using TDD. Your final project should have passing tests and

clean code. The requirements are well defined for this exercise but the code can be

implemented in many ways. Put the code under version control using git and make frequent

commits while you are working. Don’t be afraid of refactoring your code.

You start with a two dimensional grid of cells, where each cell is either alive or

dead. In this version of the problem, the grid is finite, and no life can exist off the

edges. 

When calculating the next generation of the grid, follow these rules:



1. Any live cell with fewer than two live neighbors dies, as if caused by

underpopulation.

2. Any live cell with more than three live neighbors dies, as if by

overcrowding.

3. Any live cell with two or three live neighbors lives on to the next

generation.

4. Any dead cell with exactly three live neighbors becomes a live cell.



You should write a program that can accept an arbitrary grid of cells, and will

output a similar grid showing the next generation.

Try to work with immutable objects and pure functions as much as possible