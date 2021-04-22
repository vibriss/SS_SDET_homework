import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void givenUrl_whenGetAllUsers_thenUserEmailFound() {
        get("https://reqres.in/api/users").then().assertThat()
                .body("data[0].first_name", equalTo("George"))
                .body("data[0].last_name", equalTo("Bluth"))
                .body("data[0].email", equalTo("george.bluth@reqres.in"));
    }

    @Test
    public void givenUrlAndPageNumber_whenGetAllUsers_thenUserEmailFound() {
        get("https://reqres.in/api/users?page=2").then().assertThat()
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"))
                .body("data[0].email", equalTo("michael.lawson@reqres.in"));
    }
}
