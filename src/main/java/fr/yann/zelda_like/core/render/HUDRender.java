package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Cursor;
import fr.yann.zelda_like.api.dialog.Dialog;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Inventory;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import fr.yann.zelda_like.core.inventory.MoneyItem;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HUDRender implements Render {

    private static final Image HEALTH_TEXTURE = new Image("assets/textures/health.png");

    private final ZeldaLike zeldaLike;
    private final LevelRender levelRender;
    private Group group;

    public HUDRender(ZeldaLike zeldaLike, LevelRender levelRender)
    {
        this.zeldaLike = zeldaLike;
        this.levelRender = levelRender;
    }

    @Override
    public void render() {
        if (this.group != null) {
            this.levelRender.getGroup().getChildren().remove(this.group);
        }

        final Level level = this.zeldaLike.getLevelManager().get();
        if (level == null) {
            return;
        }

        this.group = new Group();

        final PlayerEntity player = level.getPlayer();

        if (level.isHUDShow()) {
            this.drawPlayerInformation(player);
            this.drawInventory(player.getInventory());
        }

        if (level.isDebugShow()) {
            this.drawInformations();
        }

        this.drawInventoryView(player);
        this.drawDialog(level.getDialogManager().get());

        this.levelRender.getGroup().getChildren().add(3, this.group);
    }

    private boolean intersect(Cursor cursor, double x, double y, double width, double height, boolean drag) {
        return x <= (drag ? cursor.getDragX() : cursor.getX())
            && (x + width) >= (drag ? cursor.getDragX() : cursor.getX())
            && y <= (drag ? cursor.getDragY() : cursor.getY())
            && (y + height) >= (drag ? cursor.getDragY() : cursor.getY());
    }

    private void drawInventoryView(PlayerEntity player) {
        if (player.getInventoryView() != null) {
            final Group inventoryViewGroup = new Group();
            final Image inventoryTexture = player.getInventoryView().getBackgroundTexture();
            final double inventoryWidth = ZeldaLikeApplication.WIDTH / 2.0;
            final double slotSize = inventoryWidth / 10.0;
            final double inventoryHeight = slotSize * Math.ceil(player.getInventoryView().getSize() / 10.0);
            final double x = ZeldaLikeApplication.WIDTH / 4.0;
            final double y = (ZeldaLikeApplication.HEIGHT - inventoryHeight) / 2.0;

            if (inventoryTexture != null) {
                final ImageView imageView = new ImageView(inventoryTexture);
                imageView.setFitWidth(inventoryWidth);
                imageView.setFitHeight(inventoryHeight);
                imageView.setX(x);
                imageView.setY(y);
                inventoryViewGroup.getChildren().add(0, imageView);
            } else {
                final Rectangle rectangle = new Rectangle();
                rectangle.setWidth(inventoryWidth);
                rectangle.setHeight(inventoryHeight);
                rectangle.setX(x);
                rectangle.setY(y);
                rectangle.setFill(Color.color(0, 0, 0, .4));

                inventoryViewGroup.getChildren().add(0, rectangle);
            }

            final Group slotsGroup = new Group();
            Node itemViewDragged = null;
            Item itemCursor = player.getCursorItem();

            for (int i = 0; i < player.getInventoryView().getSize(); i++) {
                final Group slotGroup = new Group();
                final Rectangle slot = new Rectangle();
                slot.setWidth(slotSize * 0.75);
                slot.setHeight(slotSize * 0.75);
                slot.setX(x + ((slotSize * (i % 10.0)) + (slotSize * 0.125)));
                slot.setY(y + ((slotSize * (Math.ceil((i + 1) / 10.0) - 1)) + (slotSize * 0.125)));

                final Cursor cursor = zeldaLike.getControllerManager().getCursor();
                final boolean intersect = this.intersect(
                    cursor, slot.getX(), slot.getY(), slot.getWidth(), slot.getHeight(), false
                );
                final Color slotColor = player.getInventoryView().getSlotColor();
                if (intersect) {
                    slot.setFill(Color.color(slotColor.getRed(), slotColor.getGreen(), slotColor.getBlue(), 0.8));
                } else {
                    slot.setFill(slotColor);
                }

                slotGroup.getChildren().add(0, slot);

                final Item item = player.getInventoryView().getItemAt(i);

                if (
                    itemCursor != null
                        && !cursor.isDragged()
                        && !itemCursor.equals(item)
                        && this.intersect(cursor, slot.getX(), slot.getY(), slot.getWidth(), slot.getHeight(), true)
                ) {
                    int lastSlot = player.getInventoryView().getSlotOf(itemCursor);
                    if (lastSlot > -1) {
                        player.getInventoryView().removeItem(lastSlot);
                        player.getInventoryView().addItem(item, lastSlot);
                    }
                    player.getInventoryView().addItem(itemCursor, i);
                    if (item != null && lastSlot < 0) {
                        player.getInventoryView().addItem(item);
                    }
                }

                if (item != null) {
                    final Image itemImage = item.getTexture();
                    final Node itemCurrentView;
                    if (itemImage != null) {
                        final ImageView image = new ImageView(itemImage);
                        image.setFitWidth(slot.getWidth() * 0.75);
                        image.setFitHeight(slot.getHeight() * 0.75);
                        if (cursor.isDragged() && intersect) {
                            image.setX(cursor.getDragX() - (slot.getWidth() / 2.0));
                            image.setY(cursor.getDragY() - (slot.getHeight() / 2.0));
                        } else {
                            image.setX(slot.getX() + (slot.getWidth() * 0.125));
                            image.setY(slot.getY() + (slot.getHeight() * 0.125));
                        }
                        itemCurrentView = image;
                    } else {
                        final Rectangle itemView = new Rectangle();
                        itemView.setWidth(slot.getWidth() * 0.75);
                        itemView.setHeight(slot.getHeight() * 0.75);
                        if (cursor.isDragged() && intersect) {
                            itemView.setX(cursor.getDragX() - (slot.getWidth() / 2.0));
                            itemView.setY(cursor.getDragY() - (slot.getHeight() / 2.0));
                        } else {
                            itemView.setX(slot.getX() + (slot.getWidth() * 0.125));
                            itemView.setY(slot.getY() + (slot.getHeight() * 0.125));
                        }
                        itemView.setFill(item.getColor());
                        itemCurrentView = itemView;
                    }

                    if (cursor.isDragged() && intersect) {
                        itemViewDragged = itemCurrentView;
                        itemCursor = item;
                    } else {
                        slotGroup.getChildren().add(1, itemCurrentView);
                    }
                }

                slotsGroup.getChildren().add(slotGroup);
            }

            inventoryViewGroup.getChildren().add(1, slotsGroup);

            if (itemViewDragged != null) {
                player.setCursorItem(itemCursor);
                inventoryViewGroup.getChildren().add(2, itemViewDragged);
            } else if (player.getCursorItem() != null) {
                player.setCursorItem(null);
            }

            this.group.getChildren().add(inventoryViewGroup);
        } else if (player.getCursorItem() != null) {
            player.setCursorItem(null);
        }
    }

    private void drawInventory(Inventory inventory) {
        final Group inventoryGroup = new Group();

        final double fullSize = ZeldaLikeApplication.WIDTH * 0.04;
        final double slotSize = fullSize * 0.85;
        final double slotX = ZeldaLikeApplication.WIDTH - fullSize;

        final Rectangle inventoryBackground = new Rectangle();
        inventoryBackground.setX(slotX);
        inventoryBackground.setY(0);
        inventoryBackground.setWidth(fullSize);
        inventoryBackground.setHeight(fullSize * Math.min(inventory.getSize(), 10));
        inventoryBackground.setFill(inventory.getBackgroundColor());

        inventoryGroup.getChildren().add(0, inventoryBackground);

        final Group inventorySlot = new Group();

        for (int i = 0; i < Math.min(inventory.getSize(), 10); i++) {
            final Group slotGroup = new Group();
            final Rectangle rectangle = new Rectangle();
            rectangle.setFill(inventory.getSlotColor());
            rectangle.setWidth(slotSize);
            rectangle.setHeight(slotSize);
            rectangle.setX(slotX + ((fullSize - slotSize) / 2.0));
            rectangle.setY((i * fullSize) + ((fullSize - slotSize) / 2.0));
            slotGroup.getChildren().add(0, rectangle);

            final Item item = inventory.getItemAt(i);
            if (item != null) {
                final Image itemImage = item.getTexture();
                if (itemImage != null) {
                    final ImageView imageView = new ImageView(itemImage);
                    imageView.setFitWidth(rectangle.getWidth() * 0.75);
                    imageView.setFitHeight(rectangle.getHeight() * 0.75);
                    imageView.setX(rectangle.getX() + (rectangle.getWidth() * 0.125));
                    imageView.setY(rectangle.getY() + (rectangle.getHeight() * 0.125));
                    slotGroup.getChildren().add(1, imageView);
                } else {
                    final Rectangle itemView = new Rectangle();
                    itemView.setWidth(rectangle.getWidth() * 0.75);
                    itemView.setHeight(rectangle.getHeight() * 0.75);
                    itemView.setX(rectangle.getX() + (rectangle.getWidth() * 0.125));
                    itemView.setY(rectangle.getY() + (rectangle.getHeight() * 0.125));
                    itemView.setFill(item.getColor());
                    slotGroup.getChildren().add(1, itemView);
                }
            }

            inventorySlot.getChildren().add(slotGroup);
        }

        inventoryGroup.getChildren().add(1, inventorySlot);

        this.group.getChildren().add(inventoryGroup);
    }

    private void drawDialog(Dialog dialog) {
        if (dialog != null) {
            final Group dialogGroup = new Group();
            final Rectangle dialogBackGround = new Rectangle();
            dialogBackGround.setWidth(ZeldaLikeApplication.WIDTH);
            dialogBackGround.setHeight(ZeldaLikeApplication.HEIGHT * 0.2d);
            dialogBackGround.setX(0);
            dialogBackGround.setY(ZeldaLikeApplication.HEIGHT * 0.8d);
            dialogBackGround.setFill(Color.color(0, 0, 0, .4));

            dialogGroup.getChildren().add(0, dialogBackGround);

            final Text text = new Text();
            text.setX(ZeldaLikeApplication.WIDTH * 0.01);
            text.setY(ZeldaLikeApplication.HEIGHT * 0.87);
            text.setFill(dialog.getColor());
            text.setFont(Font.font(ZeldaLikeApplication.HEIGHT * 0.05));
            text.setText(dialog.getText());
            dialogGroup.getChildren().add(1, text);

            this.group.getChildren().add(dialogGroup);
        }
    }

    private void drawPlayerInformation(PlayerEntity player) {
        final Group informationGroup = new Group();

        final Rectangle rectangle = new Rectangle();
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(ZeldaLikeApplication.WIDTH * 0.46);
        rectangle.setHeight((ZeldaLikeApplication.HEIGHT * 0.13));
        rectangle.setFill(Color.color(0, 0, 0, 0.6));
        informationGroup.getChildren().add(0, rectangle);

        final Group contentGroup = new Group();
        final double size = ZeldaLikeApplication.WIDTH * 0.02;
        final double offset = size / 2.0;

        for (int i = 0; i < player.getHealth(); i++) {
            final ImageView imageView = new ImageView(HUDRender.HEALTH_TEXTURE);
            imageView.setFitWidth(size);
            imageView.setFitHeight(size);
            imageView.setX(offset + ((offset * 0.2) * i) + (size * i));
            imageView.setY(offset);
            contentGroup.getChildren().add(imageView);
        }

        final ImageView moneyView = new ImageView(MoneyItem.TEXTURE);
        moneyView.setX(offset);
        moneyView.setY((offset * 2) + size);
        moneyView.setFitWidth(size);
        moneyView.setFitHeight(size);
        contentGroup.getChildren().add(moneyView);

        final Text moneyText = new Text();
        moneyText.setText(String.valueOf(player.getMoney()));
        moneyText.setX(offset * 2 + size);
        moneyText.setY(moneyView.getY() + (size * 0.85));
        moneyText.setFill(Color.color(1, 1, 1));
        moneyText.setFont(Font.font(ZeldaLikeApplication.HEIGHT * 0.03));
        contentGroup.getChildren().add(moneyText);

        informationGroup.getChildren().add(1, contentGroup);
        this.group.getChildren().add(informationGroup);
    }

    private void drawInformations() {
        final Group group = new Group();

        final double x = ZeldaLikeApplication.WIDTH * 0.01;
        final Font font = Font.font(ZeldaLikeApplication.HEIGHT * 0.02);
        final Color color = Color.color(1, 1, 1);

        final Rectangle rectangle = new Rectangle();
        rectangle.setX(x - 5.0);
        rectangle.setY((ZeldaLikeApplication.HEIGHT * 0.9) - font.getSize() - 3);
        rectangle.setWidth(ZeldaLikeApplication.WIDTH * 0.1);
        rectangle.setHeight((ZeldaLikeApplication.HEIGHT * 0.05) + 10.0);
        rectangle.setFill(Color.color(0, 0, 0, 0.6));
        group.getChildren().add(rectangle);

        Text text = new Text();
        text.setText("TPS: " + ZeldaLikeApplication.getTps());
        text.setX(x);
        text.setY(ZeldaLikeApplication.HEIGHT * 0.9);
        text.setFill(color);
        text.setFont(font);
        group.getChildren().add(text);

        text = new Text();
        text.setText("FPS: " + ZeldaLikeApplication.getFps());
        text.setX(x);
        text.setY(ZeldaLikeApplication.HEIGHT * 0.93);
        text.setFill(color);
        text.setFont(font);
        group.getChildren().add(text);

        this.group.getChildren().add(group);
    }
}
