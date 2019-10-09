package calofit.ui;

import calofit.model.dish.Dish;
import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Dish}.
 */
public class DishCard extends UiPart<Region> {

    private static final String FXML = "DishListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Dish dish;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    public DishCard(Dish dish, int displayedIndex) {
        super(FXML);
        this.dish = dish;
        id.setText(displayedIndex + ". ");
        name.setText(dish.getName().fullName);
        dish.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DishCard)) {
            return false;
        }

        // state check
        DishCard card = (DishCard) other;
        return id.getText().equals(card.id.getText())
                && dish.equals(card.dish);
    }
}
