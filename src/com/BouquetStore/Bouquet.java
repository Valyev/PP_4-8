package com.BouquetStore;

import com.BouquetStore.Accessories.Ribbon;
import com.BouquetStore.Accessories.Vase;
import com.BouquetStore.Accessories.WrappingPaper;
import com.BouquetStore.Flowers.Flower;


import java.io.*;
import java.util.*;

public class Bouquet {
    private List<Flower> flowers;
    private Vase vase;
    private List<WrappingPaper> wrappingPapers;
    private List<Ribbon> ribbons;
    private List<Flower> availableFlowers;
    private List<Vase> availableVases;
    private List<Ribbon> availableRibbons;
    private List<WrappingPaper> availableWrappingPaper;
    private double price;

    public Bouquet() {
        flowers = new ArrayList<>();
        ribbons = new ArrayList<>();
        wrappingPapers = new ArrayList<>();
        availableFlowers = new ArrayList<>();
        availableVases = new ArrayList<>();
        availableRibbons = new ArrayList<>();
        availableWrappingPaper = new ArrayList<>();

    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
        price += flower.getPrice();
    }

    public void addVase(Vase vase) {
        if (this.vase == null) {
            this.vase = vase;
            price += vase.getPrice();
        } else {
            System.out.println("A bouquet can only have one vase.");
        }
    }


    public void addWrappingPaper(WrappingPaper wrappingPaper) {
        if (wrappingPapers.size() < 2) {
            wrappingPapers.add(wrappingPaper);
            price += wrappingPaper.getPrice();
        } else {
            System.out.println("A bouquet can only have two layers of wrapping paper.");
        }
    }

    public void addRibbon(Ribbon ribbon) {
        if (ribbons.size() < 3) {
            ribbons.add(ribbon);
            price += ribbon.getPrice();
        } else {
            System.out.println("A bouquet can only have three ribbons.");
        }
    }

    public void sortByFreshness() {
        flowers.sort((f1, f2) -> (int) (f2.getFreshness() - f1.getFreshness()));
        System.out.println("Sorted Flowers in the Bouquet:");
        for (Flower flower : flowers) {
            System.out.println(flower.toString());
        }
    }

    public void findByStemLength(double minLength, double maxLength) {
        List<Flower> foundFlowers = new ArrayList<Flower>();
        for (Flower flower : flowers) {
            if (flower.getStem_length() >= minLength && flower.getStem_length() <= maxLength) {
                foundFlowers.add(flower);
            }
        }
        if (foundFlowers.isEmpty()) {
            System.out.println("No flowers found with stem length between " + minLength + " and " + maxLength + ".");
        } else {
            System.out.println("Found flowers:");
            for (Flower flower : foundFlowers) {
                System.out.println(flower);
            }
        }
    }


    public void deleteFlower(String name) {
        boolean is_deleted = false;
        for (Flower flower : flowers) {
            if (Objects.equals(flower.getName(), name)) {
                this.price = this.price - flower.getPrice();
                flowers.remove(flower);
                is_deleted = true;
                break;
            }
        }
        if (!is_deleted) {
            System.out.println("Typed flower not found");
        }
    }

    public void deleteVase() {
        if (this.vase != null) {
            this.price = this.price - vase.getPrice();
            this.vase = null;
        } else {
            System.out.println("The bouquet doesn't have a vase.");
        }
    }

    public void deleteRibbon(String name) {
        boolean is_deleted = false;
        for (Ribbon ribbon : ribbons) {
            if (Objects.equals(ribbon.getName(), name)) {
                this.price = this.price - ribbon.getPrice();
                ribbons.remove(ribbon);
                is_deleted = true;
                break;
            }
        }
        if (!is_deleted) {
            System.out.println("Typed ribbon not found");
        }
    }

    public void deleteWrappingPaper(String name) {
        boolean is_deleted = false;
        for (WrappingPaper wrap : wrappingPapers) {
            if (Objects.equals(wrap.getName(), name)) {
                this.price = this.price - wrap.getPrice();
                wrappingPapers.remove(wrap);
                is_deleted = true;
                break;
            }
        }
        if (!is_deleted) {
            System.out.println("Typed wrapper not found");
        }
    }

    public void showPrice() {
        System.out.println("The bouquet price is: "+String.valueOf(price));
    }
    public List<Flower> getFlowers() {
        return flowers;
    }

    public double getPrice() {
        return price;
    }
    public Vase getVase(){
        return vase;
    }
    public List<Ribbon> getRibbons(){
        return  ribbons;
    }
    public List<WrappingPaper> getWrappingPapers(){
        return wrappingPapers;
    }

    public void ReadFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/parts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "Flower" ->
                            availableFlowers.add(new Flower(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                    case "Ribbon" -> availableRibbons.add(new Ribbon(Integer.parseInt(parts[1]), parts[2]));
                    case "Vase" -> availableVases.add(new Vase(Integer.parseInt(parts[1]), parts[2]));
                    case "WrappingPaper" ->
                            availableWrappingPaper.add(new WrappingPaper(Integer.parseInt(parts[1]), parts[2]));
                    default -> System.out.println("Unknown type: " + parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void AddExistingFlower(){
        System.out.println("Available flowers:");
        for (int i = 0; i < availableFlowers.size(); i++) {
            System.out.println((i + 1) + ". " + availableFlowers.get(i).getName());
        }

        System.out.print("Choose a flower by index: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); //clearing buffer purposes

        if (choice < 1 || choice > availableFlowers.size()) {
            System.out.println("Invalid choice.");
            return;

        }
        Flower chosenFlower = availableFlowers.get(choice - 1);
        addFlower(chosenFlower);

    }
    public void AddExistingVase(){
        System.out.println("Available vases:");
        for (int i = 0; i < availableVases.size(); i++) {
            System.out.println((i + 1) + ". " + availableVases.get(i).getName());
        }

        System.out.print("Choose a vase by index: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); //clearing buffer purposes

        if (choice < 1 || choice > availableVases.size()) {
            System.out.println("Invalid choice.");
            return;

        }
        Vase chosenVase = availableVases.get(choice - 1);
        addVase(chosenVase);
    }
    public void AddExistingRibbon(){
        System.out.println("Available ribbons:");
        for (int i = 0; i < availableRibbons.size(); i++) {
            System.out.println((i + 1) + ". " + availableRibbons.get(i).getName());
        }

        System.out.print("Choose a ribbon by index: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); //clearing buffer purposes

        if (choice < 1 || choice > availableRibbons.size()) {
            System.out.println("Invalid choice.");
            return;

        }
        Ribbon chosenRibbon = availableRibbons.get(choice - 1);
        addRibbon(chosenRibbon);

    }
    public void AddExistingWrappingPaper(){
        System.out.println("Available Wrapping papers:");
        for (int i = 0; i < availableWrappingPaper.size(); i++) {
            System.out.println((i + 1) + ". " + availableWrappingPaper.get(i).getName());
        }

        System.out.print("Choose a Wrapping paper by index: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); //clearing buffer purposes

        if (choice < 1 || choice > availableWrappingPaper.size()) {
            System.out.println("Invalid choice.");
            return;

        }
        WrappingPaper chosenWrappingPaper = availableWrappingPaper.get(choice - 1);
        addWrappingPaper(chosenWrappingPaper);

    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Bouquet: \n");
        output.append("Flowers: \n");
        for (Flower flower : flowers) {
            output.append("\t" + flower.toString() + "\n");
        }
        output.append("Vase: " + vase + "\n");
        output.append("Wrapping papers: \n");
        for (WrappingPaper wrappingPaper : wrappingPapers) {
            output.append("\t" + wrappingPaper.toString() + "\n");
        }
        output.append("Ribbons: \n");
        for (Ribbon ribbon : ribbons) {
            output.append("\t" + ribbon.toString() + "\n");
        }
        return output.toString();
    }


}
