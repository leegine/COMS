head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcResultPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>IfoDepositCalcResult</b>データベーステーブルで一意である1つのレコードをあらわす<b>IfoDepositCalcResult</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>IfoDepositCalcResult</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDepositCalcResultRow 
 */
public class IfoDepositCalcResultPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_deposit_calc_result";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: IfoDepositCalcResultRow. 
   */
  public RowType getRowType() {
    return IfoDepositCalcResultRow.TYPE;
  }

  /**
   * <em>deposit_info_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long deposit_info_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public IfoDepositCalcResultPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_depositInfoId <em>deposit_info_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public IfoDepositCalcResultPK( long p_depositInfoId ) {
      deposit_info_id = p_depositInfoId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static IfoDepositCalcResultPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoDepositCalcResultPK pk = new IfoDepositCalcResultPK();
    pk.deposit_info_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(deposit_info_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoDepositCalcResultPK) )
      return false;
    IfoDepositCalcResultPK o = (IfoDepositCalcResultPK) other;
    if ( deposit_info_id != o.deposit_info_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) deposit_info_id)
    ;
  }

}

@
