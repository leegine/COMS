head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.19.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec trigger_order_stop_columns[] = {
    new ColumnSpec("trigger_order_stop_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("target_type",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("key",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("addition",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("stop_reason",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("succ_order_stop_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("stop_order_stop_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("wlimit_order_stop_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("oco_order_stop_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("ifd_order_stop_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("valid_term_from",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("valid_term_to",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("trigger_order_stop",trigger_order_stop_columns,"inv=0"),
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
