package gormmod.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gormmod.util.CardStats;

import java.util.List;

public class BeardedValley extends BaseCard implements ModalChoice.Callback {

    public static final String ID = makeID(BeardedValley.class.getSimpleName());
    public static final String NAME = "The Bearded Valley";
    private static final int COST = 1;
    private static final int UPG_COST = 0;

    public static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, COST);
    private ModalChoice modal;

    public BeardedValley() {
        super(ID, info);
        setMagic(COST, UPG_COST);

        modal = new ModalChoiceBuilder()
                .setCallback(this) // Sets callback of all the below options to this
                .setColor(CardColor.RED)
                .addOption("Add Spriggs' card to your hand.", CardTarget.NONE)
                .setColor(CardColor.GREEN)
                .addOption("Add Sam's card to your hand.", CardTarget.NONE)
                .setColor(CardColor.BLUE)
                .addOption("Add Ben's card to your hand.", CardTarget.NONE)
                .setColor(CardColor.PURPLE)
                .addOption("Add Adnan's card to your hand.", CardTarget.NONE)
                .setColor(CardColor.CURSE)
                .addOption("Add Tom's card to your hand.", CardTarget.NONE)
                .create();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        return modal.generateTooltips();
    }

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {
        String chosenBeardedValley;

        switch(i){
            case 0:
                chosenBeardedValley = "Spriggs";
                break;
            case 1:
                chosenBeardedValley = "Sam";
                break;
            case 2:
                chosenBeardedValley = "Ben";
                break;
            case 3:
                chosenBeardedValley = "Adnan";
                break;
            case 4:
                chosenBeardedValley = "Tom";
                break;
            default:
                return;
        }

        AbstractCard c;
        c = CardLibrary.getCard("${GormMod}:"+chosenBeardedValley).makeCopy();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        modal.open();

    }

    @Override
    public AbstractCard makeCopy()
    {
        return new BeardedValley();
    }
}
