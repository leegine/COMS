head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	DataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class DataSpecInstance extends DataSpec {

  private static final ColumnSpec affinity_key_based_map_columns[] = {
    new ColumnSpec("key",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("app_id",java.sql.Types.VARCHAR,18,0,false,true,null,null),
    new ColumnSpec("db_id",java.sql.Types.VARCHAR,18,0,false,true,null,null),
  };
  private static final ColumnSpec affinity_range_based_map_columns[] = {
    new ColumnSpec("key_start",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("key_end",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("range_order_no",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("server_type",java.sql.Types.NUMERIC,1,0,false,true,null,null),
    new ColumnSpec("server_id",java.sql.Types.VARCHAR,18,0,false,false,null,null),
    new ColumnSpec("ctx_name",java.sql.Types.VARCHAR,100,0,false,true,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("affinity_key_based_map",affinity_key_based_map_columns,"inv=0"),
    new TableSpec("affinity_range_based_map",affinity_range_based_map_columns,"inv=0"),
  };

  private static final DataSpecInstance instance = new DataSpecInstance();

  private DataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
