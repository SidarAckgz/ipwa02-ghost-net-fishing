package de.iu.ipwa02.controller;

import de.iu.ipwa02.dao.GhostNetDAO;
import de.iu.ipwa02.model.GhostNet;
import de.iu.ipwa02.model.GhostNetStatus;
import de.iu.ipwa02.model.Person;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class AssignRecoveryController {

    private final GhostNetDAO ghostNetDAO = new GhostNetDAO();

    private Long selectedGhostNetId;
    private String recoveringName;
    private String recoveringPhoneNumber;

    public List<GhostNet> getOpenGhostNets() {
        return ghostNetDAO.findOpenGhostNets();
    }

    public String assignRecovery() {
        if (selectedGhostNetId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Bitte ein Geisternetz auswählen.",
                            null));
            return null;
        }

        if (recoveringName == null || recoveringName.isBlank()
                || recoveringPhoneNumber == null || recoveringPhoneNumber.isBlank()) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Bitte Name und Telefonnummer der bergenden Person angeben.",
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

        Person recoveringPerson = new Person();
        recoveringPerson.setAnonymous(false);
        recoveringPerson.setName(recoveringName);
        recoveringPerson.setPhoneNumber(recoveringPhoneNumber);

        ghostNet.setRecoveringPerson(recoveringPerson);
        ghostNet.setStatus(GhostNetStatus.BERGUNG_BEVORSTEHEND);

        ghostNetDAO.update(ghostNet);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Die Bergung wurde erfolgreich übernommen.",
                        null));

        selectedGhostNetId = null;
        recoveringName = "";
        recoveringPhoneNumber = "";

        return null;
    }

    public Long getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public void setSelectedGhostNetId(Long selectedGhostNetId) {
        this.selectedGhostNetId = selectedGhostNetId;
    }

    public String getRecoveringName() {
        return recoveringName;
    }

    public void setRecoveringName(String recoveringName) {
        this.recoveringName = recoveringName;
    }

    public String getRecoveringPhoneNumber() {
        return recoveringPhoneNumber;
    }

    public void setRecoveringPhoneNumber(String recoveringPhoneNumber) {
        this.recoveringPhoneNumber = recoveringPhoneNumber;
    }
}