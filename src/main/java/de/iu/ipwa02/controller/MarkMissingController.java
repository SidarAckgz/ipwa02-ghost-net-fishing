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
public class MarkMissingController {

    private final GhostNetDAO ghostNetDAO = new GhostNetDAO();

    private Long selectedGhostNetId;
    private String reportingName;
    private String reportingPhoneNumber;

    public List<GhostNet> getGhostNetsNotRecovered() {
        return ghostNetDAO.findGhostNetsNotRecovered();
    }

    public String markAsMissing() {
        if (selectedGhostNetId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Bitte ein Geisternetz auswählen.",
                            null));
            return null;
        }

        if (reportingName == null || reportingName.isBlank()
                || reportingPhoneNumber == null || reportingPhoneNumber.isBlank()) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Bitte Name und Telefonnummer angeben. Eine Verschollenmeldung darf nicht anonym erfolgen.",
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

        Person missingReportingPerson = new Person();
        missingReportingPerson.setAnonymous(false);
        missingReportingPerson.setName(reportingName);
        missingReportingPerson.setPhoneNumber(reportingPhoneNumber);

        ghostNet.setMissingReportingPerson(missingReportingPerson);
        ghostNet.setStatus(GhostNetStatus.VERSCHOLLEN);

        ghostNetDAO.update(ghostNet);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Das Geisternetz wurde erfolgreich als verschollen gemeldet.",
                        null));

        selectedGhostNetId = null;
        reportingName = "";
        reportingPhoneNumber = "";

        return null;
    }

    public Long getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public void setSelectedGhostNetId(Long selectedGhostNetId) {
        this.selectedGhostNetId = selectedGhostNetId;
    }

    public String getReportingName() {
        return reportingName;
    }

    public void setReportingName(String reportingName) {
        this.reportingName = reportingName;
    }

    public String getReportingPhoneNumber() {
        return reportingPhoneNumber;
    }

    public void setReportingPhoneNumber(String reportingPhoneNumber) {
        this.reportingPhoneNumber = reportingPhoneNumber;
    }
}