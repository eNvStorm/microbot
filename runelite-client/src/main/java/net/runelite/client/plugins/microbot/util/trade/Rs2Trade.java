package net.runelite.client.plugins.microbot.util.trade;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.widgets.Widget;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;

import java.util.function.Predicate;

@Slf4j
public class Rs2Trade {/*

    public enum Screen {
        FIRST,
        SECOND
    }

    public enum Party {
        ME,
        THEM
    }

    private static final int MAX_INVENTORY_SLOTS = 28;

    private static final int FIRST_TRADE_WINDOW_GROUP = 335;
    private static final int SECOND_TRADE_WINDOW_GROUP = 334;

    private static final String FIRST_SCREEN_MY_OFFER_PRICE_PREFIX_1 = "Your offer:";
    private static final String FIRST_SCREEN_MY_OFFER_PRICE_PREFIX_2 = "You offer:";

    private static final int FIRST_TRADE_WINDOW_PARENT_INDEX = 335;
    private static final int SECOND_TRADE_WINDOW_PARENT_INDEX = 334;

    public static boolean isOpen() {
        return isOpen(Rs2Trade.Screen.FIRST) || isOpen(Rs2Trade.Screen.SECOND);
    }

    public static boolean isOpen(Rs2Trade.Screen screen) {
        if (screen == Rs2Trade.Screen.FIRST) {
            return Rs2Widget.isWidgetVisible(FIRST_TRADE_WINDOW_GROUP, 1);
        }
        else if (screen == Rs2Trade.Screen.SECOND) {
            return Rs2Widget.isWidgetVisible(SECOND_TRADE_WINDOW_GROUP, 1);
        }
        return false;
    }


    private static final int HAS_ACCEPTED_FIRST_SCREEN_CHILD_INDEX = 30;
    private static final int HAS_ACCEPTED_SECOND_SCREEN_CHILD_INDEX = 4;
    private static final String I_ACCEPTED_MESSAGE = "Waiting for other player";
    private static final String THEY_ACCEPTED_MESSAGE = "Other player has accepted";

    public static boolean hasAccepted(Rs2Trade.Party party) {
        if (!isOpen()) {
            return false;
        }

        int tradeWindowWidgetIndex;
        int hasAcceptedWidgetChildIndex;

        if (isOpen(Rs2Trade.Screen.FIRST)) {
            tradeWindowWidgetIndex = FIRST_TRADE_WINDOW_PARENT_INDEX;
            hasAcceptedWidgetChildIndex = HAS_ACCEPTED_FIRST_SCREEN_CHILD_INDEX;
        } else {
            tradeWindowWidgetIndex = SECOND_TRADE_WINDOW_PARENT_INDEX;
            hasAcceptedWidgetChildIndex = HAS_ACCEPTED_SECOND_SCREEN_CHILD_INDEX;
        }
        String acceptedMessage = party == Rs2Trade.Party.THEM ? THEY_ACCEPTED_MESSAGE : I_ACCEPTED_MESSAGE;

        System.out.println("Trade window widget index: " + tradeWindowWidgetIndex);
        System.out.println("Has accepted widget child index: " + hasAcceptedWidgetChildIndex);
        //Widget acceptedMessageWidget = Rs2Widget.getWidget(tradeWindowWidgetIndex, hasAcceptedWidgetChildIndex);
        Widget acceptedMessageWidget = Microbot.getClient().getWidget(tradeWindowWidgetIndex, hasAcceptedWidgetChildIndex);
        System.out.println("We made it past line 72");
        if (acceptedMessageWidget == null || Microbot.getClientThread().runOnClientThread(() -> acceptedMessageWidget.isHidden())) {
            System.out.println("Can't find message");
            return false;
        }
        if(acceptedMessageWidget.getText()==null){
            System.out.println("we get to 81");
            return false;
        }
        System.out.println("Accepted message: " + acceptedMessageWidget.getText());
        return acceptedMessageWidget.getText().startsWith(acceptedMessage);
    }

    private static final String FIRST_SCREEN_TRADER_NAME_PREFIX = "Trading With: ";
    private static final Predicate<Widget> FIRST_SCREEN_TRADER_NAME = comp ->
            comp.getParentId() == FIRST_TRADE_WINDOW_GROUP
                    && comp.getText().startsWith(FIRST_SCREEN_TRADER_NAME_PREFIX);

    private static final String SECOND_SCREEN_TRADER_NAME_PREFIX = "Trading with:";
    private static final Predicate<Widget> SECOND_SCREEN_TRADER_NAME = comp ->
            comp.getParentId() == SECOND_TRADE_WINDOW_GROUP
                    && comp.getText().startsWith(SECOND_SCREEN_TRADER_NAME_PREFIX);

    public static String getTheirName() {
        Widget traderNameComp = isOpen(Screen.FIRST) ?
                Rs2Widget.getMatchingWidget(FIRST_SCREEN_TRADER_NAME)
                : Rs2Widget.getMatchingWidget(SECOND_SCREEN_TRADER_NAME);
        String prefix = isOpen(Screen.FIRST) ? FIRST_SCREEN_TRADER_NAME_PREFIX : SECOND_SCREEN_TRADER_NAME_PREFIX;
        if (traderNameComp == null || traderNameComp.isHidden()) {
            return null;
        }
        return sanitize(traderNameComp.getText(), prefix);
    }

    private static final Predicate<Widget> FIRST_SCREEN_THEIR_OFFER_PRICE = comp ->
            comp.getParentId() == FIRST_TRADE_WINDOW_GROUP
                    && replaceJagspace(comp.getText()).startsWith(getTheirName() + " offers:");

    private static final String SECOND_SCREEN_MY_OFFER_PRICE_PREFIX = "You are about to give:";
    private static final Predicate<Widget> SECOND_SCREEN_MY_OFFER_PRICE = comp ->
            comp.getParentId() == SECOND_TRADE_WINDOW_GROUP
                    && replaceJagspace(comp.getText()).startsWith(SECOND_SCREEN_MY_OFFER_PRICE_PREFIX);

    private static final String SECOND_SCREEN_THEIR_OFFER_PRICE_PREFIX = "In return you will receive:";
    private static final Predicate<Widget> SECOND_SCREEN_THEIR_OFFER_PRICE = comp ->
            comp.getParentId() == SECOND_TRADE_WINDOW_GROUP
                    && replaceJagspace(comp.getText()).startsWith(SECOND_SCREEN_THEIR_OFFER_PRICE_PREFIX);

    private static final String[] FIRST_SCREEN_MY_OFFER_PRICE_PREFIXES = new String[] {"Your offer", "You offer"};
    private static final Predicate<Widget> FIRST_SCREEN_MY_OFFER_PRICE = comp -> {
        if (comp.getParentId() != FIRST_TRADE_WINDOW_GROUP) {
            return false;
        }
        for (String prefix : FIRST_SCREEN_MY_OFFER_PRICE_PREFIXES) {
            String text = replaceJagspace(comp.getText());
            if (text == null) {
                continue;
            }
            if (text.contains(prefix)) {
                return true;
            }
        }
        return false;
    };

    public static int getOfferPrice(Rs2Trade.Party party) {
        Widget priceComp = null;
        if (isOpen(Rs2Trade.Screen.FIRST)) {
            priceComp = party == Rs2Trade.Party.ME ?
                    Rs2Widget.getMatchingWidget(FIRST_SCREEN_MY_OFFER_PRICE)
                    : Rs2Widget.getMatchingWidget(FIRST_SCREEN_THEIR_OFFER_PRICE);
        }
        if (isOpen(Rs2Trade.Screen.SECOND)) {
            priceComp = party == Rs2Trade.Party.ME  ?
                    Rs2Widget.getMatchingWidget(SECOND_SCREEN_MY_OFFER_PRICE)
                    : Rs2Widget.getMatchingWidget(SECOND_SCREEN_THEIR_OFFER_PRICE);
        }
        if (priceComp != null && !priceComp.isHidden()) {
            String priceText = sanitize(priceComp.getText());
            int startingIndex = priceText.indexOf(':');
            log.info("PriceText: " + priceText);
            if (priceText.toLowerCase().contains("value: one")) {
                return 1;
            } else if (startingIndex >= 0) {
                return extractNumber(priceText.substring(priceText.indexOf(':')));
            }
        }
        return -1;
    }

    public static boolean isWaitingForMe() {
        return hasAccepted(Rs2Trade.Party.THEM) && !hasAccepted(Rs2Trade.Party.ME);
    }


    private static String sanitize(String text) {
        return sanitize(text, "");
    }

    private static String sanitize(String text, String prefix) {
        return text.replaceAll(prefix, "").trim()
                .replaceAll("(<col=[0-9a-f]+>|</col>|<br>)", "")
                .replace('\u00A0', ' ');
    }

    private static String replaceJagspace(String text) {
        if (text == null) {
            return null;
        }
        return text.replace('\u00A0', ' ');
    }

    public static int extractNumber(String input) {
        String numberString = input.replaceAll("[^\\d,]", "");
        numberString = numberString.replace(",", "");
        return Integer.parseInt(numberString);
    }*/
}
