head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
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
@package webbroker3.feq.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec feq_tick_values_mst_columns[] = {
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("low_price",java.sql.Types.DECIMAL,18,6,false,true,null,null),
    new ColumnSpec("cap_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("tick_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("scale",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec feq_limit_price_range_mst_columns[] = {
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("low_price",java.sql.Types.DECIMAL,18,6,false,true,null,null),
    new ColumnSpec("cap_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("range",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec feq_orderexecution_end_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("last_execute_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec foreign_cost_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("cost_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("amount_from",java.sql.Types.NUMERIC,13,2,false,true,null,null),
    new ColumnSpec("amount_to",java.sql.Types.NUMERIC,13,2,true,false,null,null),
    new ColumnSpec("commision_rate",java.sql.Types.NUMERIC,8,5,true,false,null,null),
    new ColumnSpec("add_amount",java.sql.Types.NUMERIC,14,5,true,false,null,null),
    new ColumnSpec("scale",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("round_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("side_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
  };
  private static final ColumnSpec special_product_foreign_cost_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("offshore_product_code",java.sql.Types.VARCHAR,9,0,false,true,null,null),
    new ColumnSpec("cost_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.TIMESTAMP,0,0,false,true,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("amount_from",java.sql.Types.NUMERIC,13,2,false,true,null,null),
    new ColumnSpec("amount_to",java.sql.Types.NUMERIC,13,2,true,false,null,null),
    new ColumnSpec("commision_rate",java.sql.Types.NUMERIC,8,5,true,false,null,null),
    new ColumnSpec("add_amount",java.sql.Types.NUMERIC,14,5,true,false,null,null),
    new ColumnSpec("scale",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("round_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("side_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("feq_tick_values_mst",feq_tick_values_mst_columns,"inv=0"),
    new TableSpec("feq_limit_price_range_mst",feq_limit_price_range_mst_columns,"inv=0"),
    new TableSpec("feq_orderexecution_end",feq_orderexecution_end_columns,"inv=0"),
    new TableSpec("foreign_cost",foreign_cost_columns,"inv=0"),
    new TableSpec("special_product_foreign_cost",special_product_foreign_cost_columns,"inv=0"),
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
