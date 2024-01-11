package gormmod.powers;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static gormmod.GormMod.makeID;

public class LuckPower extends BasePower {

    public static final String POWER_ID = makeID("Luck");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private AbstractMonster m = ReflectionHacks.getPrivate(AbstractDungeon.player, AbstractPlayer.class, "hoveredMonster");



    int roll1 = AbstractDungeon.relicRng.random(1, 5);
    int roll2 = AbstractDungeon.relicRng.random(1, 5);

    public LuckPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        ;
    }


    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        int damageRoll = Math.max(roll1, roll2);
        AbstractMonster m2 = ReflectionHacks.getPrivate(AbstractDungeon.player, AbstractPlayer.class, "hoveredMonster");

        if (this.m != m2 && type == DamageInfo.DamageType.NORMAL)
        {
            return damage + damageRoll;
        }
            else return damage;
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
