package kakuro.ui;
import java.util.Scanner;
import kakuro.logic.Square;
import kakuro.logic.Row;
import kakuro.logic.Column;
import kakuro.logic.Puzzle;

public class KakuroUi {

    private Scanner scanner;
    private Puzzle puzzle;

    public KakuroUi(Scanner scanner) {
        this.scanner = scanner;
    }

    public void welcome() {
        System.out.println("Tervetuloa pelaamaan Kakuroa!\n");
    }

    
// Pelin valinta    
    public boolean chooseGame() {
        while (true) {
            System.out.println("Syötä pelin numero, 1-10 (exit=x):");
            System.out.print("> ");
            String str = scanner.nextLine();
            if (str.equals("x")) {
                return false;
            }
            if (!isNumeric(str)) {
                System.out.println("Pelin numero ei kelpaa");
                continue;
            }
            int number = Integer.parseInt(str);
            if (number < 1 || number > 10) {
                System.out.println("Virheellinen numero!");
                continue;
            }
            this.puzzle = new Puzzle(number);
            return true;
        }
    }
    
// Pelin käynnistys    
    public void play() {
        
        System.out.println("");
        System.out.println(puzzle.toString());
        while (true) {
            System.out.println("Syötä ruutu (ij, i=rivi, j=sarake, exit=x):");
            System.out.print("> ");
            String str = scanner.nextLine();
            if (str.equals("x")) {
                break;
            }
            if (!isNumeric(str)) {
                System.out.println("Ruutunumero ei kelpaa");
                continue;
            }
            int sNr = Integer.parseInt(str);
            if (this.puzzle.checkSquare(sNr / 10, sNr % 10) == false) {
                System.out.println("Ruutunumero ei kelpaa");
                continue;
            }
            System.out.println("Syötä ruutuun tuleva numero:");
            System.out.print("> ");
            str = scanner.nextLine();
            if (!isNumeric(str)) {
                System.out.println("Antamasi teksti ei ole numero");
                continue;
            }
            int number = Integer.parseInt(str);
            if (number < 1 || number > 9) {
                System.out.println("Virheellinen numero!");
                continue;
            }
            
            int res = puzzle.setSquare(sNr / 10, sNr % 10, number);
            System.out.println("");
            System.out.println(this.puzzle.toString());
            int errCol = res / 8;
            res = res  % 8;
            int errRow = res / 4;
            res = res  % 4;
            if (res == 1) {
                System.out.println("VIRHE, SAMA NUMERO RIVISSÄ");
            } else if (res == 2) {
                System.out.println("VIRHE, SAMA NUMERO SARAKKEESSA");
            } else if (res == 3) {
                System.out.println("VIRHE, SAMA NUMERO RIVISSÄ JA SARAKKEESSA");
            }
            if (errRow == 1) {
                System.out.println("VIRHE, RIVISUMMA EI TÄSMÄÄ");
            }
            if (errCol == 1) {
                System.out.println("VIRHE, SARAKESUMMA EI TÄSMÄÄ");
            }
            if (puzzle.checkCompleted()) {
                System.out.println("Onnittelut!!! Ratkaisit Kakuro-ruudukon!");
                break;
            }
            
        }
    }

// Tarkistetaan, onko syöte numeerinen
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
