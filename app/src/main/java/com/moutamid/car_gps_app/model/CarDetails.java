package com.moutamid.car_gps_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CarDetails implements Parcelable {

    private String id;
    private String car;
    private String username;
    private String email;
    private String password;
    private String speed;
    private String location;
    private String distance;
    private String consumption;
    private String time;
    private double lat;
    private double lng;
    private String status;
    private String phone;

    public CarDetails(){

    }

    public CarDetails(String id, String car, String username, String email, String password,String phone, String speed,
                      String location, double lat, double lng, String distance, String consumption, String time, String status) {
        this.id = id;
        this.car = car;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.speed = speed;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
        this.consumption = consumption;
        this.time = time;
        this.status = status;
    }

    protected CarDetails(Parcel in) {
        id = in.readString();
        car = in.readString();
        username = in.readString();
        email = in.readString();
        password = in.readString();
        speed = in.readString();
        location = in.readString();
        distance = in.readString();
        consumption = in.readString();
        time = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        status = in.readString();
        phone = in.readString();
    }

    public static final Creator<CarDetails> CREATOR = new Creator<CarDetails>() {
        @Override
        public CarDetails createFromParcel(Parcel in) {
            return new CarDetails(in);
        }

        @Override
        public CarDetails[] newArray(int size) {
            return new CarDetails[size];
        }
    };

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(car);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(speed);
        parcel.writeString(location);
        parcel.writeString(distance);
        parcel.writeString(consumption);
        parcel.writeString(time);
        parcel.writeDouble(lat);
        parcel.writeDouble(lng);
        parcel.writeString(status);
        parcel.writeString(phone);
    }
}
