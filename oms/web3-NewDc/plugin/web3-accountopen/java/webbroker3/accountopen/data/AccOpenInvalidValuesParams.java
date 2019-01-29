head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.17.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenInvalidValuesParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * acc_open_invalid_valuesテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccOpenInvalidValuesRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccOpenInvalidValuesParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccOpenInvalidValuesParams}が{@@link AccOpenInvalidValuesRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenInvalidValuesPK 
 * @@see AccOpenInvalidValuesRow 
 */
public class AccOpenInvalidValuesParams extends Params implements AccOpenInvalidValuesRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_invalid_values";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccOpenInvalidValuesRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccOpenInvalidValuesRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>acc_open_request_number</em>カラムの値 
   */
  public  String  acc_open_request_number;

  /** 
   * <em>item_name1</em>カラムの値 
   */
  public  String  item_name1;

  /** 
   * <em>comment1</em>カラムの値 
   */
  public  String  comment1;

  /** 
   * <em>comp_flag1</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag1;

  /** 
   * <em>item_name2</em>カラムの値 
   */
  public  String  item_name2;

  /** 
   * <em>comment2</em>カラムの値 
   */
  public  String  comment2;

  /** 
   * <em>comp_flag2</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag2;

  /** 
   * <em>item_name3</em>カラムの値 
   */
  public  String  item_name3;

  /** 
   * <em>comment3</em>カラムの値 
   */
  public  String  comment3;

  /** 
   * <em>comp_flag3</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag3;

  /** 
   * <em>item_name4</em>カラムの値 
   */
  public  String  item_name4;

  /** 
   * <em>comment4</em>カラムの値 
   */
  public  String  comment4;

  /** 
   * <em>comp_flag4</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag4;

  /** 
   * <em>item_name5</em>カラムの値 
   */
  public  String  item_name5;

  /** 
   * <em>comment5</em>カラムの値 
   */
  public  String  comment5;

  /** 
   * <em>comp_flag5</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag5;

  /** 
   * <em>item_name6</em>カラムの値 
   */
  public  String  item_name6;

  /** 
   * <em>comment6</em>カラムの値 
   */
  public  String  comment6;

  /** 
   * <em>comp_flag6</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag6;

  /** 
   * <em>item_name7</em>カラムの値 
   */
  public  String  item_name7;

  /** 
   * <em>comment7</em>カラムの値 
   */
  public  String  comment7;

  /** 
   * <em>comp_flag7</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag7;

  /** 
   * <em>item_name8</em>カラムの値 
   */
  public  String  item_name8;

  /** 
   * <em>comment8</em>カラムの値 
   */
  public  String  comment8;

  /** 
   * <em>comp_flag8</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag8;

  /** 
   * <em>item_name9</em>カラムの値 
   */
  public  String  item_name9;

  /** 
   * <em>comment9</em>カラムの値 
   */
  public  String  comment9;

  /** 
   * <em>comp_flag9</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag9;

  /** 
   * <em>item_name10</em>カラムの値 
   */
  public  String  item_name10;

  /** 
   * <em>comment10</em>カラムの値 
   */
  public  String  comment10;

  /** 
   * <em>comp_flag10</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  comp_flag10;

  /** 
   * <em>note1</em>カラムの値 
   */
  public  String  note1;

  /** 
   * <em>note2</em>カラムの値 
   */
  public  String  note2;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean acc_open_request_number_is_set = false;
  private boolean acc_open_request_number_is_modified = false;
  private boolean item_name1_is_set = false;
  private boolean item_name1_is_modified = false;
  private boolean comment1_is_set = false;
  private boolean comment1_is_modified = false;
  private boolean comp_flag1_is_set = false;
  private boolean comp_flag1_is_modified = false;
  private boolean item_name2_is_set = false;
  private boolean item_name2_is_modified = false;
  private boolean comment2_is_set = false;
  private boolean comment2_is_modified = false;
  private boolean comp_flag2_is_set = false;
  private boolean comp_flag2_is_modified = false;
  private boolean item_name3_is_set = false;
  private boolean item_name3_is_modified = false;
  private boolean comment3_is_set = false;
  private boolean comment3_is_modified = false;
  private boolean comp_flag3_is_set = false;
  private boolean comp_flag3_is_modified = false;
  private boolean item_name4_is_set = false;
  private boolean item_name4_is_modified = false;
  private boolean comment4_is_set = false;
  private boolean comment4_is_modified = false;
  private boolean comp_flag4_is_set = false;
  private boolean comp_flag4_is_modified = false;
  private boolean item_name5_is_set = false;
  private boolean item_name5_is_modified = false;
  private boolean comment5_is_set = false;
  private boolean comment5_is_modified = false;
  private boolean comp_flag5_is_set = false;
  private boolean comp_flag5_is_modified = false;
  private boolean item_name6_is_set = false;
  private boolean item_name6_is_modified = false;
  private boolean comment6_is_set = false;
  private boolean comment6_is_modified = false;
  private boolean comp_flag6_is_set = false;
  private boolean comp_flag6_is_modified = false;
  private boolean item_name7_is_set = false;
  private boolean item_name7_is_modified = false;
  private boolean comment7_is_set = false;
  private boolean comment7_is_modified = false;
  private boolean comp_flag7_is_set = false;
  private boolean comp_flag7_is_modified = false;
  private boolean item_name8_is_set = false;
  private boolean item_name8_is_modified = false;
  private boolean comment8_is_set = false;
  private boolean comment8_is_modified = false;
  private boolean comp_flag8_is_set = false;
  private boolean comp_flag8_is_modified = false;
  private boolean item_name9_is_set = false;
  private boolean item_name9_is_modified = false;
  private boolean comment9_is_set = false;
  private boolean comment9_is_modified = false;
  private boolean comp_flag9_is_set = false;
  private boolean comp_flag9_is_modified = false;
  private boolean item_name10_is_set = false;
  private boolean item_name10_is_modified = false;
  private boolean comment10_is_set = false;
  private boolean comment10_is_modified = false;
  private boolean comp_flag10_is_set = false;
  private boolean comp_flag10_is_modified = false;
  private boolean note1_is_set = false;
  private boolean note1_is_modified = false;
  private boolean note2_is_set = false;
  private boolean note2_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "acc_open_request_number=" + acc_open_request_number
      + "," + "item_name1=" +item_name1
      + "," + "comment1=" +comment1
      + "," + "comp_flag1=" +comp_flag1
      + "," + "item_name2=" +item_name2
      + "," + "comment2=" +comment2
      + "," + "comp_flag2=" +comp_flag2
      + "," + "item_name3=" +item_name3
      + "," + "comment3=" +comment3
      + "," + "comp_flag3=" +comp_flag3
      + "," + "item_name4=" +item_name4
      + "," + "comment4=" +comment4
      + "," + "comp_flag4=" +comp_flag4
      + "," + "item_name5=" +item_name5
      + "," + "comment5=" +comment5
      + "," + "comp_flag5=" +comp_flag5
      + "," + "item_name6=" +item_name6
      + "," + "comment6=" +comment6
      + "," + "comp_flag6=" +comp_flag6
      + "," + "item_name7=" +item_name7
      + "," + "comment7=" +comment7
      + "," + "comp_flag7=" +comp_flag7
      + "," + "item_name8=" +item_name8
      + "," + "comment8=" +comment8
      + "," + "comp_flag8=" +comp_flag8
      + "," + "item_name9=" +item_name9
      + "," + "comment9=" +comment9
      + "," + "comp_flag9=" +comp_flag9
      + "," + "item_name10=" +item_name10
      + "," + "comment10=" +comment10
      + "," + "comp_flag10=" +comp_flag10
      + "," + "note1=" +note1
      + "," + "note2=" +note2
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAccOpenInvalidValuesParamsオブジェクトを作成します。 
   */
  public AccOpenInvalidValuesParams() {
    institution_code_is_modified = true;
    acc_open_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccOpenInvalidValuesRowオブジェクトの値を利用してAccOpenInvalidValuesParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccOpenInvalidValuesRowオブジェクト 
   */
  public AccOpenInvalidValuesParams( AccOpenInvalidValuesRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    acc_open_request_number = p_row.getAccOpenRequestNumber();
    acc_open_request_number_is_set = p_row.getAccOpenRequestNumberIsSet();
    acc_open_request_number_is_modified = p_row.getAccOpenRequestNumberIsModified();
    item_name1 = p_row.getItemName1();
    item_name1_is_set = p_row.getItemName1IsSet();
    item_name1_is_modified = p_row.getItemName1IsModified();
    comment1 = p_row.getComment1();
    comment1_is_set = p_row.getComment1IsSet();
    comment1_is_modified = p_row.getComment1IsModified();
    comp_flag1 = p_row.getCompFlag1();
    comp_flag1_is_set = p_row.getCompFlag1IsSet();
    comp_flag1_is_modified = p_row.getCompFlag1IsModified();
    item_name2 = p_row.getItemName2();
    item_name2_is_set = p_row.getItemName2IsSet();
    item_name2_is_modified = p_row.getItemName2IsModified();
    comment2 = p_row.getComment2();
    comment2_is_set = p_row.getComment2IsSet();
    comment2_is_modified = p_row.getComment2IsModified();
    comp_flag2 = p_row.getCompFlag2();
    comp_flag2_is_set = p_row.getCompFlag2IsSet();
    comp_flag2_is_modified = p_row.getCompFlag2IsModified();
    item_name3 = p_row.getItemName3();
    item_name3_is_set = p_row.getItemName3IsSet();
    item_name3_is_modified = p_row.getItemName3IsModified();
    comment3 = p_row.getComment3();
    comment3_is_set = p_row.getComment3IsSet();
    comment3_is_modified = p_row.getComment3IsModified();
    comp_flag3 = p_row.getCompFlag3();
    comp_flag3_is_set = p_row.getCompFlag3IsSet();
    comp_flag3_is_modified = p_row.getCompFlag3IsModified();
    item_name4 = p_row.getItemName4();
    item_name4_is_set = p_row.getItemName4IsSet();
    item_name4_is_modified = p_row.getItemName4IsModified();
    comment4 = p_row.getComment4();
    comment4_is_set = p_row.getComment4IsSet();
    comment4_is_modified = p_row.getComment4IsModified();
    comp_flag4 = p_row.getCompFlag4();
    comp_flag4_is_set = p_row.getCompFlag4IsSet();
    comp_flag4_is_modified = p_row.getCompFlag4IsModified();
    item_name5 = p_row.getItemName5();
    item_name5_is_set = p_row.getItemName5IsSet();
    item_name5_is_modified = p_row.getItemName5IsModified();
    comment5 = p_row.getComment5();
    comment5_is_set = p_row.getComment5IsSet();
    comment5_is_modified = p_row.getComment5IsModified();
    comp_flag5 = p_row.getCompFlag5();
    comp_flag5_is_set = p_row.getCompFlag5IsSet();
    comp_flag5_is_modified = p_row.getCompFlag5IsModified();
    item_name6 = p_row.getItemName6();
    item_name6_is_set = p_row.getItemName6IsSet();
    item_name6_is_modified = p_row.getItemName6IsModified();
    comment6 = p_row.getComment6();
    comment6_is_set = p_row.getComment6IsSet();
    comment6_is_modified = p_row.getComment6IsModified();
    comp_flag6 = p_row.getCompFlag6();
    comp_flag6_is_set = p_row.getCompFlag6IsSet();
    comp_flag6_is_modified = p_row.getCompFlag6IsModified();
    item_name7 = p_row.getItemName7();
    item_name7_is_set = p_row.getItemName7IsSet();
    item_name7_is_modified = p_row.getItemName7IsModified();
    comment7 = p_row.getComment7();
    comment7_is_set = p_row.getComment7IsSet();
    comment7_is_modified = p_row.getComment7IsModified();
    comp_flag7 = p_row.getCompFlag7();
    comp_flag7_is_set = p_row.getCompFlag7IsSet();
    comp_flag7_is_modified = p_row.getCompFlag7IsModified();
    item_name8 = p_row.getItemName8();
    item_name8_is_set = p_row.getItemName8IsSet();
    item_name8_is_modified = p_row.getItemName8IsModified();
    comment8 = p_row.getComment8();
    comment8_is_set = p_row.getComment8IsSet();
    comment8_is_modified = p_row.getComment8IsModified();
    comp_flag8 = p_row.getCompFlag8();
    comp_flag8_is_set = p_row.getCompFlag8IsSet();
    comp_flag8_is_modified = p_row.getCompFlag8IsModified();
    item_name9 = p_row.getItemName9();
    item_name9_is_set = p_row.getItemName9IsSet();
    item_name9_is_modified = p_row.getItemName9IsModified();
    comment9 = p_row.getComment9();
    comment9_is_set = p_row.getComment9IsSet();
    comment9_is_modified = p_row.getComment9IsModified();
    comp_flag9 = p_row.getCompFlag9();
    comp_flag9_is_set = p_row.getCompFlag9IsSet();
    comp_flag9_is_modified = p_row.getCompFlag9IsModified();
    item_name10 = p_row.getItemName10();
    item_name10_is_set = p_row.getItemName10IsSet();
    item_name10_is_modified = p_row.getItemName10IsModified();
    comment10 = p_row.getComment10();
    comment10_is_set = p_row.getComment10IsSet();
    comment10_is_modified = p_row.getComment10IsModified();
    comp_flag10 = p_row.getCompFlag10();
    comp_flag10_is_set = p_row.getCompFlag10IsSet();
    comp_flag10_is_modified = p_row.getCompFlag10IsModified();
    note1 = p_row.getNote1();
    note1_is_set = p_row.getNote1IsSet();
    note1_is_modified = p_row.getNote1IsModified();
    note2 = p_row.getNote2();
    note2_is_set = p_row.getNote2IsSet();
    note2_is_modified = p_row.getNote2IsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      item_name1_is_set = true;
      item_name1_is_modified = true;
      comment1_is_set = true;
      comment1_is_modified = true;
      comp_flag1_is_set = true;
      comp_flag1_is_modified = true;
      item_name2_is_set = true;
      item_name2_is_modified = true;
      comment2_is_set = true;
      comment2_is_modified = true;
      comp_flag2_is_set = true;
      comp_flag2_is_modified = true;
      item_name3_is_set = true;
      item_name3_is_modified = true;
      comment3_is_set = true;
      comment3_is_modified = true;
      comp_flag3_is_set = true;
      comp_flag3_is_modified = true;
      item_name4_is_set = true;
      item_name4_is_modified = true;
      comment4_is_set = true;
      comment4_is_modified = true;
      comp_flag4_is_set = true;
      comp_flag4_is_modified = true;
      item_name5_is_set = true;
      item_name5_is_modified = true;
      comment5_is_set = true;
      comment5_is_modified = true;
      comp_flag5_is_set = true;
      comp_flag5_is_modified = true;
      item_name6_is_set = true;
      item_name6_is_modified = true;
      comment6_is_set = true;
      comment6_is_modified = true;
      comp_flag6_is_set = true;
      comp_flag6_is_modified = true;
      item_name7_is_set = true;
      item_name7_is_modified = true;
      comment7_is_set = true;
      comment7_is_modified = true;
      comp_flag7_is_set = true;
      comp_flag7_is_modified = true;
      item_name8_is_set = true;
      item_name8_is_modified = true;
      comment8_is_set = true;
      comment8_is_modified = true;
      comp_flag8_is_set = true;
      comp_flag8_is_modified = true;
      item_name9_is_set = true;
      item_name9_is_modified = true;
      comment9_is_set = true;
      comment9_is_modified = true;
      comp_flag9_is_set = true;
      comp_flag9_is_modified = true;
      item_name10_is_set = true;
      item_name10_is_modified = true;
      comment10_is_set = true;
      comment10_is_modified = true;
      comp_flag10_is_set = true;
      comp_flag10_is_modified = true;
      note1_is_set = true;
      note1_is_modified = true;
      note2_is_set = true;
      note2_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccOpenInvalidValuesRow ) )
       return false;
    return fieldsEqual( (AccOpenInvalidValuesRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccOpenInvalidValuesRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccOpenInvalidValuesRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( acc_open_request_number == null ) {
      if ( row.getAccOpenRequestNumber() != null )
        return false;
    } else if ( !acc_open_request_number.equals( row.getAccOpenRequestNumber() ) ) {
        return false;
    }
    if ( item_name1 == null ) {
      if ( row.getItemName1() != null )
        return false;
    } else if ( !item_name1.equals( row.getItemName1() ) ) {
        return false;
    }
    if ( comment1 == null ) {
      if ( row.getComment1() != null )
        return false;
    } else if ( !comment1.equals( row.getComment1() ) ) {
        return false;
    }
    if ( comp_flag1 == null ) {
      if ( row.getCompFlag1() != null )
        return false;
    } else if ( !comp_flag1.equals( row.getCompFlag1() ) ) {
        return false;
    }
    if ( item_name2 == null ) {
      if ( row.getItemName2() != null )
        return false;
    } else if ( !item_name2.equals( row.getItemName2() ) ) {
        return false;
    }
    if ( comment2 == null ) {
      if ( row.getComment2() != null )
        return false;
    } else if ( !comment2.equals( row.getComment2() ) ) {
        return false;
    }
    if ( comp_flag2 == null ) {
      if ( row.getCompFlag2() != null )
        return false;
    } else if ( !comp_flag2.equals( row.getCompFlag2() ) ) {
        return false;
    }
    if ( item_name3 == null ) {
      if ( row.getItemName3() != null )
        return false;
    } else if ( !item_name3.equals( row.getItemName3() ) ) {
        return false;
    }
    if ( comment3 == null ) {
      if ( row.getComment3() != null )
        return false;
    } else if ( !comment3.equals( row.getComment3() ) ) {
        return false;
    }
    if ( comp_flag3 == null ) {
      if ( row.getCompFlag3() != null )
        return false;
    } else if ( !comp_flag3.equals( row.getCompFlag3() ) ) {
        return false;
    }
    if ( item_name4 == null ) {
      if ( row.getItemName4() != null )
        return false;
    } else if ( !item_name4.equals( row.getItemName4() ) ) {
        return false;
    }
    if ( comment4 == null ) {
      if ( row.getComment4() != null )
        return false;
    } else if ( !comment4.equals( row.getComment4() ) ) {
        return false;
    }
    if ( comp_flag4 == null ) {
      if ( row.getCompFlag4() != null )
        return false;
    } else if ( !comp_flag4.equals( row.getCompFlag4() ) ) {
        return false;
    }
    if ( item_name5 == null ) {
      if ( row.getItemName5() != null )
        return false;
    } else if ( !item_name5.equals( row.getItemName5() ) ) {
        return false;
    }
    if ( comment5 == null ) {
      if ( row.getComment5() != null )
        return false;
    } else if ( !comment5.equals( row.getComment5() ) ) {
        return false;
    }
    if ( comp_flag5 == null ) {
      if ( row.getCompFlag5() != null )
        return false;
    } else if ( !comp_flag5.equals( row.getCompFlag5() ) ) {
        return false;
    }
    if ( item_name6 == null ) {
      if ( row.getItemName6() != null )
        return false;
    } else if ( !item_name6.equals( row.getItemName6() ) ) {
        return false;
    }
    if ( comment6 == null ) {
      if ( row.getComment6() != null )
        return false;
    } else if ( !comment6.equals( row.getComment6() ) ) {
        return false;
    }
    if ( comp_flag6 == null ) {
      if ( row.getCompFlag6() != null )
        return false;
    } else if ( !comp_flag6.equals( row.getCompFlag6() ) ) {
        return false;
    }
    if ( item_name7 == null ) {
      if ( row.getItemName7() != null )
        return false;
    } else if ( !item_name7.equals( row.getItemName7() ) ) {
        return false;
    }
    if ( comment7 == null ) {
      if ( row.getComment7() != null )
        return false;
    } else if ( !comment7.equals( row.getComment7() ) ) {
        return false;
    }
    if ( comp_flag7 == null ) {
      if ( row.getCompFlag7() != null )
        return false;
    } else if ( !comp_flag7.equals( row.getCompFlag7() ) ) {
        return false;
    }
    if ( item_name8 == null ) {
      if ( row.getItemName8() != null )
        return false;
    } else if ( !item_name8.equals( row.getItemName8() ) ) {
        return false;
    }
    if ( comment8 == null ) {
      if ( row.getComment8() != null )
        return false;
    } else if ( !comment8.equals( row.getComment8() ) ) {
        return false;
    }
    if ( comp_flag8 == null ) {
      if ( row.getCompFlag8() != null )
        return false;
    } else if ( !comp_flag8.equals( row.getCompFlag8() ) ) {
        return false;
    }
    if ( item_name9 == null ) {
      if ( row.getItemName9() != null )
        return false;
    } else if ( !item_name9.equals( row.getItemName9() ) ) {
        return false;
    }
    if ( comment9 == null ) {
      if ( row.getComment9() != null )
        return false;
    } else if ( !comment9.equals( row.getComment9() ) ) {
        return false;
    }
    if ( comp_flag9 == null ) {
      if ( row.getCompFlag9() != null )
        return false;
    } else if ( !comp_flag9.equals( row.getCompFlag9() ) ) {
        return false;
    }
    if ( item_name10 == null ) {
      if ( row.getItemName10() != null )
        return false;
    } else if ( !item_name10.equals( row.getItemName10() ) ) {
        return false;
    }
    if ( comment10 == null ) {
      if ( row.getComment10() != null )
        return false;
    } else if ( !comment10.equals( row.getComment10() ) ) {
        return false;
    }
    if ( comp_flag10 == null ) {
      if ( row.getCompFlag10() != null )
        return false;
    } else if ( !comp_flag10.equals( row.getCompFlag10() ) ) {
        return false;
    }
    if ( note1 == null ) {
      if ( row.getNote1() != null )
        return false;
    } else if ( !note1.equals( row.getNote1() ) ) {
        return false;
    }
    if ( note2 == null ) {
      if ( row.getNote2() != null )
        return false;
    } else if ( !note2.equals( row.getNote2() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (acc_open_request_number!=null? acc_open_request_number.hashCode(): 0) 
        + (item_name1!=null? item_name1.hashCode(): 0) 
        + (comment1!=null? comment1.hashCode(): 0) 
        + (comp_flag1!=null? comp_flag1.hashCode(): 0) 
        + (item_name2!=null? item_name2.hashCode(): 0) 
        + (comment2!=null? comment2.hashCode(): 0) 
        + (comp_flag2!=null? comp_flag2.hashCode(): 0) 
        + (item_name3!=null? item_name3.hashCode(): 0) 
        + (comment3!=null? comment3.hashCode(): 0) 
        + (comp_flag3!=null? comp_flag3.hashCode(): 0) 
        + (item_name4!=null? item_name4.hashCode(): 0) 
        + (comment4!=null? comment4.hashCode(): 0) 
        + (comp_flag4!=null? comp_flag4.hashCode(): 0) 
        + (item_name5!=null? item_name5.hashCode(): 0) 
        + (comment5!=null? comment5.hashCode(): 0) 
        + (comp_flag5!=null? comp_flag5.hashCode(): 0) 
        + (item_name6!=null? item_name6.hashCode(): 0) 
        + (comment6!=null? comment6.hashCode(): 0) 
        + (comp_flag6!=null? comp_flag6.hashCode(): 0) 
        + (item_name7!=null? item_name7.hashCode(): 0) 
        + (comment7!=null? comment7.hashCode(): 0) 
        + (comp_flag7!=null? comp_flag7.hashCode(): 0) 
        + (item_name8!=null? item_name8.hashCode(): 0) 
        + (comment8!=null? comment8.hashCode(): 0) 
        + (comp_flag8!=null? comp_flag8.hashCode(): 0) 
        + (item_name9!=null? item_name9.hashCode(): 0) 
        + (comment9!=null? comment9.hashCode(): 0) 
        + (comp_flag9!=null? comp_flag9.hashCode(): 0) 
        + (item_name10!=null? item_name10.hashCode(): 0) 
        + (comment10!=null? comment10.hashCode(): 0) 
        + (comp_flag10!=null? comp_flag10.hashCode(): 0) 
        + (note1!=null? note1.hashCode(): 0) 
        + (note2!=null? note2.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("acc_open_request_number",acc_open_request_number);
		if ( item_name1 != null )
			map.put("item_name1",item_name1);
		if ( comment1 != null )
			map.put("comment1",comment1);
		if ( comp_flag1 != null )
			map.put("comp_flag1",comp_flag1);
		if ( item_name2 != null )
			map.put("item_name2",item_name2);
		if ( comment2 != null )
			map.put("comment2",comment2);
		if ( comp_flag2 != null )
			map.put("comp_flag2",comp_flag2);
		if ( item_name3 != null )
			map.put("item_name3",item_name3);
		if ( comment3 != null )
			map.put("comment3",comment3);
		if ( comp_flag3 != null )
			map.put("comp_flag3",comp_flag3);
		if ( item_name4 != null )
			map.put("item_name4",item_name4);
		if ( comment4 != null )
			map.put("comment4",comment4);
		if ( comp_flag4 != null )
			map.put("comp_flag4",comp_flag4);
		if ( item_name5 != null )
			map.put("item_name5",item_name5);
		if ( comment5 != null )
			map.put("comment5",comment5);
		if ( comp_flag5 != null )
			map.put("comp_flag5",comp_flag5);
		if ( item_name6 != null )
			map.put("item_name6",item_name6);
		if ( comment6 != null )
			map.put("comment6",comment6);
		if ( comp_flag6 != null )
			map.put("comp_flag6",comp_flag6);
		if ( item_name7 != null )
			map.put("item_name7",item_name7);
		if ( comment7 != null )
			map.put("comment7",comment7);
		if ( comp_flag7 != null )
			map.put("comp_flag7",comp_flag7);
		if ( item_name8 != null )
			map.put("item_name8",item_name8);
		if ( comment8 != null )
			map.put("comment8",comment8);
		if ( comp_flag8 != null )
			map.put("comp_flag8",comp_flag8);
		if ( item_name9 != null )
			map.put("item_name9",item_name9);
		if ( comment9 != null )
			map.put("comment9",comment9);
		if ( comp_flag9 != null )
			map.put("comp_flag9",comp_flag9);
		if ( item_name10 != null )
			map.put("item_name10",item_name10);
		if ( comment10 != null )
			map.put("comment10",comment10);
		if ( comp_flag10 != null )
			map.put("comp_flag10",comp_flag10);
		if ( note1 != null )
			map.put("note1",note1);
		if ( note2 != null )
			map.put("note2",note2);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( item_name1_is_modified )
			map.put("item_name1",item_name1);
		if ( comment1_is_modified )
			map.put("comment1",comment1);
		if ( comp_flag1_is_modified )
			map.put("comp_flag1",comp_flag1);
		if ( item_name2_is_modified )
			map.put("item_name2",item_name2);
		if ( comment2_is_modified )
			map.put("comment2",comment2);
		if ( comp_flag2_is_modified )
			map.put("comp_flag2",comp_flag2);
		if ( item_name3_is_modified )
			map.put("item_name3",item_name3);
		if ( comment3_is_modified )
			map.put("comment3",comment3);
		if ( comp_flag3_is_modified )
			map.put("comp_flag3",comp_flag3);
		if ( item_name4_is_modified )
			map.put("item_name4",item_name4);
		if ( comment4_is_modified )
			map.put("comment4",comment4);
		if ( comp_flag4_is_modified )
			map.put("comp_flag4",comp_flag4);
		if ( item_name5_is_modified )
			map.put("item_name5",item_name5);
		if ( comment5_is_modified )
			map.put("comment5",comment5);
		if ( comp_flag5_is_modified )
			map.put("comp_flag5",comp_flag5);
		if ( item_name6_is_modified )
			map.put("item_name6",item_name6);
		if ( comment6_is_modified )
			map.put("comment6",comment6);
		if ( comp_flag6_is_modified )
			map.put("comp_flag6",comp_flag6);
		if ( item_name7_is_modified )
			map.put("item_name7",item_name7);
		if ( comment7_is_modified )
			map.put("comment7",comment7);
		if ( comp_flag7_is_modified )
			map.put("comp_flag7",comp_flag7);
		if ( item_name8_is_modified )
			map.put("item_name8",item_name8);
		if ( comment8_is_modified )
			map.put("comment8",comment8);
		if ( comp_flag8_is_modified )
			map.put("comp_flag8",comp_flag8);
		if ( item_name9_is_modified )
			map.put("item_name9",item_name9);
		if ( comment9_is_modified )
			map.put("comment9",comment9);
		if ( comp_flag9_is_modified )
			map.put("comp_flag9",comp_flag9);
		if ( item_name10_is_modified )
			map.put("item_name10",item_name10);
		if ( comment10_is_modified )
			map.put("comment10",comment10);
		if ( comp_flag10_is_modified )
			map.put("comp_flag10",comp_flag10);
		if ( note1_is_modified )
			map.put("note1",note1);
		if ( note2_is_modified )
			map.put("note2",note2);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("item_name1",item_name1);
			map.put("comment1",comment1);
			map.put("comp_flag1",comp_flag1);
			map.put("item_name2",item_name2);
			map.put("comment2",comment2);
			map.put("comp_flag2",comp_flag2);
			map.put("item_name3",item_name3);
			map.put("comment3",comment3);
			map.put("comp_flag3",comp_flag3);
			map.put("item_name4",item_name4);
			map.put("comment4",comment4);
			map.put("comp_flag4",comp_flag4);
			map.put("item_name5",item_name5);
			map.put("comment5",comment5);
			map.put("comp_flag5",comp_flag5);
			map.put("item_name6",item_name6);
			map.put("comment6",comment6);
			map.put("comp_flag6",comp_flag6);
			map.put("item_name7",item_name7);
			map.put("comment7",comment7);
			map.put("comp_flag7",comp_flag7);
			map.put("item_name8",item_name8);
			map.put("comment8",comment8);
			map.put("comp_flag8",comp_flag8);
			map.put("item_name9",item_name9);
			map.put("comment9",comment9);
			map.put("comp_flag9",comp_flag9);
			map.put("item_name10",item_name10);
			map.put("comment10",comment10);
			map.put("comp_flag10",comp_flag10);
			map.put("note1",note1);
			map.put("note2",note2);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccOpenRequestNumber()
  {
    return acc_open_request_number;
  }


  /** 
   * <em>acc_open_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsSet() {
    return acc_open_request_number_is_set;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsModified() {
    return acc_open_request_number_is_modified;
  }


  /** 
   * <em>item_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName1()
  {
    return item_name1;
  }


  /** 
   * <em>item_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName1IsSet() {
    return item_name1_is_set;
  }


  /** 
   * <em>item_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName1IsModified() {
    return item_name1_is_modified;
  }


  /** 
   * <em>comment1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment1()
  {
    return comment1;
  }


  /** 
   * <em>comment1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment1IsSet() {
    return comment1_is_set;
  }


  /** 
   * <em>comment1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment1IsModified() {
    return comment1_is_modified;
  }


  /** 
   * <em>comp_flag1</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag1()
  {
    return comp_flag1;
  }


  /** 
   * <em>comp_flag1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag1IsSet() {
    return comp_flag1_is_set;
  }


  /** 
   * <em>comp_flag1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag1IsModified() {
    return comp_flag1_is_modified;
  }


  /** 
   * <em>item_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName2()
  {
    return item_name2;
  }


  /** 
   * <em>item_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName2IsSet() {
    return item_name2_is_set;
  }


  /** 
   * <em>item_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName2IsModified() {
    return item_name2_is_modified;
  }


  /** 
   * <em>comment2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment2()
  {
    return comment2;
  }


  /** 
   * <em>comment2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment2IsSet() {
    return comment2_is_set;
  }


  /** 
   * <em>comment2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment2IsModified() {
    return comment2_is_modified;
  }


  /** 
   * <em>comp_flag2</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag2()
  {
    return comp_flag2;
  }


  /** 
   * <em>comp_flag2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag2IsSet() {
    return comp_flag2_is_set;
  }


  /** 
   * <em>comp_flag2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag2IsModified() {
    return comp_flag2_is_modified;
  }


  /** 
   * <em>item_name3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName3()
  {
    return item_name3;
  }


  /** 
   * <em>item_name3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName3IsSet() {
    return item_name3_is_set;
  }


  /** 
   * <em>item_name3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName3IsModified() {
    return item_name3_is_modified;
  }


  /** 
   * <em>comment3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment3()
  {
    return comment3;
  }


  /** 
   * <em>comment3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment3IsSet() {
    return comment3_is_set;
  }


  /** 
   * <em>comment3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment3IsModified() {
    return comment3_is_modified;
  }


  /** 
   * <em>comp_flag3</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag3()
  {
    return comp_flag3;
  }


  /** 
   * <em>comp_flag3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag3IsSet() {
    return comp_flag3_is_set;
  }


  /** 
   * <em>comp_flag3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag3IsModified() {
    return comp_flag3_is_modified;
  }


  /** 
   * <em>item_name4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName4()
  {
    return item_name4;
  }


  /** 
   * <em>item_name4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName4IsSet() {
    return item_name4_is_set;
  }


  /** 
   * <em>item_name4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName4IsModified() {
    return item_name4_is_modified;
  }


  /** 
   * <em>comment4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment4()
  {
    return comment4;
  }


  /** 
   * <em>comment4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment4IsSet() {
    return comment4_is_set;
  }


  /** 
   * <em>comment4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment4IsModified() {
    return comment4_is_modified;
  }


  /** 
   * <em>comp_flag4</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag4()
  {
    return comp_flag4;
  }


  /** 
   * <em>comp_flag4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag4IsSet() {
    return comp_flag4_is_set;
  }


  /** 
   * <em>comp_flag4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag4IsModified() {
    return comp_flag4_is_modified;
  }


  /** 
   * <em>item_name5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName5()
  {
    return item_name5;
  }


  /** 
   * <em>item_name5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName5IsSet() {
    return item_name5_is_set;
  }


  /** 
   * <em>item_name5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName5IsModified() {
    return item_name5_is_modified;
  }


  /** 
   * <em>comment5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment5()
  {
    return comment5;
  }


  /** 
   * <em>comment5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment5IsSet() {
    return comment5_is_set;
  }


  /** 
   * <em>comment5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment5IsModified() {
    return comment5_is_modified;
  }


  /** 
   * <em>comp_flag5</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag5()
  {
    return comp_flag5;
  }


  /** 
   * <em>comp_flag5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag5IsSet() {
    return comp_flag5_is_set;
  }


  /** 
   * <em>comp_flag5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag5IsModified() {
    return comp_flag5_is_modified;
  }


  /** 
   * <em>item_name6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName6()
  {
    return item_name6;
  }


  /** 
   * <em>item_name6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName6IsSet() {
    return item_name6_is_set;
  }


  /** 
   * <em>item_name6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName6IsModified() {
    return item_name6_is_modified;
  }


  /** 
   * <em>comment6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment6()
  {
    return comment6;
  }


  /** 
   * <em>comment6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment6IsSet() {
    return comment6_is_set;
  }


  /** 
   * <em>comment6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment6IsModified() {
    return comment6_is_modified;
  }


  /** 
   * <em>comp_flag6</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag6()
  {
    return comp_flag6;
  }


  /** 
   * <em>comp_flag6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag6IsSet() {
    return comp_flag6_is_set;
  }


  /** 
   * <em>comp_flag6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag6IsModified() {
    return comp_flag6_is_modified;
  }


  /** 
   * <em>item_name7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName7()
  {
    return item_name7;
  }


  /** 
   * <em>item_name7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName7IsSet() {
    return item_name7_is_set;
  }


  /** 
   * <em>item_name7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName7IsModified() {
    return item_name7_is_modified;
  }


  /** 
   * <em>comment7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment7()
  {
    return comment7;
  }


  /** 
   * <em>comment7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment7IsSet() {
    return comment7_is_set;
  }


  /** 
   * <em>comment7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment7IsModified() {
    return comment7_is_modified;
  }


  /** 
   * <em>comp_flag7</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag7()
  {
    return comp_flag7;
  }


  /** 
   * <em>comp_flag7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag7IsSet() {
    return comp_flag7_is_set;
  }


  /** 
   * <em>comp_flag7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag7IsModified() {
    return comp_flag7_is_modified;
  }


  /** 
   * <em>item_name8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName8()
  {
    return item_name8;
  }


  /** 
   * <em>item_name8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName8IsSet() {
    return item_name8_is_set;
  }


  /** 
   * <em>item_name8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName8IsModified() {
    return item_name8_is_modified;
  }


  /** 
   * <em>comment8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment8()
  {
    return comment8;
  }


  /** 
   * <em>comment8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment8IsSet() {
    return comment8_is_set;
  }


  /** 
   * <em>comment8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment8IsModified() {
    return comment8_is_modified;
  }


  /** 
   * <em>comp_flag8</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag8()
  {
    return comp_flag8;
  }


  /** 
   * <em>comp_flag8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag8IsSet() {
    return comp_flag8_is_set;
  }


  /** 
   * <em>comp_flag8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag8IsModified() {
    return comp_flag8_is_modified;
  }


  /** 
   * <em>item_name9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName9()
  {
    return item_name9;
  }


  /** 
   * <em>item_name9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName9IsSet() {
    return item_name9_is_set;
  }


  /** 
   * <em>item_name9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName9IsModified() {
    return item_name9_is_modified;
  }


  /** 
   * <em>comment9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment9()
  {
    return comment9;
  }


  /** 
   * <em>comment9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment9IsSet() {
    return comment9_is_set;
  }


  /** 
   * <em>comment9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment9IsModified() {
    return comment9_is_modified;
  }


  /** 
   * <em>comp_flag9</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag9()
  {
    return comp_flag9;
  }


  /** 
   * <em>comp_flag9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag9IsSet() {
    return comp_flag9_is_set;
  }


  /** 
   * <em>comp_flag9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag9IsModified() {
    return comp_flag9_is_modified;
  }


  /** 
   * <em>item_name10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getItemName10()
  {
    return item_name10;
  }


  /** 
   * <em>item_name10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName10IsSet() {
    return item_name10_is_set;
  }


  /** 
   * <em>item_name10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getItemName10IsModified() {
    return item_name10_is_modified;
  }


  /** 
   * <em>comment10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComment10()
  {
    return comment10;
  }


  /** 
   * <em>comment10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment10IsSet() {
    return comment10_is_set;
  }


  /** 
   * <em>comment10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComment10IsModified() {
    return comment10_is_modified;
  }


  /** 
   * <em>comp_flag10</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag10()
  {
    return comp_flag10;
  }


  /** 
   * <em>comp_flag10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag10IsSet() {
    return comp_flag10_is_set;
  }


  /** 
   * <em>comp_flag10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompFlag10IsModified() {
    return comp_flag10_is_modified;
  }


  /** 
   * <em>note1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNote1()
  {
    return note1;
  }


  /** 
   * <em>note1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote1IsSet() {
    return note1_is_set;
  }


  /** 
   * <em>note1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote1IsModified() {
    return note1_is_modified;
  }


  /** 
   * <em>note2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNote2()
  {
    return note2;
  }


  /** 
   * <em>note2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote2IsSet() {
    return note2_is_set;
  }


  /** 
   * <em>note2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNote2IsModified() {
    return note2_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccOpenInvalidValuesPK(institution_code, acc_open_request_number);
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値を設定します。 
   *
   * @@param p_accOpenRequestNumber <em>acc_open_request_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public final void setAccOpenRequestNumber( String p_accOpenRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_request_number = p_accOpenRequestNumber;
    acc_open_request_number_is_set = true;
    acc_open_request_number_is_modified = true;
  }


  /** 
   * <em>item_name1</em>カラムの値を設定します。 
   *
   * @@param p_itemName1 <em>item_name1</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName1( String p_itemName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name1 = p_itemName1;
    item_name1_is_set = true;
    item_name1_is_modified = true;
  }


  /** 
   * <em>comment1</em>カラムの値を設定します。 
   *
   * @@param p_comment1 <em>comment1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment1( String p_comment1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment1 = p_comment1;
    comment1_is_set = true;
    comment1_is_modified = true;
  }


  /** 
   * <em>comp_flag1</em>カラムの値を設定します。 
   *
   * @@param p_compFlag1 <em>comp_flag1</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag1( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag1 = p_compFlag1;
    comp_flag1_is_set = true;
    comp_flag1_is_modified = true;
  }


  /** 
   * <em>item_name2</em>カラムの値を設定します。 
   *
   * @@param p_itemName2 <em>item_name2</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName2( String p_itemName2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name2 = p_itemName2;
    item_name2_is_set = true;
    item_name2_is_modified = true;
  }


  /** 
   * <em>comment2</em>カラムの値を設定します。 
   *
   * @@param p_comment2 <em>comment2</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment2( String p_comment2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment2 = p_comment2;
    comment2_is_set = true;
    comment2_is_modified = true;
  }


  /** 
   * <em>comp_flag2</em>カラムの値を設定します。 
   *
   * @@param p_compFlag2 <em>comp_flag2</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag2( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag2 = p_compFlag2;
    comp_flag2_is_set = true;
    comp_flag2_is_modified = true;
  }


  /** 
   * <em>item_name3</em>カラムの値を設定します。 
   *
   * @@param p_itemName3 <em>item_name3</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName3( String p_itemName3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name3 = p_itemName3;
    item_name3_is_set = true;
    item_name3_is_modified = true;
  }


  /** 
   * <em>comment3</em>カラムの値を設定します。 
   *
   * @@param p_comment3 <em>comment3</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment3( String p_comment3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment3 = p_comment3;
    comment3_is_set = true;
    comment3_is_modified = true;
  }


  /** 
   * <em>comp_flag3</em>カラムの値を設定します。 
   *
   * @@param p_compFlag3 <em>comp_flag3</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag3( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag3 = p_compFlag3;
    comp_flag3_is_set = true;
    comp_flag3_is_modified = true;
  }


  /** 
   * <em>item_name4</em>カラムの値を設定します。 
   *
   * @@param p_itemName4 <em>item_name4</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName4( String p_itemName4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name4 = p_itemName4;
    item_name4_is_set = true;
    item_name4_is_modified = true;
  }


  /** 
   * <em>comment4</em>カラムの値を設定します。 
   *
   * @@param p_comment4 <em>comment4</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment4( String p_comment4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment4 = p_comment4;
    comment4_is_set = true;
    comment4_is_modified = true;
  }


  /** 
   * <em>comp_flag4</em>カラムの値を設定します。 
   *
   * @@param p_compFlag4 <em>comp_flag4</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag4( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag4 = p_compFlag4;
    comp_flag4_is_set = true;
    comp_flag4_is_modified = true;
  }


  /** 
   * <em>item_name5</em>カラムの値を設定します。 
   *
   * @@param p_itemName5 <em>item_name5</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName5( String p_itemName5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name5 = p_itemName5;
    item_name5_is_set = true;
    item_name5_is_modified = true;
  }


  /** 
   * <em>comment5</em>カラムの値を設定します。 
   *
   * @@param p_comment5 <em>comment5</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment5( String p_comment5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment5 = p_comment5;
    comment5_is_set = true;
    comment5_is_modified = true;
  }


  /** 
   * <em>comp_flag5</em>カラムの値を設定します。 
   *
   * @@param p_compFlag5 <em>comp_flag5</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag5( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag5 = p_compFlag5;
    comp_flag5_is_set = true;
    comp_flag5_is_modified = true;
  }


  /** 
   * <em>item_name6</em>カラムの値を設定します。 
   *
   * @@param p_itemName6 <em>item_name6</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName6( String p_itemName6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name6 = p_itemName6;
    item_name6_is_set = true;
    item_name6_is_modified = true;
  }


  /** 
   * <em>comment6</em>カラムの値を設定します。 
   *
   * @@param p_comment6 <em>comment6</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment6( String p_comment6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment6 = p_comment6;
    comment6_is_set = true;
    comment6_is_modified = true;
  }


  /** 
   * <em>comp_flag6</em>カラムの値を設定します。 
   *
   * @@param p_compFlag6 <em>comp_flag6</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag6( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag6 = p_compFlag6;
    comp_flag6_is_set = true;
    comp_flag6_is_modified = true;
  }


  /** 
   * <em>item_name7</em>カラムの値を設定します。 
   *
   * @@param p_itemName7 <em>item_name7</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName7( String p_itemName7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name7 = p_itemName7;
    item_name7_is_set = true;
    item_name7_is_modified = true;
  }


  /** 
   * <em>comment7</em>カラムの値を設定します。 
   *
   * @@param p_comment7 <em>comment7</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment7( String p_comment7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment7 = p_comment7;
    comment7_is_set = true;
    comment7_is_modified = true;
  }


  /** 
   * <em>comp_flag7</em>カラムの値を設定します。 
   *
   * @@param p_compFlag7 <em>comp_flag7</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag7( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag7 = p_compFlag7;
    comp_flag7_is_set = true;
    comp_flag7_is_modified = true;
  }


  /** 
   * <em>item_name8</em>カラムの値を設定します。 
   *
   * @@param p_itemName8 <em>item_name8</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName8( String p_itemName8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name8 = p_itemName8;
    item_name8_is_set = true;
    item_name8_is_modified = true;
  }


  /** 
   * <em>comment8</em>カラムの値を設定します。 
   *
   * @@param p_comment8 <em>comment8</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment8( String p_comment8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment8 = p_comment8;
    comment8_is_set = true;
    comment8_is_modified = true;
  }


  /** 
   * <em>comp_flag8</em>カラムの値を設定します。 
   *
   * @@param p_compFlag8 <em>comp_flag8</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag8( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag8 = p_compFlag8;
    comp_flag8_is_set = true;
    comp_flag8_is_modified = true;
  }


  /** 
   * <em>item_name9</em>カラムの値を設定します。 
   *
   * @@param p_itemName9 <em>item_name9</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName9( String p_itemName9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name9 = p_itemName9;
    item_name9_is_set = true;
    item_name9_is_modified = true;
  }


  /** 
   * <em>comment9</em>カラムの値を設定します。 
   *
   * @@param p_comment9 <em>comment9</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment9( String p_comment9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment9 = p_comment9;
    comment9_is_set = true;
    comment9_is_modified = true;
  }


  /** 
   * <em>comp_flag9</em>カラムの値を設定します。 
   *
   * @@param p_compFlag9 <em>comp_flag9</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag9( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag9 = p_compFlag9;
    comp_flag9_is_set = true;
    comp_flag9_is_modified = true;
  }


  /** 
   * <em>item_name10</em>カラムの値を設定します。 
   *
   * @@param p_itemName10 <em>item_name10</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setItemName10( String p_itemName10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    item_name10 = p_itemName10;
    item_name10_is_set = true;
    item_name10_is_modified = true;
  }


  /** 
   * <em>comment10</em>カラムの値を設定します。 
   *
   * @@param p_comment10 <em>comment10</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setComment10( String p_comment10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment10 = p_comment10;
    comment10_is_set = true;
    comment10_is_modified = true;
  }


  /** 
   * <em>comp_flag10</em>カラムの値を設定します。 
   *
   * @@param p_compFlag10 <em>comp_flag10</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCompFlag10( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_compFlag10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_flag10 = p_compFlag10;
    comp_flag10_is_set = true;
    comp_flag10_is_modified = true;
  }


  /** 
   * <em>note1</em>カラムの値を設定します。 
   *
   * @@param p_note1 <em>note1</em>カラムの値をあらわす240桁以下のString値 
   */
  public final void setNote1( String p_note1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    note1 = p_note1;
    note1_is_set = true;
    note1_is_modified = true;
  }


  /** 
   * <em>note2</em>カラムの値を設定します。 
   *
   * @@param p_note2 <em>note2</em>カラムの値をあらわす240桁以下のString値 
   */
  public final void setNote2( String p_note2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    note2 = p_note2;
    note2_is_set = true;
    note2_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("acc_open_request_number") ) {
                    return this.acc_open_request_number;
                }
                break;
            case 'c':
                if ( name.equals("comment1") ) {
                    return this.comment1;
                }
                else if ( name.equals("comp_flag1") ) {
                    return this.comp_flag1;
                }
                else if ( name.equals("comment2") ) {
                    return this.comment2;
                }
                else if ( name.equals("comp_flag2") ) {
                    return this.comp_flag2;
                }
                else if ( name.equals("comment3") ) {
                    return this.comment3;
                }
                else if ( name.equals("comp_flag3") ) {
                    return this.comp_flag3;
                }
                else if ( name.equals("comment4") ) {
                    return this.comment4;
                }
                else if ( name.equals("comp_flag4") ) {
                    return this.comp_flag4;
                }
                else if ( name.equals("comment5") ) {
                    return this.comment5;
                }
                else if ( name.equals("comp_flag5") ) {
                    return this.comp_flag5;
                }
                else if ( name.equals("comment6") ) {
                    return this.comment6;
                }
                else if ( name.equals("comp_flag6") ) {
                    return this.comp_flag6;
                }
                else if ( name.equals("comment7") ) {
                    return this.comment7;
                }
                else if ( name.equals("comp_flag7") ) {
                    return this.comp_flag7;
                }
                else if ( name.equals("comment8") ) {
                    return this.comment8;
                }
                else if ( name.equals("comp_flag8") ) {
                    return this.comp_flag8;
                }
                else if ( name.equals("comment9") ) {
                    return this.comment9;
                }
                else if ( name.equals("comp_flag9") ) {
                    return this.comp_flag9;
                }
                else if ( name.equals("comment10") ) {
                    return this.comment10;
                }
                else if ( name.equals("comp_flag10") ) {
                    return this.comp_flag10;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("item_name1") ) {
                    return this.item_name1;
                }
                else if ( name.equals("item_name2") ) {
                    return this.item_name2;
                }
                else if ( name.equals("item_name3") ) {
                    return this.item_name3;
                }
                else if ( name.equals("item_name4") ) {
                    return this.item_name4;
                }
                else if ( name.equals("item_name5") ) {
                    return this.item_name5;
                }
                else if ( name.equals("item_name6") ) {
                    return this.item_name6;
                }
                else if ( name.equals("item_name7") ) {
                    return this.item_name7;
                }
                else if ( name.equals("item_name8") ) {
                    return this.item_name8;
                }
                else if ( name.equals("item_name9") ) {
                    return this.item_name9;
                }
                else if ( name.equals("item_name10") ) {
                    return this.item_name10;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("note1") ) {
                    return this.note1;
                }
                else if ( name.equals("note2") ) {
                    return this.note2;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("acc_open_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_request_number' must be String: '"+value+"' is not." );
                    this.acc_open_request_number = (String) value;
                    if (this.acc_open_request_number_is_set)
                        this.acc_open_request_number_is_modified = true;
                    this.acc_open_request_number_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("comment1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment1' must be String: '"+value+"' is not." );
                    this.comment1 = (String) value;
                    if (this.comment1_is_set)
                        this.comment1_is_modified = true;
                    this.comment1_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag1") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag1' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag1 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag1_is_set)
                        this.comp_flag1_is_modified = true;
                    this.comp_flag1_is_set = true;
                    return;
                }
                else if ( name.equals("comment2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment2' must be String: '"+value+"' is not." );
                    this.comment2 = (String) value;
                    if (this.comment2_is_set)
                        this.comment2_is_modified = true;
                    this.comment2_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag2") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag2' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag2 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag2_is_set)
                        this.comp_flag2_is_modified = true;
                    this.comp_flag2_is_set = true;
                    return;
                }
                else if ( name.equals("comment3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment3' must be String: '"+value+"' is not." );
                    this.comment3 = (String) value;
                    if (this.comment3_is_set)
                        this.comment3_is_modified = true;
                    this.comment3_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag3") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag3' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag3 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag3_is_set)
                        this.comp_flag3_is_modified = true;
                    this.comp_flag3_is_set = true;
                    return;
                }
                else if ( name.equals("comment4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment4' must be String: '"+value+"' is not." );
                    this.comment4 = (String) value;
                    if (this.comment4_is_set)
                        this.comment4_is_modified = true;
                    this.comment4_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag4") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag4' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag4 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag4_is_set)
                        this.comp_flag4_is_modified = true;
                    this.comp_flag4_is_set = true;
                    return;
                }
                else if ( name.equals("comment5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment5' must be String: '"+value+"' is not." );
                    this.comment5 = (String) value;
                    if (this.comment5_is_set)
                        this.comment5_is_modified = true;
                    this.comment5_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag5") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag5' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag5 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag5_is_set)
                        this.comp_flag5_is_modified = true;
                    this.comp_flag5_is_set = true;
                    return;
                }
                else if ( name.equals("comment6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment6' must be String: '"+value+"' is not." );
                    this.comment6 = (String) value;
                    if (this.comment6_is_set)
                        this.comment6_is_modified = true;
                    this.comment6_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag6") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag6' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag6 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag6_is_set)
                        this.comp_flag6_is_modified = true;
                    this.comp_flag6_is_set = true;
                    return;
                }
                else if ( name.equals("comment7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment7' must be String: '"+value+"' is not." );
                    this.comment7 = (String) value;
                    if (this.comment7_is_set)
                        this.comment7_is_modified = true;
                    this.comment7_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag7") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag7' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag7 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag7_is_set)
                        this.comp_flag7_is_modified = true;
                    this.comp_flag7_is_set = true;
                    return;
                }
                else if ( name.equals("comment8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment8' must be String: '"+value+"' is not." );
                    this.comment8 = (String) value;
                    if (this.comment8_is_set)
                        this.comment8_is_modified = true;
                    this.comment8_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag8") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag8' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag8 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag8_is_set)
                        this.comp_flag8_is_modified = true;
                    this.comp_flag8_is_set = true;
                    return;
                }
                else if ( name.equals("comment9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment9' must be String: '"+value+"' is not." );
                    this.comment9 = (String) value;
                    if (this.comment9_is_set)
                        this.comment9_is_modified = true;
                    this.comment9_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag9") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag9' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag9 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag9_is_set)
                        this.comp_flag9_is_modified = true;
                    this.comp_flag9_is_set = true;
                    return;
                }
                else if ( name.equals("comment10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment10' must be String: '"+value+"' is not." );
                    this.comment10 = (String) value;
                    if (this.comment10_is_set)
                        this.comment10_is_modified = true;
                    this.comment10_is_set = true;
                    return;
                }
                else if ( name.equals("comp_flag10") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'comp_flag10' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.comp_flag10 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.comp_flag10_is_set)
                        this.comp_flag10_is_modified = true;
                    this.comp_flag10_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("item_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name1' must be String: '"+value+"' is not." );
                    this.item_name1 = (String) value;
                    if (this.item_name1_is_set)
                        this.item_name1_is_modified = true;
                    this.item_name1_is_set = true;
                    return;
                }
                else if ( name.equals("item_name2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name2' must be String: '"+value+"' is not." );
                    this.item_name2 = (String) value;
                    if (this.item_name2_is_set)
                        this.item_name2_is_modified = true;
                    this.item_name2_is_set = true;
                    return;
                }
                else if ( name.equals("item_name3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name3' must be String: '"+value+"' is not." );
                    this.item_name3 = (String) value;
                    if (this.item_name3_is_set)
                        this.item_name3_is_modified = true;
                    this.item_name3_is_set = true;
                    return;
                }
                else if ( name.equals("item_name4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name4' must be String: '"+value+"' is not." );
                    this.item_name4 = (String) value;
                    if (this.item_name4_is_set)
                        this.item_name4_is_modified = true;
                    this.item_name4_is_set = true;
                    return;
                }
                else if ( name.equals("item_name5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name5' must be String: '"+value+"' is not." );
                    this.item_name5 = (String) value;
                    if (this.item_name5_is_set)
                        this.item_name5_is_modified = true;
                    this.item_name5_is_set = true;
                    return;
                }
                else if ( name.equals("item_name6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name6' must be String: '"+value+"' is not." );
                    this.item_name6 = (String) value;
                    if (this.item_name6_is_set)
                        this.item_name6_is_modified = true;
                    this.item_name6_is_set = true;
                    return;
                }
                else if ( name.equals("item_name7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name7' must be String: '"+value+"' is not." );
                    this.item_name7 = (String) value;
                    if (this.item_name7_is_set)
                        this.item_name7_is_modified = true;
                    this.item_name7_is_set = true;
                    return;
                }
                else if ( name.equals("item_name8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name8' must be String: '"+value+"' is not." );
                    this.item_name8 = (String) value;
                    if (this.item_name8_is_set)
                        this.item_name8_is_modified = true;
                    this.item_name8_is_set = true;
                    return;
                }
                else if ( name.equals("item_name9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name9' must be String: '"+value+"' is not." );
                    this.item_name9 = (String) value;
                    if (this.item_name9_is_set)
                        this.item_name9_is_modified = true;
                    this.item_name9_is_set = true;
                    return;
                }
                else if ( name.equals("item_name10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'item_name10' must be String: '"+value+"' is not." );
                    this.item_name10 = (String) value;
                    if (this.item_name10_is_set)
                        this.item_name10_is_modified = true;
                    this.item_name10_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("note1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'note1' must be String: '"+value+"' is not." );
                    this.note1 = (String) value;
                    if (this.note1_is_set)
                        this.note1_is_modified = true;
                    this.note1_is_set = true;
                    return;
                }
                else if ( name.equals("note2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'note2' must be String: '"+value+"' is not." );
                    this.note2 = (String) value;
                    if (this.note2_is_set)
                        this.note2_is_modified = true;
                    this.note2_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
