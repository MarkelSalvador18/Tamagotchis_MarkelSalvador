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
	private final long tiempoNacimiento = System.currentTimeMillis();
	
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
	
	@Override
    public void run() {
        while (this.vivo) {
            try {
                Thread.sleep(20_000);

                if (System.currentTimeMillis() - this.tiempoNacimiento >= GestorTamagotchis.TIEMPO_VIDA_MS) {
                    System.out.println("aviso, han pasado 5 minutos! " + this.nombreTamagotchi + " ha muerto de vejez");
                    morir("vejez");
                    break;
                }

                this.suciedad = this.suciedad +1;
                System.out.println("\n[" + this.nombreTamagotchi + "] Suciedad acumulada: " + this.suciedad + "/10");

                if (this.suciedad == 5) {
                    System.out.println("[AVISO] " + this.nombreTamagotchi + " está bastante sucio. Conviene limpiarlo.");
                } else if (this.suciedad >= 10) {
                    System.out.println("\n[" + this.nombreTamagotchi + "] Ha muerto por exceso de suciedad.");
                    morir("suciedad");
                    break;
                }

            } catch (InterruptedException e) {
                break; 
            }
        }
	}
	
	public void comer() {
		int tiempoAleatorioGenerado = 0;
		
		this.estadoActual = Estado.COMIENDO;
		System.out.println(this.nombreTamagotchi + " Comienza a comer una sandia");
		try {
			while (tiempoAleatorioGenerado >= 5) {
				tiempoAleatorioGenerado = ThreadLocalRandom.current().nextInt(2, 6);
				System.out.println(this.nombreTamagotchi + " tardare " + tiempoAleatorioGenerado + " seg");
				
			}
		} catch (Exception e) {
			System.out.println(this.nombreTamagotchi + " fue interrumpido mientras comía.");
		} finally {
			System.out.println(this.nombreTamagotchi + ": He terminado de comer la sandia");
	        this.estadoActual = Estado.OCIOSO;
		}
		
	}
	boolean vivo = true;
	public void limpiar(Scanner teclado, Thread hiloTamagotchi) {
	    while (this.vivo && this.suciedad < 10) {
	        try {
	            Thread.sleep(20_000);
	            this.suciedad = this.suciedad +1;
	            System.out.println(this.nombreTamagotchi + " - Suciedad: " + this.suciedad + "/10");

	            if (this.suciedad >= 5 && this.suciedad < 10) {
	                System.out.println("Aviso: " + this.nombreTamagotchi + " está empezando a estar muy sucio");
	                System.out.print("¿Quieres limpiarlo? (s/n): ");
	                String respuesta = teclado.nextLine().trim();

	                if (respuesta.equalsIgnoreCase("s")) {
	                    System.out.println(this.nombreTamagotchi + " se está limpiando...");
	                    this.estadoActual = Estado.LIMPIANDO;

	                    try {
	                        Thread.sleep(5_000);
	                        this.suciedad = 0;
	                        System.out.println(this.nombreTamagotchi + ": He terminado de limpiarme");
	                    } catch (InterruptedException e) {
	                        System.out.println(this.nombreTamagotchi + " fue interrumpido mientras se limpiaba");
	                    } finally {
	                        this.estadoActual = Estado.OCIOSO;
	                    }
	                }
	            }

	        } catch (InterruptedException e) {
	            break;
	        }
	    }

	    if (this.suciedad >= 10) {
	        this.vivo = false;
	        this.estadoActual = Estado.MUERTO;
	        System.out.println(this.nombreTamagotchi + " ha muerto por suciedad.");
	        if (hiloTamagotchi != null) {
	            hiloTamagotchi.interrupt();
	        }
	    }
	}
	
	public void morir(String str) {
		if (str.contains("orden")) {
			
		}
	}
	
	
	
	private static final Estado[] ACCIONES = { Estado.COMIENDO, Estado.JUGANDO, Estado.LIMPIANDO, Estado.OCIOSO };

	public static Estado accionAleatoria() {
	    return ACCIONES[ThreadLocalRandom.current().nextInt(ACCIONES.length)];
	}
	
	
	
	
	
	
}
