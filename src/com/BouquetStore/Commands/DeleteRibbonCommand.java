package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.util.Scanner;

public class DeleteRibbonCommand implements Command{
    private Bouquet bouquet;

    public DeleteRibbonCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the ribbon to remove: ");
        String name = scanner.nextLine();
        bouquet.deleteRibbon(name);
    }
}
