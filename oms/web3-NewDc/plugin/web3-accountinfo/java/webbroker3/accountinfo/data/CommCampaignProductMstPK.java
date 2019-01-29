head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignProductMstPK.java;


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
 * <b>CommCampaignProductMst</b>データベーステーブルで一意である1つのレコードをあらわす<b>CommCampaignProductMst</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CommCampaignProductMst</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommCampaignProductMstRow 
 */
public class CommCampaignProductMstPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comm_campaign_product_mst";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CommCampaignProductMstRow. 
   */
  public RowType getRowType() {
    return CommCampaignProductMstRow.TYPE;
  }

  /**
   * <em>campaign_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long campaign_id;
  /**
   * <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String comm_product_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public CommCampaignProductMstPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_campaignId <em>campaign_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public CommCampaignProductMstPK( long p_campaignId, String p_commProductCode ) {
      campaign_id = p_campaignId;
      comm_product_code = p_commProductCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CommCampaignProductMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CommCampaignProductMstPK pk = new CommCampaignProductMstPK();
    int i = pkValueString.indexOf(',');
    pk.campaign_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.comm_product_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(campaign_id) + "," + comm_product_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CommCampaignProductMstPK) )
      return false;
    CommCampaignProductMstPK o = (CommCampaignProductMstPK) other;
    if ( campaign_id != o.campaign_id )
      return false;
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) campaign_id)
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
    ;
  }

}

@
