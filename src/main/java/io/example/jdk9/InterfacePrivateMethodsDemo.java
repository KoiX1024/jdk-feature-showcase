package io.example.jdk9;

public class InterfacePrivateMethodsDemo {
    public static void main(String[] args){
        MyInterface.doStaticThing();
        MyInterface myInterface = new MyInterface() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        myInterface.doSomething();
    }
}
