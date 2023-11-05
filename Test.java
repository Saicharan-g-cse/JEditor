package com.jeditor;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

interface List {
    public default int get(int index) {
        return 10;
    }
    public default int size() {
        return 10;
    }
}

interface Stack extends List {
    public void push();
    public void pop();
}

abstract class Queue implements List {
    public abstract void enqueue();
    public abstract void dequeue();
}

public class Test extends Exception implements Runnable, ActionListener {
    public static final int MAX = Integer.MAX_VALUE;
    private static volatile boolean LOCK = false;
    protected transient int variable = 10;

    public static void main(String[] args) throws Exception {
        boolean wow = true;
        int x = 0;
        while (true) {
            if (x++ == 5)
                break;
            if (x == 3)
                continue;
        }
        for (byte i = 0 ; i < 255 ; i++) {
            System.out.println(i);
        }
        try {
            switch (10) {
                case 11:
                    break;
                case 10:
                    System.out.println(10);
                    break;
                default:
                    System.out.println("Default");
                    break;
            }
            throw new ArithmeticException("Division by zero");
        }
        catch (Exception e) {
            int xx = 0;
            do {
                System.out.println("do");
            }
            while (xx++ < 10);
        }
        finally {
            System.out.println("Finally");
        }
    }

    @Override
    public void run() {
        synchronized(this) {
            // Synchronized...
        }
    }

    @Override
    public synchronized void actionPerformed(ActionEvent event) {
        // Comment...
    }
}
