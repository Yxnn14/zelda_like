package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
            final Rectangle rectangle = new Rectangle();
            final double inventoryWidth = ZeldaLikeApplication.WIDTH / 2.0;
            final double slotSize = inventoryWidth / 10.0;
            rectangle.setWidth(inventoryWidth);
            final double inventoryHeight = slotSize * Math.ceil(player.getInventoryView().getSize() / 10.0);
            rectangle.setHeight(inventoryHeight);
            rectangle.setX(ZeldaLikeApplication.WIDTH / 4.0);
            rectangle.setY((ZeldaLikeApplication.HEIGHT - inventoryHeight) / 2.0);
            rectangle.setFill(Color.color(0, 0, 0, .4));

            inventoryViewGroup.getChildren().add(0, rectangle);

            final Group slotsGroup = new Group();

            for (int i = 0; i < player.getInventoryView().getSize(); i++) {
                final Group slotGroup = new Group();
                final Rectangle slot = new Rectangle();
                slot.setWidth(slotSize * 0.75);
                slot.setHeight(slotSize * 0.75);
                slot.setX(rectangle.getX() + ((slotSize * (i % 10.0)) + (slotSize * 0.125)));
                slot.setY(rectangle.getY() + ((slotSize * (Math.ceil((i + 1) / 10.0) - 1)) + (slotSize * 0.125)));
                slot.setFill(Color.color(0.6, 0.6, 0.6));
                slotGroup.getChildren().add(0, slot);

                final Item item = player.getInventoryView().getItemAt(i);
                if (item != null) {
                    final Rectangle itemView = new Rectangle();
                    itemView.setWidth(slot.getWidth() * 0.75);
                    itemView.setHeight(slot.getHeight() * 0.75);
                    itemView.setX(slot.getX() + (slot.getWidth() * 0.125));
                    itemView.setY(slot.getY() + (slot.getHeight() * 0.125));
                    itemView.setFill(item.getColor());

                    slotGroup.getChildren().add(1, itemView);
                }

                slotsGroup.getChildren().add(slotGroup);
            }

            inventoryViewGroup.getChildren().add(1, slotsGroup);
            this.group.getChildren().add(inventoryViewGroup);
        }

        this.levelRender.getGroup().getChildren().add(2, this.group);
    }
}
