package com.BouquetStore.Commands;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Exiting the program...");
    }
}
