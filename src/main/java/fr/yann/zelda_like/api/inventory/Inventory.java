package fr.yann.zelda_like.api.inventory;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public interface Inventory {

    List<Item> getItems();

    void addItem(Item item, int slot);

    boolean addItem(Item item);

    void removeItem(int slot);

    boolean removeItem(Item item);

    Item getItemAt(int slot);

    int getSlotOf(Item item);

    int getSize();

    void clear();

    Color getBackgroundColor();

    Image getBackgroundTexture();

    Color getSlotColor();
}
