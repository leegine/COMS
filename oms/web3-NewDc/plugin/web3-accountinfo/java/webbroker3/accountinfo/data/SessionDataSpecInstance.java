head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	SessionDataSpecInstance.java;


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

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec host_lock_regist_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("before_mng_lock_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("before_mng_lock_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("before_branch_lock",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("before_order_permission",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("before_lock_regist_reason",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("before_yellow_customer",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mng_lock_cancel_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("mng_lock_cancel_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("mng_lock_cancel_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("branch_lock",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("order_permission",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("lock_registration_reason",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("attribute",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("regi_erase_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec host_lock_regi_accept_columns[] = {
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };
  private static final ColumnSpec comm_account_send_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("operations_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("comm_product_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.VARCHAR,8,0,false,true,null,null),
    new ColumnSpec("commission_no",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("account_charge_ratio",java.sql.Types.DECIMAL,8,5,false,false,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec comm_campaign_cond_mst_columns[] = {
    new ColumnSpec("campaign_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("comm_campaign_name",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("family_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("acc_open_pass_month",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("acc_open_pass_date",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("account_charge_ratio",java.sql.Types.DECIMAL,8,5,false,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("acc_open_kind_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("acc_open_date_from",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("acc_open_date_to",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("regist_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec comm_campaign_product_mst_columns[] = {
    new ColumnSpec("campaign_id",java.sql.Types.NUMERIC,18,0,false,true,"comm_campaign_cond_mst","campaign_id"),
    new ColumnSpec("comm_product_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec comm_campaign_acc_history_columns[] = {
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("campaign_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("comm_campaign_name",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("family_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("acc_open_kind_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("account_charge_ratio",java.sql.Types.DECIMAL,8,5,false,false,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("regist_type",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("valid_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("host_lock_regist",host_lock_regist_columns,"rowid_pk=true"),
    new TableSpec("host_lock_regi_accept",host_lock_regi_accept_columns,"rowid_pk=true"),
    new TableSpec("comm_account_send",comm_account_send_columns,null),
    new TableSpec("comm_campaign_cond_mst",comm_campaign_cond_mst_columns,null),
    new TableSpec("comm_campaign_product_mst",comm_campaign_product_mst_columns,null),
    new TableSpec("comm_campaign_acc_history",comm_campaign_acc_history_columns,null),
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
