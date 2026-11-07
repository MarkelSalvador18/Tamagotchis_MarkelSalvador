package org.cuatrovientos.Tamagotchis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner scn =  new Scanner(System.in);
		try {
			
			Map<String, Tamagotchis> mapaTamagotchis = new HashMap<>();
			for (int i = 1; i <= GestorTamagotchis.NUMERO_TAMAGOTCHIS; i++) {
			    Tamagotchis tama = new Tamagotchis("Tamagotchi-" + i); 
			    
			    mapaTamagotchis.put(tama.getNombreTamagotchi(), tama);
			    
			    Thread hilo = new Thread(tama, "Hilo-" + tama.getNombreTamagotchi());
			    hilo.start();
			    
			    System.out.println("Creado y lanzado: " + tama.getNombreTamagotchi());
			}
			String txt;
			int opcionTama = 0;

			do {
			    System.out.println("\n--- SELECCIÓN DE TAMAGOTCHI ---");
			    System.out.println("1. Tamagotchi-1");
			    System.out.println("2. Tamagotchi-2");
			    System.out.println("3. Tamagotchi-3");
			    System.out.println("0. Salir");
			    System.out.print("Elige un Tamagotchi: ");

			    txt = scn.nextLine();

			    if (txt.equals("1") || txt.equals("2") || txt.equals("3")) {
			        opcionTama = Integer.parseInt(txt);
			        
			        String clave = "Tamagotchi-" + opcionTama;
			        Tamagotchis tamaElegido = mapaTamagotchis.get(clave);

			        System.out.println("Has elegido el " + clave);

			        boolean volver = false;
			        do {
			            System.out.println("¿Qué acción quieres hacer con " + clave + "?");
			            System.out.println("1. Ver estado actual");
			            System.out.println("2. Alimentar");
			            System.out.println("3. Jugar");
			            System.out.println("4. Limpiar");
			            System.out.println("5. Matar (solo si está ocioso)");
			            System.out.println("0. Volver a elegir Tamagotchi");
			            System.out.print("Opción: ");

			            String accion = scn.nextLine();

			            switch (accion) {
			                case "1":
			                    System.out.println("Estado actual: " + tamaElegido.getEstado());
			                    break;

			                case "2":
			                    tamaElegido.comer();
			                    break;

			                case "3":
			                    tamaElegido.jugar(scn);
			                    break;

			                case "4":
			                    tamaElegido.limpiar(scn, tamaElegido);
			                    break;

			                case "5":
			                    if (tamaElegido.getEstado() == Tamagotchis.Estado.OCIOSO) {
			                        tamaElegido.morir("orden del cuidador");
			                        System.out.println(clave + " ha sido eliminado");
			                    } else {
			                        System.out.println("Error: No puedes matarlo: está " + tamaElegido.getEstado());
			                    }
			                    break;

			                case "0":
			                    volver = true;
			                    break;

			                default:
			                    System.out.println("Error: Introduce un número del 0 al 5.");
			                    break;
			            }

			        } while (!volver);

			    } else if (txt.equals("0")) {
			        System.out.println("Cerrando el programa");
			        break;
			    } else {
			        System.out.println("Opción no válida. Introduce 1, 2, 3 o 0.");
			    }

			} while (true);
		} catch (Exception e) {
			
		} finally {
			scn.close();
		}
	}

}

