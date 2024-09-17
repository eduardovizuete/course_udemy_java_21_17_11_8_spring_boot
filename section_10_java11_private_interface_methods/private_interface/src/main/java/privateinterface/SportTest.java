package privateinterface;

interface Tennis {
    private static void hit(String stroke) {
        System.out.println("Hitting a " + stroke);
    }

    default void forehand() {
        hit("forehand");
    }

    static void backhand() {
        //smash();// static to instance not allowed!
        hit("backhand");
    }

    private void smash() {
        hit("smash");
    }
}

public class SportTest implements Tennis {
    public static void main(String[] args) {
        new SportTest().forehand(); // Hitting a forehand
        Tennis.backhand();          // Hitting a backhand
        //new SportTest().hit();
        //new SportTest().smash();
    }
}



