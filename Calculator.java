import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator extends Frame {

       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button[] buttons = new Button[15]; 
       private TextArea ta = new TextArea(15,40);
       private Panel p = new Panel(new GridLayout(5,3));
       private int[] count = new int[3];
       private String prev=null;
	   public int op1=0;
	   public int op2 = 0;
	   public int trigger = 0;
       public Calculator(){
    	   setTitle("Calculator");
    	   setSize(2000,2000);
    	   for(int i = 0;i<3; i++) {
    		   count[i] = 1;
    	   }
    	   buttons[10] = new Button("+");
    	   buttons[11] = new Button("-");
    	   buttons[12] = new Button("*");
    	   for(int i = 10;i<13;i++) {
    		   p.add(buttons[i]);
    		   buttons[i].setBackground(Color.yellow);
    	   }
    	   for(int i = 0;i<10;i++) {
    		   buttons[i] = new Button(String.valueOf(i));
    		   buttons[i].setBackground(Color.gray);
    	   }
    	   for(int i = 0;i<10;i++) {
    		   p.add(buttons[i]);
    	   }
    	   buttons[13] = new Button("C");
    	   buttons[14] = new Button("=");
    	   for(int i = 13;i<15;i++) {
    		   p.add(buttons[i]);
    		   buttons[i].setBackground(Color.YELLOW);
    	   }
    	   add(ta,BorderLayout.NORTH);
    	   add(p,BorderLayout.CENTER);
    	   addWindowListener(new windowListener());
    	   for(int i=0; i<10; i++) {
    		   buttons[i].addActionListener(new buttonListener(i));
    	   }
    	   buttons[13].addActionListener(new cButtonListener("C"));
     	   buttons[14].addActionListener(new buttonListens("="));
    	   buttons[10].addActionListener(new buttonListens("+"));
    	   buttons[11].addActionListener(new buttonListens("-"));
    	   buttons[12].addActionListener(new buttonListens("*"));
    	   pack();
    	   setVisible(true);
    	   ta.setEditable(false);
    	   ta.setBackground(Color.CYAN);
       }
       public class windowListener extends WindowAdapter {
       	
       	public void windowClosing(WindowEvent arg0) {
       		System.exit(0);
       	}


       }
       public class buttonListener implements ActionListener{
    	   int key;
    	   public buttonListener(int i) {
    		   key = i;
    	   }
    	   public void actionPerformed(ActionEvent e) {
    		   if(trigger == 0) {
    		   ta.append(Integer.toString(key));
    		   }
    		   else {
    			  ta.setText(Integer.toString(key));
    			  trigger = 0;
    		   }
    	   }
       }
       public class buttonListens implements ActionListener{
    	   String key1,key2;
    	   public buttonListens(String i) {
    		   key1 = i;
    	   }
    	   public void actionPerformed(ActionEvent e) {
    		   if(prev == null) {
       			op1 =Integer.parseInt(ta.getText());
       			op2 = op1+op2;
       			ta.setText(Integer.toString(op2));
       			trigger = 1;
       			prev = key1;
       		   }
       		   else {
       			   if(prev == "+" && trigger == 0) {
       	    			op1 =Integer.parseInt(ta.getText());
       	    			op2 = op1+op2;
       	    			ta.setText(Integer.toString(op2));
       	    			trigger = 1;
       	    			prev = key1;
       	    			//ta.append(prev);
       	    		   }
       			   else if(prev == "-" && trigger == 0) {
       	    			op1 =Integer.parseInt(ta.getText());
       	    			op2 = op2-op1;
       	    			ta.setText(Integer.toString(op2));
       	    			trigger = 1;
       	    			prev = key1;
       	    		   }
       			   else if(prev == "*" && trigger == 0) {
       	    			op1 =Integer.parseInt(ta.getText());
       	    			op2 = op1*op2;
       	    			ta.setText(Integer.toString(op2));
       	    			trigger = 1;
       	    			prev = key1;
       	    		   }
       			   else {
       				   prev = key1;
       				   trigger = 1;
       				   if(key1 == "=") {
       					   prev = null;
       					   op1 = 0;
       					   op2 = 0;
       				   }
       			   }
       			   
       		   }
    	   }
       }
       public class cButtonListener implements ActionListener{
    	   public cButtonListener(String i) {
    		   
    	   }
    	   public void actionPerformed(ActionEvent e) {
    			   ta.setText(null);
    			   prev = null;
    			   trigger = 0;
    			   op2 = 0;
    			   op1 = 0;
    		   }
    	   }
       public static void main(String... args) {
    	   new Calculator();
       }
}


/*public class buttonListener implements ActionListener{
int key2 = -9;
String key1 = null;
public buttonListener(int i) {
	  key2 = i;
}
public buttonListener(String i) {
	  key1 = i;
}
 public void actionPerformed(ActionEvent e) {
	   if(key2==-9) {
		   ta.append(key1);
	   }
	   else {
		   ta.append(Integer.toString(key2));
	   }
 }
}
public class buttonListens implements ActionListener{
 String key;
 public buttonListens(String i){
	   key = i;
 }
 public void actionPerformed(ActionEvent e) {
	   String pof = infixToPostfix(ta.getText());
	   if(pof=="Invalid Expression") {
		   ta.setText(pof);
	   }
	   else {
	   int result = evaluatePostfix(pof);
	   ta.setText(Integer.toString(result));
	   }
 }
 int Prec(char ch) 
  { 
      switch (ch) 
      { 
      case '+': 
      case '-': 
          return 1; 
     
      case '*': 
      case '/': 
          return 2; 
     
      case '^': 
          return 3; 
      } 
      return -1; 
  } 
     
  // The main method that converts given infix expression 
  // to postfix expression.  
  String infixToPostfix(String exp) 
  { 
      // initializing empty String for result 
      String result = new String("");
      int count = 0;
        
      // initializing empty stack 
      Stack<Character> stack = new Stack<>(); 
        
      for (int i = 0; i<exp.length(); ++i) 
      { 
          char c = exp.charAt(i);
            
           // If the scanned character is an operand, add it to output. 
          if (Character.isLetterOrDigit(c)) 
              result += c; 
             
          // If the scanned character is an '(', push it to the stack. 
          else if (c == '(') { 
              stack.push(c);
              count++;
          }
            
          //  If the scanned character is an ')', pop and output from the stack  
          // until an '(' is encountered. 
          else if (c == ')') 
          { 
              while (!stack.isEmpty() && stack.peek() != '(') 
                  result += stack.pop(); 
                
              if (!stack.isEmpty() && stack.peek() != '(') 
                  return "Invalid Expression"; // invalid expression                 
              else
                  stack.pop();
              count--;
          } 
          else // an operator is encountered 
          { 
              while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) 
                  result += stack.pop(); 
              stack.push(c); 
          } 
     
      } 
     
      // pop all the operators from the stack 
      while (!stack.isEmpty()) 
          result += stack.pop(); 
     if(count !=0)
  	   return "Invalid Expression";
      return result; 
  }
int evaluatePostfix(String exp) 
{ 
 //create a stack 
 Stack<Integer> stack=new Stack<>(); 
   
 // Scan all characters one by one 
 for(int i=0;i<exp.length();i++) 
 { 
     char c=exp.charAt(i); 
       
     // If the scanned character is an operand (number here), 
     // push it to the stack. 
     if(Character.isDigit(c)) 
     stack.push(c - '0'); 
       
     //  If the scanned character is an operator, pop two 
     // elements from stack apply the operator 
     else
     { 
         int val1 = stack.pop(); 
         int val2 = stack.pop(); 
           
         switch(c) 
         { 
             case '+': 
             stack.push(val2+val1); 
             break; 
               
             case '-': 
             stack.push(val2- val1); 
             break; 
               
             case '/': 
             stack.push(val2/val1); 
             break; 
               
             case '*': 
             stack.push(val2*val1); 
             break; 
       } 
     } 
 } 
 return stack.pop();     
}
}*/
