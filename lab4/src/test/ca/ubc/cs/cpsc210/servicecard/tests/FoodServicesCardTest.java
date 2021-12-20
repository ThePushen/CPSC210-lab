package ca.ubc.cs.cpsc210.servicecard.tests;

import ca.ubc.cs.cpsc210.servicecard.model.FoodServicesCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Unit tests for FoodServiceCard class
class FoodServicesCardTest {
    public static int BAL = 10000;
    private FoodServicesCard card;

    @BeforeEach
    public void runBefore() {
        card = new FoodServicesCard(BAL);
        FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK = 2000;
        FoodServicesCard.CASH_BACK_REWARD = 10;
        FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED = 1;
    }

    @Test
    public void testServicesCard() {
        assertEquals(BAL, card.getBalance());
        assertEquals(0, card.getRewardPoints());
    }

    @Test
    public void testReload() {
        int ADD_AMOUNT = 100;

        card.reload(ADD_AMOUNT);
        assertEquals(BAL + ADD_AMOUNT, card.getBalance());
    }

    @Test
    public void testMultiReload(){
        int ADD1 = 50;
        int ADD_TIMES = 10;

        for(int i=0; i< ADD_TIMES; i++){
            card.reload(ADD1);
        }

        assertEquals(card.getBalance(), BAL + ADD1 * ADD_TIMES);
    }


    @Test
    public void testMakePurchase() {

        // balance > purchase
        // payment success
        // enough points for 1 time cashback
        // adding remaining rewardpoints after cashback
        card = new FoodServicesCard(BAL);
        assertTrue(card.makePurchase(3000));
        assertEquals(BAL - 3000 + 3000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED /
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK * FoodServicesCard.CASH_BACK_REWARD, card.getBalance());
        assertEquals(3000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED - 3000 *
                FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED / FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK *
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK, card.getRewardPoints());

        // balance > purchase
        // payment success
        // more than 1 time cashback
        // adding remaining rewardpoints after cashback
        card = new FoodServicesCard(BAL);
        assertTrue(card.makePurchase(5000));
        assertEquals(BAL - 5000 + 5000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED /
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK * FoodServicesCard.CASH_BACK_REWARD, card.getBalance());
        assertEquals(5000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED - 5000 *
                FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED / FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK *
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK, card.getRewardPoints());

        // balance > purchase
        // payment success
        // no cashback
        // adding remaining rewardpoints
        card = new FoodServicesCard(BAL);
        assertTrue(card.makePurchase(1000));
        assertEquals(BAL - 1000 + 1000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED /
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK * FoodServicesCard.CASH_BACK_REWARD, card.getBalance());
        assertEquals(1000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED - 1000 *
                FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED / FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK *
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK, card.getRewardPoints());

        // balance > purchase
        // payment success
        // no cashback
        // no rewardpoints
        card = new FoodServicesCard(1000);
        assertTrue(card.makePurchase(500));
        assertEquals(1000 - 500 + 500 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED /
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK * FoodServicesCard.CASH_BACK_REWARD, card.getBalance());
        assertEquals(500 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED - 500 *
                FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED / FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK *
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK, card.getRewardPoints());

        // purchase = balance
        // payment success
        // more than 1 time cashback
        // no rewardpoints
        card = new FoodServicesCard(BAL);
        assertTrue(card.makePurchase(10000));
        assertEquals(BAL - 10000 + 10000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED /
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK * FoodServicesCard.CASH_BACK_REWARD, card.getBalance());
        assertEquals(10000 * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED - 10000 *
                FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED / FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK *
                FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK, card.getRewardPoints());

        // balance < purchase
        // payment fail
        // no cashback
        // no rewardpoints
        card = new FoodServicesCard(BAL);
        assertFalse(card.makePurchase(12000));
        assertEquals(BAL, card.getBalance());
        assertEquals(0, card.getRewardPoints());

    }
}