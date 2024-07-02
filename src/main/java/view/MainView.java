package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

     private  JButton getWeatherDataBtn;
     private JTextField longitudeTf, latitudeTf;


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
        latitudeTf = new JTextField();
        longitudeTf = new JTextField();

        JLabel longitudeLabel = new JLabel("Longitude:");
        JLabel latitudeLabel = new JLabel("Latitude:");

        JLabel topLabel = new JLabel("Bitte geben Sie die Koordinaten des Ortes an");

        //einfügen
        topPanel.add(topLabel);

        centerPanel.setLayout( new GridLayout( 2, 2));
        centerPanel.setBorder( new EmptyBorder(5,5,5,5));
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

}
