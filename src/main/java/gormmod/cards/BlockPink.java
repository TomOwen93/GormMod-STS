package gormmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gormmod.util.CardStats;

public class BlockPink extends BaseCard {

    public static final String ID = makeID(BlockPink.class.getSimpleName());
    public static final CardStats info = new CardStats(CardColor.RED, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, 1);
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 6;

    public BlockPink() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setInnate(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, BLOCK));
    }
}
