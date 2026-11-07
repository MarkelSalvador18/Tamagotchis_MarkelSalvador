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
			
			Map<String, Tamagotchis> mapaTamagotchis = new HashMap<>();
			for (int i = 1; i < GestorTamagotchis.NUMERO_TAMAGOTCHIS; i++) {
        		String nombreTamagotchi = "";
        		System.out.print("Como se va a llamar tu " + i + " tamagotchi?: ");
        		nombreTamagotchi = scn.nextLine();
        	
            Tamagotchis tama = new Tamagotchis(nombreTamagotchi); 
            
            mapaTamagotchis.put(tama.getNombreTamagotchi(), tama);
            Thread hilo = new Thread(tama);
            
            tama.getEstado();
            tama.iniciarContadorSuciedad();
            
            tama.contadorVida();
			}
	        

	            for (Tamagotchis t : mapaTamagotchis.values()) {
	            		System.out.println(t.getNombreTamagotchi() + " está en estado: " + t.getEstado());
	            
	            		if (t.getEstado() == Estado.JUGANDO) {
	            			t.jugar(scn);
	            		}
	            
	            		if (t.getEstado() == Estado.COMIENDO) {
	            		t.comer();
	            		}
	            		
	            		if (t.getEstado() == Estado.LIMPIANDO) {
	            			t.limpiar();
	            			
	            		}
	            }
	        
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
