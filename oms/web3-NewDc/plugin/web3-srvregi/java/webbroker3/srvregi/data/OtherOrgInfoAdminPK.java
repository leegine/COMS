head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.43.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	OtherOrgInfoAdminPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OtherOrgInfoAdmin</b>データベーステーブルで一意である1つのレコードをあらわす<b>OtherOrgInfoAdmin</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OtherOrgInfoAdmin</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgInfoAdminRow 
 */
public class OtherOrgInfoAdminPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "other_org_info_admin";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OtherOrgInfoAdminRow. 
   */
  public RowType getRowType() {
    return OtherOrgInfoAdminRow.TYPE;
  }

  /**
   * <em>sequence_number</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long sequence_number;
  /**
   * <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public String srv_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public OtherOrgInfoAdminPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_sequenceNumber <em>sequence_number</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_srvDiv <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public OtherOrgInfoAdminPK( long p_sequenceNumber, String p_srvDiv ) {
      sequence_number = p_sequenceNumber;
      srv_div = p_srvDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OtherOrgInfoAdminPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OtherOrgInfoAdminPK pk = new OtherOrgInfoAdminPK();
    int i = pkValueString.indexOf(',');
    pk.sequence_number = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.srv_div = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(sequence_number) + "," + srv_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OtherOrgInfoAdminPK) )
      return false;
    OtherOrgInfoAdminPK o = (OtherOrgInfoAdminPK) other;
    if ( sequence_number != o.sequence_number )
      return false;
    if ( srv_div == null ) {
      if ( o.srv_div != null )
        return false;
    } else if ( !srv_div.equals( o.srv_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) sequence_number)
        + (srv_div!=null? srv_div.hashCode(): 0) 
    ;
  }

}

@
