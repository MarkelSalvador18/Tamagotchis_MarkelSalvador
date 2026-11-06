package org.cuatrovientos.Tamagotchis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.cuatrovientos.Tamagotchis.Tamagotchis.Estado;

public class Main {

	public static void main(String[] args) {
		Scanner scn =  new Scanner(System.in);
		try {
			ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

	        System.out.println("Tamagotchi iniciado. Vida: 5 minutos.");

	        scheduler.schedule(() -> {
	            System.out.println("¡Han pasado 5 minutos! El tamagotchi da un aviso y muere.");
	            scheduler.shutdown();
	        }, 5, TimeUnit.MINUTES);

	        Map<Integer, Tamagotchis> mapaTamagotchis = new HashMap<>();

	        for (int i = 0; i < GestorTamagotchis.NUMERO_TAMAGOTCHIS; i++) {
	            Tamagotchis tama = new Tamagotchis(); 
	            mapaTamagotchis.put(tama.getId(), tama);
	            Thread hilo = new Thread(tama);
	        }

	        for (Tamagotchis t : mapaTamagotchis.values()) {
	            System.out.println("Tamagotchi ID " + t.getId() + " está en estado: " + t.getEstado());
	            if (t.getEstado() == Estado.JUGANDO) {
	            t.jugar(scn);
	            }
	            
	            if (t.getEstado() == Estado.COMIENDO) {
	            	
	            }
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
