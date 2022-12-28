package stepdefinition.generateBooking;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import setup.ServiceSetUp;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static util.Paths.REQUEST_JSON_FILE_BOOKING;

public class GenerateBookingStepDefinition extends ServiceSetUp {

    private Response response;

    private RequestSpecification request;

    private static final Logger LOGGER = Logger.getLogger(GenerateBookingStepDefinition.class);

    private final File requestJsonFile=new File(REQUEST_JSON_FILE_BOOKING.getValue());

    @Dado("que el administrador navegó hasta la sección de actualización de reservas")
    public void navegarHastaSeccion() {
        try {
            generalSetUp();
            request = given()
                    .contentType(ContentType.JSON)
                    .body(requestJsonFile);

        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("el administrador suministre los datos de la reserva")
    public void suministrarDatos() {
        try{
            response=request
                    .when()
                    .log()
                    .all()
                    .post(RESOURCE_CREATE_BOOKING);
        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @Entonces("se observaran los datos de la reserva exitosa")
    public void observarDatosReservaExitosa() {
        String name=response
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid", Matchers.notNullValue())
                .extract()
                .jsonPath()
                .getString("booking.firstname");

        assertThat(name, equalTo("Jim"));
    }
}
