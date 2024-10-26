import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Minesweeper {

    private int row, col, m = 0;
    private ArrayList<ArrayList<Mines>> board = new ArrayList<>();
    private final Random rand = new Random();
    private int status = 0; //0 run, 1 win, 2 lost
    private int totalMines = 0;

    private int totalMoves = 0;


    Minesweeper(int row, int col, int mines){
        this.row = row;
        this.col = col;
        this.m = mines;
        CreateBoard(this.row, this.col);
        set(0,1,Mines.ONE);
        mines(m);
        numberSet();
//        set(0,0,1);
//        flag(0,1);
//        selection(2,2);
//        printBoard();

    }

    private void CreateBoard(int row, int col){

        for(int i = 0; i < row; i++){
            ArrayList<Mines> Arow = new ArrayList<>();
            for(int j = 0; j < col; j++){
                Arow.add(Mines.DEFAULT);
            }
            this.board.add(Arow);
        }

        this.board.get(0).set(0,Mines.BOMB);

    }

    private void set(int row, int col, Mines c){
        if (row >= 0 && row <= board.size() && col >= 0 && col < board.get(row).size()){
            board.get(row).set(col, c);
        }
        else{
            throw new IndexOutOfBoundsException("Invalid row or col index");
        }
    }
    private Mines get(int row, int col){
        if (row >= 0 && row <= board.size() && col >= 0 && col < board.get(row).size()){
            return board.get(row).get(col);
        }
        else{
            throw new IndexOutOfBoundsException("Invalid row or col index");
        }
    }

    public void printBoard() {
        for (ArrayList<Mines> row: board){
            for (Mines value: row){
                //System.out.print(value);
                switch (value) {
                    case DEFAULT -> System.out.print("#" + " ");
                    case BOMB -> System.out.print("#" + " "); //Data to be used for game systems
                    case CLEAR -> System.out.println("_" + " "); //used for 0 tiles, and uncover
                    case ONE -> System.out.print("1" + " ");
                    case TWO -> System.out.print("2" + " ");
                    case THREE -> System.out.print("3" + " ");
                    case FOUR -> System.out.print("4" + " ");
                    case FIVE -> System.out.print("5" + " ");
                    case SIX -> System.out.print("6" + " ");
                    case SEVEN -> System.out.print("7" + " ");
                    case EIGHT -> System.out.print("8" + " ");
                    case IGNITE -> System.out.print('*' + " "); //will be used if the player uncovers Bombs
                    default -> System.out.print("#" + " "); //UH OH

                }
            }
            System.out.println();
        }
    }

// OLD SYSTEM. FLAW UNORGANIZED
    private void mines(int m) {
        int selection;

        while (totalMines < m){
            if (minesSubFunction() == 0){
                totalMines += 1;
            }
        }



    }

    private int minesSubFunction(){
        Mines selection;
        int row = rand.nextInt(0, board.size());
        int col = rand.nextInt(0,board.get(row).size());
        selection = get(row,col);
        if(selection == Mines.BOMB){
            //System.out.println("Overlap");
            return -1;
        }
        set(row,col, Mines.BOMB);
        return 0;
    }
//
//    public void flag(int row, int col){
//        set(row, col, -1);
//    }
//

//
    public void numberSet(){ //setting field with bombs.
        Mines selection;
        Mines temp = Mines.DEFAULT;
        int tempNum = 0;

        //check right
        for (int row = 0; row < board.size(); row++ ){
            for (int col = 0; col < board.get(row).size(); col++){
                selection = board.get(row).get(col);
                if(selection == Mines.BOMB){
                    //check right
                    if(col < board.get(row).size() - 1){
                        if(get(row,col + 1) != Mines.BOMB){
                            temp = get(row,col + 1);
                            tempNum = getNumFromEnum(temp);
                            set(row,col + 1,setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }

                    }

                    //check left
                    if(col > 0){
                        if(get(row,col-1) != Mines.BOMB){
                            temp = get(row,col - 1);
                            tempNum = getNumFromEnum(temp);
                            set(row,col - 1,setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }
                    }
//                    //check up
                    if(row > 0){
                        if(get(row -1,col) != Mines.BOMB ){
                            temp = get(row - 1,col);
                            tempNum = getNumFromEnum(temp);
                            set(row - 1, col, setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }
                    }
//                    // check down
                    if (row < board.size() - 1){
                        if(get(row + 1,col) != Mines.BOMB ){
                            temp = get(row + 1,col);
                            tempNum = getNumFromEnum(temp);
                            set(row + 1, col, setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }
                    }
//
//                    // up left and down left
//
//                    //up left
                    if (row > 0 && col > 0){
                        if (get(row - 1, col - 1) != Mines.BOMB){
                            temp = get(row - 1,col - 1);
                            tempNum = getNumFromEnum(temp);
                            set(row - 1, col - 1, setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }
                    }
//
//                    //down left
                    if (row < board.size() - 1 && col > 0){
                        if (get(row + 1, col - 1) != Mines.BOMB){
                            temp = get(row + 1,col - 1);
                            tempNum = getNumFromEnum(temp);
                            set(row + 1, col - 1, setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }
                    }

                    // up right and down right

                    //up right
                    if (row > 0 && col < board.get(row).size() -1 ){
                        if (get(row - 1, col + 1) !=  Mines.BOMB) {
                            temp = get(row - 1, col + 1);
                              tempNum = getNumFromEnum(temp);
                            set(row - 1, col + 1, setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }
                    }

                    //down right
                    if (row < board.size() - 1 && col < board.get(row).size() -1 ){
                        if (get(row + 1, col + 1) !=  Mines.BOMB) {
                            temp = get(row + 1, col + 1);
                              tempNum = getNumFromEnum(temp);
                            set(row + 1, col + 1, setNumToEnumHidden(tempNum + 1));
                            temp = Mines.DEFAULT;
                            tempNum = 0;
                        }
                    }

                }
            }

        }



    }

    public void selection(int row, int col){
        Mines select = Mines.DEFAULT;
        if (row > this.row && row >= 0 && col >= 0 && board.get(row).size() > col){
            select = get(row,col);
        }
    }

    private int getNumFromEnum(Mines m){
        switch (m){
            case ONE -> {
                return 1;
            }
            case TWO -> {
                return 2;
            }
            case THREE -> {
                return 3;
            }
            case FOUR -> {
                return 4;
            }
            case FIVE -> {
                return 5;
            }
            case SIX -> {
                return 6;
            }
            case SEVEN -> {
                return 7;
            }
            case EIGHT -> {
                return 8;
            }
        }
        return 0;
    }
    private Mines setNumToEnumHidden(int x){
        switch (x){
            case 1 -> {
                return Mines.HONE;
            }
            case 2 -> {
                return Mines.HTWO;
            }
            case 3 -> {
                return Mines.HTHREE;
            }
            case 4 -> {
                return Mines.HFOUR;
            }
            case 5 -> {
                return Mines.HFIVE;
            }
            case 6 -> {
                return Mines.HSIX;
            }
            case 7 -> {
                return Mines.HSEVEN;
            }
            case 8 -> {
                return Mines.HEIGHT;
            }
        }
        return Mines.DEFAULT;
    }
    private Mines setNumToEnum(int x){
        switch (x){
            case 1 -> {
                return Mines.ONE;
            }
            case 2 -> {
                return Mines.TWO;
            }
            case 3 -> {
                return Mines.THREE;
            }
            case 4 -> {
                return Mines.FOUR;
            }
            case 5 -> {
                return Mines.FIVE;
            }
            case 6 -> {
                return Mines.SIX;
            }
            case 7 -> {
                return Mines.SEVEN;
            }
            case 8 -> {
                return Mines.EIGHT;
            }
        }
        return Mines.DEFAULT;
    }

}
