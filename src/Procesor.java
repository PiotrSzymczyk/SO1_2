/**
 * Instancje tej klasy symulują prace procesora "przetwarzając" procesy poprzez
 * skrócenie ich długości. Posiadają one dwa pola : clock oraz cycle. Nowy 
 * proces może zostać wczytany jedynie na początku cylku procesora (faza fetch). 
 * @author Piotrek
 */
public class Procesor {
    
    private int clock = 0;
    private int cycle;
    
    public Procesor(int cykl){
        clock = 0;
        cycle = cykl;
    }
    
    /**
     * Przetwarza proces przez 1 cykl procesora. Zwraca czas pozostały do 
     * przetworzenia dla procesu.
     * @param proc
     * @return int
     */
    public int przetworz(Proces proc){
        clock++;                                    // Czas na wczytanie procesu
        clock += cycle -1;                          // Przesnięcie na koniec cyklu
        int lengthLeft = proc.getLength() - cycle;
        return lengthLeft;
    }   
    
    public void setClock(int val){
        clock = val;
    }
    
    public void setKwantCzasu(int kwant){
        cycle = kwant;
    }
    
    public int getClock(){
        return clock;
    }
    
}
