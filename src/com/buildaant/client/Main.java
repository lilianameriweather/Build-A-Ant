package com.buildaant.client;

import com.apps.util.Prompter;
import com.buildaant.app.BuildAAntApp;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        BuildAAntApp app = new BuildAAntApp(new Prompter(new Scanner(System.in)));
        app.execute();
    }
}