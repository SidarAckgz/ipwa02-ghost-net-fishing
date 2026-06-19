package de.iu.ipwa02.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HomeController {

    public String getWelcomeText() {
        return "Ghost Net Fishing läuft.";
    }
}