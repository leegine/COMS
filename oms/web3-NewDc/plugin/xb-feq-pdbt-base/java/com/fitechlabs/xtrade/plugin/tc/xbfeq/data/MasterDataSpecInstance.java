head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.02.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec feq_product_columns[] = {
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("standard_name_kana",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("standard_name_kanji",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("offshore_product_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("books_closing_ym1",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("books_closing_ym2",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("offering_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("ex_right_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("capital_gain_tax_dealings_reg",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec feq_traded_product_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"feq_product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("list_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("listed_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("unlisted_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("trade_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_lot_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("buy_min_qty",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("sell_lot_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("sell_min_qty",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec feq_traded_product_updq_columns[] = {
    new ColumnSpec("traded_product_id",java.sql.Types.NUMERIC,18,0,false,true,"traded_product","traded_product_id"),
    new ColumnSpec("valid_for_biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"feq_product","product_id"),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,"market","market_id"),
    new ColumnSpec("list_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("listed_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("unlisted_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("trade_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("sell_stop",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("buy_lot_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("buy_min_qty",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("sell_lot_size",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("sell_min_qty",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("feq_product",feq_product_columns,"inv=0"),
    new TableSpec("feq_traded_product",feq_traded_product_columns,"inv=0"),
    new TableSpec("feq_traded_product_updq",feq_traded_product_updq_columns,"inv=0"),
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
