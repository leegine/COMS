head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundProductSonarRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFundProductSonarRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>mutual_fund_product_sonar</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link MutualFundProductSonarRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundProductSonarPK 
 */
public interface MutualFundProductSonarRow extends Row {


  /** 
   * ����{@@link MutualFundProductSonarRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_product_sonar", "master" );


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
   * <em>product_name_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductNameKana();


  /** 
   * <em>product_name_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanaIsSet();


  /** 
   * <em>product_name_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanaIsModified();


  /** 
   * <em>product_name_kanji</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductNameKanji();


  /** 
   * <em>product_name_kanji</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanjiIsSet();


  /** 
   * <em>product_name_kanji</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanjiIsModified();


  /** 
   * <em>product_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductDiv();


  /** 
   * <em>product_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductDivIsSet();


  /** 
   * <em>product_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductDivIsModified();


  /** 
   * <em>work_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getWorkDate();


  /** 
   * <em>work_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWorkDateIsSet();


  /** 
   * <em>work_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWorkDateIsModified();


  /** 
   * <em>effect_generating_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getEffectGeneratingDate();


  /** 
   * <em>effect_generating_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEffectGeneratingDateIsSet();


  /** 
   * <em>effect_generating_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEffectGeneratingDateIsModified();


  /** 
   * <em>invalid_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getInvalidDate();


  /** 
   * <em>invalid_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvalidDateIsSet();


  /** 
   * <em>invalid_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInvalidDateIsModified();


  /** 
   * <em>closing_date1</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getClosingDate1();


  /** 
   * <em>closing_date1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingDate1IsSet();


  /** 
   * <em>closing_date1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingDate1IsModified();


  /** 
   * <em>closing_date2</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getClosingDate2();


  /** 
   * <em>closing_date2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingDate2IsSet();


  /** 
   * <em>closing_date2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingDate2IsModified();


  /** 
   * <em>redemption_extend_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRedemptionExtendDiv();


  /** 
   * <em>redemption_extend_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionExtendDivIsSet();


  /** 
   * <em>redemption_extend_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionExtendDivIsModified();


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
   * <em>first_recruitment_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getFirstRecruitmentDate();


  /** 
   * <em>first_recruitment_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFirstRecruitmentDateIsSet();


  /** 
   * <em>first_recruitment_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFirstRecruitmentDateIsModified();


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
   * <em>recruit_price</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRecruitPrice();


  /** 
   * <em>recruit_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitPriceIsNull();


  /** 
   * <em>recruit_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitPriceIsSet();


  /** 
   * <em>recruit_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitPriceIsModified();


  /** 
   * <em>payment_start_date1</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPaymentStartDate1();


  /** 
   * <em>payment_start_date1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentStartDate1IsSet();


  /** 
   * <em>payment_start_date1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentStartDate1IsModified();


  /** 
   * <em>payment_start_date2</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPaymentStartDate2();


  /** 
   * <em>payment_start_date2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentStartDate2IsSet();


  /** 
   * <em>payment_start_date2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaymentStartDate2IsModified();


  /** 
   * <em>storage_stop_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStorageStopFlag();


  /** 
   * <em>storage_stop_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStorageStopFlagIsSet();


  /** 
   * <em>storage_stop_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStorageStopFlagIsModified();


  /** 
   * <em>trade_stop_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTradeStopFlag();


  /** 
   * <em>trade_stop_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradeStopFlagIsSet();


  /** 
   * <em>trade_stop_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradeStopFlagIsModified();


  /** 
   * <em>obliterate_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getObliterateType();


  /** 
   * <em>obliterate_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getObliterateTypeIsSet();


  /** 
   * <em>obliterate_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getObliterateTypeIsModified();


  /** 
   * <em>corpus_price</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getCorpusPrice();


  /** 
   * <em>corpus_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCorpusPriceIsNull();


  /** 
   * <em>corpus_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorpusPriceIsSet();


  /** 
   * <em>corpus_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCorpusPriceIsModified();


  /** 
   * <em>open_close_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOpenCloseType();


  /** 
   * <em>open_close_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenCloseTypeIsSet();


  /** 
   * <em>open_close_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenCloseTypeIsModified();


  /** 
   * <em>dayreport_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDayreportProductCode();


  /** 
   * <em>dayreport_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayreportProductCodeIsSet();


  /** 
   * <em>dayreport_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDayreportProductCodeIsModified();


  /** 
   * <em>recruit_sales</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitSales();


  /** 
   * <em>recruit_sales</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitSalesIsSet();


  /** 
   * <em>recruit_sales</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitSalesIsModified();


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
   * <em>purchs_deduction_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPurchsDeductionStartDate();


  /** 
   * <em>purchs_deduction_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPurchsDeductionStartDateIsSet();


  /** 
   * <em>purchs_deduction_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPurchsDeductionStartDateIsModified();


  /** 
   * <em>spot_closing_date1</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSpotClosingDate1();


  /** 
   * <em>spot_closing_date1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpotClosingDate1IsSet();


  /** 
   * <em>spot_closing_date1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpotClosingDate1IsModified();


  /** 
   * <em>spot_closing_date2</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSpotClosingDate2();


  /** 
   * <em>spot_closing_date2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpotClosingDate2IsSet();


  /** 
   * <em>spot_closing_date2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpotClosingDate2IsModified();


  /** 
   * <em>calc_unit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getCalcUnit();


  /** 
   * <em>calc_unit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCalcUnitIsNull();


  /** 
   * <em>calc_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcUnitIsSet();


  /** 
   * <em>calc_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcUnitIsModified();


  /** 
   * <em>biz_asset_product_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBizAssetProductType();


  /** 
   * <em>biz_asset_product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetProductTypeIsSet();


  /** 
   * <em>biz_asset_product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetProductTypeIsModified();


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getBizAssetEvaluatePrice();


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBizAssetEvaluatePriceIsNull();


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetEvaluatePriceIsSet();


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetEvaluatePriceIsModified();


  /** 
   * <em>profit_balance_confirm_data</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getProfitBalanceConfirmData();


  /** 
   * <em>profit_balance_confirm_data</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitBalanceConfirmDataIsSet();


  /** 
   * <em>profit_balance_confirm_data</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitBalanceConfirmDataIsModified();


  /** 
   * <em>profit_term_quantity</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProfitTermQuantity();


  /** 
   * <em>profit_term_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitTermQuantityIsSet();


  /** 
   * <em>profit_term_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitTermQuantityIsModified();


  /** 
   * <em>general_profit_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getGeneralProfitPrice();


  /** 
   * <em>general_profit_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getGeneralProfitPriceIsNull();


  /** 
   * <em>general_profit_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGeneralProfitPriceIsSet();


  /** 
   * <em>general_profit_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getGeneralProfitPriceIsModified();


  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getSpcprofitDistributionPrice();


  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSpcprofitDistributionPriceIsNull();


  /** 
   * <em>spcprofit_distribution_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpcprofitDistributionPriceIsSet();


  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpcprofitDistributionPriceIsModified();


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTaxinlotsAftertaxPrice();


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTaxinlotsAftertaxPriceIsNull();


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxinlotsAftertaxPriceIsSet();


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxinlotsAftertaxPriceIsModified();


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTaxaggregateAftertaxPrice();


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTaxaggregateAftertaxPriceIsNull();


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxaggregateAftertaxPriceIsSet();


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxaggregateAftertaxPriceIsModified();


  /** 
   * <em>pay_start_date_advanced_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPayStartDateAdvancedDiv();


  /** 
   * <em>pay_start_date_advanced_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPayStartDateAdvancedDivIsSet();


  /** 
   * <em>pay_start_date_advanced_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPayStartDateAdvancedDivIsModified();


  /** 
   * <em>method_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMethodType();


  /** 
   * <em>method_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMethodTypeIsSet();


  /** 
   * <em>method_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMethodTypeIsModified();


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
   * <em>sell_forbidden_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getSellForbiddenDate();


  /** 
   * <em>sell_forbidden_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellForbiddenDateIsSet();


  /** 
   * <em>sell_forbidden_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellForbiddenDateIsModified();


  /** 
   * <em>adding_forbidden_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getAddingForbiddenDate();


  /** 
   * <em>adding_forbidden_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddingForbiddenDateIsSet();


  /** 
   * <em>adding_forbidden_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAddingForbiddenDateIsModified();


  /** 
   * <em>profit_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getProfitStartDate();


  /** 
   * <em>profit_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitStartDateIsSet();


  /** 
   * <em>profit_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitStartDateIsModified();


  /** 
   * <em>best_exception_product_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBestExceptionProductFlag();


  /** 
   * <em>best_exception_product_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBestExceptionProductFlagIsSet();


  /** 
   * <em>best_exception_product_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBestExceptionProductFlagIsModified();


  /** 
   * <em>currency_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCurrencyType();


  /** 
   * <em>currency_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCurrencyTypeIsSet();


  /** 
   * <em>currency_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCurrencyTypeIsModified();


  /** 
   * <em>profit_distribution_regdate</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getProfitDistributionRegdate();


  /** 
   * <em>profit_distribution_regdate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitDistributionRegdateIsSet();


  /** 
   * <em>profit_distribution_regdate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProfitDistributionRegdateIsModified();


  /** 
   * <em>consign_contact_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getConsignContactProductCode();


  /** 
   * <em>consign_contact_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConsignContactProductCodeIsSet();


  /** 
   * <em>consign_contact_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConsignContactProductCodeIsModified();


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
   * <em>trust_bank_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTrustBankCode();


  /** 
   * <em>trust_bank_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrustBankCodeIsSet();


  /** 
   * <em>trust_bank_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTrustBankCodeIsModified();


  /** 
   * <em>consign_institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getConsignInstitutionCode();


  /** 
   * <em>consign_institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConsignInstitutionCodeIsSet();


  /** 
   * <em>consign_institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getConsignInstitutionCodeIsModified();


  /** 
   * <em>average_trust_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAverageTrustPrice();


  /** 
   * <em>average_trust_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAverageTrustPriceIsNull();


  /** 
   * <em>average_trust_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAverageTrustPriceIsSet();


  /** 
   * <em>average_trust_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAverageTrustPriceIsModified();


  /** 
   * <em>same_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSameCheckDiv();


  /** 
   * <em>same_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSameCheckDivIsSet();


  /** 
   * <em>same_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSameCheckDivIsModified();


  /** 
   * <em>same_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSameDiv();


  /** 
   * <em>same_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSameDivIsSet();


  /** 
   * <em>same_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSameDivIsModified();


  /** 
   * <em>recruit_short_swt_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitShortSwtCheckDiv();


  /** 
   * <em>recruit_short_swt_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitShortSwtCheckDivIsSet();


  /** 
   * <em>recruit_short_swt_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitShortSwtCheckDivIsModified();


  /** 
   * <em>buy_short_swt_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyShortSwtCheckDiv();


  /** 
   * <em>buy_short_swt_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyShortSwtCheckDivIsSet();


  /** 
   * <em>buy_short_swt_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyShortSwtCheckDivIsModified();


  /** 
   * <em>sell_short_swt_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellShortSwtCheckDiv();


  /** 
   * <em>sell_short_swt_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellShortSwtCheckDivIsSet();


  /** 
   * <em>sell_short_swt_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellShortSwtCheckDivIsModified();


  /** 
   * <em>recruit_start_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitStartStop();


  /** 
   * <em>recruit_start_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStartStopIsSet();


  /** 
   * <em>recruit_start_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStartStopIsModified();


  /** 
   * <em>collateral_qualified_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCollateralQualifiedDiv();


  /** 
   * <em>collateral_qualified_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralQualifiedDivIsSet();


  /** 
   * <em>collateral_qualified_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralQualifiedDivIsModified();


  /** 
   * <em>collateral_evaluation</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCollateralEvaluation();


  /** 
   * <em>collateral_evaluation</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCollateralEvaluationIsNull();


  /** 
   * <em>collateral_evaluation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralEvaluationIsSet();


  /** 
   * <em>collateral_evaluation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralEvaluationIsModified();


  /** 
   * <em>collateral_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCollateralRatio();


  /** 
   * <em>collateral_ratio</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCollateralRatioIsNull();


  /** 
   * <em>collateral_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralRatioIsSet();


  /** 
   * <em>collateral_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralRatioIsModified();


}
@
