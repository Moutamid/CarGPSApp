package com.moutamid.car_gps_app.model;

public class Report {

    private String id;
    private String report_name;
    private String vehicle;
    private String start_date;
    private String end_date;

    public Report(){

    }

    public Report(String id, String report_name, String vehicle, String start_date, String end_date) {
        this.id = id;
        this.report_name = report_name;
        this.vehicle = vehicle;
        this.start_date = start_date;
        this.end_date = end_date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
