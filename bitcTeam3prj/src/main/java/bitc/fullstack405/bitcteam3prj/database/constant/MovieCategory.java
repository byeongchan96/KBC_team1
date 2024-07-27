package bitc.fullstack405.bitcteam3prj.database.constant;

import lombok.Getter;

public enum   MovieCategory {
    CATE_1("Category_1"),
    CATE_2("Category_2"),
    CATE_3("Category_3"),
    CATE_4("Category_4"),
    CATE_5("Category_5");

    @Getter private final String description;

    MovieCategory(String description) {
        this.description = description;
    }
}
