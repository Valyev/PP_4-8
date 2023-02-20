package com.BouquetStore.Commands;

import com.BouquetStore.Accessories.Ribbon;
import com.BouquetStore.Bouquet;

import java.util.Scanner;

public class AddRibbonCommand implements Command{
    private Bouquet bouquet;

    public AddRibbonCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ribbon name: ");
        String name = scanner.nextLine();
        System.out.println("Enter ribbon price");
        double price = scanner.nextDouble();
        bouquet.addRibbon(new Ribbon(price,name));
    }
}
