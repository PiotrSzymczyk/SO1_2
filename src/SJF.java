/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */
public class SJF extends Kolejka {
    private Proces obecnyProc;
    public SJF(){
        super();
        obecnyProc = null;
    }
    public void add(Proces proc){
        int i = 0;
            while(i < kolejka.size() &&proc.getLength() > kolejka.get(i++).getLength());
            if(i < kolejka.size()){
                kolejka.add(i, proc);
            }else{kolejka.add(proc);}
    }
    public void ustawObecny(Proces proc){
        
        obecnyProc = remove();
    }
    public boolean isEmpty(){
        return kolejka.isEmpty() && obecnyProc == null;
    }
    public Proces get(){
        if(obecnyProc == null){
            return kolejka.getFirst();
        }else{
            return obecnyProc;
        }
    }
    public Proces remove(){
        if(obecnyProc == null)
            return kolejka.remove();
        else
            obecnyProc = null;
            return obecnyProc;
    }
}
