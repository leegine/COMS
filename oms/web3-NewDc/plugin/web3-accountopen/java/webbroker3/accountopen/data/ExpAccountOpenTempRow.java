head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ExpAccountOpenTempRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ExpAccountOpenTempRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>exp_account_open_temp</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link ExpAccountOpenTempRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ExpAccountOpenTempPK 
 */
public interface ExpAccountOpenTempRow extends Row {


  /** 
   * ����{@@link ExpAccountOpenTempRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "exp_account_open_temp", "session" );


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
   * <em>institution_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getInstitutionId();


  /** 
   * <em>institution_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionIdIsSet();


  /** 
   * <em>institution_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionIdIsModified();


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
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>acc_open_request_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccOpenRequestNumber();


  /** 
   * <em>acc_open_request_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccOpenRequestNumberIsSet();


  /** 
   * <em>acc_open_request_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccOpenRequestNumberIsModified();


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
   * <em>sonar_trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSonarTraderCode();


  /** 
   * <em>sonar_trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSonarTraderCodeIsSet();


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSonarTraderCodeIsModified();


  /** 
   * <em>ex_account_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExAccountFlag();


  /** 
   * <em>ex_account_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExAccountFlagIsSet();


  /** 
   * <em>ex_account_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExAccountFlagIsModified();


  /** 
   * <em>ex_branch_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExBranchName();


  /** 
   * <em>ex_branch_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExBranchNameIsSet();


  /** 
   * <em>ex_branch_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExBranchNameIsModified();


  /** 
   * <em>ex_account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExAccountCode();


  /** 
   * <em>ex_account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExAccountCodeIsSet();


  /** 
   * <em>ex_account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExAccountCodeIsModified();


  /** 
   * <em>account_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountDiv();


  /** 
   * <em>account_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountDivIsSet();


  /** 
   * <em>account_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountDivIsModified();


  /** 
   * <em>order_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderDiv();


  /** 
   * <em>order_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderDivIsSet();


  /** 
   * <em>order_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderDivIsModified();


  /** 
   * <em>infomation_claim_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getInfomationClaimDatetime();


  /** 
   * <em>infomation_claim_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInfomationClaimDatetimeIsSet();


  /** 
   * <em>infomation_claim_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInfomationClaimDatetimeIsModified();


  /** 
   * <em>account_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getAccountOpenDate();


  /** 
   * <em>account_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDateIsSet();


  /** 
   * <em>account_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDateIsModified();


  /** 
   * <em>initial_password</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInitialPassword();


  /** 
   * <em>initial_password</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInitialPasswordIsSet();


  /** 
   * <em>initial_password</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInitialPasswordIsModified();


  /** 
   * <em>family_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyName();


  /** 
   * <em>family_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameIsSet();


  /** 
   * <em>family_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameIsModified();


  /** 
   * <em>given_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGivenName();


  /** 
   * <em>given_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGivenNameIsSet();


  /** 
   * <em>given_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGivenNameIsModified();


  /** 
   * <em>family_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyNameAlt1();


  /** 
   * <em>family_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameAlt1IsSet();


  /** 
   * <em>family_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameAlt1IsModified();


  /** 
   * <em>given_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGivenNameAlt1();


  /** 
   * <em>given_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGivenNameAlt1IsSet();


  /** 
   * <em>given_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGivenNameAlt1IsModified();


  /** 
   * <em>sex</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSex();


  /** 
   * <em>sex</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSexIsSet();


  /** 
   * <em>sex</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSexIsModified();


  /** 
   * <em>era_born</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEraBorn();


  /** 
   * <em>era_born</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEraBornIsSet();


  /** 
   * <em>era_born</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEraBornIsModified();


  /** 
   * <em>born_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBornDate();


  /** 
   * <em>born_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBornDateIsSet();


  /** 
   * <em>born_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBornDateIsModified();


  /** 
   * <em>email_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEmailAddress();


  /** 
   * <em>email_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressIsSet();


  /** 
   * <em>email_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressIsModified();


  /** 
   * <em>email_address_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEmailAddressAlt1();


  /** 
   * <em>email_address_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressAlt1IsSet();


  /** 
   * <em>email_address_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressAlt1IsModified();


  /** 
   * <em>zip_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getZipCode();


  /** 
   * <em>zip_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getZipCodeIsSet();


  /** 
   * <em>zip_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getZipCodeIsModified();


  /** 
   * <em>address_line1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine1();


  /** 
   * <em>address_line1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1IsSet();


  /** 
   * <em>address_line1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1IsModified();


  /** 
   * <em>address_line2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine2();


  /** 
   * <em>address_line2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2IsSet();


  /** 
   * <em>address_line2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2IsModified();


  /** 
   * <em>address_line3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine3();


  /** 
   * <em>address_line3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3IsSet();


  /** 
   * <em>address_line3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3IsModified();


  /** 
   * <em>address_line1_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine1Kana();


  /** 
   * <em>address_line1_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1KanaIsSet();


  /** 
   * <em>address_line1_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine1KanaIsModified();


  /** 
   * <em>address_line2_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine2Kana();


  /** 
   * <em>address_line2_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2KanaIsSet();


  /** 
   * <em>address_line2_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine2KanaIsModified();


  /** 
   * <em>address_line3_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAddressLine3Kana();


  /** 
   * <em>address_line3_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3KanaIsSet();


  /** 
   * <em>address_line3_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddressLine3KanaIsModified();


  /** 
   * <em>telephone</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTelephone();


  /** 
   * <em>telephone</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTelephoneIsSet();


  /** 
   * <em>telephone</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTelephoneIsModified();


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
   * <em>office_fax</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOfficeFax();


  /** 
   * <em>office_fax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeFaxIsSet();


  /** 
   * <em>office_fax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfficeFaxIsModified();


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
   * <em>contact_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContactAddress();


  /** 
   * <em>contact_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactAddressIsSet();


  /** 
   * <em>contact_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactAddressIsModified();


  /** 
   * <em>contact_telephone</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContactTelephone();


  /** 
   * <em>contact_telephone</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactTelephoneIsSet();


  /** 
   * <em>contact_telephone</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContactTelephoneIsModified();


  /** 
   * <em>family_relationship</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyRelationship();


  /** 
   * <em>family_relationship</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyRelationshipIsSet();


  /** 
   * <em>family_relationship</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyRelationshipIsModified();


  /** 
   * <em>family_relationship_etc</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyRelationshipEtc();


  /** 
   * <em>family_relationship_etc</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyRelationshipEtcIsSet();


  /** 
   * <em>family_relationship_etc</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyRelationshipEtcIsModified();


  /** 
   * <em>householder</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholder();


  /** 
   * <em>householder</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderIsSet();


  /** 
   * <em>householder</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderIsModified();


  /** 
   * <em>householder_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderKana();


  /** 
   * <em>householder_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderKanaIsSet();


  /** 
   * <em>householder_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderKanaIsModified();


  /** 
   * <em>householder_occupation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderOccupationDiv();


  /** 
   * <em>householder_occupation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOccupationDivIsSet();


  /** 
   * <em>householder_occupation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOccupationDivIsModified();


  /** 
   * <em>householder_office</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderOffice();


  /** 
   * <em>householder_office</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeIsSet();


  /** 
   * <em>householder_office</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeIsModified();


  /** 
   * <em>householder_office_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderOfficeAddress();


  /** 
   * <em>householder_office_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeAddressIsSet();


  /** 
   * <em>householder_office_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeAddressIsModified();


  /** 
   * <em>householder_department</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderDepartment();


  /** 
   * <em>householder_department</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderDepartmentIsSet();


  /** 
   * <em>householder_department</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderDepartmentIsModified();


  /** 
   * <em>householder_office_tel</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderOfficeTel();


  /** 
   * <em>householder_office_tel</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeTelIsSet();


  /** 
   * <em>householder_office_tel</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeTelIsModified();


  /** 
   * <em>householder_office_fax</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderOfficeFax();


  /** 
   * <em>householder_office_fax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeFaxIsSet();


  /** 
   * <em>householder_office_fax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderOfficeFaxIsModified();


  /** 
   * <em>householder_post</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHouseholderPost();


  /** 
   * <em>householder_post</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderPostIsSet();


  /** 
   * <em>householder_post</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHouseholderPostIsModified();


  /** 
   * <em>resident</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getResident();


  /** 
   * <em>resident</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getResidentIsSet();


  /** 
   * <em>resident</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getResidentIsModified();


  /** 
   * <em>transfer_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTransferDiv();


  /** 
   * <em>transfer_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferDivIsSet();


  /** 
   * <em>transfer_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferDivIsModified();


  /** 
   * <em>fin_institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinInstitutionCode();


  /** 
   * <em>fin_institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinInstitutionCodeIsSet();


  /** 
   * <em>fin_institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinInstitutionCodeIsModified();


  /** 
   * <em>fin_institution_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinInstitutionName();


  /** 
   * <em>fin_institution_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinInstitutionNameIsSet();


  /** 
   * <em>fin_institution_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinInstitutionNameIsModified();


  /** 
   * <em>fin_branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinBranchCode();


  /** 
   * <em>fin_branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinBranchCodeIsSet();


  /** 
   * <em>fin_branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinBranchCodeIsModified();


  /** 
   * <em>fin_branch_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinBranchName();


  /** 
   * <em>fin_branch_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinBranchNameIsSet();


  /** 
   * <em>fin_branch_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinBranchNameIsModified();


  /** 
   * <em>fin_save_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinSaveDiv();


  /** 
   * <em>fin_save_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinSaveDivIsSet();


  /** 
   * <em>fin_save_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinSaveDivIsModified();


  /** 
   * <em>fin_account_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinAccountNo();


  /** 
   * <em>fin_account_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinAccountNoIsSet();


  /** 
   * <em>fin_account_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinAccountNoIsModified();


  /** 
   * <em>postal_save_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPostalSaveCode();


  /** 
   * <em>postal_save_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPostalSaveCodeIsSet();


  /** 
   * <em>postal_save_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPostalSaveCodeIsModified();


  /** 
   * <em>postal_save_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPostalSaveNo();


  /** 
   * <em>postal_save_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPostalSaveNoIsSet();


  /** 
   * <em>postal_save_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPostalSaveNoIsModified();


  /** 
   * <em>fin_account_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinAccountName();


  /** 
   * <em>fin_account_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinAccountNameIsSet();


  /** 
   * <em>fin_account_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinAccountNameIsModified();


  /** 
   * <em>trans_commission</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTransCommission();


  /** 
   * <em>trans_commission</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransCommissionIsSet();


  /** 
   * <em>trans_commission</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransCommissionIsModified();


  /** 
   * <em>experience_div_equity</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivEquity();


  /** 
   * <em>experience_div_equity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivEquityIsSet();


  /** 
   * <em>experience_div_equity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivEquityIsModified();


  /** 
   * <em>experience_div_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivMargin();


  /** 
   * <em>experience_div_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivMarginIsSet();


  /** 
   * <em>experience_div_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivMarginIsModified();


  /** 
   * <em>experience_div_bond</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivBond();


  /** 
   * <em>experience_div_bond</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivBondIsSet();


  /** 
   * <em>experience_div_bond</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivBondIsModified();


  /** 
   * <em>experience_div_wt</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivWt();


  /** 
   * <em>experience_div_wt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivWtIsSet();


  /** 
   * <em>experience_div_wt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivWtIsModified();


  /** 
   * <em>experience_div_fund_sk</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivFundSk();


  /** 
   * <em>experience_div_fund_sk</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFundSkIsSet();


  /** 
   * <em>experience_div_fund_sk</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFundSkIsModified();


  /** 
   * <em>experience_div_fund_bd</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivFundBd();


  /** 
   * <em>experience_div_fund_bd</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFundBdIsSet();


  /** 
   * <em>experience_div_fund_bd</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFundBdIsModified();


  /** 
   * <em>experience_div_fo</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivFo();


  /** 
   * <em>experience_div_fo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFoIsSet();


  /** 
   * <em>experience_div_fo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFoIsModified();


  /** 
   * <em>experience_div_f_equity</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivFEquity();


  /** 
   * <em>experience_div_f_equity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFEquityIsSet();


  /** 
   * <em>experience_div_f_equity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivFEquityIsModified();


  /** 
   * <em>experience_div_etc</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceDivEtc();


  /** 
   * <em>experience_div_etc</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivEtcIsSet();


  /** 
   * <em>experience_div_etc</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceDivEtcIsModified();


  /** 
   * <em>experience_flag_equity</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagEquity();


  /** 
   * <em>experience_flag_equity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagEquityIsSet();


  /** 
   * <em>experience_flag_equity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagEquityIsModified();


  /** 
   * <em>experience_flag_margin</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagMargin();


  /** 
   * <em>experience_flag_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagMarginIsSet();


  /** 
   * <em>experience_flag_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagMarginIsModified();


  /** 
   * <em>experience_flag_bond</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagBond();


  /** 
   * <em>experience_flag_bond</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagBondIsSet();


  /** 
   * <em>experience_flag_bond</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagBondIsModified();


  /** 
   * <em>experience_flag_wt</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagWt();


  /** 
   * <em>experience_flag_wt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagWtIsSet();


  /** 
   * <em>experience_flag_wt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagWtIsModified();


  /** 
   * <em>experience_flag_fund_sk</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFundSk();


  /** 
   * <em>experience_flag_fund_sk</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFundSkIsSet();


  /** 
   * <em>experience_flag_fund_sk</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFundSkIsModified();


  /** 
   * <em>experience_flag_fund_bd</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFundBd();


  /** 
   * <em>experience_flag_fund_bd</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFundBdIsSet();


  /** 
   * <em>experience_flag_fund_bd</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFundBdIsModified();


  /** 
   * <em>experience_flag_fo</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFo();


  /** 
   * <em>experience_flag_fo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFoIsSet();


  /** 
   * <em>experience_flag_fo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFoIsModified();


  /** 
   * <em>experience_flag_f_equity</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFEquity();


  /** 
   * <em>experience_flag_f_equity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFEquityIsSet();


  /** 
   * <em>experience_flag_f_equity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagFEquityIsModified();


  /** 
   * <em>experience_flag_etc</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagEtc();


  /** 
   * <em>experience_flag_etc</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagEtcIsSet();


  /** 
   * <em>experience_flag_etc</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFlagEtcIsModified();


  /** 
   * <em>experience_from</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceFrom();


  /** 
   * <em>experience_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFromIsSet();


  /** 
   * <em>experience_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceFromIsModified();


  /** 
   * <em>experience_to</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExperienceTo();


  /** 
   * <em>experience_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceToIsSet();


  /** 
   * <em>experience_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExperienceToIsModified();


  /** 
   * <em>interest_flag_equity</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagEquity();


  /** 
   * <em>interest_flag_equity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagEquityIsSet();


  /** 
   * <em>interest_flag_equity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagEquityIsModified();


  /** 
   * <em>interest_flag_ministock</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagMinistock();


  /** 
   * <em>interest_flag_ministock</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagMinistockIsSet();


  /** 
   * <em>interest_flag_ministock</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagMinistockIsModified();


  /** 
   * <em>interest_flag_margin</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagMargin();


  /** 
   * <em>interest_flag_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagMarginIsSet();


  /** 
   * <em>interest_flag_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagMarginIsModified();


  /** 
   * <em>interest_flag_bond</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagBond();


  /** 
   * <em>interest_flag_bond</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagBondIsSet();


  /** 
   * <em>interest_flag_bond</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagBondIsModified();


  /** 
   * <em>interest_flag_fund</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFund();


  /** 
   * <em>interest_flag_fund</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagFundIsSet();


  /** 
   * <em>interest_flag_fund</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagFundIsModified();


  /** 
   * <em>interest_flag_fo</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFo();


  /** 
   * <em>interest_flag_fo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagFoIsSet();


  /** 
   * <em>interest_flag_fo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagFoIsModified();


  /** 
   * <em>interest_flag_f_equity</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFEquity();


  /** 
   * <em>interest_flag_f_equity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagFEquityIsSet();


  /** 
   * <em>interest_flag_f_equity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagFEquityIsModified();


  /** 
   * <em>interest_flag_etc</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagEtc();


  /** 
   * <em>interest_flag_etc</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagEtcIsSet();


  /** 
   * <em>interest_flag_etc</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInterestFlagEtcIsModified();


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
   * <em>annual_income_from</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAnnualIncomeFrom();


  /** 
   * <em>annual_income_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualIncomeFromIsSet();


  /** 
   * <em>annual_income_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualIncomeFromIsModified();


  /** 
   * <em>annual_income_to</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAnnualIncomeTo();


  /** 
   * <em>annual_income_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualIncomeToIsSet();


  /** 
   * <em>annual_income_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualIncomeToIsModified();


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
   * <em>asset_value_from</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAssetValueFrom();


  /** 
   * <em>asset_value_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetValueFromIsSet();


  /** 
   * <em>asset_value_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetValueFromIsModified();


  /** 
   * <em>asset_value_to</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAssetValueTo();


  /** 
   * <em>asset_value_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetValueToIsSet();


  /** 
   * <em>asset_value_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetValueToIsModified();


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
   * <em>fund_budget_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFundBudgetDiv();


  /** 
   * <em>fund_budget_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundBudgetDivIsSet();


  /** 
   * <em>fund_budget_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundBudgetDivIsModified();


  /** 
   * <em>fund_budget_etc</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFundBudgetEtc();


  /** 
   * <em>fund_budget_etc</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundBudgetEtcIsSet();


  /** 
   * <em>fund_budget_etc</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundBudgetEtcIsModified();


  /** 
   * <em>id_confirm_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIdConfirmFlag();


  /** 
   * <em>id_confirm_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIdConfirmFlagIsSet();


  /** 
   * <em>id_confirm_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIdConfirmFlagIsModified();


  /** 
   * <em>id_confirm_doc_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIdConfirmDocDiv();


  /** 
   * <em>id_confirm_doc_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIdConfirmDocDivIsSet();


  /** 
   * <em>id_confirm_doc_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIdConfirmDocDivIsModified();


  /** 
   * <em>id_confirm_doc_etc</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIdConfirmDocEtc();


  /** 
   * <em>id_confirm_doc_etc</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIdConfirmDocEtcIsSet();


  /** 
   * <em>id_confirm_doc_etc</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIdConfirmDocEtcIsModified();


  /** 
   * <em>special_acc</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSpecialAcc();


  /** 
   * <em>special_acc</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpecialAccIsSet();


  /** 
   * <em>special_acc</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpecialAccIsModified();


  /** 
   * <em>special_acc_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSpecialAccMargin();


  /** 
   * <em>special_acc_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpecialAccMarginIsSet();


  /** 
   * <em>special_acc_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpecialAccMarginIsModified();


  /** 
   * <em>insider_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInsiderFlag();


  /** 
   * <em>insider_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderFlagIsSet();


  /** 
   * <em>insider_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderFlagIsModified();


  /** 
   * <em>product_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductName();


  /** 
   * <em>product_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameIsSet();


  /** 
   * <em>product_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameIsModified();


  /** 
   * <em>send_zip_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSendZipCode();


  /** 
   * <em>send_zip_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendZipCodeIsSet();


  /** 
   * <em>send_zip_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendZipCodeIsModified();


  /** 
   * <em>send_address_line1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSendAddressLine1();


  /** 
   * <em>send_address_line1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendAddressLine1IsSet();


  /** 
   * <em>send_address_line1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendAddressLine1IsModified();


  /** 
   * <em>send_address_line2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSendAddressLine2();


  /** 
   * <em>send_address_line2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendAddressLine2IsSet();


  /** 
   * <em>send_address_line2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendAddressLine2IsModified();


  /** 
   * <em>send_address_line3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSendAddressLine3();


  /** 
   * <em>send_address_line3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendAddressLine3IsSet();


  /** 
   * <em>send_address_line3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendAddressLine3IsModified();


  /** 
   * <em>ext_item_div1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv1();


  /** 
   * <em>ext_item_div1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv1IsSet();


  /** 
   * <em>ext_item_div1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv1IsModified();


  /** 
   * <em>ext_item_div2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv2();


  /** 
   * <em>ext_item_div2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv2IsSet();


  /** 
   * <em>ext_item_div2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv2IsModified();


  /** 
   * <em>ext_item_div3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv3();


  /** 
   * <em>ext_item_div3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv3IsSet();


  /** 
   * <em>ext_item_div3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv3IsModified();


  /** 
   * <em>ext_item_div4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv4();


  /** 
   * <em>ext_item_div4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv4IsSet();


  /** 
   * <em>ext_item_div4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv4IsModified();


  /** 
   * <em>ext_item_div5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv5();


  /** 
   * <em>ext_item_div5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv5IsSet();


  /** 
   * <em>ext_item_div5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv5IsModified();


  /** 
   * <em>ext_item_div6</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv6();


  /** 
   * <em>ext_item_div6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv6IsSet();


  /** 
   * <em>ext_item_div6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv6IsModified();


  /** 
   * <em>ext_item_div7</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv7();


  /** 
   * <em>ext_item_div7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv7IsSet();


  /** 
   * <em>ext_item_div7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv7IsModified();


  /** 
   * <em>ext_item_div8</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv8();


  /** 
   * <em>ext_item_div8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv8IsSet();


  /** 
   * <em>ext_item_div8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv8IsModified();


  /** 
   * <em>ext_item_div9</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv9();


  /** 
   * <em>ext_item_div9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv9IsSet();


  /** 
   * <em>ext_item_div9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv9IsModified();


  /** 
   * <em>ext_item_div10</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv10();


  /** 
   * <em>ext_item_div10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv10IsSet();


  /** 
   * <em>ext_item_div10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv10IsModified();


  /** 
   * <em>ext_item_flag1</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag1();


  /** 
   * <em>ext_item_flag1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag1IsSet();


  /** 
   * <em>ext_item_flag1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag1IsModified();


  /** 
   * <em>ext_item_flag2</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag2();


  /** 
   * <em>ext_item_flag2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag2IsSet();


  /** 
   * <em>ext_item_flag2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag2IsModified();


  /** 
   * <em>ext_item_flag3</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag3();


  /** 
   * <em>ext_item_flag3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag3IsSet();


  /** 
   * <em>ext_item_flag3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag3IsModified();


  /** 
   * <em>ext_item_flag4</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag4();


  /** 
   * <em>ext_item_flag4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag4IsSet();


  /** 
   * <em>ext_item_flag4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag4IsModified();


  /** 
   * <em>ext_item_flag5</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag5();


  /** 
   * <em>ext_item_flag5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag5IsSet();


  /** 
   * <em>ext_item_flag5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag5IsModified();


  /** 
   * <em>ext_item_flag6</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag6();


  /** 
   * <em>ext_item_flag6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag6IsSet();


  /** 
   * <em>ext_item_flag6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag6IsModified();


  /** 
   * <em>ext_item_flag7</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag7();


  /** 
   * <em>ext_item_flag7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag7IsSet();


  /** 
   * <em>ext_item_flag7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag7IsModified();


  /** 
   * <em>ext_item_flag8</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag8();


  /** 
   * <em>ext_item_flag8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag8IsSet();


  /** 
   * <em>ext_item_flag8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag8IsModified();


  /** 
   * <em>ext_item_flag9</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag9();


  /** 
   * <em>ext_item_flag9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag9IsSet();


  /** 
   * <em>ext_item_flag9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag9IsModified();


  /** 
   * <em>ext_item_flag10</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag10();


  /** 
   * <em>ext_item_flag10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag10IsSet();


  /** 
   * <em>ext_item_flag10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemFlag10IsModified();


  /** 
   * <em>ext_item_text1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText1();


  /** 
   * <em>ext_item_text1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText1IsSet();


  /** 
   * <em>ext_item_text1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText1IsModified();


  /** 
   * <em>ext_item_text2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText2();


  /** 
   * <em>ext_item_text2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText2IsSet();


  /** 
   * <em>ext_item_text2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText2IsModified();


  /** 
   * <em>ext_item_text3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText3();


  /** 
   * <em>ext_item_text3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText3IsSet();


  /** 
   * <em>ext_item_text3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText3IsModified();


  /** 
   * <em>ext_item_text4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText4();


  /** 
   * <em>ext_item_text4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText4IsSet();


  /** 
   * <em>ext_item_text4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText4IsModified();


  /** 
   * <em>ext_item_text5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText5();


  /** 
   * <em>ext_item_text5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText5IsSet();


  /** 
   * <em>ext_item_text5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText5IsModified();


  /** 
   * <em>ext_item_text6</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText6();


  /** 
   * <em>ext_item_text6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText6IsSet();


  /** 
   * <em>ext_item_text6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText6IsModified();


  /** 
   * <em>ext_item_text7</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText7();


  /** 
   * <em>ext_item_text7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText7IsSet();


  /** 
   * <em>ext_item_text7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText7IsModified();


  /** 
   * <em>ext_item_text8</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText8();


  /** 
   * <em>ext_item_text8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText8IsSet();


  /** 
   * <em>ext_item_text8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText8IsModified();


  /** 
   * <em>ext_item_text9</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText9();


  /** 
   * <em>ext_item_text9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText9IsSet();


  /** 
   * <em>ext_item_text9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText9IsModified();


  /** 
   * <em>ext_item_text10</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemText10();


  /** 
   * <em>ext_item_text10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText10IsSet();


  /** 
   * <em>ext_item_text10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemText10IsModified();


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
   * <em>creator</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCreator();


  /** 
   * <em>creator</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatorIsSet();


  /** 
   * <em>creator</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatorIsModified();


  /** 
   * <em>exclusive_use_account_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExclusiveUseAccountNo();


  /** 
   * <em>exclusive_use_account_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExclusiveUseAccountNoIsSet();


  /** 
   * <em>exclusive_use_account_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExclusiveUseAccountNoIsModified();


  /** 
   * <em>send_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSendTimestamp();


  /** 
   * <em>send_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendTimestampIsSet();


  /** 
   * <em>send_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendTimestampIsModified();


  /** 
   * <em>real_name_voucher_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRealNameVoucherDiv();


  /** 
   * <em>real_name_voucher_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRealNameVoucherDivIsSet();


  /** 
   * <em>real_name_voucher_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRealNameVoucherDivIsModified();


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
   * <em>insider_voucher_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsiderVoucherDiv();


  /** 
   * <em>insider_voucher_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderVoucherDivIsSet();


  /** 
   * <em>insider_voucher_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderVoucherDivIsModified();


  /** 
   * <em>insider_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsiderProductCode();


  /** 
   * <em>insider_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderProductCodeIsSet();


  /** 
   * <em>insider_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderProductCodeIsModified();


  /** 
   * <em>insider_relation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsiderRelationDiv();


  /** 
   * <em>insider_relation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderRelationDivIsSet();


  /** 
   * <em>insider_relation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderRelationDivIsModified();


  /** 
   * <em>insider_officer_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsiderOfficerName();


  /** 
   * <em>insider_officer_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderOfficerNameIsSet();


  /** 
   * <em>insider_officer_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderOfficerNameIsModified();


  /** 
   * <em>insider_post_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsiderPostCode();


  /** 
   * <em>insider_post_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderPostCodeIsSet();


  /** 
   * <em>insider_post_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderPostCodeIsModified();


  /** 
   * <em>insider_post_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsiderPostName();


  /** 
   * <em>insider_post_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderPostNameIsSet();


  /** 
   * <em>insider_post_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderPostNameIsModified();


  /** 
   * <em>gp_voucher_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpVoucherDiv();


  /** 
   * <em>gp_voucher_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpVoucherDivIsSet();


  /** 
   * <em>gp_voucher_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpVoucherDivIsModified();


  /** 
   * <em>gp_course</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpCourse();


  /** 
   * <em>gp_course</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpCourseIsSet();


  /** 
   * <em>gp_course</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpCourseIsModified();


  /** 
   * <em>gp_plan</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpPlan();


  /** 
   * <em>gp_plan</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpPlanIsSet();


  /** 
   * <em>gp_plan</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpPlanIsModified();


  /** 
   * <em>gp_target_figure</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpTargetFigure();


  /** 
   * <em>gp_target_figure</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTargetFigureIsSet();


  /** 
   * <em>gp_target_figure</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTargetFigureIsModified();


  /** 
   * <em>gp_target_year</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpTargetYear();


  /** 
   * <em>gp_target_year</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTargetYearIsSet();


  /** 
   * <em>gp_target_year</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTargetYearIsModified();


  /** 
   * <em>gp_target_month</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpTargetMonth();


  /** 
   * <em>gp_target_month</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTargetMonthIsSet();


  /** 
   * <em>gp_target_month</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTargetMonthIsModified();


  /** 
   * <em>gp_installment_figure</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpInstallmentFigure();


  /** 
   * <em>gp_installment_figure</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpInstallmentFigureIsSet();


  /** 
   * <em>gp_installment_figure</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpInstallmentFigureIsModified();


  /** 
   * <em>gp_deposit_cycle</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpDepositCycle();


  /** 
   * <em>gp_deposit_cycle</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpDepositCycleIsSet();


  /** 
   * <em>gp_deposit_cycle</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpDepositCycleIsModified();


  /** 
   * <em>gp_payment_root</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpPaymentRoot();


  /** 
   * <em>gp_payment_root</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpPaymentRootIsSet();


  /** 
   * <em>gp_payment_root</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpPaymentRootIsModified();


  /** 
   * <em>gp_reinvest_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpReinvestDiv();


  /** 
   * <em>gp_reinvest_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpReinvestDivIsSet();


  /** 
   * <em>gp_reinvest_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpReinvestDivIsModified();


  /** 
   * <em>gp_tax_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpTaxDiv();


  /** 
   * <em>gp_tax_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTaxDivIsSet();


  /** 
   * <em>gp_tax_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTaxDivIsModified();


  /** 
   * <em>gp_taxfree_limit</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpTaxfreeLimit();


  /** 
   * <em>gp_taxfree_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTaxfreeLimitIsSet();


  /** 
   * <em>gp_taxfree_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpTaxfreeLimitIsModified();


  /** 
   * <em>gp_special_taxfree_limit</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpSpecialTaxfreeLimit();


  /** 
   * <em>gp_special_taxfree_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpSpecialTaxfreeLimitIsSet();


  /** 
   * <em>gp_special_taxfree_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpSpecialTaxfreeLimitIsModified();


  /** 
   * <em>gp_subscr_summary</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpSubscrSummary();


  /** 
   * <em>gp_subscr_summary</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpSubscrSummaryIsSet();


  /** 
   * <em>gp_subscr_summary</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpSubscrSummaryIsModified();


  /** 
   * <em>gp_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpProductCode();


  /** 
   * <em>gp_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpProductCodeIsSet();


  /** 
   * <em>gp_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpProductCodeIsModified();


  /** 
   * <em>gp_mortgage_customer</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpMortgageCustomer();


  /** 
   * <em>gp_mortgage_customer</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpMortgageCustomerIsSet();


  /** 
   * <em>gp_mortgage_customer</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpMortgageCustomerIsModified();


  /** 
   * <em>gp_mix_customer</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpMixCustomer();


  /** 
   * <em>gp_mix_customer</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpMixCustomerIsSet();


  /** 
   * <em>gp_mix_customer</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpMixCustomerIsModified();


  /** 
   * <em>gp_contract</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGpContract();


  /** 
   * <em>gp_contract</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpContractIsSet();


  /** 
   * <em>gp_contract</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGpContractIsModified();


  /** 
   * <em>stk_voucher_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkVoucherDiv();


  /** 
   * <em>stk_voucher_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkVoucherDivIsSet();


  /** 
   * <em>stk_voucher_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkVoucherDivIsModified();


  /** 
   * <em>stk_taxation_tran_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkTaxationTranDiv();


  /** 
   * <em>stk_taxation_tran_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkTaxationTranDivIsSet();


  /** 
   * <em>stk_taxation_tran_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkTaxationTranDivIsModified();


  /** 
   * <em>stk_address_line_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkAddressLineKana();


  /** 
   * <em>stk_address_line_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkAddressLineKanaIsSet();


  /** 
   * <em>stk_address_line_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkAddressLineKanaIsModified();


  /** 
   * <em>stk_transfer_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkTransferDiv();


  /** 
   * <em>stk_transfer_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkTransferDivIsSet();


  /** 
   * <em>stk_transfer_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkTransferDivIsModified();


  /** 
   * <em>stk_fin_institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkFinInstitutionCode();


  /** 
   * <em>stk_fin_institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinInstitutionCodeIsSet();


  /** 
   * <em>stk_fin_institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinInstitutionCodeIsModified();


  /** 
   * <em>stk_fin_branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkFinBranchCode();


  /** 
   * <em>stk_fin_branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinBranchCodeIsSet();


  /** 
   * <em>stk_fin_branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinBranchCodeIsModified();


  /** 
   * <em>stk_fin_save_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkFinSaveDiv();


  /** 
   * <em>stk_fin_save_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinSaveDivIsSet();


  /** 
   * <em>stk_fin_save_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinSaveDivIsModified();


  /** 
   * <em>stk_fin_account_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStkFinAccountNo();


  /** 
   * <em>stk_fin_account_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinAccountNoIsSet();


  /** 
   * <em>stk_fin_account_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStkFinAccountNoIsModified();


  /** 
   * <em>brokerage_trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBrokerageTraderCode();


  /** 
   * <em>brokerage_trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBrokerageTraderCodeIsSet();


  /** 
   * <em>brokerage_trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBrokerageTraderCodeIsModified();


  /** 
   * <em>ext_item_div11</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv11();


  /** 
   * <em>ext_item_div11</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv11IsSet();


  /** 
   * <em>ext_item_div11</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv11IsModified();


  /** 
   * <em>ext_item_div12</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv12();


  /** 
   * <em>ext_item_div12</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv12IsSet();


  /** 
   * <em>ext_item_div12</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv12IsModified();


  /** 
   * <em>ext_item_div13</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv13();


  /** 
   * <em>ext_item_div13</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv13IsSet();


  /** 
   * <em>ext_item_div13</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv13IsModified();


  /** 
   * <em>ext_item_div14</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv14();


  /** 
   * <em>ext_item_div14</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv14IsSet();


  /** 
   * <em>ext_item_div14</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv14IsModified();


  /** 
   * <em>ext_item_div15</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExtItemDiv15();


  /** 
   * <em>ext_item_div15</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv15IsSet();


  /** 
   * <em>ext_item_div15</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExtItemDiv15IsModified();


  /** 
   * <em>foreign_account_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignAccountNo();


  /** 
   * <em>foreign_account_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignAccountNoIsSet();


  /** 
   * <em>foreign_account_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignAccountNoIsModified();


  /** 
   * <em>foreign_account_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignAccountName();


  /** 
   * <em>foreign_account_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignAccountNameIsSet();


  /** 
   * <em>foreign_account_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignAccountNameIsModified();


  /** 
   * <em>foreign_account_name_eng</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignAccountNameEng();


  /** 
   * <em>foreign_account_name_eng</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignAccountNameEngIsSet();


  /** 
   * <em>foreign_account_name_eng</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignAccountNameEngIsModified();


  /** 
   * <em>foreign_save_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignSaveDiv();


  /** 
   * <em>foreign_save_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignSaveDivIsSet();


  /** 
   * <em>foreign_save_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignSaveDivIsModified();


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
   * <em>delete_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDeleteTimestamp();


  /** 
   * <em>delete_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeleteTimestampIsSet();


  /** 
   * <em>delete_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeleteTimestampIsModified();


  /** 
   * <em>print_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPrintFlag();


  /** 
   * <em>print_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPrintFlagIsSet();


  /** 
   * <em>print_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPrintFlagIsModified();


  /** 
   * <em>receipt_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getReceiptFlag();


  /** 
   * <em>receipt_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptFlagIsSet();


  /** 
   * <em>receipt_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReceiptFlagIsModified();


  /** 
   * <em>agreement_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getAgreementFlag();


  /** 
   * <em>agreement_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgreementFlagIsSet();


  /** 
   * <em>agreement_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgreementFlagIsModified();


  /** 
   * <em>foreign_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getForeignFlag();


  /** 
   * <em>foreign_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignFlagIsSet();


  /** 
   * <em>foreign_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignFlagIsModified();


  /** 
   * <em>agency_acc_name_kana1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyAccNameKana1();


  /** 
   * <em>agency_acc_name_kana1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccNameKana1IsSet();


  /** 
   * <em>agency_acc_name_kana1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccNameKana1IsModified();


  /** 
   * <em>agency_acc_name_kana2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyAccNameKana2();


  /** 
   * <em>agency_acc_name_kana2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccNameKana2IsSet();


  /** 
   * <em>agency_acc_name_kana2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccNameKana2IsModified();


  /** 
   * <em>agency_acc_name1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyAccName1();


  /** 
   * <em>agency_acc_name1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccName1IsSet();


  /** 
   * <em>agency_acc_name1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccName1IsModified();


  /** 
   * <em>agency_acc_name2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyAccName2();


  /** 
   * <em>agency_acc_name2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccName2IsSet();


  /** 
   * <em>agency_acc_name2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAccName2IsModified();


  /** 
   * <em>agency_address_line1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyAddressLine1();


  /** 
   * <em>agency_address_line1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAddressLine1IsSet();


  /** 
   * <em>agency_address_line1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAddressLine1IsModified();


  /** 
   * <em>agency_address_line2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyAddressLine2();


  /** 
   * <em>agency_address_line2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAddressLine2IsSet();


  /** 
   * <em>agency_address_line2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyAddressLine2IsModified();


  /** 
   * <em>agency_rep_post</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyRepPost();


  /** 
   * <em>agency_rep_post</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepPostIsSet();


  /** 
   * <em>agency_rep_post</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepPostIsModified();


  /** 
   * <em>agency_rep_name_kana1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyRepNameKana1();


  /** 
   * <em>agency_rep_name_kana1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepNameKana1IsSet();


  /** 
   * <em>agency_rep_name_kana1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepNameKana1IsModified();


  /** 
   * <em>agency_rep_name_kana2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyRepNameKana2();


  /** 
   * <em>agency_rep_name_kana2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepNameKana2IsSet();


  /** 
   * <em>agency_rep_name_kana2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepNameKana2IsModified();


  /** 
   * <em>agency_rep_name1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyRepName1();


  /** 
   * <em>agency_rep_name1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepName1IsSet();


  /** 
   * <em>agency_rep_name1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepName1IsModified();


  /** 
   * <em>agency_rep_name2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyRepName2();


  /** 
   * <em>agency_rep_name2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepName2IsSet();


  /** 
   * <em>agency_rep_name2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyRepName2IsModified();


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStatus();


  /** 
   * <em>status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsModified();


  /** 
   * <em>error_info</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getErrorInfo();


  /** 
   * <em>error_info</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getErrorInfoIsSet();


  /** 
   * <em>error_info</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getErrorInfoIsModified();


}
@
