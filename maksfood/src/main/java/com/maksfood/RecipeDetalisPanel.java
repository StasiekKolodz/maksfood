package com.maksfood;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class RecipeDetalisPanel extends RoundedPanel implements ActionListener {
    
    public MainWindow window;
    public Recipe current_recipe;
    public DefaultListModel<String> ingredient_model = new DefaultListModel<>();
    public JList<String> ingredientList = new JList<>(ingredient_model);
    public URL photo_url;
    public JLabel photo = new JLabel();
    public JLabel recipesLabel = new JLabel();
    public JScrollPane ingredientScrollPane = new JScrollPane(ingredientList);
    public JPanel previousPanel;
    
    public RecipeDetalisPanel(LayoutManager layout, int r, MainWindow w) {
        super(layout, r);
        window = w;
        
        // creating label
        recipesLabel.setForeground(new Color(165, 56, 96));
        recipesLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));

        // creating buttons
        ColorButton returnButton = new ColorButton("Return");
        window.setButton(returnButton, this);

        ColorButton seeOnWebsiteButton = new ColorButton("See on website");
        window.setButton(seeOnWebsiteButton, this);

        ColorButton addToShoppingListButton = new ColorButton("Add to shopping list");
        window.setButton(addToShoppingListButton, this);

        // creating button and label panels
        JPanel recipesLabelPanel = new JPanel(new GridLayout());
        recipesLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        recipesLabelPanel.add(recipesLabel);
        returnButtonPanel.add(returnButton);
        recipesLabelPanel.setBounds(getVisibleRect());
        
        // setting panels visual sides
        setOpaque(false);
        setBackground(new Color(255, 238, 219, 200));
        
        // configuring scroll Panel and product list
        ingredientList.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        ingredientList.setBackground(new Color(165, 56, 96));
        ingredientList.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 17));
        ingredientList.setForeground(Color.WHITE);
        ingredientList.setFixedCellWidth(400);
        ingredientList.setSelectionBackground(Color.WHITE);
        ingredientList.setSelectionForeground(new Color(165, 56, 96));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)ingredientList.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER);  
        renderer.setVerticalAlignment(JLabel.CENTER); 

        ingredientList.setCellRenderer(renderer);
        ingredientScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ingredientScrollPane.setMinimumSize(new Dimension(400,100));
        ingredientScrollPane.setMaximumSize(new Dimension(500,250));
        ingredientScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(76, 59, 77);
            }
        });

        // adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(10, 10, 10, 10);
        e.gridx = 0;
        e.gridy = 3;
        this.add(seeOnWebsiteButton, e);
        
        e.gridx = 1;
        e.gridy = 3;
        this.add(addToShoppingListButton, e);
        
        e.gridwidth = 2;
        e.gridx = 0;
        e.gridy = 0;
        this.add(recipesLabelPanel, e);
        
        e.gridx = 0;
        e.gridy = 1;
        this.add(photo, e);

        e.gridx = 0;
        e.gridy = 2;
        this.add(ingredientScrollPane, e);
        
        e.gridx = 0;
        e.gridy = 4;
        this.add(returnButtonPanel, e);
    }

    public void update_details(Recipe current_recipe) throws IOException{
        this.current_recipe = current_recipe;
        ingredient_model.clear();
        for (String ing : current_recipe.ingredient_lines) {
            String text = String.format("<html><div WIDTH=%d style="+"text-align: center"+">%s</div></html>", 400, ing);
            ingredient_model.addElement(text);
        }
        photo_url = new URL(current_recipe.link_to_photo);
        BufferedImage image = ImageIO.read(photo_url);
        ImageIcon ii = new ImageIcon(image);
        photo.setIcon(ii);

        recipesLabel.setText(String.format("<html><div WIDTH=%d style="+"text-align: center"+">%s</div></html>", 400, current_recipe.recipe_text));
        recipesLabel.setHorizontalAlignment(JLabel.CENTER);
        

    }

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    // button click perform
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Return")) {

        window.currentPanel.setVisible(false);

        window.currentPanel = previousPanel;

        window.currentPanel.setVisible(true);
    }
    if (e.getActionCommand().equals("See on website")) {
        try {
            openWebpage(new URL(current_recipe.link_to_recipe));
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
    }
    if (e.getActionCommand().equals("Add to shopping list")) {
        window.shoppingPanel.shoppingDB.sqlUpdate("INSERT INTO maksfood.listsList VALUES(DEFAULT,'"
        +current_recipe.recipe_text+"');");
        for (String s : current_recipe.ingredient_lines) {
            window.shoppingPanel.shoppingDB.sqlUpdate("INSERT INTO maksfood.shoppingList VALUES(DEFAULT,'"
            +s+"','"+"1"+"','"+current_recipe.recipe_text+"');");
        }
    }
    
}
}