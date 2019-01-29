head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.50.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoProductRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * IpoProductRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>ipo_product</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link IpoProductRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoProductPK 
 */
public interface IpoProductRow extends Row {


  /** 
   * ����{@@link IpoProductRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "ipo_product", "master" );


  /** 
   * <em>ipo_product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getIpoProductId();


  /** 
   * <em>ipo_product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoProductIdIsSet();


  /** 
   * <em>ipo_product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoProductIdIsModified();


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
   * <em>product_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductTypeIsSet();


  /** 
   * <em>product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductTypeIsModified();


  /** 
   * <em>ipo_regist_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIpoRegistDiv();


  /** 
   * <em>ipo_regist_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoRegistDivIsSet();


  /** 
   * <em>ipo_regist_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoRegistDivIsModified();


  /** 
   * <em>ipo_regist_detail_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIpoRegistDetailDiv();


  /** 
   * <em>ipo_regist_detail_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoRegistDetailDivIsSet();


  /** 
   * <em>ipo_regist_detail_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoRegistDetailDivIsModified();


  /** 
   * <em>public_offering_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPublicOfferingDate();


  /** 
   * <em>public_offering_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicOfferingDateIsSet();


  /** 
   * <em>public_offering_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicOfferingDateIsModified();


  /** 
   * <em>public_offering_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getPublicOfferingDateCount();


  /** 
   * <em>public_offering_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPublicOfferingDateCountIsNull();


  /** 
   * <em>public_offering_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicOfferingDateCountIsSet();


  /** 
   * <em>public_offering_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicOfferingDateCountIsModified();


  /** 
   * <em>public_market</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPublicMarket();


  /** 
   * <em>public_market</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicMarketIsSet();


  /** 
   * <em>public_market</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicMarketIsModified();


  /** 
   * <em>provisional_value_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProvisionalValueDiv();


  /** 
   * <em>provisional_value_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalValueDivIsSet();


  /** 
   * <em>provisional_value_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalValueDivIsModified();


  /** 
   * <em>provisional_min_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getProvisionalMinValue();


  /** 
   * <em>provisional_min_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getProvisionalMinValueIsNull();


  /** 
   * <em>provisional_min_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalMinValueIsSet();


  /** 
   * <em>provisional_min_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalMinValueIsModified();


  /** 
   * <em>provisional_max_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getProvisionalMaxValue();


  /** 
   * <em>provisional_max_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getProvisionalMaxValueIsNull();


  /** 
   * <em>provisional_max_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalMaxValueIsSet();


  /** 
   * <em>provisional_max_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalMaxValueIsModified();


  /** 
   * <em>provisional_value_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getProvisionalValueOpenDate();


  /** 
   * <em>provisional_value_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalValueOpenDateIsSet();


  /** 
   * <em>provisional_value_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProvisionalValueOpenDateIsModified();


  /** 
   * <em>public_offering_quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getPublicOfferingQuantity();


  /** 
   * <em>public_offering_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPublicOfferingQuantityIsNull();


  /** 
   * <em>public_offering_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicOfferingQuantityIsSet();


  /** 
   * <em>public_offering_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicOfferingQuantityIsModified();


  /** 
   * <em>public_sales_quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getPublicSalesQuantity();


  /** 
   * <em>public_sales_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPublicSalesQuantityIsNull();


  /** 
   * <em>public_sales_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicSalesQuantityIsSet();


  /** 
   * <em>public_sales_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicSalesQuantityIsModified();


  /** 
   * <em>dealing_quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getDealingQuantity();


  /** 
   * <em>dealing_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDealingQuantityIsNull();


  /** 
   * <em>dealing_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDealingQuantityIsSet();


  /** 
   * <em>dealing_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDealingQuantityIsModified();


  /** 
   * <em>lead_managing_underwriter</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLeadManagingUnderwriter();


  /** 
   * <em>lead_managing_underwriter</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLeadManagingUnderwriterIsSet();


  /** 
   * <em>lead_managing_underwriter</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLeadManagingUnderwriterIsModified();


  /** 
   * <em>lot_size</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getLotSize();


  /** 
   * <em>lot_size</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLotSizeIsNull();


  /** 
   * <em>lot_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotSizeIsSet();


  /** 
   * <em>lot_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotSizeIsModified();


  /** 
   * <em>tick_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTickValue();


  /** 
   * <em>tick_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTickValueIsNull();


  /** 
   * <em>tick_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTickValueIsSet();


  /** 
   * <em>tick_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTickValueIsModified();


  /** 
   * <em>ipo_unit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIpoUnitDiv();


  /** 
   * <em>ipo_unit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoUnitDivIsSet();


  /** 
   * <em>ipo_unit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoUnitDivIsModified();


  /** 
   * <em>enable_market_order</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEnableMarketOrder();


  /** 
   * <em>enable_market_order</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableMarketOrderIsSet();


  /** 
   * <em>enable_market_order</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEnableMarketOrderIsModified();


  /** 
   * <em>bookbuilding_start_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBookbuildingStartDatetime();


  /** 
   * <em>bookbuilding_start_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBookbuildingStartDatetimeIsSet();


  /** 
   * <em>bookbuilding_start_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBookbuildingStartDatetimeIsModified();


  /** 
   * <em>bookbuilding_end_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getBookbuildingEndDatetime();


  /** 
   * <em>bookbuilding_end_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBookbuildingEndDatetimeIsSet();


  /** 
   * <em>bookbuilding_end_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBookbuildingEndDatetimeIsModified();


  /** 
   * <em>public_price_settle_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPublicPriceSettleDate();


  /** 
   * <em>public_price_settle_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicPriceSettleDateIsSet();


  /** 
   * <em>public_price_settle_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicPriceSettleDateIsModified();


  /** 
   * <em>public_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPublicPrice();


  /** 
   * <em>public_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPublicPriceIsNull();


  /** 
   * <em>public_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicPriceIsSet();


  /** 
   * <em>public_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicPriceIsModified();


  /** 
   * <em>public_price_discount_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPublicPriceDiscountRate();


  /** 
   * <em>public_price_discount_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPublicPriceDiscountRateIsNull();


  /** 
   * <em>public_price_discount_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicPriceDiscountRateIsSet();


  /** 
   * <em>public_price_discount_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPublicPriceDiscountRateIsModified();


  /** 
   * <em>lot_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLotDate();


  /** 
   * <em>lot_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotDateIsSet();


  /** 
   * <em>lot_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotDateIsModified();


  /** 
   * <em>lot_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLotDateCount();


  /** 
   * <em>lot_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLotDateCountIsNull();


  /** 
   * <em>lot_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotDateCountIsSet();


  /** 
   * <em>lot_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotDateCountIsModified();


  /** 
   * <em>lot_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLotStatus();


  /** 
   * <em>lot_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotStatusIsSet();


  /** 
   * <em>lot_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotStatusIsModified();


  /** 
   * <em>offer_start_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOfferStartDatetime();


  /** 
   * <em>offer_start_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDatetimeIsSet();


  /** 
   * <em>offer_start_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDatetimeIsModified();


  /** 
   * <em>offer_start_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getOfferStartDateCount();


  /** 
   * <em>offer_start_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOfferStartDateCountIsNull();


  /** 
   * <em>offer_start_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDateCountIsSet();


  /** 
   * <em>offer_start_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDateCountIsModified();


  /** 
   * <em>offer_end_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOfferEndDatetime();


  /** 
   * <em>offer_end_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDatetimeIsSet();


  /** 
   * <em>offer_end_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDatetimeIsModified();


  /** 
   * <em>offer_end_date_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getOfferEndDateCount();


  /** 
   * <em>offer_end_date_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOfferEndDateCountIsNull();


  /** 
   * <em>offer_end_date_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDateCountIsSet();


  /** 
   * <em>offer_end_date_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDateCountIsModified();


  /** 
   * <em>offer_start_date_prospec</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOfferStartDateProspec();


  /** 
   * <em>offer_start_date_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDateProspecIsSet();


  /** 
   * <em>offer_start_date_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDateProspecIsModified();


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getOfferStartDateCountProspec();


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOfferStartDateCountProspecIsNull();


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDateCountProspecIsSet();


  /** 
   * <em>offer_start_date_count_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferStartDateCountProspecIsModified();


  /** 
   * <em>offer_end_date_prospec</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOfferEndDateProspec();


  /** 
   * <em>offer_end_date_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDateProspecIsSet();


  /** 
   * <em>offer_end_date_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDateProspecIsModified();


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getOfferEndDateCountProspec();


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOfferEndDateCountProspecIsNull();


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDateCountProspecIsSet();


  /** 
   * <em>offer_end_date_count_prospec</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOfferEndDateCountProspecIsModified();


  /** 
   * <em>company_logo_url</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCompanyLogoUrl();


  /** 
   * <em>company_logo_url</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompanyLogoUrlIsSet();


  /** 
   * <em>company_logo_url</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompanyLogoUrlIsModified();


  /** 
   * <em>company_url</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCompanyUrl();


  /** 
   * <em>company_url</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompanyUrlIsSet();


  /** 
   * <em>company_url</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompanyUrlIsModified();


  /** 
   * <em>company_outline</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCompanyOutline();


  /** 
   * <em>company_outline</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompanyOutlineIsSet();


  /** 
   * <em>company_outline</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompanyOutlineIsModified();


  /** 
   * <em>note</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNote();


  /** 
   * <em>note</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNoteIsSet();


  /** 
   * <em>note</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNoteIsModified();


  /** 
   * <em>ipo_stop</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getIpoStop();


  /** 
   * <em>ipo_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoStopIsSet();


  /** 
   * <em>ipo_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getIpoStopIsModified();


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
   * <em>doc_reading_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDocReadingDiv();


  /** 
   * <em>doc_reading_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDocReadingDivIsSet();


  /** 
   * <em>doc_reading_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDocReadingDivIsModified();


}
@
