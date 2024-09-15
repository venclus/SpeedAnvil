package dev.venclus.speedanvil.config

import com.cryptomorin.xseries.XMaterial
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.Comment
import eu.okaeri.configs.annotation.Header
import eu.okaeri.configs.annotation.Headers

@Headers(
    Header("## SpeedAnvil (Main-Config) ##"),
)
class Config : OkaeriConfig() {
    @Comment("Jaki przedmiot ma być brany przy kliknięciu w kowadło")
    var repairMaterial: XMaterial = XMaterial.DIAMOND
    @Comment("Jaka ilość tego fajnego przedmiotu ma być brana podczas naprawiania przedmiotu")
    var repairCost: Int = 1
    @Comment("Permisja do przeładowywania konfiguracji")
    var reloadPermission = "speedanvil.reload"
}