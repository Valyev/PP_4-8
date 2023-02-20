package com.BouquetStore.Commands;

import com.BouquetStore.Accessories.Vase;
import com.BouquetStore.Bouquet;

import java.util.Scanner;

public class AddVaseCommand implements Command{
    private Bouquet bouquet;

    public AddVaseCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vase name: ");
        String name = scanner.nextLine();
        System.out.println("Enter vase price");
        double price = scanner.nextDouble();
        bouquet.addVase(new Vase(price,name));
    }
}
