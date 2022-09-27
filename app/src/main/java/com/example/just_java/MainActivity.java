package com.example.just_java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //get their name into string form
        EditText nameField = (EditText) findViewById(R.id.name);
        String name = nameField.getText().toString();

        //see if they want whipped cream and/or chocolate
        CheckBox whip = (CheckBox) findViewById(R.id.whipped_cream);
        boolean ifWhip = whip.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean ifChoc = chocolate.isChecked();

        //calculate the price
        int pricePerCoffee = 5;
        if (ifWhip) {
            pricePerCoffee += 1; //whipped cream costs $1
        }
        if (ifChoc) {
            pricePerCoffee += 2; //chocolate costs $2
        }
        int price = pricePerCoffee * quantity;

        String priceMessage = createOrderSummary(name, price, ifWhip, ifChoc);

        displayMessage(priceMessage);
    }

    private String createOrderSummary(String name, int price, boolean addWhip, boolean addChoc) {
        String message = "Name: " + name;
        message += "\nQuantity: " + quantity;
        if (addWhip) {
            message += "\nAdd whipped cream";
        }
        else {
            message += "\nNo whipped cream";
        }
        if (addChoc) {
            message += "\nAdd chocolate";
        }
        else {
            message += "\nNo chocolate";
        }
        message += "\nTotal: $" + price;
        message += "\nThank you!";
        return message;
    }

    //displays the given text on the screen
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view) {
        if (quantity >= 100) {
            //show error message using Toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return; //exit method
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return; //exit method w/out decrementing
        }
        quantity -= 1;
        displayQuantity(quantity);
    }
}