package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import javafx.scene.Group;

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

        this.blockRender.render();
        this.entityRender.render();
        this.particuleRender.render();
        this.hudRender.render();

        this.zeldaLike.getScene().getChildren().add(0, this.group);
    }
}
