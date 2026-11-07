package org.cuatrovientos.Tamagotchis;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Tamagotchis extends Thread implements Runnable {
	public enum Estado {
        OCIOSO, COMIENDO, JUGANDO, LIMPIANDO, MUERTO
    }
	

	private String nombreTamagotchi;
	private int suciedad;
	private Estado estadoActual;
	
	public Tamagotchis(String nombre) {
		this.nombreTamagotchi = nombre;
	}

    public Estado getEstado() {
    		this.estadoActual = accionAleatoria();
        return this.estadoActual;
    }

	public String getNombreTamagotchi() {
		return nombreTamagotchi;
	}
	public void setNombreTamagotchi(String nombreTamagotchi) {
		this.nombreTamagotchi = nombreTamagotchi;
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
	
	public void limpiar() {
		System.out.println(this.nombreTamagotchi + " se esta limpiando");
		
		this.estadoActual = Estado.LIMPIANDO;
		try {
				Thread.sleep(5_000);
		} catch (InterruptedException e) {
			System.out.println("Tamagotchi " + this.nombreTamagotchi + " fue interrumpido mientras se limpiaba.");
		} finally {
			System.out.println("Tamagotchi " + this.nombreTamagotchi + ": He terminado de limpiarme");
	        this.estadoActual = Estado.OCIOSO;
		}
		
		
	}
	boolean vivo;
	public void iniciarContadorSuciedad(Scanner teclado, Thread hiloTamagotchi) {
	        while (vivo && suciedad < 10) {
	            try {
	                Thread.sleep(20_000);
	                suciedad = GestorTamagotchis.SUMA_SUCIEDAD +1;
	                System.out.println(this.nombreTamagotchi + " - Suciedad: " + suciedad + "/10");

	                if (suciedad == 5) {
	                	System.out.println("Aviso " + this.nombreTamagotchi + " está empezando a estar muy sucio");
	                				System.out.print("Quieres limpiarlo? (s/n)");
	                				String respuestaCuidador = teclado.nextLine().toLowerCase();
	                				if (respuestaCuidador.equalsIgnoreCase("s")) {
	                					limpiar();
	                				}
	                }
	            } catch (InterruptedException e) {
	                break;
	            }
	        }
	        if (suciedad >= 10) {
	            morirPorSuciedad(hiloTamagotchi);
	        }
	}
	public void morirPorSuciedad(Thread hiloTamagotchi) {
		
        this.vivo = false;
        this.estadoActual = Estado.MUERTO;
        System.out.println(this.nombreTamagotchi + " ha muerto por suciedad");
		hiloTamagotchi.interrupt();	
	}
	
	public void contadorVida() {
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        System.out.println("Tamagotchi iniciado. Vida: 5 minutos");

        scheduler.schedule(() -> {
        		System.out.println("¡Han pasado 5 minutos! El tamagotchi da un aviso y muere");
        		scheduler.shutdown();
        }, 5, TimeUnit.MINUTES);
	}
	public void morir(String str) {
		if (str.contains("orden")) {
			
		}
	}
	
	
	
	private static final Estado[] ACCIONES = { Estado.COMIENDO, Estado.JUGANDO, Estado.LIMPIANDO };

	public static Estado accionAleatoria() {
	    return ACCIONES[ThreadLocalRandom.current().nextInt(ACCIONES.length)];
	}
	
	
	
	
	
	
}
