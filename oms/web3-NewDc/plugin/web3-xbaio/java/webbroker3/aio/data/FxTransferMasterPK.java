head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.39.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FxTransferMasterPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * <b>FxTransferMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>FxTransferMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FxTransferMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FxTransferMasterRow 
 */
public class FxTransferMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "fx_transfer_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FxTransferMasterRow. 
   */
  public RowType getRowType() {
    return FxTransferMasterRow.TYPE;
  }

  /**
   * <em>fx_system_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long fx_system_id;
  /**
   * <em>transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String transfer_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public FxTransferMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_fxSystemId <em>fx_system_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_transferDiv <em>transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public FxTransferMasterPK( long p_fxSystemId, String p_transferDiv ) {
      fx_system_id = p_fxSystemId;
      transfer_div = p_transferDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FxTransferMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FxTransferMasterPK pk = new FxTransferMasterPK();
    int i = pkValueString.indexOf(',');
    pk.fx_system_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.transfer_div = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(fx_system_id) + "," + transfer_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FxTransferMasterPK) )
      return false;
    FxTransferMasterPK o = (FxTransferMasterPK) other;
    if ( fx_system_id != o.fx_system_id )
      return false;
    if ( transfer_div == null ) {
      if ( o.transfer_div != null )
        return false;
    } else if ( !transfer_div.equals( o.transfer_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) fx_system_id)
        + (transfer_div!=null? transfer_div.hashCode(): 0) 
    ;
  }

}

@
