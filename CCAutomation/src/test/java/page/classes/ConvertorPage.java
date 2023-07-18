package page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConvertorPage extends PageBase{

	public ConvertorPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@class=\"text-input__TextInput-sc-17mujrb-0 amount-input__Wrapper-sc-1gq6pic-0 fbQqSt ezbfAz\"]")
	WebElement amount;
	
	@FindBy(xpath="//div[@id=\"midmarketFromCurrency-descriptiveText\"]")
	WebElement fromDropdown;
	
	@FindBy(xpath="//div[@id=\"midmarketFromCurrency-descriptiveText\"]")
	WebElement fromTextVal;
	
}
