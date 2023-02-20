package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

public class DeleteVaseCommand implements Command{
        private Bouquet bouquet;

    public DeleteVaseCommand(Bouquet bouquet) {
            this.bouquet = bouquet;
        }

    @Override
    public void execute() {bouquet.deleteVase();}
}

