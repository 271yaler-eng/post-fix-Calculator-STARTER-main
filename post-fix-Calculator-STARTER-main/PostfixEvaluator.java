import java.util.Scanner;

/** STUDENT FILE: Complete evaluate. Leave all other project files unchanged. */
public class PostfixEvaluator {
    /**
     * Evaluates a space-separated postfix expression using integer arithmetic.
     * Supported operators: +, -, *, / (integer division).
     *
     * @throws PostFixException for blank, malformed, or invalid expressions
     * @throws ArithmeticException for division by zero
     */
    public static int evaluate(String expression) {
        BoundedStackInterface<Integer> stack = new ArrayStack<>(100);
        Scanner tokens = new Scanner(expression);
        int stacksize = 0;

        if (!tokens.hasNext()) {
            tokens.close();
            throw new PostFixException("Blank expression");
        }
        while(tokens.hasNext())
        {
            String input = tokens.next().trim();
            if(isInteger(input))
            {
                if(stack.isFull())
                {
                    tokens.close();
                    throw new StackOverflowException("Stack is full");
                }
                stack.push(Integer.parseInt(String.valueOf(input)));
                stacksize++;
            }
            else if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/"))
            {
                if (stacksize < 2) {
                    tokens.close();
                    throw new PostFixException("Syntax error - Needs two oprators for one operand");
                }
                int first = stack.top();
                stack.pop();
                int second = stack.top();
                stack.pop();
                stacksize -= 2;
                int solution = 0;
                if(input.equals("+"))
                {
                    solution = second + first;
                }
                if(input.equals("-"))
                {
                    solution = second - first;
                }
                if(input.equals("*"))
                {
                    solution = second * first;
                }
                if(input.equals("/"))
                {
                    if(first == 0)
                    {
                        tokens.close();
                        throw new ArithmeticException("Syntax error: Cannot divide by 0");
                    }
                    solution = second / first;
                }
                if(stack.isFull())
                {
                    tokens.close();
                    throw new StackOverflowException("Stack is full");
                }
                stack.push(solution);
                stacksize++;
                
            }
            else
            {
                tokens.close();
                throw new PostFixException("Invalid Operation");
            }
        }
        if(stacksize == 1)
        {
            tokens.close();
            return stack.top();
        }
        else
        {
            tokens.close();
            throw new PostFixException("Not enough operators");
        }

        // TODO 1: Process tokens one at a time.
        // TODO 2: If a token is an integer, push it onto the stack.
        // TODO 3: If it is +, -, *, or /, pop two operands.
        //         IMPORTANT: the first popped number is the RIGHT operand.
        // TODO 4: Calculate and push the result.
        // TODO 5: Reject unknown tokens and expressions with too few operands.
        // TODO 6: After processing, require EXACTLY one result on the stack.
        //         Catch stack underflow/overflow or check before operations.
        // Close the Scanner when you are finished.
        //throw new UnsupportedOperationException("TODO: implement postfix evaluation");
    }

    public static boolean isInteger(String input)
    {
        try
        {
            Integer.parseInt(input);
                return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
}
