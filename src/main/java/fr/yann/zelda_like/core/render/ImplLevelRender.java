package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.render.LevelRender;
import javafx.scene.Group;

public class ImplLevelRender implements LevelRender {
    private final ZeldaLike zeldaLike;
    private final BlockRender blockRender;
    private Group group;

    public ImplLevelRender(ZeldaLike zeldaLike) {
        this.zeldaLike = zeldaLike;
        this.blockRender = new BlockRender(zeldaLike, this);
    }

    @Override
    public BlockRender getBlockRender() {
        return this.blockRender;
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

        this.zeldaLike.getScene().getChildren().add(0, this.group);
    }
}
