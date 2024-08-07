package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class RestFulBookerBaseUrl {

    protected RequestSpecification spec;  // nulldir. Her methoddan önce spec objesine değer atamak istiyorum

    @BeforeMethod
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setContentType(ContentType.JSON)
                .build();
    }

    /*
    Bu sınıfın setUp() metodu, her test methodundan önce çalışır
    ve RequestSpecBuilder sınıfından bir RequestSpecification nesnesi üretilir.
    Bu nesne, testlerde kullanılacak olan HTTP isteklerinin temel yapılandırmasını içerir.
    */

    /*
    Bu projede TestNG ve RestAssured kütüphanelerinden faydalanıyoruz.

    RestAssured:
    - Java ile API testlerini kolaylaştıran bir kütüphanedir.
    - HTTP isteklerini (GET, POST, PUT, DELETE vb.) yapmayı sağlar.
    - RequestSpecification ile isteklerin temel özelliklerini (base URI, path parameters, query parameters, headers, cookies vb.) tanımlar.
    - HTTP yanıtlarının çeşitli bölümlerini doğrulamak için metotlar sağlar (statusCode(), contentType(), body() vb.).
    - JSON ve XML formatındaki yanıtlarla çalışmayı destekler.

    TestNG:
    - Java dilinde test yazmayı ve çalıştırmayı sağlayan bir test framework'üdür.
    - Testleri organize eder ve yaşam döngüsünü yönetir.
    - Anotasyonlar (@Test, @BeforeMethod, @AfterMethod, @BeforeClass, @AfterClass vb.) ile testlerin çalışma sırasını ve bağımlılıklarını kontrol eder.
    - Doğrulamalar (Assertions) için Assert metotlarını kullanır (Assert.assertEquals(), Assert.assertTrue() vb.).
    - Test sonuçlarını HTML veya XML formatında raporlar.
    - Veri sağlayıcılar (Data Providers) ile parametrizasyon sunar.
    - Paralel test çalıştırmayı destekler.

    TestNG Anotasyonları:
    @Test: Test metodu olduğunu belirtir.
    @BeforeMethod: Her test metodundan önce çalışacak metodu belirtir.
    @AfterMethod: Her test metodundan sonra çalışacak metodu belirtir.
    @BeforeClass: Sınıftaki tüm test metotlarından önce çalışacak metodu belirtir.
    @AfterClass: Sınıftaki tüm test metotlarından sonra çalışacak metodu belirtir.
    */
}
