import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @Test
    void shouldChangeDateForDelivery() {
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999/");

        String FirstDate = DataGenerate.generateDate(3);
        String SecondDate = DataGenerate.generateDate(6);

        $("[data-test-id=city] input").setValue(DataGenerate.generateCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(FirstDate);
        $("[data-test-id=name] input").setValue(DataGenerate.generateName());
        $("[data-test-id=phone] input").setValue(DataGenerate.generatePhone());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + FirstDate));

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(SecondDate);
        $(".button").click();
        $(withText("У вас уже запланирована встреча на другую дату."))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + SecondDate));

    }
}
