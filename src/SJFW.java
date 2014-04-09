/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */
public class SJFW extends Kolejka{
    public SJFW(){
        super();
    }
    public void add(Proces proc){
        int i = 0;
        while(i < kolejka.size() &&proc.getLength() > kolejka.get(i++).getLength());
        if(i < kolejka.size()){
            kolejka.add(i, proc);
        }else{kolejka.add(proc);
        }
    }
}
