import java.util.*;


public class Polynomial extends AbstractPolynomial {
   public Polynomial(String s) throws Exception{
      DList<Term> termList = this.data;
      DList<Term> dummyList = new DList<>();

      int i = 0;
      String beforeCurrentChar;
      String afterCurrentChar;

      //get rid of spaces from the string
      while (i < s.length()){
         char currentChar = s.charAt(i);
         if(currentChar == ' ' || currentChar == '*'){
            beforeCurrentChar = s.substring(0,i); //everything that comes before currentChar
            afterCurrentChar = s.substring(i+1); // everything that comes after currentChar
            s = beforeCurrentChar + afterCurrentChar; // combine everything except the currentChar which essentially removes that char
         }else{
            i++;
         }
      }

      //System.out.println(s);
      double coeffTemp;
      int start = 0;
      int end = 0;
      String sect = "";
      //iterate through the string to break down the polynomial by terms
      for(int k =0; k < s.length();k++){
         char currChar = s.charAt(k);
         //System.out.println(currChar);
         //if we come across a + or a -, anything coming before it , up to the previous + or - is a term
         if(currChar == '+' || currChar =='-'){
            end = k;
         }
         //the last term doesn't have a + or - after it, so when the loop is at the very last element,
         //  from the previous + or - , up to the end of string is the last term
         if(k == s.length()-1){
            start = end;
            end = s.length();
         }
         //if there is a term, identified by the difference of starting point and ending point being > 0 , isolate it into the sect variable and put it into parsing method.
         if(end-start > 0) {
            sect = s.substring(start, end);
            //System.out.println("start: " + start + " end: " + end + " term: " + sect + " Term length: " + sect.length());

            dummyList.addLast(parseTerm(sect));
            start = end;
         }
      }
      //put elements of dummy DLists into the main DList (termList) in sorted order, using the sort method.
      DNode<Term> currentDNode = dummyList.getFirst();
      while(currentDNode.getNext() != null){
         Term holder = currentDNode.getData();
         sort(holder,termList,dummyList);
         currentDNode = currentDNode.getNext();
      }


      canonicalize(termList);



   }

   private void canonicalize(DList<Term> tList) throws Exception{
      boolean running = true;
      double coef ;
      for(int i = 0; i < tList.size(); i++) {
         DNode<Term> current = tList.getFirst();
         try {
            while (current.getNext() != null) {

               if ((current.getNext().getNext() != null) && current.getData().getDegree() == current.getNext().getData().getDegree()) { //if current term's degree and next terms degree is the same
                  coef = current.getData().getCoefficient() + current.getNext().getData().getCoefficient();
                  current.getData().setCoefficient(coef);
                  tList.remove(current.getNext());

               }
               current = current.getNext();

            } //end of while loop

         } catch (Exception e) {
            if (running) {
               System.out.println("");
            }
         }//end of try/catch
      }//end of for loop

   }
   //method that inserts elements from the dummyList to the termList in sorted order.
   private void sort(Term holder, DList<Term> termList, DList<Term> dummyList) throws Exception{
      DNode<Term> current = dummyList.getFirst();
      DNode<Term> currentMainNode = null;

      boolean isLoopExecuted = false;
      if(termList.size() == 0){ //if the list is empty add the term to the front of list
         termList.addFirst(holder);
         //System.out.println("case 1: " + holder);
      }else if(termList.getFirst().getData().getDegree() < holder.getDegree()){ //if the degree of 1st term in list is smaller then term being checked then add it to the front
         termList.addFirst(holder);
         //System.out.println("case 2: " + holder);
      }else if(termList.getLast().getData().getDegree() > holder.getDegree()){ // if degree of the last term in list is bigger than term being checked , add that term to the end
         termList.addLast(holder);
         //System.out.println("case 3: " + holder);
      }else if(termList.getFirst().getData().getDegree() == holder.getDegree()){ //if the degree of the first term is same as the one being checked, add the one being checked to  front
         termList.addFirst(holder);
         //System.out.println("case 4: "+ holder);
      }else if (termList.getLast().getData().getDegree() == holder.getDegree()){ //if the degree of the last element is same as one being checked add it to the end
         termList.addLast(holder);
        // System.out.println("case 5:" + holder);
      } else{
         currentMainNode = termList.getFirst(); //or else iterate through to loop to find its place.
         for(int x = 0; x < termList.size(); x++){
            if(current != null) { //current != null
               if (holder.getDegree() == currentMainNode.getData().getDegree()) { //if the current node's degree is the same as
                  isLoopExecuted = true;
                  termList.addAfter(holder, currentMainNode);
                  //System.out.println("case 6: " + holder);
                  break;
               }else if( (holder.getDegree() < currentMainNode.getData().getDegree()) && holder.getDegree() > currentMainNode.getNext().getData().getDegree()){
                  isLoopExecuted = true;
                  termList.addAfter(holder,currentMainNode);
                  //System.out.println("case 6.1: " + holder);
                  break;
               }
               currentMainNode = currentMainNode.getNext();
            }

         }
         if(!isLoopExecuted) {
            termList.addLast(holder);
            isLoopExecuted = false;
         }

      }


   }

   private Term parseTerm(String term){
      double coefficient = 0;
      int degree = 0;
      int start = 0;
      int end = 0;
      boolean isXthere = false;
      String sub = "";
      //iterate through the string term
      for(int i = 0; i < term.length(); i++){
         //if the fist char of the string is an x or a + with an x after it (+x), it has a 1 as coefficient
         if(term.charAt(0) == 'x' || term.charAt(0) == 'X' ||(term.charAt(0) == '+' && (term.charAt(1) == 'x' || term.charAt(1) == 'X'))){
            coefficient = 1.0;
         }
         else if(term.charAt(0) == '-' && (term.charAt(1) == 'x' || term.charAt(1) == 'X')){ // if there is a - in front then coefficient is -1
            coefficient = -1.0;
         }
         else if (term.charAt(i) == 'x' || term.charAt(i) == 'X') { //when looking at X, the char's that come before x is the coefficient
               end = i;
               sub = term.substring(start, end);
               coefficient = Double.parseDouble(sub);

            }
         if (term.charAt(i) == '^') { //anything that comes after a ^ is a power/degree
               start = i;
               end = term.length();
               sub = term.substring(start + 1, end);
               degree = Integer.parseInt(sub);
            }
         if(term.charAt(term.length()-1) == 'x' || term.charAt(term.length()-1) == 'X'){ //if the very last char of the string is x, its degree is 1
            degree = 1;
         }
      }
      if(term.contains("x") || term.contains("X")){ //looking to see if the string has a X in it.
         isXthere = true;
      }
      if(!isXthere){ //if there is no x in the string, it is a constant. Hence, the coefficient is it self.
         coefficient = Double.parseDouble(term);
      }
      //System.out.println("coefficient :" + coefficient + " Degree: " + degree);
      Term element = new Term(coefficient,degree); //put the coefficient in a Term object and
      return element; //return it back to the constructor

   }





   public Polynomial() {
      super();
   }
   //-------------------------------------------------PART TWO-----------------------------------------

   public AbstractPolynomial add(AbstractPolynomial p)  {
      AbstractPolynomial ans = new Polynomial();

      DList<Term> sumList = ans.data;
      DList<Term> qList = p.data;
      DList<Term> pList = this.data;

      //one while loop takes the content of the qList (qList = q polynomial) and puts it inside the sumList
      //other while loop takes the content of the pList (pList = p polynomial) and puts it inside the sumList
      //note : the contents are being transferred in sorted order
      //when both list are put inside the sumList, canonicalize the list which is essentially summing it up

      try {

         DNode<Term> currentDNode = qList.getFirst();
         while (currentDNode.getNext() != null) { // O(n^2)

            Term holder = new Term();
            holder.setCoefficient(currentDNode.getData().getCoefficient());
            holder.setDegree(currentDNode.getData().getDegree());
            sort(holder,sumList,qList);
            currentDNode = currentDNode.getNext();
         }
         DNode<Term> currentDNode2 = pList.getFirst();
         while (currentDNode2.getNext() != null) { //O(n^2);

            Term holder = new Term();
            holder.setCoefficient(currentDNode2.getData().getCoefficient());
            holder.setDegree(currentDNode2.getData().getDegree());
            sort(holder,sumList,pList);
            currentDNode2= currentDNode2.getNext();
         }
         canonicalize(sumList); //canonicalization is essentially adding up the polynomials together hence this will give us the sum
         //O(n^2)


      }catch (Exception e){
         //System.out.println(e);

      }

      return ans;
   }

   public AbstractPolynomial subtract(AbstractPolynomial p) {
      AbstractPolynomial ans = new Polynomial();
      // complete this code
      //negate q list and and p and q.

      DList<Term> differenceList = ans.data;
      DList<Term> qList = p.data;
      DList<Term> pList = this.data;
      //one while loop takes the content of the qList (qList = q polynomial) and puts it inside the sumList
      //but before transferring the content, negate every coefficient . This way, when canonicalizing, it will essentially subtract.
      //other while loop takes the content of the pList (pList = p polynomial) and puts it inside the sumList
      //note : the contents are being transferred in sorted order
      //when both list are put inside the sumList, canonicalize the list which is essentially summing it up

      try {

         DNode<Term> currentDNode = qList.getFirst();
         while (currentDNode.getNext() != null) {

            Term holder = new Term();
            holder.setCoefficient(currentDNode.getData().getCoefficient()*-1);
            holder.setDegree(currentDNode.getData().getDegree());
            sort(holder,differenceList,qList);
            currentDNode = currentDNode.getNext();
         }



         DNode<Term> currentDNode2 = pList.getFirst();
         while (currentDNode2.getNext() != null) {

            Term holder = new Term();
            holder.setCoefficient(currentDNode2.getData().getCoefficient());
            holder.setDegree(currentDNode2.getData().getDegree());
            sort(holder,differenceList,pList);
            currentDNode2= currentDNode2.getNext();

         }

         canonicalize(differenceList);

      }catch(Exception e){
         System.out.println(e);
      }

      return ans;
   }

   public AbstractPolynomial multiply(AbstractPolynomial p) {
      AbstractPolynomial ans = new Polynomial();
      // complete this code

      //take the values of pList , 1 by 1 , multiply each term of pList with the Entire qlist
      //each resulting term gets placed inside the multList.
      //when multList has all the terms it gets canonicalize
      //https://www.youtube.com/watch?v=QlAJAUpG2-M
      //that youtube video basically sums up the process.

      DList<Term> multList = ans.data;
      DList<Term> dummyList = new DList<>();
      DList<Term> qList = p.data;
      DList<Term> pList = this.data;


      try {

         DNode<Term> currPlistNode = pList.getFirst();
         DNode<Term> currQlistNode;
         while (currPlistNode.getNext() != null) {
            Term pTerm = new Term();
            pTerm.setCoefficient(currPlistNode.getData().getCoefficient());
            pTerm.setDegree(currPlistNode.getData().getDegree());
            currQlistNode = qList.getFirst();
            while(currQlistNode.getNext() != null){
               Term qTerm = new Term();
               Term prodTerm = new Term();
               qTerm.setCoefficient(currQlistNode.getData().getCoefficient());
               qTerm.setDegree(currQlistNode.getData().getDegree());
               prodTerm.setCoefficient(qTerm.getCoefficient() * pTerm.getCoefficient());
               prodTerm.setDegree(qTerm.getDegree() + pTerm.getDegree());
               dummyList.addFirst(prodTerm);

               currQlistNode = currQlistNode.getNext();
            }
            currPlistNode= currPlistNode.getNext();
         }
      }catch (Exception e){
         System.out.println(e);
      }

      try {
         DNode<Term> currentDNode = dummyList.getFirst();
         while (currentDNode.getNext() != null) {
            Term holder = currentDNode.getData();
            sort(holder, multList, dummyList);
            currentDNode = currentDNode.getNext();
         }

         canonicalize(multList);
      }catch (Exception e){
         System.out.println(e);
      }



      return ans;
   }


   
   /****************
    * MAIN FUNCTION
    ****************/
    
   public static void main(String args[]) throws Exception {
      //Variables
      Scanner scnr = new Scanner(System.in);
      AbstractPolynomial p, q;
      String testInput = "";
      //Custom test case
      if(scnr.hasNext()){
         testInput = scnr.nextLine();
         p = new Polynomial(testInput);
         testInput = scnr.nextLine();
         q = new Polynomial(testInput);
         System.out.println("User Input\n***************************");
         Utility.run(p, q);
      }
      //Default test case
      else{
         p = new Polynomial(" X^5");
         q = new Polynomial("X^2 - X + 1");
         System.out.println("Default Input\n***************************");
         Utility.run(p, q);
      }
   }
}