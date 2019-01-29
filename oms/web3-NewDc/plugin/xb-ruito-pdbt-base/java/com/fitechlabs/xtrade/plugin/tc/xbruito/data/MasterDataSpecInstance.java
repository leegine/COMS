head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.17.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec ruito_traded_product_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("order_close_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ruito_product","product_id"),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_stop",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_stop",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_delivery_date_shift_days",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("sell_delivery_date_shift_days",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("online_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ruito_product_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("product_issue_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("ruito_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("init_purchase_min_qty",java.sql.Types.NUMERIC,10,0,false,false,null,null),
    new ColumnSpec("addtl_purchase_min_qty",java.sql.Types.NUMERIC,10,0,false,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,64,0,true,false,null,null),
    new ColumnSpec("course",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("plan",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mrf_fund_code",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_max_price",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("buy_min_price",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("sell_max_price",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("sell_min_price",java.sql.Types.NUMERIC,11,0,true,false,null,null),
    new ColumnSpec("buy_designate_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_designate_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("payment_method",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("buy_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sell_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("sell_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("online_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ruito_traded_product_updq_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("order_close_time",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"ruito_product","product_id"),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_stop",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("sell_stop",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("buy_delivery_date_shift_days",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("sell_delivery_date_shift_days",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("online_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ruito_traded_product",ruito_traded_product_columns,"inv=0"),
    new TableSpec("ruito_product",ruito_product_columns,"inv=0"),
    new TableSpec("ruito_traded_product_updq",ruito_traded_product_updq_columns,"inv=0"),
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
