// package com.maksfood;

// import org.junit.jupiter.api.Test;
// import org.junit.Before;
// import org.junit.Test;

// import java.awt.*;
// import java.awt.event.ActionEvent;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.*;

// public class UnitTests {
//     private MainWindow mockWindow;
//     private Time mockTime;
//     private Menu menu;

//     @Before
//     public void setUp() {
//         mockWindow = mock(MainWindow.class);
//         mockTime = mock(Time.class);
//         menu = new Menu(new FlowLayout(), 10, mockWindow);
//         menu.time = mockTime;
//     }

//     @Test
//     public void testDisplayExpData_NoExpiredProducts() {
//         when(mockTime.getAboutToExpire()).thenReturn(new ArrayList<>());

//         DefaultLabel result = menu.displayExpData();

//         assertEquals("No products in the fridge are about to expire", result.getText());
//     }

//     @Test
//     public void testDisplayExpData_OneExpiredProduct() {
//         List<Product> expiredProducts = new ArrayList<>();
//         expiredProducts.add(new Product("Product 1", LocalDate.now()));
//         when(mockTime.getAboutToExpire()).thenReturn(expiredProducts);

//         DefaultLabel result = menu.displayExpData();

//         assertEquals("<html>Warning!<br> 1 product is about to expire!</html>", result.getText());
//     }

//     @Test
//     public void testDisplayExpData_MultipleExpiredProducts() {
//         List<Product> expiredProducts = new ArrayList<>();
//         expiredProducts.add(new Product("Product 1", LocalDate.now()));
//         expiredProducts.add(new Product("Product 2", LocalDate.now()));
//         when(mockTime.getAboutToExpire()).thenReturn(expiredProducts);

//         DefaultLabel result = menu.displayExpData();

//         assertEquals("<html>Warning!<br> 2 products are about to expire!</html>", result.getText());
//     }

//     @Test
//     public void testActionPerformed_MyFridgeButton() {
//         ActionEvent mockEvent = mock(ActionEvent.class);
//         when(mockEvent.getActionCommand()).thenReturn("My Fridge");
//         JPanel mockFridgePanel = mock(JPanel.class);
//         menu.window.fridgePanel = mockFridgePanel;

//         menu.actionPerformed(mockEvent);

//         verify(menu.window).setContentPane(mockFridgePanel);
//         verify(mockFridgePanel).updateList();
//     }

//     @Test
//     public void testActionPerformed_RecipesButton() {
//         ActionEvent mockEvent = mock(ActionEvent.class);
//         when(mockEvent.getActionCommand()).thenReturn("Recipes");
//         JPanel mockRecipesPanel = mock(JPanel.class);
//         menu.window.recipesPanel = mockRecipesPanel;

//         menu.actionPerformed(mockEvent);

//         verify(menu.window).setContentPane(mockRecipesPanel);
//     }

//     @Test
//     public void testActionPerformed_PlanShoppingButton() {
//         ActionEvent mockEvent = mock(ActionEvent.class);
//         when(mockEvent.getActionCommand()).thenReturn("Plan shopping");
//         JPanel mockShoppingPanel = mock(JPanel.class);
//         menu.window.shoppingPanel = mockShoppingPanel;

//         menu.actionPerformed(mockEvent);

//         verify(menu.window).setContentPane(mockShoppingPanel);
//     }
// }
