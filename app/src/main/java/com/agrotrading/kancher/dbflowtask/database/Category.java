package com.agrotrading.kancher.dbflowtask.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

@ModelContainer
@Table(database = MagazineDatabase.class)
public class Category extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String name;

    List<Product> products;

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "products")
    public List<Product> getMyProducts() {
        if (products == null || products.isEmpty()) {
            products = SQLite.select()
                    .from(Product.class)
                    .where(Product_Table.categoryForeignKeyContainer_id.eq(id))
                    .queryList();
        }
        return products;
    }

    public static List<Category> getAllCategories() {

        return SQLite.select()
                .from(Category.class)
                .queryList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
