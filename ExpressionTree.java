/* 
 * Lindsey Lopez Cacho
 */
import java.util.*;

public class ExpressionTree implements ExpressionTreeInterface{

    //instance variables
    private String expression; //holds postfix expression
    private ExpressionNode root; //root node of expression tree

    //constructor for the ExpressionTree class
    public ExpressionTree(String expression){
        //set instance variable
        this.expression = expression;

        //splits the postfix expression
        String[] splitExpression = expression.split(" ");

        //creates a stack to store and track nodes of as we build expression tree
        Stack<ExpressionNode> stack = new Stack<ExpressionNode>();
        
        //initialize counters for the number of operands and operators
        int numOperands = 0; //counts number of operands
        int numOperators = 0; //counts number of operators

        //iterate over each element of the split expression
        for(String element: splitExpression){
            if(isOperator(element)){ //if element is an operator
                if(stack.size() < 2){ //if there aren't enough operands in stack to apply operator
                    throw new IllegalArgumentException("Invalid input: not enough operands.");
                }

                /*pop the two operands from stack and create new ExpressionNode
                within the operator*/
                ExpressionNode right = stack.pop();
                ExpressionNode left = stack.pop();
                
                //create a new node representing operation and push it back onto stack
                stack.push(new ExpressionNode(element, left, right));
                
                numOperators++; //increment the operator counter

            }else{ //if the element is an operand
                try{
                    int val = Integer.parseInt(element); //parse through operant as int
                    
                    //create a new node representing the operand and push to stack
                    stack.push(new ExpressionNode(element, null, null));
                    
                    numOperands++; //increment the operand counter

                } catch(NumberFormatException e){ //if the operand is not a valid int
                    throw new IllegalArgumentException("Invalid input: invalid operand.");
                }
            }
        }
        /*at the end of the loop, there should be one node left in the stack (root of expression tree)
        the num of operands should be equal to num of operators plus one since each operator requires
        two numOperands*/

        //check the number of operands and operators are valid for posfix
        if(stack.size() != 1 || numOperands != numOperators + 1){
            throw new IllegalArgumentException("Invalid input: too many operands.");
        }
        
        root = stack.pop(); /*set the root of the expression tree to the
                            last remaining node on the stack*/
    }

    //ExpressionNode nested static class
    private static class ExpressionNode{
            
        //declare instance variables for ExpressionNode
        private String value; //holds val of node(operator or operand)
        private ExpressionNode left; //points to the left child of node
        private ExpressionNode right; //points to right child of node

        //constructor for ExpressionNode class
        public ExpressionNode(String value, ExpressionNode left, ExpressionNode right){
            this.value = value;
            this.left = left;
            this.right = right;
        }

        //gettter methods for instance variables
        public String getValue(){ //returns val of node
            return value;
        }

        public ExpressionNode getLeft(){ //returns left child of node
            return left;
        }

        public ExpressionNode getRight(){ //returns right child of node
            return right;
        }

        //checks if ExpressionNode is a leaf w/no children
        public boolean isLeaf(){
            return left == null && right == null;
        }
    }

    //method to check if a String is an operator
    private boolean isOperator(String input){
        if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")){
            return true;
        }
        else{
            return false; //if invalid operator, returns false
        }
    }

    //evaluate expression tree and return the result
    public int eval(){
        return getEval(root); //invoke recursive method to eval expression tree
    }
    
    //helper method to eval expression tree recursively
    private int getEval(ExpressionNode node){
        if(node.isLeaf()){ //base case: if node is leaf, return int val of node
            return Integer.parseInt(node.getValue());
        }else{ //otherwise, recursively eval left and right subtrees to apply operator
            int leftValue = getEval(node.getLeft()); //eval left subtree
            
            int rightValue = getEval(node.getRight()); //eval right subtree
            
            String operator = node.getValue(); //get operator from current node
            
            //apply operator based on its value
            if(operator.equals("+")){ //eval addition
                return leftValue + rightValue;
            } else if(operator.equals("-")){ //eval substraction
                return leftValue - rightValue;
            } else if(operator.equals("*")){ //eval multiplication
                return leftValue * rightValue;
            } else if(operator.equals("/")){ //eval division
                return leftValue / rightValue;
            } else{
                return 0; //this should never happen since it should cover invalid operators
            }
        }
    }

    //returns the postfix expression of the expression tree
    public String postfix(){
        return getPostfix(root); //invoke recursive method for postfix expression
    }

    //helper method to get postfix exp recursively
    private String getPostfix(ExpressionNode node){
        if(node.isLeaf()){ //base case: if node is a leaf, return its val
            return node.getValue();
        }else{ //otherwise, recursively get postfix exp of left and right subtrees
            String leftPostfix = getPostfix(node.getLeft()); //get postfix exp of left subtree
            
            String rightPostfix = getPostfix(node.getRight()); //get postfix exp of right subtree
            
            String operator = node.getValue(); //get operator from current node
            
            //concatenate left postfix, right postfix, and operator w/spaces
            return leftPostfix + " " + rightPostfix + " " + operator;
        }
    }

    //return the prefix expression of the expression tree
	public String prefix(){
        return getPrefix(root); //invoke recursive method to get prefix expression
    }

    //helper method to get prefix expression recursively
    private String getPrefix(ExpressionNode node){
        if(node.isLeaf()){ //base case: if node is a leaf, return its value
            return node.getValue();
        }else{ //otherwise, recursively get prefix expression of left and right subtree
            String operator = node.getValue(); //get operator from current node
            
            String leftPrefix = getPrefix(node.getLeft()); //get prefix exp of left subtree
            
            String rightPrefix = getPrefix(node.getRight()); //get prefix exp of right subtree
            
            //concatenate operator, left prefix, and right prefix w/spaces
            return operator + " " + leftPrefix + " " + rightPrefix;
        }
    }

    //return the infix expression of the expression tree
	public String infix(){
        return getInfix(root); //invoke recursive method to get infix expression
    }

    //helper method to get infix recursively
    private String getInfix(ExpressionNode node){
        if(node.isLeaf()){ //base case: if node is a leaf, return its value
            return node.getValue();
        }else{ /*otherwise, recursively get infix expression of left and right subtrees
        and enclose them in parentheses with current operator*/
            String operator = node.getValue(); //get operator from current node
            
            String leftInfix = getInfix(node.getLeft()); //get infix expression of left subtree
            
            String rightInfix = getInfix(node.getRight()); //get infix expression of right subtree
            
            //concatenate left infix, operator, and right infix w/spaces and parentheses
            return "(" + leftInfix + " " + operator + " " + rightInfix + ")";
        }
    }

}