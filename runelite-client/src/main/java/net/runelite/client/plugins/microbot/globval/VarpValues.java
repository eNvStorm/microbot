package net.runelite.client.plugins.microbot.globval;

import java.util.HashMap;
import java.util.Map;

public enum VarpValues {
    // Quest Clock Tower
    QUEST_CLOCK_TOWER_NOT_STARTED(0),
    QUEST_CLOCK_TOWER_STARTED(1),
    // TODO: states 2 - 6
    QUEST_CLOCK_TOWER_FINISHED(8),
    // Music play option
    MUSIC_PLAY_OPTION_MAN(0),
    MUSIC_PLAY_OPTION_AUTO(1),
    // Music looping
    MUSIC_LOOPING_DISABLED(0),
    MUSIC_LOOPING_ENABLED(1),
    // Quest Cook Assistant
    QUEST_COOK_ASSISTANT_NOT_STARTED(0),
    QUEST_COOK_ASSISTANT_STARTED(1),
    QUEST_COOK_ASSISTANT_FINISHED(2),
    // Quest Monks Friend
    QUEST_MONKS_FRIEND_NOT_STARTED(0),
    QUEST_MONKS_FRIEND_STARTED(10),
    // TODO: states 20 - 70
    QUEST_MONKS_FRIEND_FINISHED(80),
    // Quest Doric's Quest
    QUEST_DORICS_QUEST_NOT_STARTED(0),
    QUEST_DORICS_QUEST_STARTED(10),
    QUEST_DORICS_QUEST_FINISHED(100),
    // Quest Earnest The Chicken
    QUEST_EARNEST_THE_CHICKEN_NOT_STARTED(0),
    QUEST_EARNEST_THE_CHICKEN_STARTED(1),
    QUEST_EARNEST_THE_CHICKEN_TALKED_TO_PROF(2),
    QUEST_EARNEST_THE_CHICKEN_FINISHED(3),
    // Combat Style
    COMBAT_STYLE_FIRST(0),
    COMBAT_STYLE_SECOND(1),
    COMBAT_STYLE_THIRD(2),
    COMBAT_STYLE_FOURTH(3),
    COMBAT_STYLE_AUTOCAST(4),
    // Quest Sheep Herder
    QUEST_SHEEP_HERDER_NOT_STARTED(0),
    QUEST_SHEEP_HERDER_STARTED(1),
    // TODO: states 2
    QUEST_SHEEP_HERDER_FINISHED(3),
    // Quest Rune Mysteries
    QUEST_RUNE_MYSTERIES_NOT_STARTED(0),
    QUEST_RUNE_MYSTERIES_STARTED(1),
    QUEST_RUNE_MYSTERIES_TALISMAN_GIVEN_SERIDOR(2),
    QUEST_RUNE_MYSTERIES_TOOK_PACKAGE(3),
    QUEST_RUNE_MYSTERIES_PACKAGE_GIVEN_AUBURY(4),
    QUEST_RUNE_MYSTERIES_TOOK_NOTES(5),
    QUEST_RUNE_MYSTERIES_FINISHED(6),
    // Quest Waterfall Quest
    QUEST_WATERFALL_QUEST_NOT_STARTED(0),
    QUEST_WATERFALL_QUEST_STARTED(1),
    // TODO: states 2 - 8
    QUEST_WATERFALL_QUEST_FINISHED(10),
    // Quest points
    QUEST_POINTS_NONE(0),
    QUEST_POINTS_MAX(287),
    // Restless Ghost
    QUEST_RESTLESS_GHOST_NOT_STARTED(0),
    QUEST_RESTLESS_GHOST_STARTED(1),
    QUEST_RESTLESS_AMULET_GIVEN(2),
    QUEST_RESTLESS_TALKED_TO_GHOST(3),
    QUEST_RESTLESS_SKULL_OBTAINED(4),
    QUEST_RESTLESS_GHOST_FINISHED(5),
    // Selected autocast spell
    SELECTED_SPELL_WIND_STRIKE(3),
    SELECTED_SPELL_WATER_STRIKE(5),
    SELECTED_SPELL_EARTH_STRIKE(7),
    SELECTED_SPELL_FIRE_STRIKE(9),
    // Bank withdraw mode
    BANK_WITHDRAW_MODE_ITEM(0),
    BANK_WITHDRAW_MODE_NOTE(1),
    // Quest the knights sword
    QUEST_THE_KNIGHTS_SWORD_NOT_STARTED(0),
    QUEST_THE_KNIGHTS_SWORD_STARTED(1),
    QUEST_THE_KNIGHTS_SWORD_TALKED_TO_RELDO(2),
    QUEST_THE_KNIGHTS_SWORD_PIE_GIVEN_TO_THURGO(3),
    QUEST_THE_KNIGHTS_SWORD_THURGO_REQUESTED_PORTRAIT(4),
    QUEST_THE_KNIGHTS_SWORD_SQUIRE_TALKED_TO_2ND_TIME(5),
    QUEST_THE_KNIGHTS_SWORD_PORTRAIT_GIVEN_TO_THURGO(6),
    QUEST_THE_KNIGHTS_SWORD_FINISHED(7),
    // Black knights fortress
    QUEST_BLACK_KNIGHTS_FORTRESS_NOT_STARTED(0),
    QUEST_BLACK_KNIGHTS_FORTRESS_STARTED(1),
    QUEST_BLACK_KNIGHTS_FORTRESS_LISTENED_AT_GRILL(2),
    QUEST_BLACK_KNIGHTS_FORTRESS_CABBAGE_THROWN(3),
    QUEST_BLACK_KNIGHTS_FORTRESS_FINISHED(4),
    // Romeo and Juliet
    QUEST_ROMEO_AND_JULIET_NOT_STARTED(0),
    QUEST_ROMEO_AND_JULIET_STARTED(10),
    QUEST_ROMEO_AND_JULIET_LETTER_GIVEN_BY_JULIET(20),
    QUEST_ROMEO_AND_JULIET_LETTER_TAKEN_TO_ROMEO(30),
    QUEST_ROMEO_AND_JULIET_TALKED_TO_LAWRENCE(40),
    QUEST_ROMEO_AND_JULIET_TALKED_TO_APOTHECARY(50),
    QUEST_ROMEO_AND_JULIET_POTION_GIVEN_TO_JULIET(60),
    QUEST_ROMEO_AND_JULIET_FINISHED(100),
    // Imp catcher
    QUEST_IMP_CATCHER_NOT_STARTED(0),
    QUEST_IMP_CATCHER_STARTED(1),
    QUEST_IMP_CATCHER_FINISHED(2),
    // Screen brightness
    SCREEN_BRIGHTNESS_MIN(0),
    SCREEN_BRIGHTNESS_MAX(100),
    // Music volume
    MUSIC_VOLUME_MIN(0),
    MUSIC_VOLUME_MAX(100),
    // Sound effects
    SOUND_EFFECTS_VOLUME_MIN(0),
    SOUND_EFFECTS_VOLUME_MAX(100),
    // Auto retaliate
    AUTO_RETALIATE_ENABLED(0),
    AUTO_RETALIATE_DISABLED(1),
    // Toggle run
    RUN_DISABLED(0),
    RUN_ENABLED(1),
    // Vampyre slayer
    QUEST_VAMPYRE_SLAYER_NOT_STARTED(0),
    QUEST_VAMPYRE_SLAYER_STARTED(1),
    QUEST_VAMPYRE_SLAYER_TALKED_TO_DR_HALLOW(2),
    QUEST_VAMPYRE_SLAYER_FINISHED(3),
    // Sheep shearer
    QUEST_SHEEP_SHEARER_NOT_STARTED(0),
    QUEST_SHEEP_SHEARER_STARTED(1),
    QUEST_SHEEP_SHEARER_1_BALL_OF_WOOL_GIVEN(2),
    QUEST_SHEEP_SHEARER_2_BALL_OF_WOOL_GIVEN(3),
    QUEST_SHEEP_SHEARER_3_BALL_OF_WOOL_GIVEN(4),
    QUEST_SHEEP_SHEARER_4_BALL_OF_WOOL_GIVEN(5),
    QUEST_SHEEP_SHEARER_5_BALL_OF_WOOL_GIVEN(6),
    QUEST_SHEEP_SHEARER_6_BALL_OF_WOOL_GIVEN(7),
    QUEST_SHEEP_SHEARER_7_BALL_OF_WOOL_GIVEN(8),
    QUEST_SHEEP_SHEARER_8_BALL_OF_WOOL_GIVEN(9),
    QUEST_SHEEP_SHEARER_9_BALL_OF_WOOL_GIVEN(10),
    QUEST_SHEEP_SHEARER_10_BALL_OF_WOOL_GIVEN(11),
    QUEST_SHEEP_SHEARER_11_BALL_OF_WOOL_GIVEN(12),
    QUEST_SHEEP_SHEARER_12_BALL_OF_WOOL_GIVEN(13),
    QUEST_SHEEP_SHEARER_13_BALL_OF_WOOL_GIVEN(14),
    QUEST_SHEEP_SHEARER_14_BALL_OF_WOOL_GIVEN(15),
    QUEST_SHEEP_SHEARER_15_BALL_OF_WOOL_GIVEN(16),
    QUEST_SHEEP_SHEARER_17_BALL_OF_WOOL_GIVEN(18),
    QUEST_SHEEP_SHEARER_18_BALL_OF_WOOL_GIVEN(19),
    QUEST_SHEEP_SHEARER_19_BALL_OF_WOOL_GIVEN(20),
    QUEST_SHEEP_SHEARER_FINISHED(21),
    // Prince Ali rescue
    QUEST_PRINCE_ALI_RESCUE_NOT_STARTED(0),
    QUEST_PRINCE_ALI_RESCUE_STARTED(10),
    QUEST_PRINCE_ALI_RESCUE_TALKED_TO_OSMAN(20),
    QUEST_PRINCE_ALI_RESCUE_LEELA_GAVE_KEY(30),
    QUEST_PRINCE_ALI_RESCUE_FIRST_BEER_GIVEN_GUARD(31),
    QUEST_PRINCE_ALI_RESCUE_SECOND_BEER_GIVEN_GUARD(40),
    QUEST_PRINCE_ALI_RESCUE_LADY_KELI_PUT_IN_CUPBOARD(50),
    QUEST_PRINCE_ALI_RESCUE_DISGUISE_GIVEN_ALI(100),
    QUEST_PRINCE_ALI_RESCUE_FINISHED(110),
    // Special attack energy
    SPECIAL_ATTACK_ENERGY_MIN(0),
    SPECIAL_ATTACK_ENERGY_MAX(100),
    // Special attack
    SPECIAL_ATTACK_DISABLED(0),
    SPECIAL_ATTACK_ENABLED(1),
    // Bank rearrange mode
    BANK_REARRANGE_MODE_SWAP(0),
    BANK_REARRANGE_MODE_INSERT(1),
    // Accept aid
    ACCEPT_AID_DISABLED(0),
    ACCEPT_AID_ENABLED(1),
    // Active Magic Book
    MAGIC_BOOK_STANDARD(0),
    // Area sound effects
    AREA_SOUND_EFFECT_VOLUME_MIN(0),
    AREA_SOUND_EFFECT_VOLUME_MAX(100),
    // Blast furnace bar dispenser
    BAR_DISPENSER_NO_BAR_PROCESSING(0),
    BAR_DISPENSER_ORE_PROCESSING(1),
    BAR_DISPENSER_BARS_COOLING_DOWN(2),
    BAR_DISPENSER_BARS_READY_FOR_COLLECTION(3);

    // cache values on load
    private static final Map<Object, Object> hashMap = new HashMap<>();
    static {
        for (VarpValues varpEnum : VarpValues.values()) {
            hashMap.put(varpEnum.value, varpEnum);
        }
    }

    private final int value;

    VarpValues(int value) {
        this.value = value;
    }

    public static VarpValues valueOf(int varpEnum) {
        return (VarpValues) hashMap.get(varpEnum);
    }

    public int getValue() {
        return value;
    }
}