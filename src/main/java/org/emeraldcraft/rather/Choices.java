package org.emeraldcraft.rather;

import org.emeraldcraft.rather.choices.negative.LoseHealthChoice;
import org.emeraldcraft.rather.choices.negative.PermBlindnessChoice;
import org.emeraldcraft.rather.choices.positive.DoubleDropsChoice;
import org.emeraldcraft.rather.choices.positive.InfiniteDiamondsChoice;

public class Choices {
    public static final Choice[] POSITIVE = new Choice[] {
            new Choice(new InfiniteDiamondsChoice(), "You can only get diamonds", true, "INFINITE_DIAMONDS"),
            new Choice(new DoubleDropsChoice(), "You get double the drops", true, "DOUBLE_DROPS"),

    };
    public static final Choice[] NEGATIVE = new Choice[] {
            new Choice(new PermBlindnessChoice(), "You have permanent blindness.", false, "PERM_BLINDNESS"),
            new Choice(new LoseHealthChoice(), "You lose 25% of your health. ", false, "LOSE_HEALTH")

    };
}
