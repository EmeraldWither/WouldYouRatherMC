package org.emeraldcraft.rather;

import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choices.negative.*;
import org.emeraldcraft.rather.choices.positive.*;

public class Choices {
    public static final Choice[] POSITIVE = new Choice[] {
            new Choice(InfiniteDiamondsChoice::new, "You can only get diamonds", true, "INFINITE_DIAMONDS"),
            new Choice(DoubleDropsChoice::new, "You get double the drops", true, "DOUBLE_DROPS"),
            new Choice(EnderPearlEggsChoice::new, "You can use eggs to teleport as ender pearls", true, "EGG_TELEPORT"),
            new Choice(EveryAdvancementChoice::new, "You get every advancement", true, "ALL_ADVANCEMENTS"),
            new Choice(FireResChoice::new, "You get fire resistance.", true, "FIRE_RES"),
            new Choice(ItemsFullStackChoice::new, "You have all your items be a full stack", true, "FULL_STACK_ITEMS"),

    };
    public static final Choice[] NEGATIVE = new Choice[] {
            new Choice(PermBlindnessChoice::new, "You have permanent blindness.", false, "PERM_BLINDNESS"),
            new Choice(LoseHealthChoice::new, "You lose 25% of your health. ", false, "LOSE_HEALTH"),
            new Choice(InstantHighTeleportChoice::new, "You get teleported to y = 250 ", false, "INSTANT_HIGH_TELEPORT"),
            new Choice(NoArmorChoice::new, "You cannot wear any armor", false, "NO_ARMOR"),
            new Choice(ScrambledChatChoice::new, "Your chat is scrambled", false, "SCRAMBLED_CHAT"),
            new Choice(OnlyTopPlacingChoice::new, "You can only place on the top of blocks", false, "TOP_BLOCK_PLACING"),
            new Choice(ChunkDeleteChoice::new, "The chunk you're standing in gets deleted", false, "CHUNK_DELETE"),
            new Choice(NetherTeleportChoice::new, "You get teleported to the Nether", false, "NETHER_TELEPORT"),
    };
}
