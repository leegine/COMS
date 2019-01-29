head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec admin_eq_forced_settle_order_columns[] = {
    new ColumnSpec("order_unit_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("trader_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("order_categ",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("order_status",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("order_open_status",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("expiration_status",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("forced_settle_reason_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("approve_status_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("approver_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("approve_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("margin_maintenance_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("additional_margin_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("additional_margin_accrued_days",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("forced_expire_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("org_contract_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("original_contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("open_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("close_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("repayment_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("repayment_num",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("contract_amount",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
  };
  private static final ColumnSpec forced_settle_order_inq_columns[] = {
    new ColumnSpec("forced_settle_order_inq_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("order_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("order_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("order_categ",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,"market","market_id"),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("limit_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("received_date_time",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("forced_settle_reason_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("approve_status_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("margin_maintenance_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("additional_margin_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("additional_margin_accrued_days",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("contract_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("org_contract_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_quantity",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("original_contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("contract_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("open_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("close_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("tax_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("repayment_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("repayment_num",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("approver_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("approve_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
  };
  private static final ColumnSpec attention_info_history_columns[] = {
    new ColumnSpec("attention_info_history_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("attention_info_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("valid_until_biz_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("attention_info_div_code",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("old_estimation_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("new_estimation_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("old_last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("new_last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("old_base_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("new_base_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("old_price_range_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("new_price_range_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("old_price_range_unit_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("new_price_range_unit_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("old_high_comp_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("new_high_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("old_low_comp_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("new_low_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("old_last_closing_price_updq",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("new_last_closing_price_updq",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("old_base_price_updq",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("new_base_price_updq",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("free_format_title",java.sql.Types.VARCHAR,60,0,true,false,null,null),
    new ColumnSpec("free_format_text",java.sql.Types.VARCHAR,600,0,true,false,null,null),
    new ColumnSpec("info_generation_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("ord_receipt_restart_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trade_stop_restart_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("process_result_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec host_attention_info_notify_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("sonar_market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("attention_info_div_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("base_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("high_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("low_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("free_format_title",java.sql.Types.VARCHAR,60,0,true,false,null,null),
    new ColumnSpec("free_format_text",java.sql.Types.VARCHAR,600,0,true,false,null,null),
    new ColumnSpec("info_generation_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("ord_receipt_restart_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trade_stop_restart_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("admin_eq_forced_settle_order",admin_eq_forced_settle_order_columns,null),
    new TableSpec("forced_settle_order_inq",forced_settle_order_inq_columns,null),
    new TableSpec("attention_info_history",attention_info_history_columns,null),
    new TableSpec("host_attention_info_notify",host_attention_info_notify_columns,"rowid_pk=true"),
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
