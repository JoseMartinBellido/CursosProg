package prog.foundations.employees;

public interface Chef {

    default void cook(String food){
        System.out.println("I am cooking " + food);
    }

    default String cleanUp(){
        return "I'm done cleaning up";
    }

    void yellAtPeople();
    
}


