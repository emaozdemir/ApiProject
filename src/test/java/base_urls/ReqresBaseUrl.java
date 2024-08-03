package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class ReqresBaseUrl {

    protected RequestSpecification spec;  // Şu an spec null'dır. Her metoddan önce spec objesine değer atamak istiyorum.

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .build();
    }

    /*
     pathParams Metodu ve Dinamik URL Oluşturma
     * pathParams metodu, URL'nin farklı kısımlarını dinamik olarak ayarlamak için kullanılır.
     * Bu, aynı temel URI'yi (Base URI) kullanarak farklı endpoint'lere istek göndermek istediğinizde çok kullanışlıdır.

      Adım Adım Açıklama:
      1. Temel URI (Base URI) Tanımlama:
         - spec değişkeni içinde setBaseUri("https://reqres.in/api") ile temel URI ayarlanmıştır.
         - Bu temel URI, tüm istekler için başlangıç noktasıdır: https://reqres.in/api.

      2. Path Parameters (Yol Parametreleri) Tanımlama:
        - spec.pathParams("first", "users", "second", 3); metodu ile URL'nin değişken kısımları tanımlanır.
        - Anahtar-değer çiftleri olarak parametreler alır: "first" -> "users", "second" -> 3.

      3. URL'nin Oluşturulması:
         - İstek gönderirken, yol parametreleri yer tutucular ({}) ile belirtilir:
           Response response = given(spec).when().get("{first}/{second}");
         - Bu yer tutucular, pathParams ile tanımlanan parametrelerle doldurulur:
           {first} -> "users", {second} -> 3.
         - Sonuç olarak URL: https://reqres.in/api/users/3.

      Neden Yol Parametreleri Kullanılır?

      1. Esneklik:
         - Aynı temel URI'yi kullanarak farklı endpoint'lere istek gönderebilirsiniz.
           Örneğin: spec.pathParams("first", "users", "second", 3); // https://reqres.in/api/users/3
                     spec.pathParams("first", "users", "second", 5); // https://reqres.in/api/users/5

      2. Kod Tekrarını Azaltma:
         - Aynı kod bloğunu tekrar yazmak yerine, yol parametreleri ile dinamik URL'ler oluşturabilirsiniz.

      3. Okunabilirlik:
         - URL'yi doğrudan yazmak yerine, yer tutucular ve parametrelerle çalışmak kodun okunabilirliğini artırır.
     */
}
