package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.BrokenLinksImagesPages;
import org.junit.jupiter.api.*;

@DisplayName("ElementsTests")
public class ElementsTests extends TestBase {
    SidePanel sidePanel;
    BrokenLinksImagesPages broken;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        new HomePage(driver).getElements();
        broken = new BrokenLinksImagesPages(driver);
    }

    @Test
    @DisplayName("getAllLinksTest")
    @Tag("smoke")
    public void getAllLinksTest() {
        sidePanel.getBrokenLinksImages();
        broken.getAllLinks();
    }

    @Test
    @Disabled("bug not fixed")
    public void checkBrokenLinksTest() {
        sidePanel.getBrokenLinksImages();
        broken.checkBrokenLinks();
    }

    @Test
    @Disabled("bug not fixed")
    public void checkBrokenImagesTest() {
        sidePanel.getBrokenLinksImages();
        broken.checkBrokenImages();
    }
}
