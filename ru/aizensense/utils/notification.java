/*
 * Decompiled with CFR 0.150.
 */
package ru.aizensense.utils;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

public class notification {
    public static void main(String text) throws Exception {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("images/bor.png");
            TrayIcon trayIcon = new TrayIcon(image);
            tray.add(trayIcon);
            trayIcon.displayMessage("AizenSense", text, TrayIcon.MessageType.INFO);
        }
    }
}

