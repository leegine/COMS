head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec ifo_deposit_calc_result_columns[] = {
    new ColumnSpec("deposit_info_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("base_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("balance_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("balance_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("balance_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_balance_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_balance_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_balance_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_balance_f0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_balance_f1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_balance_f2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_transfer_amt_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_transfer_amt_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_transfer_amt_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_fut_settle_profit_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_fut_settle_profit_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_fut_settle_profit_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_op_net_amt_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_op_net_amt_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_op_net_amt_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("fut_eval_profit_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("fut_eval_profit_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("fut_eval_profit_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_fut_eval_profit_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_fut_eval_profit_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_fut_eval_profit_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_fut_eval_profit_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_fut_eval_profit_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_fut_eval_profit_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("acceptance_ifo_deposit_bal_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("acceptance_ifo_deposit_bal_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("acceptance_ifo_deposit_bal_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("acceptance_ifo_deposit_bal_f0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("acceptance_ifo_deposit_bal_f1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("acceptance_ifo_deposit_bal_f2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("net_op_value_total_amt_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("net_op_value_total_amt_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("net_op_value_total_amt_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("net_op_value_total_amt_f0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("net_op_value_total_amt_f1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("net_op_value_total_amt_f2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_necessary_amt_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_necessary_amt_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_necessary_amt_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_necessary_amt_f0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_necessary_amt_f1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_necessary_amt_f2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_power_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_power_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_power_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_transfer_power_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_transfer_power_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_transfer_power_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bull_quantity_nk225_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bull_quantity_nk225_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bull_quantity_nk225_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bear_quantity_nk225_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bear_quantity_nk225_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bear_quantity_nk225_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_pos_nk225_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_pos_nk225_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_pos_nk225_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_long_pos_nk225_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_long_pos_nk225_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_long_pos_nk225_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_pos_nk225_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_pos_nk225_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_pos_nk225_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_short_pos_nk225_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_short_pos_nk225_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_short_pos_nk225_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bull_quantity_nk225_mini_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bull_quantity_nk225_mini_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bull_quantity_nk225_mini_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bear_quantity_nk225_mini_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bear_quantity_nk225_mini_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("bear_quantity_nk225_mini_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_pos_nk225_mini_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_pos_nk225_mini_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("long_pos_nk225_mini_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_long_pos_nk225_mini_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_long_pos_nk225_mini_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_long_pos_nk225_mini_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_pos_nk225_mini_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_pos_nk225_mini_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("short_pos_nk225_mini_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_short_pos_nk225_mini_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_short_pos_nk225_mini_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("part_short_pos_nk225_mini_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("buy_contract_amt_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("buy_contract_amt_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("buy_contract_amt_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("buy_contract_amt_f0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("buy_contract_amt_f1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("buy_contract_amt_f2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("sell_contract_amt_0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("sell_contract_amt_1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("sell_contract_amt_2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("sell_contract_amt_f0",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("sell_contract_amt_f1",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("sell_contract_amt_f2",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("non_pay_amt",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("today_claim_amt",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("tomorrow_claim_amt",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_insufficient_flag",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_index_nk225",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("ifo_deposit_index_nk225_mini",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("after_tomorrow_claim_amt",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("tomorrow_claim_amt_evening",java.sql.Types.VARCHAR,18,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("error_message",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_deposit_calc_lock_columns[] = {
    new ColumnSpec("service_name",java.sql.Types.VARCHAR,100,0,false,true,null,null),
    new ColumnSpec("thread_no",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("ap_host_name",java.sql.Types.VARCHAR,100,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ifo_deposit_calc_result",ifo_deposit_calc_result_columns,null),
    new TableSpec("ifo_deposit_calc_lock",ifo_deposit_calc_lock_columns,null),
  };

  private static final SessionDataSpecInstance instance = new SessionDataSpecInstance();

  private SessionDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
