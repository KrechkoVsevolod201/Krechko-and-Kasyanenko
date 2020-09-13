package com.company;

public class Main {

    public static void main(String[] args) {
        int x, y, z, i = 0, j = 0;
        x = 1;
        y = 2;
        z = 20;
        for (i = 0; i < z; i ++){
            for (j = 0; j < z; j ++) {
                System.out.print(x);
                System.out.print(" ");
            }
            System.out.println(y);
        }
    }
}