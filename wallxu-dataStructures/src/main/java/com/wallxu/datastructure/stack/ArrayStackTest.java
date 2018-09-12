package com.wallxu.datastructure.stack;

public class ArrayStackTest {

    public static void main(String[] args) {
//        testArrayStack();
        testLinkedListStack();
//        System.out.println(testBracket("({[()]})"));
    }

    private static void testLinkedListStack() {

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();

        for(int i =0; i<10;i++){
            linkedListStack.push(i);
        }

        linkedListStack.pop();
        System.out.println(linkedListStack);
    }


    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * @param str
     * @return
     */
    private static boolean testBracket(String str) {

        ArrayStack<Character> stack = new ArrayStack<Character>(100);
        
        if (str != null){
            //处理掉多余空格
            int length = str.trim().length();
            str = str.trim();

            //创建栈对象

            for (int i = 0; i < length; i++) {
                char ch = str.charAt(i);

                if(ch == '(' || ch == '[' || ch == '{'){
                    //是左括号的话，入栈
                    stack.push(ch);
                }else if(ch == ')'){
                    //如果是右 小括号，看前一个是不是左小括号，是的话，出栈
                    if(stack.peek() == '('){
                        stack.pop();
                    }
                }else if(ch == ']'){

                    //如果是右 中括号，看前一个是不是左中括号，是的话，出栈
                    if(stack.peek() == '['){
                        stack.pop();
                    }
                }else if(ch == '}'){

                    //如果是右 大括号，看前一个是不是左大括号，是的话，出栈
                    if(stack.peek() == '{'){
                        stack.pop();
                    }
                }
            }
        }
        return  stack.isEmpty();
    }


    private static void testArrayStack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(10);

        for(int i =0; i<10;i++){
            arrayStack.push(i);
        }

        arrayStack.pop();
        System.out.println(arrayStack);
    }
}
