package gormmod.relics;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AccuracyPower;

import static gormmod.GormMod.makeID;

@AutoAdd.Seen
public class CoffeeRelic extends BaseRelic {
    private static final String NAME = "CoffeeRelic";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int ACCURACY = 10;

    public CoffeeRelic() {
        super(ID, NAME, AbstractCard.CardColor.RED, RARITY, SOUND);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AccuracyPower(AbstractDungeon.player, ACCURACY)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + ACCURACY + DESCRIPTIONS[1];
    }
}
