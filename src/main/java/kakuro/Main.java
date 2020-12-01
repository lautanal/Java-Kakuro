package kakuro;

import java.util.Scanner;
import kakuro.ui.HelloFX;


public class Main {
    public static void main(String[] args) {
        HelloFX.main(args);
    }
}

/*

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KakuroUi kakuroUi = new KakuroUi(scanner);
        
        kakuroUi.welcome();
        while (true) {
            if (!kakuroUi.chooseGame()) {
                break;
            }
            kakuroUi.play();
        }
    }
}
*/
