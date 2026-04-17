package se.sprinto.hakan.demospringsecurity.util;

import java.util.List;

public class ProductCategory {
    public static final String COMPUTERS = "Datorer";
    public static final String MONITORS = "Monitorer";
    public static final String ACCESSORIES = "Tillbehör";

    public List<String> getCategories() {
        return List.of(COMPUTERS, MONITORS, ACCESSORIES);
    }
}
