package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.io.IOException;

public class AddExistingFlowerCommand implements Command{
    private Bouquet bouquet;

    public AddExistingFlowerCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() throws IOException {
        bouquet.AddExistingFlower();
    }
}
