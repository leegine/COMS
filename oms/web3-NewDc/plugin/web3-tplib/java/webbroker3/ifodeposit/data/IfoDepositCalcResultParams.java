head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcResultParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ifo_deposit_calc_resultテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link IfoDepositCalcResultRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link IfoDepositCalcResultParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link IfoDepositCalcResultParams}が{@@link IfoDepositCalcResultRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDepositCalcResultPK 
 * @@see IfoDepositCalcResultRow 
 */
public class IfoDepositCalcResultParams extends Params implements IfoDepositCalcResultRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_deposit_calc_result";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = IfoDepositCalcResultRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return IfoDepositCalcResultRow.TYPE;
  }


  /** 
   * <em>deposit_info_id</em>カラムの値 
   */
  public  long  deposit_info_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>base_date</em>カラムの値 
   */
  public  String  base_date;

  /** 
   * <em>balance_0</em>カラムの値 
   */
  public  String  balance_0;

  /** 
   * <em>balance_1</em>カラムの値 
   */
  public  String  balance_1;

  /** 
   * <em>balance_2</em>カラムの値 
   */
  public  String  balance_2;

  /** 
   * <em>ifo_deposit_balance_0</em>カラムの値 
   */
  public  String  ifo_deposit_balance_0;

  /** 
   * <em>ifo_deposit_balance_1</em>カラムの値 
   */
  public  String  ifo_deposit_balance_1;

  /** 
   * <em>ifo_deposit_balance_2</em>カラムの値 
   */
  public  String  ifo_deposit_balance_2;

  /** 
   * <em>ifo_deposit_balance_f0</em>カラムの値 
   */
  public  String  ifo_deposit_balance_f0;

  /** 
   * <em>ifo_deposit_balance_f1</em>カラムの値 
   */
  public  String  ifo_deposit_balance_f1;

  /** 
   * <em>ifo_deposit_balance_f2</em>カラムの値 
   */
  public  String  ifo_deposit_balance_f2;

  /** 
   * <em>today_transfer_amt_0</em>カラムの値 
   */
  public  String  today_transfer_amt_0;

  /** 
   * <em>today_transfer_amt_1</em>カラムの値 
   */
  public  String  today_transfer_amt_1;

  /** 
   * <em>today_transfer_amt_2</em>カラムの値 
   */
  public  String  today_transfer_amt_2;

  /** 
   * <em>today_fut_settle_profit_0</em>カラムの値 
   */
  public  String  today_fut_settle_profit_0;

  /** 
   * <em>today_fut_settle_profit_1</em>カラムの値 
   */
  public  String  today_fut_settle_profit_1;

  /** 
   * <em>today_fut_settle_profit_2</em>カラムの値 
   */
  public  String  today_fut_settle_profit_2;

  /** 
   * <em>today_op_net_amt_0</em>カラムの値 
   */
  public  String  today_op_net_amt_0;

  /** 
   * <em>today_op_net_amt_1</em>カラムの値 
   */
  public  String  today_op_net_amt_1;

  /** 
   * <em>today_op_net_amt_2</em>カラムの値 
   */
  public  String  today_op_net_amt_2;

  /** 
   * <em>fut_eval_profit_0</em>カラムの値 
   */
  public  String  fut_eval_profit_0;

  /** 
   * <em>fut_eval_profit_1</em>カラムの値 
   */
  public  String  fut_eval_profit_1;

  /** 
   * <em>fut_eval_profit_2</em>カラムの値 
   */
  public  String  fut_eval_profit_2;

  /** 
   * <em>long_fut_eval_profit_0</em>カラムの値 
   */
  public  String  long_fut_eval_profit_0;

  /** 
   * <em>long_fut_eval_profit_1</em>カラムの値 
   */
  public  String  long_fut_eval_profit_1;

  /** 
   * <em>long_fut_eval_profit_2</em>カラムの値 
   */
  public  String  long_fut_eval_profit_2;

  /** 
   * <em>short_fut_eval_profit_0</em>カラムの値 
   */
  public  String  short_fut_eval_profit_0;

  /** 
   * <em>short_fut_eval_profit_1</em>カラムの値 
   */
  public  String  short_fut_eval_profit_1;

  /** 
   * <em>short_fut_eval_profit_2</em>カラムの値 
   */
  public  String  short_fut_eval_profit_2;

  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムの値 
   */
  public  String  acceptance_ifo_deposit_bal_0;

  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムの値 
   */
  public  String  acceptance_ifo_deposit_bal_1;

  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムの値 
   */
  public  String  acceptance_ifo_deposit_bal_2;

  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムの値 
   */
  public  String  acceptance_ifo_deposit_bal_f0;

  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムの値 
   */
  public  String  acceptance_ifo_deposit_bal_f1;

  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムの値 
   */
  public  String  acceptance_ifo_deposit_bal_f2;

  /** 
   * <em>net_op_value_total_amt_0</em>カラムの値 
   */
  public  String  net_op_value_total_amt_0;

  /** 
   * <em>net_op_value_total_amt_1</em>カラムの値 
   */
  public  String  net_op_value_total_amt_1;

  /** 
   * <em>net_op_value_total_amt_2</em>カラムの値 
   */
  public  String  net_op_value_total_amt_2;

  /** 
   * <em>net_op_value_total_amt_f0</em>カラムの値 
   */
  public  String  net_op_value_total_amt_f0;

  /** 
   * <em>net_op_value_total_amt_f1</em>カラムの値 
   */
  public  String  net_op_value_total_amt_f1;

  /** 
   * <em>net_op_value_total_amt_f2</em>カラムの値 
   */
  public  String  net_op_value_total_amt_f2;

  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムの値 
   */
  public  String  ifo_deposit_necessary_amt_0;

  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムの値 
   */
  public  String  ifo_deposit_necessary_amt_1;

  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムの値 
   */
  public  String  ifo_deposit_necessary_amt_2;

  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムの値 
   */
  public  String  ifo_deposit_necessary_amt_f0;

  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムの値 
   */
  public  String  ifo_deposit_necessary_amt_f1;

  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムの値 
   */
  public  String  ifo_deposit_necessary_amt_f2;

  /** 
   * <em>ifo_deposit_power_0</em>カラムの値 
   */
  public  String  ifo_deposit_power_0;

  /** 
   * <em>ifo_deposit_power_1</em>カラムの値 
   */
  public  String  ifo_deposit_power_1;

  /** 
   * <em>ifo_deposit_power_2</em>カラムの値 
   */
  public  String  ifo_deposit_power_2;

  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムの値 
   */
  public  String  ifo_deposit_transfer_power_0;

  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムの値 
   */
  public  String  ifo_deposit_transfer_power_1;

  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムの値 
   */
  public  String  ifo_deposit_transfer_power_2;

  /** 
   * <em>bull_quantity_nk225_0</em>カラムの値 
   */
  public  String  bull_quantity_nk225_0;

  /** 
   * <em>bull_quantity_nk225_1</em>カラムの値 
   */
  public  String  bull_quantity_nk225_1;

  /** 
   * <em>bull_quantity_nk225_2</em>カラムの値 
   */
  public  String  bull_quantity_nk225_2;

  /** 
   * <em>bear_quantity_nk225_0</em>カラムの値 
   */
  public  String  bear_quantity_nk225_0;

  /** 
   * <em>bear_quantity_nk225_1</em>カラムの値 
   */
  public  String  bear_quantity_nk225_1;

  /** 
   * <em>bear_quantity_nk225_2</em>カラムの値 
   */
  public  String  bear_quantity_nk225_2;

  /** 
   * <em>long_pos_nk225_0</em>カラムの値 
   */
  public  String  long_pos_nk225_0;

  /** 
   * <em>long_pos_nk225_1</em>カラムの値 
   */
  public  String  long_pos_nk225_1;

  /** 
   * <em>long_pos_nk225_2</em>カラムの値 
   */
  public  String  long_pos_nk225_2;

  /** 
   * <em>part_long_pos_nk225_0</em>カラムの値 
   */
  public  String  part_long_pos_nk225_0;

  /** 
   * <em>part_long_pos_nk225_1</em>カラムの値 
   */
  public  String  part_long_pos_nk225_1;

  /** 
   * <em>part_long_pos_nk225_2</em>カラムの値 
   */
  public  String  part_long_pos_nk225_2;

  /** 
   * <em>short_pos_nk225_0</em>カラムの値 
   */
  public  String  short_pos_nk225_0;

  /** 
   * <em>short_pos_nk225_1</em>カラムの値 
   */
  public  String  short_pos_nk225_1;

  /** 
   * <em>short_pos_nk225_2</em>カラムの値 
   */
  public  String  short_pos_nk225_2;

  /** 
   * <em>part_short_pos_nk225_0</em>カラムの値 
   */
  public  String  part_short_pos_nk225_0;

  /** 
   * <em>part_short_pos_nk225_1</em>カラムの値 
   */
  public  String  part_short_pos_nk225_1;

  /** 
   * <em>part_short_pos_nk225_2</em>カラムの値 
   */
  public  String  part_short_pos_nk225_2;

  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムの値 
   */
  public  String  bull_quantity_nk225_mini_0;

  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムの値 
   */
  public  String  bull_quantity_nk225_mini_1;

  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムの値 
   */
  public  String  bull_quantity_nk225_mini_2;

  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムの値 
   */
  public  String  bear_quantity_nk225_mini_0;

  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムの値 
   */
  public  String  bear_quantity_nk225_mini_1;

  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムの値 
   */
  public  String  bear_quantity_nk225_mini_2;

  /** 
   * <em>long_pos_nk225_mini_0</em>カラムの値 
   */
  public  String  long_pos_nk225_mini_0;

  /** 
   * <em>long_pos_nk225_mini_1</em>カラムの値 
   */
  public  String  long_pos_nk225_mini_1;

  /** 
   * <em>long_pos_nk225_mini_2</em>カラムの値 
   */
  public  String  long_pos_nk225_mini_2;

  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムの値 
   */
  public  String  part_long_pos_nk225_mini_0;

  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムの値 
   */
  public  String  part_long_pos_nk225_mini_1;

  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムの値 
   */
  public  String  part_long_pos_nk225_mini_2;

  /** 
   * <em>short_pos_nk225_mini_0</em>カラムの値 
   */
  public  String  short_pos_nk225_mini_0;

  /** 
   * <em>short_pos_nk225_mini_1</em>カラムの値 
   */
  public  String  short_pos_nk225_mini_1;

  /** 
   * <em>short_pos_nk225_mini_2</em>カラムの値 
   */
  public  String  short_pos_nk225_mini_2;

  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムの値 
   */
  public  String  part_short_pos_nk225_mini_0;

  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムの値 
   */
  public  String  part_short_pos_nk225_mini_1;

  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムの値 
   */
  public  String  part_short_pos_nk225_mini_2;

  /** 
   * <em>buy_contract_amt_0</em>カラムの値 
   */
  public  String  buy_contract_amt_0;

  /** 
   * <em>buy_contract_amt_1</em>カラムの値 
   */
  public  String  buy_contract_amt_1;

  /** 
   * <em>buy_contract_amt_2</em>カラムの値 
   */
  public  String  buy_contract_amt_2;

  /** 
   * <em>buy_contract_amt_f0</em>カラムの値 
   */
  public  String  buy_contract_amt_f0;

  /** 
   * <em>buy_contract_amt_f1</em>カラムの値 
   */
  public  String  buy_contract_amt_f1;

  /** 
   * <em>buy_contract_amt_f2</em>カラムの値 
   */
  public  String  buy_contract_amt_f2;

  /** 
   * <em>sell_contract_amt_0</em>カラムの値 
   */
  public  String  sell_contract_amt_0;

  /** 
   * <em>sell_contract_amt_1</em>カラムの値 
   */
  public  String  sell_contract_amt_1;

  /** 
   * <em>sell_contract_amt_2</em>カラムの値 
   */
  public  String  sell_contract_amt_2;

  /** 
   * <em>sell_contract_amt_f0</em>カラムの値 
   */
  public  String  sell_contract_amt_f0;

  /** 
   * <em>sell_contract_amt_f1</em>カラムの値 
   */
  public  String  sell_contract_amt_f1;

  /** 
   * <em>sell_contract_amt_f2</em>カラムの値 
   */
  public  String  sell_contract_amt_f2;

  /** 
   * <em>non_pay_amt</em>カラムの値 
   */
  public  String  non_pay_amt;

  /** 
   * <em>today_claim_amt</em>カラムの値 
   */
  public  String  today_claim_amt;

  /** 
   * <em>tomorrow_claim_amt</em>カラムの値 
   */
  public  String  tomorrow_claim_amt;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムの値 
   */
  public  String  ifo_deposit_insufficient_flag;

  /** 
   * <em>ifo_deposit_index_nk225</em>カラムの値 
   */
  public  String  ifo_deposit_index_nk225;

  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムの値 
   */
  public  String  ifo_deposit_index_nk225_mini;

  /** 
   * <em>after_tomorrow_claim_amt</em>カラムの値 
   */
  public  String  after_tomorrow_claim_amt;

  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムの値 
   */
  public  String  tomorrow_claim_amt_evening;

  /** 
   * <em>status</em>カラムの値 
   */
  public  Integer  status;

  /** 
   * <em>error_message</em>カラムの値 
   */
  public  String  error_message;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean deposit_info_id_is_set = false;
  private boolean deposit_info_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean base_date_is_set = false;
  private boolean base_date_is_modified = false;
  private boolean balance_0_is_set = false;
  private boolean balance_0_is_modified = false;
  private boolean balance_1_is_set = false;
  private boolean balance_1_is_modified = false;
  private boolean balance_2_is_set = false;
  private boolean balance_2_is_modified = false;
  private boolean ifo_deposit_balance_0_is_set = false;
  private boolean ifo_deposit_balance_0_is_modified = false;
  private boolean ifo_deposit_balance_1_is_set = false;
  private boolean ifo_deposit_balance_1_is_modified = false;
  private boolean ifo_deposit_balance_2_is_set = false;
  private boolean ifo_deposit_balance_2_is_modified = false;
  private boolean ifo_deposit_balance_f0_is_set = false;
  private boolean ifo_deposit_balance_f0_is_modified = false;
  private boolean ifo_deposit_balance_f1_is_set = false;
  private boolean ifo_deposit_balance_f1_is_modified = false;
  private boolean ifo_deposit_balance_f2_is_set = false;
  private boolean ifo_deposit_balance_f2_is_modified = false;
  private boolean today_transfer_amt_0_is_set = false;
  private boolean today_transfer_amt_0_is_modified = false;
  private boolean today_transfer_amt_1_is_set = false;
  private boolean today_transfer_amt_1_is_modified = false;
  private boolean today_transfer_amt_2_is_set = false;
  private boolean today_transfer_amt_2_is_modified = false;
  private boolean today_fut_settle_profit_0_is_set = false;
  private boolean today_fut_settle_profit_0_is_modified = false;
  private boolean today_fut_settle_profit_1_is_set = false;
  private boolean today_fut_settle_profit_1_is_modified = false;
  private boolean today_fut_settle_profit_2_is_set = false;
  private boolean today_fut_settle_profit_2_is_modified = false;
  private boolean today_op_net_amt_0_is_set = false;
  private boolean today_op_net_amt_0_is_modified = false;
  private boolean today_op_net_amt_1_is_set = false;
  private boolean today_op_net_amt_1_is_modified = false;
  private boolean today_op_net_amt_2_is_set = false;
  private boolean today_op_net_amt_2_is_modified = false;
  private boolean fut_eval_profit_0_is_set = false;
  private boolean fut_eval_profit_0_is_modified = false;
  private boolean fut_eval_profit_1_is_set = false;
  private boolean fut_eval_profit_1_is_modified = false;
  private boolean fut_eval_profit_2_is_set = false;
  private boolean fut_eval_profit_2_is_modified = false;
  private boolean long_fut_eval_profit_0_is_set = false;
  private boolean long_fut_eval_profit_0_is_modified = false;
  private boolean long_fut_eval_profit_1_is_set = false;
  private boolean long_fut_eval_profit_1_is_modified = false;
  private boolean long_fut_eval_profit_2_is_set = false;
  private boolean long_fut_eval_profit_2_is_modified = false;
  private boolean short_fut_eval_profit_0_is_set = false;
  private boolean short_fut_eval_profit_0_is_modified = false;
  private boolean short_fut_eval_profit_1_is_set = false;
  private boolean short_fut_eval_profit_1_is_modified = false;
  private boolean short_fut_eval_profit_2_is_set = false;
  private boolean short_fut_eval_profit_2_is_modified = false;
  private boolean acceptance_ifo_deposit_bal_0_is_set = false;
  private boolean acceptance_ifo_deposit_bal_0_is_modified = false;
  private boolean acceptance_ifo_deposit_bal_1_is_set = false;
  private boolean acceptance_ifo_deposit_bal_1_is_modified = false;
  private boolean acceptance_ifo_deposit_bal_2_is_set = false;
  private boolean acceptance_ifo_deposit_bal_2_is_modified = false;
  private boolean acceptance_ifo_deposit_bal_f0_is_set = false;
  private boolean acceptance_ifo_deposit_bal_f0_is_modified = false;
  private boolean acceptance_ifo_deposit_bal_f1_is_set = false;
  private boolean acceptance_ifo_deposit_bal_f1_is_modified = false;
  private boolean acceptance_ifo_deposit_bal_f2_is_set = false;
  private boolean acceptance_ifo_deposit_bal_f2_is_modified = false;
  private boolean net_op_value_total_amt_0_is_set = false;
  private boolean net_op_value_total_amt_0_is_modified = false;
  private boolean net_op_value_total_amt_1_is_set = false;
  private boolean net_op_value_total_amt_1_is_modified = false;
  private boolean net_op_value_total_amt_2_is_set = false;
  private boolean net_op_value_total_amt_2_is_modified = false;
  private boolean net_op_value_total_amt_f0_is_set = false;
  private boolean net_op_value_total_amt_f0_is_modified = false;
  private boolean net_op_value_total_amt_f1_is_set = false;
  private boolean net_op_value_total_amt_f1_is_modified = false;
  private boolean net_op_value_total_amt_f2_is_set = false;
  private boolean net_op_value_total_amt_f2_is_modified = false;
  private boolean ifo_deposit_necessary_amt_0_is_set = false;
  private boolean ifo_deposit_necessary_amt_0_is_modified = false;
  private boolean ifo_deposit_necessary_amt_1_is_set = false;
  private boolean ifo_deposit_necessary_amt_1_is_modified = false;
  private boolean ifo_deposit_necessary_amt_2_is_set = false;
  private boolean ifo_deposit_necessary_amt_2_is_modified = false;
  private boolean ifo_deposit_necessary_amt_f0_is_set = false;
  private boolean ifo_deposit_necessary_amt_f0_is_modified = false;
  private boolean ifo_deposit_necessary_amt_f1_is_set = false;
  private boolean ifo_deposit_necessary_amt_f1_is_modified = false;
  private boolean ifo_deposit_necessary_amt_f2_is_set = false;
  private boolean ifo_deposit_necessary_amt_f2_is_modified = false;
  private boolean ifo_deposit_power_0_is_set = false;
  private boolean ifo_deposit_power_0_is_modified = false;
  private boolean ifo_deposit_power_1_is_set = false;
  private boolean ifo_deposit_power_1_is_modified = false;
  private boolean ifo_deposit_power_2_is_set = false;
  private boolean ifo_deposit_power_2_is_modified = false;
  private boolean ifo_deposit_transfer_power_0_is_set = false;
  private boolean ifo_deposit_transfer_power_0_is_modified = false;
  private boolean ifo_deposit_transfer_power_1_is_set = false;
  private boolean ifo_deposit_transfer_power_1_is_modified = false;
  private boolean ifo_deposit_transfer_power_2_is_set = false;
  private boolean ifo_deposit_transfer_power_2_is_modified = false;
  private boolean bull_quantity_nk225_0_is_set = false;
  private boolean bull_quantity_nk225_0_is_modified = false;
  private boolean bull_quantity_nk225_1_is_set = false;
  private boolean bull_quantity_nk225_1_is_modified = false;
  private boolean bull_quantity_nk225_2_is_set = false;
  private boolean bull_quantity_nk225_2_is_modified = false;
  private boolean bear_quantity_nk225_0_is_set = false;
  private boolean bear_quantity_nk225_0_is_modified = false;
  private boolean bear_quantity_nk225_1_is_set = false;
  private boolean bear_quantity_nk225_1_is_modified = false;
  private boolean bear_quantity_nk225_2_is_set = false;
  private boolean bear_quantity_nk225_2_is_modified = false;
  private boolean long_pos_nk225_0_is_set = false;
  private boolean long_pos_nk225_0_is_modified = false;
  private boolean long_pos_nk225_1_is_set = false;
  private boolean long_pos_nk225_1_is_modified = false;
  private boolean long_pos_nk225_2_is_set = false;
  private boolean long_pos_nk225_2_is_modified = false;
  private boolean part_long_pos_nk225_0_is_set = false;
  private boolean part_long_pos_nk225_0_is_modified = false;
  private boolean part_long_pos_nk225_1_is_set = false;
  private boolean part_long_pos_nk225_1_is_modified = false;
  private boolean part_long_pos_nk225_2_is_set = false;
  private boolean part_long_pos_nk225_2_is_modified = false;
  private boolean short_pos_nk225_0_is_set = false;
  private boolean short_pos_nk225_0_is_modified = false;
  private boolean short_pos_nk225_1_is_set = false;
  private boolean short_pos_nk225_1_is_modified = false;
  private boolean short_pos_nk225_2_is_set = false;
  private boolean short_pos_nk225_2_is_modified = false;
  private boolean part_short_pos_nk225_0_is_set = false;
  private boolean part_short_pos_nk225_0_is_modified = false;
  private boolean part_short_pos_nk225_1_is_set = false;
  private boolean part_short_pos_nk225_1_is_modified = false;
  private boolean part_short_pos_nk225_2_is_set = false;
  private boolean part_short_pos_nk225_2_is_modified = false;
  private boolean bull_quantity_nk225_mini_0_is_set = false;
  private boolean bull_quantity_nk225_mini_0_is_modified = false;
  private boolean bull_quantity_nk225_mini_1_is_set = false;
  private boolean bull_quantity_nk225_mini_1_is_modified = false;
  private boolean bull_quantity_nk225_mini_2_is_set = false;
  private boolean bull_quantity_nk225_mini_2_is_modified = false;
  private boolean bear_quantity_nk225_mini_0_is_set = false;
  private boolean bear_quantity_nk225_mini_0_is_modified = false;
  private boolean bear_quantity_nk225_mini_1_is_set = false;
  private boolean bear_quantity_nk225_mini_1_is_modified = false;
  private boolean bear_quantity_nk225_mini_2_is_set = false;
  private boolean bear_quantity_nk225_mini_2_is_modified = false;
  private boolean long_pos_nk225_mini_0_is_set = false;
  private boolean long_pos_nk225_mini_0_is_modified = false;
  private boolean long_pos_nk225_mini_1_is_set = false;
  private boolean long_pos_nk225_mini_1_is_modified = false;
  private boolean long_pos_nk225_mini_2_is_set = false;
  private boolean long_pos_nk225_mini_2_is_modified = false;
  private boolean part_long_pos_nk225_mini_0_is_set = false;
  private boolean part_long_pos_nk225_mini_0_is_modified = false;
  private boolean part_long_pos_nk225_mini_1_is_set = false;
  private boolean part_long_pos_nk225_mini_1_is_modified = false;
  private boolean part_long_pos_nk225_mini_2_is_set = false;
  private boolean part_long_pos_nk225_mini_2_is_modified = false;
  private boolean short_pos_nk225_mini_0_is_set = false;
  private boolean short_pos_nk225_mini_0_is_modified = false;
  private boolean short_pos_nk225_mini_1_is_set = false;
  private boolean short_pos_nk225_mini_1_is_modified = false;
  private boolean short_pos_nk225_mini_2_is_set = false;
  private boolean short_pos_nk225_mini_2_is_modified = false;
  private boolean part_short_pos_nk225_mini_0_is_set = false;
  private boolean part_short_pos_nk225_mini_0_is_modified = false;
  private boolean part_short_pos_nk225_mini_1_is_set = false;
  private boolean part_short_pos_nk225_mini_1_is_modified = false;
  private boolean part_short_pos_nk225_mini_2_is_set = false;
  private boolean part_short_pos_nk225_mini_2_is_modified = false;
  private boolean buy_contract_amt_0_is_set = false;
  private boolean buy_contract_amt_0_is_modified = false;
  private boolean buy_contract_amt_1_is_set = false;
  private boolean buy_contract_amt_1_is_modified = false;
  private boolean buy_contract_amt_2_is_set = false;
  private boolean buy_contract_amt_2_is_modified = false;
  private boolean buy_contract_amt_f0_is_set = false;
  private boolean buy_contract_amt_f0_is_modified = false;
  private boolean buy_contract_amt_f1_is_set = false;
  private boolean buy_contract_amt_f1_is_modified = false;
  private boolean buy_contract_amt_f2_is_set = false;
  private boolean buy_contract_amt_f2_is_modified = false;
  private boolean sell_contract_amt_0_is_set = false;
  private boolean sell_contract_amt_0_is_modified = false;
  private boolean sell_contract_amt_1_is_set = false;
  private boolean sell_contract_amt_1_is_modified = false;
  private boolean sell_contract_amt_2_is_set = false;
  private boolean sell_contract_amt_2_is_modified = false;
  private boolean sell_contract_amt_f0_is_set = false;
  private boolean sell_contract_amt_f0_is_modified = false;
  private boolean sell_contract_amt_f1_is_set = false;
  private boolean sell_contract_amt_f1_is_modified = false;
  private boolean sell_contract_amt_f2_is_set = false;
  private boolean sell_contract_amt_f2_is_modified = false;
  private boolean non_pay_amt_is_set = false;
  private boolean non_pay_amt_is_modified = false;
  private boolean today_claim_amt_is_set = false;
  private boolean today_claim_amt_is_modified = false;
  private boolean tomorrow_claim_amt_is_set = false;
  private boolean tomorrow_claim_amt_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean ifo_deposit_insufficient_flag_is_set = false;
  private boolean ifo_deposit_insufficient_flag_is_modified = false;
  private boolean ifo_deposit_index_nk225_is_set = false;
  private boolean ifo_deposit_index_nk225_is_modified = false;
  private boolean ifo_deposit_index_nk225_mini_is_set = false;
  private boolean ifo_deposit_index_nk225_mini_is_modified = false;
  private boolean after_tomorrow_claim_amt_is_set = false;
  private boolean after_tomorrow_claim_amt_is_modified = false;
  private boolean tomorrow_claim_amt_evening_is_set = false;
  private boolean tomorrow_claim_amt_evening_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean error_message_is_set = false;
  private boolean error_message_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "deposit_info_id=" + deposit_info_id
      + "," + "account_id=" +account_id
      + "," + "base_date=" +base_date
      + "," + "balance_0=" +balance_0
      + "," + "balance_1=" +balance_1
      + "," + "balance_2=" +balance_2
      + "," + "ifo_deposit_balance_0=" +ifo_deposit_balance_0
      + "," + "ifo_deposit_balance_1=" +ifo_deposit_balance_1
      + "," + "ifo_deposit_balance_2=" +ifo_deposit_balance_2
      + "," + "ifo_deposit_balance_f0=" +ifo_deposit_balance_f0
      + "," + "ifo_deposit_balance_f1=" +ifo_deposit_balance_f1
      + "," + "ifo_deposit_balance_f2=" +ifo_deposit_balance_f2
      + "," + "today_transfer_amt_0=" +today_transfer_amt_0
      + "," + "today_transfer_amt_1=" +today_transfer_amt_1
      + "," + "today_transfer_amt_2=" +today_transfer_amt_2
      + "," + "today_fut_settle_profit_0=" +today_fut_settle_profit_0
      + "," + "today_fut_settle_profit_1=" +today_fut_settle_profit_1
      + "," + "today_fut_settle_profit_2=" +today_fut_settle_profit_2
      + "," + "today_op_net_amt_0=" +today_op_net_amt_0
      + "," + "today_op_net_amt_1=" +today_op_net_amt_1
      + "," + "today_op_net_amt_2=" +today_op_net_amt_2
      + "," + "fut_eval_profit_0=" +fut_eval_profit_0
      + "," + "fut_eval_profit_1=" +fut_eval_profit_1
      + "," + "fut_eval_profit_2=" +fut_eval_profit_2
      + "," + "long_fut_eval_profit_0=" +long_fut_eval_profit_0
      + "," + "long_fut_eval_profit_1=" +long_fut_eval_profit_1
      + "," + "long_fut_eval_profit_2=" +long_fut_eval_profit_2
      + "," + "short_fut_eval_profit_0=" +short_fut_eval_profit_0
      + "," + "short_fut_eval_profit_1=" +short_fut_eval_profit_1
      + "," + "short_fut_eval_profit_2=" +short_fut_eval_profit_2
      + "," + "acceptance_ifo_deposit_bal_0=" +acceptance_ifo_deposit_bal_0
      + "," + "acceptance_ifo_deposit_bal_1=" +acceptance_ifo_deposit_bal_1
      + "," + "acceptance_ifo_deposit_bal_2=" +acceptance_ifo_deposit_bal_2
      + "," + "acceptance_ifo_deposit_bal_f0=" +acceptance_ifo_deposit_bal_f0
      + "," + "acceptance_ifo_deposit_bal_f1=" +acceptance_ifo_deposit_bal_f1
      + "," + "acceptance_ifo_deposit_bal_f2=" +acceptance_ifo_deposit_bal_f2
      + "," + "net_op_value_total_amt_0=" +net_op_value_total_amt_0
      + "," + "net_op_value_total_amt_1=" +net_op_value_total_amt_1
      + "," + "net_op_value_total_amt_2=" +net_op_value_total_amt_2
      + "," + "net_op_value_total_amt_f0=" +net_op_value_total_amt_f0
      + "," + "net_op_value_total_amt_f1=" +net_op_value_total_amt_f1
      + "," + "net_op_value_total_amt_f2=" +net_op_value_total_amt_f2
      + "," + "ifo_deposit_necessary_amt_0=" +ifo_deposit_necessary_amt_0
      + "," + "ifo_deposit_necessary_amt_1=" +ifo_deposit_necessary_amt_1
      + "," + "ifo_deposit_necessary_amt_2=" +ifo_deposit_necessary_amt_2
      + "," + "ifo_deposit_necessary_amt_f0=" +ifo_deposit_necessary_amt_f0
      + "," + "ifo_deposit_necessary_amt_f1=" +ifo_deposit_necessary_amt_f1
      + "," + "ifo_deposit_necessary_amt_f2=" +ifo_deposit_necessary_amt_f2
      + "," + "ifo_deposit_power_0=" +ifo_deposit_power_0
      + "," + "ifo_deposit_power_1=" +ifo_deposit_power_1
      + "," + "ifo_deposit_power_2=" +ifo_deposit_power_2
      + "," + "ifo_deposit_transfer_power_0=" +ifo_deposit_transfer_power_0
      + "," + "ifo_deposit_transfer_power_1=" +ifo_deposit_transfer_power_1
      + "," + "ifo_deposit_transfer_power_2=" +ifo_deposit_transfer_power_2
      + "," + "bull_quantity_nk225_0=" +bull_quantity_nk225_0
      + "," + "bull_quantity_nk225_1=" +bull_quantity_nk225_1
      + "," + "bull_quantity_nk225_2=" +bull_quantity_nk225_2
      + "," + "bear_quantity_nk225_0=" +bear_quantity_nk225_0
      + "," + "bear_quantity_nk225_1=" +bear_quantity_nk225_1
      + "," + "bear_quantity_nk225_2=" +bear_quantity_nk225_2
      + "," + "long_pos_nk225_0=" +long_pos_nk225_0
      + "," + "long_pos_nk225_1=" +long_pos_nk225_1
      + "," + "long_pos_nk225_2=" +long_pos_nk225_2
      + "," + "part_long_pos_nk225_0=" +part_long_pos_nk225_0
      + "," + "part_long_pos_nk225_1=" +part_long_pos_nk225_1
      + "," + "part_long_pos_nk225_2=" +part_long_pos_nk225_2
      + "," + "short_pos_nk225_0=" +short_pos_nk225_0
      + "," + "short_pos_nk225_1=" +short_pos_nk225_1
      + "," + "short_pos_nk225_2=" +short_pos_nk225_2
      + "," + "part_short_pos_nk225_0=" +part_short_pos_nk225_0
      + "," + "part_short_pos_nk225_1=" +part_short_pos_nk225_1
      + "," + "part_short_pos_nk225_2=" +part_short_pos_nk225_2
      + "," + "bull_quantity_nk225_mini_0=" +bull_quantity_nk225_mini_0
      + "," + "bull_quantity_nk225_mini_1=" +bull_quantity_nk225_mini_1
      + "," + "bull_quantity_nk225_mini_2=" +bull_quantity_nk225_mini_2
      + "," + "bear_quantity_nk225_mini_0=" +bear_quantity_nk225_mini_0
      + "," + "bear_quantity_nk225_mini_1=" +bear_quantity_nk225_mini_1
      + "," + "bear_quantity_nk225_mini_2=" +bear_quantity_nk225_mini_2
      + "," + "long_pos_nk225_mini_0=" +long_pos_nk225_mini_0
      + "," + "long_pos_nk225_mini_1=" +long_pos_nk225_mini_1
      + "," + "long_pos_nk225_mini_2=" +long_pos_nk225_mini_2
      + "," + "part_long_pos_nk225_mini_0=" +part_long_pos_nk225_mini_0
      + "," + "part_long_pos_nk225_mini_1=" +part_long_pos_nk225_mini_1
      + "," + "part_long_pos_nk225_mini_2=" +part_long_pos_nk225_mini_2
      + "," + "short_pos_nk225_mini_0=" +short_pos_nk225_mini_0
      + "," + "short_pos_nk225_mini_1=" +short_pos_nk225_mini_1
      + "," + "short_pos_nk225_mini_2=" +short_pos_nk225_mini_2
      + "," + "part_short_pos_nk225_mini_0=" +part_short_pos_nk225_mini_0
      + "," + "part_short_pos_nk225_mini_1=" +part_short_pos_nk225_mini_1
      + "," + "part_short_pos_nk225_mini_2=" +part_short_pos_nk225_mini_2
      + "," + "buy_contract_amt_0=" +buy_contract_amt_0
      + "," + "buy_contract_amt_1=" +buy_contract_amt_1
      + "," + "buy_contract_amt_2=" +buy_contract_amt_2
      + "," + "buy_contract_amt_f0=" +buy_contract_amt_f0
      + "," + "buy_contract_amt_f1=" +buy_contract_amt_f1
      + "," + "buy_contract_amt_f2=" +buy_contract_amt_f2
      + "," + "sell_contract_amt_0=" +sell_contract_amt_0
      + "," + "sell_contract_amt_1=" +sell_contract_amt_1
      + "," + "sell_contract_amt_2=" +sell_contract_amt_2
      + "," + "sell_contract_amt_f0=" +sell_contract_amt_f0
      + "," + "sell_contract_amt_f1=" +sell_contract_amt_f1
      + "," + "sell_contract_amt_f2=" +sell_contract_amt_f2
      + "," + "non_pay_amt=" +non_pay_amt
      + "," + "today_claim_amt=" +today_claim_amt
      + "," + "tomorrow_claim_amt=" +tomorrow_claim_amt
      + "," + "delivery_date=" +delivery_date
      + "," + "ifo_deposit_insufficient_flag=" +ifo_deposit_insufficient_flag
      + "," + "ifo_deposit_index_nk225=" +ifo_deposit_index_nk225
      + "," + "ifo_deposit_index_nk225_mini=" +ifo_deposit_index_nk225_mini
      + "," + "after_tomorrow_claim_amt=" +after_tomorrow_claim_amt
      + "," + "tomorrow_claim_amt_evening=" +tomorrow_claim_amt_evening
      + "," + "status=" +status
      + "," + "error_message=" +error_message
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のIfoDepositCalcResultParamsオブジェクトを作成します。 
   */
  public IfoDepositCalcResultParams() {
    deposit_info_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のIfoDepositCalcResultRowオブジェクトの値を利用してIfoDepositCalcResultParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するIfoDepositCalcResultRowオブジェクト 
   */
  public IfoDepositCalcResultParams( IfoDepositCalcResultRow p_row )
  {
    deposit_info_id = p_row.getDepositInfoId();
    deposit_info_id_is_set = p_row.getDepositInfoIdIsSet();
    deposit_info_id_is_modified = p_row.getDepositInfoIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    base_date = p_row.getBaseDate();
    base_date_is_set = p_row.getBaseDateIsSet();
    base_date_is_modified = p_row.getBaseDateIsModified();
    balance_0 = p_row.getBalance0();
    balance_0_is_set = p_row.getBalance0IsSet();
    balance_0_is_modified = p_row.getBalance0IsModified();
    balance_1 = p_row.getBalance1();
    balance_1_is_set = p_row.getBalance1IsSet();
    balance_1_is_modified = p_row.getBalance1IsModified();
    balance_2 = p_row.getBalance2();
    balance_2_is_set = p_row.getBalance2IsSet();
    balance_2_is_modified = p_row.getBalance2IsModified();
    ifo_deposit_balance_0 = p_row.getIfoDepositBalance0();
    ifo_deposit_balance_0_is_set = p_row.getIfoDepositBalance0IsSet();
    ifo_deposit_balance_0_is_modified = p_row.getIfoDepositBalance0IsModified();
    ifo_deposit_balance_1 = p_row.getIfoDepositBalance1();
    ifo_deposit_balance_1_is_set = p_row.getIfoDepositBalance1IsSet();
    ifo_deposit_balance_1_is_modified = p_row.getIfoDepositBalance1IsModified();
    ifo_deposit_balance_2 = p_row.getIfoDepositBalance2();
    ifo_deposit_balance_2_is_set = p_row.getIfoDepositBalance2IsSet();
    ifo_deposit_balance_2_is_modified = p_row.getIfoDepositBalance2IsModified();
    ifo_deposit_balance_f0 = p_row.getIfoDepositBalanceF0();
    ifo_deposit_balance_f0_is_set = p_row.getIfoDepositBalanceF0IsSet();
    ifo_deposit_balance_f0_is_modified = p_row.getIfoDepositBalanceF0IsModified();
    ifo_deposit_balance_f1 = p_row.getIfoDepositBalanceF1();
    ifo_deposit_balance_f1_is_set = p_row.getIfoDepositBalanceF1IsSet();
    ifo_deposit_balance_f1_is_modified = p_row.getIfoDepositBalanceF1IsModified();
    ifo_deposit_balance_f2 = p_row.getIfoDepositBalanceF2();
    ifo_deposit_balance_f2_is_set = p_row.getIfoDepositBalanceF2IsSet();
    ifo_deposit_balance_f2_is_modified = p_row.getIfoDepositBalanceF2IsModified();
    today_transfer_amt_0 = p_row.getTodayTransferAmt0();
    today_transfer_amt_0_is_set = p_row.getTodayTransferAmt0IsSet();
    today_transfer_amt_0_is_modified = p_row.getTodayTransferAmt0IsModified();
    today_transfer_amt_1 = p_row.getTodayTransferAmt1();
    today_transfer_amt_1_is_set = p_row.getTodayTransferAmt1IsSet();
    today_transfer_amt_1_is_modified = p_row.getTodayTransferAmt1IsModified();
    today_transfer_amt_2 = p_row.getTodayTransferAmt2();
    today_transfer_amt_2_is_set = p_row.getTodayTransferAmt2IsSet();
    today_transfer_amt_2_is_modified = p_row.getTodayTransferAmt2IsModified();
    today_fut_settle_profit_0 = p_row.getTodayFutSettleProfit0();
    today_fut_settle_profit_0_is_set = p_row.getTodayFutSettleProfit0IsSet();
    today_fut_settle_profit_0_is_modified = p_row.getTodayFutSettleProfit0IsModified();
    today_fut_settle_profit_1 = p_row.getTodayFutSettleProfit1();
    today_fut_settle_profit_1_is_set = p_row.getTodayFutSettleProfit1IsSet();
    today_fut_settle_profit_1_is_modified = p_row.getTodayFutSettleProfit1IsModified();
    today_fut_settle_profit_2 = p_row.getTodayFutSettleProfit2();
    today_fut_settle_profit_2_is_set = p_row.getTodayFutSettleProfit2IsSet();
    today_fut_settle_profit_2_is_modified = p_row.getTodayFutSettleProfit2IsModified();
    today_op_net_amt_0 = p_row.getTodayOpNetAmt0();
    today_op_net_amt_0_is_set = p_row.getTodayOpNetAmt0IsSet();
    today_op_net_amt_0_is_modified = p_row.getTodayOpNetAmt0IsModified();
    today_op_net_amt_1 = p_row.getTodayOpNetAmt1();
    today_op_net_amt_1_is_set = p_row.getTodayOpNetAmt1IsSet();
    today_op_net_amt_1_is_modified = p_row.getTodayOpNetAmt1IsModified();
    today_op_net_amt_2 = p_row.getTodayOpNetAmt2();
    today_op_net_amt_2_is_set = p_row.getTodayOpNetAmt2IsSet();
    today_op_net_amt_2_is_modified = p_row.getTodayOpNetAmt2IsModified();
    fut_eval_profit_0 = p_row.getFutEvalProfit0();
    fut_eval_profit_0_is_set = p_row.getFutEvalProfit0IsSet();
    fut_eval_profit_0_is_modified = p_row.getFutEvalProfit0IsModified();
    fut_eval_profit_1 = p_row.getFutEvalProfit1();
    fut_eval_profit_1_is_set = p_row.getFutEvalProfit1IsSet();
    fut_eval_profit_1_is_modified = p_row.getFutEvalProfit1IsModified();
    fut_eval_profit_2 = p_row.getFutEvalProfit2();
    fut_eval_profit_2_is_set = p_row.getFutEvalProfit2IsSet();
    fut_eval_profit_2_is_modified = p_row.getFutEvalProfit2IsModified();
    long_fut_eval_profit_0 = p_row.getLongFutEvalProfit0();
    long_fut_eval_profit_0_is_set = p_row.getLongFutEvalProfit0IsSet();
    long_fut_eval_profit_0_is_modified = p_row.getLongFutEvalProfit0IsModified();
    long_fut_eval_profit_1 = p_row.getLongFutEvalProfit1();
    long_fut_eval_profit_1_is_set = p_row.getLongFutEvalProfit1IsSet();
    long_fut_eval_profit_1_is_modified = p_row.getLongFutEvalProfit1IsModified();
    long_fut_eval_profit_2 = p_row.getLongFutEvalProfit2();
    long_fut_eval_profit_2_is_set = p_row.getLongFutEvalProfit2IsSet();
    long_fut_eval_profit_2_is_modified = p_row.getLongFutEvalProfit2IsModified();
    short_fut_eval_profit_0 = p_row.getShortFutEvalProfit0();
    short_fut_eval_profit_0_is_set = p_row.getShortFutEvalProfit0IsSet();
    short_fut_eval_profit_0_is_modified = p_row.getShortFutEvalProfit0IsModified();
    short_fut_eval_profit_1 = p_row.getShortFutEvalProfit1();
    short_fut_eval_profit_1_is_set = p_row.getShortFutEvalProfit1IsSet();
    short_fut_eval_profit_1_is_modified = p_row.getShortFutEvalProfit1IsModified();
    short_fut_eval_profit_2 = p_row.getShortFutEvalProfit2();
    short_fut_eval_profit_2_is_set = p_row.getShortFutEvalProfit2IsSet();
    short_fut_eval_profit_2_is_modified = p_row.getShortFutEvalProfit2IsModified();
    acceptance_ifo_deposit_bal_0 = p_row.getAcceptanceIfoDepositBal0();
    acceptance_ifo_deposit_bal_0_is_set = p_row.getAcceptanceIfoDepositBal0IsSet();
    acceptance_ifo_deposit_bal_0_is_modified = p_row.getAcceptanceIfoDepositBal0IsModified();
    acceptance_ifo_deposit_bal_1 = p_row.getAcceptanceIfoDepositBal1();
    acceptance_ifo_deposit_bal_1_is_set = p_row.getAcceptanceIfoDepositBal1IsSet();
    acceptance_ifo_deposit_bal_1_is_modified = p_row.getAcceptanceIfoDepositBal1IsModified();
    acceptance_ifo_deposit_bal_2 = p_row.getAcceptanceIfoDepositBal2();
    acceptance_ifo_deposit_bal_2_is_set = p_row.getAcceptanceIfoDepositBal2IsSet();
    acceptance_ifo_deposit_bal_2_is_modified = p_row.getAcceptanceIfoDepositBal2IsModified();
    acceptance_ifo_deposit_bal_f0 = p_row.getAcceptanceIfoDepositBalF0();
    acceptance_ifo_deposit_bal_f0_is_set = p_row.getAcceptanceIfoDepositBalF0IsSet();
    acceptance_ifo_deposit_bal_f0_is_modified = p_row.getAcceptanceIfoDepositBalF0IsModified();
    acceptance_ifo_deposit_bal_f1 = p_row.getAcceptanceIfoDepositBalF1();
    acceptance_ifo_deposit_bal_f1_is_set = p_row.getAcceptanceIfoDepositBalF1IsSet();
    acceptance_ifo_deposit_bal_f1_is_modified = p_row.getAcceptanceIfoDepositBalF1IsModified();
    acceptance_ifo_deposit_bal_f2 = p_row.getAcceptanceIfoDepositBalF2();
    acceptance_ifo_deposit_bal_f2_is_set = p_row.getAcceptanceIfoDepositBalF2IsSet();
    acceptance_ifo_deposit_bal_f2_is_modified = p_row.getAcceptanceIfoDepositBalF2IsModified();
    net_op_value_total_amt_0 = p_row.getNetOpValueTotalAmt0();
    net_op_value_total_amt_0_is_set = p_row.getNetOpValueTotalAmt0IsSet();
    net_op_value_total_amt_0_is_modified = p_row.getNetOpValueTotalAmt0IsModified();
    net_op_value_total_amt_1 = p_row.getNetOpValueTotalAmt1();
    net_op_value_total_amt_1_is_set = p_row.getNetOpValueTotalAmt1IsSet();
    net_op_value_total_amt_1_is_modified = p_row.getNetOpValueTotalAmt1IsModified();
    net_op_value_total_amt_2 = p_row.getNetOpValueTotalAmt2();
    net_op_value_total_amt_2_is_set = p_row.getNetOpValueTotalAmt2IsSet();
    net_op_value_total_amt_2_is_modified = p_row.getNetOpValueTotalAmt2IsModified();
    net_op_value_total_amt_f0 = p_row.getNetOpValueTotalAmtF0();
    net_op_value_total_amt_f0_is_set = p_row.getNetOpValueTotalAmtF0IsSet();
    net_op_value_total_amt_f0_is_modified = p_row.getNetOpValueTotalAmtF0IsModified();
    net_op_value_total_amt_f1 = p_row.getNetOpValueTotalAmtF1();
    net_op_value_total_amt_f1_is_set = p_row.getNetOpValueTotalAmtF1IsSet();
    net_op_value_total_amt_f1_is_modified = p_row.getNetOpValueTotalAmtF1IsModified();
    net_op_value_total_amt_f2 = p_row.getNetOpValueTotalAmtF2();
    net_op_value_total_amt_f2_is_set = p_row.getNetOpValueTotalAmtF2IsSet();
    net_op_value_total_amt_f2_is_modified = p_row.getNetOpValueTotalAmtF2IsModified();
    ifo_deposit_necessary_amt_0 = p_row.getIfoDepositNecessaryAmt0();
    ifo_deposit_necessary_amt_0_is_set = p_row.getIfoDepositNecessaryAmt0IsSet();
    ifo_deposit_necessary_amt_0_is_modified = p_row.getIfoDepositNecessaryAmt0IsModified();
    ifo_deposit_necessary_amt_1 = p_row.getIfoDepositNecessaryAmt1();
    ifo_deposit_necessary_amt_1_is_set = p_row.getIfoDepositNecessaryAmt1IsSet();
    ifo_deposit_necessary_amt_1_is_modified = p_row.getIfoDepositNecessaryAmt1IsModified();
    ifo_deposit_necessary_amt_2 = p_row.getIfoDepositNecessaryAmt2();
    ifo_deposit_necessary_amt_2_is_set = p_row.getIfoDepositNecessaryAmt2IsSet();
    ifo_deposit_necessary_amt_2_is_modified = p_row.getIfoDepositNecessaryAmt2IsModified();
    ifo_deposit_necessary_amt_f0 = p_row.getIfoDepositNecessaryAmtF0();
    ifo_deposit_necessary_amt_f0_is_set = p_row.getIfoDepositNecessaryAmtF0IsSet();
    ifo_deposit_necessary_amt_f0_is_modified = p_row.getIfoDepositNecessaryAmtF0IsModified();
    ifo_deposit_necessary_amt_f1 = p_row.getIfoDepositNecessaryAmtF1();
    ifo_deposit_necessary_amt_f1_is_set = p_row.getIfoDepositNecessaryAmtF1IsSet();
    ifo_deposit_necessary_amt_f1_is_modified = p_row.getIfoDepositNecessaryAmtF1IsModified();
    ifo_deposit_necessary_amt_f2 = p_row.getIfoDepositNecessaryAmtF2();
    ifo_deposit_necessary_amt_f2_is_set = p_row.getIfoDepositNecessaryAmtF2IsSet();
    ifo_deposit_necessary_amt_f2_is_modified = p_row.getIfoDepositNecessaryAmtF2IsModified();
    ifo_deposit_power_0 = p_row.getIfoDepositPower0();
    ifo_deposit_power_0_is_set = p_row.getIfoDepositPower0IsSet();
    ifo_deposit_power_0_is_modified = p_row.getIfoDepositPower0IsModified();
    ifo_deposit_power_1 = p_row.getIfoDepositPower1();
    ifo_deposit_power_1_is_set = p_row.getIfoDepositPower1IsSet();
    ifo_deposit_power_1_is_modified = p_row.getIfoDepositPower1IsModified();
    ifo_deposit_power_2 = p_row.getIfoDepositPower2();
    ifo_deposit_power_2_is_set = p_row.getIfoDepositPower2IsSet();
    ifo_deposit_power_2_is_modified = p_row.getIfoDepositPower2IsModified();
    ifo_deposit_transfer_power_0 = p_row.getIfoDepositTransferPower0();
    ifo_deposit_transfer_power_0_is_set = p_row.getIfoDepositTransferPower0IsSet();
    ifo_deposit_transfer_power_0_is_modified = p_row.getIfoDepositTransferPower0IsModified();
    ifo_deposit_transfer_power_1 = p_row.getIfoDepositTransferPower1();
    ifo_deposit_transfer_power_1_is_set = p_row.getIfoDepositTransferPower1IsSet();
    ifo_deposit_transfer_power_1_is_modified = p_row.getIfoDepositTransferPower1IsModified();
    ifo_deposit_transfer_power_2 = p_row.getIfoDepositTransferPower2();
    ifo_deposit_transfer_power_2_is_set = p_row.getIfoDepositTransferPower2IsSet();
    ifo_deposit_transfer_power_2_is_modified = p_row.getIfoDepositTransferPower2IsModified();
    bull_quantity_nk225_0 = p_row.getBullQuantityNk2250();
    bull_quantity_nk225_0_is_set = p_row.getBullQuantityNk2250IsSet();
    bull_quantity_nk225_0_is_modified = p_row.getBullQuantityNk2250IsModified();
    bull_quantity_nk225_1 = p_row.getBullQuantityNk2251();
    bull_quantity_nk225_1_is_set = p_row.getBullQuantityNk2251IsSet();
    bull_quantity_nk225_1_is_modified = p_row.getBullQuantityNk2251IsModified();
    bull_quantity_nk225_2 = p_row.getBullQuantityNk2252();
    bull_quantity_nk225_2_is_set = p_row.getBullQuantityNk2252IsSet();
    bull_quantity_nk225_2_is_modified = p_row.getBullQuantityNk2252IsModified();
    bear_quantity_nk225_0 = p_row.getBearQuantityNk2250();
    bear_quantity_nk225_0_is_set = p_row.getBearQuantityNk2250IsSet();
    bear_quantity_nk225_0_is_modified = p_row.getBearQuantityNk2250IsModified();
    bear_quantity_nk225_1 = p_row.getBearQuantityNk2251();
    bear_quantity_nk225_1_is_set = p_row.getBearQuantityNk2251IsSet();
    bear_quantity_nk225_1_is_modified = p_row.getBearQuantityNk2251IsModified();
    bear_quantity_nk225_2 = p_row.getBearQuantityNk2252();
    bear_quantity_nk225_2_is_set = p_row.getBearQuantityNk2252IsSet();
    bear_quantity_nk225_2_is_modified = p_row.getBearQuantityNk2252IsModified();
    long_pos_nk225_0 = p_row.getLongPosNk2250();
    long_pos_nk225_0_is_set = p_row.getLongPosNk2250IsSet();
    long_pos_nk225_0_is_modified = p_row.getLongPosNk2250IsModified();
    long_pos_nk225_1 = p_row.getLongPosNk2251();
    long_pos_nk225_1_is_set = p_row.getLongPosNk2251IsSet();
    long_pos_nk225_1_is_modified = p_row.getLongPosNk2251IsModified();
    long_pos_nk225_2 = p_row.getLongPosNk2252();
    long_pos_nk225_2_is_set = p_row.getLongPosNk2252IsSet();
    long_pos_nk225_2_is_modified = p_row.getLongPosNk2252IsModified();
    part_long_pos_nk225_0 = p_row.getPartLongPosNk2250();
    part_long_pos_nk225_0_is_set = p_row.getPartLongPosNk2250IsSet();
    part_long_pos_nk225_0_is_modified = p_row.getPartLongPosNk2250IsModified();
    part_long_pos_nk225_1 = p_row.getPartLongPosNk2251();
    part_long_pos_nk225_1_is_set = p_row.getPartLongPosNk2251IsSet();
    part_long_pos_nk225_1_is_modified = p_row.getPartLongPosNk2251IsModified();
    part_long_pos_nk225_2 = p_row.getPartLongPosNk2252();
    part_long_pos_nk225_2_is_set = p_row.getPartLongPosNk2252IsSet();
    part_long_pos_nk225_2_is_modified = p_row.getPartLongPosNk2252IsModified();
    short_pos_nk225_0 = p_row.getShortPosNk2250();
    short_pos_nk225_0_is_set = p_row.getShortPosNk2250IsSet();
    short_pos_nk225_0_is_modified = p_row.getShortPosNk2250IsModified();
    short_pos_nk225_1 = p_row.getShortPosNk2251();
    short_pos_nk225_1_is_set = p_row.getShortPosNk2251IsSet();
    short_pos_nk225_1_is_modified = p_row.getShortPosNk2251IsModified();
    short_pos_nk225_2 = p_row.getShortPosNk2252();
    short_pos_nk225_2_is_set = p_row.getShortPosNk2252IsSet();
    short_pos_nk225_2_is_modified = p_row.getShortPosNk2252IsModified();
    part_short_pos_nk225_0 = p_row.getPartShortPosNk2250();
    part_short_pos_nk225_0_is_set = p_row.getPartShortPosNk2250IsSet();
    part_short_pos_nk225_0_is_modified = p_row.getPartShortPosNk2250IsModified();
    part_short_pos_nk225_1 = p_row.getPartShortPosNk2251();
    part_short_pos_nk225_1_is_set = p_row.getPartShortPosNk2251IsSet();
    part_short_pos_nk225_1_is_modified = p_row.getPartShortPosNk2251IsModified();
    part_short_pos_nk225_2 = p_row.getPartShortPosNk2252();
    part_short_pos_nk225_2_is_set = p_row.getPartShortPosNk2252IsSet();
    part_short_pos_nk225_2_is_modified = p_row.getPartShortPosNk2252IsModified();
    bull_quantity_nk225_mini_0 = p_row.getBullQuantityNk225Mini0();
    bull_quantity_nk225_mini_0_is_set = p_row.getBullQuantityNk225Mini0IsSet();
    bull_quantity_nk225_mini_0_is_modified = p_row.getBullQuantityNk225Mini0IsModified();
    bull_quantity_nk225_mini_1 = p_row.getBullQuantityNk225Mini1();
    bull_quantity_nk225_mini_1_is_set = p_row.getBullQuantityNk225Mini1IsSet();
    bull_quantity_nk225_mini_1_is_modified = p_row.getBullQuantityNk225Mini1IsModified();
    bull_quantity_nk225_mini_2 = p_row.getBullQuantityNk225Mini2();
    bull_quantity_nk225_mini_2_is_set = p_row.getBullQuantityNk225Mini2IsSet();
    bull_quantity_nk225_mini_2_is_modified = p_row.getBullQuantityNk225Mini2IsModified();
    bear_quantity_nk225_mini_0 = p_row.getBearQuantityNk225Mini0();
    bear_quantity_nk225_mini_0_is_set = p_row.getBearQuantityNk225Mini0IsSet();
    bear_quantity_nk225_mini_0_is_modified = p_row.getBearQuantityNk225Mini0IsModified();
    bear_quantity_nk225_mini_1 = p_row.getBearQuantityNk225Mini1();
    bear_quantity_nk225_mini_1_is_set = p_row.getBearQuantityNk225Mini1IsSet();
    bear_quantity_nk225_mini_1_is_modified = p_row.getBearQuantityNk225Mini1IsModified();
    bear_quantity_nk225_mini_2 = p_row.getBearQuantityNk225Mini2();
    bear_quantity_nk225_mini_2_is_set = p_row.getBearQuantityNk225Mini2IsSet();
    bear_quantity_nk225_mini_2_is_modified = p_row.getBearQuantityNk225Mini2IsModified();
    long_pos_nk225_mini_0 = p_row.getLongPosNk225Mini0();
    long_pos_nk225_mini_0_is_set = p_row.getLongPosNk225Mini0IsSet();
    long_pos_nk225_mini_0_is_modified = p_row.getLongPosNk225Mini0IsModified();
    long_pos_nk225_mini_1 = p_row.getLongPosNk225Mini1();
    long_pos_nk225_mini_1_is_set = p_row.getLongPosNk225Mini1IsSet();
    long_pos_nk225_mini_1_is_modified = p_row.getLongPosNk225Mini1IsModified();
    long_pos_nk225_mini_2 = p_row.getLongPosNk225Mini2();
    long_pos_nk225_mini_2_is_set = p_row.getLongPosNk225Mini2IsSet();
    long_pos_nk225_mini_2_is_modified = p_row.getLongPosNk225Mini2IsModified();
    part_long_pos_nk225_mini_0 = p_row.getPartLongPosNk225Mini0();
    part_long_pos_nk225_mini_0_is_set = p_row.getPartLongPosNk225Mini0IsSet();
    part_long_pos_nk225_mini_0_is_modified = p_row.getPartLongPosNk225Mini0IsModified();
    part_long_pos_nk225_mini_1 = p_row.getPartLongPosNk225Mini1();
    part_long_pos_nk225_mini_1_is_set = p_row.getPartLongPosNk225Mini1IsSet();
    part_long_pos_nk225_mini_1_is_modified = p_row.getPartLongPosNk225Mini1IsModified();
    part_long_pos_nk225_mini_2 = p_row.getPartLongPosNk225Mini2();
    part_long_pos_nk225_mini_2_is_set = p_row.getPartLongPosNk225Mini2IsSet();
    part_long_pos_nk225_mini_2_is_modified = p_row.getPartLongPosNk225Mini2IsModified();
    short_pos_nk225_mini_0 = p_row.getShortPosNk225Mini0();
    short_pos_nk225_mini_0_is_set = p_row.getShortPosNk225Mini0IsSet();
    short_pos_nk225_mini_0_is_modified = p_row.getShortPosNk225Mini0IsModified();
    short_pos_nk225_mini_1 = p_row.getShortPosNk225Mini1();
    short_pos_nk225_mini_1_is_set = p_row.getShortPosNk225Mini1IsSet();
    short_pos_nk225_mini_1_is_modified = p_row.getShortPosNk225Mini1IsModified();
    short_pos_nk225_mini_2 = p_row.getShortPosNk225Mini2();
    short_pos_nk225_mini_2_is_set = p_row.getShortPosNk225Mini2IsSet();
    short_pos_nk225_mini_2_is_modified = p_row.getShortPosNk225Mini2IsModified();
    part_short_pos_nk225_mini_0 = p_row.getPartShortPosNk225Mini0();
    part_short_pos_nk225_mini_0_is_set = p_row.getPartShortPosNk225Mini0IsSet();
    part_short_pos_nk225_mini_0_is_modified = p_row.getPartShortPosNk225Mini0IsModified();
    part_short_pos_nk225_mini_1 = p_row.getPartShortPosNk225Mini1();
    part_short_pos_nk225_mini_1_is_set = p_row.getPartShortPosNk225Mini1IsSet();
    part_short_pos_nk225_mini_1_is_modified = p_row.getPartShortPosNk225Mini1IsModified();
    part_short_pos_nk225_mini_2 = p_row.getPartShortPosNk225Mini2();
    part_short_pos_nk225_mini_2_is_set = p_row.getPartShortPosNk225Mini2IsSet();
    part_short_pos_nk225_mini_2_is_modified = p_row.getPartShortPosNk225Mini2IsModified();
    buy_contract_amt_0 = p_row.getBuyContractAmt0();
    buy_contract_amt_0_is_set = p_row.getBuyContractAmt0IsSet();
    buy_contract_amt_0_is_modified = p_row.getBuyContractAmt0IsModified();
    buy_contract_amt_1 = p_row.getBuyContractAmt1();
    buy_contract_amt_1_is_set = p_row.getBuyContractAmt1IsSet();
    buy_contract_amt_1_is_modified = p_row.getBuyContractAmt1IsModified();
    buy_contract_amt_2 = p_row.getBuyContractAmt2();
    buy_contract_amt_2_is_set = p_row.getBuyContractAmt2IsSet();
    buy_contract_amt_2_is_modified = p_row.getBuyContractAmt2IsModified();
    buy_contract_amt_f0 = p_row.getBuyContractAmtF0();
    buy_contract_amt_f0_is_set = p_row.getBuyContractAmtF0IsSet();
    buy_contract_amt_f0_is_modified = p_row.getBuyContractAmtF0IsModified();
    buy_contract_amt_f1 = p_row.getBuyContractAmtF1();
    buy_contract_amt_f1_is_set = p_row.getBuyContractAmtF1IsSet();
    buy_contract_amt_f1_is_modified = p_row.getBuyContractAmtF1IsModified();
    buy_contract_amt_f2 = p_row.getBuyContractAmtF2();
    buy_contract_amt_f2_is_set = p_row.getBuyContractAmtF2IsSet();
    buy_contract_amt_f2_is_modified = p_row.getBuyContractAmtF2IsModified();
    sell_contract_amt_0 = p_row.getSellContractAmt0();
    sell_contract_amt_0_is_set = p_row.getSellContractAmt0IsSet();
    sell_contract_amt_0_is_modified = p_row.getSellContractAmt0IsModified();
    sell_contract_amt_1 = p_row.getSellContractAmt1();
    sell_contract_amt_1_is_set = p_row.getSellContractAmt1IsSet();
    sell_contract_amt_1_is_modified = p_row.getSellContractAmt1IsModified();
    sell_contract_amt_2 = p_row.getSellContractAmt2();
    sell_contract_amt_2_is_set = p_row.getSellContractAmt2IsSet();
    sell_contract_amt_2_is_modified = p_row.getSellContractAmt2IsModified();
    sell_contract_amt_f0 = p_row.getSellContractAmtF0();
    sell_contract_amt_f0_is_set = p_row.getSellContractAmtF0IsSet();
    sell_contract_amt_f0_is_modified = p_row.getSellContractAmtF0IsModified();
    sell_contract_amt_f1 = p_row.getSellContractAmtF1();
    sell_contract_amt_f1_is_set = p_row.getSellContractAmtF1IsSet();
    sell_contract_amt_f1_is_modified = p_row.getSellContractAmtF1IsModified();
    sell_contract_amt_f2 = p_row.getSellContractAmtF2();
    sell_contract_amt_f2_is_set = p_row.getSellContractAmtF2IsSet();
    sell_contract_amt_f2_is_modified = p_row.getSellContractAmtF2IsModified();
    non_pay_amt = p_row.getNonPayAmt();
    non_pay_amt_is_set = p_row.getNonPayAmtIsSet();
    non_pay_amt_is_modified = p_row.getNonPayAmtIsModified();
    today_claim_amt = p_row.getTodayClaimAmt();
    today_claim_amt_is_set = p_row.getTodayClaimAmtIsSet();
    today_claim_amt_is_modified = p_row.getTodayClaimAmtIsModified();
    tomorrow_claim_amt = p_row.getTomorrowClaimAmt();
    tomorrow_claim_amt_is_set = p_row.getTomorrowClaimAmtIsSet();
    tomorrow_claim_amt_is_modified = p_row.getTomorrowClaimAmtIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    ifo_deposit_insufficient_flag = p_row.getIfoDepositInsufficientFlag();
    ifo_deposit_insufficient_flag_is_set = p_row.getIfoDepositInsufficientFlagIsSet();
    ifo_deposit_insufficient_flag_is_modified = p_row.getIfoDepositInsufficientFlagIsModified();
    ifo_deposit_index_nk225 = p_row.getIfoDepositIndexNk225();
    ifo_deposit_index_nk225_is_set = p_row.getIfoDepositIndexNk225IsSet();
    ifo_deposit_index_nk225_is_modified = p_row.getIfoDepositIndexNk225IsModified();
    ifo_deposit_index_nk225_mini = p_row.getIfoDepositIndexNk225Mini();
    ifo_deposit_index_nk225_mini_is_set = p_row.getIfoDepositIndexNk225MiniIsSet();
    ifo_deposit_index_nk225_mini_is_modified = p_row.getIfoDepositIndexNk225MiniIsModified();
    after_tomorrow_claim_amt = p_row.getAfterTomorrowClaimAmt();
    after_tomorrow_claim_amt_is_set = p_row.getAfterTomorrowClaimAmtIsSet();
    after_tomorrow_claim_amt_is_modified = p_row.getAfterTomorrowClaimAmtIsModified();
    tomorrow_claim_amt_evening = p_row.getTomorrowClaimAmtEvening();
    tomorrow_claim_amt_evening_is_set = p_row.getTomorrowClaimAmtEveningIsSet();
    tomorrow_claim_amt_evening_is_modified = p_row.getTomorrowClaimAmtEveningIsModified();
    if ( !p_row.getStatusIsNull() )
      status = new Integer( p_row.getStatus() );
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    error_message = p_row.getErrorMessage();
    error_message_is_set = p_row.getErrorMessageIsSet();
    error_message_is_modified = p_row.getErrorMessageIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      base_date_is_set = true;
      base_date_is_modified = true;
      balance_0_is_set = true;
      balance_0_is_modified = true;
      balance_1_is_set = true;
      balance_1_is_modified = true;
      balance_2_is_set = true;
      balance_2_is_modified = true;
      ifo_deposit_balance_0_is_set = true;
      ifo_deposit_balance_0_is_modified = true;
      ifo_deposit_balance_1_is_set = true;
      ifo_deposit_balance_1_is_modified = true;
      ifo_deposit_balance_2_is_set = true;
      ifo_deposit_balance_2_is_modified = true;
      ifo_deposit_balance_f0_is_set = true;
      ifo_deposit_balance_f0_is_modified = true;
      ifo_deposit_balance_f1_is_set = true;
      ifo_deposit_balance_f1_is_modified = true;
      ifo_deposit_balance_f2_is_set = true;
      ifo_deposit_balance_f2_is_modified = true;
      today_transfer_amt_0_is_set = true;
      today_transfer_amt_0_is_modified = true;
      today_transfer_amt_1_is_set = true;
      today_transfer_amt_1_is_modified = true;
      today_transfer_amt_2_is_set = true;
      today_transfer_amt_2_is_modified = true;
      today_fut_settle_profit_0_is_set = true;
      today_fut_settle_profit_0_is_modified = true;
      today_fut_settle_profit_1_is_set = true;
      today_fut_settle_profit_1_is_modified = true;
      today_fut_settle_profit_2_is_set = true;
      today_fut_settle_profit_2_is_modified = true;
      today_op_net_amt_0_is_set = true;
      today_op_net_amt_0_is_modified = true;
      today_op_net_amt_1_is_set = true;
      today_op_net_amt_1_is_modified = true;
      today_op_net_amt_2_is_set = true;
      today_op_net_amt_2_is_modified = true;
      fut_eval_profit_0_is_set = true;
      fut_eval_profit_0_is_modified = true;
      fut_eval_profit_1_is_set = true;
      fut_eval_profit_1_is_modified = true;
      fut_eval_profit_2_is_set = true;
      fut_eval_profit_2_is_modified = true;
      long_fut_eval_profit_0_is_set = true;
      long_fut_eval_profit_0_is_modified = true;
      long_fut_eval_profit_1_is_set = true;
      long_fut_eval_profit_1_is_modified = true;
      long_fut_eval_profit_2_is_set = true;
      long_fut_eval_profit_2_is_modified = true;
      short_fut_eval_profit_0_is_set = true;
      short_fut_eval_profit_0_is_modified = true;
      short_fut_eval_profit_1_is_set = true;
      short_fut_eval_profit_1_is_modified = true;
      short_fut_eval_profit_2_is_set = true;
      short_fut_eval_profit_2_is_modified = true;
      acceptance_ifo_deposit_bal_0_is_set = true;
      acceptance_ifo_deposit_bal_0_is_modified = true;
      acceptance_ifo_deposit_bal_1_is_set = true;
      acceptance_ifo_deposit_bal_1_is_modified = true;
      acceptance_ifo_deposit_bal_2_is_set = true;
      acceptance_ifo_deposit_bal_2_is_modified = true;
      acceptance_ifo_deposit_bal_f0_is_set = true;
      acceptance_ifo_deposit_bal_f0_is_modified = true;
      acceptance_ifo_deposit_bal_f1_is_set = true;
      acceptance_ifo_deposit_bal_f1_is_modified = true;
      acceptance_ifo_deposit_bal_f2_is_set = true;
      acceptance_ifo_deposit_bal_f2_is_modified = true;
      net_op_value_total_amt_0_is_set = true;
      net_op_value_total_amt_0_is_modified = true;
      net_op_value_total_amt_1_is_set = true;
      net_op_value_total_amt_1_is_modified = true;
      net_op_value_total_amt_2_is_set = true;
      net_op_value_total_amt_2_is_modified = true;
      net_op_value_total_amt_f0_is_set = true;
      net_op_value_total_amt_f0_is_modified = true;
      net_op_value_total_amt_f1_is_set = true;
      net_op_value_total_amt_f1_is_modified = true;
      net_op_value_total_amt_f2_is_set = true;
      net_op_value_total_amt_f2_is_modified = true;
      ifo_deposit_necessary_amt_0_is_set = true;
      ifo_deposit_necessary_amt_0_is_modified = true;
      ifo_deposit_necessary_amt_1_is_set = true;
      ifo_deposit_necessary_amt_1_is_modified = true;
      ifo_deposit_necessary_amt_2_is_set = true;
      ifo_deposit_necessary_amt_2_is_modified = true;
      ifo_deposit_necessary_amt_f0_is_set = true;
      ifo_deposit_necessary_amt_f0_is_modified = true;
      ifo_deposit_necessary_amt_f1_is_set = true;
      ifo_deposit_necessary_amt_f1_is_modified = true;
      ifo_deposit_necessary_amt_f2_is_set = true;
      ifo_deposit_necessary_amt_f2_is_modified = true;
      ifo_deposit_power_0_is_set = true;
      ifo_deposit_power_0_is_modified = true;
      ifo_deposit_power_1_is_set = true;
      ifo_deposit_power_1_is_modified = true;
      ifo_deposit_power_2_is_set = true;
      ifo_deposit_power_2_is_modified = true;
      ifo_deposit_transfer_power_0_is_set = true;
      ifo_deposit_transfer_power_0_is_modified = true;
      ifo_deposit_transfer_power_1_is_set = true;
      ifo_deposit_transfer_power_1_is_modified = true;
      ifo_deposit_transfer_power_2_is_set = true;
      ifo_deposit_transfer_power_2_is_modified = true;
      bull_quantity_nk225_0_is_set = true;
      bull_quantity_nk225_0_is_modified = true;
      bull_quantity_nk225_1_is_set = true;
      bull_quantity_nk225_1_is_modified = true;
      bull_quantity_nk225_2_is_set = true;
      bull_quantity_nk225_2_is_modified = true;
      bear_quantity_nk225_0_is_set = true;
      bear_quantity_nk225_0_is_modified = true;
      bear_quantity_nk225_1_is_set = true;
      bear_quantity_nk225_1_is_modified = true;
      bear_quantity_nk225_2_is_set = true;
      bear_quantity_nk225_2_is_modified = true;
      long_pos_nk225_0_is_set = true;
      long_pos_nk225_0_is_modified = true;
      long_pos_nk225_1_is_set = true;
      long_pos_nk225_1_is_modified = true;
      long_pos_nk225_2_is_set = true;
      long_pos_nk225_2_is_modified = true;
      part_long_pos_nk225_0_is_set = true;
      part_long_pos_nk225_0_is_modified = true;
      part_long_pos_nk225_1_is_set = true;
      part_long_pos_nk225_1_is_modified = true;
      part_long_pos_nk225_2_is_set = true;
      part_long_pos_nk225_2_is_modified = true;
      short_pos_nk225_0_is_set = true;
      short_pos_nk225_0_is_modified = true;
      short_pos_nk225_1_is_set = true;
      short_pos_nk225_1_is_modified = true;
      short_pos_nk225_2_is_set = true;
      short_pos_nk225_2_is_modified = true;
      part_short_pos_nk225_0_is_set = true;
      part_short_pos_nk225_0_is_modified = true;
      part_short_pos_nk225_1_is_set = true;
      part_short_pos_nk225_1_is_modified = true;
      part_short_pos_nk225_2_is_set = true;
      part_short_pos_nk225_2_is_modified = true;
      bull_quantity_nk225_mini_0_is_set = true;
      bull_quantity_nk225_mini_0_is_modified = true;
      bull_quantity_nk225_mini_1_is_set = true;
      bull_quantity_nk225_mini_1_is_modified = true;
      bull_quantity_nk225_mini_2_is_set = true;
      bull_quantity_nk225_mini_2_is_modified = true;
      bear_quantity_nk225_mini_0_is_set = true;
      bear_quantity_nk225_mini_0_is_modified = true;
      bear_quantity_nk225_mini_1_is_set = true;
      bear_quantity_nk225_mini_1_is_modified = true;
      bear_quantity_nk225_mini_2_is_set = true;
      bear_quantity_nk225_mini_2_is_modified = true;
      long_pos_nk225_mini_0_is_set = true;
      long_pos_nk225_mini_0_is_modified = true;
      long_pos_nk225_mini_1_is_set = true;
      long_pos_nk225_mini_1_is_modified = true;
      long_pos_nk225_mini_2_is_set = true;
      long_pos_nk225_mini_2_is_modified = true;
      part_long_pos_nk225_mini_0_is_set = true;
      part_long_pos_nk225_mini_0_is_modified = true;
      part_long_pos_nk225_mini_1_is_set = true;
      part_long_pos_nk225_mini_1_is_modified = true;
      part_long_pos_nk225_mini_2_is_set = true;
      part_long_pos_nk225_mini_2_is_modified = true;
      short_pos_nk225_mini_0_is_set = true;
      short_pos_nk225_mini_0_is_modified = true;
      short_pos_nk225_mini_1_is_set = true;
      short_pos_nk225_mini_1_is_modified = true;
      short_pos_nk225_mini_2_is_set = true;
      short_pos_nk225_mini_2_is_modified = true;
      part_short_pos_nk225_mini_0_is_set = true;
      part_short_pos_nk225_mini_0_is_modified = true;
      part_short_pos_nk225_mini_1_is_set = true;
      part_short_pos_nk225_mini_1_is_modified = true;
      part_short_pos_nk225_mini_2_is_set = true;
      part_short_pos_nk225_mini_2_is_modified = true;
      buy_contract_amt_0_is_set = true;
      buy_contract_amt_0_is_modified = true;
      buy_contract_amt_1_is_set = true;
      buy_contract_amt_1_is_modified = true;
      buy_contract_amt_2_is_set = true;
      buy_contract_amt_2_is_modified = true;
      buy_contract_amt_f0_is_set = true;
      buy_contract_amt_f0_is_modified = true;
      buy_contract_amt_f1_is_set = true;
      buy_contract_amt_f1_is_modified = true;
      buy_contract_amt_f2_is_set = true;
      buy_contract_amt_f2_is_modified = true;
      sell_contract_amt_0_is_set = true;
      sell_contract_amt_0_is_modified = true;
      sell_contract_amt_1_is_set = true;
      sell_contract_amt_1_is_modified = true;
      sell_contract_amt_2_is_set = true;
      sell_contract_amt_2_is_modified = true;
      sell_contract_amt_f0_is_set = true;
      sell_contract_amt_f0_is_modified = true;
      sell_contract_amt_f1_is_set = true;
      sell_contract_amt_f1_is_modified = true;
      sell_contract_amt_f2_is_set = true;
      sell_contract_amt_f2_is_modified = true;
      non_pay_amt_is_set = true;
      non_pay_amt_is_modified = true;
      today_claim_amt_is_set = true;
      today_claim_amt_is_modified = true;
      tomorrow_claim_amt_is_set = true;
      tomorrow_claim_amt_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      ifo_deposit_insufficient_flag_is_set = true;
      ifo_deposit_insufficient_flag_is_modified = true;
      ifo_deposit_index_nk225_is_set = true;
      ifo_deposit_index_nk225_is_modified = true;
      ifo_deposit_index_nk225_mini_is_set = true;
      ifo_deposit_index_nk225_mini_is_modified = true;
      after_tomorrow_claim_amt_is_set = true;
      after_tomorrow_claim_amt_is_modified = true;
      tomorrow_claim_amt_evening_is_set = true;
      tomorrow_claim_amt_evening_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      error_message_is_set = true;
      error_message_is_modified = true;
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
    if ( !( other instanceof IfoDepositCalcResultRow ) )
       return false;
    return fieldsEqual( (IfoDepositCalcResultRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のIfoDepositCalcResultRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( IfoDepositCalcResultRow row )
  {
    if ( deposit_info_id != row.getDepositInfoId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( base_date == null ) {
      if ( row.getBaseDate() != null )
        return false;
    } else if ( !base_date.equals( row.getBaseDate() ) ) {
        return false;
    }
    if ( balance_0 == null ) {
      if ( row.getBalance0() != null )
        return false;
    } else if ( !balance_0.equals( row.getBalance0() ) ) {
        return false;
    }
    if ( balance_1 == null ) {
      if ( row.getBalance1() != null )
        return false;
    } else if ( !balance_1.equals( row.getBalance1() ) ) {
        return false;
    }
    if ( balance_2 == null ) {
      if ( row.getBalance2() != null )
        return false;
    } else if ( !balance_2.equals( row.getBalance2() ) ) {
        return false;
    }
    if ( ifo_deposit_balance_0 == null ) {
      if ( row.getIfoDepositBalance0() != null )
        return false;
    } else if ( !ifo_deposit_balance_0.equals( row.getIfoDepositBalance0() ) ) {
        return false;
    }
    if ( ifo_deposit_balance_1 == null ) {
      if ( row.getIfoDepositBalance1() != null )
        return false;
    } else if ( !ifo_deposit_balance_1.equals( row.getIfoDepositBalance1() ) ) {
        return false;
    }
    if ( ifo_deposit_balance_2 == null ) {
      if ( row.getIfoDepositBalance2() != null )
        return false;
    } else if ( !ifo_deposit_balance_2.equals( row.getIfoDepositBalance2() ) ) {
        return false;
    }
    if ( ifo_deposit_balance_f0 == null ) {
      if ( row.getIfoDepositBalanceF0() != null )
        return false;
    } else if ( !ifo_deposit_balance_f0.equals( row.getIfoDepositBalanceF0() ) ) {
        return false;
    }
    if ( ifo_deposit_balance_f1 == null ) {
      if ( row.getIfoDepositBalanceF1() != null )
        return false;
    } else if ( !ifo_deposit_balance_f1.equals( row.getIfoDepositBalanceF1() ) ) {
        return false;
    }
    if ( ifo_deposit_balance_f2 == null ) {
      if ( row.getIfoDepositBalanceF2() != null )
        return false;
    } else if ( !ifo_deposit_balance_f2.equals( row.getIfoDepositBalanceF2() ) ) {
        return false;
    }
    if ( today_transfer_amt_0 == null ) {
      if ( row.getTodayTransferAmt0() != null )
        return false;
    } else if ( !today_transfer_amt_0.equals( row.getTodayTransferAmt0() ) ) {
        return false;
    }
    if ( today_transfer_amt_1 == null ) {
      if ( row.getTodayTransferAmt1() != null )
        return false;
    } else if ( !today_transfer_amt_1.equals( row.getTodayTransferAmt1() ) ) {
        return false;
    }
    if ( today_transfer_amt_2 == null ) {
      if ( row.getTodayTransferAmt2() != null )
        return false;
    } else if ( !today_transfer_amt_2.equals( row.getTodayTransferAmt2() ) ) {
        return false;
    }
    if ( today_fut_settle_profit_0 == null ) {
      if ( row.getTodayFutSettleProfit0() != null )
        return false;
    } else if ( !today_fut_settle_profit_0.equals( row.getTodayFutSettleProfit0() ) ) {
        return false;
    }
    if ( today_fut_settle_profit_1 == null ) {
      if ( row.getTodayFutSettleProfit1() != null )
        return false;
    } else if ( !today_fut_settle_profit_1.equals( row.getTodayFutSettleProfit1() ) ) {
        return false;
    }
    if ( today_fut_settle_profit_2 == null ) {
      if ( row.getTodayFutSettleProfit2() != null )
        return false;
    } else if ( !today_fut_settle_profit_2.equals( row.getTodayFutSettleProfit2() ) ) {
        return false;
    }
    if ( today_op_net_amt_0 == null ) {
      if ( row.getTodayOpNetAmt0() != null )
        return false;
    } else if ( !today_op_net_amt_0.equals( row.getTodayOpNetAmt0() ) ) {
        return false;
    }
    if ( today_op_net_amt_1 == null ) {
      if ( row.getTodayOpNetAmt1() != null )
        return false;
    } else if ( !today_op_net_amt_1.equals( row.getTodayOpNetAmt1() ) ) {
        return false;
    }
    if ( today_op_net_amt_2 == null ) {
      if ( row.getTodayOpNetAmt2() != null )
        return false;
    } else if ( !today_op_net_amt_2.equals( row.getTodayOpNetAmt2() ) ) {
        return false;
    }
    if ( fut_eval_profit_0 == null ) {
      if ( row.getFutEvalProfit0() != null )
        return false;
    } else if ( !fut_eval_profit_0.equals( row.getFutEvalProfit0() ) ) {
        return false;
    }
    if ( fut_eval_profit_1 == null ) {
      if ( row.getFutEvalProfit1() != null )
        return false;
    } else if ( !fut_eval_profit_1.equals( row.getFutEvalProfit1() ) ) {
        return false;
    }
    if ( fut_eval_profit_2 == null ) {
      if ( row.getFutEvalProfit2() != null )
        return false;
    } else if ( !fut_eval_profit_2.equals( row.getFutEvalProfit2() ) ) {
        return false;
    }
    if ( long_fut_eval_profit_0 == null ) {
      if ( row.getLongFutEvalProfit0() != null )
        return false;
    } else if ( !long_fut_eval_profit_0.equals( row.getLongFutEvalProfit0() ) ) {
        return false;
    }
    if ( long_fut_eval_profit_1 == null ) {
      if ( row.getLongFutEvalProfit1() != null )
        return false;
    } else if ( !long_fut_eval_profit_1.equals( row.getLongFutEvalProfit1() ) ) {
        return false;
    }
    if ( long_fut_eval_profit_2 == null ) {
      if ( row.getLongFutEvalProfit2() != null )
        return false;
    } else if ( !long_fut_eval_profit_2.equals( row.getLongFutEvalProfit2() ) ) {
        return false;
    }
    if ( short_fut_eval_profit_0 == null ) {
      if ( row.getShortFutEvalProfit0() != null )
        return false;
    } else if ( !short_fut_eval_profit_0.equals( row.getShortFutEvalProfit0() ) ) {
        return false;
    }
    if ( short_fut_eval_profit_1 == null ) {
      if ( row.getShortFutEvalProfit1() != null )
        return false;
    } else if ( !short_fut_eval_profit_1.equals( row.getShortFutEvalProfit1() ) ) {
        return false;
    }
    if ( short_fut_eval_profit_2 == null ) {
      if ( row.getShortFutEvalProfit2() != null )
        return false;
    } else if ( !short_fut_eval_profit_2.equals( row.getShortFutEvalProfit2() ) ) {
        return false;
    }
    if ( acceptance_ifo_deposit_bal_0 == null ) {
      if ( row.getAcceptanceIfoDepositBal0() != null )
        return false;
    } else if ( !acceptance_ifo_deposit_bal_0.equals( row.getAcceptanceIfoDepositBal0() ) ) {
        return false;
    }
    if ( acceptance_ifo_deposit_bal_1 == null ) {
      if ( row.getAcceptanceIfoDepositBal1() != null )
        return false;
    } else if ( !acceptance_ifo_deposit_bal_1.equals( row.getAcceptanceIfoDepositBal1() ) ) {
        return false;
    }
    if ( acceptance_ifo_deposit_bal_2 == null ) {
      if ( row.getAcceptanceIfoDepositBal2() != null )
        return false;
    } else if ( !acceptance_ifo_deposit_bal_2.equals( row.getAcceptanceIfoDepositBal2() ) ) {
        return false;
    }
    if ( acceptance_ifo_deposit_bal_f0 == null ) {
      if ( row.getAcceptanceIfoDepositBalF0() != null )
        return false;
    } else if ( !acceptance_ifo_deposit_bal_f0.equals( row.getAcceptanceIfoDepositBalF0() ) ) {
        return false;
    }
    if ( acceptance_ifo_deposit_bal_f1 == null ) {
      if ( row.getAcceptanceIfoDepositBalF1() != null )
        return false;
    } else if ( !acceptance_ifo_deposit_bal_f1.equals( row.getAcceptanceIfoDepositBalF1() ) ) {
        return false;
    }
    if ( acceptance_ifo_deposit_bal_f2 == null ) {
      if ( row.getAcceptanceIfoDepositBalF2() != null )
        return false;
    } else if ( !acceptance_ifo_deposit_bal_f2.equals( row.getAcceptanceIfoDepositBalF2() ) ) {
        return false;
    }
    if ( net_op_value_total_amt_0 == null ) {
      if ( row.getNetOpValueTotalAmt0() != null )
        return false;
    } else if ( !net_op_value_total_amt_0.equals( row.getNetOpValueTotalAmt0() ) ) {
        return false;
    }
    if ( net_op_value_total_amt_1 == null ) {
      if ( row.getNetOpValueTotalAmt1() != null )
        return false;
    } else if ( !net_op_value_total_amt_1.equals( row.getNetOpValueTotalAmt1() ) ) {
        return false;
    }
    if ( net_op_value_total_amt_2 == null ) {
      if ( row.getNetOpValueTotalAmt2() != null )
        return false;
    } else if ( !net_op_value_total_amt_2.equals( row.getNetOpValueTotalAmt2() ) ) {
        return false;
    }
    if ( net_op_value_total_amt_f0 == null ) {
      if ( row.getNetOpValueTotalAmtF0() != null )
        return false;
    } else if ( !net_op_value_total_amt_f0.equals( row.getNetOpValueTotalAmtF0() ) ) {
        return false;
    }
    if ( net_op_value_total_amt_f1 == null ) {
      if ( row.getNetOpValueTotalAmtF1() != null )
        return false;
    } else if ( !net_op_value_total_amt_f1.equals( row.getNetOpValueTotalAmtF1() ) ) {
        return false;
    }
    if ( net_op_value_total_amt_f2 == null ) {
      if ( row.getNetOpValueTotalAmtF2() != null )
        return false;
    } else if ( !net_op_value_total_amt_f2.equals( row.getNetOpValueTotalAmtF2() ) ) {
        return false;
    }
    if ( ifo_deposit_necessary_amt_0 == null ) {
      if ( row.getIfoDepositNecessaryAmt0() != null )
        return false;
    } else if ( !ifo_deposit_necessary_amt_0.equals( row.getIfoDepositNecessaryAmt0() ) ) {
        return false;
    }
    if ( ifo_deposit_necessary_amt_1 == null ) {
      if ( row.getIfoDepositNecessaryAmt1() != null )
        return false;
    } else if ( !ifo_deposit_necessary_amt_1.equals( row.getIfoDepositNecessaryAmt1() ) ) {
        return false;
    }
    if ( ifo_deposit_necessary_amt_2 == null ) {
      if ( row.getIfoDepositNecessaryAmt2() != null )
        return false;
    } else if ( !ifo_deposit_necessary_amt_2.equals( row.getIfoDepositNecessaryAmt2() ) ) {
        return false;
    }
    if ( ifo_deposit_necessary_amt_f0 == null ) {
      if ( row.getIfoDepositNecessaryAmtF0() != null )
        return false;
    } else if ( !ifo_deposit_necessary_amt_f0.equals( row.getIfoDepositNecessaryAmtF0() ) ) {
        return false;
    }
    if ( ifo_deposit_necessary_amt_f1 == null ) {
      if ( row.getIfoDepositNecessaryAmtF1() != null )
        return false;
    } else if ( !ifo_deposit_necessary_amt_f1.equals( row.getIfoDepositNecessaryAmtF1() ) ) {
        return false;
    }
    if ( ifo_deposit_necessary_amt_f2 == null ) {
      if ( row.getIfoDepositNecessaryAmtF2() != null )
        return false;
    } else if ( !ifo_deposit_necessary_amt_f2.equals( row.getIfoDepositNecessaryAmtF2() ) ) {
        return false;
    }
    if ( ifo_deposit_power_0 == null ) {
      if ( row.getIfoDepositPower0() != null )
        return false;
    } else if ( !ifo_deposit_power_0.equals( row.getIfoDepositPower0() ) ) {
        return false;
    }
    if ( ifo_deposit_power_1 == null ) {
      if ( row.getIfoDepositPower1() != null )
        return false;
    } else if ( !ifo_deposit_power_1.equals( row.getIfoDepositPower1() ) ) {
        return false;
    }
    if ( ifo_deposit_power_2 == null ) {
      if ( row.getIfoDepositPower2() != null )
        return false;
    } else if ( !ifo_deposit_power_2.equals( row.getIfoDepositPower2() ) ) {
        return false;
    }
    if ( ifo_deposit_transfer_power_0 == null ) {
      if ( row.getIfoDepositTransferPower0() != null )
        return false;
    } else if ( !ifo_deposit_transfer_power_0.equals( row.getIfoDepositTransferPower0() ) ) {
        return false;
    }
    if ( ifo_deposit_transfer_power_1 == null ) {
      if ( row.getIfoDepositTransferPower1() != null )
        return false;
    } else if ( !ifo_deposit_transfer_power_1.equals( row.getIfoDepositTransferPower1() ) ) {
        return false;
    }
    if ( ifo_deposit_transfer_power_2 == null ) {
      if ( row.getIfoDepositTransferPower2() != null )
        return false;
    } else if ( !ifo_deposit_transfer_power_2.equals( row.getIfoDepositTransferPower2() ) ) {
        return false;
    }
    if ( bull_quantity_nk225_0 == null ) {
      if ( row.getBullQuantityNk2250() != null )
        return false;
    } else if ( !bull_quantity_nk225_0.equals( row.getBullQuantityNk2250() ) ) {
        return false;
    }
    if ( bull_quantity_nk225_1 == null ) {
      if ( row.getBullQuantityNk2251() != null )
        return false;
    } else if ( !bull_quantity_nk225_1.equals( row.getBullQuantityNk2251() ) ) {
        return false;
    }
    if ( bull_quantity_nk225_2 == null ) {
      if ( row.getBullQuantityNk2252() != null )
        return false;
    } else if ( !bull_quantity_nk225_2.equals( row.getBullQuantityNk2252() ) ) {
        return false;
    }
    if ( bear_quantity_nk225_0 == null ) {
      if ( row.getBearQuantityNk2250() != null )
        return false;
    } else if ( !bear_quantity_nk225_0.equals( row.getBearQuantityNk2250() ) ) {
        return false;
    }
    if ( bear_quantity_nk225_1 == null ) {
      if ( row.getBearQuantityNk2251() != null )
        return false;
    } else if ( !bear_quantity_nk225_1.equals( row.getBearQuantityNk2251() ) ) {
        return false;
    }
    if ( bear_quantity_nk225_2 == null ) {
      if ( row.getBearQuantityNk2252() != null )
        return false;
    } else if ( !bear_quantity_nk225_2.equals( row.getBearQuantityNk2252() ) ) {
        return false;
    }
    if ( long_pos_nk225_0 == null ) {
      if ( row.getLongPosNk2250() != null )
        return false;
    } else if ( !long_pos_nk225_0.equals( row.getLongPosNk2250() ) ) {
        return false;
    }
    if ( long_pos_nk225_1 == null ) {
      if ( row.getLongPosNk2251() != null )
        return false;
    } else if ( !long_pos_nk225_1.equals( row.getLongPosNk2251() ) ) {
        return false;
    }
    if ( long_pos_nk225_2 == null ) {
      if ( row.getLongPosNk2252() != null )
        return false;
    } else if ( !long_pos_nk225_2.equals( row.getLongPosNk2252() ) ) {
        return false;
    }
    if ( part_long_pos_nk225_0 == null ) {
      if ( row.getPartLongPosNk2250() != null )
        return false;
    } else if ( !part_long_pos_nk225_0.equals( row.getPartLongPosNk2250() ) ) {
        return false;
    }
    if ( part_long_pos_nk225_1 == null ) {
      if ( row.getPartLongPosNk2251() != null )
        return false;
    } else if ( !part_long_pos_nk225_1.equals( row.getPartLongPosNk2251() ) ) {
        return false;
    }
    if ( part_long_pos_nk225_2 == null ) {
      if ( row.getPartLongPosNk2252() != null )
        return false;
    } else if ( !part_long_pos_nk225_2.equals( row.getPartLongPosNk2252() ) ) {
        return false;
    }
    if ( short_pos_nk225_0 == null ) {
      if ( row.getShortPosNk2250() != null )
        return false;
    } else if ( !short_pos_nk225_0.equals( row.getShortPosNk2250() ) ) {
        return false;
    }
    if ( short_pos_nk225_1 == null ) {
      if ( row.getShortPosNk2251() != null )
        return false;
    } else if ( !short_pos_nk225_1.equals( row.getShortPosNk2251() ) ) {
        return false;
    }
    if ( short_pos_nk225_2 == null ) {
      if ( row.getShortPosNk2252() != null )
        return false;
    } else if ( !short_pos_nk225_2.equals( row.getShortPosNk2252() ) ) {
        return false;
    }
    if ( part_short_pos_nk225_0 == null ) {
      if ( row.getPartShortPosNk2250() != null )
        return false;
    } else if ( !part_short_pos_nk225_0.equals( row.getPartShortPosNk2250() ) ) {
        return false;
    }
    if ( part_short_pos_nk225_1 == null ) {
      if ( row.getPartShortPosNk2251() != null )
        return false;
    } else if ( !part_short_pos_nk225_1.equals( row.getPartShortPosNk2251() ) ) {
        return false;
    }
    if ( part_short_pos_nk225_2 == null ) {
      if ( row.getPartShortPosNk2252() != null )
        return false;
    } else if ( !part_short_pos_nk225_2.equals( row.getPartShortPosNk2252() ) ) {
        return false;
    }
    if ( bull_quantity_nk225_mini_0 == null ) {
      if ( row.getBullQuantityNk225Mini0() != null )
        return false;
    } else if ( !bull_quantity_nk225_mini_0.equals( row.getBullQuantityNk225Mini0() ) ) {
        return false;
    }
    if ( bull_quantity_nk225_mini_1 == null ) {
      if ( row.getBullQuantityNk225Mini1() != null )
        return false;
    } else if ( !bull_quantity_nk225_mini_1.equals( row.getBullQuantityNk225Mini1() ) ) {
        return false;
    }
    if ( bull_quantity_nk225_mini_2 == null ) {
      if ( row.getBullQuantityNk225Mini2() != null )
        return false;
    } else if ( !bull_quantity_nk225_mini_2.equals( row.getBullQuantityNk225Mini2() ) ) {
        return false;
    }
    if ( bear_quantity_nk225_mini_0 == null ) {
      if ( row.getBearQuantityNk225Mini0() != null )
        return false;
    } else if ( !bear_quantity_nk225_mini_0.equals( row.getBearQuantityNk225Mini0() ) ) {
        return false;
    }
    if ( bear_quantity_nk225_mini_1 == null ) {
      if ( row.getBearQuantityNk225Mini1() != null )
        return false;
    } else if ( !bear_quantity_nk225_mini_1.equals( row.getBearQuantityNk225Mini1() ) ) {
        return false;
    }
    if ( bear_quantity_nk225_mini_2 == null ) {
      if ( row.getBearQuantityNk225Mini2() != null )
        return false;
    } else if ( !bear_quantity_nk225_mini_2.equals( row.getBearQuantityNk225Mini2() ) ) {
        return false;
    }
    if ( long_pos_nk225_mini_0 == null ) {
      if ( row.getLongPosNk225Mini0() != null )
        return false;
    } else if ( !long_pos_nk225_mini_0.equals( row.getLongPosNk225Mini0() ) ) {
        return false;
    }
    if ( long_pos_nk225_mini_1 == null ) {
      if ( row.getLongPosNk225Mini1() != null )
        return false;
    } else if ( !long_pos_nk225_mini_1.equals( row.getLongPosNk225Mini1() ) ) {
        return false;
    }
    if ( long_pos_nk225_mini_2 == null ) {
      if ( row.getLongPosNk225Mini2() != null )
        return false;
    } else if ( !long_pos_nk225_mini_2.equals( row.getLongPosNk225Mini2() ) ) {
        return false;
    }
    if ( part_long_pos_nk225_mini_0 == null ) {
      if ( row.getPartLongPosNk225Mini0() != null )
        return false;
    } else if ( !part_long_pos_nk225_mini_0.equals( row.getPartLongPosNk225Mini0() ) ) {
        return false;
    }
    if ( part_long_pos_nk225_mini_1 == null ) {
      if ( row.getPartLongPosNk225Mini1() != null )
        return false;
    } else if ( !part_long_pos_nk225_mini_1.equals( row.getPartLongPosNk225Mini1() ) ) {
        return false;
    }
    if ( part_long_pos_nk225_mini_2 == null ) {
      if ( row.getPartLongPosNk225Mini2() != null )
        return false;
    } else if ( !part_long_pos_nk225_mini_2.equals( row.getPartLongPosNk225Mini2() ) ) {
        return false;
    }
    if ( short_pos_nk225_mini_0 == null ) {
      if ( row.getShortPosNk225Mini0() != null )
        return false;
    } else if ( !short_pos_nk225_mini_0.equals( row.getShortPosNk225Mini0() ) ) {
        return false;
    }
    if ( short_pos_nk225_mini_1 == null ) {
      if ( row.getShortPosNk225Mini1() != null )
        return false;
    } else if ( !short_pos_nk225_mini_1.equals( row.getShortPosNk225Mini1() ) ) {
        return false;
    }
    if ( short_pos_nk225_mini_2 == null ) {
      if ( row.getShortPosNk225Mini2() != null )
        return false;
    } else if ( !short_pos_nk225_mini_2.equals( row.getShortPosNk225Mini2() ) ) {
        return false;
    }
    if ( part_short_pos_nk225_mini_0 == null ) {
      if ( row.getPartShortPosNk225Mini0() != null )
        return false;
    } else if ( !part_short_pos_nk225_mini_0.equals( row.getPartShortPosNk225Mini0() ) ) {
        return false;
    }
    if ( part_short_pos_nk225_mini_1 == null ) {
      if ( row.getPartShortPosNk225Mini1() != null )
        return false;
    } else if ( !part_short_pos_nk225_mini_1.equals( row.getPartShortPosNk225Mini1() ) ) {
        return false;
    }
    if ( part_short_pos_nk225_mini_2 == null ) {
      if ( row.getPartShortPosNk225Mini2() != null )
        return false;
    } else if ( !part_short_pos_nk225_mini_2.equals( row.getPartShortPosNk225Mini2() ) ) {
        return false;
    }
    if ( buy_contract_amt_0 == null ) {
      if ( row.getBuyContractAmt0() != null )
        return false;
    } else if ( !buy_contract_amt_0.equals( row.getBuyContractAmt0() ) ) {
        return false;
    }
    if ( buy_contract_amt_1 == null ) {
      if ( row.getBuyContractAmt1() != null )
        return false;
    } else if ( !buy_contract_amt_1.equals( row.getBuyContractAmt1() ) ) {
        return false;
    }
    if ( buy_contract_amt_2 == null ) {
      if ( row.getBuyContractAmt2() != null )
        return false;
    } else if ( !buy_contract_amt_2.equals( row.getBuyContractAmt2() ) ) {
        return false;
    }
    if ( buy_contract_amt_f0 == null ) {
      if ( row.getBuyContractAmtF0() != null )
        return false;
    } else if ( !buy_contract_amt_f0.equals( row.getBuyContractAmtF0() ) ) {
        return false;
    }
    if ( buy_contract_amt_f1 == null ) {
      if ( row.getBuyContractAmtF1() != null )
        return false;
    } else if ( !buy_contract_amt_f1.equals( row.getBuyContractAmtF1() ) ) {
        return false;
    }
    if ( buy_contract_amt_f2 == null ) {
      if ( row.getBuyContractAmtF2() != null )
        return false;
    } else if ( !buy_contract_amt_f2.equals( row.getBuyContractAmtF2() ) ) {
        return false;
    }
    if ( sell_contract_amt_0 == null ) {
      if ( row.getSellContractAmt0() != null )
        return false;
    } else if ( !sell_contract_amt_0.equals( row.getSellContractAmt0() ) ) {
        return false;
    }
    if ( sell_contract_amt_1 == null ) {
      if ( row.getSellContractAmt1() != null )
        return false;
    } else if ( !sell_contract_amt_1.equals( row.getSellContractAmt1() ) ) {
        return false;
    }
    if ( sell_contract_amt_2 == null ) {
      if ( row.getSellContractAmt2() != null )
        return false;
    } else if ( !sell_contract_amt_2.equals( row.getSellContractAmt2() ) ) {
        return false;
    }
    if ( sell_contract_amt_f0 == null ) {
      if ( row.getSellContractAmtF0() != null )
        return false;
    } else if ( !sell_contract_amt_f0.equals( row.getSellContractAmtF0() ) ) {
        return false;
    }
    if ( sell_contract_amt_f1 == null ) {
      if ( row.getSellContractAmtF1() != null )
        return false;
    } else if ( !sell_contract_amt_f1.equals( row.getSellContractAmtF1() ) ) {
        return false;
    }
    if ( sell_contract_amt_f2 == null ) {
      if ( row.getSellContractAmtF2() != null )
        return false;
    } else if ( !sell_contract_amt_f2.equals( row.getSellContractAmtF2() ) ) {
        return false;
    }
    if ( non_pay_amt == null ) {
      if ( row.getNonPayAmt() != null )
        return false;
    } else if ( !non_pay_amt.equals( row.getNonPayAmt() ) ) {
        return false;
    }
    if ( today_claim_amt == null ) {
      if ( row.getTodayClaimAmt() != null )
        return false;
    } else if ( !today_claim_amt.equals( row.getTodayClaimAmt() ) ) {
        return false;
    }
    if ( tomorrow_claim_amt == null ) {
      if ( row.getTomorrowClaimAmt() != null )
        return false;
    } else if ( !tomorrow_claim_amt.equals( row.getTomorrowClaimAmt() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( ifo_deposit_insufficient_flag == null ) {
      if ( row.getIfoDepositInsufficientFlag() != null )
        return false;
    } else if ( !ifo_deposit_insufficient_flag.equals( row.getIfoDepositInsufficientFlag() ) ) {
        return false;
    }
    if ( ifo_deposit_index_nk225 == null ) {
      if ( row.getIfoDepositIndexNk225() != null )
        return false;
    } else if ( !ifo_deposit_index_nk225.equals( row.getIfoDepositIndexNk225() ) ) {
        return false;
    }
    if ( ifo_deposit_index_nk225_mini == null ) {
      if ( row.getIfoDepositIndexNk225Mini() != null )
        return false;
    } else if ( !ifo_deposit_index_nk225_mini.equals( row.getIfoDepositIndexNk225Mini() ) ) {
        return false;
    }
    if ( after_tomorrow_claim_amt == null ) {
      if ( row.getAfterTomorrowClaimAmt() != null )
        return false;
    } else if ( !after_tomorrow_claim_amt.equals( row.getAfterTomorrowClaimAmt() ) ) {
        return false;
    }
    if ( tomorrow_claim_amt_evening == null ) {
      if ( row.getTomorrowClaimAmtEvening() != null )
        return false;
    } else if ( !tomorrow_claim_amt_evening.equals( row.getTomorrowClaimAmtEvening() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( !row.getStatusIsNull() )
        return false;
    } else if ( row.getStatusIsNull() || ( status.intValue() != row.getStatus() ) ) {
        return false;
    }
    if ( error_message == null ) {
      if ( row.getErrorMessage() != null )
        return false;
    } else if ( !error_message.equals( row.getErrorMessage() ) ) {
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
      return  ((int) deposit_info_id)
        + ((int) account_id)
        + (base_date!=null? base_date.hashCode(): 0) 
        + (balance_0!=null? balance_0.hashCode(): 0) 
        + (balance_1!=null? balance_1.hashCode(): 0) 
        + (balance_2!=null? balance_2.hashCode(): 0) 
        + (ifo_deposit_balance_0!=null? ifo_deposit_balance_0.hashCode(): 0) 
        + (ifo_deposit_balance_1!=null? ifo_deposit_balance_1.hashCode(): 0) 
        + (ifo_deposit_balance_2!=null? ifo_deposit_balance_2.hashCode(): 0) 
        + (ifo_deposit_balance_f0!=null? ifo_deposit_balance_f0.hashCode(): 0) 
        + (ifo_deposit_balance_f1!=null? ifo_deposit_balance_f1.hashCode(): 0) 
        + (ifo_deposit_balance_f2!=null? ifo_deposit_balance_f2.hashCode(): 0) 
        + (today_transfer_amt_0!=null? today_transfer_amt_0.hashCode(): 0) 
        + (today_transfer_amt_1!=null? today_transfer_amt_1.hashCode(): 0) 
        + (today_transfer_amt_2!=null? today_transfer_amt_2.hashCode(): 0) 
        + (today_fut_settle_profit_0!=null? today_fut_settle_profit_0.hashCode(): 0) 
        + (today_fut_settle_profit_1!=null? today_fut_settle_profit_1.hashCode(): 0) 
        + (today_fut_settle_profit_2!=null? today_fut_settle_profit_2.hashCode(): 0) 
        + (today_op_net_amt_0!=null? today_op_net_amt_0.hashCode(): 0) 
        + (today_op_net_amt_1!=null? today_op_net_amt_1.hashCode(): 0) 
        + (today_op_net_amt_2!=null? today_op_net_amt_2.hashCode(): 0) 
        + (fut_eval_profit_0!=null? fut_eval_profit_0.hashCode(): 0) 
        + (fut_eval_profit_1!=null? fut_eval_profit_1.hashCode(): 0) 
        + (fut_eval_profit_2!=null? fut_eval_profit_2.hashCode(): 0) 
        + (long_fut_eval_profit_0!=null? long_fut_eval_profit_0.hashCode(): 0) 
        + (long_fut_eval_profit_1!=null? long_fut_eval_profit_1.hashCode(): 0) 
        + (long_fut_eval_profit_2!=null? long_fut_eval_profit_2.hashCode(): 0) 
        + (short_fut_eval_profit_0!=null? short_fut_eval_profit_0.hashCode(): 0) 
        + (short_fut_eval_profit_1!=null? short_fut_eval_profit_1.hashCode(): 0) 
        + (short_fut_eval_profit_2!=null? short_fut_eval_profit_2.hashCode(): 0) 
        + (acceptance_ifo_deposit_bal_0!=null? acceptance_ifo_deposit_bal_0.hashCode(): 0) 
        + (acceptance_ifo_deposit_bal_1!=null? acceptance_ifo_deposit_bal_1.hashCode(): 0) 
        + (acceptance_ifo_deposit_bal_2!=null? acceptance_ifo_deposit_bal_2.hashCode(): 0) 
        + (acceptance_ifo_deposit_bal_f0!=null? acceptance_ifo_deposit_bal_f0.hashCode(): 0) 
        + (acceptance_ifo_deposit_bal_f1!=null? acceptance_ifo_deposit_bal_f1.hashCode(): 0) 
        + (acceptance_ifo_deposit_bal_f2!=null? acceptance_ifo_deposit_bal_f2.hashCode(): 0) 
        + (net_op_value_total_amt_0!=null? net_op_value_total_amt_0.hashCode(): 0) 
        + (net_op_value_total_amt_1!=null? net_op_value_total_amt_1.hashCode(): 0) 
        + (net_op_value_total_amt_2!=null? net_op_value_total_amt_2.hashCode(): 0) 
        + (net_op_value_total_amt_f0!=null? net_op_value_total_amt_f0.hashCode(): 0) 
        + (net_op_value_total_amt_f1!=null? net_op_value_total_amt_f1.hashCode(): 0) 
        + (net_op_value_total_amt_f2!=null? net_op_value_total_amt_f2.hashCode(): 0) 
        + (ifo_deposit_necessary_amt_0!=null? ifo_deposit_necessary_amt_0.hashCode(): 0) 
        + (ifo_deposit_necessary_amt_1!=null? ifo_deposit_necessary_amt_1.hashCode(): 0) 
        + (ifo_deposit_necessary_amt_2!=null? ifo_deposit_necessary_amt_2.hashCode(): 0) 
        + (ifo_deposit_necessary_amt_f0!=null? ifo_deposit_necessary_amt_f0.hashCode(): 0) 
        + (ifo_deposit_necessary_amt_f1!=null? ifo_deposit_necessary_amt_f1.hashCode(): 0) 
        + (ifo_deposit_necessary_amt_f2!=null? ifo_deposit_necessary_amt_f2.hashCode(): 0) 
        + (ifo_deposit_power_0!=null? ifo_deposit_power_0.hashCode(): 0) 
        + (ifo_deposit_power_1!=null? ifo_deposit_power_1.hashCode(): 0) 
        + (ifo_deposit_power_2!=null? ifo_deposit_power_2.hashCode(): 0) 
        + (ifo_deposit_transfer_power_0!=null? ifo_deposit_transfer_power_0.hashCode(): 0) 
        + (ifo_deposit_transfer_power_1!=null? ifo_deposit_transfer_power_1.hashCode(): 0) 
        + (ifo_deposit_transfer_power_2!=null? ifo_deposit_transfer_power_2.hashCode(): 0) 
        + (bull_quantity_nk225_0!=null? bull_quantity_nk225_0.hashCode(): 0) 
        + (bull_quantity_nk225_1!=null? bull_quantity_nk225_1.hashCode(): 0) 
        + (bull_quantity_nk225_2!=null? bull_quantity_nk225_2.hashCode(): 0) 
        + (bear_quantity_nk225_0!=null? bear_quantity_nk225_0.hashCode(): 0) 
        + (bear_quantity_nk225_1!=null? bear_quantity_nk225_1.hashCode(): 0) 
        + (bear_quantity_nk225_2!=null? bear_quantity_nk225_2.hashCode(): 0) 
        + (long_pos_nk225_0!=null? long_pos_nk225_0.hashCode(): 0) 
        + (long_pos_nk225_1!=null? long_pos_nk225_1.hashCode(): 0) 
        + (long_pos_nk225_2!=null? long_pos_nk225_2.hashCode(): 0) 
        + (part_long_pos_nk225_0!=null? part_long_pos_nk225_0.hashCode(): 0) 
        + (part_long_pos_nk225_1!=null? part_long_pos_nk225_1.hashCode(): 0) 
        + (part_long_pos_nk225_2!=null? part_long_pos_nk225_2.hashCode(): 0) 
        + (short_pos_nk225_0!=null? short_pos_nk225_0.hashCode(): 0) 
        + (short_pos_nk225_1!=null? short_pos_nk225_1.hashCode(): 0) 
        + (short_pos_nk225_2!=null? short_pos_nk225_2.hashCode(): 0) 
        + (part_short_pos_nk225_0!=null? part_short_pos_nk225_0.hashCode(): 0) 
        + (part_short_pos_nk225_1!=null? part_short_pos_nk225_1.hashCode(): 0) 
        + (part_short_pos_nk225_2!=null? part_short_pos_nk225_2.hashCode(): 0) 
        + (bull_quantity_nk225_mini_0!=null? bull_quantity_nk225_mini_0.hashCode(): 0) 
        + (bull_quantity_nk225_mini_1!=null? bull_quantity_nk225_mini_1.hashCode(): 0) 
        + (bull_quantity_nk225_mini_2!=null? bull_quantity_nk225_mini_2.hashCode(): 0) 
        + (bear_quantity_nk225_mini_0!=null? bear_quantity_nk225_mini_0.hashCode(): 0) 
        + (bear_quantity_nk225_mini_1!=null? bear_quantity_nk225_mini_1.hashCode(): 0) 
        + (bear_quantity_nk225_mini_2!=null? bear_quantity_nk225_mini_2.hashCode(): 0) 
        + (long_pos_nk225_mini_0!=null? long_pos_nk225_mini_0.hashCode(): 0) 
        + (long_pos_nk225_mini_1!=null? long_pos_nk225_mini_1.hashCode(): 0) 
        + (long_pos_nk225_mini_2!=null? long_pos_nk225_mini_2.hashCode(): 0) 
        + (part_long_pos_nk225_mini_0!=null? part_long_pos_nk225_mini_0.hashCode(): 0) 
        + (part_long_pos_nk225_mini_1!=null? part_long_pos_nk225_mini_1.hashCode(): 0) 
        + (part_long_pos_nk225_mini_2!=null? part_long_pos_nk225_mini_2.hashCode(): 0) 
        + (short_pos_nk225_mini_0!=null? short_pos_nk225_mini_0.hashCode(): 0) 
        + (short_pos_nk225_mini_1!=null? short_pos_nk225_mini_1.hashCode(): 0) 
        + (short_pos_nk225_mini_2!=null? short_pos_nk225_mini_2.hashCode(): 0) 
        + (part_short_pos_nk225_mini_0!=null? part_short_pos_nk225_mini_0.hashCode(): 0) 
        + (part_short_pos_nk225_mini_1!=null? part_short_pos_nk225_mini_1.hashCode(): 0) 
        + (part_short_pos_nk225_mini_2!=null? part_short_pos_nk225_mini_2.hashCode(): 0) 
        + (buy_contract_amt_0!=null? buy_contract_amt_0.hashCode(): 0) 
        + (buy_contract_amt_1!=null? buy_contract_amt_1.hashCode(): 0) 
        + (buy_contract_amt_2!=null? buy_contract_amt_2.hashCode(): 0) 
        + (buy_contract_amt_f0!=null? buy_contract_amt_f0.hashCode(): 0) 
        + (buy_contract_amt_f1!=null? buy_contract_amt_f1.hashCode(): 0) 
        + (buy_contract_amt_f2!=null? buy_contract_amt_f2.hashCode(): 0) 
        + (sell_contract_amt_0!=null? sell_contract_amt_0.hashCode(): 0) 
        + (sell_contract_amt_1!=null? sell_contract_amt_1.hashCode(): 0) 
        + (sell_contract_amt_2!=null? sell_contract_amt_2.hashCode(): 0) 
        + (sell_contract_amt_f0!=null? sell_contract_amt_f0.hashCode(): 0) 
        + (sell_contract_amt_f1!=null? sell_contract_amt_f1.hashCode(): 0) 
        + (sell_contract_amt_f2!=null? sell_contract_amt_f2.hashCode(): 0) 
        + (non_pay_amt!=null? non_pay_amt.hashCode(): 0) 
        + (today_claim_amt!=null? today_claim_amt.hashCode(): 0) 
        + (tomorrow_claim_amt!=null? tomorrow_claim_amt.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (ifo_deposit_insufficient_flag!=null? ifo_deposit_insufficient_flag.hashCode(): 0) 
        + (ifo_deposit_index_nk225!=null? ifo_deposit_index_nk225.hashCode(): 0) 
        + (ifo_deposit_index_nk225_mini!=null? ifo_deposit_index_nk225_mini.hashCode(): 0) 
        + (after_tomorrow_claim_amt!=null? after_tomorrow_claim_amt.hashCode(): 0) 
        + (tomorrow_claim_amt_evening!=null? tomorrow_claim_amt_evening.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (error_message!=null? error_message.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("deposit_info_id",new Long(deposit_info_id));
		map.put("account_id",new Long(account_id));
		if ( base_date != null )
			map.put("base_date",base_date);
		if ( balance_0 != null )
			map.put("balance_0",balance_0);
		if ( balance_1 != null )
			map.put("balance_1",balance_1);
		if ( balance_2 != null )
			map.put("balance_2",balance_2);
		if ( ifo_deposit_balance_0 != null )
			map.put("ifo_deposit_balance_0",ifo_deposit_balance_0);
		if ( ifo_deposit_balance_1 != null )
			map.put("ifo_deposit_balance_1",ifo_deposit_balance_1);
		if ( ifo_deposit_balance_2 != null )
			map.put("ifo_deposit_balance_2",ifo_deposit_balance_2);
		if ( ifo_deposit_balance_f0 != null )
			map.put("ifo_deposit_balance_f0",ifo_deposit_balance_f0);
		if ( ifo_deposit_balance_f1 != null )
			map.put("ifo_deposit_balance_f1",ifo_deposit_balance_f1);
		if ( ifo_deposit_balance_f2 != null )
			map.put("ifo_deposit_balance_f2",ifo_deposit_balance_f2);
		if ( today_transfer_amt_0 != null )
			map.put("today_transfer_amt_0",today_transfer_amt_0);
		if ( today_transfer_amt_1 != null )
			map.put("today_transfer_amt_1",today_transfer_amt_1);
		if ( today_transfer_amt_2 != null )
			map.put("today_transfer_amt_2",today_transfer_amt_2);
		if ( today_fut_settle_profit_0 != null )
			map.put("today_fut_settle_profit_0",today_fut_settle_profit_0);
		if ( today_fut_settle_profit_1 != null )
			map.put("today_fut_settle_profit_1",today_fut_settle_profit_1);
		if ( today_fut_settle_profit_2 != null )
			map.put("today_fut_settle_profit_2",today_fut_settle_profit_2);
		if ( today_op_net_amt_0 != null )
			map.put("today_op_net_amt_0",today_op_net_amt_0);
		if ( today_op_net_amt_1 != null )
			map.put("today_op_net_amt_1",today_op_net_amt_1);
		if ( today_op_net_amt_2 != null )
			map.put("today_op_net_amt_2",today_op_net_amt_2);
		if ( fut_eval_profit_0 != null )
			map.put("fut_eval_profit_0",fut_eval_profit_0);
		if ( fut_eval_profit_1 != null )
			map.put("fut_eval_profit_1",fut_eval_profit_1);
		if ( fut_eval_profit_2 != null )
			map.put("fut_eval_profit_2",fut_eval_profit_2);
		if ( long_fut_eval_profit_0 != null )
			map.put("long_fut_eval_profit_0",long_fut_eval_profit_0);
		if ( long_fut_eval_profit_1 != null )
			map.put("long_fut_eval_profit_1",long_fut_eval_profit_1);
		if ( long_fut_eval_profit_2 != null )
			map.put("long_fut_eval_profit_2",long_fut_eval_profit_2);
		if ( short_fut_eval_profit_0 != null )
			map.put("short_fut_eval_profit_0",short_fut_eval_profit_0);
		if ( short_fut_eval_profit_1 != null )
			map.put("short_fut_eval_profit_1",short_fut_eval_profit_1);
		if ( short_fut_eval_profit_2 != null )
			map.put("short_fut_eval_profit_2",short_fut_eval_profit_2);
		if ( acceptance_ifo_deposit_bal_0 != null )
			map.put("acceptance_ifo_deposit_bal_0",acceptance_ifo_deposit_bal_0);
		if ( acceptance_ifo_deposit_bal_1 != null )
			map.put("acceptance_ifo_deposit_bal_1",acceptance_ifo_deposit_bal_1);
		if ( acceptance_ifo_deposit_bal_2 != null )
			map.put("acceptance_ifo_deposit_bal_2",acceptance_ifo_deposit_bal_2);
		if ( acceptance_ifo_deposit_bal_f0 != null )
			map.put("acceptance_ifo_deposit_bal_f0",acceptance_ifo_deposit_bal_f0);
		if ( acceptance_ifo_deposit_bal_f1 != null )
			map.put("acceptance_ifo_deposit_bal_f1",acceptance_ifo_deposit_bal_f1);
		if ( acceptance_ifo_deposit_bal_f2 != null )
			map.put("acceptance_ifo_deposit_bal_f2",acceptance_ifo_deposit_bal_f2);
		if ( net_op_value_total_amt_0 != null )
			map.put("net_op_value_total_amt_0",net_op_value_total_amt_0);
		if ( net_op_value_total_amt_1 != null )
			map.put("net_op_value_total_amt_1",net_op_value_total_amt_1);
		if ( net_op_value_total_amt_2 != null )
			map.put("net_op_value_total_amt_2",net_op_value_total_amt_2);
		if ( net_op_value_total_amt_f0 != null )
			map.put("net_op_value_total_amt_f0",net_op_value_total_amt_f0);
		if ( net_op_value_total_amt_f1 != null )
			map.put("net_op_value_total_amt_f1",net_op_value_total_amt_f1);
		if ( net_op_value_total_amt_f2 != null )
			map.put("net_op_value_total_amt_f2",net_op_value_total_amt_f2);
		if ( ifo_deposit_necessary_amt_0 != null )
			map.put("ifo_deposit_necessary_amt_0",ifo_deposit_necessary_amt_0);
		if ( ifo_deposit_necessary_amt_1 != null )
			map.put("ifo_deposit_necessary_amt_1",ifo_deposit_necessary_amt_1);
		if ( ifo_deposit_necessary_amt_2 != null )
			map.put("ifo_deposit_necessary_amt_2",ifo_deposit_necessary_amt_2);
		if ( ifo_deposit_necessary_amt_f0 != null )
			map.put("ifo_deposit_necessary_amt_f0",ifo_deposit_necessary_amt_f0);
		if ( ifo_deposit_necessary_amt_f1 != null )
			map.put("ifo_deposit_necessary_amt_f1",ifo_deposit_necessary_amt_f1);
		if ( ifo_deposit_necessary_amt_f2 != null )
			map.put("ifo_deposit_necessary_amt_f2",ifo_deposit_necessary_amt_f2);
		if ( ifo_deposit_power_0 != null )
			map.put("ifo_deposit_power_0",ifo_deposit_power_0);
		if ( ifo_deposit_power_1 != null )
			map.put("ifo_deposit_power_1",ifo_deposit_power_1);
		if ( ifo_deposit_power_2 != null )
			map.put("ifo_deposit_power_2",ifo_deposit_power_2);
		if ( ifo_deposit_transfer_power_0 != null )
			map.put("ifo_deposit_transfer_power_0",ifo_deposit_transfer_power_0);
		if ( ifo_deposit_transfer_power_1 != null )
			map.put("ifo_deposit_transfer_power_1",ifo_deposit_transfer_power_1);
		if ( ifo_deposit_transfer_power_2 != null )
			map.put("ifo_deposit_transfer_power_2",ifo_deposit_transfer_power_2);
		if ( bull_quantity_nk225_0 != null )
			map.put("bull_quantity_nk225_0",bull_quantity_nk225_0);
		if ( bull_quantity_nk225_1 != null )
			map.put("bull_quantity_nk225_1",bull_quantity_nk225_1);
		if ( bull_quantity_nk225_2 != null )
			map.put("bull_quantity_nk225_2",bull_quantity_nk225_2);
		if ( bear_quantity_nk225_0 != null )
			map.put("bear_quantity_nk225_0",bear_quantity_nk225_0);
		if ( bear_quantity_nk225_1 != null )
			map.put("bear_quantity_nk225_1",bear_quantity_nk225_1);
		if ( bear_quantity_nk225_2 != null )
			map.put("bear_quantity_nk225_2",bear_quantity_nk225_2);
		if ( long_pos_nk225_0 != null )
			map.put("long_pos_nk225_0",long_pos_nk225_0);
		if ( long_pos_nk225_1 != null )
			map.put("long_pos_nk225_1",long_pos_nk225_1);
		if ( long_pos_nk225_2 != null )
			map.put("long_pos_nk225_2",long_pos_nk225_2);
		if ( part_long_pos_nk225_0 != null )
			map.put("part_long_pos_nk225_0",part_long_pos_nk225_0);
		if ( part_long_pos_nk225_1 != null )
			map.put("part_long_pos_nk225_1",part_long_pos_nk225_1);
		if ( part_long_pos_nk225_2 != null )
			map.put("part_long_pos_nk225_2",part_long_pos_nk225_2);
		if ( short_pos_nk225_0 != null )
			map.put("short_pos_nk225_0",short_pos_nk225_0);
		if ( short_pos_nk225_1 != null )
			map.put("short_pos_nk225_1",short_pos_nk225_1);
		if ( short_pos_nk225_2 != null )
			map.put("short_pos_nk225_2",short_pos_nk225_2);
		if ( part_short_pos_nk225_0 != null )
			map.put("part_short_pos_nk225_0",part_short_pos_nk225_0);
		if ( part_short_pos_nk225_1 != null )
			map.put("part_short_pos_nk225_1",part_short_pos_nk225_1);
		if ( part_short_pos_nk225_2 != null )
			map.put("part_short_pos_nk225_2",part_short_pos_nk225_2);
		if ( bull_quantity_nk225_mini_0 != null )
			map.put("bull_quantity_nk225_mini_0",bull_quantity_nk225_mini_0);
		if ( bull_quantity_nk225_mini_1 != null )
			map.put("bull_quantity_nk225_mini_1",bull_quantity_nk225_mini_1);
		if ( bull_quantity_nk225_mini_2 != null )
			map.put("bull_quantity_nk225_mini_2",bull_quantity_nk225_mini_2);
		if ( bear_quantity_nk225_mini_0 != null )
			map.put("bear_quantity_nk225_mini_0",bear_quantity_nk225_mini_0);
		if ( bear_quantity_nk225_mini_1 != null )
			map.put("bear_quantity_nk225_mini_1",bear_quantity_nk225_mini_1);
		if ( bear_quantity_nk225_mini_2 != null )
			map.put("bear_quantity_nk225_mini_2",bear_quantity_nk225_mini_2);
		if ( long_pos_nk225_mini_0 != null )
			map.put("long_pos_nk225_mini_0",long_pos_nk225_mini_0);
		if ( long_pos_nk225_mini_1 != null )
			map.put("long_pos_nk225_mini_1",long_pos_nk225_mini_1);
		if ( long_pos_nk225_mini_2 != null )
			map.put("long_pos_nk225_mini_2",long_pos_nk225_mini_2);
		if ( part_long_pos_nk225_mini_0 != null )
			map.put("part_long_pos_nk225_mini_0",part_long_pos_nk225_mini_0);
		if ( part_long_pos_nk225_mini_1 != null )
			map.put("part_long_pos_nk225_mini_1",part_long_pos_nk225_mini_1);
		if ( part_long_pos_nk225_mini_2 != null )
			map.put("part_long_pos_nk225_mini_2",part_long_pos_nk225_mini_2);
		if ( short_pos_nk225_mini_0 != null )
			map.put("short_pos_nk225_mini_0",short_pos_nk225_mini_0);
		if ( short_pos_nk225_mini_1 != null )
			map.put("short_pos_nk225_mini_1",short_pos_nk225_mini_1);
		if ( short_pos_nk225_mini_2 != null )
			map.put("short_pos_nk225_mini_2",short_pos_nk225_mini_2);
		if ( part_short_pos_nk225_mini_0 != null )
			map.put("part_short_pos_nk225_mini_0",part_short_pos_nk225_mini_0);
		if ( part_short_pos_nk225_mini_1 != null )
			map.put("part_short_pos_nk225_mini_1",part_short_pos_nk225_mini_1);
		if ( part_short_pos_nk225_mini_2 != null )
			map.put("part_short_pos_nk225_mini_2",part_short_pos_nk225_mini_2);
		if ( buy_contract_amt_0 != null )
			map.put("buy_contract_amt_0",buy_contract_amt_0);
		if ( buy_contract_amt_1 != null )
			map.put("buy_contract_amt_1",buy_contract_amt_1);
		if ( buy_contract_amt_2 != null )
			map.put("buy_contract_amt_2",buy_contract_amt_2);
		if ( buy_contract_amt_f0 != null )
			map.put("buy_contract_amt_f0",buy_contract_amt_f0);
		if ( buy_contract_amt_f1 != null )
			map.put("buy_contract_amt_f1",buy_contract_amt_f1);
		if ( buy_contract_amt_f2 != null )
			map.put("buy_contract_amt_f2",buy_contract_amt_f2);
		if ( sell_contract_amt_0 != null )
			map.put("sell_contract_amt_0",sell_contract_amt_0);
		if ( sell_contract_amt_1 != null )
			map.put("sell_contract_amt_1",sell_contract_amt_1);
		if ( sell_contract_amt_2 != null )
			map.put("sell_contract_amt_2",sell_contract_amt_2);
		if ( sell_contract_amt_f0 != null )
			map.put("sell_contract_amt_f0",sell_contract_amt_f0);
		if ( sell_contract_amt_f1 != null )
			map.put("sell_contract_amt_f1",sell_contract_amt_f1);
		if ( sell_contract_amt_f2 != null )
			map.put("sell_contract_amt_f2",sell_contract_amt_f2);
		if ( non_pay_amt != null )
			map.put("non_pay_amt",non_pay_amt);
		if ( today_claim_amt != null )
			map.put("today_claim_amt",today_claim_amt);
		if ( tomorrow_claim_amt != null )
			map.put("tomorrow_claim_amt",tomorrow_claim_amt);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( ifo_deposit_insufficient_flag != null )
			map.put("ifo_deposit_insufficient_flag",ifo_deposit_insufficient_flag);
		if ( ifo_deposit_index_nk225 != null )
			map.put("ifo_deposit_index_nk225",ifo_deposit_index_nk225);
		if ( ifo_deposit_index_nk225_mini != null )
			map.put("ifo_deposit_index_nk225_mini",ifo_deposit_index_nk225_mini);
		if ( after_tomorrow_claim_amt != null )
			map.put("after_tomorrow_claim_amt",after_tomorrow_claim_amt);
		if ( tomorrow_claim_amt_evening != null )
			map.put("tomorrow_claim_amt_evening",tomorrow_claim_amt_evening);
		if ( status != null )
			map.put("status",status);
		if ( error_message != null )
			map.put("error_message",error_message);
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
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( base_date_is_modified )
			map.put("base_date",base_date);
		if ( balance_0_is_modified )
			map.put("balance_0",balance_0);
		if ( balance_1_is_modified )
			map.put("balance_1",balance_1);
		if ( balance_2_is_modified )
			map.put("balance_2",balance_2);
		if ( ifo_deposit_balance_0_is_modified )
			map.put("ifo_deposit_balance_0",ifo_deposit_balance_0);
		if ( ifo_deposit_balance_1_is_modified )
			map.put("ifo_deposit_balance_1",ifo_deposit_balance_1);
		if ( ifo_deposit_balance_2_is_modified )
			map.put("ifo_deposit_balance_2",ifo_deposit_balance_2);
		if ( ifo_deposit_balance_f0_is_modified )
			map.put("ifo_deposit_balance_f0",ifo_deposit_balance_f0);
		if ( ifo_deposit_balance_f1_is_modified )
			map.put("ifo_deposit_balance_f1",ifo_deposit_balance_f1);
		if ( ifo_deposit_balance_f2_is_modified )
			map.put("ifo_deposit_balance_f2",ifo_deposit_balance_f2);
		if ( today_transfer_amt_0_is_modified )
			map.put("today_transfer_amt_0",today_transfer_amt_0);
		if ( today_transfer_amt_1_is_modified )
			map.put("today_transfer_amt_1",today_transfer_amt_1);
		if ( today_transfer_amt_2_is_modified )
			map.put("today_transfer_amt_2",today_transfer_amt_2);
		if ( today_fut_settle_profit_0_is_modified )
			map.put("today_fut_settle_profit_0",today_fut_settle_profit_0);
		if ( today_fut_settle_profit_1_is_modified )
			map.put("today_fut_settle_profit_1",today_fut_settle_profit_1);
		if ( today_fut_settle_profit_2_is_modified )
			map.put("today_fut_settle_profit_2",today_fut_settle_profit_2);
		if ( today_op_net_amt_0_is_modified )
			map.put("today_op_net_amt_0",today_op_net_amt_0);
		if ( today_op_net_amt_1_is_modified )
			map.put("today_op_net_amt_1",today_op_net_amt_1);
		if ( today_op_net_amt_2_is_modified )
			map.put("today_op_net_amt_2",today_op_net_amt_2);
		if ( fut_eval_profit_0_is_modified )
			map.put("fut_eval_profit_0",fut_eval_profit_0);
		if ( fut_eval_profit_1_is_modified )
			map.put("fut_eval_profit_1",fut_eval_profit_1);
		if ( fut_eval_profit_2_is_modified )
			map.put("fut_eval_profit_2",fut_eval_profit_2);
		if ( long_fut_eval_profit_0_is_modified )
			map.put("long_fut_eval_profit_0",long_fut_eval_profit_0);
		if ( long_fut_eval_profit_1_is_modified )
			map.put("long_fut_eval_profit_1",long_fut_eval_profit_1);
		if ( long_fut_eval_profit_2_is_modified )
			map.put("long_fut_eval_profit_2",long_fut_eval_profit_2);
		if ( short_fut_eval_profit_0_is_modified )
			map.put("short_fut_eval_profit_0",short_fut_eval_profit_0);
		if ( short_fut_eval_profit_1_is_modified )
			map.put("short_fut_eval_profit_1",short_fut_eval_profit_1);
		if ( short_fut_eval_profit_2_is_modified )
			map.put("short_fut_eval_profit_2",short_fut_eval_profit_2);
		if ( acceptance_ifo_deposit_bal_0_is_modified )
			map.put("acceptance_ifo_deposit_bal_0",acceptance_ifo_deposit_bal_0);
		if ( acceptance_ifo_deposit_bal_1_is_modified )
			map.put("acceptance_ifo_deposit_bal_1",acceptance_ifo_deposit_bal_1);
		if ( acceptance_ifo_deposit_bal_2_is_modified )
			map.put("acceptance_ifo_deposit_bal_2",acceptance_ifo_deposit_bal_2);
		if ( acceptance_ifo_deposit_bal_f0_is_modified )
			map.put("acceptance_ifo_deposit_bal_f0",acceptance_ifo_deposit_bal_f0);
		if ( acceptance_ifo_deposit_bal_f1_is_modified )
			map.put("acceptance_ifo_deposit_bal_f1",acceptance_ifo_deposit_bal_f1);
		if ( acceptance_ifo_deposit_bal_f2_is_modified )
			map.put("acceptance_ifo_deposit_bal_f2",acceptance_ifo_deposit_bal_f2);
		if ( net_op_value_total_amt_0_is_modified )
			map.put("net_op_value_total_amt_0",net_op_value_total_amt_0);
		if ( net_op_value_total_amt_1_is_modified )
			map.put("net_op_value_total_amt_1",net_op_value_total_amt_1);
		if ( net_op_value_total_amt_2_is_modified )
			map.put("net_op_value_total_amt_2",net_op_value_total_amt_2);
		if ( net_op_value_total_amt_f0_is_modified )
			map.put("net_op_value_total_amt_f0",net_op_value_total_amt_f0);
		if ( net_op_value_total_amt_f1_is_modified )
			map.put("net_op_value_total_amt_f1",net_op_value_total_amt_f1);
		if ( net_op_value_total_amt_f2_is_modified )
			map.put("net_op_value_total_amt_f2",net_op_value_total_amt_f2);
		if ( ifo_deposit_necessary_amt_0_is_modified )
			map.put("ifo_deposit_necessary_amt_0",ifo_deposit_necessary_amt_0);
		if ( ifo_deposit_necessary_amt_1_is_modified )
			map.put("ifo_deposit_necessary_amt_1",ifo_deposit_necessary_amt_1);
		if ( ifo_deposit_necessary_amt_2_is_modified )
			map.put("ifo_deposit_necessary_amt_2",ifo_deposit_necessary_amt_2);
		if ( ifo_deposit_necessary_amt_f0_is_modified )
			map.put("ifo_deposit_necessary_amt_f0",ifo_deposit_necessary_amt_f0);
		if ( ifo_deposit_necessary_amt_f1_is_modified )
			map.put("ifo_deposit_necessary_amt_f1",ifo_deposit_necessary_amt_f1);
		if ( ifo_deposit_necessary_amt_f2_is_modified )
			map.put("ifo_deposit_necessary_amt_f2",ifo_deposit_necessary_amt_f2);
		if ( ifo_deposit_power_0_is_modified )
			map.put("ifo_deposit_power_0",ifo_deposit_power_0);
		if ( ifo_deposit_power_1_is_modified )
			map.put("ifo_deposit_power_1",ifo_deposit_power_1);
		if ( ifo_deposit_power_2_is_modified )
			map.put("ifo_deposit_power_2",ifo_deposit_power_2);
		if ( ifo_deposit_transfer_power_0_is_modified )
			map.put("ifo_deposit_transfer_power_0",ifo_deposit_transfer_power_0);
		if ( ifo_deposit_transfer_power_1_is_modified )
			map.put("ifo_deposit_transfer_power_1",ifo_deposit_transfer_power_1);
		if ( ifo_deposit_transfer_power_2_is_modified )
			map.put("ifo_deposit_transfer_power_2",ifo_deposit_transfer_power_2);
		if ( bull_quantity_nk225_0_is_modified )
			map.put("bull_quantity_nk225_0",bull_quantity_nk225_0);
		if ( bull_quantity_nk225_1_is_modified )
			map.put("bull_quantity_nk225_1",bull_quantity_nk225_1);
		if ( bull_quantity_nk225_2_is_modified )
			map.put("bull_quantity_nk225_2",bull_quantity_nk225_2);
		if ( bear_quantity_nk225_0_is_modified )
			map.put("bear_quantity_nk225_0",bear_quantity_nk225_0);
		if ( bear_quantity_nk225_1_is_modified )
			map.put("bear_quantity_nk225_1",bear_quantity_nk225_1);
		if ( bear_quantity_nk225_2_is_modified )
			map.put("bear_quantity_nk225_2",bear_quantity_nk225_2);
		if ( long_pos_nk225_0_is_modified )
			map.put("long_pos_nk225_0",long_pos_nk225_0);
		if ( long_pos_nk225_1_is_modified )
			map.put("long_pos_nk225_1",long_pos_nk225_1);
		if ( long_pos_nk225_2_is_modified )
			map.put("long_pos_nk225_2",long_pos_nk225_2);
		if ( part_long_pos_nk225_0_is_modified )
			map.put("part_long_pos_nk225_0",part_long_pos_nk225_0);
		if ( part_long_pos_nk225_1_is_modified )
			map.put("part_long_pos_nk225_1",part_long_pos_nk225_1);
		if ( part_long_pos_nk225_2_is_modified )
			map.put("part_long_pos_nk225_2",part_long_pos_nk225_2);
		if ( short_pos_nk225_0_is_modified )
			map.put("short_pos_nk225_0",short_pos_nk225_0);
		if ( short_pos_nk225_1_is_modified )
			map.put("short_pos_nk225_1",short_pos_nk225_1);
		if ( short_pos_nk225_2_is_modified )
			map.put("short_pos_nk225_2",short_pos_nk225_2);
		if ( part_short_pos_nk225_0_is_modified )
			map.put("part_short_pos_nk225_0",part_short_pos_nk225_0);
		if ( part_short_pos_nk225_1_is_modified )
			map.put("part_short_pos_nk225_1",part_short_pos_nk225_1);
		if ( part_short_pos_nk225_2_is_modified )
			map.put("part_short_pos_nk225_2",part_short_pos_nk225_2);
		if ( bull_quantity_nk225_mini_0_is_modified )
			map.put("bull_quantity_nk225_mini_0",bull_quantity_nk225_mini_0);
		if ( bull_quantity_nk225_mini_1_is_modified )
			map.put("bull_quantity_nk225_mini_1",bull_quantity_nk225_mini_1);
		if ( bull_quantity_nk225_mini_2_is_modified )
			map.put("bull_quantity_nk225_mini_2",bull_quantity_nk225_mini_2);
		if ( bear_quantity_nk225_mini_0_is_modified )
			map.put("bear_quantity_nk225_mini_0",bear_quantity_nk225_mini_0);
		if ( bear_quantity_nk225_mini_1_is_modified )
			map.put("bear_quantity_nk225_mini_1",bear_quantity_nk225_mini_1);
		if ( bear_quantity_nk225_mini_2_is_modified )
			map.put("bear_quantity_nk225_mini_2",bear_quantity_nk225_mini_2);
		if ( long_pos_nk225_mini_0_is_modified )
			map.put("long_pos_nk225_mini_0",long_pos_nk225_mini_0);
		if ( long_pos_nk225_mini_1_is_modified )
			map.put("long_pos_nk225_mini_1",long_pos_nk225_mini_1);
		if ( long_pos_nk225_mini_2_is_modified )
			map.put("long_pos_nk225_mini_2",long_pos_nk225_mini_2);
		if ( part_long_pos_nk225_mini_0_is_modified )
			map.put("part_long_pos_nk225_mini_0",part_long_pos_nk225_mini_0);
		if ( part_long_pos_nk225_mini_1_is_modified )
			map.put("part_long_pos_nk225_mini_1",part_long_pos_nk225_mini_1);
		if ( part_long_pos_nk225_mini_2_is_modified )
			map.put("part_long_pos_nk225_mini_2",part_long_pos_nk225_mini_2);
		if ( short_pos_nk225_mini_0_is_modified )
			map.put("short_pos_nk225_mini_0",short_pos_nk225_mini_0);
		if ( short_pos_nk225_mini_1_is_modified )
			map.put("short_pos_nk225_mini_1",short_pos_nk225_mini_1);
		if ( short_pos_nk225_mini_2_is_modified )
			map.put("short_pos_nk225_mini_2",short_pos_nk225_mini_2);
		if ( part_short_pos_nk225_mini_0_is_modified )
			map.put("part_short_pos_nk225_mini_0",part_short_pos_nk225_mini_0);
		if ( part_short_pos_nk225_mini_1_is_modified )
			map.put("part_short_pos_nk225_mini_1",part_short_pos_nk225_mini_1);
		if ( part_short_pos_nk225_mini_2_is_modified )
			map.put("part_short_pos_nk225_mini_2",part_short_pos_nk225_mini_2);
		if ( buy_contract_amt_0_is_modified )
			map.put("buy_contract_amt_0",buy_contract_amt_0);
		if ( buy_contract_amt_1_is_modified )
			map.put("buy_contract_amt_1",buy_contract_amt_1);
		if ( buy_contract_amt_2_is_modified )
			map.put("buy_contract_amt_2",buy_contract_amt_2);
		if ( buy_contract_amt_f0_is_modified )
			map.put("buy_contract_amt_f0",buy_contract_amt_f0);
		if ( buy_contract_amt_f1_is_modified )
			map.put("buy_contract_amt_f1",buy_contract_amt_f1);
		if ( buy_contract_amt_f2_is_modified )
			map.put("buy_contract_amt_f2",buy_contract_amt_f2);
		if ( sell_contract_amt_0_is_modified )
			map.put("sell_contract_amt_0",sell_contract_amt_0);
		if ( sell_contract_amt_1_is_modified )
			map.put("sell_contract_amt_1",sell_contract_amt_1);
		if ( sell_contract_amt_2_is_modified )
			map.put("sell_contract_amt_2",sell_contract_amt_2);
		if ( sell_contract_amt_f0_is_modified )
			map.put("sell_contract_amt_f0",sell_contract_amt_f0);
		if ( sell_contract_amt_f1_is_modified )
			map.put("sell_contract_amt_f1",sell_contract_amt_f1);
		if ( sell_contract_amt_f2_is_modified )
			map.put("sell_contract_amt_f2",sell_contract_amt_f2);
		if ( non_pay_amt_is_modified )
			map.put("non_pay_amt",non_pay_amt);
		if ( today_claim_amt_is_modified )
			map.put("today_claim_amt",today_claim_amt);
		if ( tomorrow_claim_amt_is_modified )
			map.put("tomorrow_claim_amt",tomorrow_claim_amt);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( ifo_deposit_insufficient_flag_is_modified )
			map.put("ifo_deposit_insufficient_flag",ifo_deposit_insufficient_flag);
		if ( ifo_deposit_index_nk225_is_modified )
			map.put("ifo_deposit_index_nk225",ifo_deposit_index_nk225);
		if ( ifo_deposit_index_nk225_mini_is_modified )
			map.put("ifo_deposit_index_nk225_mini",ifo_deposit_index_nk225_mini);
		if ( after_tomorrow_claim_amt_is_modified )
			map.put("after_tomorrow_claim_amt",after_tomorrow_claim_amt);
		if ( tomorrow_claim_amt_evening_is_modified )
			map.put("tomorrow_claim_amt_evening",tomorrow_claim_amt_evening);
		if ( status_is_modified )
			map.put("status",status);
		if ( error_message_is_modified )
			map.put("error_message",error_message);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			map.put("base_date",base_date);
			map.put("balance_0",balance_0);
			map.put("balance_1",balance_1);
			map.put("balance_2",balance_2);
			map.put("ifo_deposit_balance_0",ifo_deposit_balance_0);
			map.put("ifo_deposit_balance_1",ifo_deposit_balance_1);
			map.put("ifo_deposit_balance_2",ifo_deposit_balance_2);
			map.put("ifo_deposit_balance_f0",ifo_deposit_balance_f0);
			map.put("ifo_deposit_balance_f1",ifo_deposit_balance_f1);
			map.put("ifo_deposit_balance_f2",ifo_deposit_balance_f2);
			map.put("today_transfer_amt_0",today_transfer_amt_0);
			map.put("today_transfer_amt_1",today_transfer_amt_1);
			map.put("today_transfer_amt_2",today_transfer_amt_2);
			map.put("today_fut_settle_profit_0",today_fut_settle_profit_0);
			map.put("today_fut_settle_profit_1",today_fut_settle_profit_1);
			map.put("today_fut_settle_profit_2",today_fut_settle_profit_2);
			map.put("today_op_net_amt_0",today_op_net_amt_0);
			map.put("today_op_net_amt_1",today_op_net_amt_1);
			map.put("today_op_net_amt_2",today_op_net_amt_2);
			map.put("fut_eval_profit_0",fut_eval_profit_0);
			map.put("fut_eval_profit_1",fut_eval_profit_1);
			map.put("fut_eval_profit_2",fut_eval_profit_2);
			map.put("long_fut_eval_profit_0",long_fut_eval_profit_0);
			map.put("long_fut_eval_profit_1",long_fut_eval_profit_1);
			map.put("long_fut_eval_profit_2",long_fut_eval_profit_2);
			map.put("short_fut_eval_profit_0",short_fut_eval_profit_0);
			map.put("short_fut_eval_profit_1",short_fut_eval_profit_1);
			map.put("short_fut_eval_profit_2",short_fut_eval_profit_2);
			map.put("acceptance_ifo_deposit_bal_0",acceptance_ifo_deposit_bal_0);
			map.put("acceptance_ifo_deposit_bal_1",acceptance_ifo_deposit_bal_1);
			map.put("acceptance_ifo_deposit_bal_2",acceptance_ifo_deposit_bal_2);
			map.put("acceptance_ifo_deposit_bal_f0",acceptance_ifo_deposit_bal_f0);
			map.put("acceptance_ifo_deposit_bal_f1",acceptance_ifo_deposit_bal_f1);
			map.put("acceptance_ifo_deposit_bal_f2",acceptance_ifo_deposit_bal_f2);
			map.put("net_op_value_total_amt_0",net_op_value_total_amt_0);
			map.put("net_op_value_total_amt_1",net_op_value_total_amt_1);
			map.put("net_op_value_total_amt_2",net_op_value_total_amt_2);
			map.put("net_op_value_total_amt_f0",net_op_value_total_amt_f0);
			map.put("net_op_value_total_amt_f1",net_op_value_total_amt_f1);
			map.put("net_op_value_total_amt_f2",net_op_value_total_amt_f2);
			map.put("ifo_deposit_necessary_amt_0",ifo_deposit_necessary_amt_0);
			map.put("ifo_deposit_necessary_amt_1",ifo_deposit_necessary_amt_1);
			map.put("ifo_deposit_necessary_amt_2",ifo_deposit_necessary_amt_2);
			map.put("ifo_deposit_necessary_amt_f0",ifo_deposit_necessary_amt_f0);
			map.put("ifo_deposit_necessary_amt_f1",ifo_deposit_necessary_amt_f1);
			map.put("ifo_deposit_necessary_amt_f2",ifo_deposit_necessary_amt_f2);
			map.put("ifo_deposit_power_0",ifo_deposit_power_0);
			map.put("ifo_deposit_power_1",ifo_deposit_power_1);
			map.put("ifo_deposit_power_2",ifo_deposit_power_2);
			map.put("ifo_deposit_transfer_power_0",ifo_deposit_transfer_power_0);
			map.put("ifo_deposit_transfer_power_1",ifo_deposit_transfer_power_1);
			map.put("ifo_deposit_transfer_power_2",ifo_deposit_transfer_power_2);
			map.put("bull_quantity_nk225_0",bull_quantity_nk225_0);
			map.put("bull_quantity_nk225_1",bull_quantity_nk225_1);
			map.put("bull_quantity_nk225_2",bull_quantity_nk225_2);
			map.put("bear_quantity_nk225_0",bear_quantity_nk225_0);
			map.put("bear_quantity_nk225_1",bear_quantity_nk225_1);
			map.put("bear_quantity_nk225_2",bear_quantity_nk225_2);
			map.put("long_pos_nk225_0",long_pos_nk225_0);
			map.put("long_pos_nk225_1",long_pos_nk225_1);
			map.put("long_pos_nk225_2",long_pos_nk225_2);
			map.put("part_long_pos_nk225_0",part_long_pos_nk225_0);
			map.put("part_long_pos_nk225_1",part_long_pos_nk225_1);
			map.put("part_long_pos_nk225_2",part_long_pos_nk225_2);
			map.put("short_pos_nk225_0",short_pos_nk225_0);
			map.put("short_pos_nk225_1",short_pos_nk225_1);
			map.put("short_pos_nk225_2",short_pos_nk225_2);
			map.put("part_short_pos_nk225_0",part_short_pos_nk225_0);
			map.put("part_short_pos_nk225_1",part_short_pos_nk225_1);
			map.put("part_short_pos_nk225_2",part_short_pos_nk225_2);
			map.put("bull_quantity_nk225_mini_0",bull_quantity_nk225_mini_0);
			map.put("bull_quantity_nk225_mini_1",bull_quantity_nk225_mini_1);
			map.put("bull_quantity_nk225_mini_2",bull_quantity_nk225_mini_2);
			map.put("bear_quantity_nk225_mini_0",bear_quantity_nk225_mini_0);
			map.put("bear_quantity_nk225_mini_1",bear_quantity_nk225_mini_1);
			map.put("bear_quantity_nk225_mini_2",bear_quantity_nk225_mini_2);
			map.put("long_pos_nk225_mini_0",long_pos_nk225_mini_0);
			map.put("long_pos_nk225_mini_1",long_pos_nk225_mini_1);
			map.put("long_pos_nk225_mini_2",long_pos_nk225_mini_2);
			map.put("part_long_pos_nk225_mini_0",part_long_pos_nk225_mini_0);
			map.put("part_long_pos_nk225_mini_1",part_long_pos_nk225_mini_1);
			map.put("part_long_pos_nk225_mini_2",part_long_pos_nk225_mini_2);
			map.put("short_pos_nk225_mini_0",short_pos_nk225_mini_0);
			map.put("short_pos_nk225_mini_1",short_pos_nk225_mini_1);
			map.put("short_pos_nk225_mini_2",short_pos_nk225_mini_2);
			map.put("part_short_pos_nk225_mini_0",part_short_pos_nk225_mini_0);
			map.put("part_short_pos_nk225_mini_1",part_short_pos_nk225_mini_1);
			map.put("part_short_pos_nk225_mini_2",part_short_pos_nk225_mini_2);
			map.put("buy_contract_amt_0",buy_contract_amt_0);
			map.put("buy_contract_amt_1",buy_contract_amt_1);
			map.put("buy_contract_amt_2",buy_contract_amt_2);
			map.put("buy_contract_amt_f0",buy_contract_amt_f0);
			map.put("buy_contract_amt_f1",buy_contract_amt_f1);
			map.put("buy_contract_amt_f2",buy_contract_amt_f2);
			map.put("sell_contract_amt_0",sell_contract_amt_0);
			map.put("sell_contract_amt_1",sell_contract_amt_1);
			map.put("sell_contract_amt_2",sell_contract_amt_2);
			map.put("sell_contract_amt_f0",sell_contract_amt_f0);
			map.put("sell_contract_amt_f1",sell_contract_amt_f1);
			map.put("sell_contract_amt_f2",sell_contract_amt_f2);
			map.put("non_pay_amt",non_pay_amt);
			map.put("today_claim_amt",today_claim_amt);
			map.put("tomorrow_claim_amt",tomorrow_claim_amt);
			map.put("delivery_date",delivery_date);
			map.put("ifo_deposit_insufficient_flag",ifo_deposit_insufficient_flag);
			map.put("ifo_deposit_index_nk225",ifo_deposit_index_nk225);
			map.put("ifo_deposit_index_nk225_mini",ifo_deposit_index_nk225_mini);
			map.put("after_tomorrow_claim_amt",after_tomorrow_claim_amt);
			map.put("tomorrow_claim_amt_evening",tomorrow_claim_amt_evening);
			map.put("status",status);
			map.put("error_message",error_message);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>deposit_info_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDepositInfoId()
  {
    return deposit_info_id;
  }


  /** 
   * <em>deposit_info_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositInfoIdIsSet() {
    return deposit_info_id_is_set;
  }


  /** 
   * <em>deposit_info_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositInfoIdIsModified() {
    return deposit_info_id_is_modified;
  }


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>base_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBaseDate()
  {
    return base_date;
  }


  /** 
   * <em>base_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateIsSet() {
    return base_date_is_set;
  }


  /** 
   * <em>base_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBaseDateIsModified() {
    return base_date_is_modified;
  }


  /** 
   * <em>balance_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBalance0()
  {
    return balance_0;
  }


  /** 
   * <em>balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalance0IsSet() {
    return balance_0_is_set;
  }


  /** 
   * <em>balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalance0IsModified() {
    return balance_0_is_modified;
  }


  /** 
   * <em>balance_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBalance1()
  {
    return balance_1;
  }


  /** 
   * <em>balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalance1IsSet() {
    return balance_1_is_set;
  }


  /** 
   * <em>balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalance1IsModified() {
    return balance_1_is_modified;
  }


  /** 
   * <em>balance_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBalance2()
  {
    return balance_2;
  }


  /** 
   * <em>balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalance2IsSet() {
    return balance_2_is_set;
  }


  /** 
   * <em>balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalance2IsModified() {
    return balance_2_is_modified;
  }


  /** 
   * <em>ifo_deposit_balance_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositBalance0()
  {
    return ifo_deposit_balance_0;
  }


  /** 
   * <em>ifo_deposit_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalance0IsSet() {
    return ifo_deposit_balance_0_is_set;
  }


  /** 
   * <em>ifo_deposit_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalance0IsModified() {
    return ifo_deposit_balance_0_is_modified;
  }


  /** 
   * <em>ifo_deposit_balance_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositBalance1()
  {
    return ifo_deposit_balance_1;
  }


  /** 
   * <em>ifo_deposit_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalance1IsSet() {
    return ifo_deposit_balance_1_is_set;
  }


  /** 
   * <em>ifo_deposit_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalance1IsModified() {
    return ifo_deposit_balance_1_is_modified;
  }


  /** 
   * <em>ifo_deposit_balance_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositBalance2()
  {
    return ifo_deposit_balance_2;
  }


  /** 
   * <em>ifo_deposit_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalance2IsSet() {
    return ifo_deposit_balance_2_is_set;
  }


  /** 
   * <em>ifo_deposit_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalance2IsModified() {
    return ifo_deposit_balance_2_is_modified;
  }


  /** 
   * <em>ifo_deposit_balance_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositBalanceF0()
  {
    return ifo_deposit_balance_f0;
  }


  /** 
   * <em>ifo_deposit_balance_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalanceF0IsSet() {
    return ifo_deposit_balance_f0_is_set;
  }


  /** 
   * <em>ifo_deposit_balance_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalanceF0IsModified() {
    return ifo_deposit_balance_f0_is_modified;
  }


  /** 
   * <em>ifo_deposit_balance_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositBalanceF1()
  {
    return ifo_deposit_balance_f1;
  }


  /** 
   * <em>ifo_deposit_balance_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalanceF1IsSet() {
    return ifo_deposit_balance_f1_is_set;
  }


  /** 
   * <em>ifo_deposit_balance_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalanceF1IsModified() {
    return ifo_deposit_balance_f1_is_modified;
  }


  /** 
   * <em>ifo_deposit_balance_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositBalanceF2()
  {
    return ifo_deposit_balance_f2;
  }


  /** 
   * <em>ifo_deposit_balance_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalanceF2IsSet() {
    return ifo_deposit_balance_f2_is_set;
  }


  /** 
   * <em>ifo_deposit_balance_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositBalanceF2IsModified() {
    return ifo_deposit_balance_f2_is_modified;
  }


  /** 
   * <em>today_transfer_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayTransferAmt0()
  {
    return today_transfer_amt_0;
  }


  /** 
   * <em>today_transfer_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayTransferAmt0IsSet() {
    return today_transfer_amt_0_is_set;
  }


  /** 
   * <em>today_transfer_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayTransferAmt0IsModified() {
    return today_transfer_amt_0_is_modified;
  }


  /** 
   * <em>today_transfer_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayTransferAmt1()
  {
    return today_transfer_amt_1;
  }


  /** 
   * <em>today_transfer_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayTransferAmt1IsSet() {
    return today_transfer_amt_1_is_set;
  }


  /** 
   * <em>today_transfer_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayTransferAmt1IsModified() {
    return today_transfer_amt_1_is_modified;
  }


  /** 
   * <em>today_transfer_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayTransferAmt2()
  {
    return today_transfer_amt_2;
  }


  /** 
   * <em>today_transfer_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayTransferAmt2IsSet() {
    return today_transfer_amt_2_is_set;
  }


  /** 
   * <em>today_transfer_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayTransferAmt2IsModified() {
    return today_transfer_amt_2_is_modified;
  }


  /** 
   * <em>today_fut_settle_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayFutSettleProfit0()
  {
    return today_fut_settle_profit_0;
  }


  /** 
   * <em>today_fut_settle_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayFutSettleProfit0IsSet() {
    return today_fut_settle_profit_0_is_set;
  }


  /** 
   * <em>today_fut_settle_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayFutSettleProfit0IsModified() {
    return today_fut_settle_profit_0_is_modified;
  }


  /** 
   * <em>today_fut_settle_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayFutSettleProfit1()
  {
    return today_fut_settle_profit_1;
  }


  /** 
   * <em>today_fut_settle_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayFutSettleProfit1IsSet() {
    return today_fut_settle_profit_1_is_set;
  }


  /** 
   * <em>today_fut_settle_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayFutSettleProfit1IsModified() {
    return today_fut_settle_profit_1_is_modified;
  }


  /** 
   * <em>today_fut_settle_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayFutSettleProfit2()
  {
    return today_fut_settle_profit_2;
  }


  /** 
   * <em>today_fut_settle_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayFutSettleProfit2IsSet() {
    return today_fut_settle_profit_2_is_set;
  }


  /** 
   * <em>today_fut_settle_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayFutSettleProfit2IsModified() {
    return today_fut_settle_profit_2_is_modified;
  }


  /** 
   * <em>today_op_net_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayOpNetAmt0()
  {
    return today_op_net_amt_0;
  }


  /** 
   * <em>today_op_net_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayOpNetAmt0IsSet() {
    return today_op_net_amt_0_is_set;
  }


  /** 
   * <em>today_op_net_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayOpNetAmt0IsModified() {
    return today_op_net_amt_0_is_modified;
  }


  /** 
   * <em>today_op_net_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayOpNetAmt1()
  {
    return today_op_net_amt_1;
  }


  /** 
   * <em>today_op_net_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayOpNetAmt1IsSet() {
    return today_op_net_amt_1_is_set;
  }


  /** 
   * <em>today_op_net_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayOpNetAmt1IsModified() {
    return today_op_net_amt_1_is_modified;
  }


  /** 
   * <em>today_op_net_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayOpNetAmt2()
  {
    return today_op_net_amt_2;
  }


  /** 
   * <em>today_op_net_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayOpNetAmt2IsSet() {
    return today_op_net_amt_2_is_set;
  }


  /** 
   * <em>today_op_net_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayOpNetAmt2IsModified() {
    return today_op_net_amt_2_is_modified;
  }


  /** 
   * <em>fut_eval_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutEvalProfit0()
  {
    return fut_eval_profit_0;
  }


  /** 
   * <em>fut_eval_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutEvalProfit0IsSet() {
    return fut_eval_profit_0_is_set;
  }


  /** 
   * <em>fut_eval_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutEvalProfit0IsModified() {
    return fut_eval_profit_0_is_modified;
  }


  /** 
   * <em>fut_eval_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutEvalProfit1()
  {
    return fut_eval_profit_1;
  }


  /** 
   * <em>fut_eval_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutEvalProfit1IsSet() {
    return fut_eval_profit_1_is_set;
  }


  /** 
   * <em>fut_eval_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutEvalProfit1IsModified() {
    return fut_eval_profit_1_is_modified;
  }


  /** 
   * <em>fut_eval_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutEvalProfit2()
  {
    return fut_eval_profit_2;
  }


  /** 
   * <em>fut_eval_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutEvalProfit2IsSet() {
    return fut_eval_profit_2_is_set;
  }


  /** 
   * <em>fut_eval_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutEvalProfit2IsModified() {
    return fut_eval_profit_2_is_modified;
  }


  /** 
   * <em>long_fut_eval_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongFutEvalProfit0()
  {
    return long_fut_eval_profit_0;
  }


  /** 
   * <em>long_fut_eval_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongFutEvalProfit0IsSet() {
    return long_fut_eval_profit_0_is_set;
  }


  /** 
   * <em>long_fut_eval_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongFutEvalProfit0IsModified() {
    return long_fut_eval_profit_0_is_modified;
  }


  /** 
   * <em>long_fut_eval_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongFutEvalProfit1()
  {
    return long_fut_eval_profit_1;
  }


  /** 
   * <em>long_fut_eval_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongFutEvalProfit1IsSet() {
    return long_fut_eval_profit_1_is_set;
  }


  /** 
   * <em>long_fut_eval_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongFutEvalProfit1IsModified() {
    return long_fut_eval_profit_1_is_modified;
  }


  /** 
   * <em>long_fut_eval_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongFutEvalProfit2()
  {
    return long_fut_eval_profit_2;
  }


  /** 
   * <em>long_fut_eval_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongFutEvalProfit2IsSet() {
    return long_fut_eval_profit_2_is_set;
  }


  /** 
   * <em>long_fut_eval_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongFutEvalProfit2IsModified() {
    return long_fut_eval_profit_2_is_modified;
  }


  /** 
   * <em>short_fut_eval_profit_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortFutEvalProfit0()
  {
    return short_fut_eval_profit_0;
  }


  /** 
   * <em>short_fut_eval_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortFutEvalProfit0IsSet() {
    return short_fut_eval_profit_0_is_set;
  }


  /** 
   * <em>short_fut_eval_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortFutEvalProfit0IsModified() {
    return short_fut_eval_profit_0_is_modified;
  }


  /** 
   * <em>short_fut_eval_profit_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortFutEvalProfit1()
  {
    return short_fut_eval_profit_1;
  }


  /** 
   * <em>short_fut_eval_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortFutEvalProfit1IsSet() {
    return short_fut_eval_profit_1_is_set;
  }


  /** 
   * <em>short_fut_eval_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortFutEvalProfit1IsModified() {
    return short_fut_eval_profit_1_is_modified;
  }


  /** 
   * <em>short_fut_eval_profit_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortFutEvalProfit2()
  {
    return short_fut_eval_profit_2;
  }


  /** 
   * <em>short_fut_eval_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortFutEvalProfit2IsSet() {
    return short_fut_eval_profit_2_is_set;
  }


  /** 
   * <em>short_fut_eval_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortFutEvalProfit2IsModified() {
    return short_fut_eval_profit_2_is_modified;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptanceIfoDepositBal0()
  {
    return acceptance_ifo_deposit_bal_0;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBal0IsSet() {
    return acceptance_ifo_deposit_bal_0_is_set;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBal0IsModified() {
    return acceptance_ifo_deposit_bal_0_is_modified;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptanceIfoDepositBal1()
  {
    return acceptance_ifo_deposit_bal_1;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBal1IsSet() {
    return acceptance_ifo_deposit_bal_1_is_set;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBal1IsModified() {
    return acceptance_ifo_deposit_bal_1_is_modified;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptanceIfoDepositBal2()
  {
    return acceptance_ifo_deposit_bal_2;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBal2IsSet() {
    return acceptance_ifo_deposit_bal_2_is_set;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBal2IsModified() {
    return acceptance_ifo_deposit_bal_2_is_modified;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptanceIfoDepositBalF0()
  {
    return acceptance_ifo_deposit_bal_f0;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBalF0IsSet() {
    return acceptance_ifo_deposit_bal_f0_is_set;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBalF0IsModified() {
    return acceptance_ifo_deposit_bal_f0_is_modified;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptanceIfoDepositBalF1()
  {
    return acceptance_ifo_deposit_bal_f1;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBalF1IsSet() {
    return acceptance_ifo_deposit_bal_f1_is_set;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBalF1IsModified() {
    return acceptance_ifo_deposit_bal_f1_is_modified;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptanceIfoDepositBalF2()
  {
    return acceptance_ifo_deposit_bal_f2;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBalF2IsSet() {
    return acceptance_ifo_deposit_bal_f2_is_set;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptanceIfoDepositBalF2IsModified() {
    return acceptance_ifo_deposit_bal_f2_is_modified;
  }


  /** 
   * <em>net_op_value_total_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNetOpValueTotalAmt0()
  {
    return net_op_value_total_amt_0;
  }


  /** 
   * <em>net_op_value_total_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmt0IsSet() {
    return net_op_value_total_amt_0_is_set;
  }


  /** 
   * <em>net_op_value_total_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmt0IsModified() {
    return net_op_value_total_amt_0_is_modified;
  }


  /** 
   * <em>net_op_value_total_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNetOpValueTotalAmt1()
  {
    return net_op_value_total_amt_1;
  }


  /** 
   * <em>net_op_value_total_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmt1IsSet() {
    return net_op_value_total_amt_1_is_set;
  }


  /** 
   * <em>net_op_value_total_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmt1IsModified() {
    return net_op_value_total_amt_1_is_modified;
  }


  /** 
   * <em>net_op_value_total_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNetOpValueTotalAmt2()
  {
    return net_op_value_total_amt_2;
  }


  /** 
   * <em>net_op_value_total_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmt2IsSet() {
    return net_op_value_total_amt_2_is_set;
  }


  /** 
   * <em>net_op_value_total_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmt2IsModified() {
    return net_op_value_total_amt_2_is_modified;
  }


  /** 
   * <em>net_op_value_total_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNetOpValueTotalAmtF0()
  {
    return net_op_value_total_amt_f0;
  }


  /** 
   * <em>net_op_value_total_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmtF0IsSet() {
    return net_op_value_total_amt_f0_is_set;
  }


  /** 
   * <em>net_op_value_total_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmtF0IsModified() {
    return net_op_value_total_amt_f0_is_modified;
  }


  /** 
   * <em>net_op_value_total_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNetOpValueTotalAmtF1()
  {
    return net_op_value_total_amt_f1;
  }


  /** 
   * <em>net_op_value_total_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmtF1IsSet() {
    return net_op_value_total_amt_f1_is_set;
  }


  /** 
   * <em>net_op_value_total_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmtF1IsModified() {
    return net_op_value_total_amt_f1_is_modified;
  }


  /** 
   * <em>net_op_value_total_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNetOpValueTotalAmtF2()
  {
    return net_op_value_total_amt_f2;
  }


  /** 
   * <em>net_op_value_total_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmtF2IsSet() {
    return net_op_value_total_amt_f2_is_set;
  }


  /** 
   * <em>net_op_value_total_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetOpValueTotalAmtF2IsModified() {
    return net_op_value_total_amt_f2_is_modified;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositNecessaryAmt0()
  {
    return ifo_deposit_necessary_amt_0;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmt0IsSet() {
    return ifo_deposit_necessary_amt_0_is_set;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmt0IsModified() {
    return ifo_deposit_necessary_amt_0_is_modified;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositNecessaryAmt1()
  {
    return ifo_deposit_necessary_amt_1;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmt1IsSet() {
    return ifo_deposit_necessary_amt_1_is_set;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmt1IsModified() {
    return ifo_deposit_necessary_amt_1_is_modified;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositNecessaryAmt2()
  {
    return ifo_deposit_necessary_amt_2;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmt2IsSet() {
    return ifo_deposit_necessary_amt_2_is_set;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmt2IsModified() {
    return ifo_deposit_necessary_amt_2_is_modified;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositNecessaryAmtF0()
  {
    return ifo_deposit_necessary_amt_f0;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmtF0IsSet() {
    return ifo_deposit_necessary_amt_f0_is_set;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmtF0IsModified() {
    return ifo_deposit_necessary_amt_f0_is_modified;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositNecessaryAmtF1()
  {
    return ifo_deposit_necessary_amt_f1;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmtF1IsSet() {
    return ifo_deposit_necessary_amt_f1_is_set;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmtF1IsModified() {
    return ifo_deposit_necessary_amt_f1_is_modified;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositNecessaryAmtF2()
  {
    return ifo_deposit_necessary_amt_f2;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmtF2IsSet() {
    return ifo_deposit_necessary_amt_f2_is_set;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositNecessaryAmtF2IsModified() {
    return ifo_deposit_necessary_amt_f2_is_modified;
  }


  /** 
   * <em>ifo_deposit_power_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositPower0()
  {
    return ifo_deposit_power_0;
  }


  /** 
   * <em>ifo_deposit_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPower0IsSet() {
    return ifo_deposit_power_0_is_set;
  }


  /** 
   * <em>ifo_deposit_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPower0IsModified() {
    return ifo_deposit_power_0_is_modified;
  }


  /** 
   * <em>ifo_deposit_power_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositPower1()
  {
    return ifo_deposit_power_1;
  }


  /** 
   * <em>ifo_deposit_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPower1IsSet() {
    return ifo_deposit_power_1_is_set;
  }


  /** 
   * <em>ifo_deposit_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPower1IsModified() {
    return ifo_deposit_power_1_is_modified;
  }


  /** 
   * <em>ifo_deposit_power_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositPower2()
  {
    return ifo_deposit_power_2;
  }


  /** 
   * <em>ifo_deposit_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPower2IsSet() {
    return ifo_deposit_power_2_is_set;
  }


  /** 
   * <em>ifo_deposit_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPower2IsModified() {
    return ifo_deposit_power_2_is_modified;
  }


  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositTransferPower0()
  {
    return ifo_deposit_transfer_power_0;
  }


  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositTransferPower0IsSet() {
    return ifo_deposit_transfer_power_0_is_set;
  }


  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositTransferPower0IsModified() {
    return ifo_deposit_transfer_power_0_is_modified;
  }


  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositTransferPower1()
  {
    return ifo_deposit_transfer_power_1;
  }


  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositTransferPower1IsSet() {
    return ifo_deposit_transfer_power_1_is_set;
  }


  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositTransferPower1IsModified() {
    return ifo_deposit_transfer_power_1_is_modified;
  }


  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositTransferPower2()
  {
    return ifo_deposit_transfer_power_2;
  }


  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositTransferPower2IsSet() {
    return ifo_deposit_transfer_power_2_is_set;
  }


  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositTransferPower2IsModified() {
    return ifo_deposit_transfer_power_2_is_modified;
  }


  /** 
   * <em>bull_quantity_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBullQuantityNk2250()
  {
    return bull_quantity_nk225_0;
  }


  /** 
   * <em>bull_quantity_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk2250IsSet() {
    return bull_quantity_nk225_0_is_set;
  }


  /** 
   * <em>bull_quantity_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk2250IsModified() {
    return bull_quantity_nk225_0_is_modified;
  }


  /** 
   * <em>bull_quantity_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBullQuantityNk2251()
  {
    return bull_quantity_nk225_1;
  }


  /** 
   * <em>bull_quantity_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk2251IsSet() {
    return bull_quantity_nk225_1_is_set;
  }


  /** 
   * <em>bull_quantity_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk2251IsModified() {
    return bull_quantity_nk225_1_is_modified;
  }


  /** 
   * <em>bull_quantity_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBullQuantityNk2252()
  {
    return bull_quantity_nk225_2;
  }


  /** 
   * <em>bull_quantity_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk2252IsSet() {
    return bull_quantity_nk225_2_is_set;
  }


  /** 
   * <em>bull_quantity_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk2252IsModified() {
    return bull_quantity_nk225_2_is_modified;
  }


  /** 
   * <em>bear_quantity_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBearQuantityNk2250()
  {
    return bear_quantity_nk225_0;
  }


  /** 
   * <em>bear_quantity_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk2250IsSet() {
    return bear_quantity_nk225_0_is_set;
  }


  /** 
   * <em>bear_quantity_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk2250IsModified() {
    return bear_quantity_nk225_0_is_modified;
  }


  /** 
   * <em>bear_quantity_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBearQuantityNk2251()
  {
    return bear_quantity_nk225_1;
  }


  /** 
   * <em>bear_quantity_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk2251IsSet() {
    return bear_quantity_nk225_1_is_set;
  }


  /** 
   * <em>bear_quantity_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk2251IsModified() {
    return bear_quantity_nk225_1_is_modified;
  }


  /** 
   * <em>bear_quantity_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBearQuantityNk2252()
  {
    return bear_quantity_nk225_2;
  }


  /** 
   * <em>bear_quantity_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk2252IsSet() {
    return bear_quantity_nk225_2_is_set;
  }


  /** 
   * <em>bear_quantity_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk2252IsModified() {
    return bear_quantity_nk225_2_is_modified;
  }


  /** 
   * <em>long_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongPosNk2250()
  {
    return long_pos_nk225_0;
  }


  /** 
   * <em>long_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk2250IsSet() {
    return long_pos_nk225_0_is_set;
  }


  /** 
   * <em>long_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk2250IsModified() {
    return long_pos_nk225_0_is_modified;
  }


  /** 
   * <em>long_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongPosNk2251()
  {
    return long_pos_nk225_1;
  }


  /** 
   * <em>long_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk2251IsSet() {
    return long_pos_nk225_1_is_set;
  }


  /** 
   * <em>long_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk2251IsModified() {
    return long_pos_nk225_1_is_modified;
  }


  /** 
   * <em>long_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongPosNk2252()
  {
    return long_pos_nk225_2;
  }


  /** 
   * <em>long_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk2252IsSet() {
    return long_pos_nk225_2_is_set;
  }


  /** 
   * <em>long_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk2252IsModified() {
    return long_pos_nk225_2_is_modified;
  }


  /** 
   * <em>part_long_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartLongPosNk2250()
  {
    return part_long_pos_nk225_0;
  }


  /** 
   * <em>part_long_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk2250IsSet() {
    return part_long_pos_nk225_0_is_set;
  }


  /** 
   * <em>part_long_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk2250IsModified() {
    return part_long_pos_nk225_0_is_modified;
  }


  /** 
   * <em>part_long_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartLongPosNk2251()
  {
    return part_long_pos_nk225_1;
  }


  /** 
   * <em>part_long_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk2251IsSet() {
    return part_long_pos_nk225_1_is_set;
  }


  /** 
   * <em>part_long_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk2251IsModified() {
    return part_long_pos_nk225_1_is_modified;
  }


  /** 
   * <em>part_long_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartLongPosNk2252()
  {
    return part_long_pos_nk225_2;
  }


  /** 
   * <em>part_long_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk2252IsSet() {
    return part_long_pos_nk225_2_is_set;
  }


  /** 
   * <em>part_long_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk2252IsModified() {
    return part_long_pos_nk225_2_is_modified;
  }


  /** 
   * <em>short_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortPosNk2250()
  {
    return short_pos_nk225_0;
  }


  /** 
   * <em>short_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk2250IsSet() {
    return short_pos_nk225_0_is_set;
  }


  /** 
   * <em>short_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk2250IsModified() {
    return short_pos_nk225_0_is_modified;
  }


  /** 
   * <em>short_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortPosNk2251()
  {
    return short_pos_nk225_1;
  }


  /** 
   * <em>short_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk2251IsSet() {
    return short_pos_nk225_1_is_set;
  }


  /** 
   * <em>short_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk2251IsModified() {
    return short_pos_nk225_1_is_modified;
  }


  /** 
   * <em>short_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortPosNk2252()
  {
    return short_pos_nk225_2;
  }


  /** 
   * <em>short_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk2252IsSet() {
    return short_pos_nk225_2_is_set;
  }


  /** 
   * <em>short_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk2252IsModified() {
    return short_pos_nk225_2_is_modified;
  }


  /** 
   * <em>part_short_pos_nk225_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartShortPosNk2250()
  {
    return part_short_pos_nk225_0;
  }


  /** 
   * <em>part_short_pos_nk225_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk2250IsSet() {
    return part_short_pos_nk225_0_is_set;
  }


  /** 
   * <em>part_short_pos_nk225_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk2250IsModified() {
    return part_short_pos_nk225_0_is_modified;
  }


  /** 
   * <em>part_short_pos_nk225_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartShortPosNk2251()
  {
    return part_short_pos_nk225_1;
  }


  /** 
   * <em>part_short_pos_nk225_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk2251IsSet() {
    return part_short_pos_nk225_1_is_set;
  }


  /** 
   * <em>part_short_pos_nk225_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk2251IsModified() {
    return part_short_pos_nk225_1_is_modified;
  }


  /** 
   * <em>part_short_pos_nk225_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartShortPosNk2252()
  {
    return part_short_pos_nk225_2;
  }


  /** 
   * <em>part_short_pos_nk225_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk2252IsSet() {
    return part_short_pos_nk225_2_is_set;
  }


  /** 
   * <em>part_short_pos_nk225_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk2252IsModified() {
    return part_short_pos_nk225_2_is_modified;
  }


  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBullQuantityNk225Mini0()
  {
    return bull_quantity_nk225_mini_0;
  }


  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk225Mini0IsSet() {
    return bull_quantity_nk225_mini_0_is_set;
  }


  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk225Mini0IsModified() {
    return bull_quantity_nk225_mini_0_is_modified;
  }


  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBullQuantityNk225Mini1()
  {
    return bull_quantity_nk225_mini_1;
  }


  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk225Mini1IsSet() {
    return bull_quantity_nk225_mini_1_is_set;
  }


  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk225Mini1IsModified() {
    return bull_quantity_nk225_mini_1_is_modified;
  }


  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBullQuantityNk225Mini2()
  {
    return bull_quantity_nk225_mini_2;
  }


  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk225Mini2IsSet() {
    return bull_quantity_nk225_mini_2_is_set;
  }


  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBullQuantityNk225Mini2IsModified() {
    return bull_quantity_nk225_mini_2_is_modified;
  }


  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBearQuantityNk225Mini0()
  {
    return bear_quantity_nk225_mini_0;
  }


  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk225Mini0IsSet() {
    return bear_quantity_nk225_mini_0_is_set;
  }


  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk225Mini0IsModified() {
    return bear_quantity_nk225_mini_0_is_modified;
  }


  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBearQuantityNk225Mini1()
  {
    return bear_quantity_nk225_mini_1;
  }


  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk225Mini1IsSet() {
    return bear_quantity_nk225_mini_1_is_set;
  }


  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk225Mini1IsModified() {
    return bear_quantity_nk225_mini_1_is_modified;
  }


  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBearQuantityNk225Mini2()
  {
    return bear_quantity_nk225_mini_2;
  }


  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk225Mini2IsSet() {
    return bear_quantity_nk225_mini_2_is_set;
  }


  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBearQuantityNk225Mini2IsModified() {
    return bear_quantity_nk225_mini_2_is_modified;
  }


  /** 
   * <em>long_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongPosNk225Mini0()
  {
    return long_pos_nk225_mini_0;
  }


  /** 
   * <em>long_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk225Mini0IsSet() {
    return long_pos_nk225_mini_0_is_set;
  }


  /** 
   * <em>long_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk225Mini0IsModified() {
    return long_pos_nk225_mini_0_is_modified;
  }


  /** 
   * <em>long_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongPosNk225Mini1()
  {
    return long_pos_nk225_mini_1;
  }


  /** 
   * <em>long_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk225Mini1IsSet() {
    return long_pos_nk225_mini_1_is_set;
  }


  /** 
   * <em>long_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk225Mini1IsModified() {
    return long_pos_nk225_mini_1_is_modified;
  }


  /** 
   * <em>long_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLongPosNk225Mini2()
  {
    return long_pos_nk225_mini_2;
  }


  /** 
   * <em>long_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk225Mini2IsSet() {
    return long_pos_nk225_mini_2_is_set;
  }


  /** 
   * <em>long_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLongPosNk225Mini2IsModified() {
    return long_pos_nk225_mini_2_is_modified;
  }


  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartLongPosNk225Mini0()
  {
    return part_long_pos_nk225_mini_0;
  }


  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk225Mini0IsSet() {
    return part_long_pos_nk225_mini_0_is_set;
  }


  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk225Mini0IsModified() {
    return part_long_pos_nk225_mini_0_is_modified;
  }


  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartLongPosNk225Mini1()
  {
    return part_long_pos_nk225_mini_1;
  }


  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk225Mini1IsSet() {
    return part_long_pos_nk225_mini_1_is_set;
  }


  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk225Mini1IsModified() {
    return part_long_pos_nk225_mini_1_is_modified;
  }


  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartLongPosNk225Mini2()
  {
    return part_long_pos_nk225_mini_2;
  }


  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk225Mini2IsSet() {
    return part_long_pos_nk225_mini_2_is_set;
  }


  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartLongPosNk225Mini2IsModified() {
    return part_long_pos_nk225_mini_2_is_modified;
  }


  /** 
   * <em>short_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortPosNk225Mini0()
  {
    return short_pos_nk225_mini_0;
  }


  /** 
   * <em>short_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk225Mini0IsSet() {
    return short_pos_nk225_mini_0_is_set;
  }


  /** 
   * <em>short_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk225Mini0IsModified() {
    return short_pos_nk225_mini_0_is_modified;
  }


  /** 
   * <em>short_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortPosNk225Mini1()
  {
    return short_pos_nk225_mini_1;
  }


  /** 
   * <em>short_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk225Mini1IsSet() {
    return short_pos_nk225_mini_1_is_set;
  }


  /** 
   * <em>short_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk225Mini1IsModified() {
    return short_pos_nk225_mini_1_is_modified;
  }


  /** 
   * <em>short_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShortPosNk225Mini2()
  {
    return short_pos_nk225_mini_2;
  }


  /** 
   * <em>short_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk225Mini2IsSet() {
    return short_pos_nk225_mini_2_is_set;
  }


  /** 
   * <em>short_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShortPosNk225Mini2IsModified() {
    return short_pos_nk225_mini_2_is_modified;
  }


  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartShortPosNk225Mini0()
  {
    return part_short_pos_nk225_mini_0;
  }


  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk225Mini0IsSet() {
    return part_short_pos_nk225_mini_0_is_set;
  }


  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk225Mini0IsModified() {
    return part_short_pos_nk225_mini_0_is_modified;
  }


  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartShortPosNk225Mini1()
  {
    return part_short_pos_nk225_mini_1;
  }


  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk225Mini1IsSet() {
    return part_short_pos_nk225_mini_1_is_set;
  }


  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk225Mini1IsModified() {
    return part_short_pos_nk225_mini_1_is_modified;
  }


  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartShortPosNk225Mini2()
  {
    return part_short_pos_nk225_mini_2;
  }


  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk225Mini2IsSet() {
    return part_short_pos_nk225_mini_2_is_set;
  }


  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartShortPosNk225Mini2IsModified() {
    return part_short_pos_nk225_mini_2_is_modified;
  }


  /** 
   * <em>buy_contract_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyContractAmt0()
  {
    return buy_contract_amt_0;
  }


  /** 
   * <em>buy_contract_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmt0IsSet() {
    return buy_contract_amt_0_is_set;
  }


  /** 
   * <em>buy_contract_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmt0IsModified() {
    return buy_contract_amt_0_is_modified;
  }


  /** 
   * <em>buy_contract_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyContractAmt1()
  {
    return buy_contract_amt_1;
  }


  /** 
   * <em>buy_contract_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmt1IsSet() {
    return buy_contract_amt_1_is_set;
  }


  /** 
   * <em>buy_contract_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmt1IsModified() {
    return buy_contract_amt_1_is_modified;
  }


  /** 
   * <em>buy_contract_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyContractAmt2()
  {
    return buy_contract_amt_2;
  }


  /** 
   * <em>buy_contract_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmt2IsSet() {
    return buy_contract_amt_2_is_set;
  }


  /** 
   * <em>buy_contract_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmt2IsModified() {
    return buy_contract_amt_2_is_modified;
  }


  /** 
   * <em>buy_contract_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyContractAmtF0()
  {
    return buy_contract_amt_f0;
  }


  /** 
   * <em>buy_contract_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmtF0IsSet() {
    return buy_contract_amt_f0_is_set;
  }


  /** 
   * <em>buy_contract_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmtF0IsModified() {
    return buy_contract_amt_f0_is_modified;
  }


  /** 
   * <em>buy_contract_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyContractAmtF1()
  {
    return buy_contract_amt_f1;
  }


  /** 
   * <em>buy_contract_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmtF1IsSet() {
    return buy_contract_amt_f1_is_set;
  }


  /** 
   * <em>buy_contract_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmtF1IsModified() {
    return buy_contract_amt_f1_is_modified;
  }


  /** 
   * <em>buy_contract_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyContractAmtF2()
  {
    return buy_contract_amt_f2;
  }


  /** 
   * <em>buy_contract_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmtF2IsSet() {
    return buy_contract_amt_f2_is_set;
  }


  /** 
   * <em>buy_contract_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyContractAmtF2IsModified() {
    return buy_contract_amt_f2_is_modified;
  }


  /** 
   * <em>sell_contract_amt_0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellContractAmt0()
  {
    return sell_contract_amt_0;
  }


  /** 
   * <em>sell_contract_amt_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmt0IsSet() {
    return sell_contract_amt_0_is_set;
  }


  /** 
   * <em>sell_contract_amt_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmt0IsModified() {
    return sell_contract_amt_0_is_modified;
  }


  /** 
   * <em>sell_contract_amt_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellContractAmt1()
  {
    return sell_contract_amt_1;
  }


  /** 
   * <em>sell_contract_amt_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmt1IsSet() {
    return sell_contract_amt_1_is_set;
  }


  /** 
   * <em>sell_contract_amt_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmt1IsModified() {
    return sell_contract_amt_1_is_modified;
  }


  /** 
   * <em>sell_contract_amt_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellContractAmt2()
  {
    return sell_contract_amt_2;
  }


  /** 
   * <em>sell_contract_amt_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmt2IsSet() {
    return sell_contract_amt_2_is_set;
  }


  /** 
   * <em>sell_contract_amt_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmt2IsModified() {
    return sell_contract_amt_2_is_modified;
  }


  /** 
   * <em>sell_contract_amt_f0</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellContractAmtF0()
  {
    return sell_contract_amt_f0;
  }


  /** 
   * <em>sell_contract_amt_f0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmtF0IsSet() {
    return sell_contract_amt_f0_is_set;
  }


  /** 
   * <em>sell_contract_amt_f0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmtF0IsModified() {
    return sell_contract_amt_f0_is_modified;
  }


  /** 
   * <em>sell_contract_amt_f1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellContractAmtF1()
  {
    return sell_contract_amt_f1;
  }


  /** 
   * <em>sell_contract_amt_f1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmtF1IsSet() {
    return sell_contract_amt_f1_is_set;
  }


  /** 
   * <em>sell_contract_amt_f1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmtF1IsModified() {
    return sell_contract_amt_f1_is_modified;
  }


  /** 
   * <em>sell_contract_amt_f2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellContractAmtF2()
  {
    return sell_contract_amt_f2;
  }


  /** 
   * <em>sell_contract_amt_f2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmtF2IsSet() {
    return sell_contract_amt_f2_is_set;
  }


  /** 
   * <em>sell_contract_amt_f2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellContractAmtF2IsModified() {
    return sell_contract_amt_f2_is_modified;
  }


  /** 
   * <em>non_pay_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNonPayAmt()
  {
    return non_pay_amt;
  }


  /** 
   * <em>non_pay_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNonPayAmtIsSet() {
    return non_pay_amt_is_set;
  }


  /** 
   * <em>non_pay_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNonPayAmtIsModified() {
    return non_pay_amt_is_modified;
  }


  /** 
   * <em>today_claim_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTodayClaimAmt()
  {
    return today_claim_amt;
  }


  /** 
   * <em>today_claim_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayClaimAmtIsSet() {
    return today_claim_amt_is_set;
  }


  /** 
   * <em>today_claim_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayClaimAmtIsModified() {
    return today_claim_amt_is_modified;
  }


  /** 
   * <em>tomorrow_claim_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTomorrowClaimAmt()
  {
    return tomorrow_claim_amt;
  }


  /** 
   * <em>tomorrow_claim_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTomorrowClaimAmtIsSet() {
    return tomorrow_claim_amt_is_set;
  }


  /** 
   * <em>tomorrow_claim_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTomorrowClaimAmtIsModified() {
    return tomorrow_claim_amt_is_modified;
  }


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositInsufficientFlag()
  {
    return ifo_deposit_insufficient_flag;
  }


  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositInsufficientFlagIsSet() {
    return ifo_deposit_insufficient_flag_is_set;
  }


  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositInsufficientFlagIsModified() {
    return ifo_deposit_insufficient_flag_is_modified;
  }


  /** 
   * <em>ifo_deposit_index_nk225</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositIndexNk225()
  {
    return ifo_deposit_index_nk225;
  }


  /** 
   * <em>ifo_deposit_index_nk225</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositIndexNk225IsSet() {
    return ifo_deposit_index_nk225_is_set;
  }


  /** 
   * <em>ifo_deposit_index_nk225</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositIndexNk225IsModified() {
    return ifo_deposit_index_nk225_is_modified;
  }


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoDepositIndexNk225Mini()
  {
    return ifo_deposit_index_nk225_mini;
  }


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositIndexNk225MiniIsSet() {
    return ifo_deposit_index_nk225_mini_is_set;
  }


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositIndexNk225MiniIsModified() {
    return ifo_deposit_index_nk225_mini_is_modified;
  }


  /** 
   * <em>after_tomorrow_claim_amt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAfterTomorrowClaimAmt()
  {
    return after_tomorrow_claim_amt;
  }


  /** 
   * <em>after_tomorrow_claim_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAfterTomorrowClaimAmtIsSet() {
    return after_tomorrow_claim_amt_is_set;
  }


  /** 
   * <em>after_tomorrow_claim_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAfterTomorrowClaimAmtIsModified() {
    return after_tomorrow_claim_amt_is_modified;
  }


  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTomorrowClaimAmtEvening()
  {
    return tomorrow_claim_amt_evening;
  }


  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTomorrowClaimAmtEveningIsSet() {
    return tomorrow_claim_amt_evening_is_set;
  }


  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTomorrowClaimAmtEveningIsModified() {
    return tomorrow_claim_amt_evening_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getStatus()
  {
    return ( status==null? ((int)0): status.intValue() );
  }


  /** 
   * <em>status</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStatusIsNull()
  {
    return status == null;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>error_message</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorMessage()
  {
    return error_message;
  }


  /** 
   * <em>error_message</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorMessageIsSet() {
    return error_message_is_set;
  }


  /** 
   * <em>error_message</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorMessageIsModified() {
    return error_message_is_modified;
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
    return new IfoDepositCalcResultPK(deposit_info_id);
  }


  /** 
   * <em>deposit_info_id</em>カラムの値を設定します。 
   *
   * @@param p_depositInfoId <em>deposit_info_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setDepositInfoId( long p_depositInfoId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_info_id = p_depositInfoId;
    deposit_info_id_is_set = true;
    deposit_info_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>base_date</em>カラムの値を設定します。 
   *
   * @@param p_baseDate <em>base_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setBaseDate( String p_baseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_date = p_baseDate;
    base_date_is_set = true;
    base_date_is_modified = true;
  }


  /** 
   * <em>balance_0</em>カラムの値を設定します。 
   *
   * @@param p_balance0 <em>balance_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBalance0( String p_balance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    balance_0 = p_balance0;
    balance_0_is_set = true;
    balance_0_is_modified = true;
  }


  /** 
   * <em>balance_1</em>カラムの値を設定します。 
   *
   * @@param p_balance1 <em>balance_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBalance1( String p_balance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    balance_1 = p_balance1;
    balance_1_is_set = true;
    balance_1_is_modified = true;
  }


  /** 
   * <em>balance_2</em>カラムの値を設定します。 
   *
   * @@param p_balance2 <em>balance_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBalance2( String p_balance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    balance_2 = p_balance2;
    balance_2_is_set = true;
    balance_2_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_balance_0</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositBalance0 <em>ifo_deposit_balance_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositBalance0( String p_ifoDepositBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_balance_0 = p_ifoDepositBalance0;
    ifo_deposit_balance_0_is_set = true;
    ifo_deposit_balance_0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_balance_1</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositBalance1 <em>ifo_deposit_balance_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositBalance1( String p_ifoDepositBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_balance_1 = p_ifoDepositBalance1;
    ifo_deposit_balance_1_is_set = true;
    ifo_deposit_balance_1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_balance_2</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositBalance2 <em>ifo_deposit_balance_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositBalance2( String p_ifoDepositBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_balance_2 = p_ifoDepositBalance2;
    ifo_deposit_balance_2_is_set = true;
    ifo_deposit_balance_2_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_balance_f0</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositBalanceF0 <em>ifo_deposit_balance_f0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositBalanceF0( String p_ifoDepositBalanceF0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_balance_f0 = p_ifoDepositBalanceF0;
    ifo_deposit_balance_f0_is_set = true;
    ifo_deposit_balance_f0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_balance_f1</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositBalanceF1 <em>ifo_deposit_balance_f1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositBalanceF1( String p_ifoDepositBalanceF1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_balance_f1 = p_ifoDepositBalanceF1;
    ifo_deposit_balance_f1_is_set = true;
    ifo_deposit_balance_f1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_balance_f2</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositBalanceF2 <em>ifo_deposit_balance_f2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositBalanceF2( String p_ifoDepositBalanceF2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_balance_f2 = p_ifoDepositBalanceF2;
    ifo_deposit_balance_f2_is_set = true;
    ifo_deposit_balance_f2_is_modified = true;
  }


  /** 
   * <em>today_transfer_amt_0</em>カラムの値を設定します。 
   *
   * @@param p_todayTransferAmt0 <em>today_transfer_amt_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayTransferAmt0( String p_todayTransferAmt0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_transfer_amt_0 = p_todayTransferAmt0;
    today_transfer_amt_0_is_set = true;
    today_transfer_amt_0_is_modified = true;
  }


  /** 
   * <em>today_transfer_amt_1</em>カラムの値を設定します。 
   *
   * @@param p_todayTransferAmt1 <em>today_transfer_amt_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayTransferAmt1( String p_todayTransferAmt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_transfer_amt_1 = p_todayTransferAmt1;
    today_transfer_amt_1_is_set = true;
    today_transfer_amt_1_is_modified = true;
  }


  /** 
   * <em>today_transfer_amt_2</em>カラムの値を設定します。 
   *
   * @@param p_todayTransferAmt2 <em>today_transfer_amt_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayTransferAmt2( String p_todayTransferAmt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_transfer_amt_2 = p_todayTransferAmt2;
    today_transfer_amt_2_is_set = true;
    today_transfer_amt_2_is_modified = true;
  }


  /** 
   * <em>today_fut_settle_profit_0</em>カラムの値を設定します。 
   *
   * @@param p_todayFutSettleProfit0 <em>today_fut_settle_profit_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayFutSettleProfit0( String p_todayFutSettleProfit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_fut_settle_profit_0 = p_todayFutSettleProfit0;
    today_fut_settle_profit_0_is_set = true;
    today_fut_settle_profit_0_is_modified = true;
  }


  /** 
   * <em>today_fut_settle_profit_1</em>カラムの値を設定します。 
   *
   * @@param p_todayFutSettleProfit1 <em>today_fut_settle_profit_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayFutSettleProfit1( String p_todayFutSettleProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_fut_settle_profit_1 = p_todayFutSettleProfit1;
    today_fut_settle_profit_1_is_set = true;
    today_fut_settle_profit_1_is_modified = true;
  }


  /** 
   * <em>today_fut_settle_profit_2</em>カラムの値を設定します。 
   *
   * @@param p_todayFutSettleProfit2 <em>today_fut_settle_profit_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayFutSettleProfit2( String p_todayFutSettleProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_fut_settle_profit_2 = p_todayFutSettleProfit2;
    today_fut_settle_profit_2_is_set = true;
    today_fut_settle_profit_2_is_modified = true;
  }


  /** 
   * <em>today_op_net_amt_0</em>カラムの値を設定します。 
   *
   * @@param p_todayOpNetAmt0 <em>today_op_net_amt_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayOpNetAmt0( String p_todayOpNetAmt0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_op_net_amt_0 = p_todayOpNetAmt0;
    today_op_net_amt_0_is_set = true;
    today_op_net_amt_0_is_modified = true;
  }


  /** 
   * <em>today_op_net_amt_1</em>カラムの値を設定します。 
   *
   * @@param p_todayOpNetAmt1 <em>today_op_net_amt_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayOpNetAmt1( String p_todayOpNetAmt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_op_net_amt_1 = p_todayOpNetAmt1;
    today_op_net_amt_1_is_set = true;
    today_op_net_amt_1_is_modified = true;
  }


  /** 
   * <em>today_op_net_amt_2</em>カラムの値を設定します。 
   *
   * @@param p_todayOpNetAmt2 <em>today_op_net_amt_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayOpNetAmt2( String p_todayOpNetAmt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_op_net_amt_2 = p_todayOpNetAmt2;
    today_op_net_amt_2_is_set = true;
    today_op_net_amt_2_is_modified = true;
  }


  /** 
   * <em>fut_eval_profit_0</em>カラムの値を設定します。 
   *
   * @@param p_futEvalProfit0 <em>fut_eval_profit_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setFutEvalProfit0( String p_futEvalProfit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fut_eval_profit_0 = p_futEvalProfit0;
    fut_eval_profit_0_is_set = true;
    fut_eval_profit_0_is_modified = true;
  }


  /** 
   * <em>fut_eval_profit_1</em>カラムの値を設定します。 
   *
   * @@param p_futEvalProfit1 <em>fut_eval_profit_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setFutEvalProfit1( String p_futEvalProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fut_eval_profit_1 = p_futEvalProfit1;
    fut_eval_profit_1_is_set = true;
    fut_eval_profit_1_is_modified = true;
  }


  /** 
   * <em>fut_eval_profit_2</em>カラムの値を設定します。 
   *
   * @@param p_futEvalProfit2 <em>fut_eval_profit_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setFutEvalProfit2( String p_futEvalProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fut_eval_profit_2 = p_futEvalProfit2;
    fut_eval_profit_2_is_set = true;
    fut_eval_profit_2_is_modified = true;
  }


  /** 
   * <em>long_fut_eval_profit_0</em>カラムの値を設定します。 
   *
   * @@param p_longFutEvalProfit0 <em>long_fut_eval_profit_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongFutEvalProfit0( String p_longFutEvalProfit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_fut_eval_profit_0 = p_longFutEvalProfit0;
    long_fut_eval_profit_0_is_set = true;
    long_fut_eval_profit_0_is_modified = true;
  }


  /** 
   * <em>long_fut_eval_profit_1</em>カラムの値を設定します。 
   *
   * @@param p_longFutEvalProfit1 <em>long_fut_eval_profit_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongFutEvalProfit1( String p_longFutEvalProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_fut_eval_profit_1 = p_longFutEvalProfit1;
    long_fut_eval_profit_1_is_set = true;
    long_fut_eval_profit_1_is_modified = true;
  }


  /** 
   * <em>long_fut_eval_profit_2</em>カラムの値を設定します。 
   *
   * @@param p_longFutEvalProfit2 <em>long_fut_eval_profit_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongFutEvalProfit2( String p_longFutEvalProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_fut_eval_profit_2 = p_longFutEvalProfit2;
    long_fut_eval_profit_2_is_set = true;
    long_fut_eval_profit_2_is_modified = true;
  }


  /** 
   * <em>short_fut_eval_profit_0</em>カラムの値を設定します。 
   *
   * @@param p_shortFutEvalProfit0 <em>short_fut_eval_profit_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortFutEvalProfit0( String p_shortFutEvalProfit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_fut_eval_profit_0 = p_shortFutEvalProfit0;
    short_fut_eval_profit_0_is_set = true;
    short_fut_eval_profit_0_is_modified = true;
  }


  /** 
   * <em>short_fut_eval_profit_1</em>カラムの値を設定します。 
   *
   * @@param p_shortFutEvalProfit1 <em>short_fut_eval_profit_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortFutEvalProfit1( String p_shortFutEvalProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_fut_eval_profit_1 = p_shortFutEvalProfit1;
    short_fut_eval_profit_1_is_set = true;
    short_fut_eval_profit_1_is_modified = true;
  }


  /** 
   * <em>short_fut_eval_profit_2</em>カラムの値を設定します。 
   *
   * @@param p_shortFutEvalProfit2 <em>short_fut_eval_profit_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortFutEvalProfit2( String p_shortFutEvalProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_fut_eval_profit_2 = p_shortFutEvalProfit2;
    short_fut_eval_profit_2_is_set = true;
    short_fut_eval_profit_2_is_modified = true;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_0</em>カラムの値を設定します。 
   *
   * @@param p_acceptanceIfoDepositBal0 <em>acceptance_ifo_deposit_bal_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAcceptanceIfoDepositBal0( String p_acceptanceIfoDepositBal0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acceptance_ifo_deposit_bal_0 = p_acceptanceIfoDepositBal0;
    acceptance_ifo_deposit_bal_0_is_set = true;
    acceptance_ifo_deposit_bal_0_is_modified = true;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_1</em>カラムの値を設定します。 
   *
   * @@param p_acceptanceIfoDepositBal1 <em>acceptance_ifo_deposit_bal_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAcceptanceIfoDepositBal1( String p_acceptanceIfoDepositBal1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acceptance_ifo_deposit_bal_1 = p_acceptanceIfoDepositBal1;
    acceptance_ifo_deposit_bal_1_is_set = true;
    acceptance_ifo_deposit_bal_1_is_modified = true;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_2</em>カラムの値を設定します。 
   *
   * @@param p_acceptanceIfoDepositBal2 <em>acceptance_ifo_deposit_bal_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAcceptanceIfoDepositBal2( String p_acceptanceIfoDepositBal2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acceptance_ifo_deposit_bal_2 = p_acceptanceIfoDepositBal2;
    acceptance_ifo_deposit_bal_2_is_set = true;
    acceptance_ifo_deposit_bal_2_is_modified = true;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f0</em>カラムの値を設定します。 
   *
   * @@param p_acceptanceIfoDepositBalF0 <em>acceptance_ifo_deposit_bal_f0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAcceptanceIfoDepositBalF0( String p_acceptanceIfoDepositBalF0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acceptance_ifo_deposit_bal_f0 = p_acceptanceIfoDepositBalF0;
    acceptance_ifo_deposit_bal_f0_is_set = true;
    acceptance_ifo_deposit_bal_f0_is_modified = true;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f1</em>カラムの値を設定します。 
   *
   * @@param p_acceptanceIfoDepositBalF1 <em>acceptance_ifo_deposit_bal_f1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAcceptanceIfoDepositBalF1( String p_acceptanceIfoDepositBalF1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acceptance_ifo_deposit_bal_f1 = p_acceptanceIfoDepositBalF1;
    acceptance_ifo_deposit_bal_f1_is_set = true;
    acceptance_ifo_deposit_bal_f1_is_modified = true;
  }


  /** 
   * <em>acceptance_ifo_deposit_bal_f2</em>カラムの値を設定します。 
   *
   * @@param p_acceptanceIfoDepositBalF2 <em>acceptance_ifo_deposit_bal_f2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAcceptanceIfoDepositBalF2( String p_acceptanceIfoDepositBalF2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acceptance_ifo_deposit_bal_f2 = p_acceptanceIfoDepositBalF2;
    acceptance_ifo_deposit_bal_f2_is_set = true;
    acceptance_ifo_deposit_bal_f2_is_modified = true;
  }


  /** 
   * <em>net_op_value_total_amt_0</em>カラムの値を設定します。 
   *
   * @@param p_netOpValueTotalAmt0 <em>net_op_value_total_amt_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setNetOpValueTotalAmt0( String p_netOpValueTotalAmt0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_op_value_total_amt_0 = p_netOpValueTotalAmt0;
    net_op_value_total_amt_0_is_set = true;
    net_op_value_total_amt_0_is_modified = true;
  }


  /** 
   * <em>net_op_value_total_amt_1</em>カラムの値を設定します。 
   *
   * @@param p_netOpValueTotalAmt1 <em>net_op_value_total_amt_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setNetOpValueTotalAmt1( String p_netOpValueTotalAmt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_op_value_total_amt_1 = p_netOpValueTotalAmt1;
    net_op_value_total_amt_1_is_set = true;
    net_op_value_total_amt_1_is_modified = true;
  }


  /** 
   * <em>net_op_value_total_amt_2</em>カラムの値を設定します。 
   *
   * @@param p_netOpValueTotalAmt2 <em>net_op_value_total_amt_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setNetOpValueTotalAmt2( String p_netOpValueTotalAmt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_op_value_total_amt_2 = p_netOpValueTotalAmt2;
    net_op_value_total_amt_2_is_set = true;
    net_op_value_total_amt_2_is_modified = true;
  }


  /** 
   * <em>net_op_value_total_amt_f0</em>カラムの値を設定します。 
   *
   * @@param p_netOpValueTotalAmtF0 <em>net_op_value_total_amt_f0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setNetOpValueTotalAmtF0( String p_netOpValueTotalAmtF0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_op_value_total_amt_f0 = p_netOpValueTotalAmtF0;
    net_op_value_total_amt_f0_is_set = true;
    net_op_value_total_amt_f0_is_modified = true;
  }


  /** 
   * <em>net_op_value_total_amt_f1</em>カラムの値を設定します。 
   *
   * @@param p_netOpValueTotalAmtF1 <em>net_op_value_total_amt_f1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setNetOpValueTotalAmtF1( String p_netOpValueTotalAmtF1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_op_value_total_amt_f1 = p_netOpValueTotalAmtF1;
    net_op_value_total_amt_f1_is_set = true;
    net_op_value_total_amt_f1_is_modified = true;
  }


  /** 
   * <em>net_op_value_total_amt_f2</em>カラムの値を設定します。 
   *
   * @@param p_netOpValueTotalAmtF2 <em>net_op_value_total_amt_f2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setNetOpValueTotalAmtF2( String p_netOpValueTotalAmtF2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_op_value_total_amt_f2 = p_netOpValueTotalAmtF2;
    net_op_value_total_amt_f2_is_set = true;
    net_op_value_total_amt_f2_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_0</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositNecessaryAmt0 <em>ifo_deposit_necessary_amt_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositNecessaryAmt0( String p_ifoDepositNecessaryAmt0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_necessary_amt_0 = p_ifoDepositNecessaryAmt0;
    ifo_deposit_necessary_amt_0_is_set = true;
    ifo_deposit_necessary_amt_0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_1</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositNecessaryAmt1 <em>ifo_deposit_necessary_amt_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositNecessaryAmt1( String p_ifoDepositNecessaryAmt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_necessary_amt_1 = p_ifoDepositNecessaryAmt1;
    ifo_deposit_necessary_amt_1_is_set = true;
    ifo_deposit_necessary_amt_1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_2</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositNecessaryAmt2 <em>ifo_deposit_necessary_amt_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositNecessaryAmt2( String p_ifoDepositNecessaryAmt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_necessary_amt_2 = p_ifoDepositNecessaryAmt2;
    ifo_deposit_necessary_amt_2_is_set = true;
    ifo_deposit_necessary_amt_2_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f0</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositNecessaryAmtF0 <em>ifo_deposit_necessary_amt_f0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositNecessaryAmtF0( String p_ifoDepositNecessaryAmtF0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_necessary_amt_f0 = p_ifoDepositNecessaryAmtF0;
    ifo_deposit_necessary_amt_f0_is_set = true;
    ifo_deposit_necessary_amt_f0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f1</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositNecessaryAmtF1 <em>ifo_deposit_necessary_amt_f1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositNecessaryAmtF1( String p_ifoDepositNecessaryAmtF1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_necessary_amt_f1 = p_ifoDepositNecessaryAmtF1;
    ifo_deposit_necessary_amt_f1_is_set = true;
    ifo_deposit_necessary_amt_f1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_necessary_amt_f2</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositNecessaryAmtF2 <em>ifo_deposit_necessary_amt_f2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositNecessaryAmtF2( String p_ifoDepositNecessaryAmtF2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_necessary_amt_f2 = p_ifoDepositNecessaryAmtF2;
    ifo_deposit_necessary_amt_f2_is_set = true;
    ifo_deposit_necessary_amt_f2_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_power_0</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositPower0 <em>ifo_deposit_power_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositPower0( String p_ifoDepositPower0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_power_0 = p_ifoDepositPower0;
    ifo_deposit_power_0_is_set = true;
    ifo_deposit_power_0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_power_1</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositPower1 <em>ifo_deposit_power_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositPower1( String p_ifoDepositPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_power_1 = p_ifoDepositPower1;
    ifo_deposit_power_1_is_set = true;
    ifo_deposit_power_1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_power_2</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositPower2 <em>ifo_deposit_power_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositPower2( String p_ifoDepositPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_power_2 = p_ifoDepositPower2;
    ifo_deposit_power_2_is_set = true;
    ifo_deposit_power_2_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_transfer_power_0</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositTransferPower0 <em>ifo_deposit_transfer_power_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositTransferPower0( String p_ifoDepositTransferPower0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_transfer_power_0 = p_ifoDepositTransferPower0;
    ifo_deposit_transfer_power_0_is_set = true;
    ifo_deposit_transfer_power_0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_transfer_power_1</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositTransferPower1 <em>ifo_deposit_transfer_power_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositTransferPower1( String p_ifoDepositTransferPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_transfer_power_1 = p_ifoDepositTransferPower1;
    ifo_deposit_transfer_power_1_is_set = true;
    ifo_deposit_transfer_power_1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_transfer_power_2</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositTransferPower2 <em>ifo_deposit_transfer_power_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositTransferPower2( String p_ifoDepositTransferPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_transfer_power_2 = p_ifoDepositTransferPower2;
    ifo_deposit_transfer_power_2_is_set = true;
    ifo_deposit_transfer_power_2_is_modified = true;
  }


  /** 
   * <em>bull_quantity_nk225_0</em>カラムの値を設定します。 
   *
   * @@param p_bullQuantityNk2250 <em>bull_quantity_nk225_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBullQuantityNk2250( String p_bullQuantityNk2250 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bull_quantity_nk225_0 = p_bullQuantityNk2250;
    bull_quantity_nk225_0_is_set = true;
    bull_quantity_nk225_0_is_modified = true;
  }


  /** 
   * <em>bull_quantity_nk225_1</em>カラムの値を設定します。 
   *
   * @@param p_bullQuantityNk2251 <em>bull_quantity_nk225_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBullQuantityNk2251( String p_bullQuantityNk2251 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bull_quantity_nk225_1 = p_bullQuantityNk2251;
    bull_quantity_nk225_1_is_set = true;
    bull_quantity_nk225_1_is_modified = true;
  }


  /** 
   * <em>bull_quantity_nk225_2</em>カラムの値を設定します。 
   *
   * @@param p_bullQuantityNk2252 <em>bull_quantity_nk225_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBullQuantityNk2252( String p_bullQuantityNk2252 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bull_quantity_nk225_2 = p_bullQuantityNk2252;
    bull_quantity_nk225_2_is_set = true;
    bull_quantity_nk225_2_is_modified = true;
  }


  /** 
   * <em>bear_quantity_nk225_0</em>カラムの値を設定します。 
   *
   * @@param p_bearQuantityNk2250 <em>bear_quantity_nk225_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBearQuantityNk2250( String p_bearQuantityNk2250 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bear_quantity_nk225_0 = p_bearQuantityNk2250;
    bear_quantity_nk225_0_is_set = true;
    bear_quantity_nk225_0_is_modified = true;
  }


  /** 
   * <em>bear_quantity_nk225_1</em>カラムの値を設定します。 
   *
   * @@param p_bearQuantityNk2251 <em>bear_quantity_nk225_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBearQuantityNk2251( String p_bearQuantityNk2251 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bear_quantity_nk225_1 = p_bearQuantityNk2251;
    bear_quantity_nk225_1_is_set = true;
    bear_quantity_nk225_1_is_modified = true;
  }


  /** 
   * <em>bear_quantity_nk225_2</em>カラムの値を設定します。 
   *
   * @@param p_bearQuantityNk2252 <em>bear_quantity_nk225_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBearQuantityNk2252( String p_bearQuantityNk2252 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bear_quantity_nk225_2 = p_bearQuantityNk2252;
    bear_quantity_nk225_2_is_set = true;
    bear_quantity_nk225_2_is_modified = true;
  }


  /** 
   * <em>long_pos_nk225_0</em>カラムの値を設定します。 
   *
   * @@param p_longPosNk2250 <em>long_pos_nk225_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongPosNk2250( String p_longPosNk2250 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_pos_nk225_0 = p_longPosNk2250;
    long_pos_nk225_0_is_set = true;
    long_pos_nk225_0_is_modified = true;
  }


  /** 
   * <em>long_pos_nk225_1</em>カラムの値を設定します。 
   *
   * @@param p_longPosNk2251 <em>long_pos_nk225_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongPosNk2251( String p_longPosNk2251 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_pos_nk225_1 = p_longPosNk2251;
    long_pos_nk225_1_is_set = true;
    long_pos_nk225_1_is_modified = true;
  }


  /** 
   * <em>long_pos_nk225_2</em>カラムの値を設定します。 
   *
   * @@param p_longPosNk2252 <em>long_pos_nk225_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongPosNk2252( String p_longPosNk2252 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_pos_nk225_2 = p_longPosNk2252;
    long_pos_nk225_2_is_set = true;
    long_pos_nk225_2_is_modified = true;
  }


  /** 
   * <em>part_long_pos_nk225_0</em>カラムの値を設定します。 
   *
   * @@param p_partLongPosNk2250 <em>part_long_pos_nk225_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartLongPosNk2250( String p_partLongPosNk2250 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_long_pos_nk225_0 = p_partLongPosNk2250;
    part_long_pos_nk225_0_is_set = true;
    part_long_pos_nk225_0_is_modified = true;
  }


  /** 
   * <em>part_long_pos_nk225_1</em>カラムの値を設定します。 
   *
   * @@param p_partLongPosNk2251 <em>part_long_pos_nk225_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartLongPosNk2251( String p_partLongPosNk2251 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_long_pos_nk225_1 = p_partLongPosNk2251;
    part_long_pos_nk225_1_is_set = true;
    part_long_pos_nk225_1_is_modified = true;
  }


  /** 
   * <em>part_long_pos_nk225_2</em>カラムの値を設定します。 
   *
   * @@param p_partLongPosNk2252 <em>part_long_pos_nk225_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartLongPosNk2252( String p_partLongPosNk2252 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_long_pos_nk225_2 = p_partLongPosNk2252;
    part_long_pos_nk225_2_is_set = true;
    part_long_pos_nk225_2_is_modified = true;
  }


  /** 
   * <em>short_pos_nk225_0</em>カラムの値を設定します。 
   *
   * @@param p_shortPosNk2250 <em>short_pos_nk225_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortPosNk2250( String p_shortPosNk2250 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_pos_nk225_0 = p_shortPosNk2250;
    short_pos_nk225_0_is_set = true;
    short_pos_nk225_0_is_modified = true;
  }


  /** 
   * <em>short_pos_nk225_1</em>カラムの値を設定します。 
   *
   * @@param p_shortPosNk2251 <em>short_pos_nk225_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortPosNk2251( String p_shortPosNk2251 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_pos_nk225_1 = p_shortPosNk2251;
    short_pos_nk225_1_is_set = true;
    short_pos_nk225_1_is_modified = true;
  }


  /** 
   * <em>short_pos_nk225_2</em>カラムの値を設定します。 
   *
   * @@param p_shortPosNk2252 <em>short_pos_nk225_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortPosNk2252( String p_shortPosNk2252 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_pos_nk225_2 = p_shortPosNk2252;
    short_pos_nk225_2_is_set = true;
    short_pos_nk225_2_is_modified = true;
  }


  /** 
   * <em>part_short_pos_nk225_0</em>カラムの値を設定します。 
   *
   * @@param p_partShortPosNk2250 <em>part_short_pos_nk225_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartShortPosNk2250( String p_partShortPosNk2250 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_short_pos_nk225_0 = p_partShortPosNk2250;
    part_short_pos_nk225_0_is_set = true;
    part_short_pos_nk225_0_is_modified = true;
  }


  /** 
   * <em>part_short_pos_nk225_1</em>カラムの値を設定します。 
   *
   * @@param p_partShortPosNk2251 <em>part_short_pos_nk225_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartShortPosNk2251( String p_partShortPosNk2251 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_short_pos_nk225_1 = p_partShortPosNk2251;
    part_short_pos_nk225_1_is_set = true;
    part_short_pos_nk225_1_is_modified = true;
  }


  /** 
   * <em>part_short_pos_nk225_2</em>カラムの値を設定します。 
   *
   * @@param p_partShortPosNk2252 <em>part_short_pos_nk225_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartShortPosNk2252( String p_partShortPosNk2252 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_short_pos_nk225_2 = p_partShortPosNk2252;
    part_short_pos_nk225_2_is_set = true;
    part_short_pos_nk225_2_is_modified = true;
  }


  /** 
   * <em>bull_quantity_nk225_mini_0</em>カラムの値を設定します。 
   *
   * @@param p_bullQuantityNk225Mini0 <em>bull_quantity_nk225_mini_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBullQuantityNk225Mini0( String p_bullQuantityNk225Mini0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bull_quantity_nk225_mini_0 = p_bullQuantityNk225Mini0;
    bull_quantity_nk225_mini_0_is_set = true;
    bull_quantity_nk225_mini_0_is_modified = true;
  }


  /** 
   * <em>bull_quantity_nk225_mini_1</em>カラムの値を設定します。 
   *
   * @@param p_bullQuantityNk225Mini1 <em>bull_quantity_nk225_mini_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBullQuantityNk225Mini1( String p_bullQuantityNk225Mini1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bull_quantity_nk225_mini_1 = p_bullQuantityNk225Mini1;
    bull_quantity_nk225_mini_1_is_set = true;
    bull_quantity_nk225_mini_1_is_modified = true;
  }


  /** 
   * <em>bull_quantity_nk225_mini_2</em>カラムの値を設定します。 
   *
   * @@param p_bullQuantityNk225Mini2 <em>bull_quantity_nk225_mini_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBullQuantityNk225Mini2( String p_bullQuantityNk225Mini2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bull_quantity_nk225_mini_2 = p_bullQuantityNk225Mini2;
    bull_quantity_nk225_mini_2_is_set = true;
    bull_quantity_nk225_mini_2_is_modified = true;
  }


  /** 
   * <em>bear_quantity_nk225_mini_0</em>カラムの値を設定します。 
   *
   * @@param p_bearQuantityNk225Mini0 <em>bear_quantity_nk225_mini_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBearQuantityNk225Mini0( String p_bearQuantityNk225Mini0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bear_quantity_nk225_mini_0 = p_bearQuantityNk225Mini0;
    bear_quantity_nk225_mini_0_is_set = true;
    bear_quantity_nk225_mini_0_is_modified = true;
  }


  /** 
   * <em>bear_quantity_nk225_mini_1</em>カラムの値を設定します。 
   *
   * @@param p_bearQuantityNk225Mini1 <em>bear_quantity_nk225_mini_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBearQuantityNk225Mini1( String p_bearQuantityNk225Mini1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bear_quantity_nk225_mini_1 = p_bearQuantityNk225Mini1;
    bear_quantity_nk225_mini_1_is_set = true;
    bear_quantity_nk225_mini_1_is_modified = true;
  }


  /** 
   * <em>bear_quantity_nk225_mini_2</em>カラムの値を設定します。 
   *
   * @@param p_bearQuantityNk225Mini2 <em>bear_quantity_nk225_mini_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBearQuantityNk225Mini2( String p_bearQuantityNk225Mini2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bear_quantity_nk225_mini_2 = p_bearQuantityNk225Mini2;
    bear_quantity_nk225_mini_2_is_set = true;
    bear_quantity_nk225_mini_2_is_modified = true;
  }


  /** 
   * <em>long_pos_nk225_mini_0</em>カラムの値を設定します。 
   *
   * @@param p_longPosNk225Mini0 <em>long_pos_nk225_mini_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongPosNk225Mini0( String p_longPosNk225Mini0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_pos_nk225_mini_0 = p_longPosNk225Mini0;
    long_pos_nk225_mini_0_is_set = true;
    long_pos_nk225_mini_0_is_modified = true;
  }


  /** 
   * <em>long_pos_nk225_mini_1</em>カラムの値を設定します。 
   *
   * @@param p_longPosNk225Mini1 <em>long_pos_nk225_mini_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongPosNk225Mini1( String p_longPosNk225Mini1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_pos_nk225_mini_1 = p_longPosNk225Mini1;
    long_pos_nk225_mini_1_is_set = true;
    long_pos_nk225_mini_1_is_modified = true;
  }


  /** 
   * <em>long_pos_nk225_mini_2</em>カラムの値を設定します。 
   *
   * @@param p_longPosNk225Mini2 <em>long_pos_nk225_mini_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setLongPosNk225Mini2( String p_longPosNk225Mini2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    long_pos_nk225_mini_2 = p_longPosNk225Mini2;
    long_pos_nk225_mini_2_is_set = true;
    long_pos_nk225_mini_2_is_modified = true;
  }


  /** 
   * <em>part_long_pos_nk225_mini_0</em>カラムの値を設定します。 
   *
   * @@param p_partLongPosNk225Mini0 <em>part_long_pos_nk225_mini_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartLongPosNk225Mini0( String p_partLongPosNk225Mini0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_long_pos_nk225_mini_0 = p_partLongPosNk225Mini0;
    part_long_pos_nk225_mini_0_is_set = true;
    part_long_pos_nk225_mini_0_is_modified = true;
  }


  /** 
   * <em>part_long_pos_nk225_mini_1</em>カラムの値を設定します。 
   *
   * @@param p_partLongPosNk225Mini1 <em>part_long_pos_nk225_mini_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartLongPosNk225Mini1( String p_partLongPosNk225Mini1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_long_pos_nk225_mini_1 = p_partLongPosNk225Mini1;
    part_long_pos_nk225_mini_1_is_set = true;
    part_long_pos_nk225_mini_1_is_modified = true;
  }


  /** 
   * <em>part_long_pos_nk225_mini_2</em>カラムの値を設定します。 
   *
   * @@param p_partLongPosNk225Mini2 <em>part_long_pos_nk225_mini_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartLongPosNk225Mini2( String p_partLongPosNk225Mini2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_long_pos_nk225_mini_2 = p_partLongPosNk225Mini2;
    part_long_pos_nk225_mini_2_is_set = true;
    part_long_pos_nk225_mini_2_is_modified = true;
  }


  /** 
   * <em>short_pos_nk225_mini_0</em>カラムの値を設定します。 
   *
   * @@param p_shortPosNk225Mini0 <em>short_pos_nk225_mini_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortPosNk225Mini0( String p_shortPosNk225Mini0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_pos_nk225_mini_0 = p_shortPosNk225Mini0;
    short_pos_nk225_mini_0_is_set = true;
    short_pos_nk225_mini_0_is_modified = true;
  }


  /** 
   * <em>short_pos_nk225_mini_1</em>カラムの値を設定します。 
   *
   * @@param p_shortPosNk225Mini1 <em>short_pos_nk225_mini_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortPosNk225Mini1( String p_shortPosNk225Mini1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_pos_nk225_mini_1 = p_shortPosNk225Mini1;
    short_pos_nk225_mini_1_is_set = true;
    short_pos_nk225_mini_1_is_modified = true;
  }


  /** 
   * <em>short_pos_nk225_mini_2</em>カラムの値を設定します。 
   *
   * @@param p_shortPosNk225Mini2 <em>short_pos_nk225_mini_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setShortPosNk225Mini2( String p_shortPosNk225Mini2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_pos_nk225_mini_2 = p_shortPosNk225Mini2;
    short_pos_nk225_mini_2_is_set = true;
    short_pos_nk225_mini_2_is_modified = true;
  }


  /** 
   * <em>part_short_pos_nk225_mini_0</em>カラムの値を設定します。 
   *
   * @@param p_partShortPosNk225Mini0 <em>part_short_pos_nk225_mini_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartShortPosNk225Mini0( String p_partShortPosNk225Mini0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_short_pos_nk225_mini_0 = p_partShortPosNk225Mini0;
    part_short_pos_nk225_mini_0_is_set = true;
    part_short_pos_nk225_mini_0_is_modified = true;
  }


  /** 
   * <em>part_short_pos_nk225_mini_1</em>カラムの値を設定します。 
   *
   * @@param p_partShortPosNk225Mini1 <em>part_short_pos_nk225_mini_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartShortPosNk225Mini1( String p_partShortPosNk225Mini1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_short_pos_nk225_mini_1 = p_partShortPosNk225Mini1;
    part_short_pos_nk225_mini_1_is_set = true;
    part_short_pos_nk225_mini_1_is_modified = true;
  }


  /** 
   * <em>part_short_pos_nk225_mini_2</em>カラムの値を設定します。 
   *
   * @@param p_partShortPosNk225Mini2 <em>part_short_pos_nk225_mini_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPartShortPosNk225Mini2( String p_partShortPosNk225Mini2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    part_short_pos_nk225_mini_2 = p_partShortPosNk225Mini2;
    part_short_pos_nk225_mini_2_is_set = true;
    part_short_pos_nk225_mini_2_is_modified = true;
  }


  /** 
   * <em>buy_contract_amt_0</em>カラムの値を設定します。 
   *
   * @@param p_buyContractAmt0 <em>buy_contract_amt_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBuyContractAmt0( String p_buyContractAmt0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_contract_amt_0 = p_buyContractAmt0;
    buy_contract_amt_0_is_set = true;
    buy_contract_amt_0_is_modified = true;
  }


  /** 
   * <em>buy_contract_amt_1</em>カラムの値を設定します。 
   *
   * @@param p_buyContractAmt1 <em>buy_contract_amt_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBuyContractAmt1( String p_buyContractAmt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_contract_amt_1 = p_buyContractAmt1;
    buy_contract_amt_1_is_set = true;
    buy_contract_amt_1_is_modified = true;
  }


  /** 
   * <em>buy_contract_amt_2</em>カラムの値を設定します。 
   *
   * @@param p_buyContractAmt2 <em>buy_contract_amt_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBuyContractAmt2( String p_buyContractAmt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_contract_amt_2 = p_buyContractAmt2;
    buy_contract_amt_2_is_set = true;
    buy_contract_amt_2_is_modified = true;
  }


  /** 
   * <em>buy_contract_amt_f0</em>カラムの値を設定します。 
   *
   * @@param p_buyContractAmtF0 <em>buy_contract_amt_f0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBuyContractAmtF0( String p_buyContractAmtF0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_contract_amt_f0 = p_buyContractAmtF0;
    buy_contract_amt_f0_is_set = true;
    buy_contract_amt_f0_is_modified = true;
  }


  /** 
   * <em>buy_contract_amt_f1</em>カラムの値を設定します。 
   *
   * @@param p_buyContractAmtF1 <em>buy_contract_amt_f1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBuyContractAmtF1( String p_buyContractAmtF1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_contract_amt_f1 = p_buyContractAmtF1;
    buy_contract_amt_f1_is_set = true;
    buy_contract_amt_f1_is_modified = true;
  }


  /** 
   * <em>buy_contract_amt_f2</em>カラムの値を設定します。 
   *
   * @@param p_buyContractAmtF2 <em>buy_contract_amt_f2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setBuyContractAmtF2( String p_buyContractAmtF2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_contract_amt_f2 = p_buyContractAmtF2;
    buy_contract_amt_f2_is_set = true;
    buy_contract_amt_f2_is_modified = true;
  }


  /** 
   * <em>sell_contract_amt_0</em>カラムの値を設定します。 
   *
   * @@param p_sellContractAmt0 <em>sell_contract_amt_0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setSellContractAmt0( String p_sellContractAmt0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_contract_amt_0 = p_sellContractAmt0;
    sell_contract_amt_0_is_set = true;
    sell_contract_amt_0_is_modified = true;
  }


  /** 
   * <em>sell_contract_amt_1</em>カラムの値を設定します。 
   *
   * @@param p_sellContractAmt1 <em>sell_contract_amt_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setSellContractAmt1( String p_sellContractAmt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_contract_amt_1 = p_sellContractAmt1;
    sell_contract_amt_1_is_set = true;
    sell_contract_amt_1_is_modified = true;
  }


  /** 
   * <em>sell_contract_amt_2</em>カラムの値を設定します。 
   *
   * @@param p_sellContractAmt2 <em>sell_contract_amt_2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setSellContractAmt2( String p_sellContractAmt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_contract_amt_2 = p_sellContractAmt2;
    sell_contract_amt_2_is_set = true;
    sell_contract_amt_2_is_modified = true;
  }


  /** 
   * <em>sell_contract_amt_f0</em>カラムの値を設定します。 
   *
   * @@param p_sellContractAmtF0 <em>sell_contract_amt_f0</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setSellContractAmtF0( String p_sellContractAmtF0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_contract_amt_f0 = p_sellContractAmtF0;
    sell_contract_amt_f0_is_set = true;
    sell_contract_amt_f0_is_modified = true;
  }


  /** 
   * <em>sell_contract_amt_f1</em>カラムの値を設定します。 
   *
   * @@param p_sellContractAmtF1 <em>sell_contract_amt_f1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setSellContractAmtF1( String p_sellContractAmtF1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_contract_amt_f1 = p_sellContractAmtF1;
    sell_contract_amt_f1_is_set = true;
    sell_contract_amt_f1_is_modified = true;
  }


  /** 
   * <em>sell_contract_amt_f2</em>カラムの値を設定します。 
   *
   * @@param p_sellContractAmtF2 <em>sell_contract_amt_f2</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setSellContractAmtF2( String p_sellContractAmtF2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_contract_amt_f2 = p_sellContractAmtF2;
    sell_contract_amt_f2_is_set = true;
    sell_contract_amt_f2_is_modified = true;
  }


  /** 
   * <em>non_pay_amt</em>カラムの値を設定します。 
   *
   * @@param p_nonPayAmt <em>non_pay_amt</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setNonPayAmt( String p_nonPayAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    non_pay_amt = p_nonPayAmt;
    non_pay_amt_is_set = true;
    non_pay_amt_is_modified = true;
  }


  /** 
   * <em>today_claim_amt</em>カラムの値を設定します。 
   *
   * @@param p_todayClaimAmt <em>today_claim_amt</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTodayClaimAmt( String p_todayClaimAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_claim_amt = p_todayClaimAmt;
    today_claim_amt_is_set = true;
    today_claim_amt_is_modified = true;
  }


  /** 
   * <em>tomorrow_claim_amt</em>カラムの値を設定します。 
   *
   * @@param p_tomorrowClaimAmt <em>tomorrow_claim_amt</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTomorrowClaimAmt( String p_tomorrowClaimAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tomorrow_claim_amt = p_tomorrowClaimAmt;
    tomorrow_claim_amt_is_set = true;
    tomorrow_claim_amt_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_insufficient_flag</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositInsufficientFlag <em>ifo_deposit_insufficient_flag</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositInsufficientFlag( String p_ifoDepositInsufficientFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_insufficient_flag = p_ifoDepositInsufficientFlag;
    ifo_deposit_insufficient_flag_is_set = true;
    ifo_deposit_insufficient_flag_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_index_nk225</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositIndexNk225 <em>ifo_deposit_index_nk225</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositIndexNk225( String p_ifoDepositIndexNk225 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_index_nk225 = p_ifoDepositIndexNk225;
    ifo_deposit_index_nk225_is_set = true;
    ifo_deposit_index_nk225_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_index_nk225_mini</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositIndexNk225Mini <em>ifo_deposit_index_nk225_mini</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setIfoDepositIndexNk225Mini( String p_ifoDepositIndexNk225Mini )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_index_nk225_mini = p_ifoDepositIndexNk225Mini;
    ifo_deposit_index_nk225_mini_is_set = true;
    ifo_deposit_index_nk225_mini_is_modified = true;
  }


  /** 
   * <em>after_tomorrow_claim_amt</em>カラムの値を設定します。 
   *
   * @@param p_afterTomorrowClaimAmt <em>after_tomorrow_claim_amt</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAfterTomorrowClaimAmt( String p_afterTomorrowClaimAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    after_tomorrow_claim_amt = p_afterTomorrowClaimAmt;
    after_tomorrow_claim_amt_is_set = true;
    after_tomorrow_claim_amt_is_modified = true;
  }


  /** 
   * <em>tomorrow_claim_amt_evening</em>カラムの値を設定します。 
   *
   * @@param p_tomorrowClaimAmtEvening <em>tomorrow_claim_amt_evening</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setTomorrowClaimAmtEvening( String p_tomorrowClaimAmtEvening )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tomorrow_claim_amt_evening = p_tomorrowClaimAmtEvening;
    tomorrow_claim_amt_evening_is_set = true;
    tomorrow_claim_amt_evening_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setStatus( int p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = new Integer(p_status);
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>status</em>カラムに値を設定します。 
   */
  public final void setStatus( Integer p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>error_message</em>カラムの値を設定します。 
   *
   * @@param p_errorMessage <em>error_message</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setErrorMessage( String p_errorMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_message = p_errorMessage;
    error_message_is_set = true;
    error_message_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_0") ) {
                    return this.acceptance_ifo_deposit_bal_0;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_1") ) {
                    return this.acceptance_ifo_deposit_bal_1;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_2") ) {
                    return this.acceptance_ifo_deposit_bal_2;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_f0") ) {
                    return this.acceptance_ifo_deposit_bal_f0;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_f1") ) {
                    return this.acceptance_ifo_deposit_bal_f1;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_f2") ) {
                    return this.acceptance_ifo_deposit_bal_f2;
                }
                else if ( name.equals("after_tomorrow_claim_amt") ) {
                    return this.after_tomorrow_claim_amt;
                }
                break;
            case 'b':
                if ( name.equals("base_date") ) {
                    return this.base_date;
                }
                else if ( name.equals("balance_0") ) {
                    return this.balance_0;
                }
                else if ( name.equals("balance_1") ) {
                    return this.balance_1;
                }
                else if ( name.equals("balance_2") ) {
                    return this.balance_2;
                }
                else if ( name.equals("bull_quantity_nk225_0") ) {
                    return this.bull_quantity_nk225_0;
                }
                else if ( name.equals("bull_quantity_nk225_1") ) {
                    return this.bull_quantity_nk225_1;
                }
                else if ( name.equals("bull_quantity_nk225_2") ) {
                    return this.bull_quantity_nk225_2;
                }
                else if ( name.equals("bear_quantity_nk225_0") ) {
                    return this.bear_quantity_nk225_0;
                }
                else if ( name.equals("bear_quantity_nk225_1") ) {
                    return this.bear_quantity_nk225_1;
                }
                else if ( name.equals("bear_quantity_nk225_2") ) {
                    return this.bear_quantity_nk225_2;
                }
                else if ( name.equals("bull_quantity_nk225_mini_0") ) {
                    return this.bull_quantity_nk225_mini_0;
                }
                else if ( name.equals("bull_quantity_nk225_mini_1") ) {
                    return this.bull_quantity_nk225_mini_1;
                }
                else if ( name.equals("bull_quantity_nk225_mini_2") ) {
                    return this.bull_quantity_nk225_mini_2;
                }
                else if ( name.equals("bear_quantity_nk225_mini_0") ) {
                    return this.bear_quantity_nk225_mini_0;
                }
                else if ( name.equals("bear_quantity_nk225_mini_1") ) {
                    return this.bear_quantity_nk225_mini_1;
                }
                else if ( name.equals("bear_quantity_nk225_mini_2") ) {
                    return this.bear_quantity_nk225_mini_2;
                }
                else if ( name.equals("buy_contract_amt_0") ) {
                    return this.buy_contract_amt_0;
                }
                else if ( name.equals("buy_contract_amt_1") ) {
                    return this.buy_contract_amt_1;
                }
                else if ( name.equals("buy_contract_amt_2") ) {
                    return this.buy_contract_amt_2;
                }
                else if ( name.equals("buy_contract_amt_f0") ) {
                    return this.buy_contract_amt_f0;
                }
                else if ( name.equals("buy_contract_amt_f1") ) {
                    return this.buy_contract_amt_f1;
                }
                else if ( name.equals("buy_contract_amt_f2") ) {
                    return this.buy_contract_amt_f2;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("deposit_info_id") ) {
                    return new Long( deposit_info_id );
                }
                else if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("error_message") ) {
                    return this.error_message;
                }
                break;
            case 'f':
                if ( name.equals("fut_eval_profit_0") ) {
                    return this.fut_eval_profit_0;
                }
                else if ( name.equals("fut_eval_profit_1") ) {
                    return this.fut_eval_profit_1;
                }
                else if ( name.equals("fut_eval_profit_2") ) {
                    return this.fut_eval_profit_2;
                }
                break;
            case 'i':
                if ( name.equals("ifo_deposit_balance_0") ) {
                    return this.ifo_deposit_balance_0;
                }
                else if ( name.equals("ifo_deposit_balance_1") ) {
                    return this.ifo_deposit_balance_1;
                }
                else if ( name.equals("ifo_deposit_balance_2") ) {
                    return this.ifo_deposit_balance_2;
                }
                else if ( name.equals("ifo_deposit_balance_f0") ) {
                    return this.ifo_deposit_balance_f0;
                }
                else if ( name.equals("ifo_deposit_balance_f1") ) {
                    return this.ifo_deposit_balance_f1;
                }
                else if ( name.equals("ifo_deposit_balance_f2") ) {
                    return this.ifo_deposit_balance_f2;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_0") ) {
                    return this.ifo_deposit_necessary_amt_0;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_1") ) {
                    return this.ifo_deposit_necessary_amt_1;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_2") ) {
                    return this.ifo_deposit_necessary_amt_2;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_f0") ) {
                    return this.ifo_deposit_necessary_amt_f0;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_f1") ) {
                    return this.ifo_deposit_necessary_amt_f1;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_f2") ) {
                    return this.ifo_deposit_necessary_amt_f2;
                }
                else if ( name.equals("ifo_deposit_power_0") ) {
                    return this.ifo_deposit_power_0;
                }
                else if ( name.equals("ifo_deposit_power_1") ) {
                    return this.ifo_deposit_power_1;
                }
                else if ( name.equals("ifo_deposit_power_2") ) {
                    return this.ifo_deposit_power_2;
                }
                else if ( name.equals("ifo_deposit_transfer_power_0") ) {
                    return this.ifo_deposit_transfer_power_0;
                }
                else if ( name.equals("ifo_deposit_transfer_power_1") ) {
                    return this.ifo_deposit_transfer_power_1;
                }
                else if ( name.equals("ifo_deposit_transfer_power_2") ) {
                    return this.ifo_deposit_transfer_power_2;
                }
                else if ( name.equals("ifo_deposit_insufficient_flag") ) {
                    return this.ifo_deposit_insufficient_flag;
                }
                else if ( name.equals("ifo_deposit_index_nk225") ) {
                    return this.ifo_deposit_index_nk225;
                }
                else if ( name.equals("ifo_deposit_index_nk225_mini") ) {
                    return this.ifo_deposit_index_nk225_mini;
                }
                break;
            case 'l':
                if ( name.equals("long_fut_eval_profit_0") ) {
                    return this.long_fut_eval_profit_0;
                }
                else if ( name.equals("long_fut_eval_profit_1") ) {
                    return this.long_fut_eval_profit_1;
                }
                else if ( name.equals("long_fut_eval_profit_2") ) {
                    return this.long_fut_eval_profit_2;
                }
                else if ( name.equals("long_pos_nk225_0") ) {
                    return this.long_pos_nk225_0;
                }
                else if ( name.equals("long_pos_nk225_1") ) {
                    return this.long_pos_nk225_1;
                }
                else if ( name.equals("long_pos_nk225_2") ) {
                    return this.long_pos_nk225_2;
                }
                else if ( name.equals("long_pos_nk225_mini_0") ) {
                    return this.long_pos_nk225_mini_0;
                }
                else if ( name.equals("long_pos_nk225_mini_1") ) {
                    return this.long_pos_nk225_mini_1;
                }
                else if ( name.equals("long_pos_nk225_mini_2") ) {
                    return this.long_pos_nk225_mini_2;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("net_op_value_total_amt_0") ) {
                    return this.net_op_value_total_amt_0;
                }
                else if ( name.equals("net_op_value_total_amt_1") ) {
                    return this.net_op_value_total_amt_1;
                }
                else if ( name.equals("net_op_value_total_amt_2") ) {
                    return this.net_op_value_total_amt_2;
                }
                else if ( name.equals("net_op_value_total_amt_f0") ) {
                    return this.net_op_value_total_amt_f0;
                }
                else if ( name.equals("net_op_value_total_amt_f1") ) {
                    return this.net_op_value_total_amt_f1;
                }
                else if ( name.equals("net_op_value_total_amt_f2") ) {
                    return this.net_op_value_total_amt_f2;
                }
                else if ( name.equals("non_pay_amt") ) {
                    return this.non_pay_amt;
                }
                break;
            case 'p':
                if ( name.equals("part_long_pos_nk225_0") ) {
                    return this.part_long_pos_nk225_0;
                }
                else if ( name.equals("part_long_pos_nk225_1") ) {
                    return this.part_long_pos_nk225_1;
                }
                else if ( name.equals("part_long_pos_nk225_2") ) {
                    return this.part_long_pos_nk225_2;
                }
                else if ( name.equals("part_short_pos_nk225_0") ) {
                    return this.part_short_pos_nk225_0;
                }
                else if ( name.equals("part_short_pos_nk225_1") ) {
                    return this.part_short_pos_nk225_1;
                }
                else if ( name.equals("part_short_pos_nk225_2") ) {
                    return this.part_short_pos_nk225_2;
                }
                else if ( name.equals("part_long_pos_nk225_mini_0") ) {
                    return this.part_long_pos_nk225_mini_0;
                }
                else if ( name.equals("part_long_pos_nk225_mini_1") ) {
                    return this.part_long_pos_nk225_mini_1;
                }
                else if ( name.equals("part_long_pos_nk225_mini_2") ) {
                    return this.part_long_pos_nk225_mini_2;
                }
                else if ( name.equals("part_short_pos_nk225_mini_0") ) {
                    return this.part_short_pos_nk225_mini_0;
                }
                else if ( name.equals("part_short_pos_nk225_mini_1") ) {
                    return this.part_short_pos_nk225_mini_1;
                }
                else if ( name.equals("part_short_pos_nk225_mini_2") ) {
                    return this.part_short_pos_nk225_mini_2;
                }
                break;
            case 's':
                if ( name.equals("short_fut_eval_profit_0") ) {
                    return this.short_fut_eval_profit_0;
                }
                else if ( name.equals("short_fut_eval_profit_1") ) {
                    return this.short_fut_eval_profit_1;
                }
                else if ( name.equals("short_fut_eval_profit_2") ) {
                    return this.short_fut_eval_profit_2;
                }
                else if ( name.equals("short_pos_nk225_0") ) {
                    return this.short_pos_nk225_0;
                }
                else if ( name.equals("short_pos_nk225_1") ) {
                    return this.short_pos_nk225_1;
                }
                else if ( name.equals("short_pos_nk225_2") ) {
                    return this.short_pos_nk225_2;
                }
                else if ( name.equals("short_pos_nk225_mini_0") ) {
                    return this.short_pos_nk225_mini_0;
                }
                else if ( name.equals("short_pos_nk225_mini_1") ) {
                    return this.short_pos_nk225_mini_1;
                }
                else if ( name.equals("short_pos_nk225_mini_2") ) {
                    return this.short_pos_nk225_mini_2;
                }
                else if ( name.equals("sell_contract_amt_0") ) {
                    return this.sell_contract_amt_0;
                }
                else if ( name.equals("sell_contract_amt_1") ) {
                    return this.sell_contract_amt_1;
                }
                else if ( name.equals("sell_contract_amt_2") ) {
                    return this.sell_contract_amt_2;
                }
                else if ( name.equals("sell_contract_amt_f0") ) {
                    return this.sell_contract_amt_f0;
                }
                else if ( name.equals("sell_contract_amt_f1") ) {
                    return this.sell_contract_amt_f1;
                }
                else if ( name.equals("sell_contract_amt_f2") ) {
                    return this.sell_contract_amt_f2;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("today_transfer_amt_0") ) {
                    return this.today_transfer_amt_0;
                }
                else if ( name.equals("today_transfer_amt_1") ) {
                    return this.today_transfer_amt_1;
                }
                else if ( name.equals("today_transfer_amt_2") ) {
                    return this.today_transfer_amt_2;
                }
                else if ( name.equals("today_fut_settle_profit_0") ) {
                    return this.today_fut_settle_profit_0;
                }
                else if ( name.equals("today_fut_settle_profit_1") ) {
                    return this.today_fut_settle_profit_1;
                }
                else if ( name.equals("today_fut_settle_profit_2") ) {
                    return this.today_fut_settle_profit_2;
                }
                else if ( name.equals("today_op_net_amt_0") ) {
                    return this.today_op_net_amt_0;
                }
                else if ( name.equals("today_op_net_amt_1") ) {
                    return this.today_op_net_amt_1;
                }
                else if ( name.equals("today_op_net_amt_2") ) {
                    return this.today_op_net_amt_2;
                }
                else if ( name.equals("today_claim_amt") ) {
                    return this.today_claim_amt;
                }
                else if ( name.equals("tomorrow_claim_amt") ) {
                    return this.tomorrow_claim_amt;
                }
                else if ( name.equals("tomorrow_claim_amt_evening") ) {
                    return this.tomorrow_claim_amt_evening;
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
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acceptance_ifo_deposit_bal_0' must be String: '"+value+"' is not." );
                    this.acceptance_ifo_deposit_bal_0 = (String) value;
                    if (this.acceptance_ifo_deposit_bal_0_is_set)
                        this.acceptance_ifo_deposit_bal_0_is_modified = true;
                    this.acceptance_ifo_deposit_bal_0_is_set = true;
                    return;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acceptance_ifo_deposit_bal_1' must be String: '"+value+"' is not." );
                    this.acceptance_ifo_deposit_bal_1 = (String) value;
                    if (this.acceptance_ifo_deposit_bal_1_is_set)
                        this.acceptance_ifo_deposit_bal_1_is_modified = true;
                    this.acceptance_ifo_deposit_bal_1_is_set = true;
                    return;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acceptance_ifo_deposit_bal_2' must be String: '"+value+"' is not." );
                    this.acceptance_ifo_deposit_bal_2 = (String) value;
                    if (this.acceptance_ifo_deposit_bal_2_is_set)
                        this.acceptance_ifo_deposit_bal_2_is_modified = true;
                    this.acceptance_ifo_deposit_bal_2_is_set = true;
                    return;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_f0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acceptance_ifo_deposit_bal_f0' must be String: '"+value+"' is not." );
                    this.acceptance_ifo_deposit_bal_f0 = (String) value;
                    if (this.acceptance_ifo_deposit_bal_f0_is_set)
                        this.acceptance_ifo_deposit_bal_f0_is_modified = true;
                    this.acceptance_ifo_deposit_bal_f0_is_set = true;
                    return;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_f1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acceptance_ifo_deposit_bal_f1' must be String: '"+value+"' is not." );
                    this.acceptance_ifo_deposit_bal_f1 = (String) value;
                    if (this.acceptance_ifo_deposit_bal_f1_is_set)
                        this.acceptance_ifo_deposit_bal_f1_is_modified = true;
                    this.acceptance_ifo_deposit_bal_f1_is_set = true;
                    return;
                }
                else if ( name.equals("acceptance_ifo_deposit_bal_f2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acceptance_ifo_deposit_bal_f2' must be String: '"+value+"' is not." );
                    this.acceptance_ifo_deposit_bal_f2 = (String) value;
                    if (this.acceptance_ifo_deposit_bal_f2_is_set)
                        this.acceptance_ifo_deposit_bal_f2_is_modified = true;
                    this.acceptance_ifo_deposit_bal_f2_is_set = true;
                    return;
                }
                else if ( name.equals("after_tomorrow_claim_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'after_tomorrow_claim_amt' must be String: '"+value+"' is not." );
                    this.after_tomorrow_claim_amt = (String) value;
                    if (this.after_tomorrow_claim_amt_is_set)
                        this.after_tomorrow_claim_amt_is_modified = true;
                    this.after_tomorrow_claim_amt_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("base_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'base_date' must be String: '"+value+"' is not." );
                    this.base_date = (String) value;
                    if (this.base_date_is_set)
                        this.base_date_is_modified = true;
                    this.base_date_is_set = true;
                    return;
                }
                else if ( name.equals("balance_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'balance_0' must be String: '"+value+"' is not." );
                    this.balance_0 = (String) value;
                    if (this.balance_0_is_set)
                        this.balance_0_is_modified = true;
                    this.balance_0_is_set = true;
                    return;
                }
                else if ( name.equals("balance_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'balance_1' must be String: '"+value+"' is not." );
                    this.balance_1 = (String) value;
                    if (this.balance_1_is_set)
                        this.balance_1_is_modified = true;
                    this.balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("balance_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'balance_2' must be String: '"+value+"' is not." );
                    this.balance_2 = (String) value;
                    if (this.balance_2_is_set)
                        this.balance_2_is_modified = true;
                    this.balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("bull_quantity_nk225_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bull_quantity_nk225_0' must be String: '"+value+"' is not." );
                    this.bull_quantity_nk225_0 = (String) value;
                    if (this.bull_quantity_nk225_0_is_set)
                        this.bull_quantity_nk225_0_is_modified = true;
                    this.bull_quantity_nk225_0_is_set = true;
                    return;
                }
                else if ( name.equals("bull_quantity_nk225_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bull_quantity_nk225_1' must be String: '"+value+"' is not." );
                    this.bull_quantity_nk225_1 = (String) value;
                    if (this.bull_quantity_nk225_1_is_set)
                        this.bull_quantity_nk225_1_is_modified = true;
                    this.bull_quantity_nk225_1_is_set = true;
                    return;
                }
                else if ( name.equals("bull_quantity_nk225_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bull_quantity_nk225_2' must be String: '"+value+"' is not." );
                    this.bull_quantity_nk225_2 = (String) value;
                    if (this.bull_quantity_nk225_2_is_set)
                        this.bull_quantity_nk225_2_is_modified = true;
                    this.bull_quantity_nk225_2_is_set = true;
                    return;
                }
                else if ( name.equals("bear_quantity_nk225_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bear_quantity_nk225_0' must be String: '"+value+"' is not." );
                    this.bear_quantity_nk225_0 = (String) value;
                    if (this.bear_quantity_nk225_0_is_set)
                        this.bear_quantity_nk225_0_is_modified = true;
                    this.bear_quantity_nk225_0_is_set = true;
                    return;
                }
                else if ( name.equals("bear_quantity_nk225_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bear_quantity_nk225_1' must be String: '"+value+"' is not." );
                    this.bear_quantity_nk225_1 = (String) value;
                    if (this.bear_quantity_nk225_1_is_set)
                        this.bear_quantity_nk225_1_is_modified = true;
                    this.bear_quantity_nk225_1_is_set = true;
                    return;
                }
                else if ( name.equals("bear_quantity_nk225_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bear_quantity_nk225_2' must be String: '"+value+"' is not." );
                    this.bear_quantity_nk225_2 = (String) value;
                    if (this.bear_quantity_nk225_2_is_set)
                        this.bear_quantity_nk225_2_is_modified = true;
                    this.bear_quantity_nk225_2_is_set = true;
                    return;
                }
                else if ( name.equals("bull_quantity_nk225_mini_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bull_quantity_nk225_mini_0' must be String: '"+value+"' is not." );
                    this.bull_quantity_nk225_mini_0 = (String) value;
                    if (this.bull_quantity_nk225_mini_0_is_set)
                        this.bull_quantity_nk225_mini_0_is_modified = true;
                    this.bull_quantity_nk225_mini_0_is_set = true;
                    return;
                }
                else if ( name.equals("bull_quantity_nk225_mini_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bull_quantity_nk225_mini_1' must be String: '"+value+"' is not." );
                    this.bull_quantity_nk225_mini_1 = (String) value;
                    if (this.bull_quantity_nk225_mini_1_is_set)
                        this.bull_quantity_nk225_mini_1_is_modified = true;
                    this.bull_quantity_nk225_mini_1_is_set = true;
                    return;
                }
                else if ( name.equals("bull_quantity_nk225_mini_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bull_quantity_nk225_mini_2' must be String: '"+value+"' is not." );
                    this.bull_quantity_nk225_mini_2 = (String) value;
                    if (this.bull_quantity_nk225_mini_2_is_set)
                        this.bull_quantity_nk225_mini_2_is_modified = true;
                    this.bull_quantity_nk225_mini_2_is_set = true;
                    return;
                }
                else if ( name.equals("bear_quantity_nk225_mini_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bear_quantity_nk225_mini_0' must be String: '"+value+"' is not." );
                    this.bear_quantity_nk225_mini_0 = (String) value;
                    if (this.bear_quantity_nk225_mini_0_is_set)
                        this.bear_quantity_nk225_mini_0_is_modified = true;
                    this.bear_quantity_nk225_mini_0_is_set = true;
                    return;
                }
                else if ( name.equals("bear_quantity_nk225_mini_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bear_quantity_nk225_mini_1' must be String: '"+value+"' is not." );
                    this.bear_quantity_nk225_mini_1 = (String) value;
                    if (this.bear_quantity_nk225_mini_1_is_set)
                        this.bear_quantity_nk225_mini_1_is_modified = true;
                    this.bear_quantity_nk225_mini_1_is_set = true;
                    return;
                }
                else if ( name.equals("bear_quantity_nk225_mini_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bear_quantity_nk225_mini_2' must be String: '"+value+"' is not." );
                    this.bear_quantity_nk225_mini_2 = (String) value;
                    if (this.bear_quantity_nk225_mini_2_is_set)
                        this.bear_quantity_nk225_mini_2_is_modified = true;
                    this.bear_quantity_nk225_mini_2_is_set = true;
                    return;
                }
                else if ( name.equals("buy_contract_amt_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_contract_amt_0' must be String: '"+value+"' is not." );
                    this.buy_contract_amt_0 = (String) value;
                    if (this.buy_contract_amt_0_is_set)
                        this.buy_contract_amt_0_is_modified = true;
                    this.buy_contract_amt_0_is_set = true;
                    return;
                }
                else if ( name.equals("buy_contract_amt_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_contract_amt_1' must be String: '"+value+"' is not." );
                    this.buy_contract_amt_1 = (String) value;
                    if (this.buy_contract_amt_1_is_set)
                        this.buy_contract_amt_1_is_modified = true;
                    this.buy_contract_amt_1_is_set = true;
                    return;
                }
                else if ( name.equals("buy_contract_amt_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_contract_amt_2' must be String: '"+value+"' is not." );
                    this.buy_contract_amt_2 = (String) value;
                    if (this.buy_contract_amt_2_is_set)
                        this.buy_contract_amt_2_is_modified = true;
                    this.buy_contract_amt_2_is_set = true;
                    return;
                }
                else if ( name.equals("buy_contract_amt_f0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_contract_amt_f0' must be String: '"+value+"' is not." );
                    this.buy_contract_amt_f0 = (String) value;
                    if (this.buy_contract_amt_f0_is_set)
                        this.buy_contract_amt_f0_is_modified = true;
                    this.buy_contract_amt_f0_is_set = true;
                    return;
                }
                else if ( name.equals("buy_contract_amt_f1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_contract_amt_f1' must be String: '"+value+"' is not." );
                    this.buy_contract_amt_f1 = (String) value;
                    if (this.buy_contract_amt_f1_is_set)
                        this.buy_contract_amt_f1_is_modified = true;
                    this.buy_contract_amt_f1_is_set = true;
                    return;
                }
                else if ( name.equals("buy_contract_amt_f2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_contract_amt_f2' must be String: '"+value+"' is not." );
                    this.buy_contract_amt_f2 = (String) value;
                    if (this.buy_contract_amt_f2_is_set)
                        this.buy_contract_amt_f2_is_modified = true;
                    this.buy_contract_amt_f2_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("deposit_info_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'deposit_info_id' must be Long: '"+value+"' is not." );
                    this.deposit_info_id = ((Long) value).longValue();
                    if (this.deposit_info_id_is_set)
                        this.deposit_info_id_is_modified = true;
                    this.deposit_info_id_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("error_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_message' must be String: '"+value+"' is not." );
                    this.error_message = (String) value;
                    if (this.error_message_is_set)
                        this.error_message_is_modified = true;
                    this.error_message_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fut_eval_profit_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fut_eval_profit_0' must be String: '"+value+"' is not." );
                    this.fut_eval_profit_0 = (String) value;
                    if (this.fut_eval_profit_0_is_set)
                        this.fut_eval_profit_0_is_modified = true;
                    this.fut_eval_profit_0_is_set = true;
                    return;
                }
                else if ( name.equals("fut_eval_profit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fut_eval_profit_1' must be String: '"+value+"' is not." );
                    this.fut_eval_profit_1 = (String) value;
                    if (this.fut_eval_profit_1_is_set)
                        this.fut_eval_profit_1_is_modified = true;
                    this.fut_eval_profit_1_is_set = true;
                    return;
                }
                else if ( name.equals("fut_eval_profit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fut_eval_profit_2' must be String: '"+value+"' is not." );
                    this.fut_eval_profit_2 = (String) value;
                    if (this.fut_eval_profit_2_is_set)
                        this.fut_eval_profit_2_is_modified = true;
                    this.fut_eval_profit_2_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("ifo_deposit_balance_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_balance_0' must be String: '"+value+"' is not." );
                    this.ifo_deposit_balance_0 = (String) value;
                    if (this.ifo_deposit_balance_0_is_set)
                        this.ifo_deposit_balance_0_is_modified = true;
                    this.ifo_deposit_balance_0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_balance_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_balance_1' must be String: '"+value+"' is not." );
                    this.ifo_deposit_balance_1 = (String) value;
                    if (this.ifo_deposit_balance_1_is_set)
                        this.ifo_deposit_balance_1_is_modified = true;
                    this.ifo_deposit_balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_balance_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_balance_2' must be String: '"+value+"' is not." );
                    this.ifo_deposit_balance_2 = (String) value;
                    if (this.ifo_deposit_balance_2_is_set)
                        this.ifo_deposit_balance_2_is_modified = true;
                    this.ifo_deposit_balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_balance_f0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_balance_f0' must be String: '"+value+"' is not." );
                    this.ifo_deposit_balance_f0 = (String) value;
                    if (this.ifo_deposit_balance_f0_is_set)
                        this.ifo_deposit_balance_f0_is_modified = true;
                    this.ifo_deposit_balance_f0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_balance_f1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_balance_f1' must be String: '"+value+"' is not." );
                    this.ifo_deposit_balance_f1 = (String) value;
                    if (this.ifo_deposit_balance_f1_is_set)
                        this.ifo_deposit_balance_f1_is_modified = true;
                    this.ifo_deposit_balance_f1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_balance_f2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_balance_f2' must be String: '"+value+"' is not." );
                    this.ifo_deposit_balance_f2 = (String) value;
                    if (this.ifo_deposit_balance_f2_is_set)
                        this.ifo_deposit_balance_f2_is_modified = true;
                    this.ifo_deposit_balance_f2_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_necessary_amt_0' must be String: '"+value+"' is not." );
                    this.ifo_deposit_necessary_amt_0 = (String) value;
                    if (this.ifo_deposit_necessary_amt_0_is_set)
                        this.ifo_deposit_necessary_amt_0_is_modified = true;
                    this.ifo_deposit_necessary_amt_0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_necessary_amt_1' must be String: '"+value+"' is not." );
                    this.ifo_deposit_necessary_amt_1 = (String) value;
                    if (this.ifo_deposit_necessary_amt_1_is_set)
                        this.ifo_deposit_necessary_amt_1_is_modified = true;
                    this.ifo_deposit_necessary_amt_1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_necessary_amt_2' must be String: '"+value+"' is not." );
                    this.ifo_deposit_necessary_amt_2 = (String) value;
                    if (this.ifo_deposit_necessary_amt_2_is_set)
                        this.ifo_deposit_necessary_amt_2_is_modified = true;
                    this.ifo_deposit_necessary_amt_2_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_f0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_necessary_amt_f0' must be String: '"+value+"' is not." );
                    this.ifo_deposit_necessary_amt_f0 = (String) value;
                    if (this.ifo_deposit_necessary_amt_f0_is_set)
                        this.ifo_deposit_necessary_amt_f0_is_modified = true;
                    this.ifo_deposit_necessary_amt_f0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_f1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_necessary_amt_f1' must be String: '"+value+"' is not." );
                    this.ifo_deposit_necessary_amt_f1 = (String) value;
                    if (this.ifo_deposit_necessary_amt_f1_is_set)
                        this.ifo_deposit_necessary_amt_f1_is_modified = true;
                    this.ifo_deposit_necessary_amt_f1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_necessary_amt_f2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_necessary_amt_f2' must be String: '"+value+"' is not." );
                    this.ifo_deposit_necessary_amt_f2 = (String) value;
                    if (this.ifo_deposit_necessary_amt_f2_is_set)
                        this.ifo_deposit_necessary_amt_f2_is_modified = true;
                    this.ifo_deposit_necessary_amt_f2_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_power_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_power_0' must be String: '"+value+"' is not." );
                    this.ifo_deposit_power_0 = (String) value;
                    if (this.ifo_deposit_power_0_is_set)
                        this.ifo_deposit_power_0_is_modified = true;
                    this.ifo_deposit_power_0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_power_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_power_1' must be String: '"+value+"' is not." );
                    this.ifo_deposit_power_1 = (String) value;
                    if (this.ifo_deposit_power_1_is_set)
                        this.ifo_deposit_power_1_is_modified = true;
                    this.ifo_deposit_power_1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_power_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_power_2' must be String: '"+value+"' is not." );
                    this.ifo_deposit_power_2 = (String) value;
                    if (this.ifo_deposit_power_2_is_set)
                        this.ifo_deposit_power_2_is_modified = true;
                    this.ifo_deposit_power_2_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_transfer_power_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_transfer_power_0' must be String: '"+value+"' is not." );
                    this.ifo_deposit_transfer_power_0 = (String) value;
                    if (this.ifo_deposit_transfer_power_0_is_set)
                        this.ifo_deposit_transfer_power_0_is_modified = true;
                    this.ifo_deposit_transfer_power_0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_transfer_power_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_transfer_power_1' must be String: '"+value+"' is not." );
                    this.ifo_deposit_transfer_power_1 = (String) value;
                    if (this.ifo_deposit_transfer_power_1_is_set)
                        this.ifo_deposit_transfer_power_1_is_modified = true;
                    this.ifo_deposit_transfer_power_1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_transfer_power_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_transfer_power_2' must be String: '"+value+"' is not." );
                    this.ifo_deposit_transfer_power_2 = (String) value;
                    if (this.ifo_deposit_transfer_power_2_is_set)
                        this.ifo_deposit_transfer_power_2_is_modified = true;
                    this.ifo_deposit_transfer_power_2_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_insufficient_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_insufficient_flag' must be String: '"+value+"' is not." );
                    this.ifo_deposit_insufficient_flag = (String) value;
                    if (this.ifo_deposit_insufficient_flag_is_set)
                        this.ifo_deposit_insufficient_flag_is_modified = true;
                    this.ifo_deposit_insufficient_flag_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_index_nk225") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_index_nk225' must be String: '"+value+"' is not." );
                    this.ifo_deposit_index_nk225 = (String) value;
                    if (this.ifo_deposit_index_nk225_is_set)
                        this.ifo_deposit_index_nk225_is_modified = true;
                    this.ifo_deposit_index_nk225_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_index_nk225_mini") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_index_nk225_mini' must be String: '"+value+"' is not." );
                    this.ifo_deposit_index_nk225_mini = (String) value;
                    if (this.ifo_deposit_index_nk225_mini_is_set)
                        this.ifo_deposit_index_nk225_mini_is_modified = true;
                    this.ifo_deposit_index_nk225_mini_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("long_fut_eval_profit_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_fut_eval_profit_0' must be String: '"+value+"' is not." );
                    this.long_fut_eval_profit_0 = (String) value;
                    if (this.long_fut_eval_profit_0_is_set)
                        this.long_fut_eval_profit_0_is_modified = true;
                    this.long_fut_eval_profit_0_is_set = true;
                    return;
                }
                else if ( name.equals("long_fut_eval_profit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_fut_eval_profit_1' must be String: '"+value+"' is not." );
                    this.long_fut_eval_profit_1 = (String) value;
                    if (this.long_fut_eval_profit_1_is_set)
                        this.long_fut_eval_profit_1_is_modified = true;
                    this.long_fut_eval_profit_1_is_set = true;
                    return;
                }
                else if ( name.equals("long_fut_eval_profit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_fut_eval_profit_2' must be String: '"+value+"' is not." );
                    this.long_fut_eval_profit_2 = (String) value;
                    if (this.long_fut_eval_profit_2_is_set)
                        this.long_fut_eval_profit_2_is_modified = true;
                    this.long_fut_eval_profit_2_is_set = true;
                    return;
                }
                else if ( name.equals("long_pos_nk225_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_pos_nk225_0' must be String: '"+value+"' is not." );
                    this.long_pos_nk225_0 = (String) value;
                    if (this.long_pos_nk225_0_is_set)
                        this.long_pos_nk225_0_is_modified = true;
                    this.long_pos_nk225_0_is_set = true;
                    return;
                }
                else if ( name.equals("long_pos_nk225_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_pos_nk225_1' must be String: '"+value+"' is not." );
                    this.long_pos_nk225_1 = (String) value;
                    if (this.long_pos_nk225_1_is_set)
                        this.long_pos_nk225_1_is_modified = true;
                    this.long_pos_nk225_1_is_set = true;
                    return;
                }
                else if ( name.equals("long_pos_nk225_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_pos_nk225_2' must be String: '"+value+"' is not." );
                    this.long_pos_nk225_2 = (String) value;
                    if (this.long_pos_nk225_2_is_set)
                        this.long_pos_nk225_2_is_modified = true;
                    this.long_pos_nk225_2_is_set = true;
                    return;
                }
                else if ( name.equals("long_pos_nk225_mini_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_pos_nk225_mini_0' must be String: '"+value+"' is not." );
                    this.long_pos_nk225_mini_0 = (String) value;
                    if (this.long_pos_nk225_mini_0_is_set)
                        this.long_pos_nk225_mini_0_is_modified = true;
                    this.long_pos_nk225_mini_0_is_set = true;
                    return;
                }
                else if ( name.equals("long_pos_nk225_mini_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_pos_nk225_mini_1' must be String: '"+value+"' is not." );
                    this.long_pos_nk225_mini_1 = (String) value;
                    if (this.long_pos_nk225_mini_1_is_set)
                        this.long_pos_nk225_mini_1_is_modified = true;
                    this.long_pos_nk225_mini_1_is_set = true;
                    return;
                }
                else if ( name.equals("long_pos_nk225_mini_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'long_pos_nk225_mini_2' must be String: '"+value+"' is not." );
                    this.long_pos_nk225_mini_2 = (String) value;
                    if (this.long_pos_nk225_mini_2_is_set)
                        this.long_pos_nk225_mini_2_is_modified = true;
                    this.long_pos_nk225_mini_2_is_set = true;
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
            case 'n':
                if ( name.equals("net_op_value_total_amt_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'net_op_value_total_amt_0' must be String: '"+value+"' is not." );
                    this.net_op_value_total_amt_0 = (String) value;
                    if (this.net_op_value_total_amt_0_is_set)
                        this.net_op_value_total_amt_0_is_modified = true;
                    this.net_op_value_total_amt_0_is_set = true;
                    return;
                }
                else if ( name.equals("net_op_value_total_amt_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'net_op_value_total_amt_1' must be String: '"+value+"' is not." );
                    this.net_op_value_total_amt_1 = (String) value;
                    if (this.net_op_value_total_amt_1_is_set)
                        this.net_op_value_total_amt_1_is_modified = true;
                    this.net_op_value_total_amt_1_is_set = true;
                    return;
                }
                else if ( name.equals("net_op_value_total_amt_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'net_op_value_total_amt_2' must be String: '"+value+"' is not." );
                    this.net_op_value_total_amt_2 = (String) value;
                    if (this.net_op_value_total_amt_2_is_set)
                        this.net_op_value_total_amt_2_is_modified = true;
                    this.net_op_value_total_amt_2_is_set = true;
                    return;
                }
                else if ( name.equals("net_op_value_total_amt_f0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'net_op_value_total_amt_f0' must be String: '"+value+"' is not." );
                    this.net_op_value_total_amt_f0 = (String) value;
                    if (this.net_op_value_total_amt_f0_is_set)
                        this.net_op_value_total_amt_f0_is_modified = true;
                    this.net_op_value_total_amt_f0_is_set = true;
                    return;
                }
                else if ( name.equals("net_op_value_total_amt_f1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'net_op_value_total_amt_f1' must be String: '"+value+"' is not." );
                    this.net_op_value_total_amt_f1 = (String) value;
                    if (this.net_op_value_total_amt_f1_is_set)
                        this.net_op_value_total_amt_f1_is_modified = true;
                    this.net_op_value_total_amt_f1_is_set = true;
                    return;
                }
                else if ( name.equals("net_op_value_total_amt_f2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'net_op_value_total_amt_f2' must be String: '"+value+"' is not." );
                    this.net_op_value_total_amt_f2 = (String) value;
                    if (this.net_op_value_total_amt_f2_is_set)
                        this.net_op_value_total_amt_f2_is_modified = true;
                    this.net_op_value_total_amt_f2_is_set = true;
                    return;
                }
                else if ( name.equals("non_pay_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'non_pay_amt' must be String: '"+value+"' is not." );
                    this.non_pay_amt = (String) value;
                    if (this.non_pay_amt_is_set)
                        this.non_pay_amt_is_modified = true;
                    this.non_pay_amt_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("part_long_pos_nk225_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_long_pos_nk225_0' must be String: '"+value+"' is not." );
                    this.part_long_pos_nk225_0 = (String) value;
                    if (this.part_long_pos_nk225_0_is_set)
                        this.part_long_pos_nk225_0_is_modified = true;
                    this.part_long_pos_nk225_0_is_set = true;
                    return;
                }
                else if ( name.equals("part_long_pos_nk225_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_long_pos_nk225_1' must be String: '"+value+"' is not." );
                    this.part_long_pos_nk225_1 = (String) value;
                    if (this.part_long_pos_nk225_1_is_set)
                        this.part_long_pos_nk225_1_is_modified = true;
                    this.part_long_pos_nk225_1_is_set = true;
                    return;
                }
                else if ( name.equals("part_long_pos_nk225_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_long_pos_nk225_2' must be String: '"+value+"' is not." );
                    this.part_long_pos_nk225_2 = (String) value;
                    if (this.part_long_pos_nk225_2_is_set)
                        this.part_long_pos_nk225_2_is_modified = true;
                    this.part_long_pos_nk225_2_is_set = true;
                    return;
                }
                else if ( name.equals("part_short_pos_nk225_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_short_pos_nk225_0' must be String: '"+value+"' is not." );
                    this.part_short_pos_nk225_0 = (String) value;
                    if (this.part_short_pos_nk225_0_is_set)
                        this.part_short_pos_nk225_0_is_modified = true;
                    this.part_short_pos_nk225_0_is_set = true;
                    return;
                }
                else if ( name.equals("part_short_pos_nk225_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_short_pos_nk225_1' must be String: '"+value+"' is not." );
                    this.part_short_pos_nk225_1 = (String) value;
                    if (this.part_short_pos_nk225_1_is_set)
                        this.part_short_pos_nk225_1_is_modified = true;
                    this.part_short_pos_nk225_1_is_set = true;
                    return;
                }
                else if ( name.equals("part_short_pos_nk225_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_short_pos_nk225_2' must be String: '"+value+"' is not." );
                    this.part_short_pos_nk225_2 = (String) value;
                    if (this.part_short_pos_nk225_2_is_set)
                        this.part_short_pos_nk225_2_is_modified = true;
                    this.part_short_pos_nk225_2_is_set = true;
                    return;
                }
                else if ( name.equals("part_long_pos_nk225_mini_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_long_pos_nk225_mini_0' must be String: '"+value+"' is not." );
                    this.part_long_pos_nk225_mini_0 = (String) value;
                    if (this.part_long_pos_nk225_mini_0_is_set)
                        this.part_long_pos_nk225_mini_0_is_modified = true;
                    this.part_long_pos_nk225_mini_0_is_set = true;
                    return;
                }
                else if ( name.equals("part_long_pos_nk225_mini_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_long_pos_nk225_mini_1' must be String: '"+value+"' is not." );
                    this.part_long_pos_nk225_mini_1 = (String) value;
                    if (this.part_long_pos_nk225_mini_1_is_set)
                        this.part_long_pos_nk225_mini_1_is_modified = true;
                    this.part_long_pos_nk225_mini_1_is_set = true;
                    return;
                }
                else if ( name.equals("part_long_pos_nk225_mini_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_long_pos_nk225_mini_2' must be String: '"+value+"' is not." );
                    this.part_long_pos_nk225_mini_2 = (String) value;
                    if (this.part_long_pos_nk225_mini_2_is_set)
                        this.part_long_pos_nk225_mini_2_is_modified = true;
                    this.part_long_pos_nk225_mini_2_is_set = true;
                    return;
                }
                else if ( name.equals("part_short_pos_nk225_mini_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_short_pos_nk225_mini_0' must be String: '"+value+"' is not." );
                    this.part_short_pos_nk225_mini_0 = (String) value;
                    if (this.part_short_pos_nk225_mini_0_is_set)
                        this.part_short_pos_nk225_mini_0_is_modified = true;
                    this.part_short_pos_nk225_mini_0_is_set = true;
                    return;
                }
                else if ( name.equals("part_short_pos_nk225_mini_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_short_pos_nk225_mini_1' must be String: '"+value+"' is not." );
                    this.part_short_pos_nk225_mini_1 = (String) value;
                    if (this.part_short_pos_nk225_mini_1_is_set)
                        this.part_short_pos_nk225_mini_1_is_modified = true;
                    this.part_short_pos_nk225_mini_1_is_set = true;
                    return;
                }
                else if ( name.equals("part_short_pos_nk225_mini_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'part_short_pos_nk225_mini_2' must be String: '"+value+"' is not." );
                    this.part_short_pos_nk225_mini_2 = (String) value;
                    if (this.part_short_pos_nk225_mini_2_is_set)
                        this.part_short_pos_nk225_mini_2_is_modified = true;
                    this.part_short_pos_nk225_mini_2_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("short_fut_eval_profit_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_fut_eval_profit_0' must be String: '"+value+"' is not." );
                    this.short_fut_eval_profit_0 = (String) value;
                    if (this.short_fut_eval_profit_0_is_set)
                        this.short_fut_eval_profit_0_is_modified = true;
                    this.short_fut_eval_profit_0_is_set = true;
                    return;
                }
                else if ( name.equals("short_fut_eval_profit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_fut_eval_profit_1' must be String: '"+value+"' is not." );
                    this.short_fut_eval_profit_1 = (String) value;
                    if (this.short_fut_eval_profit_1_is_set)
                        this.short_fut_eval_profit_1_is_modified = true;
                    this.short_fut_eval_profit_1_is_set = true;
                    return;
                }
                else if ( name.equals("short_fut_eval_profit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_fut_eval_profit_2' must be String: '"+value+"' is not." );
                    this.short_fut_eval_profit_2 = (String) value;
                    if (this.short_fut_eval_profit_2_is_set)
                        this.short_fut_eval_profit_2_is_modified = true;
                    this.short_fut_eval_profit_2_is_set = true;
                    return;
                }
                else if ( name.equals("short_pos_nk225_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_pos_nk225_0' must be String: '"+value+"' is not." );
                    this.short_pos_nk225_0 = (String) value;
                    if (this.short_pos_nk225_0_is_set)
                        this.short_pos_nk225_0_is_modified = true;
                    this.short_pos_nk225_0_is_set = true;
                    return;
                }
                else if ( name.equals("short_pos_nk225_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_pos_nk225_1' must be String: '"+value+"' is not." );
                    this.short_pos_nk225_1 = (String) value;
                    if (this.short_pos_nk225_1_is_set)
                        this.short_pos_nk225_1_is_modified = true;
                    this.short_pos_nk225_1_is_set = true;
                    return;
                }
                else if ( name.equals("short_pos_nk225_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_pos_nk225_2' must be String: '"+value+"' is not." );
                    this.short_pos_nk225_2 = (String) value;
                    if (this.short_pos_nk225_2_is_set)
                        this.short_pos_nk225_2_is_modified = true;
                    this.short_pos_nk225_2_is_set = true;
                    return;
                }
                else if ( name.equals("short_pos_nk225_mini_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_pos_nk225_mini_0' must be String: '"+value+"' is not." );
                    this.short_pos_nk225_mini_0 = (String) value;
                    if (this.short_pos_nk225_mini_0_is_set)
                        this.short_pos_nk225_mini_0_is_modified = true;
                    this.short_pos_nk225_mini_0_is_set = true;
                    return;
                }
                else if ( name.equals("short_pos_nk225_mini_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_pos_nk225_mini_1' must be String: '"+value+"' is not." );
                    this.short_pos_nk225_mini_1 = (String) value;
                    if (this.short_pos_nk225_mini_1_is_set)
                        this.short_pos_nk225_mini_1_is_modified = true;
                    this.short_pos_nk225_mini_1_is_set = true;
                    return;
                }
                else if ( name.equals("short_pos_nk225_mini_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_pos_nk225_mini_2' must be String: '"+value+"' is not." );
                    this.short_pos_nk225_mini_2 = (String) value;
                    if (this.short_pos_nk225_mini_2_is_set)
                        this.short_pos_nk225_mini_2_is_modified = true;
                    this.short_pos_nk225_mini_2_is_set = true;
                    return;
                }
                else if ( name.equals("sell_contract_amt_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_contract_amt_0' must be String: '"+value+"' is not." );
                    this.sell_contract_amt_0 = (String) value;
                    if (this.sell_contract_amt_0_is_set)
                        this.sell_contract_amt_0_is_modified = true;
                    this.sell_contract_amt_0_is_set = true;
                    return;
                }
                else if ( name.equals("sell_contract_amt_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_contract_amt_1' must be String: '"+value+"' is not." );
                    this.sell_contract_amt_1 = (String) value;
                    if (this.sell_contract_amt_1_is_set)
                        this.sell_contract_amt_1_is_modified = true;
                    this.sell_contract_amt_1_is_set = true;
                    return;
                }
                else if ( name.equals("sell_contract_amt_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_contract_amt_2' must be String: '"+value+"' is not." );
                    this.sell_contract_amt_2 = (String) value;
                    if (this.sell_contract_amt_2_is_set)
                        this.sell_contract_amt_2_is_modified = true;
                    this.sell_contract_amt_2_is_set = true;
                    return;
                }
                else if ( name.equals("sell_contract_amt_f0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_contract_amt_f0' must be String: '"+value+"' is not." );
                    this.sell_contract_amt_f0 = (String) value;
                    if (this.sell_contract_amt_f0_is_set)
                        this.sell_contract_amt_f0_is_modified = true;
                    this.sell_contract_amt_f0_is_set = true;
                    return;
                }
                else if ( name.equals("sell_contract_amt_f1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_contract_amt_f1' must be String: '"+value+"' is not." );
                    this.sell_contract_amt_f1 = (String) value;
                    if (this.sell_contract_amt_f1_is_set)
                        this.sell_contract_amt_f1_is_modified = true;
                    this.sell_contract_amt_f1_is_set = true;
                    return;
                }
                else if ( name.equals("sell_contract_amt_f2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_contract_amt_f2' must be String: '"+value+"' is not." );
                    this.sell_contract_amt_f2 = (String) value;
                    if (this.sell_contract_amt_f2_is_set)
                        this.sell_contract_amt_f2_is_modified = true;
                    this.sell_contract_amt_f2_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'status' must be Integer: '"+value+"' is not." );
                    this.status = (Integer) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("today_transfer_amt_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_transfer_amt_0' must be String: '"+value+"' is not." );
                    this.today_transfer_amt_0 = (String) value;
                    if (this.today_transfer_amt_0_is_set)
                        this.today_transfer_amt_0_is_modified = true;
                    this.today_transfer_amt_0_is_set = true;
                    return;
                }
                else if ( name.equals("today_transfer_amt_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_transfer_amt_1' must be String: '"+value+"' is not." );
                    this.today_transfer_amt_1 = (String) value;
                    if (this.today_transfer_amt_1_is_set)
                        this.today_transfer_amt_1_is_modified = true;
                    this.today_transfer_amt_1_is_set = true;
                    return;
                }
                else if ( name.equals("today_transfer_amt_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_transfer_amt_2' must be String: '"+value+"' is not." );
                    this.today_transfer_amt_2 = (String) value;
                    if (this.today_transfer_amt_2_is_set)
                        this.today_transfer_amt_2_is_modified = true;
                    this.today_transfer_amt_2_is_set = true;
                    return;
                }
                else if ( name.equals("today_fut_settle_profit_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_fut_settle_profit_0' must be String: '"+value+"' is not." );
                    this.today_fut_settle_profit_0 = (String) value;
                    if (this.today_fut_settle_profit_0_is_set)
                        this.today_fut_settle_profit_0_is_modified = true;
                    this.today_fut_settle_profit_0_is_set = true;
                    return;
                }
                else if ( name.equals("today_fut_settle_profit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_fut_settle_profit_1' must be String: '"+value+"' is not." );
                    this.today_fut_settle_profit_1 = (String) value;
                    if (this.today_fut_settle_profit_1_is_set)
                        this.today_fut_settle_profit_1_is_modified = true;
                    this.today_fut_settle_profit_1_is_set = true;
                    return;
                }
                else if ( name.equals("today_fut_settle_profit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_fut_settle_profit_2' must be String: '"+value+"' is not." );
                    this.today_fut_settle_profit_2 = (String) value;
                    if (this.today_fut_settle_profit_2_is_set)
                        this.today_fut_settle_profit_2_is_modified = true;
                    this.today_fut_settle_profit_2_is_set = true;
                    return;
                }
                else if ( name.equals("today_op_net_amt_0") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_op_net_amt_0' must be String: '"+value+"' is not." );
                    this.today_op_net_amt_0 = (String) value;
                    if (this.today_op_net_amt_0_is_set)
                        this.today_op_net_amt_0_is_modified = true;
                    this.today_op_net_amt_0_is_set = true;
                    return;
                }
                else if ( name.equals("today_op_net_amt_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_op_net_amt_1' must be String: '"+value+"' is not." );
                    this.today_op_net_amt_1 = (String) value;
                    if (this.today_op_net_amt_1_is_set)
                        this.today_op_net_amt_1_is_modified = true;
                    this.today_op_net_amt_1_is_set = true;
                    return;
                }
                else if ( name.equals("today_op_net_amt_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_op_net_amt_2' must be String: '"+value+"' is not." );
                    this.today_op_net_amt_2 = (String) value;
                    if (this.today_op_net_amt_2_is_set)
                        this.today_op_net_amt_2_is_modified = true;
                    this.today_op_net_amt_2_is_set = true;
                    return;
                }
                else if ( name.equals("today_claim_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'today_claim_amt' must be String: '"+value+"' is not." );
                    this.today_claim_amt = (String) value;
                    if (this.today_claim_amt_is_set)
                        this.today_claim_amt_is_modified = true;
                    this.today_claim_amt_is_set = true;
                    return;
                }
                else if ( name.equals("tomorrow_claim_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tomorrow_claim_amt' must be String: '"+value+"' is not." );
                    this.tomorrow_claim_amt = (String) value;
                    if (this.tomorrow_claim_amt_is_set)
                        this.tomorrow_claim_amt_is_modified = true;
                    this.tomorrow_claim_amt_is_set = true;
                    return;
                }
                else if ( name.equals("tomorrow_claim_amt_evening") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tomorrow_claim_amt_evening' must be String: '"+value+"' is not." );
                    this.tomorrow_claim_amt_evening = (String) value;
                    if (this.tomorrow_claim_amt_evening_is_set)
                        this.tomorrow_claim_amt_evening_is_modified = true;
                    this.tomorrow_claim_amt_evening_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
