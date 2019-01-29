head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.10;	author che-jin;	state Exp;
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
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec payment_requisition_columns[] = {
    new ColumnSpec("payment_requisition_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("occurred_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("payment_requisition_division",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("requisition_status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("payment_requisition_amount",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_collateral_rate",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("calclation_source",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec requisition_account_equity_columns[] = {
    new ColumnSpec("calc_result_equity_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("family_name",java.sql.Types.VARCHAR,40,0,false,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("asset_evaluation_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("real_account_balance_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("real_account_balance_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("real_account_balance_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("real_account_balance_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("real_account_balance_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("real_account_balance_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("security_asset_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("security_asset_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("security_asset_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("security_asset_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("security_asset_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("security_asset_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
  };
  private static final ColumnSpec requisition_account_margin_columns[] = {
    new ColumnSpec("calc_result_margin_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("family_name",java.sql.Types.VARCHAR,40,0,false,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("mark_to_market_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("debit_amount_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("debit_amount_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("special_debit_amount_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("receipt_margin_deposit_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("receipt_margin_deposit_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("receipt_margin_deposit_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("receipt_margin_deposit_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("receipt_margin_deposit_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("receipt_margin_deposit_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_maintenance_amount_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_maintenance_amount_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_maintenance_amount_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_maintenance_amount_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_maintenance_amount_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_maintenance_amount_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_deposit_rate_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_deposit_rate_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_deposit_rate_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_deposit_rate_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_deposit_rate_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_deposit_rate_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_claimed_amount_0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_claimed_amount_1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_claimed_amount_2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_claimed_amount_3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_claimed_amount_4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("margin_claimed_amount_5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
  };
  private static final ColumnSpec payment_requisition_equity_columns[] = {
    new ColumnSpec("calc_result_equity_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("family_name",java.sql.Types.VARCHAR,40,0,false,false,null,null),
    new ColumnSpec("account_attribute",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("calc_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("trading_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("ifo_open_position_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("payment_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("other_trading_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("payment_requisition_amount_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
  };
  private static final ColumnSpec payment_requisition_margin_columns[] = {
    new ColumnSpec("calc_result_margin_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("family_name",java.sql.Types.VARCHAR,40,0,false,false,null,null),
    new ColumnSpec("account_attribute",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("calc_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("trading_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("margin_open_position_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("ifo_open_position_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("payment_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("other_trading_stop",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("break20day",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("break20elapsed_days",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("break30day",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("break30elapsed_days",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_amount_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_requisition_division_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_balance_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("day_trade_restraint_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other_restraint_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("paid_margin_deposit_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("paid_margin_deposit_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("paid_margin_deposit_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("paid_margin_deposit_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("paid_margin_deposit_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("paid_margin_deposit_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("receipt_margin_deposit_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("receipt_margin_deposit_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("receipt_margin_deposit_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("receipt_margin_deposit_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("receipt_margin_deposit_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("receipt_margin_deposit_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_margin_deposit_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_margin_deposit_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_margin_deposit_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_margin_deposit_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_margin_deposit_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_margin_deposit_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_amount_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_amount_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_amount_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_amount_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_amount_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_amount_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_rate_0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_rate_1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_rate_2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_rate_3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_rate_4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_rate_5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("payment_requisition",payment_requisition_columns,null),
    new TableSpec("requisition_account_equity",requisition_account_equity_columns,null),
    new TableSpec("requisition_account_margin",requisition_account_margin_columns,null),
    new TableSpec("payment_requisition_equity",payment_requisition_equity_columns,null),
    new TableSpec("payment_requisition_margin",payment_requisition_margin_columns,null),
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
