head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.39.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankDepositErrorHistoryPK.java;


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
 * <b>BankDepositErrorHistory</b>データベーステーブルで一意である1つのレコードをあらわす<b>BankDepositErrorHistory</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BankDepositErrorHistory</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankDepositErrorHistoryRow 
 */
public class BankDepositErrorHistoryPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bank_deposit_error_history";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BankDepositErrorHistoryRow. 
   */
  public RowType getRowType() {
    return BankDepositErrorHistoryRow.TYPE;
  }

  /**
   * <em>bank_deposit_error_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long bank_deposit_error_history_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public BankDepositErrorHistoryPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_bankDepositErrorHistoryId <em>bank_deposit_error_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public BankDepositErrorHistoryPK( long p_bankDepositErrorHistoryId ) {
      bank_deposit_error_history_id = p_bankDepositErrorHistoryId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BankDepositErrorHistoryPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BankDepositErrorHistoryPK pk = new BankDepositErrorHistoryPK();
    pk.bank_deposit_error_history_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(bank_deposit_error_history_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BankDepositErrorHistoryPK) )
      return false;
    BankDepositErrorHistoryPK o = (BankDepositErrorHistoryPK) other;
    if ( bank_deposit_error_history_id != o.bank_deposit_error_history_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) bank_deposit_error_history_id)
    ;
  }

}

@
