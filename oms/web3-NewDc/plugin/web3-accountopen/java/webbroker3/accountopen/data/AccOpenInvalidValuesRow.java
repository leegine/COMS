head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenInvalidValuesRow.java;


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
 * AccOpenInvalidValuesRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>acc_open_invalid_values</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link AccOpenInvalidValuesRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenInvalidValuesPK 
 */
public interface AccOpenInvalidValuesRow extends Row {


  /** 
   * ����{@@link AccOpenInvalidValuesRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "acc_open_invalid_values", "session" );


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
   * <em>item_name1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName1();


  /** 
   * <em>item_name1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName1IsSet();


  /** 
   * <em>item_name1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName1IsModified();


  /** 
   * <em>comment1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment1();


  /** 
   * <em>comment1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment1IsSet();


  /** 
   * <em>comment1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment1IsModified();


  /** 
   * <em>comp_flag1</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag1();


  /** 
   * <em>comp_flag1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag1IsSet();


  /** 
   * <em>comp_flag1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag1IsModified();


  /** 
   * <em>item_name2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName2();


  /** 
   * <em>item_name2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName2IsSet();


  /** 
   * <em>item_name2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName2IsModified();


  /** 
   * <em>comment2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment2();


  /** 
   * <em>comment2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment2IsSet();


  /** 
   * <em>comment2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment2IsModified();


  /** 
   * <em>comp_flag2</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag2();


  /** 
   * <em>comp_flag2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag2IsSet();


  /** 
   * <em>comp_flag2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag2IsModified();


  /** 
   * <em>item_name3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName3();


  /** 
   * <em>item_name3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName3IsSet();


  /** 
   * <em>item_name3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName3IsModified();


  /** 
   * <em>comment3</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment3();


  /** 
   * <em>comment3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment3IsSet();


  /** 
   * <em>comment3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment3IsModified();


  /** 
   * <em>comp_flag3</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag3();


  /** 
   * <em>comp_flag3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag3IsSet();


  /** 
   * <em>comp_flag3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag3IsModified();


  /** 
   * <em>item_name4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName4();


  /** 
   * <em>item_name4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName4IsSet();


  /** 
   * <em>item_name4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName4IsModified();


  /** 
   * <em>comment4</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment4();


  /** 
   * <em>comment4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment4IsSet();


  /** 
   * <em>comment4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment4IsModified();


  /** 
   * <em>comp_flag4</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag4();


  /** 
   * <em>comp_flag4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag4IsSet();


  /** 
   * <em>comp_flag4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag4IsModified();


  /** 
   * <em>item_name5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName5();


  /** 
   * <em>item_name5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName5IsSet();


  /** 
   * <em>item_name5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName5IsModified();


  /** 
   * <em>comment5</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment5();


  /** 
   * <em>comment5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment5IsSet();


  /** 
   * <em>comment5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment5IsModified();


  /** 
   * <em>comp_flag5</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag5();


  /** 
   * <em>comp_flag5</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag5IsSet();


  /** 
   * <em>comp_flag5</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag5IsModified();


  /** 
   * <em>item_name6</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName6();


  /** 
   * <em>item_name6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName6IsSet();


  /** 
   * <em>item_name6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName6IsModified();


  /** 
   * <em>comment6</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment6();


  /** 
   * <em>comment6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment6IsSet();


  /** 
   * <em>comment6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment6IsModified();


  /** 
   * <em>comp_flag6</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag6();


  /** 
   * <em>comp_flag6</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag6IsSet();


  /** 
   * <em>comp_flag6</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag6IsModified();


  /** 
   * <em>item_name7</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName7();


  /** 
   * <em>item_name7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName7IsSet();


  /** 
   * <em>item_name7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName7IsModified();


  /** 
   * <em>comment7</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment7();


  /** 
   * <em>comment7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment7IsSet();


  /** 
   * <em>comment7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment7IsModified();


  /** 
   * <em>comp_flag7</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag7();


  /** 
   * <em>comp_flag7</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag7IsSet();


  /** 
   * <em>comp_flag7</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag7IsModified();


  /** 
   * <em>item_name8</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName8();


  /** 
   * <em>item_name8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName8IsSet();


  /** 
   * <em>item_name8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName8IsModified();


  /** 
   * <em>comment8</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment8();


  /** 
   * <em>comment8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment8IsSet();


  /** 
   * <em>comment8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment8IsModified();


  /** 
   * <em>comp_flag8</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag8();


  /** 
   * <em>comp_flag8</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag8IsSet();


  /** 
   * <em>comp_flag8</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag8IsModified();


  /** 
   * <em>item_name9</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName9();


  /** 
   * <em>item_name9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName9IsSet();


  /** 
   * <em>item_name9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName9IsModified();


  /** 
   * <em>comment9</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment9();


  /** 
   * <em>comment9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment9IsSet();


  /** 
   * <em>comment9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment9IsModified();


  /** 
   * <em>comp_flag9</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag9();


  /** 
   * <em>comp_flag9</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag9IsSet();


  /** 
   * <em>comp_flag9</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag9IsModified();


  /** 
   * <em>item_name10</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getItemName10();


  /** 
   * <em>item_name10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName10IsSet();


  /** 
   * <em>item_name10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getItemName10IsModified();


  /** 
   * <em>comment10</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComment10();


  /** 
   * <em>comment10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment10IsSet();


  /** 
   * <em>comment10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComment10IsModified();


  /** 
   * <em>comp_flag10</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCompFlag10();


  /** 
   * <em>comp_flag10</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag10IsSet();


  /** 
   * <em>comp_flag10</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompFlag10IsModified();


  /** 
   * <em>note1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNote1();


  /** 
   * <em>note1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNote1IsSet();


  /** 
   * <em>note1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNote1IsModified();


  /** 
   * <em>note2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNote2();


  /** 
   * <em>note2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNote2IsSet();


  /** 
   * <em>note2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNote2IsModified();


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


}
@
