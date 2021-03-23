package tests.page_objects.scenarios;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PracticeFormTests {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    void successfulFillPracticeFormTest() {
        practiceFormPage.openPage();
        practiceFormPage.fillForm();
        practiceFormPage.checkData();
    }
}
