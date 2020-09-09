package ticTacToe;

public class Player {

    private Mark mark;
    private boolean win;

    public Player (Mark mark){
        this.mark = mark;
        this.win = false;
    }

    public String getMark() {
        return "" + mark;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin() {
        this.win = true;
    }

    public void switchPlayer(){
        if (this.mark == Mark.X){
            this.mark = Mark.O;
        } else {
            this.mark = Mark.X;
        }
    }
}