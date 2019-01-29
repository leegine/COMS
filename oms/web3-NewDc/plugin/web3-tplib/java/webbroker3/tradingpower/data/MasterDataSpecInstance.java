head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec eqtype_fixed_contract_columns[] = {
    new ColumnSpec("fixed_contract_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("original_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("original_contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("open_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("close_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("setup_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("setup_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("name_transfer_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("name_transfer_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("management_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("management_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("interest_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("interest_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("pay_interest_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("pay_interest_fee_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("loan_equity_fee",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("other",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("contract_asset_profit_loss",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("repayment_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("repayment_num",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("first_open_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec fixed_fin_transaction_columns[] = {
    new ColumnSpec("fixed_fin_transaction_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("fin_transaction_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("fin_transaction_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("fin_transaction_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("commission_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("commission_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_paid_value",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_setup_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_setup_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_name_transfer_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_name_transfer_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("capital_gain",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("capital_gain_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("fixed_contract_id",java.sql.Types.NUMERIC,18,0,true,false,"eqtype_fixed_contract","fixed_contract_id"),
    new ColumnSpec("imported_management_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_management_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_interest_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_interest_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_pay_interest_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_pay_interest_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_loan_equity_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("imported_other",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
  };
  private static final ColumnSpec tp_cash_balance_columns[] = {
    new ColumnSpec("tp_cash_balance_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("cash_balance0",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("cash_balance1",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("cash_balance2",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("cash_balance3",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("cash_balance4",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("cash_balance5",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("mrf_balance",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("current_term_capital_gain",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("next_month_capital_gain",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("eqtype_fixed_contract",eqtype_fixed_contract_columns,"inv=0"),
    new TableSpec("fixed_fin_transaction",fixed_fin_transaction_columns,"inv=0"),
    new TableSpec("tp_cash_balance",tp_cash_balance_columns,"inv=0"),
  };

  private static final MasterDataSpecInstance instance = new MasterDataSpecInstance();

  private MasterDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
