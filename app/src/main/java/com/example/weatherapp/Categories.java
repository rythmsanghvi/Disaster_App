package com.example.weatherapp;// MainActivity.java

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Categories extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        LinearLayout cardContainer = findViewById(R.id.card_container);

        // Read the card data from the file
        List<String> cardDataList = readCardData();

        for (String cardData : cardDataList) {
            // Split the card data into image and text
            String[] parts = cardData.split(",");

            if (parts.length == 2) {
                String image = parts[0].trim();
                String text = parts[1].trim();

                // Inflate the card view layout
                LinearLayout cardView = (LinearLayout) getLayoutInflater().inflate(R.layout.card_view, null);

                // Find the ImageView and TextView inside the card view
                ImageView imageView = cardView.findViewById(R.id.image_view);
                TextView textView = cardView.findViewById(R.id.text_view);

                // Set the image and text for the current card
                int imageResource = getResources().getIdentifier(image, "drawable", getPackageName());
                imageView.setImageResource(imageResource);
                textView.setText(text);

                // Add the card view to the container
                cardContainer.addView(cardView);
            }
        }
    }

    private List<String> readCardData() {
        List<String> cardDataList = new ArrayList<>();

        try {
            // Read the file input stream
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.cards_data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Read each line and add it to the list
            String line;
            while ((line = reader.readLine()) != null) {
                cardDataList.add(line);
                System.out.println(line);
            }

            // Close the reader and input stream
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cardDataList;
    }
}
