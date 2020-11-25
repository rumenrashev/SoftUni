package hiberspring.common;

public final class Constants {

    public final static String PATH_TO_FILES = System.getProperty("user.dir") + "/src/main/resources/files/";

    public final static String INCORRECT_DATA_MESSAGE = "Error: Invalid data.";

    public final static String SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s %s.";

    public static final String BRANCHES_PATH = PATH_TO_FILES + "branches.json";
    public static final String EMPLOYEE_CARDS_PATH = PATH_TO_FILES + "employee-cards.json";
    public static final String EMPLOYEES_PATH = PATH_TO_FILES + "employees.xml";
    public static final String PRODUCTS_PATH = PATH_TO_FILES + "products.xml";
    public static final String TOWNS_PATH = PATH_TO_FILES + "towns.json";

    public static final String TOWN = "Town";
    public static final String BRANCH = "Branch";
    public static final String EMPLOYEE_CARD = "Employee Card";
    public static final String PRODUCT = "Product";
    public static final String EMPLOYEE = "Employee";

    public static final String EXTRACT_MESSAGE =
            "\nName: %s\n" +
                    "\tPosition: %s\n" +
                    "\tCard Number: %s\n";

    public static final String LINE_SEPARATOR = "-------------------------";
}
