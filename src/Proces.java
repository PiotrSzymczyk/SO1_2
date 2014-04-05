 /**
 * Instancje tej klasy symulują procesy przetrzymując (pozostałą) dłługość 
 * procesu oraz moment jego pojawienia się.
 * @author Piotrek
 */
public class Proces {
    
    private int length;
    private int czasWejscia;
    
    /**
     * Tworzy proces losowej długości ustawiając jego czas wejścia na 
     * otrzymaną wartość (parametr).
     * @param time 
     */
    public Proces(int time){
        length = (int) (Math.random()*20) + 1;
        czasWejscia = time;
    }
    
    /**
     * Tworzy proces zadanej długości ustawiając jego czas wejścia na 
     * otrzymaną wartość (parametr).
     * @param time
     * @param len 
     */
    public Proces(int time, int len){
        length = len;
        czasWejscia = time;
    }
    
    public void setLength(int val){
        length = val;
    }
    
    public void setCzasWejscia(int val){
        czasWejscia = val;
    }
    
    public int getLength(){
        return length;
    }
    
    public int getCzasWejscia(){
        return czasWejscia;
        
    } 
}