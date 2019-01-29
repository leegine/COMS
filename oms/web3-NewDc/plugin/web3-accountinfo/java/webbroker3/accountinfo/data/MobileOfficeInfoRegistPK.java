head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	MobileOfficeInfoRegistPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MobileOfficeInfoRegist</b>データベーステーブルで一意である1つのレコードをあらわす<b>MobileOfficeInfoRegist</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MobileOfficeInfoRegist</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MobileOfficeInfoRegistRow 
 */
public class MobileOfficeInfoRegistPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mobile_office_info_regist";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MobileOfficeInfoRegistRow. 
   */
  public RowType getRowType() {
    return MobileOfficeInfoRegistRow.TYPE;
  }

  /**
   * <em>mobile_office_info_regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long mobile_office_info_regist_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public MobileOfficeInfoRegistPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_mobileOfficeInfoRegistId <em>mobile_office_info_regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public MobileOfficeInfoRegistPK( long p_mobileOfficeInfoRegistId ) {
      mobile_office_info_regist_id = p_mobileOfficeInfoRegistId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MobileOfficeInfoRegistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MobileOfficeInfoRegistPK pk = new MobileOfficeInfoRegistPK();
    pk.mobile_office_info_regist_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(mobile_office_info_regist_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MobileOfficeInfoRegistPK) )
      return false;
    MobileOfficeInfoRegistPK o = (MobileOfficeInfoRegistPK) other;
    if ( mobile_office_info_regist_id != o.mobile_office_info_regist_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) mobile_office_info_regist_id)
    ;
  }

}

@
