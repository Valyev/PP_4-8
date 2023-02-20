package com.BouquetStore.Commands;

import com.BouquetStore.Accessories.WrappingPaper;
import com.BouquetStore.Bouquet;

import java.util.Scanner;

public class AddWrappingPaperCommand implements Command{
    private Bouquet bouquet;

    public AddWrappingPaperCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter wrapping paper name: ");
        String name = scanner.nextLine();
        System.out.println("Enter wrapping paper price");
        double price = scanner.nextDouble();
        bouquet.addWrappingPaper(new WrappingPaper(price,name));
}}
