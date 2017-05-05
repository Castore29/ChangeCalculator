
package changecalculator.controller;

import changecalculator.model.CalculatorModel;
import java.util.Iterator;
import java.util.Map;


public class CalculatorController {

    private final CalculatorModel model;
    
    public CalculatorController(CalculatorModel model) {
        this.model = model;
    }
    
    public void calculate(String amountToPay, String amountPaid) throws CalculatorException {
        
        if ("".equals(amountToPay)) {
            throw new CalculatorException("Fill in the field: Amount to pay");
        }
        if ("".equals(amountPaid)) {
            throw new CalculatorException("Fill in the field: Amount paid");
        }
        
        try {
            long toPay = Long.parseLong(amountToPay);
            long paid = Long.parseLong(amountPaid);
            
            if (toPay < 0 || paid < 0) {
                throw new CalculatorException("Only enter positive values!");
            }
            
            if (toPay > paid) {
                throw new CalculatorException("Amount paid must be equal or greater than the amount to pay!");
            }

            model.calculateChange(toPay, paid);
            model.calculateNoteMap();
        } catch (NumberFormatException ex) {
            throw new CalculatorException("Only enter numeric values!");
        }

    }
    
    public String getResults() {
        Map<Integer, Integer> noteMap = model.getNoteMap();

        StringBuilder sb = new StringBuilder();
        sb.append("Change: ").append(model.getChange()).append(" HUF");
        
        if (!noteMap.isEmpty()) {
            sb.append("\n\n");
            Iterator it = noteMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                sb.append(entry.getKey()).append(" HUF - ").append(entry.getValue());
                if ((Integer)entry.getValue() > 1) {
                    sb.append(" pieces");
                } else {
                    sb.append(" piece");
                }
                if (it.hasNext()) {
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }
    
}
