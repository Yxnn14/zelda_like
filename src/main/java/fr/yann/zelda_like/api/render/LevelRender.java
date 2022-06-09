package fr.yann.zelda_like.api.render;

import javafx.scene.Group;

public interface LevelRender extends Render {
    Render getBlockRender();

    Group getGroup();
}
