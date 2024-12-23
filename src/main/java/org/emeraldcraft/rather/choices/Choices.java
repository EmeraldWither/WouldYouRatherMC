package org.emeraldcraft.rather.choices;

import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choices.negative.BlocksFightBackChoice;
import org.emeraldcraft.rather.choices.positive.*;
import org.emeraldcraft.rather.choices.negative.*;

public class Choices {
    public static final Choice[] POSITIVE = new Choice[]{
            new Choice(InfiniteDiamondsChoice::new),
            new Choice(DoubleDropsChoice::new),
            new Choice(EnderPearlEggsChoice::new),
            new Choice(EveryAdvancementChoice::new),
            new Choice(FireResChoice::new),
            new Choice(ItemsFullStackChoice::new),
            new Choice(NoExplosionDamageChoice::new),
            new Choice(NoHungerChoice::new),
            new Choice(AlwaysEnderEyesChoice::new),
            new Choice(FasterMovementChoice::new),
            new Choice(IncreaseJumpHeightChoice::new),
            new Choice(PetRockChoice::new),
            new Choice(RemoveRandomPositiveChoice::new),
            new Choice(EndermenNeverAgroChoice::new),
//            new Choice(ESPChoice::new),
    };
    public static final Choice[] NEGATIVE = new Choice[]{
            new Choice(PermBlindnessChoice::new),
            new Choice(LoseHealthChoice::new),
            new Choice(InstantHighTeleportChoice::new),
            new Choice(NoArmorChoice::new),
            new Choice(ScrambledChatChoice::new),
            new Choice(OnlyTopPlacingChoice::new),
            new Choice(ChunkDeleteChoice::new),
            new Choice(NetherTeleportChoice::new),
            new Choice(LeavesToCreepersChoice::new),
//            new Choice(AllItemsDeCraftedChoice::new),
            new Choice(OnlyRottenFleshChoice::new),
            new Choice(SinkingBoatsChoice::new),
            new Choice(NoSprintChoice::new),
            new Choice(BlocksFightBackChoice::new),
            new Choice(CantTouchGrassChoice::new),
            new Choice(RepeatingTNTChoice::new),
            new Choice(HallucinationsChoice::new),
            new Choice(OnlyAcaciaChoice::new),
            new Choice(RandomStructureSpawnChoice::new),
            new Choice(TaxesChoice::new),
            new Choice(MCGirlfriendChoice::new),
            new Choice(DecayingWorldChoice::new),
            new Choice(RemoveRandomPositiveChoice::new)
    };
}
