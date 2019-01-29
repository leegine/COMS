head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.50.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec ipo_product_columns[] = {
    new ColumnSpec("ipo_product_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("product_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("ipo_regist_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("ipo_regist_detail_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("public_offering_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("public_offering_date_count",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("public_market",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("provisional_value_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("provisional_min_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("provisional_max_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("provisional_value_open_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("public_offering_quantity",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("public_sales_quantity",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("dealing_quantity",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("lead_managing_underwriter",java.sql.Types.VARCHAR,80,0,true,false,null,null),
    new ColumnSpec("lot_size",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("tick_value",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("ipo_unit_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("enable_market_order",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("bookbuilding_start_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("bookbuilding_end_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("public_price_settle_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("public_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("public_price_discount_rate",java.sql.Types.DECIMAL,4,2,true,false,null,null),
    new ColumnSpec("lot_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("lot_date_count",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("lot_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("offer_start_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("offer_start_date_count",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("offer_end_datetime",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("offer_end_date_count",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("offer_start_date_prospec",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("offer_start_date_count_prospec",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("offer_end_date_prospec",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("offer_end_date_count_prospec",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("company_logo_url",java.sql.Types.VARCHAR,80,0,true,false,null,null),
    new ColumnSpec("company_url",java.sql.Types.VARCHAR,80,0,true,false,null,null),
    new ColumnSpec("company_outline",java.sql.Types.VARCHAR,400,0,true,false,null,null),
    new ColumnSpec("note",java.sql.Types.VARCHAR,400,0,true,false,null,null),
    new ColumnSpec("ipo_stop",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("doc_reading_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec ipo_bookbuilding_columns[] = {
    new ColumnSpec("ipo_product_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("bb_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("bb_seq",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("bb_quantity_all",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("bb_quantity_loop",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("sub_bb_quantity_all",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("sub_bb_quantity_loop",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("process",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("bb_result_quantity_sum",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("bb_result_acc_count",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("bb_result_quantity_max",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("bb_result_quantity_min",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("sub_bb_result_quantity_sum",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("sub_bb_result_acc_count",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("sub_bb_result_quantity_max",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("sub_bb_result_quantity_min",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("ipo_product",ipo_product_columns,"inv=0"),
    new TableSpec("ipo_bookbuilding",ipo_bookbuilding_columns,"inv=0"),
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
