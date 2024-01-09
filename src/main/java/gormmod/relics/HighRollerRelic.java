package gormmod.relics;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static gormmod.GormMod.makeID;

@AutoAdd.Seen
public class HighRollerRelic extends BaseRelic {
    private static final String NAME = "HighRollerRelic";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final float ROLL_CHANCE = 0.8f;

    public HighRollerRelic() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {

        String successPercentage = String.format("%d%%", (int) (ROLL_CHANCE * 100));
        String failPercentage = String.format("%d%%", (int) ((1.01 - ROLL_CHANCE) * 100));

        return String.format(DESCRIPTIONS[0], successPercentage) + DESCRIPTIONS[1] +  String.format(DESCRIPTIONS[2], failPercentage);
    }

    @Override
    public void onChestOpenAfter(boolean bossChest){

        ArrayList<RelicTier> relics = new ArrayList<>();
        relics.add(RelicTier.COMMON);
        relics.add(RelicTier.UNCOMMON);
        relics.add(RelicTier.RARE);

        int randomIndex = ThreadLocalRandom.current().nextInt(0, relics.size());

        if(!bossChest) {
            if (AbstractDungeon.relicRng.randomBoolean(ROLL_CHANCE)) {
                CombatRewardScreen.TEXT[7] = "Crabtastic Roll!";
                AbstractDungeon.getCurrRoom().addRelicToRewards(relics.get(randomIndex));
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            } else {
                CombatRewardScreen.TEXT[7] = "That's some crabby luck!";
                AbstractDungeon.getCurrRoom().removeOneRelicFromRewards();
            }
        }
    }
}


