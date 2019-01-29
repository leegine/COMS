head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.47.12;	author che-jin;	state Exp;
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
@package webbroker3.tradehistory.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec settle_detail_history_columns[] = {
    new ColumnSpec("settle_detail_history_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("commodity_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("trade_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("market_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_sell_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("open_exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("close_exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("contract_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("close_contract_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("close_contract_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("open_commission",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("close_commission",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("open_commission_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("close_commission_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("management_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("management_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("name_transfer_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("name_transfer_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("capital_gain_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("debit_daily_interest",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("credit_daily_interest",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("loan_equity_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("dividend_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("adjust_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("repayment_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec transaction_history_columns[] = {
    new ColumnSpec("transaction_history_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("confirmed_balance",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("deposit_margin_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec trade_detail_history_columns[] = {
    new ColumnSpec("trade_detail_history_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("commodity_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("trade_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("market_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_sell_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("executed_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("commission_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("commission_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("capital_gain_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("monetary_unit",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("fstk_exch_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("monetary_unit_executed_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("monetary_unit_comission",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("monetary_unit_trade_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("monetary_unit_interest",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("local_settle_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("local_settle_amount_yen",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("exec_exchange",java.sql.Types.DECIMAL,11,7,true,false,null,null),
    new ColumnSpec("domestic_commission",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("payment_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("accrued_interest_yen",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("accrued_interest",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("denomination",java.sql.Types.DECIMAL,18,6,true,false,null,null),
  };
  private static final ColumnSpec trade_history_columns[] = {
    new ColumnSpec("trade_history_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("details_management_no",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("commodity_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("trade_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("remark_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("remark_name",java.sql.Types.VARCHAR,60,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("product_name",java.sql.Types.VARCHAR,60,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("quantity_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("account_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("capital_gain",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("io_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_sell_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("repayment_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("monetary_unit",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("payment_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec book_value_spec_columns[] = {
    new ColumnSpec("book_value_spec_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("work_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rec_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("commodity_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("sort_no",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("calc_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("exec_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("stock_exchg",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trade_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("buy_sell_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("debit_bal_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("ccy_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("calc_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("bal_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("book_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("book_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("proloss1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("proloss2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("remark",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec profit_loss_spec_columns[] = {
    new ColumnSpec("profit_loss_spec_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code_sonar",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("work_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("rec_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("sort_no",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("calc_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("commodity_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("application_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,26,0,true,false,null,null),
    new ColumnSpec("term_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,19,5,true,false,null,null),
    new ColumnSpec("pass_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("pass_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("get_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("get_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("proloss_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("total_proloss_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("taxable_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("collect_tax_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("collect_tax_n_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("collect_tax_l_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("return_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("colltax_amount_curr",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("colltax_n_amount_curr",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("colltax_l_amount_curr",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("colltax_amount_nxt",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("colltax_n_amount_nxt",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("colltax_l_amount_nxt",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("remark",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("proloss_amount_cps",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("settle_detail_history",settle_detail_history_columns,null),
    new TableSpec("transaction_history",transaction_history_columns,null),
    new TableSpec("trade_detail_history",trade_detail_history_columns,null),
    new TableSpec("trade_history",trade_history_columns,null),
    new TableSpec("book_value_spec",book_value_spec_columns,null),
    new TableSpec("profit_loss_spec",profit_loss_spec_columns,null),
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
