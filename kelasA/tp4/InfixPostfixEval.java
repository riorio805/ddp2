package ddp2.kelasA.tp4;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Stack;

public class InfixPostfixEval {
    // Input expression of the equation
    private final String expression;
    // Infix and postfix form of the equation, using a string array
    private ArrayList<String> infix, postfix;

    // Result of the expression
    private long result;
    // Getter for result
    public long getResult() {return result;}

    /*
    Status code to determine if an error has occured:
    Current list of status codes:
    No error: result should be exact
    0 -> OK
    1 -> Missing closed parenthesis (Assume the rest is at the end of the equation)
    2 -> Missing open parenthesis (Assume the rest is at the beginning of the equation)
    Format error: arbitrary output
    3 -> Not an equation
    4 -> Missing operand
    5 -> Missing operator
    Calculation error: arbitrary output
    6 -> Division by zero
    */
    private int statusCode;


    // Test cases
    // 1. long, no parenthesis
    // 2. long with parenthesis
    // 3. missing open parenthesis
    // 4. not an equation
    // 5. missing operand
    // 6. missing operator
    // 7. division by zero
    // 8. integer overflow
    // 9-12. parenthesis changing operation order
    // 13. multiple parenthesis
    // 14. missing closed parenthesis
    // 15. nothing
    public static void main(String[] args) {
        InfixPostfixEval test = new InfixPostfixEval("1 - 2 - 4^5 * 3 * 6 / 7 ^ 2 ^ 2");
        System.out.print("test 1: ");
        System.out.println(test);

        test = new InfixPostfixEval("1 - 2^3^3 - (4 + 5 * 6) * 7");
        System.out.print("test 2: ");
        System.out.println(test);

        test = new InfixPostfixEval("250 / 25 * 2 )");
        System.out.print("test 3: ");
        System.out.println(test);

        test = new InfixPostfixEval("09s + 19a = ???");
        System.out.print("test 4: ");
        System.out.println(test);

        test = new InfixPostfixEval("3 +");
        System.out.print("test 5: ");
        System.out.println(test);

        test = new InfixPostfixEval("4 4 + 2 2");
        System.out.print("test 6: ");
        System.out.println(test);

        test = new InfixPostfixEval("83/0");
        System.out.print("test 7: ");
        System.out.println(test);

        test = new InfixPostfixEval("2^3^4");
        System.out.print("test 8: ");
        System.out.println(test);

        test = new InfixPostfixEval("(2^3)^3");
        System.out.print("test 9: ");
        System.out.println(test);

        test = new InfixPostfixEval("24-100+25-30");
        System.out.print("test 10: ");
        System.out.println(test);

        test = new InfixPostfixEval("24-100+(25-30)");
        System.out.print("test 11: ");
        System.out.println(test);

        test = new InfixPostfixEval("24-(100+25)-30");
        System.out.print("test 12: ");
        System.out.println(test);

        test = new InfixPostfixEval("(((2+3)))");
        System.out.print("test 13: ");
        System.out.println(test);

        test = new InfixPostfixEval("(((2+3 * 2");
        System.out.print("test 14: ");
        System.out.println(test);

        test = new InfixPostfixEval("");
        System.out.print("test 15: ");
        System.out.println(test);
    }

    // Constructor
    public InfixPostfixEval(String input) {
        expression = input;
        tokenizeString(expression);
        statusCode = validateTokens(infix);
        result = 0;

        // no bad format error, try to change to postfix
        if (statusCode != 3) {
            postfix = infixToPostfix(infix);
        }
        //
        if (statusCode <= 2) {
            result = resultOfPostfix(postfix);
        }
    }

    /**
     * Returns the postfix as a string with the tokens separated by a single space
     * @return String, stringified version of the postfix
     */
    public String getPostfix() {
        String output = "";

        // Append every token with a space in front
        // [24, 100, -, 25, 30, -, +] => " 24 100 - 25 30 - +"
        for (String token: postfix) {
            output = output.concat(" " + token);
        }

        // Removes the whitespace char before outputting
        return output.strip();
    }

    // Getter for status code
    public int getStatusCode() {return statusCode;}



    @Override
    public String toString() {
        return this.expression + "\n" + this.infix + "\n" + this.postfix + "\n" + this.statusCode + "\n" + this.result;
    }





    /**
     String tokenizing method, ignores spaces.
     */
    private void tokenizeString(String input) {
        // split into tokens
        StringTokenizer tokens = new StringTokenizer(input, "()/*^+- ", true);
        ArrayList<String> out = new ArrayList<>();

        // remove spaces
        while (tokens.hasMoreTokens()) {
            String nextToken = tokens.nextToken();
            if (! nextToken.equals(" ")) out.add(nextToken);
        }

        infix = out;
    }

    /**
     * Checks the token array for potential errors
     * @return status code 0, 2, 3, 4, or 5
     */
    private int validateTokens (ArrayList<String> infix) {
        // check for invalid tokens (token is not one of \d+,(,),*,+,-,/,) ) (code 3)
        for (String token: infix) {
            if (! token.matches("(\\d+|\\(|\\)|\\*|\\+|-|/|\\^)")) return 3;
        }

        // check for missing operands or operators (code 4,5)
        for (int i = 0; i < infix.size() - 1; i++) {
            String current = infix.get(i);
            String next = infix.get(i+1);
            if (current.matches("\\d+") && next.matches("\\d+")) {
                return 5;
            }
            if (current.matches("\\*|\\+|-|/|^") && next.matches("\\*|\\+|-|/|^")) {
                return 4;
            }
        }

        // check first for digits (code 4)
        for (String token: infix) {
            // any parenthesis in front
            if (token.matches("[()]")) continue;
            // digit
            if (token.matches("\\d+")) break;
            // not a digit
            return 4;
        }

        // check last for digits (code 4)
        for (int i = infix.size()-1; i >= 0; i--) {
            String token = infix.get(i);
            // any parenthesis at the back
            if (token.matches("[()]")) continue;
            // digit
            if (token.matches("\\d+")) break;
            // not a digit
            return 4;
        }

        // check for parenthesis (code 2, 1)
        int parenthesisCount = 0;   // ++ = open, -- = closed
        for (String token: infix) {
            if (token.matches("\\(")) {
                parenthesisCount++;
            } else if (token.matches("\\)")) {
                // closed parenthesis without any open parenthesis
                if (parenthesisCount == 0) {
                    return 2;
                }
                parenthesisCount--;
            }
        }
        if (parenthesisCount > 0) return 1;

        return 0;
    }

    /**
     Returns the postfix form of the input infix.
     */
    private ArrayList<String> infixToPostfix (ArrayList<String> infix) {
        Stack<String> operandStack = new Stack<>();
        ArrayList<String> postfixOutput = new ArrayList<>();

        for (String token: infix) {
            switch (token) {
                // open parenthesis, push to stack
                case "(" -> operandStack.push(token);
                // pop from stack into output until "(" appears or stack is empty
                case ")" -> {
                    // loop until stack empty
                    while ( !operandStack.empty() ) {
                        // pop from stack
                        String nextToken = operandStack.pop();
                        // "(" appears
                        if (nextToken.equals("(")) {
                            break;
                        }
                        // else copy to output
                        else {
                            postfixOutput.add(nextToken);
                        }
                    }
                }
                // Exponent: Always push into the stack
                case "^" -> operandStack.push(token);
                // Multiply, Divide: Pop every (^|*|/), then push into the stack
                case "*", "/" -> {
                    while (! operandStack.empty()) {
                        // take the next operand from the stack
                        String nextToken = operandStack.pop();
                        // if same or higher precedence, add to output
                        if (nextToken.matches("[\\^*/]")) {
                            postfixOutput.add(nextToken);
                        }
                        // else push back into the stack, then break
                        else {
                            operandStack.push(nextToken);
                            break;
                        }
                    }
                    // push current token into the stack
                    operandStack.push(token);
                }
                // Add, Subtract: Pop every (^|*|/|+|-), then push into the stack
                case "+", "-" -> {
                    while (! operandStack.empty()) {
                        // take the next operand from the stack
                        String nextToken = operandStack.pop();
                        // if same or higher precedence, add to output
                        if (nextToken.matches("[\\^*/+-]")) {
                            postfixOutput.add(nextToken);
                        }
                        // else push back into the stack, then break
                        else {
                            operandStack.push(nextToken);
                            break;
                        }
                    }
                    // push current token into the stack
                    operandStack.push(token);
                }
                // not a symbol => digit, add to output
                default -> postfixOutput.add(token);
            }
        }
        // end of input: pop all remaining tokens in stack, except if it is a parenthesis
        while (!operandStack.empty()) {
            String nextToken = operandStack.pop();
            if (nextToken.matches("[^()]")) postfixOutput.add(nextToken);
        }

        return postfixOutput;
    }

    /**
     * Returns the output of a given postfix
     * May change the status code to 6 depending on calculation
     */
    private long resultOfPostfix(ArrayList<String> postfix) {
        // Create a stack for digits
        Stack<Long> digitStack = new Stack<>();
        // Add a buffer in case the input is empty
        digitStack.add(0L);

        for (String token: postfix) {
            switch (token) {
                // Exponent: pop 2 elements a,b, push a^b
                case "^" -> {
                    Long b = digitStack.pop();
                    Long a = digitStack.pop();
                    a = (long) Math.pow(a, b);
                    digitStack.push(a);
                }
                // Multiply: pop 2 elements a,b, push a*b
                case "*" -> {
                    Long b = digitStack.pop();
                    Long a = digitStack.pop();
                    digitStack.push(a*b);
                }
                // Divide: pop 2 elements a,b, push a/b (floor div)
                case "/" -> {
                    Long b = digitStack.pop();
                    // Check for zero division, return if so. (set code to 6)
                    if (b == 0) {
                        statusCode = 6;
                        return 0;
                    }
                    Long a = digitStack.pop();
                    digitStack.push(a/b);
                }
                // Add: pop 2 elements a,b, push a+b
                case "+" -> {
                    Long b = digitStack.pop();
                    Long a = digitStack.pop();
                    digitStack.push(a+b);
                }
                // Subtract: pop 2 elements a,b, push a-b
                case "-" -> {
                    Long b = digitStack.pop();
                    Long a = digitStack.pop();
                    digitStack.push(a-b);
                }
                // Digit: push into stack
                default -> digitStack.add(Long.parseLong(token));
            }
        }


        // return top element of the stack
        return digitStack.peek();
    }
}
