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
	private final int tiempoComerMs;
	private final int tiempoNacimiento;
	private int suciedad;
	private Estado estadoActual;
	private boolean vivo;

	
	public Tamagotchis(String nombre) {
		this.nombreTamagotchi = nombre;
		this.suciedad = 0;
		this.estadoActual = Estado.OCIOSO;
		this.vivo = true;
		this.tiempoNacimiento = System.currentTimeMillis();
		this.tiempoComerMs = ThreadLocalRandom.current().nextInt(3000, 9000);
	}

    public Estado getEstado() {
        return estadoActual;
    }
   
	public String getNombreTamagotchi() {
		return nombreTamagotchi;
	}
	public int getSuciedad() {
		return suciedad;
	}
	
	
	@Override
    public void run() {
		long aumentoSuciedad = System.currentTimeMillis();
		
        while (this.vivo) {
            try {
            		if (this.estadoActual == Estado.COMIENDO) {
            			System.out.println(this.nombreTamagotchi + " comienza a comer una sandia " + this.tiempoComerMs / 1000 + " seg");
            			Thread.sleep(this.tiempoComerMs);
            			if (this.vivo) {
            				System.out.println(this.nombreTamagotchi + " ha terminado de comer");
            				
            			}
            		} else if (this.estadoActual == Estado.LIMPIANDO) {
            			System.out.println(this.nombreTamagotchi +  " comienza a limpiarse");
            			Thread.sleep(GestorTamagotchis.TIEMPO_BANO_MS);
            			if (this.vivo) {
            				this.suciedad = 0;
            				this.estadoActual = Estado.OCIOSO;
            				aumentoSuciedad = System.currentTimeMillis();
            				System.out.println(this.nombreTamagotchi + " ha terminado de limpiarse, suciedad 0/10 " );
            			}
            		} else {
            			Thread.sleep(500);
            		}
                long ahora = System.currentTimeMillis();
                if (ahora - this.tiempoNacimiento >= GestorTamagotchis.TIEMPO_VIDA_MS) {
                	System.out.println("Han pasado 5 minutos, " + this.nombreTamagotchi + " muere");
                finalizarVida();
                break;
                }
                if (ahora - aumentoSuciedad >= GestorTamagotchis.TIEMPO_SUCIEDAD_MS);
                aumentoSuciedad = ahora;
                this.suciedad = this.suciedad +1;
                		if (this.suciedad == 5) {
                			System.out.println(this.nombreTamagotchi + " esta sucio");
                			
                		} else if (this.suciedad >= 10) {
                			System.out.println(this.nombreTamagotchi + " ha muerto por suciedad");
                			finalizarVida();
                			break;
                		}
                
            		} catch (InterruptedException e) {
                        break; 
                    }
           } 
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
	

	public boolean matar() {
		if (!this.vivo) {
			System.out.println(this.nombreTamagotchi + " ya esta muerto");
			return false;
		}
		if (this.estadoActual != Estado.OCIOSO) {
			System.out.println(this.nombreTamagotchi + " esta ocupado " + this.estadoActual);
			return false;
		}
		finalizarVida();
		System.out.println(this.nombreTamagotchi + " ha sido destruido por el cuidador ");
		return true;
	}
	
	public boolean puedeRealizarAccion() {
		if (!this.vivo || this.estadoActual == Estado.MUERTO) {
			System.out.println(this.nombreTamagotchi + " esta muerto y no puede hacer acciones");
			return false;
		}
		
		if (this.estadoActual != Estado.OCIOSO) {
			System.out.println(this.nombreTamagotchi + " esta ocupado con " + this.estadoActual);
			return false;
		}
		return true;
	}
	
	public void finalizarVida() {
		this.vivo = false;
		this.estadoActual = Estado.MUERTO;
		this.interrupt();
	}
	
	
	
	
	
	
	
}
