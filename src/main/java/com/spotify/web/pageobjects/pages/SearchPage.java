package com.spotify.web.pageobjects.pages;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.utils.EventLogger;
import com.spotify.web.utils.ResourceUtils;

public class SearchPage extends BasePage{
	
	private By search_txt = By.xpath("//input[@data-testid='search-input']");
	private By ResultedSongsListLocator = By.xpath("//div[@data-testid='tracklist-row']");
	private By ResultedListLocator = By.xpath("//div[contains(@data-testid,'search-category-card')]");
	private By songTitleLocator = By.xpath(".//div[@class='_iQpvk1c9OgRAc8KRTlH']/a/div");
	private By songArtistsLocator = By.xpath(".//div[@class='_iQpvk1c9OgRAc8KRTlH']//a[contains(@href,'/artist')]");
	private By TitleLocator = By.xpath(".//span[contains(@class,'CardTitle__LineClamp')]");
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	public SearchPage openPage() {
		EventLogger.info("Opening search page");
		driver.get(ResourceUtils.getProperty("configuration//web_config.properties", "baseUrl") + "/search");
		return this;
	}
	
	public By getLocatorForFilterBtn(String filter) {
		return By.xpath("//a[contains(@href,'/search')]//span[text()='"+filter+"']");
	}
	
	public SearchPage enterSearchText(String songName, String artistName) {
		utils.waitForElementToBeClickable(search_txt);
		driver.findElement(search_txt).sendKeys(songName + " " + artistName);
		driver.findElement(search_txt).sendKeys(Keys.ENTER);
		EventLogger.info("searching for "+songName +" song with artist name " + artistName);
		return this;
	}
	
	public SearchPage enterSearchText(String searchKey) {
		utils.waitForElementToBeClickable(search_txt);
		driver.findElement(search_txt).sendKeys(searchKey);
		driver.findElement(search_txt).sendKeys(Keys.ENTER);
		EventLogger.info("searching for "+searchKey);
		return this;
	}
	
	public SearchPage clickFilterBtn(String filter) {
		utils.waitForElementToBeClickable(getLocatorForFilterBtn(filter));
		driver.findElement(getLocatorForFilterBtn(filter)).click();
		EventLogger.info("Clicked on " + filter + " filter button successfully" );
		return this;
	}
			
	public SearchPage assertSongInTopResults(String song, String artist) {
		utils.waitForVisibilityOfAllElements(ResultedSongsListLocator);
		List<WebElement> resultedSongsList = driver.findElements(ResultedSongsListLocator);
		String[][] resultedSongsInfo =  new String[10][2];
		
		for(int i =0;i<10;i++) {
			String songTitle = resultedSongsList.get(i).findElement(songTitleLocator).getText();
			List<WebElement> artistList = resultedSongsList.get(i).findElements(songArtistsLocator);
			StringBuilder songArtists = new StringBuilder();
			for(int j =0;j<artistList.size();j++) {
				if(songArtists.length()!=0) {
					songArtists.append(",");
				}
				songArtists.append(artistList.get(j).getText());
			}
			resultedSongsInfo[i][0]= songTitle;
			resultedSongsInfo[i][1]= songArtists.toString();
		}
				
		boolean isSongReturned = Arrays.stream(resultedSongsInfo)
				.filter(Objects::nonNull)
				.anyMatch(n->n[0].contains(song)&&n[1].contains(artist));
		
		Assert.assertTrue(isSongReturned);
		EventLogger.info(song+" found in top results");
		return this;
		
	}
	
	public SearchPage assertInTopResults(String key) {
		utils.waitForVisibilityOfAllElements(ResultedListLocator);
		List<WebElement> resultedList = driver.findElements(ResultedListLocator);
		String[] resultedInfo =  new String[10];
		
		for(int i =0;i<10;i++) {
			String title = resultedList.get(i).findElement(TitleLocator).getText();
			resultedInfo[i]= title;
		}
				
		boolean isPlaylistReturned = Arrays.stream(resultedInfo)
				.filter(Objects::nonNull)
				.anyMatch(n->n.contains(key));
		
		Assert.assertTrue(isPlaylistReturned);
		EventLogger.info(key+" found in top results");
		return this;
	}
}
