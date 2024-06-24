package model;

public class WeatherData {
    private double longitude;
    private double latitude;
    private double temperature;
    private double rain;
    private double wind;

    public WeatherData(double longitude, double latitude, double temperature, double rain, double wind) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.rain = rain;
        this.wind = wind;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }
}
