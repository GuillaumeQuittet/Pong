package com.gquittet.pong.logs;

public class Log {

    public static void info(String message) {
        System.out.println("[Info]: " + message + ".");
    }

    public static void error(String message) {
        System.err.println("[Error]: " + message + "!");
    }

    public static void fatal(String message) {
        System.err.println("[Fatal]: " + message + "!");
        System.exit(-1);
    }

}