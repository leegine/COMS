head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadTempPK.java;


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
 * <b>AdministratorUploadTemp</b>データベーステーブルで一意である1つのレコードをあらわす<b>AdministratorUploadTemp</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AdministratorUploadTemp</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdministratorUploadTempRow 
 */
public class AdministratorUploadTempPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "administrator_upload_temp";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AdministratorUploadTempRow. 
   */
  public RowType getRowType() {
    return AdministratorUploadTempRow.TYPE;
  }

  /**
   * <em>administrator_upload_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long administrator_upload_id;
  /**
   * <em>line_number</em>カラムの値をあらわす6桁以下のint値 
   */
  public int line_number;


  /** 
   * デフォルトコンストラクタ 
   */
  public AdministratorUploadTempPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_administratorUploadId <em>administrator_upload_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_lineNumber <em>line_number</em>カラムの値をあらわす6桁以下のint値 
   */
  public AdministratorUploadTempPK( long p_administratorUploadId, int p_lineNumber ) {
      administrator_upload_id = p_administratorUploadId;
      line_number = p_lineNumber;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AdministratorUploadTempPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AdministratorUploadTempPK pk = new AdministratorUploadTempPK();
    int i = pkValueString.indexOf(',');
    pk.administrator_upload_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.line_number = Integer.valueOf(pkValueString.substring(i+1)).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(administrator_upload_id) + "," + String.valueOf(line_number);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AdministratorUploadTempPK) )
      return false;
    AdministratorUploadTempPK o = (AdministratorUploadTempPK) other;
    if ( administrator_upload_id != o.administrator_upload_id )
      return false;
    if ( line_number != o.line_number )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) administrator_upload_id)
        + ((int) line_number)
    ;
  }

}

@
