package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

public class GetPriceCommand implements Command{
    private Bouquet bouquet;

    public GetPriceCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }
        @Override
    public void execute() {
        bouquet.showPrice();
    }
}
