package homework;

import base_urls.SpaceXDataBaseUrl;
import org.testng.annotations.Test;

public class HW010  extends SpaceXDataBaseUrl {
       /*
Given
    https://api.spacexdata.com/v3/launches
When
    User sends GET request
Then
    HTTP Status Code should be 200
And
    There are more than 100 launches
And
    "FalconSat" and "Starlink 4" are among the mission names
And
    The earliest launch year is 2006
And
    The latest launch year is 2020
And
    The number of successful launches is greater than 50
 */
    @Test
    public void test01() {
        spec.pathParams("first","launches");

    }
}
