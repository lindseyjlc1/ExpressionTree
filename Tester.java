public class Tester {
    public static void main(String[] args) {

        // Stack underflow example - not enough operands
        // String expression1 = "3 4 + * 5 6 *";
        // ExpressionTree tree1 = new ExpressionTree(expression1);

        // Invalid operand example
        // String expression2 = "5 4 + 3 k +";
        // ExpressionTree tree2 = new ExpressionTree(expression2);

        
        //Too many operands
        // String expression3 = "3 4 + 5 - 6 * 7 8 +";
        // ExpressionTree tree3 = new ExpressionTree(expression3);


        String expression4 = "3 4 +";
        ExpressionTree tree4 = new ExpressionTree(expression4);
        System.out.println("Postfix: " + tree4.postfix());
        System.out.println("Prefix: " + tree4.prefix());
        System.out.println("Infix: " + tree4.infix());
        System.out.println("Evaluated value: " + tree4.eval());

    }
}
