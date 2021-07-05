import java.util.Stack;
import java.util.Arrays;
public class stack {
    public static boolean matching_prantheses(String equ){
        Stack<String> tmp = new Stack<>();
        for(int i=0; i<equ.length(); i++) {

            //if Opening baracket founds, Push it
            if (equ.charAt(i) == '(' || equ.charAt(i) == '[')
                tmp.push(String.valueOf(equ.charAt(i)));

            //If closing found then
            else if (equ.charAt(i) == ')' || equ.charAt(i) == ']') {
                //if emtpu return false
                if (tmp.empty())
                    return false;
                String ch = tmp.pop();
                //if opposite find, then return fasle
                if (equ.charAt(i) == ')' && ch.equals("[")) {
                    return false;
                }
                if (equ.charAt(i) == ']' && ch.equals("(")){
                    return false;
                }

            }

        }

        return true;
    }
    //Funtion for precendence of operators
    static int precedence(char ch){
        switch (ch){
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
    public static String infix_to_postfix(String equ){
        Stack<String> tmp = new Stack<>();
        String postfix = "";

        for(int i=0; i<equ.length(); i++){
            //if letter(equation leeter) or a digit, just add into postfix
            if(Character.isLetterOrDigit(equ.charAt(i)))
                postfix += equ.charAt(i);
            //even if space, append it
            else if(equ.charAt(i) == ' '){
                postfix+=equ.charAt(i);
            }

            //if opening found then push it in stack
            else if(equ.charAt(i) == '('){
                tmp.push(String.valueOf(equ.charAt(i)));
            }
            //if closing found
            else if(equ.charAt(i) == ')'){
                //pop and add untill opening (which is puhed before) is not reached
                while(!tmp.isEmpty() && !tmp.peek().equals("(")) {
                    postfix += tmp.pop();
                }
                    tmp.pop();

            }
            else
                //opetator found
            {
                while (!tmp.isEmpty() && precedence(equ.charAt(i)) < precedence(tmp.peek().charAt(0))){
                    postfix+= tmp.pop();
                }
                tmp.push(String.valueOf(equ.charAt(i)));
            }

        }
        while (!tmp.isEmpty()){
            if(tmp.peek().equals("("))
                return "Invalid Expression";
            postfix += tmp.pop();
        }
        return postfix;
    }
    //Without Stack, O(n^2) Time complexity
    static void stock_span_simple(int [] price, int [] result){
        result[0] = 1;
        for(int i=1; i<price.length; i++){
            result[i] = 1;
            for(int j = i-1; j>=0 && price[i] >= price[j]; j--){
                result[i]+= 1;
            }
        }
    }
    //Stack based 1st approach (Only usage of 1 stack)
    static void stock_span_stack_1(int[] price, int[] results){
    Stack<Integer> tmp = new Stack<>();
    tmp.push(0);
    //First have stock span of 1
    results[0] = 1;
    for(int i=0; i<price.length; i++){
        //Cheking previous ones days
        while (!tmp.isEmpty() && price[tmp.peek()] <= price[i])
            tmp.pop();

        results[i] = tmp.isEmpty() ? i+1 : i - tmp.peek();

        //Again push index
        tmp.push(i);
    }

    }
    //Solved vis using 2 stacks (Fastest one, because only push & pop operations)
    static void stock_span_stack_2(int [] price, int [] result){
        Stack<Integer> original = new Stack<>();
        Stack<Integer> temp = new Stack<>();

        result[0] = 1;
        int cn;
        temp.push(price[0]);
        for(int i=1; i<price.length; i++){
            cn = 1;

            //Comparing
            while (!temp.isEmpty() && temp.peek()<=price[i]){
                original.push(temp.pop());
                cn++;
            }
            while (!original.isEmpty())
                temp.push(original.pop());

            result[i] = cn;
            temp.push(price[i]);
        }
    }
    static void next_greater_element(int [] arr){
        int max;
        for(int i=0; i<arr.length; i++){
            max = -1;
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] < arr[j]) {
                    max = arr[j];
                    break;
                }
            }
            System.out.print(max + ", ");
        }
    }
    static void delete_mid(Stack<Integer> a){
        Stack <Integer> tmp = new Stack<>();
        int mid = a.size()/2;
        for(int i=0; i<mid; i++){
            tmp.push(a.pop());
        }
        a.pop();
        for(int i=0; !tmp.isEmpty(); i++){
            a.push(tmp.pop());
        }
    }
    static  void sort_stack(Stack<Integer> st){
        Stack<Integer> ab = new Stack<>();
     //   for(int i=0; )
    }
    public static void main(String [] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(12);
        stack.push(23);
        stack.push(253);
        stack.push(134);
        stack.push(41);
        stack.push(31);
       /* int [] price = { 10, 4, 5, 90, 120, 80};
        int [] span = new int[price.length];
        stock_span_simple(price, span);
        stock_span_stack_2(price, span);
        System.out.print(Arrays.toString(span));
        //System.out.println(infix_to_postfix("4+ 9* 2+ 4"));
        next_greater_element(new int[]{4, 5, 2, 25});

        System.out.printf("Initial Stack: %s%n", matching_prantheses("[45+ 24 + ( 4+ 4)]"));
        */
        delete_mid(stack);
        System.out.print(stack);
    }


}

