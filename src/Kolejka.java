
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */

public abstract class Kolejka {
    LinkedList<Proces> kolejka;
    int waitingTime;
    public Kolejka(){
        waitingTime = 0;
        kolejka = new LinkedList<Proces>();
    }
    public void add(Proces proc){};
    
    public Proces remove(){
        return kolejka.remove();
    }
    public Proces get(){
        return kolejka.getFirst();
    }
    public boolean isEmpty(){
        return kolejka.isEmpty();
    }
    public void increaseWaitingTime(int czas){
        waitingTime+= (kolejka.size() -1)*czas;
    }
    public int getWaitingTime(){
        return waitingTime;
    }
    
}
