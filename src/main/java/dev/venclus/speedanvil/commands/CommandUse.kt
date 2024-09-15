package dev.venclus.speedanvil.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginIdentifiableCommand
import org.bukkit.entity.Player
import dev.venclus.speedanvil.Main
import dev.venclus.speedanvil.config.Config
import dev.venclus.speedanvil.config.MessageConfig

abstract class CommandUse(name: String, aliases: List<String>? = null) : Command(name), PluginIdentifiableCommand {
    val plugin: Main = Main.main;
    val config = Config()
    val messageConfig = MessageConfig()

    init {
        aliases?.let { this.aliases = it }
    }

    abstract fun run(sender: CommandSender, args: Array<String>)

    abstract fun tab(p: Player, args: Array<String>): List<String>?

    override fun execute(sender: CommandSender, commandLabel: String, arguments: Array<String>): Boolean {
        run(sender, arguments)
        return true
    }

    override fun tabComplete(sender: CommandSender, label: String, args: Array<String>): List<String> {
        return (sender as? Player)?.let { tab(it, args) } ?: emptyList()
    }
}

