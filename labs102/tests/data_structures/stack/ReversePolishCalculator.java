package stack;

import java.util.ArrayList;
import java.util.Stack;

public class ReversePolishCalculator {
    public static void main(String[] args) {
        String expression = "-6 -11 -2 3 ^ % -";
        System.out.println(expression + "  =  " + RPNcalculator(expression));
    }

    public static ArrayList<String> expressionDivider(String expression) {
        ArrayList<String> list = new ArrayList<>();

        int start = 0;
        for (int i = 0; i < expression.length() + 1; i++) {
            if (i != 0 && expression.substring(i - 1, i).equals(" ")) {
                list.add(expression.substring(start, i - 1));
                start = i;
            }
            else if (i == expression.length()) {
               list.add(expression.substring(start));
            }
        }

        return list;
    }

    public static int RPNcalculator(String RPNexpression) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = expressionDivider(RPNexpression);

        for (int i = 0; i < list.size(); i++) {
            stack.push(list.get(i));

            switch (stack.peek()) {
                case "+":
                    stack.pop();
                    int firstOperand = Integer.parseInt(stack.pop());
                    int secondOperand = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(firstOperand + secondOperand));
                    break;
                case "-":
                    stack.pop();
                    secondOperand = Integer.parseInt(stack.pop());
                    firstOperand = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(firstOperand - secondOperand));
                    break;
                case "*":
                    stack.pop();
                    secondOperand = Integer.parseInt(stack.pop());
                    firstOperand = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(firstOperand * secondOperand));
                    break;
                case "/":
                    stack.pop();
                    secondOperand = Integer.parseInt(stack.pop());
                    firstOperand = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(firstOperand / secondOperand));
                    break;
                case "%":
                    stack.pop();
                    secondOperand = Integer.parseInt(stack.pop());
                    firstOperand = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(firstOperand % secondOperand));
                    break;
                case "^":
                    stack.pop();
                    secondOperand = Integer.parseInt(stack.pop());
                    firstOperand = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString((int)Math.pow(firstOperand, secondOperand)));
                    break;
                default:
                    break;
            }
        }

        int result = Integer.parseInt(stack.peek());
        return result;
    }
}
