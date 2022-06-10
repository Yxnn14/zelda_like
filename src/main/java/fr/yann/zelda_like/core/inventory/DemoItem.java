package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.entity.Entity;
import javafx.scene.paint.Color;

public class DemoItem extends AbstractItem {

    public DemoItem() {
        super("DemoItem", Color.color(0.50, 0.45, 0.80));
    }

    @Override
    public boolean use(Entity entity) {
        System.out.println("DemoUsed");
        return super.use(entity);
    }
}

