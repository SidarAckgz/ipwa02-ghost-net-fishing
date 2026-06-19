package de.iu.ipwa02.controller;

import de.iu.ipwa02.dao.GhostNetDAO;
import de.iu.ipwa02.model.GhostNet;
import de.iu.ipwa02.model.Person;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ReportGhostNetController {

    private GhostNet ghostNet = new GhostNet();

    private boolean anonymous;
    private String reportingName;
    private String reportingPhoneNumber;

    private final GhostNetDAO ghostNetDAO = new GhostNetDAO();

    public String saveGhostNet() {
        if (!anonymous && (reportingName == null || reportingName.isBlank()
                || reportingPhoneNumber == null || reportingPhoneNumber.isBlank())) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Bitte Name und Telefonnummer angeben oder anonym melden.",
                            null));
            return null;
        }

        Person reportingPerson = new Person();

        if (anonymous) {
            reportingPerson.setAnonymous(true);
            reportingPerson.setName("Anonym");
            reportingPerson.setPhoneNumber("");
        } else {
            reportingPerson.setAnonymous(false);
            reportingPerson.setName(reportingName);
            reportingPerson.setPhoneNumber(reportingPhoneNumber);
        }

        ghostNet.setReportingPerson(reportingPerson);
        ghostNetDAO.save(ghostNet);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Geisternetz wurde erfolgreich gemeldet.",
                        null));

        ghostNet = new GhostNet();
        reportingName = "";
        reportingPhoneNumber = "";
        anonymous = false;

        return null;
    }

    public GhostNet getGhostNet() {
        return ghostNet;
    }

    public void setGhostNet(GhostNet ghostNet) {
        this.ghostNet = ghostNet;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
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