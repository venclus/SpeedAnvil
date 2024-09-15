package dev.venclus.speedanvil.commands.use

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import dev.venclus.speedanvil.commands.CommandUse
import org.bukkit.plugin.Plugin
import pl.speedplugins.utilities.ChatUtil

class ReloadCommand(name: String, aliases: List<String>? = null) : CommandUse(name, aliases) {

    override fun run(sender: CommandSender, args: Array<String>) {
        if (sender.hasPermission(this.config.reloadPermission)) {
            try {
                this.config.load()
                this.messageConfig.load()
                sender.sendMessage(ChatUtil.fixColor(this.messageConfig.reloadedConfig))
            } catch (e: Exception) {
                sender.sendMessage(ChatUtil.fixColor("&cWystąpił błąd podczas przeładowywania konfiguracji"))
                e.printStackTrace()
            }
        } else {
            sender.sendMessage(ChatUtil.fixColor(this.messageConfig.noPermission))
        }
    }

    override fun tab(p: Player, args: Array<String>): List<String>? {
        return null
    }

    override fun getPlugin(): Plugin {
        return plugin
    }
}
