head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DirectDebitParams.java;


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
 * direct_debitテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link DirectDebitRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link DirectDebitParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link DirectDebitParams}が{@@link DirectDebitRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DirectDebitPK 
 * @@see DirectDebitRow 
 */
public class DirectDebitParams extends Params implements DirectDebitRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "direct_debit";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = DirectDebitRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return DirectDebitRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>sonar_last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  sonar_last_updated_timestamp;

  /** 
   * <em>sonar_created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  sonar_created_timestamp;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>designate_div</em>カラムの値 
   */
  public  String  designate_div;

  /** 
   * <em>comodity</em>カラムの値 
   */
  public  String  comodity;

  /** 
   * <em>fund_code</em>カラムの値 
   */
  public  String  fund_code;

  /** 
   * <em>transfer_div</em>カラムの値 
   */
  public  String  transfer_div;

  /** 
   * <em>fin_institution_code</em>カラムの値 
   */
  public  String  fin_institution_code;

  /** 
   * <em>fin_institution_name</em>カラムの値 
   */
  public  String  fin_institution_name;

  /** 
   * <em>fin_branch_code</em>カラムの値 
   */
  public  String  fin_branch_code;

  /** 
   * <em>fin_branch_name</em>カラムの値 
   */
  public  String  fin_branch_name;

  /** 
   * <em>fin_save_div</em>カラムの値 
   */
  public  String  fin_save_div;

  /** 
   * <em>fin_account_no</em>カラムの値 
   */
  public  String  fin_account_no;

  /** 
   * <em>fin_account_name</em>カラムの値 
   */
  public  String  fin_account_name;

  /** 
   * <em>trans_commission</em>カラムの値 
   */
  public  String  trans_commission;

  /** 
   * <em>trans_deal_div</em>カラムの値 
   */
  public  String  trans_deal_div;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

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
  private boolean sonar_last_updated_timestamp_is_set = false;
  private boolean sonar_last_updated_timestamp_is_modified = false;
  private boolean sonar_created_timestamp_is_set = false;
  private boolean sonar_created_timestamp_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean designate_div_is_set = false;
  private boolean designate_div_is_modified = false;
  private boolean comodity_is_set = false;
  private boolean comodity_is_modified = false;
  private boolean fund_code_is_set = false;
  private boolean fund_code_is_modified = false;
  private boolean transfer_div_is_set = false;
  private boolean transfer_div_is_modified = false;
  private boolean fin_institution_code_is_set = false;
  private boolean fin_institution_code_is_modified = false;
  private boolean fin_institution_name_is_set = false;
  private boolean fin_institution_name_is_modified = false;
  private boolean fin_branch_code_is_set = false;
  private boolean fin_branch_code_is_modified = false;
  private boolean fin_branch_name_is_set = false;
  private boolean fin_branch_name_is_modified = false;
  private boolean fin_save_div_is_set = false;
  private boolean fin_save_div_is_modified = false;
  private boolean fin_account_no_is_set = false;
  private boolean fin_account_no_is_modified = false;
  private boolean fin_account_name_is_set = false;
  private boolean fin_account_name_is_modified = false;
  private boolean trans_commission_is_set = false;
  private boolean trans_commission_is_modified = false;
  private boolean trans_deal_div_is_set = false;
  private boolean trans_deal_div_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" +institution_code
      + "," + "sonar_last_updated_timestamp=" +sonar_last_updated_timestamp
      + "," + "sonar_created_timestamp=" +sonar_created_timestamp
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "designate_div=" +designate_div
      + "," + "comodity=" +comodity
      + "," + "fund_code=" +fund_code
      + "," + "transfer_div=" +transfer_div
      + "," + "fin_institution_code=" +fin_institution_code
      + "," + "fin_institution_name=" +fin_institution_name
      + "," + "fin_branch_code=" +fin_branch_code
      + "," + "fin_branch_name=" +fin_branch_name
      + "," + "fin_save_div=" +fin_save_div
      + "," + "fin_account_no=" +fin_account_no
      + "," + "fin_account_name=" +fin_account_name
      + "," + "trans_commission=" +trans_commission
      + "," + "trans_deal_div=" +trans_deal_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のDirectDebitParamsオブジェクトを作成します。 
   */
  public DirectDebitParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のDirectDebitRowオブジェクトの値を利用してDirectDebitParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するDirectDebitRowオブジェクト 
   */
  public DirectDebitParams( DirectDebitRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    sonar_last_updated_timestamp = p_row.getSonarLastUpdatedTimestamp();
    sonar_last_updated_timestamp_is_set = p_row.getSonarLastUpdatedTimestampIsSet();
    sonar_last_updated_timestamp_is_modified = p_row.getSonarLastUpdatedTimestampIsModified();
    sonar_created_timestamp = p_row.getSonarCreatedTimestamp();
    sonar_created_timestamp_is_set = p_row.getSonarCreatedTimestampIsSet();
    sonar_created_timestamp_is_modified = p_row.getSonarCreatedTimestampIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    designate_div = p_row.getDesignateDiv();
    designate_div_is_set = p_row.getDesignateDivIsSet();
    designate_div_is_modified = p_row.getDesignateDivIsModified();
    comodity = p_row.getComodity();
    comodity_is_set = p_row.getComodityIsSet();
    comodity_is_modified = p_row.getComodityIsModified();
    fund_code = p_row.getFundCode();
    fund_code_is_set = p_row.getFundCodeIsSet();
    fund_code_is_modified = p_row.getFundCodeIsModified();
    transfer_div = p_row.getTransferDiv();
    transfer_div_is_set = p_row.getTransferDivIsSet();
    transfer_div_is_modified = p_row.getTransferDivIsModified();
    fin_institution_code = p_row.getFinInstitutionCode();
    fin_institution_code_is_set = p_row.getFinInstitutionCodeIsSet();
    fin_institution_code_is_modified = p_row.getFinInstitutionCodeIsModified();
    fin_institution_name = p_row.getFinInstitutionName();
    fin_institution_name_is_set = p_row.getFinInstitutionNameIsSet();
    fin_institution_name_is_modified = p_row.getFinInstitutionNameIsModified();
    fin_branch_code = p_row.getFinBranchCode();
    fin_branch_code_is_set = p_row.getFinBranchCodeIsSet();
    fin_branch_code_is_modified = p_row.getFinBranchCodeIsModified();
    fin_branch_name = p_row.getFinBranchName();
    fin_branch_name_is_set = p_row.getFinBranchNameIsSet();
    fin_branch_name_is_modified = p_row.getFinBranchNameIsModified();
    fin_save_div = p_row.getFinSaveDiv();
    fin_save_div_is_set = p_row.getFinSaveDivIsSet();
    fin_save_div_is_modified = p_row.getFinSaveDivIsModified();
    fin_account_no = p_row.getFinAccountNo();
    fin_account_no_is_set = p_row.getFinAccountNoIsSet();
    fin_account_no_is_modified = p_row.getFinAccountNoIsModified();
    fin_account_name = p_row.getFinAccountName();
    fin_account_name_is_set = p_row.getFinAccountNameIsSet();
    fin_account_name_is_modified = p_row.getFinAccountNameIsModified();
    trans_commission = p_row.getTransCommission();
    trans_commission_is_set = p_row.getTransCommissionIsSet();
    trans_commission_is_modified = p_row.getTransCommissionIsModified();
    trans_deal_div = p_row.getTransDealDiv();
    trans_deal_div_is_set = p_row.getTransDealDivIsSet();
    trans_deal_div_is_modified = p_row.getTransDealDivIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      sonar_last_updated_timestamp_is_set = true;
      sonar_last_updated_timestamp_is_modified = true;
      sonar_created_timestamp_is_set = true;
      sonar_created_timestamp_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      designate_div_is_set = true;
      designate_div_is_modified = true;
      comodity_is_set = true;
      comodity_is_modified = true;
      fund_code_is_set = true;
      fund_code_is_modified = true;
      transfer_div_is_set = true;
      transfer_div_is_modified = true;
      fin_institution_code_is_set = true;
      fin_institution_code_is_modified = true;
      fin_institution_name_is_set = true;
      fin_institution_name_is_modified = true;
      fin_branch_code_is_set = true;
      fin_branch_code_is_modified = true;
      fin_branch_name_is_set = true;
      fin_branch_name_is_modified = true;
      fin_save_div_is_set = true;
      fin_save_div_is_modified = true;
      fin_account_no_is_set = true;
      fin_account_no_is_modified = true;
      fin_account_name_is_set = true;
      fin_account_name_is_modified = true;
      trans_commission_is_set = true;
      trans_commission_is_modified = true;
      trans_deal_div_is_set = true;
      trans_deal_div_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof DirectDebitRow ) )
       return false;
    return fieldsEqual( (DirectDebitRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のDirectDebitRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( DirectDebitRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( sonar_last_updated_timestamp == null ) {
      if ( row.getSonarLastUpdatedTimestamp() != null )
        return false;
    } else if ( !sonar_last_updated_timestamp.equals( row.getSonarLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( sonar_created_timestamp == null ) {
      if ( row.getSonarCreatedTimestamp() != null )
        return false;
    } else if ( !sonar_created_timestamp.equals( row.getSonarCreatedTimestamp() ) ) {
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
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( designate_div == null ) {
      if ( row.getDesignateDiv() != null )
        return false;
    } else if ( !designate_div.equals( row.getDesignateDiv() ) ) {
        return false;
    }
    if ( comodity == null ) {
      if ( row.getComodity() != null )
        return false;
    } else if ( !comodity.equals( row.getComodity() ) ) {
        return false;
    }
    if ( fund_code == null ) {
      if ( row.getFundCode() != null )
        return false;
    } else if ( !fund_code.equals( row.getFundCode() ) ) {
        return false;
    }
    if ( transfer_div == null ) {
      if ( row.getTransferDiv() != null )
        return false;
    } else if ( !transfer_div.equals( row.getTransferDiv() ) ) {
        return false;
    }
    if ( fin_institution_code == null ) {
      if ( row.getFinInstitutionCode() != null )
        return false;
    } else if ( !fin_institution_code.equals( row.getFinInstitutionCode() ) ) {
        return false;
    }
    if ( fin_institution_name == null ) {
      if ( row.getFinInstitutionName() != null )
        return false;
    } else if ( !fin_institution_name.equals( row.getFinInstitutionName() ) ) {
        return false;
    }
    if ( fin_branch_code == null ) {
      if ( row.getFinBranchCode() != null )
        return false;
    } else if ( !fin_branch_code.equals( row.getFinBranchCode() ) ) {
        return false;
    }
    if ( fin_branch_name == null ) {
      if ( row.getFinBranchName() != null )
        return false;
    } else if ( !fin_branch_name.equals( row.getFinBranchName() ) ) {
        return false;
    }
    if ( fin_save_div == null ) {
      if ( row.getFinSaveDiv() != null )
        return false;
    } else if ( !fin_save_div.equals( row.getFinSaveDiv() ) ) {
        return false;
    }
    if ( fin_account_no == null ) {
      if ( row.getFinAccountNo() != null )
        return false;
    } else if ( !fin_account_no.equals( row.getFinAccountNo() ) ) {
        return false;
    }
    if ( fin_account_name == null ) {
      if ( row.getFinAccountName() != null )
        return false;
    } else if ( !fin_account_name.equals( row.getFinAccountName() ) ) {
        return false;
    }
    if ( trans_commission == null ) {
      if ( row.getTransCommission() != null )
        return false;
    } else if ( !trans_commission.equals( row.getTransCommission() ) ) {
        return false;
    }
    if ( trans_deal_div == null ) {
      if ( row.getTransDealDiv() != null )
        return false;
    } else if ( !trans_deal_div.equals( row.getTransDealDiv() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (sonar_last_updated_timestamp!=null? sonar_last_updated_timestamp.hashCode(): 0) 
        + (sonar_created_timestamp!=null? sonar_created_timestamp.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (designate_div!=null? designate_div.hashCode(): 0) 
        + (comodity!=null? comodity.hashCode(): 0) 
        + (fund_code!=null? fund_code.hashCode(): 0) 
        + (transfer_div!=null? transfer_div.hashCode(): 0) 
        + (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_institution_name!=null? fin_institution_name.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
        + (fin_branch_name!=null? fin_branch_name.hashCode(): 0) 
        + (fin_save_div!=null? fin_save_div.hashCode(): 0) 
        + (fin_account_no!=null? fin_account_no.hashCode(): 0) 
        + (fin_account_name!=null? fin_account_name.hashCode(): 0) 
        + (trans_commission!=null? trans_commission.hashCode(): 0) 
        + (trans_deal_div!=null? trans_deal_div.hashCode(): 0) 
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
		if ( !designate_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'designate_div' must be set before inserting.");
		if ( !transfer_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_div' must be set before inserting.");
		if ( !fin_save_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_save_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		if ( sonar_last_updated_timestamp != null )
			map.put("sonar_last_updated_timestamp",sonar_last_updated_timestamp);
		if ( sonar_created_timestamp != null )
			map.put("sonar_created_timestamp",sonar_created_timestamp);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("designate_div",designate_div);
		if ( comodity != null )
			map.put("comodity",comodity);
		if ( fund_code != null )
			map.put("fund_code",fund_code);
		map.put("transfer_div",transfer_div);
		if ( fin_institution_code != null )
			map.put("fin_institution_code",fin_institution_code);
		if ( fin_institution_name != null )
			map.put("fin_institution_name",fin_institution_name);
		if ( fin_branch_code != null )
			map.put("fin_branch_code",fin_branch_code);
		if ( fin_branch_name != null )
			map.put("fin_branch_name",fin_branch_name);
		map.put("fin_save_div",fin_save_div);
		if ( fin_account_no != null )
			map.put("fin_account_no",fin_account_no);
		if ( fin_account_name != null )
			map.put("fin_account_name",fin_account_name);
		if ( trans_commission != null )
			map.put("trans_commission",trans_commission);
		if ( trans_deal_div != null )
			map.put("trans_deal_div",trans_deal_div);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( sonar_last_updated_timestamp_is_modified )
			map.put("sonar_last_updated_timestamp",sonar_last_updated_timestamp);
		if ( sonar_created_timestamp_is_modified )
			map.put("sonar_created_timestamp",sonar_created_timestamp);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( designate_div_is_modified )
			map.put("designate_div",designate_div);
		if ( comodity_is_modified )
			map.put("comodity",comodity);
		if ( fund_code_is_modified )
			map.put("fund_code",fund_code);
		if ( transfer_div_is_modified )
			map.put("transfer_div",transfer_div);
		if ( fin_institution_code_is_modified )
			map.put("fin_institution_code",fin_institution_code);
		if ( fin_institution_name_is_modified )
			map.put("fin_institution_name",fin_institution_name);
		if ( fin_branch_code_is_modified )
			map.put("fin_branch_code",fin_branch_code);
		if ( fin_branch_name_is_modified )
			map.put("fin_branch_name",fin_branch_name);
		if ( fin_save_div_is_modified )
			map.put("fin_save_div",fin_save_div);
		if ( fin_account_no_is_modified )
			map.put("fin_account_no",fin_account_no);
		if ( fin_account_name_is_modified )
			map.put("fin_account_name",fin_account_name);
		if ( trans_commission_is_modified )
			map.put("trans_commission",trans_commission);
		if ( trans_deal_div_is_modified )
			map.put("trans_deal_div",trans_deal_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("sonar_last_updated_timestamp",sonar_last_updated_timestamp);
			map.put("sonar_created_timestamp",sonar_created_timestamp);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( designate_div_is_set )
				map.put("designate_div",designate_div);
			map.put("comodity",comodity);
			map.put("fund_code",fund_code);
			if ( transfer_div_is_set )
				map.put("transfer_div",transfer_div);
			map.put("fin_institution_code",fin_institution_code);
			map.put("fin_institution_name",fin_institution_name);
			map.put("fin_branch_code",fin_branch_code);
			map.put("fin_branch_name",fin_branch_name);
			if ( fin_save_div_is_set )
				map.put("fin_save_div",fin_save_div);
			map.put("fin_account_no",fin_account_no);
			map.put("fin_account_name",fin_account_name);
			map.put("trans_commission",trans_commission);
			map.put("trans_deal_div",trans_deal_div);
			map.put("last_updater",last_updater);
			map.put("created_timestamp",created_timestamp);
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
   * <em>sonar_last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSonarLastUpdatedTimestamp()
  {
    return sonar_last_updated_timestamp;
  }


  /** 
   * <em>sonar_last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarLastUpdatedTimestampIsSet() {
    return sonar_last_updated_timestamp_is_set;
  }


  /** 
   * <em>sonar_last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarLastUpdatedTimestampIsModified() {
    return sonar_last_updated_timestamp_is_modified;
  }


  /** 
   * <em>sonar_created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSonarCreatedTimestamp()
  {
    return sonar_created_timestamp;
  }


  /** 
   * <em>sonar_created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCreatedTimestampIsSet() {
    return sonar_created_timestamp_is_set;
  }


  /** 
   * <em>sonar_created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCreatedTimestampIsModified() {
    return sonar_created_timestamp_is_modified;
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
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>designate_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDesignateDiv()
  {
    return designate_div;
  }


  /** 
   * <em>designate_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDesignateDivIsSet() {
    return designate_div_is_set;
  }


  /** 
   * <em>designate_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDesignateDivIsModified() {
    return designate_div_is_modified;
  }


  /** 
   * <em>comodity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComodity()
  {
    return comodity;
  }


  /** 
   * <em>comodity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComodityIsSet() {
    return comodity_is_set;
  }


  /** 
   * <em>comodity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getComodityIsModified() {
    return comodity_is_modified;
  }


  /** 
   * <em>fund_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundCode()
  {
    return fund_code;
  }


  /** 
   * <em>fund_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundCodeIsSet() {
    return fund_code_is_set;
  }


  /** 
   * <em>fund_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundCodeIsModified() {
    return fund_code_is_modified;
  }


  /** 
   * <em>transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferDiv()
  {
    return transfer_div;
  }


  /** 
   * <em>transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsSet() {
    return transfer_div_is_set;
  }


  /** 
   * <em>transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsModified() {
    return transfer_div_is_modified;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinInstitutionCode()
  {
    return fin_institution_code;
  }


  /** 
   * <em>fin_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionCodeIsSet() {
    return fin_institution_code_is_set;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionCodeIsModified() {
    return fin_institution_code_is_modified;
  }


  /** 
   * <em>fin_institution_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinInstitutionName()
  {
    return fin_institution_name;
  }


  /** 
   * <em>fin_institution_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionNameIsSet() {
    return fin_institution_name_is_set;
  }


  /** 
   * <em>fin_institution_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionNameIsModified() {
    return fin_institution_name_is_modified;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinBranchCode()
  {
    return fin_branch_code;
  }


  /** 
   * <em>fin_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchCodeIsSet() {
    return fin_branch_code_is_set;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchCodeIsModified() {
    return fin_branch_code_is_modified;
  }


  /** 
   * <em>fin_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinBranchName()
  {
    return fin_branch_name;
  }


  /** 
   * <em>fin_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchNameIsSet() {
    return fin_branch_name_is_set;
  }


  /** 
   * <em>fin_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchNameIsModified() {
    return fin_branch_name_is_modified;
  }


  /** 
   * <em>fin_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinSaveDiv()
  {
    return fin_save_div;
  }


  /** 
   * <em>fin_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinSaveDivIsSet() {
    return fin_save_div_is_set;
  }


  /** 
   * <em>fin_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinSaveDivIsModified() {
    return fin_save_div_is_modified;
  }


  /** 
   * <em>fin_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinAccountNo()
  {
    return fin_account_no;
  }


  /** 
   * <em>fin_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNoIsSet() {
    return fin_account_no_is_set;
  }


  /** 
   * <em>fin_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNoIsModified() {
    return fin_account_no_is_modified;
  }


  /** 
   * <em>fin_account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinAccountName()
  {
    return fin_account_name;
  }


  /** 
   * <em>fin_account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNameIsSet() {
    return fin_account_name_is_set;
  }


  /** 
   * <em>fin_account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNameIsModified() {
    return fin_account_name_is_modified;
  }


  /** 
   * <em>trans_commission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransCommission()
  {
    return trans_commission;
  }


  /** 
   * <em>trans_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransCommissionIsSet() {
    return trans_commission_is_set;
  }


  /** 
   * <em>trans_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransCommissionIsModified() {
    return trans_commission_is_modified;
  }


  /** 
   * <em>trans_deal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransDealDiv()
  {
    return trans_deal_div;
  }


  /** 
   * <em>trans_deal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransDealDivIsSet() {
    return trans_deal_div_is_set;
  }


  /** 
   * <em>trans_deal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransDealDivIsModified() {
    return trans_deal_div_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
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
    throw new UnsupportedOperationException("Table direct_debit has no primary key.");
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>sonar_last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_sonarLastUpdatedTimestamp <em>sonar_last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSonarLastUpdatedTimestamp( java.sql.Timestamp p_sonarLastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_last_updated_timestamp = p_sonarLastUpdatedTimestamp;
    sonar_last_updated_timestamp_is_set = true;
    sonar_last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sonar_last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSonarLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sonar_last_updated_timestamp_is_set = true;
    sonar_last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>sonar_created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_sonarCreatedTimestamp <em>sonar_created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSonarCreatedTimestamp( java.sql.Timestamp p_sonarCreatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_created_timestamp = p_sonarCreatedTimestamp;
    sonar_created_timestamp_is_set = true;
    sonar_created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sonar_created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSonarCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sonar_created_timestamp_is_set = true;
    sonar_created_timestamp_is_modified = true;
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
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>trader_code</em>カラムの値を設定します。 
   *
   * @@param p_traderCode <em>trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>designate_div</em>カラムの値を設定します。 
   *
   * @@param p_designateDiv <em>designate_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDesignateDiv( String p_designateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    designate_div = p_designateDiv;
    designate_div_is_set = true;
    designate_div_is_modified = true;
  }


  /** 
   * <em>comodity</em>カラムの値を設定します。 
   *
   * @@param p_comodity <em>comodity</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setComodity( String p_comodity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comodity = p_comodity;
    comodity_is_set = true;
    comodity_is_modified = true;
  }


  /** 
   * <em>fund_code</em>カラムの値を設定します。 
   *
   * @@param p_fundCode <em>fund_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setFundCode( String p_fundCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_code = p_fundCode;
    fund_code_is_set = true;
    fund_code_is_modified = true;
  }


  /** 
   * <em>transfer_div</em>カラムの値を設定します。 
   *
   * @@param p_transferDiv <em>transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransferDiv( String p_transferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_div = p_transferDiv;
    transfer_div_is_set = true;
    transfer_div_is_modified = true;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値を設定します。 
   *
   * @@param p_finInstitutionCode <em>fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setFinInstitutionCode( String p_finInstitutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_code = p_finInstitutionCode;
    fin_institution_code_is_set = true;
    fin_institution_code_is_modified = true;
  }


  /** 
   * <em>fin_institution_name</em>カラムの値を設定します。 
   *
   * @@param p_finInstitutionName <em>fin_institution_name</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setFinInstitutionName( String p_finInstitutionName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_name = p_finInstitutionName;
    fin_institution_name_is_set = true;
    fin_institution_name_is_modified = true;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_finBranchCode <em>fin_branch_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setFinBranchCode( String p_finBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_code = p_finBranchCode;
    fin_branch_code_is_set = true;
    fin_branch_code_is_modified = true;
  }


  /** 
   * <em>fin_branch_name</em>カラムの値を設定します。 
   *
   * @@param p_finBranchName <em>fin_branch_name</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setFinBranchName( String p_finBranchName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_name = p_finBranchName;
    fin_branch_name_is_set = true;
    fin_branch_name_is_modified = true;
  }


  /** 
   * <em>fin_save_div</em>カラムの値を設定します。 
   *
   * @@param p_finSaveDiv <em>fin_save_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFinSaveDiv( String p_finSaveDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_save_div = p_finSaveDiv;
    fin_save_div_is_set = true;
    fin_save_div_is_modified = true;
  }


  /** 
   * <em>fin_account_no</em>カラムの値を設定します。 
   *
   * @@param p_finAccountNo <em>fin_account_no</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setFinAccountNo( String p_finAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_account_no = p_finAccountNo;
    fin_account_no_is_set = true;
    fin_account_no_is_modified = true;
  }


  /** 
   * <em>fin_account_name</em>カラムの値を設定します。 
   *
   * @@param p_finAccountName <em>fin_account_name</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setFinAccountName( String p_finAccountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_account_name = p_finAccountName;
    fin_account_name_is_set = true;
    fin_account_name_is_modified = true;
  }


  /** 
   * <em>trans_commission</em>カラムの値を設定します。 
   *
   * @@param p_transCommission <em>trans_commission</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransCommission( String p_transCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trans_commission = p_transCommission;
    trans_commission_is_set = true;
    trans_commission_is_modified = true;
  }


  /** 
   * <em>trans_deal_div</em>カラムの値を設定します。 
   *
   * @@param p_transDealDiv <em>trans_deal_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransDealDiv( String p_transDealDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trans_deal_div = p_transDealDiv;
    trans_deal_div_is_set = true;
    trans_deal_div_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
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
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("comodity") ) {
                    return this.comodity;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("designate_div") ) {
                    return this.designate_div;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    return this.fund_code;
                }
                else if ( name.equals("fin_institution_code") ) {
                    return this.fin_institution_code;
                }
                else if ( name.equals("fin_institution_name") ) {
                    return this.fin_institution_name;
                }
                else if ( name.equals("fin_branch_code") ) {
                    return this.fin_branch_code;
                }
                else if ( name.equals("fin_branch_name") ) {
                    return this.fin_branch_name;
                }
                else if ( name.equals("fin_save_div") ) {
                    return this.fin_save_div;
                }
                else if ( name.equals("fin_account_no") ) {
                    return this.fin_account_no;
                }
                else if ( name.equals("fin_account_name") ) {
                    return this.fin_account_name;
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
            case 's':
                if ( name.equals("sonar_last_updated_timestamp") ) {
                    return this.sonar_last_updated_timestamp;
                }
                else if ( name.equals("sonar_created_timestamp") ) {
                    return this.sonar_created_timestamp;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("transfer_div") ) {
                    return this.transfer_div;
                }
                else if ( name.equals("trans_commission") ) {
                    return this.trans_commission;
                }
                else if ( name.equals("trans_deal_div") ) {
                    return this.trans_deal_div;
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
                if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                break;
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
                break;
            case 'c':
                if ( name.equals("comodity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comodity' must be String: '"+value+"' is not." );
                    this.comodity = (String) value;
                    if (this.comodity_is_set)
                        this.comodity_is_modified = true;
                    this.comodity_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("designate_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'designate_div' must be String: '"+value+"' is not." );
                    this.designate_div = (String) value;
                    if (this.designate_div_is_set)
                        this.designate_div_is_modified = true;
                    this.designate_div_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_code' must be String: '"+value+"' is not." );
                    this.fund_code = (String) value;
                    if (this.fund_code_is_set)
                        this.fund_code_is_modified = true;
                    this.fund_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_code' must be String: '"+value+"' is not." );
                    this.fin_institution_code = (String) value;
                    if (this.fin_institution_code_is_set)
                        this.fin_institution_code_is_modified = true;
                    this.fin_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_name' must be String: '"+value+"' is not." );
                    this.fin_institution_name = (String) value;
                    if (this.fin_institution_name_is_set)
                        this.fin_institution_name_is_modified = true;
                    this.fin_institution_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_code' must be String: '"+value+"' is not." );
                    this.fin_branch_code = (String) value;
                    if (this.fin_branch_code_is_set)
                        this.fin_branch_code_is_modified = true;
                    this.fin_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_name' must be String: '"+value+"' is not." );
                    this.fin_branch_name = (String) value;
                    if (this.fin_branch_name_is_set)
                        this.fin_branch_name_is_modified = true;
                    this.fin_branch_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_save_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_save_div' must be String: '"+value+"' is not." );
                    this.fin_save_div = (String) value;
                    if (this.fin_save_div_is_set)
                        this.fin_save_div_is_modified = true;
                    this.fin_save_div_is_set = true;
                    return;
                }
                else if ( name.equals("fin_account_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_account_no' must be String: '"+value+"' is not." );
                    this.fin_account_no = (String) value;
                    if (this.fin_account_no_is_set)
                        this.fin_account_no_is_modified = true;
                    this.fin_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("fin_account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_account_name' must be String: '"+value+"' is not." );
                    this.fin_account_name = (String) value;
                    if (this.fin_account_name_is_set)
                        this.fin_account_name_is_modified = true;
                    this.fin_account_name_is_set = true;
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
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sonar_last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sonar_last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.sonar_last_updated_timestamp_is_set)
                        this.sonar_last_updated_timestamp_is_modified = true;
                    this.sonar_last_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sonar_created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sonar_created_timestamp = (java.sql.Timestamp) value;
                    if (this.sonar_created_timestamp_is_set)
                        this.sonar_created_timestamp_is_modified = true;
                    this.sonar_created_timestamp_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_div' must be String: '"+value+"' is not." );
                    this.transfer_div = (String) value;
                    if (this.transfer_div_is_set)
                        this.transfer_div_is_modified = true;
                    this.transfer_div_is_set = true;
                    return;
                }
                else if ( name.equals("trans_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trans_commission' must be String: '"+value+"' is not." );
                    this.trans_commission = (String) value;
                    if (this.trans_commission_is_set)
                        this.trans_commission_is_modified = true;
                    this.trans_commission_is_set = true;
                    return;
                }
                else if ( name.equals("trans_deal_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trans_deal_div' must be String: '"+value+"' is not." );
                    this.trans_deal_div = (String) value;
                    if (this.trans_deal_div_is_set)
                        this.trans_deal_div_is_modified = true;
                    this.trans_deal_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
