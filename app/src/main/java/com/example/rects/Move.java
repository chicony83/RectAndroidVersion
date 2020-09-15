package com.example.rects;

public class Move {
    int width = SettingGame.getWIDTH();
    int height = SettingGame.getHEIGHT();
    int rectSize = SettingGame.getSideSizeOfRect();

    private static long moveTime;                           //время хода

    public void moveUpToDown(int x){

        Move.actionBeforeMove();

        x = x/rectSize;
        int newI = 1;
        int h = height - 2; //высота игрового поля
        int c,y;
        int forSetX,forSetY;
        A:
        for ( int i = 1; i < h ; i++ ){
            if(AreaForGame.getArea(x,i) == 0){
                if(AreaForGame.getArea(x,1) == 0){
                    newI++;
                    if(AreaForGame.getArea(x,newI) > 0){
                        c = AreaForGame.getArea(x,0);
                        y = newI - 1;
                        forSetX = x;
                        forSetY = 0;


                        Move.actionMove(x,y,c,forSetX,forSetY);

                        new Find();

                        break A;
                    }
                }
            }
        }
    }

    public void moveDownToUp(int x) {
        Move.actionBeforeMove();
        x = x/rectSize;
        int newI = SettingGame.getHEIGHT()-2;
        int h = SettingGame.getHEIGHT()-2;
        int c,y;
        int forSetX,forSetY;
        A:
        for(int i = h ; i > 1 ; i--){
            if(AreaForGame.getArea(x,i) == 0){
                if(AreaForGame.getArea(x,h) == 0){
                    newI--;
                    if(AreaForGame.getArea(x,newI)>0){
                        c = AreaForGame.getArea(x,(height-1));
                        y = newI + 1;
                        forSetX = x;
                        forSetY = height - 1;

                        Move.actionMove(x,y,c,forSetX,forSetY);
                        new Find();
                        break A;
                    }
                }
            }
        }
    }

    public void moveLeftToRight(int y) {

        Move.actionBeforeMove();

        y = y/rectSize;
        int newI = 1;
        int h = SettingGame.getWIDTH() - 2;
        int c,x;
        int forSetX,forSetY;
        A:
        for ( int i = 1 ; i < h ; i++ ){
            if(AreaForGame.getArea(i,y) == 0){
                if(AreaForGame.getArea(1,y) == 0){
                    newI++;
                    if(AreaForGame.getArea(newI,y) > 0){
                        c = AreaForGame.getArea(0,y);
                        x = newI - 1;
                        forSetX = 0;
                        forSetY = y;

                        Move.actionMove(x,y,c,forSetX,forSetY);
                        new Find();
                        break A;
                    }
                }
            }
        }
    }


    public void moveRightToLeft(int y) {

        Move.actionBeforeMove();

        y = y/rectSize;
        int newI = SettingGame.getWIDTH() - 2 ;
        int h = SettingGame.getWIDTH() - 2;
        int c,x;
        int forSetX,forSetY;
        A:
        for(int i = h ; i > 1 ; i--){
            if(AreaForGame.getArea(i,y)==0){
                if(AreaForGame.getArea(h,y)==0){
                    newI--;
                    if (AreaForGame.getArea(newI,y)>0){
                        c = AreaForGame.getArea((width) - 1 , y );
                        x = newI + 1;
                        forSetX = height-1;
                        forSetY = y;

                        Move.actionMove(x,y,c,forSetX,forSetY);

                        new Find();
                        break A;
                    }
                }
            }
        }
    }
    private static void actionBeforeMove() {

        moveTime = GameActivity.getTime();
//        Log.i("TAG", "moveTime:" + moveTime);

        AreaForGame.saveMove();
    }
    private static void actionMove(int x,int y,int c,int forSetX,int forSetY) {
        AreaForGame.setGameArea(x,y,c);

        AreaForGame.setAreaRandomNumToCell(forSetX,forSetY);
        AreaForGame.nextColor();
        AreaForClearing.setLastMove(x, y);              //указываем куда мы сделали ход
        Information.addMove();
        MoveBack.setBackwardAllowed(true);


    }
    public static void actionAfterMove() {
        AreaForClearing.cleanAreaForClearing();
        AreaForGame.countHowManyRectanglesToClear();
        AreaForGrayTargetLine.grayLineSearch();
    }

    public static long getMoveTime() {
        return moveTime;
    }

    public static void startMoveTime(long moveTime) {
        Move.moveTime = moveTime;
    }

}
