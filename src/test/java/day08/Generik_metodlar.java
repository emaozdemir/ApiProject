package day08;

public class Generik_metodlar {

    public static void main(String[] args) {
        Generik_metodlar test = new Generik_metodlar();


        // String türünde değer
        String resultString = test.getValue("Hello");
        System.out.println(resultString); // Çıktı: Hello

        // int türünde değer
        int resultInt = test.getValue(174);
        System.out.println(resultInt); // Çıktı: 174

        // Boolean türünde değer
        Boolean resultBoolean = test.getValue(true);
        System.out.println(resultBoolean); // Çıktı: true
    }

    public  <T> T getValue(T input) {
        return input;

            /*
    <T> Nedir?

        <T> bir tür parametresidir. T yerine başka harfler de kullanılabilir
        (E, K, V, vb.), ancak genellikle T (Type) kullanılır.
        Bu, bir sınıfın veya metodun belirli bir türle çalışmasını sağlar,
        fakat hangi tür olduğunu kodu yazarken bilmeniz gerekmez. Türü, kodu
        çalıştırdığınızda belirleyeceksiniz.


    T Nedir?

         T burada jenerik tür anlamına gelir. T, String, Integer, List gibi herhangi
          bir Java türü olabilir.
         Yani, T'nin yerine istediğiniz türü koyabilirsiniz.
                 */
    }
}