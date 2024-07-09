package toolkit;

import arc.util.Threads;
import arc.util.Timer;
import mindustry.Vars;
import mindustry.core.GameState.State;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.mod.Mod;
import mindustry.type.Item;

public class ToolkitMod extends Mod {
    public ToolkitMod() {
        Threads.daemon(() -> {
            Timer.schedule(() -> {
                Vars.state.rules.reactorExplosions = false;
                if (Vars.state.getState() != State.playing) {
                    return;
                }
                Groups.player.each((player) -> {
                    if (player.team() == Team.sharded) {
                        for (Item item : Vars.content.items()) {
                            Vars.state.teams.cores(player.team()).first().items.set(item, Vars.state.teams.cores(player.team()).first().storageCapacity);
                        }
                    }
                });
            }, 0.0f, 0.1f);
        }).start();

    }
}
