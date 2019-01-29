head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointConvertMasterPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>PointConvertMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>PointConvertMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>PointConvertMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointConvertMasterRow 
 */
public class PointConvertMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "point_convert_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: PointConvertMasterRow. 
   */
  public RowType getRowType() {
    return PointConvertMasterRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;
  /**
   * <em>fund_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String fund_type;
  /**
   * <em>tran_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String tran_type;
  /**
   * <em>buy_sell_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String buy_sell_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public PointConvertMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_fundType <em>fund_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_tranType <em>tran_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_buySellDiv <em>buy_sell_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public PointConvertMasterPK( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      fund_type = p_fundType;
      tran_type = p_tranType;
      buy_sell_div = p_buySellDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static PointConvertMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    PointConvertMasterPK pk = new PointConvertMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.fund_type = st.nextToken();
    pk.tran_type = st.nextToken();
    pk.buy_sell_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + fund_type + "," + tran_type + "," + buy_sell_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof PointConvertMasterPK) )
      return false;
    PointConvertMasterPK o = (PointConvertMasterPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( o.branch_code != null )
        return false;
    } else if ( !branch_code.equals( o.branch_code ) ) {
        return false;
    }
    if ( fund_type == null ) {
      if ( o.fund_type != null )
        return false;
    } else if ( !fund_type.equals( o.fund_type ) ) {
        return false;
    }
    if ( tran_type == null ) {
      if ( o.tran_type != null )
        return false;
    } else if ( !tran_type.equals( o.tran_type ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( o.buy_sell_div != null )
        return false;
    } else if ( !buy_sell_div.equals( o.buy_sell_div ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (tran_type!=null? tran_type.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
    ;
  }

}

@
