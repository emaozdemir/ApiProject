package day02;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersMethods {

    @Test
    public void commonHamcrestMethods() {

/*
    Temel Eşleştiriciler:

    is(value):            İncelenen nesne belirtilen değere eşitse eşleşir.
    equalTo(value):       is(value) ile aynı.
    not(value):           İncelenen nesne belirtilen değere eşit değilse eşleşir.
    nullValue():          İncelenen nesne null ise eşleşir.
    notNullValue():       İncelenen nesne null değilse eşleşir.

    Mantıksal Eşleştiriciler:

    allOf(matcher1, matcher2, ...):     Belirtilen tüm eşleştiriciler eşleşirse eşleşir.
    anyOf(matcher1, matcher2, ...):     Belirtilen eşleştiricilerden herhangi biri eşleşirse eşleşir.
    not(matcher):                       Belirtilen eşleştirici eşleşmezse eşleşir.

    Metin Eşleştiriciler:

    containsString(substring):          İncelenen string belirtilen alt string'i içeriyorsa eşleşir.
    startsWith(prefix):                 İncelenen string belirtilen önek ile başlıyorsa eşleşir.
    endsWith(suffix):                   İncelenen string belirtilen son ek ile bitiyorsa eşleşir.

    Sayı Eşleştiriciler:

    closeTo(expected, delta):           İncelenen değer, beklenen değerin belirli bir delta aralığında ise eşleşir.
    greaterThan(value), greaterThanOrEqualTo(value): İncelenen değer belirtilen değerden büyük veya eşitse eşleşir.
    lessThan(value), lessThanOrEqualTo(value):       İncelenen değer belirtilen değerden küçük veya eşitse eşleşir.

    Koleksiyon Eşleştiriciler:

    hasItem(value):                 İncelenen iterable, belirtilen değere eşit en az bir öğeye sahipse eşleşir.
    hasItems(value1, value2, ...):  İncelenen iterable, her belirtilen değere eşit en az bir öğeye sahipse eşleşir.
    hasSize(matcher):               Koleksiyondaki eşleştiricilerin boyutunu kontrol eder.

    Nesne Eşleştiriciler:

    equalToIgnoringCase(string):    İncelenen string, büyük/küçük harf farkı gözetmeksizin belirtilen string'e eşitse eşleşir.
    sameInstance(expected):         İncelenen nesne, belirtilen nesnenin aynı örneği ise eşleşir.
    hasProperty(propertyName):      İncelenen nesne, belirtilen isme sahip bir JavaBean özelliğine sahipse eşleşir.

    Bunlar sadece yaygın olarak kullanılan bazı Hamcrest eşleştiricileri ve yöntemleridir. Hamcrest, testlerinizde ifade edici ve okunabilir doğrulamalar oluşturmanızı sağlayan zengin bir eşleştirici seti sunar.
*/



        //i) Set the Url
        String url ="https://jsonplaceholder.typicode.com/todos";

        //ii) Set the Expected Data
        //iii) Send Request And Get Response
        Response response = given().accept(ContentType.JSON).when().get(url);
        response.prettyPrint();

        //iv)  Do Assertions
        response.then()
                .statusCode(200)
                .body("[0].title",equalTo("delectus aut autem")
                        ,"[0].title",is("delectus aut autem")
                        ,"[0].title",not("Sda ")
                        ,"[0].title",not(nullValue())
                        ,"[0].title",anyOf(equalTo("delectus aut autema"), isA(String.class))
                        ,"[0].title",containsString("aut")
                        ,"[0].title",startsWith("delectus"));




//           int actual = 42;
//           int expected = 42;

/// Both of these assertions are equivalent
//           assertThat(actual, is(expected));
//           assertThat(actual, equalTo(expected));


//           String actual1 = "Hello";
//           String unexpected = "World";

/// Assert that actual is not equal to "World"
//           assertThat(actual1, not(unexpected));

//           Object nullObject = null;
//           Object nonNullObject = new Object();

/// Assert that the object is null
//           assertThat(nullObject, nullValue());

/// Assert that the object is not null
//           assertThat(nonNullObject, notNullValue());


//           String actual4 = "Hello";

/// Assert that actual is either equal to "Hello" or is an integer
//           assertThat(actual4, anyOf(equalTo("Hello"), isA(Integer.class)));

/// Assert that actual is not equal to 43
//           assertThat(actual, not(equalTo(43)));

//           String sentence = "This is a sentence.";

/// Assert that the sentence contains the word "is"
//           assertThat(sentence, containsString("is"));

/// Assert that the sentence starts with "This"
//           assertThat(sentence, startsWith("This"));

/// Assert that the sentence ends with "sentence."
//           assertThat(sentence, endsWith("sentence."));

//           String actualStr = "hello";
//           String expectedStr = "HELLO";

/// Assert that actual is equal to "HELLO" ignoring case
//           assertThat(actualStr, equalToIgnoringCase(expectedStr));


//           double actual3 = 3.0;
//           double expected3 = 3.2;
//           double delta = 0.3;

/// Assert that actual is close to 3.2 with a delta of 0.3
//           assertThat(actual3, closeTo(expected3, delta));

/// Assert that actual is greater than 4
//           assertThat(actual, greaterThan(41));

/// Assert that actual is greater than or equal to 5
//           assertThat(actual, greaterThanOrEqualTo(42));

/// Assert that actual is less than 6
//           assertThat(actual, lessThan(46));

/// Assert that actual is less than or equal to 5
//           assertThat(actual, lessThanOrEqualTo(45));

//           List<String> names = Arrays.asList("John", "Doe", "Jane");

/// Assert that the list has the item "Doe"
//           assertThat(names, hasItem("Doe"));

/// Assert that the list has both "John" and "Jane"
//           assertThat(names, hasItems("John", "Jane"));


//           class Person {
//               private String name;

//               public Person(String name) {
//                   this.name = name;
//               }

//               public String getName() {
//                   return name;
//               }
//           }

//           Person person = new Person("John");

/// Assert that the person object has a property named "name"
//           assertThat(person, hasProperty("name"));


    }

}