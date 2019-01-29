head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec equity_tick_values_mst_columns[] = {
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("low_price",java.sql.Types.DECIMAL,18,6,false,true,null,null),
    new ColumnSpec("cap_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("tick_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec equity_limit_price_range_mst_columns[] = {
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("low_price",java.sql.Types.DECIMAL,18,6,false,true,null,null),
    new ColumnSpec("cap_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec order_carryover_skip_prod_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("regist_division",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec off_floor_order_product_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("order_start_datetime",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("order_end_datetime",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("max_apply_quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("off_floor_order_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_closing_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("daily_delivery_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec short_selling_restraint_time_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("biz_date_type",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("start_time",java.sql.Types.VARCHAR,6,0,false,true,null,null),
    new ColumnSpec("end_time",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("short_selling_count_method_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("equity_tick_values_mst",equity_tick_values_mst_columns,"inv=0"),
    new TableSpec("equity_limit_price_range_mst",equity_limit_price_range_mst_columns,"inv=0"),
    new TableSpec("order_carryover_skip_prod",order_carryover_skip_prod_columns,"inv=0"),
    new TableSpec("off_floor_order_product",off_floor_order_product_columns,"inv=0"),
    new TableSpec("short_selling_restraint_time",short_selling_restraint_time_columns,"inv=0"),
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
