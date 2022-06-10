package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class BlockRender implements Render {
    private final ZeldaLike zeldaLike;
    private final LevelRender levelRender;
    private Group group;
    public BlockRender(ZeldaLike zeldaLike, LevelRender levelRender)
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

        final double xRatio = (double) ZeldaLikeApplication.WIDTH / (double) level.getWidth();
        final double yRatio = (double) ZeldaLikeApplication.HEIGHT / (double) level.getHeight();

        level.getBlocks()
            .forEach(block -> {
                final Rectangle rectangle = new Rectangle();
                rectangle.setFill(block.getColor());
                rectangle.setWidth(xRatio + 1);
                rectangle.setHeight(yRatio + 1);
                rectangle.setX(xRatio * (double) block.getLocation().getX());
                rectangle.setY(yRatio * (double) block.getLocation().getY());
                this.group.getChildren().add(rectangle);
            });

        this.levelRender.getGroup().getChildren().add(0, this.group);
    }
}
