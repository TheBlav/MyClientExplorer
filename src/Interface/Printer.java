package Interface;

public interface Printer {

    default void print (String text){
        System.out.println(text);
    }

}
