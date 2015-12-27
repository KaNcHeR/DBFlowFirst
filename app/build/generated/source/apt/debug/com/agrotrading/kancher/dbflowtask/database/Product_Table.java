package com.agrotrading.kancher.dbflowtask.database;

import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.sql.language.property.LongProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import java.lang.IllegalArgumentException;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class Product_Table {
  public static final LongProperty id = new LongProperty(Product.class, "id");

  public static final Property<String> name = new Property<String>(Product.class, "name");

  public static final LongProperty categoryForeignKeyContainer_id = new LongProperty(Product.class, "categoryForeignKeyContainer_id");

  public static BaseProperty getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch (columnName)  {
      case "`id`":  {
        return id;
      }
      case "`name`":  {
        return name;
      }
      case "categoryForeignKeyContainer_id":  {
        return categoryForeignKeyContainer_id;
      }
      default:  {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }
}
