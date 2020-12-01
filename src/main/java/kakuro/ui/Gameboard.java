package kakuro.ui;

/**
 *
 * @author lasselautanala
 */

import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane; 
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Random;

import kakuro.logic.Puzzle;

/**
 *
 * @author Lasse
 */

public class Gameboard {
    private int gamenr;
    private Puzzle puzzle;
    private int nRows;
    private int nCols;
    private Button buttons[][];
    private Button numbers[];
    private int xfocus;
    private int yfocus;
    private boolean completed;

    public Gameboard() {
        Random rn = new Random();
        this.gamenr = rn.nextInt(10) + 1;
        this.puzzle = new Puzzle(this.gamenr);
        this.nRows = this.puzzle.getnRows();
        this.nCols = this.puzzle.getnCols();
        this.buttons = new Button[this.nRows][this.nCols];
        this.numbers = new Button[10];
        this.completed = false;
        this.xfocus = 0;
        this.yfocus = 0;
    }
    
    public Parent getScene() {

        BorderPane gameLayout = new BorderPane();
        gameLayout.setPrefSize(300, 300);
        
        Label infoText = new Label("");
        infoText.setFont(Font.font("Monospaced", 40));
        infoText.setAlignment(Pos.TOP_CENTER);
        infoText.setPadding(new Insets(10, 10, 10, 10));
        
        GridPane numberGrid = new GridPane();
        numberGrid.setAlignment(Pos.TOP_LEFT);
        numberGrid.setVgap(5);
        numberGrid.setHgap(5);
        numberGrid.setPadding(new Insets(10, 10, 10, 10));
        for (int x = 0; x < 10; x++) {
                this.numbers[x] = new Button(" ");
                this.numbers[x].setFont(Font.font("Monospaced", 25));
                if (x == 0) {
                    this.numbers[x].setText("X");
                } else {
                    this.numbers[x].setText(Integer.toString(x));
                }
//                numbers[x].setStyle("-fx-background-color: #ffffff; ");
                numberGrid.add(numbers[x], x, 1);
                int rx = x;
                this.numbers[x].setOnAction((event) -> {
                    int res = 0;
                    if (rx == 0) {
                        this.buttons[this.yfocus][this.xfocus].setText(" ");
                        res = puzzle.zeroSquare(this.yfocus,this.xfocus);
                    } else {
                        this.buttons[this.yfocus][this.xfocus].setText(Integer.toString(rx));
                        res = puzzle.setSquare(this.yfocus,this.xfocus, rx);
                    }
                    String tx1 = "";
                    int errCol = res / 8;
                    res = res  % 8;
                    int errRow = res / 4;
                    res = res  % 4;
                    if (res > 0 || errRow > 0) {
                        tx1 = "VIRHE: ";
                    }
                    if (res > 0) {
                        tx1 = tx1 + "kaksi samaa numeroa ";
                    }
                    if (errRow > 0 || errCol > 0) {
                        tx1 = tx1 + ", summa ei täsmää ";
                    }
                    if (puzzle.checkCompleted()) {
                        tx1 = "ONNITTELUT, RATKAISIT TEHTÄVÄN";
                        this.completed = true;
                    }
                    infoText.setText(tx1);
                });
        }

        GridPane kakuroGrid = new GridPane();
        kakuroGrid.setAlignment(Pos.CENTER);
        kakuroGrid.setVgap(1);
        kakuroGrid.setHgap(1);
        kakuroGrid.setPadding(new Insets(10, 10, 10, 10));
        kakuroGrid.setStyle("-fx-background-color: white;");
        for (int y = 0; y < this.nRows; y++) {
            for (int x = 0; x < this.nCols; x++) {
                if (this.puzzle.checkSquare(y, x)) {
                    buttons[y][x] = new Button(" ");
                    buttons[y][x].setFont(Font.font("Monospaced", 40));
                    kakuroGrid.add(buttons[y][x], x, y);
                    int rx = x;
                    int ry = y;
                    buttons[y][x].setOnAction((event) -> {
                        this.xfocus = rx;
                        this.yfocus = ry;
                    });
                } else {
                    StackPane square = new StackPane(); 
                    createSquare(square, puzzle.getSquareRowSum(y, x), puzzle.getSquareColSum(y, x));
                    kakuroGrid.add(square, x, y);
                }
            }
        }

        gameLayout.setTop(infoText);
        gameLayout.setCenter(numberGrid);
        gameLayout.setBottom(kakuroGrid);
        return gameLayout;
    }

	private void createSquare(StackPane square, int topRightNum, int bottomLeftNum) {
            
		Rectangle rect = new Rectangle(75, 75);
		rect.getStyleClass().add("white");
		rect.setFill(Color.DARKGREY);
		rect.setStroke(Color.BLACK);
		square.getChildren().addAll(rect);
		if(topRightNum != 0 || bottomLeftNum != 0) {
			Line line = new Line(0, 0, 75, 75);
			line.setStroke(Color.BLACK);
			square.getChildren().addAll(line);
		}
		if(topRightNum != 0) {
			Text tR = new Text(Integer.toString(topRightNum));
			tR.setTranslateX(14.00);
			tR.setTranslateY(-14.00);
			tR.setStyle(
					"-fx-font-family: \"Helvetica\";" +
							"-fx-font-size: 20px;"	+
							"-fx-fill: black;"
					);
			square.getChildren().addAll(tR);
		}
		if(bottomLeftNum != 0) {
			Text bL = new Text(Integer.toString(bottomLeftNum));
			bL.setTranslateX(-14.00);
			bL.setTranslateY(14.00);
			bL.setStyle(
					"-fx-font-family: \"Helvetica\";" +
							"-fx-font-size: 20px;"	+
							"-fx-fill: black;"
					);
			square.getChildren().addAll(bL);
		}
	}
}