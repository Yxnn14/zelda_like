package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Cursor;
import fr.yann.zelda_like.api.dialog.Dialog;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HUDRender implements Render {

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
            }

            this.group.getChildren().add(inventoryViewGroup);
        } else if (player.getCursorItem() != null) {
            player.setCursorItem(null);
        }

        final Dialog dialog = level.getDialogManager().get();
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

        this.levelRender.getGroup().getChildren().add(2, this.group);
    }

    private boolean intersect(Cursor cursor, double x, double y, double width, double height, boolean drag) {
        return x <= (drag ? cursor.getDragX() : cursor.getX())
            && (x + width) >= (drag ? cursor.getDragX() : cursor.getX())
            && y <= (drag ? cursor.getDragY() : cursor.getY())
            && (y + height) >= (drag ? cursor.getDragY() : cursor.getY());
    }
}
