package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestPage extends BasePage {

    //selectors
    private static final By BUTTON_RENDER_THE_CHALLENGE = By.xpath("//*[@id=\"home\"]/div/div/button/div/div");
    private static final By TABLE_ROWS = By.cssSelector("tbody > tr");
    private static final By TABLE_CELLS = By.cssSelector("td");
    private static final By INPUT_ANSWER_1 = By.cssSelector("[data-test-id=submit-1]");
    private static final By INPUT_ANSWER_2 = By.cssSelector("[data-test-id=submit-2]");
    private static final By INPUT_ANSWER_3 = By.cssSelector("[data-test-id=submit-3]");
    private static final By INPUT_NAME = By.cssSelector("[data-test-id=submit-4");
    public static final By BUTTON_SUBMIT = By.xpath("//*[@id=\"challenge\"]/div/div/div[2]/div/div[2]");


    //variables
    private static Map<String, ArrayList<Integer>> arrays = new LinkedHashMap<>();
    private static Map<String, Integer> values = new LinkedHashMap<>();


    public TestPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        String HOME_PAGE;
        try {
            HOME_PAGE = props.getProperty("home.page");
        } catch (NullPointerException e) {
            System.out.println("home page config not found, defaulting to \"http://192.168.99.100:3000/\"");
            HOME_PAGE = "http://192.168.99.100:3000/";
        }
        goTo(HOME_PAGE);
    }

    public void clickRenderTheChallenge() {
        click(BUTTON_RENDER_THE_CHALLENGE);
    }

    public void fillArrays() {
        List<WebElement> rows = getWebElements(TABLE_ROWS);

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            WebElement row = rows.get(rowIndex);
            List<WebElement> cellWebElements = row.findElements(TABLE_CELLS);
            arrays.put("list" + rowIndex, getCells(cellWebElements));
        }
    }

    private ArrayList<Integer> getCells(List<WebElement> cells) {
        ArrayList<Integer> cellValues = new ArrayList<>();

        for (WebElement cell : cells) {
            cellValues.add(Integer.parseInt(cell.getText()));
        }

        return cellValues;
    }

    public void saveEquilibriumIndexes() {
        int i = 0;
        while (i < arrays.size()) {
            values.put("Index" + i, getEquilibriumIndex(arrays.get("list" + i), arrays.get("list" + i).size()));
            i++;
        }
    }

    private int getEquilibriumIndex(List<Integer> cellValues, int n) {
        int i, j, leftsum, rightsum;;

        for (i = 0; i < n; ++i) {

            leftsum = 0;
            for (j = 0; j < i; j++)
                leftsum += cellValues.get(j);

            rightsum = 0;
            for (j = i + 1; j < n; j++)
                rightsum += cellValues.get(j);

            if (leftsum == rightsum)
                return i;
        }
        /*Null is only returned here for the sake of the requirements - returning null should be avoided or else you end up with
        potentially hundreds of methods trying to handle the null when it should be done in the lowest level method - this one! */
        return i;
    }

    public void fillAnswerBoxes() {
        sendKeys(INPUT_ANSWER_1, values.get("Index0").toString());
        sendKeys(INPUT_ANSWER_2, values.get("Index1").toString());
        sendKeys(INPUT_ANSWER_3, values.get("Index2").toString());
        sendKeys(INPUT_NAME, "Jonathan Millington-Hotze");
    }
}
