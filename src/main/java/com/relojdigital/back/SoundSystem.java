package com.relojdigital.back;

import java.io.File;

public class SoundSystem {
    public static File getSystemSound() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return new File("C:\\Windows\\Media\\ding.wav"); // Ruta al sonido predeterminado de Windows
        } else {
            // Aquí, simplemente devolvemos null para que no se reproduzca ningún sonido por
            // defecto en otros sistemas.
            return null;
        }
    }
}
