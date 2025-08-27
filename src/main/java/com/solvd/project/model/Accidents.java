package com.solvd.project.model;

import java.time.LocalDateTime;

public class Accidents {
    private int idAccidents;
    private LocalDateTime date;
    private String severity;
    private String location;
    private String weather;

    public Accidents(int idAccidents, LocalDateTime date, String severity, String location, String weather) {
        this.idAccidents = idAccidents;
        this.date = date;
        this.severity = severity;
        this.location = location;
        this.weather = weather;
    }

    public int getIdAccidents() {
        return idAccidents;
    }

    public void setIdAccidents(int idAccidents) {
        this.idAccidents = idAccidents;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
