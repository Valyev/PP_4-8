package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.util.Scanner;

public class RemoveFlowerCommand implements Command{
    private Bouquet bouquet;

    public RemoveFlowerCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the flower to remove: ");
        String name = scanner.nextLine();
        bouquet.deleteFlower(name);
    }
}
