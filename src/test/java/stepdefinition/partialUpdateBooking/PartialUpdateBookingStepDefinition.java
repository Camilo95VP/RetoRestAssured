package stepdefinition.partialUpdateBooking;

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
import static util.Paths.REQUEST_JSON_FILE_TOKEN;
import static util.Paths.REQUEST_JSON_FILE_UPDATE_BOOKING;

public class PartialUpdateBookingStepDefinition extends ServiceSetUp {

    private static final Logger LOGGER=Logger.getLogger(PartialUpdateBookingStepDefinition.class);

    private Response response;

    private RequestSpecification request;

    private final File requestJsonFileUpdateBooking=new File(REQUEST_JSON_FILE_UPDATE_BOOKING.getValue());
    private final File requestJsonFileBooking=new File(REQUEST_JSON_FILE_BOOKING.getValue());
    private final File requestJsonFileToken=new File(REQUEST_JSON_FILE_TOKEN.getValue());

    @Dado("que el administrador navegó hasta la sección de actualización de reservas")
    public void navegarHastaSeccion() {
        try{
            generalSetUp();
            request= given()
                    .contentType(ContentType.JSON)
                    .cookie("token",createToken())
                    .body(requestJsonFileUpdateBooking);

        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("el administrador suministre los datos de actualización de reserva y confirme")
    public void suministrarDatos() {
        try{
            response=request.when().patch(RESOURCE_CREATE_BOOKING+"/"+createBooking());
        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @Entonces("el administrador debera ver un código de respuesta exitoso y los datos de la reserva")
    public void verRespuestaExitosa() {
        String nameUpdate=response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("firstname")
                ;
        assertThat(nameUpdate, equalTo("James"));
    }

    private String createBooking(){
        try {
            return given()
                    .contentType(ContentType.JSON)
                    .body(requestJsonFileBooking)
                    .when()
                    .post(RESOURCE_CREATE_BOOKING)
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("bookingid", Matchers.notNullValue())
                    .extract()
                    .path("bookingid").toString();


        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
        return "";
    }

    private String createToken(){
        try{
            return given()
                    .contentType(ContentType.JSON)
                    .body(requestJsonFileToken)
                    .when()
                    .post(RESOURCE_TOKEN)
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token", Matchers.notNullValue())
                    .extract()
                    .path("token");
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
        return "";
    }
}
