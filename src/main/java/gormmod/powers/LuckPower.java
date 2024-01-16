package gormmod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static gormmod.GormMod.makeID;

public class LuckPower extends BasePower {

    public static final String POWER_ID = makeID("Luck");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private final int roll = Math.max(AbstractDungeon.relicRng.random(1, 5),
            AbstractDungeon.relicRng.random(1, 5));

    public LuckPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float) this.roll : damage;
    }



    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
