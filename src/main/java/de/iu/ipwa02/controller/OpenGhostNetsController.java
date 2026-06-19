package de.iu.ipwa02.controller;

import de.iu.ipwa02.dao.GhostNetDAO;
import de.iu.ipwa02.model.GhostNet;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class OpenGhostNetsController {

    private final GhostNetDAO ghostNetDAO = new GhostNetDAO();

    public List<GhostNet> getOpenGhostNets() {
        return ghostNetDAO.findOpenGhostNets();
    }
}