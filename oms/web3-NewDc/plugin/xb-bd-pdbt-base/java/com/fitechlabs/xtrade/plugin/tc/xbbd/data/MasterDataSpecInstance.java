head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.57.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec bond_product_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("product_issue_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("bond_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("host_product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("host_product_issue_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("issue_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("issue_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("issue_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("par_value",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("maturity_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("redemption_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("coupon_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("coupon",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("yearly_interest_payments",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("interest_payment_day_1st",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("interest_payment_day_2nd",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("first_interest_payment_day",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("interest_payment_day",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("interest_payment_day2",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("interest_payment_day3",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("interest_payment_day4",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("host_recruit_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("host_recruit_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trade_handle_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("trade_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trade_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("recruit_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("recruit_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("trade_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("product_name",java.sql.Types.VARCHAR,64,0,true,false,null,null),
    new ColumnSpec("buy_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("sell_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("trade_unit",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("min_face_amount",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("max_face_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cal_linked_market_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("buy_delivery_date_shiftdays",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_delivery_date_shiftdays",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("auto_exec_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("auto_exec_amount",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("auto_exec_limit",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("custodian_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("host_product_name_1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("host_product_name_2",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("host_short_product_name",java.sql.Types.VARCHAR,30,0,true,false,null,null),
    new ColumnSpec("isin_code",java.sql.Types.VARCHAR,12,0,true,false,null,null),
    new ColumnSpec("bond_categ_code",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("issue_market_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("issue_association_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("accrued_interest_calc_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("accrued_interest_start_day",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("special_payment_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("floating_interest_period_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("floating_interest_period",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("floating_interest_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("floating_interest_spread",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("floating_min_coupon",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("tax_free_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("s_and_p",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("moodys",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("cusip",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("buying_fx_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("trading_time_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mediator_commission_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("admin_last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("recruit_invitation_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("recruit_accept_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("redemption_term",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("min_issue_coupon_type",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("prospectus_check_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec bond_traded_product_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("min_par_value",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("weekly_order_allowed_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_high_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_low_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec bond_traded_product_updq_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"bond_product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("min_par_value",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("weekly_order_allowed_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_high_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_low_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("bond_product",bond_product_columns,"inv=0"),
    new TableSpec("bond_traded_product",bond_traded_product_columns,"inv=0"),
    new TableSpec("bond_traded_product_updq",bond_traded_product_updq_columns,"inv=0"),
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
