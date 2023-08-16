package toolkit;

import arc.util.Timer;
import mindustry.Vars;
import mindustry.core.GameState.State;
import mindustry.gen.Groups;
import mindustry.mod.Mod;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.Turret;

public class ToolkitMod extends Mod {
    public ToolkitMod() {
        
        Timer.schedule(() -> {
            Vars.state.rules.reactorExplosions = false;
            if (Vars.state.getState() != State.playing) {
                return;
            }
            Groups.build.each((build) -> {
                Block block = build.block;
                if (block instanceof Turret) {
                    return;
                }
                for (Item item : Vars.content.items()) {
                    if (block.consumesItem(item)) {
                        if (build.items.get(item) < block.itemCapacity) {
                            build.items.set(item, block.itemCapacity);
                        }
                    }
                }
                for (Liquid liquid : Vars.content.liquids()) {
                    if (block.consumesLiquid(liquid)) {
                        if (build.liquids().get(liquid) < block.liquidCapacity) {
                            build.liquids.set(liquid, block.liquidCapacity);
                        }
                    }
                }
            });
        }, 0.0f, 0.2f);
    }
}
