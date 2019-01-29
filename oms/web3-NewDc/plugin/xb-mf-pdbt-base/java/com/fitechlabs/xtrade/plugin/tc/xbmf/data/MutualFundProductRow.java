head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.12.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundProductRow.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFundProductRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>mutual_fund_product</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link MutualFundProductRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundProductPK 
 */
public interface MutualFundProductRow extends Row {


  /** 
   * ����{@@link MutualFundProductRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_product", "master" );


  /** 
   * <em>product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsModified();


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
   * <em>product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductCode();


  /** 
   * <em>product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductCodeIsSet();


  /** 
   * <em>product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductCodeIsModified();


  /** 
   * <em>product_issue_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductIssueCode();


  /** 
   * <em>product_issue_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIssueCodeIsSet();


  /** 
   * <em>product_issue_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIssueCodeIsModified();


  /** 
   * <em>fund_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum getFundType();


  /** 
   * <em>fund_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundTypeIsSet();


  /** 
   * <em>fund_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFundTypeIsModified();


  /** 
   * <em>init_purchase_min_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getInitPurchaseMinQty();


  /** 
   * <em>init_purchase_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInitPurchaseMinQtyIsSet();


  /** 
   * <em>init_purchase_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInitPurchaseMinQtyIsModified();


  /** 
   * <em>addtl_purchase_min_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAddtlPurchaseMinQty();


  /** 
   * <em>addtl_purchase_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddtlPurchaseMinQtyIsSet();


  /** 
   * <em>addtl_purchase_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddtlPurchaseMinQtyIsModified();


  /** 
   * <em>mutualassoc_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMutualassocProductCode();


  /** 
   * <em>mutualassoc_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMutualassocProductCodeIsSet();


  /** 
   * <em>mutualassoc_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMutualassocProductCodeIsModified();


  /** 
   * <em>system_handling_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSystemHandlingDiv();


  /** 
   * <em>system_handling_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSystemHandlingDivIsSet();


  /** 
   * <em>system_handling_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSystemHandlingDivIsModified();


  /** 
   * <em>buy_limit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyLimitDiv();


  /** 
   * <em>buy_limit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyLimitDivIsSet();


  /** 
   * <em>buy_limit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyLimitDivIsModified();


  /** 
   * <em>standard_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStandardName();


  /** 
   * <em>standard_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStandardNameIsSet();


  /** 
   * <em>standard_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStandardNameIsModified();


  /** 
   * <em>eng_product_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEngProductName();


  /** 
   * <em>eng_product_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEngProductNameIsSet();


  /** 
   * <em>eng_product_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEngProductNameIsModified();


  /** 
   * <em>setting_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSettingDate();


  /** 
   * <em>setting_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSettingDateIsSet();


  /** 
   * <em>setting_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSettingDateIsModified();


  /** 
   * <em>redemption_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getRedemptionDate();


  /** 
   * <em>redemption_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionDateIsSet();


  /** 
   * <em>redemption_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionDateIsModified();


  /** 
   * <em>sell_ban_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSellBanDate();


  /** 
   * <em>sell_ban_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellBanDateIsSet();


  /** 
   * <em>sell_ban_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellBanDateIsModified();


  /** 
   * <em>swt_possible_group_id</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSwtPossibleGroupId();


  /** 
   * <em>swt_possible_group_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtPossibleGroupIdIsNull();


  /** 
   * <em>swt_possible_group_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtPossibleGroupIdIsSet();


  /** 
   * <em>swt_possible_group_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtPossibleGroupIdIsModified();


  /** 
   * <em>category_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCategoryCode();


  /** 
   * <em>category_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCategoryCodeIsSet();


  /** 
   * <em>category_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCategoryCodeIsModified();


  /** 
   * <em>indication_ranking</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getIndicationRanking();


  /** 
   * <em>indication_ranking</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getIndicationRankingIsNull();


  /** 
   * <em>indication_ranking</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIndicationRankingIsSet();


  /** 
   * <em>indication_ranking</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIndicationRankingIsModified();


  /** 
   * <em>buy_constant_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getBuyConstantValue();


  /** 
   * <em>buy_constant_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyConstantValueIsNull();


  /** 
   * <em>buy_constant_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyConstantValueIsSet();


  /** 
   * <em>buy_constant_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyConstantValueIsModified();


  /** 
   * <em>sell_constant_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSellConstantValue();


  /** 
   * <em>sell_constant_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellConstantValueIsNull();


  /** 
   * <em>sell_constant_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellConstantValueIsSet();


  /** 
   * <em>sell_constant_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellConstantValueIsModified();


  /** 
   * <em>reference_constant_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getReferenceConstantValue();


  /** 
   * <em>reference_constant_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getReferenceConstantValueIsNull();


  /** 
   * <em>reference_constant_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReferenceConstantValueIsSet();


  /** 
   * <em>reference_constant_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReferenceConstantValueIsModified();


  /** 
   * <em>constant_value_app_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getConstantValueAppDate();


  /** 
   * <em>constant_value_app_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConstantValueAppDateIsSet();


  /** 
   * <em>constant_value_app_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConstantValueAppDateIsModified();


  /** 
   * <em>currency_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCurrencyCode();


  /** 
   * <em>currency_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCurrencyCodeIsSet();


  /** 
   * <em>currency_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCurrencyCodeIsModified();


  /** 
   * <em>constant_value_calc_unit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getConstantValueCalcUnit();


  /** 
   * <em>constant_value_calc_unit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getConstantValueCalcUnitIsNull();


  /** 
   * <em>constant_value_calc_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConstantValueCalcUnitIsSet();


  /** 
   * <em>constant_value_calc_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConstantValueCalcUnitIsModified();


  /** 
   * <em>buy_settlement_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuySettlementDiv();


  /** 
   * <em>buy_settlement_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuySettlementDivIsSet();


  /** 
   * <em>buy_settlement_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuySettlementDivIsModified();


  /** 
   * <em>sell_settlement_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellSettlementDiv();


  /** 
   * <em>sell_settlement_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSettlementDivIsSet();


  /** 
   * <em>sell_settlement_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSettlementDivIsModified();


  /** 
   * <em>delivery_method</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDeliveryMethod();


  /** 
   * <em>delivery_method</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeliveryMethodIsSet();


  /** 
   * <em>delivery_method</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeliveryMethodIsModified();


  /** 
   * <em>buy_specity_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuySpecityDiv();


  /** 
   * <em>buy_specity_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuySpecityDivIsSet();


  /** 
   * <em>buy_specity_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuySpecityDivIsModified();


  /** 
   * <em>sell_specify_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellSpecifyDiv();


  /** 
   * <em>sell_specify_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSpecifyDivIsSet();


  /** 
   * <em>sell_specify_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSpecifyDivIsModified();


  /** 
   * <em>swt_specify_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSwtSpecifyDiv();


  /** 
   * <em>swt_specify_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtSpecifyDivIsSet();


  /** 
   * <em>swt_specify_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtSpecifyDivIsModified();


  /** 
   * <em>stock_type_bond_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStockTypeBondType();


  /** 
   * <em>stock_type_bond_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStockTypeBondTypeIsSet();


  /** 
   * <em>stock_type_bond_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStockTypeBondTypeIsModified();


  /** 
   * <em>contract_institution_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getContractInstitutionType();


  /** 
   * <em>contract_institution_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInstitutionTypeIsSet();


  /** 
   * <em>contract_institution_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractInstitutionTypeIsModified();


  /** 
   * <em>perference_money_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPerferenceMoneyDiv();


  /** 
   * <em>perference_money_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPerferenceMoneyDivIsSet();


  /** 
   * <em>perference_money_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPerferenceMoneyDivIsModified();


  /** 
   * <em>input_unit</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInputUnit();


  /** 
   * <em>input_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInputUnitIsSet();


  /** 
   * <em>input_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInputUnitIsModified();


  /** 
   * <em>new_buy_min_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getNewBuyMinQty();


  /** 
   * <em>new_buy_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getNewBuyMinQtyIsNull();


  /** 
   * <em>new_buy_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyMinQtyIsSet();


  /** 
   * <em>new_buy_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyMinQtyIsModified();


  /** 
   * <em>add_buy_min_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAddBuyMinQty();


  /** 
   * <em>add_buy_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAddBuyMinQtyIsNull();


  /** 
   * <em>add_buy_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyMinQtyIsSet();


  /** 
   * <em>add_buy_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyMinQtyIsModified();


  /** 
   * <em>sell_min_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSellMinQty();


  /** 
   * <em>sell_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellMinQtyIsNull();


  /** 
   * <em>sell_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinQtyIsSet();


  /** 
   * <em>sell_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinQtyIsModified();


  /** 
   * <em>swt_min_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSwtMinQty();


  /** 
   * <em>swt_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtMinQtyIsNull();


  /** 
   * <em>swt_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinQtyIsSet();


  /** 
   * <em>swt_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinQtyIsModified();


  /** 
   * <em>new_buy_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getNewBuyUnitQty();


  /** 
   * <em>new_buy_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getNewBuyUnitQtyIsNull();


  /** 
   * <em>new_buy_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyUnitQtyIsSet();


  /** 
   * <em>new_buy_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyUnitQtyIsModified();


  /** 
   * <em>add_buy_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAddBuyUnitQty();


  /** 
   * <em>add_buy_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAddBuyUnitQtyIsNull();


  /** 
   * <em>add_buy_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyUnitQtyIsSet();


  /** 
   * <em>add_buy_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyUnitQtyIsModified();


  /** 
   * <em>sell_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSellUnitQty();


  /** 
   * <em>sell_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellUnitQtyIsNull();


  /** 
   * <em>sell_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitQtyIsSet();


  /** 
   * <em>sell_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitQtyIsModified();


  /** 
   * <em>swt_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSwtUnitQty();


  /** 
   * <em>swt_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtUnitQtyIsNull();


  /** 
   * <em>swt_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitQtyIsSet();


  /** 
   * <em>swt_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitQtyIsModified();


  /** 
   * <em>new_buy_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getNewBuyMinAmt();


  /** 
   * <em>new_buy_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getNewBuyMinAmtIsNull();


  /** 
   * <em>new_buy_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyMinAmtIsSet();


  /** 
   * <em>new_buy_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyMinAmtIsModified();


  /** 
   * <em>add_buy_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAddBuyMinAmt();


  /** 
   * <em>add_buy_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAddBuyMinAmtIsNull();


  /** 
   * <em>add_buy_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyMinAmtIsSet();


  /** 
   * <em>add_buy_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyMinAmtIsModified();


  /** 
   * <em>sell_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSellMinAmt();


  /** 
   * <em>sell_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellMinAmtIsNull();


  /** 
   * <em>sell_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinAmtIsSet();


  /** 
   * <em>sell_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinAmtIsModified();


  /** 
   * <em>swt_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSwtMinAmt();


  /** 
   * <em>swt_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtMinAmtIsNull();


  /** 
   * <em>swt_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinAmtIsSet();


  /** 
   * <em>swt_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinAmtIsModified();


  /** 
   * <em>new_buy_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getNewBuyUnitAmt();


  /** 
   * <em>new_buy_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getNewBuyUnitAmtIsNull();


  /** 
   * <em>new_buy_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyUnitAmtIsSet();


  /** 
   * <em>new_buy_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewBuyUnitAmtIsModified();


  /** 
   * <em>add_buy_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAddBuyUnitAmt();


  /** 
   * <em>add_buy_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAddBuyUnitAmtIsNull();


  /** 
   * <em>add_buy_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyUnitAmtIsSet();


  /** 
   * <em>add_buy_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddBuyUnitAmtIsModified();


  /** 
   * <em>sell_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSellUnitAmt();


  /** 
   * <em>sell_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellUnitAmtIsNull();


  /** 
   * <em>sell_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitAmtIsSet();


  /** 
   * <em>sell_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitAmtIsModified();


  /** 
   * <em>swt_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSwtUnitAmt();


  /** 
   * <em>swt_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtUnitAmtIsNull();


  /** 
   * <em>swt_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitAmtIsSet();


  /** 
   * <em>swt_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitAmtIsModified();


  /** 
   * <em>buy_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBuyStartDate();


  /** 
   * <em>buy_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyStartDateIsSet();


  /** 
   * <em>buy_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyStartDateIsModified();


  /** 
   * <em>buy_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBuyEndDate();


  /** 
   * <em>buy_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyEndDateIsSet();


  /** 
   * <em>buy_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyEndDateIsModified();


  /** 
   * <em>sell_swt_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSellSwtStartDate();


  /** 
   * <em>sell_swt_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSwtStartDateIsSet();


  /** 
   * <em>sell_swt_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSwtStartDateIsModified();


  /** 
   * <em>sell_swt_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSellSwtEndDate();


  /** 
   * <em>sell_swt_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSwtEndDateIsSet();


  /** 
   * <em>sell_swt_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSwtEndDateIsModified();


  /** 
   * <em>buy_claim_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBuyClaimStartDate();


  /** 
   * <em>buy_claim_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyClaimStartDateIsSet();


  /** 
   * <em>buy_claim_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyClaimStartDateIsModified();


  /** 
   * <em>buy_claim_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBuyClaimEndDate();


  /** 
   * <em>buy_claim_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyClaimEndDateIsSet();


  /** 
   * <em>buy_claim_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyClaimEndDateIsModified();


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
   * <em>online_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOnlineUpdatedTimestamp();


  /** 
   * <em>online_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlineUpdatedTimestampIsSet();


  /** 
   * <em>online_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOnlineUpdatedTimestampIsModified();


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
   * <em>recruit_constant_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getRecruitConstantValue();


  /** 
   * <em>recruit_constant_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitConstantValueIsNull();


  /** 
   * <em>recruit_constant_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitConstantValueIsSet();


  /** 
   * <em>recruit_constant_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitConstantValueIsModified();


  /** 
   * <em>recruit_settlement_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitSettlementDiv();


  /** 
   * <em>recruit_settlement_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitSettlementDivIsSet();


  /** 
   * <em>recruit_settlement_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitSettlementDivIsModified();


  /** 
   * <em>recruit_specity_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitSpecityDiv();


  /** 
   * <em>recruit_specity_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitSpecityDivIsSet();


  /** 
   * <em>recruit_specity_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitSpecityDivIsModified();


  /** 
   * <em>recruit_min_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getRecruitMinQty();


  /** 
   * <em>recruit_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitMinQtyIsNull();


  /** 
   * <em>recruit_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinQtyIsSet();


  /** 
   * <em>recruit_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinQtyIsModified();


  /** 
   * <em>recruit_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getRecruitUnitQty();


  /** 
   * <em>recruit_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitUnitQtyIsNull();


  /** 
   * <em>recruit_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitQtyIsSet();


  /** 
   * <em>recruit_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitQtyIsModified();


  /** 
   * <em>recruit_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getRecruitMinAmt();


  /** 
   * <em>recruit_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitMinAmtIsNull();


  /** 
   * <em>recruit_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinAmtIsSet();


  /** 
   * <em>recruit_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinAmtIsModified();


  /** 
   * <em>recruit_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getRecruitUnitAmt();


  /** 
   * <em>recruit_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitUnitAmtIsNull();


  /** 
   * <em>recruit_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitAmtIsSet();


  /** 
   * <em>recruit_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitAmtIsModified();


  /** 
   * <em>recruit_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getRecruitStartDate();


  /** 
   * <em>recruit_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStartDateIsSet();


  /** 
   * <em>recruit_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStartDateIsModified();


  /** 
   * <em>recruit_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getRecruitEndDate();


  /** 
   * <em>recruit_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitEndDateIsSet();


  /** 
   * <em>recruit_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitEndDateIsModified();


  /** 
   * <em>cal_price_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCalPriceDiv();


  /** 
   * <em>cal_price_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalPriceDivIsSet();


  /** 
   * <em>cal_price_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalPriceDivIsModified();


  /** 
   * <em>plowback_product_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPlowbackProductDiv();


  /** 
   * <em>plowback_product_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPlowbackProductDivIsSet();


  /** 
   * <em>plowback_product_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPlowbackProductDivIsModified();


  /** 
   * <em>recruit_start_date_sonar</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getRecruitStartDateSonar();


  /** 
   * <em>recruit_start_date_sonar</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStartDateSonarIsSet();


  /** 
   * <em>recruit_start_date_sonar</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStartDateSonarIsModified();


  /** 
   * <em>recruit_end_date_sonar</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getRecruitEndDateSonar();


  /** 
   * <em>recruit_end_date_sonar</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitEndDateSonarIsSet();


  /** 
   * <em>recruit_end_date_sonar</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitEndDateSonarIsModified();


  /** 
   * <em>fixed_buy_possible_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFixedBuyPossibleDiv();


  /** 
   * <em>fixed_buy_possible_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFixedBuyPossibleDivIsSet();


  /** 
   * <em>fixed_buy_possible_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFixedBuyPossibleDivIsModified();


  /** 
   * <em>unit_type_product_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getUnitTypeProductDiv();


  /** 
   * <em>unit_type_product_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnitTypeProductDivIsSet();


  /** 
   * <em>unit_type_product_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnitTypeProductDivIsModified();


  /** 
   * <em>frgn_new_buy_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getFrgnNewBuyMinAmt();


  /** 
   * <em>frgn_new_buy_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnNewBuyMinAmtIsNull();


  /** 
   * <em>frgn_new_buy_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnNewBuyMinAmtIsSet();


  /** 
   * <em>frgn_new_buy_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnNewBuyMinAmtIsModified();


  /** 
   * <em>frgn_add_buy_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getFrgnAddBuyMinAmt();


  /** 
   * <em>frgn_add_buy_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnAddBuyMinAmtIsNull();


  /** 
   * <em>frgn_add_buy_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnAddBuyMinAmtIsSet();


  /** 
   * <em>frgn_add_buy_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnAddBuyMinAmtIsModified();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getFrgnSellMinAmt();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnSellMinAmtIsNull();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellMinAmtIsSet();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellMinAmtIsModified();


  /** 
   * <em>frgn_new_buy_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getFrgnNewBuyUnitAmt();


  /** 
   * <em>frgn_new_buy_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnNewBuyUnitAmtIsNull();


  /** 
   * <em>frgn_new_buy_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnNewBuyUnitAmtIsSet();


  /** 
   * <em>frgn_new_buy_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnNewBuyUnitAmtIsModified();


  /** 
   * <em>frgn_add_buy_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getFrgnAddBuyUnitAmt();


  /** 
   * <em>frgn_add_buy_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnAddBuyUnitAmtIsNull();


  /** 
   * <em>frgn_add_buy_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnAddBuyUnitAmtIsSet();


  /** 
   * <em>frgn_add_buy_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnAddBuyUnitAmtIsModified();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getFrgnSellUnitAmt();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnSellUnitAmtIsNull();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellUnitAmtIsSet();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellUnitAmtIsModified();


  /** 
   * <em>recruit_commission_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitCommissionDiv();


  /** 
   * <em>recruit_commission_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitCommissionDivIsSet();


  /** 
   * <em>recruit_commission_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitCommissionDivIsModified();


  /** 
   * <em>open_close_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOpenCloseDiv();


  /** 
   * <em>open_close_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenCloseDivIsSet();


  /** 
   * <em>open_close_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenCloseDivIsModified();


}
@
