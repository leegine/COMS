head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.22.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketRepayDealtCondParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * branch_market_repay_dealt_condテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BranchMarketRepayDealtCondRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BranchMarketRepayDealtCondParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BranchMarketRepayDealtCondParams}が{@@link BranchMarketRepayDealtCondRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchMarketRepayDealtCondPK 
 * @@see BranchMarketRepayDealtCondRow 
 */
public class BranchMarketRepayDealtCondParams extends Params implements BranchMarketRepayDealtCondRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch_market_repay_dealt_cond";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BranchMarketRepayDealtCondRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BranchMarketRepayDealtCondRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>repayment_div</em>カラムの値 
   */
  public  String  repayment_div;

  /** 
   * <em>repayment_num</em>カラムの値 
   */
  public  int  repayment_num;

  /** 
   * <em>sonar_repayment_type</em>カラムの値 
   */
  public  String  sonar_repayment_type;

  /** 
   * <em>mart_can_dealt</em>カラムの値 
   */
  public  String  mart_can_dealt;

  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値 
   */
  public  Integer  limited_unit_m_long_1st_sec;

  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値 
   */
  public  Integer  limited_unit_m_long_2nd_sec;

  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値 
   */
  public  Integer  limited_unit_m_short_1st_sec;

  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値 
   */
  public  Integer  limited_unit_m_short_2nd_sec;

  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値 
   */
  public  Integer  limited_unit_cm_long_1st_sec;

  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値 
   */
  public  Integer  limited_unit_cm_long_2nd_sec;

  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値 
   */
  public  Integer  limited_unit_cm_short_1st_sec;

  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値 
   */
  public  Integer  limited_unit_cm_short_2nd_sec;

  /** 
   * <em>cont_liquidate_term</em>カラムの値 
   */
  public  Integer  cont_liquidate_term;

  /** 
   * <em>buy_interest_rate</em>カラムの値 
   */
  public  Double  buy_interest_rate;

  /** 
   * <em>sell_interest_rate</em>カラムの値 
   */
  public  Double  sell_interest_rate;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean repayment_div_is_set = false;
  private boolean repayment_div_is_modified = false;
  private boolean repayment_num_is_set = false;
  private boolean repayment_num_is_modified = false;
  private boolean sonar_repayment_type_is_set = false;
  private boolean sonar_repayment_type_is_modified = false;
  private boolean mart_can_dealt_is_set = false;
  private boolean mart_can_dealt_is_modified = false;
  private boolean limited_unit_m_long_1st_sec_is_set = false;
  private boolean limited_unit_m_long_1st_sec_is_modified = false;
  private boolean limited_unit_m_long_2nd_sec_is_set = false;
  private boolean limited_unit_m_long_2nd_sec_is_modified = false;
  private boolean limited_unit_m_short_1st_sec_is_set = false;
  private boolean limited_unit_m_short_1st_sec_is_modified = false;
  private boolean limited_unit_m_short_2nd_sec_is_set = false;
  private boolean limited_unit_m_short_2nd_sec_is_modified = false;
  private boolean limited_unit_cm_long_1st_sec_is_set = false;
  private boolean limited_unit_cm_long_1st_sec_is_modified = false;
  private boolean limited_unit_cm_long_2nd_sec_is_set = false;
  private boolean limited_unit_cm_long_2nd_sec_is_modified = false;
  private boolean limited_unit_cm_short_1st_sec_is_set = false;
  private boolean limited_unit_cm_short_1st_sec_is_modified = false;
  private boolean limited_unit_cm_short_2nd_sec_is_set = false;
  private boolean limited_unit_cm_short_2nd_sec_is_modified = false;
  private boolean cont_liquidate_term_is_set = false;
  private boolean cont_liquidate_term_is_modified = false;
  private boolean buy_interest_rate_is_set = false;
  private boolean buy_interest_rate_is_modified = false;
  private boolean sell_interest_rate_is_set = false;
  private boolean sell_interest_rate_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "market_code=" + market_code
      + "," + "repayment_div=" + repayment_div
      + "," + "repayment_num=" + repayment_num
      + "," + "sonar_repayment_type=" +sonar_repayment_type
      + "," + "mart_can_dealt=" +mart_can_dealt
      + "," + "limited_unit_m_long_1st_sec=" +limited_unit_m_long_1st_sec
      + "," + "limited_unit_m_long_2nd_sec=" +limited_unit_m_long_2nd_sec
      + "," + "limited_unit_m_short_1st_sec=" +limited_unit_m_short_1st_sec
      + "," + "limited_unit_m_short_2nd_sec=" +limited_unit_m_short_2nd_sec
      + "," + "limited_unit_cm_long_1st_sec=" +limited_unit_cm_long_1st_sec
      + "," + "limited_unit_cm_long_2nd_sec=" +limited_unit_cm_long_2nd_sec
      + "," + "limited_unit_cm_short_1st_sec=" +limited_unit_cm_short_1st_sec
      + "," + "limited_unit_cm_short_2nd_sec=" +limited_unit_cm_short_2nd_sec
      + "," + "cont_liquidate_term=" +cont_liquidate_term
      + "," + "buy_interest_rate=" +buy_interest_rate
      + "," + "sell_interest_rate=" +sell_interest_rate
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBranchMarketRepayDealtCondParamsオブジェクトを作成します。 
   */
  public BranchMarketRepayDealtCondParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    market_code_is_modified = true;
    repayment_div_is_modified = true;
    repayment_num_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBranchMarketRepayDealtCondRowオブジェクトの値を利用してBranchMarketRepayDealtCondParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBranchMarketRepayDealtCondRowオブジェクト 
   */
  public BranchMarketRepayDealtCondParams( BranchMarketRepayDealtCondRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    repayment_div = p_row.getRepaymentDiv();
    repayment_div_is_set = p_row.getRepaymentDivIsSet();
    repayment_div_is_modified = p_row.getRepaymentDivIsModified();
    repayment_num = p_row.getRepaymentNum();
    repayment_num_is_set = p_row.getRepaymentNumIsSet();
    repayment_num_is_modified = p_row.getRepaymentNumIsModified();
    sonar_repayment_type = p_row.getSonarRepaymentType();
    sonar_repayment_type_is_set = p_row.getSonarRepaymentTypeIsSet();
    sonar_repayment_type_is_modified = p_row.getSonarRepaymentTypeIsModified();
    mart_can_dealt = p_row.getMartCanDealt();
    mart_can_dealt_is_set = p_row.getMartCanDealtIsSet();
    mart_can_dealt_is_modified = p_row.getMartCanDealtIsModified();
    if ( !p_row.getLimitedUnitMLong1stSecIsNull() )
      limited_unit_m_long_1st_sec = new Integer( p_row.getLimitedUnitMLong1stSec() );
    limited_unit_m_long_1st_sec_is_set = p_row.getLimitedUnitMLong1stSecIsSet();
    limited_unit_m_long_1st_sec_is_modified = p_row.getLimitedUnitMLong1stSecIsModified();
    if ( !p_row.getLimitedUnitMLong2ndSecIsNull() )
      limited_unit_m_long_2nd_sec = new Integer( p_row.getLimitedUnitMLong2ndSec() );
    limited_unit_m_long_2nd_sec_is_set = p_row.getLimitedUnitMLong2ndSecIsSet();
    limited_unit_m_long_2nd_sec_is_modified = p_row.getLimitedUnitMLong2ndSecIsModified();
    if ( !p_row.getLimitedUnitMShort1stSecIsNull() )
      limited_unit_m_short_1st_sec = new Integer( p_row.getLimitedUnitMShort1stSec() );
    limited_unit_m_short_1st_sec_is_set = p_row.getLimitedUnitMShort1stSecIsSet();
    limited_unit_m_short_1st_sec_is_modified = p_row.getLimitedUnitMShort1stSecIsModified();
    if ( !p_row.getLimitedUnitMShort2ndSecIsNull() )
      limited_unit_m_short_2nd_sec = new Integer( p_row.getLimitedUnitMShort2ndSec() );
    limited_unit_m_short_2nd_sec_is_set = p_row.getLimitedUnitMShort2ndSecIsSet();
    limited_unit_m_short_2nd_sec_is_modified = p_row.getLimitedUnitMShort2ndSecIsModified();
    if ( !p_row.getLimitedUnitCmLong1stSecIsNull() )
      limited_unit_cm_long_1st_sec = new Integer( p_row.getLimitedUnitCmLong1stSec() );
    limited_unit_cm_long_1st_sec_is_set = p_row.getLimitedUnitCmLong1stSecIsSet();
    limited_unit_cm_long_1st_sec_is_modified = p_row.getLimitedUnitCmLong1stSecIsModified();
    if ( !p_row.getLimitedUnitCmLong2ndSecIsNull() )
      limited_unit_cm_long_2nd_sec = new Integer( p_row.getLimitedUnitCmLong2ndSec() );
    limited_unit_cm_long_2nd_sec_is_set = p_row.getLimitedUnitCmLong2ndSecIsSet();
    limited_unit_cm_long_2nd_sec_is_modified = p_row.getLimitedUnitCmLong2ndSecIsModified();
    if ( !p_row.getLimitedUnitCmShort1stSecIsNull() )
      limited_unit_cm_short_1st_sec = new Integer( p_row.getLimitedUnitCmShort1stSec() );
    limited_unit_cm_short_1st_sec_is_set = p_row.getLimitedUnitCmShort1stSecIsSet();
    limited_unit_cm_short_1st_sec_is_modified = p_row.getLimitedUnitCmShort1stSecIsModified();
    if ( !p_row.getLimitedUnitCmShort2ndSecIsNull() )
      limited_unit_cm_short_2nd_sec = new Integer( p_row.getLimitedUnitCmShort2ndSec() );
    limited_unit_cm_short_2nd_sec_is_set = p_row.getLimitedUnitCmShort2ndSecIsSet();
    limited_unit_cm_short_2nd_sec_is_modified = p_row.getLimitedUnitCmShort2ndSecIsModified();
    if ( !p_row.getContLiquidateTermIsNull() )
      cont_liquidate_term = new Integer( p_row.getContLiquidateTerm() );
    cont_liquidate_term_is_set = p_row.getContLiquidateTermIsSet();
    cont_liquidate_term_is_modified = p_row.getContLiquidateTermIsModified();
    if ( !p_row.getBuyInterestRateIsNull() )
      buy_interest_rate = new Double( p_row.getBuyInterestRate() );
    buy_interest_rate_is_set = p_row.getBuyInterestRateIsSet();
    buy_interest_rate_is_modified = p_row.getBuyInterestRateIsModified();
    if ( !p_row.getSellInterestRateIsNull() )
      sell_interest_rate = new Double( p_row.getSellInterestRate() );
    sell_interest_rate_is_set = p_row.getSellInterestRateIsSet();
    sell_interest_rate_is_modified = p_row.getSellInterestRateIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      sonar_repayment_type_is_set = true;
      sonar_repayment_type_is_modified = true;
      mart_can_dealt_is_set = true;
      mart_can_dealt_is_modified = true;
      limited_unit_m_long_1st_sec_is_set = true;
      limited_unit_m_long_1st_sec_is_modified = true;
      limited_unit_m_long_2nd_sec_is_set = true;
      limited_unit_m_long_2nd_sec_is_modified = true;
      limited_unit_m_short_1st_sec_is_set = true;
      limited_unit_m_short_1st_sec_is_modified = true;
      limited_unit_m_short_2nd_sec_is_set = true;
      limited_unit_m_short_2nd_sec_is_modified = true;
      limited_unit_cm_long_1st_sec_is_set = true;
      limited_unit_cm_long_1st_sec_is_modified = true;
      limited_unit_cm_long_2nd_sec_is_set = true;
      limited_unit_cm_long_2nd_sec_is_modified = true;
      limited_unit_cm_short_1st_sec_is_set = true;
      limited_unit_cm_short_1st_sec_is_modified = true;
      limited_unit_cm_short_2nd_sec_is_set = true;
      limited_unit_cm_short_2nd_sec_is_modified = true;
      cont_liquidate_term_is_set = true;
      cont_liquidate_term_is_modified = true;
      buy_interest_rate_is_set = true;
      buy_interest_rate_is_modified = true;
      sell_interest_rate_is_set = true;
      sell_interest_rate_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BranchMarketRepayDealtCondRow ) )
       return false;
    return fieldsEqual( (BranchMarketRepayDealtCondRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBranchMarketRepayDealtCondRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BranchMarketRepayDealtCondRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( repayment_div == null ) {
      if ( row.getRepaymentDiv() != null )
        return false;
    } else if ( !repayment_div.equals( row.getRepaymentDiv() ) ) {
        return false;
    }
    if ( repayment_num != row.getRepaymentNum() )
      return false;
    if ( sonar_repayment_type == null ) {
      if ( row.getSonarRepaymentType() != null )
        return false;
    } else if ( !sonar_repayment_type.equals( row.getSonarRepaymentType() ) ) {
        return false;
    }
    if ( mart_can_dealt == null ) {
      if ( row.getMartCanDealt() != null )
        return false;
    } else if ( !mart_can_dealt.equals( row.getMartCanDealt() ) ) {
        return false;
    }
    if ( limited_unit_m_long_1st_sec == null ) {
      if ( !row.getLimitedUnitMLong1stSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitMLong1stSecIsNull() || ( limited_unit_m_long_1st_sec.intValue() != row.getLimitedUnitMLong1stSec() ) ) {
        return false;
    }
    if ( limited_unit_m_long_2nd_sec == null ) {
      if ( !row.getLimitedUnitMLong2ndSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitMLong2ndSecIsNull() || ( limited_unit_m_long_2nd_sec.intValue() != row.getLimitedUnitMLong2ndSec() ) ) {
        return false;
    }
    if ( limited_unit_m_short_1st_sec == null ) {
      if ( !row.getLimitedUnitMShort1stSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitMShort1stSecIsNull() || ( limited_unit_m_short_1st_sec.intValue() != row.getLimitedUnitMShort1stSec() ) ) {
        return false;
    }
    if ( limited_unit_m_short_2nd_sec == null ) {
      if ( !row.getLimitedUnitMShort2ndSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitMShort2ndSecIsNull() || ( limited_unit_m_short_2nd_sec.intValue() != row.getLimitedUnitMShort2ndSec() ) ) {
        return false;
    }
    if ( limited_unit_cm_long_1st_sec == null ) {
      if ( !row.getLimitedUnitCmLong1stSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitCmLong1stSecIsNull() || ( limited_unit_cm_long_1st_sec.intValue() != row.getLimitedUnitCmLong1stSec() ) ) {
        return false;
    }
    if ( limited_unit_cm_long_2nd_sec == null ) {
      if ( !row.getLimitedUnitCmLong2ndSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitCmLong2ndSecIsNull() || ( limited_unit_cm_long_2nd_sec.intValue() != row.getLimitedUnitCmLong2ndSec() ) ) {
        return false;
    }
    if ( limited_unit_cm_short_1st_sec == null ) {
      if ( !row.getLimitedUnitCmShort1stSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitCmShort1stSecIsNull() || ( limited_unit_cm_short_1st_sec.intValue() != row.getLimitedUnitCmShort1stSec() ) ) {
        return false;
    }
    if ( limited_unit_cm_short_2nd_sec == null ) {
      if ( !row.getLimitedUnitCmShort2ndSecIsNull() )
        return false;
    } else if ( row.getLimitedUnitCmShort2ndSecIsNull() || ( limited_unit_cm_short_2nd_sec.intValue() != row.getLimitedUnitCmShort2ndSec() ) ) {
        return false;
    }
    if ( cont_liquidate_term == null ) {
      if ( !row.getContLiquidateTermIsNull() )
        return false;
    } else if ( row.getContLiquidateTermIsNull() || ( cont_liquidate_term.intValue() != row.getContLiquidateTerm() ) ) {
        return false;
    }
    if ( buy_interest_rate == null ) {
      if ( !row.getBuyInterestRateIsNull() )
        return false;
    } else if ( row.getBuyInterestRateIsNull() || ( buy_interest_rate.doubleValue() != row.getBuyInterestRate() ) ) {
        return false;
    }
    if ( sell_interest_rate == null ) {
      if ( !row.getSellInterestRateIsNull() )
        return false;
    } else if ( row.getSellInterestRateIsNull() || ( sell_interest_rate.doubleValue() != row.getSellInterestRate() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (repayment_div!=null? repayment_div.hashCode(): 0) 
        + ((int) repayment_num)
        + (sonar_repayment_type!=null? sonar_repayment_type.hashCode(): 0) 
        + (mart_can_dealt!=null? mart_can_dealt.hashCode(): 0) 
        + (limited_unit_m_long_1st_sec!=null? limited_unit_m_long_1st_sec.hashCode(): 0) 
        + (limited_unit_m_long_2nd_sec!=null? limited_unit_m_long_2nd_sec.hashCode(): 0) 
        + (limited_unit_m_short_1st_sec!=null? limited_unit_m_short_1st_sec.hashCode(): 0) 
        + (limited_unit_m_short_2nd_sec!=null? limited_unit_m_short_2nd_sec.hashCode(): 0) 
        + (limited_unit_cm_long_1st_sec!=null? limited_unit_cm_long_1st_sec.hashCode(): 0) 
        + (limited_unit_cm_long_2nd_sec!=null? limited_unit_cm_long_2nd_sec.hashCode(): 0) 
        + (limited_unit_cm_short_1st_sec!=null? limited_unit_cm_short_1st_sec.hashCode(): 0) 
        + (limited_unit_cm_short_2nd_sec!=null? limited_unit_cm_short_2nd_sec.hashCode(): 0) 
        + (cont_liquidate_term!=null? cont_liquidate_term.hashCode(): 0) 
        + (buy_interest_rate!=null? buy_interest_rate.hashCode(): 0) 
        + (sell_interest_rate!=null? sell_interest_rate.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("market_code",market_code);
		map.put("repayment_div",repayment_div);
		map.put("repayment_num",new Integer(repayment_num));
		if ( sonar_repayment_type != null )
			map.put("sonar_repayment_type",sonar_repayment_type);
		if ( mart_can_dealt != null )
			map.put("mart_can_dealt",mart_can_dealt);
		if ( limited_unit_m_long_1st_sec != null )
			map.put("limited_unit_m_long_1st_sec",limited_unit_m_long_1st_sec);
		if ( limited_unit_m_long_2nd_sec != null )
			map.put("limited_unit_m_long_2nd_sec",limited_unit_m_long_2nd_sec);
		if ( limited_unit_m_short_1st_sec != null )
			map.put("limited_unit_m_short_1st_sec",limited_unit_m_short_1st_sec);
		if ( limited_unit_m_short_2nd_sec != null )
			map.put("limited_unit_m_short_2nd_sec",limited_unit_m_short_2nd_sec);
		if ( limited_unit_cm_long_1st_sec != null )
			map.put("limited_unit_cm_long_1st_sec",limited_unit_cm_long_1st_sec);
		if ( limited_unit_cm_long_2nd_sec != null )
			map.put("limited_unit_cm_long_2nd_sec",limited_unit_cm_long_2nd_sec);
		if ( limited_unit_cm_short_1st_sec != null )
			map.put("limited_unit_cm_short_1st_sec",limited_unit_cm_short_1st_sec);
		if ( limited_unit_cm_short_2nd_sec != null )
			map.put("limited_unit_cm_short_2nd_sec",limited_unit_cm_short_2nd_sec);
		if ( cont_liquidate_term != null )
			map.put("cont_liquidate_term",cont_liquidate_term);
		if ( buy_interest_rate != null )
			map.put("buy_interest_rate",buy_interest_rate);
		if ( sell_interest_rate != null )
			map.put("sell_interest_rate",sell_interest_rate);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( sonar_repayment_type_is_modified )
			map.put("sonar_repayment_type",sonar_repayment_type);
		if ( mart_can_dealt_is_modified )
			map.put("mart_can_dealt",mart_can_dealt);
		if ( limited_unit_m_long_1st_sec_is_modified )
			map.put("limited_unit_m_long_1st_sec",limited_unit_m_long_1st_sec);
		if ( limited_unit_m_long_2nd_sec_is_modified )
			map.put("limited_unit_m_long_2nd_sec",limited_unit_m_long_2nd_sec);
		if ( limited_unit_m_short_1st_sec_is_modified )
			map.put("limited_unit_m_short_1st_sec",limited_unit_m_short_1st_sec);
		if ( limited_unit_m_short_2nd_sec_is_modified )
			map.put("limited_unit_m_short_2nd_sec",limited_unit_m_short_2nd_sec);
		if ( limited_unit_cm_long_1st_sec_is_modified )
			map.put("limited_unit_cm_long_1st_sec",limited_unit_cm_long_1st_sec);
		if ( limited_unit_cm_long_2nd_sec_is_modified )
			map.put("limited_unit_cm_long_2nd_sec",limited_unit_cm_long_2nd_sec);
		if ( limited_unit_cm_short_1st_sec_is_modified )
			map.put("limited_unit_cm_short_1st_sec",limited_unit_cm_short_1st_sec);
		if ( limited_unit_cm_short_2nd_sec_is_modified )
			map.put("limited_unit_cm_short_2nd_sec",limited_unit_cm_short_2nd_sec);
		if ( cont_liquidate_term_is_modified )
			map.put("cont_liquidate_term",cont_liquidate_term);
		if ( buy_interest_rate_is_modified )
			map.put("buy_interest_rate",buy_interest_rate);
		if ( sell_interest_rate_is_modified )
			map.put("sell_interest_rate",sell_interest_rate);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("sonar_repayment_type",sonar_repayment_type);
			map.put("mart_can_dealt",mart_can_dealt);
			map.put("limited_unit_m_long_1st_sec",limited_unit_m_long_1st_sec);
			map.put("limited_unit_m_long_2nd_sec",limited_unit_m_long_2nd_sec);
			map.put("limited_unit_m_short_1st_sec",limited_unit_m_short_1st_sec);
			map.put("limited_unit_m_short_2nd_sec",limited_unit_m_short_2nd_sec);
			map.put("limited_unit_cm_long_1st_sec",limited_unit_cm_long_1st_sec);
			map.put("limited_unit_cm_long_2nd_sec",limited_unit_cm_long_2nd_sec);
			map.put("limited_unit_cm_short_1st_sec",limited_unit_cm_short_1st_sec);
			map.put("limited_unit_cm_short_2nd_sec",limited_unit_cm_short_2nd_sec);
			map.put("cont_liquidate_term",cont_liquidate_term);
			map.put("buy_interest_rate",buy_interest_rate);
			map.put("sell_interest_rate",sell_interest_rate);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>repayment_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepaymentDiv()
  {
    return repayment_div;
  }


  /** 
   * <em>repayment_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentDivIsSet() {
    return repayment_div_is_set;
  }


  /** 
   * <em>repayment_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentDivIsModified() {
    return repayment_div_is_modified;
  }


  /** 
   * <em>repayment_num</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRepaymentNum()
  {
    return repayment_num;
  }


  /** 
   * <em>repayment_num</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentNumIsSet() {
    return repayment_num_is_set;
  }


  /** 
   * <em>repayment_num</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentNumIsModified() {
    return repayment_num_is_modified;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarRepaymentType()
  {
    return sonar_repayment_type;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarRepaymentTypeIsSet() {
    return sonar_repayment_type_is_set;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarRepaymentTypeIsModified() {
    return sonar_repayment_type_is_modified;
  }


  /** 
   * <em>mart_can_dealt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMartCanDealt()
  {
    return mart_can_dealt;
  }


  /** 
   * <em>mart_can_dealt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartCanDealtIsSet() {
    return mart_can_dealt_is_set;
  }


  /** 
   * <em>mart_can_dealt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartCanDealtIsModified() {
    return mart_can_dealt_is_modified;
  }


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitMLong1stSec()
  {
    return ( limited_unit_m_long_1st_sec==null? ((int)0): limited_unit_m_long_1st_sec.intValue() );
  }


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitMLong1stSecIsNull()
  {
    return limited_unit_m_long_1st_sec == null;
  }


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMLong1stSecIsSet() {
    return limited_unit_m_long_1st_sec_is_set;
  }


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMLong1stSecIsModified() {
    return limited_unit_m_long_1st_sec_is_modified;
  }


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitMLong2ndSec()
  {
    return ( limited_unit_m_long_2nd_sec==null? ((int)0): limited_unit_m_long_2nd_sec.intValue() );
  }


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitMLong2ndSecIsNull()
  {
    return limited_unit_m_long_2nd_sec == null;
  }


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMLong2ndSecIsSet() {
    return limited_unit_m_long_2nd_sec_is_set;
  }


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMLong2ndSecIsModified() {
    return limited_unit_m_long_2nd_sec_is_modified;
  }


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitMShort1stSec()
  {
    return ( limited_unit_m_short_1st_sec==null? ((int)0): limited_unit_m_short_1st_sec.intValue() );
  }


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitMShort1stSecIsNull()
  {
    return limited_unit_m_short_1st_sec == null;
  }


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMShort1stSecIsSet() {
    return limited_unit_m_short_1st_sec_is_set;
  }


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMShort1stSecIsModified() {
    return limited_unit_m_short_1st_sec_is_modified;
  }


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitMShort2ndSec()
  {
    return ( limited_unit_m_short_2nd_sec==null? ((int)0): limited_unit_m_short_2nd_sec.intValue() );
  }


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitMShort2ndSecIsNull()
  {
    return limited_unit_m_short_2nd_sec == null;
  }


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMShort2ndSecIsSet() {
    return limited_unit_m_short_2nd_sec_is_set;
  }


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitMShort2ndSecIsModified() {
    return limited_unit_m_short_2nd_sec_is_modified;
  }


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitCmLong1stSec()
  {
    return ( limited_unit_cm_long_1st_sec==null? ((int)0): limited_unit_cm_long_1st_sec.intValue() );
  }


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitCmLong1stSecIsNull()
  {
    return limited_unit_cm_long_1st_sec == null;
  }


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmLong1stSecIsSet() {
    return limited_unit_cm_long_1st_sec_is_set;
  }


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmLong1stSecIsModified() {
    return limited_unit_cm_long_1st_sec_is_modified;
  }


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitCmLong2ndSec()
  {
    return ( limited_unit_cm_long_2nd_sec==null? ((int)0): limited_unit_cm_long_2nd_sec.intValue() );
  }


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitCmLong2ndSecIsNull()
  {
    return limited_unit_cm_long_2nd_sec == null;
  }


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmLong2ndSecIsSet() {
    return limited_unit_cm_long_2nd_sec_is_set;
  }


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmLong2ndSecIsModified() {
    return limited_unit_cm_long_2nd_sec_is_modified;
  }


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitCmShort1stSec()
  {
    return ( limited_unit_cm_short_1st_sec==null? ((int)0): limited_unit_cm_short_1st_sec.intValue() );
  }


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitCmShort1stSecIsNull()
  {
    return limited_unit_cm_short_1st_sec == null;
  }


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmShort1stSecIsSet() {
    return limited_unit_cm_short_1st_sec_is_set;
  }


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmShort1stSecIsModified() {
    return limited_unit_cm_short_1st_sec_is_modified;
  }


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getLimitedUnitCmShort2ndSec()
  {
    return ( limited_unit_cm_short_2nd_sec==null? ((int)0): limited_unit_cm_short_2nd_sec.intValue() );
  }


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLimitedUnitCmShort2ndSecIsNull()
  {
    return limited_unit_cm_short_2nd_sec == null;
  }


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmShort2ndSecIsSet() {
    return limited_unit_cm_short_2nd_sec_is_set;
  }


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLimitedUnitCmShort2ndSecIsModified() {
    return limited_unit_cm_short_2nd_sec_is_modified;
  }


  /** 
   * <em>cont_liquidate_term</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getContLiquidateTerm()
  {
    return ( cont_liquidate_term==null? ((int)0): cont_liquidate_term.intValue() );
  }


  /** 
   * <em>cont_liquidate_term</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContLiquidateTermIsNull()
  {
    return cont_liquidate_term == null;
  }


  /** 
   * <em>cont_liquidate_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContLiquidateTermIsSet() {
    return cont_liquidate_term_is_set;
  }


  /** 
   * <em>cont_liquidate_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContLiquidateTermIsModified() {
    return cont_liquidate_term_is_modified;
  }


  /** 
   * <em>buy_interest_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBuyInterestRate()
  {
    return ( buy_interest_rate==null? ((double)0): buy_interest_rate.doubleValue() );
  }


  /** 
   * <em>buy_interest_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyInterestRateIsNull()
  {
    return buy_interest_rate == null;
  }


  /** 
   * <em>buy_interest_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyInterestRateIsSet() {
    return buy_interest_rate_is_set;
  }


  /** 
   * <em>buy_interest_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyInterestRateIsModified() {
    return buy_interest_rate_is_modified;
  }


  /** 
   * <em>sell_interest_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSellInterestRate()
  {
    return ( sell_interest_rate==null? ((double)0): sell_interest_rate.doubleValue() );
  }


  /** 
   * <em>sell_interest_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellInterestRateIsNull()
  {
    return sell_interest_rate == null;
  }


  /** 
   * <em>sell_interest_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellInterestRateIsSet() {
    return sell_interest_rate_is_set;
  }


  /** 
   * <em>sell_interest_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellInterestRateIsModified() {
    return sell_interest_rate_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BranchMarketRepayDealtCondPK(institution_code, branch_code, market_code, repayment_div, repayment_num);
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>market_code</em>カラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>repayment_div</em>カラムの値を設定します。 
   *
   * @@param p_repaymentDiv <em>repayment_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRepaymentDiv( String p_repaymentDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_div = p_repaymentDiv;
    repayment_div_is_set = true;
    repayment_div_is_modified = true;
  }


  /** 
   * <em>repayment_num</em>カラムの値を設定します。 
   *
   * @@param p_repaymentNum <em>repayment_num</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setRepaymentNum( int p_repaymentNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_num = p_repaymentNum;
    repayment_num_is_set = true;
    repayment_num_is_modified = true;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値を設定します。 
   *
   * @@param p_sonarRepaymentType <em>sonar_repayment_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarRepaymentType( String p_sonarRepaymentType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_repayment_type = p_sonarRepaymentType;
    sonar_repayment_type_is_set = true;
    sonar_repayment_type_is_modified = true;
  }


  /** 
   * <em>mart_can_dealt</em>カラムの値を設定します。 
   *
   * @@param p_martCanDealt <em>mart_can_dealt</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMartCanDealt( String p_martCanDealt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mart_can_dealt = p_martCanDealt;
    mart_can_dealt_is_set = true;
    mart_can_dealt_is_modified = true;
  }


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitMLong1stSec <em>limited_unit_m_long_1st_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitMLong1stSec( int p_limitedUnitMLong1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_long_1st_sec = new Integer(p_limitedUnitMLong1stSec);
    limited_unit_m_long_1st_sec_is_set = true;
    limited_unit_m_long_1st_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_m_long_1st_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitMLong1stSec( Integer p_limitedUnitMLong1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_long_1st_sec = p_limitedUnitMLong1stSec;
    limited_unit_m_long_1st_sec_is_set = true;
    limited_unit_m_long_1st_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitMLong2ndSec <em>limited_unit_m_long_2nd_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitMLong2ndSec( int p_limitedUnitMLong2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_long_2nd_sec = new Integer(p_limitedUnitMLong2ndSec);
    limited_unit_m_long_2nd_sec_is_set = true;
    limited_unit_m_long_2nd_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_m_long_2nd_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitMLong2ndSec( Integer p_limitedUnitMLong2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_long_2nd_sec = p_limitedUnitMLong2ndSec;
    limited_unit_m_long_2nd_sec_is_set = true;
    limited_unit_m_long_2nd_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitMShort1stSec <em>limited_unit_m_short_1st_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitMShort1stSec( int p_limitedUnitMShort1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_short_1st_sec = new Integer(p_limitedUnitMShort1stSec);
    limited_unit_m_short_1st_sec_is_set = true;
    limited_unit_m_short_1st_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_m_short_1st_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitMShort1stSec( Integer p_limitedUnitMShort1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_short_1st_sec = p_limitedUnitMShort1stSec;
    limited_unit_m_short_1st_sec_is_set = true;
    limited_unit_m_short_1st_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitMShort2ndSec <em>limited_unit_m_short_2nd_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitMShort2ndSec( int p_limitedUnitMShort2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_short_2nd_sec = new Integer(p_limitedUnitMShort2ndSec);
    limited_unit_m_short_2nd_sec_is_set = true;
    limited_unit_m_short_2nd_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_m_short_2nd_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitMShort2ndSec( Integer p_limitedUnitMShort2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_m_short_2nd_sec = p_limitedUnitMShort2ndSec;
    limited_unit_m_short_2nd_sec_is_set = true;
    limited_unit_m_short_2nd_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitCmLong1stSec <em>limited_unit_cm_long_1st_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitCmLong1stSec( int p_limitedUnitCmLong1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_long_1st_sec = new Integer(p_limitedUnitCmLong1stSec);
    limited_unit_cm_long_1st_sec_is_set = true;
    limited_unit_cm_long_1st_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_cm_long_1st_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitCmLong1stSec( Integer p_limitedUnitCmLong1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_long_1st_sec = p_limitedUnitCmLong1stSec;
    limited_unit_cm_long_1st_sec_is_set = true;
    limited_unit_cm_long_1st_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitCmLong2ndSec <em>limited_unit_cm_long_2nd_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitCmLong2ndSec( int p_limitedUnitCmLong2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_long_2nd_sec = new Integer(p_limitedUnitCmLong2ndSec);
    limited_unit_cm_long_2nd_sec_is_set = true;
    limited_unit_cm_long_2nd_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_cm_long_2nd_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitCmLong2ndSec( Integer p_limitedUnitCmLong2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_long_2nd_sec = p_limitedUnitCmLong2ndSec;
    limited_unit_cm_long_2nd_sec_is_set = true;
    limited_unit_cm_long_2nd_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitCmShort1stSec <em>limited_unit_cm_short_1st_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitCmShort1stSec( int p_limitedUnitCmShort1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_short_1st_sec = new Integer(p_limitedUnitCmShort1stSec);
    limited_unit_cm_short_1st_sec_is_set = true;
    limited_unit_cm_short_1st_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_cm_short_1st_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitCmShort1stSec( Integer p_limitedUnitCmShort1stSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_short_1st_sec = p_limitedUnitCmShort1stSec;
    limited_unit_cm_short_1st_sec_is_set = true;
    limited_unit_cm_short_1st_sec_is_modified = true;
  }


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値を設定します。 
   *
   * @@param p_limitedUnitCmShort2ndSec <em>limited_unit_cm_short_2nd_sec</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setLimitedUnitCmShort2ndSec( int p_limitedUnitCmShort2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_short_2nd_sec = new Integer(p_limitedUnitCmShort2ndSec);
    limited_unit_cm_short_2nd_sec_is_set = true;
    limited_unit_cm_short_2nd_sec_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limited_unit_cm_short_2nd_sec</em>カラムに値を設定します。 
   */
  public final void setLimitedUnitCmShort2ndSec( Integer p_limitedUnitCmShort2ndSec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limited_unit_cm_short_2nd_sec = p_limitedUnitCmShort2ndSec;
    limited_unit_cm_short_2nd_sec_is_set = true;
    limited_unit_cm_short_2nd_sec_is_modified = true;
  }


  /** 
   * <em>cont_liquidate_term</em>カラムの値を設定します。 
   *
   * @@param p_contLiquidateTerm <em>cont_liquidate_term</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setContLiquidateTerm( int p_contLiquidateTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cont_liquidate_term = new Integer(p_contLiquidateTerm);
    cont_liquidate_term_is_set = true;
    cont_liquidate_term_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cont_liquidate_term</em>カラムに値を設定します。 
   */
  public final void setContLiquidateTerm( Integer p_contLiquidateTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cont_liquidate_term = p_contLiquidateTerm;
    cont_liquidate_term_is_set = true;
    cont_liquidate_term_is_modified = true;
  }


  /** 
   * <em>buy_interest_rate</em>カラムの値を設定します。 
   *
   * @@param p_buyInterestRate <em>buy_interest_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBuyInterestRate( double p_buyInterestRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_interest_rate = new Double(p_buyInterestRate);
    buy_interest_rate_is_set = true;
    buy_interest_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_interest_rate</em>カラムに値を設定します。 
   */
  public final void setBuyInterestRate( Double p_buyInterestRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_interest_rate = p_buyInterestRate;
    buy_interest_rate_is_set = true;
    buy_interest_rate_is_modified = true;
  }


  /** 
   * <em>sell_interest_rate</em>カラムの値を設定します。 
   *
   * @@param p_sellInterestRate <em>sell_interest_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSellInterestRate( double p_sellInterestRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_interest_rate = new Double(p_sellInterestRate);
    sell_interest_rate_is_set = true;
    sell_interest_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_interest_rate</em>カラムに値を設定します。 
   */
  public final void setSellInterestRate( Double p_sellInterestRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_interest_rate = p_sellInterestRate;
    sell_interest_rate_is_set = true;
    sell_interest_rate_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_interest_rate") ) {
                    return this.buy_interest_rate;
                }
                break;
            case 'c':
                if ( name.equals("cont_liquidate_term") ) {
                    return this.cont_liquidate_term;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("limited_unit_m_long_1st_sec") ) {
                    return this.limited_unit_m_long_1st_sec;
                }
                else if ( name.equals("limited_unit_m_long_2nd_sec") ) {
                    return this.limited_unit_m_long_2nd_sec;
                }
                else if ( name.equals("limited_unit_m_short_1st_sec") ) {
                    return this.limited_unit_m_short_1st_sec;
                }
                else if ( name.equals("limited_unit_m_short_2nd_sec") ) {
                    return this.limited_unit_m_short_2nd_sec;
                }
                else if ( name.equals("limited_unit_cm_long_1st_sec") ) {
                    return this.limited_unit_cm_long_1st_sec;
                }
                else if ( name.equals("limited_unit_cm_long_2nd_sec") ) {
                    return this.limited_unit_cm_long_2nd_sec;
                }
                else if ( name.equals("limited_unit_cm_short_1st_sec") ) {
                    return this.limited_unit_cm_short_1st_sec;
                }
                else if ( name.equals("limited_unit_cm_short_2nd_sec") ) {
                    return this.limited_unit_cm_short_2nd_sec;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                else if ( name.equals("mart_can_dealt") ) {
                    return this.mart_can_dealt;
                }
                break;
            case 'r':
                if ( name.equals("repayment_div") ) {
                    return this.repayment_div;
                }
                else if ( name.equals("repayment_num") ) {
                    return new Integer( repayment_num );
                }
                break;
            case 's':
                if ( name.equals("sonar_repayment_type") ) {
                    return this.sonar_repayment_type;
                }
                else if ( name.equals("sell_interest_rate") ) {
                    return this.sell_interest_rate;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("buy_interest_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buy_interest_rate' must be Double: '"+value+"' is not." );
                    this.buy_interest_rate = (Double) value;
                    if (this.buy_interest_rate_is_set)
                        this.buy_interest_rate_is_modified = true;
                    this.buy_interest_rate_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cont_liquidate_term") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'cont_liquidate_term' must be Integer: '"+value+"' is not." );
                    this.cont_liquidate_term = (Integer) value;
                    if (this.cont_liquidate_term_is_set)
                        this.cont_liquidate_term_is_modified = true;
                    this.cont_liquidate_term_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("limited_unit_m_long_1st_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_m_long_1st_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_m_long_1st_sec = (Integer) value;
                    if (this.limited_unit_m_long_1st_sec_is_set)
                        this.limited_unit_m_long_1st_sec_is_modified = true;
                    this.limited_unit_m_long_1st_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_m_long_2nd_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_m_long_2nd_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_m_long_2nd_sec = (Integer) value;
                    if (this.limited_unit_m_long_2nd_sec_is_set)
                        this.limited_unit_m_long_2nd_sec_is_modified = true;
                    this.limited_unit_m_long_2nd_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_m_short_1st_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_m_short_1st_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_m_short_1st_sec = (Integer) value;
                    if (this.limited_unit_m_short_1st_sec_is_set)
                        this.limited_unit_m_short_1st_sec_is_modified = true;
                    this.limited_unit_m_short_1st_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_m_short_2nd_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_m_short_2nd_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_m_short_2nd_sec = (Integer) value;
                    if (this.limited_unit_m_short_2nd_sec_is_set)
                        this.limited_unit_m_short_2nd_sec_is_modified = true;
                    this.limited_unit_m_short_2nd_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_cm_long_1st_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_cm_long_1st_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_cm_long_1st_sec = (Integer) value;
                    if (this.limited_unit_cm_long_1st_sec_is_set)
                        this.limited_unit_cm_long_1st_sec_is_modified = true;
                    this.limited_unit_cm_long_1st_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_cm_long_2nd_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_cm_long_2nd_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_cm_long_2nd_sec = (Integer) value;
                    if (this.limited_unit_cm_long_2nd_sec_is_set)
                        this.limited_unit_cm_long_2nd_sec_is_modified = true;
                    this.limited_unit_cm_long_2nd_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_cm_short_1st_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_cm_short_1st_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_cm_short_1st_sec = (Integer) value;
                    if (this.limited_unit_cm_short_1st_sec_is_set)
                        this.limited_unit_cm_short_1st_sec_is_modified = true;
                    this.limited_unit_cm_short_1st_sec_is_set = true;
                    return;
                }
                else if ( name.equals("limited_unit_cm_short_2nd_sec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'limited_unit_cm_short_2nd_sec' must be Integer: '"+value+"' is not." );
                    this.limited_unit_cm_short_2nd_sec = (Integer) value;
                    if (this.limited_unit_cm_short_2nd_sec_is_set)
                        this.limited_unit_cm_short_2nd_sec_is_modified = true;
                    this.limited_unit_cm_short_2nd_sec_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                else if ( name.equals("mart_can_dealt") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mart_can_dealt' must be String: '"+value+"' is not." );
                    this.mart_can_dealt = (String) value;
                    if (this.mart_can_dealt_is_set)
                        this.mart_can_dealt_is_modified = true;
                    this.mart_can_dealt_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("repayment_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'repayment_div' must be String: '"+value+"' is not." );
                    this.repayment_div = (String) value;
                    if (this.repayment_div_is_set)
                        this.repayment_div_is_modified = true;
                    this.repayment_div_is_set = true;
                    return;
                }
                else if ( name.equals("repayment_num") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'repayment_num' must be Integer: '"+value+"' is not." );
                    this.repayment_num = ((Integer) value).intValue();
                    if (this.repayment_num_is_set)
                        this.repayment_num_is_modified = true;
                    this.repayment_num_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_repayment_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_repayment_type' must be String: '"+value+"' is not." );
                    this.sonar_repayment_type = (String) value;
                    if (this.sonar_repayment_type_is_set)
                        this.sonar_repayment_type_is_modified = true;
                    this.sonar_repayment_type_is_set = true;
                    return;
                }
                else if ( name.equals("sell_interest_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sell_interest_rate' must be Double: '"+value+"' is not." );
                    this.sell_interest_rate = (Double) value;
                    if (this.sell_interest_rate_is_set)
                        this.sell_interest_rate_is_modified = true;
                    this.sell_interest_rate_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
