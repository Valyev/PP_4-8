package com.BouquetStore.JunitTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.BouquetStore.Accessories.Ribbon;
import com.BouquetStore.Accessories.Vase;
import com.BouquetStore.Accessories.WrappingPaper;
import com.BouquetStore.Bouquet;
import com.BouquetStore.Flowers.Flower;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BouquetTest {
    private Bouquet bouquet;
    private Flower flower1;
    private Flower flower2;
    private Flower flower3;
    private Vase vase;
    private Vase vase2;
    private WrappingPaper wrappingPaper1;
    private WrappingPaper wrappingPaper2;
    private Ribbon ribbon1;
    private Ribbon ribbon2;
    private Ribbon ribbon3;
    private Ribbon ribbon4;

    @Before
    public void setUp() {
        bouquet = new Bouquet();
        flower1 = new Flower("Rose", 10, 10, 10);
        flower2 = new Flower("Lily", 20, 20, 20);
        flower3 = new Flower("Daisy", 5, 5, 5);
        vase = new Vase(5, "Glass");
        vase2 = new Vase(7,"gourd");
        wrappingPaper1 = new WrappingPaper(2, "Red");
        wrappingPaper2 = new WrappingPaper(3, "Green");
        ribbon1 = new Ribbon(1, "Blue");
        ribbon2 = new Ribbon(2, "Pink");
        ribbon3 = new Ribbon(3,"Violet");
        ribbon4= new Ribbon(4,"Golden");

    }

    @Test
    public void testAddFlower() {
        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        List<Flower> expectedFlowers = new ArrayList<>();
        expectedFlowers.add(flower1);
        expectedFlowers.add(flower2);
        assertEquals(expectedFlowers, bouquet.getFlowers());
        assertEquals(30.0, bouquet.getPrice(), 0.0);
    }

    @Test
    public void testAddVase() {
        bouquet.addVase(vase);
        assertEquals(vase, bouquet.getVase());
        assertEquals(5.0, bouquet.getPrice(), 0.0);


        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        bouquet.addVase(vase2);
        String expectedOutput = "A bouquet can only have one vase." + lineSeparator;
        assertEquals(expectedOutput, consoleContent.toString());

    }

    @Test
    public void testAddWrappingPaper() {
        bouquet.addWrappingPaper(wrappingPaper1);
        bouquet.addWrappingPaper(wrappingPaper2);
        List<WrappingPaper> expectedWrappingPapers = new ArrayList<>();
        expectedWrappingPapers.add(wrappingPaper1);
        expectedWrappingPapers.add(wrappingPaper2);
        assertEquals(expectedWrappingPapers, bouquet.getWrappingPapers());
        assertEquals(5.0, bouquet.getPrice(), 0.0);
    }

    @Test
    public void testAddWrappingPaperLimit() {
        bouquet.addWrappingPaper(wrappingPaper1);
        bouquet.addWrappingPaper(wrappingPaper2);
        bouquet.addWrappingPaper(new WrappingPaper(4, "Blue"));
        List<WrappingPaper> expectedWrappingPapers = new ArrayList<>();
        expectedWrappingPapers.add(wrappingPaper1);
        expectedWrappingPapers.add(wrappingPaper2);
        assertEquals(expectedWrappingPapers, bouquet.getWrappingPapers());
    }

    @Test
    public void testAddRibbon() {
        bouquet.addRibbon(ribbon1);
        bouquet.addRibbon(ribbon2);
        bouquet.addRibbon(ribbon3);
        List<Ribbon> expectedRibbons = new ArrayList<>();
        expectedRibbons.add(ribbon1);
        expectedRibbons.add(ribbon2);
        expectedRibbons.add(ribbon3);
        assertEquals(expectedRibbons, bouquet.getRibbons());
        assertEquals(6, bouquet.getPrice(), 0.0);

        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        bouquet.addRibbon(ribbon4);
        String expectedOutput = "A bouquet can only have three ribbons." + lineSeparator;
        assertEquals(expectedOutput, consoleContent.toString());

    }

    @Test
    public void testSortByFreshness() {
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        bouquet.addFlower(flower3);

        bouquet.sortByFreshness();
        List<Flower> expectedSortedFlowers = new ArrayList<>();
        expectedSortedFlowers.add(flower2);
        expectedSortedFlowers.add(flower1);
        expectedSortedFlowers.add(flower3);

        assertEquals(expectedSortedFlowers, bouquet.getFlowers());
    }

    @Test
    public void testFindByStemLength() {
        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        bouquet.addFlower(flower3);
        String lineSeparator = System.lineSeparator();

        ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        bouquet.findByStemLength(5, 12);
        String expectedOutput = "Found flowers:" + lineSeparator +
                "Name: Rose, Freshness: 10.0%, Stem length: 10.0, Price: 10.0" + lineSeparator +
                "Name: Daisy, Freshness: 5.0%, Stem length: 5.0, Price: 5.0" + lineSeparator;

        assertEquals(expectedOutput, consoleContent.toString());

        consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        bouquet.findByStemLength(15, 25);
        expectedOutput = "Found flowers:" + lineSeparator +
                "Name: Lily, Freshness: 20.0%, Stem length: 20.0, Price: 20.0" + lineSeparator;

        assertEquals(expectedOutput, consoleContent.toString());

        consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        bouquet.findByStemLength(25, 35);
        expectedOutput = "No flowers found with stem length between 25.0 and 35.0." + lineSeparator;

        assertEquals(expectedOutput, consoleContent.toString());

    }

    @Test
    public void testDeleteFlower() {
        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        bouquet.addFlower(flower3);

        bouquet.deleteFlower("Rose");

        List<Flower> expectedFlowers = new ArrayList<>();
        expectedFlowers.add(flower2);
        expectedFlowers.add(flower3);
        assertEquals(expectedFlowers, bouquet.getFlowers());
    }

    @Test
    public void testDeleteFlowerNotFound() {
        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        bouquet.addFlower(flower3);
        String lineSeparator = System.lineSeparator();

        ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));

        bouquet.deleteFlower("Tulip");

        assertEquals("Typed flower not found" + lineSeparator, consoleContent.toString());
    }

    @Test
    public void testDeleteVase() {
        bouquet.addVase(vase);
        bouquet.deleteVase();

        assertNull(bouquet.getVase());
    }

    @Test
    public void testDeleteVaseWithoutVase() {
        String lineSeparator = System.lineSeparator();

        ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleContent));
        bouquet.deleteVase();
        String expectedOutput = "The bouquet doesn't have a vase." + lineSeparator;
        assertEquals(expectedOutput, consoleContent.toString());
    }

    @Test
    public void testDeleteRibbon() {

        bouquet.addRibbon(ribbon1);
        bouquet.addRibbon(ribbon2);
        bouquet.deleteRibbon("Blue");
        List<Ribbon> expectedRibbons = new ArrayList<>();
        expectedRibbons.add(ribbon2);
        assertEquals(expectedRibbons, bouquet.getRibbons());
        assertEquals(2, bouquet.getPrice(), 0.0);

    }

    @Test
    public void testDeleteRibbonNotFound() {
        bouquet.addRibbon(ribbon1);
        bouquet.addRibbon(ribbon2);
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream consloleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consloleOutput));

        bouquet.deleteRibbon("Yellow");

        assertEquals("Typed ribbon not found"+lineSeparator, consloleOutput.toString());
    }
    @Test
    public void testDeleteWrappingPaper() {
        bouquet.addWrappingPaper(wrappingPaper1);
        bouquet.addWrappingPaper(wrappingPaper2);
        bouquet.deleteWrappingPaper("Red");
        List<WrappingPaper> expectedWrappingPapers = new ArrayList<>();
        expectedWrappingPapers.add(wrappingPaper2);
        assertEquals(expectedWrappingPapers, bouquet.getWrappingPapers());

        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        bouquet.deleteWrappingPaper("Orange");
        String expectedOutput = "Typed wrapper not found"+lineSeparator;
        assertEquals(expectedOutput, consoleOutput.toString());
    }
    @Test
    public void testShowPrice() {
        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        bouquet.addWrappingPaper(wrappingPaper1);
        bouquet.addWrappingPaper(wrappingPaper2);
        bouquet.addRibbon(ribbon1);
        bouquet.addRibbon(ribbon2);
        bouquet.addVase(vase);
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        bouquet.showPrice();
        assertEquals("The bouquet price is: "+bouquet.getPrice()+lineSeparator, consoleOutput.toString());
    }
    @Test
    public void testToString() {
        Bouquet bouquet = new Bouquet();

        // add some flowers, vase, wrapping paper, and ribbon to the bouquet
        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        bouquet.addVase(vase);
        bouquet.addWrappingPaper(wrappingPaper1);
        bouquet.addWrappingPaper(wrappingPaper2);
        bouquet.addRibbon(ribbon1);
        bouquet.addRibbon(ribbon2);
        String expected = "Bouquet: \n" +
                "Flowers: \n" +
                "\tName: Rose, Freshness: 10.0%, Stem length: 10.0, Price: 10.0\n" +
                "\tName: Lily, Freshness: 20.0%, Stem length: 20.0, Price: 20.0\n" +
                "Vase: Name: Glass, Price: 5.0\n" +
                "Wrapping papers: \n" +
                "\tName: Red, Price: 2.0\n" +
                "\tName: Green, Price: 3.0\n" +
                "Ribbons: \n" +
                "\tName: Blue, Price: 1.0\n" +
                "\tName: Pink, Price: 2.0\n";
        assertEquals(expected, bouquet.toString());
    }
}




