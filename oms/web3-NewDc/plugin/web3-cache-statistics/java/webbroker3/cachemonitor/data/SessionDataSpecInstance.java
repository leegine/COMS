head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.cachemonitor.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec cache_monitor_ord_acc_status_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("order_acc_product",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("order_acc_transaction",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("order_accept_status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("order_accept_status_before",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("cache_monitor_ord_acc_status",cache_monitor_ord_acc_status_columns,null),
  };

  private static final SessionDataSpecInstance instance = new SessionDataSpecInstance();

  private SessionDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
