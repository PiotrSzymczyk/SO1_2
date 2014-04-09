import java.util.LinkedList;

/**
 * Zadaniem metod tej klasy jest wygenerowanie uporządkowanej według czasu 
 * wejścia listy Procesów.
 * @author Piotrek
 */

public class ProcesListGenerator {
    
    /**
     * Tworzy listę o zadanej długości zawierającą procesy o losowej długości
     * i losowym czasie wejścia
     * @param arrayLength
     * @return LinkedList
     */
    public LinkedList<Proces> randGenerate(int arrayLength){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        for(int i=0; i < arrayLength; i++){
            list.add(new Proces( (int) (Math.random() * safeClock) ));
            safeClock += list.getLast().getLength() + 1;
        }
        System.out.print("\nrandGenerate ");
        return sort(list);
    }
    
   /**
     * Tworzy listę o zadanej długości zawierającą procesy o malejącej 
     * (hiperbolicznie) długości i losowym czasie wejścia
     * @param arrayLength
     * @return LinkedList
     */
    public LinkedList<Proces> hyperbolaGenerate(int arrayLength){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int len;
        for(int num =0; num < arrayLength;){
            len = (39 + 3*num)/++num;
            list.add(new Proces((int)(Math.random() * safeClock), len));
            safeClock += list.getLast().getLength() + 1;
        }
        System.out.print("\nhyperbolaGenerate ");
        return sort(list);
    }
    
    /**
     * Tworzy listę o zadanej długości zawierającą procesy o rosnącej 
     * wykładniczo długości i losowym czasie wejścia
     * @param arrayLength
     * @return LinkedList
     */    
    public LinkedList<Proces> sqrtGenerate(int arrayLength){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int len;
        for(int i=0; i < arrayLength;){
            len = (int) Math.sqrt(++i);
            list.add(new Proces((int)(Math.random() * safeClock), len + 1 ));
            safeClock += list.getLast().getLength() + 1;
        }
        System.out.print("\nsqrtGenerate ");
        return sort(list);
    }
    
    /**
     * Otrzymuje listę procesów, porządkuje ją według czasów wejścia procesu,
     * zwraca posortowaną listę
     * @param list
     * @return LinkedList
     */
    private LinkedList<Proces> sort(LinkedList<Proces> list){
        
        LinkedList<Proces> tempList = new LinkedList();
        tempList.add(list.remove(0));
        int i;
        for(Proces proc : list){
            for(i = 0; i < tempList.size() && !(proc.getCzasWejscia() < tempList.get(i).getCzasWejscia()); i++);
            tempList.add(i, proc);  
        }
        
        System.out.println("dla " + tempList.size() + " procesów");
        return tempList;
    }   
}
