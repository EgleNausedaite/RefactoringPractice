package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item createAndUpdate(String itemName, int sellIn, int quality)
    {
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    @Test
    public void testFrameworkWorks() {
        Item item = createAndUpdate("foo", 0, 0);
        assertEquals("foo", item.name);
    }
    @Test
    public void systemLowersValues()
    {
        Item item = createAndUpdate("foo",15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }
    @Test
    public void qualityDegradesTwiceAsFast()
    {
        Item item = createAndUpdate("foo",0, 17);
        assertEquals(15, item.quality);
    }
    @Test
    public void qualityNeverNegative()
    {
        Item item = createAndUpdate("foo",0, 0);
        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieIncreasesInQuality()
    {
        Item item = createAndUpdate(GildedRose.AGED_BRIE, 0, 16);
        assertEquals(18, item.quality);
    }

    @Test
    public void qualityIsNeverMoreThanGildedRoseMaximum()
    {
        Item item = createAndUpdate(GildedRose.AGED_BRIE, 0, GildedRose.MAXIMUM_QUALITY);
        assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);
    }

    @Test
    public void sulfurasQualityNeverDecreases()
    {
        Item item = createAndUpdate(GildedRose.SULFURAS, 0, GildedRose.MAXIMUM_QUALITY);
        assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);
    }

    @Test
    public void backstageQualityIncreasesBy2When10DaysOrLess()
    {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 9, 29);
        assertEquals(31, item.quality);
    }

    @Test
    public void backstageQualityIncreasesBy3When5DaysOrLess()
    {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 3, 45);
        assertEquals(48, item.quality);
    }

    @Test
    public void backstageQualityDropsTo0AfterConcert()
    {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 0, 30);
        assertEquals(0, item.quality);
    }

    @Test
    public void itemQualityIncreasesBy1IfQualityLessThanGildedRoseMaximum()
    {
        Item item = createAndUpdate(GildedRose.AGED_BRIE, -1, 48);
        assertEquals(-2, item.sellIn);
        assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);
    }

    @Test
    public void itemsQualityTo0WhenSellinLessThan0OnBackstage()
    {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, -1, 30);
        assertEquals(-2, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    public void conjuredDegradeTwiceAsFast(){
        Item item = createAndUpdate(GildedRose.CONJURED, 15, 25);
        assertEquals(23, item.quality);
    }


}
