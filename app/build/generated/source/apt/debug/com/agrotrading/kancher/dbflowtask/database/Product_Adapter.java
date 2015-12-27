package com.agrotrading.kancher.dbflowtask.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import java.lang.Class;
import java.lang.Number;
import java.lang.Override;
import java.lang.String;

public final class Product_Adapter extends ModelAdapter<Product> {
  public Product_Adapter(DatabaseHolder holder) {
  }

  @Override
  public final Class<Product> getModelClass() {
    return Product.class;
  }

  public final String getTableName() {
    return "`Product`";
  }

  public final void updateAutoIncrement(Product model, Number id) {
    model.id = id.longValue();
  }

  @Override
  public final Number getAutoIncrementingId(Product model) {
    return model.id;
  }

  @Override
  public final String getAutoIncrementingColumnName() {
    return "id";
  }

  @Override
  public final void bindToInsertValues(ContentValues values, Product model) {
    if (model.name != null) {
      values.put("`name`", model.name);
    } else {
      values.putNull("`name`");
    }
    if (model.categoryForeignKeyContainer != null) {
      values.put("`categoryForeignKeyContainer_id`", model.categoryForeignKeyContainer.getLngValue("id"));
    } else {
      values.putNull("`categoryForeignKeyContainer_id`");
    }
  }

  @Override
  public final void bindToContentValues(ContentValues values, Product model) {
    values.put("`id`", model.id);
    bindToInsertValues(values, model);
  }

  @Override
  public final void bindToInsertStatement(SQLiteStatement statement, Product model, int start) {
    if (model.name != null) {
      statement.bindString(1 + start, model.name);
    } else {
      statement.bindNull(1 + start);
    }
    if (model.categoryForeignKeyContainer != null) {
      statement.bindLong(2 + start, model.categoryForeignKeyContainer.getLngValue("id"));
    } else {
      statement.bindNull(2 + start);
    }
  }

  @Override
  public final void bindToStatement(SQLiteStatement statement, Product model) {
    statement.bindLong(1, model.id);
    bindToInsertStatement(statement, model, 1);
  }

  @Override
  public final String getInsertStatementQuery() {
    return "INSERT INTO `Product`(`name`,`categoryForeignKeyContainer_id`) VALUES (?,?)";
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Product`(`id`,`name`,`categoryForeignKeyContainer_id`) VALUES (?,?,?)";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Product`(`id` INTEGER,`name` TEXT,`categoryForeignKeyContainer_id` INTEGER, PRIMARY KEY(`id`)"+ ", FOREIGN KEY(`categoryForeignKeyContainer_id`) REFERENCES " + FlowManager.getTableName(Category.class) + "(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION" + ");";
  }

  @Override
  public final void loadFromCursor(Cursor cursor, Product model) {
    int indexid = cursor.getColumnIndex("id");
    if (indexid != -1 && !cursor.isNull(indexid)) {
      model.id = cursor.getLong(indexid);
    } else {
      model.id = 0;
    }
    int indexname = cursor.getColumnIndex("name");
    if (indexname != -1 && !cursor.isNull(indexname)) {
      model.name = cursor.getString(indexname);
    } else {
      model.name = null;
    }
    //// Only load model if references match, for efficiency
    int indexcategoryForeignKeyContainer_id = cursor.getColumnIndex("categoryForeignKeyContainer_id");
    if (indexcategoryForeignKeyContainer_id != -1 && !cursor.isNull(indexcategoryForeignKeyContainer_id)) {
      model.categoryForeignKeyContainer = new com.raizlabs.android.dbflow.sql.language.Select().from(com.agrotrading.kancher.dbflowtask.database.Category.class).where()
          .and(com.agrotrading.kancher.dbflowtask.database.Category_Table.id.eq(cursor.getLong(indexcategoryForeignKeyContainer_id))).queryModelContainer(new com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer<com.agrotrading.kancher.dbflowtask.database.Category>(com.agrotrading.kancher.dbflowtask.database.Category.class));
    }
  }

  @Override
  public final boolean exists(Product model) {
    return model.id > 0 && new Select(Method.count()).from(Product.class).where(getPrimaryConditionClause(model)).count() > 0;
  }

  @Override
  public final ConditionGroup getPrimaryConditionClause(Product model) {
    return ConditionGroup.clause().and(Product_Table.id.eq(model.id));
  }

  @Override
  public final Product newInstance() {
    return new Product();
  }

  @Override
  public final BaseProperty getProperty(String name) {
    return Product_Table.getProperty(name);
  }
}
