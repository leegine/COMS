head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadPK.java;


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
 * <b>AdministratorUpload</b>データベーステーブルで一意である1つのレコードをあらわす<b>AdministratorUpload</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AdministratorUpload</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdministratorUploadRow 
 */
public class AdministratorUploadPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "administrator_upload";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AdministratorUploadRow. 
   */
  public RowType getRowType() {
    return AdministratorUploadRow.TYPE;
  }

  /**
   * <em>administrator_upload_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long administrator_upload_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public AdministratorUploadPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_administratorUploadId <em>administrator_upload_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public AdministratorUploadPK( long p_administratorUploadId ) {
      administrator_upload_id = p_administratorUploadId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AdministratorUploadPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AdministratorUploadPK pk = new AdministratorUploadPK();
    pk.administrator_upload_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(administrator_upload_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AdministratorUploadPK) )
      return false;
    AdministratorUploadPK o = (AdministratorUploadPK) other;
    if ( administrator_upload_id != o.administrator_upload_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) administrator_upload_id)
    ;
  }

}

@
