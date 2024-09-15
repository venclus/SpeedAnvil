package dev.venclus.speedanvil.config

import dev.venclus.speedanvil.config.other.Type
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.*
import lombok.Getter

@Headers(
    Header("## SpeedAnvil (Message-Config) ##"),
    Header("Dostepne type: (CHAT, ACTIONBAR, SUBTITLE, TITLE, TITLE_SUBTITLE, DO_NOT_SEND)")
)

@Getter
class MessageConfig : OkaeriConfig() {
    @Comment("Sekcja wiadomości ogólnych")
    @Comment(" ")
    var reloadedConfig = "&aPomyślnie przeładowano konfigurację!"
    var noPermission = "&4✘ &cNie posiadasz permisji. "
    @Comment("Sekcja konfiguracji wiadomości dla gracza")
    @Comment(" ")
    var otherItem = Type("CHAT", "&cMusisz trzymać w ręce przedmiot, który chciałbyś naprawić :)")
    var successRepair = Type("CHAT", "&2✔ &aPomyślnie naprawiłeś przedmiot.")
    var dontHaveRequiredItem = Type("CHAT", "&4✘ &cNie masz jednego diamentu, aby naprawić ten przedmiot.")
    var cantBeRepair = Type("CHAT", "&4✘ &cTego przedmiotu nie możesz naprawić")
}

