package de.iu.ipwa02.controller;

import de.iu.ipwa02.dao.GhostNetDAO;
import de.iu.ipwa02.model.GhostNet;
import de.iu.ipwa02.model.GhostNetStatus;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class CompleteRecoveryController {

    private final GhostNetDAO ghostNetDAO = new GhostNetDAO();

    private Long selectedGhostNetId;

    public List<GhostNet> getGhostNetsInRecovery() {
        return ghostNetDAO.findGhostNetsInRecovery();
    }

    public String completeRecovery() {
        if (selectedGhostNetId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Bitte ein Geisternetz auswählen.",
                            null));
            return null;
        }

        GhostNet ghostNet = ghostNetDAO.findById(selectedGhostNetId);

        if (ghostNet == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Das ausgewählte Geisternetz wurde nicht gefunden.",
                            null));
            return null;
        }

        ghostNet.setStatus(GhostNetStatus.GEBORGEN);
        ghostNetDAO.update(ghostNet);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Das Geisternetz wurde erfolgreich als geborgen gemeldet.",
                        null));

        selectedGhostNetId = null;

        return null;
    }

    public Long getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public void setSelectedGhostNetId(Long selectedGhostNetId) {
        this.selectedGhostNetId = selectedGhostNetId;
    }
}
