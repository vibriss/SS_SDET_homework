import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    /**
     * Проверка API: у пользователя George Bluth e-mail: george.bluth@reqres.in.
     */
    @Test
    public void givenUrl_whenGetAllUsers_thenUserFoundAndEmailMatch() {
        get("https://reqres.in/api/users").then().assertThat()
                .body("data[0].first_name", equalTo("George"))
                .body("data[0].last_name", equalTo("Bluth"))
                .body("data[0].email", equalTo("george.bluth@reqres.in"));
    }

    /**
     * Проверка API: у пользователя Michael Lawson e-mail: michael.lawson@reqres.
     */
    @Test
    public void givenUrlAndPageNumberAndPageSize_whenGetAllUsers_thenUserFoundAndEmailMatch() {
        get("https://reqres.in/api/users?page=2&per_page=6").then().assertThat()
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"))
                .body("data[0].email", equalTo("michael.lawson@reqres.in"));
    }

    /**
     * Проверка пагинации API.
     */
    @Test
    public void givenUrlAndPageNumberAndPageSize_whenGetAllUsers_thenPaginationCorrect() {
        get("https://reqres.in/api/users?page=3&per_page=5").then().assertThat()
                .body("total", equalTo(12))
                .body("total_pages", equalTo(3))
                .body("data.size()", equalTo(2));
    }
}
