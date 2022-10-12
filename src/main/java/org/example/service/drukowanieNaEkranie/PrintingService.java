package org.example.service.drukowanieNaEkranie;

import org.example.model.InputData;

public class PrintingService implements IPrintingService {

    @Override
    public void PrintInputDataInfo(final InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        printMessege(msg);
    }

    public void printMessege(StringBuilder stringBuilder) {
        System.out.println(stringBuilder);
    }
}
