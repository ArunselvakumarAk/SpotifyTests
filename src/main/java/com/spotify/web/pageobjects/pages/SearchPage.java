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

public class SearchPage extends BasePage{
	
	private By search_txt = By.xpath("//input[@data-testid='search-input']");
	private By songsFilter_btn = By.xpath("//span[text()='Songs']");
	private By playlistsFilter_btn = By.xpath("//a[contains(@href,'/search')]//span[text()='Playlists']");
	private By ResultedSongsListLocator = By.xpath("//div[@data-testid='tracklist-row']");
	private By ResultedPlaylistssListLocator = By.xpath("//div[contains(@data-testid,'search-category-card')]");
	private By songTitleLocator = By.xpath(".//div[@class='_iQpvk1c9OgRAc8KRTlH']/a/div");
	private By songArtistsLocator = By.xpath(".//div[@class='_iQpvk1c9OgRAc8KRTlH']//a[contains(@href,'/artist')]");
	private By playlistTitleLocator = By.xpath(".//span[contains(@class,'CardTitle__LineClamp')]");
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	public SearchPage navigateToSearchPage() {
		navigateTo("/search");
		return this;
	}
	
	public SearchPage enterSearchText(String song, String artist) {
		driver.findElement(search_txt).sendKeys(song + " " + artist);
		driver.findElement(search_txt).sendKeys(Keys.ENTER);
		return this;
	}
	
	public SearchPage enterSearchText(String song) {
		driver.findElement(search_txt).sendKeys(song);
		driver.findElement(search_txt).sendKeys(Keys.ENTER);
		return this;
	}
		
	public SearchPage clickSongsFilterBtn() {
		driver.findElement(songsFilter_btn).click();
		return this;
	}
	
	public SearchPage clickPlaylistsFilterBtn() {
		driver.findElement(playlistsFilter_btn).click();
		return this;
	}
	
	public SearchPage assertSongInTopResults(String song, String artist) {
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
		return this;
		
	}
	
	public SearchPage assertPlaylistInTopResults(String playlist) {
		List<WebElement> resultedSongsList = driver.findElements(ResultedPlaylistssListLocator);
		String[] resultedPlaylistsInfo =  new String[10];
		
		for(int i =0;i<10;i++) {
			String playlistTitle = resultedSongsList.get(i).findElement(playlistTitleLocator).getText();
			resultedPlaylistsInfo[i]= playlistTitle;
		}
				
		boolean isPlaylistReturned = Arrays.stream(resultedPlaylistsInfo)
				.filter(Objects::nonNull)
				.anyMatch(n->n.contains(playlist));
		
		Assert.assertTrue(isPlaylistReturned);
		return this;
		
	}
}
