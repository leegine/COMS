head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.22.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TaxRateTablePK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>TaxRateTable</b>データベーステーブルで一意である1つのレコードをあらわす<b>TaxRateTable</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TaxRateTable</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TaxRateTableRow 
 */
public class TaxRateTablePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tax_rate_table";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TaxRateTableRow. 
   */
  public RowType getRowType() {
    return TaxRateTableRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>tax_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String tax_type;
  /**
   * <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp appli_start_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public TaxRateTablePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_appliStartDate <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public TaxRateTablePK( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) {
      institution_code = p_institutionCode;
      tax_type = p_taxType;
      appli_start_date = p_appliStartDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TaxRateTablePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TaxRateTablePK pk = new TaxRateTablePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.tax_type = st.nextToken();
    pk.appli_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + tax_type + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(appli_start_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TaxRateTablePK) )
      return false;
    TaxRateTablePK o = (TaxRateTablePK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( o.tax_type != null )
        return false;
    } else if ( !tax_type.equals( o.tax_type ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
    ;
  }

}

@
