package org.example.service.kalkulacjaKredytuHipotecznego;

import org.example.model.InputData;
import org.example.model.Rate;
import org.example.service.drukowanieNaEkranie.IPrintingService;
import org.example.service.KalkulacjaRaty.IRateCalculatedService;

import java.util.List;

public class MorgageCalculationService implements IMorgageCalculationService {
    private final IPrintingService IPrintingService;

    private final IRateCalculatedService IRateCalculatedService;

    public MorgageCalculationService(
            IPrintingService IPrintingService,
            IRateCalculatedService IRateCalculatedService
    ) {
        this.IPrintingService = IPrintingService;
        this.IRateCalculatedService = IRateCalculatedService;
    }

    @Override
    public void calculate(final InputData inputData) {
        IPrintingService.PrintInputDataInfo(inputData);

        List<Rate> calculate = IRateCalculatedService.calculate(inputData);


    }
}
