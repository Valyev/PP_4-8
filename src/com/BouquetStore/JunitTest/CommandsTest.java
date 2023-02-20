package com.BouquetStore.JunitTest;

import com.BouquetStore.Accessories.Ribbon;
import com.BouquetStore.Accessories.Vase;
import com.BouquetStore.Accessories.WrappingPaper;
import com.BouquetStore.Commands.*;
import com.BouquetStore.Flowers.Flower;
import org.junit.Test;

import com.BouquetStore.Bouquet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.*;

public class CommandsTest {
    @Test
    public void TestManipulationsWithFlowers() {
        Bouquet bouquet = new Bouquet();
        Flower expectedFlower = new Flower("Rose", 10, 10, 10);

        AddFlowerCommand addFlowerCommand = new AddFlowerCommand(bouquet);
        RemoveFlowerCommand deleteFlower = new RemoveFlowerCommand(bouquet);
        GetPriceCommand getPriceCommand = new GetPriceCommand(bouquet);
        FindFlowerByStemLengthCommand findByStem = new FindFlowerByStemLengthCommand((bouquet));

        String input = "Rose\n10\n10\n10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        addFlowerCommand.execute();
//adding
        assertEquals("[" + expectedFlower.toString() + "]", bouquet.getFlowers().toString());
//price
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        getPriceCommand.execute();
        String lineSeparator = System.lineSeparator();
        assertEquals("The bouquet price is: " + bouquet.getPrice() + lineSeparator, consoleOutput.toString());
//is empty
        input = "Rose\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        deleteFlower.execute();
        assertEquals("[]", bouquet.getFlowers().toString());

        //find by stem
        bouquet.addFlower(new Flower("Rose", 10, 10, 10));
        bouquet.addFlower(new Flower("Lily", 20, 20, 20));
        bouquet.addFlower(new Flower("Daisy", 5, 5, 5));
        input = "4\n10\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        findByStem.execute();

        String expectedOutput = "Enter the minimum stem length: " + lineSeparator +
                "Enter the maximum stem length: " + lineSeparator +
                "Found flowers:" + lineSeparator +
                "Name: Rose, Freshness: 10.0%, Stem length: 10.0, Price: 10.0" + lineSeparator +
                "Name: Daisy, Freshness: 5.0%, Stem length: 5.0, Price: 5.0" + lineSeparator;

        assertEquals(expectedOutput, consoleContent.toString());

    }

    @Test
    public void TestAddVaseCommand() {

        Bouquet bouquet = new Bouquet();
        AddVaseCommand addVaseCommand = new AddVaseCommand(bouquet);
        DeleteVaseCommand deleteVase = new DeleteVaseCommand(bouquet);
        String input = "Glass\n5\n";
        Vase expectedVase = new Vase(5, "Glass");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        addVaseCommand.execute();
        assertEquals(expectedVase.toString(), bouquet.getVase().toString());

        input = "Glass\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        deleteVase.execute();
        assertNull(bouquet.getVase());
    }

    @Test
    public void TestAddRibbonCommand() {
        Bouquet bouquet = new Bouquet();
        AddRibbonCommand addribbon = new AddRibbonCommand(bouquet);
        DeleteRibbonCommand deleteRibbonCommand = new DeleteRibbonCommand(bouquet);
        String input = "Blue\n5\n";
        Ribbon expectedRibbon = new Ribbon(5, "Blue");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        addribbon.execute();
        assertEquals("[" + expectedRibbon.toString() + "]", bouquet.getRibbons().toString());
        assertEquals(5, bouquet.getPrice(), 0);
        input = "Blue\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        deleteRibbonCommand.execute();
        assertEquals(0, bouquet.getPrice(), 0);
    }

    @Test
    public void TestAddWrappingPaperCommand() {
        Bouquet bouquet = new Bouquet();
        AddWrappingPaperCommand addpaper = new AddWrappingPaperCommand(bouquet);
        DeleteWrappingPaperCommand deleteWrapping = new DeleteWrappingPaperCommand(bouquet);
        String input = "Blue\n5\n";
        WrappingPaper expectedWrappingpaper = new WrappingPaper(5, "Blue");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        addpaper.execute();
        assertEquals("[" + expectedWrappingpaper.toString() + "]", bouquet.getWrappingPapers().toString());
        assertEquals(5, bouquet.getPrice(), 0);
        input = "Blue\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        deleteWrapping.execute();
        assertEquals(0, bouquet.getPrice(), 0);
    }

    @Test
    public void TestExitCommand() {
        ExitCommand exit = new ExitCommand();
        String lineSeparator = lineSeparator();
        ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        exit.execute();

        assertEquals("Exiting the program..." + lineSeparator, consoleContent.toString());
    }

    @Test
    public void TestShowBouquetCommandAndSortByFreshness() {
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Flower("Rose",15,23,14));
        ShowBouquetCommand showBouquet = new ShowBouquetCommand(bouquet);

        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        showBouquet.execute();

        String expectedOutput = "Bouquet: \n" +
                "Flowers: \n" +
                "\tName: Rose, Freshness: 23.0%, Stem length: 14.0, Price: 15.0\n" +
                "Vase: null\n" +
                "Wrapping papers: \n" +
                "Ribbons: \n";
        assertEquals(expectedOutput, bouquet.toString());

        bouquet.addFlower(new Flower("Daisy",20,8,11));
        bouquet.addFlower(new Flower("Rose",15,30,15));

    }
    @Test
    public void TestSortByFreshnessCommand(){
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Flower("Rose",15,23,14));
        bouquet.addFlower(new Flower("Daisy",20,8,11));
        bouquet.addFlower(new Flower("Rose",15,30,15));
        SortBouquetByFreshnessCommand sortFreshness =new SortBouquetByFreshnessCommand(bouquet);

        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        sortFreshness.execute();
        String expectedOutput = "Sorted Flowers in the Bouquet:" +lineSeparator+
                "Name: Rose, Freshness: 30.0%, Stem length: 15.0, Price: 15.0" +lineSeparator+
                "Name: Rose, Freshness: 23.0%, Stem length: 14.0, Price: 15.0" +lineSeparator+
                "Name: Daisy, Freshness: 8.0%, Stem length: 11.0, Price: 20.0"+lineSeparator;
        assertEquals(expectedOutput,consoleOutput.toString());
    }
    @Test
    public void TestLoadFromFileCommand() throws IOException {
        Bouquet bouquet = new Bouquet();
        LoadFromFileCommand load = new LoadFromFileCommand(bouquet);
        AddExistingFlowerCommand flowerCommand = new AddExistingFlowerCommand(bouquet);
        AddExistingRibbonCommand ribbonCommand = new AddExistingRibbonCommand(bouquet);
        AddExistingVaseCommand vaseCommand = new AddExistingVaseCommand(bouquet);
        AddExistingWrappingPaperCommand wrappingPaperCommand = new AddExistingWrappingPaperCommand(bouquet);

        load.execute();

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        flowerCommand.execute();

        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ribbonCommand.execute();
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vaseCommand.execute();
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        wrappingPaperCommand.execute();

        String expectedOutput = "Bouquet: \n" +
                "Flowers: \n" +
                "\tName: Rose, Freshness: 90.0%, Stem length: 15.0, Price: 10.0\n" +
                "Vase: Name: Glass, Price: 4.0\n" +
                "Wrapping papers: \n" +
                "\tName: Gold, Price: 2.0\n" +
                "Ribbons: \n" +
                "\tName: Red, Price: 1.0\n";
        assertEquals(expectedOutput,bouquet.toString());

    }

}
