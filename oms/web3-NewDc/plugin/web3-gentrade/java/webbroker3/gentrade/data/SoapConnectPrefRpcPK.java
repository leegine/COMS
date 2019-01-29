head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SoapConnectPrefRpcPK.java;


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
 * <b>SoapConnectPrefRpc</b>データベーステーブルで一意である1つのレコードをあらわす<b>SoapConnectPrefRpc</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SoapConnectPrefRpc</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SoapConnectPrefRpcRow 
 */
public class SoapConnectPrefRpcPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "soap_connect_pref_rpc";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SoapConnectPrefRpcRow. 
   */
  public RowType getRowType() {
    return SoapConnectPrefRpcRow.TYPE;
  }

  /**
   * <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long branch_id;
  /**
   * <em>connect_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String connect_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public SoapConnectPrefRpcPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_connectDiv <em>connect_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public SoapConnectPrefRpcPK( long p_branchId, String p_connectDiv ) {
      branch_id = p_branchId;
      connect_div = p_connectDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SoapConnectPrefRpcPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SoapConnectPrefRpcPK pk = new SoapConnectPrefRpcPK();
    int i = pkValueString.indexOf(',');
    pk.branch_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.connect_div = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(branch_id) + "," + connect_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SoapConnectPrefRpcPK) )
      return false;
    SoapConnectPrefRpcPK o = (SoapConnectPrefRpcPK) other;
    if ( branch_id != o.branch_id )
      return false;
    if ( connect_div == null ) {
      if ( o.connect_div != null )
        return false;
    } else if ( !connect_div.equals( o.connect_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) branch_id)
        + (connect_div!=null? connect_div.hashCode(): 0) 
    ;
  }

}

@
