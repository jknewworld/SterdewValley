package com.P.common.model.enums;

public enum TreeName {
    ApricotTree("Apricot Tree", ForAgingSeeds.apricotSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SPRING}, Ingredients.APRICOT),
    CherryTree("Cherry Tree", ForAgingSeeds.cherrySapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SPRING}, Ingredients.CHERRY),
    BananaTree("Banana Tree", ForAgingSeeds.bananaSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.BANANA),
    MangoTree("Mango Tree", ForAgingSeeds.mangoSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.MANGO),
    OrangeTree("Orange Tree", ForAgingSeeds.orangeSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.ORANGE),
    PeachTree("Peach Tree", ForAgingSeeds.peachSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.PEACH),
    AppleTree("Apple Tree", ForAgingSeeds.appleSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN}, Ingredients.APPLE),
    PomegranateTree("Pomegranate Tree", ForAgingSeeds.pomegranateSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN}, Ingredients.POMEGRANATE),
    OakTree("Oak Tree", ForAgingSeeds.acorns, new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.OAKRESIN),
    MapleTree("Maple Tree", ForAgingSeeds.mapleSeeds, new int[]{7, 7, 7, 7}, 28, 9, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.MAPLESYRUP),
    PineTree("Pine Tree", ForAgingSeeds.pineCones, new int[]{7, 7, 7, 7}, 28, 5, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.PINETAR),
    MahoganyTree("Mahogany Tree", ForAgingSeeds.mahoganySeeds, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.SAP),
    MushroomTree("Mushroom Tree", ForAgingSeeds.mushroomTreeSeeds, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.COMMON_MUSHROOM),
    MysticTree("Mystic Tree", ForAgingSeeds.mysticTreeSeeds, new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.MYSTICSYRUP),
    BurntTree("Burnt Tree", null, null, 0, 0, null, Ingredients.COAL),
    ;


    private final String name;
    private final ForAgingSeeds source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final int fruitingCycle;
    private final Season[] seasons;
    private final Ingredients fruitType;

    TreeName(String name, ForAgingSeeds source, int[] stages, int totalHarvestTime, int fruitingCycle, Season[] seasons, Ingredients fruitType) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitingCycle = fruitingCycle;
        this.seasons = seasons;
        this.fruitType = fruitType;
    }

    public String getName() {
        return name;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public int[] getStages() {
        return stages;
    }

    public int getFruitingCycle() {
        return fruitingCycle;
    }

    public Ingredients getFruitType() {
        return fruitType;
    }

    public ForAgingSeeds getSource() {
        return source;
    }

    public Season[] getSeasons() {
        return seasons;
    }


}
