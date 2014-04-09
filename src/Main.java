/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author Mateusz
*/
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    public static void main(String[]args) throws FileNotFoundException{
        int pomocniczaDoWyboru;
        Scanner sc = new Scanner(new FileReader("Test.txt"));
        
        while(sc.hasNextInt()){
            try{
                Sheluder shlud = new Sheluder(sc.nextInt());
                ProcesListGenerator plg = new ProcesListGenerator();
                LinkedList<Proces> procesy = new LinkedList<>();
                switch(sc.nextInt()){
                    case 0:
                        pomocniczaDoWyboru = sc.nextInt();
                        procesy = plg.hyperbolaGenerate(pomocniczaDoWyboru);
                        do{
                           while(procesy.size() > 0 && procesy.getFirst().getCzasWejscia() <= shlud.workerSJF.getClock()){
                                shlud.add(procesy.getFirst());
                                procesy.remove();
                            }

                        }while(shlud.sendToProcesor() || procesy.size() > 0);

                        shlud.printTimes(pomocniczaDoWyboru);
                        break;
                    case 1:
                        pomocniczaDoWyboru = sc.nextInt();
                        procesy = plg.sqrtGenerate(pomocniczaDoWyboru);

                        do{
                               while(procesy.size() > 0 && procesy.getFirst().getCzasWejscia() <= shlud.workerSJF.getClock()){
                                   shlud.add(procesy.getFirst());
                                   procesy.remove();

                               }
                        }while(shlud.sendToProcesor() || procesy.size() > 0);

                        shlud.printTimes(pomocniczaDoWyboru);
                        break;
                    case 2:
                        pomocniczaDoWyboru = sc.nextInt();
                        procesy = plg.randGenerate(pomocniczaDoWyboru);
                        do{
                           while(procesy.size() > 0 && procesy.getFirst().getCzasWejscia() <= shlud.workerSJF.getClock()){
                                shlud.add(procesy.getFirst());
                                procesy.remove();
                            }

                        }while(shlud.sendToProcesor() || procesy.size() > 0);

                        shlud.printTimes(pomocniczaDoWyboru);
                        break;
                } 
            }catch (NoSuchElementException e){
                System.out.println(" \nNieprawidłowa skłądnia piliku : Plik powinien byc postaci :\n"
                       + "Długość_cyklu_procesora  Sposób_generowania_procesów  Ilość_procesów");
            }
        }
    }
}
