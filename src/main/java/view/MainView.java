package view;

import model.Locations;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

     private  JButton getWeatherDataBtn;
     private JTextField longitudeTf, latitudeTf;

     private JComboBox locationPresetMenu;

     public MainView( int width, int height ){

         setSize( width, height );
         setFont( new Font("Arial", Font.PLAIN, 20));
         setTitle("Wetter App");
         setDefaultCloseOperation( DISPOSE_ON_CLOSE );
         addUIComponents();
         setVisible(true);
     }


    /**
     * Hinzufuegen von UI-Elementen
     */
    private void addUIComponents( ){

        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        getWeatherDataBtn = new JButton("Wetterdaten laden");
        locationPresetMenu = new JComboBox(Locations.values());
        latitudeTf = new JTextField();
        longitudeTf = new JTextField();

        JLabel presetLabel = new JLabel("Preset:");
        JLabel longitudeLabel = new JLabel("Longitude:");
        JLabel latitudeLabel = new JLabel("Latitude:");

        JLabel topLabel = new JLabel("Bitte geben Sie die Koordinaten des Ortes an");

        //einfügen
        topPanel.add(topLabel);

        centerPanel.setLayout( new GridLayout( 3, 2));
        centerPanel.setBorder( new EmptyBorder(5,5,5,5));

        centerPanel.add(presetLabel);
        centerPanel.add(locationPresetMenu);
        centerPanel.add(longitudeLabel);
        centerPanel.add(longitudeTf);
        centerPanel.add(latitudeLabel);
        centerPanel.add(latitudeTf);

        bottomPanel.add(getWeatherDataBtn);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
     }

     public void addButtonHandler(ActionListener listener){
        getWeatherDataBtn.addActionListener(listener);
     }


     public double[] getCoords(){

        double longitude = 0;
        double latitude = 0;

        try{
            longitude = Double.parseDouble( longitudeTf.getText() );
            latitude = Double.parseDouble( latitudeTf.getText() );

            longitude = Math.clamp(longitude, -180, 180);
            latitude = Math.clamp(latitude, -180, 180);


        }catch( Exception e){
            throw new RuntimeException(e);
        }

        return new double[]{longitude, latitude};
     }

     public Locations getLocationPreset(){
        var item = locationPresetMenu.getSelectedItem();
         System.out.println(item);
        return (Locations) item;
     }


     public void showInfoWindow( String text ){
        JOptionPane.showMessageDialog( this, text, "Info", JOptionPane.INFORMATION_MESSAGE );
     }


}
