head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.07.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec ifo_product_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("derivative_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("month_of_delivery",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("exercise_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("split_type",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("target_market_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("index_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_traded_product_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
    new ColumnSpec("unit_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("unit_margin",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("per_order_max_units",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("order_close_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("start_trading_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_trading_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("trade_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("buy_to_open_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("sell_to_open_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("sell_to_close_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("buy_to_close_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("list_flag",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("list_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("unlisted_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("exercise_stop",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("actual_recieve_stop",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("actual_delivary_stop",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("reserve_stop",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("indication_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_liquidation_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("target_spot_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("liquidation_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ifo_traded_product_updq_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_product","product_id"),
    new ColumnSpec("unit_size",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("unit_margin",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("per_order_max_units",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("order_close_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("start_trading_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_trading_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("trade_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("buy_to_open_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("sell_to_open_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("sell_to_close_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("buy_to_close_stop_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("list_flag",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("list_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("unlisted_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("exercise_stop",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("actual_recieve_stop",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("actual_delivary_stop",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("reserve_stop",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("indication_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("last_liquidation_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("target_spot_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("liquidation_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ifo_product",ifo_product_columns,"inv=0"),
    new TableSpec("ifo_traded_product",ifo_traded_product_columns,"inv=0"),
    new TableSpec("ifo_traded_product_updq",ifo_traded_product_updq_columns,"inv=0"),
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
