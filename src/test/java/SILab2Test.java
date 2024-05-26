import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void testCheckCart_InvalidBarcodeCharacter_ThrowsException() {
        List<Item> invalidBarcodeItemList = new ArrayList<>();
        invalidBarcodeItemList.add(new Item("InvalidItem", "12a345", 100, 0));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(invalidBarcodeItemList, 100);
        });

        assertEquals("Invalid character in item barcode!", exception.getMessage());
    }

    @Test
    void testCheckCart_NoBarcode_ThrowsException() {
        List<Item> noBarcodeItemList = new ArrayList<>();
        noBarcodeItemList.add(new Item("NoBarcodeItem", null, 100, 0));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(noBarcodeItemList, 100);
        });

        assertEquals("No barcode!", exception.getMessage());
    }

    @Test
    void testCheckCart_NullItemList_ThrowsException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, 100);
        });
        assertEquals("allItems list can't be null!", exception.getMessage());
    }

    @Test
    void testCheckCart_EmptyItemList_ReturnsTrue() {
        List<Item> emptyItemList = new ArrayList<>();
        assertTrue(SILab2.checkCart(emptyItemList, 10));
    }

    @Test
    void testCheckCart_EmptyItemListNegativePayment_ReturnsFalse() {
        List<Item> emptyItemList = new ArrayList<>();
        assertFalse(SILab2.checkCart(emptyItemList, -15));
    }

    @Test
    void testCheckCart_ValidItemList_PaymentLessThanTotal_ReturnsFalse() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("ValidItem", "012345", 500, 0.2f));
        assertFalse(SILab2.checkCart(itemList, 50));
    }

    @Test
    void testCheckCart_ValidItemList_PaymentGreaterThanTotal_ReturnsTrue() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("ValidItem", "012345", 500, 0.2f));
        assertTrue(SILab2.checkCart(itemList, 500));
    }

    @Test
    void testCheckCart_ItemWithHighPriceAndDiscount_ReturnsTrue() {
        List<Item> highPriceDiscountedItemList = new ArrayList<>();
        highPriceDiscountedItemList.add(new Item("HighPriceDiscountedItem", "012345", 400, 0.1f));
        assertTrue(SILab2.checkCart(highPriceDiscountedItemList, 370));
    }

    @Test
    void testCheckCart_ItemWithHighPriceNoDiscount_ReturnsFalse() {
        List<Item> highPriceNoDiscountItemList = new ArrayList<>();
        highPriceNoDiscountItemList.add(new Item("HighPriceNoDiscountItem", "012345", 400, 0));
        assertFalse(SILab2.checkCart(highPriceNoDiscountItemList, 370));
    }
}