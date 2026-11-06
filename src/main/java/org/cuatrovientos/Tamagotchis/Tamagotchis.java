package org.cuatrovientos.Tamagotchis;

import java.time.Duration;

public class Tamagotchis {

	private int id;
	private String nombreTamagotchi;
	private int suciedad;
	private static final Duration TIEMPO_VIDA_TAMAGOTCHI = Duration.ofMinutes(5);
	public Tamagotchis(int id, String nombreTamagotchi, int suciedad) {
		super();
		this.id = id;
		this.nombreTamagotchi = nombreTamagotchi;
		this.suciedad = suciedad;
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
	public int getSuciedad() {
		return suciedad;
	}
	public void setSuciedad(int suciedad) {
		this.suciedad = suciedad;
	}
	public static Duration getTiempoVidaTamagotchi() {
		return TIEMPO_VIDA_TAMAGOTCHI;
	}
	
	
}
