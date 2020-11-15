package com.tsystems.javaschool.tasks.calculator;


import java.util.*;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        try {
            String s = Objects.requireNonNull(CalculatorEx.calculateExpression(statement)).toString();

            for (int i = 0; i < statement.length() - 1; ++i)
                if (statement.charAt(i) == statement.charAt(i + 1) && (statement.charAt(i) == '-' ||
                        statement.charAt(i) == '+' || statement.charAt(i) == '/' || statement.charAt(i) == '*'))
                    return null;

            if (s.charAt(s.length() - 1) == '0' && s.charAt(s.length() - 2) == '.')
                return s.substring(0, s.length() - 2);

            if (s.equals("Infinity"))
                return null;

            return s;
        } catch (Exception e) {
            return null;
        }
    }
}

class CalculatorEx {

    public static final Map<String, Integer> MAIN_MATH_OPERATIONS;

    static {
        MAIN_MATH_OPERATIONS = new HashMap<String, Integer>();
        MAIN_MATH_OPERATIONS.put("*", 1);
        MAIN_MATH_OPERATIONS.put("/", 1);
        MAIN_MATH_OPERATIONS.put("+", 2);
        MAIN_MATH_OPERATIONS.put("-", 2);
    }


    public static String sortingStation(String expression, Map<String, Integer> operations, String leftBracket,
                                        String rightBracket) {
        if (expression == null || expression.length() == 0)
            return null;
        if (operations == null || operations.isEmpty())
            return null;

        List<String> out = new ArrayList<String>();

        Stack<String> stack = new Stack<String>();


        expression = expression.replace(" ", "");


        Set<String> operationSymbols = new HashSet<String>(operations.keySet());
        operationSymbols.add(leftBracket);
        operationSymbols.add(rightBracket);


        int index = 0;

        boolean findNext = true;
        while (findNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";

            for (String operation : operationSymbols) {
                int i = expression.indexOf(operation, index);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }

            if (nextOperationIndex == expression.length()) {
                findNext = false;
            } else {
                if (index != nextOperationIndex) {
                    out.add(expression.substring(index, nextOperationIndex));
                }

                if (nextOperation.equals(leftBracket)) {
                    stack.push(nextOperation);
                } else if (nextOperation.equals(rightBracket)) {
                    while (!stack.peek().equals(leftBracket)) {
                        out.add(stack.pop());
                        if (stack.empty())
                            return null;
                    }
                    stack.pop();
                } else {
                    while (!stack.empty() && !stack.peek().equals(leftBracket) &&
                            (operations.get(nextOperation) >= operations.get(stack.peek())))
                        out.add(stack.pop());

                    stack.push(nextOperation);
                }
                index = nextOperationIndex + nextOperation.length();
            }
        }

        if (index != expression.length())
            out.add(expression.substring(index));

        while (!stack.empty())
            out.add(stack.pop());

        StringBuilder result = new StringBuilder();
        if (!out.isEmpty())
            result.append(out.remove(0));
        while (!out.isEmpty())
            result.append(" ").append(out.remove(0));

        return result.toString();
    }


    public static String sortingStation(String expression, Map<String, Integer> operations) {
        return sortingStation(expression, operations, "(", ")");
    }


    public static Double calculateExpression(String expression) {
        String rpn = sortingStation(expression, MAIN_MATH_OPERATIONS);
        StringTokenizer tokenizer = new StringTokenizer(rpn, " ");
        Stack<Double> stack = new Stack<Double>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (!MAIN_MATH_OPERATIONS.containsKey(token)) {
                stack.push(new Double(token));
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.empty() ? 0 : stack.pop();

                switch (token) {
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        stack.push(operand1 / operand2);
                        break;
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                }
            }
        }
        if (stack.size() != 1)
            return null;
        return stack.pop();
    }
}