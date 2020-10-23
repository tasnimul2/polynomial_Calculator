![](https://i.imgur.com/Bpc2uRc.png)

![]()

Chowdhury Mohammed || Project 1 Phase 2:  Arithmetic  Operation|| Fall 2020 Version 2

TESTING CORRECTNESS
Method : public Polynomial(String s);
Test Cases:

Input :
X^2 + X^2 - X^2 
1 + 2 + 3 + 4
Output:
p =  X^2
q =  10.0
Edge case → if all Terms are the same or if all terms have no variables 
This output is correct because terms of same degrees must be combined into one as well as summing up their coefficients 

Input :
X^        3 - X          ^ 2 + 3       .       8
2            + 9+ X  ^                   3
Output:
p =  X^3 -X^2 + 3.8
q =  X^3 + 11.0
Edge Case → if the inputs have large amounts of spaces in between them.
This output is correct because it ignores all of the extra spaces in between terms. 

Input:
x^2 + 3X^4 - 1x
X + x + X + x
Output:
p =  3.0 X^4 + X^2 -X
q =  4.0 X
Edge Case → If the variables  of the polynomials are different sizes (ie. X’s are different physical sizes)
This output is correct because it ignores the size of the variables and returns all uppercase X’s as if all x’s in the inputs were the same size,




Input:
42069X^360 - 696969X^360
131355X^42036069 + 800X^42036069
Output:
p =  -654900.0 X^360
q =  132155.0 X^42036069
Edge Case → Canonicalizing really large numbers 
This output is correct because it correctly added/subtracted the coefficients together during the canonicalization process despite the numbers being large.


Input:
X^2 + 2*X^2 - X^2 + 3 + 1
X + X^2 + 1 + X + X^2 + 5*X^3
Output:
p =  2.0 X^2 + 4.0
q =  5.0 X^3 + 2.0 X^2 + 2.0 X + 1.0
Edge case → instead of putting 2x^2, if the user chooses to put 2*x^2 (which essentially means the same thing)
The output is correct because the algorithm ignores the * and reads the input as if the * never existed as it adds no significance to the polynomial to any arithmetic done to it. 


Method :  
public AbstractPolynomial add(AbstractPolynomial p);
public AbstractPolynomial subtract(AbstractPolynomial p);
public AbstractPolynomial multiply(AbstractPolynomial p);

Test Cases:
Input :
X^2 + X^2 - X^2 
1 + 2 + 3 + 4
Output:
Sum  X^2 + 10.0
Difference  X^2 -10.0
Product  10.0 X^2
Edge case → If one polynomial is all variables while the other is all constants
This is the correct output because it simply combines all the constants together into one term then adds/subtracts/multiplies  it to the other polynomial






Input :
X^        3 - X          ^ 2 + 3       .       8
2            + 9+ X  ^                   3
Output:
Sum  2.0 X^3 -X^2 + 14.8
Difference   -X^2 -7.2
Product  X^6 -X^5 + 14.8 X^3 -11.0 X^2 + 41.8
Edge Case → if the inputs have large amounts of spaces in between them.
This output is correct because it ignores all of the extra spaces in between terms and computes  the polynomials as if they had no space in between them. 

Input:
x^2 + 3X^4 - 1x
X + x + X + x
Output:
Sum  3.0 X^4 + X^2 + 3.0 X
Difference  3.0 X^4 + X^2 -5.0 X
Product  12.0 X^5 + 4.0 X^3 -4.0 X^2

Edge Case → If the variables  of the polynomials are different sizes (ie. X’s are different physical sizes)
This output is correct because it ignores the size of the variables and returns all uppercase X’s as if all x’s in the inputs were the same size. The algorithm is not case sensitive. 

Input:
42069X^360 - 696969X^360
131355X^42036069 + 800X^42036069
Output:
Sum  132155.0 X^42036069 -654900.0 X^360
Difference  -132155.0 X^42036069 -654900.0 X^360
Product  -8.65483095E10 X^42036429


Edge Case → Canonicalizing really large numbers 
This output is correct because it correctly added/subtracted/multiplied the coefficients together despite the numbers being large. When the product is too large it appends an E to indicate that it is multiplied by the power of 10  (-8.65483095 x 10^ 10)


Input:
X^2 + 2*X^2 - X^2 + 3 + 1
X + X^2 + 1 + X + X^2 + 5*X^3
Output:
Sum  5.0 X^3 + 4.0 X^2 + 2.0 X + 5.0
Difference  -5.0 X^3  -2.0 X + 3.0
Product  10.0 X^5 + 4.0 X^4 + 24.0 X^3 + 10.0 X^2 + 8.0 X + 4.0
Edge case → instead of putting 2x^2, if the user chooses to put 2*x^2 (which essentially means the same thing)
The output is correct because the algorithm ignores the * and reads the input as if the * never existed since it adds no significance to the polynomials  arithmetic process. 


RUNTIMES
Method : public Polynomial (String s);
Method Input : A string of length n  that contains a polynomial;
Best Case : Ω(n^2)
In the best case, we must still iterate through the entire polynomial in the nested loop when sorting and canonicalizing even if the polynomial is sorted in canonical form.
Worst Case : O(n^2)
If as the input size gets larger, the algorithm scales quadratically. The reason being is that my sorting and canonicalization process utilizes nested loops. 









Method :  public AbstractPolynomial add(AbstractPolynomial p);
Method Input : An  AbstractPolynomial that constructs  a doubly LinkedList of length n  that contains a polynomial;
Best Case : Ω(n^2)
Even if the DList’s are completely sorted, the elements must still be transferred from the input DList into the solution DList, which takes n^2 time in my algorithm .
The Canonicalization process still takes N^2 time as well, even if  everything is sorted 
Worst Case : O(n^2)
if the DList’s are completely unsorted, the elements must be transferred from the input DList into the solution DList in sorted order, which takes n^2 time in my algorithm 
The Canonicalization process still takes n^2 time as well, even if  everything is unsorted


Method :  public AbstractPolynomial subtract(AbstractPolynomial p);
Method Input : An  AbstractPolynomial that constructs  a doubly LinkedList of length n  that contains a polynomial;
Best Case : Ω(n^2) || Worst Case : O(n^2)
The subtract method works exactly the same as the add method. Except when the transferring process takes place, one of the polynomials is negated.


Method :  public AbstractPolynomial multiply(AbstractPolynomial p);
Method Input : An  AbstractPolynomial that constructs  a doubly LinkedList of length n  that contains a polynomial;
Best Case : Ω(n^2) || Worst Case : O(n^2)
No matter if the polynomial is sorted or unsorted, it does not matter since we are multiplying the polynomials using a nested while loop which takes n^2 time. There is no way around this since we must get to every term and multiply it. 
The canonicalization of the product polynomial will take n^2 time, as always. 








USER MANUAL

The purpose of this program is to take a given input  string of  polynomials, such as 2X^3 + x^2 + 5x + 1 and 2X^5 + X^2 - 2X + 1 , and to  simplify the polynomials ,  return its sum, difference and
product.  

This project consists of 6 classes and a main method. 

Main method:
In the main method, the program takes in two inputs of strings. The two strings call on the polynomial class’s constructor.  Then it calls the run method of the Utility class. 

Polynomial Class:
In his class, there is a constructor, 3 methods that add, subtract and multiply the given polynomials as well as 3 of my own methods , paseTerm, sort and canonicalize.  My personal three methods are used within the constructor of the polynomial class.
 First, the constructor removes all junk in the user inputs such as  extra spaces and unnecessary symbols like  * . Then the constructor searches through the input string for terms , which when combined, create a polynomial. Once each term is found from the input string, the substring of terms are parsed into type Term, from type string. This is done using the parseTerm method (a method that works just like parseInt or parseDouble. Look at the design document for further details). Once the substring of terms are turned into Term objects , it is placed inside a dummy linked list (unsorted). Once the dummy linked list has all the terms of the polynomial in it , its data is then transferred to the main linked list in a sorted fashion. This is done using the sort method. (details in design document). Once the main doubly linked list is all sorted, it is placed inside the canonicalize method, which simply combines the like terms in the linked list. We now have simplified  doubly linked lists in canonical form.
In the add method, we simply take the two input polynomials that were constructed by the polynomial constructor  and combine them into a separate DList. Once in the DList, it is canonicalized.
In the subtract method,  we simply take the two input polynomials that were constructed by the polynomial constructor  and combine them into a separate DList. However, before combining the two polynomials, one of the polynomials is negated . That way when they are  in the DList and  canonicalized, it essentially subtracts  the two polynomials using mathematical trickery. 
In the multiply method, each term from the p polynomial is individually multiplied by the entire q polynomial. The product is then placed inside the answer DList , sorted and canonicalized.






DESIGN DOCUMENT

Abstract 
The purpose of this program is to take a given input  string of  polynomials, such as 2X^3 + x^2 + 5x + 1 and 2X^5 + X^2 - 2X + 1 , and to  simplify the polynomials ,  return its sum, difference and
product.  

Summary of Implementation (part 1)
Given String s  of polynomials , break the string down into smaller  substrings of terms. Once the larger string is broken down to smaller strings to terms, extract its coefficient and its degree. Once it is extracted, place the data into a doubly linkedList called DList. Once that is done, sort the DList using a sorting method. After the list is sorted, the polynomial is canonicalized using  the  canonicalize() method, which looks for terms with the same degree and combines its coefficients, transforming  the two terms into one single term.








Implementation Breakdown

Method:  public Polynomial (String s);

This method starts by removing all the unnecessary junk in the input. This includes all spaces in between characters and * symbols which add to unique meaning to the computations or output. This is done using the substring() method of the String class. Using a while loop, I iterated through the entire string. If the loop comes across a space or a * symbol, it islotes every character that comes before it and it isolates every character that comes after it and puts it into respective string variables. Then it combines the two strings,  essentially removing the space or *. 



Once the junk characters are removed from the input strings , the string is then put through a for loop. The loop iterates through one character at a time , searching for a “+” or a “-”. (Note that “start” and “end” variables are set to 0). Once the loop comes across a + or  - , it updates the value of “end” to the element number of which the symbol is located.  If another “+” ir “-” is found,” end”  value is updated to its element number and “start”value is updated to “end” ’s previous  value. Since the last term doesn't have a + or - after it, when the loop is at the very last element, from the very last occurrence of “+” or “-” up to that point (the very end of the string), is the last term.  Now that you know the behavior of the “start” and “end” variable, let us take a look at its purpose.

The purpose of doing this is to break down string s into substring of terms. Notice that in the polynomial 2x^3 + x^2 - 2x + 1 there is either a + or a  - before or after a term. Hence, that “landmark” so to say, is used to identify a term. 2x^3 is a term ,  x^2  is a term ,  and 2x + 1 is a term. Why? Because they all have either a “+” or a “-” that come after it or before it. 




Once we have gotten our substring of terms [sect ]  , we put the parsed substrings [sect  variable,  stands for “section” , ie.  “a small section of the string” ] into a method called parseTerm(sect); which returns a type Term and places it into a dummy linked list. In other words, parseTerm method  takes a substring of the polynomial (type string )as a parameter and converts that substring into type Term.  Just like how parseInteger method converts a type string to a type int .  [Implementation  of this method will be explained further down the line in the manual in detail]. 



Once all the elements of Terms are put inside the dummy linked list , a while loop is used to pass every element of dummyList into sort(Term, DList<Term>, DList<Term>); method . This method takes one element of dummyList , per iteration, and places it carefully into the main doubly linked list [termList , ie. this.Data ] . By “carefully” , it is meant that the highest degree is placed in the beginning and lowest degree is placed in the end. [The sort method’s implementation will be explained in detail further down the line in the manual]. The reason I did not directly place the Terms into the main linked list, termList, in sorted order  is that I could not figure out how to, which is why I used a dummy list to first take in all the terms, then transfer those terms into the main linked list into a sorted order. 



Finally, now that the main doubly linked list has all the Terms in descending order, termList is now passed into the canonicalize(DList<Term>);  method.  The canonicalize method simply looks for terms with the same degree and combines them,  essentially turning the two terms into one single term [canonicalize method implementation will be explained in detail down the line in the manual].



Implementation Breakdown

Method : private Term parseTerm(String);

Just like the parseInt or parseDouble method that converts a string into an int or a double respectively,  parseTerm method converts a string into a Term. 

The parseTerm method is implemented using a for loop. Initially, it looks for the character “x” in the string. If it finds it, then we look at its surroundings. If there is an x, any characters coming before it must be a coefficient, hence , convert it into a double. This is why it was vital to isolate the main given string ,  s , into substrings of terms. If we never broke it down, it would not be so simple to find out where the  coefficient is. 

After finding the coefficient , it looks for the character “^”. Anything that comes after it must be a degree. Hence it extracts all the characters that come after “^” and converts it into an integer. 

Finally, a Term object is created , with the coefficient and degree as its parameters , and returned as the output.
Some of the edge cases the parseTerm method took into account :
if the first char of the string is an x or a   “+”  with an x after it (+x), it has a 1 as its coefficient
if the first char of the string is an  “-”   with an x after it (-x) then its coefficient is  -1
if the very last char of the string is x, its degree is 1
if there is no “x”  in the string, it is a constant. Hence, the coefficient is itself



Implementation Breakdown

Method :  private void sort(Term, DList<Term>, DList<Term>);

The method sort() takes in a Term object as well as two Doubly linked lists of type Term as its parameters. This method can only function properly if used inside a loop 
Ex.
While(next node of sourceList is not null){
   sort(termObjectTakenFromSouceList,destinationList, sourceList );
   sourcelistNode is now equal to the node that comes after it
}

The way the method works is by using a while loop to traverse a linked list. While traversing through its data, pass the current data into the sort method and insert it  into the destination list.  
Note: the while loop is used to take out terms from the source linked list and pass that term into sort method to be put inside the destination list. The reason why the source linked list is also passed into the sort method, is to keep track whether the source linked list has traversed through the entire list yet. 

Here is how it is implemented : 
In order to traverse the source Linked list [dummyList] , the current node is set to dummyList’s first node (this keeps track whether we’ve traversed through the entire list yet). Then the following edge cases are checked to see where in the destination linked list  [termList] does the  current term being inputted belong:
Let's call the current term being checked to be put inside the destination list [termList] , “holder” [as in, it is “holding” the current term ].
if the destination list [termList]  is empty add holder to the front of the list 
if the degree of 1st term in termList is smaller than holder,  then add it to the front of termList
if degree of the last term in termList is bigger than holder, then add that term to the end
if the degree of the first term in termList  is the same as holder, add holder to the front of termList. 
if the degree of the last element in termList  is same as holder then  add holder to the end
If none of these cases apply to the current term being put in , traverse through termList to see where in termList does holder belong.  This is done using the following algorithm:
If the source linked list is not null (ie. if dummyList is not null), do the following :
If holder’s degree is the same as the degree of the current node, add holder right after the current node.(I could have added holder before the current node instead of after, it doesn't make a difference)
Or else if holder ‘s degree is smaller than current node’s degree  BUT holder’s degree is larger than the node that comes after current node, put holder in between current and next.  For example if termList is 2x^3 → x → 1 , holder is x^2 , current node is 2x^3 and current’ nodes next node is x, then x^2 will be placed in between 2x^3 and x , making the new list 2x^3 → x^2 → x → 1.
If not a single one of these conditions are met, simply add the holder at the very end of the list. 
After the  while loop that sort() method is located inside terminates , the main doubly linked list , i.e. the destination doubly linked list,  is completely sorted with the terms degrees  in descending order.  


 
Implementation Breakdown

Method :  private void canonicalize(DList<Term>);

The method canonicalize() simply combines terms together, of which share the same degrees. 
For example, if the doubly linked list containing  the polynomial 2x^3 + 5x^3 + x^2 + 2X^2 + x + 1 is passed into canonicalize() method, it will result in the following list 7x^3+3x^2+x+1 . Why? because 2x^3 and 5x^3 , x^2 and  2X^2  share the same degrees hence, they were combined into a single term by adding their coefficients together. 

How it is implemented : 
This method utilizes a nested loop. The outer loop is a for loop and  the inner loop is a while loop. The while loop traverses through the linked list and looks for degrees that are the same. If two terms have the same degree,  add the coefficient together then put the combined coefficients into the current node and delete the next node. The remove method takes care of connecting/linking current node to current.next.next , so there is no need to worry about a broken list. 
The outer for loop causes this process to run n times until everything is combined. Not all variables get combined on the first attempt , if there are more than two variables that are of the same degree. 

Implementation Breakdown

Method:  AbstractPolynomial add(AbstractPolynomial p);
one while loop takes the content of the qList (qList = q polynomial) and puts it inside the sumList (the answer DList)
other while loop takes the content of the pList (pList = p polynomial) and puts it inside the sumList 
note : the contents are being transferred in sorted order 
when both lists are put inside the sumList, canonicalize the list which is essentially summing it up and combining all of the terms 






Implementation Breakdown

Method:  AbstractPolynomial subtract(AbstractPolynomial p);
one while loop takes the content of the qList (qList = q polynomial) and puts it inside the sumList 
but before transferring the content, negate every coefficient . This way, when canonicalizing, it will essentially subtract. 

other while loop takes the content of the pList (pList = p polynomial) and puts it inside the sumList 
note : the contents are being transferred in sorted order
when both lists are put inside the sumList, canonicalize the list which is essentially subtracts it by adding the polynomials together (Remember: one of the polynomials is negated, so adding them actually subtracts it.  Like 1 + (-1) = 0  )

Implementation Breakdown

Method:  AbstractPolynomial multiply(AbstractPolynomial p);
take the Terms  of pList individually  , 1 by 1 , and multiply each term of pList with the Entire qlist 
each resulting term gets placed inside the multList (the solution DList). 
when multList has all the terms it gets canonicalized
https://www.youtube.com/watch?v=QlAJAUpG2-M 
that youtube video basically sums up the process.


           This image shows the multiplication process of the terms. 




















REFLECTION


There were many things that I have learned during this project. One of the things that I learned include breaking the large problem at hand into smaller problems, which make the problem much easier to solve. For example, instead of trying to get the coefficient and degree of all the terms in one swoop, like I tried initially,  I learned that it would be easier to break it down into substrings by creating separate methods that take care of the smaller issue at hand. Another thing that I learn is to read existing code thoroughly. I made the mistake of not reading everything and getting stuck. 
One thing that I enjoyed in the duration of the first part of the project is the small dopamine hits I get after solving a small portion of the problem or after fixing bugs. It was an emotional rollercoaster where I felt like the worst coder ever to feeling like Google should hire me. In my opinion , these are the emotions that truly make programming fun.  I also enjoyed helping my classmates (Rupakshi , Viviann and a few others ) debug their code after I was done doing my own parts for the project. 
The most challenging portion of this project was isolating the terms of the strings. Solving the problem was not the hardest part. The most difficult part was trying to figure out how to approach the problem. Initially I tried to get degrees and coefficients all in one go, Spent two whole days trying it. Then it  dawned on me that I should break the string up into smaller pieces, and then try to get the degree and coefficients. 
I had to deal with many dugs. Most of which were null pointer exceptions. This was the main reason why I decided to make a dummy linked list as well as making the sort method separate, instead of trying to do it all at once. 
What I found helpful was that every time I was stuck,  I looked up online resources that helped me with the necessary bug fixes. This project overall strengthened my understanding of generics.
Part one of the project took me 5 whole days, in its entirety to complete. Approximately  25 hours. 
Part two of  the project took me about 6 hours to complete. It was significantly simpler than part one. 




REFERENCES


https://stackoverflow.com/questions/218384/what-is-a-nullpointerexception-and-how-do-i-fix-it       
(Helped me fix null pointer exception)

https://www.youtube.com/watch?v=NaZJ5fmzDhA&t=55s  
(helped me figure out how to traverse the linked list )

https://www.youtube.com/watch?v=njTh_OwMljA 
(helped me brush up on how linked lists worked.)

https://www.youtube.com/watch?v=RhBXtGPrQOE 
(helped be brush up on polynomial subtraction)

https://www.youtube.com/watch?v=QlAJAUpG2-M 
(helped me brush up on polynomial multiplication)

https://www.youtube.com/watch?v=SgLT-A3ZMpA
(I watched this video like 10 times every time I got stuck in a particular section of the project to fuel the depression )






