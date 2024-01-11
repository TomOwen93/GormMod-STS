package gormmod.relics;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gormmod.powers.LuckPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.*;
import static gormmod.GormMod.makeID;

@AutoAdd.Seen
public class GoldDice extends BaseRelic {
    private static final String NAME = "GoldDice";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int COUNTER = 1;

    public GoldDice() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0], COUNTER);
    }

    @Override
    public void atTurnStart(){
        AbstractPlayer p = AbstractDungeon.player;
        this.counter = 1;
        this.flash();

        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(p, p, new LuckPower(p, 1)));

    }

    public void onPlayCard(AbstractCard card, AbstractMonster m){
        AbstractPlayer p =  AbstractDungeon.player;
        if(card.type == CardType.ATTACK && this.counter > 0){
            addToBot(new RemoveSpecificPowerAction(p, p, makeID("Luck")));
            --this.counter;
        }
    }

}


