head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec commission_course_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("comm_product_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("commission_course_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("standard_name",java.sql.Types.VARCHAR,64,0,false,false,null,null),
    new ColumnSpec("regist_end_day_spec",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("regist_end_time",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("appli_start_date_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("appli_start_day_count",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("appli_start_end_time",java.sql.Types.VARCHAR,6,0,false,false,null,null),
    new ColumnSpec("appli_term_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("appli_term_date_count",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("commission_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec information_mail_request_columns[] = {
    new ColumnSpec("information_mail_request_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("sendmail_dev",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("discernment_id",java.sql.Types.VARCHAR,4,0,false,false,null,null),
    new ColumnSpec("all_account_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("account_count",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("request_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("commission_course_master",commission_course_master_columns,"inv=0"),
    new TableSpec("information_mail_request",information_mail_request_columns,"inv=0"),
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
