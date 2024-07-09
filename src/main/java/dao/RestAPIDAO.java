package dao;

import model.WeatherData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RestAPIDAO{

    private Consumer<WeatherData> onSuccessCallback;
    public void getWeatherData(double longitude, double latitude, Consumer<WeatherData> onSuccessCallback  ){
        sendRequest(latitude, longitude);
        this.onSuccessCallback = onSuccessCallback;
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

        if(response.statusCode() == 200 ){
            var weatherData = getWeatherDataFromJson( response.body() );
            onSuccessCallback.accept( weatherData );
        }
        else{
            System.err.println("API response failed");
        }
    }

    /**
     * Json umwandeln in Java Objekt WeatherData
     * @param json
     * @return Wetterdatenobjekt
     */
    private WeatherData getWeatherDataFromJson( String json ){

        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(json);

            double longitude = (double) jsonObject.get("longitude");
            double latitude = (double) jsonObject.get("latitude");

            JSONObject current = (JSONObject) jsonObject.get("current");
            double temperature = (double) current.get("temperature_2m");
            double rain = (double) current.get("rain");
            double wind = (double) current.get("wind_speed_10m");

            WeatherData weatherData = new WeatherData(longitude, latitude,temperature,rain, wind );
            return weatherData;

        }
        catch( Exception e ){
            throw new RuntimeException(e);
        }
    }
}
