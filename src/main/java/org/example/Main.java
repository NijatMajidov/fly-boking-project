package org.example;

import org.example.helper.ConsoleApp;
import org.example.util.DataInitializer;

public class Main {
    public static void main(String[] args) {
        DataInitializer.initializeDataFiles();
        new ConsoleApp().start();
    }
}