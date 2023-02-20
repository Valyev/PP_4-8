package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;
import com.BouquetStore.Flowers.Flower;

import java.util.Scanner;

public class AddFlowerCommand implements Command {
    private Bouquet bouquet;

    public AddFlowerCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flower name: ");
        String name = scanner.nextLine();
        System.out.println("Enter flower price");
        double price = scanner.nextDouble();
        System.out.println("Enter flower freshness level (1-100): ");
        double freshness = scanner.nextDouble();
        System.out.println("Enter flower stem length: ");
        double stemLength = scanner.nextDouble();
        bouquet.addFlower(new Flower(name,price, freshness, stemLength));
    }
}
