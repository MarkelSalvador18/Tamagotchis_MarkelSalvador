package org.cuatrovientos.Tamagotchis;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Tamagotchis extends Thread implements Runnable{
	public enum Estado {
        OCIOSO, COMIENDO, JUGANDO, LIMPIANDO, MUERTO
    }
	

	private String nombreTamagotchi;
	private Estado estadoActual;
	
	public Tamagotchis(String nombreTamagotchi) {
		this.nombreTamagotchi = nombreTamagotchi;
	}
	public Tamagotchis() {}

    public Estado getEstado() {
        return this.estadoActual;
    }

	public String getNombreTamagotchi() {
		return nombreTamagotchi;
	}
	public void setNombreTamagotchi(String nombreTamagotchi) {
		this.nombreTamagotchi = nombreTamagotchi;
	}

	
	public void run() {
		
	}
	public void jugar(Scanner teclado) {
	    this.estadoActual = Estado.JUGANDO;
	    boolean acertado = false;

	    while (!acertado) {
	        int num1 = ThreadLocalRandom.current().nextInt(0, 10);
	        int num2 = ThreadLocalRandom.current().nextInt(0, 10);
	        int resultadoCorrecto = num1 + num2;

	        System.out.println(this.nombreTamagotchi + ": ¿Cuánto es " + num1 + " + " + num2 + "?");
	        System.out.print("Tu respuesta: ");

	        int respuestaCuidador = teclado.nextInt();

	        if (respuestaCuidador == resultadoCorrecto) {
	            System.out.println("Correcto " + this.nombreTamagotchi + " termina de jugar");
	            acertado = true;
	        } else {
	            System.out.println("Respuesta incorrecta. " + this.nombreTamagotchi + " sigue jugando");
	        }
	    }

	    this.estadoActual = Estado.OCIOSO;
	}
	
	public void comer() {
		int tiempoAleatorioGenerado = 0;
		
		this.estadoActual = Estado.COMIENDO;
		System.out.println(this.nombreTamagotchi + " Comienza a comer una sandia");
		try {
			while (tiempoAleatorioGenerado >= 5) {
				tiempoAleatorioGenerado = ThreadLocalRandom.current().nextInt(100, 1001);
				Thread.sleep(tiempoAleatorioGenerado);
				
			}
		} catch (InterruptedException e) {
			System.out.println("Tamagotchi " + this.nombreTamagotchi + " fue interrumpido mientras comía.");
		} finally {
			System.out.println("Tamagotchi " + this.nombreTamagotchi + ": He terminado de comer la sandia");
	        this.estadoActual = Estado.OCIOSO;
		}
		
	}
	
	
	
	
	
	
	
}
