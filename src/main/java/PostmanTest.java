import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PostmanTest {
    private RestAssured RestAssured;
    private RequestSpecification requestSpec;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
        requestSpec = RestAssured.given()
                .log().all()
                .header("User-Agent", "PostmanRuntime/7.43.0")
                .header("postman-token", "67aad1c7-7465-4f49-913d-3699ecc7dae4")
                .header("cookie", "sails.sid=s%3AwJbdCUsKYK0mj06JJcovg_ZBk5Ibk8zr.ewXT8PsupRBH7g6Q%2Fcp8BAYcoGQjeyLJgKkl%2Bzmer8c")
                .header("accept-encoding", "deflate, br, gzip");
    }

    //Get Request
    @Test
    void testGetRequest() {
        requestSpec.when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then().log().body().statusCode(200)
                .assertThat()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token",not(""))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    //Get Request Woops Передаем специально неверно bar
    @Test
    void testGetRequestNotCorrect() {
        requestSpec.when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then().log().body().statusCode(200)
                .assertThat()
                .body("args.foo1", equalTo("bar11"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token",not(""))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    //PostRawText Request
    @Test
    public void testPostRawText() {
        String requestBody = "This is expected to be sent back as part of response body.";
        requestSpec.when()
                .header("content-type", "text/plain; charset=ISO-8859-1")
                .body(requestBody)
                .when()
                .post("/post")
        .then().log().all()
                .statusCode(200)
                .body("args", notNullValue())
                .body("data", equalTo(requestBody))
                .body("files", notNullValue()) // Проверяем, что поле "files" пустое
                .body("forms", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1")) // Проверяем заголовок "content-type"
                .body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", notNullValue())
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.accept-encoding", containsString("deflate"))
                .body("headers.accept-encoding", containsString("br"))
                .body("headers.cookie", containsString("sails.sid"));

    }
    //PostFormData Request
    @Test
    public void testPostFormData() {

        requestSpec.when()
                .header("content-type", "application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then().log().all()
                .statusCode(200)
                .body("args", notNullValue())
                .body("data", equalTo(""))
                .body("files", notNullValue())
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("19"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("application/x-www-form-urlencoded; charset=utf-8")) // Проверяем заголовок "content-type"
                .body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", notNullValue())
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.accept-encoding", containsString("deflate"))
                .body("headers.accept-encoding", containsString("br"))
                .body("headers.cookie", containsString("sails.sid"));
    }
    //Put Request
    @Test
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        requestSpec.when()
                .header("content-type", "text/plain; charset=ISO-8859-1")
                .body(requestBody)
                .when()
                .put("/put")
                .then().log().all()
                .statusCode(200)
                .body("args", notNullValue())
                .body("data", equalTo(requestBody))
                .body("files", notNullValue())
                .body("forms", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1")) // Проверяем заголовок "content-type"
                .body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", notNullValue())
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.accept-encoding", containsString("deflate"))
                .body("headers.accept-encoding", containsString("br"))
                .body("headers.cookie", containsString("sails.sid"));

    }
    //Patch Request
    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        requestSpec.when()
                .header("content-type", "text/plain; charset=ISO-8859-1")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then().log().all()
                .statusCode(200)
                .body("args", notNullValue())
                .body("data", equalTo(requestBody))
                .body("files", notNullValue())
                .body("forms", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1")) // Проверяем заголовок "content-type"
                .body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", notNullValue())
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.accept-encoding", containsString("deflate"))
                .body("headers.accept-encoding", containsString("br"))
                .body("headers.cookie", containsString("sails.sid"));

    }
    //Delete Request
    @Test
    public void testDeleteRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        requestSpec.when()
                .header("content-type", "text/plain; charset=ISO-8859-1")
                .body(requestBody)
                .when()
                .delete("/delete")
                .then().log().all()
                .statusCode(200)
                .body("args", notNullValue())
                .body("data", equalTo(requestBody))
                .body("files", notNullValue())
                .body("forms", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1"))
                .body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", notNullValue())
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.accept-encoding", containsString("deflate"))
                .body("headers.accept-encoding", containsString("br"))
                .body("headers.cookie", containsString("sails.sid"));

    }

}

