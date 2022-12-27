package setup;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Assertions;

import static util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class ServiceSetUp {

    private static final String BASE_URI="https://restful-booker.herokuapp.com";

    protected static final String RESOURCE_CREATE_BOOKING="/booking";

    protected static final String RESOURCE_TOKEN="/auth";

    private static final Logger LOGGER=Logger.getLogger(ServiceSetUp.class);

    protected void generalSetUp(){
        try{
            setUpLog4j2();
            setUpBase();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }

    }

    protected void setUpLog4j2(){
        PropertyConfigurator.configure(System.getProperty("user.dir")+LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    private void setUpBase(){
        RestAssured.baseURI=BASE_URI;
    }
}
