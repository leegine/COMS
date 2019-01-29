head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec eqtype_traded_product_updq_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"eqtype_product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("valid_until_biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("list_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("list_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("new_list_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("listed_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("unlisted_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("marginable_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("shortable_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("open_otc_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("margin_sys_product_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("margin_gen_product_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mini_stock_can_dealt",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("buy_cash_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_cash_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_spot_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_spot_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_ms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_ms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_mg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_mg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_close_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_close_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_cms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_cms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_close_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_close_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_cmg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_cmg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_swap_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_swap_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_swap_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_swap_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_mini_stock_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_mini_stock_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("lot_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("long_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("short_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("long_cash_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("short_cash_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("price_range_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("price_range_unit_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("high_compulsive_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("low_compulsive_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_high_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_low_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price_range_ratio",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("compulsive_limited_unit",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("mini_stock_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("today_dep_fund_reg",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("base_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
  };
  private static final ColumnSpec eqtype_traded_product_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"eqtype_product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("valid_until_biz_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("list_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("list_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("new_list_type",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("listed_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("unlisted_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("marginable_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("shortable_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("open_otc_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("margin_sys_product_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("margin_gen_product_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mini_stock_can_dealt",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("buy_cash_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_cash_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_spot_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_spot_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_ms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_ms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_mg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_mg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_close_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_close_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_cms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_cms_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_close_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_close_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_cmg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_cmg_market_ord_des_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_swap_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_swap_margin_sys_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("long_swap_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("short_swap_margin_gen_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_mini_stock_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_mini_stock_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("lot_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("long_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("short_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("long_cash_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("short_cash_margin_deposit_rate",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("price_range_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("price_range_unit_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("high_compulsive_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("low_compulsive_price_range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_high_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("stop_low_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("price_range_ratio",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("compulsive_limited_unit",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("mini_stock_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("today_dep_fund_reg",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("base_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
  };
  private static final ColumnSpec eqtype_product_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("mini_stock_lot_size",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("yearly_books_closing_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("trade_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("margin_sys_trade_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("margin_gen_trade_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("capital_gain_tax_dealings_reg",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("decimal_devide_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("eqtype_traded_product_updq",eqtype_traded_product_updq_columns,"inv=0"),
    new TableSpec("eqtype_traded_product",eqtype_traded_product_columns,"inv=0"),
    new TableSpec("eqtype_product",eqtype_product_columns,"inv=0"),
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
