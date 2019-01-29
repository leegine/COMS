head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec ifo_tick_values_master_columns[] = {
    new ColumnSpec("target_product_code",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("low_price",java.sql.Types.DECIMAL,18,6,false,true,null,null),
    new ColumnSpec("cap_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("tick_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec ifo_limit_price_range_master_columns[] = {
    new ColumnSpec("target_product_code",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("low_price",java.sql.Types.DECIMAL,18,6,false,true,null,null),
    new ColumnSpec("cap_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec ifo_index_master_columns[] = {
    new ColumnSpec("index_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec ifo_order_carryover_skip_prod_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("regist_division",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec ifo_delivery_month_master_columns[] = {
    new ColumnSpec("underlying_product_code",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("future_option_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("delivery_month",java.sql.Types.VARCHAR,6,0,false,true,null,null),
    new ColumnSpec("index_id",java.sql.Types.NUMERIC,18,0,false,false,"ifo_index_master","index_id"),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ifo_tick_values_master",ifo_tick_values_master_columns,"inv=0"),
    new TableSpec("ifo_limit_price_range_master",ifo_limit_price_range_master_columns,"inv=0"),
    new TableSpec("ifo_index_master",ifo_index_master_columns,"inv=0"),
    new TableSpec("ifo_order_carryover_skip_prod",ifo_order_carryover_skip_prod_columns,"inv=0"),
    new TableSpec("ifo_delivery_month_master",ifo_delivery_month_master_columns,"inv=0"),
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
