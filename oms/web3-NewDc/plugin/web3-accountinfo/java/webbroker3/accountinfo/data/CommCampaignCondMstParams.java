head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignCondMstParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * comm_campaign_cond_mst�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link CommCampaignCondMstRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link CommCampaignCondMstParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link CommCampaignCondMstParams}��{@@link CommCampaignCondMstRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommCampaignCondMstPK 
 * @@see CommCampaignCondMstRow 
 */
public class CommCampaignCondMstParams extends Params implements CommCampaignCondMstRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "comm_campaign_cond_mst";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = CommCampaignCondMstRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return CommCampaignCondMstRow.TYPE;
  }


  /** 
   * <em>campaign_id</em>�J�����̒l 
   */
  public  long  campaign_id;

  /** 
   * <em>comm_campaign_name</em>�J�����̒l 
   */
  public  String  comm_campaign_name;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�J�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>�J�����̒l 
   */
  public  String  account_code;

  /** 
   * <em>family_name</em>�J�����̒l 
   */
  public  String  family_name;

  /** 
   * <em>acc_open_pass_month</em>�J�����̒l 
   */
  public  String  acc_open_pass_month;

  /** 
   * <em>acc_open_pass_date</em>�J�����̒l 
   */
  public  String  acc_open_pass_date;

  /** 
   * <em>appli_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>appli_end_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>account_charge_ratio</em>�J�����̒l 
   */
  public  double  account_charge_ratio;

  /** 
   * <em>sonar_trader_code</em>�J�����̒l 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>acc_open_kind_div</em>�J�����̒l 
   */
  public  String  acc_open_kind_div;

  /** 
   * <em>acc_open_date_from</em>�J�����̒l 
   */
  public  java.sql.Timestamp  acc_open_date_from;

  /** 
   * <em>acc_open_date_to</em>�J�����̒l 
   */
  public  java.sql.Timestamp  acc_open_date_to;

  /** 
   * <em>regist_type</em>�J�����̒l 
   */
  public  String  regist_type;

  /** 
   * <em>delete_flag</em>�J�����̒l 
   */
  public  String  delete_flag;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean campaign_id_is_set = false;
  private boolean campaign_id_is_modified = false;
  private boolean comm_campaign_name_is_set = false;
  private boolean comm_campaign_name_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean acc_open_pass_month_is_set = false;
  private boolean acc_open_pass_month_is_modified = false;
  private boolean acc_open_pass_date_is_set = false;
  private boolean acc_open_pass_date_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean account_charge_ratio_is_set = false;
  private boolean account_charge_ratio_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean acc_open_kind_div_is_set = false;
  private boolean acc_open_kind_div_is_modified = false;
  private boolean acc_open_date_from_is_set = false;
  private boolean acc_open_date_from_is_modified = false;
  private boolean acc_open_date_to_is_set = false;
  private boolean acc_open_date_to_is_modified = false;
  private boolean regist_type_is_set = false;
  private boolean regist_type_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "campaign_id=" + campaign_id
      + "," + "comm_campaign_name=" +comm_campaign_name
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "family_name=" +family_name
      + "," + "acc_open_pass_month=" +acc_open_pass_month
      + "," + "acc_open_pass_date=" +acc_open_pass_date
      + "," + "appli_start_date=" +appli_start_date
      + "," + "appli_end_date=" +appli_end_date
      + "," + "account_charge_ratio=" +account_charge_ratio
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "acc_open_kind_div=" +acc_open_kind_div
      + "," + "acc_open_date_from=" +acc_open_date_from
      + "," + "acc_open_date_to=" +acc_open_date_to
      + "," + "regist_type=" +regist_type
      + "," + "delete_flag=" +delete_flag
      + "," + "status=" +status
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��CommCampaignCondMstParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public CommCampaignCondMstParams() {
    campaign_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���CommCampaignCondMstRow�I�u�W�F�N�g�̒l�𗘗p����CommCampaignCondMstParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����CommCampaignCondMstRow�I�u�W�F�N�g 
   */
  public CommCampaignCondMstParams( CommCampaignCondMstRow p_row )
  {
    campaign_id = p_row.getCampaignId();
    campaign_id_is_set = p_row.getCampaignIdIsSet();
    campaign_id_is_modified = p_row.getCampaignIdIsModified();
    comm_campaign_name = p_row.getCommCampaignName();
    comm_campaign_name_is_set = p_row.getCommCampaignNameIsSet();
    comm_campaign_name_is_modified = p_row.getCommCampaignNameIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    acc_open_pass_month = p_row.getAccOpenPassMonth();
    acc_open_pass_month_is_set = p_row.getAccOpenPassMonthIsSet();
    acc_open_pass_month_is_modified = p_row.getAccOpenPassMonthIsModified();
    acc_open_pass_date = p_row.getAccOpenPassDate();
    acc_open_pass_date_is_set = p_row.getAccOpenPassDateIsSet();
    acc_open_pass_date_is_modified = p_row.getAccOpenPassDateIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    account_charge_ratio = p_row.getAccountChargeRatio();
    account_charge_ratio_is_set = p_row.getAccountChargeRatioIsSet();
    account_charge_ratio_is_modified = p_row.getAccountChargeRatioIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    acc_open_kind_div = p_row.getAccOpenKindDiv();
    acc_open_kind_div_is_set = p_row.getAccOpenKindDivIsSet();
    acc_open_kind_div_is_modified = p_row.getAccOpenKindDivIsModified();
    acc_open_date_from = p_row.getAccOpenDateFrom();
    acc_open_date_from_is_set = p_row.getAccOpenDateFromIsSet();
    acc_open_date_from_is_modified = p_row.getAccOpenDateFromIsModified();
    acc_open_date_to = p_row.getAccOpenDateTo();
    acc_open_date_to_is_set = p_row.getAccOpenDateToIsSet();
    acc_open_date_to_is_modified = p_row.getAccOpenDateToIsModified();
    regist_type = p_row.getRegistType();
    regist_type_is_set = p_row.getRegistTypeIsSet();
    regist_type_is_modified = p_row.getRegistTypeIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      comm_campaign_name_is_set = true;
      comm_campaign_name_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      acc_open_pass_month_is_set = true;
      acc_open_pass_month_is_modified = true;
      acc_open_pass_date_is_set = true;
      acc_open_pass_date_is_modified = true;
      appli_start_date_is_set = true;
      appli_start_date_is_modified = true;
      appli_end_date_is_set = true;
      appli_end_date_is_modified = true;
      account_charge_ratio_is_set = true;
      account_charge_ratio_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      acc_open_kind_div_is_set = true;
      acc_open_kind_div_is_modified = true;
      acc_open_date_from_is_set = true;
      acc_open_date_from_is_modified = true;
      acc_open_date_to_is_set = true;
      acc_open_date_to_is_modified = true;
      regist_type_is_set = true;
      regist_type_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CommCampaignCondMstRow ) )
       return false;
    return fieldsEqual( (CommCampaignCondMstRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�CommCampaignCondMstRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( CommCampaignCondMstRow row )
  {
    if ( campaign_id != row.getCampaignId() )
      return false;
    if ( comm_campaign_name == null ) {
      if ( row.getCommCampaignName() != null )
        return false;
    } else if ( !comm_campaign_name.equals( row.getCommCampaignName() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( acc_open_pass_month == null ) {
      if ( row.getAccOpenPassMonth() != null )
        return false;
    } else if ( !acc_open_pass_month.equals( row.getAccOpenPassMonth() ) ) {
        return false;
    }
    if ( acc_open_pass_date == null ) {
      if ( row.getAccOpenPassDate() != null )
        return false;
    } else if ( !acc_open_pass_date.equals( row.getAccOpenPassDate() ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( row.getAppliStartDate() != null )
        return false;
    } else if ( !appli_start_date.equals( row.getAppliStartDate() ) ) {
        return false;
    }
    if ( appli_end_date == null ) {
      if ( row.getAppliEndDate() != null )
        return false;
    } else if ( !appli_end_date.equals( row.getAppliEndDate() ) ) {
        return false;
    }
    if ( account_charge_ratio != row.getAccountChargeRatio() )
      return false;
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( acc_open_kind_div == null ) {
      if ( row.getAccOpenKindDiv() != null )
        return false;
    } else if ( !acc_open_kind_div.equals( row.getAccOpenKindDiv() ) ) {
        return false;
    }
    if ( acc_open_date_from == null ) {
      if ( row.getAccOpenDateFrom() != null )
        return false;
    } else if ( !acc_open_date_from.equals( row.getAccOpenDateFrom() ) ) {
        return false;
    }
    if ( acc_open_date_to == null ) {
      if ( row.getAccOpenDateTo() != null )
        return false;
    } else if ( !acc_open_date_to.equals( row.getAccOpenDateTo() ) ) {
        return false;
    }
    if ( regist_type == null ) {
      if ( row.getRegistType() != null )
        return false;
    } else if ( !regist_type.equals( row.getRegistType() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) campaign_id)
        + (comm_campaign_name!=null? comm_campaign_name.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (acc_open_pass_month!=null? acc_open_pass_month.hashCode(): 0) 
        + (acc_open_pass_date!=null? acc_open_pass_date.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + ((int) account_charge_ratio)
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (acc_open_kind_div!=null? acc_open_kind_div.hashCode(): 0) 
        + (acc_open_date_from!=null? acc_open_date_from.hashCode(): 0) 
        + (acc_open_date_to!=null? acc_open_date_to.hashCode(): 0) 
        + (regist_type!=null? regist_type.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !account_charge_ratio_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_charge_ratio' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("campaign_id",new Long(campaign_id));
		if ( comm_campaign_name != null )
			map.put("comm_campaign_name",comm_campaign_name);
		map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( family_name != null )
			map.put("family_name",family_name);
		if ( acc_open_pass_month != null )
			map.put("acc_open_pass_month",acc_open_pass_month);
		if ( acc_open_pass_date != null )
			map.put("acc_open_pass_date",acc_open_pass_date);
		if ( appli_start_date != null )
			map.put("appli_start_date",appli_start_date);
		if ( appli_end_date != null )
			map.put("appli_end_date",appli_end_date);
		map.put("account_charge_ratio",new Double(account_charge_ratio));
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( acc_open_kind_div != null )
			map.put("acc_open_kind_div",acc_open_kind_div);
		if ( acc_open_date_from != null )
			map.put("acc_open_date_from",acc_open_date_from);
		if ( acc_open_date_to != null )
			map.put("acc_open_date_to",acc_open_date_to);
		if ( regist_type != null )
			map.put("regist_type",regist_type);
		if ( delete_flag != null )
			map.put("delete_flag",delete_flag);
		if ( status != null )
			map.put("status",status);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( comm_campaign_name_is_modified )
			map.put("comm_campaign_name",comm_campaign_name);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( acc_open_pass_month_is_modified )
			map.put("acc_open_pass_month",acc_open_pass_month);
		if ( acc_open_pass_date_is_modified )
			map.put("acc_open_pass_date",acc_open_pass_date);
		if ( appli_start_date_is_modified )
			map.put("appli_start_date",appli_start_date);
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( account_charge_ratio_is_modified )
			map.put("account_charge_ratio",new Double(account_charge_ratio));
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( acc_open_kind_div_is_modified )
			map.put("acc_open_kind_div",acc_open_kind_div);
		if ( acc_open_date_from_is_modified )
			map.put("acc_open_date_from",acc_open_date_from);
		if ( acc_open_date_to_is_modified )
			map.put("acc_open_date_to",acc_open_date_to);
		if ( regist_type_is_modified )
			map.put("regist_type",regist_type);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( status_is_modified )
			map.put("status",status);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("comm_campaign_name",comm_campaign_name);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("family_name",family_name);
			map.put("acc_open_pass_month",acc_open_pass_month);
			map.put("acc_open_pass_date",acc_open_pass_date);
			map.put("appli_start_date",appli_start_date);
			map.put("appli_end_date",appli_end_date);
			if ( account_charge_ratio_is_set )
				map.put("account_charge_ratio",new Double(account_charge_ratio));
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("acc_open_kind_div",acc_open_kind_div);
			map.put("acc_open_date_from",acc_open_date_from);
			map.put("acc_open_date_to",acc_open_date_to);
			map.put("regist_type",regist_type);
			map.put("delete_flag",delete_flag);
			map.put("status",status);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>campaign_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getCampaignId()
  {
    return campaign_id;
  }


  /** 
   * <em>campaign_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCampaignIdIsSet() {
    return campaign_id_is_set;
  }


  /** 
   * <em>campaign_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCampaignIdIsModified() {
    return campaign_id_is_modified;
  }


  /** 
   * <em>comm_campaign_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommCampaignName()
  {
    return comm_campaign_name;
  }


  /** 
   * <em>comm_campaign_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommCampaignNameIsSet() {
    return comm_campaign_name_is_set;
  }


  /** 
   * <em>comm_campaign_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommCampaignNameIsModified() {
    return comm_campaign_name_is_modified;
  }


  /** 
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>family_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFamilyName()
  {
    return family_name;
  }


  /** 
   * <em>family_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFamilyNameIsSet() {
    return family_name_is_set;
  }


  /** 
   * <em>family_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFamilyNameIsModified() {
    return family_name_is_modified;
  }


  /** 
   * <em>acc_open_pass_month</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccOpenPassMonth()
  {
    return acc_open_pass_month;
  }


  /** 
   * <em>acc_open_pass_month</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenPassMonthIsSet() {
    return acc_open_pass_month_is_set;
  }


  /** 
   * <em>acc_open_pass_month</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenPassMonthIsModified() {
    return acc_open_pass_month_is_modified;
  }


  /** 
   * <em>acc_open_pass_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccOpenPassDate()
  {
    return acc_open_pass_date;
  }


  /** 
   * <em>acc_open_pass_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenPassDateIsSet() {
    return acc_open_pass_date_is_set;
  }


  /** 
   * <em>acc_open_pass_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenPassDateIsModified() {
    return acc_open_pass_date_is_modified;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliStartDate()
  {
    return appli_start_date;
  }


  /** 
   * <em>appli_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateIsSet() {
    return appli_start_date_is_set;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateIsModified() {
    return appli_start_date_is_modified;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliEndDate()
  {
    return appli_end_date;
  }


  /** 
   * <em>appli_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndDateIsSet() {
    return appli_end_date_is_set;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndDateIsModified() {
    return appli_end_date_is_modified;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAccountChargeRatio()
  {
    return account_charge_ratio;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountChargeRatioIsSet() {
    return account_charge_ratio_is_set;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountChargeRatioIsModified() {
    return account_charge_ratio_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>acc_open_kind_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccOpenKindDiv()
  {
    return acc_open_kind_div;
  }


  /** 
   * <em>acc_open_kind_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenKindDivIsSet() {
    return acc_open_kind_div_is_set;
  }


  /** 
   * <em>acc_open_kind_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenKindDivIsModified() {
    return acc_open_kind_div_is_modified;
  }


  /** 
   * <em>acc_open_date_from</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAccOpenDateFrom()
  {
    return acc_open_date_from;
  }


  /** 
   * <em>acc_open_date_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenDateFromIsSet() {
    return acc_open_date_from_is_set;
  }


  /** 
   * <em>acc_open_date_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenDateFromIsModified() {
    return acc_open_date_from_is_modified;
  }


  /** 
   * <em>acc_open_date_to</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAccOpenDateTo()
  {
    return acc_open_date_to;
  }


  /** 
   * <em>acc_open_date_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenDateToIsSet() {
    return acc_open_date_to_is_set;
  }


  /** 
   * <em>acc_open_date_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccOpenDateToIsModified() {
    return acc_open_date_to_is_modified;
  }


  /** 
   * <em>regist_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRegistType()
  {
    return regist_type;
  }


  /** 
   * <em>regist_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistTypeIsSet() {
    return regist_type_is_set;
  }


  /** 
   * <em>regist_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegistTypeIsModified() {
    return regist_type_is_modified;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
  }


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CommCampaignCondMstPK(campaign_id);
  }


  /** 
   * <em>campaign_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_campaignId <em>campaign_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setCampaignId( long p_campaignId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    campaign_id = p_campaignId;
    campaign_id_is_set = true;
    campaign_id_is_modified = true;
  }


  /** 
   * <em>comm_campaign_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commCampaignName <em>comm_campaign_name</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public final void setCommCampaignName( String p_commCampaignName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_campaign_name = p_commCampaignName;
    comm_campaign_name_is_set = true;
    comm_campaign_name_is_modified = true;
  }


  /** 
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountCode <em>account_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>family_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_familyName <em>family_name</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public final void setFamilyName( String p_familyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name = p_familyName;
    family_name_is_set = true;
    family_name_is_modified = true;
  }


  /** 
   * <em>acc_open_pass_month</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accOpenPassMonth <em>acc_open_pass_month</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setAccOpenPassMonth( String p_accOpenPassMonth )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_pass_month = p_accOpenPassMonth;
    acc_open_pass_month_is_set = true;
    acc_open_pass_month_is_modified = true;
  }


  /** 
   * <em>acc_open_pass_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accOpenPassDate <em>acc_open_pass_date</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setAccOpenPassDate( String p_accOpenPassDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_pass_date = p_accOpenPassDate;
    acc_open_pass_date_is_set = true;
    acc_open_pass_date_is_modified = true;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartDate <em>appli_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliStartDate( java.sql.Timestamp p_appliStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = p_appliStartDate;
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliEndDate <em>appli_end_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliEndDate( java.sql.Timestamp p_appliEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = p_appliEndDate;
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_end_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


  /** 
   * <em>account_charge_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountChargeRatio <em>account_charge_ratio</em>�J�����̒l������킷8���ȉ���double�l�ŁA���̐��x��5���܂�
   */
  public final void setAccountChargeRatio( double p_accountChargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_charge_ratio = p_accountChargeRatio;
    account_charge_ratio_is_set = true;
    account_charge_ratio_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>acc_open_kind_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accOpenKindDiv <em>acc_open_kind_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAccOpenKindDiv( String p_accOpenKindDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_kind_div = p_accOpenKindDiv;
    acc_open_kind_div_is_set = true;
    acc_open_kind_div_is_modified = true;
  }


  /** 
   * <em>acc_open_date_from</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accOpenDateFrom <em>acc_open_date_from</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAccOpenDateFrom( java.sql.Timestamp p_accOpenDateFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_date_from = p_accOpenDateFrom;
    acc_open_date_from_is_set = true;
    acc_open_date_from_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>acc_open_date_from</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAccOpenDateFrom( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_date_from = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    acc_open_date_from_is_set = true;
    acc_open_date_from_is_modified = true;
  }


  /** 
   * <em>acc_open_date_to</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accOpenDateTo <em>acc_open_date_to</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAccOpenDateTo( java.sql.Timestamp p_accOpenDateTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_date_to = p_accOpenDateTo;
    acc_open_date_to_is_set = true;
    acc_open_date_to_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>acc_open_date_to</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAccOpenDateTo( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_date_to = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    acc_open_date_to_is_set = true;
    acc_open_date_to_is_modified = true;
  }


  /** 
   * <em>regist_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_registType <em>regist_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRegistType( String p_registType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_type = p_registType;
    regist_type_is_set = true;
    regist_type_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deleteFlag <em>delete_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setDeleteFlag( String p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
  }


  /** 
   * <em>status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_status <em>status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>last_updater</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdater <em>last_updater</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("acc_open_pass_month") ) {
                    return this.acc_open_pass_month;
                }
                else if ( name.equals("acc_open_pass_date") ) {
                    return this.acc_open_pass_date;
                }
                else if ( name.equals("appli_start_date") ) {
                    return this.appli_start_date;
                }
                else if ( name.equals("appli_end_date") ) {
                    return this.appli_end_date;
                }
                else if ( name.equals("account_charge_ratio") ) {
                    return new Double( account_charge_ratio );
                }
                else if ( name.equals("acc_open_kind_div") ) {
                    return this.acc_open_kind_div;
                }
                else if ( name.equals("acc_open_date_from") ) {
                    return this.acc_open_date_from;
                }
                else if ( name.equals("acc_open_date_to") ) {
                    return this.acc_open_date_to;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("campaign_id") ) {
                    return new Long( campaign_id );
                }
                else if ( name.equals("comm_campaign_name") ) {
                    return this.comm_campaign_name;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("regist_type") ) {
                    return this.regist_type;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_pass_month") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_pass_month' must be String: '"+value+"' is not." );
                    this.acc_open_pass_month = (String) value;
                    if (this.acc_open_pass_month_is_set)
                        this.acc_open_pass_month_is_modified = true;
                    this.acc_open_pass_month_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_pass_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_pass_date' must be String: '"+value+"' is not." );
                    this.acc_open_pass_date = (String) value;
                    if (this.acc_open_pass_date_is_set)
                        this.acc_open_pass_date_is_modified = true;
                    this.acc_open_pass_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_date = (java.sql.Timestamp) value;
                    if (this.appli_start_date_is_set)
                        this.appli_start_date_is_modified = true;
                    this.appli_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_date = (java.sql.Timestamp) value;
                    if (this.appli_end_date_is_set)
                        this.appli_end_date_is_modified = true;
                    this.appli_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("account_charge_ratio") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_charge_ratio' must be Double: '"+value+"' is not." );
                    this.account_charge_ratio = ((Double) value).doubleValue();
                    if (this.account_charge_ratio_is_set)
                        this.account_charge_ratio_is_modified = true;
                    this.account_charge_ratio_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_kind_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_kind_div' must be String: '"+value+"' is not." );
                    this.acc_open_kind_div = (String) value;
                    if (this.acc_open_kind_div_is_set)
                        this.acc_open_kind_div_is_modified = true;
                    this.acc_open_kind_div_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_date_from") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'acc_open_date_from' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.acc_open_date_from = (java.sql.Timestamp) value;
                    if (this.acc_open_date_from_is_set)
                        this.acc_open_date_from_is_modified = true;
                    this.acc_open_date_from_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_date_to") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'acc_open_date_to' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.acc_open_date_to = (java.sql.Timestamp) value;
                    if (this.acc_open_date_to_is_set)
                        this.acc_open_date_to_is_modified = true;
                    this.acc_open_date_to_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("campaign_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'campaign_id' must be Long: '"+value+"' is not." );
                    this.campaign_id = ((Long) value).longValue();
                    if (this.campaign_id_is_set)
                        this.campaign_id_is_modified = true;
                    this.campaign_id_is_set = true;
                    return;
                }
                else if ( name.equals("comm_campaign_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_campaign_name' must be String: '"+value+"' is not." );
                    this.comm_campaign_name = (String) value;
                    if (this.comm_campaign_name_is_set)
                        this.comm_campaign_name_is_modified = true;
                    this.comm_campaign_name_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be String: '"+value+"' is not." );
                    this.delete_flag = (String) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name' must be String: '"+value+"' is not." );
                    this.family_name = (String) value;
                    if (this.family_name_is_set)
                        this.family_name_is_modified = true;
                    this.family_name_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("regist_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_type' must be String: '"+value+"' is not." );
                    this.regist_type = (String) value;
                    if (this.regist_type_is_set)
                        this.regist_type_is_modified = true;
                    this.regist_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
