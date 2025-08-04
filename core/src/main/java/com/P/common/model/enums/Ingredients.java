package com.P.common.model.enums;

import com.P.Client.model.GameAssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum Ingredients {
    WOOD("wood", IngredientsTypes.dairy, 10, 0, GameAssetManager.WOOD),
    STONE("stone", IngredientsTypes.dairy, 20, 0, GameAssetManager.STONE),
    HAY("hay", IngredientsTypes.dairy, 50, 0, GameAssetManager.HAY),
    EGG("egg", IngredientsTypes.dairy, 50, 0, GameAssetManager.EGG),
    DUCK_EGG("duck egg", IngredientsTypes.dairy, 95, 0, GameAssetManager.DUCK_EGG),
    DINOSAUR_EGG("dinosaur egg", IngredientsTypes.dairy, 350, 0, GameAssetManager.DINOSAUR_EGG),
    MILK("milk", IngredientsTypes.dairy, 190, 0, GameAssetManager.MILK),
    GOAT_MILK("goat milk", IngredientsTypes.dairy, 345, 0, GameAssetManager.GOAT_MILK),
    DUCK_FEATHER("duck feather", IngredientsTypes.dairy, 250, 0, GameAssetManager.DUCK_FEATHER),
    WOOL("wool", IngredientsTypes.dairy, 340, 0, GameAssetManager.WOOL),
    RABBIT_PIE("rabbit pie", IngredientsTypes.dairy, 565, 0, GameAssetManager.RABBIT_PIE),
    TRUFFLE("truffle", IngredientsTypes.dairy, 625, 0, GameAssetManager.TRUFFLE),

    RICE("rice", IngredientsTypes.junk, 200, 0, GameAssetManager.RICE),
    SUGAR("sugar", IngredientsTypes.junk, 100, 0, GameAssetManager.SUGAR),
    WHEAT_FLOUR("wheat flour", IngredientsTypes.junk, 100, 0, GameAssetManager.WHEAT_FLOUR),
    OIL("oil", IngredientsTypes.junk, 200, 0, GameAssetManager.OIL),
    VINEGAR("vinegar", IngredientsTypes.junk, 200, 0, GameAssetManager.VINEGAR),

    SpeedGro("speed gro", IngredientsTypes.junk, 100, 0, GameAssetManager.SPEED_GRO),
    BasicRetainingSoil("basic retaining soil", IngredientsTypes.junk, 100, 0, GameAssetManager.BASIC_RETAINING_SOIL),
    QualityRetainingSoil("quality retaining soil", IngredientsTypes.junk, 150, 0, GameAssetManager.QUALITY_RETAINING_SOIL),
    DeluxeRetainingSoil("deluxeRetainingSoil", IngredientsTypes.junk, 150, 0, GameAssetManager.DELUXE_RETAINING_SOIL),

    Bouquet("bouquet", IngredientsTypes.junk, 1000, 0, GameAssetManager.BOUQUET),
    WeddingRing("wedding ring", IngredientsTypes.junk, 10000, 0, GameAssetManager.WEDDING_RING),

    JOJA_COLA("joja cola", IngredientsTypes.junk, 75, 0, GameAssetManager.JOJA_COLA),

    BEER("beer", IngredientsTypes.food, 400, 0, GameAssetManager.BEER),
    SALAD("salad", IngredientsTypes.food, 220, 0, GameAssetManager.SALAD),
    BREAD("bread", IngredientsTypes.food, 120, 0, GameAssetManager.BREAD),
    SPAGHETTI("spaghetti", IngredientsTypes.food, 240, 0, GameAssetManager.SPAGHETTI),
    PIZZA("pizza", IngredientsTypes.food, 600, 0, GameAssetManager.PIZZA),
    COFFEE("coffee", IngredientsTypes.food, 300, 0, GameAssetManager.COFFEE),
    TROUT_SOUP("trout soup", IngredientsTypes.food, 250, 0, GameAssetManager.TROUT_SOUP),

    COOPER_ORE("cooper ore", IngredientsTypes.ore, 75, 0, GameAssetManager.COOPER_ORE),
    IRON_ORE("iron ore", IngredientsTypes.ore, 150, 0, GameAssetManager.IRON_ORE),
    GOLD_ORE("gold ore", IngredientsTypes.ore, 400, 0, GameAssetManager.GOLD_ORE),
    IRIDIUM_ORE("iridium ore", IngredientsTypes.ore, 0, 0, GameAssetManager.IRIDIUM_ORE),

    BLUE_JAZZ("blue jazz", IngredientsTypes.fruit, 50, 45, GameAssetManager.BLUE_JAZZ),
    CARROT("carrot", IngredientsTypes.fruit, 35, 75, GameAssetManager.CARROT),
    CAULIFLOWER("cauliflower", IngredientsTypes.fruit, 175, 75, GameAssetManager.CAULIFLOWER),
    COFFEE_BEAN("coffee bean", IngredientsTypes.vegetable, 15, 0, GameAssetManager.COFFEE_BEAN),
    GARLIC("garlic", IngredientsTypes.vegetable, 60, 20, GameAssetManager.GARLIC),
    GREEN_BEAN("green bean", IngredientsTypes.vegetable, 40, 25, GameAssetManager.GREEN_BEAN),
    KALE("kale", IngredientsTypes.vegetable, 110, 50, GameAssetManager.KALE),
    PARSNIP("parsnip", IngredientsTypes.vegetable, 35, 25, GameAssetManager.PARSNIP),
    POTATO("potato", IngredientsTypes.vegetable, 80, 25, GameAssetManager.POTATO),
    RHUBARB("rhubarb", IngredientsTypes.fruit, 220, 0, GameAssetManager.RHUBARB),
    STRAWBERRY("strawberry", IngredientsTypes.fruit, 120, 50, GameAssetManager.STRAWBERRY),
    TULIP("tulip", IngredientsTypes.fruit, 30, 45, GameAssetManager.TULIP),
    UNMILLED_RICE("unmilled rice", IngredientsTypes.vegetable, 30, 3, GameAssetManager.UNMILLED_RICE),
    BLUEBERRY("blueberry", IngredientsTypes.fruit, 50, 25, GameAssetManager.BLUEBERRY),
    CORN("corn", IngredientsTypes.vegetable, 50, 25, GameAssetManager.CORN),
    HOPS("hops", IngredientsTypes.fruit, 25, 45, GameAssetManager.HOPS),
    HOT_PEPPER("hot pepper", IngredientsTypes.vegetable, 40, 13, GameAssetManager.HOT_PEPPER),
    MELON("melon", IngredientsTypes.fruit, 250, 113, GameAssetManager.MELON),
    POPPY("poppy", IngredientsTypes.fruit, 140, 45, GameAssetManager.POPPY),
    RADISH("radish", IngredientsTypes.fruit, 90, 45, GameAssetManager.RADISH),
    RED_CABBAGE("red cabbage", IngredientsTypes.vegetable, 260, 75, GameAssetManager.RED_CABBAGE),
    STARFRUIT("starfruit", IngredientsTypes.vegetable, 750, 125, GameAssetManager.STARFRUIT),
    SUMMER_SPANGLE("summer spangle", IngredientsTypes.fruit, 90, 45, GameAssetManager.SUMMER_SPANGLE),
    SUMMER_SQUASH("summer squash", IngredientsTypes.fruit, 45, 63, GameAssetManager.SUMMER_SQUASH),
    SUNFLOWER("sunflower", IngredientsTypes.fruit, 80, 45, GameAssetManager.SUNFLOWER),
    TOMATO("tomato", IngredientsTypes.vegetable, 60, 20, GameAssetManager.TOMATO),
    WHEAT("wheat", IngredientsTypes.vegetable, 25, 0, GameAssetManager.WHEAT),
    AMARANTH("amaranth", IngredientsTypes.fruit, 150, 50, GameAssetManager.AMARANTH),
    ARTICHOKE("artichoke", IngredientsTypes.fruit, 160, 30, GameAssetManager.ARTICHOKE),
    BEET("beet", IngredientsTypes.fruit, 100, 30, GameAssetManager.BEET),
    BOK_CHOY("bok choy", IngredientsTypes.vegetable, 80, 25, GameAssetManager.BOK_CHOY),
    BROCCOLI("broccoli", IngredientsTypes.vegetable, 70, 63, GameAssetManager.BROCCOLI),
    CRANBERRIES("cranberries", IngredientsTypes.fruit, 75, 38, GameAssetManager.CRANBERRIES),
    EGGPLANT("eggplant", IngredientsTypes.vegetable, 60, 20, GameAssetManager.EGGPLANT),
    FAIRY_ROSE("fairy rose", IngredientsTypes.fruit, 290, 45, GameAssetManager.FAIRY_ROSE),
    GRAPE("grape", IngredientsTypes.fruit, 80, 38, GameAssetManager.GRAPE),
    PUMPKIN("pumpkin", IngredientsTypes.vegetable, 320, 0, GameAssetManager.PUMPKIN),
    YAM("yam", IngredientsTypes.fruit, 160, 45, GameAssetManager.YAM),
    SWEET_GEM_BERRY("sweet gem berry", IngredientsTypes.fruit, 3000, 0, GameAssetManager.SWEET_GEM_BERRY),
    POWDERMELON("powdermelon", IngredientsTypes.fruit, 60, 63, GameAssetManager.POWDERMELON),
    ANCIENT_FRUIT("ancient fruit", IngredientsTypes.fruit, 550, 0, GameAssetManager.ANCIENT_FRUIT),

    COMMON_MUSHROOM("common mushroom", IngredientsTypes.vegetable, 40, 38, GameAssetManager.COMMON_MUSHROOM),
    DAFFODIL("daffodil", IngredientsTypes.vegetable, 30, 0, GameAssetManager.DAFFODIL),
    DANDELION("dandelion", IngredientsTypes.vegetable, 40, 25, GameAssetManager.DANDELION),
    LEEK("leek", IngredientsTypes.vegetable, 60, 40, GameAssetManager.LEEK),
    MOREL("morel", IngredientsTypes.vegetable, 150, 20, GameAssetManager.MOREL),
    SALMONBERRY("salmonberry", IngredientsTypes.fruit, 5, 25, null),
    SPRING_ONION("spring onion", IngredientsTypes.vegetable, 8, 13, null),
    WILD_HORSERADISH("wild horseradish", IngredientsTypes.vegetable, 50, 13, null),
    FIDDLEHEAD_FERN("fiddlehead fern", IngredientsTypes.vegetable, 90, 25, null),
    RED_MUSHROOM("red mushroom", IngredientsTypes.vegetable, 75, -50, null),
    SPICE_BERRY("spice berry", IngredientsTypes.fruit, 80, 25, null),
    SWEET_PEA("sweet pea", IngredientsTypes.fruit, 50, 0, null),
    BLACKBERRY("blackberry", IngredientsTypes.fruit, 25, 25, null),
    CHANTERELLE("chanterelle", IngredientsTypes.fruit, 160, 75, null),
    HAZELNUT("hazelnut", IngredientsTypes.vegetable, 40, 38, null),
    PURPLE_MUSHROOM("purple mushroom", IngredientsTypes.vegetable, 90, 30, null),
    WILD_PLUM("wild plum", IngredientsTypes.vegetable, 80, 25, null),
    CROCUS("crocus", IngredientsTypes.vegetable, 60, 0, null),
    CRYSTAL_FRUIT("crystal fruit", IngredientsTypes.fruit, 150, 63, null),
    HOLLY("holly", IngredientsTypes.vegetable, 80, -37, null),
    SNOW_YAM("snow yam", IngredientsTypes.vegetable, 100, 30, null),
    WINTER_ROOT("winter root", IngredientsTypes.vegetable, 70, 25, null),


    Apricot("Apricot", IngredientsTypes.fruit, 59, 38, null),
    Cherry("Cherry", IngredientsTypes.fruit, 80, 38, null),
    Banana("Banana", IngredientsTypes.fruit, 150, 75, null),
    Mango("Mango", IngredientsTypes.fruit, 130, 100, null),
    Orange("Orange", IngredientsTypes.fruit, 100, 38, null),
    Peach("Peach", IngredientsTypes.fruit, 140, 38, null),
    Apple("Apple", IngredientsTypes.fruit, 100, 38, null),
    Pomegranate("Pomegranate", IngredientsTypes.fruit, 140, 38, null),
    OakResin("Oak Resin", IngredientsTypes.fruit, 150, 0, null),
    MapleSyrup("Maple Syrup", IngredientsTypes.fruit, 200, 0, null),
    PineTar("Pine Tar", IngredientsTypes.fruit, 100, 0, null),
    Sap("Sap", IngredientsTypes.fruit, 2, 2, null),
    CommonMushroom("Common Mushroom", IngredientsTypes.fruit, 40, 38, null),
    MysticSyrup("Mystic Syrup", IngredientsTypes.fruit, 1000, 500, null),

    QUARTZ("quartz", IngredientsTypes.mineral, 25, 0, null),
    EARTH_CRYSTAL("earth crystal", IngredientsTypes.mineral, 50, 0, null),
    FROZEN_TEAR("frozen tear", IngredientsTypes.mineral, 75, 0, null),
    FIRE_QUARTZ("fire quartz", IngredientsTypes.mineral, 100, 0, null),
    EMERALD("emerald", IngredientsTypes.mineral, 250, 0, null),
    AQUAMARINE("aquamarine", IngredientsTypes.mineral, 180, 0, null),
    RUBY("ruby", IngredientsTypes.mineral, 250, 0, null),
    AMETHYST("amethyst", IngredientsTypes.mineral, 100, 0, null),
    TOPAZ("topaz", IngredientsTypes.mineral, 80, 0, null),
    JADE("jade", IngredientsTypes.mineral, 200, 0, null),
    DIAMOND("diamond", IngredientsTypes.mineral, 750, 0, null),
    PRISMATIC_SHARD("prismatic shard", IngredientsTypes.mineral, 2000, 0, null),
    COPPER("copper", IngredientsTypes.mineral, 5, 0, null),
    IRON("iron", IngredientsTypes.mineral, 10, 0, null),
    GOLD("gold", IngredientsTypes.mineral, 25, 0,null),
    IRIDIUM("iridium", IngredientsTypes.mineral, 100, 0,null),
    COAL("coal", IngredientsTypes.mineral, 150, 0,null),

    CHERRY_BOMB("cherry bomb", IngredientsTypes.craftedObjects, 50, 0,null),
    BOMB("bomb", IngredientsTypes.craftedObjects, 50, 0,null),
    MEGA_BOMB("mega bomb", IngredientsTypes.craftedObjects, 50, 0,null),
    SPRINKLER("sprinkler", IngredientsTypes.craftedObjects, 0, 0,null),
    QUALITY_SPRINKLER("quality sprinkler", IngredientsTypes.craftedObjects, 0, 0,null),
    IRIDIUM_SPRINKLER("iridium sprinkler", IngredientsTypes.craftedObjects, 0, 0,null),
    CHARCOAL_KLIN("charcoal klin", IngredientsTypes.craftedObjects, 0, 0,null),
    FURNACE("furnace", IngredientsTypes.craftedObjects, 0, 0,null),
    SCARECROW("scarecrow", IngredientsTypes.craftedObjects, 0, 0,null),
    DELUXE_SCARECROW("deluxe scarecrow", IngredientsTypes.craftedObjects, 0, 0,null),
    BEE_HOUSE("bee house", IngredientsTypes.craftedObjects, 0, 0,null),
    CHEESE_PRESS("cheese press", IngredientsTypes.craftedObjects, 0, 0,null),
    KEG("keg", IngredientsTypes.craftedObjects, 0, 0,null),
    LOOM("loom", IngredientsTypes.craftedObjects, 0, 0,null),
    MAYONNAISE_MACHINE("mayonnaise machine", IngredientsTypes.craftedObjects, 0, 0,null),
    OIL_MAKER("oil maker", IngredientsTypes.craftedObjects, 0, 0,null),
    PRESERVES_JAR("preserves jar", IngredientsTypes.craftedObjects, 0, 0,null),
    DEHYDRATOR("dehydrator", IngredientsTypes.craftedObjects, 0, 0,null),
    GRASS_STARTER("grass starter", IngredientsTypes.craftedObjects, 0, 0,null),
    FISH_SMOKER("fish smoker", IngredientsTypes.craftedObjects, 0, 0,null),

    HONEY("honey", IngredientsTypes.food, 350, 75,null),
    Cheese("cheese", IngredientsTypes.food, 280, 100,null),
    GoatCheese("goat cheese", IngredientsTypes.food, 500, 100,null),
    Beer("beer", IngredientsTypes.food, 200, 50,null),
    Juice("juice", IngredientsTypes.food, 450, 100,null),
    Maed("maed", IngredientsTypes.food, 300, 100,null),
    PaleAle("pale apple", IngredientsTypes.food, 300, 50,null),
    Wine("wine", IngredientsTypes.food, 350, 150,null),
    DriedMushroom("dried mushroom", IngredientsTypes.food, 320, 50,null),
    DriedFruit("dried fruit", IngredientsTypes.food, 750, 75,null),
    Raisins("raisin", IngredientsTypes.food, 600, 125,null),
    Cloth("cloth", IngredientsTypes.junk, 470, 0,null),


    // Fishhhhhhhhhhhhhhhh
    Flounder("Flounder", IngredientsTypes.fish, 100, 0,null),
    LionFish("LionFish", IngredientsTypes.fish, 100, 0,null),
    Herring("Herring", IngredientsTypes.fish, 30, 0,null),
    GhostFish("Ghost Fish", IngredientsTypes.fish, 45, 0,null),
    Legend("Legend", IngredientsTypes.fish, 5000, 0,null),
    Tilapia("Tilapia", IngredientsTypes.fish, 75, 0,null),
    Dorado("Dorado", IngredientsTypes.fish, 100, 0,null),
    SunFish("Sunfish", IngredientsTypes.fish, 30, 0,null),
    RainbowTrout("Rainbow Trout", IngredientsTypes.fish, 65, 0,null),
    CrimsonFish("Crimsonfish", IngredientsTypes.fish, 1500, 0,null),
    Salmon("Salmon", IngredientsTypes.fish, 75, 0,null),
    Sardine("Sardine", IngredientsTypes.fish, 40, 0,null),
    Shad("Shad", IngredientsTypes.fish, 60, 0,null),
    BlueDiscus("Blue Discus", IngredientsTypes.fish, 120, 0,null),
    Angler("Angler", IngredientsTypes.fish, 900, 0,null),
    MidnightCarp("Midnight Carp", IngredientsTypes.fish, 150, 0,null),
    Squid("Squid", IngredientsTypes.fish, 80, 0,null),
    Tuna("Tuna", IngredientsTypes.fish, 100, 0,null),
    Perch("Perch", IngredientsTypes.fish, 55, 0,null),
    GlacierFish("Glacier Fish", IngredientsTypes.fish, 1000, 0,null),

    PUMPKIN_PIE("pumpkin pie", IngredientsTypes.food, 385, 225,null),

    ASH_BROWNS("hash brown", IngredientsTypes.food, 120, 90,null),
    FRIED_EGG("fried egg", IngredientsTypes.food, 35, 50,null),
    BAKED_FISH("baked fish", IngredientsTypes.food, 100, 85,null),
    OMELET("omelet", IngredientsTypes.food, 125, 100,null),
    TORTILLA("tortilla", IngredientsTypes.food, 50, 50,null),
    MAKI_ROLL("maki roll", IngredientsTypes.food, 220, 100,null),
    TRIPLE_SHOT_ESPRESSO("tripple shot espresso", IngredientsTypes.food, 450, 200,null),
    COOKIE("cookie", IngredientsTypes.food, 140, 90,null),
    PANCAKES("panCakes", IngredientsTypes.food, 80, 90,null),
    FRUIT_SALAD("fruit salad", IngredientsTypes.food, 450, 263,null),
    RED_PLATE("red plate", IngredientsTypes.food, 300, 125,null),
    SALMON_DINNER("salmon dinner", IngredientsTypes.food, 300, 125,null),
    VEGETABLE_MEDLEY("vegetable medley", IngredientsTypes.food, 120, 165,null),
    FARMERS_LUNCH("farmers lunch", IngredientsTypes.food, 150, 200,null),
    SURVIVAL_BURGER("survival burger", IngredientsTypes.food, 180, 125,null),
    SEA_DISH("sea dish", IngredientsTypes.food, 220, 150,null),
    SEAFORM_PUDDING("seaFood pudding", IngredientsTypes.food, 300, 175,null),
    MINERS_TREAT("miner's treat", IngredientsTypes.food, 200, 125,null),
    FIBER("fiber", IngredientsTypes.junk, 0, 0,null),
    WaterFertility("water fertility", IngredientsTypes.junk, 200, 0,null),
    SpeedFertility("speed fertility", IngredientsTypes.junk, 200, 0,null);

    private final String name;
    private final IngredientsTypes type;
    private final int price;
    private final int energy;
    private final Texture texture;


    Ingredients(String name, IngredientsTypes type, int price, int energy, Texture texture) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.energy = energy;
        this.texture = texture;
    }

    public IngredientsTypes getType() {
        return type;
    }

    public int getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
