head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.49.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	EleDeliveryManagementParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ele_delivery_managementテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EleDeliveryManagementRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EleDeliveryManagementParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EleDeliveryManagementParams}が{@@link EleDeliveryManagementRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EleDeliveryManagementPK 
 * @@see EleDeliveryManagementRow 
 */
public class EleDeliveryManagementParams extends Params implements EleDeliveryManagementRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ele_delivery_management";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EleDeliveryManagementRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EleDeliveryManagementRow.TYPE;
  }


  /** 
   * <em>account_id</em>コラムの値 
   */
  public  long  account_id;

  /** 
   * <em>institution_code</em>コラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>コラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>コラムの値 
   */
  public  String  account_code;

  /** 
   * <em>ele_del_regi_flag</em>コラムの値 
   */
  public  int  ele_del_regi_flag;

  /** 
   * <em>ele_del_regi_upd_date</em>コラムの値 
   */
  public  java.sql.Timestamp  ele_del_regi_upd_date;

  /** 
   * <em>trading_report_div</em>コラムの値 
   */
  public  String  trading_report_div;

  /** 
   * <em>trading_report_reg_div</em>コラムの値 
   */
  public  String  trading_report_reg_div;

  /** 
   * <em>trading_report_div_upd_date</em>コラムの値 
   */
  public  java.sql.Timestamp  trading_report_div_upd_date;

  /** 
   * <em>position_report_div</em>コラムの値 
   */
  public  String  position_report_div;

  /** 
   * <em>position_report_reg_div</em>コラムの値 
   */
  public  String  position_report_reg_div;

  /** 
   * <em>position_report_div_upd_date</em>コラムの値 
   */
  public  java.sql.Timestamp  position_report_div_upd_date;

  /** 
   * <em>ope_report_div</em>コラムの値 
   */
  public  String  ope_report_div;

  /** 
   * <em>ope_report_reg_div</em>コラムの値 
   */
  public  String  ope_report_reg_div;

  /** 
   * <em>ope_report_div_upd_date</em>コラムの値 
   */
  public  java.sql.Timestamp  ope_report_div_upd_date;

  /** 
   * <em>ord_rul_report_div</em>コラムの値 
   */
  public  String  ord_rul_report_div;

  /** 
   * <em>ord_rul_rep_reg_div</em>コラムの値 
   */
  public  String  ord_rul_rep_reg_div;

  /** 
   * <em>ord_rul_report_div_upd_date</em>コラムの値 
   */
  public  java.sql.Timestamp  ord_rul_report_div_upd_date;

  /** 
   * <em>report_div1</em>コラムの値 
   */
  public  String  report_div1;

  /** 
   * <em>report_reg_div1</em>コラムの値 
   */
  public  String  report_reg_div1;

  /** 
   * <em>report_div_upd_date1</em>コラムの値 
   */
  public  java.sql.Timestamp  report_div_upd_date1;

  /** 
   * <em>report_div2</em>コラムの値 
   */
  public  String  report_div2;

  /** 
   * <em>report_reg_div2</em>コラムの値 
   */
  public  String  report_reg_div2;

  /** 
   * <em>report_div_upd_date2</em>コラムの値 
   */
  public  java.sql.Timestamp  report_div_upd_date2;

  /** 
   * <em>report_div3</em>コラムの値 
   */
  public  String  report_div3;

  /** 
   * <em>report_reg_div3</em>コラムの値 
   */
  public  String  report_reg_div3;

  /** 
   * <em>report_div_upd_date3</em>コラムの値 
   */
  public  java.sql.Timestamp  report_div_upd_date3;

  /** 
   * <em>report_div4</em>コラムの値 
   */
  public  String  report_div4;

  /** 
   * <em>report_reg_div4</em>コラムの値 
   */
  public  String  report_reg_div4;

  /** 
   * <em>report_div_upd_date4</em>コラムの値 
   */
  public  java.sql.Timestamp  report_div_upd_date4;

  /** 
   * <em>report_div5</em>コラムの値 
   */
  public  String  report_div5;

  /** 
   * <em>report_reg_div5</em>コラムの値 
   */
  public  String  report_reg_div5;

  /** 
   * <em>report_div_upd_date5</em>コラムの値 
   */
  public  java.sql.Timestamp  report_div_upd_date5;

  /** 
   * <em>last_updater</em>コラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean account_id_is_set;
  private boolean institution_code_is_set;
  private boolean branch_code_is_set;
  private boolean account_code_is_set;
  private boolean ele_del_regi_flag_is_set;
  private boolean created_timestamp_is_set;
  private boolean last_updated_timestamp_is_set;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "account_id=" + account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "ele_del_regi_flag=" +ele_del_regi_flag
      + "," + "ele_del_regi_upd_date=" +ele_del_regi_upd_date
      + "," + "trading_report_div=" +trading_report_div
      + "," + "trading_report_reg_div=" +trading_report_reg_div
      + "," + "trading_report_div_upd_date=" +trading_report_div_upd_date
      + "," + "position_report_div=" +position_report_div
      + "," + "position_report_reg_div=" +position_report_reg_div
      + "," + "position_report_div_upd_date=" +position_report_div_upd_date
      + "," + "ope_report_div=" +ope_report_div
      + "," + "ope_report_reg_div=" +ope_report_reg_div
      + "," + "ope_report_div_upd_date=" +ope_report_div_upd_date
      + "," + "ord_rul_report_div=" +ord_rul_report_div
      + "," + "ord_rul_rep_reg_div=" +ord_rul_rep_reg_div
      + "," + "ord_rul_report_div_upd_date=" +ord_rul_report_div_upd_date
      + "," + "report_div1=" +report_div1
      + "," + "report_reg_div1=" +report_reg_div1
      + "," + "report_div_upd_date1=" +report_div_upd_date1
      + "," + "report_div2=" +report_div2
      + "," + "report_reg_div2=" +report_reg_div2
      + "," + "report_div_upd_date2=" +report_div_upd_date2
      + "," + "report_div3=" +report_div3
      + "," + "report_reg_div3=" +report_reg_div3
      + "," + "report_div_upd_date3=" +report_div_upd_date3
      + "," + "report_div4=" +report_div4
      + "," + "report_reg_div4=" +report_reg_div4
      + "," + "report_div_upd_date4=" +report_div_upd_date4
      + "," + "report_div5=" +report_div5
      + "," + "report_reg_div5=" +report_reg_div5
      + "," + "report_div_upd_date5=" +report_div_upd_date5
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のEleDeliveryManagementParamsオブジェクトを作成します。 
   */
  public EleDeliveryManagementParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEleDeliveryManagementRowオブジェクトの値を利用してEleDeliveryManagementParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEleDeliveryManagementRowオブジェクト 
   */
  public EleDeliveryManagementParams( EleDeliveryManagementRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    ele_del_regi_flag = p_row.getEleDelRegiFlag();
    ele_del_regi_flag_is_set = p_row.getEleDelRegiFlagIsSet();
    ele_del_regi_upd_date = p_row.getEleDelRegiUpdDate();
    trading_report_div = p_row.getTradingReportDiv();
    trading_report_reg_div = p_row.getTradingReportRegDiv();
    trading_report_div_upd_date = p_row.getTradingReportDivUpdDate();
    position_report_div = p_row.getPositionReportDiv();
    position_report_reg_div = p_row.getPositionReportRegDiv();
    position_report_div_upd_date = p_row.getPositionReportDivUpdDate();
    ope_report_div = p_row.getOpeReportDiv();
    ope_report_reg_div = p_row.getOpeReportRegDiv();
    ope_report_div_upd_date = p_row.getOpeReportDivUpdDate();
    ord_rul_report_div = p_row.getOrdRulReportDiv();
    ord_rul_rep_reg_div = p_row.getOrdRulRepRegDiv();
    ord_rul_report_div_upd_date = p_row.getOrdRulReportDivUpdDate();
    report_div1 = p_row.getReportDiv1();
    report_reg_div1 = p_row.getReportRegDiv1();
    report_div_upd_date1 = p_row.getReportDivUpdDate1();
    report_div2 = p_row.getReportDiv2();
    report_reg_div2 = p_row.getReportRegDiv2();
    report_div_upd_date2 = p_row.getReportDivUpdDate2();
    report_div3 = p_row.getReportDiv3();
    report_reg_div3 = p_row.getReportRegDiv3();
    report_div_upd_date3 = p_row.getReportDivUpdDate3();
    report_div4 = p_row.getReportDiv4();
    report_reg_div4 = p_row.getReportRegDiv4();
    report_div_upd_date4 = p_row.getReportDivUpdDate4();
    report_div5 = p_row.getReportDiv5();
    report_reg_div5 = p_row.getReportRegDiv5();
    report_div_upd_date5 = p_row.getReportDivUpdDate5();
    last_updater = p_row.getLastUpdater();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      branch_code_is_set = true;
      account_code_is_set = true;
      ele_del_regi_flag_is_set = true;
      created_timestamp_is_set = true;
      last_updated_timestamp_is_set = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EleDeliveryManagementRow ) )
       return false;
    return fieldsEqual( (EleDeliveryManagementRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEleDeliveryManagementRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EleDeliveryManagementRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
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
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( ele_del_regi_flag != row.getEleDelRegiFlag() )
      return false;
    if ( ele_del_regi_upd_date == null ) {
      if ( row.getEleDelRegiUpdDate() != null )
        return false;
    } else if ( !ele_del_regi_upd_date.equals( row.getEleDelRegiUpdDate() ) ) {
        return false;
    }
    if ( trading_report_div == null ) {
      if ( row.getTradingReportDiv() != null )
        return false;
    } else if ( !trading_report_div.equals( row.getTradingReportDiv() ) ) {
        return false;
    }
    if ( trading_report_reg_div == null ) {
      if ( row.getTradingReportRegDiv() != null )
        return false;
    } else if ( !trading_report_reg_div.equals( row.getTradingReportRegDiv() ) ) {
        return false;
    }
    if ( trading_report_div_upd_date == null ) {
      if ( row.getTradingReportDivUpdDate() != null )
        return false;
    } else if ( !trading_report_div_upd_date.equals( row.getTradingReportDivUpdDate() ) ) {
        return false;
    }
    if ( position_report_div == null ) {
      if ( row.getPositionReportDiv() != null )
        return false;
    } else if ( !position_report_div.equals( row.getPositionReportDiv() ) ) {
        return false;
    }
    if ( position_report_reg_div == null ) {
      if ( row.getPositionReportRegDiv() != null )
        return false;
    } else if ( !position_report_reg_div.equals( row.getPositionReportRegDiv() ) ) {
        return false;
    }
    if ( position_report_div_upd_date == null ) {
      if ( row.getPositionReportDivUpdDate() != null )
        return false;
    } else if ( !position_report_div_upd_date.equals( row.getPositionReportDivUpdDate() ) ) {
        return false;
    }
    if ( ope_report_div == null ) {
      if ( row.getOpeReportDiv() != null )
        return false;
    } else if ( !ope_report_div.equals( row.getOpeReportDiv() ) ) {
        return false;
    }
    if ( ope_report_reg_div == null ) {
      if ( row.getOpeReportRegDiv() != null )
        return false;
    } else if ( !ope_report_reg_div.equals( row.getOpeReportRegDiv() ) ) {
        return false;
    }
    if ( ope_report_div_upd_date == null ) {
      if ( row.getOpeReportDivUpdDate() != null )
        return false;
    } else if ( !ope_report_div_upd_date.equals( row.getOpeReportDivUpdDate() ) ) {
        return false;
    }
    if ( ord_rul_report_div == null ) {
      if ( row.getOrdRulReportDiv() != null )
        return false;
    } else if ( !ord_rul_report_div.equals( row.getOrdRulReportDiv() ) ) {
        return false;
    }
    if ( ord_rul_rep_reg_div == null ) {
      if ( row.getOrdRulRepRegDiv() != null )
        return false;
    } else if ( !ord_rul_rep_reg_div.equals( row.getOrdRulRepRegDiv() ) ) {
        return false;
    }
    if ( ord_rul_report_div_upd_date == null ) {
      if ( row.getOrdRulReportDivUpdDate() != null )
        return false;
    } else if ( !ord_rul_report_div_upd_date.equals( row.getOrdRulReportDivUpdDate() ) ) {
        return false;
    }
    if ( report_div1 == null ) {
      if ( row.getReportDiv1() != null )
        return false;
    } else if ( !report_div1.equals( row.getReportDiv1() ) ) {
        return false;
    }
    if ( report_reg_div1 == null ) {
      if ( row.getReportRegDiv1() != null )
        return false;
    } else if ( !report_reg_div1.equals( row.getReportRegDiv1() ) ) {
        return false;
    }
    if ( report_div_upd_date1 == null ) {
      if ( row.getReportDivUpdDate1() != null )
        return false;
    } else if ( !report_div_upd_date1.equals( row.getReportDivUpdDate1() ) ) {
        return false;
    }
    if ( report_div2 == null ) {
      if ( row.getReportDiv2() != null )
        return false;
    } else if ( !report_div2.equals( row.getReportDiv2() ) ) {
        return false;
    }
    if ( report_reg_div2 == null ) {
      if ( row.getReportRegDiv2() != null )
        return false;
    } else if ( !report_reg_div2.equals( row.getReportRegDiv2() ) ) {
        return false;
    }
    if ( report_div_upd_date2 == null ) {
      if ( row.getReportDivUpdDate2() != null )
        return false;
    } else if ( !report_div_upd_date2.equals( row.getReportDivUpdDate2() ) ) {
        return false;
    }
    if ( report_div3 == null ) {
      if ( row.getReportDiv3() != null )
        return false;
    } else if ( !report_div3.equals( row.getReportDiv3() ) ) {
        return false;
    }
    if ( report_reg_div3 == null ) {
      if ( row.getReportRegDiv3() != null )
        return false;
    } else if ( !report_reg_div3.equals( row.getReportRegDiv3() ) ) {
        return false;
    }
    if ( report_div_upd_date3 == null ) {
      if ( row.getReportDivUpdDate3() != null )
        return false;
    } else if ( !report_div_upd_date3.equals( row.getReportDivUpdDate3() ) ) {
        return false;
    }
    if ( report_div4 == null ) {
      if ( row.getReportDiv4() != null )
        return false;
    } else if ( !report_div4.equals( row.getReportDiv4() ) ) {
        return false;
    }
    if ( report_reg_div4 == null ) {
      if ( row.getReportRegDiv4() != null )
        return false;
    } else if ( !report_reg_div4.equals( row.getReportRegDiv4() ) ) {
        return false;
    }
    if ( report_div_upd_date4 == null ) {
      if ( row.getReportDivUpdDate4() != null )
        return false;
    } else if ( !report_div_upd_date4.equals( row.getReportDivUpdDate4() ) ) {
        return false;
    }
    if ( report_div5 == null ) {
      if ( row.getReportDiv5() != null )
        return false;
    } else if ( !report_div5.equals( row.getReportDiv5() ) ) {
        return false;
    }
    if ( report_reg_div5 == null ) {
      if ( row.getReportRegDiv5() != null )
        return false;
    } else if ( !report_reg_div5.equals( row.getReportRegDiv5() ) ) {
        return false;
    }
    if ( report_div_upd_date5 == null ) {
      if ( row.getReportDivUpdDate5() != null )
        return false;
    } else if ( !report_div_upd_date5.equals( row.getReportDivUpdDate5() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
      return  ((int) account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) ele_del_regi_flag)
        + (ele_del_regi_upd_date!=null? ele_del_regi_upd_date.hashCode(): 0) 
        + (trading_report_div!=null? trading_report_div.hashCode(): 0) 
        + (trading_report_reg_div!=null? trading_report_reg_div.hashCode(): 0) 
        + (trading_report_div_upd_date!=null? trading_report_div_upd_date.hashCode(): 0) 
        + (position_report_div!=null? position_report_div.hashCode(): 0) 
        + (position_report_reg_div!=null? position_report_reg_div.hashCode(): 0) 
        + (position_report_div_upd_date!=null? position_report_div_upd_date.hashCode(): 0) 
        + (ope_report_div!=null? ope_report_div.hashCode(): 0) 
        + (ope_report_reg_div!=null? ope_report_reg_div.hashCode(): 0) 
        + (ope_report_div_upd_date!=null? ope_report_div_upd_date.hashCode(): 0) 
        + (ord_rul_report_div!=null? ord_rul_report_div.hashCode(): 0) 
        + (ord_rul_rep_reg_div!=null? ord_rul_rep_reg_div.hashCode(): 0) 
        + (ord_rul_report_div_upd_date!=null? ord_rul_report_div_upd_date.hashCode(): 0) 
        + (report_div1!=null? report_div1.hashCode(): 0) 
        + (report_reg_div1!=null? report_reg_div1.hashCode(): 0) 
        + (report_div_upd_date1!=null? report_div_upd_date1.hashCode(): 0) 
        + (report_div2!=null? report_div2.hashCode(): 0) 
        + (report_reg_div2!=null? report_reg_div2.hashCode(): 0) 
        + (report_div_upd_date2!=null? report_div_upd_date2.hashCode(): 0) 
        + (report_div3!=null? report_div3.hashCode(): 0) 
        + (report_reg_div3!=null? report_reg_div3.hashCode(): 0) 
        + (report_div_upd_date3!=null? report_div_upd_date3.hashCode(): 0) 
        + (report_div4!=null? report_div4.hashCode(): 0) 
        + (report_reg_div4!=null? report_reg_div4.hashCode(): 0) 
        + (report_div_upd_date4!=null? report_div_upd_date4.hashCode(): 0) 
        + (report_div5!=null? report_div5.hashCode(): 0) 
        + (report_reg_div5!=null? report_reg_div5.hashCode(): 0) 
        + (report_div_upd_date5!=null? report_div_upd_date5.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !ele_del_regi_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ele_del_regi_flag' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("ele_del_regi_flag",new Integer(ele_del_regi_flag));
		if ( ele_del_regi_upd_date != null )
			map.put("ele_del_regi_upd_date",ele_del_regi_upd_date);
		if ( trading_report_div != null )
			map.put("trading_report_div",trading_report_div);
		if ( trading_report_reg_div != null )
			map.put("trading_report_reg_div",trading_report_reg_div);
		if ( trading_report_div_upd_date != null )
			map.put("trading_report_div_upd_date",trading_report_div_upd_date);
		if ( position_report_div != null )
			map.put("position_report_div",position_report_div);
		if ( position_report_reg_div != null )
			map.put("position_report_reg_div",position_report_reg_div);
		if ( position_report_div_upd_date != null )
			map.put("position_report_div_upd_date",position_report_div_upd_date);
		if ( ope_report_div != null )
			map.put("ope_report_div",ope_report_div);
		if ( ope_report_reg_div != null )
			map.put("ope_report_reg_div",ope_report_reg_div);
		if ( ope_report_div_upd_date != null )
			map.put("ope_report_div_upd_date",ope_report_div_upd_date);
		if ( ord_rul_report_div != null )
			map.put("ord_rul_report_div",ord_rul_report_div);
		if ( ord_rul_rep_reg_div != null )
			map.put("ord_rul_rep_reg_div",ord_rul_rep_reg_div);
		if ( ord_rul_report_div_upd_date != null )
			map.put("ord_rul_report_div_upd_date",ord_rul_report_div_upd_date);
		if ( report_div1 != null )
			map.put("report_div1",report_div1);
		if ( report_reg_div1 != null )
			map.put("report_reg_div1",report_reg_div1);
		if ( report_div_upd_date1 != null )
			map.put("report_div_upd_date1",report_div_upd_date1);
		if ( report_div2 != null )
			map.put("report_div2",report_div2);
		if ( report_reg_div2 != null )
			map.put("report_reg_div2",report_reg_div2);
		if ( report_div_upd_date2 != null )
			map.put("report_div_upd_date2",report_div_upd_date2);
		if ( report_div3 != null )
			map.put("report_div3",report_div3);
		if ( report_reg_div3 != null )
			map.put("report_reg_div3",report_reg_div3);
		if ( report_div_upd_date3 != null )
			map.put("report_div_upd_date3",report_div_upd_date3);
		if ( report_div4 != null )
			map.put("report_div4",report_div4);
		if ( report_reg_div4 != null )
			map.put("report_reg_div4",report_reg_div4);
		if ( report_div_upd_date4 != null )
			map.put("report_div_upd_date4",report_div_upd_date4);
		if ( report_div5 != null )
			map.put("report_div5",report_div5);
		if ( report_reg_div5 != null )
			map.put("report_reg_div5",report_reg_div5);
		if ( report_div_upd_date5 != null )
			map.put("report_div_upd_date5",report_div_upd_date5);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_set )
			map.put("institution_code",institution_code);
		if ( branch_code_is_set )
			map.put("branch_code",branch_code);
		if ( account_code_is_set )
			map.put("account_code",account_code);
		if ( ele_del_regi_flag_is_set )
			map.put("ele_del_regi_flag",new Integer(ele_del_regi_flag));
		map.put("ele_del_regi_upd_date",ele_del_regi_upd_date);
		map.put("trading_report_div",trading_report_div);
		map.put("trading_report_reg_div",trading_report_reg_div);
		map.put("trading_report_div_upd_date",trading_report_div_upd_date);
		map.put("position_report_div",position_report_div);
		map.put("position_report_reg_div",position_report_reg_div);
		map.put("position_report_div_upd_date",position_report_div_upd_date);
		map.put("ope_report_div",ope_report_div);
		map.put("ope_report_reg_div",ope_report_reg_div);
		map.put("ope_report_div_upd_date",ope_report_div_upd_date);
		map.put("ord_rul_report_div",ord_rul_report_div);
		map.put("ord_rul_rep_reg_div",ord_rul_rep_reg_div);
		map.put("ord_rul_report_div_upd_date",ord_rul_report_div_upd_date);
		map.put("report_div1",report_div1);
		map.put("report_reg_div1",report_reg_div1);
		map.put("report_div_upd_date1",report_div_upd_date1);
		map.put("report_div2",report_div2);
		map.put("report_reg_div2",report_reg_div2);
		map.put("report_div_upd_date2",report_div_upd_date2);
		map.put("report_div3",report_div3);
		map.put("report_reg_div3",report_reg_div3);
		map.put("report_div_upd_date3",report_div_upd_date3);
		map.put("report_div4",report_div4);
		map.put("report_reg_div4",report_reg_div4);
		map.put("report_div_upd_date4",report_div_upd_date4);
		map.put("report_div5",report_div5);
		map.put("report_reg_div5",report_reg_div5);
		map.put("report_div_upd_date5",report_div_upd_date5);
		map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * <em>account_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>institution_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>branch_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>account_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>ele_del_regi_flag</em>コラムの値を取得します。
   * @@return intの値 
   */
  public final int getEleDelRegiFlag()
  {
    return ele_del_regi_flag;
  }


  /** 
   * <em>ele_del_regi_flag</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEleDelRegiFlagIsSet() {
    return ele_del_regi_flag_is_set;
  }


  /** 
   * <em>ele_del_regi_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEleDelRegiUpdDate()
  {
    return ele_del_regi_upd_date;
  }


  /** 
   * <em>trading_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingReportDiv()
  {
    return trading_report_div;
  }


  /** 
   * <em>trading_report_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingReportRegDiv()
  {
    return trading_report_reg_div;
  }


  /** 
   * <em>trading_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTradingReportDivUpdDate()
  {
    return trading_report_div_upd_date;
  }


  /** 
   * <em>position_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPositionReportDiv()
  {
    return position_report_div;
  }


  /** 
   * <em>position_report_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPositionReportRegDiv()
  {
    return position_report_reg_div;
  }


  /** 
   * <em>position_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPositionReportDivUpdDate()
  {
    return position_report_div_upd_date;
  }


  /** 
   * <em>ope_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOpeReportDiv()
  {
    return ope_report_div;
  }


  /** 
   * <em>ope_report_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOpeReportRegDiv()
  {
    return ope_report_reg_div;
  }


  /** 
   * <em>ope_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOpeReportDivUpdDate()
  {
    return ope_report_div_upd_date;
  }


  /** 
   * <em>ord_rul_report_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrdRulReportDiv()
  {
    return ord_rul_report_div;
  }


  /** 
   * <em>ord_rul_rep_reg_div</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrdRulRepRegDiv()
  {
    return ord_rul_rep_reg_div;
  }


  /** 
   * <em>ord_rul_report_div_upd_date</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrdRulReportDivUpdDate()
  {
    return ord_rul_report_div_upd_date;
  }


  /** 
   * <em>report_div1</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportDiv1()
  {
    return report_div1;
  }


  /** 
   * <em>report_reg_div1</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportRegDiv1()
  {
    return report_reg_div1;
  }


  /** 
   * <em>report_div_upd_date1</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getReportDivUpdDate1()
  {
    return report_div_upd_date1;
  }


  /** 
   * <em>report_div2</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportDiv2()
  {
    return report_div2;
  }


  /** 
   * <em>report_reg_div2</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportRegDiv2()
  {
    return report_reg_div2;
  }


  /** 
   * <em>report_div_upd_date2</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getReportDivUpdDate2()
  {
    return report_div_upd_date2;
  }


  /** 
   * <em>report_div3</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportDiv3()
  {
    return report_div3;
  }


  /** 
   * <em>report_reg_div3</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportRegDiv3()
  {
    return report_reg_div3;
  }


  /** 
   * <em>report_div_upd_date3</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getReportDivUpdDate3()
  {
    return report_div_upd_date3;
  }


  /** 
   * <em>report_div4</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportDiv4()
  {
    return report_div4;
  }


  /** 
   * <em>report_reg_div4</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportRegDiv4()
  {
    return report_reg_div4;
  }


  /** 
   * <em>report_div_upd_date4</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getReportDivUpdDate4()
  {
    return report_div_upd_date4;
  }


  /** 
   * <em>report_div5</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportDiv5()
  {
    return report_div5;
  }


  /** 
   * <em>report_reg_div5</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportRegDiv5()
  {
    return report_reg_div5;
  }


  /** 
   * <em>report_div_upd_date5</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getReportDivUpdDate5()
  {
    return report_div_upd_date5;
  }


  /** 
   * <em>last_updater</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>created_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EleDeliveryManagementPK(account_id);
  }


  /** 
   * <em>account_id</em>コラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
  }


  /** 
   * <em>institution_code</em>コラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>コラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
  }


  /** 
   * <em>branch_code</em>コラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>コラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
  }


  /** 
   * <em>account_code</em>コラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>コラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
  }


  /** 
   * <em>ele_del_regi_flag</em>コラムの値を設定します。 
   *
   * @@param p_eleDelRegiFlag <em>ele_del_regi_flag</em>コラムの値をあらわす1桁以下のint値 
   */
  public final void setEleDelRegiFlag( int p_eleDelRegiFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ele_del_regi_flag = p_eleDelRegiFlag;
    ele_del_regi_flag_is_set = true;
  }


  /** 
   * <em>ele_del_regi_upd_date</em>コラムの値を設定します。 
   *
   * @@param p_eleDelRegiUpdDate <em>ele_del_regi_upd_date</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEleDelRegiUpdDate( java.sql.Timestamp p_eleDelRegiUpdDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ele_del_regi_upd_date = p_eleDelRegiUpdDate;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ele_del_regi_upd_date</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEleDelRegiUpdDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ele_del_regi_upd_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>trading_report_div</em>コラムの値を設定します。 
   *
   * @@param p_tradingReportDiv <em>trading_report_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setTradingReportDiv( String p_tradingReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_report_div = p_tradingReportDiv;
  }


  /** 
   * <em>trading_report_reg_div</em>コラムの値を設定します。 
   *
   * @@param p_tradingReportRegDiv <em>trading_report_reg_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setTradingReportRegDiv( String p_tradingReportRegDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_report_reg_div = p_tradingReportRegDiv;
  }


  /** 
   * <em>trading_report_div_upd_date</em>コラムの値を設定します。 
   *
   * @@param p_tradingReportDivUpdDate <em>trading_report_div_upd_date</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTradingReportDivUpdDate( java.sql.Timestamp p_tradingReportDivUpdDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_report_div_upd_date = p_tradingReportDivUpdDate;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trading_report_div_upd_date</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTradingReportDivUpdDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trading_report_div_upd_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>position_report_div</em>コラムの値を設定します。 
   *
   * @@param p_positionReportDiv <em>position_report_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setPositionReportDiv( String p_positionReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    position_report_div = p_positionReportDiv;
  }


  /** 
   * <em>position_report_reg_div</em>コラムの値を設定します。 
   *
   * @@param p_positionReportRegDiv <em>position_report_reg_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setPositionReportRegDiv( String p_positionReportRegDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    position_report_reg_div = p_positionReportRegDiv;
  }


  /** 
   * <em>position_report_div_upd_date</em>コラムの値を設定します。 
   *
   * @@param p_positionReportDivUpdDate <em>position_report_div_upd_date</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPositionReportDivUpdDate( java.sql.Timestamp p_positionReportDivUpdDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    position_report_div_upd_date = p_positionReportDivUpdDate;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>position_report_div_upd_date</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPositionReportDivUpdDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    position_report_div_upd_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>ope_report_div</em>コラムの値を設定します。 
   *
   * @@param p_opeReportDiv <em>ope_report_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setOpeReportDiv( String p_opeReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ope_report_div = p_opeReportDiv;
  }


  /** 
   * <em>ope_report_reg_div</em>コラムの値を設定します。 
   *
   * @@param p_opeReportRegDiv <em>ope_report_reg_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setOpeReportRegDiv( String p_opeReportRegDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ope_report_reg_div = p_opeReportRegDiv;
  }


  /** 
   * <em>ope_report_div_upd_date</em>コラムの値を設定します。 
   *
   * @@param p_opeReportDivUpdDate <em>ope_report_div_upd_date</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOpeReportDivUpdDate( java.sql.Timestamp p_opeReportDivUpdDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ope_report_div_upd_date = p_opeReportDivUpdDate;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ope_report_div_upd_date</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOpeReportDivUpdDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ope_report_div_upd_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>ord_rul_report_div</em>コラムの値を設定します。 
   *
   * @@param p_ordRulReportDiv <em>ord_rul_report_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setOrdRulReportDiv( String p_ordRulReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_rul_report_div = p_ordRulReportDiv;
  }


  /** 
   * <em>ord_rul_rep_reg_div</em>コラムの値を設定します。 
   *
   * @@param p_ordRulRepRegDiv <em>ord_rul_rep_reg_div</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setOrdRulRepRegDiv( String p_ordRulRepRegDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_rul_rep_reg_div = p_ordRulRepRegDiv;
  }


  /** 
   * <em>ord_rul_report_div_upd_date</em>コラムの値を設定します。 
   *
   * @@param p_ordRulReportDivUpdDate <em>ord_rul_report_div_upd_date</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrdRulReportDivUpdDate( java.sql.Timestamp p_ordRulReportDivUpdDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_rul_report_div_upd_date = p_ordRulReportDivUpdDate;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ord_rul_report_div_upd_date</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrdRulReportDivUpdDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ord_rul_report_div_upd_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>report_div1</em>コラムの値を設定します。 
   *
   * @@param p_reportDiv1 <em>report_div1</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportDiv1( String p_reportDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div1 = p_reportDiv1;
  }


  /** 
   * <em>report_reg_div1</em>コラムの値を設定します。 
   *
   * @@param p_reportRegDiv1 <em>report_reg_div1</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportRegDiv1( String p_reportRegDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_reg_div1 = p_reportRegDiv1;
  }


  /** 
   * <em>report_div_upd_date1</em>コラムの値を設定します。 
   *
   * @@param p_reportDivUpdDate1 <em>report_div_upd_date1</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setReportDivUpdDate1( java.sql.Timestamp p_reportDivUpdDate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date1 = p_reportDivUpdDate1;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>report_div_upd_date1</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setReportDivUpdDate1( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date1 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>report_div2</em>コラムの値を設定します。 
   *
   * @@param p_reportDiv2 <em>report_div2</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportDiv2( String p_reportDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div2 = p_reportDiv2;
  }


  /** 
   * <em>report_reg_div2</em>コラムの値を設定します。 
   *
   * @@param p_reportRegDiv2 <em>report_reg_div2</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportRegDiv2( String p_reportRegDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_reg_div2 = p_reportRegDiv2;
  }


  /** 
   * <em>report_div_upd_date2</em>コラムの値を設定します。 
   *
   * @@param p_reportDivUpdDate2 <em>report_div_upd_date2</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setReportDivUpdDate2( java.sql.Timestamp p_reportDivUpdDate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date2 = p_reportDivUpdDate2;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>report_div_upd_date2</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setReportDivUpdDate2( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date2 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>report_div3</em>コラムの値を設定します。 
   *
   * @@param p_reportDiv3 <em>report_div3</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportDiv3( String p_reportDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div3 = p_reportDiv3;
  }


  /** 
   * <em>report_reg_div3</em>コラムの値を設定します。 
   *
   * @@param p_reportRegDiv3 <em>report_reg_div3</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportRegDiv3( String p_reportRegDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_reg_div3 = p_reportRegDiv3;
  }


  /** 
   * <em>report_div_upd_date3</em>コラムの値を設定します。 
   *
   * @@param p_reportDivUpdDate3 <em>report_div_upd_date3</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setReportDivUpdDate3( java.sql.Timestamp p_reportDivUpdDate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date3 = p_reportDivUpdDate3;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>report_div_upd_date3</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setReportDivUpdDate3( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date3 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>report_div4</em>コラムの値を設定します。 
   *
   * @@param p_reportDiv4 <em>report_div4</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportDiv4( String p_reportDiv4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div4 = p_reportDiv4;
  }


  /** 
   * <em>report_reg_div4</em>コラムの値を設定します。 
   *
   * @@param p_reportRegDiv4 <em>report_reg_div4</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportRegDiv4( String p_reportRegDiv4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_reg_div4 = p_reportRegDiv4;
  }


  /** 
   * <em>report_div_upd_date4</em>コラムの値を設定します。 
   *
   * @@param p_reportDivUpdDate4 <em>report_div_upd_date4</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setReportDivUpdDate4( java.sql.Timestamp p_reportDivUpdDate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date4 = p_reportDivUpdDate4;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>report_div_upd_date4</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setReportDivUpdDate4( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date4 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>report_div5</em>コラムの値を設定します。 
   *
   * @@param p_reportDiv5 <em>report_div5</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportDiv5( String p_reportDiv5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div5 = p_reportDiv5;
  }


  /** 
   * <em>report_reg_div5</em>コラムの値を設定します。 
   *
   * @@param p_reportRegDiv5 <em>report_reg_div5</em>コラムの値をあらわす1桁以下のString値 
   */
  public final void setReportRegDiv5( String p_reportRegDiv5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_reg_div5 = p_reportRegDiv5;
  }


  /** 
   * <em>report_div_upd_date5</em>コラムの値を設定します。 
   *
   * @@param p_reportDivUpdDate5 <em>report_div_upd_date5</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setReportDivUpdDate5( java.sql.Timestamp p_reportDivUpdDate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date5 = p_reportDivUpdDate5;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>report_div_upd_date5</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setReportDivUpdDate5( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    report_div_upd_date5 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>last_updater</em>コラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
  }


  /** 
   * <em>created_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("ele_del_regi_flag") ) {
                    return new Integer( ele_del_regi_flag );
                }
                else if ( name.equals("ele_del_regi_upd_date") ) {
                    return this.ele_del_regi_upd_date;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("ope_report_div") ) {
                    return this.ope_report_div;
                }
                else if ( name.equals("ope_report_reg_div") ) {
                    return this.ope_report_reg_div;
                }
                else if ( name.equals("ope_report_div_upd_date") ) {
                    return this.ope_report_div_upd_date;
                }
                else if ( name.equals("ord_rul_report_div") ) {
                    return this.ord_rul_report_div;
                }
                else if ( name.equals("ord_rul_rep_reg_div") ) {
                    return this.ord_rul_rep_reg_div;
                }
                else if ( name.equals("ord_rul_report_div_upd_date") ) {
                    return this.ord_rul_report_div_upd_date;
                }
                break;
            case 'p':
                if ( name.equals("position_report_div") ) {
                    return this.position_report_div;
                }
                else if ( name.equals("position_report_reg_div") ) {
                    return this.position_report_reg_div;
                }
                else if ( name.equals("position_report_div_upd_date") ) {
                    return this.position_report_div_upd_date;
                }
                break;
            case 'r':
                if ( name.equals("report_div1") ) {
                    return this.report_div1;
                }
                else if ( name.equals("report_reg_div1") ) {
                    return this.report_reg_div1;
                }
                else if ( name.equals("report_div_upd_date1") ) {
                    return this.report_div_upd_date1;
                }
                else if ( name.equals("report_div2") ) {
                    return this.report_div2;
                }
                else if ( name.equals("report_reg_div2") ) {
                    return this.report_reg_div2;
                }
                else if ( name.equals("report_div_upd_date2") ) {
                    return this.report_div_upd_date2;
                }
                else if ( name.equals("report_div3") ) {
                    return this.report_div3;
                }
                else if ( name.equals("report_reg_div3") ) {
                    return this.report_reg_div3;
                }
                else if ( name.equals("report_div_upd_date3") ) {
                    return this.report_div_upd_date3;
                }
                else if ( name.equals("report_div4") ) {
                    return this.report_div4;
                }
                else if ( name.equals("report_reg_div4") ) {
                    return this.report_reg_div4;
                }
                else if ( name.equals("report_div_upd_date4") ) {
                    return this.report_div_upd_date4;
                }
                else if ( name.equals("report_div5") ) {
                    return this.report_div5;
                }
                else if ( name.equals("report_reg_div5") ) {
                    return this.report_reg_div5;
                }
                else if ( name.equals("report_div_upd_date5") ) {
                    return this.report_div_upd_date5;
                }
                break;
            case 't':
                if ( name.equals("trading_report_div") ) {
                    return this.trading_report_div;
                }
                else if ( name.equals("trading_report_reg_div") ) {
                    return this.trading_report_reg_div;
                }
                else if ( name.equals("trading_report_div_upd_date") ) {
                    return this.trading_report_div_upd_date;
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
            case 'a':
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    this.account_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("ele_del_regi_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ele_del_regi_flag' must be Integer: '"+value+"' is not." );
                    this.ele_del_regi_flag = ((Integer) value).intValue();
                    this.ele_del_regi_flag_is_set = true;
                    return;
                }
                else if ( name.equals("ele_del_regi_upd_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ele_del_regi_upd_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ele_del_regi_upd_date = (java.sql.Timestamp) value;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("ope_report_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ope_report_div' must be String: '"+value+"' is not." );
                    this.ope_report_div = (String) value;
                    return;
                }
                else if ( name.equals("ope_report_reg_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ope_report_reg_div' must be String: '"+value+"' is not." );
                    this.ope_report_reg_div = (String) value;
                    return;
                }
                else if ( name.equals("ope_report_div_upd_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ope_report_div_upd_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ope_report_div_upd_date = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("ord_rul_report_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ord_rul_report_div' must be String: '"+value+"' is not." );
                    this.ord_rul_report_div = (String) value;
                    return;
                }
                else if ( name.equals("ord_rul_rep_reg_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ord_rul_rep_reg_div' must be String: '"+value+"' is not." );
                    this.ord_rul_rep_reg_div = (String) value;
                    return;
                }
                else if ( name.equals("ord_rul_report_div_upd_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ord_rul_report_div_upd_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ord_rul_report_div_upd_date = (java.sql.Timestamp) value;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("position_report_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'position_report_div' must be String: '"+value+"' is not." );
                    this.position_report_div = (String) value;
                    return;
                }
                else if ( name.equals("position_report_reg_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'position_report_reg_div' must be String: '"+value+"' is not." );
                    this.position_report_reg_div = (String) value;
                    return;
                }
                else if ( name.equals("position_report_div_upd_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'position_report_div_upd_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.position_report_div_upd_date = (java.sql.Timestamp) value;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("report_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_div1' must be String: '"+value+"' is not." );
                    this.report_div1 = (String) value;
                    return;
                }
                else if ( name.equals("report_reg_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_reg_div1' must be String: '"+value+"' is not." );
                    this.report_reg_div1 = (String) value;
                    return;
                }
                else if ( name.equals("report_div_upd_date1") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'report_div_upd_date1' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.report_div_upd_date1 = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("report_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_div2' must be String: '"+value+"' is not." );
                    this.report_div2 = (String) value;
                    return;
                }
                else if ( name.equals("report_reg_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_reg_div2' must be String: '"+value+"' is not." );
                    this.report_reg_div2 = (String) value;
                    return;
                }
                else if ( name.equals("report_div_upd_date2") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'report_div_upd_date2' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.report_div_upd_date2 = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("report_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_div3' must be String: '"+value+"' is not." );
                    this.report_div3 = (String) value;
                    return;
                }
                else if ( name.equals("report_reg_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_reg_div3' must be String: '"+value+"' is not." );
                    this.report_reg_div3 = (String) value;
                    return;
                }
                else if ( name.equals("report_div_upd_date3") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'report_div_upd_date3' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.report_div_upd_date3 = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("report_div4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_div4' must be String: '"+value+"' is not." );
                    this.report_div4 = (String) value;
                    return;
                }
                else if ( name.equals("report_reg_div4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_reg_div4' must be String: '"+value+"' is not." );
                    this.report_reg_div4 = (String) value;
                    return;
                }
                else if ( name.equals("report_div_upd_date4") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'report_div_upd_date4' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.report_div_upd_date4 = (java.sql.Timestamp) value;
                    return;
                }
                else if ( name.equals("report_div5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_div5' must be String: '"+value+"' is not." );
                    this.report_div5 = (String) value;
                    return;
                }
                else if ( name.equals("report_reg_div5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_reg_div5' must be String: '"+value+"' is not." );
                    this.report_reg_div5 = (String) value;
                    return;
                }
                else if ( name.equals("report_div_upd_date5") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'report_div_upd_date5' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.report_div_upd_date5 = (java.sql.Timestamp) value;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trading_report_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_report_div' must be String: '"+value+"' is not." );
                    this.trading_report_div = (String) value;
                    return;
                }
                else if ( name.equals("trading_report_reg_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_report_reg_div' must be String: '"+value+"' is not." );
                    this.trading_report_reg_div = (String) value;
                    return;
                }
                else if ( name.equals("trading_report_div_upd_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trading_report_div_upd_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trading_report_div_upd_date = (java.sql.Timestamp) value;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
