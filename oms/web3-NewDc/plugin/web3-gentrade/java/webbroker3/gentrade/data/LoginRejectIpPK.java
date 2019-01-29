head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginRejectIpPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>LoginRejectIp</b>データベーステーブルで一意である1つのレコードをあらわす<b>LoginRejectIp</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>LoginRejectIp</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LoginRejectIpRow 
 */
public class LoginRejectIpPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "login_reject_ip";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: LoginRejectIpRow. 
   */
  public RowType getRowType() {
    return LoginRejectIpRow.TYPE;
  }

  /**
   * <em>login_reject_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long login_reject_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public LoginRejectIpPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_loginRejectId <em>login_reject_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public LoginRejectIpPK( long p_loginRejectId ) {
      login_reject_id = p_loginRejectId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static LoginRejectIpPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    LoginRejectIpPK pk = new LoginRejectIpPK();
    pk.login_reject_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(login_reject_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof LoginRejectIpPK) )
      return false;
    LoginRejectIpPK o = (LoginRejectIpPK) other;
    if ( login_reject_id != o.login_reject_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) login_reject_id)
    ;
  }

}

@
