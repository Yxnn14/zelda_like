package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.inventory.Inventory;
import fr.yann.zelda_like.api.inventory.Item;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractInventory implements Inventory {

    protected final Item[] items;
    private final Color backgroundColor;
    private final Color slotColor;

    protected AbstractInventory(int size) {
        this(size, Color.color(0, 0, 0, .4), Color.color(0.6, 0.6, 0.6));
    }

    protected AbstractInventory(int size, Color backgroundColor, Color slotColor) {
        this.items = new Item[size];
        this.backgroundColor = backgroundColor;
        this.slotColor = slotColor;
    }

    @Override
    public List<Item> getItems() {
        final List<Item> list = new ArrayList<>();
        for (Item item : this.items) {
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public void addItem(Item item, int slot) {
        this.items[slot] = item;
    }

    @Override
    public boolean addItem(Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                this.addItem(item, i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeItem(int slot) {
        this.items[slot] = null;
    }

    @Override
    public boolean removeItem(Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if (item.equals(this.items[i])) {
                this.removeItem(i);
                return true;
            }
        }
        return false;
    }


    @Override
    public Item getItemAt(int slot) {
        return this.items[slot];
    }

    @Override
    public int getSlotOf(Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if (item.equals(this.items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        return this.items.length;
    }

    @Override
    public void clear() {
        Arrays.fill(this.items, null);
    }

    @Override
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override
    public Color getSlotColor() {
        return this.slotColor;
    }

    @Override
    public Image getBackgroundTexture() {
        return null;
    }

    @Override
    public boolean contains(Class<? extends Item> itemClass) {
        for (final Item item : this.items) {
            if (item != null && item.getClass().equals(itemClass)) {
                return true;
            }
        }
        return false;
    }
}
