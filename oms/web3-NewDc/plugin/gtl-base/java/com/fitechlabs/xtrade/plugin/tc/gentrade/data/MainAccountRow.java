head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MainAccountRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * MainAccountRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>main_account</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link MainAccountRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountPK 
 */
public interface MainAccountRow extends Row {


  /** 
   * ����{@@link MainAccountRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "main_account", "master" );


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
   * <em>account_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum getAccountType();


  /** 
   * <em>account_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountTypeIsSet();


  /** 
   * <em>account_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountTypeIsModified();


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
   * <em>middle_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMiddleName();


  /** 
   * <em>middle_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiddleNameIsSet();


  /** 
   * <em>middle_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiddleNameIsModified();


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
   * <em>middle_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMiddleNameAlt1();


  /** 
   * <em>middle_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiddleNameAlt1IsSet();


  /** 
   * <em>middle_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiddleNameAlt1IsModified();


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
   * <em>family_name_alt2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFamilyNameAlt2();


  /** 
   * <em>family_name_alt2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameAlt2IsSet();


  /** 
   * <em>family_name_alt2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFamilyNameAlt2IsModified();


  /** 
   * <em>middle_name_alt2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMiddleNameAlt2();


  /** 
   * <em>middle_name_alt2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiddleNameAlt2IsSet();


  /** 
   * <em>middle_name_alt2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiddleNameAlt2IsModified();


  /** 
   * <em>given_name_alt2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getGivenNameAlt2();


  /** 
   * <em>given_name_alt2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGivenNameAlt2IsSet();


  /** 
   * <em>given_name_alt2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGivenNameAlt2IsModified();


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
   * <em>sub_zip_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSubZipCode();


  /** 
   * <em>sub_zip_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubZipCodeIsSet();


  /** 
   * <em>sub_zip_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubZipCodeIsModified();


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
   * <em>equity_order_exe_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getEquityOrderExeMailFlag();


  /** 
   * <em>equity_order_exe_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityOrderExeMailFlagIsSet();


  /** 
   * <em>equity_order_exe_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityOrderExeMailFlagIsModified();


  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getEquityOrderUnexecMailFlag();


  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityOrderUnexecMailFlagIsSet();


  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquityOrderUnexecMailFlagIsModified();


  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIfoOrderExecMailFlag();


  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoOrderExecMailFlagIsSet();


  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoOrderExecMailFlagIsModified();


  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIfoOrderUnexecMailFlag();


  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoOrderUnexecMailFlagIsSet();


  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoOrderUnexecMailFlagIsModified();


  /** 
   * <em>information_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInformationMailFlag();


  /** 
   * <em>information_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInformationMailFlagIsSet();


  /** 
   * <em>information_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInformationMailFlagIsModified();


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
   * <em>new_account_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNewAccountDiv();


  /** 
   * <em>new_account_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewAccountDivIsSet();


  /** 
   * <em>new_account_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewAccountDivIsModified();


  /** 
   * <em>via_trust_bank_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getViaTrustBankDiv();


  /** 
   * <em>via_trust_bank_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getViaTrustBankDivIsSet();


  /** 
   * <em>via_trust_bank_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getViaTrustBankDivIsModified();


  /** 
   * <em>class_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getClassDiv();


  /** 
   * <em>class_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClassDivIsSet();


  /** 
   * <em>class_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClassDivIsModified();


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
   * <em>trading_password</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingPassword();


  /** 
   * <em>trading_password</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingPasswordIsSet();


  /** 
   * <em>trading_password</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingPasswordIsModified();


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
   * <em>account_close_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getAccountCloseDate();


  /** 
   * <em>account_close_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCloseDateIsSet();


  /** 
   * <em>account_close_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCloseDateIsModified();


  /** 
   * <em>acc_open_send_mail_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccOpenSendMailStatus();


  /** 
   * <em>acc_open_send_mail_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccOpenSendMailStatusIsSet();


  /** 
   * <em>acc_open_send_mail_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccOpenSendMailStatusIsModified();


  /** 
   * <em>person_identify</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPersonIdentify();


  /** 
   * <em>person_identify</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPersonIdentifyIsSet();


  /** 
   * <em>person_identify</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPersonIdentifyIsModified();


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
   * <em>yellow_customer</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getYellowCustomer();


  /** 
   * <em>yellow_customer</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getYellowCustomerIsSet();


  /** 
   * <em>yellow_customer</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getYellowCustomerIsModified();


  /** 
   * <em>ht_settlement_way</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getHtSettlementWay();


  /** 
   * <em>ht_settlement_way</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHtSettlementWayIsSet();


  /** 
   * <em>ht_settlement_way</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHtSettlementWayIsModified();


  /** 
   * <em>bank_account_regi</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBankAccountRegi();


  /** 
   * <em>bank_account_regi</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBankAccountRegiIsSet();


  /** 
   * <em>bank_account_regi</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBankAccountRegiIsModified();


  /** 
   * <em>account_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum getAccountStatus();


  /** 
   * <em>account_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountStatusIsSet();


  /** 
   * <em>account_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountStatusIsModified();


  /** 
   * <em>examin_lock_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExaminLockFlag();


  /** 
   * <em>examin_lock_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExaminLockFlagIsSet();


  /** 
   * <em>examin_lock_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getExaminLockFlagIsModified();


  /** 
   * <em>mng_lock_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMngLockFlag();


  /** 
   * <em>mng_lock_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagIsSet();


  /** 
   * <em>mng_lock_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagIsModified();


  /** 
   * <em>mng_lock_off_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getMngLockOffStartDate();


  /** 
   * <em>mng_lock_off_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockOffStartDateIsSet();


  /** 
   * <em>mng_lock_off_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockOffStartDateIsModified();


  /** 
   * <em>mng_lock_off_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getMngLockOffEndDate();


  /** 
   * <em>mng_lock_off_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockOffEndDateIsSet();


  /** 
   * <em>mng_lock_off_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockOffEndDateIsModified();


  /** 
   * <em>mng_lock_flag_advance</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagAdvance();


  /** 
   * <em>mng_lock_flag_advance</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagAdvanceIsSet();


  /** 
   * <em>mng_lock_flag_advance</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagAdvanceIsModified();


  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnpayMargin();


  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagUnpayMarginIsSet();


  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagUnpayMarginIsModified();


  /** 
   * <em>mng_lock_flag_short_security</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagShortSecurity();


  /** 
   * <em>mng_lock_flag_short_security</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagShortSecurityIsSet();


  /** 
   * <em>mng_lock_flag_short_security</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagShortSecurityIsModified();


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnsubstitDepo();


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagUnsubstitDepoIsSet();


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMngLockFlagUnsubstitDepoIsModified();


  /** 
   * <em>branch_lock</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchLock();


  /** 
   * <em>branch_lock</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchLockIsSet();


  /** 
   * <em>branch_lock</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchLockIsModified();


  /** 
   * <em>order_permission</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderPermission();


  /** 
   * <em>order_permission</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderPermissionIsSet();


  /** 
   * <em>order_permission</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderPermissionIsModified();


  /** 
   * <em>tax_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType();


  /** 
   * <em>tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeIsSet();


  /** 
   * <em>tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeIsModified();


  /** 
   * <em>tax_type_next</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxTypeNext();


  /** 
   * <em>tax_type_next</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeNextIsSet();


  /** 
   * <em>tax_type_next</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeNextIsModified();


  /** 
   * <em>margin_tax_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getMarginTaxType();


  /** 
   * <em>margin_tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeIsSet();


  /** 
   * <em>margin_tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeIsModified();


  /** 
   * <em>margin_tax_type_next</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getMarginTaxTypeNext();


  /** 
   * <em>margin_tax_type_next</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeNextIsSet();


  /** 
   * <em>margin_tax_type_next</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginTaxTypeNextIsModified();


  /** 
   * <em>qualified_inst_investor_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getQualifiedInstInvestorDiv();


  /** 
   * <em>qualified_inst_investor_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQualifiedInstInvestorDivIsSet();


  /** 
   * <em>qualified_inst_investor_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQualifiedInstInvestorDivIsModified();


  /** 
   * <em>margin_sys_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginSysAccOpenDiv();


  /** 
   * <em>margin_sys_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSysAccOpenDivIsSet();


  /** 
   * <em>margin_sys_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSysAccOpenDivIsModified();


  /** 
   * <em>margin_gen_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginGenAccOpenDiv();


  /** 
   * <em>margin_gen_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginGenAccOpenDivIsSet();


  /** 
   * <em>margin_gen_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginGenAccOpenDivIsModified();


  /** 
   * <em>equity_sp_acc_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEquitySpAccOpenDate();


  /** 
   * <em>equity_sp_acc_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquitySpAccOpenDateIsSet();


  /** 
   * <em>equity_sp_acc_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEquitySpAccOpenDateIsModified();


  /** 
   * <em>margin_sp_acc_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getMarginSpAccOpenDate();


  /** 
   * <em>margin_sp_acc_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSpAccOpenDateIsSet();


  /** 
   * <em>margin_sp_acc_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSpAccOpenDateIsModified();


  /** 
   * <em>transfer_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getTransferCount();


  /** 
   * <em>transfer_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTransferCountIsNull();


  /** 
   * <em>transfer_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferCountIsSet();


  /** 
   * <em>transfer_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferCountIsModified();


  /** 
   * <em>foreign_sec_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getForeignSecAccOpenDiv();


  /** 
   * <em>foreign_sec_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignSecAccOpenDivIsSet();


  /** 
   * <em>foreign_sec_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getForeignSecAccOpenDivIsModified();


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
   * <em>ruito_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRuitoAccOpenDiv();


  /** 
   * <em>ruito_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAccOpenDivIsSet();


  /** 
   * <em>ruito_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoAccOpenDivIsModified();


  /** 
   * <em>mrf_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMrfAccOpenDiv();


  /** 
   * <em>mrf_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfAccOpenDivIsSet();


  /** 
   * <em>mrf_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfAccOpenDivIsModified();


  /** 
   * <em>fx_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFxAccOpenDiv();


  /** 
   * <em>fx_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFxAccOpenDivIsSet();


  /** 
   * <em>fx_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFxAccOpenDivIsModified();


  /** 
   * <em>feq_con_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFeqConAccOpenDiv();


  /** 
   * <em>feq_con_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFeqConAccOpenDivIsSet();


  /** 
   * <em>feq_con_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFeqConAccOpenDivIsModified();


  /** 
   * <em>top_page_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTopPageId();


  /** 
   * <em>top_page_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTopPageIdIsSet();


  /** 
   * <em>top_page_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTopPageIdIsModified();


  /** 
   * <em>quoto_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getQuotoType();


  /** 
   * <em>quoto_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuotoTypeIsSet();


  /** 
   * <em>quoto_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuotoTypeIsModified();


  /** 
   * <em>trading_report_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingReportDiv();


  /** 
   * <em>trading_report_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingReportDivIsSet();


  /** 
   * <em>trading_report_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingReportDivIsModified();


  /** 
   * <em>position_report_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPositionReportDiv();


  /** 
   * <em>position_report_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportDivIsSet();


  /** 
   * <em>position_report_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportDivIsModified();


  /** 
   * <em>position_report_cycle_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPositionReportCycleDiv();


  /** 
   * <em>position_report_cycle_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportCycleDivIsSet();


  /** 
   * <em>position_report_cycle_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPositionReportCycleDivIsModified();


  /** 
   * <em>certificate_deposit_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCertificateDepositFlag();


  /** 
   * <em>certificate_deposit_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCertificateDepositFlagIsSet();


  /** 
   * <em>certificate_deposit_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCertificateDepositFlagIsModified();


  /** 
   * <em>account_statement_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getAccountStatementFlag();


  /** 
   * <em>account_statement_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountStatementFlagIsSet();


  /** 
   * <em>account_statement_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountStatementFlagIsModified();


  /** 
   * <em>email_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEmailLastUpdater();


  /** 
   * <em>email_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailLastUpdaterIsSet();


  /** 
   * <em>email_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailLastUpdaterIsModified();


  /** 
   * <em>email_last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEmailLastUpdatedTimestamp();


  /** 
   * <em>email_last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailLastUpdatedTimestampIsSet();


  /** 
   * <em>email_last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailLastUpdatedTimestampIsModified();


  /** 
   * <em>trading_password_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradingPasswordUpdater();


  /** 
   * <em>trading_password_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingPasswordUpdaterIsSet();


  /** 
   * <em>trading_password_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradingPasswordUpdaterIsModified();


  /** 
   * <em>tr_pwd_last_update_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getTrPwdLastUpdateTimestamp();


  /** 
   * <em>tr_pwd_last_update_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrPwdLastUpdateTimestampIsSet();


  /** 
   * <em>tr_pwd_last_update_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrPwdLastUpdateTimestampIsModified();


  /** 
   * <em>mb_off_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMbOffLastUpdater();


  /** 
   * <em>mb_off_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMbOffLastUpdaterIsSet();


  /** 
   * <em>mb_off_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMbOffLastUpdaterIsModified();


  /** 
   * <em>mb_off_last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getMbOffLastUpdatedTimestamp();


  /** 
   * <em>mb_off_last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMbOffLastUpdatedTimestampIsSet();


  /** 
   * <em>mb_off_last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMbOffLastUpdatedTimestampIsModified();


  /** 
   * <em>enable_order_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEnableOrderLastUpdater();


  /** 
   * <em>enable_order_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableOrderLastUpdaterIsSet();


  /** 
   * <em>enable_order_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableOrderLastUpdaterIsModified();


  /** 
   * <em>enable_order_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEnableOrderUpdatedTimestamp();


  /** 
   * <em>enable_order_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableOrderUpdatedTimestampIsSet();


  /** 
   * <em>enable_order_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableOrderUpdatedTimestampIsModified();


  /** 
   * <em>fx_acc_open_div_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFxAccOpenDivLastUpdater();


  /** 
   * <em>fx_acc_open_div_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFxAccOpenDivLastUpdaterIsSet();


  /** 
   * <em>fx_acc_open_div_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFxAccOpenDivLastUpdaterIsModified();


  /** 
   * <em>fx_acc_open_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getFxAccOpenUpdatedTimestamp();


  /** 
   * <em>fx_acc_open_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFxAccOpenUpdatedTimestampIsSet();


  /** 
   * <em>fx_acc_open_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFxAccOpenUpdatedTimestampIsModified();


  /** 
   * <em>feq_con_acc_open_div_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFeqConAccOpenDivUpdater();


  /** 
   * <em>feq_con_acc_open_div_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFeqConAccOpenDivUpdaterIsSet();


  /** 
   * <em>feq_con_acc_open_div_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFeqConAccOpenDivUpdaterIsModified();


  /** 
   * <em>feq_con_acc_open_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getFeqConAccOpenTimestamp();


  /** 
   * <em>feq_con_acc_open_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFeqConAccOpenTimestampIsSet();


  /** 
   * <em>feq_con_acc_open_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFeqConAccOpenTimestampIsModified();


  /** 
   * <em>mrf_fund_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMrfFundCode();


  /** 
   * <em>mrf_fund_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfFundCodeIsSet();


  /** 
   * <em>mrf_fund_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMrfFundCodeIsModified();


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
   * <em>sp_mng_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSpMngAccOpenDiv();


  /** 
   * <em>sp_mng_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpMngAccOpenDivIsSet();


  /** 
   * <em>sp_mng_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpMngAccOpenDivIsModified();


  /** 
   * <em>lock_registration_reason</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLockRegistrationReason();


  /** 
   * <em>lock_registration_reason</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLockRegistrationReasonIsSet();


  /** 
   * <em>lock_registration_reason</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLockRegistrationReasonIsModified();


  /** 
   * <em>inf_mail_flg_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInfMailFlgLastUpdater();


  /** 
   * <em>inf_mail_flg_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInfMailFlgLastUpdaterIsSet();


  /** 
   * <em>inf_mail_flg_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInfMailFlgLastUpdaterIsModified();


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getInfMailFlgUpdatedTimestamp();


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInfMailFlgUpdatedTimestampIsSet();


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInfMailFlgUpdatedTimestampIsModified();


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEqExeMlFlgLastUpdater();


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqExeMlFlgLastUpdaterIsSet();


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqExeMlFlgLastUpdaterIsModified();


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEqExeMlFlgTimestamp();


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqExeMlFlgTimestampIsSet();


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqExeMlFlgTimestampIsModified();


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEqUnexeMlFlgLastUpdater();


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqUnexeMlFlgLastUpdaterIsSet();


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqUnexeMlFlgLastUpdaterIsModified();


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEqUnexeMlFlgTimestamp();


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqUnexeMlFlgTimestampIsSet();


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqUnexeMlFlgTimestampIsModified();


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoExeMlFlgLastUpdater();


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoExeMlFlgLastUpdaterIsSet();


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoExeMlFlgLastUpdaterIsModified();


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getIfoExeMlFlgTimestamp();


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoExeMlFlgTimestampIsSet();


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoExeMlFlgTimestampIsModified();


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIfoUnexeMlFlgLastUpdater();


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoUnexeMlFlgLastUpdaterIsSet();


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoUnexeMlFlgLastUpdaterIsModified();


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getIfoUnexeMlFlgTimestamp();


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoUnexeMlFlgTimestampIsSet();


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoUnexeMlFlgTimestampIsModified();


  /** 
   * <em>stock_option_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStockOptionAccOpenDiv();


  /** 
   * <em>stock_option_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStockOptionAccOpenDivIsSet();


  /** 
   * <em>stock_option_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStockOptionAccOpenDivIsModified();


  /** 
   * <em>secured_loan_sec_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSecuredLoanSecAccOpenDiv();


  /** 
   * <em>secured_loan_sec_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSecuredLoanSecAccOpenDivIsSet();


  /** 
   * <em>secured_loan_sec_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSecuredLoanSecAccOpenDivIsModified();


  /** 
   * <em>margin_acc_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getMarginAccOpenDate();


  /** 
   * <em>margin_acc_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccOpenDateIsSet();


  /** 
   * <em>margin_acc_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginAccOpenDateIsModified();


  /** 
   * <em>ifo_acc_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getIfoAccOpenDate();


  /** 
   * <em>ifo_acc_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDateIsSet();


  /** 
   * <em>ifo_acc_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIfoAccOpenDateIsModified();


  /** 
   * <em>only_mobile_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOnlyMobileOpenDiv();


  /** 
   * <em>only_mobile_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlyMobileOpenDivIsSet();


  /** 
   * <em>only_mobile_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlyMobileOpenDivIsModified();


  /** 
   * <em>only_mbl_opn_div_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOnlyMblOpnDivLastUpdater();


  /** 
   * <em>only_mbl_opn_div_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlyMblOpnDivLastUpdaterIsSet();


  /** 
   * <em>only_mbl_opn_div_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlyMblOpnDivLastUpdaterIsModified();


  /** 
   * <em>only_mbl_opn_div_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOnlyMblOpnDivTimestamp();


  /** 
   * <em>only_mbl_opn_div_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlyMblOpnDivTimestampIsSet();


  /** 
   * <em>only_mbl_opn_div_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlyMblOpnDivTimestampIsModified();


  /** 
   * <em>email_address_alt2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEmailAddressAlt2();


  /** 
   * <em>email_address_alt2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressAlt2IsSet();


  /** 
   * <em>email_address_alt2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressAlt2IsModified();


  /** 
   * <em>order_exe_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getOrderExeFlag();


  /** 
   * <em>order_exe_flag</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOrderExeFlagIsNull();


  /** 
   * <em>order_exe_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderExeFlagIsSet();


  /** 
   * <em>order_exe_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderExeFlagIsModified();


  /** 
   * <em>order_unexe_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getOrderUnexeFlag();


  /** 
   * <em>order_unexe_flag</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOrderUnexeFlagIsNull();


  /** 
   * <em>order_unexe_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderUnexeFlagIsSet();


  /** 
   * <em>order_unexe_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderUnexeFlagIsModified();


  /** 
   * <em>important_connection_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getImportantConnectionMailFlag();


  /** 
   * <em>important_connection_mail_flag</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getImportantConnectionMailFlagIsNull();


  /** 
   * <em>important_connection_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportantConnectionMailFlagIsSet();


  /** 
   * <em>important_connection_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportantConnectionMailFlagIsModified();


  /** 
   * <em>information_mail2_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getInformationMail2Flag();


  /** 
   * <em>information_mail2_flag</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getInformationMail2FlagIsNull();


  /** 
   * <em>information_mail2_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInformationMail2FlagIsSet();


  /** 
   * <em>information_mail2_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInformationMail2FlagIsModified();


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
   * <em>org_deposit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrgDepositDiv();


  /** 
   * <em>org_deposit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrgDepositDivIsSet();


  /** 
   * <em>org_deposit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrgDepositDivIsModified();


  /** 
   * <em>eq_hold_rep_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEqHoldRepDiv();


  /** 
   * <em>eq_hold_rep_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqHoldRepDivIsSet();


  /** 
   * <em>eq_hold_rep_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEqHoldRepDivIsModified();


  /** 
   * <em>chkup_rep_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getChkupRepDiv();


  /** 
   * <em>chkup_rep_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getChkupRepDivIsSet();


  /** 
   * <em>chkup_rep_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getChkupRepDivIsModified();


  /** 
   * <em>pts_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPtsAccOpenDiv();


  /** 
   * <em>pts_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPtsAccOpenDivIsSet();


  /** 
   * <em>pts_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPtsAccOpenDivIsModified();


  /** 
   * <em>pts_acc_open_div_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPtsAccOpenDivLastUpdater();


  /** 
   * <em>pts_acc_open_div_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPtsAccOpenDivLastUpdaterIsSet();


  /** 
   * <em>pts_acc_open_div_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPtsAccOpenDivLastUpdaterIsModified();


  /** 
   * <em>pts_acc_open_div_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPtsAccOpenDivTimestamp();


  /** 
   * <em>pts_acc_open_div_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPtsAccOpenDivTimestampIsSet();


  /** 
   * <em>pts_acc_open_div_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPtsAccOpenDivTimestampIsModified();


  /** 
   * <em>broadcast_law</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBroadcastLaw();


  /** 
   * <em>broadcast_law</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBroadcastLawIsSet();


  /** 
   * <em>broadcast_law</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBroadcastLawIsModified();


  /** 
   * <em>aviation_law</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAviationLaw();


  /** 
   * <em>aviation_law</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAviationLawIsSet();


  /** 
   * <em>aviation_law</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAviationLawIsModified();


  /** 
   * <em>ntt_law</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNttLaw();


  /** 
   * <em>ntt_law</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNttLawIsSet();


  /** 
   * <em>ntt_law</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNttLawIsModified();


  /** 
   * <em>dividend_trans_designate</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDividendTransDesignate();


  /** 
   * <em>dividend_trans_designate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDividendTransDesignateIsSet();


  /** 
   * <em>dividend_trans_designate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDividendTransDesignateIsModified();


  /** 
   * <em>standing_proxy</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStandingProxy();


  /** 
   * <em>standing_proxy</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStandingProxyIsSet();


  /** 
   * <em>standing_proxy</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStandingProxyIsModified();


  /** 
   * <em>statutory_agent</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStatutoryAgent();


  /** 
   * <em>statutory_agent</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatutoryAgentIsSet();


  /** 
   * <em>statutory_agent</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatutoryAgentIsModified();


  /** 
   * <em>affiliate_account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAffiliateAccountCode();


  /** 
   * <em>affiliate_account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAffiliateAccountCodeIsSet();


  /** 
   * <em>affiliate_account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAffiliateAccountCodeIsModified();


  /** 
   * <em>agency_notify_cmp_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAgencyNotifyCmpDiv();


  /** 
   * <em>agency_notify_cmp_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyNotifyCmpDivIsSet();


  /** 
   * <em>agency_notify_cmp_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAgencyNotifyCmpDivIsModified();


  /** 
   * <em>cfd_acc_open_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCfdAccOpenDiv();


  /** 
   * <em>cfd_acc_open_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCfdAccOpenDivIsSet();


  /** 
   * <em>cfd_acc_open_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCfdAccOpenDivIsModified();


  /** 
   * <em>cfd_acc_open_div_last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCfdAccOpenDivLastUpdater();


  /** 
   * <em>cfd_acc_open_div_last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCfdAccOpenDivLastUpdaterIsSet();


  /** 
   * <em>cfd_acc_open_div_last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCfdAccOpenDivLastUpdaterIsModified();


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCfdAccOpenUpdatedTimestamp();


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCfdAccOpenUpdatedTimestampIsSet();


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCfdAccOpenUpdatedTimestampIsModified();


}
@
