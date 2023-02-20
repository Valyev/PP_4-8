package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.io.IOException;

public class AddExistingRibbonCommand implements Command{
    private Bouquet bouquet;
    public AddExistingRibbonCommand(Bouquet bouquet){
        this.bouquet = bouquet;
    }


    @Override
    public void execute() throws IOException {
        bouquet.AddExistingRibbon();
    }
}
