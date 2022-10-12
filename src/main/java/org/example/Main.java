package org.example;

import org.example.model.InputData;
import org.example.service.KalkulacjaRaty.IRateCalculatedService;
import org.example.service.KalkulacjaRaty.RateCalculatedService;
import org.example.service.drukowanieNaEkranie.IPrintingService;
import org.example.service.drukowanieNaEkranie.PrintingService;
import org.example.service.kalkulacjaKredytuHipotecznego.IMorgageCalculationService;
import org.example.service.kalkulacjaKredytuHipotecznego.MorgageCalculationService;
import org.example.service.kalkulacjaKwot.AmountsCalculationService;
import org.example.service.okresKredytowania.TimePointService;
import org.example.service.pozostaloKredytu.ResidualCalculationService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {


        InputData inputData = new InputData()
                .withAmount(new BigDecimal("298000"));

        IPrintingService IPrintingService = new PrintingService();
        IRateCalculatedService IRateCalculatedService = new RateCalculatedService(
                new TimePointService(),
                new AmountsCalculationService(),
                new ResidualCalculationService()
        );

        IMorgageCalculationService IMorgageCalculationService = new MorgageCalculationService(
                IPrintingService,
                IRateCalculatedService
        );
        IMorgageCalculationService.calculate(inputData);
    }

}