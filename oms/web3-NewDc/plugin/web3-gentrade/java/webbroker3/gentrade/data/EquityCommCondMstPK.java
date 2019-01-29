head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommCondMstPK.java;


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
 * <b>EquityCommCondMst</b>データベーステーブルで一意である1つのレコードをあらわす<b>EquityCommCondMst</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>EquityCommCondMst</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EquityCommCondMstRow 
 */
public class EquityCommCondMstPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "equity_comm_cond_mst";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: EquityCommCondMstRow. 
   */
  public RowType getRowType() {
    return EquityCommCondMstRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String comm_product_code;
  /**
   * <em>reg_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public String reg_no;
  /**
   * <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp appli_start_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public EquityCommCondMstPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_regNo <em>reg_no</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_appliStartDate <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public EquityCommCondMstPK( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) {
      institution_code = p_institutionCode;
      comm_product_code = p_commProductCode;
      reg_no = p_regNo;
      appli_start_date = p_appliStartDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static EquityCommCondMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    EquityCommCondMstPK pk = new EquityCommCondMstPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.comm_product_code = st.nextToken();
    pk.reg_no = st.nextToken();
    pk.appli_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + comm_product_code + "," + reg_no + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(appli_start_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof EquityCommCondMstPK) )
      return false;
    EquityCommCondMstPK o = (EquityCommCondMstPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    if ( reg_no == null ) {
      if ( o.reg_no != null )
        return false;
    } else if ( !reg_no.equals( o.reg_no ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (reg_no!=null? reg_no.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
    ;
  }

}

@
