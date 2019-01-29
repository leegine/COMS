head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.18.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MailAddressRegiPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MailAddressRegi</b>データベーステーブルで一意である1つのレコードをあらわす<b>MailAddressRegi</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MailAddressRegi</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MailAddressRegiRow 
 */
public class MailAddressRegiPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mail_address_regi";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MailAddressRegiRow. 
   */
  public RowType getRowType() {
    return MailAddressRegiRow.TYPE;
  }

  /**
   * <em>mail_address_regi_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long mail_address_regi_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public MailAddressRegiPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_mailAddressRegiId <em>mail_address_regi_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public MailAddressRegiPK( long p_mailAddressRegiId ) {
      mail_address_regi_id = p_mailAddressRegiId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MailAddressRegiPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MailAddressRegiPK pk = new MailAddressRegiPK();
    pk.mail_address_regi_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(mail_address_regi_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MailAddressRegiPK) )
      return false;
    MailAddressRegiPK o = (MailAddressRegiPK) other;
    if ( mail_address_regi_id != o.mail_address_regi_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) mail_address_regi_id)
    ;
  }

}

@
