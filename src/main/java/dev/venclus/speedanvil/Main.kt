package dev.venclus.speedanvil

import dev.venclus.speedanvil.commands.CommandMap
import dev.venclus.speedanvil.config.Config
import dev.venclus.speedanvil.config.MessageConfig
import dev.venclus.speedanvil.listener.PlayerAnvilListener
import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit
import lombok.Getter
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import pl.speedplugins.utilities.Message
import java.io.File
import java.util.logging.Level

class Main : JavaPlugin() {
    @Getter
    lateinit var messageConfig: MessageConfig
    lateinit var config: Config
    lateinit var message: Message

    companion object {
        lateinit var main: Main
            private set
    }

    override fun onEnable() {
        main = this
        checkAuthor()
        loadConfig()
        loadMessageCfg()
        CommandMap(this)
        message = Message()
        registerEvents()
        logger.info(" ")
        logger.info("# SpeedAnvil (1.0) został włączony!")
        logger.info(" ")
    }

    override fun onDisable() {
        logger.info(" ")
        logger.info("# SpeedAnvil (1.0) został wyłączony!")
        logger.info(" ")
        super.onDisable()
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(PlayerAnvilListener(config, messageConfig, message), this)
        logger.info("Zarejestrowano listenery")
    }

    private fun checkAuthor() {
        if (!this.description.authors.contains("venclus")) {
            this.logger.info(" ")
            logger.info(" ")
            logger.info("# SpeedAnvil (1.0) został wyłączony!")
            logger.info("# Powód: Zmiana autora pluginu")
            logger.info("# Oryginalny autor: venclus")
            logger.info(" ")
            this.logger.info(" ")
            this.pluginLoader.disablePlugin(this as Plugin)
            return
        }
        if (this.description.description != "Plugin na magiczne kowadło z SpeedPlugins.PL") {
            logger.info(" ")
            logger.info("# SpeedAnvil (1.0) został wyłączony!")
            logger.info("# Powód: Zmiana opisu pluginu")
            logger.info(" ")
            this.pluginLoader.disablePlugin(this as Plugin)
            return
        }
        if (this.description.name != "SpeedAnvil") {
            logger.info(" ")
            logger.info("# SpeedAnvil (1.0) został wyłączony!")
            logger.info("# Powód: Zmiana nazwy pluginu")
            logger.info(" ")
            this.pluginLoader.disablePlugin(this as Plugin)
            return
        }
        if (this.description.website != "dc.speedplugins.pl") {
            logger.info(" ")
            logger.info("# SpeedAnvil (1.0) został wyłączony!")
            logger.info("# Powód: Zmiana website pluginu")
            logger.info(" ")
            this.pluginLoader.disablePlugin(this as Plugin)
            return
        }
    }



    private fun loadConfig() {
        try {
            config = ConfigManager.create(Config::class.java) {
                it.withConfigurer(YamlBukkitConfigurer(), SerdesBukkit())
                it.withBindFile(File(dataFolder, "config.yml"))
                it.saveDefaults()
                it.load(true)
                logger.info("Załadowano config")
            }
        } catch (exception: Exception) {
            logger.log(Level.SEVERE, "Wystąpił błąd podczas ładowania config.yml, BŁĄD:", exception)
            pluginLoader.disablePlugin(this)
        }
    }

    private fun loadMessageCfg() {
        try {
            messageConfig = ConfigManager.create(MessageConfig::class.java) {
                it.withConfigurer(YamlBukkitConfigurer(), SerdesBukkit())
                it.withBindFile(File(dataFolder, "message.yml"))
                it.saveDefaults()
                it.load(true)
                logger.info("Załadowano message.yml")
            }
        } catch (exception: Exception) {
            logger.log(Level.SEVERE, "Wystąpił błąd podczas ładowania message.yml, BŁĄD:", exception)
            pluginLoader.disablePlugin(this)
        }
    }
}
