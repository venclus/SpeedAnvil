package dev.venclus.speedanvil.listener

import dev.venclus.speedanvil.config.Config
import dev.venclus.speedanvil.config.MessageConfig
import pl.speedplugins.utilities.Message
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class PlayerAnvilListener(
    private val pluginConfig: Config,
    private val messageConfig: MessageConfig,
    private val message: Message
) : Listener {

    @EventHandler(priority = EventPriority.HIGH)
    fun onBlockClick(e: PlayerInteractEvent) {
        val player = e.player
        val clickedBlock = e.clickedBlock ?: return
        if (e.action == Action.LEFT_CLICK_BLOCK) {
            return
        }
        if (clickedBlock.type == Material.ANVIL) {
            e.isCancelled = true
            val itemInHand = player.itemInHand
            if (itemInHand == null || itemInHand.type == Material.AIR) {
                message.message(player, messageConfig.otherItem.typ, messageConfig.otherItem.wiadomosc)
                return
            }
            if (itemInHand.durability.toInt() == 0) {
                message.message(player, messageConfig.cantBeRepair.typ, messageConfig.cantBeRepair.wiadomosc)
                return
            }

            val repairMaterial = pluginConfig.repairMaterial.parseMaterial() ?: run {
                // player.sendMessage("Error: Invalid repair material configured.")
                return
            }

            if (player.inventory.containsAtLeast(ItemStack(repairMaterial), pluginConfig.repairCost)) {
                itemInHand.durability = 0
                player.inventory.removeItem(ItemStack(repairMaterial, pluginConfig.repairCost))
                player.setItemInHand(itemInHand)
                message.message(player as CommandSender, messageConfig.successRepair.typ, messageConfig.successRepair.wiadomosc)
                return
            }
            message.message(player as CommandSender, messageConfig.dontHaveRequiredItem.typ, messageConfig.dontHaveRequiredItem.wiadomosc)
        }
    }
}
