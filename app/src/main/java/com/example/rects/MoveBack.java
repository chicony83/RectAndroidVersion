package com.example.rects;

public class MoveBack {
    private static boolean backwardAllowed = false; //проверяет выполнен ли ход назад

    public static boolean isBackwardAllowed() {
        return backwardAllowed;
    }

    public static void setBackwardAllowed(boolean bA) {
        MoveBack.backwardAllowed = bA;
    }
}
