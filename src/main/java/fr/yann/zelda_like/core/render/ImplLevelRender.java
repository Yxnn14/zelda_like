package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ImplLevelRender implements LevelRender {
    private final ZeldaLike zeldaLike;
    private final Render blockRender;
    private final Render entityRender;
    private final Render particuleRender;

    private final Render hudRender;
    private Group group;

    public ImplLevelRender(ZeldaLike zeldaLike) {
        this.zeldaLike = zeldaLike;
        this.blockRender = new BlockRender(zeldaLike, this);
        this.entityRender = new EntityRender(zeldaLike, this);
        this.particuleRender = new ParticuleRender(zeldaLike, this);
        this.hudRender = new HUDRender(zeldaLike, this);
    }

    @Override
    public Render getBlockRender() {
        return this.blockRender;
    }

    @Override
    public Render getEntityRender() {
        return this.entityRender;
    }

    @Override
    public Render getHudRender() {
        return hudRender;
    }

    public Group getGroup() {
        return this.group;
    }

    @Override
    public void render() {
        if (this.group != null) {
            this.zeldaLike.getScene().getChildren().remove(group);
        }
        this.group = new Group();
        final Level level = zeldaLike.getLevelManager().get();;

        if (level != null) {
            final PlayerEntity player = level.getPlayer();
            if (level.isVictory() || (player != null && player.isDeath())) {
                this.gameOver(!level.isVictory());
            } else {
                this.blockRender.render();
                this.entityRender.render();
                this.particuleRender.render();
                this.hudRender.render();
            }
        }

        this.zeldaLike.getScene().getChildren().add(0, this.group);
    }

    private void gameOver(boolean gameOver) {
        final Text text = new Text();
        text.setText(gameOver ? "Game Over" : "Victoire");
        text.setFont(Font.font(null, FontWeight.BOLD, ZeldaLikeApplication.HEIGHT * 0.2));
        text.setFill(
            gameOver
                ? Color.color(.6, 0, 0)
                : Color.color(.6, .6, 0)
        );

        text.setX((ZeldaLikeApplication.WIDTH - text.getLayoutBounds().getWidth()) / 2.0);
        text.setY(ZeldaLikeApplication.HEIGHT / 2.0);

        this.group.getChildren().add(text);
    }
}
