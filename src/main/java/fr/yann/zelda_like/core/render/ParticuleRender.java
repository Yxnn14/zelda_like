package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ParticuleRender implements Render {
    private final ZeldaLike zeldaLike;
    private final LevelRender levelRender;

    private Group group;

    public ParticuleRender(ZeldaLike zeldaLike, LevelRender levelRender) {
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
        final double size = ZeldaLikeApplication.WIDTH * 0.01;

        level.getParticles()
            .forEach(particule -> {
                final Image image = particule.getTexture();
                if (image != null) {
                    final ImageView imageView = new ImageView(image);
                    imageView.setX(particule.getLocation().getX() - (size / 2.0));
                    imageView.setY(particule.getLocation().getY() - (size / 2.0));
                    imageView.setFitWidth(size);
                    imageView.setFitHeight(size);
                    this.group.getChildren().add(imageView);
                    return;
                }
                final Rectangle rectangle = new Rectangle();
                rectangle.setFill(particule.getColor());
                rectangle.setX(particule.getLocation().getX() - (size / 2.0));
                rectangle.setY(particule.getLocation().getY() - (size / 2.0));
                rectangle.setWidth(size);
                rectangle.setHeight(size);
                this.group.getChildren().add(rectangle);
            });

        this.levelRender.getGroup().getChildren().add(2, this.group);
    }
}
