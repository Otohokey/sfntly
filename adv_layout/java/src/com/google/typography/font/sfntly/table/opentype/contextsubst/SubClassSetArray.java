package com.google.typography.font.sfntly.table.opentype.contextsubst;

import com.google.typography.font.sfntly.data.ReadableFontData;
import com.google.typography.font.sfntly.table.opentype.ClassDefTable;
import com.google.typography.font.sfntly.table.opentype.CoverageTable;
import com.google.typography.font.sfntly.table.opentype.component.OffsetRecordTable;
import com.google.typography.font.sfntly.table.opentype.component.VisibleBuilder;

public class SubClassSetArray extends OffsetRecordTable<SubClassSet> {
  public static final int FIELD_COUNT = 2;

  public static final int COVERAGE_INDEX = 0;
  public static final int COVERAGE_DEFAULT = 0;
  public static final int CLASS_DEF_INDEX = 1;
  public static final int CLASS_DEF_DEFAULT = 0;

  public final CoverageTable coverage;
  public final ClassDefTable classDef;

  public SubClassSetArray(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
    int coverageOffset = getField(COVERAGE_INDEX);
    coverage = new CoverageTable(data.slice(coverageOffset), 0, dataIsCanonical);
    int classDefOffset = getField(CLASS_DEF_INDEX);
    classDef = new ClassDefTable(data.slice(classDefOffset), 0, dataIsCanonical);
  }

  @Override
  public SubClassSet readSubTable(ReadableFontData data, boolean dataIsCanonical) {
    return new SubClassSet(data, 0, dataIsCanonical);
  }

  public static class Builder extends OffsetRecordTable.Builder<SubClassSetArray, SubClassSet> {

    public Builder() {
      super();
    }

    public Builder(ReadableFontData data, boolean dataIsCanonical, boolean isFmt2) {
      super(data, dataIsCanonical);
    }

    public Builder(SubClassSetArray table) {
      super(table);
    }

    @Override
    protected SubClassSetArray readTable(ReadableFontData data, int base, boolean dataIsCanonical) {
      return new SubClassSetArray(data, base, dataIsCanonical);
    }

    @Override
    protected VisibleBuilder<SubClassSet> createSubTableBuilder() {
      return new SubClassSet.Builder();
    }

    @Override
    protected VisibleBuilder<SubClassSet> createSubTableBuilder(
        ReadableFontData data, boolean dataIsCanonical) {
      return new SubClassSet.Builder(data, dataIsCanonical);
    }

    @Override
    protected VisibleBuilder<SubClassSet> createSubTableBuilder(SubClassSet subTable) {
      return new SubClassSet.Builder(subTable);
    }

    @Override
    protected void initFields() {
      setField(COVERAGE_INDEX, COVERAGE_DEFAULT);
      setField(CLASS_DEF_INDEX, CLASS_DEF_DEFAULT);
    }

    @Override
    public int fieldCount() {
      return FIELD_COUNT;
    }
  }

  @Override
  public int fieldCount() {
    return FIELD_COUNT;
  }
}
