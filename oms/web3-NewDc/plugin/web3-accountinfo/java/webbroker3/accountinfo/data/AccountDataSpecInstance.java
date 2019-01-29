head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	AccountDataSpecInstance.java;


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

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec commission_course_regist_columns[] = {
    new ColumnSpec("commission_course_regist_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("comm_product_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("appli_start_datetime",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("appli_end_datetime",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("commission_course_div",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("regist_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("regist_end_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("download_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec mobile_office_info_regist_columns[] = {
    new ColumnSpec("mobile_office_info_regist_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,false,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,false,false,null,null),
    new ColumnSpec("mobile",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("office",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("office_zip_code",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("office_address",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("office_telephone",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("post",java.sql.Types.VARCHAR,36,0,true,false,null,null),
    new ColumnSpec("regist_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("decision_flag",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("decision",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("decision_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("decision_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("delete_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("contact_priority1",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("contact_priority2",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("contact_priority3",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("real_name1",java.sql.Types.VARCHAR,66,0,true,false,null,null),
    new ColumnSpec("real_name2",java.sql.Types.VARCHAR,66,0,true,false,null,null),
    new ColumnSpec("occupation_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("department",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("nationality",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("nationality_other",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_family_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_given_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_family_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_given_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("represent_power",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_family_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_given_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_family_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_given_name_alt1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_department",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_post",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_zip_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("director_address1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_address2",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_address3",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("director_era_born",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("director_born_date",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("director_corp_telephone",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("other_contact",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("fax",java.sql.Types.VARCHAR,16,0,true,false,null,null),
    new ColumnSpec("annual_income_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("asset_value_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("fund_budget_amount_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("invest_purpose_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("invest_plan_period_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_flag1",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag2",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag3",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag4",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag5",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag6",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag7",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag8",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag9",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_flag10",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("experience_div1",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div2",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div3",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div4",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div5",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div6",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div7",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div8",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div9",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("experience_div10",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("interest_flag1",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag2",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag3",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag4",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag5",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag6",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag7",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag8",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag9",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("interest_flag10",java.sql.Types.NUMERIC,1,0,true,false,null,null),
    new ColumnSpec("appli_motivat_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("appli_motivat_div_detail",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("use_institution_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("internet_trade_div",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("introduce_branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("accept_status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("commission_course_regist",commission_course_regist_columns,"inv=?.account_id"),
    new TableSpec("mobile_office_info_regist",mobile_office_info_regist_columns,"inv=?.account_id"),
  };

  private static final AccountDataSpecInstance instance = new AccountDataSpecInstance();

  private AccountDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
