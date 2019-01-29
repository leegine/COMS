head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.18.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec rsv_eq_order_unit_columns[] = {
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,"eqtype_order_unit","order_unit_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("reserve_order_trading_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price_adjust_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("expiration_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("swap_tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("repayment_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("repayment_num",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"eqtype_product","product_id"),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("capital_gain",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("capital_gain_tax",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("closing_order_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("first_order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_error_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("parent_order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("parent_order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,"eqtype_order_unit","order_unit_id"),
    new ColumnSpec("serial_no_in_parent",java.sql.Types.NUMERIC,8,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("forced_expire_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec rsv_eq_order_action_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"rsv_eq_order_unit","order_id"),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price_adjust_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("expiration_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("ip_address",java.sql.Types.VARCHAR,15,0,true,false,null,null),
  };
  private static final ColumnSpec rsv_eq_closing_contract_spec_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"rsv_eq_order_unit","order_id"),
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,false,false,"eqtype_contract","contract_id"),
    new ColumnSpec("closing_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec rsv_ifo_order_unit_columns[] = {
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,"ifo_order_unit","order_unit_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("reserve_order_trading_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price_adjust_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("expiration_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("closing_order",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("first_order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("first_order_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_error_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("parent_order_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("parent_order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,"ifo_order_unit","order_unit_id"),
    new ColumnSpec("serial_no_in_parent",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("evening_session_carryover_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("session_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("expiration_date_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec rsv_ifo_order_action_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"rsv_ifo_order_unit","order_id"),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price_adjust_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("expiration_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_action_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("estimated_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,"trader","trader_id"),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("expiration_date_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec rsv_ifo_closing_contract_spec_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,false,false,"rsv_ifo_order_unit","order_id"),
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_contract","contract_id"),
    new ColumnSpec("closing_serial_no",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("rsv_eq_order_unit",rsv_eq_order_unit_columns,"inv=?.account_id"),
    new TableSpec("rsv_eq_order_action",rsv_eq_order_action_columns,"inv=?.account_id"),
    new TableSpec("rsv_eq_closing_contract_spec",rsv_eq_closing_contract_spec_columns,"inv=?.account_id"),
    new TableSpec("rsv_ifo_order_unit",rsv_ifo_order_unit_columns,"inv=?.account_id"),
    new TableSpec("rsv_ifo_order_action",rsv_ifo_order_action_columns,"inv=?.account_id"),
    new TableSpec("rsv_ifo_closing_contract_spec",rsv_ifo_closing_contract_spec_columns,"inv=?.account_id"),
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
