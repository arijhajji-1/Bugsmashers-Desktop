package GUI;
import javax.swing.*;

import com.teamdev.jxmaps.swing.MapView;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import java.awt.*;
import com.teamdev.jxmaps.*;

public class Map extends MapView{

	private com.teamdev.jxmaps.Map map;
	public Map(String nom) {
		
		JFrame frame= new JFrame(nom);
		
		setOnMapReadyHandler(new MapReadyHandler()
		
		{
			@Override
			public void onMapReady(MapStatus status) {
				
				if(status==MapStatus.MAP_STATUS_OK )
				{
					map= getMap();
					MapOptions mapOptions= new MapOptions();
					MapTypeControlOptions controlsOption= new MapTypeControlOptions();
					mapOptions.setMapTypeControlOptions(controlsOption);
					
					map.setOptions(mapOptions);
					//coordonnées de la ville de tunis
					map.setCenter(new LatLng(36.80278,10.17972));
					//pour pouvoir zoomer sur la carte
					map.setZoom(11.0);
					
					Marker mark=new Marker(map);
					mark.setPosition(map.getCenter());
					
					
					Circle circle= new Circle(map);
					circle.setCenter(map.getCenter());
					circle.setRadius(400);
					
					
					CircleOptions co= new CircleOptions();
					co.setFillColor("#FF0000");
					co.setFillOpacity(0.35);
					
					circle.setOptions(co);
					
				
					
				}	
			    }
		        });
		
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(700,500);
		frame.setVisible(true);
		
		}

	 public static void main(String[] args) {
	         
		 Map temp= new Map("carte de livraison");
	    }

	
	    
}
