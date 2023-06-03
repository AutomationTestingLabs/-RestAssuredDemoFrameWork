package api.tests;

import api.endpoints.UsersEndPoints;
import api.payloads.User;
import api.utils.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersTests{
    User payload;
    Logger logger;
    @Test(priority = 1 , dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testCreateUser(String id, String username,
                               String fname, String lname, String email,
                               String password, String ph){
        logger = LogManager.getLogger(getClass());
        logger.info("-----------------create user start---------------");
        payload = new User();
        payload.setId(Integer.parseInt(id));
        payload.setUsername(username);
        payload.setFirstname(fname);
        payload.setLastname(lname);
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setPhone(ph);
        Response response = UsersEndPoints.createUser(payload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("-----------------create user completed---------------");
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void updateUserTest(String username){
        logger.info("-----------------update user start---------------");
        payload = new User();
        payload.setFirstname("navi");
        payload.setLastname("hans");
        payload.setEmail("t@gmail.com");
        Response response = UsersEndPoints.updateUser(username, payload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("-----------------update user end---------------");
    }

    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUser(String username){
        logger.info("-----------------read user start---------------");
        Response response = UsersEndPoints.getUser(username);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("-----------------read user end---------------");
    }
}
