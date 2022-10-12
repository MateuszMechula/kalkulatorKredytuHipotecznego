package org.example.service.KalkulacjaRaty;

import org.example.model.InputData;
import org.example.model.Rate;

import java.util.List;

public interface IRateCalculatedService {

    List<Rate> calculate(final InputData inputData);
}
