// part 2

public class StringConversion
{
    public static void main(String[] args)
    {
        String value1 = "12345";
        int intValue = Integer.parseInt(value1);
        System.out.println("intValue = " + intValue);

        String value2 = "12.345";
        // Convert value2 to a double here
        double doubleValue = Double.parseDouble(value2);
        // Print the converted value
        System.out.println("doubleValue = " + doubleValue);

        String value3 = "9876543210";
        // Convert value3 to a long here
        long longValue = Long.parseLong(value3);
        // Print the converted value
        System.out.println("longValue = " + longValue);

        String value4 = "321";
        // Convert value4 to a short here
        short shortValue = Short.parseShort(value4);
        // Print the converted value
        System.out.println("shortValue = " + shortValue);

        String value5 = "-28";
        // Convert value5 to a byte here
        byte byteValue = Byte.parseByte(value5);
        // Print the converted value
        System.out.println("byteValue = " + byteValue);

        String value6 = "-45.237";
        // Convert value6 to a float here
        float floatValue = Float.parseFloat(value6);
        // Print the converted value
        System.out.println("floatValue = " + floatValue);

        String value7 = "3BABE";
        //Assume that value7 contains a hexadecimal number. Convert it to an int here
        int hexValue = Integer.parseInt(value7, 16);
        //Print the converted value
        System.out.println("hexValue = " + hexValue);

        String value8 = "11001";
        //Assume that value8 contains a binary number. Convert it to an int here
        int binValue = Integer.parseInt(value8, 2);
        //Print the converted value
        System.out.println("binValue = " + binValue);
    }
}
