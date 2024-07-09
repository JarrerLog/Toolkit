package toolkit;

import arc.util.Threads;
import arc.util.Timer;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.core.GameState.State;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.mod.Mod;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.blocks.power.ConsumeGenerator;


public class ToolkitMod extends Mod {
    public ToolkitMod() {
        Vars.state.rules.reactorExplosions = false;
        Vars.mods.getScripts().runConsole("Blocks.mender.reload = 0");
        Vars.mods.getScripts().runConsole("Blocks.mendProjector.reload = 0");
        


        Timer.schedule(() -> {
            Vars.state.rules.reactorExplosions = false;
            if (Vars.state.getState() != State.playing) {
                return;
            }

            Groups.build.each((build) -> {
                if (build.block instanceof ConsumeGenerator) {
                    for (Item item : Vars.content.items()) {
                        if (build.block.consumesItem(item)) {
                            build.items.set(item, 123456);
                        }
                    }
                    for (Liquid liq : Vars.content.liquids()) {
                        if (build.block.consumesLiquid(liq)) {
                            build.liquids.set(liq, 123456);
                        }
                    }
                }
            });
            Groups.player.each((player) -> {
                if (player.team() == Team.sharded) {
                    for (Item item : Vars.content.items()) {
                        Vars.state.teams.cores(player.team()).first().items.set(item, Vars.state.teams.cores(player.team()).first().storageCapacity);
                    }
                }
            });
        }, 0.0f, 0.1f);

    }
}
