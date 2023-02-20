package com.BouquetStore;

import com.BouquetStore.Commands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Добити той лог, і зробити юз кейс діаграму, можна якось ідейкою
public class Main {
    private static final String[] menuOptions = {
            "Load from file",
            "Add flower",
            "Add custom flower",
            "Remove flower",
            "Add wrapping paper",
            "Add custom wrapping paper",
            "Delete wrapping paper",
            "Add ribbons",
            "Add custom ribbons",
            "Delete ribbons",
            "Add vase",
            "Add custom vase",
            "Delete vase",
            "Show bouquet",
            "Show bouquet price",
            "Sort bouquet by freshness",
            "Find flower by stem length",
            "Exit"
    };

    public static void main(String[] args) throws IOException {
        List<Command> commands = new ArrayList<>();
        Bouquet bouquet = new Bouquet();

        commands.add(new LoadFromFileCommand(bouquet));
        commands.add(new AddExistingFlowerCommand(bouquet));
        commands.add(new AddFlowerCommand(bouquet));
        commands.add(new RemoveFlowerCommand(bouquet));
        commands.add(new AddExistingWrappingPaperCommand(bouquet));
        commands.add(new AddWrappingPaperCommand(bouquet));
        commands.add(new DeleteWrappingPaperCommand(bouquet));
        commands.add(new AddExistingRibbonCommand(bouquet));
        commands.add(new AddRibbonCommand(bouquet));
        commands.add(new DeleteRibbonCommand(bouquet));
        commands.add(new AddExistingVaseCommand(bouquet));
        commands.add(new AddVaseCommand(bouquet));
        commands.add(new DeleteVaseCommand(bouquet));
        commands.add(new ShowBouquetCommand(bouquet));
        commands.add(new GetPriceCommand(bouquet));
        commands.add(new SortBouquetByFreshnessCommand(bouquet));
        commands.add(new FindFlowerByStemLengthCommand(bouquet));
        commands.add(new ExitCommand());


        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(i + ": " + menuOptions[i]);
            }

            int option = sc.nextInt();
            if (option >= 0 && option < commands.size()) {
                commands.get(option).execute();
                if (commands.get(option) instanceof ExitCommand) {
                    exit = true;
                }
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}

