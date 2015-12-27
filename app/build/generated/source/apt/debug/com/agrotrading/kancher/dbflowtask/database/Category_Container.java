package com.agrotrading.kancher.dbflowtask.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;
import com.raizlabs.android.dbflow.structure.container.ModelContainer;
import com.raizlabs.android.dbflow.structure.container.ModelContainerAdapter;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class Category_Container extends ModelContainerAdapter<Category> {
  public Category_Container(DatabaseHolder holder) {
    columnMap.put("id", long.class);
    columnMap.put("name", String.class);
  }

  @Override
  public final Class<Category> getModelClass() {
    return Category.class;
  }

  public final String getTableName() {
    return "`Category`";
  }

  @Override
  public final void bindToInsertValues(ContentValues values, ModelContainer<Category, ?> modelContainer) {
    String modelContainername = modelContainer.getStringValue("name");
    if (modelContainername != null) {
      values.put("`name`", modelContainername);
    } else {
      values.putNull("`name`");
    }
  }

  @Override
  public final void bindToContentValues(ContentValues values, ModelContainer<Category, ?> modelContainer) {
    values.put("`id`", modelContainer.getLngValue("id"));
    bindToInsertValues(values, modelContainer);
  }

  @Override
  public final void bindToInsertStatement(SQLiteStatement statement, ModelContainer<Category, ?> modelContainer, int start) {
    String modelContainername = modelContainer.getStringValue("name");
    if (modelContainername != null) {
      statement.bindString(1 + start, modelContainername);
    } else {
      statement.bindNull(1 + start);
    }
  }

  @Override
  public final void bindToStatement(SQLiteStatement statement, ModelContainer<Category, ?> modelContainer) {
    statement.bindLong(1, modelContainer.getLngValue("id"));
    bindToInsertStatement(statement, modelContainer, 1);
  }

  @Override
  public final boolean exists(ModelContainer<Category, ?> modelContainer) {
    return modelContainer.getLngValue("id") > 0 && new Select(Method.count()).from(Category.class).where(getPrimaryConditionClause(modelContainer)).count() > 0;
  }

  @Override
  public final ConditionGroup getPrimaryConditionClause(ModelContainer<Category, ?> modelContainer) {
    return ConditionGroup.clause().and(Category_Table.id.eq(modelContainer.getLngValue("id")));
  }

  @Override
  public final Category toModel(ModelContainer<Category, ?> modelContainer) {
    Category model = new Category();
    model.id = modelContainer.getLngValue("id");
    model.name = modelContainer.getStringValue("name");
    return model;
  }

  @Override
  public final void loadFromCursor(Cursor cursor, ModelContainer<Category, ?> modelContainer) {
    int indexid = cursor.getColumnIndex("id");
    if (indexid != -1 && !cursor.isNull(indexid)) {
      modelContainer.put("id", cursor.getLong(indexid));
    } else {
      modelContainer.putDefault("id");
    }
    int indexname = cursor.getColumnIndex("name");
    if (indexname != -1 && !cursor.isNull(indexname)) {
      modelContainer.put("name", cursor.getString(indexname));
    } else {
      modelContainer.putDefault("name");
    }
  }

  @Override
  public final ForeignKeyContainer<Category> toForeignKeyContainer(Category model) {
    ForeignKeyContainer modelContainer = new ForeignKeyContainer<>(Category.class);
    modelContainer.put(Category_Table.id, model.id);
    return modelContainer;
  }
}
