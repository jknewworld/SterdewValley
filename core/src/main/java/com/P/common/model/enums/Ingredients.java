package com.P.common.model.enums;

public enum Ingredients {
    WOOD("wood", IngredientsTypes.dairy, 10, 0, "game/crops/wood.png"),
    STONE("stone", IngredientsTypes.dairy, 20, 0, "game/crops/stone.png"),
    HAY("hay", IngredientsTypes.dairy, 50, 0, "game/crops/hay.png"),
    EGG("egg", IngredientsTypes.dairy, 50, 0, "game/crops/egg.png"),
    DUCK_EGG("duck_egg", IngredientsTypes.dairy, 95, 0, "game/crops/duck_egg.png"),
    DINOSAUR_EGG("dinosaur_egg", IngredientsTypes.dairy, 350, 0, "game/crops/dinosaur_egg.png"),
    MILK("milk", IngredientsTypes.dairy, 190, 0, "game/crops/milk.png"),
    GOAT_MILK("goat_milk", IngredientsTypes.dairy, 345, 0, "game/crops/goat_milk.png"),
    DUCK_FEATHER("duck_feather", IngredientsTypes.dairy, 250, 0, "game/crops/duck_feather.png"),
    WOOL("wool", IngredientsTypes.dairy, 340, 0, "game/crops/wool.png"),
    RABBIT_PIE("rabbit_pie", IngredientsTypes.dairy, 565, 0, "game/crops/rabbit_pie.png"),
    TRUFFLE("truffle", IngredientsTypes.dairy, 625, 0, "game/crops/truffle.png"),

    RICE("rice", IngredientsTypes.junk, 200, 0, "game/crops/rice.png"),
    SUGAR("sugar", IngredientsTypes.junk, 100, 0, "game/crops/sugar.png"),
    WHEAT_FLOUR("wheat_flour", IngredientsTypes.junk, 100, 0, "game/crops/wheat_flour.png"),
    OIL("oil", IngredientsTypes.junk, 200, 0, "game/crops/oil.png"),
    VINEGAR("vinegar", IngredientsTypes.junk, 200, 0, "game/crops/vinegar.png"),


    SpeedGro("speed gro", IngredientsTypes.junk, 100, 0, "game/crops/speed_gro.png"),
    BasicRetainingSoil("basic retaining soil", IngredientsTypes.junk, 100, 0, "game/crops/basic_retaining_soil.png"),
    QualityRetainingSoil("quality retaining soil", IngredientsTypes.junk, 150, 0, "game/crops/quality_retaining_soil.png"),
    DeluxeRetainingSoil("deluxeRetainingSoil", IngredientsTypes.junk, 150, 0, "game/crops/deluxeRetainingSoil.png"),
    Bouquet("bouquet", IngredientsTypes.junk, 1000, 0, "game/crops/bouquet.png"),
    WeddingRing("wedding ring", IngredientsTypes.junk, 10000, 0, "game/crops/wedding_ring.png"),
    JOJA_COLA("joja cola", IngredientsTypes.junk, 75, 0, "game/crops/joja_cola.png"),
    BEER("beer", IngredientsTypes.food, 400, 0, "game/crops/beer.png"),
    SALAD("salad", IngredientsTypes.food, 220, 0, "game/crops/salad.png"),
    BREAD("bread", IngredientsTypes.food, 120, 0, "game/crops/bread.png"),
    SPAGHETTI("spaghetti", IngredientsTypes.food, 240, 0, "game/crops/spaghetti.png"),
    PIZZA("pizza", IngredientsTypes.food, 600, 0, "game/crops/pizza.png"),
    COFFEE("coffee", IngredientsTypes.food, 300, 0, "game/crops/coffee.png"),
    TROUT_SOUP("trout soup", IngredientsTypes.food, 250, 0, "game/crops/trout_soup.png"),
    COOPER_ORE("cooper ore", IngredientsTypes.ore, 75, 0, "game/crops/cooper_ore.png"),
    IRON_ORE("iron ore", IngredientsTypes.ore, 150, 0, "game/crops/iron_ore.png"),
    GOLD_ORE("gold ore", IngredientsTypes.ore, 400, 0, "game/crops/gold_ore.png"),
    IRIDIUM_ORE("iridium ore", IngredientsTypes.ore, 0, 0, "game/crops/iridium_ore.png"),
    BLUE_JAZZ("blue jazz", IngredientsTypes.fruit, 50, 45, "game/crops/blue_jazz.png"),
    CARROT("carrot", IngredientsTypes.fruit, 35, 75, "game/crops/carrot.png"),
    CAULIFLOWER("cauliflower", IngredientsTypes.fruit, 175, 75, "game/crops/cauliflower.png"),
    COFFEE_BEAN("coffee bean", IngredientsTypes.vegetable, 15, 0, "game/crops/coffee_bean.png"),
    GARLIC("garlic", IngredientsTypes.vegetable, 60, 20, "game/crops/garlic.png"),
    GREEN_BEAN("green bean", IngredientsTypes.vegetable, 40, 25, "game/crops/green_bean.png"),
    KALE("kale", IngredientsTypes.vegetable, 110, 50, "game/crops/kale.png"),
    PARSNIP("parsnip", IngredientsTypes.vegetable, 35, 25, "game/crops/parsnip.png"),
    POTATO("potato", IngredientsTypes.vegetable, 80, 25, "game/crops/potato.png"),
    RHUBARB("rhubarb", IngredientsTypes.fruit, 220, 0, "game/crops/rhubarb.png"),
    STRAWBERRY("strawberry", IngredientsTypes.fruit, 120, 50, "game/crops/strawberry.png"),
    TULIP("tulip", IngredientsTypes.fruit, 30, 45, "game/crops/tulip.png"),
    UNMILLED_RICE("unmilled rice", IngredientsTypes.vegetable, 30, 3, "game/crops/unmilled_rice.png"),
    BLUEBERRY("blueberry", IngredientsTypes.fruit, 50, 25, "game/crops/blueberry.png"),
    CORN("corn", IngredientsTypes.vegetable, 50, 25, "game/crops/corn.png"),
    HOPS("hops", IngredientsTypes.fruit, 25, 45, "game/crops/hops.png"),
    HOT_PEPPER("hot pepper", IngredientsTypes.vegetable, 40, 13, "game/crops/hot_pepper.png"),
    MELON("melon", IngredientsTypes.fruit, 250, 113, "game/crops/melon.png"),
    POPPY("poppy", IngredientsTypes.fruit, 140, 45, "game/crops/poppy.png"),
    RADISH("radish", IngredientsTypes.fruit, 90, 45, "game/crops/radish.png"),
    RED_CABBAGE("red cabbage", IngredientsTypes.vegetable, 260, 75, "game/crops/red_cabbage.png"),
    STARFRUIT("starfruit", IngredientsTypes.vegetable, 750, 125, "game/crops/starfruit.png"),
    SUMMER_SPANGLE("summer spangle", IngredientsTypes.fruit, 90, 45, "game/crops/summer_spangle.png"),
    SUMMER_SQUASH("summer squash", IngredientsTypes.fruit, 45, 63, "game/crops/summer_squash.png"),
    SUNFLOWER("sunflower", IngredientsTypes.fruit, 80, 45, "game/crops/sunflower.png"),
    TOMATO("tomato", IngredientsTypes.vegetable, 60, 20, "game/crops/tomato.png"),
    WHEAT("wheat", IngredientsTypes.vegetable, 25, 0, "game/crops/wheat.png"),
    AMARANTH("amaranth", IngredientsTypes.fruit, 150, 50, "game/crops/amaranth.png"),
    ARTICHOKE("artichoke", IngredientsTypes.fruit, 160, 30, "game/crops/artichoke.png"),
    BEET("beet", IngredientsTypes.fruit, 100, 30, "game/crops/beet.png"),
    BOK_CHOY("bok choy", IngredientsTypes.vegetable, 80, 25, "game/crops/bok_choy.png"),
    BROCCOLI("broccoli", IngredientsTypes.vegetable, 70, 63, "game/crops/broccoli.png"),
    CRANBERRIES("cranberries", IngredientsTypes.fruit, 75, 38, "game/crops/cranberries.png"),
    EGGPLANT("eggplant", IngredientsTypes.vegetable, 60, 20, "game/crops/eggplant.png"),
    FAIRY_ROSE("fairy rose", IngredientsTypes.fruit, 290, 45, "game/crops/fairy_rose.png"),
    GRAPE("grape", IngredientsTypes.fruit, 80, 38, "game/crops/grape.png"),
    PUMPKIN("pumpkin", IngredientsTypes.vegetable, 320, 0, "game/crops/pumpkin.png"),
    YAM("yam", IngredientsTypes.fruit, 160, 45, "game/crops/yam.png"),
    SWEET_GEM_BERRY("sweet gem berry", IngredientsTypes.fruit, 3000, 0, "game/crops/sweet_gem_berry.png"),
    POWDERMELON("powdermelon", IngredientsTypes.fruit, 60, 63, "game/crops/powdermelon.png"),
    ANCIENT_FRUIT("ancient fruit", IngredientsTypes.fruit, 550, 0, "game/crops/ancient_fruit.png"),
    COMMON_MUSHROOM("common mushroom", IngredientsTypes.vegetable, 40, 38, "game/crops/common_mushroom.png"),
    DAFFODIL("daffodil", IngredientsTypes.vegetable, 30, 0, "game/crops/daffodil.png"),
    DANDELION("dandelion", IngredientsTypes.vegetable, 40, 25, "game/crops/dandelion.png"),
    LEEK("leek", IngredientsTypes.vegetable, 60, 40, "game/crops/leek.png"),
    MOREL("morel", IngredientsTypes.vegetable, 150, 20, "game/crops/morel.png"),
    SALMONBERRY("salmonberry", IngredientsTypes.fruit, 5, 25, "game/crops/salmonberry.png"),SPRING_ONION("spring onion", IngredientsTypes.vegetable, 8, 13, "game/crops/SPRING_ONION.png"),
    WILD_HORSERADISH("wild horseradish", IngredientsTypes.vegetable, 50, 13, "game/crops/WILD_HORSERADISH.png"),
    FIDDLEHEAD_FERN("fiddlehead fern", IngredientsTypes.vegetable, 90, 25, "game/crops/FIDDLEHEAD_FERN.png"),
    RED_MUSHROOM("red mushroom", IngredientsTypes.vegetable, 75, -50, "game/crops/RED_MUSHROOM.png"),
    SPICE_BERRY("spice berry", IngredientsTypes.fruit, 80, 25, "game/crops/SPICE_BERRY.png"),
    SWEET_PEA("sweet pea", IngredientsTypes.fruit, 50, 0, "game/crops/SWEET_PEA.png"),
    BLACKBERRY("blackberry", IngredientsTypes.fruit, 25, 25, "game/crops/BLACKBERRY.png"),
    CHANTERELLE("chanterelle", IngredientsTypes.fruit, 160, 75, "game/crops/CHANTERELLE.png"),
    HAZELNUT("hazelnut", IngredientsTypes.vegetable, 40, 38, "game/crops/HAZELNUT.png"),
    PURPLE_MUSHROOM("purple mushroom", IngredientsTypes.vegetable, 90, 30, "game/crops/PURPLE_MUSHROOM.png"),
    WILD_PLUM("wild plum", IngredientsTypes.vegetable, 80, 25, "game/crops/WILD_PLUM.png"),
    CROCUS("crocus", IngredientsTypes.vegetable, 60, 0, "game/crops/CROCUS.png"),
    CRYSTAL_FRUIT("crystal fruit", IngredientsTypes.fruit, 150, 63, "game/crops/CRYSTAL_FRUIT.png"),
    HOLLY("holly", IngredientsTypes.vegetable, 80, -37, "game/crops/HOLLY.png"),
    SNOW_YAM("snow yam", IngredientsTypes.vegetable, 100, 30, "game/crops/SNOW_YAM.png"),
    WINTER_ROOT("winter root", IngredientsTypes.vegetable, 70, 25, "game/crops/WINTER_ROOT.png"),

    APRICOT("Apricot", IngredientsTypes.fruit, 59, 38, "game/crops/APRICOT.png"),
    CHERRY("Cherry", IngredientsTypes.fruit, 80, 38, "game/crops/CHERRY.png"),
    BANANA("Banana", IngredientsTypes.fruit, 150, 75, "game/crops/BANANA.png"),
    MANGO("Mango", IngredientsTypes.fruit, 130, 100, "game/crops/MANGO.png"),
    ORANGE("Orange", IngredientsTypes.fruit, 100, 38, "game/crops/ORANGE.png"),
    PEACH("Peach", IngredientsTypes.fruit, 140, 38, "game/crops/PEACH.png"),
    APPLE("Apple", IngredientsTypes.fruit, 100, 38, "game/crops/APPLE.png"),
    POMEGRANATE("Pomegranate", IngredientsTypes.fruit, 140, 38, "game/crops/POMEGRANATE.png"),
    OAKRESIN("Oak Resin", IngredientsTypes.fruit, 150, 0, "game/crops/OAKRESIN.png"),
    MAPLESYRUP("Maple Syrup", IngredientsTypes.fruit, 200, 0, "game/crops/MAPLESYRUP.png"),
    PINETAR("Pine Tar", IngredientsTypes.fruit, 100, 0, "game/crops/PINETAR.png"),
    SAP("Sap", IngredientsTypes.fruit, 2, 2, "game/crops/SAP.png"),
    COMMONMUSHROOM("Common Mushroom", IngredientsTypes.fruit, 40, 38, "game/crops/COMMONMUSHROOM.png"),
    MYSTICSYRUP("Mystic Syrup", IngredientsTypes.fruit, 1000, 500, "game/crops/MYSTICSYRUP.png"),

    QUARTZ("quartz", IngredientsTypes.mineral, 25, 0, "game/crops/QUARTZ.png"),
    EARTH_CRYSTAL("earth crystal", IngredientsTypes.mineral, 50, 0, "game/crops/EARTH_CRYSTAL.png"),
    FROZEN_TEAR("frozen tear", IngredientsTypes.mineral, 75, 0, "game/crops/FROZEN_TEAR.png"),
    FIRE_QUARTZ("fire quartz", IngredientsTypes.mineral, 100, 0, "game/crops/FIRE_QUARTZ.png"),
    EMERALD("emerald", IngredientsTypes.mineral, 250, 0, "game/crops/EMERALD.png"),
    AQUAMARINE("aquamarine", IngredientsTypes.mineral, 180, 0, "game/crops/AQUAMARINE.png"),
    RUBY("ruby", IngredientsTypes.mineral, 250, 0, "game/crops/RUBY.png"),
    AMETHYST("amethyst", IngredientsTypes.mineral, 100, 0, "game/crops/AMETHYST.png"),
    TOPAZ("topaz", IngredientsTypes.mineral, 80, 0, "game/crops/TOPAZ.png"),
    JADE("jade", IngredientsTypes.mineral, 200, 0, "game/crops/JADE.png"),
    DIAMOND("diamond", IngredientsTypes.mineral, 750, 0, "game/crops/DIAMOND.png"),
    PRISMATIC_SHARD("prismatic shard", IngredientsTypes.mineral, 2000, 0, "game/crops/PRISMATIC_SHARD.png"),
    COPPER("copper", IngredientsTypes.mineral, 5, 0, "game/crops/COPPER.png"),
    IRON("iron", IngredientsTypes.mineral, 10, 0, "game/crops/IRON.png"),
    GOLD("gold", IngredientsTypes.mineral, 25, 0, "game/crops/GOLD.png"),
    IRIDIUM("iridium", IngredientsTypes.mineral, 100, 0, "game/crops/IRIDIUM.png"),
    COAL("coal", IngredientsTypes.mineral, 150, 0, "game/crops/COAL.png"),

    CHERRY_BOMB("cherry bomb", IngredientsTypes.craftedObjects, 50, 0, "game/crops/CHERRY_BOMB.png"),
    BOMB("bomb", IngredientsTypes.craftedObjects, 50, 0, "game/crops/BOMB.png"),
    MEGA_BOMB("mega bomb", IngredientsTypes.craftedObjects, 50, 0, "game/crops/MEGA_BOMB.png"),
    SPRINKLER("sprinkler", IngredientsTypes.craftedObjects, 0, 0, "game/crops/sprinkler.png"),
    QUALITY_SPRINKLER("quality sprinkler", IngredientsTypes.craftedObjects, 0, 0, "game/crops/quality_sprinkler.png"),
    IRIDIUM_SPRINKLER("iridium sprinkler",IngredientsTypes.craftedObjects , 0, 0, "game/crops/iridium_sprinkler.png"),
    CHARCOAL_KLIN("charcoal klin",IngredientsTypes.craftedObjects , 0, 0, "game/crops/charcoal_klin.png"),
    FURNACE("furnace", IngredientsTypes.craftedObjects, 0, 0, "game/crops/furnace.png"),
    SCARECROW("scarecrow", IngredientsTypes.craftedObjects, 0, 0, "game/crops/scarecrow.png"),
    DELUXE_SCARECROW("deluxe scarecrow", IngredientsTypes.craftedObjects, 0, 0, "game/crops/deluxe_scarecrow.png"),
    BEE_HOUSE("bee house",IngredientsTypes.craftedObjects , 0, 0, "game/crops/bee_house.png"),
    CHEESE_PRESS("cheese press", IngredientsTypes.craftedObjects, 0, 0, "game/crops/cheese_press.png"),
    KEG("keg", IngredientsTypes.craftedObjects, 0, 0, "game/crops/keg.png"),
    LOOM("loom",IngredientsTypes.craftedObjects , 0, 0, "game/crops/loom.png"),
    MAYONNAISE_MACHINE("mayonnaise machine", IngredientsTypes.craftedObjects, 0, 0, "game/crops/mayonnaise_machine.png"),
    OIL_MAKER("oil maker", IngredientsTypes.craftedObjects, 0, 0, "game/crops/oil_maker.png"),
    PRESERVES_JAR("preserves jar", IngredientsTypes.craftedObjects, 0, 0, "game/crops/preserves_jar.png"),
    DEHYDRATOR("dehydrator",IngredientsTypes.craftedObjects , 0, 0, "game/crops/dehydrator.png"),
    GRASS_STARTER("grass starter", IngredientsTypes.craftedObjects, 0, 0, "game/crops/grass_starter.png"),
    FISH_SMOKER("fish smoker",IngredientsTypes.craftedObjects , 0, 0, "game/crops/fish_smoker.png"),

    HONEY("honey", IngredientsTypes.food ,350, 75, "game/crops/honey.png"),
    Cheese("cheese", IngredientsTypes.food,280,100, "game/crops/Cheese.png"),
    GoatCheese("goat cheese", IngredientsTypes.food, 500, 100, "game/crops/GoatCheese.png"),
    Beer("beer", IngredientsTypes.food,200, 50, "game/crops/Beer.png"),
    Juice("juice", IngredientsTypes.food,450,100, "game/crops/Juice.png"),
    Maed("maed", IngredientsTypes.food, 300,100, "game/crops/Maed.png"),
    PaleAle("pale apple", IngredientsTypes.food, 300,50, "game/crops/PaleAle.png"),
    Wine("wine", IngredientsTypes.food, 350,150, "game/crops/Wine.png"),
    DriedMushroom("dried mushroom", IngredientsTypes.food, 320, 50, "game/crops/DriedMushroom.png"),
    DriedFruit("dried fruit", IngredientsTypes.food, 750,75, "game/crops/DriedFruit.png"),
    Raisins("raisin", IngredientsTypes.food,600, 125, "game/crops/Raisins.png"),
    Cloth("cloth", IngredientsTypes.junk, 470,0, "game/crops/Cloth.png"),

    Flounder("Flounder", IngredientsTypes.fish, 100, 0, "game/crops/Flounder.png"),
    LionFish("LionFish", IngredientsTypes.fish, 100, 0, "game/crops/LionFish.png"),
    Herring("Herring", IngredientsTypes.fish, 30, 0, "game/crops/Herring.png"),
    GhostFish("Ghost Fish", IngredientsTypes.fish, 45, 0, "game/crops/Ghost_Fish.png"),
    Legend("Legend", IngredientsTypes.fish, 5000, 0, "game/crops/Legend.png"),
    Tilapia("Tilapia", IngredientsTypes.fish, 75, 0, "game/crops/Tilapia.png"),
    Dorado("Dorado", IngredientsTypes.fish, 100, 0, "game/crops/Dorado.png"),
    SunFish("Sunfish", IngredientsTypes.fish, 30, 0, "game/crops/SunFish.png"),
    RainbowTrout("Rainbow Trout", IngredientsTypes.fish, 65, 0, "game/crops/Rainbow_Trout.png"),
    CrimsonFish("Crimsonfish", IngredientsTypes.fish, 1500, 0, "game/crops/CrimsonFish.png"),
    Salmon("Salmon", IngredientsTypes.fish, 75, 0, "game/crops/Salmon.png"),
    Sardine("Sardine", IngredientsTypes.fish, 40, 0, "game/crops/Sardine.png"),
    Shad("Shad", IngredientsTypes.fish, 60, 0, "game/crops/Shad.png"),
    BlueDiscus("Blue Discus", IngredientsTypes.fish, 120, 0, "game/crops/Blue_Discus.png"),
    Angler("Angler", IngredientsTypes.fish, 900, 0, "game/crops/Angler.png"),
    MidnightCarp("Midnight Carp", IngredientsTypes.fish, 150, 0, "game/crops/Midnight_Carp.png"),
    Squid("Squid", IngredientsTypes.fish, 80, 0, "game/crops/Squid.png"),
    Tuna("Tuna", IngredientsTypes.fish, 100, 0, "game/crops/Tuna.png"),
    Perch("Perch", IngredientsTypes.fish, 55, 0, "game/crops/Perch.png"),
    GlacierFish("Glacier Fish", IngredientsTypes.fish, 1000, 0, "game/crops/Glacier_Fish.png"),

    PUMPKIN_PIE("pumpkin pie", IngredientsTypes.food, 385, 225, "game/crops/pumpkin_pie.png"),

    HASH_BROWNS("hash brown",IngredientsTypes.food, 120,90, "game/crops/hash_brown.png"),
    FRIED_EGG("fried egg", IngredientsTypes.food, 35,50, "game/crops/fried_egg.png"),
    BAKED_FISH("baked fish", IngredientsTypes.food,100,85, "game/crops/baked_fish.png"),
    OMELET("omelet", IngredientsTypes.food,125,100, "game/crops/omelet.png"),
    TORTILLA("tortilla", IngredientsTypes.food,50,50, "game/crops/tortilla.png"),
    MAKI_ROLL("maki roll", IngredientsTypes.food,220,100, "game/crops/maki_roll.png"),
    TRIPLE_SHOT_ESPRESSO("tripple shot espresso", IngredientsTypes.food,450,200, "game/crops/tripple_shot_espresso.png"),
    COOKIE("cookie", IngredientsTypes.food,140,90, "game/crops/cookie.png"),
    PANCAKES("panCakes", IngredientsTypes.food,80,90, "game/crops/panCakes.png"),
    FRUIT_SALAD("fruit salad", IngredientsTypes.food,450,263, "game/crops/fruit_salad.png"),
    RED_PLATE("red plate", IngredientsTypes.food, 300,125, "game/crops/red_plate.png"),
    SALMON_DINNER("salmon dinner", IngredientsTypes.food, 300,125, "game/crops/salmon_dinner.png"),
    VEGETABLE_MEDLEY("vegetable medley", IngredientsTypes.food, 120,165, "game/crops/vegetable_medley.png"),
    FARMERS_LUNCH("farmers lunch", IngredientsTypes.food, 150,200, "game/crops/farmers_lunch.png"),
    SURVIVAL_BURGER("survival burger",IngredientsTypes.food,180,125,"game/crops/survival_burger.png"),
    SEA_DISH("sea dish",IngredientsTypes.food,220,150,"game/crops/sea_dish.png"),
    SEAFORM_PUDDING("seaFood pudding", IngredientsTypes.food,300,175,"game/crops/seaFood_pudding.png"),
    MINERS_TREAT("miner's treat",IngredientsTypes.food,200,125,"game/crops/miner's_treat.png"),
    FIBER("fiber", IngredientsTypes.junk, 0, 0,"game/crops/fiber.png"),
    WaterFertility("water fertility",IngredientsTypes.junk, 200,0,"game/crops/WaterFertility.png"),
    SpeedFertility("speed fertility",IngredientsTypes.junk, 200,0, "game/crops/SpeedFertility.png");


    private final String name;
    private final IngredientsTypes type;
    private final int price;
    private final int energy;
    private final String textureName;


    Ingredients(String name, IngredientsTypes type, int price, int energy, String textureName) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.energy = energy;

        this.textureName = textureName;
    }

    public String getTextureName() {
        return textureName;
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
