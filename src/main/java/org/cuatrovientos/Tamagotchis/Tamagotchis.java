package org.cuatrovientos.Tamagotchis;

import java.util.concurrent.ThreadLocalRandom;

public class Tamagotchis implements Runnable{


	private int id;
	private String nombreTamagotchi;

	public Tamagotchis(int id, String nombreTamagotchi, int suciedad) {
		super();
		this.id = id;
		this.nombreTamagotchi = nombreTamagotchi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreTamagotchi() {
		return nombreTamagotchi;
	}
	public void setNombreTamagotchi(String nombreTamagotchi) {
		this.nombreTamagotchi = nombreTamagotchi;
	}

	
	public void run() {
		
	}
	
	public int generarNumeroAleatorios1() {
		int numeroAleatorio1 = ThreadLocalRandom.current().nextInt(0, 10);
		return numeroAleatorio1;
		
	}
	public int generarNumeroAleatorios2() {
		int numeroAleatorio2 = ThreadLocalRandom.current().nextInt(0, 10);
		return numeroAleatorio2;
	}
	
	
}
