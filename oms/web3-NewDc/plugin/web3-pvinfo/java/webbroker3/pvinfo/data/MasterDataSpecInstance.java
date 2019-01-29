head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.12.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec display_condition_columns[] = {
    new ColumnSpec("display_condition_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("condition_no",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("condition_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec display_contents_columns[] = {
    new ColumnSpec("display_contents_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("condition_no",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("priority_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("term_from",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("term_to",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("display_title",java.sql.Types.VARCHAR,100,0,false,false,null,null),
    new ColumnSpec("display_message",java.sql.Types.VARCHAR,2000,0,false,false,null,null),
    new ColumnSpec("display_color",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("blink_display_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("display_url",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("last_update_time_display_flag",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("effective_flag",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("display_device",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_update_member",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("display_condition",display_condition_columns,"inv=0"),
    new TableSpec("display_contents",display_contents_columns,"inv=0"),
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
