package de.iu.ipwa02.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GhostNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    private double estimatedSize;

    @Enumerated(EnumType.STRING)
    private GhostNetStatus status = GhostNetStatus.GEMELDET;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person reportingPerson;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person recoveringPerson;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person missingReportingPerson;

    public GhostNet() {
    }

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(double estimatedSize) {
        this.estimatedSize = estimatedSize;
    }

    public GhostNetStatus getStatus() {
        return status;
    }

    public void setStatus(GhostNetStatus status) {
        this.status = status;
    }

    public Person getReportingPerson() {
        return reportingPerson;
    }

    public void setReportingPerson(Person reportingPerson) {
        this.reportingPerson = reportingPerson;
    }

    public Person getRecoveringPerson() {
        return recoveringPerson;
    }

    public void setRecoveringPerson(Person recoveringPerson) {
        this.recoveringPerson = recoveringPerson;
    }

    public Person getMissingReportingPerson() {
        return missingReportingPerson;
    }

    public void setMissingReportingPerson(Person missingReportingPerson) {
        this.missingReportingPerson = missingReportingPerson;
    }
}