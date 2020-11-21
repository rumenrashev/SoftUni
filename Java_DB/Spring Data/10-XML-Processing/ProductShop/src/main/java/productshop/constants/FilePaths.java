package productshop.constants;

public class FilePaths {

    public static final String DIRECTORY = "src/main/resources/xml/";

    public static final String IN = "in/";
    public static final String OUT = "out/";

    public static final String XML = ".xml";

    public static final String USERS_IN = DIRECTORY + IN + "users" + XML;
    public static final String CATEGORIES_IN = DIRECTORY + IN + "categories" + XML;
    public static final String PRODUCTS_IN = DIRECTORY + IN + "products" + XML;

    public static final String PRODUCTS_INT_RANGE = DIRECTORY + OUT +"products-in-range" + XML;
    public static final String USERS_SOLD_PRODUCTS = DIRECTORY + OUT + "users-sold-products" +  XML;
    public static final String CATEGORIES_BY_PRODUCTS = DIRECTORY + OUT + "categories-by-products" +  XML;
    public static final String USERS_AND_PRODUCTS = DIRECTORY + OUT + "users-and-products" + XML;



}
