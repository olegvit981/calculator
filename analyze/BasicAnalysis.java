package analyze;

import calculate.Calculation;
import exceptions.NumberTypeMismatchException;
import exceptions.OutOfNumberFormatException;

public interface BasicAnalysis {
    
    boolean isMatch() throws NumberTypeMismatchException;
    
    Calculation getData() throws OutOfNumberFormatException;

}
