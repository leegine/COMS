head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.11.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostAccRegVoucherRow.java;


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
 * HostAccRegVoucherRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>host_acc_reg_voucher</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link HostAccRegVoucherRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostAccRegVoucherPK 
 */
public interface HostAccRegVoucherRow extends Row {


  /** 
   * ����{@@link HostAccRegVoucherRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "host_acc_reg_voucher", "session" );


  /** 
   * <em>order_request_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderRequestNumberIsModified();


  /** 
   * <em>request_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRequestCode();


  /** 
   * <em>request_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRequestCodeIsSet();


  /** 
   * <em>request_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRequestCodeIsModified();


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
   * <em>trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTraderCode();


  /** 
   * <em>trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTraderCodeIsSet();


  /** 
   * <em>trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTraderCodeIsModified();


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
   * <em>serial_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSerialNo();


  /** 
   * <em>serial_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSerialNoIsSet();


  /** 
   * <em>serial_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSerialNoIsModified();


  /** 
   * <em>regist_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRegistDiv();


  /** 
   * <em>regist_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegistDivIsSet();


  /** 
   * <em>regist_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegistDivIsModified();


  /** 
   * <em>account_name_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountNameKana();


  /** 
   * <em>account_name_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountNameKanaIsSet();


  /** 
   * <em>account_name_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountNameKanaIsModified();


  /** 
   * <em>account_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountName();


  /** 
   * <em>account_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountNameIsSet();


  /** 
   * <em>account_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountNameIsModified();


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
   * <em>document</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDocument();


  /** 
   * <em>document</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDocumentIsSet();


  /** 
   * <em>document</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDocumentIsModified();


  /** 
   * <em>unknown_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getUnknownAddress();


  /** 
   * <em>unknown_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnknownAddressIsSet();


  /** 
   * <em>unknown_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnknownAddressIsModified();


  /** 
   * <em>report</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReport();


  /** 
   * <em>report</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReportIsSet();


  /** 
   * <em>report</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReportIsModified();


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
   * <em>taxation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxationDiv();


  /** 
   * <em>taxation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationDivIsSet();


  /** 
   * <em>taxation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationDivIsModified();


  /** 
   * <em>forign_taxation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForignTaxationDiv();


  /** 
   * <em>forign_taxation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForignTaxationDivIsSet();


  /** 
   * <em>forign_taxation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForignTaxationDivIsModified();


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
   * <em>account_open_div1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv1();


  /** 
   * <em>account_open_div1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv1IsSet();


  /** 
   * <em>account_open_div1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv1IsModified();


  /** 
   * <em>account_open_div2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv2();


  /** 
   * <em>account_open_div2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv2IsSet();


  /** 
   * <em>account_open_div2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv2IsModified();


  /** 
   * <em>account_open_div3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv3();


  /** 
   * <em>account_open_div3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv3IsSet();


  /** 
   * <em>account_open_div3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv3IsModified();


  /** 
   * <em>account_open_div4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv4();


  /** 
   * <em>account_open_div4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv4IsSet();


  /** 
   * <em>account_open_div4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv4IsModified();


  /** 
   * <em>account_open_div5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv5();


  /** 
   * <em>account_open_div5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv5IsSet();


  /** 
   * <em>account_open_div5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv5IsModified();


  /** 
   * <em>account_open_div6</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv6();


  /** 
   * <em>account_open_div6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv6IsSet();


  /** 
   * <em>account_open_div6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv6IsModified();


  /** 
   * <em>account_open_div7</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv7();


  /** 
   * <em>account_open_div7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv7IsSet();


  /** 
   * <em>account_open_div7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv7IsModified();


  /** 
   * <em>account_open_div8</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv8();


  /** 
   * <em>account_open_div8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv8IsSet();


  /** 
   * <em>account_open_div8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv8IsModified();


  /** 
   * <em>account_open_div9</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv9();


  /** 
   * <em>account_open_div9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv9IsSet();


  /** 
   * <em>account_open_div9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv9IsModified();


  /** 
   * <em>account_open_div10</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv10();


  /** 
   * <em>account_open_div10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv10IsSet();


  /** 
   * <em>account_open_div10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv10IsModified();


  /** 
   * <em>account_open_div11</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv11();


  /** 
   * <em>account_open_div11</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv11IsSet();


  /** 
   * <em>account_open_div11</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv11IsModified();


  /** 
   * <em>account_open_div12</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv12();


  /** 
   * <em>account_open_div12</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv12IsSet();


  /** 
   * <em>account_open_div12</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv12IsModified();


  /** 
   * <em>account_open_div13</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountOpenDiv13();


  /** 
   * <em>account_open_div13</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv13IsSet();


  /** 
   * <em>account_open_div13</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountOpenDiv13IsModified();


  /** 
   * <em>taxation_appl_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxationApplDiv();


  /** 
   * <em>taxation_appl_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationApplDivIsSet();


  /** 
   * <em>taxation_appl_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationApplDivIsModified();


  /** 
   * <em>taxation_tran_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxationTranDiv();


  /** 
   * <em>taxation_tran_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationTranDivIsSet();


  /** 
   * <em>taxation_tran_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxationTranDivIsModified();


  /** 
   * <em>send_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSendDiv();


  /** 
   * <em>send_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendDivIsSet();


  /** 
   * <em>send_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSendDivIsModified();


  /** 
   * <em>trust_via_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTrustViaDiv();


  /** 
   * <em>trust_via_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrustViaDivIsSet();


  /** 
   * <em>trust_via_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrustViaDivIsModified();


  /** 
   * <em>correct_div1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCorrectDiv1();


  /** 
   * <em>correct_div1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv1IsSet();


  /** 
   * <em>correct_div1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv1IsModified();


  /** 
   * <em>correct_div2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCorrectDiv2();


  /** 
   * <em>correct_div2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv2IsSet();


  /** 
   * <em>correct_div2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv2IsModified();


  /** 
   * <em>correct_div3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCorrectDiv3();


  /** 
   * <em>correct_div3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv3IsSet();


  /** 
   * <em>correct_div3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv3IsModified();


  /** 
   * <em>correct_div4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCorrectDiv4();


  /** 
   * <em>correct_div4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv4IsSet();


  /** 
   * <em>correct_div4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv4IsModified();


  /** 
   * <em>correct_div5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCorrectDiv5();


  /** 
   * <em>correct_div5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv5IsSet();


  /** 
   * <em>correct_div5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorrectDiv5IsModified();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoAccOpenDivTokyo();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivTokyoIsSet();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivTokyoIsModified();


  /** 
   * <em>ifo_acc_open_div_osaka</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoAccOpenDivOsaka();


  /** 
   * <em>ifo_acc_open_div_osaka</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivOsakaIsSet();


  /** 
   * <em>ifo_acc_open_div_osaka</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivOsakaIsModified();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoAccOpenDivNagoya();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsSet();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsModified();


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
   * <em>other_contact_telephone</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOtherContactTelephone();


  /** 
   * <em>other_contact_telephone</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherContactTelephoneIsSet();


  /** 
   * <em>other_contact_telephone</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOtherContactTelephoneIsModified();


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
   * <em>proam_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProamDiv();


  /** 
   * <em>proam_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProamDivIsSet();


  /** 
   * <em>proam_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProamDivIsModified();


  /** 
   * <em>foreigner_div_broadcast</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignerDivBroadcast();


  /** 
   * <em>foreigner_div_broadcast</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignerDivBroadcastIsSet();


  /** 
   * <em>foreigner_div_broadcast</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignerDivBroadcastIsModified();


  /** 
   * <em>foreigner_div_aviation</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignerDivAviation();


  /** 
   * <em>foreigner_div_aviation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignerDivAviationIsSet();


  /** 
   * <em>foreigner_div_aviation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignerDivAviationIsModified();


  /** 
   * <em>foreigner_div_ntt</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignerDivNtt();


  /** 
   * <em>foreigner_div_ntt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignerDivNttIsSet();


  /** 
   * <em>foreigner_div_ntt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignerDivNttIsModified();


  /** 
   * <em>dividend_transfer_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDividendTransferDiv();


  /** 
   * <em>dividend_transfer_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDividendTransferDivIsSet();


  /** 
   * <em>dividend_transfer_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDividendTransferDivIsModified();


  /** 
   * <em>agent_div_permanent</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgentDivPermanent();


  /** 
   * <em>agent_div_permanent</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgentDivPermanentIsSet();


  /** 
   * <em>agent_div_permanent</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgentDivPermanentIsModified();


  /** 
   * <em>agent_div_legal</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgentDivLegal();


  /** 
   * <em>agent_div_legal</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgentDivLegalIsSet();


  /** 
   * <em>agent_div_legal</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgentDivLegalIsModified();


}
@
