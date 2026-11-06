package org.cuatrovientos.Tamagotchis;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Tamagotchis implements Runnable{
	public enum Estado {
        OCIOSO, COMIENDO, JUGANDO, LIMPIANDO, MUERTO
    }
	

	private int id;
	private String nombreTamagotchi;
	private Estado estadoActual;
	
	public Tamagotchis(int id, String nombreTamagotchi, int suciedad) {
		super();
		this.id = id;
		this.nombreTamagotchi = nombreTamagotchi;
	}
	public Tamagotchis() {}

    public Estado getEstado() {
        return this.estadoActual;
    }

	public int getId() {
		return id = +1;
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
	        int num1 = ThreadLocalRandom.current().nextInt(0, 9);
	        int num2 = ThreadLocalRandom.current().nextInt(0, 10 - num1);
	        int resultadoCorrecto = num1 + num2;

	        System.out.println("[" + this.id + "] ¿Cuánto es " + num1 + " + " + num2 + "?");
	        System.out.print("Tu respuesta: ");

	        int respuestaCuidador = teclado.nextInt();

	        if (respuestaCuidador == resultadoCorrecto) {
	            System.out.println("¡Correcto! " + this.id + " termina de jugar.\n");
	            acertado = true;
	        } else {
	            System.out.println("Respuesta incorrecta. " + this.id + " sigue jugando...\n");
	        }
	    }

	    this.estadoActual = Estado.OCIOSO;
	}
	
	
	
}
