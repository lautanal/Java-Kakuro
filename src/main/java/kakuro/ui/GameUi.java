package kakuro.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
// import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;



import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane; 
import javafx.scene.layout.HBox; 
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.util.Duration;
import java.util.Random;

import kakuro.logic.Puzzle;
import kakuro.records.Records;

public class GameUi {
    private int gamenr;
    private Puzzle puzzle;
    private int nRows;
    private int nCols;
    private Records records;
    private int gameRecord;
    private Button buttons[][];
    private Button numbers[];
    private int xfocus;
    private int yfocus;
    private boolean completed;
    private BorderPane gameLayout;
    private GridPane numberGrid;
    private GridPane kakuroGrid;
    private GridPane infoGrid;
    private Label infoText;
    private Label timerLabel;
    private Label levelLabel;
    private Label gameLabel;
    private Label recordLabel;
    private HBox hbox;
    private Stage window;
    private Scene scene;
    private Integer timeSeconds;
    private Timeline timeline;
    

    public GameUi(Stage gameWindow) throws Exception {
        this.window = gameWindow;
        this.records = new Records("results.txt");
        this.gamenr = this.startGame();
        this.puzzle = new Puzzle(this.gamenr);
        this.nRows = this.puzzle.getnRows();
        this.nCols = this.puzzle.getnCols();
        this.records = new Records("results.txt");
        this.gameRecord = this.records.gameResults[gamenr];
        if (this.gameRecord == 0) {
            this.gameRecord = 999;
        }
        this.buttons = new Button[this.nRows][this.nCols];
        this.numbers = new Button[10];
        this.completed = false;
        this.xfocus = this.puzzle.getXstart();;
        this.yfocus = this.puzzle.getYstart();;
        this.gameLayout = new BorderPane();
        this.numberGrid = new GridPane();
        this.kakuroGrid = new GridPane();
        this.infoText = new Label("");
        if (this.gamenr <= 10) {
            this.levelLabel = new Label("Taso: 1");
        } else if (this.gamenr <= 20) {
            this.levelLabel = new Label("Taso: 2");
        } else {
            this.levelLabel = new Label("Taso: 3");
        }
        this.gameLabel = new Label("Peli: " + this.gamenr);
        this.recordLabel = new Label("Paras tulos: " + this.gameRecord + "s");
        this.timerLabel = new Label("Aika: 0s");
        this.hbox = new HBox();
        this.infoGrid = new GridPane();
        this.timeSeconds = 0;
        this.startTimer();
    }
    
    public int startGame() {
        int gnr = 1;
        for (int i = 1; i <= 20; i++) {
            if (this.records.gameResults[i] == 0) {
                gnr = i;
                break;
            }
        }
        if (gnr > this.records.gameLimit) {
            gnr = 1;
        }
        return gnr;
    }
    
    /**
    * Scenen määritys
    *
    */
    public void getScene() {

    // Laudan koko        
        this.gameLayout.setPrefSize(300, 300);

    // Numerovalinta-gridi        
        createNumberGrid();
        
    // Uusi peli -nappi
        newGameButton();
        
    // Kakuro-gridi
        createKakuroGrid();
        
    // Teksti-labelit
        createLabels();
        
    // Pelin layout
        this.gameLayout.setTop(this.numberGrid);
        this.gameLayout.setCenter(this.kakuroGrid);
        this.gameLayout.setBottom(this.infoGrid);
//        this.gameLayout.setBottom(this.scoreGrid);

    // Uusi scene        
        this.scene = new Scene(gameLayout);
        this.window.setScene(this.scene);
        
    // Numerovalinnat näppäimistöltä
        keyPressEvents();
    }

    /**
    * Numerogridi
    *
    */
    private void createNumberGrid() {
        this.numberGrid.setAlignment(Pos.TOP_LEFT);
        this.numberGrid.setVgap(5);
        this.numberGrid.setHgap(5);
        this.numberGrid.setPadding(new Insets(10, 10, 10, 10));
        this.numberGrid.setStyle("-fx-background-color: lightgrey;");
        for (int x = 0; x < 10; x++) {
            this.numbers[x] = new Button(" ");
            this.numbers[x].setFont(Font.font("Helvetica", 25));
            if (x == 0) {
                this.numbers[x].setText("X");
            } else {
                this.numbers[x].setText(Integer.toString(x));
            }
            this.numberGrid.add(numbers[x], x, 1);
            int rx = x;
            this.numbers[x].setOnAction((event) -> {
                setNumber(rx);
            });
        }
    }

    /**
    * Numeron muuttaminen
    *
    */
    public void setNumber(int rx) {
        int res = 0;
        if (rx == 0) {
            this.buttons[this.yfocus][this.xfocus].setText("  ");
            res = puzzle.setSquare(this.yfocus,this.xfocus, 0);
        } else {
            this.buttons[this.yfocus][this.xfocus].setText(Integer.toString(rx));
            res = puzzle.setSquare(this.yfocus,this.xfocus, rx);
        }
        if (res > 0) {
            errorNumbers(res, this.yfocus, this.xfocus);
            errorMessage(res);
        } else {
            okNumbers(this.yfocus, this.xfocus);
            String tx = "";
            if (puzzle.checkCompleted()) {
                tx = "Onnittelut, ratkaisit tehtävän ajassa " + this.timeSeconds + "s";
                this.completed = true;
                if (this.records.gameResults[this.gamenr] == 0) {
                    this.records.gamesCompleted += 1;
                }
                if (this.records.gameResults[this.gamenr] == 0 || this.timeSeconds < this.records.gameResults[this.gamenr]) {
                    this.records.gameResults[this.gamenr] = this.timeSeconds;
                    this.recordLabel.setText("Paras tulos: " + this.records.gameResults[this.gamenr] + "s");
                }
                if (this.records.gameLevel == 1 && this.records.gamesCompleted == 10) {
                    this.records.gameLevel = 2;
                    this.records.gameLimit = 20;
                    tx = "Onnittelut, pääsit tasolle 2 ";
                }
                if (this.records.gameLevel == 2 && this.records.gamesCompleted == 20) {
                    this.records.gameLevel = 3;
                    this.records.gameLimit = 30;
                    tx = "Onnittelut, pääsit tasolle 3 ";
                }
                this.records.save();
            }
            this.infoText.setText(tx);
        }
        this.buttons[this.yfocus][this.xfocus].setDefaultButton(true);
    }
    
    /**
    * Uuden pelin aloitus
    *
    */
    private void newGameButton() {
        Button newGame = new Button("Uusi peli");
        newGame.setFont(Font.font("Helvetica", 25));
        this.numberGrid.add(newGame, 12, 1);
        
        newGame.setOnAction((event) -> {
            this.newGame();
            this.getScene();
            window.setScene(this.scene);
        });
    }

    /**
    * Uuden pelialueen määritykset
    *
    */
     public void newGame() {
        this.gamenr = nextGame();
        this.puzzle = new Puzzle(this.gamenr);
        this.nRows = this.puzzle.getnRows();
        this.nCols = this.puzzle.getnCols();
        this.gameRecord = this.records.gameResults[gamenr];
        if (this.gameRecord == 0) {
            this.gameRecord = 999;
        }
        this.buttons = new Button[this.nRows][this.nCols];
        this.numbers = new Button[10];
        this.completed = false;
        this.xfocus = this.puzzle.getXstart();;
        this.yfocus = this.puzzle.getYstart();;
        this.gameLayout = new BorderPane();
        this.numberGrid = new GridPane();
        this.kakuroGrid = new GridPane();
        this.infoText = new Label("");
        if (this.gamenr <= 10) {
            this.levelLabel = new Label("Taso: 1");
        } else if (this.gamenr <= 20) {
            this.levelLabel = new Label("Taso: 2");
        } else {
            this.levelLabel = new Label("Taso: 3");
        }
        this.gameLabel = new Label("Peli: " + this.gamenr);
        this.recordLabel = new Label("Paras tulos: " + this.gameRecord + "s");
        this.timerLabel = new Label("Aika: 0s");
        this.hbox = new HBox();
        this.infoGrid = new GridPane();
        this.timeSeconds = 0;
    }
    
    public int nextGame() {
        int gnr = this.gamenr + 1;
        if (gnr > 30) {
            gnr = 1;
        }
        if (gnr > this.records.gameLimit) {
            gnr = 1;
        }
        return gnr;
    }
   
    /**
    * Kakuro-gridin grafiikka
    *
    */
    private void createKakuroGrid() {
        this.kakuroGrid.setAlignment(Pos.CENTER);
        this.kakuroGrid.setVgap(1);
        this.kakuroGrid.setHgap(1);
        this.kakuroGrid.setPadding(new Insets(10, 10, 10, 10));
        this.kakuroGrid.setStyle("-fx-background-color: lightgrey;");
        for (int y = 0; y < this.nRows; y++) {
            for (int x = 0; x < this.nCols; x++) {
                if (this.puzzle.checkSquare(y, x)) {
                    this.buttons[y][x] = new Button("  ");
                    this.buttons[y][x].setFont(Font.font("Helvetica", 40));
                    this.buttons[y][x].setStyle("-fx-text-fill: green");
//                    this.buttons[y][x].setStyle("-fx-background-color: #f8f8f8");
                    this.kakuroGrid.add(buttons[y][x], x, y);
                    int rx = x;
                    int ry = y;
                    this.buttons[y][x].setOnAction((event) -> {
                        this.buttons[this.yfocus][this.xfocus].setDefaultButton(false);
                        this.xfocus = rx;
                        this.yfocus = ry;
                        this.buttons[this.yfocus][this.xfocus].setDefaultButton(true);
                    });
                } else {
                    StackPane square = new StackPane(); 
                    createSquare(square, puzzle.getSquareRowSum(y, x), puzzle.getSquareColSum(y, x));
                    this.kakuroGrid.add(square, x, y);
                }
            }
        }
    }
    
    /**
    * Ei-pelattavan ruudun grafiikka
    *
    */
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
                tR.setStyle("-fx-font-family: \"Helvetica\";" +
                             "-fx-font-size: 20px;"	+
                             "-fx-fill: black;"
                );
                square.getChildren().addAll(tR);
        }
        if(bottomLeftNum != 0) {
                Text bL = new Text(Integer.toString(bottomLeftNum));
                bL.setTranslateX(-14.00);
                bL.setTranslateY(14.00);
                bL.setStyle("-fx-font-family: \"Helvetica\";" +
                            "-fx-font-size: 20px;"	+
                            "-fx-fill: black;"
                                );
                square.getChildren().addAll(bL);
        }
    }

    /**
    * Virheellisten numeroiden väritys punaisella
    *
    */
    private void errorNumbers(int res, int y, int x) {
        int xStart = this.puzzle.getRowStart(y,x);
        int xSquares = this.puzzle.getRowSquares(y,x);
        int yStart = this.puzzle.getColumnStart(y,x);
        int ySquares = this.puzzle.getColumnSquares(y,x);
        int errColSum = res / 8;
        res = res  % 8;
        int errRowSum = res / 4;
        res = res  % 4;
        int errColSame = res / 2;
        res = res  % 2;
        int errRowSame = res;
        if (errColSum > 0 || errColSame > 0) {
            redColumn(x, yStart, ySquares);
        }
        if (errRowSum > 0 || errRowSame > 0) {
            redRow(y, xStart, xSquares);
        }
    }

    /**
    * OK-numerot vihreällä
    *
    */
    private void okNumbers(int y, int x) {
        int xStart = this.puzzle.getRowStart(y,x);
        int xSquares = this.puzzle.getRowSquares(y,x);
        int yStart = this.puzzle.getColumnStart(y,x);
        int ySquares = this.puzzle.getColumnSquares(y,x);
        greenColumn(x, yStart, ySquares);
        greenRow(y, xStart, xSquares);
    }
    
    /**
    * Punainen rivi
    *
    */
    private void redRow(int yRow, int xStart, int nSquares) {
        int x = xStart;
        for (int i = 0; i < nSquares; i++) {
            this.buttons[yRow][x].setStyle("-fx-text-fill: red");
            x++;
        }
    }
    
    /**
    * Vihreä rivi
    *
    */
    private void greenRow(int yRow, int xStart, int nSquares) {
        int x = xStart;
        for (int i = 0; i < nSquares; i++) {
            this.buttons[yRow][x].setStyle("-fx-text-fill: green");
            x++;
        }
    }
    
    /**
    * Punainen sarake
    *
    */
    private void redColumn(int xColumn, int yStart, int nSquares) {
        int y = yStart;
        for (int i = 0; i < nSquares; i++) {
            this.buttons[y][xColumn].setStyle("-fx-text-fill: red");
            y++;
        }
    }
    
    /**
    * Vihreä sarake
    *
    */
    private void greenColumn(int xColumn, int yStart, int nSquares) {
        int y = yStart;
        for (int i = 0; i < nSquares; i++) {
            this.buttons[y][xColumn].setStyle("-fx-text-fill: green");
            y++;
        }
    }

    /**
    * Tekstilabelit
    *
    */
    private void createLabels() {
// Info text        
        this.infoText.setFont(Font.font("Helvetica", 30));
        this.infoText.setPadding(new Insets(10, 10, 10, 10));
        this.infoGrid.add(infoText, 1, 2);
        
    // Game level label
        this.levelLabel.setFont(Font.font("Helvetica", 30));
        this.levelLabel.setPadding(new Insets(10, 10, 10, 10));
    
    // Game number label
        this.gameLabel.setFont(Font.font("Helvetica", 30));
        this.gameLabel.setPadding(new Insets(10, 10, 10, 10));
    
    // Game record label
        this.recordLabel.setFont(Font.font("Helvetica", 30));
        this.recordLabel.setPadding(new Insets(10, 10, 10, 10));
       
    // Timer text
        this.timerLabel.setFont(Font.font("Helvetica", 30));
        this.timerLabel.setPadding(new Insets(10, 10, 10, 10));
        
        this.hbox.getChildren().add(this.levelLabel);
        this.hbox.getChildren().add(this.gameLabel);
        this.hbox.getChildren().add(this.recordLabel);
        this.hbox.getChildren().add(this.timerLabel);
        this.infoGrid.add(this.hbox, 1, 1);
    }
    
    /**
    * Info-teksti
    *
    */
    private void errorMessage(int res) {
        String tx1 = "";
        int errCol = res / 8;
        res = res  % 8;
        int errRow = res / 4;
        res = res  % 4;
        if (res > 0 || errRow > 0 || errCol > 0) {
            tx1 = "Virhe";
        }
        if (res > 0) {
            tx1 = tx1 + ", kaksi samaa";
        }
        if (errRow > 0 || errCol > 0) {
            tx1 = tx1 + ", summa ei täsmää";
        }
        this.infoText.setText(tx1);
    }

    /**
    * Ajanotto
    *
    */
    private void startTimer() {
        this.timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> addSecond()
        ));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    private void addSecond() {
        if (!this.completed) {
            this.timerLabel.setText("Aika: " + Integer.toString(this.timeSeconds++) + "s");
        }
    }
    
    /**
    * Numerovalinnat näppäimistöltä
    *
    */
    private void keyPressEvents() {
        this.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DIGIT0) {
                setNumber(0);
            } else if (event.getCode() == KeyCode.DIGIT1) {
                setNumber(1);
            } else if (event.getCode() == KeyCode.DIGIT2) {
                setNumber(2);
            } else if (event.getCode() == KeyCode.DIGIT3) {
                setNumber(3);
            } else if (event.getCode() == KeyCode.DIGIT4) {
                setNumber(4);
            } else if (event.getCode() == KeyCode.DIGIT5) {
                setNumber(5);
            } else if (event.getCode() == KeyCode.DIGIT6) {
                setNumber(6);
            } else if (event.getCode() == KeyCode.DIGIT7) {
                setNumber(7);
            } else if (event.getCode() == KeyCode.DIGIT8) {
                setNumber(8);
            } else if (event.getCode() == KeyCode.DIGIT9) {
                setNumber(9);
            }
        }); 
    }
}