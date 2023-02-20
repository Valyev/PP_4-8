package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.io.IOException;

public class AddExistingVaseCommand implements Command{
    private Bouquet bouquet;
    public AddExistingVaseCommand(Bouquet bouquet){
        this.bouquet = bouquet;
    }

    @Override
    public void execute() throws IOException {
        bouquet.AddExistingVase();
    }
}
