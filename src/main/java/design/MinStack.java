package design;

import java.util.Stack;

public class MinStack {

	Stack<Integer> st=new Stack<>();
    Stack<Integer> mn=new Stack<>();
   
    public void push(int x) 
    {
        if(st.empty() || x<=mn.peek())
            mn.push(x);
        st.push(x);
    }
    
    public void pop() 
    {
        if(st.peek().equals(mn.peek())) 
            mn.pop();
        st.pop();
    }
    
    public int top() 
    {
         return st.peek();
    }
    
    public int getMin() 
    {
        return mn.peek();
    }
}
