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
filename	MasterDataSpecInstance.java;


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

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec submit_trigger_info_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,6,0,false,true,null,null),
    new ColumnSpec("user_data",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("job_id",java.sql.Types.VARCHAR,16,0,false,true,null,null),
    new ColumnSpec("id_no",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("trigger_id",java.sql.Types.VARCHAR,44,0,false,false,null,null),
    new ColumnSpec("account_start",java.sql.Types.NUMERIC,15,0,false,false,null,null),
    new ColumnSpec("account_end",java.sql.Types.NUMERIC,15,0,false,false,null,null),
    new ColumnSpec("trading_time_type",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("enable_submit_trigger_flag",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("product_handling_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec ap_management_columns[] = {
    new ColumnSpec("ptype",java.sql.Types.VARCHAR,50,0,false,true,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,true,null,null),
    new ColumnSpec("ap_name",java.sql.Types.VARCHAR,50,0,false,false,null,null),
    new ColumnSpec("order_request_number_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("thread_number_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("submit_trigger_info",submit_trigger_info_columns,"inv=0"),
    new TableSpec("ap_management",ap_management_columns,"inv=0"),
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
