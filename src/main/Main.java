package main;

import arc.*;
import arc.util.*;
import main.ui.SchematicDialog;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.mod.*;

public class Main extends Mod {

    public Main() {
        Events.on(ClientLoadEvent.class, e -> {
            SchematicDialog schematicDialog = new SchematicDialog();
            Vars.ui.menufrag.addButton("Schematic browser", () -> schematicDialog.show());
        });
    }

    @Override
    public void loadContent() {
    }
}