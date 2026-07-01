package de.txmmy.kurzecommands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class KurzeCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerShortcuts();

        getLogger().info("KurzeCommands aktiviert.");
    }

    private void registerShortcuts() {

        ConfigurationSection section =
                getConfig().getConfigurationSection("commands");

        if (section == null) {
            getLogger().warning("Keine Commands gefunden.");
            return;
        }

        for (String shortcut : section.getKeys(false)) {

            boolean success = getServer()
                    .getCommandMap()
                    .register(
                            "kurzecommands",
                            new ShortcutCommand(this, shortcut)
                    );

            if (success) {
                getLogger().info("Shortcut registriert: /" + shortcut);
            }
        }
    }
}
