package com.github.frostyaxe.cucumber.stepdefs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.frostyaxe.cucumber.controllers.BaseController;
import com.github.frostyaxe.cucumber.pages.ExpediaSearch;
import com.github.frostyaxe.cucumber.pages.ExpediaSearch.HotelSearch;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

public class ExpediaHotelSearchStepDefs implements En
{
		BaseController controller;
		
		/**
		 * @param controller
		 */
		public ExpediaHotelSearchStepDefs(BaseController controller)
		{

			this.controller = controller;
			HotelSearch hotelSearchObj = new ExpediaSearch(controller).getHotelSearchObj();

			
			When("User clicks on Hotel Tab button", () -> {
				
				hotelSearchObj.getHotelTabButton().click();
				
			});
			
			And("User enters the correct data in the form fields", (DataTable table) -> {
				List<Map<String,String>> formData = table.transpose().asMaps(String.class,String.class);
				new WebDriverWait(controller.getDriver(), 15).until(ExpectedConditions.visibilityOf(hotelSearchObj.getGoingToTextField()));
				hotelSearchObj.getGoingToTextField().sendKeys(formData.get(0).get("goingTo"));
				
				hotelSearchObj.getCheckInDateField().sendKeys(formData.get(0).get("checkIn"));
				
				hotelSearchObj.getCheckOutDateField().sendKeys(formData.get(0).get("checkOut"));
				
			});
			
			
			And("Clicks on the search button",() -> {
				hotelSearchObj.getSearchButton().click();
			} );
			
			Then("User must be navigated to the results page", () -> {
				
				
				controller.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				Thread.sleep(10000);
				String actualTitle = controller.getDriver().getTitle();
				String expectedTitle = "Mumbai, India (BOM-Chhatrapati Shivaji Intl.) Hotel Search Results";
				
				Assert.assertEquals(actualTitle, expectedTitle);
				
			});
			
		
		}
}
