
package changecalculator.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class CalculatorModel {
    
    private final ArrayList<Integer> notes;
    private final Map<Integer, Integer> noteMap;
    private final CalculatorModelListener listener;
    private long change;
    
    public CalculatorModel(CalculatorModelListener listener) {
        notes = new ArrayList<>();
        noteMap = new TreeMap<>();
        
        notes.add(20000);
        notes.add(10000);
        notes.add(5000);
        notes.add(2000);
        notes.add(1000);
        notes.add(500);
        notes.add(200);
        notes.add(100);
        notes.add(50);
        notes.add(20);
        notes.add(10);
        notes.add(5);
        
        this.listener = listener;
    }
    
    public void calculateChange(long amountToPay, long amountPaid) {
        long ch = amountPaid - amountToPay;
        
        long rounding = ch % 5;
        if (rounding == 0) {
            this.change = ch;
        } else if (rounding > 2){
            this.change = ch - rounding + 5;
        } else {
            this.change = ch - rounding;
        }
        
    }
    
    public void calculateNoteMap() {
        noteMap.clear();

        long ch = this.change;

        for (int i = 0; ch != 0 || i < notes.size(); i++) {
            int note = notes.get(i);
            while (note <= ch) {
                noteMap.put(note, noteMap.get(note) == null ? 1 : noteMap.get(note) + 1);
                ch -= note;
            }
        }
        listener.onStateChanged();
    }
    
    public Map<Integer, Integer> getNoteMap() {
        return this.noteMap;
    }
    
    public long getChange() {
        return this.change;
    }

}
