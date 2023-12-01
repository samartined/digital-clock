package com.relojdigital;

import com.relojdigital.interfaz.MyFrame;

public class App {

    public static void main(String[] args) {
        // Punto de entrada de la aplicación
        launchApp();
    }

    private static void launchApp() {
        // Inicializa y muestra la ventana principal
        new MyFrame();
    }
}
