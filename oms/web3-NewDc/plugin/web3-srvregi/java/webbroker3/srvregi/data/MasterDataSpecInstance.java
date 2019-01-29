head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec srv_regi_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("srv_name",java.sql.Types.VARCHAR,100,0,false,false,null,null),
    new ColumnSpec("srv_status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("offering_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("srv_url",java.sql.Types.VARCHAR,256,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("special_process_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec srv_regi_lot_info_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("consecutive_numbers",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("appli_date_from",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("appli_date_to",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("lot_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("use_amt",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("bidding_price",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("payment_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("public_offering_qty",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("invest_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("high_success_bid",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("low_success_bid",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("average_success_bid",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_regi_key_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("srv_use_key_type",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("srv_use_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("srv_use_key",java.sql.Types.VARCHAR,256,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_regi_charge_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("consecutive_numbers",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("srv_use_period_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("srv_use_period",java.sql.Types.NUMERIC,3,0,false,false,null,null),
    new ColumnSpec("use_amt",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_regi_setup_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("summary",java.sql.Types.VARCHAR,25,0,false,false,null,null),
    new ColumnSpec("lot_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("trial_period_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("trial_period",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("appli_date_from",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("appli_date_to",java.sql.Types.NUMERIC,2,0,true,false,null,null),
    new ColumnSpec("srv_contents",java.sql.Types.VARCHAR,4000,0,true,false,null,null),
    new ColumnSpec("srv_explan_url",java.sql.Types.VARCHAR,256,0,true,false,null,null),
    new ColumnSpec("start_mail_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("end_mail_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("send_mail_interval",java.sql.Types.NUMERIC,3,0,true,false,null,null),
    new ColumnSpec("electric_issue_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("min_comm_amt",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("supply_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("free_coverage_length",java.sql.Types.NUMERIC,2,0,true,false,null,null),
  };
  private static final ColumnSpec srv_regi_cons_doc_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("line_number",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("line_value",java.sql.Types.VARCHAR,4000,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_regi_dealing_comm_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,20,0,false,true,null,null),
    new ColumnSpec("accumulate_term",java.sql.Types.VARCHAR,6,0,false,true,null,null),
    new ColumnSpec("order_acc_product",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("comm_amt",java.sql.Types.DECIMAL,18,6,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_regi_comm_cond_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("order_acc_product",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_regi_comm_cond_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("order_acc_product",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("comm_prod_type_name",java.sql.Types.VARCHAR,100,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_regi_history_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("regist_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("order_root_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec srv_appli_attribute_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("appli_attribute",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("proc_div",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };
  private static final ColumnSpec other_org_info_admin_columns[] = {
    new ColumnSpec("sequence_number",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("srv_div",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("id",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("password",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("appli_start_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("appli_end_date",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("srv_regi_master",srv_regi_master_columns,"inv=0"),
    new TableSpec("srv_regi_lot_info",srv_regi_lot_info_columns,"inv=0"),
    new TableSpec("srv_regi_key",srv_regi_key_columns,"inv=0"),
    new TableSpec("srv_regi_charge",srv_regi_charge_columns,"inv=0"),
    new TableSpec("srv_regi_setup",srv_regi_setup_columns,"inv=0"),
    new TableSpec("srv_regi_cons_doc",srv_regi_cons_doc_columns,"inv=0"),
    new TableSpec("srv_regi_dealing_comm",srv_regi_dealing_comm_columns,"inv=0"),
    new TableSpec("srv_regi_comm_cond",srv_regi_comm_cond_columns,"inv=0"),
    new TableSpec("srv_regi_comm_cond_master",srv_regi_comm_cond_master_columns,"inv=0"),
    new TableSpec("srv_regi_history",srv_regi_history_columns,"inv=0"),
    new TableSpec("srv_appli_attribute",srv_appli_attribute_columns,"inv=0"),
    new TableSpec("other_org_info_admin",other_org_info_admin_columns,"inv=0"),
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
