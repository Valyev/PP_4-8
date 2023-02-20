package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.util.Scanner;

public class FindFlowerByStemLengthCommand implements Command {
    private Bouquet bouquet;

    public FindFlowerByStemLengthCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the minimum stem length: ");
        double minLength = scanner.nextDouble();
        System.out.println("Enter the maximum stem length: ");
        double maxLength = scanner.nextDouble();
        bouquet.findByStemLength(minLength, maxLength);
//
    }
}
