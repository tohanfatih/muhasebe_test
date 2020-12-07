package muhasebe;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class  Login extends Values{

    @BeforeClass
    public static void register (ITestContext context){
        Util.register(context);

    }
    public void register(ITestContext context,String password) {

    }

    @Test
    public void get_token(ITestContext context) {

        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token"))
                //.contentType(ContentType.JSON)
                .contentType("application/json").
                        body("").
                        when().
                        post("/get_token/");

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();
        // String name = jsonPathEvaluator.get("name");

        Assert.assertEquals(statusCode , 200, "Status code returned was false !");
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK", "Status line returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("id"),getRandomid(),  "ID returned was false !");
        //Assert.assertEquals(jsonPathEvaluator.get("password"),password1,  "Password returned was false !");

    }
    /*@Test
    public void authenticate(ITestContext context) {
        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token"))
                //.contentType(ContentType.JSON)
                .contentType("application/json").
                        body("").
                        when().
                        post("/authenticate");

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();
        // String name = jsonPathEvaluator.get("name");

        Assert.assertEquals(statusCode , 307, "Status code returned was false !");
        Assert.assertEquals(statusLine , "HTTP/1.1 307 Temporary Redirect", "Status line returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("name"),getName1(),  "Name returned was false !");
        //Assert.assertEquals(jsonPathEvaluator.get("password"),password1,  "Password returned was false !");

        }
        */

    @Test
    public void admin_login(ITestContext context) {
        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token"))
                //.contentType(ContentType.JSON)
                .contentType("application/json").
                        body("").
                        when().
                        post("/admin-login");

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();
        // String name = jsonPathEvaluator.get("name");

        Assert.assertEquals(statusCode , 200, "Status code returned was false !");
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK", "Status line returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("id"),getRandomid(),  "ID returned was false !");
        //Assert.assertEquals(jsonPathEvaluator.get("password"),password1,  "Password returned was false !");

    }

}
