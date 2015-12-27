package com.agrotrading.kancher.dbflowtask.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.util.List;

@Table(database = MagazineDatabase.class)
public class Product extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String name;

    public String category;

    @ForeignKey(saveForeignKeyModel = false)
    ForeignKeyContainer<Category> categoryForeignKeyContainer;

    public void associateCategory(Category category) {
        categoryForeignKeyContainer = FlowManager.getContainerAdapter(Category.class).toForeignKeyContainer(category);

    }

    public static List<Product> getAllProducts() {

        return SQLite.select()
                .from(Product.class)
                .queryList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return categoryForeignKeyContainer.load();
    }
}
