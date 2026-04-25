package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrokenLinksImagesPages extends BasePage {

    public BrokenLinksImagesPages(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;
    @FindBy(css = "img")
    List<WebElement> images;

    public BrokenLinksImagesPages getAllLinks() {
        System.out.println(allLinks.size());
//        String url = "";
//        Iterator<WebElement> itr = allLinks.iterator();
//        while (itr.hasNext()){
//            url = itr.next().getText();
//            System.out.println(url);
//        }
        return this;
    }

    public BrokenLinksImagesPages checkBrokenLinks() {
        allLinks.forEach(link -> {
            String url = link.getDomAttribute("href");
            verifyLink(url);
        });
        softly.assertAll();
        return this;
    }

    public BrokenLinksImagesPages checkBrokenImages() {
        System.out.println(images.size());
        images.forEach(img -> {
            String imageUrl = img.getDomAttribute("src");
            verifyLink(imageUrl);
            verifyDisplayedImg(img);
        });
        softly.assertAll();
        return this;
    }

}
