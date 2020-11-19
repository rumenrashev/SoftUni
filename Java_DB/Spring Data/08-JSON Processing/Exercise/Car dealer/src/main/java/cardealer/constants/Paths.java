package cardealer.constants;

public class Paths {

    public static final String MAIN_DIRECTORY = "src/main/resources/json/";

    public static final String IN = "in/";
    public static final String OUT = "out/";

    public static final String JSON = ".json";

    public static final String SUPPLIERS_IN_PATH = MAIN_DIRECTORY + IN + "suppliers" + JSON;
    public static final String CUSTOMERS_IN_PATH = MAIN_DIRECTORY + IN + "customers" + JSON;
    public static final String PARTS_IN_PATH = MAIN_DIRECTORY + IN + "parts"  + JSON;
    public static final String CAR_IN_PATH = MAIN_DIRECTORY + IN + "cars"  +JSON;

    public static final String ORDERED_CUSTOMERS = MAIN_DIRECTORY + OUT + "ordered-customers" + JSON;
    public static final String TOYOTA_CARS = MAIN_DIRECTORY + OUT  + "toyota-cars" + JSON;
    public static final String LOCAL_SUPPLIERS = MAIN_DIRECTORY + OUT + "local-suppliers" + JSON;
    public static final String CARS_PARTS = MAIN_DIRECTORY + OUT + "cars-and-parts" + JSON;
    public static final String CUSTOMERS_TOTAL_SALES = MAIN_DIRECTORY + OUT + "customers-total-sales" + JSON;
    public static final String SALES_DISCOUNTS = MAIN_DIRECTORY + OUT + "sales-discounts" + JSON;

    public static final String EMPTY_DELIMITER = "";
}
