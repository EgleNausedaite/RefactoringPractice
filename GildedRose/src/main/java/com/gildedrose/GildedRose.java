package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAXIMUM_QUALITY = 50;
    public static final String CONJURED = "Conjured";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            handleIFNormalItem(item);
            handleIFAgedBrie(item);
            handleIFBackstagePasses(item);
            handleIFSulfuras(item);
            handleIFConjured(item);
        }
    }

    private void handleIFConjured(Item item) {
        if (isConjured(item)){
            item.sellIn--;
            if (item.quality < MAXIMUM_QUALITY) {
                item.quality++;
            }
            item.quality = item.quality - 2;
        }
    }

    private void handleIFSulfuras(Item item) {
        if (isSulfuras(item)){
            //do nothing
        }
    }

    private void handleIFBackstagePasses(Item item) {
        if (isBackstagePasses(item)) {
            item.sellIn--;
            if (item.quality < MAXIMUM_QUALITY) {
                item.quality++;
                if (item.sellIn < 11) {
                    item.quality++;
                    if (item.sellIn < 6) {
                        item.quality++;
                    }
                }
            }
            if (item.sellIn < 0) {
                item.quality = 0;
            }
        }
    }

    private void handleIFAgedBrie(Item item) {
        if(isAgedBrie(item)) {
            item.sellIn--;
            if (item.quality < MAXIMUM_QUALITY) {
                item.quality++;
                if (item.sellIn < 0){
                    item.quality++;
                }
            }
        }
    }

    private void handleIFNormalItem(Item item) {
        if (isNormalItem(item)) {
            item.sellIn--;
            if (item.sellIn <= 0) {
                item.quality = item.quality - 2;
            } else {
                item.quality--;
            }
            if (item.quality < 0) {
                item.quality = 0;
            }
        }
    }

    private boolean isNormalItem(Item item) {
        if (!(isSulfuras(item) || isAgedBrie(item) || isBackstagePasses(item))){
            return true;
        }
        return false;
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
    
    private boolean isConjured(Item item) {
        return item.name.equals(CONJURED);
    }
}