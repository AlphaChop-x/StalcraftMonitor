package manakin.ru.stalcraftmonitor.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import manakin.ru.stalcraftmonitor.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import manakin.ru.stalcraftmonitor.entity.Item;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemRestControllerTest {

    @LocalServerPort
    private int port;

    @Mock
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void addItemTest() {
        Item testItem = new Item("1k4g", "Атом", "Артефакт", "Очень полезный артефакт");

        Mockito.when(itemService.createItem(testItem))
                .thenReturn(testItem);

        given()
                .contentType(ContentType.JSON)
                .body(testItem)
                .when()
                .post("/monitor/items")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", equalTo("1k4g"))
                .body("name", equalTo("Атом"))
                .body("category", equalTo("Артефакт"));
    }

    @Test
    void addItemTestFailure() {
        Item existingItem = new Item("1k4g", "Атом", "Артефакт", "Очень полезный артефакт");

        Mockito.when(itemService.createItem(existingItem))
                .thenThrow(new ResourceNotFoundException("Item already exists!"));

        given()
                .contentType(ContentType.JSON)
                .body(existingItem)
                .when()
                .post("/monitor/items")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void addItemTestBadItem() {
        given()
                .contentType(ContentType.JSON)
                .body("{}") // Пустое тело
                .when()
                .post("/monitor/items")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}