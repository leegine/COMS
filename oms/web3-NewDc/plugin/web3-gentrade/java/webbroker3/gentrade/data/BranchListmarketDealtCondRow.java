head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchListmarketDealtCondRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * BranchListmarketDealtCondRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>branch_listmarket_dealt_cond</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link BranchListmarketDealtCondRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchListmarketDealtCondPK 
 */
public interface BranchListmarketDealtCondRow extends Row {


  /** 
   * ����{@@link BranchListmarketDealtCondRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "branch_listmarket_dealt_cond", "master" );


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
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarketId();


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsSet();


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsModified();


  /** 
   * <em>list_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getListType();


  /** 
   * <em>list_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListTypeIsSet();


  /** 
   * <em>list_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListTypeIsModified();


  /** 
   * <em>new_list_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNewListType();


  /** 
   * <em>new_list_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewListTypeIsSet();


  /** 
   * <em>new_list_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewListTypeIsModified();


  /** 
   * <em>open_otc_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOpenOtcDiv();


  /** 
   * <em>open_otc_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenOtcDivIsSet();


  /** 
   * <em>open_otc_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenOtcDivIsModified();


  /** 
   * <em>listmarket_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getListmarketName();


  /** 
   * <em>listmarket_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListmarketNameIsSet();


  /** 
   * <em>listmarket_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListmarketNameIsModified();


  /** 
   * <em>margin_sec_check_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginSecCheckRate();


  /** 
   * <em>margin_sec_check_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginSecCheckRateIsNull();


  /** 
   * <em>margin_sec_check_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecCheckRateIsSet();


  /** 
   * <em>margin_sec_check_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecCheckRateIsModified();


  /** 
   * <em>max_cont_price_ind</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPriceInd();


  /** 
   * <em>max_cont_price_ind</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPriceIndIsNull();


  /** 
   * <em>max_cont_price_ind</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceIndIsSet();


  /** 
   * <em>max_cont_price_ind</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceIndIsModified();


  /** 
   * <em>max_cont_price_corp</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPriceCorp();


  /** 
   * <em>max_cont_price_corp</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPriceCorpIsNull();


  /** 
   * <em>max_cont_price_corp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceCorpIsSet();


  /** 
   * <em>max_cont_price_corp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceCorpIsModified();


  /** 
   * <em>max_cont_unit_ind</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContUnitInd();


  /** 
   * <em>max_cont_unit_ind</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContUnitIndIsNull();


  /** 
   * <em>max_cont_unit_ind</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContUnitIndIsSet();


  /** 
   * <em>max_cont_unit_ind</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContUnitIndIsModified();


  /** 
   * <em>max_cont_unit_corp</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContUnitCorp();


  /** 
   * <em>max_cont_unit_corp</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContUnitCorpIsNull();


  /** 
   * <em>max_cont_unit_corp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContUnitCorpIsSet();


  /** 
   * <em>max_cont_unit_corp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContUnitCorpIsModified();


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
