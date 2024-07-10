package controller;

import dao.RestAPIDAO;
import model.Locations;
import model.WeatherData;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainController {

    private MainView view;
    private RestAPIDAO dao = new RestAPIDAO();

    public MainController( MainView view ){
        this.view = view;
        view.addButtonHandler( this::getWeatherData );
    }

    public static void main(String[] args) {
       new MainController( new MainView( 500, 250 ));
    }

    private void getWeatherData( ActionEvent event){
        System.out.println( "Action: "+event.getActionCommand() );

        double[] coords = getLocationByPreset();

        dao.getWeatherData( coords[0], coords[1], this::handleWeatherData );
    }

    private double[] getLocationByPreset(){

        Locations locationPreset = view.getLocationPreset();
        double[] location = new double[]{0,0};

        if(locationPreset == Locations.NONE){
            view.getCoords();
        }
        else {
            switch (locationPreset) {
                case BERLIN:
                    location = new double[]{13.4115, 52.5235};
                    break;
                case WIEN:
                    location = new double[]{16.3728, 48.2092};
                    break;
                case WARSCHAU:
                    location = new double[]{21.0122, 52.2297};
                    break;
                case STOCKHOLM:
                    location = new double[]{18.0645, 59.3328};
                    break;
                case PARIS:
                    location = new double[]{2.3510, 48.8567};
                    break;
            }
        }

        return location;

    }

    private void handleWeatherData(WeatherData weatherData){
        System.out.println( weatherData.getTemperature() );

        String weatherText = "Aktuelles Wetter:\n";
        weatherText +="Longitude:           " + weatherData.getLongitude() +"\n";
        weatherText +="Latitude:            " + weatherData.getLatitude() +"\n";
        weatherText +="Temperatur:          " + weatherData.getTemperature() +"\n";
        weatherText +="Regen:               " + weatherData.getRain() +"\n";
        weatherText +="Windgeschwindigkeit: " + weatherData.getWind() +"\n";

       view.showInfoWindow( weatherText );
    }


}