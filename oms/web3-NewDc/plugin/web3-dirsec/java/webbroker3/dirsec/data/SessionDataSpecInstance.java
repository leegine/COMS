head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec host_management_columns[] = {
    new ColumnSpec("host_table_name",java.sql.Types.VARCHAR,60,0,false,true,null,null),
    new ColumnSpec("host_table_physics_name",java.sql.Types.VARCHAR,60,0,false,false,null,null),
    new ColumnSpec("order_request_number_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("queryprocessor_name",java.sql.Types.VARCHAR,10,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("host_management",host_management_columns,null),
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
