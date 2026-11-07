package org.cuatrovientos.Tamagotchis;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Tamagotchis extends Thread implements Runnable{
	public enum Estado {
        OCIOSO, COMIENDO, JUGANDO, LIMPIANDO, MUERTO
    }
	

	private String nombreTamagotchi;
	private int suciedad;
	private Estado estadoActual;
	
	public Tamagotchis(String nombreTamagotchi) {
		this.nombreTamagotchi = nombreTamagotchi;
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
	
	public void limpiar() {
		//TODO hacer una escala de 0 a 10 por cada hilo, cuando pasen 20 segundos se 
		// sumara una cantidad de suciedad, + 1, cuando se limpie seran 5 segundos
		// de duracion y la cantidad volvera a 0, si la suciedad llega a 5, un mensaje
		// por pantalla, si llega a 10, muere
		
		
	}
	boolean vivo;
	public void iniciarContadorSuciedad() {
	    
	        while (vivo && suciedad < 10) {
	            try {
	                Thread.sleep(20_000);
	                suciedad = suciedad +1;
	                System.out.println(this.nombreTamagotchi + " - Suciedad: " + suciedad + "/10");

	                if (suciedad == 5) {
	                    System.out.println("Aviso " + this.nombreTamagotchi + " está empezando a estar muy sucio.");
	                }
	            } catch (InterruptedException e) {
	                break;
	            }
	        }

	        if (suciedad >= 10) {
	            System.out.println(this.nombreTamagotchi + " ha muerto por suciedad.");
	            this.vivo = false;
	            this.estadoActual = Estado.MUERTO;
	        }
	}
	
	public void contadorVida() {
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        System.out.println("Tamagotchi iniciado. Vida: 5 minutos.");

        scheduler.schedule(() -> {
        		System.out.println("¡Han pasado 5 minutos! El tamagotchi da un aviso y muere.");
        		scheduler.shutdown();
        }, 5, TimeUnit.MINUTES);
	}
	
	private static final Estado[] ACCIONES = { Estado.COMIENDO, Estado.JUGANDO, Estado.LIMPIANDO };

	public static Estado accionAleatoria() {
	    return ACCIONES[ThreadLocalRandom.current().nextInt(ACCIONES.length)];
	}
	
	
	
	
	
	
}
