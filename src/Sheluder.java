
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */
public class Sheluder {
    SJF kolejkaSJF;
    FIFO kolejkaFIFO;
    SJFW kolejkaSJFW;
    Rot kolejkaRot;
    Procesor workerSJF;
    Procesor workerFIFO;
    Procesor workerSJFW;
    Procesor workerRot;
            
    public Sheluder(int kwant){
        kolejkaSJF = new SJF();
        kolejkaFIFO = new FIFO();
        kolejkaSJFW = new SJFW();
        kolejkaRot = new Rot();
        workerSJF = new Procesor(kwant);
        workerFIFO = new Procesor(kwant);
        workerSJFW = new Procesor(kwant);
        workerRot = new Procesor(kwant);
        }
    
    public void add(Proces proc){               //dodaje proces, do każdej z 4 list
        kolejkaSJF.add(new Proces(proc.getCzasWejscia(), proc.getLength()));
        kolejkaFIFO.add(new Proces(proc.getCzasWejscia(), proc.getLength()));
        kolejkaSJFW.add(new Proces(proc.getCzasWejscia(), proc.getLength()));
        kolejkaRot.add(new Proces(proc.getCzasWejscia(), proc.getLength()));
        
        
    }
    public boolean sendToProcesor(){     //
        int licznik = 0;                // zmienna pomocnicza przy sprawdzaniu czy proces się wykonał
        if(!kolejkaSJF.isEmpty()){
            licznik = workerSJF.przetworz(kolejkaSJF.get());
            
            kolejkaSJF.increaseWaitingTime(workerSJF.getCykl());
            if(licznik <= 0){
                kolejkaSJF.remove();
            }else{
                kolejkaSJF.get().setLength(licznik);
                kolejkaSJF.ustawObecny(kolejkaSJF.get());
            }
        }
        if(!kolejkaSJFW.isEmpty()){
            
            licznik = workerSJFW.przetworz(kolejkaSJFW.get());
            
            kolejkaSJFW.increaseWaitingTime(workerSJFW.getCykl());
            if(licznik <= 0){
                kolejkaSJFW.remove();
            }else{
                kolejkaSJFW.get().setLength(licznik);
            }
        }
        if(!kolejkaFIFO.isEmpty()){
        
            licznik = workerFIFO.przetworz(kolejkaFIFO.get());
            
            kolejkaFIFO.increaseWaitingTime(workerFIFO.getCykl());
            if(licznik <= 0){
                kolejkaFIFO.remove();
            }else{
                kolejkaFIFO.get().setLength(licznik);
            }
        }
        if(!kolejkaRot.isEmpty()){
        
            licznik = workerRot.przetworz(kolejkaRot.get());
            
            kolejkaRot.increaseWaitingTime(workerRot.getCykl());
            if(licznik <= 0){
                kolejkaRot.remove();
            }else{
                kolejkaRot.get().setLength(licznik);
                kolejkaRot.add(kolejkaRot.remove());
            }
        }
        boolean anything = (!kolejkaSJF.kolejka.isEmpty() || !kolejkaSJFW.kolejka.isEmpty()|| !kolejkaRot.kolejka.isEmpty()|| !kolejkaFIFO.kolejka.isEmpty()); // zmienna pomocnicza pozwalająca sprawdzić
        if(!anything){
            workerSJF.setClock(workerSJF.getClock()+workerSJF.getCykl());
        }
        return anything;                                                                                         //czy zostały jeszcze jakiekolwiek procesy na którejkolwiek liście
    }

    public void printTimes(int liczbaProcesow){
        System.out.println("średni czas oczekiwania dla SJF: "  +   kolejkaSJF.getWaitingTime()/liczbaProcesow);
        System.out.println("średni czas oczekiwania dla SJF z wywłaszczeniem: "  +   kolejkaSJFW.getWaitingTime()/liczbaProcesow);
        System.out.println("średni czas oczekiwania dla Rotacyjnego: "  +   kolejkaRot.getWaitingTime()/liczbaProcesow);
        System.out.println("średni czas oczekiwania dla FiFo: "  +   kolejkaFIFO.getWaitingTime()/liczbaProcesow);
    }
    
    
}
