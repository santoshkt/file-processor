FileProcesser
=============

This java maven application reads a file (name passed in as 1st argument to program).  Each line of the file has a category, a space, and a sub-category (sub-category can have whitespace within it).  Want to:
- Only process the pair (category, sub-category) once
- If a pair appears twice, ignore the second one
- Keep track of the order of the first occurrence of each pair
- Keep track of the count for each category
- Legal category values are: PERSON PLACE ANIMAL COMPUTER OTHER
- Illegal category values should be ignored
- Output is:
CATEGORY     COUNT
PERSON
PLACE
ANIMAL
COMPUTER
OTHER
Ordered list of input
-- Category output should be in the order shown
-- If there are no items in the file for a specified category, it should be shown with a count of 0

Example:

input file:
===========

PERSON Bob Jones
PLACE Washington
PERSON Mary
COMPUTER Mac
PERSON Bob Jones
OTHER Tree
ANIMAL Dog
PLACE Texas
FOOD Steak
ANIMAL Cat

output:
======

CATEGORY     COUNT
PERSON       2
PLACE        2
ANIMAL       2
COMPUTER     1
OTHER        1
PERSON Bob Jones
PLACE Washington
PERSON Mary
COMPUTER Mac
OTHER Tree
ANIMAL Dog
PLACE Texas
ANIMAL Cat
 
