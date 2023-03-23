package com.rossi.javasimulatoratm;

import com.rossi.javasimulatoratm.service.AtmService;

public class Main {
    public static void main(String[] args) {
        AtmService service = new AtmService();
        service.welcomeScreen();
    }
}