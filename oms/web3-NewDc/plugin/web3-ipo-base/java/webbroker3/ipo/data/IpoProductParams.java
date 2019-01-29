head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ipo_product�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link IpoProductRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link IpoProductParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link IpoProductParams}��{@@link IpoProductRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoProductPK 
 * @@see IpoProductRow 
 */
public class IpoProductParams extends Params implements IpoProductRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ipo_product";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = IpoProductRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return IpoProductRow.TYPE;
  }


  /** 
   * <em>ipo_product_id</em>�J�����̒l 
   */
  public  long  ipo_product_id;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>standard_name</em>�J�����̒l 
   */
  public  String  standard_name;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>ipo_regist_div</em>�J�����̒l 
   */
  public  String  ipo_regist_div;

  /** 
   * <em>ipo_regist_detail_div</em>�J�����̒l 
   */
  public  String  ipo_regist_detail_div;

  /** 
   * <em>public_offering_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  public_offering_date;

  /** 
   * <em>public_offering_date_count</em>�J�����̒l 
   */
  public  Integer  public_offering_date_count;

  /** 
   * <em>public_market</em>�J�����̒l 
   */
  public  String  public_market;

  /** 
   * <em>provisional_value_div</em>�J�����̒l 
   */
  public  String  provisional_value_div;

  /** 
   * <em>provisional_min_value</em>�J�����̒l 
   */
  public  Double  provisional_min_value;

  /** 
   * <em>provisional_max_value</em>�J�����̒l 
   */
  public  Double  provisional_max_value;

  /** 
   * <em>provisional_value_open_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  provisional_value_open_date;

  /** 
   * <em>public_offering_quantity</em>�J�����̒l 
   */
  public  Long  public_offering_quantity;

  /** 
   * <em>public_sales_quantity</em>�J�����̒l 
   */
  public  Long  public_sales_quantity;

  /** 
   * <em>dealing_quantity</em>�J�����̒l 
   */
  public  Long  dealing_quantity;

  /** 
   * <em>lead_managing_underwriter</em>�J�����̒l 
   */
  public  String  lead_managing_underwriter;

  /** 
   * <em>lot_size</em>�J�����̒l 
   */
  public  Long  lot_size;

  /** 
   * <em>tick_value</em>�J�����̒l 
   */
  public  Double  tick_value;

  /** 
   * <em>ipo_unit_div</em>�J�����̒l 
   */
  public  String  ipo_unit_div;

  /** 
   * <em>enable_market_order</em>�J�����̒l 
   */
  public  String  enable_market_order;

  /** 
   * <em>bookbuilding_start_datetime</em>�J�����̒l 
   */
  public  java.sql.Timestamp  bookbuilding_start_datetime;

  /** 
   * <em>bookbuilding_end_datetime</em>�J�����̒l 
   */
  public  java.sql.Timestamp  bookbuilding_end_datetime;

  /** 
   * <em>public_price_settle_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  public_price_settle_date;

  /** 
   * <em>public_price</em>�J�����̒l 
   */
  public  Double  public_price;

  /** 
   * <em>public_price_discount_rate</em>�J�����̒l 
   */
  public  Double  public_price_discount_rate;

  /** 
   * <em>lot_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  lot_date;

  /** 
   * <em>lot_date_count</em>�J�����̒l 
   */
  public  Integer  lot_date_count;

  /** 
   * <em>lot_status</em>�J�����̒l 
   */
  public  String  lot_status;

  /** 
   * <em>offer_start_datetime</em>�J�����̒l 
   */
  public  java.sql.Timestamp  offer_start_datetime;

  /** 
   * <em>offer_start_date_count</em>�J�����̒l 
   */
  public  Integer  offer_start_date_count;

  /** 
   * <em>offer_end_datetime</em>�J�����̒l 
   */
  public  java.sql.Timestamp  offer_end_datetime;

  /** 
   * <em>offer_end_date_count</em>�J�����̒l 
   */
  public  Integer  offer_end_date_count;

  /** 
   * <em>offer_start_date_prospec</em>�J�����̒l 
   */
  public  java.sql.Timestamp  offer_start_date_prospec;

  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l 
   */
  public  Integer  offer_start_date_count_prospec;

  /** 
   * <em>offer_end_date_prospec</em>�J�����̒l 
   */
  public  java.sql.Timestamp  offer_end_date_prospec;

  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l 
   */
  public  Integer  offer_end_date_count_prospec;

  /** 
   * <em>company_logo_url</em>�J�����̒l 
   */
  public  String  company_logo_url;

  /** 
   * <em>company_url</em>�J�����̒l 
   */
  public  String  company_url;

  /** 
   * <em>company_outline</em>�J�����̒l 
   */
  public  String  company_outline;

  /** 
   * <em>note</em>�J�����̒l 
   */
  public  String  note;

  /** 
   * <em>ipo_stop</em>�J�����̒l 
   */
  public  String  ipo_stop;

  /** 
   * <em>delete_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

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

  /** 
   * <em>doc_reading_div</em>�J�����̒l 
   */
  public  String  doc_reading_div;

  private boolean ipo_product_id_is_set = false;
  private boolean ipo_product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean ipo_regist_div_is_set = false;
  private boolean ipo_regist_div_is_modified = false;
  private boolean ipo_regist_detail_div_is_set = false;
  private boolean ipo_regist_detail_div_is_modified = false;
  private boolean public_offering_date_is_set = false;
  private boolean public_offering_date_is_modified = false;
  private boolean public_offering_date_count_is_set = false;
  private boolean public_offering_date_count_is_modified = false;
  private boolean public_market_is_set = false;
  private boolean public_market_is_modified = false;
  private boolean provisional_value_div_is_set = false;
  private boolean provisional_value_div_is_modified = false;
  private boolean provisional_min_value_is_set = false;
  private boolean provisional_min_value_is_modified = false;
  private boolean provisional_max_value_is_set = false;
  private boolean provisional_max_value_is_modified = false;
  private boolean provisional_value_open_date_is_set = false;
  private boolean provisional_value_open_date_is_modified = false;
  private boolean public_offering_quantity_is_set = false;
  private boolean public_offering_quantity_is_modified = false;
  private boolean public_sales_quantity_is_set = false;
  private boolean public_sales_quantity_is_modified = false;
  private boolean dealing_quantity_is_set = false;
  private boolean dealing_quantity_is_modified = false;
  private boolean lead_managing_underwriter_is_set = false;
  private boolean lead_managing_underwriter_is_modified = false;
  private boolean lot_size_is_set = false;
  private boolean lot_size_is_modified = false;
  private boolean tick_value_is_set = false;
  private boolean tick_value_is_modified = false;
  private boolean ipo_unit_div_is_set = false;
  private boolean ipo_unit_div_is_modified = false;
  private boolean enable_market_order_is_set = false;
  private boolean enable_market_order_is_modified = false;
  private boolean bookbuilding_start_datetime_is_set = false;
  private boolean bookbuilding_start_datetime_is_modified = false;
  private boolean bookbuilding_end_datetime_is_set = false;
  private boolean bookbuilding_end_datetime_is_modified = false;
  private boolean public_price_settle_date_is_set = false;
  private boolean public_price_settle_date_is_modified = false;
  private boolean public_price_is_set = false;
  private boolean public_price_is_modified = false;
  private boolean public_price_discount_rate_is_set = false;
  private boolean public_price_discount_rate_is_modified = false;
  private boolean lot_date_is_set = false;
  private boolean lot_date_is_modified = false;
  private boolean lot_date_count_is_set = false;
  private boolean lot_date_count_is_modified = false;
  private boolean lot_status_is_set = false;
  private boolean lot_status_is_modified = false;
  private boolean offer_start_datetime_is_set = false;
  private boolean offer_start_datetime_is_modified = false;
  private boolean offer_start_date_count_is_set = false;
  private boolean offer_start_date_count_is_modified = false;
  private boolean offer_end_datetime_is_set = false;
  private boolean offer_end_datetime_is_modified = false;
  private boolean offer_end_date_count_is_set = false;
  private boolean offer_end_date_count_is_modified = false;
  private boolean offer_start_date_prospec_is_set = false;
  private boolean offer_start_date_prospec_is_modified = false;
  private boolean offer_start_date_count_prospec_is_set = false;
  private boolean offer_start_date_count_prospec_is_modified = false;
  private boolean offer_end_date_prospec_is_set = false;
  private boolean offer_end_date_prospec_is_modified = false;
  private boolean offer_end_date_count_prospec_is_set = false;
  private boolean offer_end_date_count_prospec_is_modified = false;
  private boolean company_logo_url_is_set = false;
  private boolean company_logo_url_is_modified = false;
  private boolean company_url_is_set = false;
  private boolean company_url_is_modified = false;
  private boolean company_outline_is_set = false;
  private boolean company_outline_is_modified = false;
  private boolean note_is_set = false;
  private boolean note_is_modified = false;
  private boolean ipo_stop_is_set = false;
  private boolean ipo_stop_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean doc_reading_div_is_set = false;
  private boolean doc_reading_div_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "ipo_product_id=" + ipo_product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "standard_name=" +standard_name
      + "," + "product_type=" +product_type
      + "," + "ipo_regist_div=" +ipo_regist_div
      + "," + "ipo_regist_detail_div=" +ipo_regist_detail_div
      + "," + "public_offering_date=" +public_offering_date
      + "," + "public_offering_date_count=" +public_offering_date_count
      + "," + "public_market=" +public_market
      + "," + "provisional_value_div=" +provisional_value_div
      + "," + "provisional_min_value=" +provisional_min_value
      + "," + "provisional_max_value=" +provisional_max_value
      + "," + "provisional_value_open_date=" +provisional_value_open_date
      + "," + "public_offering_quantity=" +public_offering_quantity
      + "," + "public_sales_quantity=" +public_sales_quantity
      + "," + "dealing_quantity=" +dealing_quantity
      + "," + "lead_managing_underwriter=" +lead_managing_underwriter
      + "," + "lot_size=" +lot_size
      + "," + "tick_value=" +tick_value
      + "," + "ipo_unit_div=" +ipo_unit_div
      + "," + "enable_market_order=" +enable_market_order
      + "," + "bookbuilding_start_datetime=" +bookbuilding_start_datetime
      + "," + "bookbuilding_end_datetime=" +bookbuilding_end_datetime
      + "," + "public_price_settle_date=" +public_price_settle_date
      + "," + "public_price=" +public_price
      + "," + "public_price_discount_rate=" +public_price_discount_rate
      + "," + "lot_date=" +lot_date
      + "," + "lot_date_count=" +lot_date_count
      + "," + "lot_status=" +lot_status
      + "," + "offer_start_datetime=" +offer_start_datetime
      + "," + "offer_start_date_count=" +offer_start_date_count
      + "," + "offer_end_datetime=" +offer_end_datetime
      + "," + "offer_end_date_count=" +offer_end_date_count
      + "," + "offer_start_date_prospec=" +offer_start_date_prospec
      + "," + "offer_start_date_count_prospec=" +offer_start_date_count_prospec
      + "," + "offer_end_date_prospec=" +offer_end_date_prospec
      + "," + "offer_end_date_count_prospec=" +offer_end_date_count_prospec
      + "," + "company_logo_url=" +company_logo_url
      + "," + "company_url=" +company_url
      + "," + "company_outline=" +company_outline
      + "," + "note=" +note
      + "," + "ipo_stop=" +ipo_stop
      + "," + "delete_flag=" +delete_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "doc_reading_div=" +doc_reading_div
      + "}";
  }


  /** 
   * �l�����ݒ��IpoProductParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public IpoProductParams() {
    ipo_product_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���IpoProductRow�I�u�W�F�N�g�̒l�𗘗p����IpoProductParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����IpoProductRow�I�u�W�F�N�g 
   */
  public IpoProductParams( IpoProductRow p_row )
  {
    ipo_product_id = p_row.getIpoProductId();
    ipo_product_id_is_set = p_row.getIpoProductIdIsSet();
    ipo_product_id_is_modified = p_row.getIpoProductIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    ipo_regist_div = p_row.getIpoRegistDiv();
    ipo_regist_div_is_set = p_row.getIpoRegistDivIsSet();
    ipo_regist_div_is_modified = p_row.getIpoRegistDivIsModified();
    ipo_regist_detail_div = p_row.getIpoRegistDetailDiv();
    ipo_regist_detail_div_is_set = p_row.getIpoRegistDetailDivIsSet();
    ipo_regist_detail_div_is_modified = p_row.getIpoRegistDetailDivIsModified();
    public_offering_date = p_row.getPublicOfferingDate();
    public_offering_date_is_set = p_row.getPublicOfferingDateIsSet();
    public_offering_date_is_modified = p_row.getPublicOfferingDateIsModified();
    if ( !p_row.getPublicOfferingDateCountIsNull() )
      public_offering_date_count = new Integer( p_row.getPublicOfferingDateCount() );
    public_offering_date_count_is_set = p_row.getPublicOfferingDateCountIsSet();
    public_offering_date_count_is_modified = p_row.getPublicOfferingDateCountIsModified();
    public_market = p_row.getPublicMarket();
    public_market_is_set = p_row.getPublicMarketIsSet();
    public_market_is_modified = p_row.getPublicMarketIsModified();
    provisional_value_div = p_row.getProvisionalValueDiv();
    provisional_value_div_is_set = p_row.getProvisionalValueDivIsSet();
    provisional_value_div_is_modified = p_row.getProvisionalValueDivIsModified();
    if ( !p_row.getProvisionalMinValueIsNull() )
      provisional_min_value = new Double( p_row.getProvisionalMinValue() );
    provisional_min_value_is_set = p_row.getProvisionalMinValueIsSet();
    provisional_min_value_is_modified = p_row.getProvisionalMinValueIsModified();
    if ( !p_row.getProvisionalMaxValueIsNull() )
      provisional_max_value = new Double( p_row.getProvisionalMaxValue() );
    provisional_max_value_is_set = p_row.getProvisionalMaxValueIsSet();
    provisional_max_value_is_modified = p_row.getProvisionalMaxValueIsModified();
    provisional_value_open_date = p_row.getProvisionalValueOpenDate();
    provisional_value_open_date_is_set = p_row.getProvisionalValueOpenDateIsSet();
    provisional_value_open_date_is_modified = p_row.getProvisionalValueOpenDateIsModified();
    if ( !p_row.getPublicOfferingQuantityIsNull() )
      public_offering_quantity = new Long( p_row.getPublicOfferingQuantity() );
    public_offering_quantity_is_set = p_row.getPublicOfferingQuantityIsSet();
    public_offering_quantity_is_modified = p_row.getPublicOfferingQuantityIsModified();
    if ( !p_row.getPublicSalesQuantityIsNull() )
      public_sales_quantity = new Long( p_row.getPublicSalesQuantity() );
    public_sales_quantity_is_set = p_row.getPublicSalesQuantityIsSet();
    public_sales_quantity_is_modified = p_row.getPublicSalesQuantityIsModified();
    if ( !p_row.getDealingQuantityIsNull() )
      dealing_quantity = new Long( p_row.getDealingQuantity() );
    dealing_quantity_is_set = p_row.getDealingQuantityIsSet();
    dealing_quantity_is_modified = p_row.getDealingQuantityIsModified();
    lead_managing_underwriter = p_row.getLeadManagingUnderwriter();
    lead_managing_underwriter_is_set = p_row.getLeadManagingUnderwriterIsSet();
    lead_managing_underwriter_is_modified = p_row.getLeadManagingUnderwriterIsModified();
    if ( !p_row.getLotSizeIsNull() )
      lot_size = new Long( p_row.getLotSize() );
    lot_size_is_set = p_row.getLotSizeIsSet();
    lot_size_is_modified = p_row.getLotSizeIsModified();
    if ( !p_row.getTickValueIsNull() )
      tick_value = new Double( p_row.getTickValue() );
    tick_value_is_set = p_row.getTickValueIsSet();
    tick_value_is_modified = p_row.getTickValueIsModified();
    ipo_unit_div = p_row.getIpoUnitDiv();
    ipo_unit_div_is_set = p_row.getIpoUnitDivIsSet();
    ipo_unit_div_is_modified = p_row.getIpoUnitDivIsModified();
    enable_market_order = p_row.getEnableMarketOrder();
    enable_market_order_is_set = p_row.getEnableMarketOrderIsSet();
    enable_market_order_is_modified = p_row.getEnableMarketOrderIsModified();
    bookbuilding_start_datetime = p_row.getBookbuildingStartDatetime();
    bookbuilding_start_datetime_is_set = p_row.getBookbuildingStartDatetimeIsSet();
    bookbuilding_start_datetime_is_modified = p_row.getBookbuildingStartDatetimeIsModified();
    bookbuilding_end_datetime = p_row.getBookbuildingEndDatetime();
    bookbuilding_end_datetime_is_set = p_row.getBookbuildingEndDatetimeIsSet();
    bookbuilding_end_datetime_is_modified = p_row.getBookbuildingEndDatetimeIsModified();
    public_price_settle_date = p_row.getPublicPriceSettleDate();
    public_price_settle_date_is_set = p_row.getPublicPriceSettleDateIsSet();
    public_price_settle_date_is_modified = p_row.getPublicPriceSettleDateIsModified();
    if ( !p_row.getPublicPriceIsNull() )
      public_price = new Double( p_row.getPublicPrice() );
    public_price_is_set = p_row.getPublicPriceIsSet();
    public_price_is_modified = p_row.getPublicPriceIsModified();
    if ( !p_row.getPublicPriceDiscountRateIsNull() )
      public_price_discount_rate = new Double( p_row.getPublicPriceDiscountRate() );
    public_price_discount_rate_is_set = p_row.getPublicPriceDiscountRateIsSet();
    public_price_discount_rate_is_modified = p_row.getPublicPriceDiscountRateIsModified();
    lot_date = p_row.getLotDate();
    lot_date_is_set = p_row.getLotDateIsSet();
    lot_date_is_modified = p_row.getLotDateIsModified();
    if ( !p_row.getLotDateCountIsNull() )
      lot_date_count = new Integer( p_row.getLotDateCount() );
    lot_date_count_is_set = p_row.getLotDateCountIsSet();
    lot_date_count_is_modified = p_row.getLotDateCountIsModified();
    lot_status = p_row.getLotStatus();
    lot_status_is_set = p_row.getLotStatusIsSet();
    lot_status_is_modified = p_row.getLotStatusIsModified();
    offer_start_datetime = p_row.getOfferStartDatetime();
    offer_start_datetime_is_set = p_row.getOfferStartDatetimeIsSet();
    offer_start_datetime_is_modified = p_row.getOfferStartDatetimeIsModified();
    if ( !p_row.getOfferStartDateCountIsNull() )
      offer_start_date_count = new Integer( p_row.getOfferStartDateCount() );
    offer_start_date_count_is_set = p_row.getOfferStartDateCountIsSet();
    offer_start_date_count_is_modified = p_row.getOfferStartDateCountIsModified();
    offer_end_datetime = p_row.getOfferEndDatetime();
    offer_end_datetime_is_set = p_row.getOfferEndDatetimeIsSet();
    offer_end_datetime_is_modified = p_row.getOfferEndDatetimeIsModified();
    if ( !p_row.getOfferEndDateCountIsNull() )
      offer_end_date_count = new Integer( p_row.getOfferEndDateCount() );
    offer_end_date_count_is_set = p_row.getOfferEndDateCountIsSet();
    offer_end_date_count_is_modified = p_row.getOfferEndDateCountIsModified();
    offer_start_date_prospec = p_row.getOfferStartDateProspec();
    offer_start_date_prospec_is_set = p_row.getOfferStartDateProspecIsSet();
    offer_start_date_prospec_is_modified = p_row.getOfferStartDateProspecIsModified();
    if ( !p_row.getOfferStartDateCountProspecIsNull() )
      offer_start_date_count_prospec = new Integer( p_row.getOfferStartDateCountProspec() );
    offer_start_date_count_prospec_is_set = p_row.getOfferStartDateCountProspecIsSet();
    offer_start_date_count_prospec_is_modified = p_row.getOfferStartDateCountProspecIsModified();
    offer_end_date_prospec = p_row.getOfferEndDateProspec();
    offer_end_date_prospec_is_set = p_row.getOfferEndDateProspecIsSet();
    offer_end_date_prospec_is_modified = p_row.getOfferEndDateProspecIsModified();
    if ( !p_row.getOfferEndDateCountProspecIsNull() )
      offer_end_date_count_prospec = new Integer( p_row.getOfferEndDateCountProspec() );
    offer_end_date_count_prospec_is_set = p_row.getOfferEndDateCountProspecIsSet();
    offer_end_date_count_prospec_is_modified = p_row.getOfferEndDateCountProspecIsModified();
    company_logo_url = p_row.getCompanyLogoUrl();
    company_logo_url_is_set = p_row.getCompanyLogoUrlIsSet();
    company_logo_url_is_modified = p_row.getCompanyLogoUrlIsModified();
    company_url = p_row.getCompanyUrl();
    company_url_is_set = p_row.getCompanyUrlIsSet();
    company_url_is_modified = p_row.getCompanyUrlIsModified();
    company_outline = p_row.getCompanyOutline();
    company_outline_is_set = p_row.getCompanyOutlineIsSet();
    company_outline_is_modified = p_row.getCompanyOutlineIsModified();
    note = p_row.getNote();
    note_is_set = p_row.getNoteIsSet();
    note_is_modified = p_row.getNoteIsModified();
    ipo_stop = p_row.getIpoStop();
    ipo_stop_is_set = p_row.getIpoStopIsSet();
    ipo_stop_is_modified = p_row.getIpoStopIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    doc_reading_div = p_row.getDocReadingDiv();
    doc_reading_div_is_set = p_row.getDocReadingDivIsSet();
    doc_reading_div_is_modified = p_row.getDocReadingDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      ipo_regist_div_is_set = true;
      ipo_regist_div_is_modified = true;
      ipo_regist_detail_div_is_set = true;
      ipo_regist_detail_div_is_modified = true;
      public_offering_date_is_set = true;
      public_offering_date_is_modified = true;
      public_offering_date_count_is_set = true;
      public_offering_date_count_is_modified = true;
      public_market_is_set = true;
      public_market_is_modified = true;
      provisional_value_div_is_set = true;
      provisional_value_div_is_modified = true;
      provisional_min_value_is_set = true;
      provisional_min_value_is_modified = true;
      provisional_max_value_is_set = true;
      provisional_max_value_is_modified = true;
      provisional_value_open_date_is_set = true;
      provisional_value_open_date_is_modified = true;
      public_offering_quantity_is_set = true;
      public_offering_quantity_is_modified = true;
      public_sales_quantity_is_set = true;
      public_sales_quantity_is_modified = true;
      dealing_quantity_is_set = true;
      dealing_quantity_is_modified = true;
      lead_managing_underwriter_is_set = true;
      lead_managing_underwriter_is_modified = true;
      lot_size_is_set = true;
      lot_size_is_modified = true;
      tick_value_is_set = true;
      tick_value_is_modified = true;
      ipo_unit_div_is_set = true;
      ipo_unit_div_is_modified = true;
      enable_market_order_is_set = true;
      enable_market_order_is_modified = true;
      bookbuilding_start_datetime_is_set = true;
      bookbuilding_start_datetime_is_modified = true;
      bookbuilding_end_datetime_is_set = true;
      bookbuilding_end_datetime_is_modified = true;
      public_price_settle_date_is_set = true;
      public_price_settle_date_is_modified = true;
      public_price_is_set = true;
      public_price_is_modified = true;
      public_price_discount_rate_is_set = true;
      public_price_discount_rate_is_modified = true;
      lot_date_is_set = true;
      lot_date_is_modified = true;
      lot_date_count_is_set = true;
      lot_date_count_is_modified = true;
      lot_status_is_set = true;
      lot_status_is_modified = true;
      offer_start_datetime_is_set = true;
      offer_start_datetime_is_modified = true;
      offer_start_date_count_is_set = true;
      offer_start_date_count_is_modified = true;
      offer_end_datetime_is_set = true;
      offer_end_datetime_is_modified = true;
      offer_end_date_count_is_set = true;
      offer_end_date_count_is_modified = true;
      offer_start_date_prospec_is_set = true;
      offer_start_date_prospec_is_modified = true;
      offer_start_date_count_prospec_is_set = true;
      offer_start_date_count_prospec_is_modified = true;
      offer_end_date_prospec_is_set = true;
      offer_end_date_prospec_is_modified = true;
      offer_end_date_count_prospec_is_set = true;
      offer_end_date_count_prospec_is_modified = true;
      company_logo_url_is_set = true;
      company_logo_url_is_modified = true;
      company_url_is_set = true;
      company_url_is_modified = true;
      company_outline_is_set = true;
      company_outline_is_modified = true;
      note_is_set = true;
      note_is_modified = true;
      ipo_stop_is_set = true;
      ipo_stop_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      doc_reading_div_is_set = true;
      doc_reading_div_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof IpoProductRow ) )
       return false;
    return fieldsEqual( (IpoProductRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�IpoProductRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( IpoProductRow row )
  {
    if ( ipo_product_id != row.getIpoProductId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( ipo_regist_div == null ) {
      if ( row.getIpoRegistDiv() != null )
        return false;
    } else if ( !ipo_regist_div.equals( row.getIpoRegistDiv() ) ) {
        return false;
    }
    if ( ipo_regist_detail_div == null ) {
      if ( row.getIpoRegistDetailDiv() != null )
        return false;
    } else if ( !ipo_regist_detail_div.equals( row.getIpoRegistDetailDiv() ) ) {
        return false;
    }
    if ( public_offering_date == null ) {
      if ( row.getPublicOfferingDate() != null )
        return false;
    } else if ( !public_offering_date.equals( row.getPublicOfferingDate() ) ) {
        return false;
    }
    if ( public_offering_date_count == null ) {
      if ( !row.getPublicOfferingDateCountIsNull() )
        return false;
    } else if ( row.getPublicOfferingDateCountIsNull() || ( public_offering_date_count.intValue() != row.getPublicOfferingDateCount() ) ) {
        return false;
    }
    if ( public_market == null ) {
      if ( row.getPublicMarket() != null )
        return false;
    } else if ( !public_market.equals( row.getPublicMarket() ) ) {
        return false;
    }
    if ( provisional_value_div == null ) {
      if ( row.getProvisionalValueDiv() != null )
        return false;
    } else if ( !provisional_value_div.equals( row.getProvisionalValueDiv() ) ) {
        return false;
    }
    if ( provisional_min_value == null ) {
      if ( !row.getProvisionalMinValueIsNull() )
        return false;
    } else if ( row.getProvisionalMinValueIsNull() || ( provisional_min_value.doubleValue() != row.getProvisionalMinValue() ) ) {
        return false;
    }
    if ( provisional_max_value == null ) {
      if ( !row.getProvisionalMaxValueIsNull() )
        return false;
    } else if ( row.getProvisionalMaxValueIsNull() || ( provisional_max_value.doubleValue() != row.getProvisionalMaxValue() ) ) {
        return false;
    }
    if ( provisional_value_open_date == null ) {
      if ( row.getProvisionalValueOpenDate() != null )
        return false;
    } else if ( !provisional_value_open_date.equals( row.getProvisionalValueOpenDate() ) ) {
        return false;
    }
    if ( public_offering_quantity == null ) {
      if ( !row.getPublicOfferingQuantityIsNull() )
        return false;
    } else if ( row.getPublicOfferingQuantityIsNull() || ( public_offering_quantity.longValue() != row.getPublicOfferingQuantity() ) ) {
        return false;
    }
    if ( public_sales_quantity == null ) {
      if ( !row.getPublicSalesQuantityIsNull() )
        return false;
    } else if ( row.getPublicSalesQuantityIsNull() || ( public_sales_quantity.longValue() != row.getPublicSalesQuantity() ) ) {
        return false;
    }
    if ( dealing_quantity == null ) {
      if ( !row.getDealingQuantityIsNull() )
        return false;
    } else if ( row.getDealingQuantityIsNull() || ( dealing_quantity.longValue() != row.getDealingQuantity() ) ) {
        return false;
    }
    if ( lead_managing_underwriter == null ) {
      if ( row.getLeadManagingUnderwriter() != null )
        return false;
    } else if ( !lead_managing_underwriter.equals( row.getLeadManagingUnderwriter() ) ) {
        return false;
    }
    if ( lot_size == null ) {
      if ( !row.getLotSizeIsNull() )
        return false;
    } else if ( row.getLotSizeIsNull() || ( lot_size.longValue() != row.getLotSize() ) ) {
        return false;
    }
    if ( tick_value == null ) {
      if ( !row.getTickValueIsNull() )
        return false;
    } else if ( row.getTickValueIsNull() || ( tick_value.doubleValue() != row.getTickValue() ) ) {
        return false;
    }
    if ( ipo_unit_div == null ) {
      if ( row.getIpoUnitDiv() != null )
        return false;
    } else if ( !ipo_unit_div.equals( row.getIpoUnitDiv() ) ) {
        return false;
    }
    if ( enable_market_order == null ) {
      if ( row.getEnableMarketOrder() != null )
        return false;
    } else if ( !enable_market_order.equals( row.getEnableMarketOrder() ) ) {
        return false;
    }
    if ( bookbuilding_start_datetime == null ) {
      if ( row.getBookbuildingStartDatetime() != null )
        return false;
    } else if ( !bookbuilding_start_datetime.equals( row.getBookbuildingStartDatetime() ) ) {
        return false;
    }
    if ( bookbuilding_end_datetime == null ) {
      if ( row.getBookbuildingEndDatetime() != null )
        return false;
    } else if ( !bookbuilding_end_datetime.equals( row.getBookbuildingEndDatetime() ) ) {
        return false;
    }
    if ( public_price_settle_date == null ) {
      if ( row.getPublicPriceSettleDate() != null )
        return false;
    } else if ( !public_price_settle_date.equals( row.getPublicPriceSettleDate() ) ) {
        return false;
    }
    if ( public_price == null ) {
      if ( !row.getPublicPriceIsNull() )
        return false;
    } else if ( row.getPublicPriceIsNull() || ( public_price.doubleValue() != row.getPublicPrice() ) ) {
        return false;
    }
    if ( public_price_discount_rate == null ) {
      if ( !row.getPublicPriceDiscountRateIsNull() )
        return false;
    } else if ( row.getPublicPriceDiscountRateIsNull() || ( public_price_discount_rate.doubleValue() != row.getPublicPriceDiscountRate() ) ) {
        return false;
    }
    if ( lot_date == null ) {
      if ( row.getLotDate() != null )
        return false;
    } else if ( !lot_date.equals( row.getLotDate() ) ) {
        return false;
    }
    if ( lot_date_count == null ) {
      if ( !row.getLotDateCountIsNull() )
        return false;
    } else if ( row.getLotDateCountIsNull() || ( lot_date_count.intValue() != row.getLotDateCount() ) ) {
        return false;
    }
    if ( lot_status == null ) {
      if ( row.getLotStatus() != null )
        return false;
    } else if ( !lot_status.equals( row.getLotStatus() ) ) {
        return false;
    }
    if ( offer_start_datetime == null ) {
      if ( row.getOfferStartDatetime() != null )
        return false;
    } else if ( !offer_start_datetime.equals( row.getOfferStartDatetime() ) ) {
        return false;
    }
    if ( offer_start_date_count == null ) {
      if ( !row.getOfferStartDateCountIsNull() )
        return false;
    } else if ( row.getOfferStartDateCountIsNull() || ( offer_start_date_count.intValue() != row.getOfferStartDateCount() ) ) {
        return false;
    }
    if ( offer_end_datetime == null ) {
      if ( row.getOfferEndDatetime() != null )
        return false;
    } else if ( !offer_end_datetime.equals( row.getOfferEndDatetime() ) ) {
        return false;
    }
    if ( offer_end_date_count == null ) {
      if ( !row.getOfferEndDateCountIsNull() )
        return false;
    } else if ( row.getOfferEndDateCountIsNull() || ( offer_end_date_count.intValue() != row.getOfferEndDateCount() ) ) {
        return false;
    }
    if ( offer_start_date_prospec == null ) {
      if ( row.getOfferStartDateProspec() != null )
        return false;
    } else if ( !offer_start_date_prospec.equals( row.getOfferStartDateProspec() ) ) {
        return false;
    }
    if ( offer_start_date_count_prospec == null ) {
      if ( !row.getOfferStartDateCountProspecIsNull() )
        return false;
    } else if ( row.getOfferStartDateCountProspecIsNull() || ( offer_start_date_count_prospec.intValue() != row.getOfferStartDateCountProspec() ) ) {
        return false;
    }
    if ( offer_end_date_prospec == null ) {
      if ( row.getOfferEndDateProspec() != null )
        return false;
    } else if ( !offer_end_date_prospec.equals( row.getOfferEndDateProspec() ) ) {
        return false;
    }
    if ( offer_end_date_count_prospec == null ) {
      if ( !row.getOfferEndDateCountProspecIsNull() )
        return false;
    } else if ( row.getOfferEndDateCountProspecIsNull() || ( offer_end_date_count_prospec.intValue() != row.getOfferEndDateCountProspec() ) ) {
        return false;
    }
    if ( company_logo_url == null ) {
      if ( row.getCompanyLogoUrl() != null )
        return false;
    } else if ( !company_logo_url.equals( row.getCompanyLogoUrl() ) ) {
        return false;
    }
    if ( company_url == null ) {
      if ( row.getCompanyUrl() != null )
        return false;
    } else if ( !company_url.equals( row.getCompanyUrl() ) ) {
        return false;
    }
    if ( company_outline == null ) {
      if ( row.getCompanyOutline() != null )
        return false;
    } else if ( !company_outline.equals( row.getCompanyOutline() ) ) {
        return false;
    }
    if ( note == null ) {
      if ( row.getNote() != null )
        return false;
    } else if ( !note.equals( row.getNote() ) ) {
        return false;
    }
    if ( ipo_stop == null ) {
      if ( row.getIpoStop() != null )
        return false;
    } else if ( !ipo_stop.equals( row.getIpoStop() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
    if ( doc_reading_div == null ) {
      if ( row.getDocReadingDiv() != null )
        return false;
    } else if ( !doc_reading_div.equals( row.getDocReadingDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) ipo_product_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (ipo_regist_div!=null? ipo_regist_div.hashCode(): 0) 
        + (ipo_regist_detail_div!=null? ipo_regist_detail_div.hashCode(): 0) 
        + (public_offering_date!=null? public_offering_date.hashCode(): 0) 
        + (public_offering_date_count!=null? public_offering_date_count.hashCode(): 0) 
        + (public_market!=null? public_market.hashCode(): 0) 
        + (provisional_value_div!=null? provisional_value_div.hashCode(): 0) 
        + (provisional_min_value!=null? provisional_min_value.hashCode(): 0) 
        + (provisional_max_value!=null? provisional_max_value.hashCode(): 0) 
        + (provisional_value_open_date!=null? provisional_value_open_date.hashCode(): 0) 
        + (public_offering_quantity!=null? public_offering_quantity.hashCode(): 0) 
        + (public_sales_quantity!=null? public_sales_quantity.hashCode(): 0) 
        + (dealing_quantity!=null? dealing_quantity.hashCode(): 0) 
        + (lead_managing_underwriter!=null? lead_managing_underwriter.hashCode(): 0) 
        + (lot_size!=null? lot_size.hashCode(): 0) 
        + (tick_value!=null? tick_value.hashCode(): 0) 
        + (ipo_unit_div!=null? ipo_unit_div.hashCode(): 0) 
        + (enable_market_order!=null? enable_market_order.hashCode(): 0) 
        + (bookbuilding_start_datetime!=null? bookbuilding_start_datetime.hashCode(): 0) 
        + (bookbuilding_end_datetime!=null? bookbuilding_end_datetime.hashCode(): 0) 
        + (public_price_settle_date!=null? public_price_settle_date.hashCode(): 0) 
        + (public_price!=null? public_price.hashCode(): 0) 
        + (public_price_discount_rate!=null? public_price_discount_rate.hashCode(): 0) 
        + (lot_date!=null? lot_date.hashCode(): 0) 
        + (lot_date_count!=null? lot_date_count.hashCode(): 0) 
        + (lot_status!=null? lot_status.hashCode(): 0) 
        + (offer_start_datetime!=null? offer_start_datetime.hashCode(): 0) 
        + (offer_start_date_count!=null? offer_start_date_count.hashCode(): 0) 
        + (offer_end_datetime!=null? offer_end_datetime.hashCode(): 0) 
        + (offer_end_date_count!=null? offer_end_date_count.hashCode(): 0) 
        + (offer_start_date_prospec!=null? offer_start_date_prospec.hashCode(): 0) 
        + (offer_start_date_count_prospec!=null? offer_start_date_count_prospec.hashCode(): 0) 
        + (offer_end_date_prospec!=null? offer_end_date_prospec.hashCode(): 0) 
        + (offer_end_date_count_prospec!=null? offer_end_date_count_prospec.hashCode(): 0) 
        + (company_logo_url!=null? company_logo_url.hashCode(): 0) 
        + (company_url!=null? company_url.hashCode(): 0) 
        + (company_outline!=null? company_outline.hashCode(): 0) 
        + (note!=null? note.hashCode(): 0) 
        + (ipo_stop!=null? ipo_stop.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (doc_reading_div!=null? doc_reading_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !ipo_regist_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'ipo_regist_div' must be set before inserting.");
		if ( !ipo_regist_detail_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'ipo_regist_detail_div' must be set before inserting.");
		if ( !public_market_is_set )
			throw new IllegalArgumentException("non-nullable field 'public_market' must be set before inserting.");
		if ( !provisional_value_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'provisional_value_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("ipo_product_id",new Long(ipo_product_id));
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		if ( standard_name != null )
			map.put("standard_name",standard_name);
		map.put("product_type",product_type);
		map.put("ipo_regist_div",ipo_regist_div);
		map.put("ipo_regist_detail_div",ipo_regist_detail_div);
		if ( public_offering_date != null )
			map.put("public_offering_date",public_offering_date);
		if ( public_offering_date_count != null )
			map.put("public_offering_date_count",public_offering_date_count);
		map.put("public_market",public_market);
		map.put("provisional_value_div",provisional_value_div);
		if ( provisional_min_value != null )
			map.put("provisional_min_value",provisional_min_value);
		if ( provisional_max_value != null )
			map.put("provisional_max_value",provisional_max_value);
		if ( provisional_value_open_date != null )
			map.put("provisional_value_open_date",provisional_value_open_date);
		if ( public_offering_quantity != null )
			map.put("public_offering_quantity",public_offering_quantity);
		if ( public_sales_quantity != null )
			map.put("public_sales_quantity",public_sales_quantity);
		if ( dealing_quantity != null )
			map.put("dealing_quantity",dealing_quantity);
		if ( lead_managing_underwriter != null )
			map.put("lead_managing_underwriter",lead_managing_underwriter);
		if ( lot_size != null )
			map.put("lot_size",lot_size);
		if ( tick_value != null )
			map.put("tick_value",tick_value);
		if ( ipo_unit_div != null )
			map.put("ipo_unit_div",ipo_unit_div);
		if ( enable_market_order != null )
			map.put("enable_market_order",enable_market_order);
		if ( bookbuilding_start_datetime != null )
			map.put("bookbuilding_start_datetime",bookbuilding_start_datetime);
		if ( bookbuilding_end_datetime != null )
			map.put("bookbuilding_end_datetime",bookbuilding_end_datetime);
		if ( public_price_settle_date != null )
			map.put("public_price_settle_date",public_price_settle_date);
		if ( public_price != null )
			map.put("public_price",public_price);
		if ( public_price_discount_rate != null )
			map.put("public_price_discount_rate",public_price_discount_rate);
		if ( lot_date != null )
			map.put("lot_date",lot_date);
		if ( lot_date_count != null )
			map.put("lot_date_count",lot_date_count);
		if ( lot_status != null )
			map.put("lot_status",lot_status);
		if ( offer_start_datetime != null )
			map.put("offer_start_datetime",offer_start_datetime);
		if ( offer_start_date_count != null )
			map.put("offer_start_date_count",offer_start_date_count);
		if ( offer_end_datetime != null )
			map.put("offer_end_datetime",offer_end_datetime);
		if ( offer_end_date_count != null )
			map.put("offer_end_date_count",offer_end_date_count);
		if ( offer_start_date_prospec != null )
			map.put("offer_start_date_prospec",offer_start_date_prospec);
		if ( offer_start_date_count_prospec != null )
			map.put("offer_start_date_count_prospec",offer_start_date_count_prospec);
		if ( offer_end_date_prospec != null )
			map.put("offer_end_date_prospec",offer_end_date_prospec);
		if ( offer_end_date_count_prospec != null )
			map.put("offer_end_date_count_prospec",offer_end_date_count_prospec);
		if ( company_logo_url != null )
			map.put("company_logo_url",company_logo_url);
		if ( company_url != null )
			map.put("company_url",company_url);
		if ( company_outline != null )
			map.put("company_outline",company_outline);
		if ( note != null )
			map.put("note",note);
		if ( ipo_stop != null )
			map.put("ipo_stop",ipo_stop);
		if ( delete_flag != null )
			map.put("delete_flag",delete_flag);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( doc_reading_div != null )
			map.put("doc_reading_div",doc_reading_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( ipo_regist_div_is_modified )
			map.put("ipo_regist_div",ipo_regist_div);
		if ( ipo_regist_detail_div_is_modified )
			map.put("ipo_regist_detail_div",ipo_regist_detail_div);
		if ( public_offering_date_is_modified )
			map.put("public_offering_date",public_offering_date);
		if ( public_offering_date_count_is_modified )
			map.put("public_offering_date_count",public_offering_date_count);
		if ( public_market_is_modified )
			map.put("public_market",public_market);
		if ( provisional_value_div_is_modified )
			map.put("provisional_value_div",provisional_value_div);
		if ( provisional_min_value_is_modified )
			map.put("provisional_min_value",provisional_min_value);
		if ( provisional_max_value_is_modified )
			map.put("provisional_max_value",provisional_max_value);
		if ( provisional_value_open_date_is_modified )
			map.put("provisional_value_open_date",provisional_value_open_date);
		if ( public_offering_quantity_is_modified )
			map.put("public_offering_quantity",public_offering_quantity);
		if ( public_sales_quantity_is_modified )
			map.put("public_sales_quantity",public_sales_quantity);
		if ( dealing_quantity_is_modified )
			map.put("dealing_quantity",dealing_quantity);
		if ( lead_managing_underwriter_is_modified )
			map.put("lead_managing_underwriter",lead_managing_underwriter);
		if ( lot_size_is_modified )
			map.put("lot_size",lot_size);
		if ( tick_value_is_modified )
			map.put("tick_value",tick_value);
		if ( ipo_unit_div_is_modified )
			map.put("ipo_unit_div",ipo_unit_div);
		if ( enable_market_order_is_modified )
			map.put("enable_market_order",enable_market_order);
		if ( bookbuilding_start_datetime_is_modified )
			map.put("bookbuilding_start_datetime",bookbuilding_start_datetime);
		if ( bookbuilding_end_datetime_is_modified )
			map.put("bookbuilding_end_datetime",bookbuilding_end_datetime);
		if ( public_price_settle_date_is_modified )
			map.put("public_price_settle_date",public_price_settle_date);
		if ( public_price_is_modified )
			map.put("public_price",public_price);
		if ( public_price_discount_rate_is_modified )
			map.put("public_price_discount_rate",public_price_discount_rate);
		if ( lot_date_is_modified )
			map.put("lot_date",lot_date);
		if ( lot_date_count_is_modified )
			map.put("lot_date_count",lot_date_count);
		if ( lot_status_is_modified )
			map.put("lot_status",lot_status);
		if ( offer_start_datetime_is_modified )
			map.put("offer_start_datetime",offer_start_datetime);
		if ( offer_start_date_count_is_modified )
			map.put("offer_start_date_count",offer_start_date_count);
		if ( offer_end_datetime_is_modified )
			map.put("offer_end_datetime",offer_end_datetime);
		if ( offer_end_date_count_is_modified )
			map.put("offer_end_date_count",offer_end_date_count);
		if ( offer_start_date_prospec_is_modified )
			map.put("offer_start_date_prospec",offer_start_date_prospec);
		if ( offer_start_date_count_prospec_is_modified )
			map.put("offer_start_date_count_prospec",offer_start_date_count_prospec);
		if ( offer_end_date_prospec_is_modified )
			map.put("offer_end_date_prospec",offer_end_date_prospec);
		if ( offer_end_date_count_prospec_is_modified )
			map.put("offer_end_date_count_prospec",offer_end_date_count_prospec);
		if ( company_logo_url_is_modified )
			map.put("company_logo_url",company_logo_url);
		if ( company_url_is_modified )
			map.put("company_url",company_url);
		if ( company_outline_is_modified )
			map.put("company_outline",company_outline);
		if ( note_is_modified )
			map.put("note",note);
		if ( ipo_stop_is_modified )
			map.put("ipo_stop",ipo_stop);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( doc_reading_div_is_modified )
			map.put("doc_reading_div",doc_reading_div);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			map.put("standard_name",standard_name);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( ipo_regist_div_is_set )
				map.put("ipo_regist_div",ipo_regist_div);
			if ( ipo_regist_detail_div_is_set )
				map.put("ipo_regist_detail_div",ipo_regist_detail_div);
			map.put("public_offering_date",public_offering_date);
			map.put("public_offering_date_count",public_offering_date_count);
			if ( public_market_is_set )
				map.put("public_market",public_market);
			if ( provisional_value_div_is_set )
				map.put("provisional_value_div",provisional_value_div);
			map.put("provisional_min_value",provisional_min_value);
			map.put("provisional_max_value",provisional_max_value);
			map.put("provisional_value_open_date",provisional_value_open_date);
			map.put("public_offering_quantity",public_offering_quantity);
			map.put("public_sales_quantity",public_sales_quantity);
			map.put("dealing_quantity",dealing_quantity);
			map.put("lead_managing_underwriter",lead_managing_underwriter);
			map.put("lot_size",lot_size);
			map.put("tick_value",tick_value);
			map.put("ipo_unit_div",ipo_unit_div);
			map.put("enable_market_order",enable_market_order);
			map.put("bookbuilding_start_datetime",bookbuilding_start_datetime);
			map.put("bookbuilding_end_datetime",bookbuilding_end_datetime);
			map.put("public_price_settle_date",public_price_settle_date);
			map.put("public_price",public_price);
			map.put("public_price_discount_rate",public_price_discount_rate);
			map.put("lot_date",lot_date);
			map.put("lot_date_count",lot_date_count);
			map.put("lot_status",lot_status);
			map.put("offer_start_datetime",offer_start_datetime);
			map.put("offer_start_date_count",offer_start_date_count);
			map.put("offer_end_datetime",offer_end_datetime);
			map.put("offer_end_date_count",offer_end_date_count);
			map.put("offer_start_date_prospec",offer_start_date_prospec);
			map.put("offer_start_date_count_prospec",offer_start_date_count_prospec);
			map.put("offer_end_date_prospec",offer_end_date_prospec);
			map.put("offer_end_date_count_prospec",offer_end_date_count_prospec);
			map.put("company_logo_url",company_logo_url);
			map.put("company_url",company_url);
			map.put("company_outline",company_outline);
			map.put("note",note);
			map.put("ipo_stop",ipo_stop);
			map.put("delete_flag",delete_flag);
			map.put("last_updater",last_updater);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("doc_reading_div",doc_reading_div);
		}
		return map;
	}


  /** 
   * <em>ipo_product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getIpoProductId()
  {
    return ipo_product_id;
  }


  /** 
   * <em>ipo_product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoProductIdIsSet() {
    return ipo_product_id_is_set;
  }


  /** 
   * <em>ipo_product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoProductIdIsModified() {
    return ipo_product_id_is_modified;
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
   * <em>standard_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStandardName()
  {
    return standard_name;
  }


  /** 
   * <em>standard_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStandardNameIsSet() {
    return standard_name_is_set;
  }


  /** 
   * <em>standard_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStandardNameIsModified() {
    return standard_name_is_modified;
  }


  /** 
   * <em>product_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>ipo_regist_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIpoRegistDiv()
  {
    return ipo_regist_div;
  }


  /** 
   * <em>ipo_regist_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoRegistDivIsSet() {
    return ipo_regist_div_is_set;
  }


  /** 
   * <em>ipo_regist_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoRegistDivIsModified() {
    return ipo_regist_div_is_modified;
  }


  /** 
   * <em>ipo_regist_detail_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIpoRegistDetailDiv()
  {
    return ipo_regist_detail_div;
  }


  /** 
   * <em>ipo_regist_detail_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoRegistDetailDivIsSet() {
    return ipo_regist_detail_div_is_set;
  }


  /** 
   * <em>ipo_regist_detail_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoRegistDetailDivIsModified() {
    return ipo_regist_detail_div_is_modified;
  }


  /** 
   * <em>public_offering_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getPublicOfferingDate()
  {
    return public_offering_date;
  }


  /** 
   * <em>public_offering_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingDateIsSet() {
    return public_offering_date_is_set;
  }


  /** 
   * <em>public_offering_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingDateIsModified() {
    return public_offering_date_is_modified;
  }


  /** 
   * <em>public_offering_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getPublicOfferingDateCount()
  {
    return ( public_offering_date_count==null? ((int)0): public_offering_date_count.intValue() );
  }


  /** 
   * <em>public_offering_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPublicOfferingDateCountIsNull()
  {
    return public_offering_date_count == null;
  }


  /** 
   * <em>public_offering_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingDateCountIsSet() {
    return public_offering_date_count_is_set;
  }


  /** 
   * <em>public_offering_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingDateCountIsModified() {
    return public_offering_date_count_is_modified;
  }


  /** 
   * <em>public_market</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPublicMarket()
  {
    return public_market;
  }


  /** 
   * <em>public_market</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicMarketIsSet() {
    return public_market_is_set;
  }


  /** 
   * <em>public_market</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicMarketIsModified() {
    return public_market_is_modified;
  }


  /** 
   * <em>provisional_value_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProvisionalValueDiv()
  {
    return provisional_value_div;
  }


  /** 
   * <em>provisional_value_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalValueDivIsSet() {
    return provisional_value_div_is_set;
  }


  /** 
   * <em>provisional_value_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalValueDivIsModified() {
    return provisional_value_div_is_modified;
  }


  /** 
   * <em>provisional_min_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getProvisionalMinValue()
  {
    return ( provisional_min_value==null? ((double)0): provisional_min_value.doubleValue() );
  }


  /** 
   * <em>provisional_min_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getProvisionalMinValueIsNull()
  {
    return provisional_min_value == null;
  }


  /** 
   * <em>provisional_min_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalMinValueIsSet() {
    return provisional_min_value_is_set;
  }


  /** 
   * <em>provisional_min_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalMinValueIsModified() {
    return provisional_min_value_is_modified;
  }


  /** 
   * <em>provisional_max_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getProvisionalMaxValue()
  {
    return ( provisional_max_value==null? ((double)0): provisional_max_value.doubleValue() );
  }


  /** 
   * <em>provisional_max_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getProvisionalMaxValueIsNull()
  {
    return provisional_max_value == null;
  }


  /** 
   * <em>provisional_max_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalMaxValueIsSet() {
    return provisional_max_value_is_set;
  }


  /** 
   * <em>provisional_max_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalMaxValueIsModified() {
    return provisional_max_value_is_modified;
  }


  /** 
   * <em>provisional_value_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getProvisionalValueOpenDate()
  {
    return provisional_value_open_date;
  }


  /** 
   * <em>provisional_value_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalValueOpenDateIsSet() {
    return provisional_value_open_date_is_set;
  }


  /** 
   * <em>provisional_value_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProvisionalValueOpenDateIsModified() {
    return provisional_value_open_date_is_modified;
  }


  /** 
   * <em>public_offering_quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getPublicOfferingQuantity()
  {
    return ( public_offering_quantity==null? ((long)0): public_offering_quantity.longValue() );
  }


  /** 
   * <em>public_offering_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPublicOfferingQuantityIsNull()
  {
    return public_offering_quantity == null;
  }


  /** 
   * <em>public_offering_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingQuantityIsSet() {
    return public_offering_quantity_is_set;
  }


  /** 
   * <em>public_offering_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingQuantityIsModified() {
    return public_offering_quantity_is_modified;
  }


  /** 
   * <em>public_sales_quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getPublicSalesQuantity()
  {
    return ( public_sales_quantity==null? ((long)0): public_sales_quantity.longValue() );
  }


  /** 
   * <em>public_sales_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPublicSalesQuantityIsNull()
  {
    return public_sales_quantity == null;
  }


  /** 
   * <em>public_sales_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicSalesQuantityIsSet() {
    return public_sales_quantity_is_set;
  }


  /** 
   * <em>public_sales_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicSalesQuantityIsModified() {
    return public_sales_quantity_is_modified;
  }


  /** 
   * <em>dealing_quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getDealingQuantity()
  {
    return ( dealing_quantity==null? ((long)0): dealing_quantity.longValue() );
  }


  /** 
   * <em>dealing_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getDealingQuantityIsNull()
  {
    return dealing_quantity == null;
  }


  /** 
   * <em>dealing_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDealingQuantityIsSet() {
    return dealing_quantity_is_set;
  }


  /** 
   * <em>dealing_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDealingQuantityIsModified() {
    return dealing_quantity_is_modified;
  }


  /** 
   * <em>lead_managing_underwriter</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLeadManagingUnderwriter()
  {
    return lead_managing_underwriter;
  }


  /** 
   * <em>lead_managing_underwriter</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLeadManagingUnderwriterIsSet() {
    return lead_managing_underwriter_is_set;
  }


  /** 
   * <em>lead_managing_underwriter</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLeadManagingUnderwriterIsModified() {
    return lead_managing_underwriter_is_modified;
  }


  /** 
   * <em>lot_size</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getLotSize()
  {
    return ( lot_size==null? ((long)0): lot_size.longValue() );
  }


  /** 
   * <em>lot_size</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLotSizeIsNull()
  {
    return lot_size == null;
  }


  /** 
   * <em>lot_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotSizeIsSet() {
    return lot_size_is_set;
  }


  /** 
   * <em>lot_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotSizeIsModified() {
    return lot_size_is_modified;
  }


  /** 
   * <em>tick_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getTickValue()
  {
    return ( tick_value==null? ((double)0): tick_value.doubleValue() );
  }


  /** 
   * <em>tick_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTickValueIsNull()
  {
    return tick_value == null;
  }


  /** 
   * <em>tick_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTickValueIsSet() {
    return tick_value_is_set;
  }


  /** 
   * <em>tick_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTickValueIsModified() {
    return tick_value_is_modified;
  }


  /** 
   * <em>ipo_unit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIpoUnitDiv()
  {
    return ipo_unit_div;
  }


  /** 
   * <em>ipo_unit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoUnitDivIsSet() {
    return ipo_unit_div_is_set;
  }


  /** 
   * <em>ipo_unit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoUnitDivIsModified() {
    return ipo_unit_div_is_modified;
  }


  /** 
   * <em>enable_market_order</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEnableMarketOrder()
  {
    return enable_market_order;
  }


  /** 
   * <em>enable_market_order</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableMarketOrderIsSet() {
    return enable_market_order_is_set;
  }


  /** 
   * <em>enable_market_order</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableMarketOrderIsModified() {
    return enable_market_order_is_modified;
  }


  /** 
   * <em>bookbuilding_start_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getBookbuildingStartDatetime()
  {
    return bookbuilding_start_datetime;
  }


  /** 
   * <em>bookbuilding_start_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBookbuildingStartDatetimeIsSet() {
    return bookbuilding_start_datetime_is_set;
  }


  /** 
   * <em>bookbuilding_start_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBookbuildingStartDatetimeIsModified() {
    return bookbuilding_start_datetime_is_modified;
  }


  /** 
   * <em>bookbuilding_end_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getBookbuildingEndDatetime()
  {
    return bookbuilding_end_datetime;
  }


  /** 
   * <em>bookbuilding_end_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBookbuildingEndDatetimeIsSet() {
    return bookbuilding_end_datetime_is_set;
  }


  /** 
   * <em>bookbuilding_end_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBookbuildingEndDatetimeIsModified() {
    return bookbuilding_end_datetime_is_modified;
  }


  /** 
   * <em>public_price_settle_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getPublicPriceSettleDate()
  {
    return public_price_settle_date;
  }


  /** 
   * <em>public_price_settle_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicPriceSettleDateIsSet() {
    return public_price_settle_date_is_set;
  }


  /** 
   * <em>public_price_settle_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicPriceSettleDateIsModified() {
    return public_price_settle_date_is_modified;
  }


  /** 
   * <em>public_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getPublicPrice()
  {
    return ( public_price==null? ((double)0): public_price.doubleValue() );
  }


  /** 
   * <em>public_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPublicPriceIsNull()
  {
    return public_price == null;
  }


  /** 
   * <em>public_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicPriceIsSet() {
    return public_price_is_set;
  }


  /** 
   * <em>public_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicPriceIsModified() {
    return public_price_is_modified;
  }


  /** 
   * <em>public_price_discount_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getPublicPriceDiscountRate()
  {
    return ( public_price_discount_rate==null? ((double)0): public_price_discount_rate.doubleValue() );
  }


  /** 
   * <em>public_price_discount_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPublicPriceDiscountRateIsNull()
  {
    return public_price_discount_rate == null;
  }


  /** 
   * <em>public_price_discount_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicPriceDiscountRateIsSet() {
    return public_price_discount_rate_is_set;
  }


  /** 
   * <em>public_price_discount_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicPriceDiscountRateIsModified() {
    return public_price_discount_rate_is_modified;
  }


  /** 
   * <em>lot_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLotDate()
  {
    return lot_date;
  }


  /** 
   * <em>lot_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotDateIsSet() {
    return lot_date_is_set;
  }


  /** 
   * <em>lot_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotDateIsModified() {
    return lot_date_is_modified;
  }


  /** 
   * <em>lot_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getLotDateCount()
  {
    return ( lot_date_count==null? ((int)0): lot_date_count.intValue() );
  }


  /** 
   * <em>lot_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLotDateCountIsNull()
  {
    return lot_date_count == null;
  }


  /** 
   * <em>lot_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotDateCountIsSet() {
    return lot_date_count_is_set;
  }


  /** 
   * <em>lot_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotDateCountIsModified() {
    return lot_date_count_is_modified;
  }


  /** 
   * <em>lot_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLotStatus()
  {
    return lot_status;
  }


  /** 
   * <em>lot_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotStatusIsSet() {
    return lot_status_is_set;
  }


  /** 
   * <em>lot_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotStatusIsModified() {
    return lot_status_is_modified;
  }


  /** 
   * <em>offer_start_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOfferStartDatetime()
  {
    return offer_start_datetime;
  }


  /** 
   * <em>offer_start_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDatetimeIsSet() {
    return offer_start_datetime_is_set;
  }


  /** 
   * <em>offer_start_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDatetimeIsModified() {
    return offer_start_datetime_is_modified;
  }


  /** 
   * <em>offer_start_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOfferStartDateCount()
  {
    return ( offer_start_date_count==null? ((int)0): offer_start_date_count.intValue() );
  }


  /** 
   * <em>offer_start_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOfferStartDateCountIsNull()
  {
    return offer_start_date_count == null;
  }


  /** 
   * <em>offer_start_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDateCountIsSet() {
    return offer_start_date_count_is_set;
  }


  /** 
   * <em>offer_start_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDateCountIsModified() {
    return offer_start_date_count_is_modified;
  }


  /** 
   * <em>offer_end_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOfferEndDatetime()
  {
    return offer_end_datetime;
  }


  /** 
   * <em>offer_end_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDatetimeIsSet() {
    return offer_end_datetime_is_set;
  }


  /** 
   * <em>offer_end_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDatetimeIsModified() {
    return offer_end_datetime_is_modified;
  }


  /** 
   * <em>offer_end_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOfferEndDateCount()
  {
    return ( offer_end_date_count==null? ((int)0): offer_end_date_count.intValue() );
  }


  /** 
   * <em>offer_end_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOfferEndDateCountIsNull()
  {
    return offer_end_date_count == null;
  }


  /** 
   * <em>offer_end_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDateCountIsSet() {
    return offer_end_date_count_is_set;
  }


  /** 
   * <em>offer_end_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDateCountIsModified() {
    return offer_end_date_count_is_modified;
  }


  /** 
   * <em>offer_start_date_prospec</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOfferStartDateProspec()
  {
    return offer_start_date_prospec;
  }


  /** 
   * <em>offer_start_date_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDateProspecIsSet() {
    return offer_start_date_prospec_is_set;
  }


  /** 
   * <em>offer_start_date_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDateProspecIsModified() {
    return offer_start_date_prospec_is_modified;
  }


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOfferStartDateCountProspec()
  {
    return ( offer_start_date_count_prospec==null? ((int)0): offer_start_date_count_prospec.intValue() );
  }


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOfferStartDateCountProspecIsNull()
  {
    return offer_start_date_count_prospec == null;
  }


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDateCountProspecIsSet() {
    return offer_start_date_count_prospec_is_set;
  }


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferStartDateCountProspecIsModified() {
    return offer_start_date_count_prospec_is_modified;
  }


  /** 
   * <em>offer_end_date_prospec</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOfferEndDateProspec()
  {
    return offer_end_date_prospec;
  }


  /** 
   * <em>offer_end_date_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDateProspecIsSet() {
    return offer_end_date_prospec_is_set;
  }


  /** 
   * <em>offer_end_date_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDateProspecIsModified() {
    return offer_end_date_prospec_is_modified;
  }


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOfferEndDateCountProspec()
  {
    return ( offer_end_date_count_prospec==null? ((int)0): offer_end_date_count_prospec.intValue() );
  }


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOfferEndDateCountProspecIsNull()
  {
    return offer_end_date_count_prospec == null;
  }


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDateCountProspecIsSet() {
    return offer_end_date_count_prospec_is_set;
  }


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOfferEndDateCountProspecIsModified() {
    return offer_end_date_count_prospec_is_modified;
  }


  /** 
   * <em>company_logo_url</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCompanyLogoUrl()
  {
    return company_logo_url;
  }


  /** 
   * <em>company_logo_url</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompanyLogoUrlIsSet() {
    return company_logo_url_is_set;
  }


  /** 
   * <em>company_logo_url</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompanyLogoUrlIsModified() {
    return company_logo_url_is_modified;
  }


  /** 
   * <em>company_url</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCompanyUrl()
  {
    return company_url;
  }


  /** 
   * <em>company_url</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompanyUrlIsSet() {
    return company_url_is_set;
  }


  /** 
   * <em>company_url</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompanyUrlIsModified() {
    return company_url_is_modified;
  }


  /** 
   * <em>company_outline</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCompanyOutline()
  {
    return company_outline;
  }


  /** 
   * <em>company_outline</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompanyOutlineIsSet() {
    return company_outline_is_set;
  }


  /** 
   * <em>company_outline</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompanyOutlineIsModified() {
    return company_outline_is_modified;
  }


  /** 
   * <em>note</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNote()
  {
    return note;
  }


  /** 
   * <em>note</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNoteIsSet() {
    return note_is_set;
  }


  /** 
   * <em>note</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNoteIsModified() {
    return note_is_modified;
  }


  /** 
   * <em>ipo_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIpoStop()
  {
    return ipo_stop;
  }


  /** 
   * <em>ipo_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoStopIsSet() {
    return ipo_stop_is_set;
  }


  /** 
   * <em>ipo_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoStopIsModified() {
    return ipo_stop_is_modified;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
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
   * <em>doc_reading_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDocReadingDiv()
  {
    return doc_reading_div;
  }


  /** 
   * <em>doc_reading_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDocReadingDivIsSet() {
    return doc_reading_div_is_set;
  }


  /** 
   * <em>doc_reading_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDocReadingDivIsModified() {
    return doc_reading_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new IpoProductPK(ipo_product_id);
  }


  /** 
   * <em>ipo_product_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ipoProductId <em>ipo_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setIpoProductId( long p_ipoProductId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_product_id = p_ipoProductId;
    ipo_product_id_is_set = true;
    ipo_product_id_is_modified = true;
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
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>standard_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_standardName <em>standard_name</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>product_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productType <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>ipo_regist_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ipoRegistDiv <em>ipo_regist_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setIpoRegistDiv( String p_ipoRegistDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_regist_div = p_ipoRegistDiv;
    ipo_regist_div_is_set = true;
    ipo_regist_div_is_modified = true;
  }


  /** 
   * <em>ipo_regist_detail_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ipoRegistDetailDiv <em>ipo_regist_detail_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setIpoRegistDetailDiv( String p_ipoRegistDetailDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_regist_detail_div = p_ipoRegistDetailDiv;
    ipo_regist_detail_div_is_set = true;
    ipo_regist_detail_div_is_modified = true;
  }


  /** 
   * <em>public_offering_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicOfferingDate <em>public_offering_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setPublicOfferingDate( java.sql.Timestamp p_publicOfferingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_date = p_publicOfferingDate;
    public_offering_date_is_set = true;
    public_offering_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>public_offering_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setPublicOfferingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    public_offering_date_is_set = true;
    public_offering_date_is_modified = true;
  }


  /** 
   * <em>public_offering_date_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicOfferingDateCount <em>public_offering_date_count</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setPublicOfferingDateCount( int p_publicOfferingDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_date_count = new Integer(p_publicOfferingDateCount);
    public_offering_date_count_is_set = true;
    public_offering_date_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>public_offering_date_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPublicOfferingDateCount( Integer p_publicOfferingDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_date_count = p_publicOfferingDateCount;
    public_offering_date_count_is_set = true;
    public_offering_date_count_is_modified = true;
  }


  /** 
   * <em>public_market</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicMarket <em>public_market</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setPublicMarket( String p_publicMarket )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_market = p_publicMarket;
    public_market_is_set = true;
    public_market_is_modified = true;
  }


  /** 
   * <em>provisional_value_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_provisionalValueDiv <em>provisional_value_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setProvisionalValueDiv( String p_provisionalValueDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    provisional_value_div = p_provisionalValueDiv;
    provisional_value_div_is_set = true;
    provisional_value_div_is_modified = true;
  }


  /** 
   * <em>provisional_min_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_provisionalMinValue <em>provisional_min_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setProvisionalMinValue( double p_provisionalMinValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    provisional_min_value = new Double(p_provisionalMinValue);
    provisional_min_value_is_set = true;
    provisional_min_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>provisional_min_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setProvisionalMinValue( Double p_provisionalMinValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    provisional_min_value = p_provisionalMinValue;
    provisional_min_value_is_set = true;
    provisional_min_value_is_modified = true;
  }


  /** 
   * <em>provisional_max_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_provisionalMaxValue <em>provisional_max_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setProvisionalMaxValue( double p_provisionalMaxValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    provisional_max_value = new Double(p_provisionalMaxValue);
    provisional_max_value_is_set = true;
    provisional_max_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>provisional_max_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setProvisionalMaxValue( Double p_provisionalMaxValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    provisional_max_value = p_provisionalMaxValue;
    provisional_max_value_is_set = true;
    provisional_max_value_is_modified = true;
  }


  /** 
   * <em>provisional_value_open_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_provisionalValueOpenDate <em>provisional_value_open_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setProvisionalValueOpenDate( java.sql.Timestamp p_provisionalValueOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    provisional_value_open_date = p_provisionalValueOpenDate;
    provisional_value_open_date_is_set = true;
    provisional_value_open_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>provisional_value_open_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setProvisionalValueOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    provisional_value_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    provisional_value_open_date_is_set = true;
    provisional_value_open_date_is_modified = true;
  }


  /** 
   * <em>public_offering_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicOfferingQuantity <em>public_offering_quantity</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setPublicOfferingQuantity( long p_publicOfferingQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_quantity = new Long(p_publicOfferingQuantity);
    public_offering_quantity_is_set = true;
    public_offering_quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>public_offering_quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPublicOfferingQuantity( Long p_publicOfferingQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_quantity = p_publicOfferingQuantity;
    public_offering_quantity_is_set = true;
    public_offering_quantity_is_modified = true;
  }


  /** 
   * <em>public_sales_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicSalesQuantity <em>public_sales_quantity</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setPublicSalesQuantity( long p_publicSalesQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_sales_quantity = new Long(p_publicSalesQuantity);
    public_sales_quantity_is_set = true;
    public_sales_quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>public_sales_quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPublicSalesQuantity( Long p_publicSalesQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_sales_quantity = p_publicSalesQuantity;
    public_sales_quantity_is_set = true;
    public_sales_quantity_is_modified = true;
  }


  /** 
   * <em>dealing_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dealingQuantity <em>dealing_quantity</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setDealingQuantity( long p_dealingQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dealing_quantity = new Long(p_dealingQuantity);
    dealing_quantity_is_set = true;
    dealing_quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>dealing_quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setDealingQuantity( Long p_dealingQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    dealing_quantity = p_dealingQuantity;
    dealing_quantity_is_set = true;
    dealing_quantity_is_modified = true;
  }


  /** 
   * <em>lead_managing_underwriter</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_leadManagingUnderwriter <em>lead_managing_underwriter</em>�J�����̒l������킷80���ȉ���String�l 
   */
  public final void setLeadManagingUnderwriter( String p_leadManagingUnderwriter )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lead_managing_underwriter = p_leadManagingUnderwriter;
    lead_managing_underwriter_is_set = true;
    lead_managing_underwriter_is_modified = true;
  }


  /** 
   * <em>lot_size</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lotSize <em>lot_size</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setLotSize( long p_lotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_size = new Long(p_lotSize);
    lot_size_is_set = true;
    lot_size_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>lot_size</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLotSize( Long p_lotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    lot_size = p_lotSize;
    lot_size_is_set = true;
    lot_size_is_modified = true;
  }


  /** 
   * <em>tick_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tickValue <em>tick_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setTickValue( double p_tickValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tick_value = new Double(p_tickValue);
    tick_value_is_set = true;
    tick_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>tick_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTickValue( Double p_tickValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tick_value = p_tickValue;
    tick_value_is_set = true;
    tick_value_is_modified = true;
  }


  /** 
   * <em>ipo_unit_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ipoUnitDiv <em>ipo_unit_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setIpoUnitDiv( String p_ipoUnitDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_unit_div = p_ipoUnitDiv;
    ipo_unit_div_is_set = true;
    ipo_unit_div_is_modified = true;
  }


  /** 
   * <em>enable_market_order</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_enableMarketOrder <em>enable_market_order</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setEnableMarketOrder( String p_enableMarketOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_market_order = p_enableMarketOrder;
    enable_market_order_is_set = true;
    enable_market_order_is_modified = true;
  }


  /** 
   * <em>bookbuilding_start_datetime</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bookbuildingStartDatetime <em>bookbuilding_start_datetime</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setBookbuildingStartDatetime( java.sql.Timestamp p_bookbuildingStartDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_start_datetime = p_bookbuildingStartDatetime;
    bookbuilding_start_datetime_is_set = true;
    bookbuilding_start_datetime_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>bookbuilding_start_datetime</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setBookbuildingStartDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_start_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    bookbuilding_start_datetime_is_set = true;
    bookbuilding_start_datetime_is_modified = true;
  }


  /** 
   * <em>bookbuilding_end_datetime</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bookbuildingEndDatetime <em>bookbuilding_end_datetime</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setBookbuildingEndDatetime( java.sql.Timestamp p_bookbuildingEndDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_end_datetime = p_bookbuildingEndDatetime;
    bookbuilding_end_datetime_is_set = true;
    bookbuilding_end_datetime_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>bookbuilding_end_datetime</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setBookbuildingEndDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bookbuilding_end_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    bookbuilding_end_datetime_is_set = true;
    bookbuilding_end_datetime_is_modified = true;
  }


  /** 
   * <em>public_price_settle_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicPriceSettleDate <em>public_price_settle_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setPublicPriceSettleDate( java.sql.Timestamp p_publicPriceSettleDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_price_settle_date = p_publicPriceSettleDate;
    public_price_settle_date_is_set = true;
    public_price_settle_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>public_price_settle_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setPublicPriceSettleDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_price_settle_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    public_price_settle_date_is_set = true;
    public_price_settle_date_is_modified = true;
  }


  /** 
   * <em>public_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicPrice <em>public_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setPublicPrice( double p_publicPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_price = new Double(p_publicPrice);
    public_price_is_set = true;
    public_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>public_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPublicPrice( Double p_publicPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_price = p_publicPrice;
    public_price_is_set = true;
    public_price_is_modified = true;
  }


  /** 
   * <em>public_price_discount_rate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicPriceDiscountRate <em>public_price_discount_rate</em>�J�����̒l������킷4���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setPublicPriceDiscountRate( double p_publicPriceDiscountRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_price_discount_rate = new Double(p_publicPriceDiscountRate);
    public_price_discount_rate_is_set = true;
    public_price_discount_rate_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>public_price_discount_rate</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPublicPriceDiscountRate( Double p_publicPriceDiscountRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_price_discount_rate = p_publicPriceDiscountRate;
    public_price_discount_rate_is_set = true;
    public_price_discount_rate_is_modified = true;
  }


  /** 
   * <em>lot_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lotDate <em>lot_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLotDate( java.sql.Timestamp p_lotDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_date = p_lotDate;
    lot_date_is_set = true;
    lot_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>lot_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLotDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    lot_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    lot_date_is_set = true;
    lot_date_is_modified = true;
  }


  /** 
   * <em>lot_date_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lotDateCount <em>lot_date_count</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setLotDateCount( int p_lotDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_date_count = new Integer(p_lotDateCount);
    lot_date_count_is_set = true;
    lot_date_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>lot_date_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLotDateCount( Integer p_lotDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    lot_date_count = p_lotDateCount;
    lot_date_count_is_set = true;
    lot_date_count_is_modified = true;
  }


  /** 
   * <em>lot_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lotStatus <em>lot_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setLotStatus( String p_lotStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_status = p_lotStatus;
    lot_status_is_set = true;
    lot_status_is_modified = true;
  }


  /** 
   * <em>offer_start_datetime</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerStartDatetime <em>offer_start_datetime</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOfferStartDatetime( java.sql.Timestamp p_offerStartDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_datetime = p_offerStartDatetime;
    offer_start_datetime_is_set = true;
    offer_start_datetime_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>offer_start_datetime</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOfferStartDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    offer_start_datetime_is_set = true;
    offer_start_datetime_is_modified = true;
  }


  /** 
   * <em>offer_start_date_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerStartDateCount <em>offer_start_date_count</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setOfferStartDateCount( int p_offerStartDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_date_count = new Integer(p_offerStartDateCount);
    offer_start_date_count_is_set = true;
    offer_start_date_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>offer_start_date_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOfferStartDateCount( Integer p_offerStartDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_date_count = p_offerStartDateCount;
    offer_start_date_count_is_set = true;
    offer_start_date_count_is_modified = true;
  }


  /** 
   * <em>offer_end_datetime</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerEndDatetime <em>offer_end_datetime</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOfferEndDatetime( java.sql.Timestamp p_offerEndDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_datetime = p_offerEndDatetime;
    offer_end_datetime_is_set = true;
    offer_end_datetime_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>offer_end_datetime</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOfferEndDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    offer_end_datetime_is_set = true;
    offer_end_datetime_is_modified = true;
  }


  /** 
   * <em>offer_end_date_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerEndDateCount <em>offer_end_date_count</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setOfferEndDateCount( int p_offerEndDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_date_count = new Integer(p_offerEndDateCount);
    offer_end_date_count_is_set = true;
    offer_end_date_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>offer_end_date_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOfferEndDateCount( Integer p_offerEndDateCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_date_count = p_offerEndDateCount;
    offer_end_date_count_is_set = true;
    offer_end_date_count_is_modified = true;
  }


  /** 
   * <em>offer_start_date_prospec</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerStartDateProspec <em>offer_start_date_prospec</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOfferStartDateProspec( java.sql.Timestamp p_offerStartDateProspec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_date_prospec = p_offerStartDateProspec;
    offer_start_date_prospec_is_set = true;
    offer_start_date_prospec_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>offer_start_date_prospec</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOfferStartDateProspec( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_date_prospec = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    offer_start_date_prospec_is_set = true;
    offer_start_date_prospec_is_modified = true;
  }


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerStartDateCountProspec <em>offer_start_date_count_prospec</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setOfferStartDateCountProspec( int p_offerStartDateCountProspec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_date_count_prospec = new Integer(p_offerStartDateCountProspec);
    offer_start_date_count_prospec_is_set = true;
    offer_start_date_count_prospec_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>offer_start_date_count_prospec</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOfferStartDateCountProspec( Integer p_offerStartDateCountProspec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_start_date_count_prospec = p_offerStartDateCountProspec;
    offer_start_date_count_prospec_is_set = true;
    offer_start_date_count_prospec_is_modified = true;
  }


  /** 
   * <em>offer_end_date_prospec</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerEndDateProspec <em>offer_end_date_prospec</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOfferEndDateProspec( java.sql.Timestamp p_offerEndDateProspec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_date_prospec = p_offerEndDateProspec;
    offer_end_date_prospec_is_set = true;
    offer_end_date_prospec_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>offer_end_date_prospec</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOfferEndDateProspec( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_date_prospec = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    offer_end_date_prospec_is_set = true;
    offer_end_date_prospec_is_modified = true;
  }


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offerEndDateCountProspec <em>offer_end_date_count_prospec</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setOfferEndDateCountProspec( int p_offerEndDateCountProspec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_date_count_prospec = new Integer(p_offerEndDateCountProspec);
    offer_end_date_count_prospec_is_set = true;
    offer_end_date_count_prospec_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>offer_end_date_count_prospec</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOfferEndDateCountProspec( Integer p_offerEndDateCountProspec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    offer_end_date_count_prospec = p_offerEndDateCountProspec;
    offer_end_date_count_prospec_is_set = true;
    offer_end_date_count_prospec_is_modified = true;
  }


  /** 
   * <em>company_logo_url</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_companyLogoUrl <em>company_logo_url</em>�J�����̒l������킷80���ȉ���String�l 
   */
  public final void setCompanyLogoUrl( String p_companyLogoUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    company_logo_url = p_companyLogoUrl;
    company_logo_url_is_set = true;
    company_logo_url_is_modified = true;
  }


  /** 
   * <em>company_url</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_companyUrl <em>company_url</em>�J�����̒l������킷80���ȉ���String�l 
   */
  public final void setCompanyUrl( String p_companyUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    company_url = p_companyUrl;
    company_url_is_set = true;
    company_url_is_modified = true;
  }


  /** 
   * <em>company_outline</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_companyOutline <em>company_outline</em>�J�����̒l������킷400���ȉ���String�l 
   */
  public final void setCompanyOutline( String p_companyOutline )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    company_outline = p_companyOutline;
    company_outline_is_set = true;
    company_outline_is_modified = true;
  }


  /** 
   * <em>note</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_note <em>note</em>�J�����̒l������킷400���ȉ���String�l 
   */
  public final void setNote( String p_note )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    note = p_note;
    note_is_set = true;
    note_is_modified = true;
  }


  /** 
   * <em>ipo_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ipoStop <em>ipo_stop</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setIpoStop( String p_ipoStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_stop = p_ipoStop;
    ipo_stop_is_set = true;
    ipo_stop_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deleteFlag <em>delete_flag</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
   * <em>doc_reading_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_docReadingDiv <em>doc_reading_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setDocReadingDiv( String p_docReadingDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    doc_reading_div = p_docReadingDiv;
    doc_reading_div_is_set = true;
    doc_reading_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("bookbuilding_start_datetime") ) {
                    return this.bookbuilding_start_datetime;
                }
                else if ( name.equals("bookbuilding_end_datetime") ) {
                    return this.bookbuilding_end_datetime;
                }
                break;
            case 'c':
                if ( name.equals("company_logo_url") ) {
                    return this.company_logo_url;
                }
                else if ( name.equals("company_url") ) {
                    return this.company_url;
                }
                else if ( name.equals("company_outline") ) {
                    return this.company_outline;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("dealing_quantity") ) {
                    return this.dealing_quantity;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                else if ( name.equals("doc_reading_div") ) {
                    return this.doc_reading_div;
                }
                break;
            case 'e':
                if ( name.equals("enable_market_order") ) {
                    return this.enable_market_order;
                }
                break;
            case 'i':
                if ( name.equals("ipo_product_id") ) {
                    return new Long( ipo_product_id );
                }
                else if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ipo_regist_div") ) {
                    return this.ipo_regist_div;
                }
                else if ( name.equals("ipo_regist_detail_div") ) {
                    return this.ipo_regist_detail_div;
                }
                else if ( name.equals("ipo_unit_div") ) {
                    return this.ipo_unit_div;
                }
                else if ( name.equals("ipo_stop") ) {
                    return this.ipo_stop;
                }
                break;
            case 'l':
                if ( name.equals("lead_managing_underwriter") ) {
                    return this.lead_managing_underwriter;
                }
                else if ( name.equals("lot_size") ) {
                    return this.lot_size;
                }
                else if ( name.equals("lot_date") ) {
                    return this.lot_date;
                }
                else if ( name.equals("lot_date_count") ) {
                    return this.lot_date_count;
                }
                else if ( name.equals("lot_status") ) {
                    return this.lot_status;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("note") ) {
                    return this.note;
                }
                break;
            case 'o':
                if ( name.equals("offer_start_datetime") ) {
                    return this.offer_start_datetime;
                }
                else if ( name.equals("offer_start_date_count") ) {
                    return this.offer_start_date_count;
                }
                else if ( name.equals("offer_end_datetime") ) {
                    return this.offer_end_datetime;
                }
                else if ( name.equals("offer_end_date_count") ) {
                    return this.offer_end_date_count;
                }
                else if ( name.equals("offer_start_date_prospec") ) {
                    return this.offer_start_date_prospec;
                }
                else if ( name.equals("offer_start_date_count_prospec") ) {
                    return this.offer_start_date_count_prospec;
                }
                else if ( name.equals("offer_end_date_prospec") ) {
                    return this.offer_end_date_prospec;
                }
                else if ( name.equals("offer_end_date_count_prospec") ) {
                    return this.offer_end_date_count_prospec;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("public_offering_date") ) {
                    return this.public_offering_date;
                }
                else if ( name.equals("public_offering_date_count") ) {
                    return this.public_offering_date_count;
                }
                else if ( name.equals("public_market") ) {
                    return this.public_market;
                }
                else if ( name.equals("provisional_value_div") ) {
                    return this.provisional_value_div;
                }
                else if ( name.equals("provisional_min_value") ) {
                    return this.provisional_min_value;
                }
                else if ( name.equals("provisional_max_value") ) {
                    return this.provisional_max_value;
                }
                else if ( name.equals("provisional_value_open_date") ) {
                    return this.provisional_value_open_date;
                }
                else if ( name.equals("public_offering_quantity") ) {
                    return this.public_offering_quantity;
                }
                else if ( name.equals("public_sales_quantity") ) {
                    return this.public_sales_quantity;
                }
                else if ( name.equals("public_price_settle_date") ) {
                    return this.public_price_settle_date;
                }
                else if ( name.equals("public_price") ) {
                    return this.public_price;
                }
                else if ( name.equals("public_price_discount_rate") ) {
                    return this.public_price_discount_rate;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                break;
            case 't':
                if ( name.equals("tick_value") ) {
                    return this.tick_value;
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
            case 'b':
                if ( name.equals("bookbuilding_start_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'bookbuilding_start_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.bookbuilding_start_datetime = (java.sql.Timestamp) value;
                    if (this.bookbuilding_start_datetime_is_set)
                        this.bookbuilding_start_datetime_is_modified = true;
                    this.bookbuilding_start_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("bookbuilding_end_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'bookbuilding_end_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.bookbuilding_end_datetime = (java.sql.Timestamp) value;
                    if (this.bookbuilding_end_datetime_is_set)
                        this.bookbuilding_end_datetime_is_modified = true;
                    this.bookbuilding_end_datetime_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("company_logo_url") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'company_logo_url' must be String: '"+value+"' is not." );
                    this.company_logo_url = (String) value;
                    if (this.company_logo_url_is_set)
                        this.company_logo_url_is_modified = true;
                    this.company_logo_url_is_set = true;
                    return;
                }
                else if ( name.equals("company_url") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'company_url' must be String: '"+value+"' is not." );
                    this.company_url = (String) value;
                    if (this.company_url_is_set)
                        this.company_url_is_modified = true;
                    this.company_url_is_set = true;
                    return;
                }
                else if ( name.equals("company_outline") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'company_outline' must be String: '"+value+"' is not." );
                    this.company_outline = (String) value;
                    if (this.company_outline_is_set)
                        this.company_outline_is_modified = true;
                    this.company_outline_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
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
                if ( name.equals("dealing_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'dealing_quantity' must be Long: '"+value+"' is not." );
                    this.dealing_quantity = (Long) value;
                    if (this.dealing_quantity_is_set)
                        this.dealing_quantity_is_modified = true;
                    this.dealing_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                else if ( name.equals("doc_reading_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'doc_reading_div' must be String: '"+value+"' is not." );
                    this.doc_reading_div = (String) value;
                    if (this.doc_reading_div_is_set)
                        this.doc_reading_div_is_modified = true;
                    this.doc_reading_div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("enable_market_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enable_market_order' must be String: '"+value+"' is not." );
                    this.enable_market_order = (String) value;
                    if (this.enable_market_order_is_set)
                        this.enable_market_order_is_modified = true;
                    this.enable_market_order_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("ipo_product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ipo_product_id' must be Long: '"+value+"' is not." );
                    this.ipo_product_id = ((Long) value).longValue();
                    if (this.ipo_product_id_is_set)
                        this.ipo_product_id_is_modified = true;
                    this.ipo_product_id_is_set = true;
                    return;
                }
                else if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("ipo_regist_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ipo_regist_div' must be String: '"+value+"' is not." );
                    this.ipo_regist_div = (String) value;
                    if (this.ipo_regist_div_is_set)
                        this.ipo_regist_div_is_modified = true;
                    this.ipo_regist_div_is_set = true;
                    return;
                }
                else if ( name.equals("ipo_regist_detail_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ipo_regist_detail_div' must be String: '"+value+"' is not." );
                    this.ipo_regist_detail_div = (String) value;
                    if (this.ipo_regist_detail_div_is_set)
                        this.ipo_regist_detail_div_is_modified = true;
                    this.ipo_regist_detail_div_is_set = true;
                    return;
                }
                else if ( name.equals("ipo_unit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ipo_unit_div' must be String: '"+value+"' is not." );
                    this.ipo_unit_div = (String) value;
                    if (this.ipo_unit_div_is_set)
                        this.ipo_unit_div_is_modified = true;
                    this.ipo_unit_div_is_set = true;
                    return;
                }
                else if ( name.equals("ipo_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ipo_stop' must be String: '"+value+"' is not." );
                    this.ipo_stop = (String) value;
                    if (this.ipo_stop_is_set)
                        this.ipo_stop_is_modified = true;
                    this.ipo_stop_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("lead_managing_underwriter") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'lead_managing_underwriter' must be String: '"+value+"' is not." );
                    this.lead_managing_underwriter = (String) value;
                    if (this.lead_managing_underwriter_is_set)
                        this.lead_managing_underwriter_is_modified = true;
                    this.lead_managing_underwriter_is_set = true;
                    return;
                }
                else if ( name.equals("lot_size") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'lot_size' must be Long: '"+value+"' is not." );
                    this.lot_size = (Long) value;
                    if (this.lot_size_is_set)
                        this.lot_size_is_modified = true;
                    this.lot_size_is_set = true;
                    return;
                }
                else if ( name.equals("lot_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'lot_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.lot_date = (java.sql.Timestamp) value;
                    if (this.lot_date_is_set)
                        this.lot_date_is_modified = true;
                    this.lot_date_is_set = true;
                    return;
                }
                else if ( name.equals("lot_date_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'lot_date_count' must be Integer: '"+value+"' is not." );
                    this.lot_date_count = (Integer) value;
                    if (this.lot_date_count_is_set)
                        this.lot_date_count_is_modified = true;
                    this.lot_date_count_is_set = true;
                    return;
                }
                else if ( name.equals("lot_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'lot_status' must be String: '"+value+"' is not." );
                    this.lot_status = (String) value;
                    if (this.lot_status_is_set)
                        this.lot_status_is_modified = true;
                    this.lot_status_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("note") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'note' must be String: '"+value+"' is not." );
                    this.note = (String) value;
                    if (this.note_is_set)
                        this.note_is_modified = true;
                    this.note_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("offer_start_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'offer_start_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.offer_start_datetime = (java.sql.Timestamp) value;
                    if (this.offer_start_datetime_is_set)
                        this.offer_start_datetime_is_modified = true;
                    this.offer_start_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("offer_start_date_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'offer_start_date_count' must be Integer: '"+value+"' is not." );
                    this.offer_start_date_count = (Integer) value;
                    if (this.offer_start_date_count_is_set)
                        this.offer_start_date_count_is_modified = true;
                    this.offer_start_date_count_is_set = true;
                    return;
                }
                else if ( name.equals("offer_end_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'offer_end_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.offer_end_datetime = (java.sql.Timestamp) value;
                    if (this.offer_end_datetime_is_set)
                        this.offer_end_datetime_is_modified = true;
                    this.offer_end_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("offer_end_date_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'offer_end_date_count' must be Integer: '"+value+"' is not." );
                    this.offer_end_date_count = (Integer) value;
                    if (this.offer_end_date_count_is_set)
                        this.offer_end_date_count_is_modified = true;
                    this.offer_end_date_count_is_set = true;
                    return;
                }
                else if ( name.equals("offer_start_date_prospec") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'offer_start_date_prospec' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.offer_start_date_prospec = (java.sql.Timestamp) value;
                    if (this.offer_start_date_prospec_is_set)
                        this.offer_start_date_prospec_is_modified = true;
                    this.offer_start_date_prospec_is_set = true;
                    return;
                }
                else if ( name.equals("offer_start_date_count_prospec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'offer_start_date_count_prospec' must be Integer: '"+value+"' is not." );
                    this.offer_start_date_count_prospec = (Integer) value;
                    if (this.offer_start_date_count_prospec_is_set)
                        this.offer_start_date_count_prospec_is_modified = true;
                    this.offer_start_date_count_prospec_is_set = true;
                    return;
                }
                else if ( name.equals("offer_end_date_prospec") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'offer_end_date_prospec' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.offer_end_date_prospec = (java.sql.Timestamp) value;
                    if (this.offer_end_date_prospec_is_set)
                        this.offer_end_date_prospec_is_modified = true;
                    this.offer_end_date_prospec_is_set = true;
                    return;
                }
                else if ( name.equals("offer_end_date_count_prospec") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'offer_end_date_count_prospec' must be Integer: '"+value+"' is not." );
                    this.offer_end_date_count_prospec = (Integer) value;
                    if (this.offer_end_date_count_prospec_is_set)
                        this.offer_end_date_count_prospec_is_modified = true;
                    this.offer_end_date_count_prospec_is_set = true;
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
                else if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("public_offering_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'public_offering_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.public_offering_date = (java.sql.Timestamp) value;
                    if (this.public_offering_date_is_set)
                        this.public_offering_date_is_modified = true;
                    this.public_offering_date_is_set = true;
                    return;
                }
                else if ( name.equals("public_offering_date_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'public_offering_date_count' must be Integer: '"+value+"' is not." );
                    this.public_offering_date_count = (Integer) value;
                    if (this.public_offering_date_count_is_set)
                        this.public_offering_date_count_is_modified = true;
                    this.public_offering_date_count_is_set = true;
                    return;
                }
                else if ( name.equals("public_market") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'public_market' must be String: '"+value+"' is not." );
                    this.public_market = (String) value;
                    if (this.public_market_is_set)
                        this.public_market_is_modified = true;
                    this.public_market_is_set = true;
                    return;
                }
                else if ( name.equals("provisional_value_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'provisional_value_div' must be String: '"+value+"' is not." );
                    this.provisional_value_div = (String) value;
                    if (this.provisional_value_div_is_set)
                        this.provisional_value_div_is_modified = true;
                    this.provisional_value_div_is_set = true;
                    return;
                }
                else if ( name.equals("provisional_min_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'provisional_min_value' must be Double: '"+value+"' is not." );
                    this.provisional_min_value = (Double) value;
                    if (this.provisional_min_value_is_set)
                        this.provisional_min_value_is_modified = true;
                    this.provisional_min_value_is_set = true;
                    return;
                }
                else if ( name.equals("provisional_max_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'provisional_max_value' must be Double: '"+value+"' is not." );
                    this.provisional_max_value = (Double) value;
                    if (this.provisional_max_value_is_set)
                        this.provisional_max_value_is_modified = true;
                    this.provisional_max_value_is_set = true;
                    return;
                }
                else if ( name.equals("provisional_value_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'provisional_value_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.provisional_value_open_date = (java.sql.Timestamp) value;
                    if (this.provisional_value_open_date_is_set)
                        this.provisional_value_open_date_is_modified = true;
                    this.provisional_value_open_date_is_set = true;
                    return;
                }
                else if ( name.equals("public_offering_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'public_offering_quantity' must be Long: '"+value+"' is not." );
                    this.public_offering_quantity = (Long) value;
                    if (this.public_offering_quantity_is_set)
                        this.public_offering_quantity_is_modified = true;
                    this.public_offering_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("public_sales_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'public_sales_quantity' must be Long: '"+value+"' is not." );
                    this.public_sales_quantity = (Long) value;
                    if (this.public_sales_quantity_is_set)
                        this.public_sales_quantity_is_modified = true;
                    this.public_sales_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("public_price_settle_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'public_price_settle_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.public_price_settle_date = (java.sql.Timestamp) value;
                    if (this.public_price_settle_date_is_set)
                        this.public_price_settle_date_is_modified = true;
                    this.public_price_settle_date_is_set = true;
                    return;
                }
                else if ( name.equals("public_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'public_price' must be Double: '"+value+"' is not." );
                    this.public_price = (Double) value;
                    if (this.public_price_is_set)
                        this.public_price_is_modified = true;
                    this.public_price_is_set = true;
                    return;
                }
                else if ( name.equals("public_price_discount_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'public_price_discount_rate' must be Double: '"+value+"' is not." );
                    this.public_price_discount_rate = (Double) value;
                    if (this.public_price_discount_rate_is_set)
                        this.public_price_discount_rate_is_modified = true;
                    this.public_price_discount_rate_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name' must be String: '"+value+"' is not." );
                    this.standard_name = (String) value;
                    if (this.standard_name_is_set)
                        this.standard_name_is_modified = true;
                    this.standard_name_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tick_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tick_value' must be Double: '"+value+"' is not." );
                    this.tick_value = (Double) value;
                    if (this.tick_value_is_set)
                        this.tick_value_is_modified = true;
                    this.tick_value_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
