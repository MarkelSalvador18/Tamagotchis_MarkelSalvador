package org.cuatrovientos.Tamagotchis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		Scanner scn =  new Scanner(System.in);
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        System.out.println("Tamagotchi iniciado. Vida: 5 minutos.");

        scheduler.schedule(() -> {
            System.out.println("¡Han pasado 5 minutos! El tamagotchi da un aviso y muere.");
            scheduler.shutdown();
        }, 5, TimeUnit.MINUTES);

		
		Map<Tamagotchis, Thread> hilosTamagotchis = new HashMap<>();
		
	}

}
