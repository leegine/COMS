head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.13.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec mutual_fund_traded_product_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("order_close_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("exec_date_shiftdays",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("buy_undelivering_term",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("sell_undelivering_term",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("scram_biz_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("online_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("recruit_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec mf_traded_product_updq_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("order_close_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"mutual_fund_product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("exec_date_shiftdays",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("buy_undelivering_term",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("sell_undelivering_term",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("scram_biz_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("online_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("recruit_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec mutual_fund_product_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("product_issue_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("fund_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("init_purchase_min_qty",java.sql.Types.NUMERIC,10,0,false,false,null,null),
    new ColumnSpec("addtl_purchase_min_qty",java.sql.Types.NUMERIC,10,0,false,false,null,null),
    new ColumnSpec("mutualassoc_product_code",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("system_handling_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_limit_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,64,0,true,false,null,null),
    new ColumnSpec("eng_product_name",java.sql.Types.VARCHAR,64,0,true,false,null,null),
    new ColumnSpec("setting_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("redemption_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sell_ban_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("swt_possible_group_id",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("category_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("indication_ranking",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("buy_constant_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("sell_constant_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("reference_constant_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("constant_value_app_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("constant_value_calc_unit",java.sql.Types.NUMERIC,9,0,true,false,null,null),
    new ColumnSpec("buy_settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delivery_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_specity_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_specify_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("swt_specify_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("stock_type_bond_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("contract_institution_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("perference_money_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("input_unit",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("new_buy_min_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("add_buy_min_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("sell_min_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("swt_min_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("new_buy_unit_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("add_buy_unit_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("sell_unit_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("swt_unit_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("new_buy_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("add_buy_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("sell_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("swt_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("new_buy_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("add_buy_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("sell_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("swt_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("buy_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("buy_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sell_swt_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sell_swt_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("buy_claim_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("buy_claim_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("online_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("recruit_constant_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("recruit_settlement_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("recruit_specity_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("recruit_min_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("recruit_unit_qty",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("recruit_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("recruit_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("recruit_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("recruit_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("cal_price_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("plowback_product_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("recruit_start_date_sonar",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("recruit_end_date_sonar",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("fixed_buy_possible_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("unit_type_product_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("frgn_new_buy_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("frgn_add_buy_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("frgn_sell_min_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("frgn_new_buy_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("frgn_add_buy_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("frgn_sell_unit_amt",java.sql.Types.NUMERIC,10,0,true,false,null,null),
    new ColumnSpec("recruit_commission_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("open_close_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("mutual_fund_traded_product",mutual_fund_traded_product_columns,"inv=0"),
    new TableSpec("mf_traded_product_updq",mf_traded_product_updq_columns,"inv=0"),
    new TableSpec("mutual_fund_product",mutual_fund_product_columns,"inv=0"),
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
