head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SystemPreferencesPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>SystemPreferences</b>データベーステーブルで一意である1つのレコードをあらわす<b>SystemPreferences</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SystemPreferences</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SystemPreferencesRow 
 */
public class SystemPreferencesPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "system_preferences";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SystemPreferencesRow. 
   */
  public RowType getRowType() {
    return SystemPreferencesRow.TYPE;
  }

  /**
   * <em>name</em>カラムの値をあらわす80桁以下のString値 
   */
  public String name;


  /** 
   * デフォルトコンストラクタ 
   */
  public SystemPreferencesPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_name <em>name</em>カラムの値をあらわす80桁以下のString値 
   */
  public SystemPreferencesPK( String p_name ) {
      name = p_name;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SystemPreferencesPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new SystemPreferencesPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return name;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SystemPreferencesPK) )
      return false;
    SystemPreferencesPK o = (SystemPreferencesPK) other;
    if ( name == null ) {
      if ( o.name != null )
        return false;
    } else if ( !name.equals( o.name ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (name!=null? name.hashCode(): 0) 
    ;
  }

}

@
