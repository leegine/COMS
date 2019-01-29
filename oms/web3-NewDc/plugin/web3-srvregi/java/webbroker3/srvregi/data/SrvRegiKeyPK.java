head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiKeyPK.java;


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
 * <b>SrvRegiKey</b>データベーステーブルで一意である1つのレコードをあらわす<b>SrvRegiKey</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SrvRegiKey</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiKeyRow 
 */
public class SrvRegiKeyPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "srv_regi_key";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SrvRegiKeyRow. 
   */
  public RowType getRowType() {
    return SrvRegiKeyRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public String srv_div;
  /**
   * <em>srv_use_key_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String srv_use_key_type;
  /**
   * <em>srv_use_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long srv_use_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public SrvRegiKeyPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_srvDiv <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_srvUseKeyType <em>srv_use_key_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_srvUseId <em>srv_use_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public SrvRegiKeyPK( String p_institutionCode, String p_srvDiv, String p_srvUseKeyType, long p_srvUseId ) {
      institution_code = p_institutionCode;
      srv_div = p_srvDiv;
      srv_use_key_type = p_srvUseKeyType;
      srv_use_id = p_srvUseId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SrvRegiKeyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SrvRegiKeyPK pk = new SrvRegiKeyPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.srv_div = st.nextToken();
    pk.srv_use_key_type = st.nextToken();
    pk.srv_use_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + srv_div + "," + srv_use_key_type + "," + String.valueOf(srv_use_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SrvRegiKeyPK) )
      return false;
    SrvRegiKeyPK o = (SrvRegiKeyPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( o.srv_div != null )
        return false;
    } else if ( !srv_div.equals( o.srv_div ) ) {
        return false;
    }
    if ( srv_use_key_type == null ) {
      if ( o.srv_use_key_type != null )
        return false;
    } else if ( !srv_use_key_type.equals( o.srv_use_key_type ) ) {
        return false;
    }
    if ( srv_use_id != o.srv_use_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + (srv_use_key_type!=null? srv_use_key_type.hashCode(): 0) 
        + ((int) srv_use_id)
    ;
  }

}

@
