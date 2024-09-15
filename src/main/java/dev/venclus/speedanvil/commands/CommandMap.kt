package dev.venclus.speedanvil.commands

import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.SimplePluginManager
import dev.venclus.speedanvil.Main
import dev.venclus.speedanvil.commands.use.ReloadCommand
import java.lang.reflect.Field
import java.util.*

class CommandMap(private val plugin: Main) {
    private var scm: SimpleCommandMap? = null

    init {
        setupSimpleCommandMap()
        listOf(
            ReloadCommand("speedanvil", Collections.singletonList("anvilplugin"))
        ).forEach { registerCommands(it) }
    }

    private fun registerCommands(cmd: CommandUse) {
        scm?.register(plugin.description.name, cmd)
    }

    private fun setupSimpleCommandMap() {
        val spm = plugin.server.pluginManager as SimplePluginManager
        val field: Field? = try {
            SimplePluginManager::class.java.getDeclaredField("commandMap")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        field?.isAccessible = true

        scm = try {
            field?.get(spm) as? SimpleCommandMap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
