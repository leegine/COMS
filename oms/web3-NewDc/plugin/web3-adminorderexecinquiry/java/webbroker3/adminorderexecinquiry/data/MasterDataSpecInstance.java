head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.25;	author che-jin;	state Exp;
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
@package webbroker3.adminorderexecinquiry.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec order_executed_count_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("record_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("product_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("order_chanel",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("biz_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("buy_order_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("sell_order_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("execute_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("change_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("cancel_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("expire_count",java.sql.Types.NUMERIC,8,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("order_executed_count",order_executed_count_columns,"inv=0"),
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
