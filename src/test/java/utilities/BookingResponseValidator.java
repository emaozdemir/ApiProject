package utilities;

import org.testng.Assert;
import pojos.BookingPojo;

public class BookingResponseValidator {
    public static void validateResponse(BookingPojo actualData, BookingPojo expectedData){
        Assert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        Assert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        Assert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        Assert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        Assert.assertEquals(actualData.getBookingdates().getCheckin(),expectedData.getBookingdates().getCheckin());
        Assert.assertEquals(actualData.getBookingdates().getCheckout(),expectedData.getBookingdates().getCheckout());
        Assert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());
    }
}
