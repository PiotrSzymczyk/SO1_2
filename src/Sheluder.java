
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
    private Proces currentSjf;                  //obecnie wykonywany proces dla Sjf bez wywłaszczenia
    private int waitingTimeFifo;            //suma czasów oczekiwania dla Fifo
    private int waitingTimeSjf;             //suma czasów oczekiwania dla Sjf bez wywłaszczenia
    private int waitingTimeSjfW;            //suma czasów oczekiwania dla Sjf z wywłaszczeniem
    private int waitingTimeRot;             //suma czasów oczekiwania dla Rotacyjnego
    private LinkedList<Proces> listFifo;        //First in fist out
    private LinkedList<Proces> listSjf;         //Shortest job first
    private LinkedList<Proces> listRot;         // Rotacyjny
    private LinkedList<Proces> listSjfW;       // Sjf z wywłaszczeniem
    
            
    public Sheluder(){
        listFifo = new LinkedList<Proces>();
        listSjf = new LinkedList<Proces>();
        listRot = new LinkedList<Proces>();
        listSjfW = new LinkedList<Proces>();
        currentSjf = null;
        waitingTimeFifo = 0;
        waitingTimeSjf = 0;
        waitingTimeSjfW = 0;
        waitingTimeRot = 0;
        }
    
    public void add(Proces proc){               //dodaje proces, do każdej z 4 list
        listFifo.add(proc);                        //w przypadku Sjf i SjfW w odpowiednie miejsce na liście
        listRot.add(proc);
        int i = 0;
        while(i < listSjf.size() &&proc.getLength() > listSjf.get(i++).getLength());
        if(i < listSjf.size()){
            listSjf.add(i, proc);
        }else{listSjf.add(proc);}
        i = 0;
        while(i < listSjfW.size() &&proc.getLength() > listSjfW.get(i++).getLength());
        if(i < listSjfW.size()){
            listSjfW.add(i, proc);
        }else{listSjfW.add(proc);
        }
    }
    public boolean sendToProcesor(Procesor procek){     //
        int licznik;                // zmienna pomocnicza przy sprawdzaniu czy proces się wykonał
            
        if(listFifo.size()!= 0){

            waitingTimeFifo += listFifo.size()*procek.getClock();
            licznik = procek.przetworz(listFifo.getFirst());
            if(licznik > 0){
                listFifo.getFirst().setLength(licznik);
            }else{
                listFifo.remove();
                
            }
        }                               // wykonanie kwantu czasu procesora dla FiFo
        if(listSjf.size() != 0){

            waitingTimeSjf += listSjf.size()*procek.getClock();
            if(currentSjf == null){
                licznik = procek.przetworz(listSjf.getFirst());
                
                    
                if(licznik > 0){
                    
                    currentSjf = listSjf.getFirst();
                    listSjf.remove();
                    
                    currentSjf.setLength(licznik);
                }else{
                    listSjf.remove();

                }
            }else{
                licznik = procek.przetworz(currentSjf);
                if(licznik > 0){
                    currentSjf.setLength(licznik);
                }else{                                      // wykonanie kwantu czasu dla SJF, jeżeli jest jakiś aktualnie wykonywany
                    currentSjf = null;                      //proces to go kontynuuje, jeśli nie, bierze następny
                }                                           // jeśli nie skończy procesu ustawia go jako aktualnie wykonywany 
            }                                            // (i usuwa z listy jeśli jeszcze tam jest)
        }
        if(listSjfW.size()!= 0){

            waitingTimeSjfW += listSjfW.size()*procek.getClock();
            licznik = procek.przetworz(listSjfW.getFirst());
            if(licznik > 0){
                listSjfW.getFirst().setLength(licznik);
            }else{
                listSjfW.remove();
            }
        }
        if(listRot.size()!= 0){ 

            waitingTimeRot += listRot.size()*procek.getClock();
            licznik = procek.przetworz(listRot.getFirst());
            if(licznik > 0){
                listRot.getFirst().setLength(licznik);
                listRot.add(listRot.getFirst());
                listRot.remove();
                
            }else{
                listRot.remove();                                       // wysłanie Rotacyjnego do procesora, proces który ma wykonany kwant czasu przesuwany jest na koniec listy
            }
        }
        boolean anything = (listRot.size() != 0 || listSjfW.size()!= 0|| listSjf.size()!= 0|| listFifo.size()!= 0); // zmienna pomocnicza pozwalająca sprawdzić
        return anything;                                                                                         //czy zostały jeszcze jakiekolwiek procesy na którejkolwiek liście
    }
    public void printTimes(int liczbaProcesow){
        System.out.println("średni czas oczekiwania dla SJF: "  +   waitingTimeSjf/liczbaProcesow);
        System.out.println("średni czas oczekiwania dla SJF z wywłaszczeniem: "  +   waitingTimeSjfW/liczbaProcesow  );
        System.out.println("średni czas oczekiwania dla Rotacyjnego: "  +   waitingTimeRot/liczbaProcesow  );
        System.out.println("średni czas oczekiwania dla FiFo: "  +   waitingTimeFifo/liczbaProcesow  );
    }
    
    
}
