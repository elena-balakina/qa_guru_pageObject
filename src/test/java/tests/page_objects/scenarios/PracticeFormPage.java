package tests.page_objects.scenarios;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {

    private String firstName = "Ivan",
            lastName = "Ivanov",
            email = "ivan.ivanov@company.com",
            gender = "Male",
            mobilePhone = "1234567890",
            monthOfBirth = "January",
            yearOfBirth = "1990",
            dayOfBirth = "1",
            pictureName = "img.jpg",
            currentAddress = "Rajasthan, Jaipur, Street, 1st",
            state = "Rajasthan",
            city = "Jaipur";

    private String[] subjects = new String[]{"Maths", "English", "Computer Science"},
            hobbies = new String[]{"Reading", "Music"};

    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    }

    public void fillForm() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(String.format("input[value='%s'", gender)).parent().$("label").click();
        $("#userNumber").setValue(mobilePhone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(monthOfBirth);
        $(".react-datepicker__year-select").selectOptionContainingText(yearOfBirth);
        $(".react-datepicker__month").$(byText(dayOfBirth)).click();

        setSubjects(subjects);
        setHobbies(hobbies);

        $("#uploadPicture").uploadFromClasspath(pictureName);

        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();

        $("#submit").click();
    }

    public void checkData() {
        SelenideElement resultsTable = $(".table-responsive");
        resultsTable.$(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
        resultsTable.$(byText("Student Email")).parent().shouldHave(text(email));
        resultsTable.$(byText("Gender")).parent().shouldHave(text(gender));
        resultsTable.$(byText("Mobile")).parent().$("td", 1).shouldHave(text(mobilePhone));
        resultsTable.$(byText("Date of Birth")).parent().$("td", 1).shouldHave(text(dayOfBirth), text(monthOfBirth), text(yearOfBirth));

        checkSubjects(resultsTable, subjects);
        checkHobbies(resultsTable, hobbies);

        resultsTable.$(byText("Picture")).parent().$("td", 1).shouldHave(text(pictureName));
        resultsTable.$(byText("Address")).parent().$("td", 1).shouldHave(text(currentAddress));
        resultsTable.$(byText("State and City")).parent().$("td", 1).shouldHave(text(state), text(city));
    }

    private void setSubjects(String[] subjects) {
        for (String subject : subjects) {
            $("#subjectsInput").setValue(subject).pressEnter();
        }
    }

    private void setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            $("#hobbiesWrapper").$(byText(hobby)).click();
        }
    }

    private void checkSubjects(SelenideElement resultsTable, String[] subjects) {
        for (String subject : subjects) {
            resultsTable.$(byText("Subjects")).parent().$("td", 1).shouldHave(text(subject));
        }
    }

    private void checkHobbies(SelenideElement resultsTable, String[] hobbies) {
        for (String hobby : hobbies) {
            resultsTable.$(byText("Hobbies")).parent().$("td", 1).shouldHave(text(hobby));
        }
    }
}
