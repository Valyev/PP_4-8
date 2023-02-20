package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

public class SortBouquetByFreshnessCommand implements Command {
    private Bouquet bouquet;

    public SortBouquetByFreshnessCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        bouquet.sortByFreshness();
    }
}
