package com.P.controller;

import com.P.model.Basics.App;
import com.P.model.Basics.Player;
import com.P.model.Basics.Result;
import com.P.model.Command;
import com.P.model.Maps.Position;
import com.P.model.Maps.Tile;
import com.P.model.Objects.CraftingMachine;
import com.P.model.Objects.Inventory;
import com.P.model.Resualt;
import com.P.model.enums.Ingredients;
import com.P.model.enums.IngredientsTypes;
import org.h2.engine.Right;

import java.rmi.server.RemoteRef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CraftsmanshipController extends ControllersController {
    public static Resualt useCraftingMachine(Command command) {
        String machineName = command.body.get("artisanName");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Position position=InventoryFunctionsController.findPositionByDirection("Right", player.getPosition());
        Tile tile=InventoryFunctionsController.findTileByPosition(position, player.getFarm());
        CraftingMachine machine = tile.getMachine();
        /*for (Tile tile : player.getFarm().getCells()) {
            if (tile.getMachine()!=null && tile.getMachine().getType().getName().equals(machineName) ) {
                int a = calculateDistance(player.getPosition(), tile.getCoordinate());
                if (a > 1) {
                    return new Resualt(false, "not next to the machine.");
                }
                machine = tile.getMachine();
                break;
            }
        }*/
        if (machine == null) return new Resualt(false, "You don't have this machine");
        switch (machine.getType()) {
            case BEE_HOUSE : {
                return useBeeHouse(command, machine);
            }
            case CHEESE_PRESS : {
                return useCheesePress(command, machine);
            }
            case KEG : {
                return useKeg(command, machine);
            }
            case DEHYDRATOR : {
                return useDehydrator(command, machine);
            }
            case CHARCOAL_KLIN : {
                return useKlin(command, machine);
            }
            case LOOM : {
                return useLoom(command, machine);
            }
            case MAYONNAISE_MACHINE : {
                return useMayonnaise(command, machine);
            }
            case OIL_MAKER : {
                return useOilMaker(command, machine);
            }
            case PRESERVES_JAR : {
                return usePreserver(command, machine);
            }
            case FISH_SMOKER : {
                return useFishSmoker(command, machine);
            }
            case FURNACE : {
                return useFurnace(command, machine);
            }
        }
        return new Resualt(false, "This object can not be used!");
    }

    public static Resualt getCraftingMachineProduct(Command command) {
        String machineName = command.body.get("artisanName");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Position position=InventoryFunctionsController.findPositionByDirection("Right", player.getPosition());
        Tile tile=InventoryFunctionsController.findTileByPosition(position, player.getFarm());
        CraftingMachine machine = tile.getMachine();
        /*for (Tile tile : player.getFarm().getCells()) {
            if (tile.getMachine().getType().getName().equals(machineName)) {
                int a = calculateDistance(player.getPosition(), tile.getCoordinate());
                if (a > 1) {
                    return new Resualt(false, "not next to the machine.");
                }
                machine = tile.getMachine();
                break;
            }
        }*/
        if (machine == null) return new Resualt(false, "You don't have this machine");
        if (!machine.isWorking()) return new Resualt(false, "machine wasn't working");
        switch (machine.getType()) {
            case BEE_HOUSE : {
                return getBeeHouse(machine);
            }
            case CHEESE_PRESS : {
                return getCheesePress(machine);
            }
            case KEG : {
                return getKeg(machine);
            }
            case DEHYDRATOR : {
                return getDehydrator(machine);
            }
            case CHARCOAL_KLIN : {
                return getKlin(machine);
            }
            case LOOM : {
                return getLoom(machine);
            }
            case MAYONNAISE_MACHINE : {
                return getMayonnaise(machine);
            }
            case OIL_MAKER : {
                return getOilMaker(machine);
            }
            case PRESERVES_JAR : {
                return getPreserver(machine);
            }
            case FISH_SMOKER : {
                return getFishSmoker(machine);
            }
            case FURNACE : {
                return getFurnace(machine);
            }
        }
        return new Resualt(false, "This object can not be used!");
    }

    public static Resualt useBeeHouse(Command command, CraftingMachine machine) {
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size() > 1) return new Resualt(false, "Wrong input.");
        machine.setWorking(true);
        machine.setProduct(Ingredients.HONEY);
        return new Resualt(true, "Started making Honey");

    }

    public static Resualt getBeeHouse(CraftingMachine machine) {
        System.out.println(machine.getProcessingHourTime());
        if (machine.getProcessingHourTime() < 54) return new Resualt(false, "Product not ready.");
        machine.setWorking(false);
        machine.setProcessingHourTime(0);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Ingredients ingredients = machine.getProduct();
        if (inventory.getIngredients().containsKey(ingredients)) {
            inventory.getIngredients().put(ingredients, inventory.getIngredients().get(ingredients) + 1);
        } else {
            inventory.getIngredients().put(ingredients, 2);
        }
        return new Resualt(true, "Honey added to inventory");
    }

    public static Resualt getCheesePress(CraftingMachine machine) {
        System.out.println(machine.getProcessingHourTime());
        if (machine.getProcessingHourTime() < 4) return new Resualt(false, "Product not ready.");
        machine.setWorking(false);
        machine.setProcessingHourTime(0);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Ingredients ingredients = machine.getProduct();
        if (inventory.getIngredients().containsKey(ingredients)) {
            inventory.getIngredients().put(ingredients, inventory.getIngredients().get(ingredients) + 1);
        } else {
            inventory.getIngredients().put(ingredients, 2);
        }
        return new Resualt(true, "Cheese added to inventory");
    }

    public static Resualt getKeg(CraftingMachine machine) {
        if (machine.getProcessingHourTime() < 14) return new Resualt(false, "Product not ready.");
        machine.setWorking(false);
        machine.setProcessingHourTime(0);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Ingredients ingredients = machine.getProduct();
        if (inventory.getIngredients().containsKey(ingredients)) {
            inventory.getIngredients().put(ingredients, inventory.getIngredients().get(ingredients) + 1);
        } else {
            inventory.getIngredients().put(ingredients, 2);
        }
        return new Resualt(true, "Drink added to inventory");
    }

    public static Resualt getDehydrator(CraftingMachine machine) {
        if (machine.getProcessingHourTime() < 14) return new Resualt(false, "Product not ready.");
        machine.setWorking(false);
        machine.setProcessingHourTime(0);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Ingredients ingredients = machine.getProduct();
        if (inventory.getIngredients().containsKey(ingredients)) {
            inventory.getIngredients().put(ingredients, inventory.getIngredients().get(ingredients) + 1);
        } else {
            inventory.getIngredients().put(ingredients, 2);
        }
        return new Resualt(true, "Dried fruit added to inventory");
    }

    public static Resualt getKlin(CraftingMachine machine) {
        if (machine.getProcessingHourTime() < 3) return new Resualt(false, "Product not ready.");
        machine.setWorking(false);
        machine.setProcessingHourTime(0);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Ingredients ingredients = machine.getProduct();
        if (inventory.getIngredients().containsKey(ingredients)) {
            inventory.getIngredients().put(ingredients, inventory.getIngredients().get(ingredients) + 1);
        } else {
            inventory.getIngredients().put(ingredients, 2);
        }
        return new Resualt(true, "Coal added to inventory");
    }

    public static Resualt getLoom(CraftingMachine machine) {
        if (machine.getProcessingHourTime() < 6) return new Resualt(false, "Product not ready.");
        machine.setWorking(false);
        machine.setProcessingHourTime(0);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Ingredients ingredients = machine.getProduct();
        if (inventory.getIngredients().containsKey(ingredients)) {
            inventory.getIngredients().put(ingredients, inventory.getIngredients().get(ingredients) + 1);
        } else {
            inventory.getIngredients().put(ingredients, 2);
        }
        return new Resualt(true, "Cloth added to inventory");
    }

    public static Resualt getMayonnaise(CraftingMachine machine) {
        if (machine.getProcessingHourTime() < 5) return new Resualt(false, "Product not ready.");
        machine.setWorking(false);
        machine.setProcessingHourTime(0);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Ingredients ingredients = machine.getProduct();
        if (inventory.getIngredients().containsKey(ingredients)) {
            inventory.getIngredients().put(ingredients, inventory.getIngredients().get(ingredients) + 1);
        } else {
            inventory.getIngredients().put(ingredients, 2);
        }
        return new Resualt(true, "Mayonnaise added to inventory");
    }

    public static int calculateDistance(Position start, Position end) {
        return (Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY()));
    }

    public static Resualt useCheesePress(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        for (String s : list) {
            if (!(s.equals("Milk") || s.equals("GoatMilk"))) return new Resualt(false, "Wrong input.");
        }
        if (list.getFirst().equals("Milk") && inventory.getIngredients().get(Ingredients.MILK) > 1) {
            if (inventory.getIngredients().get(Ingredients.MILK) == 1) {
                inventory.getIngredients().remove(Ingredients.MILK);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.MILK, inventory.getIngredients().get(Ingredients.MILK) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Cheese);
        } else if (list.getFirst().equals("Milk")) return new Resualt(false, "not enough ingredients");

        if (list.getFirst().equals("GoatMilk") && inventory.getIngredients().get(Ingredients.GOAT_MILK) > 1) {
            if (inventory.getIngredients().get(Ingredients.GOAT_MILK) == 1) {
                inventory.getIngredients().remove(Ingredients.GOAT_MILK);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.GOAT_MILK, inventory.getIngredients().get(Ingredients.GOAT_MILK) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.GoatCheese);
        } else if (list.getFirst().equals("GoatMilk")) return new Resualt(false, "not enough ingredients");
        return new Resualt(true, "Started making Cheese");
    }

    public static Resualt useKeg(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size() > 2) return new Resualt(false, "Wrong input.");
        Ingredients ingredient = null;
        for (Ingredients ingredients1 : Ingredients.values()) {
            if (ingredients1.getName().equals(list.getFirst())) ingredient = ingredients1;
        }
        if (ingredient.equals(Ingredients.WHEAT) && inventory.getIngredients().get(ingredient) >= 1) {
            if (inventory.getIngredients().get(Ingredients.WHEAT) == 1) {
                inventory.getIngredients().remove(Ingredients.WHEAT);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.WHEAT, inventory.getIngredients().get(Ingredients.WHEAT) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Beer);
            return new Resualt(true, "Started making.");
        } else if (ingredient.equals(Ingredients.RICE) && inventory.getIngredients().get(ingredient) >= 1) {
            if (inventory.getIngredients().get(Ingredients.RICE) == 1) {
                inventory.getIngredients().remove(Ingredients.RICE);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.RICE, inventory.getIngredients().get(Ingredients.RICE) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.VINEGAR);
            return new Resualt(true, "Started making.");
        } else if (ingredient.equals(Ingredients.COFFEE_BEAN) && inventory.getIngredients().get(ingredient) >= 5) {
            if (inventory.getIngredients().get(Ingredients.COFFEE_BEAN) == 5) {
                inventory.getIngredients().remove(Ingredients.COFFEE_BEAN);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.COFFEE_BEAN, inventory.getIngredients().get(Ingredients.COFFEE_BEAN) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.COFFEE);
            return new Resualt(true, "Started making.");
        } else if (ingredient.getType().equals(IngredientsTypes.vegetable) && inventory.getIngredients().get(ingredient) >= 1) {
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Juice);
            return new Resualt(true, "Started making.");
        } else if (ingredient.equals(Ingredients.HONEY) && inventory.getIngredients().get(ingredient) >= 1) {
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Maed);
            return new Resualt(true, "Started making.");
        } else if (ingredient.equals(Ingredients.HOPS) && inventory.getIngredients().get(ingredient) >= 1) {
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.WHEAT, inventory.getIngredients().get(Ingredients.WHEAT) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.PaleAle);
            return new Resualt(true, "Started making.");
        } else if (ingredient.getType().equals(IngredientsTypes.fruit) && inventory.getIngredients().get(ingredient) >= 1) {
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Wine);
            return new Resualt(true, "Started making.");
        }
        return new Resualt(false, "Not enough ingredients.");
    }

    public static Resualt useDehydrator(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size() > 2) return new Resualt(false, "Wrong input.");
        Ingredients ingredient = null;
        for (Ingredients ingredients1 : Ingredients.values()) {
            if (ingredients1.getName().equals(list.getFirst())) ingredient = ingredients1;
        }
        if ((ingredient.equals(Ingredients.COMMON_MUSHROOM) || ingredient.equals(Ingredients.RED_MUSHROOM) || ingredient.equals(Ingredients.PURPLE_MUSHROOM)) && inventory.getIngredients().get(ingredient) >= 5) {
            if (inventory.getIngredients().get(ingredient) == 5) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.DriedMushroom);
            return new Resualt(true, "Started making.");
        } else if ((ingredient.getType().equals(IngredientsTypes.fruit)) && inventory.getIngredients().get(ingredient) >= 5) {
            if (inventory.getIngredients().get(ingredient) == 5) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.DriedFruit);
            return new Resualt(true, "Started making.");
        } else if ((ingredient.equals(Ingredients.GRAPE)) && inventory.getIngredients().get(ingredient) >= 5) {
            if (inventory.getIngredients().get(ingredient) == 5) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Raisins);
            return new Resualt(true, "Started making.");
        }
        return new Resualt(false, "Not enough ingredients");
    }

    public static Resualt useKlin(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size() > 2) return new Resualt(false, "Wrong input.");
        Ingredients ingredient = null;
        for (Ingredients ingredients1 : Ingredients.values()) {
            if (ingredients1.getName().equals(list.getFirst())) ingredient = ingredients1;
        }
        if ((ingredient.equals(Ingredients.WOOD)) && inventory.getIngredients().get(ingredient) >= 10) {
            if (inventory.getIngredients().get(ingredient) == 10) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 10);
            machine.setWorking(true);
            machine.setProduct(Ingredients.COAL);
            return new Resualt(true, "Started making.");
        }
        return new Resualt(false, "not enough ingredients");
    }

    public static Resualt useLoom(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size() > 2) return new Resualt(false, "Wrong input.");
        Ingredients ingredient = null;
        for (Ingredients ingredients1 : Ingredients.values()) {
            if (ingredients1.getName().equals(list.getFirst())) ingredient = ingredients1;
        }
        if ((ingredient.equals(Ingredients.WOOL)) && inventory.getIngredients().get(ingredient) >= 2) {
            if (inventory.getIngredients().get(ingredient) == 2) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 2);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Cloth);
            return new Resualt(true, "Started making.");
        }
        return new Resualt(false, "not enough ingredients");
    }

    public static Resualt useMayonnaise(Command command, CraftingMachine machine) {
        return null;
    }

    public static Resualt useOilMaker(Command command, CraftingMachine machine) {
        return null;
    }

    public static Resualt usePreserver(Command command, CraftingMachine machine) {
        return null;
    }

    public static Resualt useFishSmoker(Command command, CraftingMachine machine) {
        return null;
    }

    public static Resualt useFurnace(Command command, CraftingMachine machine) {
        return null;
    }

    public static Resualt getOilMaker(CraftingMachine machine) {
        return null;
    }

    public static Resualt getPreserver(CraftingMachine machine) {
        return null;
    }

    public static Resualt getFishSmoker(CraftingMachine machine) {
        return null;
    }

    public static Resualt getFurnace(CraftingMachine machine) {
        return null;
    }


}
