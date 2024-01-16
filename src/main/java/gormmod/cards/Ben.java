package gormmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gormmod.util.CardStats;

public class Ben extends BaseCard {

    public static final String ID = makeID(Ben.class.getSimpleName());
    public static final CardStats info = new CardStats(CardColor.RED, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, 1);
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 6;

    public Ben() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setInnate(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, BLOCK));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Ben();
    }
}
