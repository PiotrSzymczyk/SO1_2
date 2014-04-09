/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author Mateusz
*/
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    public static void main(String[]args){
        int pomocniczaDoWyboru;
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj długość cyklu procesora");
        Sheluder shlud = new Sheluder(scan.nextInt());
        System.out.println("Wybierz sposob generowania listy procesów: \n0: hiperboliczny\n1:wykładniczy(^1/2)\n2:losowy");
        ProcesListGenerator plg = new ProcesListGenerator();
        LinkedList<Proces> procesy = new LinkedList<Proces>();
        
        switch(scan.nextInt()){
            case 0:
                System.out.println("Podaj ilość procesów: ");
                pomocniczaDoWyboru = scan.nextInt();
                procesy = plg.hyperbolaGenerate(pomocniczaDoWyboru);
                do{
                   if(procesy.size() > 0 && procesy.getFirst().getCzasWejscia() <= shlud.workerSJF.getClock()){
                        shlud.add(procesy.getFirst());
                        procesy.remove();
                    }
                }while(shlud.sendToProcesor() || procesy.size() > 0);

                shlud.printTimes(pomocniczaDoWyboru);
                break;
            case 1:
                System.out.println("Podaj ilość procesów: ");
                pomocniczaDoWyboru = scan.nextInt();
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
                System.out.println("Podaj ilość procesów: ");
                pomocniczaDoWyboru = scan.nextInt();
                procesy = plg.randGenerate(pomocniczaDoWyboru);
                do{
                   if(procesy.size() > 0 && procesy.getFirst().getCzasWejscia() <= shlud.workerSJF.getClock()){
                        shlud.add(procesy.getFirst());
                        procesy.remove();
                    }
                }while(shlud.sendToProcesor() || procesy.size() > 0);

                shlud.printTimes(pomocniczaDoWyboru);
                break;
        }
    }
}
