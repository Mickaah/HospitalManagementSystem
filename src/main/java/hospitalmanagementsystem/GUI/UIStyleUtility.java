package hospitalmanagementsystem.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UIStyleUtility {

    // Style Main Action Buttons
    public static void stylePrimaryButton(JButton button) {
        button.setBackground(UITheme.PRIMARY_TEAL);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Style Secondary / Danger Buttons (Remove, Back)
    public static void styleSecondaryButton(JButton button, boolean isDanger) {
        Color bg = isDanger ? UITheme.DANGER_RED : UITheme.MUTED_GRAY;
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Style Text Fields
    public static void styleTextField(JTextField field) {
        field.setFont(UITheme.REGULAR_FONT);
        field.setForeground(UITheme.DARK_SLATE);
        field.setBorder(new CompoundBorder(
            new LineBorder(new Color(203, 213, 225), 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));
    }

    // Style Title Labels
    public static void styleTitleLabel(JLabel label) {
        label.setFont(UITheme.TITLE_FONT);
        label.setForeground(UITheme.PRIMARY_TEAL);
    }

    // Style Standard Field Labels
    public static void styleLabel(JLabel label) {
        label.setFont(UITheme.LABEL_FONT);
        label.setForeground(UITheme.DARK_SLATE);
    }
}