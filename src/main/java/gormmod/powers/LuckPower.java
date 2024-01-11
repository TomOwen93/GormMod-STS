package gormmod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static gormmod.GormMod.makeID;

public class LuckPower extends BasePower {

    public static final String POWER_ID = makeID("Luck");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    int roll1 = AbstractDungeon.relicRng.random(1, 5);
    int roll2 = AbstractDungeon.relicRng.random(1, 5);

    public LuckPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        int damageRoll = Math.max(roll1, roll2);

        if (type == DamageInfo.DamageType.NORMAL && this.amount > 0) {
            return damage + damageRoll;
        }
        return damage;
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
