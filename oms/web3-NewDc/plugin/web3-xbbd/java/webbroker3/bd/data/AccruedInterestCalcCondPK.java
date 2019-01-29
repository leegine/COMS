head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	AccruedInterestCalcCondPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * <b>AccruedInterestCalcCond</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccruedInterestCalcCond</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccruedInterestCalcCond</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccruedInterestCalcCondRow 
 */
public class AccruedInterestCalcCondPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "accrued_interest_calc_cond";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccruedInterestCalcCondRow. 
   */
  public RowType getRowType() {
    return AccruedInterestCalcCondRow.TYPE;
  }

  /**
   * <em>accrued_interest_calc_type</em>カラムの値をあらわす3桁以下のString値 
   */
  public String accrued_interest_calc_type;


  /** 
   * デフォルトコンストラクタ 
   */
  public AccruedInterestCalcCondPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_accruedInterestCalcType <em>accrued_interest_calc_type</em>カラムの値をあらわす3桁以下のString値 
   */
  public AccruedInterestCalcCondPK( String p_accruedInterestCalcType ) {
      accrued_interest_calc_type = p_accruedInterestCalcType;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccruedInterestCalcCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new AccruedInterestCalcCondPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return accrued_interest_calc_type;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccruedInterestCalcCondPK) )
      return false;
    AccruedInterestCalcCondPK o = (AccruedInterestCalcCondPK) other;
    if ( accrued_interest_calc_type == null ) {
      if ( o.accrued_interest_calc_type != null )
        return false;
    } else if ( !accrued_interest_calc_type.equals( o.accrued_interest_calc_type ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (accrued_interest_calc_type!=null? accrued_interest_calc_type.hashCode(): 0) 
    ;
  }

}

@
