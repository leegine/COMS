head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
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
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec eqtype_product_condition_columns[] = {
    new ColumnSpec("eqtype_product_condition_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,false,"product","product_id"),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("market_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("table_name",java.sql.Types.VARCHAR,30,0,false,false,null,null),
    new ColumnSpec("column_name",java.sql.Types.VARCHAR,30,0,false,false,null,null),
    new ColumnSpec("data_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("large_item_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("small_item_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("data_today",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("data_next_day",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("data_plan",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("term_from",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("term_to",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delete_flg",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("eqtype_product_condition",eqtype_product_condition_columns,"inv=0"),
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
