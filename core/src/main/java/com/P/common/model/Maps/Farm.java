package com.P.common.model.Maps;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Naturals.Tree;
import com.P.common.model.Objects.Shop;
import com.P.common.model.enums.CropName;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.enums.ShopName;
import com.P.common.model.enums.TreeName;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.P.common.model.item.TileDescriptionId;
import dev.morphia.annotations.Embedded;
import com.P.common.model.Objects.ShippingBin;

@Embedded
public class Farm {
    private static TileDescriptionId[][] tiles = new TileDescriptionId[50][75];
    private static TileDescriptionId[][] villageTiles = new TileDescriptionId[50][75];
    private ArrayList<Tile> cells;
    private ArrayList<Building> buildings;
    private ArrayList<ShippingBin> shippingBins;
    private int num;
    private static int lastNum;

    static {
        lastNum = 0;
    }

    public Farm() {

    }

    public Farm(ArrayList<Tile> cells, ArrayList<Building> buildings) {
        this.cells = cells;
        this.buildings = buildings;
        this.shippingBins = new ArrayList<>();
        //tiles = new TileDescriptionId[50][75];

    }

    public void showFarm(int x, int y, int size, Game game) {
        Player owner = game.getCurrentPlayer();
        int ownerX = -1;
        int ownerY = -1;
        int partnerX = -1;
        int partnerY = -1;
        if (owner.getCurrentFarm(game) == this && !owner.isInVillage()) {
            ownerX = owner.getPosition().getX();
            ownerY = owner.getPosition().getY();
        }

        for (Tile cell : cells) {
            Position coordinate = cell.getCoordinate();

            int xOfCell = coordinate.getX();
            int yOfCell = coordinate.getY();

            if (Math.abs(x - xOfCell) <= size / 2 && Math.abs(y - yOfCell) <= size / 2) {
                if (xOfCell == ownerX && yOfCell == ownerY)
                    System.out.print("\u001B[34m " + "O" + "\033[0m");
                else if (xOfCell == partnerX && yOfCell == partnerY)
                    System.out.print("\u001B[34m " + "P" + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("blue"))
                    System.out.print("\u001B[34m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("red"))
                    System.out.print("\u001B[31m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("green"))
                    System.out.print("\u001B[32m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("yellow"))
                    System.out.print("\u001B[33m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("black"))
                    System.out.print("\u001B[90m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("gray"))
                    System.out.print("\u001B[37m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("purple"))
                    System.out.print("\u001B[35m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("cyan"))
                    System.out.print("\u001B[36m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("bright purple"))
                    System.out.print("\u001B[95m " + cell.getObjectOnCell().string() + "\033[0m");
                if (xOfCell - x == size / 2) {
                    System.out.print("\n");
                }
            }
        }
    }

    public void showEntireFarm() {
        int playerX = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getX();
        int playerY = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getY();

        for (int i = 0; i < 152; i++) {
            System.out.print("_");
        }
        System.out.println();

        int cellIndex = 0;

        for (Tile cell : cells) {
            if (cellIndex % 75 == 0)
                System.out.print("|");

            if (cell.getCoordinate().getX() == playerX && cell.getCoordinate().getY() == playerY)
                System.out.print("\u001B[34m " + "P" + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("blue"))
                System.out.print("\u001B[34m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("red"))
                System.out.print("\u001B[31m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("green"))
                System.out.print("\u001B[32m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("yellow"))
                System.out.print("\u001B[33m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("black"))
                System.out.print("\u001B[90m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("gray"))
                System.out.print("\u001B[37m " + cell.getObjectOnCell().string() + "\033[0m");

            cellIndex++;
            if (cellIndex % 75 == 0)
                System.out.println("|");
        }
        for (int i = 0; i < 152; i++) {
            System.out.print("_");
        }
    }

    public ArrayList<Tile> getCells() {
        return cells;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<ShippingBin> getShippingBins() {
        return shippingBins;
    }

    public static Farm makeFarm(int lakeModifier) {
        ArrayList<Tile> farmCells = new ArrayList<>();
        ArrayList<Building> farmBuildings = new ArrayList<>();
        makeEmptyCells(farmCells);
        addBuildings(farmBuildings, farmCells);
        System.out.println(lakeModifier);
        if ((lakeModifier == 1))
            addOneLake(farmCells);
        else if ((lakeModifier == 2))
            addTwoLakes(farmCells);

        for (Tile cell : farmCells) {
            int randomNumber = (int) (Math.random() * 8);
            int randomNumber2 = (int) (Math.random() * 3) + 1;


            if (cell.getObjectOnCell().type.equals(".") && randomNumber == 3) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                cell.setObjectOnCell(new Plant(TreeName.AppleTree));
                tiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.TREE1;
                if (randomNumber2 == 2) {
                    tiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.TREE2;
                }
            } else if (cell.getObjectOnCell().type.equals(".") && randomNumber == 2) {
                cell.setObjectOnCell(new Stone(Ingredients.STONE, "gray", "stone"));
                tiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.STONE;
            } else if (cell.getObjectOnCell().type.equals(".") && randomNumber == 1) {
                cell.setObjectOnCell(randomForagingCrop());
                tiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.F;
            } else if (cell.getObjectOnCell().type.equals(".") && randomNumber == 4) {
                cell.setObjectOnCell(randomForagingCrop());
                tiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.WOOD;
            } else if (cell.getObjectOnCell().type.equals("Mine") && (randomNumber == 4 || randomNumber == 3) && isMineCell(cell)) {
                cell.setObjectOnCell(randomForagingMineral());
            }
        }


        return new Farm(farmCells, farmBuildings);
    }

    private static WildSeeds randomForagingCrop() {
        CropName[] allValues = CropName.values();
        ArrayList<CropName> validValues = new ArrayList<>();

        for (int i = 0; i < allValues.length; i++) {
            if (allValues[i].getSeason().length > 1 || allValues[i].getSeason()[0] == App.getLoggedInUser().getCurrentGame().getSeason()) {
                validValues.add(allValues[i]);
            }
        }

        int randomNumber = (int) (Math.random() * validValues.size());
        return new WildSeeds(validValues.get(randomNumber));
    }

    private static FodderCrop randomForagingMineral() {
        Ingredients[] values = Ingredients.values();
        int randomNumber = (int) (Math.random() * values.length);
        return new FodderCrop();
    }


    private static void addOneLake(ArrayList<Tile> farmCells) {
        for (int j = 10; j < 14; j++) {
            for (int i = 10; i < 14; i++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new Water());
                tiles[j][i] = TileDescriptionId.WATER;
            }
        }
    }

    private static void addTwoLakes(ArrayList<Tile> farmCells) {
        for (int j = 11; j < 16; j++) {
            for (int i = 10; i < 16; i++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new Water());
                tiles[j][i] = TileDescriptionId.WATER;
            }
        }
        for (int j = 25; j < 30; j++) {
            for (int i = 5; i < 9; i++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new Water());
                tiles[j][i] = TileDescriptionId.WATER;
            }
        }
    }


    private static void makeEmptyCells(ArrayList<Tile> farmCells) {
        for (int j = 0; j < tiles.length; j++) {
            for (int i = 0; i < tiles[j].length; i++) {
                Position coordinate = new Position(i, j);
                farmCells.add(new Tile(new NothingInTile(), coordinate));
                tiles[j][i] = TileDescriptionId.GRASS;
            }
        }
    }

    private static void makeEmptyCellsForVillage(ArrayList<Tile> farmCells) {
        for (int j = 0; j < villageTiles.length; j++) {
            for (int i = 0; i < villageTiles[j].length; i++) {
                Position coordinate = new Position(i, j);
                farmCells.add(new Tile(new NothingInTile(), coordinate));
                villageTiles[j][i] = TileDescriptionId.floor;
            }
        }
    }

    private static boolean isMineCell(Tile cell) {
        return cell.getCoordinate().getX() <= 9 && cell.getCoordinate().getX() >= 0 && cell.getCoordinate().getY() <= 11
            && cell.getCoordinate().getY() >= 0;
    }

    private static void addBuildings(ArrayList<Building> farmBuildings, ArrayList<Tile> farmCells) {
        ArrayList<Tile> cottageCells = new ArrayList<>();
        ArrayList<Tile> greenHouseCells = new ArrayList<>();
        ArrayList<Tile> mineCells = new ArrayList<>();
        for (int i = 3; i < 10; i++) {
            for (int j = 3; j < 8; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(false, "Home"));
                cottageCells.add(cell);
                tiles[j][i] = TileDescriptionId.GRASS;// Chang It
            }
        }

        for (int i = 22; i < 26; i++) {
            for (int j = 3; j < 7; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);

                if (i != 22 && i != 28 && j != 3 && j != 10) {
                    cell.setObjectOnCell(new BuildingsForPaint(false, "Greenhouse"));
                } else
                    cell.setObjectOnCell(new BuildingsForPaint(false, "Wall"));

                greenHouseCells.add(cell);
                // tiles[j][i] = TileDescriptionId.SLOT;// Chang It
            }
        }

        tiles[3][25] = TileDescriptionId.GREENHOUSE1;
        tiles[4][25] = TileDescriptionId.GREENHOUSE2;
        tiles[5][25] = TileDescriptionId.GREENHOUSE3;
        tiles[6][25] = TileDescriptionId.GREENHOUSE4;

        tiles[3][24] = TileDescriptionId.GREENHOUSE5;
        tiles[4][24] = TileDescriptionId.GREENHOUSE6;
        tiles[5][24] = TileDescriptionId.GREENHOUSE7;
        tiles[6][24] = TileDescriptionId.GREENHOUSE8;

        tiles[3][23] = TileDescriptionId.GREENHOUSE9;
        tiles[4][23] = TileDescriptionId.GREENHOUSE10;
        tiles[5][23] = TileDescriptionId.GREENHOUSE11;
        tiles[6][23] = TileDescriptionId.GREENHOUSE12;

        tiles[3][22] = TileDescriptionId.GREENHOUSE13;
        tiles[4][22] = TileDescriptionId.GREENHOUSE14;
        tiles[5][22] = TileDescriptionId.GREENHOUSE15;
        tiles[6][22] = TileDescriptionId.GREENHOUSE16;

        for (int i = 22; i < 26; i++) {
            for (int j = 10; j < 17; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "Mine"));
                mineCells.add(cell);
                tiles[j][i] = TileDescriptionId.MINE;// Chang It
            }
        }
        //tiles[3][3] = TileDescriptionId.SLOT;
        farmBuildings.add(new Cottage(cottageCells));
        farmBuildings.add(new GreenHouse(greenHouseCells));
        farmBuildings.add(new Mine(mineCells));
    }


    public Tile findCellByCoordinate(int x, int y) {
        for (Tile cell : cells) {
            if (cell.getCoordinate().getX() == x && cell.getCoordinate().getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public static Tile getCellByCoordinate(int x, int y, ArrayList<Tile> cells) {
        for (Tile cell : cells) {
            if (cell.getCoordinate().getX() == x && cell.getCoordinate().getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public void initialCells() {
        for (Tile cell : cells) {
            cell.energy = 0;
            cell.distance = 0;
            cell.turns = 0;
            cell.prev = null;
        }
    }

    //New Jasmin

    public void setCells(ArrayList<Tile> cells) {
        this.cells = cells;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static int getLastNum() {
        return lastNum;
    }

    public static void setLastNum(int lastNum) {
        Farm.lastNum = lastNum;
    }


    //New Jasmin
    public void strikeLightning(int targetX, int targetY, LocalDateTime source) {
        Tile targetCell = findCellByCoordinate(targetX, targetY);
        if (targetCell != null) {
            if (targetCell.getObjectOnCell() instanceof Plant) {
                //TreeName burntTree = TreeName.BurntTree;

                //targetCell.setObject(new Tree(burntTree));
            }
            if (targetCell.getObjectOnCell() instanceof FodderCrop) {
                targetCell.setObjectOnCell(new NothingInTile());
            }
            if (targetCell.getObjectOnCell() instanceof FodderCrop) {
                targetCell.setObjectOnCell(new NothingInTile());
            }
        }
        System.out.println("Lightning has struck coordinates: " + targetX + ", " + targetY);
    }


    private static void addShop(ArrayList<Building> farmBuildings, ArrayList<Tile> farmCells) {
        ArrayList<Tile> blacksmithFloor = new ArrayList<>();
        ArrayList<Tile> marnieRanchFloor = new ArrayList<>();
        ArrayList<Tile> stardropSaloonFloor = new ArrayList<>();
        ArrayList<Tile> carpenterShopFloor = new ArrayList<>();
        ArrayList<Tile> jojaMartFloor = new ArrayList<>();
        ArrayList<Tile> pierreGeneralFloor = new ArrayList<>();
        ArrayList<Tile> fishShopFloor = new ArrayList<>();

        for (int i = 3; i < 9; i++) {
            for (int j = 3; j < 8; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "blackSmith"));
                blacksmithFloor.add(cell);
            }
        }

        for (int i = 9; i < 15; i++) {
            for (int j = 9; j < 14; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "marnieRanch"));
                marnieRanchFloor.add(cell);
            }
        }

        for (int i = 16; i < 20; i++) {
            for (int j = 16; j < 21; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "stardropSaloon"));
                stardropSaloonFloor.add(cell);
            }
        }

        for (int i = 25; i < 31; i++) {
            for (int j = 25; j < 32; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "carpenterShop"));
                carpenterShopFloor.add(cell);
            }
        }

        for (int i = 35; i < 41; i++) {
            for (int j = 35; j < 38; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "jojaMart"));
                jojaMartFloor.add(cell);
            }
        }

        for (int i = 45; i < 51; i++) {
            for (int j = 35; j < 38; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "pierreGeneralStore"));
                pierreGeneralFloor.add(cell);
            }
        }

        for (int i = 45; i < 50; i++) {
            for (int j = 43; j < 48; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "fishShop"));
                fishShopFloor.add(cell);
            }
        }
        Shop blacksmith = new Shop(blacksmithFloor, ShopName.BlackSmith, 9, 4);
        Shop marnieRanch = new Shop(marnieRanchFloor, ShopName.MarnieRanch, 9, 16);
        Shop stardropSaloon = new Shop(stardropSaloonFloor, ShopName.StardropSaloon, 12, 13);
        Shop carpenterShop = new Shop(carpenterShopFloor, ShopName.CarpenterShop, 9, 20);
        Shop jojaMart = new Shop(jojaMartFloor, ShopName.JojaMart, 9, 23);
        Shop pierreGeneral = new Shop(pierreGeneralFloor, ShopName.PierreGeneralStore, 9, 17);
        Shop fishShop = new Shop(fishShopFloor, ShopName.FishShop, 9, 17);
        blacksmith.ChangeSeasonSpring();
        marnieRanch.ChangeSeasonSpring();
        stardropSaloon.ChangeSeasonSpring();
        carpenterShop.ChangeSeasonSpring();
        jojaMart.ChangeSeasonSpring();
        pierreGeneral.ChangeSeasonSpring();
        fishShop.ChangeSeasonSpring();
        farmBuildings.add(blacksmith);
        farmBuildings.add(marnieRanch);
        farmBuildings.add(stardropSaloon);
        farmBuildings.add(carpenterShop);
        farmBuildings.add(jojaMart);
        farmBuildings.add(pierreGeneral);
        farmBuildings.add(fishShop);
    }

    public static Farm makeNPCFarm() {
        ArrayList<Tile> farmCells = new ArrayList<>();
        ArrayList<Building> farmBuildings = new ArrayList<>();

        makeEmptyCellsForVillage(farmCells);
        addShop(farmBuildings, farmCells);
        for (Tile cell : farmCells) {
            int randomNumber = (int) (Math.random() * 8);
            int randomNumber2 = (int) (Math.random() * 3) + 1;


            if (cell.getObjectOnCell().type.equals(".") && randomNumber == 3) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                cell.setObjectOnCell(new Plant(TreeName.AppleTree));
                villageTiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.tree1;
                if (randomNumber2 == 2) {
                    villageTiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.tree2;
                }
            } else if (cell.getObjectOnCell().type.equals(".") && randomNumber == 2) {
                cell.setObjectOnCell(new Stone(Ingredients.STONE, "gray", "stone"));
                villageTiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.stone;
            }
        }
        return new Farm(farmCells, farmBuildings);

    }

    public TileDescriptionId getTile(Point point) {
        return tiles[point.x][point.y];
    }

    public TileDescriptionId[][] getTiles() {
        return tiles;
    }

    public TileDescriptionId[][] getVillageTiles() {
        return villageTiles;
    }
}
