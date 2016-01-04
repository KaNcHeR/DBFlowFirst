package com.raizlabs.android.dbflow.config;

import com.agrotrading.kancher.dbflowtask.database.Category;
import com.agrotrading.kancher.dbflowtask.database.Category_Adapter;
import com.agrotrading.kancher.dbflowtask.database.Category_Container;
import com.agrotrading.kancher.dbflowtask.database.Product;
import com.agrotrading.kancher.dbflowtask.database.Product_Adapter;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class MagazineDatabaseMagazine_Database extends BaseDatabaseDefinition {
  public MagazineDatabaseMagazine_Database(DatabaseHolder holder) {
    holder.putDatabaseForTable(Category.class, this);
    holder.putDatabaseForTable(Product.class, this);
    models.add(Category.class);
    modelTableNames.put("Category", Category.class);
    modelAdapters.put(Category.class, new Category_Adapter(holder));
    models.add(Product.class);
    modelTableNames.put("Product", Product.class);
    modelAdapters.put(Product.class, new Product_Adapter(holder));
    modelContainerAdapters.put(Category.class, new Category_Container(holder));
  }

  @Override
  public final FlowSQLiteOpenHelper createHelper() {
    return new FlowSQLiteOpenHelper(this, internalHelperListener);
  }

  @Override
  public final boolean isForeignKeysSupported() {
    return false;
  }

  @Override
  public final boolean isInMemory() {
    return false;
  }

  @Override
  public final boolean backupEnabled() {
    return false;
  }

  @Override
  public final boolean areConsistencyChecksEnabled() {
    return false;
  }

  @Override
  public final int getDatabaseVersion() {
    return 1;
  }

  @Override
  public final String getDatabaseName() {
    return "Magazine";
  }
}
