head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec account_preferences_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("name",java.sql.Types.VARCHAR,80,0,false,true,null,null),
    new ColumnSpec("name_serial_no",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("value",java.sql.Types.VARCHAR,200,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec asset_columns[] = {
    new ColumnSpec("asset_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("quantity_cannot_sell",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("quantity_for_book_value",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("book_value",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("input_book_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("input_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("setup_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("setup_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("management_fee",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("management_fee_tax",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("mini_stock_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("profit_distribution",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("count_before_penalty",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec asset_unit_columns[] = {
    new ColumnSpec("asset_unit_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("asset_id",java.sql.Types.NUMERIC,18,0,false,false,"asset","asset_id"),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("original_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("acquired_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("acquired_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("accrued_interest_price",java.sql.Types.DECIMAL,18,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("custody_type",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("evaluation_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("collateral_ratio",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec asset_unit_sales_columns[] = {
    new ColumnSpec("asset_unit_sales_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("asset_unit_id",java.sql.Types.NUMERIC,18,0,false,false,"asset_unit","asset_unit_id"),
    new ColumnSpec("fin_transaction_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec gen_fin_transaction_columns[] = {
    new ColumnSpec("transaction_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("transaction_description",java.sql.Types.VARCHAR,200,0,false,false,null,null),
    new ColumnSpec("net_amount",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("trade_module_name",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("transaction_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("transaction_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec locked_asset_details_columns[] = {
    new ColumnSpec("asset_id",java.sql.Types.NUMERIC,18,0,false,true,"asset","asset_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("locked_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec participant_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("participant_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("participant_code",java.sql.Types.VARCHAR,40,0,false,false,null,null),
    new ColumnSpec("participant_middle_name",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("participant_family_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("participant_given_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("participant_middle_name_alt1",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("participant_family_name_alt1",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("participant_given_name_alt1",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("participant_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec sub_account_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("sub_account_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("institution_id",java.sql.Types.NUMERIC,18,0,false,false,"institution","institution_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,true,false,"branch","branch_id"),
    new ColumnSpec("sub_account_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("open_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("close_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("cash_balance",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec sub_account_preferences_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,true,"sub_account","sub_account_id"),
    new ColumnSpec("name",java.sql.Types.VARCHAR,80,0,false,true,null,null),
    new ColumnSpec("name_serial_no",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("value",java.sql.Types.VARCHAR,200,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("account_preferences",account_preferences_columns,"inv=?.account_id"),
    new TableSpec("asset",asset_columns,"inv=?.account_id"),
    new TableSpec("asset_unit",asset_unit_columns,"inv=?.account_id"),
    new TableSpec("asset_unit_sales",asset_unit_sales_columns,"inv=?.account_id"),
    new TableSpec("gen_fin_transaction",gen_fin_transaction_columns,"inv=?.account_id"),
    new TableSpec("locked_asset_details",locked_asset_details_columns,"inv=?.account_id"),
    new TableSpec("participant",participant_columns,"inv=?.account_id"),
    new TableSpec("sub_account",sub_account_columns,"inv=?.account_id"),
    new TableSpec("sub_account_preferences",sub_account_preferences_columns,"inv=?.account_id"),
  };

  private static final AccountDataSpecInstance instance = new AccountDataSpecInstance();

  private AccountDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
