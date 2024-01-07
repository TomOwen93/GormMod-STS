package gormmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AccuracyPower;
import gormmod.util.CardStats;

public class CoffeeCard extends BaseCard {

    public static final String ID = makeID(CoffeeCard.class.getSimpleName());
    public static final CardStats info = new CardStats(CardColor.RED, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, 1);
    public static final int ACCURACY = 5;
    public static final int UPG_ACCURACY = 7;
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 3;

    public CoffeeCard() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(ACCURACY, UPG_ACCURACY);
        setInnate(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, BLOCK));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new AccuracyPower(abstractPlayer, ACCURACY)));
    }
}
