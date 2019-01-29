head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	RequestCodesForReadPK.java;


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
 * <b>RequestCodesForRead</b>データベーステーブルで一意である1つのレコードをあらわす<b>RequestCodesForRead</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RequestCodesForRead</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RequestCodesForReadRow 
 */
public class RequestCodesForReadPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "request_codes_for_read";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RequestCodesForReadRow. 
   */
  public RowType getRowType() {
    return RequestCodesForReadRow.TYPE;
  }

  /**
   * <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public String request_code;
  /**
   * <em>ptype</em>カラムの値をあらわす100桁以下のString値 
   */
  public String ptype;


  /** 
   * デフォルトコンストラクタ 
   */
  public RequestCodesForReadPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   * @@param p_ptype <em>ptype</em>カラムの値をあらわす100桁以下のString値 
   */
  public RequestCodesForReadPK( String p_requestCode, String p_ptype ) {
      request_code = p_requestCode;
      ptype = p_ptype;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RequestCodesForReadPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RequestCodesForReadPK pk = new RequestCodesForReadPK();
    int i = pkValueString.indexOf(',');
    pk.request_code = pkValueString.substring(0,i);
    pk.ptype = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = request_code + "," + ptype;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RequestCodesForReadPK) )
      return false;
    RequestCodesForReadPK o = (RequestCodesForReadPK) other;
    if ( request_code == null ) {
      if ( o.request_code != null )
        return false;
    } else if ( !request_code.equals( o.request_code ) ) {
        return false;
    }
    if ( ptype == null ) {
      if ( o.ptype != null )
        return false;
    } else if ( !ptype.equals( o.ptype ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (request_code!=null? request_code.hashCode(): 0) 
        + (ptype!=null? ptype.hashCode(): 0) 
    ;
  }

}

@
