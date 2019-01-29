head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.11.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundOrderUnitParams.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mutual_fund_order_unit�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MutualFundOrderUnitRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MutualFundOrderUnitParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MutualFundOrderUnitParams}��{@@link MutualFundOrderUnitRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundOrderUnitPK 
 * @@see MutualFundOrderUnitRow 
 */
public class MutualFundOrderUnitParams extends Params implements MutualFundOrderUnitRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mutual_fund_order_unit";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MutualFundOrderUnitRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MutualFundOrderUnitRow.TYPE;
  }


  /** 
   * <em>order_unit_id</em>�J�����̒l 
   */
  public  long  order_unit_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>trader_id</em>�J�����̒l 
   */
  public  Long  trader_id;

  /** 
   * <em>order_id</em>�J�����̒l 
   */
  public  long  order_id;

  /** 
   * <em>order_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>order_categ</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum  order_categ;

  /** 
   * <em>last_order_action_serial_no</em>�J�����̒l 
   */
  public  int  last_order_action_serial_no;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  Long  market_id;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  double  quantity;

  /** 
   * <em>delivery_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>expiration_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  expiration_date;

  /** 
   * <em>confirmed_quantity</em>�J�����̒l 
   */
  public  Double  confirmed_quantity;

  /** 
   * <em>executed_quantity</em>�J�����̒l 
   */
  public  Double  executed_quantity;

  /** 
   * <em>executed_amount</em>�J�����̒l 
   */
  public  Double  executed_amount;

  /** 
   * <em>order_status</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum  order_status;

  /** 
   * <em>order_open_status</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum  order_open_status;

  /** 
   * <em>expiration_status</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum  expiration_status;

  /** 
   * <em>tax_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type;

  /** 
   * <em>biz_date</em>�J�����̒l 
   */
  public  String  biz_date;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>quantity_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum  quantity_type;

  /** 
   * <em>order_chanel</em>�J�����̒l 
   */
  public  String  order_chanel;

  /** 
   * <em>received_date_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  received_date_time;

  /** 
   * <em>sonar_trader_code</em>�J�����̒l 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>order_request_number</em>�J�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>calc_constant_value</em>�J�����̒l 
   */
  public  Double  calc_constant_value;

  /** 
   * <em>swt_calc_constant_value</em>�J�����̒l 
   */
  public  Double  swt_calc_constant_value;

  /** 
   * <em>constant_value_app_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  constant_value_app_date;

  /** 
   * <em>estimated_price</em>�J�����̒l 
   */
  public  Double  estimated_price;

  /** 
   * <em>estimate_dealing_qty</em>�J�����̒l 
   */
  public  Double  estimate_dealing_qty;

  /** 
   * <em>swt_estimate_dealing_qty</em>�J�����̒l 
   */
  public  Double  swt_estimate_dealing_qty;

  /** 
   * <em>swt_tax_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  swt_tax_type;

  /** 
   * <em>swt_product_code</em>�J�����̒l 
   */
  public  String  swt_product_code;

  /** 
   * <em>payment_method</em>�J�����̒l 
   */
  public  String  payment_method;

  /** 
   * <em>fund_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum  fund_type;

  /** 
   * <em>fund_sell_div</em>�J�����̒l 
   */
  public  String  fund_sell_div;

  /** 
   * <em>exec_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  exec_date;

  /** 
   * <em>exec_status</em>�J�����̒l 
   */
  public  String  exec_status;

  /** 
   * <em>settlement_div</em>�J�����̒l 
   */
  public  String  settlement_div;

  /** 
   * <em>no_contract_commission_div</em>�J�����̒l 
   */
  public  String  no_contract_commission_div;

  /** 
   * <em>request_div</em>�J�����̒l 
   */
  public  String  request_div;

  /** 
   * <em>order_root_div</em>�J�����̒l 
   */
  public  String  order_root_div;

  /** 
   * <em>error_reason_code</em>�J�����̒l 
   */
  public  String  error_reason_code;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>payment_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  payment_date;

  /** 
   * <em>withholding_tax_restriction</em>�J�����̒l 
   */
  public  Double  withholding_tax_restriction;

  /** 
   * <em>payment_order_req_number</em>�J�����̒l 
   */
  public  String  payment_order_req_number;

  /** 
   * <em>cpu_no</em>�J�����̒l 
   */
  public  String  cpu_no;

  /** 
   * <em>swt_exec_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  swt_exec_date;

  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean trader_id_is_set = false;
  private boolean trader_id_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean order_categ_is_set = false;
  private boolean order_categ_is_modified = false;
  private boolean last_order_action_serial_no_is_set = false;
  private boolean last_order_action_serial_no_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean expiration_date_is_set = false;
  private boolean expiration_date_is_modified = false;
  private boolean confirmed_quantity_is_set = false;
  private boolean confirmed_quantity_is_modified = false;
  private boolean executed_quantity_is_set = false;
  private boolean executed_quantity_is_modified = false;
  private boolean executed_amount_is_set = false;
  private boolean executed_amount_is_modified = false;
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean order_open_status_is_set = false;
  private boolean order_open_status_is_modified = false;
  private boolean expiration_status_is_set = false;
  private boolean expiration_status_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean quantity_type_is_set = false;
  private boolean quantity_type_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean calc_constant_value_is_set = false;
  private boolean calc_constant_value_is_modified = false;
  private boolean swt_calc_constant_value_is_set = false;
  private boolean swt_calc_constant_value_is_modified = false;
  private boolean constant_value_app_date_is_set = false;
  private boolean constant_value_app_date_is_modified = false;
  private boolean estimated_price_is_set = false;
  private boolean estimated_price_is_modified = false;
  private boolean estimate_dealing_qty_is_set = false;
  private boolean estimate_dealing_qty_is_modified = false;
  private boolean swt_estimate_dealing_qty_is_set = false;
  private boolean swt_estimate_dealing_qty_is_modified = false;
  private boolean swt_tax_type_is_set = false;
  private boolean swt_tax_type_is_modified = false;
  private boolean swt_product_code_is_set = false;
  private boolean swt_product_code_is_modified = false;
  private boolean payment_method_is_set = false;
  private boolean payment_method_is_modified = false;
  private boolean fund_type_is_set = false;
  private boolean fund_type_is_modified = false;
  private boolean fund_sell_div_is_set = false;
  private boolean fund_sell_div_is_modified = false;
  private boolean exec_date_is_set = false;
  private boolean exec_date_is_modified = false;
  private boolean exec_status_is_set = false;
  private boolean exec_status_is_modified = false;
  private boolean settlement_div_is_set = false;
  private boolean settlement_div_is_modified = false;
  private boolean no_contract_commission_div_is_set = false;
  private boolean no_contract_commission_div_is_modified = false;
  private boolean request_div_is_set = false;
  private boolean request_div_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean payment_date_is_set = false;
  private boolean payment_date_is_modified = false;
  private boolean withholding_tax_restriction_is_set = false;
  private boolean withholding_tax_restriction_is_modified = false;
  private boolean payment_order_req_number_is_set = false;
  private boolean payment_order_req_number_is_modified = false;
  private boolean cpu_no_is_set = false;
  private boolean cpu_no_is_modified = false;
  private boolean swt_exec_date_is_set = false;
  private boolean swt_exec_date_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "order_unit_id=" + order_unit_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "branch_id=" +branch_id
      + "," + "trader_id=" +trader_id
      + "," + "order_id=" +order_id
      + "," + "order_type=" +order_type
      + "," + "order_categ=" +order_categ
      + "," + "last_order_action_serial_no=" +last_order_action_serial_no
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "quantity=" +quantity
      + "," + "delivery_date=" +delivery_date
      + "," + "expiration_date=" +expiration_date
      + "," + "confirmed_quantity=" +confirmed_quantity
      + "," + "executed_quantity=" +executed_quantity
      + "," + "executed_amount=" +executed_amount
      + "," + "order_status=" +order_status
      + "," + "order_open_status=" +order_open_status
      + "," + "expiration_status=" +expiration_status
      + "," + "tax_type=" +tax_type
      + "," + "biz_date=" +biz_date
      + "," + "product_id=" +product_id
      + "," + "quantity_type=" +quantity_type
      + "," + "order_chanel=" +order_chanel
      + "," + "received_date_time=" +received_date_time
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "order_request_number=" +order_request_number
      + "," + "calc_constant_value=" +calc_constant_value
      + "," + "swt_calc_constant_value=" +swt_calc_constant_value
      + "," + "constant_value_app_date=" +constant_value_app_date
      + "," + "estimated_price=" +estimated_price
      + "," + "estimate_dealing_qty=" +estimate_dealing_qty
      + "," + "swt_estimate_dealing_qty=" +swt_estimate_dealing_qty
      + "," + "swt_tax_type=" +swt_tax_type
      + "," + "swt_product_code=" +swt_product_code
      + "," + "payment_method=" +payment_method
      + "," + "fund_type=" +fund_type
      + "," + "fund_sell_div=" +fund_sell_div
      + "," + "exec_date=" +exec_date
      + "," + "exec_status=" +exec_status
      + "," + "settlement_div=" +settlement_div
      + "," + "no_contract_commission_div=" +no_contract_commission_div
      + "," + "request_div=" +request_div
      + "," + "order_root_div=" +order_root_div
      + "," + "error_reason_code=" +error_reason_code
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "payment_date=" +payment_date
      + "," + "withholding_tax_restriction=" +withholding_tax_restriction
      + "," + "payment_order_req_number=" +payment_order_req_number
      + "," + "cpu_no=" +cpu_no
      + "," + "swt_exec_date=" +swt_exec_date
      + "}";
  }


  /** 
   * �l�����ݒ��MutualFundOrderUnitParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MutualFundOrderUnitParams() {
    order_unit_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MutualFundOrderUnitRow�I�u�W�F�N�g�̒l�𗘗p����MutualFundOrderUnitParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MutualFundOrderUnitRow�I�u�W�F�N�g 
   */
  public MutualFundOrderUnitParams( MutualFundOrderUnitRow p_row )
  {
    order_unit_id = p_row.getOrderUnitId();
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    if ( !p_row.getTraderIdIsNull() )
      trader_id = new Long( p_row.getTraderId() );
    trader_id_is_set = p_row.getTraderIdIsSet();
    trader_id_is_modified = p_row.getTraderIdIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    order_categ = p_row.getOrderCateg();
    order_categ_is_set = p_row.getOrderCategIsSet();
    order_categ_is_modified = p_row.getOrderCategIsModified();
    last_order_action_serial_no = p_row.getLastOrderActionSerialNo();
    last_order_action_serial_no_is_set = p_row.getLastOrderActionSerialNoIsSet();
    last_order_action_serial_no_is_modified = p_row.getLastOrderActionSerialNoIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getMarketIdIsNull() )
      market_id = new Long( p_row.getMarketId() );
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    expiration_date = p_row.getExpirationDate();
    expiration_date_is_set = p_row.getExpirationDateIsSet();
    expiration_date_is_modified = p_row.getExpirationDateIsModified();
    if ( !p_row.getConfirmedQuantityIsNull() )
      confirmed_quantity = new Double( p_row.getConfirmedQuantity() );
    confirmed_quantity_is_set = p_row.getConfirmedQuantityIsSet();
    confirmed_quantity_is_modified = p_row.getConfirmedQuantityIsModified();
    if ( !p_row.getExecutedQuantityIsNull() )
      executed_quantity = new Double( p_row.getExecutedQuantity() );
    executed_quantity_is_set = p_row.getExecutedQuantityIsSet();
    executed_quantity_is_modified = p_row.getExecutedQuantityIsModified();
    if ( !p_row.getExecutedAmountIsNull() )
      executed_amount = new Double( p_row.getExecutedAmount() );
    executed_amount_is_set = p_row.getExecutedAmountIsSet();
    executed_amount_is_modified = p_row.getExecutedAmountIsModified();
    order_status = p_row.getOrderStatus();
    order_status_is_set = p_row.getOrderStatusIsSet();
    order_status_is_modified = p_row.getOrderStatusIsModified();
    order_open_status = p_row.getOrderOpenStatus();
    order_open_status_is_set = p_row.getOrderOpenStatusIsSet();
    order_open_status_is_modified = p_row.getOrderOpenStatusIsModified();
    expiration_status = p_row.getExpirationStatus();
    expiration_status_is_set = p_row.getExpirationStatusIsSet();
    expiration_status_is_modified = p_row.getExpirationStatusIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    quantity_type = p_row.getQuantityType();
    quantity_type_is_set = p_row.getQuantityTypeIsSet();
    quantity_type_is_modified = p_row.getQuantityTypeIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    if ( !p_row.getCalcConstantValueIsNull() )
      calc_constant_value = new Double( p_row.getCalcConstantValue() );
    calc_constant_value_is_set = p_row.getCalcConstantValueIsSet();
    calc_constant_value_is_modified = p_row.getCalcConstantValueIsModified();
    if ( !p_row.getSwtCalcConstantValueIsNull() )
      swt_calc_constant_value = new Double( p_row.getSwtCalcConstantValue() );
    swt_calc_constant_value_is_set = p_row.getSwtCalcConstantValueIsSet();
    swt_calc_constant_value_is_modified = p_row.getSwtCalcConstantValueIsModified();
    constant_value_app_date = p_row.getConstantValueAppDate();
    constant_value_app_date_is_set = p_row.getConstantValueAppDateIsSet();
    constant_value_app_date_is_modified = p_row.getConstantValueAppDateIsModified();
    if ( !p_row.getEstimatedPriceIsNull() )
      estimated_price = new Double( p_row.getEstimatedPrice() );
    estimated_price_is_set = p_row.getEstimatedPriceIsSet();
    estimated_price_is_modified = p_row.getEstimatedPriceIsModified();
    if ( !p_row.getEstimateDealingQtyIsNull() )
      estimate_dealing_qty = new Double( p_row.getEstimateDealingQty() );
    estimate_dealing_qty_is_set = p_row.getEstimateDealingQtyIsSet();
    estimate_dealing_qty_is_modified = p_row.getEstimateDealingQtyIsModified();
    if ( !p_row.getSwtEstimateDealingQtyIsNull() )
      swt_estimate_dealing_qty = new Double( p_row.getSwtEstimateDealingQty() );
    swt_estimate_dealing_qty_is_set = p_row.getSwtEstimateDealingQtyIsSet();
    swt_estimate_dealing_qty_is_modified = p_row.getSwtEstimateDealingQtyIsModified();
    swt_tax_type = p_row.getSwtTaxType();
    swt_tax_type_is_set = p_row.getSwtTaxTypeIsSet();
    swt_tax_type_is_modified = p_row.getSwtTaxTypeIsModified();
    swt_product_code = p_row.getSwtProductCode();
    swt_product_code_is_set = p_row.getSwtProductCodeIsSet();
    swt_product_code_is_modified = p_row.getSwtProductCodeIsModified();
    payment_method = p_row.getPaymentMethod();
    payment_method_is_set = p_row.getPaymentMethodIsSet();
    payment_method_is_modified = p_row.getPaymentMethodIsModified();
    fund_type = p_row.getFundType();
    fund_type_is_set = p_row.getFundTypeIsSet();
    fund_type_is_modified = p_row.getFundTypeIsModified();
    fund_sell_div = p_row.getFundSellDiv();
    fund_sell_div_is_set = p_row.getFundSellDivIsSet();
    fund_sell_div_is_modified = p_row.getFundSellDivIsModified();
    exec_date = p_row.getExecDate();
    exec_date_is_set = p_row.getExecDateIsSet();
    exec_date_is_modified = p_row.getExecDateIsModified();
    exec_status = p_row.getExecStatus();
    exec_status_is_set = p_row.getExecStatusIsSet();
    exec_status_is_modified = p_row.getExecStatusIsModified();
    settlement_div = p_row.getSettlementDiv();
    settlement_div_is_set = p_row.getSettlementDivIsSet();
    settlement_div_is_modified = p_row.getSettlementDivIsModified();
    no_contract_commission_div = p_row.getNoContractCommissionDiv();
    no_contract_commission_div_is_set = p_row.getNoContractCommissionDivIsSet();
    no_contract_commission_div_is_modified = p_row.getNoContractCommissionDivIsModified();
    request_div = p_row.getRequestDiv();
    request_div_is_set = p_row.getRequestDivIsSet();
    request_div_is_modified = p_row.getRequestDivIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    payment_date = p_row.getPaymentDate();
    payment_date_is_set = p_row.getPaymentDateIsSet();
    payment_date_is_modified = p_row.getPaymentDateIsModified();
    if ( !p_row.getWithholdingTaxRestrictionIsNull() )
      withholding_tax_restriction = new Double( p_row.getWithholdingTaxRestriction() );
    withholding_tax_restriction_is_set = p_row.getWithholdingTaxRestrictionIsSet();
    withholding_tax_restriction_is_modified = p_row.getWithholdingTaxRestrictionIsModified();
    payment_order_req_number = p_row.getPaymentOrderReqNumber();
    payment_order_req_number_is_set = p_row.getPaymentOrderReqNumberIsSet();
    payment_order_req_number_is_modified = p_row.getPaymentOrderReqNumberIsModified();
    cpu_no = p_row.getCpuNo();
    cpu_no_is_set = p_row.getCpuNoIsSet();
    cpu_no_is_modified = p_row.getCpuNoIsModified();
    swt_exec_date = p_row.getSwtExecDate();
    swt_exec_date_is_set = p_row.getSwtExecDateIsSet();
    swt_exec_date_is_modified = p_row.getSwtExecDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      trader_id_is_set = true;
      trader_id_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      order_categ_is_set = true;
      order_categ_is_modified = true;
      last_order_action_serial_no_is_set = true;
      last_order_action_serial_no_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      expiration_date_is_set = true;
      expiration_date_is_modified = true;
      confirmed_quantity_is_set = true;
      confirmed_quantity_is_modified = true;
      executed_quantity_is_set = true;
      executed_quantity_is_modified = true;
      executed_amount_is_set = true;
      executed_amount_is_modified = true;
      order_status_is_set = true;
      order_status_is_modified = true;
      order_open_status_is_set = true;
      order_open_status_is_modified = true;
      expiration_status_is_set = true;
      expiration_status_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      quantity_type_is_set = true;
      quantity_type_is_modified = true;
      order_chanel_is_set = true;
      order_chanel_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      calc_constant_value_is_set = true;
      calc_constant_value_is_modified = true;
      swt_calc_constant_value_is_set = true;
      swt_calc_constant_value_is_modified = true;
      constant_value_app_date_is_set = true;
      constant_value_app_date_is_modified = true;
      estimated_price_is_set = true;
      estimated_price_is_modified = true;
      estimate_dealing_qty_is_set = true;
      estimate_dealing_qty_is_modified = true;
      swt_estimate_dealing_qty_is_set = true;
      swt_estimate_dealing_qty_is_modified = true;
      swt_tax_type_is_set = true;
      swt_tax_type_is_modified = true;
      swt_product_code_is_set = true;
      swt_product_code_is_modified = true;
      payment_method_is_set = true;
      payment_method_is_modified = true;
      fund_type_is_set = true;
      fund_type_is_modified = true;
      fund_sell_div_is_set = true;
      fund_sell_div_is_modified = true;
      exec_date_is_set = true;
      exec_date_is_modified = true;
      exec_status_is_set = true;
      exec_status_is_modified = true;
      settlement_div_is_set = true;
      settlement_div_is_modified = true;
      no_contract_commission_div_is_set = true;
      no_contract_commission_div_is_modified = true;
      request_div_is_set = true;
      request_div_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      payment_date_is_set = true;
      payment_date_is_modified = true;
      withholding_tax_restriction_is_set = true;
      withholding_tax_restriction_is_modified = true;
      payment_order_req_number_is_set = true;
      payment_order_req_number_is_modified = true;
      cpu_no_is_set = true;
      cpu_no_is_modified = true;
      swt_exec_date_is_set = true;
      swt_exec_date_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MutualFundOrderUnitRow ) )
       return false;
    return fieldsEqual( (MutualFundOrderUnitRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MutualFundOrderUnitRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MutualFundOrderUnitRow row )
  {
    if ( order_unit_id != row.getOrderUnitId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( trader_id == null ) {
      if ( !row.getTraderIdIsNull() )
        return false;
    } else if ( row.getTraderIdIsNull() || ( trader_id.longValue() != row.getTraderId() ) ) {
        return false;
    }
    if ( order_id != row.getOrderId() )
      return false;
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( order_categ == null ) {
      if ( row.getOrderCateg() != null )
        return false;
    } else if ( !order_categ.equals( row.getOrderCateg() ) ) {
        return false;
    }
    if ( last_order_action_serial_no != row.getLastOrderActionSerialNo() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( market_id == null ) {
      if ( !row.getMarketIdIsNull() )
        return false;
    } else if ( row.getMarketIdIsNull() || ( market_id.longValue() != row.getMarketId() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( expiration_date == null ) {
      if ( row.getExpirationDate() != null )
        return false;
    } else if ( !expiration_date.equals( row.getExpirationDate() ) ) {
        return false;
    }
    if ( confirmed_quantity == null ) {
      if ( !row.getConfirmedQuantityIsNull() )
        return false;
    } else if ( row.getConfirmedQuantityIsNull() || ( confirmed_quantity.doubleValue() != row.getConfirmedQuantity() ) ) {
        return false;
    }
    if ( executed_quantity == null ) {
      if ( !row.getExecutedQuantityIsNull() )
        return false;
    } else if ( row.getExecutedQuantityIsNull() || ( executed_quantity.doubleValue() != row.getExecutedQuantity() ) ) {
        return false;
    }
    if ( executed_amount == null ) {
      if ( !row.getExecutedAmountIsNull() )
        return false;
    } else if ( row.getExecutedAmountIsNull() || ( executed_amount.doubleValue() != row.getExecutedAmount() ) ) {
        return false;
    }
    if ( order_status == null ) {
      if ( row.getOrderStatus() != null )
        return false;
    } else if ( !order_status.equals( row.getOrderStatus() ) ) {
        return false;
    }
    if ( order_open_status == null ) {
      if ( row.getOrderOpenStatus() != null )
        return false;
    } else if ( !order_open_status.equals( row.getOrderOpenStatus() ) ) {
        return false;
    }
    if ( expiration_status == null ) {
      if ( row.getExpirationStatus() != null )
        return false;
    } else if ( !expiration_status.equals( row.getExpirationStatus() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( quantity_type == null ) {
      if ( row.getQuantityType() != null )
        return false;
    } else if ( !quantity_type.equals( row.getQuantityType() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( received_date_time == null ) {
      if ( row.getReceivedDateTime() != null )
        return false;
    } else if ( !received_date_time.equals( row.getReceivedDateTime() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( calc_constant_value == null ) {
      if ( !row.getCalcConstantValueIsNull() )
        return false;
    } else if ( row.getCalcConstantValueIsNull() || ( calc_constant_value.doubleValue() != row.getCalcConstantValue() ) ) {
        return false;
    }
    if ( swt_calc_constant_value == null ) {
      if ( !row.getSwtCalcConstantValueIsNull() )
        return false;
    } else if ( row.getSwtCalcConstantValueIsNull() || ( swt_calc_constant_value.doubleValue() != row.getSwtCalcConstantValue() ) ) {
        return false;
    }
    if ( constant_value_app_date == null ) {
      if ( row.getConstantValueAppDate() != null )
        return false;
    } else if ( !constant_value_app_date.equals( row.getConstantValueAppDate() ) ) {
        return false;
    }
    if ( estimated_price == null ) {
      if ( !row.getEstimatedPriceIsNull() )
        return false;
    } else if ( row.getEstimatedPriceIsNull() || ( estimated_price.doubleValue() != row.getEstimatedPrice() ) ) {
        return false;
    }
    if ( estimate_dealing_qty == null ) {
      if ( !row.getEstimateDealingQtyIsNull() )
        return false;
    } else if ( row.getEstimateDealingQtyIsNull() || ( estimate_dealing_qty.doubleValue() != row.getEstimateDealingQty() ) ) {
        return false;
    }
    if ( swt_estimate_dealing_qty == null ) {
      if ( !row.getSwtEstimateDealingQtyIsNull() )
        return false;
    } else if ( row.getSwtEstimateDealingQtyIsNull() || ( swt_estimate_dealing_qty.doubleValue() != row.getSwtEstimateDealingQty() ) ) {
        return false;
    }
    if ( swt_tax_type == null ) {
      if ( row.getSwtTaxType() != null )
        return false;
    } else if ( !swt_tax_type.equals( row.getSwtTaxType() ) ) {
        return false;
    }
    if ( swt_product_code == null ) {
      if ( row.getSwtProductCode() != null )
        return false;
    } else if ( !swt_product_code.equals( row.getSwtProductCode() ) ) {
        return false;
    }
    if ( payment_method == null ) {
      if ( row.getPaymentMethod() != null )
        return false;
    } else if ( !payment_method.equals( row.getPaymentMethod() ) ) {
        return false;
    }
    if ( fund_type == null ) {
      if ( row.getFundType() != null )
        return false;
    } else if ( !fund_type.equals( row.getFundType() ) ) {
        return false;
    }
    if ( fund_sell_div == null ) {
      if ( row.getFundSellDiv() != null )
        return false;
    } else if ( !fund_sell_div.equals( row.getFundSellDiv() ) ) {
        return false;
    }
    if ( exec_date == null ) {
      if ( row.getExecDate() != null )
        return false;
    } else if ( !exec_date.equals( row.getExecDate() ) ) {
        return false;
    }
    if ( exec_status == null ) {
      if ( row.getExecStatus() != null )
        return false;
    } else if ( !exec_status.equals( row.getExecStatus() ) ) {
        return false;
    }
    if ( settlement_div == null ) {
      if ( row.getSettlementDiv() != null )
        return false;
    } else if ( !settlement_div.equals( row.getSettlementDiv() ) ) {
        return false;
    }
    if ( no_contract_commission_div == null ) {
      if ( row.getNoContractCommissionDiv() != null )
        return false;
    } else if ( !no_contract_commission_div.equals( row.getNoContractCommissionDiv() ) ) {
        return false;
    }
    if ( request_div == null ) {
      if ( row.getRequestDiv() != null )
        return false;
    } else if ( !request_div.equals( row.getRequestDiv() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
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
    if ( payment_date == null ) {
      if ( row.getPaymentDate() != null )
        return false;
    } else if ( !payment_date.equals( row.getPaymentDate() ) ) {
        return false;
    }
    if ( withholding_tax_restriction == null ) {
      if ( !row.getWithholdingTaxRestrictionIsNull() )
        return false;
    } else if ( row.getWithholdingTaxRestrictionIsNull() || ( withholding_tax_restriction.doubleValue() != row.getWithholdingTaxRestriction() ) ) {
        return false;
    }
    if ( payment_order_req_number == null ) {
      if ( row.getPaymentOrderReqNumber() != null )
        return false;
    } else if ( !payment_order_req_number.equals( row.getPaymentOrderReqNumber() ) ) {
        return false;
    }
    if ( cpu_no == null ) {
      if ( row.getCpuNo() != null )
        return false;
    } else if ( !cpu_no.equals( row.getCpuNo() ) ) {
        return false;
    }
    if ( swt_exec_date == null ) {
      if ( row.getSwtExecDate() != null )
        return false;
    } else if ( !swt_exec_date.equals( row.getSwtExecDate() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) order_unit_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) branch_id)
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + ((int) order_id)
        + (order_type!=null? order_type.hashCode(): 0) 
        + (order_categ!=null? order_categ.hashCode(): 0) 
        + ((int) last_order_action_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + ((int) quantity)
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (expiration_date!=null? expiration_date.hashCode(): 0) 
        + (confirmed_quantity!=null? confirmed_quantity.hashCode(): 0) 
        + (executed_quantity!=null? executed_quantity.hashCode(): 0) 
        + (executed_amount!=null? executed_amount.hashCode(): 0) 
        + (order_status!=null? order_status.hashCode(): 0) 
        + (order_open_status!=null? order_open_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + ((int) product_id)
        + (quantity_type!=null? quantity_type.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (calc_constant_value!=null? calc_constant_value.hashCode(): 0) 
        + (swt_calc_constant_value!=null? swt_calc_constant_value.hashCode(): 0) 
        + (constant_value_app_date!=null? constant_value_app_date.hashCode(): 0) 
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (estimate_dealing_qty!=null? estimate_dealing_qty.hashCode(): 0) 
        + (swt_estimate_dealing_qty!=null? swt_estimate_dealing_qty.hashCode(): 0) 
        + (swt_tax_type!=null? swt_tax_type.hashCode(): 0) 
        + (swt_product_code!=null? swt_product_code.hashCode(): 0) 
        + (payment_method!=null? payment_method.hashCode(): 0) 
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (fund_sell_div!=null? fund_sell_div.hashCode(): 0) 
        + (exec_date!=null? exec_date.hashCode(): 0) 
        + (exec_status!=null? exec_status.hashCode(): 0) 
        + (settlement_div!=null? settlement_div.hashCode(): 0) 
        + (no_contract_commission_div!=null? no_contract_commission_div.hashCode(): 0) 
        + (request_div!=null? request_div.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (payment_date!=null? payment_date.hashCode(): 0) 
        + (withholding_tax_restriction!=null? withholding_tax_restriction.hashCode(): 0) 
        + (payment_order_req_number!=null? payment_order_req_number.hashCode(): 0) 
        + (cpu_no!=null? cpu_no.hashCode(): 0) 
        + (swt_exec_date!=null? swt_exec_date.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !order_categ_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_categ' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !expiration_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'expiration_status' must be set before inserting.");
		if ( !tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("order_unit_id",new Long(order_unit_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("branch_id",new Long(branch_id));
		if ( trader_id != null )
			map.put("trader_id",trader_id);
		map.put("order_id",new Long(order_id));
		map.put("order_type",order_type);
		map.put("order_categ",order_categ);
		if ( last_order_action_serial_no_is_set )
			map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
		map.put("product_type",product_type);
		if ( market_id != null )
			map.put("market_id",market_id);
		map.put("quantity",new Double(quantity));
		map.put("delivery_date",delivery_date);
		if ( expiration_date != null )
			map.put("expiration_date",expiration_date);
		if ( confirmed_quantity != null )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( executed_quantity != null )
			map.put("executed_quantity",executed_quantity);
		if ( executed_amount != null )
			map.put("executed_amount",executed_amount);
		if ( order_status_is_set )
			map.put("order_status",order_status);
		if ( order_open_status_is_set )
			map.put("order_open_status",order_open_status);
		map.put("expiration_status",expiration_status);
		map.put("tax_type",tax_type);
		map.put("biz_date",biz_date);
		map.put("product_id",new Long(product_id));
		if ( quantity_type_is_set )
			map.put("quantity_type",quantity_type);
		if ( order_chanel != null )
			map.put("order_chanel",order_chanel);
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( calc_constant_value != null )
			map.put("calc_constant_value",calc_constant_value);
		if ( swt_calc_constant_value != null )
			map.put("swt_calc_constant_value",swt_calc_constant_value);
		if ( constant_value_app_date != null )
			map.put("constant_value_app_date",constant_value_app_date);
		if ( estimated_price != null )
			map.put("estimated_price",estimated_price);
		if ( estimate_dealing_qty != null )
			map.put("estimate_dealing_qty",estimate_dealing_qty);
		if ( swt_estimate_dealing_qty != null )
			map.put("swt_estimate_dealing_qty",swt_estimate_dealing_qty);
		if ( swt_tax_type != null )
			map.put("swt_tax_type",swt_tax_type);
		if ( swt_product_code != null )
			map.put("swt_product_code",swt_product_code);
		if ( payment_method != null )
			map.put("payment_method",payment_method);
		if ( fund_type != null )
			map.put("fund_type",fund_type);
		if ( fund_sell_div != null )
			map.put("fund_sell_div",fund_sell_div);
		if ( exec_date != null )
			map.put("exec_date",exec_date);
		if ( exec_status != null )
			map.put("exec_status",exec_status);
		if ( settlement_div != null )
			map.put("settlement_div",settlement_div);
		if ( no_contract_commission_div != null )
			map.put("no_contract_commission_div",no_contract_commission_div);
		if ( request_div != null )
			map.put("request_div",request_div);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( payment_date != null )
			map.put("payment_date",payment_date);
		if ( withholding_tax_restriction != null )
			map.put("withholding_tax_restriction",withholding_tax_restriction);
		if ( payment_order_req_number != null )
			map.put("payment_order_req_number",payment_order_req_number);
		if ( cpu_no != null )
			map.put("cpu_no",cpu_no);
		if ( swt_exec_date_is_set )
			map.put("swt_exec_date",swt_exec_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( trader_id_is_modified )
			map.put("trader_id",trader_id);
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( order_categ_is_modified )
			map.put("order_categ",order_categ);
		if ( last_order_action_serial_no_is_modified )
			map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( expiration_date_is_modified )
			map.put("expiration_date",expiration_date);
		if ( confirmed_quantity_is_modified )
			map.put("confirmed_quantity",confirmed_quantity);
		if ( executed_quantity_is_modified )
			map.put("executed_quantity",executed_quantity);
		if ( executed_amount_is_modified )
			map.put("executed_amount",executed_amount);
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( order_open_status_is_modified )
			map.put("order_open_status",order_open_status);
		if ( expiration_status_is_modified )
			map.put("expiration_status",expiration_status);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( quantity_type_is_modified )
			map.put("quantity_type",quantity_type);
		if ( order_chanel_is_modified )
			map.put("order_chanel",order_chanel);
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( calc_constant_value_is_modified )
			map.put("calc_constant_value",calc_constant_value);
		if ( swt_calc_constant_value_is_modified )
			map.put("swt_calc_constant_value",swt_calc_constant_value);
		if ( constant_value_app_date_is_modified )
			map.put("constant_value_app_date",constant_value_app_date);
		if ( estimated_price_is_modified )
			map.put("estimated_price",estimated_price);
		if ( estimate_dealing_qty_is_modified )
			map.put("estimate_dealing_qty",estimate_dealing_qty);
		if ( swt_estimate_dealing_qty_is_modified )
			map.put("swt_estimate_dealing_qty",swt_estimate_dealing_qty);
		if ( swt_tax_type_is_modified )
			map.put("swt_tax_type",swt_tax_type);
		if ( swt_product_code_is_modified )
			map.put("swt_product_code",swt_product_code);
		if ( payment_method_is_modified )
			map.put("payment_method",payment_method);
		if ( fund_type_is_modified )
			map.put("fund_type",fund_type);
		if ( fund_sell_div_is_modified )
			map.put("fund_sell_div",fund_sell_div);
		if ( exec_date_is_modified )
			map.put("exec_date",exec_date);
		if ( exec_status_is_modified )
			map.put("exec_status",exec_status);
		if ( settlement_div_is_modified )
			map.put("settlement_div",settlement_div);
		if ( no_contract_commission_div_is_modified )
			map.put("no_contract_commission_div",no_contract_commission_div);
		if ( request_div_is_modified )
			map.put("request_div",request_div);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( payment_date_is_modified )
			map.put("payment_date",payment_date);
		if ( withholding_tax_restriction_is_modified )
			map.put("withholding_tax_restriction",withholding_tax_restriction);
		if ( payment_order_req_number_is_modified )
			map.put("payment_order_req_number",payment_order_req_number);
		if ( cpu_no_is_modified )
			map.put("cpu_no",cpu_no);
		if ( swt_exec_date_is_modified )
			map.put("swt_exec_date",swt_exec_date);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			map.put("trader_id",trader_id);
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( order_categ_is_set )
				map.put("order_categ",order_categ);
			if ( last_order_action_serial_no_is_set )
				map.put("last_order_action_serial_no",new Integer(last_order_action_serial_no));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("market_id",market_id);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			map.put("expiration_date",expiration_date);
			map.put("confirmed_quantity",confirmed_quantity);
			map.put("executed_quantity",executed_quantity);
			map.put("executed_amount",executed_amount);
			if ( order_status_is_set )
				map.put("order_status",order_status);
			if ( order_open_status_is_set )
				map.put("order_open_status",order_open_status);
			if ( expiration_status_is_set )
				map.put("expiration_status",expiration_status);
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( quantity_type_is_set )
				map.put("quantity_type",quantity_type);
			map.put("order_chanel",order_chanel);
			map.put("received_date_time",received_date_time);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("order_request_number",order_request_number);
			map.put("calc_constant_value",calc_constant_value);
			map.put("swt_calc_constant_value",swt_calc_constant_value);
			map.put("constant_value_app_date",constant_value_app_date);
			map.put("estimated_price",estimated_price);
			map.put("estimate_dealing_qty",estimate_dealing_qty);
			map.put("swt_estimate_dealing_qty",swt_estimate_dealing_qty);
			map.put("swt_tax_type",swt_tax_type);
			map.put("swt_product_code",swt_product_code);
			map.put("payment_method",payment_method);
			map.put("fund_type",fund_type);
			map.put("fund_sell_div",fund_sell_div);
			map.put("exec_date",exec_date);
			map.put("exec_status",exec_status);
			map.put("settlement_div",settlement_div);
			map.put("no_contract_commission_div",no_contract_commission_div);
			map.put("request_div",request_div);
			map.put("order_root_div",order_root_div);
			map.put("error_reason_code",error_reason_code);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("payment_date",payment_date);
			map.put("withholding_tax_restriction",withholding_tax_restriction);
			map.put("payment_order_req_number",payment_order_req_number);
			map.put("cpu_no",cpu_no);
			if ( swt_exec_date_is_set )
				map.put("swt_exec_date",swt_exec_date);
		}
		return map;
	}


  /** 
   * <em>order_unit_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderUnitId()
  {
    return order_unit_id;
  }


  /** 
   * <em>order_unit_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderUnitIdIsSet() {
    return order_unit_id_is_set;
  }


  /** 
   * <em>order_unit_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderUnitIdIsModified() {
    return order_unit_id_is_modified;
  }


  /** 
   * <em>account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>trader_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTraderId()
  {
    return ( trader_id==null? ((long)0): trader_id.longValue() );
  }


  /** 
   * <em>trader_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTraderIdIsNull()
  {
    return trader_id == null;
  }


  /** 
   * <em>trader_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderIdIsSet() {
    return trader_id_is_set;
  }


  /** 
   * <em>trader_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderIdIsModified() {
    return trader_id_is_modified;
  }


  /** 
   * <em>order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderId()
  {
    return order_id;
  }


  /** 
   * <em>order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>order_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType()
  {
    return order_type;
  }


  /** 
   * <em>order_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderTypeIsSet() {
    return order_type_is_set;
  }


  /** 
   * <em>order_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderTypeIsModified() {
    return order_type_is_modified;
  }


  /** 
   * <em>order_categ</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum getOrderCateg()
  {
    return order_categ;
  }


  /** 
   * <em>order_categ</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderCategIsSet() {
    return order_categ_is_set;
  }


  /** 
   * <em>order_categ</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderCategIsModified() {
    return order_categ_is_modified;
  }


  /** 
   * <em>last_order_action_serial_no</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getLastOrderActionSerialNo()
  {
    return last_order_action_serial_no;
  }


  /** 
   * <em>last_order_action_serial_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastOrderActionSerialNoIsSet() {
    return last_order_action_serial_no_is_set;
  }


  /** 
   * <em>last_order_action_serial_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastOrderActionSerialNoIsModified() {
    return last_order_action_serial_no_is_modified;
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
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMarketId()
  {
    return ( market_id==null? ((long)0): market_id.longValue() );
  }


  /** 
   * <em>market_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMarketIdIsNull()
  {
    return market_id == null;
  }


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantity()
  {
    return quantity;
  }


  /** 
   * <em>quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityIsSet() {
    return quantity_is_set;
  }


  /** 
   * <em>quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityIsModified() {
    return quantity_is_modified;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>expiration_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getExpirationDate()
  {
    return expiration_date;
  }


  /** 
   * <em>expiration_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationDateIsSet() {
    return expiration_date_is_set;
  }


  /** 
   * <em>expiration_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationDateIsModified() {
    return expiration_date_is_modified;
  }


  /** 
   * <em>confirmed_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getConfirmedQuantity()
  {
    return ( confirmed_quantity==null? ((double)0): confirmed_quantity.doubleValue() );
  }


  /** 
   * <em>confirmed_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getConfirmedQuantityIsNull()
  {
    return confirmed_quantity == null;
  }


  /** 
   * <em>confirmed_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConfirmedQuantityIsSet() {
    return confirmed_quantity_is_set;
  }


  /** 
   * <em>confirmed_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConfirmedQuantityIsModified() {
    return confirmed_quantity_is_modified;
  }


  /** 
   * <em>executed_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getExecutedQuantity()
  {
    return ( executed_quantity==null? ((double)0): executed_quantity.doubleValue() );
  }


  /** 
   * <em>executed_quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getExecutedQuantityIsNull()
  {
    return executed_quantity == null;
  }


  /** 
   * <em>executed_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecutedQuantityIsSet() {
    return executed_quantity_is_set;
  }


  /** 
   * <em>executed_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecutedQuantityIsModified() {
    return executed_quantity_is_modified;
  }


  /** 
   * <em>executed_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getExecutedAmount()
  {
    return ( executed_amount==null? ((double)0): executed_amount.doubleValue() );
  }


  /** 
   * <em>executed_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getExecutedAmountIsNull()
  {
    return executed_amount == null;
  }


  /** 
   * <em>executed_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecutedAmountIsSet() {
    return executed_amount_is_set;
  }


  /** 
   * <em>executed_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecutedAmountIsModified() {
    return executed_amount_is_modified;
  }


  /** 
   * <em>order_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum getOrderStatus()
  {
    return order_status;
  }


  /** 
   * <em>order_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderStatusIsSet() {
    return order_status_is_set;
  }


  /** 
   * <em>order_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderStatusIsModified() {
    return order_status_is_modified;
  }


  /** 
   * <em>order_open_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum getOrderOpenStatus()
  {
    return order_open_status;
  }


  /** 
   * <em>order_open_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderOpenStatusIsSet() {
    return order_open_status_is_set;
  }


  /** 
   * <em>order_open_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderOpenStatusIsModified() {
    return order_open_status_is_modified;
  }


  /** 
   * <em>expiration_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum getExpirationStatus()
  {
    return expiration_status;
  }


  /** 
   * <em>expiration_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationStatusIsSet() {
    return expiration_status_is_set;
  }


  /** 
   * <em>expiration_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationStatusIsModified() {
    return expiration_status_is_modified;
  }


  /** 
   * <em>tax_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType()
  {
    return tax_type;
  }


  /** 
   * <em>tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxTypeIsSet() {
    return tax_type_is_set;
  }


  /** 
   * <em>tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTaxTypeIsModified() {
    return tax_type_is_modified;
  }


  /** 
   * <em>biz_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBizDate()
  {
    return biz_date;
  }


  /** 
   * <em>biz_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizDateIsSet() {
    return biz_date_is_set;
  }


  /** 
   * <em>biz_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizDateIsModified() {
    return biz_date_is_modified;
  }


  /** 
   * <em>product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
  }


  /** 
   * <em>quantity_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum getQuantityType()
  {
    return quantity_type;
  }


  /** 
   * <em>quantity_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityTypeIsSet() {
    return quantity_type_is_set;
  }


  /** 
   * <em>quantity_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityTypeIsModified() {
    return quantity_type_is_modified;
  }


  /** 
   * <em>order_chanel</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderChanel()
  {
    return order_chanel;
  }


  /** 
   * <em>order_chanel</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderChanelIsSet() {
    return order_chanel_is_set;
  }


  /** 
   * <em>order_chanel</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderChanelIsModified() {
    return order_chanel_is_modified;
  }


  /** 
   * <em>received_date_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getReceivedDateTime()
  {
    return received_date_time;
  }


  /** 
   * <em>received_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getReceivedDateTimeIsSet() {
    return received_date_time_is_set;
  }


  /** 
   * <em>received_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getReceivedDateTimeIsModified() {
    return received_date_time_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>order_request_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>calc_constant_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCalcConstantValue()
  {
    return ( calc_constant_value==null? ((double)0): calc_constant_value.doubleValue() );
  }


  /** 
   * <em>calc_constant_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCalcConstantValueIsNull()
  {
    return calc_constant_value == null;
  }


  /** 
   * <em>calc_constant_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcConstantValueIsSet() {
    return calc_constant_value_is_set;
  }


  /** 
   * <em>calc_constant_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcConstantValueIsModified() {
    return calc_constant_value_is_modified;
  }


  /** 
   * <em>swt_calc_constant_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSwtCalcConstantValue()
  {
    return ( swt_calc_constant_value==null? ((double)0): swt_calc_constant_value.doubleValue() );
  }


  /** 
   * <em>swt_calc_constant_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSwtCalcConstantValueIsNull()
  {
    return swt_calc_constant_value == null;
  }


  /** 
   * <em>swt_calc_constant_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtCalcConstantValueIsSet() {
    return swt_calc_constant_value_is_set;
  }


  /** 
   * <em>swt_calc_constant_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtCalcConstantValueIsModified() {
    return swt_calc_constant_value_is_modified;
  }


  /** 
   * <em>constant_value_app_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getConstantValueAppDate()
  {
    return constant_value_app_date;
  }


  /** 
   * <em>constant_value_app_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConstantValueAppDateIsSet() {
    return constant_value_app_date_is_set;
  }


  /** 
   * <em>constant_value_app_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConstantValueAppDateIsModified() {
    return constant_value_app_date_is_modified;
  }


  /** 
   * <em>estimated_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getEstimatedPrice()
  {
    return ( estimated_price==null? ((double)0): estimated_price.doubleValue() );
  }


  /** 
   * <em>estimated_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getEstimatedPriceIsNull()
  {
    return estimated_price == null;
  }


  /** 
   * <em>estimated_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimatedPriceIsSet() {
    return estimated_price_is_set;
  }


  /** 
   * <em>estimated_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimatedPriceIsModified() {
    return estimated_price_is_modified;
  }


  /** 
   * <em>estimate_dealing_qty</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getEstimateDealingQty()
  {
    return ( estimate_dealing_qty==null? ((double)0): estimate_dealing_qty.doubleValue() );
  }


  /** 
   * <em>estimate_dealing_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getEstimateDealingQtyIsNull()
  {
    return estimate_dealing_qty == null;
  }


  /** 
   * <em>estimate_dealing_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimateDealingQtyIsSet() {
    return estimate_dealing_qty_is_set;
  }


  /** 
   * <em>estimate_dealing_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimateDealingQtyIsModified() {
    return estimate_dealing_qty_is_modified;
  }


  /** 
   * <em>swt_estimate_dealing_qty</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSwtEstimateDealingQty()
  {
    return ( swt_estimate_dealing_qty==null? ((double)0): swt_estimate_dealing_qty.doubleValue() );
  }


  /** 
   * <em>swt_estimate_dealing_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSwtEstimateDealingQtyIsNull()
  {
    return swt_estimate_dealing_qty == null;
  }


  /** 
   * <em>swt_estimate_dealing_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtEstimateDealingQtyIsSet() {
    return swt_estimate_dealing_qty_is_set;
  }


  /** 
   * <em>swt_estimate_dealing_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtEstimateDealingQtyIsModified() {
    return swt_estimate_dealing_qty_is_modified;
  }


  /** 
   * <em>swt_tax_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getSwtTaxType()
  {
    return swt_tax_type;
  }


  /** 
   * <em>swt_tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtTaxTypeIsSet() {
    return swt_tax_type_is_set;
  }


  /** 
   * <em>swt_tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtTaxTypeIsModified() {
    return swt_tax_type_is_modified;
  }


  /** 
   * <em>swt_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSwtProductCode()
  {
    return swt_product_code;
  }


  /** 
   * <em>swt_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtProductCodeIsSet() {
    return swt_product_code_is_set;
  }


  /** 
   * <em>swt_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtProductCodeIsModified() {
    return swt_product_code_is_modified;
  }


  /** 
   * <em>payment_method</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPaymentMethod()
  {
    return payment_method;
  }


  /** 
   * <em>payment_method</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentMethodIsSet() {
    return payment_method_is_set;
  }


  /** 
   * <em>payment_method</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentMethodIsModified() {
    return payment_method_is_modified;
  }


  /** 
   * <em>fund_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum getFundType()
  {
    return fund_type;
  }


  /** 
   * <em>fund_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundTypeIsSet() {
    return fund_type_is_set;
  }


  /** 
   * <em>fund_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundTypeIsModified() {
    return fund_type_is_modified;
  }


  /** 
   * <em>fund_sell_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFundSellDiv()
  {
    return fund_sell_div;
  }


  /** 
   * <em>fund_sell_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundSellDivIsSet() {
    return fund_sell_div_is_set;
  }


  /** 
   * <em>fund_sell_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundSellDivIsModified() {
    return fund_sell_div_is_modified;
  }


  /** 
   * <em>exec_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getExecDate()
  {
    return exec_date;
  }


  /** 
   * <em>exec_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecDateIsSet() {
    return exec_date_is_set;
  }


  /** 
   * <em>exec_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecDateIsModified() {
    return exec_date_is_modified;
  }


  /** 
   * <em>exec_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getExecStatus()
  {
    return exec_status;
  }


  /** 
   * <em>exec_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecStatusIsSet() {
    return exec_status_is_set;
  }


  /** 
   * <em>exec_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecStatusIsModified() {
    return exec_status_is_modified;
  }


  /** 
   * <em>settlement_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSettlementDiv()
  {
    return settlement_div;
  }


  /** 
   * <em>settlement_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettlementDivIsSet() {
    return settlement_div_is_set;
  }


  /** 
   * <em>settlement_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettlementDivIsModified() {
    return settlement_div_is_modified;
  }


  /** 
   * <em>no_contract_commission_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNoContractCommissionDiv()
  {
    return no_contract_commission_div;
  }


  /** 
   * <em>no_contract_commission_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNoContractCommissionDivIsSet() {
    return no_contract_commission_div_is_set;
  }


  /** 
   * <em>no_contract_commission_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNoContractCommissionDivIsModified() {
    return no_contract_commission_div_is_modified;
  }


  /** 
   * <em>request_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRequestDiv()
  {
    return request_div;
  }


  /** 
   * <em>request_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestDivIsSet() {
    return request_div_is_set;
  }


  /** 
   * <em>request_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestDivIsModified() {
    return request_div_is_modified;
  }


  /** 
   * <em>order_root_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderRootDiv()
  {
    return order_root_div;
  }


  /** 
   * <em>order_root_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRootDivIsSet() {
    return order_root_div_is_set;
  }


  /** 
   * <em>order_root_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRootDivIsModified() {
    return order_root_div_is_modified;
  }


  /** 
   * <em>error_reason_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getErrorReasonCode()
  {
    return error_reason_code;
  }


  /** 
   * <em>error_reason_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorReasonCodeIsSet() {
    return error_reason_code_is_set;
  }


  /** 
   * <em>error_reason_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorReasonCodeIsModified() {
    return error_reason_code_is_modified;
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
   * <em>payment_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getPaymentDate()
  {
    return payment_date;
  }


  /** 
   * <em>payment_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDateIsSet() {
    return payment_date_is_set;
  }


  /** 
   * <em>payment_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDateIsModified() {
    return payment_date_is_modified;
  }


  /** 
   * <em>withholding_tax_restriction</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getWithholdingTaxRestriction()
  {
    return ( withholding_tax_restriction==null? ((double)0): withholding_tax_restriction.doubleValue() );
  }


  /** 
   * <em>withholding_tax_restriction</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getWithholdingTaxRestrictionIsNull()
  {
    return withholding_tax_restriction == null;
  }


  /** 
   * <em>withholding_tax_restriction</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWithholdingTaxRestrictionIsSet() {
    return withholding_tax_restriction_is_set;
  }


  /** 
   * <em>withholding_tax_restriction</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getWithholdingTaxRestrictionIsModified() {
    return withholding_tax_restriction_is_modified;
  }


  /** 
   * <em>payment_order_req_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPaymentOrderReqNumber()
  {
    return payment_order_req_number;
  }


  /** 
   * <em>payment_order_req_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentOrderReqNumberIsSet() {
    return payment_order_req_number_is_set;
  }


  /** 
   * <em>payment_order_req_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentOrderReqNumberIsModified() {
    return payment_order_req_number_is_modified;
  }


  /** 
   * <em>cpu_no</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCpuNo()
  {
    return cpu_no;
  }


  /** 
   * <em>cpu_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCpuNoIsSet() {
    return cpu_no_is_set;
  }


  /** 
   * <em>cpu_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCpuNoIsModified() {
    return cpu_no_is_modified;
  }


  /** 
   * <em>swt_exec_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSwtExecDate()
  {
    return swt_exec_date;
  }


  /** 
   * <em>swt_exec_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtExecDateIsSet() {
    return swt_exec_date_is_set;
  }


  /** 
   * <em>swt_exec_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtExecDateIsModified() {
    return swt_exec_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MutualFundOrderUnitPK(order_unit_id);
  }


  /** 
   * <em>order_unit_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = p_orderUnitId;
    order_unit_id_is_set = true;
    order_unit_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subAccountId <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * <em>branch_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>trader_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_traderId <em>trader_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTraderId( long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = new Long(p_traderId);
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>trader_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTraderId( Long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = p_traderId;
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * <em>order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderId <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>order_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderType <em>order_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�l 
   */
  public final void setOrderType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
    order_type_is_modified = true;
  }


  /** 
   * <em>order_categ</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderCateg <em>order_categ</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum�l 
   */
  public final void setOrderCateg( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum p_orderCateg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_categ = p_orderCateg;
    order_categ_is_set = true;
    order_categ_is_modified = true;
  }


  /** 
   * <em>last_order_action_serial_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastOrderActionSerialNo <em>last_order_action_serial_no</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setLastOrderActionSerialNo( int p_lastOrderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_order_action_serial_no = p_lastOrderActionSerialNo;
    last_order_action_serial_no_is_set = true;
    last_order_action_serial_no_is_modified = true;
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
   * <em>market_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = new Long(p_marketId);
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>market_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMarketId( Long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deliveryDate <em>delivery_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>delivery_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>expiration_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_expirationDate <em>expiration_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setExpirationDate( java.sql.Timestamp p_expirationDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_date = p_expirationDate;
    expiration_date_is_set = true;
    expiration_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>expiration_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setExpirationDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    expiration_date_is_set = true;
    expiration_date_is_modified = true;
  }


  /** 
   * <em>confirmed_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_confirmedQuantity <em>confirmed_quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setConfirmedQuantity( double p_confirmedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_quantity = new Double(p_confirmedQuantity);
    confirmed_quantity_is_set = true;
    confirmed_quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>confirmed_quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setConfirmedQuantity( Double p_confirmedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_quantity = p_confirmedQuantity;
    confirmed_quantity_is_set = true;
    confirmed_quantity_is_modified = true;
  }


  /** 
   * <em>executed_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_executedQuantity <em>executed_quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setExecutedQuantity( double p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = new Double(p_executedQuantity);
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>executed_quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setExecutedQuantity( Double p_executedQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_quantity = p_executedQuantity;
    executed_quantity_is_set = true;
    executed_quantity_is_modified = true;
  }


  /** 
   * <em>executed_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_executedAmount <em>executed_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setExecutedAmount( double p_executedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount = new Double(p_executedAmount);
    executed_amount_is_set = true;
    executed_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>executed_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setExecutedAmount( Double p_executedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount = p_executedAmount;
    executed_amount_is_set = true;
    executed_amount_is_modified = true;
  }


  /** 
   * <em>order_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderStatus <em>order_status</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum�l 
   */
  public final void setOrderStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum p_orderStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_status = p_orderStatus;
    order_status_is_set = true;
    order_status_is_modified = true;
  }


  /** 
   * <em>order_open_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderOpenStatus <em>order_open_status</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum�l 
   */
  public final void setOrderOpenStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_open_status = p_orderOpenStatus;
    order_open_status_is_set = true;
    order_open_status_is_modified = true;
  }


  /** 
   * <em>expiration_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_expirationStatus <em>expiration_status</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum�l 
   */
  public final void setExpirationStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum p_expirationStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_status = p_expirationStatus;
    expiration_status_is_set = true;
    expiration_status_is_modified = true;
  }


  /** 
   * <em>tax_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_taxType <em>tax_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�l 
   */
  public final void setTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>biz_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizDate <em>biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setBizDate( String p_bizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date = p_bizDate;
    biz_date_is_set = true;
    biz_date_is_modified = true;
  }


  /** 
   * <em>product_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productId <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>quantity_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantityType <em>quantity_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum�l 
   */
  public final void setQuantityType( com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum p_quantityType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity_type = p_quantityType;
    quantity_type_is_set = true;
    quantity_type_is_modified = true;
  }


  /** 
   * <em>order_chanel</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderChanel <em>order_chanel</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderChanel( String p_orderChanel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_chanel = p_orderChanel;
    order_chanel_is_set = true;
    order_chanel_is_modified = true;
  }


  /** 
   * <em>received_date_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_receivedDateTime <em>received_date_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setReceivedDateTime( java.sql.Timestamp p_receivedDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time = p_receivedDateTime;
    received_date_time_is_set = true;
    received_date_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>received_date_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setReceivedDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    received_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    received_date_time_is_set = true;
    received_date_time_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>order_request_number</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>calc_constant_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcConstantValue <em>calc_constant_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCalcConstantValue( double p_calcConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_constant_value = new Double(p_calcConstantValue);
    calc_constant_value_is_set = true;
    calc_constant_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>calc_constant_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCalcConstantValue( Double p_calcConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_constant_value = p_calcConstantValue;
    calc_constant_value_is_set = true;
    calc_constant_value_is_modified = true;
  }


  /** 
   * <em>swt_calc_constant_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_swtCalcConstantValue <em>swt_calc_constant_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSwtCalcConstantValue( double p_swtCalcConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_calc_constant_value = new Double(p_swtCalcConstantValue);
    swt_calc_constant_value_is_set = true;
    swt_calc_constant_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>swt_calc_constant_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSwtCalcConstantValue( Double p_swtCalcConstantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_calc_constant_value = p_swtCalcConstantValue;
    swt_calc_constant_value_is_set = true;
    swt_calc_constant_value_is_modified = true;
  }


  /** 
   * <em>constant_value_app_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_constantValueAppDate <em>constant_value_app_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setConstantValueAppDate( java.sql.Timestamp p_constantValueAppDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value_app_date = p_constantValueAppDate;
    constant_value_app_date_is_set = true;
    constant_value_app_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>constant_value_app_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setConstantValueAppDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value_app_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    constant_value_app_date_is_set = true;
    constant_value_app_date_is_modified = true;
  }


  /** 
   * <em>estimated_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_estimatedPrice <em>estimated_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setEstimatedPrice( double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = new Double(p_estimatedPrice);
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>estimated_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setEstimatedPrice( Double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = p_estimatedPrice;
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
  }


  /** 
   * <em>estimate_dealing_qty</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_estimateDealingQty <em>estimate_dealing_qty</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setEstimateDealingQty( double p_estimateDealingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimate_dealing_qty = new Double(p_estimateDealingQty);
    estimate_dealing_qty_is_set = true;
    estimate_dealing_qty_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>estimate_dealing_qty</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setEstimateDealingQty( Double p_estimateDealingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    estimate_dealing_qty = p_estimateDealingQty;
    estimate_dealing_qty_is_set = true;
    estimate_dealing_qty_is_modified = true;
  }


  /** 
   * <em>swt_estimate_dealing_qty</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_swtEstimateDealingQty <em>swt_estimate_dealing_qty</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSwtEstimateDealingQty( double p_swtEstimateDealingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_estimate_dealing_qty = new Double(p_swtEstimateDealingQty);
    swt_estimate_dealing_qty_is_set = true;
    swt_estimate_dealing_qty_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>swt_estimate_dealing_qty</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSwtEstimateDealingQty( Double p_swtEstimateDealingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_estimate_dealing_qty = p_swtEstimateDealingQty;
    swt_estimate_dealing_qty_is_set = true;
    swt_estimate_dealing_qty_is_modified = true;
  }


  /** 
   * <em>swt_tax_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_swtTaxType <em>swt_tax_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�l 
   */
  public final void setSwtTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_swtTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_tax_type = p_swtTaxType;
    swt_tax_type_is_set = true;
    swt_tax_type_is_modified = true;
  }


  /** 
   * <em>swt_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_swtProductCode <em>swt_product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setSwtProductCode( String p_swtProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_product_code = p_swtProductCode;
    swt_product_code_is_set = true;
    swt_product_code_is_modified = true;
  }


  /** 
   * <em>payment_method</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentMethod <em>payment_method</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setPaymentMethod( String p_paymentMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_method = p_paymentMethod;
    payment_method_is_set = true;
    payment_method_is_modified = true;
  }


  /** 
   * <em>fund_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fundType <em>fund_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum�l 
   */
  public final void setFundType( com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum p_fundType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_type = p_fundType;
    fund_type_is_set = true;
    fund_type_is_modified = true;
  }


  /** 
   * <em>fund_sell_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fundSellDiv <em>fund_sell_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFundSellDiv( String p_fundSellDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_sell_div = p_fundSellDiv;
    fund_sell_div_is_set = true;
    fund_sell_div_is_modified = true;
  }


  /** 
   * <em>exec_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execDate <em>exec_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setExecDate( java.sql.Timestamp p_execDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date = p_execDate;
    exec_date_is_set = true;
    exec_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>exec_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setExecDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_date_is_set = true;
    exec_date_is_modified = true;
  }


  /** 
   * <em>exec_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execStatus <em>exec_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setExecStatus( String p_execStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_status = p_execStatus;
    exec_status_is_set = true;
    exec_status_is_modified = true;
  }


  /** 
   * <em>settlement_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_settlementDiv <em>settlement_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSettlementDiv( String p_settlementDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settlement_div = p_settlementDiv;
    settlement_div_is_set = true;
    settlement_div_is_modified = true;
  }


  /** 
   * <em>no_contract_commission_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_noContractCommissionDiv <em>no_contract_commission_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setNoContractCommissionDiv( String p_noContractCommissionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    no_contract_commission_div = p_noContractCommissionDiv;
    no_contract_commission_div_is_set = true;
    no_contract_commission_div_is_modified = true;
  }


  /** 
   * <em>request_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_requestDiv <em>request_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRequestDiv( String p_requestDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_div = p_requestDiv;
    request_div_is_set = true;
    request_div_is_modified = true;
  }


  /** 
   * <em>order_root_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderRootDiv <em>order_root_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderRootDiv( String p_orderRootDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_root_div = p_orderRootDiv;
    order_root_div_is_set = true;
    order_root_div_is_modified = true;
  }


  /** 
   * <em>error_reason_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_errorReasonCode <em>error_reason_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setErrorReasonCode( String p_errorReasonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_reason_code = p_errorReasonCode;
    error_reason_code_is_set = true;
    error_reason_code_is_modified = true;
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
   * <em>payment_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentDate <em>payment_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setPaymentDate( java.sql.Timestamp p_paymentDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = p_paymentDate;
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>payment_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setPaymentDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


  /** 
   * <em>withholding_tax_restriction</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_withholdingTaxRestriction <em>withholding_tax_restriction</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setWithholdingTaxRestriction( double p_withholdingTaxRestriction )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    withholding_tax_restriction = new Double(p_withholdingTaxRestriction);
    withholding_tax_restriction_is_set = true;
    withholding_tax_restriction_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>withholding_tax_restriction</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setWithholdingTaxRestriction( Double p_withholdingTaxRestriction )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    withholding_tax_restriction = p_withholdingTaxRestriction;
    withholding_tax_restriction_is_set = true;
    withholding_tax_restriction_is_modified = true;
  }


  /** 
   * <em>payment_order_req_number</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentOrderReqNumber <em>payment_order_req_number</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setPaymentOrderReqNumber( String p_paymentOrderReqNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_order_req_number = p_paymentOrderReqNumber;
    payment_order_req_number_is_set = true;
    payment_order_req_number_is_modified = true;
  }


  /** 
   * <em>cpu_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cpuNo <em>cpu_no</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setCpuNo( String p_cpuNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cpu_no = p_cpuNo;
    cpu_no_is_set = true;
    cpu_no_is_modified = true;
  }


  /** 
   * <em>swt_exec_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_swtExecDate <em>swt_exec_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSwtExecDate( java.sql.Timestamp p_swtExecDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_exec_date = p_swtExecDate;
    swt_exec_date_is_set = true;
    swt_exec_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>swt_exec_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSwtExecDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    swt_exec_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    swt_exec_date_is_set = true;
    swt_exec_date_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                break;
            case 'c':
                if ( name.equals("confirmed_quantity") ) {
                    return this.confirmed_quantity;
                }
                else if ( name.equals("calc_constant_value") ) {
                    return this.calc_constant_value;
                }
                else if ( name.equals("constant_value_app_date") ) {
                    return this.constant_value_app_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("cpu_no") ) {
                    return this.cpu_no;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("expiration_date") ) {
                    return this.expiration_date;
                }
                else if ( name.equals("executed_quantity") ) {
                    return this.executed_quantity;
                }
                else if ( name.equals("executed_amount") ) {
                    return this.executed_amount;
                }
                else if ( name.equals("expiration_status") ) {
                    return this.expiration_status;
                }
                else if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                else if ( name.equals("estimate_dealing_qty") ) {
                    return this.estimate_dealing_qty;
                }
                else if ( name.equals("exec_date") ) {
                    return this.exec_date;
                }
                else if ( name.equals("exec_status") ) {
                    return this.exec_status;
                }
                else if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    return this.fund_type;
                }
                else if ( name.equals("fund_sell_div") ) {
                    return this.fund_sell_div;
                }
                break;
            case 'l':
                if ( name.equals("last_order_action_serial_no") ) {
                    return new Integer( last_order_action_serial_no );
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return this.market_id;
                }
                break;
            case 'n':
                if ( name.equals("no_contract_commission_div") ) {
                    return this.no_contract_commission_div;
                }
                break;
            case 'o':
                if ( name.equals("order_unit_id") ) {
                    return new Long( order_unit_id );
                }
                else if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                else if ( name.equals("order_categ") ) {
                    return this.order_categ;
                }
                else if ( name.equals("order_status") ) {
                    return this.order_status;
                }
                else if ( name.equals("order_open_status") ) {
                    return this.order_open_status;
                }
                else if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("payment_method") ) {
                    return this.payment_method;
                }
                else if ( name.equals("payment_date") ) {
                    return this.payment_date;
                }
                else if ( name.equals("payment_order_req_number") ) {
                    return this.payment_order_req_number;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                else if ( name.equals("quantity_type") ) {
                    return this.quantity_type;
                }
                break;
            case 'r':
                if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
                }
                else if ( name.equals("request_div") ) {
                    return this.request_div;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("swt_calc_constant_value") ) {
                    return this.swt_calc_constant_value;
                }
                else if ( name.equals("swt_estimate_dealing_qty") ) {
                    return this.swt_estimate_dealing_qty;
                }
                else if ( name.equals("swt_tax_type") ) {
                    return this.swt_tax_type;
                }
                else if ( name.equals("swt_product_code") ) {
                    return this.swt_product_code;
                }
                else if ( name.equals("settlement_div") ) {
                    return this.settlement_div;
                }
                else if ( name.equals("swt_exec_date") ) {
                    return this.swt_exec_date;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                break;
            case 'w':
                if ( name.equals("withholding_tax_restriction") ) {
                    return this.withholding_tax_restriction;
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    if (this.biz_date_is_set)
                        this.biz_date_is_modified = true;
                    this.biz_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("confirmed_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'confirmed_quantity' must be Double: '"+value+"' is not." );
                    this.confirmed_quantity = (Double) value;
                    if (this.confirmed_quantity_is_set)
                        this.confirmed_quantity_is_modified = true;
                    this.confirmed_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("calc_constant_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'calc_constant_value' must be Double: '"+value+"' is not." );
                    this.calc_constant_value = (Double) value;
                    if (this.calc_constant_value_is_set)
                        this.calc_constant_value_is_modified = true;
                    this.calc_constant_value_is_set = true;
                    return;
                }
                else if ( name.equals("constant_value_app_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'constant_value_app_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.constant_value_app_date = (java.sql.Timestamp) value;
                    if (this.constant_value_app_date_is_set)
                        this.constant_value_app_date_is_modified = true;
                    this.constant_value_app_date_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("cpu_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cpu_no' must be String: '"+value+"' is not." );
                    this.cpu_no = (String) value;
                    if (this.cpu_no_is_set)
                        this.cpu_no_is_modified = true;
                    this.cpu_no_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("expiration_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'expiration_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.expiration_date = (java.sql.Timestamp) value;
                    if (this.expiration_date_is_set)
                        this.expiration_date_is_modified = true;
                    this.expiration_date_is_set = true;
                    return;
                }
                else if ( name.equals("executed_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_quantity' must be Double: '"+value+"' is not." );
                    this.executed_quantity = (Double) value;
                    if (this.executed_quantity_is_set)
                        this.executed_quantity_is_modified = true;
                    this.executed_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("executed_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_amount' must be Double: '"+value+"' is not." );
                    this.executed_amount = (Double) value;
                    if (this.executed_amount_is_set)
                        this.executed_amount_is_modified = true;
                    this.executed_amount_is_set = true;
                    return;
                }
                else if ( name.equals("expiration_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) )
                        throw new IllegalArgumentException( "value for 'expiration_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum: '"+value+"' is not." );
                    this.expiration_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) value;
                    if (this.expiration_status_is_set)
                        this.expiration_status_is_modified = true;
                    this.expiration_status_is_set = true;
                    return;
                }
                else if ( name.equals("estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'estimated_price' must be Double: '"+value+"' is not." );
                    this.estimated_price = (Double) value;
                    if (this.estimated_price_is_set)
                        this.estimated_price_is_modified = true;
                    this.estimated_price_is_set = true;
                    return;
                }
                else if ( name.equals("estimate_dealing_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'estimate_dealing_qty' must be Double: '"+value+"' is not." );
                    this.estimate_dealing_qty = (Double) value;
                    if (this.estimate_dealing_qty_is_set)
                        this.estimate_dealing_qty_is_modified = true;
                    this.estimate_dealing_qty_is_set = true;
                    return;
                }
                else if ( name.equals("exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_date = (java.sql.Timestamp) value;
                    if (this.exec_date_is_set)
                        this.exec_date_is_modified = true;
                    this.exec_date_is_set = true;
                    return;
                }
                else if ( name.equals("exec_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_status' must be String: '"+value+"' is not." );
                    this.exec_status = (String) value;
                    if (this.exec_status_is_set)
                        this.exec_status_is_modified = true;
                    this.exec_status_is_set = true;
                    return;
                }
                else if ( name.equals("error_reason_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_reason_code' must be String: '"+value+"' is not." );
                    this.error_reason_code = (String) value;
                    if (this.error_reason_code_is_set)
                        this.error_reason_code_is_modified = true;
                    this.error_reason_code_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum) )
                        throw new IllegalArgumentException( "value for 'fund_type' must be com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum: '"+value+"' is not." );
                    this.fund_type = (com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum) value;
                    if (this.fund_type_is_set)
                        this.fund_type_is_modified = true;
                    this.fund_type_is_set = true;
                    return;
                }
                else if ( name.equals("fund_sell_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_sell_div' must be String: '"+value+"' is not." );
                    this.fund_sell_div = (String) value;
                    if (this.fund_sell_div_is_set)
                        this.fund_sell_div_is_modified = true;
                    this.fund_sell_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'last_order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.last_order_action_serial_no = ((Integer) value).intValue();
                    if (this.last_order_action_serial_no_is_set)
                        this.last_order_action_serial_no_is_modified = true;
                    this.last_order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = (Long) value;
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("no_contract_commission_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'no_contract_commission_div' must be String: '"+value+"' is not." );
                    this.no_contract_commission_div = (String) value;
                    if (this.no_contract_commission_div_is_set)
                        this.no_contract_commission_div_is_modified = true;
                    this.no_contract_commission_div_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_unit_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = ((Long) value).longValue();
                    if (this.order_unit_id_is_set)
                        this.order_unit_id_is_modified = true;
                    this.order_unit_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_categ") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum) )
                        throw new IllegalArgumentException( "value for 'order_categ' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum: '"+value+"' is not." );
                    this.order_categ = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum) value;
                    if (this.order_categ_is_set)
                        this.order_categ_is_modified = true;
                    this.order_categ_is_set = true;
                    return;
                }
                else if ( name.equals("order_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum: '"+value+"' is not." );
                    this.order_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) value;
                    if (this.order_status_is_set)
                        this.order_status_is_modified = true;
                    this.order_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_open_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_open_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum: '"+value+"' is not." );
                    this.order_open_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) value;
                    if (this.order_open_status_is_set)
                        this.order_open_status_is_modified = true;
                    this.order_open_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_chanel") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_chanel' must be String: '"+value+"' is not." );
                    this.order_chanel = (String) value;
                    if (this.order_chanel_is_set)
                        this.order_chanel_is_modified = true;
                    this.order_chanel_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("order_root_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_root_div' must be String: '"+value+"' is not." );
                    this.order_root_div = (String) value;
                    if (this.order_root_div_is_set)
                        this.order_root_div_is_modified = true;
                    this.order_root_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("payment_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_method' must be String: '"+value+"' is not." );
                    this.payment_method = (String) value;
                    if (this.payment_method_is_set)
                        this.payment_method_is_modified = true;
                    this.payment_method_is_set = true;
                    return;
                }
                else if ( name.equals("payment_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'payment_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.payment_date = (java.sql.Timestamp) value;
                    if (this.payment_date_is_set)
                        this.payment_date_is_modified = true;
                    this.payment_date_is_set = true;
                    return;
                }
                else if ( name.equals("payment_order_req_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_order_req_number' must be String: '"+value+"' is not." );
                    this.payment_order_req_number = (String) value;
                    if (this.payment_order_req_number_is_set)
                        this.payment_order_req_number_is_modified = true;
                    this.payment_order_req_number_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = ((Double) value).doubleValue();
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                else if ( name.equals("quantity_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum) )
                        throw new IllegalArgumentException( "value for 'quantity_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum: '"+value+"' is not." );
                    this.quantity_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum) value;
                    if (this.quantity_type_is_set)
                        this.quantity_type_is_modified = true;
                    this.quantity_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("received_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'received_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.received_date_time = (java.sql.Timestamp) value;
                    if (this.received_date_time_is_set)
                        this.received_date_time_is_modified = true;
                    this.received_date_time_is_set = true;
                    return;
                }
                else if ( name.equals("request_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_div' must be String: '"+value+"' is not." );
                    this.request_div = (String) value;
                    if (this.request_div_is_set)
                        this.request_div_is_modified = true;
                    this.request_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("swt_calc_constant_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'swt_calc_constant_value' must be Double: '"+value+"' is not." );
                    this.swt_calc_constant_value = (Double) value;
                    if (this.swt_calc_constant_value_is_set)
                        this.swt_calc_constant_value_is_modified = true;
                    this.swt_calc_constant_value_is_set = true;
                    return;
                }
                else if ( name.equals("swt_estimate_dealing_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'swt_estimate_dealing_qty' must be Double: '"+value+"' is not." );
                    this.swt_estimate_dealing_qty = (Double) value;
                    if (this.swt_estimate_dealing_qty_is_set)
                        this.swt_estimate_dealing_qty_is_modified = true;
                    this.swt_estimate_dealing_qty_is_set = true;
                    return;
                }
                else if ( name.equals("swt_tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'swt_tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.swt_tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.swt_tax_type_is_set)
                        this.swt_tax_type_is_modified = true;
                    this.swt_tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("swt_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_product_code' must be String: '"+value+"' is not." );
                    this.swt_product_code = (String) value;
                    if (this.swt_product_code_is_set)
                        this.swt_product_code_is_modified = true;
                    this.swt_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("settlement_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'settlement_div' must be String: '"+value+"' is not." );
                    this.settlement_div = (String) value;
                    if (this.settlement_div_is_set)
                        this.settlement_div_is_modified = true;
                    this.settlement_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'swt_exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.swt_exec_date = (java.sql.Timestamp) value;
                    if (this.swt_exec_date_is_set)
                        this.swt_exec_date_is_modified = true;
                    this.swt_exec_date_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trader_id' must be Long: '"+value+"' is not." );
                    this.trader_id = (Long) value;
                    if (this.trader_id_is_set)
                        this.trader_id_is_modified = true;
                    this.trader_id_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("withholding_tax_restriction") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'withholding_tax_restriction' must be Double: '"+value+"' is not." );
                    this.withholding_tax_restriction = (Double) value;
                    if (this.withholding_tax_restriction_is_set)
                        this.withholding_tax_restriction_is_modified = true;
                    this.withholding_tax_restriction_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
