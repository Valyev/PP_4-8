package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

public class ShowBouquetCommand implements Command{
    private Bouquet bouquet;

    public ShowBouquetCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        System.out.println(bouquet.toString());
    }
}

