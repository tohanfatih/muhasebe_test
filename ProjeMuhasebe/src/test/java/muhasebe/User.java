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

import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class User extends Values {

    @BeforeClass
    public static void register (ITestContext context){

        Util.register(context);

    }
    /*
    @Test
    public void user (ITestContext context) {

        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token"))
                .contentType("application/json").
                        body("").
                        when().
                        get("/user");

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();

        Assert.assertEquals(statusCode , 200, "Status code returned was false !");
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK", "Status line returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("id"),getRandomid(),  "ID returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("name"),getName1(),  "Name returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("email"),getEmail1(),  "Email returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("company"),getCompany1(),  "Company returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("phonenumber"),getPhonenumber1(),  "Phone Number returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("address"),getAddress1(),  "Address returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("lastlogin"),getLastlogin1(),  "Last Login returned was false !");
        Assert.assertEquals(jsonPathEvaluator.get("activeuntil"),getActiveuntil1(),  "Active Until returned was false !");
        //Assert.assertEquals(jsonPathEvaluator.get("password"),password1,  "Password returned was false !");
        Util.getResponseTime("https://muhasebe-denetleme-backend.herokuapp.com/user");

    }*/

    @Test
    public void getComparisonOverview(ITestContext context){

        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token"))
                .contentType("application/json").
                        when().
                        get("/user/get_comparison_overview");

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();

        Assert.assertEquals(statusCode , 200, "Status code returned was false !");
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK", "Status line returned was false !");

    }



    @Test
    public void changePass(ITestContext context){

        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token"))
                .contentType("application/json").body("{\n" +
                        "  \"password\": \"1234\"\n" +
                        "}").
                        when().
                        post("/user/changepass/");

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();

        Assert.assertEquals(statusCode , 200, "Status code returned was false !");
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK", "Status line returned was false !");
        Util.getResponseTime("https://muhasebe-denetleme-backend.herokuapp.com/user/changepass");

    }

    @Test
    public void sendXmlinZip(ITestContext context){

        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        String endpoint = "/user/sendxmlinzip/";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token")).contentType("multipart/form-data")
                .multiPart("files",new File("C:\\Users\\ozdileto\\Desktop\\gelen fatura_3_hata.zip")).
                        multiPart("files",new File("C:\\Users\\ozdileto\\Desktop\\E-Defteri.xml")).
                        multiPart("ebillid","random").
                        multiPart("invoicetype","SATIS").
                        multiPart("chosenbilltype","Gider Faturasi").
                        multiPart("gelengiden","gelen").body("").
                        when().
                        post(endpoint);

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();
        // String name = jsonPathEvaluator.get("name");

        Assert.assertEquals(statusCode, 200, "Status code returned was false !");
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Status line returned was false !");
        Util.getResponseTime("https://muhasebe-denetleme-backend.herokuapp.com/user/admindebug");

    }

    @Test
    public void getSpecificComparison(ITestContext context){

        RestAssured.baseURI = "https://muhasebe-denetleme-backend.herokuapp.com";
        String endpoint = "/user/get_specific_comparison";
        Response r = given()
                .header("Authorization", "Bearer " + context.getAttribute("access_token"))
                .contentType("application/json").body("{\n" +
                        "  \"enotebookid\": \"YEV201905000008\"\n" +
                        "}").
                        when().
                        post(endpoint);

        String body = r.getBody().asString();
        int statusCode = r.getStatusCode();
        String statusLine = r.getStatusLine();

        System.out.println(statusCode);
        System.out.println(statusLine);
        System.out.println(body);

        JsonPath jsonPathEvaluator = r.jsonPath();

        Assert.assertEquals(statusCode , 200, "Status code returned was false !");
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK", "Status line returned was false !");
        Util.getResponseTime("https://muhasebe-denetleme-backend.herokuapp.com/user/get_specific_comparison");
    }

}
