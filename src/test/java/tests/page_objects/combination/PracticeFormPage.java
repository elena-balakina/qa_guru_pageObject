package tests.page_objects.combination;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            monthOfBirthOption = $(".react-datepicker__month-select"),
            yearOfBirthOption = $(".react-datepicker__year-select"),
            dayOfBirthOption = $(".react-datepicker__month"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submitButton = $("#submit"),
            resultsTable = $(".table-responsive");

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

    public PracticeFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }

    public PracticeFormPage fillForm() {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        userEmailInput.setValue(email);
        $(String.format("input[value='%s'", gender)).parent().$("label").click();
        userNumberInput.setValue(mobilePhone);
        setBirthDate(monthOfBirth, yearOfBirth, dayOfBirth);
        setSubjects(subjects);
        setHobbies(hobbies);
        uploadPictureInput.uploadFromClasspath(pictureName);
        currentAddressInput.setValue(currentAddress);
        stateInput.setValue(state).pressEnter();
        cityInput.setValue(city).pressEnter();

        return this;
    }

    public PracticeFormPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public PracticeFormPage checkData() {
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

        return this;
    }

    private void setBirthDate(String monthOfBirth, String yearOfBirth, String dayOfBirth) {
        dateOfBirthInput.click();
        monthOfBirthOption.selectOptionContainingText(monthOfBirth);
        yearOfBirthOption.selectOptionContainingText(yearOfBirth);
        dayOfBirthOption.$(byText(dayOfBirth)).click();
    }

    private void setSubjects(String[] subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
    }

    private void setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            hobbiesInput.$(byText(hobby)).click();
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
