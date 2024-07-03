package dao;

import model.WeatherData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class RestAPIDAO{

    public WeatherData getWeatherData( double longitude, double latitude ){
        sendRequest(latitude, longitude);
        return null;
    }

    /**
     * Get Request zu open-metheo.com
     */
    private void sendRequest( double latitude, double longitude ){
       String uriString = "https://api.open-meteo.com/v1/forecast?latitude="+ latitude +
                          "&longitude="+longitude+
                          "&current=temperature_2m,rain,wind_speed_10m" +
                          "&timezone=Europe%2FBerlin&forecast_days=1";

      try {
          HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder( URI.create(uriString) ).build();
          CompletableFuture<HttpResponse<String>> future = client.sendAsync( request,HttpResponse.BodyHandlers.ofString() );
          future.thenAccept( this::handleResponse );



      }
      catch( Exception e){
          throw new RuntimeException(e);
      }


    }

    /**
     * Auswerten und Behandeln des API-Response
     */
    private void handleResponse( HttpResponse<String> response){

        System.out.println( response.body() );

    }

    /**
     * Json umwandeln in Java Objekt WeatherData
     * @param json
     * @return Wetterdatenobjekt
     */
    private WeatherData getWeatherDataFromJson( String json ){

        return null;
    }
}
