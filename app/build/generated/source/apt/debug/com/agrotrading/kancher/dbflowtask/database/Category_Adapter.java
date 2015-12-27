package com.agrotrading.kancher.dbflowtask.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.raizlabs.android.dbflow.runtime.transaction.process.DeleteModelListTransaction;
import com.raizlabs.android.dbflow.runtime.transaction.process.InsertModelTransaction;
import com.raizlabs.android.dbflow.runtime.transaction.process.ProcessModelInfo;
import com.raizlabs.android.dbflow.runtime.transaction.process.SaveModelTransaction;
import com.raizlabs.android.dbflow.runtime.transaction.process.UpdateModelListTransaction;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import java.lang.Class;
import java.lang.Number;
import java.lang.Override;
import java.lang.String;

public final class Category_Adapter extends ModelAdapter<Category> {
  public Category_Adapter(DatabaseHolder holder) {
  }

  @Override
  public final Class<Category> getModelClass() {
    return Category.class;
  }

  public final String getTableName() {
    return "`Category`";
  }

  public final void updateAutoIncrement(Category model, Number id) {
    model.id = id.longValue();
  }

  @Override
  public final Number getAutoIncrementingId(Category model) {
    return model.id;
  }

  @Override
  public final String getAutoIncrementingColumnName() {
    return "id";
  }

  @Override
  public final void bindToInsertValues(ContentValues values, Category model) {
    if (model.name != null) {
      values.put("`name`", model.name);
    } else {
      values.putNull("`name`");
    }
  }

  @Override
  public final void bindToContentValues(ContentValues values, Category model) {
    values.put("`id`", model.id);
    bindToInsertValues(values, model);
  }

  @Override
  public final void bindToInsertStatement(SQLiteStatement statement, Category model, int start) {
    if (model.name != null) {
      statement.bindString(1 + start, model.name);
    } else {
      statement.bindNull(1 + start);
    }
  }

  @Override
  public final void bindToStatement(SQLiteStatement statement, Category model) {
    statement.bindLong(1, model.id);
    bindToInsertStatement(statement, model, 1);
  }

  @Override
  public final String getInsertStatementQuery() {
    return "INSERT INTO `Category`(`name`) VALUES (?)";
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Category`(`id`,`name`) VALUES (?,?)";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Category`(`id` INTEGER,`name` TEXT, PRIMARY KEY(`id`)" + ");";
  }

  @Override
  public final void loadFromCursor(Cursor cursor, Category model) {
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
    model.getMyProducts();
  }

  @Override
  public final boolean exists(Category model) {
    return model.id > 0 && new Select(Method.count()).from(Category.class).where(getPrimaryConditionClause(model)).count() > 0;
  }

  @Override
  public final ConditionGroup getPrimaryConditionClause(Category model) {
    return ConditionGroup.clause().and(Category_Table.id.eq(model.id));
  }

  @Override
  public final void delete(Category model) {
    new DeleteModelListTransaction<>(ProcessModelInfo.withModels(model.getMyProducts())).onExecute();
    model.products = null;
    super.delete(model);
  }

  @Override
  public final void save(Category model) {
    new SaveModelTransaction<>(ProcessModelInfo.withModels(model.getMyProducts())).onExecute();
    super.save(model);
  }

  @Override
  public final void insert(Category model) {
    new InsertModelTransaction<>(ProcessModelInfo.withModels(model.getMyProducts())).onExecute();
    super.insert(model);
  }

  @Override
  public final void update(Category model) {
    new UpdateModelListTransaction<>(ProcessModelInfo.withModels(model.getMyProducts())).onExecute();
    super.update(model);
  }

  @Override
  public final Category newInstance() {
    return new Category();
  }

  @Override
  public final BaseProperty getProperty(String name) {
    return Category_Table.getProperty(name);
  }
}
