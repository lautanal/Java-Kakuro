package kakuro.records;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Records {
    private String file;
    public int gameLevel;
    public int gameLimit;
    public int gamesCompleted;
    public int[] gameResults;

    public Records(String file) throws Exception {
        this.file = file;
        this.gameLevel = 1;
        this.gameLimit = 10;
        gameResults = new int[101];
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                if (parts[0].equals("TASO")) {
                    this.gameLevel = Integer.parseInt(parts[1]);
                    this.gamesCompleted = Integer.parseInt(parts[2]);
                    this.gameLimit = this.gameLevel * 10;
                } else if (parts[0].equals("PELI")) {
                    int gameNr = Integer.parseInt(parts[1]);
                    this.gameResults[gameNr] = Integer.parseInt(parts[2]);
                }
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    public void save() {
        try (FileWriter writer = new FileWriter(new File(file))) {
            writer.write("TASO;" + this.gameLevel + ";" + this.gamesCompleted + "\n");
            for (int i = 0; i < 101; i++) {
                if (this.gameResults[i] > 0) {
                    writer.write("PELI;" + i + ";" + this.gameResults[i] + "\n");
                }
            }
        } catch (Exception e) {
        }
    } 
}
