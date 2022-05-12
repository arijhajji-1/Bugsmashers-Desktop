/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Grim
 */
public class Animations {

    public static void animateButton(
            Button button,
            Color bgColor,
            Color fontColorStart,
            Color fontColorEnd,
            float imageBrightness,
            boolean changeGraphicNotText) {

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(200));
                setInterpolator(Interpolator.EASE_OUT);
            }

            @Override
            protected void interpolate(double frac) {
                Color vColor = new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 1 - frac);

                if (changeGraphicNotText) {
                    button.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                    button.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(5.0), Insets.EMPTY)));
                }
            }
        };

        // on start hover
        button.setOnMouseEntered((event) -> {
            animation.stop();

            if (changeGraphicNotText) {
                button.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                button.setBackground(new Background(new BackgroundFill(bgColor, new CornerRadii(5.0), Insets.EMPTY)));
            }

            if (changeGraphicNotText) {
                ColorAdjust whiteEffect = new ColorAdjust();
                whiteEffect.setBrightness(imageBrightness);
                button.getGraphic().setEffect(whiteEffect);
            } else {
                button.setTextFill(fontColorEnd);
            }
        });
        // on end hover
        button.setOnMouseExited((event) -> {
            animation.play();
            if (changeGraphicNotText) {
                button.getGraphic().setEffect(null);
            } else {
                button.setTextFill(fontColorStart);
            }
        });
    }
}
