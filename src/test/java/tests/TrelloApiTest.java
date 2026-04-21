package tests;

import base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utilities.Log;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloApiTest extends BaseApiTest {

    static String boardId, cardId1, cardId2, listId;

    @Test
    @Order(1)
    public void createBoard() {
        test = extent.createTest("Trello API - Create Board");

        Response response = given()
                .queryParam("name", "Proje_Board")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .contentType(ContentType.JSON)
                .when()
                .post("/boards")
                .then()
                .statusCode(200)
                .extract().response(); // SADECE BURAYI DEĞİŞTİRDİK (path("id") yerine response() yazdık)

        response.prettyPrint();
        boardId = response.path("id");
        test.pass("Board created. ID: " + boardId);
        Log.info("Created Board ID: " + boardId);
    }


    @Test
    @Order(2)
    public void getListId() {
        Response response = given()
                .pathParam("boardId", boardId)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .get("/boards/{boardId}/lists")
                .then()
                .statusCode(200)
                .extract().response(); // TÜM YANITI ALIYORUZ

        response.prettyPrint();
        listId = response.path("[0].id");

        test.pass("List ID successfully created: " + listId);
        Log.info("First List ID: " + listId);
    }


    @Test
    @Order(3)
    public void createCards() {
        test = extent.createTest("Trello API - Create Two Cards");

        // --- Create 1.Card ---
        Response response1 = given()
                .queryParam("name", "Card-1")
                .queryParam("idList", listId) // Bir önceki adımda aldığımız liste ID'si
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .contentType(ContentType.JSON)
                .when()
                .post("/cards")
                .then()
                .statusCode(200)
                .extract().response();

        cardId1 = response1.path("id");
        test.info("Card 1 created. ID: " + cardId1);
        response1.prettyPrint();


        // --- Create 2.Card ---
        Response response2 = given()
                .queryParam("name", "Cart - 2")
                .queryParam("idList", listId)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .contentType(ContentType.JSON)
                .when()
                .post("/cards")
                .then()
                .statusCode(200)
                .extract().response();

        cardId2 = response2.path("id");
        test.info("Cart 2 Created. ID: " + cardId2);
        response2.prettyPrint(); // Konsola yazdıralım

        test.pass("Two Cart Created Successfully");
        Log.info("Cards created: " + cardId1 + " and " + cardId2);
    }
    @Test
    @Order(4)
    public void updateRandomCard() {
        test = extent.createTest("Trello API - Update Random Card");
        boolean pickFirst = new java.util.Random().nextBoolean();
        String cardToUpdate = pickFirst ? cardId1 : cardId2;
        String cardName = pickFirst ? "Card 1" : "Card2";

        test.info("Selected Card For Update: " + cardName + " (ID: " + cardToUpdate + ")");

        // 2. Seçilen kartı güncelliyoruz (PUT isteği)
        Response response = given()
                .header("Accept", "application/json")
                .queryParam("name", "Card Updated")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .contentType(ContentType.JSON)
                .when()
                .put("/cards/{id}", cardToUpdate) // Path param olarak ID'yi geçiyoruz
                .then()
                .statusCode(200)
                .extract().response();

        response.prettyPrint();
        test.pass("Selected Card Updated Sucessfully");
        Log.info("Updated card ID: " + cardToUpdate);
    }


    @Test
    @Order(5)
    public void deleteCards() {
        test = extent.createTest("Trello API - Delete Cart");

        given()
                .pathParam("id", cardId1)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .delete("/cards/{id}")
                .then()
                .statusCode(200)
                .log().all();

        given()
                .pathParam("id", cardId2)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .delete("/cards/{id}")
                .then()
                .statusCode(200)
                .log().all();

        test.pass("Both Card Deleted Successfully");
        Log.info("Cards deleted: " + cardId1 + " and " + cardId2);
    }
    @Test
    @Order(6) // En son çalışması gereken test
    public void deleteBoardTest() {
        test = extent.createTest("Trello API - Board Silme");
        Response response = given()
                .pathParam("id", boardId)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .delete("/boards/{id}")
                .then()
                .log().all() // Hata alırsan nedenini burada görürsün
                .statusCode(200)
                .extract().response();
        response.prettyPrint();
        test.pass("Board Deleted");
    }
}
