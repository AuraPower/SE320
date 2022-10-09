public class GenericStack<E> {


    private int capacity;
    private E[] elements;
    private int stackCount;// keeps track of the current number of elements in the stack

    GenericStack(){
        this.capacity=10;
        this.elements = (E[])new Object[capacity];
        this.stackCount=0;
    }

    GenericStack(int capacity){
        this.capacity=capacity;
        this.elements = (E[])new Object[capacity];
        this.stackCount=0;
    }

    public int getSize(){
        return stackCount;
    }

    public E peek(){
        return elements[stackCount-1];
    }

    public void push(E o){
        if (stackCount<capacity) {
            elements[stackCount] = o;
            stackCount++;
        }
        else{
            capacity=capacity*2;
            E[] increaseCapacity = (E[]) new Object[capacity];
            for (int i=0;i<elements.length;i++){
                increaseCapacity[i]=elements[i];
            }
            elements=increaseCapacity;
            elements[stackCount]=o;
            stackCount++;
        }
    }

    public E pop(){
        E o = elements[stackCount-1];
        stackCount--;

        return o;
    }

    public boolean isEmpty(){
        return (stackCount==0);
    }

    @Override
    public String toString() {
        StringBuilder elementsOnStack = new StringBuilder();
        for (int i = 0; i<getSize();i++) {
            elementsOnStack.append(elements[i]).append(" ");
        }

        return elementsOnStack.toString();
    }
}
