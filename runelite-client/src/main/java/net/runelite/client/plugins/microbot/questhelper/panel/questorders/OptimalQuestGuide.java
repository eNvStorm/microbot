/*
 * Copyright (c) 2024, pajlada <https://github.com/pajlada>
 * Copyright (c) 2021, Zoinkwiz
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.microbot.questhelper.panel.questorders;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestHelperQuest;

import java.util.List;

/**
 * The order of these quests are parsed using data from the <a href="https://oldschool.runescape.wiki/w/Optimal_quest_guide">OSRS Wiki</a>
 */
public class OptimalQuestGuide {
    @Getter
    private static final List<QuestHelperQuest> questList = ImmutableList.of(
            QuestHelperQuest.COOKS_ASSISTANT,
            QuestHelperQuest.SHEEP_SHEARER,
            QuestHelperQuest.MISTHALIN_MYSTERY,
            QuestHelperQuest.PRINCE_ALI_RESCUE,
            QuestHelperQuest.THE_RESTLESS_GHOST,
            QuestHelperQuest.RUNE_MYSTERIES,
            QuestHelperQuest.STRONGHOLD_OF_SECURITY,
            QuestHelperQuest.IMP_CATCHER,
            QuestHelperQuest.WITCHS_POTION,
            QuestHelperQuest.GERTRUDES_CAT,
            QuestHelperQuest.CHILDREN_OF_THE_SUN,
            QuestHelperQuest.DADDYS_HOME,
            QuestHelperQuest.DWARF_CANNON,
            QuestHelperQuest.WATERFALL_QUEST,
            QuestHelperQuest.TREE_GNOME_VILLAGE,
            QuestHelperQuest.DORICS_QUEST,
            QuestHelperQuest.WITCHS_HOUSE,
            QuestHelperQuest.THE_KNIGHTS_SWORD,
            QuestHelperQuest.THE_TOURIST_TRAP,
            QuestHelperQuest.BLACK_KNIGHTS_FORTRESS,
            QuestHelperQuest.DRUIDIC_RITUAL,
            QuestHelperQuest.RECRUITMENT_DRIVE,
            QuestHelperQuest.GOBLIN_DIPLOMACY,
            QuestHelperQuest.SLEEPING_GIANTS,
            QuestHelperQuest.FIGHT_ARENA,
            QuestHelperQuest.PLAGUE_CITY,
            QuestHelperQuest.MONKS_FRIEND,
            QuestHelperQuest.HAZEEL_CULT,
            QuestHelperQuest.SHEEP_HERDER,
            QuestHelperQuest.BIOHAZARD,
            QuestHelperQuest.TOWER_OF_LIFE,
            QuestHelperQuest.TRIBAL_TOTEM,
            QuestHelperQuest.DEATH_PLATEAU,
            QuestHelperQuest.MERLINS_CRYSTAL,
            QuestHelperQuest.HOLY_GRAIL,
            QuestHelperQuest.MURDER_MYSTERY,
            QuestHelperQuest.THE_GRAND_TREE,
            QuestHelperQuest.ARDOUGNE_EASY,
            QuestHelperQuest.RAG_AND_BONE_MAN_I,
            QuestHelperQuest.PRIEST_IN_PERIL,
            QuestHelperQuest.NATURE_SPIRIT,
            QuestHelperQuest.GHOSTS_AHOY,
            QuestHelperQuest.MAKING_HISTORY,
            QuestHelperQuest.THE_LOST_TRIBE,
            QuestHelperQuest.DEATH_TO_THE_DORGESHUUN,
            QuestHelperQuest.ELEMENTAL_WORKSHOP_I,
            QuestHelperQuest.ICTHLARINS_LITTLE_HELPER,
            QuestHelperQuest.THE_GOLEM,
            QuestHelperQuest.THE_RIBBITING_TALE_OF_A_LILY_PAD_LABOUR_DISPUTE,
            QuestHelperQuest.LOST_CITY,
            QuestHelperQuest.FAIRYTALE_I__GROWING_PAINS,
            QuestHelperQuest.RECIPE_FOR_DISASTER_START,
            QuestHelperQuest.RECIPE_FOR_DISASTER_WARTFACE_AND_BENTNOZE,
            QuestHelperQuest.SEA_SLUG,
            QuestHelperQuest.FISHING_CONTEST,
            QuestHelperQuest.RECIPE_FOR_DISASTER_DWARF,
            QuestHelperQuest.MOUNTAIN_DAUGHTER,
            QuestHelperQuest.RATCATCHERS,
            QuestHelperQuest.THE_FEUD,
            QuestHelperQuest.DEATH_ON_THE_ISLE,
            QuestHelperQuest.ALFRED_GRIMHANDS_BARCRAWL,
            QuestHelperQuest.SCORPION_CATCHER,
            QuestHelperQuest.THE_DIG_SITE,
            QuestHelperQuest.ELEMENTAL_WORKSHOP_II,
            QuestHelperQuest.A_SOULS_BANE,
            QuestHelperQuest.ENTER_THE_ABYSS,
            QuestHelperQuest.X_MARKS_THE_SPOT,
            QuestHelperQuest.PIRATES_TREASURE,
            QuestHelperQuest.CLIENT_OF_KOUREND,
            QuestHelperQuest.THE_QUEEN_OF_THIEVES,
            QuestHelperQuest.THE_DEPTHS_OF_DESPAIR,
            QuestHelperQuest.A_PORCINE_OF_INTEREST,
            QuestHelperQuest.WANTED,
            QuestHelperQuest.SHIELD_OF_ARRAV_BLACK_ARM_GANG,
            QuestHelperQuest.SHIELD_OF_ARRAV_PHOENIX_GANG,
            QuestHelperQuest.BONE_VOYAGE,
            QuestHelperQuest.WATCHTOWER,
            QuestHelperQuest.THE_GIANT_DWARF,
            QuestHelperQuest.FORGETTABLE_TALE,
            QuestHelperQuest.ANOTHER_SLICE_OF_HAM,
            QuestHelperQuest.VAMPYRE_SLAYER,
            QuestHelperQuest.ERNEST_THE_CHICKEN,
            QuestHelperQuest.DEMON_SLAYER,
            QuestHelperQuest.SHADOW_OF_THE_STORM,
            QuestHelperQuest.ANIMAL_MAGNETISM,
            QuestHelperQuest.CREATURE_OF_FENKENSTRAIN,
            QuestHelperQuest.BIG_CHOMPY_BIRD_HUNTING,
            QuestHelperQuest.JUNGLE_POTION,
            QuestHelperQuest.SHILO_VILLAGE,
            QuestHelperQuest.KARAMJA_EASY,
            QuestHelperQuest.ZOGRE_FLESH_EATERS,
            QuestHelperQuest.OBSERVATORY_QUEST,
            QuestHelperQuest.HORROR_FROM_THE_DEEP,
            QuestHelperQuest.SPIRITS_OF_THE_ELID,
            QuestHelperQuest.GARDEN_OF_TRANQUILLITY,
            QuestHelperQuest.ENLIGHTENED_JOURNEY,
            QuestHelperQuest.BALLOON_TRANSPORT_CRAFTING_GUILD,
            QuestHelperQuest.BALLOON_TRANSPORT_VARROCK,
            QuestHelperQuest.ROMEO__JULIET,
            QuestHelperQuest.TEARS_OF_GUTHIX,
            QuestHelperQuest.IN_SEARCH_OF_THE_MYREQUE,
            QuestHelperQuest.SHADES_OF_MORTTON,
            QuestHelperQuest.IN_AID_OF_THE_MYREQUE,
            QuestHelperQuest.SKIPPY_AND_THE_MOGRES,
            QuestHelperQuest.TROLL_STRONGHOLD,
            QuestHelperQuest.TROLL_ROMANCE,
            QuestHelperQuest.DARKNESS_OF_HALLOWVALE,
            QuestHelperQuest.UNDERGROUND_PASS,
            QuestHelperQuest.REGICIDE,
            QuestHelperQuest.DRAGON_SLAYER_I,
            QuestHelperQuest.THE_FREMENNIK_TRIALS,
            QuestHelperQuest.THE_FREMENNIK_ISLES,
            QuestHelperQuest.RECIPE_FOR_DISASTER_EVIL_DAVE,
            QuestHelperQuest.RECIPE_FOR_DISASTER_PIRATE_PETE,
            QuestHelperQuest.TAI_BWO_WANNAI_TRIO,
            QuestHelperQuest.KANDARIN_EASY,
            QuestHelperQuest.CONTACT,
            QuestHelperQuest.TEMPLE_OF_IKOV,
            QuestHelperQuest.THE_EYES_OF_GLOUPHRIE,
            QuestHelperQuest.TEMPLE_OF_THE_EYE,
            QuestHelperQuest.ONE_SMALL_FAVOUR,
            QuestHelperQuest.THE_ASCENT_OF_ARCEUUS,
            QuestHelperQuest.TALE_OF_THE_RIGHTEOUS,
            QuestHelperQuest.DESERT_EASY,
            QuestHelperQuest.FALADOR_EASY,
            QuestHelperQuest.FREMENNIK_EASY,
            QuestHelperQuest.KOUREND_EASY,
            QuestHelperQuest.LUMBRIDGE_EASY,
            QuestHelperQuest.MORYTANIA_EASY,
            QuestHelperQuest.VARROCK_EASY,
            QuestHelperQuest.WESTERN_EASY,
            QuestHelperQuest.WILDERNESS_EASY,
            QuestHelperQuest.BETWEEN_A_ROCK,
            QuestHelperQuest.THE_FORSAKEN_TOWER,
            QuestHelperQuest.THE_SLUG_MENACE,
            QuestHelperQuest.GETTING_AHEAD,
            QuestHelperQuest.COLD_WAR,
            QuestHelperQuest.THE_HAND_IN_THE_SAND,
            QuestHelperQuest.ENAKHRAS_LAMENT,
            QuestHelperQuest.EADGARS_RUSE,
            QuestHelperQuest.MY_ARMS_BIG_ADVENTURE,
            QuestHelperQuest.THE_GARDEN_OF_DEATH,
            QuestHelperQuest.RAG_AND_BONE_MAN_II,
            QuestHelperQuest.RUM_DEAL,
            QuestHelperQuest.CABIN_FEVER,
            QuestHelperQuest.MEAT_AND_GREET,
            QuestHelperQuest.RECIPE_FOR_DISASTER_LUMBRIDGE_GUIDE,
            QuestHelperQuest.RECIPE_FOR_DISASTER_SKRACH_UGLOGWEE,
            QuestHelperQuest.HEROES_QUEST,
            QuestHelperQuest.THRONE_OF_MISCELLANIA,
            QuestHelperQuest.ROYAL_TROUBLE,
            QuestHelperQuest.HAUNTED_MINE,
            QuestHelperQuest.LAIR_OF_TARN_RAZORLOR,
            QuestHelperQuest.MONKEY_MADNESS_I,
            QuestHelperQuest.ETHICALLY_ACQUIRED_ANTIQUITIES,
            QuestHelperQuest.ROVING_ELVES,
            QuestHelperQuest.MOURNINGS_END_PART_I,
            QuestHelperQuest.MOURNINGS_END_PART_II,
            QuestHelperQuest.DESERT_TREASURE,
            QuestHelperQuest.FAMILY_CREST,
            QuestHelperQuest.WHAT_LIES_BELOW,
            QuestHelperQuest.EAGLES_PEAK,
            QuestHelperQuest.A_TAIL_OF_TWO_CATS,
            QuestHelperQuest.LEGENDS_QUEST,
            QuestHelperQuest.LAND_OF_THE_GOBLINS,
            QuestHelperQuest.RECIPE_FOR_DISASTER_SIR_AMIK_VARZE,
            QuestHelperQuest.OLAFS_QUEST,
            QuestHelperQuest.A_KINGDOM_DIVIDED,
            QuestHelperQuest.A_TASTE_OF_HOPE,
            QuestHelperQuest.AT_FIRST_LIGHT,
            QuestHelperQuest.BALLOON_TRANSPORT_CASTLE_WARS,
            QuestHelperQuest.ARDOUGNE_MEDIUM,
            QuestHelperQuest.DESERT_MEDIUM,
            QuestHelperQuest.FALADOR_MEDIUM,
            QuestHelperQuest.FREMENNIK_MEDIUM,
            QuestHelperQuest.KANDARIN_MEDIUM,
            QuestHelperQuest.KOUREND_MEDIUM,
            QuestHelperQuest.LUMBRIDGE_MEDIUM,
            QuestHelperQuest.MORYTANIA_MEDIUM,
            QuestHelperQuest.VARROCK_MEDIUM,
            QuestHelperQuest.WESTERN_MEDIUM,
            QuestHelperQuest.CURSE_OF_THE_EMPTY_LORD,
            QuestHelperQuest.THE_GENERALS_SHADOW,
            QuestHelperQuest.HIS_FAITHFUL_SERVANTS,
            QuestHelperQuest.THE_GREAT_BRAIN_ROBBERY,
            QuestHelperQuest.FAIRYTALE_II__CURE_A_QUEEN,
            QuestHelperQuest.RECIPE_FOR_DISASTER_MONKEY_AMBASSADOR,
            QuestHelperQuest.RECIPE_FOR_DISASTER_FINALE,
            QuestHelperQuest.TWILIGHTS_PROMISE,
            QuestHelperQuest.PERILOUS_MOON,
            QuestHelperQuest.THE_PATH_OF_GLOUPHRIE,
            QuestHelperQuest.THE_HEART_OF_DARKNESS,
            QuestHelperQuest.LUNAR_DIPLOMACY,
            QuestHelperQuest.KINGS_RANSOM,
            QuestHelperQuest.KNIGHT_WAVES_TRAINING_GROUNDS,
            QuestHelperQuest.SWAN_SONG,
            QuestHelperQuest.BELOW_ICE_MOUNTAIN,
            QuestHelperQuest.DEFENDER_OF_VARROCK,
            QuestHelperQuest.DEVIOUS_MINDS,
            QuestHelperQuest.GRIM_TALES,
            QuestHelperQuest.DREAM_MENTOR,
            QuestHelperQuest.KARAMJA_MEDIUM,
            QuestHelperQuest.WILDERNESS_MEDIUM,
            QuestHelperQuest.THE_FREMENNIK_EXILES,
            QuestHelperQuest.SINS_OF_THE_FATHER,
            QuestHelperQuest.BALLOON_TRANSPORT_GRAND_TREE,
            QuestHelperQuest.IN_SEARCH_OF_KNOWLEDGE,
            QuestHelperQuest.HOPESPEARS_WILL,
            QuestHelperQuest.BENEATH_CURSED_SANDS,
            QuestHelperQuest.MONKEY_MADNESS_II,
            //QuestHelperQuest.INTO_THE_TOMBS, - Placeholder for future addition.
            QuestHelperQuest.A_NIGHT_AT_THE_THEATRE,
            QuestHelperQuest.DRAGON_SLAYER_II,
            QuestHelperQuest.MAKING_FRIENDS_WITH_MY_ARM,
            QuestHelperQuest.SECRETS_OF_THE_NORTH,
            QuestHelperQuest.WHILE_GUTHIX_SLEEPS,
            QuestHelperQuest.DESERT_TREASURE_II,
            QuestHelperQuest.SONG_OF_THE_ELVES,
            QuestHelperQuest.CLOCK_TOWER,
            QuestHelperQuest.THE_CORSAIR_CURSE,
            // Quests & mini quests that are not part of the OSRS Wiki's Optimal Quest Guide
            QuestHelperQuest.BARBARIAN_TRAINING,
            QuestHelperQuest.ENCHANTED_KEY,
            QuestHelperQuest.BEAR_YOUR_SOUL,
            QuestHelperQuest.FAMILY_PEST,
            QuestHelperQuest.THE_MAGE_ARENA,
            QuestHelperQuest.THE_MAGE_ARENA_II,
            QuestHelperQuest.ARDOUGNE_HARD,
            QuestHelperQuest.DESERT_HARD,
            QuestHelperQuest.FALADOR_HARD,
            QuestHelperQuest.FREMENNIK_HARD,
            QuestHelperQuest.KANDARIN_HARD,
            QuestHelperQuest.KARAMJA_HARD,
            QuestHelperQuest.KOUREND_HARD,
            QuestHelperQuest.LUMBRIDGE_HARD,
            QuestHelperQuest.MORYTANIA_HARD,
            QuestHelperQuest.VARROCK_HARD,
            QuestHelperQuest.WESTERN_HARD,
            QuestHelperQuest.WILDERNESS_HARD,
            QuestHelperQuest.ARDOUGNE_ELITE,
            QuestHelperQuest.DESERT_ELITE,
            QuestHelperQuest.FALADOR_ELITE,
            QuestHelperQuest.FREMENNIK_ELITE,
            QuestHelperQuest.KANDARIN_ELITE,
            QuestHelperQuest.KARAMJA_ELITE,
            QuestHelperQuest.KOUREND_ELITE,
            QuestHelperQuest.LUMBRIDGE_ELITE,
            QuestHelperQuest.MORYTANIA_ELITE,
            QuestHelperQuest.VARROCK_ELITE,
            QuestHelperQuest.WESTERN_ELITE,
            QuestHelperQuest.WILDERNESS_ELITE
    );
}
