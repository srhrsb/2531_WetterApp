package controller;

import dao.RestAPIDAO;
import model.WeatherData;
import view.MainView;

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

        double longitude = 8;
        double latitude = 49;

        dao.getWeatherData( longitude, latitude, this::handleWeatherData );
    }

    private void handleWeatherData(WeatherData weatherData){
        System.out.println( weatherData.getTemperature() );
    }
}