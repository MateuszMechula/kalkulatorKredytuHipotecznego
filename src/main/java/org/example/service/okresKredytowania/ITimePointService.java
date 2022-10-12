package org.example.service.okresKredytowania;

import org.example.model.InputData;
import org.example.model.TimePoint;

import java.math.BigDecimal;

public interface ITimePointService {

    TimePoint calculate(BigDecimal rateNumber, InputData inputData);
}
