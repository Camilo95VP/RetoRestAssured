package stepdefinition.generateAuthorization;

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
import static util.Paths.REQUEST_JSON_FILE_TOKEN;

public class GenerateAuthorizationStepDefinition extends ServiceSetUp {

    private Response response;

    private RequestSpecification request;

    private static final Logger LOGGER = Logger.getLogger(GenerateAuthorizationStepDefinition.class);

    private final File requestJsonFile=new File(REQUEST_JSON_FILE_TOKEN.getValue());

    @Dado("que el administrador navegó hasta la sección de autorizaciones")
    public void navegarHastaSeccionDeAutorizaciones() {
        try{
            generalSetUp();
            request= given()
                    .contentType(ContentType.JSON)
                    .body(requestJsonFile);

        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("el administrador suministre los datos de autorización y confirme")
    public void suministrarDatos() {
        try{
            response=request
                    .when()
                    .log()
                    .all()
                    .post(RESOURCE_TOKEN);
        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @Entonces("el administrador debera ver un código de respuesta exitoso y un token")
    public void validarCodigoDeRespuesta() {
        response
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("token", Matchers.notNullValue());
    }
}
