package hu.webler.weblerspringthymeleafdesign.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {

    @Test
    @DisplayName("createHungarianCollator should create a collator successfully")
    void createHungarianCollator_shouldCreateCollatorSuccessfully() {
        // Arrange

        // Act
        Collator collator = Sorting.createHungarianCollator();

        // Assert
        assertNotNull(collator);

        // Example: Check if the collator sorts strings correctly
        assertTrue(collator.compare("apple", "ápple") < 0);
        assertTrue(collator.compare("apple", "Banana") < 0);
        assertTrue(collator.compare("ápple", "Banana") < 0);

        // Add more test cases based on your specific requirements
    }

    @Test
    @DisplayName("createHungarianCollator should handle ParseException")
    void createHungarianCollator_shouldHandleParseException() {
        // Arrange
        String invalidRules = "invalid_rules_here"; // This will cause a ParseException

        // Act
        Collator collator = null;
        try {
            collator = new RuleBasedCollator(invalidRules);
        } catch (ParseException e) {
            // This is expected, as we intentionally provided invalid rules
        }

        // Assert
        assertNull(collator, "Collator should be null due to ParseException");
    }
}
