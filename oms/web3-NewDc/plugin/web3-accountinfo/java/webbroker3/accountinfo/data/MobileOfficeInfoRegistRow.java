head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	MobileOfficeInfoRegistRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MobileOfficeInfoRegistRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>mobile_office_info_regist</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link MobileOfficeInfoRegistRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MobileOfficeInfoRegistPK 
 */
public interface MobileOfficeInfoRegistRow extends Row {


  /** 
   * ����{@@link MobileOfficeInfoRegistRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "mobile_office_info_regist", "account" );


  /** 
   * <em>mobile_office_info_regist_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMobileOfficeInfoRegistId();


  /** 
   * <em>mobile_office_info_regist_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMobileOfficeInfoRegistIdIsSet();


  /** 
   * <em>mobile_office_info_regist_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMobileOfficeInfoRegistIdIsModified();


  /** 
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>branch_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getBranchId();


  /** 
   * <em>branch_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchIdIsSet();


  /** 
   * <em>branch_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchIdIsModified();


  /** 
   * <em>account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountIdIsModified();


  /** 
   * <em>account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>mobile</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMobile();


  /** 
   * <em>mobile</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMobileIsSet();


  /** 
   * <em>mobile</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMobileIsModified();


  /** 
   * <em>office</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOffice();


  /** 
   * <em>office</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeIsSet();


  /** 
   * <em>office</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeIsModified();


  /** 
   * <em>office_zip_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOfficeZipCode();


  /** 
   * <em>office_zip_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeZipCodeIsSet();


  /** 
   * <em>office_zip_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeZipCodeIsModified();


  /** 
   * <em>office_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOfficeAddress();


  /** 
   * <em>office_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeAddressIsSet();


  /** 
   * <em>office_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeAddressIsModified();


  /** 
   * <em>office_telephone</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOfficeTelephone();


  /** 
   * <em>office_telephone</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeTelephoneIsSet();


  /** 
   * <em>office_telephone</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeTelephoneIsModified();


  /** 
   * <em>post</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPost();


  /** 
   * <em>post</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPostIsSet();


  /** 
   * <em>post</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPostIsModified();


  /** 
   * <em>regist_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRegistUpdater();


  /** 
   * <em>regist_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegistUpdaterIsSet();


  /** 
   * <em>regist_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegistUpdaterIsModified();


  /** 
   * <em>decision_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDecisionFlag();


  /** 
   * <em>decision_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionFlagIsSet();


  /** 
   * <em>decision_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionFlagIsModified();


  /** 
   * <em>decision</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getDecision();


  /** 
   * <em>decision</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDecisionIsNull();


  /** 
   * <em>decision</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionIsSet();


  /** 
   * <em>decision</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionIsModified();


  /** 
   * <em>decision_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDecisionUpdater();


  /** 
   * <em>decision_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionUpdaterIsSet();


  /** 
   * <em>decision_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionUpdaterIsModified();


  /** 
   * <em>decision_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDecisionTimestamp();


  /** 
   * <em>decision_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionTimestampIsSet();


  /** 
   * <em>decision_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDecisionTimestampIsModified();


  /** 
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag();


  /** 
   * <em>delete_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeleteFlagIsSet();


  /** 
   * <em>delete_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeleteFlagIsModified();


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsModified();


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>contact_priority1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContactPriority1();


  /** 
   * <em>contact_priority1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactPriority1IsSet();


  /** 
   * <em>contact_priority1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactPriority1IsModified();


  /** 
   * <em>contact_priority2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContactPriority2();


  /** 
   * <em>contact_priority2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactPriority2IsSet();


  /** 
   * <em>contact_priority2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactPriority2IsModified();


  /** 
   * <em>contact_priority3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContactPriority3();


  /** 
   * <em>contact_priority3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactPriority3IsSet();


  /** 
   * <em>contact_priority3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactPriority3IsModified();


  /** 
   * <em>real_name1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRealName1();


  /** 
   * <em>real_name1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRealName1IsSet();


  /** 
   * <em>real_name1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRealName1IsModified();


  /** 
   * <em>real_name2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRealName2();


  /** 
   * <em>real_name2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRealName2IsSet();


  /** 
   * <em>real_name2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRealName2IsModified();


  /** 
   * <em>occupation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOccupationDiv();


  /** 
   * <em>occupation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOccupationDivIsSet();


  /** 
   * <em>occupation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOccupationDivIsModified();


  /** 
   * <em>department</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDepartment();


  /** 
   * <em>department</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepartmentIsSet();


  /** 
   * <em>department</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDepartmentIsModified();


  /** 
   * <em>nationality</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNationality();


  /** 
   * <em>nationality</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNationalityIsSet();


  /** 
   * <em>nationality</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNationalityIsModified();


  /** 
   * <em>nationality_other</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNationalityOther();


  /** 
   * <em>nationality_other</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNationalityOtherIsSet();


  /** 
   * <em>nationality_other</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNationalityOtherIsModified();


  /** 
   * <em>represent_family_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepresentFamilyName();


  /** 
   * <em>represent_family_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentFamilyNameIsSet();


  /** 
   * <em>represent_family_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentFamilyNameIsModified();


  /** 
   * <em>represent_given_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepresentGivenName();


  /** 
   * <em>represent_given_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentGivenNameIsSet();


  /** 
   * <em>represent_given_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentGivenNameIsModified();


  /** 
   * <em>represent_family_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepresentFamilyNameAlt1();


  /** 
   * <em>represent_family_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentFamilyNameAlt1IsSet();


  /** 
   * <em>represent_family_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentFamilyNameAlt1IsModified();


  /** 
   * <em>represent_given_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepresentGivenNameAlt1();


  /** 
   * <em>represent_given_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentGivenNameAlt1IsSet();


  /** 
   * <em>represent_given_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentGivenNameAlt1IsModified();


  /** 
   * <em>represent_power</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRepresentPower();


  /** 
   * <em>represent_power</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentPowerIsSet();


  /** 
   * <em>represent_power</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRepresentPowerIsModified();


  /** 
   * <em>director_family_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorFamilyName();


  /** 
   * <em>director_family_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorFamilyNameIsSet();


  /** 
   * <em>director_family_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorFamilyNameIsModified();


  /** 
   * <em>director_given_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorGivenName();


  /** 
   * <em>director_given_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorGivenNameIsSet();


  /** 
   * <em>director_given_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorGivenNameIsModified();


  /** 
   * <em>director_family_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorFamilyNameAlt1();


  /** 
   * <em>director_family_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorFamilyNameAlt1IsSet();


  /** 
   * <em>director_family_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorFamilyNameAlt1IsModified();


  /** 
   * <em>director_given_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorGivenNameAlt1();


  /** 
   * <em>director_given_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorGivenNameAlt1IsSet();


  /** 
   * <em>director_given_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorGivenNameAlt1IsModified();


  /** 
   * <em>director_department</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorDepartment();


  /** 
   * <em>director_department</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorDepartmentIsSet();


  /** 
   * <em>director_department</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorDepartmentIsModified();


  /** 
   * <em>director_post</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorPost();


  /** 
   * <em>director_post</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorPostIsSet();


  /** 
   * <em>director_post</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorPostIsModified();


  /** 
   * <em>director_zip_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorZipCode();


  /** 
   * <em>director_zip_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorZipCodeIsSet();


  /** 
   * <em>director_zip_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorZipCodeIsModified();


  /** 
   * <em>director_address1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorAddress1();


  /** 
   * <em>director_address1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorAddress1IsSet();


  /** 
   * <em>director_address1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorAddress1IsModified();


  /** 
   * <em>director_address2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorAddress2();


  /** 
   * <em>director_address2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorAddress2IsSet();


  /** 
   * <em>director_address2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorAddress2IsModified();


  /** 
   * <em>director_address3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorAddress3();


  /** 
   * <em>director_address3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorAddress3IsSet();


  /** 
   * <em>director_address3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorAddress3IsModified();


  /** 
   * <em>director_era_born</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorEraBorn();


  /** 
   * <em>director_era_born</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorEraBornIsSet();


  /** 
   * <em>director_era_born</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorEraBornIsModified();


  /** 
   * <em>director_born_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorBornDate();


  /** 
   * <em>director_born_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorBornDateIsSet();


  /** 
   * <em>director_born_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorBornDateIsModified();


  /** 
   * <em>director_corp_telephone</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDirectorCorpTelephone();


  /** 
   * <em>director_corp_telephone</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorCorpTelephoneIsSet();


  /** 
   * <em>director_corp_telephone</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDirectorCorpTelephoneIsModified();


  /** 
   * <em>other_contact</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOtherContact();


  /** 
   * <em>other_contact</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherContactIsSet();


  /** 
   * <em>other_contact</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherContactIsModified();


  /** 
   * <em>fax</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFax();


  /** 
   * <em>fax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFaxIsSet();


  /** 
   * <em>fax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFaxIsModified();


  /** 
   * <em>annual_income_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAnnualIncomeDiv();


  /** 
   * <em>annual_income_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualIncomeDivIsSet();


  /** 
   * <em>annual_income_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualIncomeDivIsModified();


  /** 
   * <em>asset_value_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAssetValueDiv();


  /** 
   * <em>asset_value_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetValueDivIsSet();


  /** 
   * <em>asset_value_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetValueDivIsModified();


  /** 
   * <em>fund_budget_amount_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFundBudgetAmountDiv();


  /** 
   * <em>fund_budget_amount_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundBudgetAmountDivIsSet();


  /** 
   * <em>fund_budget_amount_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundBudgetAmountDivIsModified();


  /** 
   * <em>invest_purpose_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInvestPurposeDiv();


  /** 
   * <em>invest_purpose_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvestPurposeDivIsSet();


  /** 
   * <em>invest_purpose_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvestPurposeDivIsModified();


  /** 
   * <em>invest_plan_period_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInvestPlanPeriodDiv();


  /** 
   * <em>invest_plan_period_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvestPlanPeriodDivIsSet();


  /** 
   * <em>invest_plan_period_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvestPlanPeriodDivIsModified();


  /** 
   * <em>experience_flag1</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag1();


  /** 
   * <em>experience_flag1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag1IsNull();


  /** 
   * <em>experience_flag1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag1IsSet();


  /** 
   * <em>experience_flag1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag1IsModified();


  /** 
   * <em>experience_flag2</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag2();


  /** 
   * <em>experience_flag2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag2IsNull();


  /** 
   * <em>experience_flag2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag2IsSet();


  /** 
   * <em>experience_flag2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag2IsModified();


  /** 
   * <em>experience_flag3</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag3();


  /** 
   * <em>experience_flag3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag3IsNull();


  /** 
   * <em>experience_flag3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag3IsSet();


  /** 
   * <em>experience_flag3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag3IsModified();


  /** 
   * <em>experience_flag4</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag4();


  /** 
   * <em>experience_flag4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag4IsNull();


  /** 
   * <em>experience_flag4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag4IsSet();


  /** 
   * <em>experience_flag4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag4IsModified();


  /** 
   * <em>experience_flag5</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag5();


  /** 
   * <em>experience_flag5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag5IsNull();


  /** 
   * <em>experience_flag5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag5IsSet();


  /** 
   * <em>experience_flag5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag5IsModified();


  /** 
   * <em>experience_flag6</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag6();


  /** 
   * <em>experience_flag6</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag6IsNull();


  /** 
   * <em>experience_flag6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag6IsSet();


  /** 
   * <em>experience_flag6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag6IsModified();


  /** 
   * <em>experience_flag7</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag7();


  /** 
   * <em>experience_flag7</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag7IsNull();


  /** 
   * <em>experience_flag7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag7IsSet();


  /** 
   * <em>experience_flag7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag7IsModified();


  /** 
   * <em>experience_flag8</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag8();


  /** 
   * <em>experience_flag8</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag8IsNull();


  /** 
   * <em>experience_flag8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag8IsSet();


  /** 
   * <em>experience_flag8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag8IsModified();


  /** 
   * <em>experience_flag9</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag9();


  /** 
   * <em>experience_flag9</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag9IsNull();


  /** 
   * <em>experience_flag9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag9IsSet();


  /** 
   * <em>experience_flag9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag9IsModified();


  /** 
   * <em>experience_flag10</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getExperienceFlag10();


  /** 
   * <em>experience_flag10</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getExperienceFlag10IsNull();


  /** 
   * <em>experience_flag10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag10IsSet();


  /** 
   * <em>experience_flag10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlag10IsModified();


  /** 
   * <em>experience_div1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv1();


  /** 
   * <em>experience_div1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv1IsSet();


  /** 
   * <em>experience_div1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv1IsModified();


  /** 
   * <em>experience_div2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv2();


  /** 
   * <em>experience_div2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv2IsSet();


  /** 
   * <em>experience_div2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv2IsModified();


  /** 
   * <em>experience_div3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv3();


  /** 
   * <em>experience_div3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv3IsSet();


  /** 
   * <em>experience_div3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv3IsModified();


  /** 
   * <em>experience_div4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv4();


  /** 
   * <em>experience_div4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv4IsSet();


  /** 
   * <em>experience_div4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv4IsModified();


  /** 
   * <em>experience_div5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv5();


  /** 
   * <em>experience_div5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv5IsSet();


  /** 
   * <em>experience_div5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv5IsModified();


  /** 
   * <em>experience_div6</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv6();


  /** 
   * <em>experience_div6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv6IsSet();


  /** 
   * <em>experience_div6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv6IsModified();


  /** 
   * <em>experience_div7</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv7();


  /** 
   * <em>experience_div7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv7IsSet();


  /** 
   * <em>experience_div7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv7IsModified();


  /** 
   * <em>experience_div8</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv8();


  /** 
   * <em>experience_div8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv8IsSet();


  /** 
   * <em>experience_div8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv8IsModified();


  /** 
   * <em>experience_div9</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv9();


  /** 
   * <em>experience_div9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv9IsSet();


  /** 
   * <em>experience_div9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv9IsModified();


  /** 
   * <em>experience_div10</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDiv10();


  /** 
   * <em>experience_div10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv10IsSet();


  /** 
   * <em>experience_div10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDiv10IsModified();


  /** 
   * <em>interest_flag1</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag1();


  /** 
   * <em>interest_flag1</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag1IsNull();


  /** 
   * <em>interest_flag1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag1IsSet();


  /** 
   * <em>interest_flag1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag1IsModified();


  /** 
   * <em>interest_flag2</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag2();


  /** 
   * <em>interest_flag2</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag2IsNull();


  /** 
   * <em>interest_flag2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag2IsSet();


  /** 
   * <em>interest_flag2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag2IsModified();


  /** 
   * <em>interest_flag3</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag3();


  /** 
   * <em>interest_flag3</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag3IsNull();


  /** 
   * <em>interest_flag3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag3IsSet();


  /** 
   * <em>interest_flag3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag3IsModified();


  /** 
   * <em>interest_flag4</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag4();


  /** 
   * <em>interest_flag4</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag4IsNull();


  /** 
   * <em>interest_flag4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag4IsSet();


  /** 
   * <em>interest_flag4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag4IsModified();


  /** 
   * <em>interest_flag5</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag5();


  /** 
   * <em>interest_flag5</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag5IsNull();


  /** 
   * <em>interest_flag5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag5IsSet();


  /** 
   * <em>interest_flag5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag5IsModified();


  /** 
   * <em>interest_flag6</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag6();


  /** 
   * <em>interest_flag6</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag6IsNull();


  /** 
   * <em>interest_flag6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag6IsSet();


  /** 
   * <em>interest_flag6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag6IsModified();


  /** 
   * <em>interest_flag7</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag7();


  /** 
   * <em>interest_flag7</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag7IsNull();


  /** 
   * <em>interest_flag7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag7IsSet();


  /** 
   * <em>interest_flag7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag7IsModified();


  /** 
   * <em>interest_flag8</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag8();


  /** 
   * <em>interest_flag8</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag8IsNull();


  /** 
   * <em>interest_flag8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag8IsSet();


  /** 
   * <em>interest_flag8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag8IsModified();


  /** 
   * <em>interest_flag9</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag9();


  /** 
   * <em>interest_flag9</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag9IsNull();


  /** 
   * <em>interest_flag9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag9IsSet();


  /** 
   * <em>interest_flag9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag9IsModified();


  /** 
   * <em>interest_flag10</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInterestFlag10();


  /** 
   * <em>interest_flag10</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInterestFlag10IsNull();


  /** 
   * <em>interest_flag10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag10IsSet();


  /** 
   * <em>interest_flag10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlag10IsModified();


  /** 
   * <em>appli_motivat_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAppliMotivatDiv();


  /** 
   * <em>appli_motivat_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAppliMotivatDivIsSet();


  /** 
   * <em>appli_motivat_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAppliMotivatDivIsModified();


  /** 
   * <em>appli_motivat_div_detail</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAppliMotivatDivDetail();


  /** 
   * <em>appli_motivat_div_detail</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAppliMotivatDivDetailIsSet();


  /** 
   * <em>appli_motivat_div_detail</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAppliMotivatDivDetailIsModified();


  /** 
   * <em>use_institution_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getUseInstitutionDiv();


  /** 
   * <em>use_institution_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUseInstitutionDivIsSet();


  /** 
   * <em>use_institution_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUseInstitutionDivIsModified();


  /** 
   * <em>internet_trade_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInternetTradeDiv();


  /** 
   * <em>internet_trade_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInternetTradeDivIsSet();


  /** 
   * <em>internet_trade_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInternetTradeDivIsModified();


  /** 
   * <em>introduce_branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIntroduceBranchCode();


  /** 
   * <em>introduce_branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIntroduceBranchCodeIsSet();


  /** 
   * <em>introduce_branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIntroduceBranchCodeIsModified();


  /** 
   * <em>accept_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAcceptStatus();


  /** 
   * <em>accept_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptStatusIsSet();


  /** 
   * <em>accept_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAcceptStatusIsModified();


}
@
