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
filename	MutualFundProductSonarParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mutual_fund_product_sonar�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MutualFundProductSonarRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MutualFundProductSonarParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MutualFundProductSonarParams}��{@@link MutualFundProductSonarRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundProductSonarPK 
 * @@see MutualFundProductSonarRow 
 */
public class MutualFundProductSonarParams extends Params implements MutualFundProductSonarRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mutual_fund_product_sonar";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MutualFundProductSonarRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MutualFundProductSonarRow.TYPE;
  }


  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>product_name_kana</em>�J�����̒l 
   */
  public  String  product_name_kana;

  /** 
   * <em>product_name_kanji</em>�J�����̒l 
   */
  public  String  product_name_kanji;

  /** 
   * <em>product_div</em>�J�����̒l 
   */
  public  String  product_div;

  /** 
   * <em>work_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  work_date;

  /** 
   * <em>effect_generating_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  effect_generating_date;

  /** 
   * <em>invalid_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  invalid_date;

  /** 
   * <em>closing_date1</em>�J�����̒l 
   */
  public  java.sql.Timestamp  closing_date1;

  /** 
   * <em>closing_date2</em>�J�����̒l 
   */
  public  java.sql.Timestamp  closing_date2;

  /** 
   * <em>redemption_extend_div</em>�J�����̒l 
   */
  public  String  redemption_extend_div;

  /** 
   * <em>redemption_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  redemption_date;

  /** 
   * <em>first_recruitment_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  first_recruitment_date;

  /** 
   * <em>recruit_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  recruit_start_date;

  /** 
   * <em>recruit_end_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  recruit_end_date;

  /** 
   * <em>recruit_price</em>�J�����̒l 
   */
  public  Integer  recruit_price;

  /** 
   * <em>payment_start_date1</em>�J�����̒l 
   */
  public  java.sql.Timestamp  payment_start_date1;

  /** 
   * <em>payment_start_date2</em>�J�����̒l 
   */
  public  java.sql.Timestamp  payment_start_date2;

  /** 
   * <em>storage_stop_flag</em>�J�����̒l 
   */
  public  String  storage_stop_flag;

  /** 
   * <em>trade_stop_flag</em>�J�����̒l 
   */
  public  String  trade_stop_flag;

  /** 
   * <em>obliterate_type</em>�J�����̒l 
   */
  public  String  obliterate_type;

  /** 
   * <em>corpus_price</em>�J�����̒l 
   */
  public  Integer  corpus_price;

  /** 
   * <em>open_close_type</em>�J�����̒l 
   */
  public  String  open_close_type;

  /** 
   * <em>dayreport_product_code</em>�J�����̒l 
   */
  public  String  dayreport_product_code;

  /** 
   * <em>recruit_sales</em>�J�����̒l 
   */
  public  String  recruit_sales;

  /** 
   * <em>stock_type_bond_type</em>�J�����̒l 
   */
  public  String  stock_type_bond_type;

  /** 
   * <em>contract_institution_type</em>�J�����̒l 
   */
  public  String  contract_institution_type;

  /** 
   * <em>purchs_deduction_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  purchs_deduction_start_date;

  /** 
   * <em>spot_closing_date1</em>�J�����̒l 
   */
  public  java.sql.Timestamp  spot_closing_date1;

  /** 
   * <em>spot_closing_date2</em>�J�����̒l 
   */
  public  java.sql.Timestamp  spot_closing_date2;

  /** 
   * <em>calc_unit</em>�J�����̒l 
   */
  public  Integer  calc_unit;

  /** 
   * <em>biz_asset_product_type</em>�J�����̒l 
   */
  public  String  biz_asset_product_type;

  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l 
   */
  public  Double  biz_asset_evaluate_price;

  /** 
   * <em>profit_balance_confirm_data</em>�J�����̒l 
   */
  public  java.sql.Timestamp  profit_balance_confirm_data;

  /** 
   * <em>profit_term_quantity</em>�J�����̒l 
   */
  public  String  profit_term_quantity;

  /** 
   * <em>general_profit_price</em>�J�����̒l 
   */
  public  Double  general_profit_price;

  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l 
   */
  public  Double  spcprofit_distribution_price;

  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l 
   */
  public  Double  taxinlots_aftertax_price;

  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l 
   */
  public  Double  taxaggregate_aftertax_price;

  /** 
   * <em>pay_start_date_advanced_div</em>�J�����̒l 
   */
  public  String  pay_start_date_advanced_div;

  /** 
   * <em>method_type</em>�J�����̒l 
   */
  public  String  method_type;

  /** 
   * <em>setting_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  setting_date;

  /** 
   * <em>sell_forbidden_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  sell_forbidden_date;

  /** 
   * <em>adding_forbidden_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  adding_forbidden_date;

  /** 
   * <em>profit_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  profit_start_date;

  /** 
   * <em>best_exception_product_flag</em>�J�����̒l 
   */
  public  String  best_exception_product_flag;

  /** 
   * <em>currency_type</em>�J�����̒l 
   */
  public  String  currency_type;

  /** 
   * <em>profit_distribution_regdate</em>�J�����̒l 
   */
  public  java.sql.Timestamp  profit_distribution_regdate;

  /** 
   * <em>consign_contact_product_code</em>�J�����̒l 
   */
  public  String  consign_contact_product_code;

  /** 
   * <em>mutualassoc_product_code</em>�J�����̒l 
   */
  public  String  mutualassoc_product_code;

  /** 
   * <em>trust_bank_code</em>�J�����̒l 
   */
  public  String  trust_bank_code;

  /** 
   * <em>consign_institution_code</em>�J�����̒l 
   */
  public  String  consign_institution_code;

  /** 
   * <em>average_trust_price</em>�J�����̒l 
   */
  public  Double  average_trust_price;

  /** 
   * <em>same_check_div</em>�J�����̒l 
   */
  public  String  same_check_div;

  /** 
   * <em>same_div</em>�J�����̒l 
   */
  public  String  same_div;

  /** 
   * <em>recruit_short_swt_check_div</em>�J�����̒l 
   */
  public  String  recruit_short_swt_check_div;

  /** 
   * <em>buy_short_swt_check_div</em>�J�����̒l 
   */
  public  String  buy_short_swt_check_div;

  /** 
   * <em>sell_short_swt_check_div</em>�J�����̒l 
   */
  public  String  sell_short_swt_check_div;

  /** 
   * <em>recruit_start_stop</em>�J�����̒l 
   */
  public  String  recruit_start_stop;

  /** 
   * <em>collateral_qualified_div</em>�J�����̒l 
   */
  public  String  collateral_qualified_div;

  /** 
   * <em>collateral_evaluation</em>�J�����̒l 
   */
  public  Double  collateral_evaluation;

  /** 
   * <em>collateral_ratio</em>�J�����̒l 
   */
  public  Double  collateral_ratio;

  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_name_kana_is_set = false;
  private boolean product_name_kana_is_modified = false;
  private boolean product_name_kanji_is_set = false;
  private boolean product_name_kanji_is_modified = false;
  private boolean product_div_is_set = false;
  private boolean product_div_is_modified = false;
  private boolean work_date_is_set = false;
  private boolean work_date_is_modified = false;
  private boolean effect_generating_date_is_set = false;
  private boolean effect_generating_date_is_modified = false;
  private boolean invalid_date_is_set = false;
  private boolean invalid_date_is_modified = false;
  private boolean closing_date1_is_set = false;
  private boolean closing_date1_is_modified = false;
  private boolean closing_date2_is_set = false;
  private boolean closing_date2_is_modified = false;
  private boolean redemption_extend_div_is_set = false;
  private boolean redemption_extend_div_is_modified = false;
  private boolean redemption_date_is_set = false;
  private boolean redemption_date_is_modified = false;
  private boolean first_recruitment_date_is_set = false;
  private boolean first_recruitment_date_is_modified = false;
  private boolean recruit_start_date_is_set = false;
  private boolean recruit_start_date_is_modified = false;
  private boolean recruit_end_date_is_set = false;
  private boolean recruit_end_date_is_modified = false;
  private boolean recruit_price_is_set = false;
  private boolean recruit_price_is_modified = false;
  private boolean payment_start_date1_is_set = false;
  private boolean payment_start_date1_is_modified = false;
  private boolean payment_start_date2_is_set = false;
  private boolean payment_start_date2_is_modified = false;
  private boolean storage_stop_flag_is_set = false;
  private boolean storage_stop_flag_is_modified = false;
  private boolean trade_stop_flag_is_set = false;
  private boolean trade_stop_flag_is_modified = false;
  private boolean obliterate_type_is_set = false;
  private boolean obliterate_type_is_modified = false;
  private boolean corpus_price_is_set = false;
  private boolean corpus_price_is_modified = false;
  private boolean open_close_type_is_set = false;
  private boolean open_close_type_is_modified = false;
  private boolean dayreport_product_code_is_set = false;
  private boolean dayreport_product_code_is_modified = false;
  private boolean recruit_sales_is_set = false;
  private boolean recruit_sales_is_modified = false;
  private boolean stock_type_bond_type_is_set = false;
  private boolean stock_type_bond_type_is_modified = false;
  private boolean contract_institution_type_is_set = false;
  private boolean contract_institution_type_is_modified = false;
  private boolean purchs_deduction_start_date_is_set = false;
  private boolean purchs_deduction_start_date_is_modified = false;
  private boolean spot_closing_date1_is_set = false;
  private boolean spot_closing_date1_is_modified = false;
  private boolean spot_closing_date2_is_set = false;
  private boolean spot_closing_date2_is_modified = false;
  private boolean calc_unit_is_set = false;
  private boolean calc_unit_is_modified = false;
  private boolean biz_asset_product_type_is_set = false;
  private boolean biz_asset_product_type_is_modified = false;
  private boolean biz_asset_evaluate_price_is_set = false;
  private boolean biz_asset_evaluate_price_is_modified = false;
  private boolean profit_balance_confirm_data_is_set = false;
  private boolean profit_balance_confirm_data_is_modified = false;
  private boolean profit_term_quantity_is_set = false;
  private boolean profit_term_quantity_is_modified = false;
  private boolean general_profit_price_is_set = false;
  private boolean general_profit_price_is_modified = false;
  private boolean spcprofit_distribution_price_is_set = false;
  private boolean spcprofit_distribution_price_is_modified = false;
  private boolean taxinlots_aftertax_price_is_set = false;
  private boolean taxinlots_aftertax_price_is_modified = false;
  private boolean taxaggregate_aftertax_price_is_set = false;
  private boolean taxaggregate_aftertax_price_is_modified = false;
  private boolean pay_start_date_advanced_div_is_set = false;
  private boolean pay_start_date_advanced_div_is_modified = false;
  private boolean method_type_is_set = false;
  private boolean method_type_is_modified = false;
  private boolean setting_date_is_set = false;
  private boolean setting_date_is_modified = false;
  private boolean sell_forbidden_date_is_set = false;
  private boolean sell_forbidden_date_is_modified = false;
  private boolean adding_forbidden_date_is_set = false;
  private boolean adding_forbidden_date_is_modified = false;
  private boolean profit_start_date_is_set = false;
  private boolean profit_start_date_is_modified = false;
  private boolean best_exception_product_flag_is_set = false;
  private boolean best_exception_product_flag_is_modified = false;
  private boolean currency_type_is_set = false;
  private boolean currency_type_is_modified = false;
  private boolean profit_distribution_regdate_is_set = false;
  private boolean profit_distribution_regdate_is_modified = false;
  private boolean consign_contact_product_code_is_set = false;
  private boolean consign_contact_product_code_is_modified = false;
  private boolean mutualassoc_product_code_is_set = false;
  private boolean mutualassoc_product_code_is_modified = false;
  private boolean trust_bank_code_is_set = false;
  private boolean trust_bank_code_is_modified = false;
  private boolean consign_institution_code_is_set = false;
  private boolean consign_institution_code_is_modified = false;
  private boolean average_trust_price_is_set = false;
  private boolean average_trust_price_is_modified = false;
  private boolean same_check_div_is_set = false;
  private boolean same_check_div_is_modified = false;
  private boolean same_div_is_set = false;
  private boolean same_div_is_modified = false;
  private boolean recruit_short_swt_check_div_is_set = false;
  private boolean recruit_short_swt_check_div_is_modified = false;
  private boolean buy_short_swt_check_div_is_set = false;
  private boolean buy_short_swt_check_div_is_modified = false;
  private boolean sell_short_swt_check_div_is_set = false;
  private boolean sell_short_swt_check_div_is_modified = false;
  private boolean recruit_start_stop_is_set = false;
  private boolean recruit_start_stop_is_modified = false;
  private boolean collateral_qualified_div_is_set = false;
  private boolean collateral_qualified_div_is_modified = false;
  private boolean collateral_evaluation_is_set = false;
  private boolean collateral_evaluation_is_modified = false;
  private boolean collateral_ratio_is_set = false;
  private boolean collateral_ratio_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "product_code=" + product_code
      + "," + "product_name_kana=" +product_name_kana
      + "," + "product_name_kanji=" +product_name_kanji
      + "," + "product_div=" +product_div
      + "," + "work_date=" +work_date
      + "," + "effect_generating_date=" +effect_generating_date
      + "," + "invalid_date=" +invalid_date
      + "," + "closing_date1=" +closing_date1
      + "," + "closing_date2=" +closing_date2
      + "," + "redemption_extend_div=" +redemption_extend_div
      + "," + "redemption_date=" +redemption_date
      + "," + "first_recruitment_date=" +first_recruitment_date
      + "," + "recruit_start_date=" +recruit_start_date
      + "," + "recruit_end_date=" +recruit_end_date
      + "," + "recruit_price=" +recruit_price
      + "," + "payment_start_date1=" +payment_start_date1
      + "," + "payment_start_date2=" +payment_start_date2
      + "," + "storage_stop_flag=" +storage_stop_flag
      + "," + "trade_stop_flag=" +trade_stop_flag
      + "," + "obliterate_type=" +obliterate_type
      + "," + "corpus_price=" +corpus_price
      + "," + "open_close_type=" +open_close_type
      + "," + "dayreport_product_code=" +dayreport_product_code
      + "," + "recruit_sales=" +recruit_sales
      + "," + "stock_type_bond_type=" +stock_type_bond_type
      + "," + "contract_institution_type=" +contract_institution_type
      + "," + "purchs_deduction_start_date=" +purchs_deduction_start_date
      + "," + "spot_closing_date1=" +spot_closing_date1
      + "," + "spot_closing_date2=" +spot_closing_date2
      + "," + "calc_unit=" +calc_unit
      + "," + "biz_asset_product_type=" +biz_asset_product_type
      + "," + "biz_asset_evaluate_price=" +biz_asset_evaluate_price
      + "," + "profit_balance_confirm_data=" +profit_balance_confirm_data
      + "," + "profit_term_quantity=" +profit_term_quantity
      + "," + "general_profit_price=" +general_profit_price
      + "," + "spcprofit_distribution_price=" +spcprofit_distribution_price
      + "," + "taxinlots_aftertax_price=" +taxinlots_aftertax_price
      + "," + "taxaggregate_aftertax_price=" +taxaggregate_aftertax_price
      + "," + "pay_start_date_advanced_div=" +pay_start_date_advanced_div
      + "," + "method_type=" +method_type
      + "," + "setting_date=" +setting_date
      + "," + "sell_forbidden_date=" +sell_forbidden_date
      + "," + "adding_forbidden_date=" +adding_forbidden_date
      + "," + "profit_start_date=" +profit_start_date
      + "," + "best_exception_product_flag=" +best_exception_product_flag
      + "," + "currency_type=" +currency_type
      + "," + "profit_distribution_regdate=" +profit_distribution_regdate
      + "," + "consign_contact_product_code=" +consign_contact_product_code
      + "," + "mutualassoc_product_code=" +mutualassoc_product_code
      + "," + "trust_bank_code=" +trust_bank_code
      + "," + "consign_institution_code=" +consign_institution_code
      + "," + "average_trust_price=" +average_trust_price
      + "," + "same_check_div=" +same_check_div
      + "," + "same_div=" +same_div
      + "," + "recruit_short_swt_check_div=" +recruit_short_swt_check_div
      + "," + "buy_short_swt_check_div=" +buy_short_swt_check_div
      + "," + "sell_short_swt_check_div=" +sell_short_swt_check_div
      + "," + "recruit_start_stop=" +recruit_start_stop
      + "," + "collateral_qualified_div=" +collateral_qualified_div
      + "," + "collateral_evaluation=" +collateral_evaluation
      + "," + "collateral_ratio=" +collateral_ratio
      + "}";
  }


  /** 
   * �l�����ݒ��MutualFundProductSonarParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MutualFundProductSonarParams() {
    product_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MutualFundProductSonarRow�I�u�W�F�N�g�̒l�𗘗p����MutualFundProductSonarParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MutualFundProductSonarRow�I�u�W�F�N�g 
   */
  public MutualFundProductSonarParams( MutualFundProductSonarRow p_row )
  {
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_name_kana = p_row.getProductNameKana();
    product_name_kana_is_set = p_row.getProductNameKanaIsSet();
    product_name_kana_is_modified = p_row.getProductNameKanaIsModified();
    product_name_kanji = p_row.getProductNameKanji();
    product_name_kanji_is_set = p_row.getProductNameKanjiIsSet();
    product_name_kanji_is_modified = p_row.getProductNameKanjiIsModified();
    product_div = p_row.getProductDiv();
    product_div_is_set = p_row.getProductDivIsSet();
    product_div_is_modified = p_row.getProductDivIsModified();
    work_date = p_row.getWorkDate();
    work_date_is_set = p_row.getWorkDateIsSet();
    work_date_is_modified = p_row.getWorkDateIsModified();
    effect_generating_date = p_row.getEffectGeneratingDate();
    effect_generating_date_is_set = p_row.getEffectGeneratingDateIsSet();
    effect_generating_date_is_modified = p_row.getEffectGeneratingDateIsModified();
    invalid_date = p_row.getInvalidDate();
    invalid_date_is_set = p_row.getInvalidDateIsSet();
    invalid_date_is_modified = p_row.getInvalidDateIsModified();
    closing_date1 = p_row.getClosingDate1();
    closing_date1_is_set = p_row.getClosingDate1IsSet();
    closing_date1_is_modified = p_row.getClosingDate1IsModified();
    closing_date2 = p_row.getClosingDate2();
    closing_date2_is_set = p_row.getClosingDate2IsSet();
    closing_date2_is_modified = p_row.getClosingDate2IsModified();
    redemption_extend_div = p_row.getRedemptionExtendDiv();
    redemption_extend_div_is_set = p_row.getRedemptionExtendDivIsSet();
    redemption_extend_div_is_modified = p_row.getRedemptionExtendDivIsModified();
    redemption_date = p_row.getRedemptionDate();
    redemption_date_is_set = p_row.getRedemptionDateIsSet();
    redemption_date_is_modified = p_row.getRedemptionDateIsModified();
    first_recruitment_date = p_row.getFirstRecruitmentDate();
    first_recruitment_date_is_set = p_row.getFirstRecruitmentDateIsSet();
    first_recruitment_date_is_modified = p_row.getFirstRecruitmentDateIsModified();
    recruit_start_date = p_row.getRecruitStartDate();
    recruit_start_date_is_set = p_row.getRecruitStartDateIsSet();
    recruit_start_date_is_modified = p_row.getRecruitStartDateIsModified();
    recruit_end_date = p_row.getRecruitEndDate();
    recruit_end_date_is_set = p_row.getRecruitEndDateIsSet();
    recruit_end_date_is_modified = p_row.getRecruitEndDateIsModified();
    if ( !p_row.getRecruitPriceIsNull() )
      recruit_price = new Integer( p_row.getRecruitPrice() );
    recruit_price_is_set = p_row.getRecruitPriceIsSet();
    recruit_price_is_modified = p_row.getRecruitPriceIsModified();
    payment_start_date1 = p_row.getPaymentStartDate1();
    payment_start_date1_is_set = p_row.getPaymentStartDate1IsSet();
    payment_start_date1_is_modified = p_row.getPaymentStartDate1IsModified();
    payment_start_date2 = p_row.getPaymentStartDate2();
    payment_start_date2_is_set = p_row.getPaymentStartDate2IsSet();
    payment_start_date2_is_modified = p_row.getPaymentStartDate2IsModified();
    storage_stop_flag = p_row.getStorageStopFlag();
    storage_stop_flag_is_set = p_row.getStorageStopFlagIsSet();
    storage_stop_flag_is_modified = p_row.getStorageStopFlagIsModified();
    trade_stop_flag = p_row.getTradeStopFlag();
    trade_stop_flag_is_set = p_row.getTradeStopFlagIsSet();
    trade_stop_flag_is_modified = p_row.getTradeStopFlagIsModified();
    obliterate_type = p_row.getObliterateType();
    obliterate_type_is_set = p_row.getObliterateTypeIsSet();
    obliterate_type_is_modified = p_row.getObliterateTypeIsModified();
    if ( !p_row.getCorpusPriceIsNull() )
      corpus_price = new Integer( p_row.getCorpusPrice() );
    corpus_price_is_set = p_row.getCorpusPriceIsSet();
    corpus_price_is_modified = p_row.getCorpusPriceIsModified();
    open_close_type = p_row.getOpenCloseType();
    open_close_type_is_set = p_row.getOpenCloseTypeIsSet();
    open_close_type_is_modified = p_row.getOpenCloseTypeIsModified();
    dayreport_product_code = p_row.getDayreportProductCode();
    dayreport_product_code_is_set = p_row.getDayreportProductCodeIsSet();
    dayreport_product_code_is_modified = p_row.getDayreportProductCodeIsModified();
    recruit_sales = p_row.getRecruitSales();
    recruit_sales_is_set = p_row.getRecruitSalesIsSet();
    recruit_sales_is_modified = p_row.getRecruitSalesIsModified();
    stock_type_bond_type = p_row.getStockTypeBondType();
    stock_type_bond_type_is_set = p_row.getStockTypeBondTypeIsSet();
    stock_type_bond_type_is_modified = p_row.getStockTypeBondTypeIsModified();
    contract_institution_type = p_row.getContractInstitutionType();
    contract_institution_type_is_set = p_row.getContractInstitutionTypeIsSet();
    contract_institution_type_is_modified = p_row.getContractInstitutionTypeIsModified();
    purchs_deduction_start_date = p_row.getPurchsDeductionStartDate();
    purchs_deduction_start_date_is_set = p_row.getPurchsDeductionStartDateIsSet();
    purchs_deduction_start_date_is_modified = p_row.getPurchsDeductionStartDateIsModified();
    spot_closing_date1 = p_row.getSpotClosingDate1();
    spot_closing_date1_is_set = p_row.getSpotClosingDate1IsSet();
    spot_closing_date1_is_modified = p_row.getSpotClosingDate1IsModified();
    spot_closing_date2 = p_row.getSpotClosingDate2();
    spot_closing_date2_is_set = p_row.getSpotClosingDate2IsSet();
    spot_closing_date2_is_modified = p_row.getSpotClosingDate2IsModified();
    if ( !p_row.getCalcUnitIsNull() )
      calc_unit = new Integer( p_row.getCalcUnit() );
    calc_unit_is_set = p_row.getCalcUnitIsSet();
    calc_unit_is_modified = p_row.getCalcUnitIsModified();
    biz_asset_product_type = p_row.getBizAssetProductType();
    biz_asset_product_type_is_set = p_row.getBizAssetProductTypeIsSet();
    biz_asset_product_type_is_modified = p_row.getBizAssetProductTypeIsModified();
    if ( !p_row.getBizAssetEvaluatePriceIsNull() )
      biz_asset_evaluate_price = new Double( p_row.getBizAssetEvaluatePrice() );
    biz_asset_evaluate_price_is_set = p_row.getBizAssetEvaluatePriceIsSet();
    biz_asset_evaluate_price_is_modified = p_row.getBizAssetEvaluatePriceIsModified();
    profit_balance_confirm_data = p_row.getProfitBalanceConfirmData();
    profit_balance_confirm_data_is_set = p_row.getProfitBalanceConfirmDataIsSet();
    profit_balance_confirm_data_is_modified = p_row.getProfitBalanceConfirmDataIsModified();
    profit_term_quantity = p_row.getProfitTermQuantity();
    profit_term_quantity_is_set = p_row.getProfitTermQuantityIsSet();
    profit_term_quantity_is_modified = p_row.getProfitTermQuantityIsModified();
    if ( !p_row.getGeneralProfitPriceIsNull() )
      general_profit_price = new Double( p_row.getGeneralProfitPrice() );
    general_profit_price_is_set = p_row.getGeneralProfitPriceIsSet();
    general_profit_price_is_modified = p_row.getGeneralProfitPriceIsModified();
    if ( !p_row.getSpcprofitDistributionPriceIsNull() )
      spcprofit_distribution_price = new Double( p_row.getSpcprofitDistributionPrice() );
    spcprofit_distribution_price_is_set = p_row.getSpcprofitDistributionPriceIsSet();
    spcprofit_distribution_price_is_modified = p_row.getSpcprofitDistributionPriceIsModified();
    if ( !p_row.getTaxinlotsAftertaxPriceIsNull() )
      taxinlots_aftertax_price = new Double( p_row.getTaxinlotsAftertaxPrice() );
    taxinlots_aftertax_price_is_set = p_row.getTaxinlotsAftertaxPriceIsSet();
    taxinlots_aftertax_price_is_modified = p_row.getTaxinlotsAftertaxPriceIsModified();
    if ( !p_row.getTaxaggregateAftertaxPriceIsNull() )
      taxaggregate_aftertax_price = new Double( p_row.getTaxaggregateAftertaxPrice() );
    taxaggregate_aftertax_price_is_set = p_row.getTaxaggregateAftertaxPriceIsSet();
    taxaggregate_aftertax_price_is_modified = p_row.getTaxaggregateAftertaxPriceIsModified();
    pay_start_date_advanced_div = p_row.getPayStartDateAdvancedDiv();
    pay_start_date_advanced_div_is_set = p_row.getPayStartDateAdvancedDivIsSet();
    pay_start_date_advanced_div_is_modified = p_row.getPayStartDateAdvancedDivIsModified();
    method_type = p_row.getMethodType();
    method_type_is_set = p_row.getMethodTypeIsSet();
    method_type_is_modified = p_row.getMethodTypeIsModified();
    setting_date = p_row.getSettingDate();
    setting_date_is_set = p_row.getSettingDateIsSet();
    setting_date_is_modified = p_row.getSettingDateIsModified();
    sell_forbidden_date = p_row.getSellForbiddenDate();
    sell_forbidden_date_is_set = p_row.getSellForbiddenDateIsSet();
    sell_forbidden_date_is_modified = p_row.getSellForbiddenDateIsModified();
    adding_forbidden_date = p_row.getAddingForbiddenDate();
    adding_forbidden_date_is_set = p_row.getAddingForbiddenDateIsSet();
    adding_forbidden_date_is_modified = p_row.getAddingForbiddenDateIsModified();
    profit_start_date = p_row.getProfitStartDate();
    profit_start_date_is_set = p_row.getProfitStartDateIsSet();
    profit_start_date_is_modified = p_row.getProfitStartDateIsModified();
    best_exception_product_flag = p_row.getBestExceptionProductFlag();
    best_exception_product_flag_is_set = p_row.getBestExceptionProductFlagIsSet();
    best_exception_product_flag_is_modified = p_row.getBestExceptionProductFlagIsModified();
    currency_type = p_row.getCurrencyType();
    currency_type_is_set = p_row.getCurrencyTypeIsSet();
    currency_type_is_modified = p_row.getCurrencyTypeIsModified();
    profit_distribution_regdate = p_row.getProfitDistributionRegdate();
    profit_distribution_regdate_is_set = p_row.getProfitDistributionRegdateIsSet();
    profit_distribution_regdate_is_modified = p_row.getProfitDistributionRegdateIsModified();
    consign_contact_product_code = p_row.getConsignContactProductCode();
    consign_contact_product_code_is_set = p_row.getConsignContactProductCodeIsSet();
    consign_contact_product_code_is_modified = p_row.getConsignContactProductCodeIsModified();
    mutualassoc_product_code = p_row.getMutualassocProductCode();
    mutualassoc_product_code_is_set = p_row.getMutualassocProductCodeIsSet();
    mutualassoc_product_code_is_modified = p_row.getMutualassocProductCodeIsModified();
    trust_bank_code = p_row.getTrustBankCode();
    trust_bank_code_is_set = p_row.getTrustBankCodeIsSet();
    trust_bank_code_is_modified = p_row.getTrustBankCodeIsModified();
    consign_institution_code = p_row.getConsignInstitutionCode();
    consign_institution_code_is_set = p_row.getConsignInstitutionCodeIsSet();
    consign_institution_code_is_modified = p_row.getConsignInstitutionCodeIsModified();
    if ( !p_row.getAverageTrustPriceIsNull() )
      average_trust_price = new Double( p_row.getAverageTrustPrice() );
    average_trust_price_is_set = p_row.getAverageTrustPriceIsSet();
    average_trust_price_is_modified = p_row.getAverageTrustPriceIsModified();
    same_check_div = p_row.getSameCheckDiv();
    same_check_div_is_set = p_row.getSameCheckDivIsSet();
    same_check_div_is_modified = p_row.getSameCheckDivIsModified();
    same_div = p_row.getSameDiv();
    same_div_is_set = p_row.getSameDivIsSet();
    same_div_is_modified = p_row.getSameDivIsModified();
    recruit_short_swt_check_div = p_row.getRecruitShortSwtCheckDiv();
    recruit_short_swt_check_div_is_set = p_row.getRecruitShortSwtCheckDivIsSet();
    recruit_short_swt_check_div_is_modified = p_row.getRecruitShortSwtCheckDivIsModified();
    buy_short_swt_check_div = p_row.getBuyShortSwtCheckDiv();
    buy_short_swt_check_div_is_set = p_row.getBuyShortSwtCheckDivIsSet();
    buy_short_swt_check_div_is_modified = p_row.getBuyShortSwtCheckDivIsModified();
    sell_short_swt_check_div = p_row.getSellShortSwtCheckDiv();
    sell_short_swt_check_div_is_set = p_row.getSellShortSwtCheckDivIsSet();
    sell_short_swt_check_div_is_modified = p_row.getSellShortSwtCheckDivIsModified();
    recruit_start_stop = p_row.getRecruitStartStop();
    recruit_start_stop_is_set = p_row.getRecruitStartStopIsSet();
    recruit_start_stop_is_modified = p_row.getRecruitStartStopIsModified();
    collateral_qualified_div = p_row.getCollateralQualifiedDiv();
    collateral_qualified_div_is_set = p_row.getCollateralQualifiedDivIsSet();
    collateral_qualified_div_is_modified = p_row.getCollateralQualifiedDivIsModified();
    if ( !p_row.getCollateralEvaluationIsNull() )
      collateral_evaluation = new Double( p_row.getCollateralEvaluation() );
    collateral_evaluation_is_set = p_row.getCollateralEvaluationIsSet();
    collateral_evaluation_is_modified = p_row.getCollateralEvaluationIsModified();
    if ( !p_row.getCollateralRatioIsNull() )
      collateral_ratio = new Double( p_row.getCollateralRatio() );
    collateral_ratio_is_set = p_row.getCollateralRatioIsSet();
    collateral_ratio_is_modified = p_row.getCollateralRatioIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      product_name_kana_is_set = true;
      product_name_kana_is_modified = true;
      product_name_kanji_is_set = true;
      product_name_kanji_is_modified = true;
      product_div_is_set = true;
      product_div_is_modified = true;
      work_date_is_set = true;
      work_date_is_modified = true;
      effect_generating_date_is_set = true;
      effect_generating_date_is_modified = true;
      invalid_date_is_set = true;
      invalid_date_is_modified = true;
      closing_date1_is_set = true;
      closing_date1_is_modified = true;
      closing_date2_is_set = true;
      closing_date2_is_modified = true;
      redemption_extend_div_is_set = true;
      redemption_extend_div_is_modified = true;
      redemption_date_is_set = true;
      redemption_date_is_modified = true;
      first_recruitment_date_is_set = true;
      first_recruitment_date_is_modified = true;
      recruit_start_date_is_set = true;
      recruit_start_date_is_modified = true;
      recruit_end_date_is_set = true;
      recruit_end_date_is_modified = true;
      recruit_price_is_set = true;
      recruit_price_is_modified = true;
      payment_start_date1_is_set = true;
      payment_start_date1_is_modified = true;
      payment_start_date2_is_set = true;
      payment_start_date2_is_modified = true;
      storage_stop_flag_is_set = true;
      storage_stop_flag_is_modified = true;
      trade_stop_flag_is_set = true;
      trade_stop_flag_is_modified = true;
      obliterate_type_is_set = true;
      obliterate_type_is_modified = true;
      corpus_price_is_set = true;
      corpus_price_is_modified = true;
      open_close_type_is_set = true;
      open_close_type_is_modified = true;
      dayreport_product_code_is_set = true;
      dayreport_product_code_is_modified = true;
      recruit_sales_is_set = true;
      recruit_sales_is_modified = true;
      stock_type_bond_type_is_set = true;
      stock_type_bond_type_is_modified = true;
      contract_institution_type_is_set = true;
      contract_institution_type_is_modified = true;
      purchs_deduction_start_date_is_set = true;
      purchs_deduction_start_date_is_modified = true;
      spot_closing_date1_is_set = true;
      spot_closing_date1_is_modified = true;
      spot_closing_date2_is_set = true;
      spot_closing_date2_is_modified = true;
      calc_unit_is_set = true;
      calc_unit_is_modified = true;
      biz_asset_product_type_is_set = true;
      biz_asset_product_type_is_modified = true;
      biz_asset_evaluate_price_is_set = true;
      biz_asset_evaluate_price_is_modified = true;
      profit_balance_confirm_data_is_set = true;
      profit_balance_confirm_data_is_modified = true;
      profit_term_quantity_is_set = true;
      profit_term_quantity_is_modified = true;
      general_profit_price_is_set = true;
      general_profit_price_is_modified = true;
      spcprofit_distribution_price_is_set = true;
      spcprofit_distribution_price_is_modified = true;
      taxinlots_aftertax_price_is_set = true;
      taxinlots_aftertax_price_is_modified = true;
      taxaggregate_aftertax_price_is_set = true;
      taxaggregate_aftertax_price_is_modified = true;
      pay_start_date_advanced_div_is_set = true;
      pay_start_date_advanced_div_is_modified = true;
      method_type_is_set = true;
      method_type_is_modified = true;
      setting_date_is_set = true;
      setting_date_is_modified = true;
      sell_forbidden_date_is_set = true;
      sell_forbidden_date_is_modified = true;
      adding_forbidden_date_is_set = true;
      adding_forbidden_date_is_modified = true;
      profit_start_date_is_set = true;
      profit_start_date_is_modified = true;
      best_exception_product_flag_is_set = true;
      best_exception_product_flag_is_modified = true;
      currency_type_is_set = true;
      currency_type_is_modified = true;
      profit_distribution_regdate_is_set = true;
      profit_distribution_regdate_is_modified = true;
      consign_contact_product_code_is_set = true;
      consign_contact_product_code_is_modified = true;
      mutualassoc_product_code_is_set = true;
      mutualassoc_product_code_is_modified = true;
      trust_bank_code_is_set = true;
      trust_bank_code_is_modified = true;
      consign_institution_code_is_set = true;
      consign_institution_code_is_modified = true;
      average_trust_price_is_set = true;
      average_trust_price_is_modified = true;
      same_check_div_is_set = true;
      same_check_div_is_modified = true;
      same_div_is_set = true;
      same_div_is_modified = true;
      recruit_short_swt_check_div_is_set = true;
      recruit_short_swt_check_div_is_modified = true;
      buy_short_swt_check_div_is_set = true;
      buy_short_swt_check_div_is_modified = true;
      sell_short_swt_check_div_is_set = true;
      sell_short_swt_check_div_is_modified = true;
      recruit_start_stop_is_set = true;
      recruit_start_stop_is_modified = true;
      collateral_qualified_div_is_set = true;
      collateral_qualified_div_is_modified = true;
      collateral_evaluation_is_set = true;
      collateral_evaluation_is_modified = true;
      collateral_ratio_is_set = true;
      collateral_ratio_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MutualFundProductSonarRow ) )
       return false;
    return fieldsEqual( (MutualFundProductSonarRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MutualFundProductSonarRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MutualFundProductSonarRow row )
  {
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( product_name_kana == null ) {
      if ( row.getProductNameKana() != null )
        return false;
    } else if ( !product_name_kana.equals( row.getProductNameKana() ) ) {
        return false;
    }
    if ( product_name_kanji == null ) {
      if ( row.getProductNameKanji() != null )
        return false;
    } else if ( !product_name_kanji.equals( row.getProductNameKanji() ) ) {
        return false;
    }
    if ( product_div == null ) {
      if ( row.getProductDiv() != null )
        return false;
    } else if ( !product_div.equals( row.getProductDiv() ) ) {
        return false;
    }
    if ( work_date == null ) {
      if ( row.getWorkDate() != null )
        return false;
    } else if ( !work_date.equals( row.getWorkDate() ) ) {
        return false;
    }
    if ( effect_generating_date == null ) {
      if ( row.getEffectGeneratingDate() != null )
        return false;
    } else if ( !effect_generating_date.equals( row.getEffectGeneratingDate() ) ) {
        return false;
    }
    if ( invalid_date == null ) {
      if ( row.getInvalidDate() != null )
        return false;
    } else if ( !invalid_date.equals( row.getInvalidDate() ) ) {
        return false;
    }
    if ( closing_date1 == null ) {
      if ( row.getClosingDate1() != null )
        return false;
    } else if ( !closing_date1.equals( row.getClosingDate1() ) ) {
        return false;
    }
    if ( closing_date2 == null ) {
      if ( row.getClosingDate2() != null )
        return false;
    } else if ( !closing_date2.equals( row.getClosingDate2() ) ) {
        return false;
    }
    if ( redemption_extend_div == null ) {
      if ( row.getRedemptionExtendDiv() != null )
        return false;
    } else if ( !redemption_extend_div.equals( row.getRedemptionExtendDiv() ) ) {
        return false;
    }
    if ( redemption_date == null ) {
      if ( row.getRedemptionDate() != null )
        return false;
    } else if ( !redemption_date.equals( row.getRedemptionDate() ) ) {
        return false;
    }
    if ( first_recruitment_date == null ) {
      if ( row.getFirstRecruitmentDate() != null )
        return false;
    } else if ( !first_recruitment_date.equals( row.getFirstRecruitmentDate() ) ) {
        return false;
    }
    if ( recruit_start_date == null ) {
      if ( row.getRecruitStartDate() != null )
        return false;
    } else if ( !recruit_start_date.equals( row.getRecruitStartDate() ) ) {
        return false;
    }
    if ( recruit_end_date == null ) {
      if ( row.getRecruitEndDate() != null )
        return false;
    } else if ( !recruit_end_date.equals( row.getRecruitEndDate() ) ) {
        return false;
    }
    if ( recruit_price == null ) {
      if ( !row.getRecruitPriceIsNull() )
        return false;
    } else if ( row.getRecruitPriceIsNull() || ( recruit_price.intValue() != row.getRecruitPrice() ) ) {
        return false;
    }
    if ( payment_start_date1 == null ) {
      if ( row.getPaymentStartDate1() != null )
        return false;
    } else if ( !payment_start_date1.equals( row.getPaymentStartDate1() ) ) {
        return false;
    }
    if ( payment_start_date2 == null ) {
      if ( row.getPaymentStartDate2() != null )
        return false;
    } else if ( !payment_start_date2.equals( row.getPaymentStartDate2() ) ) {
        return false;
    }
    if ( storage_stop_flag == null ) {
      if ( row.getStorageStopFlag() != null )
        return false;
    } else if ( !storage_stop_flag.equals( row.getStorageStopFlag() ) ) {
        return false;
    }
    if ( trade_stop_flag == null ) {
      if ( row.getTradeStopFlag() != null )
        return false;
    } else if ( !trade_stop_flag.equals( row.getTradeStopFlag() ) ) {
        return false;
    }
    if ( obliterate_type == null ) {
      if ( row.getObliterateType() != null )
        return false;
    } else if ( !obliterate_type.equals( row.getObliterateType() ) ) {
        return false;
    }
    if ( corpus_price == null ) {
      if ( !row.getCorpusPriceIsNull() )
        return false;
    } else if ( row.getCorpusPriceIsNull() || ( corpus_price.intValue() != row.getCorpusPrice() ) ) {
        return false;
    }
    if ( open_close_type == null ) {
      if ( row.getOpenCloseType() != null )
        return false;
    } else if ( !open_close_type.equals( row.getOpenCloseType() ) ) {
        return false;
    }
    if ( dayreport_product_code == null ) {
      if ( row.getDayreportProductCode() != null )
        return false;
    } else if ( !dayreport_product_code.equals( row.getDayreportProductCode() ) ) {
        return false;
    }
    if ( recruit_sales == null ) {
      if ( row.getRecruitSales() != null )
        return false;
    } else if ( !recruit_sales.equals( row.getRecruitSales() ) ) {
        return false;
    }
    if ( stock_type_bond_type == null ) {
      if ( row.getStockTypeBondType() != null )
        return false;
    } else if ( !stock_type_bond_type.equals( row.getStockTypeBondType() ) ) {
        return false;
    }
    if ( contract_institution_type == null ) {
      if ( row.getContractInstitutionType() != null )
        return false;
    } else if ( !contract_institution_type.equals( row.getContractInstitutionType() ) ) {
        return false;
    }
    if ( purchs_deduction_start_date == null ) {
      if ( row.getPurchsDeductionStartDate() != null )
        return false;
    } else if ( !purchs_deduction_start_date.equals( row.getPurchsDeductionStartDate() ) ) {
        return false;
    }
    if ( spot_closing_date1 == null ) {
      if ( row.getSpotClosingDate1() != null )
        return false;
    } else if ( !spot_closing_date1.equals( row.getSpotClosingDate1() ) ) {
        return false;
    }
    if ( spot_closing_date2 == null ) {
      if ( row.getSpotClosingDate2() != null )
        return false;
    } else if ( !spot_closing_date2.equals( row.getSpotClosingDate2() ) ) {
        return false;
    }
    if ( calc_unit == null ) {
      if ( !row.getCalcUnitIsNull() )
        return false;
    } else if ( row.getCalcUnitIsNull() || ( calc_unit.intValue() != row.getCalcUnit() ) ) {
        return false;
    }
    if ( biz_asset_product_type == null ) {
      if ( row.getBizAssetProductType() != null )
        return false;
    } else if ( !biz_asset_product_type.equals( row.getBizAssetProductType() ) ) {
        return false;
    }
    if ( biz_asset_evaluate_price == null ) {
      if ( !row.getBizAssetEvaluatePriceIsNull() )
        return false;
    } else if ( row.getBizAssetEvaluatePriceIsNull() || ( biz_asset_evaluate_price.doubleValue() != row.getBizAssetEvaluatePrice() ) ) {
        return false;
    }
    if ( profit_balance_confirm_data == null ) {
      if ( row.getProfitBalanceConfirmData() != null )
        return false;
    } else if ( !profit_balance_confirm_data.equals( row.getProfitBalanceConfirmData() ) ) {
        return false;
    }
    if ( profit_term_quantity == null ) {
      if ( row.getProfitTermQuantity() != null )
        return false;
    } else if ( !profit_term_quantity.equals( row.getProfitTermQuantity() ) ) {
        return false;
    }
    if ( general_profit_price == null ) {
      if ( !row.getGeneralProfitPriceIsNull() )
        return false;
    } else if ( row.getGeneralProfitPriceIsNull() || ( general_profit_price.doubleValue() != row.getGeneralProfitPrice() ) ) {
        return false;
    }
    if ( spcprofit_distribution_price == null ) {
      if ( !row.getSpcprofitDistributionPriceIsNull() )
        return false;
    } else if ( row.getSpcprofitDistributionPriceIsNull() || ( spcprofit_distribution_price.doubleValue() != row.getSpcprofitDistributionPrice() ) ) {
        return false;
    }
    if ( taxinlots_aftertax_price == null ) {
      if ( !row.getTaxinlotsAftertaxPriceIsNull() )
        return false;
    } else if ( row.getTaxinlotsAftertaxPriceIsNull() || ( taxinlots_aftertax_price.doubleValue() != row.getTaxinlotsAftertaxPrice() ) ) {
        return false;
    }
    if ( taxaggregate_aftertax_price == null ) {
      if ( !row.getTaxaggregateAftertaxPriceIsNull() )
        return false;
    } else if ( row.getTaxaggregateAftertaxPriceIsNull() || ( taxaggregate_aftertax_price.doubleValue() != row.getTaxaggregateAftertaxPrice() ) ) {
        return false;
    }
    if ( pay_start_date_advanced_div == null ) {
      if ( row.getPayStartDateAdvancedDiv() != null )
        return false;
    } else if ( !pay_start_date_advanced_div.equals( row.getPayStartDateAdvancedDiv() ) ) {
        return false;
    }
    if ( method_type == null ) {
      if ( row.getMethodType() != null )
        return false;
    } else if ( !method_type.equals( row.getMethodType() ) ) {
        return false;
    }
    if ( setting_date == null ) {
      if ( row.getSettingDate() != null )
        return false;
    } else if ( !setting_date.equals( row.getSettingDate() ) ) {
        return false;
    }
    if ( sell_forbidden_date == null ) {
      if ( row.getSellForbiddenDate() != null )
        return false;
    } else if ( !sell_forbidden_date.equals( row.getSellForbiddenDate() ) ) {
        return false;
    }
    if ( adding_forbidden_date == null ) {
      if ( row.getAddingForbiddenDate() != null )
        return false;
    } else if ( !adding_forbidden_date.equals( row.getAddingForbiddenDate() ) ) {
        return false;
    }
    if ( profit_start_date == null ) {
      if ( row.getProfitStartDate() != null )
        return false;
    } else if ( !profit_start_date.equals( row.getProfitStartDate() ) ) {
        return false;
    }
    if ( best_exception_product_flag == null ) {
      if ( row.getBestExceptionProductFlag() != null )
        return false;
    } else if ( !best_exception_product_flag.equals( row.getBestExceptionProductFlag() ) ) {
        return false;
    }
    if ( currency_type == null ) {
      if ( row.getCurrencyType() != null )
        return false;
    } else if ( !currency_type.equals( row.getCurrencyType() ) ) {
        return false;
    }
    if ( profit_distribution_regdate == null ) {
      if ( row.getProfitDistributionRegdate() != null )
        return false;
    } else if ( !profit_distribution_regdate.equals( row.getProfitDistributionRegdate() ) ) {
        return false;
    }
    if ( consign_contact_product_code == null ) {
      if ( row.getConsignContactProductCode() != null )
        return false;
    } else if ( !consign_contact_product_code.equals( row.getConsignContactProductCode() ) ) {
        return false;
    }
    if ( mutualassoc_product_code == null ) {
      if ( row.getMutualassocProductCode() != null )
        return false;
    } else if ( !mutualassoc_product_code.equals( row.getMutualassocProductCode() ) ) {
        return false;
    }
    if ( trust_bank_code == null ) {
      if ( row.getTrustBankCode() != null )
        return false;
    } else if ( !trust_bank_code.equals( row.getTrustBankCode() ) ) {
        return false;
    }
    if ( consign_institution_code == null ) {
      if ( row.getConsignInstitutionCode() != null )
        return false;
    } else if ( !consign_institution_code.equals( row.getConsignInstitutionCode() ) ) {
        return false;
    }
    if ( average_trust_price == null ) {
      if ( !row.getAverageTrustPriceIsNull() )
        return false;
    } else if ( row.getAverageTrustPriceIsNull() || ( average_trust_price.doubleValue() != row.getAverageTrustPrice() ) ) {
        return false;
    }
    if ( same_check_div == null ) {
      if ( row.getSameCheckDiv() != null )
        return false;
    } else if ( !same_check_div.equals( row.getSameCheckDiv() ) ) {
        return false;
    }
    if ( same_div == null ) {
      if ( row.getSameDiv() != null )
        return false;
    } else if ( !same_div.equals( row.getSameDiv() ) ) {
        return false;
    }
    if ( recruit_short_swt_check_div == null ) {
      if ( row.getRecruitShortSwtCheckDiv() != null )
        return false;
    } else if ( !recruit_short_swt_check_div.equals( row.getRecruitShortSwtCheckDiv() ) ) {
        return false;
    }
    if ( buy_short_swt_check_div == null ) {
      if ( row.getBuyShortSwtCheckDiv() != null )
        return false;
    } else if ( !buy_short_swt_check_div.equals( row.getBuyShortSwtCheckDiv() ) ) {
        return false;
    }
    if ( sell_short_swt_check_div == null ) {
      if ( row.getSellShortSwtCheckDiv() != null )
        return false;
    } else if ( !sell_short_swt_check_div.equals( row.getSellShortSwtCheckDiv() ) ) {
        return false;
    }
    if ( recruit_start_stop == null ) {
      if ( row.getRecruitStartStop() != null )
        return false;
    } else if ( !recruit_start_stop.equals( row.getRecruitStartStop() ) ) {
        return false;
    }
    if ( collateral_qualified_div == null ) {
      if ( row.getCollateralQualifiedDiv() != null )
        return false;
    } else if ( !collateral_qualified_div.equals( row.getCollateralQualifiedDiv() ) ) {
        return false;
    }
    if ( collateral_evaluation == null ) {
      if ( !row.getCollateralEvaluationIsNull() )
        return false;
    } else if ( row.getCollateralEvaluationIsNull() || ( collateral_evaluation.doubleValue() != row.getCollateralEvaluation() ) ) {
        return false;
    }
    if ( collateral_ratio == null ) {
      if ( !row.getCollateralRatioIsNull() )
        return false;
    } else if ( row.getCollateralRatioIsNull() || ( collateral_ratio.doubleValue() != row.getCollateralRatio() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (product_code!=null? product_code.hashCode(): 0) 
        + (product_name_kana!=null? product_name_kana.hashCode(): 0) 
        + (product_name_kanji!=null? product_name_kanji.hashCode(): 0) 
        + (product_div!=null? product_div.hashCode(): 0) 
        + (work_date!=null? work_date.hashCode(): 0) 
        + (effect_generating_date!=null? effect_generating_date.hashCode(): 0) 
        + (invalid_date!=null? invalid_date.hashCode(): 0) 
        + (closing_date1!=null? closing_date1.hashCode(): 0) 
        + (closing_date2!=null? closing_date2.hashCode(): 0) 
        + (redemption_extend_div!=null? redemption_extend_div.hashCode(): 0) 
        + (redemption_date!=null? redemption_date.hashCode(): 0) 
        + (first_recruitment_date!=null? first_recruitment_date.hashCode(): 0) 
        + (recruit_start_date!=null? recruit_start_date.hashCode(): 0) 
        + (recruit_end_date!=null? recruit_end_date.hashCode(): 0) 
        + (recruit_price!=null? recruit_price.hashCode(): 0) 
        + (payment_start_date1!=null? payment_start_date1.hashCode(): 0) 
        + (payment_start_date2!=null? payment_start_date2.hashCode(): 0) 
        + (storage_stop_flag!=null? storage_stop_flag.hashCode(): 0) 
        + (trade_stop_flag!=null? trade_stop_flag.hashCode(): 0) 
        + (obliterate_type!=null? obliterate_type.hashCode(): 0) 
        + (corpus_price!=null? corpus_price.hashCode(): 0) 
        + (open_close_type!=null? open_close_type.hashCode(): 0) 
        + (dayreport_product_code!=null? dayreport_product_code.hashCode(): 0) 
        + (recruit_sales!=null? recruit_sales.hashCode(): 0) 
        + (stock_type_bond_type!=null? stock_type_bond_type.hashCode(): 0) 
        + (contract_institution_type!=null? contract_institution_type.hashCode(): 0) 
        + (purchs_deduction_start_date!=null? purchs_deduction_start_date.hashCode(): 0) 
        + (spot_closing_date1!=null? spot_closing_date1.hashCode(): 0) 
        + (spot_closing_date2!=null? spot_closing_date2.hashCode(): 0) 
        + (calc_unit!=null? calc_unit.hashCode(): 0) 
        + (biz_asset_product_type!=null? biz_asset_product_type.hashCode(): 0) 
        + (biz_asset_evaluate_price!=null? biz_asset_evaluate_price.hashCode(): 0) 
        + (profit_balance_confirm_data!=null? profit_balance_confirm_data.hashCode(): 0) 
        + (profit_term_quantity!=null? profit_term_quantity.hashCode(): 0) 
        + (general_profit_price!=null? general_profit_price.hashCode(): 0) 
        + (spcprofit_distribution_price!=null? spcprofit_distribution_price.hashCode(): 0) 
        + (taxinlots_aftertax_price!=null? taxinlots_aftertax_price.hashCode(): 0) 
        + (taxaggregate_aftertax_price!=null? taxaggregate_aftertax_price.hashCode(): 0) 
        + (pay_start_date_advanced_div!=null? pay_start_date_advanced_div.hashCode(): 0) 
        + (method_type!=null? method_type.hashCode(): 0) 
        + (setting_date!=null? setting_date.hashCode(): 0) 
        + (sell_forbidden_date!=null? sell_forbidden_date.hashCode(): 0) 
        + (adding_forbidden_date!=null? adding_forbidden_date.hashCode(): 0) 
        + (profit_start_date!=null? profit_start_date.hashCode(): 0) 
        + (best_exception_product_flag!=null? best_exception_product_flag.hashCode(): 0) 
        + (currency_type!=null? currency_type.hashCode(): 0) 
        + (profit_distribution_regdate!=null? profit_distribution_regdate.hashCode(): 0) 
        + (consign_contact_product_code!=null? consign_contact_product_code.hashCode(): 0) 
        + (mutualassoc_product_code!=null? mutualassoc_product_code.hashCode(): 0) 
        + (trust_bank_code!=null? trust_bank_code.hashCode(): 0) 
        + (consign_institution_code!=null? consign_institution_code.hashCode(): 0) 
        + (average_trust_price!=null? average_trust_price.hashCode(): 0) 
        + (same_check_div!=null? same_check_div.hashCode(): 0) 
        + (same_div!=null? same_div.hashCode(): 0) 
        + (recruit_short_swt_check_div!=null? recruit_short_swt_check_div.hashCode(): 0) 
        + (buy_short_swt_check_div!=null? buy_short_swt_check_div.hashCode(): 0) 
        + (sell_short_swt_check_div!=null? sell_short_swt_check_div.hashCode(): 0) 
        + (recruit_start_stop!=null? recruit_start_stop.hashCode(): 0) 
        + (collateral_qualified_div!=null? collateral_qualified_div.hashCode(): 0) 
        + (collateral_evaluation!=null? collateral_evaluation.hashCode(): 0) 
        + (collateral_ratio!=null? collateral_ratio.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_code",product_code);
		if ( product_name_kana != null )
			map.put("product_name_kana",product_name_kana);
		if ( product_name_kanji != null )
			map.put("product_name_kanji",product_name_kanji);
		if ( product_div != null )
			map.put("product_div",product_div);
		if ( work_date != null )
			map.put("work_date",work_date);
		if ( effect_generating_date != null )
			map.put("effect_generating_date",effect_generating_date);
		if ( invalid_date_is_set )
			map.put("invalid_date",invalid_date);
		if ( closing_date1 != null )
			map.put("closing_date1",closing_date1);
		if ( closing_date2 != null )
			map.put("closing_date2",closing_date2);
		if ( redemption_extend_div != null )
			map.put("redemption_extend_div",redemption_extend_div);
		if ( redemption_date_is_set )
			map.put("redemption_date",redemption_date);
		if ( first_recruitment_date_is_set )
			map.put("first_recruitment_date",first_recruitment_date);
		if ( recruit_start_date_is_set )
			map.put("recruit_start_date",recruit_start_date);
		if ( recruit_end_date_is_set )
			map.put("recruit_end_date",recruit_end_date);
		if ( recruit_price_is_set )
			map.put("recruit_price",recruit_price);
		if ( payment_start_date1 != null )
			map.put("payment_start_date1",payment_start_date1);
		if ( payment_start_date2 != null )
			map.put("payment_start_date2",payment_start_date2);
		if ( storage_stop_flag != null )
			map.put("storage_stop_flag",storage_stop_flag);
		if ( trade_stop_flag != null )
			map.put("trade_stop_flag",trade_stop_flag);
		if ( obliterate_type != null )
			map.put("obliterate_type",obliterate_type);
		if ( corpus_price_is_set )
			map.put("corpus_price",corpus_price);
		if ( open_close_type != null )
			map.put("open_close_type",open_close_type);
		if ( dayreport_product_code != null )
			map.put("dayreport_product_code",dayreport_product_code);
		if ( recruit_sales != null )
			map.put("recruit_sales",recruit_sales);
		if ( stock_type_bond_type != null )
			map.put("stock_type_bond_type",stock_type_bond_type);
		if ( contract_institution_type != null )
			map.put("contract_institution_type",contract_institution_type);
		if ( purchs_deduction_start_date_is_set )
			map.put("purchs_deduction_start_date",purchs_deduction_start_date);
		if ( spot_closing_date1 != null )
			map.put("spot_closing_date1",spot_closing_date1);
		if ( spot_closing_date2 != null )
			map.put("spot_closing_date2",spot_closing_date2);
		if ( calc_unit != null )
			map.put("calc_unit",calc_unit);
		if ( biz_asset_product_type != null )
			map.put("biz_asset_product_type",biz_asset_product_type);
		if ( biz_asset_evaluate_price != null )
			map.put("biz_asset_evaluate_price",biz_asset_evaluate_price);
		if ( profit_balance_confirm_data != null )
			map.put("profit_balance_confirm_data",profit_balance_confirm_data);
		if ( profit_term_quantity != null )
			map.put("profit_term_quantity",profit_term_quantity);
		if ( general_profit_price != null )
			map.put("general_profit_price",general_profit_price);
		if ( spcprofit_distribution_price != null )
			map.put("spcprofit_distribution_price",spcprofit_distribution_price);
		if ( taxinlots_aftertax_price != null )
			map.put("taxinlots_aftertax_price",taxinlots_aftertax_price);
		if ( taxaggregate_aftertax_price != null )
			map.put("taxaggregate_aftertax_price",taxaggregate_aftertax_price);
		if ( pay_start_date_advanced_div != null )
			map.put("pay_start_date_advanced_div",pay_start_date_advanced_div);
		if ( method_type != null )
			map.put("method_type",method_type);
		if ( setting_date != null )
			map.put("setting_date",setting_date);
		if ( sell_forbidden_date != null )
			map.put("sell_forbidden_date",sell_forbidden_date);
		if ( adding_forbidden_date != null )
			map.put("adding_forbidden_date",adding_forbidden_date);
		if ( profit_start_date != null )
			map.put("profit_start_date",profit_start_date);
		if ( best_exception_product_flag != null )
			map.put("best_exception_product_flag",best_exception_product_flag);
		if ( currency_type != null )
			map.put("currency_type",currency_type);
		if ( profit_distribution_regdate != null )
			map.put("profit_distribution_regdate",profit_distribution_regdate);
		if ( consign_contact_product_code != null )
			map.put("consign_contact_product_code",consign_contact_product_code);
		if ( mutualassoc_product_code != null )
			map.put("mutualassoc_product_code",mutualassoc_product_code);
		if ( trust_bank_code != null )
			map.put("trust_bank_code",trust_bank_code);
		if ( consign_institution_code != null )
			map.put("consign_institution_code",consign_institution_code);
		if ( average_trust_price != null )
			map.put("average_trust_price",average_trust_price);
		if ( same_check_div != null )
			map.put("same_check_div",same_check_div);
		if ( same_div != null )
			map.put("same_div",same_div);
		if ( recruit_short_swt_check_div != null )
			map.put("recruit_short_swt_check_div",recruit_short_swt_check_div);
		if ( buy_short_swt_check_div != null )
			map.put("buy_short_swt_check_div",buy_short_swt_check_div);
		if ( sell_short_swt_check_div != null )
			map.put("sell_short_swt_check_div",sell_short_swt_check_div);
		if ( recruit_start_stop != null )
			map.put("recruit_start_stop",recruit_start_stop);
		if ( collateral_qualified_div != null )
			map.put("collateral_qualified_div",collateral_qualified_div);
		if ( collateral_evaluation != null )
			map.put("collateral_evaluation",collateral_evaluation);
		if ( collateral_ratio != null )
			map.put("collateral_ratio",collateral_ratio);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( product_name_kana_is_modified )
			map.put("product_name_kana",product_name_kana);
		if ( product_name_kanji_is_modified )
			map.put("product_name_kanji",product_name_kanji);
		if ( product_div_is_modified )
			map.put("product_div",product_div);
		if ( work_date_is_modified )
			map.put("work_date",work_date);
		if ( effect_generating_date_is_modified )
			map.put("effect_generating_date",effect_generating_date);
		if ( invalid_date_is_modified )
			map.put("invalid_date",invalid_date);
		if ( closing_date1_is_modified )
			map.put("closing_date1",closing_date1);
		if ( closing_date2_is_modified )
			map.put("closing_date2",closing_date2);
		if ( redemption_extend_div_is_modified )
			map.put("redemption_extend_div",redemption_extend_div);
		if ( redemption_date_is_modified )
			map.put("redemption_date",redemption_date);
		if ( first_recruitment_date_is_modified )
			map.put("first_recruitment_date",first_recruitment_date);
		if ( recruit_start_date_is_modified )
			map.put("recruit_start_date",recruit_start_date);
		if ( recruit_end_date_is_modified )
			map.put("recruit_end_date",recruit_end_date);
		if ( recruit_price_is_modified )
			map.put("recruit_price",recruit_price);
		if ( payment_start_date1_is_modified )
			map.put("payment_start_date1",payment_start_date1);
		if ( payment_start_date2_is_modified )
			map.put("payment_start_date2",payment_start_date2);
		if ( storage_stop_flag_is_modified )
			map.put("storage_stop_flag",storage_stop_flag);
		if ( trade_stop_flag_is_modified )
			map.put("trade_stop_flag",trade_stop_flag);
		if ( obliterate_type_is_modified )
			map.put("obliterate_type",obliterate_type);
		if ( corpus_price_is_modified )
			map.put("corpus_price",corpus_price);
		if ( open_close_type_is_modified )
			map.put("open_close_type",open_close_type);
		if ( dayreport_product_code_is_modified )
			map.put("dayreport_product_code",dayreport_product_code);
		if ( recruit_sales_is_modified )
			map.put("recruit_sales",recruit_sales);
		if ( stock_type_bond_type_is_modified )
			map.put("stock_type_bond_type",stock_type_bond_type);
		if ( contract_institution_type_is_modified )
			map.put("contract_institution_type",contract_institution_type);
		if ( purchs_deduction_start_date_is_modified )
			map.put("purchs_deduction_start_date",purchs_deduction_start_date);
		if ( spot_closing_date1_is_modified )
			map.put("spot_closing_date1",spot_closing_date1);
		if ( spot_closing_date2_is_modified )
			map.put("spot_closing_date2",spot_closing_date2);
		if ( calc_unit_is_modified )
			map.put("calc_unit",calc_unit);
		if ( biz_asset_product_type_is_modified )
			map.put("biz_asset_product_type",biz_asset_product_type);
		if ( biz_asset_evaluate_price_is_modified )
			map.put("biz_asset_evaluate_price",biz_asset_evaluate_price);
		if ( profit_balance_confirm_data_is_modified )
			map.put("profit_balance_confirm_data",profit_balance_confirm_data);
		if ( profit_term_quantity_is_modified )
			map.put("profit_term_quantity",profit_term_quantity);
		if ( general_profit_price_is_modified )
			map.put("general_profit_price",general_profit_price);
		if ( spcprofit_distribution_price_is_modified )
			map.put("spcprofit_distribution_price",spcprofit_distribution_price);
		if ( taxinlots_aftertax_price_is_modified )
			map.put("taxinlots_aftertax_price",taxinlots_aftertax_price);
		if ( taxaggregate_aftertax_price_is_modified )
			map.put("taxaggregate_aftertax_price",taxaggregate_aftertax_price);
		if ( pay_start_date_advanced_div_is_modified )
			map.put("pay_start_date_advanced_div",pay_start_date_advanced_div);
		if ( method_type_is_modified )
			map.put("method_type",method_type);
		if ( setting_date_is_modified )
			map.put("setting_date",setting_date);
		if ( sell_forbidden_date_is_modified )
			map.put("sell_forbidden_date",sell_forbidden_date);
		if ( adding_forbidden_date_is_modified )
			map.put("adding_forbidden_date",adding_forbidden_date);
		if ( profit_start_date_is_modified )
			map.put("profit_start_date",profit_start_date);
		if ( best_exception_product_flag_is_modified )
			map.put("best_exception_product_flag",best_exception_product_flag);
		if ( currency_type_is_modified )
			map.put("currency_type",currency_type);
		if ( profit_distribution_regdate_is_modified )
			map.put("profit_distribution_regdate",profit_distribution_regdate);
		if ( consign_contact_product_code_is_modified )
			map.put("consign_contact_product_code",consign_contact_product_code);
		if ( mutualassoc_product_code_is_modified )
			map.put("mutualassoc_product_code",mutualassoc_product_code);
		if ( trust_bank_code_is_modified )
			map.put("trust_bank_code",trust_bank_code);
		if ( consign_institution_code_is_modified )
			map.put("consign_institution_code",consign_institution_code);
		if ( average_trust_price_is_modified )
			map.put("average_trust_price",average_trust_price);
		if ( same_check_div_is_modified )
			map.put("same_check_div",same_check_div);
		if ( same_div_is_modified )
			map.put("same_div",same_div);
		if ( recruit_short_swt_check_div_is_modified )
			map.put("recruit_short_swt_check_div",recruit_short_swt_check_div);
		if ( buy_short_swt_check_div_is_modified )
			map.put("buy_short_swt_check_div",buy_short_swt_check_div);
		if ( sell_short_swt_check_div_is_modified )
			map.put("sell_short_swt_check_div",sell_short_swt_check_div);
		if ( recruit_start_stop_is_modified )
			map.put("recruit_start_stop",recruit_start_stop);
		if ( collateral_qualified_div_is_modified )
			map.put("collateral_qualified_div",collateral_qualified_div);
		if ( collateral_evaluation_is_modified )
			map.put("collateral_evaluation",collateral_evaluation);
		if ( collateral_ratio_is_modified )
			map.put("collateral_ratio",collateral_ratio);
		if (map.size() == 0) {
			map.put("product_name_kana",product_name_kana);
			map.put("product_name_kanji",product_name_kanji);
			map.put("product_div",product_div);
			map.put("work_date",work_date);
			map.put("effect_generating_date",effect_generating_date);
			if ( invalid_date_is_set )
				map.put("invalid_date",invalid_date);
			map.put("closing_date1",closing_date1);
			map.put("closing_date2",closing_date2);
			map.put("redemption_extend_div",redemption_extend_div);
			if ( redemption_date_is_set )
				map.put("redemption_date",redemption_date);
			if ( first_recruitment_date_is_set )
				map.put("first_recruitment_date",first_recruitment_date);
			if ( recruit_start_date_is_set )
				map.put("recruit_start_date",recruit_start_date);
			if ( recruit_end_date_is_set )
				map.put("recruit_end_date",recruit_end_date);
			if ( recruit_price_is_set )
				map.put("recruit_price",recruit_price);
			map.put("payment_start_date1",payment_start_date1);
			map.put("payment_start_date2",payment_start_date2);
			map.put("storage_stop_flag",storage_stop_flag);
			map.put("trade_stop_flag",trade_stop_flag);
			map.put("obliterate_type",obliterate_type);
			if ( corpus_price_is_set )
				map.put("corpus_price",corpus_price);
			map.put("open_close_type",open_close_type);
			map.put("dayreport_product_code",dayreport_product_code);
			map.put("recruit_sales",recruit_sales);
			map.put("stock_type_bond_type",stock_type_bond_type);
			map.put("contract_institution_type",contract_institution_type);
			if ( purchs_deduction_start_date_is_set )
				map.put("purchs_deduction_start_date",purchs_deduction_start_date);
			map.put("spot_closing_date1",spot_closing_date1);
			map.put("spot_closing_date2",spot_closing_date2);
			map.put("calc_unit",calc_unit);
			map.put("biz_asset_product_type",biz_asset_product_type);
			map.put("biz_asset_evaluate_price",biz_asset_evaluate_price);
			map.put("profit_balance_confirm_data",profit_balance_confirm_data);
			map.put("profit_term_quantity",profit_term_quantity);
			map.put("general_profit_price",general_profit_price);
			map.put("spcprofit_distribution_price",spcprofit_distribution_price);
			map.put("taxinlots_aftertax_price",taxinlots_aftertax_price);
			map.put("taxaggregate_aftertax_price",taxaggregate_aftertax_price);
			map.put("pay_start_date_advanced_div",pay_start_date_advanced_div);
			map.put("method_type",method_type);
			map.put("setting_date",setting_date);
			map.put("sell_forbidden_date",sell_forbidden_date);
			map.put("adding_forbidden_date",adding_forbidden_date);
			map.put("profit_start_date",profit_start_date);
			map.put("best_exception_product_flag",best_exception_product_flag);
			map.put("currency_type",currency_type);
			map.put("profit_distribution_regdate",profit_distribution_regdate);
			map.put("consign_contact_product_code",consign_contact_product_code);
			map.put("mutualassoc_product_code",mutualassoc_product_code);
			map.put("trust_bank_code",trust_bank_code);
			map.put("consign_institution_code",consign_institution_code);
			map.put("average_trust_price",average_trust_price);
			map.put("same_check_div",same_check_div);
			map.put("same_div",same_div);
			map.put("recruit_short_swt_check_div",recruit_short_swt_check_div);
			map.put("buy_short_swt_check_div",buy_short_swt_check_div);
			map.put("sell_short_swt_check_div",sell_short_swt_check_div);
			map.put("recruit_start_stop",recruit_start_stop);
			map.put("collateral_qualified_div",collateral_qualified_div);
			map.put("collateral_evaluation",collateral_evaluation);
			map.put("collateral_ratio",collateral_ratio);
		}
		return map;
	}


  /** 
   * <em>product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>product_name_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductNameKana()
  {
    return product_name_kana;
  }


  /** 
   * <em>product_name_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductNameKanaIsSet() {
    return product_name_kana_is_set;
  }


  /** 
   * <em>product_name_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductNameKanaIsModified() {
    return product_name_kana_is_modified;
  }


  /** 
   * <em>product_name_kanji</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductNameKanji()
  {
    return product_name_kanji;
  }


  /** 
   * <em>product_name_kanji</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductNameKanjiIsSet() {
    return product_name_kanji_is_set;
  }


  /** 
   * <em>product_name_kanji</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductNameKanjiIsModified() {
    return product_name_kanji_is_modified;
  }


  /** 
   * <em>product_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductDiv()
  {
    return product_div;
  }


  /** 
   * <em>product_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductDivIsSet() {
    return product_div_is_set;
  }


  /** 
   * <em>product_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductDivIsModified() {
    return product_div_is_modified;
  }


  /** 
   * <em>work_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getWorkDate()
  {
    return work_date;
  }


  /** 
   * <em>work_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWorkDateIsSet() {
    return work_date_is_set;
  }


  /** 
   * <em>work_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWorkDateIsModified() {
    return work_date_is_modified;
  }


  /** 
   * <em>effect_generating_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getEffectGeneratingDate()
  {
    return effect_generating_date;
  }


  /** 
   * <em>effect_generating_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEffectGeneratingDateIsSet() {
    return effect_generating_date_is_set;
  }


  /** 
   * <em>effect_generating_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEffectGeneratingDateIsModified() {
    return effect_generating_date_is_modified;
  }


  /** 
   * <em>invalid_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getInvalidDate()
  {
    return invalid_date;
  }


  /** 
   * <em>invalid_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInvalidDateIsSet() {
    return invalid_date_is_set;
  }


  /** 
   * <em>invalid_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInvalidDateIsModified() {
    return invalid_date_is_modified;
  }


  /** 
   * <em>closing_date1</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getClosingDate1()
  {
    return closing_date1;
  }


  /** 
   * <em>closing_date1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getClosingDate1IsSet() {
    return closing_date1_is_set;
  }


  /** 
   * <em>closing_date1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getClosingDate1IsModified() {
    return closing_date1_is_modified;
  }


  /** 
   * <em>closing_date2</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getClosingDate2()
  {
    return closing_date2;
  }


  /** 
   * <em>closing_date2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getClosingDate2IsSet() {
    return closing_date2_is_set;
  }


  /** 
   * <em>closing_date2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getClosingDate2IsModified() {
    return closing_date2_is_modified;
  }


  /** 
   * <em>redemption_extend_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRedemptionExtendDiv()
  {
    return redemption_extend_div;
  }


  /** 
   * <em>redemption_extend_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRedemptionExtendDivIsSet() {
    return redemption_extend_div_is_set;
  }


  /** 
   * <em>redemption_extend_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRedemptionExtendDivIsModified() {
    return redemption_extend_div_is_modified;
  }


  /** 
   * <em>redemption_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getRedemptionDate()
  {
    return redemption_date;
  }


  /** 
   * <em>redemption_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRedemptionDateIsSet() {
    return redemption_date_is_set;
  }


  /** 
   * <em>redemption_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRedemptionDateIsModified() {
    return redemption_date_is_modified;
  }


  /** 
   * <em>first_recruitment_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getFirstRecruitmentDate()
  {
    return first_recruitment_date;
  }


  /** 
   * <em>first_recruitment_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFirstRecruitmentDateIsSet() {
    return first_recruitment_date_is_set;
  }


  /** 
   * <em>first_recruitment_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFirstRecruitmentDateIsModified() {
    return first_recruitment_date_is_modified;
  }


  /** 
   * <em>recruit_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getRecruitStartDate()
  {
    return recruit_start_date;
  }


  /** 
   * <em>recruit_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitStartDateIsSet() {
    return recruit_start_date_is_set;
  }


  /** 
   * <em>recruit_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitStartDateIsModified() {
    return recruit_start_date_is_modified;
  }


  /** 
   * <em>recruit_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getRecruitEndDate()
  {
    return recruit_end_date;
  }


  /** 
   * <em>recruit_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitEndDateIsSet() {
    return recruit_end_date_is_set;
  }


  /** 
   * <em>recruit_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitEndDateIsModified() {
    return recruit_end_date_is_modified;
  }


  /** 
   * <em>recruit_price</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getRecruitPrice()
  {
    return ( recruit_price==null? ((int)0): recruit_price.intValue() );
  }


  /** 
   * <em>recruit_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getRecruitPriceIsNull()
  {
    return recruit_price == null;
  }


  /** 
   * <em>recruit_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitPriceIsSet() {
    return recruit_price_is_set;
  }


  /** 
   * <em>recruit_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitPriceIsModified() {
    return recruit_price_is_modified;
  }


  /** 
   * <em>payment_start_date1</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getPaymentStartDate1()
  {
    return payment_start_date1;
  }


  /** 
   * <em>payment_start_date1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentStartDate1IsSet() {
    return payment_start_date1_is_set;
  }


  /** 
   * <em>payment_start_date1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentStartDate1IsModified() {
    return payment_start_date1_is_modified;
  }


  /** 
   * <em>payment_start_date2</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getPaymentStartDate2()
  {
    return payment_start_date2;
  }


  /** 
   * <em>payment_start_date2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentStartDate2IsSet() {
    return payment_start_date2_is_set;
  }


  /** 
   * <em>payment_start_date2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentStartDate2IsModified() {
    return payment_start_date2_is_modified;
  }


  /** 
   * <em>storage_stop_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStorageStopFlag()
  {
    return storage_stop_flag;
  }


  /** 
   * <em>storage_stop_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStorageStopFlagIsSet() {
    return storage_stop_flag_is_set;
  }


  /** 
   * <em>storage_stop_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStorageStopFlagIsModified() {
    return storage_stop_flag_is_modified;
  }


  /** 
   * <em>trade_stop_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTradeStopFlag()
  {
    return trade_stop_flag;
  }


  /** 
   * <em>trade_stop_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeStopFlagIsSet() {
    return trade_stop_flag_is_set;
  }


  /** 
   * <em>trade_stop_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeStopFlagIsModified() {
    return trade_stop_flag_is_modified;
  }


  /** 
   * <em>obliterate_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getObliterateType()
  {
    return obliterate_type;
  }


  /** 
   * <em>obliterate_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getObliterateTypeIsSet() {
    return obliterate_type_is_set;
  }


  /** 
   * <em>obliterate_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getObliterateTypeIsModified() {
    return obliterate_type_is_modified;
  }


  /** 
   * <em>corpus_price</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getCorpusPrice()
  {
    return ( corpus_price==null? ((int)0): corpus_price.intValue() );
  }


  /** 
   * <em>corpus_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCorpusPriceIsNull()
  {
    return corpus_price == null;
  }


  /** 
   * <em>corpus_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCorpusPriceIsSet() {
    return corpus_price_is_set;
  }


  /** 
   * <em>corpus_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCorpusPriceIsModified() {
    return corpus_price_is_modified;
  }


  /** 
   * <em>open_close_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOpenCloseType()
  {
    return open_close_type;
  }


  /** 
   * <em>open_close_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenCloseTypeIsSet() {
    return open_close_type_is_set;
  }


  /** 
   * <em>open_close_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenCloseTypeIsModified() {
    return open_close_type_is_modified;
  }


  /** 
   * <em>dayreport_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDayreportProductCode()
  {
    return dayreport_product_code;
  }


  /** 
   * <em>dayreport_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDayreportProductCodeIsSet() {
    return dayreport_product_code_is_set;
  }


  /** 
   * <em>dayreport_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDayreportProductCodeIsModified() {
    return dayreport_product_code_is_modified;
  }


  /** 
   * <em>recruit_sales</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRecruitSales()
  {
    return recruit_sales;
  }


  /** 
   * <em>recruit_sales</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitSalesIsSet() {
    return recruit_sales_is_set;
  }


  /** 
   * <em>recruit_sales</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitSalesIsModified() {
    return recruit_sales_is_modified;
  }


  /** 
   * <em>stock_type_bond_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStockTypeBondType()
  {
    return stock_type_bond_type;
  }


  /** 
   * <em>stock_type_bond_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStockTypeBondTypeIsSet() {
    return stock_type_bond_type_is_set;
  }


  /** 
   * <em>stock_type_bond_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStockTypeBondTypeIsModified() {
    return stock_type_bond_type_is_modified;
  }


  /** 
   * <em>contract_institution_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getContractInstitutionType()
  {
    return contract_institution_type;
  }


  /** 
   * <em>contract_institution_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractInstitutionTypeIsSet() {
    return contract_institution_type_is_set;
  }


  /** 
   * <em>contract_institution_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractInstitutionTypeIsModified() {
    return contract_institution_type_is_modified;
  }


  /** 
   * <em>purchs_deduction_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getPurchsDeductionStartDate()
  {
    return purchs_deduction_start_date;
  }


  /** 
   * <em>purchs_deduction_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPurchsDeductionStartDateIsSet() {
    return purchs_deduction_start_date_is_set;
  }


  /** 
   * <em>purchs_deduction_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPurchsDeductionStartDateIsModified() {
    return purchs_deduction_start_date_is_modified;
  }


  /** 
   * <em>spot_closing_date1</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSpotClosingDate1()
  {
    return spot_closing_date1;
  }


  /** 
   * <em>spot_closing_date1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpotClosingDate1IsSet() {
    return spot_closing_date1_is_set;
  }


  /** 
   * <em>spot_closing_date1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpotClosingDate1IsModified() {
    return spot_closing_date1_is_modified;
  }


  /** 
   * <em>spot_closing_date2</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSpotClosingDate2()
  {
    return spot_closing_date2;
  }


  /** 
   * <em>spot_closing_date2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpotClosingDate2IsSet() {
    return spot_closing_date2_is_set;
  }


  /** 
   * <em>spot_closing_date2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpotClosingDate2IsModified() {
    return spot_closing_date2_is_modified;
  }


  /** 
   * <em>calc_unit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getCalcUnit()
  {
    return ( calc_unit==null? ((int)0): calc_unit.intValue() );
  }


  /** 
   * <em>calc_unit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCalcUnitIsNull()
  {
    return calc_unit == null;
  }


  /** 
   * <em>calc_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcUnitIsSet() {
    return calc_unit_is_set;
  }


  /** 
   * <em>calc_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcUnitIsModified() {
    return calc_unit_is_modified;
  }


  /** 
   * <em>biz_asset_product_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBizAssetProductType()
  {
    return biz_asset_product_type;
  }


  /** 
   * <em>biz_asset_product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizAssetProductTypeIsSet() {
    return biz_asset_product_type_is_set;
  }


  /** 
   * <em>biz_asset_product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizAssetProductTypeIsModified() {
    return biz_asset_product_type_is_modified;
  }


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getBizAssetEvaluatePrice()
  {
    return ( biz_asset_evaluate_price==null? ((double)0): biz_asset_evaluate_price.doubleValue() );
  }


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBizAssetEvaluatePriceIsNull()
  {
    return biz_asset_evaluate_price == null;
  }


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizAssetEvaluatePriceIsSet() {
    return biz_asset_evaluate_price_is_set;
  }


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizAssetEvaluatePriceIsModified() {
    return biz_asset_evaluate_price_is_modified;
  }


  /** 
   * <em>profit_balance_confirm_data</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getProfitBalanceConfirmData()
  {
    return profit_balance_confirm_data;
  }


  /** 
   * <em>profit_balance_confirm_data</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitBalanceConfirmDataIsSet() {
    return profit_balance_confirm_data_is_set;
  }


  /** 
   * <em>profit_balance_confirm_data</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitBalanceConfirmDataIsModified() {
    return profit_balance_confirm_data_is_modified;
  }


  /** 
   * <em>profit_term_quantity</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProfitTermQuantity()
  {
    return profit_term_quantity;
  }


  /** 
   * <em>profit_term_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitTermQuantityIsSet() {
    return profit_term_quantity_is_set;
  }


  /** 
   * <em>profit_term_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitTermQuantityIsModified() {
    return profit_term_quantity_is_modified;
  }


  /** 
   * <em>general_profit_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getGeneralProfitPrice()
  {
    return ( general_profit_price==null? ((double)0): general_profit_price.doubleValue() );
  }


  /** 
   * <em>general_profit_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getGeneralProfitPriceIsNull()
  {
    return general_profit_price == null;
  }


  /** 
   * <em>general_profit_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getGeneralProfitPriceIsSet() {
    return general_profit_price_is_set;
  }


  /** 
   * <em>general_profit_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getGeneralProfitPriceIsModified() {
    return general_profit_price_is_modified;
  }


  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSpcprofitDistributionPrice()
  {
    return ( spcprofit_distribution_price==null? ((double)0): spcprofit_distribution_price.doubleValue() );
  }


  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSpcprofitDistributionPriceIsNull()
  {
    return spcprofit_distribution_price == null;
  }


  /** 
   * <em>spcprofit_distribution_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpcprofitDistributionPriceIsSet() {
    return spcprofit_distribution_price_is_set;
  }


  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpcprofitDistributionPriceIsModified() {
    return spcprofit_distribution_price_is_modified;
  }


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getTaxinlotsAftertaxPrice()
  {
    return ( taxinlots_aftertax_price==null? ((double)0): taxinlots_aftertax_price.doubleValue() );
  }


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTaxinlotsAftertaxPriceIsNull()
  {
    return taxinlots_aftertax_price == null;
  }


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxinlotsAftertaxPriceIsSet() {
    return taxinlots_aftertax_price_is_set;
  }


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxinlotsAftertaxPriceIsModified() {
    return taxinlots_aftertax_price_is_modified;
  }


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getTaxaggregateAftertaxPrice()
  {
    return ( taxaggregate_aftertax_price==null? ((double)0): taxaggregate_aftertax_price.doubleValue() );
  }


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTaxaggregateAftertaxPriceIsNull()
  {
    return taxaggregate_aftertax_price == null;
  }


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxaggregateAftertaxPriceIsSet() {
    return taxaggregate_aftertax_price_is_set;
  }


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxaggregateAftertaxPriceIsModified() {
    return taxaggregate_aftertax_price_is_modified;
  }


  /** 
   * <em>pay_start_date_advanced_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPayStartDateAdvancedDiv()
  {
    return pay_start_date_advanced_div;
  }


  /** 
   * <em>pay_start_date_advanced_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPayStartDateAdvancedDivIsSet() {
    return pay_start_date_advanced_div_is_set;
  }


  /** 
   * <em>pay_start_date_advanced_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPayStartDateAdvancedDivIsModified() {
    return pay_start_date_advanced_div_is_modified;
  }


  /** 
   * <em>method_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMethodType()
  {
    return method_type;
  }


  /** 
   * <em>method_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMethodTypeIsSet() {
    return method_type_is_set;
  }


  /** 
   * <em>method_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMethodTypeIsModified() {
    return method_type_is_modified;
  }


  /** 
   * <em>setting_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSettingDate()
  {
    return setting_date;
  }


  /** 
   * <em>setting_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettingDateIsSet() {
    return setting_date_is_set;
  }


  /** 
   * <em>setting_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettingDateIsModified() {
    return setting_date_is_modified;
  }


  /** 
   * <em>sell_forbidden_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSellForbiddenDate()
  {
    return sell_forbidden_date;
  }


  /** 
   * <em>sell_forbidden_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellForbiddenDateIsSet() {
    return sell_forbidden_date_is_set;
  }


  /** 
   * <em>sell_forbidden_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellForbiddenDateIsModified() {
    return sell_forbidden_date_is_modified;
  }


  /** 
   * <em>adding_forbidden_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAddingForbiddenDate()
  {
    return adding_forbidden_date;
  }


  /** 
   * <em>adding_forbidden_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAddingForbiddenDateIsSet() {
    return adding_forbidden_date_is_set;
  }


  /** 
   * <em>adding_forbidden_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAddingForbiddenDateIsModified() {
    return adding_forbidden_date_is_modified;
  }


  /** 
   * <em>profit_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getProfitStartDate()
  {
    return profit_start_date;
  }


  /** 
   * <em>profit_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitStartDateIsSet() {
    return profit_start_date_is_set;
  }


  /** 
   * <em>profit_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitStartDateIsModified() {
    return profit_start_date_is_modified;
  }


  /** 
   * <em>best_exception_product_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBestExceptionProductFlag()
  {
    return best_exception_product_flag;
  }


  /** 
   * <em>best_exception_product_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBestExceptionProductFlagIsSet() {
    return best_exception_product_flag_is_set;
  }


  /** 
   * <em>best_exception_product_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBestExceptionProductFlagIsModified() {
    return best_exception_product_flag_is_modified;
  }


  /** 
   * <em>currency_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCurrencyType()
  {
    return currency_type;
  }


  /** 
   * <em>currency_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyTypeIsSet() {
    return currency_type_is_set;
  }


  /** 
   * <em>currency_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyTypeIsModified() {
    return currency_type_is_modified;
  }


  /** 
   * <em>profit_distribution_regdate</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getProfitDistributionRegdate()
  {
    return profit_distribution_regdate;
  }


  /** 
   * <em>profit_distribution_regdate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitDistributionRegdateIsSet() {
    return profit_distribution_regdate_is_set;
  }


  /** 
   * <em>profit_distribution_regdate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProfitDistributionRegdateIsModified() {
    return profit_distribution_regdate_is_modified;
  }


  /** 
   * <em>consign_contact_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getConsignContactProductCode()
  {
    return consign_contact_product_code;
  }


  /** 
   * <em>consign_contact_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsignContactProductCodeIsSet() {
    return consign_contact_product_code_is_set;
  }


  /** 
   * <em>consign_contact_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsignContactProductCodeIsModified() {
    return consign_contact_product_code_is_modified;
  }


  /** 
   * <em>mutualassoc_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMutualassocProductCode()
  {
    return mutualassoc_product_code;
  }


  /** 
   * <em>mutualassoc_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMutualassocProductCodeIsSet() {
    return mutualassoc_product_code_is_set;
  }


  /** 
   * <em>mutualassoc_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMutualassocProductCodeIsModified() {
    return mutualassoc_product_code_is_modified;
  }


  /** 
   * <em>trust_bank_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTrustBankCode()
  {
    return trust_bank_code;
  }


  /** 
   * <em>trust_bank_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTrustBankCodeIsSet() {
    return trust_bank_code_is_set;
  }


  /** 
   * <em>trust_bank_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTrustBankCodeIsModified() {
    return trust_bank_code_is_modified;
  }


  /** 
   * <em>consign_institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getConsignInstitutionCode()
  {
    return consign_institution_code;
  }


  /** 
   * <em>consign_institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsignInstitutionCodeIsSet() {
    return consign_institution_code_is_set;
  }


  /** 
   * <em>consign_institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsignInstitutionCodeIsModified() {
    return consign_institution_code_is_modified;
  }


  /** 
   * <em>average_trust_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAverageTrustPrice()
  {
    return ( average_trust_price==null? ((double)0): average_trust_price.doubleValue() );
  }


  /** 
   * <em>average_trust_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAverageTrustPriceIsNull()
  {
    return average_trust_price == null;
  }


  /** 
   * <em>average_trust_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAverageTrustPriceIsSet() {
    return average_trust_price_is_set;
  }


  /** 
   * <em>average_trust_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAverageTrustPriceIsModified() {
    return average_trust_price_is_modified;
  }


  /** 
   * <em>same_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSameCheckDiv()
  {
    return same_check_div;
  }


  /** 
   * <em>same_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSameCheckDivIsSet() {
    return same_check_div_is_set;
  }


  /** 
   * <em>same_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSameCheckDivIsModified() {
    return same_check_div_is_modified;
  }


  /** 
   * <em>same_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSameDiv()
  {
    return same_div;
  }


  /** 
   * <em>same_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSameDivIsSet() {
    return same_div_is_set;
  }


  /** 
   * <em>same_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSameDivIsModified() {
    return same_div_is_modified;
  }


  /** 
   * <em>recruit_short_swt_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRecruitShortSwtCheckDiv()
  {
    return recruit_short_swt_check_div;
  }


  /** 
   * <em>recruit_short_swt_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitShortSwtCheckDivIsSet() {
    return recruit_short_swt_check_div_is_set;
  }


  /** 
   * <em>recruit_short_swt_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitShortSwtCheckDivIsModified() {
    return recruit_short_swt_check_div_is_modified;
  }


  /** 
   * <em>buy_short_swt_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBuyShortSwtCheckDiv()
  {
    return buy_short_swt_check_div;
  }


  /** 
   * <em>buy_short_swt_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyShortSwtCheckDivIsSet() {
    return buy_short_swt_check_div_is_set;
  }


  /** 
   * <em>buy_short_swt_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyShortSwtCheckDivIsModified() {
    return buy_short_swt_check_div_is_modified;
  }


  /** 
   * <em>sell_short_swt_check_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSellShortSwtCheckDiv()
  {
    return sell_short_swt_check_div;
  }


  /** 
   * <em>sell_short_swt_check_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellShortSwtCheckDivIsSet() {
    return sell_short_swt_check_div_is_set;
  }


  /** 
   * <em>sell_short_swt_check_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellShortSwtCheckDivIsModified() {
    return sell_short_swt_check_div_is_modified;
  }


  /** 
   * <em>recruit_start_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRecruitStartStop()
  {
    return recruit_start_stop;
  }


  /** 
   * <em>recruit_start_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitStartStopIsSet() {
    return recruit_start_stop_is_set;
  }


  /** 
   * <em>recruit_start_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitStartStopIsModified() {
    return recruit_start_stop_is_modified;
  }


  /** 
   * <em>collateral_qualified_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCollateralQualifiedDiv()
  {
    return collateral_qualified_div;
  }


  /** 
   * <em>collateral_qualified_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralQualifiedDivIsSet() {
    return collateral_qualified_div_is_set;
  }


  /** 
   * <em>collateral_qualified_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralQualifiedDivIsModified() {
    return collateral_qualified_div_is_modified;
  }


  /** 
   * <em>collateral_evaluation</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCollateralEvaluation()
  {
    return ( collateral_evaluation==null? ((double)0): collateral_evaluation.doubleValue() );
  }


  /** 
   * <em>collateral_evaluation</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCollateralEvaluationIsNull()
  {
    return collateral_evaluation == null;
  }


  /** 
   * <em>collateral_evaluation</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralEvaluationIsSet() {
    return collateral_evaluation_is_set;
  }


  /** 
   * <em>collateral_evaluation</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralEvaluationIsModified() {
    return collateral_evaluation_is_modified;
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCollateralRatio()
  {
    return ( collateral_ratio==null? ((double)0): collateral_ratio.doubleValue() );
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCollateralRatioIsNull()
  {
    return collateral_ratio == null;
  }


  /** 
   * <em>collateral_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralRatioIsSet() {
    return collateral_ratio_is_set;
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCollateralRatioIsModified() {
    return collateral_ratio_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MutualFundProductSonarPK(product_code);
  }


  /** 
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>product_name_kana</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productNameKana <em>product_name_kana</em>�J�����̒l������킷32���ȉ���String�l 
   */
  public final void setProductNameKana( String p_productNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name_kana = p_productNameKana;
    product_name_kana_is_set = true;
    product_name_kana_is_modified = true;
  }


  /** 
   * <em>product_name_kanji</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productNameKanji <em>product_name_kanji</em>�J�����̒l������킷32���ȉ���String�l 
   */
  public final void setProductNameKanji( String p_productNameKanji )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name_kanji = p_productNameKanji;
    product_name_kanji_is_set = true;
    product_name_kanji_is_modified = true;
  }


  /** 
   * <em>product_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productDiv <em>product_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setProductDiv( String p_productDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_div = p_productDiv;
    product_div_is_set = true;
    product_div_is_modified = true;
  }


  /** 
   * <em>work_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_workDate <em>work_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setWorkDate( java.sql.Timestamp p_workDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = p_workDate;
    work_date_is_set = true;
    work_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>work_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setWorkDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    work_date_is_set = true;
    work_date_is_modified = true;
  }


  /** 
   * <em>effect_generating_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_effectGeneratingDate <em>effect_generating_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setEffectGeneratingDate( java.sql.Timestamp p_effectGeneratingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    effect_generating_date = p_effectGeneratingDate;
    effect_generating_date_is_set = true;
    effect_generating_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>effect_generating_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setEffectGeneratingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    effect_generating_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    effect_generating_date_is_set = true;
    effect_generating_date_is_modified = true;
  }


  /** 
   * <em>invalid_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_invalidDate <em>invalid_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setInvalidDate( java.sql.Timestamp p_invalidDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    invalid_date = p_invalidDate;
    invalid_date_is_set = true;
    invalid_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>invalid_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setInvalidDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    invalid_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    invalid_date_is_set = true;
    invalid_date_is_modified = true;
  }


  /** 
   * <em>closing_date1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_closingDate1 <em>closing_date1</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setClosingDate1( java.sql.Timestamp p_closingDate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    closing_date1 = p_closingDate1;
    closing_date1_is_set = true;
    closing_date1_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>closing_date1</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setClosingDate1( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    closing_date1 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    closing_date1_is_set = true;
    closing_date1_is_modified = true;
  }


  /** 
   * <em>closing_date2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_closingDate2 <em>closing_date2</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setClosingDate2( java.sql.Timestamp p_closingDate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    closing_date2 = p_closingDate2;
    closing_date2_is_set = true;
    closing_date2_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>closing_date2</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setClosingDate2( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    closing_date2 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    closing_date2_is_set = true;
    closing_date2_is_modified = true;
  }


  /** 
   * <em>redemption_extend_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_redemptionExtendDiv <em>redemption_extend_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRedemptionExtendDiv( String p_redemptionExtendDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_extend_div = p_redemptionExtendDiv;
    redemption_extend_div_is_set = true;
    redemption_extend_div_is_modified = true;
  }


  /** 
   * <em>redemption_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_redemptionDate <em>redemption_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setRedemptionDate( java.sql.Timestamp p_redemptionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_date = p_redemptionDate;
    redemption_date_is_set = true;
    redemption_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>redemption_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setRedemptionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    redemption_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    redemption_date_is_set = true;
    redemption_date_is_modified = true;
  }


  /** 
   * <em>first_recruitment_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_firstRecruitmentDate <em>first_recruitment_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setFirstRecruitmentDate( java.sql.Timestamp p_firstRecruitmentDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_recruitment_date = p_firstRecruitmentDate;
    first_recruitment_date_is_set = true;
    first_recruitment_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>first_recruitment_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setFirstRecruitmentDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_recruitment_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    first_recruitment_date_is_set = true;
    first_recruitment_date_is_modified = true;
  }


  /** 
   * <em>recruit_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recruitStartDate <em>recruit_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setRecruitStartDate( java.sql.Timestamp p_recruitStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_start_date = p_recruitStartDate;
    recruit_start_date_is_set = true;
    recruit_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>recruit_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setRecruitStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    recruit_start_date_is_set = true;
    recruit_start_date_is_modified = true;
  }


  /** 
   * <em>recruit_end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recruitEndDate <em>recruit_end_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setRecruitEndDate( java.sql.Timestamp p_recruitEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_end_date = p_recruitEndDate;
    recruit_end_date_is_set = true;
    recruit_end_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>recruit_end_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setRecruitEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    recruit_end_date_is_set = true;
    recruit_end_date_is_modified = true;
  }


  /** 
   * <em>recruit_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recruitPrice <em>recruit_price</em>�J�����̒l������킷5���ȉ���int�l 
   */
  public final void setRecruitPrice( int p_recruitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_price = new Integer(p_recruitPrice);
    recruit_price_is_set = true;
    recruit_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>recruit_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setRecruitPrice( Integer p_recruitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_price = p_recruitPrice;
    recruit_price_is_set = true;
    recruit_price_is_modified = true;
  }


  /** 
   * <em>payment_start_date1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentStartDate1 <em>payment_start_date1</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setPaymentStartDate1( java.sql.Timestamp p_paymentStartDate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_start_date1 = p_paymentStartDate1;
    payment_start_date1_is_set = true;
    payment_start_date1_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>payment_start_date1</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setPaymentStartDate1( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_start_date1 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    payment_start_date1_is_set = true;
    payment_start_date1_is_modified = true;
  }


  /** 
   * <em>payment_start_date2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentStartDate2 <em>payment_start_date2</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setPaymentStartDate2( java.sql.Timestamp p_paymentStartDate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_start_date2 = p_paymentStartDate2;
    payment_start_date2_is_set = true;
    payment_start_date2_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>payment_start_date2</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setPaymentStartDate2( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_start_date2 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    payment_start_date2_is_set = true;
    payment_start_date2_is_modified = true;
  }


  /** 
   * <em>storage_stop_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_storageStopFlag <em>storage_stop_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStorageStopFlag( String p_storageStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    storage_stop_flag = p_storageStopFlag;
    storage_stop_flag_is_set = true;
    storage_stop_flag_is_modified = true;
  }


  /** 
   * <em>trade_stop_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradeStopFlag <em>trade_stop_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTradeStopFlag( String p_tradeStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop_flag = p_tradeStopFlag;
    trade_stop_flag_is_set = true;
    trade_stop_flag_is_modified = true;
  }


  /** 
   * <em>obliterate_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_obliterateType <em>obliterate_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setObliterateType( String p_obliterateType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    obliterate_type = p_obliterateType;
    obliterate_type_is_set = true;
    obliterate_type_is_modified = true;
  }


  /** 
   * <em>corpus_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_corpusPrice <em>corpus_price</em>�J�����̒l������킷5���ȉ���int�l 
   */
  public final void setCorpusPrice( int p_corpusPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    corpus_price = new Integer(p_corpusPrice);
    corpus_price_is_set = true;
    corpus_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>corpus_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCorpusPrice( Integer p_corpusPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    corpus_price = p_corpusPrice;
    corpus_price_is_set = true;
    corpus_price_is_modified = true;
  }


  /** 
   * <em>open_close_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openCloseType <em>open_close_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOpenCloseType( String p_openCloseType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_close_type = p_openCloseType;
    open_close_type_is_set = true;
    open_close_type_is_modified = true;
  }


  /** 
   * <em>dayreport_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dayreportProductCode <em>dayreport_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setDayreportProductCode( String p_dayreportProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dayreport_product_code = p_dayreportProductCode;
    dayreport_product_code_is_set = true;
    dayreport_product_code_is_modified = true;
  }


  /** 
   * <em>recruit_sales</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recruitSales <em>recruit_sales</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRecruitSales( String p_recruitSales )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_sales = p_recruitSales;
    recruit_sales_is_set = true;
    recruit_sales_is_modified = true;
  }


  /** 
   * <em>stock_type_bond_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stockTypeBondType <em>stock_type_bond_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStockTypeBondType( String p_stockTypeBondType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stock_type_bond_type = p_stockTypeBondType;
    stock_type_bond_type_is_set = true;
    stock_type_bond_type_is_modified = true;
  }


  /** 
   * <em>contract_institution_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_contractInstitutionType <em>contract_institution_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setContractInstitutionType( String p_contractInstitutionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_institution_type = p_contractInstitutionType;
    contract_institution_type_is_set = true;
    contract_institution_type_is_modified = true;
  }


  /** 
   * <em>purchs_deduction_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_purchsDeductionStartDate <em>purchs_deduction_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setPurchsDeductionStartDate( java.sql.Timestamp p_purchsDeductionStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    purchs_deduction_start_date = p_purchsDeductionStartDate;
    purchs_deduction_start_date_is_set = true;
    purchs_deduction_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>purchs_deduction_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setPurchsDeductionStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    purchs_deduction_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    purchs_deduction_start_date_is_set = true;
    purchs_deduction_start_date_is_modified = true;
  }


  /** 
   * <em>spot_closing_date1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_spotClosingDate1 <em>spot_closing_date1</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSpotClosingDate1( java.sql.Timestamp p_spotClosingDate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spot_closing_date1 = p_spotClosingDate1;
    spot_closing_date1_is_set = true;
    spot_closing_date1_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>spot_closing_date1</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSpotClosingDate1( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spot_closing_date1 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    spot_closing_date1_is_set = true;
    spot_closing_date1_is_modified = true;
  }


  /** 
   * <em>spot_closing_date2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_spotClosingDate2 <em>spot_closing_date2</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSpotClosingDate2( java.sql.Timestamp p_spotClosingDate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spot_closing_date2 = p_spotClosingDate2;
    spot_closing_date2_is_set = true;
    spot_closing_date2_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>spot_closing_date2</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSpotClosingDate2( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spot_closing_date2 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    spot_closing_date2_is_set = true;
    spot_closing_date2_is_modified = true;
  }


  /** 
   * <em>calc_unit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcUnit <em>calc_unit</em>�J�����̒l������킷7���ȉ���int�l 
   */
  public final void setCalcUnit( int p_calcUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_unit = new Integer(p_calcUnit);
    calc_unit_is_set = true;
    calc_unit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>calc_unit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCalcUnit( Integer p_calcUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_unit = p_calcUnit;
    calc_unit_is_set = true;
    calc_unit_is_modified = true;
  }


  /** 
   * <em>biz_asset_product_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizAssetProductType <em>biz_asset_product_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setBizAssetProductType( String p_bizAssetProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_asset_product_type = p_bizAssetProductType;
    biz_asset_product_type_is_set = true;
    biz_asset_product_type_is_modified = true;
  }


  /** 
   * <em>biz_asset_evaluate_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizAssetEvaluatePrice <em>biz_asset_evaluate_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setBizAssetEvaluatePrice( double p_bizAssetEvaluatePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_asset_evaluate_price = new Double(p_bizAssetEvaluatePrice);
    biz_asset_evaluate_price_is_set = true;
    biz_asset_evaluate_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>biz_asset_evaluate_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBizAssetEvaluatePrice( Double p_bizAssetEvaluatePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    biz_asset_evaluate_price = p_bizAssetEvaluatePrice;
    biz_asset_evaluate_price_is_set = true;
    biz_asset_evaluate_price_is_modified = true;
  }


  /** 
   * <em>profit_balance_confirm_data</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_profitBalanceConfirmData <em>profit_balance_confirm_data</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setProfitBalanceConfirmData( java.sql.Timestamp p_profitBalanceConfirmData )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    profit_balance_confirm_data = p_profitBalanceConfirmData;
    profit_balance_confirm_data_is_set = true;
    profit_balance_confirm_data_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>profit_balance_confirm_data</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setProfitBalanceConfirmData( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    profit_balance_confirm_data = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    profit_balance_confirm_data_is_set = true;
    profit_balance_confirm_data_is_modified = true;
  }


  /** 
   * <em>profit_term_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_profitTermQuantity <em>profit_term_quantity</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setProfitTermQuantity( String p_profitTermQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    profit_term_quantity = p_profitTermQuantity;
    profit_term_quantity_is_set = true;
    profit_term_quantity_is_modified = true;
  }


  /** 
   * <em>general_profit_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_generalProfitPrice <em>general_profit_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setGeneralProfitPrice( double p_generalProfitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    general_profit_price = new Double(p_generalProfitPrice);
    general_profit_price_is_set = true;
    general_profit_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>general_profit_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setGeneralProfitPrice( Double p_generalProfitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    general_profit_price = p_generalProfitPrice;
    general_profit_price_is_set = true;
    general_profit_price_is_modified = true;
  }


  /** 
   * <em>spcprofit_distribution_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_spcprofitDistributionPrice <em>spcprofit_distribution_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSpcprofitDistributionPrice( double p_spcprofitDistributionPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spcprofit_distribution_price = new Double(p_spcprofitDistributionPrice);
    spcprofit_distribution_price_is_set = true;
    spcprofit_distribution_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>spcprofit_distribution_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSpcprofitDistributionPrice( Double p_spcprofitDistributionPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spcprofit_distribution_price = p_spcprofitDistributionPrice;
    spcprofit_distribution_price_is_set = true;
    spcprofit_distribution_price_is_modified = true;
  }


  /** 
   * <em>taxinlots_aftertax_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_taxinlotsAftertaxPrice <em>taxinlots_aftertax_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setTaxinlotsAftertaxPrice( double p_taxinlotsAftertaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxinlots_aftertax_price = new Double(p_taxinlotsAftertaxPrice);
    taxinlots_aftertax_price_is_set = true;
    taxinlots_aftertax_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>taxinlots_aftertax_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTaxinlotsAftertaxPrice( Double p_taxinlotsAftertaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    taxinlots_aftertax_price = p_taxinlotsAftertaxPrice;
    taxinlots_aftertax_price_is_set = true;
    taxinlots_aftertax_price_is_modified = true;
  }


  /** 
   * <em>taxaggregate_aftertax_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_taxaggregateAftertaxPrice <em>taxaggregate_aftertax_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setTaxaggregateAftertaxPrice( double p_taxaggregateAftertaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxaggregate_aftertax_price = new Double(p_taxaggregateAftertaxPrice);
    taxaggregate_aftertax_price_is_set = true;
    taxaggregate_aftertax_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>taxaggregate_aftertax_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTaxaggregateAftertaxPrice( Double p_taxaggregateAftertaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    taxaggregate_aftertax_price = p_taxaggregateAftertaxPrice;
    taxaggregate_aftertax_price_is_set = true;
    taxaggregate_aftertax_price_is_modified = true;
  }


  /** 
   * <em>pay_start_date_advanced_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_payStartDateAdvancedDiv <em>pay_start_date_advanced_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setPayStartDateAdvancedDiv( String p_payStartDateAdvancedDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_start_date_advanced_div = p_payStartDateAdvancedDiv;
    pay_start_date_advanced_div_is_set = true;
    pay_start_date_advanced_div_is_modified = true;
  }


  /** 
   * <em>method_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_methodType <em>method_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setMethodType( String p_methodType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    method_type = p_methodType;
    method_type_is_set = true;
    method_type_is_modified = true;
  }


  /** 
   * <em>setting_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_settingDate <em>setting_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSettingDate( java.sql.Timestamp p_settingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setting_date = p_settingDate;
    setting_date_is_set = true;
    setting_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>setting_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSettingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setting_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    setting_date_is_set = true;
    setting_date_is_modified = true;
  }


  /** 
   * <em>sell_forbidden_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellForbiddenDate <em>sell_forbidden_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSellForbiddenDate( java.sql.Timestamp p_sellForbiddenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_forbidden_date = p_sellForbiddenDate;
    sell_forbidden_date_is_set = true;
    sell_forbidden_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>sell_forbidden_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSellForbiddenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_forbidden_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sell_forbidden_date_is_set = true;
    sell_forbidden_date_is_modified = true;
  }


  /** 
   * <em>adding_forbidden_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_addingForbiddenDate <em>adding_forbidden_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAddingForbiddenDate( java.sql.Timestamp p_addingForbiddenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    adding_forbidden_date = p_addingForbiddenDate;
    adding_forbidden_date_is_set = true;
    adding_forbidden_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>adding_forbidden_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAddingForbiddenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    adding_forbidden_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    adding_forbidden_date_is_set = true;
    adding_forbidden_date_is_modified = true;
  }


  /** 
   * <em>profit_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_profitStartDate <em>profit_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setProfitStartDate( java.sql.Timestamp p_profitStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    profit_start_date = p_profitStartDate;
    profit_start_date_is_set = true;
    profit_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>profit_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setProfitStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    profit_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    profit_start_date_is_set = true;
    profit_start_date_is_modified = true;
  }


  /** 
   * <em>best_exception_product_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bestExceptionProductFlag <em>best_exception_product_flag</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setBestExceptionProductFlag( String p_bestExceptionProductFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    best_exception_product_flag = p_bestExceptionProductFlag;
    best_exception_product_flag_is_set = true;
    best_exception_product_flag_is_modified = true;
  }


  /** 
   * <em>currency_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currencyType <em>currency_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCurrencyType( String p_currencyType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_type = p_currencyType;
    currency_type_is_set = true;
    currency_type_is_modified = true;
  }


  /** 
   * <em>profit_distribution_regdate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_profitDistributionRegdate <em>profit_distribution_regdate</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setProfitDistributionRegdate( java.sql.Timestamp p_profitDistributionRegdate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    profit_distribution_regdate = p_profitDistributionRegdate;
    profit_distribution_regdate_is_set = true;
    profit_distribution_regdate_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>profit_distribution_regdate</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setProfitDistributionRegdate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    profit_distribution_regdate = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    profit_distribution_regdate_is_set = true;
    profit_distribution_regdate_is_modified = true;
  }


  /** 
   * <em>consign_contact_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_consignContactProductCode <em>consign_contact_product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setConsignContactProductCode( String p_consignContactProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    consign_contact_product_code = p_consignContactProductCode;
    consign_contact_product_code_is_set = true;
    consign_contact_product_code_is_modified = true;
  }


  /** 
   * <em>mutualassoc_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mutualassocProductCode <em>mutualassoc_product_code</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setMutualassocProductCode( String p_mutualassocProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutualassoc_product_code = p_mutualassocProductCode;
    mutualassoc_product_code_is_set = true;
    mutualassoc_product_code_is_modified = true;
  }


  /** 
   * <em>trust_bank_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_trustBankCode <em>trust_bank_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setTrustBankCode( String p_trustBankCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_bank_code = p_trustBankCode;
    trust_bank_code_is_set = true;
    trust_bank_code_is_modified = true;
  }


  /** 
   * <em>consign_institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_consignInstitutionCode <em>consign_institution_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setConsignInstitutionCode( String p_consignInstitutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    consign_institution_code = p_consignInstitutionCode;
    consign_institution_code_is_set = true;
    consign_institution_code_is_modified = true;
  }


  /** 
   * <em>average_trust_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_averageTrustPrice <em>average_trust_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAverageTrustPrice( double p_averageTrustPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    average_trust_price = new Double(p_averageTrustPrice);
    average_trust_price_is_set = true;
    average_trust_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>average_trust_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAverageTrustPrice( Double p_averageTrustPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    average_trust_price = p_averageTrustPrice;
    average_trust_price_is_set = true;
    average_trust_price_is_modified = true;
  }


  /** 
   * <em>same_check_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sameCheckDiv <em>same_check_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSameCheckDiv( String p_sameCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    same_check_div = p_sameCheckDiv;
    same_check_div_is_set = true;
    same_check_div_is_modified = true;
  }


  /** 
   * <em>same_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sameDiv <em>same_div</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setSameDiv( String p_sameDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    same_div = p_sameDiv;
    same_div_is_set = true;
    same_div_is_modified = true;
  }


  /** 
   * <em>recruit_short_swt_check_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recruitShortSwtCheckDiv <em>recruit_short_swt_check_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRecruitShortSwtCheckDiv( String p_recruitShortSwtCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_short_swt_check_div = p_recruitShortSwtCheckDiv;
    recruit_short_swt_check_div_is_set = true;
    recruit_short_swt_check_div_is_modified = true;
  }


  /** 
   * <em>buy_short_swt_check_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyShortSwtCheckDiv <em>buy_short_swt_check_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBuyShortSwtCheckDiv( String p_buyShortSwtCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_short_swt_check_div = p_buyShortSwtCheckDiv;
    buy_short_swt_check_div_is_set = true;
    buy_short_swt_check_div_is_modified = true;
  }


  /** 
   * <em>sell_short_swt_check_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellShortSwtCheckDiv <em>sell_short_swt_check_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSellShortSwtCheckDiv( String p_sellShortSwtCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_short_swt_check_div = p_sellShortSwtCheckDiv;
    sell_short_swt_check_div_is_set = true;
    sell_short_swt_check_div_is_modified = true;
  }


  /** 
   * <em>recruit_start_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recruitStartStop <em>recruit_start_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRecruitStartStop( String p_recruitStartStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_start_stop = p_recruitStartStop;
    recruit_start_stop_is_set = true;
    recruit_start_stop_is_modified = true;
  }


  /** 
   * <em>collateral_qualified_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_collateralQualifiedDiv <em>collateral_qualified_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCollateralQualifiedDiv( String p_collateralQualifiedDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_qualified_div = p_collateralQualifiedDiv;
    collateral_qualified_div_is_set = true;
    collateral_qualified_div_is_modified = true;
  }


  /** 
   * <em>collateral_evaluation</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_collateralEvaluation <em>collateral_evaluation</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCollateralEvaluation( double p_collateralEvaluation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_evaluation = new Double(p_collateralEvaluation);
    collateral_evaluation_is_set = true;
    collateral_evaluation_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>collateral_evaluation</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCollateralEvaluation( Double p_collateralEvaluation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_evaluation = p_collateralEvaluation;
    collateral_evaluation_is_set = true;
    collateral_evaluation_is_modified = true;
  }


  /** 
   * <em>collateral_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_collateralRatio <em>collateral_ratio</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCollateralRatio( double p_collateralRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_ratio = new Double(p_collateralRatio);
    collateral_ratio_is_set = true;
    collateral_ratio_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>collateral_ratio</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCollateralRatio( Double p_collateralRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    collateral_ratio = p_collateralRatio;
    collateral_ratio_is_set = true;
    collateral_ratio_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("adding_forbidden_date") ) {
                    return this.adding_forbidden_date;
                }
                else if ( name.equals("average_trust_price") ) {
                    return this.average_trust_price;
                }
                break;
            case 'b':
                if ( name.equals("biz_asset_product_type") ) {
                    return this.biz_asset_product_type;
                }
                else if ( name.equals("biz_asset_evaluate_price") ) {
                    return this.biz_asset_evaluate_price;
                }
                else if ( name.equals("best_exception_product_flag") ) {
                    return this.best_exception_product_flag;
                }
                else if ( name.equals("buy_short_swt_check_div") ) {
                    return this.buy_short_swt_check_div;
                }
                break;
            case 'c':
                if ( name.equals("closing_date1") ) {
                    return this.closing_date1;
                }
                else if ( name.equals("closing_date2") ) {
                    return this.closing_date2;
                }
                else if ( name.equals("corpus_price") ) {
                    return this.corpus_price;
                }
                else if ( name.equals("contract_institution_type") ) {
                    return this.contract_institution_type;
                }
                else if ( name.equals("calc_unit") ) {
                    return this.calc_unit;
                }
                else if ( name.equals("currency_type") ) {
                    return this.currency_type;
                }
                else if ( name.equals("consign_contact_product_code") ) {
                    return this.consign_contact_product_code;
                }
                else if ( name.equals("consign_institution_code") ) {
                    return this.consign_institution_code;
                }
                else if ( name.equals("collateral_qualified_div") ) {
                    return this.collateral_qualified_div;
                }
                else if ( name.equals("collateral_evaluation") ) {
                    return this.collateral_evaluation;
                }
                else if ( name.equals("collateral_ratio") ) {
                    return this.collateral_ratio;
                }
                break;
            case 'd':
                if ( name.equals("dayreport_product_code") ) {
                    return this.dayreport_product_code;
                }
                break;
            case 'e':
                if ( name.equals("effect_generating_date") ) {
                    return this.effect_generating_date;
                }
                break;
            case 'f':
                if ( name.equals("first_recruitment_date") ) {
                    return this.first_recruitment_date;
                }
                break;
            case 'g':
                if ( name.equals("general_profit_price") ) {
                    return this.general_profit_price;
                }
                break;
            case 'i':
                if ( name.equals("invalid_date") ) {
                    return this.invalid_date;
                }
                break;
            case 'm':
                if ( name.equals("method_type") ) {
                    return this.method_type;
                }
                else if ( name.equals("mutualassoc_product_code") ) {
                    return this.mutualassoc_product_code;
                }
                break;
            case 'o':
                if ( name.equals("obliterate_type") ) {
                    return this.obliterate_type;
                }
                else if ( name.equals("open_close_type") ) {
                    return this.open_close_type;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_name_kana") ) {
                    return this.product_name_kana;
                }
                else if ( name.equals("product_name_kanji") ) {
                    return this.product_name_kanji;
                }
                else if ( name.equals("product_div") ) {
                    return this.product_div;
                }
                else if ( name.equals("payment_start_date1") ) {
                    return this.payment_start_date1;
                }
                else if ( name.equals("payment_start_date2") ) {
                    return this.payment_start_date2;
                }
                else if ( name.equals("purchs_deduction_start_date") ) {
                    return this.purchs_deduction_start_date;
                }
                else if ( name.equals("profit_balance_confirm_data") ) {
                    return this.profit_balance_confirm_data;
                }
                else if ( name.equals("profit_term_quantity") ) {
                    return this.profit_term_quantity;
                }
                else if ( name.equals("pay_start_date_advanced_div") ) {
                    return this.pay_start_date_advanced_div;
                }
                else if ( name.equals("profit_start_date") ) {
                    return this.profit_start_date;
                }
                else if ( name.equals("profit_distribution_regdate") ) {
                    return this.profit_distribution_regdate;
                }
                break;
            case 'r':
                if ( name.equals("redemption_extend_div") ) {
                    return this.redemption_extend_div;
                }
                else if ( name.equals("redemption_date") ) {
                    return this.redemption_date;
                }
                else if ( name.equals("recruit_start_date") ) {
                    return this.recruit_start_date;
                }
                else if ( name.equals("recruit_end_date") ) {
                    return this.recruit_end_date;
                }
                else if ( name.equals("recruit_price") ) {
                    return this.recruit_price;
                }
                else if ( name.equals("recruit_sales") ) {
                    return this.recruit_sales;
                }
                else if ( name.equals("recruit_short_swt_check_div") ) {
                    return this.recruit_short_swt_check_div;
                }
                else if ( name.equals("recruit_start_stop") ) {
                    return this.recruit_start_stop;
                }
                break;
            case 's':
                if ( name.equals("storage_stop_flag") ) {
                    return this.storage_stop_flag;
                }
                else if ( name.equals("stock_type_bond_type") ) {
                    return this.stock_type_bond_type;
                }
                else if ( name.equals("spot_closing_date1") ) {
                    return this.spot_closing_date1;
                }
                else if ( name.equals("spot_closing_date2") ) {
                    return this.spot_closing_date2;
                }
                else if ( name.equals("spcprofit_distribution_price") ) {
                    return this.spcprofit_distribution_price;
                }
                else if ( name.equals("setting_date") ) {
                    return this.setting_date;
                }
                else if ( name.equals("sell_forbidden_date") ) {
                    return this.sell_forbidden_date;
                }
                else if ( name.equals("same_check_div") ) {
                    return this.same_check_div;
                }
                else if ( name.equals("same_div") ) {
                    return this.same_div;
                }
                else if ( name.equals("sell_short_swt_check_div") ) {
                    return this.sell_short_swt_check_div;
                }
                break;
            case 't':
                if ( name.equals("trade_stop_flag") ) {
                    return this.trade_stop_flag;
                }
                else if ( name.equals("taxinlots_aftertax_price") ) {
                    return this.taxinlots_aftertax_price;
                }
                else if ( name.equals("taxaggregate_aftertax_price") ) {
                    return this.taxaggregate_aftertax_price;
                }
                else if ( name.equals("trust_bank_code") ) {
                    return this.trust_bank_code;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    return this.work_date;
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
                if ( name.equals("adding_forbidden_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'adding_forbidden_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.adding_forbidden_date = (java.sql.Timestamp) value;
                    if (this.adding_forbidden_date_is_set)
                        this.adding_forbidden_date_is_modified = true;
                    this.adding_forbidden_date_is_set = true;
                    return;
                }
                else if ( name.equals("average_trust_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'average_trust_price' must be Double: '"+value+"' is not." );
                    this.average_trust_price = (Double) value;
                    if (this.average_trust_price_is_set)
                        this.average_trust_price_is_modified = true;
                    this.average_trust_price_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("biz_asset_product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_asset_product_type' must be String: '"+value+"' is not." );
                    this.biz_asset_product_type = (String) value;
                    if (this.biz_asset_product_type_is_set)
                        this.biz_asset_product_type_is_modified = true;
                    this.biz_asset_product_type_is_set = true;
                    return;
                }
                else if ( name.equals("biz_asset_evaluate_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'biz_asset_evaluate_price' must be Double: '"+value+"' is not." );
                    this.biz_asset_evaluate_price = (Double) value;
                    if (this.biz_asset_evaluate_price_is_set)
                        this.biz_asset_evaluate_price_is_modified = true;
                    this.biz_asset_evaluate_price_is_set = true;
                    return;
                }
                else if ( name.equals("best_exception_product_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'best_exception_product_flag' must be String: '"+value+"' is not." );
                    this.best_exception_product_flag = (String) value;
                    if (this.best_exception_product_flag_is_set)
                        this.best_exception_product_flag_is_modified = true;
                    this.best_exception_product_flag_is_set = true;
                    return;
                }
                else if ( name.equals("buy_short_swt_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_short_swt_check_div' must be String: '"+value+"' is not." );
                    this.buy_short_swt_check_div = (String) value;
                    if (this.buy_short_swt_check_div_is_set)
                        this.buy_short_swt_check_div_is_modified = true;
                    this.buy_short_swt_check_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("closing_date1") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'closing_date1' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.closing_date1 = (java.sql.Timestamp) value;
                    if (this.closing_date1_is_set)
                        this.closing_date1_is_modified = true;
                    this.closing_date1_is_set = true;
                    return;
                }
                else if ( name.equals("closing_date2") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'closing_date2' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.closing_date2 = (java.sql.Timestamp) value;
                    if (this.closing_date2_is_set)
                        this.closing_date2_is_modified = true;
                    this.closing_date2_is_set = true;
                    return;
                }
                else if ( name.equals("corpus_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'corpus_price' must be Integer: '"+value+"' is not." );
                    this.corpus_price = (Integer) value;
                    if (this.corpus_price_is_set)
                        this.corpus_price_is_modified = true;
                    this.corpus_price_is_set = true;
                    return;
                }
                else if ( name.equals("contract_institution_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contract_institution_type' must be String: '"+value+"' is not." );
                    this.contract_institution_type = (String) value;
                    if (this.contract_institution_type_is_set)
                        this.contract_institution_type_is_modified = true;
                    this.contract_institution_type_is_set = true;
                    return;
                }
                else if ( name.equals("calc_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'calc_unit' must be Integer: '"+value+"' is not." );
                    this.calc_unit = (Integer) value;
                    if (this.calc_unit_is_set)
                        this.calc_unit_is_modified = true;
                    this.calc_unit_is_set = true;
                    return;
                }
                else if ( name.equals("currency_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_type' must be String: '"+value+"' is not." );
                    this.currency_type = (String) value;
                    if (this.currency_type_is_set)
                        this.currency_type_is_modified = true;
                    this.currency_type_is_set = true;
                    return;
                }
                else if ( name.equals("consign_contact_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'consign_contact_product_code' must be String: '"+value+"' is not." );
                    this.consign_contact_product_code = (String) value;
                    if (this.consign_contact_product_code_is_set)
                        this.consign_contact_product_code_is_modified = true;
                    this.consign_contact_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("consign_institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'consign_institution_code' must be String: '"+value+"' is not." );
                    this.consign_institution_code = (String) value;
                    if (this.consign_institution_code_is_set)
                        this.consign_institution_code_is_modified = true;
                    this.consign_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("collateral_qualified_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'collateral_qualified_div' must be String: '"+value+"' is not." );
                    this.collateral_qualified_div = (String) value;
                    if (this.collateral_qualified_div_is_set)
                        this.collateral_qualified_div_is_modified = true;
                    this.collateral_qualified_div_is_set = true;
                    return;
                }
                else if ( name.equals("collateral_evaluation") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'collateral_evaluation' must be Double: '"+value+"' is not." );
                    this.collateral_evaluation = (Double) value;
                    if (this.collateral_evaluation_is_set)
                        this.collateral_evaluation_is_modified = true;
                    this.collateral_evaluation_is_set = true;
                    return;
                }
                else if ( name.equals("collateral_ratio") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'collateral_ratio' must be Double: '"+value+"' is not." );
                    this.collateral_ratio = (Double) value;
                    if (this.collateral_ratio_is_set)
                        this.collateral_ratio_is_modified = true;
                    this.collateral_ratio_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("dayreport_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dayreport_product_code' must be String: '"+value+"' is not." );
                    this.dayreport_product_code = (String) value;
                    if (this.dayreport_product_code_is_set)
                        this.dayreport_product_code_is_modified = true;
                    this.dayreport_product_code_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("effect_generating_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'effect_generating_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.effect_generating_date = (java.sql.Timestamp) value;
                    if (this.effect_generating_date_is_set)
                        this.effect_generating_date_is_modified = true;
                    this.effect_generating_date_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("first_recruitment_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'first_recruitment_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.first_recruitment_date = (java.sql.Timestamp) value;
                    if (this.first_recruitment_date_is_set)
                        this.first_recruitment_date_is_modified = true;
                    this.first_recruitment_date_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("general_profit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'general_profit_price' must be Double: '"+value+"' is not." );
                    this.general_profit_price = (Double) value;
                    if (this.general_profit_price_is_set)
                        this.general_profit_price_is_modified = true;
                    this.general_profit_price_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("invalid_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'invalid_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.invalid_date = (java.sql.Timestamp) value;
                    if (this.invalid_date_is_set)
                        this.invalid_date_is_modified = true;
                    this.invalid_date_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("method_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'method_type' must be String: '"+value+"' is not." );
                    this.method_type = (String) value;
                    if (this.method_type_is_set)
                        this.method_type_is_modified = true;
                    this.method_type_is_set = true;
                    return;
                }
                else if ( name.equals("mutualassoc_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mutualassoc_product_code' must be String: '"+value+"' is not." );
                    this.mutualassoc_product_code = (String) value;
                    if (this.mutualassoc_product_code_is_set)
                        this.mutualassoc_product_code_is_modified = true;
                    this.mutualassoc_product_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("obliterate_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'obliterate_type' must be String: '"+value+"' is not." );
                    this.obliterate_type = (String) value;
                    if (this.obliterate_type_is_set)
                        this.obliterate_type_is_modified = true;
                    this.obliterate_type_is_set = true;
                    return;
                }
                else if ( name.equals("open_close_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'open_close_type' must be String: '"+value+"' is not." );
                    this.open_close_type = (String) value;
                    if (this.open_close_type_is_set)
                        this.open_close_type_is_modified = true;
                    this.open_close_type_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_name_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name_kana' must be String: '"+value+"' is not." );
                    this.product_name_kana = (String) value;
                    if (this.product_name_kana_is_set)
                        this.product_name_kana_is_modified = true;
                    this.product_name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("product_name_kanji") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name_kanji' must be String: '"+value+"' is not." );
                    this.product_name_kanji = (String) value;
                    if (this.product_name_kanji_is_set)
                        this.product_name_kanji_is_modified = true;
                    this.product_name_kanji_is_set = true;
                    return;
                }
                else if ( name.equals("product_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_div' must be String: '"+value+"' is not." );
                    this.product_div = (String) value;
                    if (this.product_div_is_set)
                        this.product_div_is_modified = true;
                    this.product_div_is_set = true;
                    return;
                }
                else if ( name.equals("payment_start_date1") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'payment_start_date1' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.payment_start_date1 = (java.sql.Timestamp) value;
                    if (this.payment_start_date1_is_set)
                        this.payment_start_date1_is_modified = true;
                    this.payment_start_date1_is_set = true;
                    return;
                }
                else if ( name.equals("payment_start_date2") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'payment_start_date2' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.payment_start_date2 = (java.sql.Timestamp) value;
                    if (this.payment_start_date2_is_set)
                        this.payment_start_date2_is_modified = true;
                    this.payment_start_date2_is_set = true;
                    return;
                }
                else if ( name.equals("purchs_deduction_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'purchs_deduction_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.purchs_deduction_start_date = (java.sql.Timestamp) value;
                    if (this.purchs_deduction_start_date_is_set)
                        this.purchs_deduction_start_date_is_modified = true;
                    this.purchs_deduction_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("profit_balance_confirm_data") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'profit_balance_confirm_data' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.profit_balance_confirm_data = (java.sql.Timestamp) value;
                    if (this.profit_balance_confirm_data_is_set)
                        this.profit_balance_confirm_data_is_modified = true;
                    this.profit_balance_confirm_data_is_set = true;
                    return;
                }
                else if ( name.equals("profit_term_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'profit_term_quantity' must be String: '"+value+"' is not." );
                    this.profit_term_quantity = (String) value;
                    if (this.profit_term_quantity_is_set)
                        this.profit_term_quantity_is_modified = true;
                    this.profit_term_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("pay_start_date_advanced_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_start_date_advanced_div' must be String: '"+value+"' is not." );
                    this.pay_start_date_advanced_div = (String) value;
                    if (this.pay_start_date_advanced_div_is_set)
                        this.pay_start_date_advanced_div_is_modified = true;
                    this.pay_start_date_advanced_div_is_set = true;
                    return;
                }
                else if ( name.equals("profit_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'profit_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.profit_start_date = (java.sql.Timestamp) value;
                    if (this.profit_start_date_is_set)
                        this.profit_start_date_is_modified = true;
                    this.profit_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("profit_distribution_regdate") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'profit_distribution_regdate' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.profit_distribution_regdate = (java.sql.Timestamp) value;
                    if (this.profit_distribution_regdate_is_set)
                        this.profit_distribution_regdate_is_modified = true;
                    this.profit_distribution_regdate_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("redemption_extend_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'redemption_extend_div' must be String: '"+value+"' is not." );
                    this.redemption_extend_div = (String) value;
                    if (this.redemption_extend_div_is_set)
                        this.redemption_extend_div_is_modified = true;
                    this.redemption_extend_div_is_set = true;
                    return;
                }
                else if ( name.equals("redemption_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'redemption_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.redemption_date = (java.sql.Timestamp) value;
                    if (this.redemption_date_is_set)
                        this.redemption_date_is_modified = true;
                    this.redemption_date_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'recruit_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.recruit_start_date = (java.sql.Timestamp) value;
                    if (this.recruit_start_date_is_set)
                        this.recruit_start_date_is_modified = true;
                    this.recruit_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'recruit_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.recruit_end_date = (java.sql.Timestamp) value;
                    if (this.recruit_end_date_is_set)
                        this.recruit_end_date_is_modified = true;
                    this.recruit_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'recruit_price' must be Integer: '"+value+"' is not." );
                    this.recruit_price = (Integer) value;
                    if (this.recruit_price_is_set)
                        this.recruit_price_is_modified = true;
                    this.recruit_price_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_sales") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_sales' must be String: '"+value+"' is not." );
                    this.recruit_sales = (String) value;
                    if (this.recruit_sales_is_set)
                        this.recruit_sales_is_modified = true;
                    this.recruit_sales_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_short_swt_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_short_swt_check_div' must be String: '"+value+"' is not." );
                    this.recruit_short_swt_check_div = (String) value;
                    if (this.recruit_short_swt_check_div_is_set)
                        this.recruit_short_swt_check_div_is_modified = true;
                    this.recruit_short_swt_check_div_is_set = true;
                    return;
                }
                else if ( name.equals("recruit_start_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_start_stop' must be String: '"+value+"' is not." );
                    this.recruit_start_stop = (String) value;
                    if (this.recruit_start_stop_is_set)
                        this.recruit_start_stop_is_modified = true;
                    this.recruit_start_stop_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("storage_stop_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'storage_stop_flag' must be String: '"+value+"' is not." );
                    this.storage_stop_flag = (String) value;
                    if (this.storage_stop_flag_is_set)
                        this.storage_stop_flag_is_modified = true;
                    this.storage_stop_flag_is_set = true;
                    return;
                }
                else if ( name.equals("stock_type_bond_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stock_type_bond_type' must be String: '"+value+"' is not." );
                    this.stock_type_bond_type = (String) value;
                    if (this.stock_type_bond_type_is_set)
                        this.stock_type_bond_type_is_modified = true;
                    this.stock_type_bond_type_is_set = true;
                    return;
                }
                else if ( name.equals("spot_closing_date1") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'spot_closing_date1' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.spot_closing_date1 = (java.sql.Timestamp) value;
                    if (this.spot_closing_date1_is_set)
                        this.spot_closing_date1_is_modified = true;
                    this.spot_closing_date1_is_set = true;
                    return;
                }
                else if ( name.equals("spot_closing_date2") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'spot_closing_date2' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.spot_closing_date2 = (java.sql.Timestamp) value;
                    if (this.spot_closing_date2_is_set)
                        this.spot_closing_date2_is_modified = true;
                    this.spot_closing_date2_is_set = true;
                    return;
                }
                else if ( name.equals("spcprofit_distribution_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'spcprofit_distribution_price' must be Double: '"+value+"' is not." );
                    this.spcprofit_distribution_price = (Double) value;
                    if (this.spcprofit_distribution_price_is_set)
                        this.spcprofit_distribution_price_is_modified = true;
                    this.spcprofit_distribution_price_is_set = true;
                    return;
                }
                else if ( name.equals("setting_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'setting_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.setting_date = (java.sql.Timestamp) value;
                    if (this.setting_date_is_set)
                        this.setting_date_is_modified = true;
                    this.setting_date_is_set = true;
                    return;
                }
                else if ( name.equals("sell_forbidden_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sell_forbidden_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sell_forbidden_date = (java.sql.Timestamp) value;
                    if (this.sell_forbidden_date_is_set)
                        this.sell_forbidden_date_is_modified = true;
                    this.sell_forbidden_date_is_set = true;
                    return;
                }
                else if ( name.equals("same_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'same_check_div' must be String: '"+value+"' is not." );
                    this.same_check_div = (String) value;
                    if (this.same_check_div_is_set)
                        this.same_check_div_is_modified = true;
                    this.same_check_div_is_set = true;
                    return;
                }
                else if ( name.equals("same_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'same_div' must be String: '"+value+"' is not." );
                    this.same_div = (String) value;
                    if (this.same_div_is_set)
                        this.same_div_is_modified = true;
                    this.same_div_is_set = true;
                    return;
                }
                else if ( name.equals("sell_short_swt_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_short_swt_check_div' must be String: '"+value+"' is not." );
                    this.sell_short_swt_check_div = (String) value;
                    if (this.sell_short_swt_check_div_is_set)
                        this.sell_short_swt_check_div_is_modified = true;
                    this.sell_short_swt_check_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trade_stop_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_stop_flag' must be String: '"+value+"' is not." );
                    this.trade_stop_flag = (String) value;
                    if (this.trade_stop_flag_is_set)
                        this.trade_stop_flag_is_modified = true;
                    this.trade_stop_flag_is_set = true;
                    return;
                }
                else if ( name.equals("taxinlots_aftertax_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'taxinlots_aftertax_price' must be Double: '"+value+"' is not." );
                    this.taxinlots_aftertax_price = (Double) value;
                    if (this.taxinlots_aftertax_price_is_set)
                        this.taxinlots_aftertax_price_is_modified = true;
                    this.taxinlots_aftertax_price_is_set = true;
                    return;
                }
                else if ( name.equals("taxaggregate_aftertax_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'taxaggregate_aftertax_price' must be Double: '"+value+"' is not." );
                    this.taxaggregate_aftertax_price = (Double) value;
                    if (this.taxaggregate_aftertax_price_is_set)
                        this.taxaggregate_aftertax_price_is_modified = true;
                    this.taxaggregate_aftertax_price_is_set = true;
                    return;
                }
                else if ( name.equals("trust_bank_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trust_bank_code' must be String: '"+value+"' is not." );
                    this.trust_bank_code = (String) value;
                    if (this.trust_bank_code_is_set)
                        this.trust_bank_code_is_modified = true;
                    this.trust_bank_code_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'work_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.work_date = (java.sql.Timestamp) value;
                    if (this.work_date_is_set)
                        this.work_date_is_modified = true;
                    this.work_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
