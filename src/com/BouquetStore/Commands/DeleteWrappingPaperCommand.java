package com.BouquetStore.Commands;

import com.BouquetStore.Bouquet;

import java.util.Scanner;

public class DeleteWrappingPaperCommand implements Command{
    private Bouquet bouquet;

    public DeleteWrappingPaperCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the wrapping paper to remove: ");
        String name = scanner.nextLine();
        bouquet.deleteWrappingPaper(name);
    }
}
