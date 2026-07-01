package de.txmmy.kurzecommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ShortcutCommand extends Command {

    private final KurzeCommands plugin;

    public ShortcutCommand(KurzeCommands plugin, String name) {
        super(name);

        this.plugin = plugin;

        setPermission("kurzecommands." + name);
        setDescription("Shortcut Command");
    }

    @Override
    public boolean execute(CommandSender sender,
                           String label,
                           String[] args) {

        String command =
                plugin.getConfig()
                        .getString("commands." + label + ".command");

        if (command == null) {
            sender.sendMessage("§cDieser Shortcut existiert nicht.");
            return true;
        }

        if (args.length >= 1) {
            command = command.replace("{user}", args[0]);
        }

        if (args.length >= 2) {
            String reason = String.join(
                    " ",
                    Arrays.copyOfRange(args, 1, args.length)
            );

            command = command.replace("{reason}", reason);
        }

        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                command
        );

        sender.sendMessage("§aAusgeführt: §f" + command);

        return true;
    }
}
