package gormmod.relics;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import gormmod.characters.TheCrab;

import static gormmod.GormMod.makeID;

@AutoAdd.Seen
public class OrangeRelic extends BaseRelic {
    private static final String NAME = "Orange";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int STRENGTH = 1;

    public OrangeRelic() {
        super(ID, NAME, TheCrab.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STRENGTH + DESCRIPTIONS[1];
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        AbstractPlayer p = AbstractDungeon.player;

        if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(m, this));
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1)));
        }
    }
}


