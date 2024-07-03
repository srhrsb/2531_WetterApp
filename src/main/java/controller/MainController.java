package controller;

import dao.RestAPIDAO;
import view.MainView;

import java.awt.event.ActionEvent;

public class MainController {

    private MainView view;

    public MainController( MainView view ){
        this.view = view;
        view.addButtonHandler( this::getWeatherData );

        RestAPIDAO dao = new RestAPIDAO();
        dao.getWeatherData(49,8);
    }

    public static void main(String[] args) {
       new MainController( new MainView( 500, 250 ));
    }

    private void getWeatherData( ActionEvent event){
        System.out.println( "Action: "+event.getActionCommand() );





    }




}