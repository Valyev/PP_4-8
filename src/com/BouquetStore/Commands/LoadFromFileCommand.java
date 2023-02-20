package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.io.IOException;

public class LoadFromFileCommand implements Command {
    private Bouquet bouquet;
    public LoadFromFileCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() throws IOException {
        bouquet.ReadFromFile();
    }
}
