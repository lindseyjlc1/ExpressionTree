# Expression Tree Builder
The Expression Tree Builder is a Java class, *ExpressionTree.java*, that implements *ExpressionTreeInterface.java* to construct and manipulate expression trees. The class takes a postfix expression as input and builds an expression tree using a stack-based algorithm. The operands in the expression are integers, and the supported operators are restricted to +, -, *, and /. Individual tokens, operands, and operators are delimited by a single space.

## Features
- **Expression Evaluation:** The method eval() allows users to evaluate the expression tree and obtain the integer result of the postfix expression. It uses a private recursive method that takes in the root of the expression tree.

- **Postfix Expression:** Users can retrieve the corresponding postfix expression using the postfix() method. The result will be a formatted string with each operator and operand delimited by a single space.

- **Prefix Expression:** The prefix() method returns the corresponding prefix expression of the expression tree. It also uses a private recursive method to traverse the tree and generate the formatted expression string.

- **Infix Expression:** The infix() method provides the correct infix expression representation of the expression tree. It considers the necessary use of parentheses and ensures that each operator and operand are delimited by a single space.

## How to Use
To use the Expression Tree Builder in your Java project, follow these steps:

1. Import or copy the *ExpressionTree.java*, *ExpressionTreeInterface.java*, and *Tester.java* classes to your project (the tester class is where you will test the code after running and compiling the other classes... feel free to work with your own tester class and examples or use mine)

2. Create a new instance of ExpressionTree, passing the postfix expression as a parameter to the constructor in the Tester class

***Example:***

```
String postfixExpression = "34 2 - 5 *";
ExpressionTree expressionTree = new ExpressionTree(postfixExpression);
```
3. You can now use the provided methods to work with the expression tree!

To evaluate the expression and obtain the result:

```
int result = expressionTree.eval();
System.out.println("Result: " + result);
```

To retrieve the postfix expression:

```
String postfix = expressionTree.postfix();
System.out.println("Postfix Expression: " + postfix);
```
To get the prefix expression:

```
String prefix = expressionTree.prefix();
System.out.println("Prefix Expression: " + prefix);
```

To obtain the infix expression:
```
String infix = expressionTree.infix();
System.out.println("Infix Expression: " + infix);
```

Feel free to test some "invalid" examples as well!
