head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ForcedSettleOrderInqParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * forced_settle_order_inq�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link ForcedSettleOrderInqRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link ForcedSettleOrderInqParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link ForcedSettleOrderInqParams}��{@@link ForcedSettleOrderInqRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ForcedSettleOrderInqPK 
 * @@see ForcedSettleOrderInqRow 
 */
public class ForcedSettleOrderInqParams extends Params implements ForcedSettleOrderInqRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "forced_settle_order_inq";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = ForcedSettleOrderInqRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return ForcedSettleOrderInqRow.TYPE;
  }


  /** 
   * <em>forced_settle_order_inq_id</em>�J�����̒l 
   */
  public  long  forced_settle_order_inq_id;

  /** 
   * <em>order_id</em>�J�����̒l 
   */
  public  Long  order_id;

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
   * <em>order_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>order_categ</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum  order_categ;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  Long  market_id;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  double  quantity;

  /** 
   * <em>limit_price</em>�J�����̒l 
   */
  public  Double  limit_price;

  /** 
   * <em>delivery_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>biz_date</em>�J�����̒l 
   */
  public  String  biz_date;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>received_date_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  received_date_time;

  /** 
   * <em>error_reason_code</em>�J�����̒l 
   */
  public  String  error_reason_code;

  /** 
   * <em>forced_settle_reason_type</em>�J�����̒l 
   */
  public  String  forced_settle_reason_type;

  /** 
   * <em>approve_status_type</em>�J�����̒l 
   */
  public  String  approve_status_type;

  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l 
   */
  public  Double  margin_maintenance_rate;

  /** 
   * <em>additional_margin_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  additional_margin_date;

  /** 
   * <em>additional_margin_accrued_days</em>�J�����̒l 
   */
  public  Long  additional_margin_accrued_days;

  /** 
   * <em>contract_id</em>�J�����̒l 
   */
  public  long  contract_id;

  /** 
   * <em>org_contract_quantity</em>�J�����̒l 
   */
  public  double  org_contract_quantity;

  /** 
   * <em>contract_quantity</em>�J�����̒l 
   */
  public  double  contract_quantity;

  /** 
   * <em>original_contract_price</em>�J�����̒l 
   */
  public  double  original_contract_price;

  /** 
   * <em>contract_price</em>�J�����̒l 
   */
  public  double  contract_price;

  /** 
   * <em>contract_type</em>�J�����̒l 
   */
  public  int  contract_type;

  /** 
   * <em>open_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  open_date;

  /** 
   * <em>close_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  close_date;

  /** 
   * <em>tax_type</em>�J�����̒l 
   */
  public  Integer  tax_type;

  /** 
   * <em>repayment_type</em>�J�����̒l 
   */
  public  String  repayment_type;

  /** 
   * <em>repayment_num</em>�J�����̒l 
   */
  public  Integer  repayment_num;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>approver_code</em>�J�����̒l 
   */
  public  String  approver_code;

  /** 
   * <em>approve_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  approve_timestamp;

  /** 
   * <em>account_code</em>�J�����̒l 
   */
  public  String  account_code;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  private boolean forced_settle_order_inq_id_is_set = false;
  private boolean forced_settle_order_inq_id_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean order_categ_is_set = false;
  private boolean order_categ_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean received_date_time_is_set = false;
  private boolean received_date_time_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean forced_settle_reason_type_is_set = false;
  private boolean forced_settle_reason_type_is_modified = false;
  private boolean approve_status_type_is_set = false;
  private boolean approve_status_type_is_modified = false;
  private boolean margin_maintenance_rate_is_set = false;
  private boolean margin_maintenance_rate_is_modified = false;
  private boolean additional_margin_date_is_set = false;
  private boolean additional_margin_date_is_modified = false;
  private boolean additional_margin_accrued_days_is_set = false;
  private boolean additional_margin_accrued_days_is_modified = false;
  private boolean contract_id_is_set = false;
  private boolean contract_id_is_modified = false;
  private boolean org_contract_quantity_is_set = false;
  private boolean org_contract_quantity_is_modified = false;
  private boolean contract_quantity_is_set = false;
  private boolean contract_quantity_is_modified = false;
  private boolean original_contract_price_is_set = false;
  private boolean original_contract_price_is_modified = false;
  private boolean contract_price_is_set = false;
  private boolean contract_price_is_modified = false;
  private boolean contract_type_is_set = false;
  private boolean contract_type_is_modified = false;
  private boolean open_date_is_set = false;
  private boolean open_date_is_modified = false;
  private boolean close_date_is_set = false;
  private boolean close_date_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean repayment_type_is_set = false;
  private boolean repayment_type_is_modified = false;
  private boolean repayment_num_is_set = false;
  private boolean repayment_num_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean approver_code_is_set = false;
  private boolean approver_code_is_modified = false;
  private boolean approve_timestamp_is_set = false;
  private boolean approve_timestamp_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "forced_settle_order_inq_id=" + forced_settle_order_inq_id
      + "," + "order_id=" +order_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "branch_id=" +branch_id
      + "," + "order_type=" +order_type
      + "," + "order_categ=" +order_categ
      + "," + "market_id=" +market_id
      + "," + "quantity=" +quantity
      + "," + "limit_price=" +limit_price
      + "," + "delivery_date=" +delivery_date
      + "," + "biz_date=" +biz_date
      + "," + "product_id=" +product_id
      + "," + "received_date_time=" +received_date_time
      + "," + "error_reason_code=" +error_reason_code
      + "," + "forced_settle_reason_type=" +forced_settle_reason_type
      + "," + "approve_status_type=" +approve_status_type
      + "," + "margin_maintenance_rate=" +margin_maintenance_rate
      + "," + "additional_margin_date=" +additional_margin_date
      + "," + "additional_margin_accrued_days=" +additional_margin_accrued_days
      + "," + "contract_id=" +contract_id
      + "," + "org_contract_quantity=" +org_contract_quantity
      + "," + "contract_quantity=" +contract_quantity
      + "," + "original_contract_price=" +original_contract_price
      + "," + "contract_price=" +contract_price
      + "," + "contract_type=" +contract_type
      + "," + "open_date=" +open_date
      + "," + "close_date=" +close_date
      + "," + "tax_type=" +tax_type
      + "," + "repayment_type=" +repayment_type
      + "," + "repayment_num=" +repayment_num
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "approver_code=" +approver_code
      + "," + "approve_timestamp=" +approve_timestamp
      + "," + "account_code=" +account_code
      + "," + "product_code=" +product_code
      + "}";
  }


  /** 
   * �l�����ݒ��ForcedSettleOrderInqParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public ForcedSettleOrderInqParams() {
    forced_settle_order_inq_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���ForcedSettleOrderInqRow�I�u�W�F�N�g�̒l�𗘗p����ForcedSettleOrderInqParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����ForcedSettleOrderInqRow�I�u�W�F�N�g 
   */
  public ForcedSettleOrderInqParams( ForcedSettleOrderInqRow p_row )
  {
    forced_settle_order_inq_id = p_row.getForcedSettleOrderInqId();
    forced_settle_order_inq_id_is_set = p_row.getForcedSettleOrderInqIdIsSet();
    forced_settle_order_inq_id_is_modified = p_row.getForcedSettleOrderInqIdIsModified();
    if ( !p_row.getOrderIdIsNull() )
      order_id = new Long( p_row.getOrderId() );
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    order_categ = p_row.getOrderCateg();
    order_categ_is_set = p_row.getOrderCategIsSet();
    order_categ_is_modified = p_row.getOrderCategIsModified();
    if ( !p_row.getMarketIdIsNull() )
      market_id = new Long( p_row.getMarketId() );
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    limit_price_is_set = p_row.getLimitPriceIsSet();
    limit_price_is_modified = p_row.getLimitPriceIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    received_date_time = p_row.getReceivedDateTime();
    received_date_time_is_set = p_row.getReceivedDateTimeIsSet();
    received_date_time_is_modified = p_row.getReceivedDateTimeIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    forced_settle_reason_type = p_row.getForcedSettleReasonType();
    forced_settle_reason_type_is_set = p_row.getForcedSettleReasonTypeIsSet();
    forced_settle_reason_type_is_modified = p_row.getForcedSettleReasonTypeIsModified();
    approve_status_type = p_row.getApproveStatusType();
    approve_status_type_is_set = p_row.getApproveStatusTypeIsSet();
    approve_status_type_is_modified = p_row.getApproveStatusTypeIsModified();
    if ( !p_row.getMarginMaintenanceRateIsNull() )
      margin_maintenance_rate = new Double( p_row.getMarginMaintenanceRate() );
    margin_maintenance_rate_is_set = p_row.getMarginMaintenanceRateIsSet();
    margin_maintenance_rate_is_modified = p_row.getMarginMaintenanceRateIsModified();
    additional_margin_date = p_row.getAdditionalMarginDate();
    additional_margin_date_is_set = p_row.getAdditionalMarginDateIsSet();
    additional_margin_date_is_modified = p_row.getAdditionalMarginDateIsModified();
    if ( !p_row.getAdditionalMarginAccruedDaysIsNull() )
      additional_margin_accrued_days = new Long( p_row.getAdditionalMarginAccruedDays() );
    additional_margin_accrued_days_is_set = p_row.getAdditionalMarginAccruedDaysIsSet();
    additional_margin_accrued_days_is_modified = p_row.getAdditionalMarginAccruedDaysIsModified();
    contract_id = p_row.getContractId();
    contract_id_is_set = p_row.getContractIdIsSet();
    contract_id_is_modified = p_row.getContractIdIsModified();
    org_contract_quantity = p_row.getOrgContractQuantity();
    org_contract_quantity_is_set = p_row.getOrgContractQuantityIsSet();
    org_contract_quantity_is_modified = p_row.getOrgContractQuantityIsModified();
    contract_quantity = p_row.getContractQuantity();
    contract_quantity_is_set = p_row.getContractQuantityIsSet();
    contract_quantity_is_modified = p_row.getContractQuantityIsModified();
    original_contract_price = p_row.getOriginalContractPrice();
    original_contract_price_is_set = p_row.getOriginalContractPriceIsSet();
    original_contract_price_is_modified = p_row.getOriginalContractPriceIsModified();
    contract_price = p_row.getContractPrice();
    contract_price_is_set = p_row.getContractPriceIsSet();
    contract_price_is_modified = p_row.getContractPriceIsModified();
    contract_type = p_row.getContractType();
    contract_type_is_set = p_row.getContractTypeIsSet();
    contract_type_is_modified = p_row.getContractTypeIsModified();
    open_date = p_row.getOpenDate();
    open_date_is_set = p_row.getOpenDateIsSet();
    open_date_is_modified = p_row.getOpenDateIsModified();
    close_date = p_row.getCloseDate();
    close_date_is_set = p_row.getCloseDateIsSet();
    close_date_is_modified = p_row.getCloseDateIsModified();
    if ( !p_row.getTaxTypeIsNull() )
      tax_type = new Integer( p_row.getTaxType() );
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    repayment_type = p_row.getRepaymentType();
    repayment_type_is_set = p_row.getRepaymentTypeIsSet();
    repayment_type_is_modified = p_row.getRepaymentTypeIsModified();
    if ( !p_row.getRepaymentNumIsNull() )
      repayment_num = new Integer( p_row.getRepaymentNum() );
    repayment_num_is_set = p_row.getRepaymentNumIsSet();
    repayment_num_is_modified = p_row.getRepaymentNumIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    approver_code = p_row.getApproverCode();
    approver_code_is_set = p_row.getApproverCodeIsSet();
    approver_code_is_modified = p_row.getApproverCodeIsModified();
    approve_timestamp = p_row.getApproveTimestamp();
    approve_timestamp_is_set = p_row.getApproveTimestampIsSet();
    approve_timestamp_is_modified = p_row.getApproveTimestampIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      order_id_is_set = true;
      order_id_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      order_categ_is_set = true;
      order_categ_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      received_date_time_is_set = true;
      received_date_time_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      forced_settle_reason_type_is_set = true;
      forced_settle_reason_type_is_modified = true;
      approve_status_type_is_set = true;
      approve_status_type_is_modified = true;
      margin_maintenance_rate_is_set = true;
      margin_maintenance_rate_is_modified = true;
      additional_margin_date_is_set = true;
      additional_margin_date_is_modified = true;
      additional_margin_accrued_days_is_set = true;
      additional_margin_accrued_days_is_modified = true;
      contract_id_is_set = true;
      contract_id_is_modified = true;
      org_contract_quantity_is_set = true;
      org_contract_quantity_is_modified = true;
      contract_quantity_is_set = true;
      contract_quantity_is_modified = true;
      original_contract_price_is_set = true;
      original_contract_price_is_modified = true;
      contract_price_is_set = true;
      contract_price_is_modified = true;
      contract_type_is_set = true;
      contract_type_is_modified = true;
      open_date_is_set = true;
      open_date_is_modified = true;
      close_date_is_set = true;
      close_date_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      repayment_type_is_set = true;
      repayment_type_is_modified = true;
      repayment_num_is_set = true;
      repayment_num_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      approver_code_is_set = true;
      approver_code_is_modified = true;
      approve_timestamp_is_set = true;
      approve_timestamp_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof ForcedSettleOrderInqRow ) )
       return false;
    return fieldsEqual( (ForcedSettleOrderInqRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�ForcedSettleOrderInqRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( ForcedSettleOrderInqRow row )
  {
    if ( forced_settle_order_inq_id != row.getForcedSettleOrderInqId() )
      return false;
    if ( order_id == null ) {
      if ( !row.getOrderIdIsNull() )
        return false;
    } else if ( row.getOrderIdIsNull() || ( order_id.longValue() != row.getOrderId() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( branch_id != row.getBranchId() )
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
    if ( market_id == null ) {
      if ( !row.getMarketIdIsNull() )
        return false;
    } else if ( row.getMarketIdIsNull() || ( market_id.longValue() != row.getMarketId() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( limit_price == null ) {
      if ( !row.getLimitPriceIsNull() )
        return false;
    } else if ( row.getLimitPriceIsNull() || ( limit_price.doubleValue() != row.getLimitPrice() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
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
    if ( received_date_time == null ) {
      if ( row.getReceivedDateTime() != null )
        return false;
    } else if ( !received_date_time.equals( row.getReceivedDateTime() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
        return false;
    }
    if ( forced_settle_reason_type == null ) {
      if ( row.getForcedSettleReasonType() != null )
        return false;
    } else if ( !forced_settle_reason_type.equals( row.getForcedSettleReasonType() ) ) {
        return false;
    }
    if ( approve_status_type == null ) {
      if ( row.getApproveStatusType() != null )
        return false;
    } else if ( !approve_status_type.equals( row.getApproveStatusType() ) ) {
        return false;
    }
    if ( margin_maintenance_rate == null ) {
      if ( !row.getMarginMaintenanceRateIsNull() )
        return false;
    } else if ( row.getMarginMaintenanceRateIsNull() || ( margin_maintenance_rate.doubleValue() != row.getMarginMaintenanceRate() ) ) {
        return false;
    }
    if ( additional_margin_date == null ) {
      if ( row.getAdditionalMarginDate() != null )
        return false;
    } else if ( !additional_margin_date.equals( row.getAdditionalMarginDate() ) ) {
        return false;
    }
    if ( additional_margin_accrued_days == null ) {
      if ( !row.getAdditionalMarginAccruedDaysIsNull() )
        return false;
    } else if ( row.getAdditionalMarginAccruedDaysIsNull() || ( additional_margin_accrued_days.longValue() != row.getAdditionalMarginAccruedDays() ) ) {
        return false;
    }
    if ( contract_id != row.getContractId() )
      return false;
    if ( org_contract_quantity != row.getOrgContractQuantity() )
      return false;
    if ( contract_quantity != row.getContractQuantity() )
      return false;
    if ( original_contract_price != row.getOriginalContractPrice() )
      return false;
    if ( contract_price != row.getContractPrice() )
      return false;
    if ( contract_type != row.getContractType() )
      return false;
    if ( open_date == null ) {
      if ( row.getOpenDate() != null )
        return false;
    } else if ( !open_date.equals( row.getOpenDate() ) ) {
        return false;
    }
    if ( close_date == null ) {
      if ( row.getCloseDate() != null )
        return false;
    } else if ( !close_date.equals( row.getCloseDate() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( !row.getTaxTypeIsNull() )
        return false;
    } else if ( row.getTaxTypeIsNull() || ( tax_type.intValue() != row.getTaxType() ) ) {
        return false;
    }
    if ( repayment_type == null ) {
      if ( row.getRepaymentType() != null )
        return false;
    } else if ( !repayment_type.equals( row.getRepaymentType() ) ) {
        return false;
    }
    if ( repayment_num == null ) {
      if ( !row.getRepaymentNumIsNull() )
        return false;
    } else if ( row.getRepaymentNumIsNull() || ( repayment_num.intValue() != row.getRepaymentNum() ) ) {
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
    if ( approver_code == null ) {
      if ( row.getApproverCode() != null )
        return false;
    } else if ( !approver_code.equals( row.getApproverCode() ) ) {
        return false;
    }
    if ( approve_timestamp == null ) {
      if ( row.getApproveTimestamp() != null )
        return false;
    } else if ( !approve_timestamp.equals( row.getApproveTimestamp() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) forced_settle_order_inq_id)
        + (order_id!=null? order_id.hashCode(): 0) 
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) branch_id)
        + (order_type!=null? order_type.hashCode(): 0) 
        + (order_categ!=null? order_categ.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + ((int) quantity)
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + ((int) product_id)
        + (received_date_time!=null? received_date_time.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (forced_settle_reason_type!=null? forced_settle_reason_type.hashCode(): 0) 
        + (approve_status_type!=null? approve_status_type.hashCode(): 0) 
        + (margin_maintenance_rate!=null? margin_maintenance_rate.hashCode(): 0) 
        + (additional_margin_date!=null? additional_margin_date.hashCode(): 0) 
        + (additional_margin_accrued_days!=null? additional_margin_accrued_days.hashCode(): 0) 
        + ((int) contract_id)
        + ((int) org_contract_quantity)
        + ((int) contract_quantity)
        + ((int) original_contract_price)
        + ((int) contract_price)
        + ((int) contract_type)
        + (open_date!=null? open_date.hashCode(): 0) 
        + (close_date!=null? close_date.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (repayment_type!=null? repayment_type.hashCode(): 0) 
        + (repayment_num!=null? repayment_num.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (approver_code!=null? approver_code.hashCode(): 0) 
        + (approve_timestamp!=null? approve_timestamp.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
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
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !order_categ_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_categ' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !contract_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_id' must be set before inserting.");
		if ( !org_contract_quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'org_contract_quantity' must be set before inserting.");
		if ( !contract_quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_quantity' must be set before inserting.");
		if ( !original_contract_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'original_contract_price' must be set before inserting.");
		if ( !contract_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_price' must be set before inserting.");
		if ( !contract_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_type' must be set before inserting.");
		if ( !open_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'open_date' must be set before inserting.");
		if ( !close_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'close_date' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("forced_settle_order_inq_id",new Long(forced_settle_order_inq_id));
		if ( order_id != null )
			map.put("order_id",order_id);
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("branch_id",new Long(branch_id));
		map.put("order_type",order_type);
		map.put("order_categ",order_categ);
		if ( market_id != null )
			map.put("market_id",market_id);
		map.put("quantity",new Double(quantity));
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		map.put("delivery_date",delivery_date);
		map.put("biz_date",biz_date);
		map.put("product_id",new Long(product_id));
		if ( received_date_time != null )
			map.put("received_date_time",received_date_time);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( forced_settle_reason_type != null )
			map.put("forced_settle_reason_type",forced_settle_reason_type);
		if ( approve_status_type != null )
			map.put("approve_status_type",approve_status_type);
		if ( margin_maintenance_rate != null )
			map.put("margin_maintenance_rate",margin_maintenance_rate);
		if ( additional_margin_date != null )
			map.put("additional_margin_date",additional_margin_date);
		if ( additional_margin_accrued_days != null )
			map.put("additional_margin_accrued_days",additional_margin_accrued_days);
		map.put("contract_id",new Long(contract_id));
		map.put("org_contract_quantity",new Double(org_contract_quantity));
		map.put("contract_quantity",new Double(contract_quantity));
		map.put("original_contract_price",new Double(original_contract_price));
		map.put("contract_price",new Double(contract_price));
		map.put("contract_type",new Integer(contract_type));
		map.put("open_date",open_date);
		map.put("close_date",close_date);
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( repayment_type != null )
			map.put("repayment_type",repayment_type);
		if ( repayment_num != null )
			map.put("repayment_num",repayment_num);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( approver_code != null )
			map.put("approver_code",approver_code);
		if ( approve_timestamp != null )
			map.put("approve_timestamp",approve_timestamp);
		map.put("account_code",account_code);
		map.put("product_code",product_code);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( order_id_is_modified )
			map.put("order_id",order_id);
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( order_categ_is_modified )
			map.put("order_categ",order_categ);
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( received_date_time_is_modified )
			map.put("received_date_time",received_date_time);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( forced_settle_reason_type_is_modified )
			map.put("forced_settle_reason_type",forced_settle_reason_type);
		if ( approve_status_type_is_modified )
			map.put("approve_status_type",approve_status_type);
		if ( margin_maintenance_rate_is_modified )
			map.put("margin_maintenance_rate",margin_maintenance_rate);
		if ( additional_margin_date_is_modified )
			map.put("additional_margin_date",additional_margin_date);
		if ( additional_margin_accrued_days_is_modified )
			map.put("additional_margin_accrued_days",additional_margin_accrued_days);
		if ( contract_id_is_modified )
			map.put("contract_id",new Long(contract_id));
		if ( org_contract_quantity_is_modified )
			map.put("org_contract_quantity",new Double(org_contract_quantity));
		if ( contract_quantity_is_modified )
			map.put("contract_quantity",new Double(contract_quantity));
		if ( original_contract_price_is_modified )
			map.put("original_contract_price",new Double(original_contract_price));
		if ( contract_price_is_modified )
			map.put("contract_price",new Double(contract_price));
		if ( contract_type_is_modified )
			map.put("contract_type",new Integer(contract_type));
		if ( open_date_is_modified )
			map.put("open_date",open_date);
		if ( close_date_is_modified )
			map.put("close_date",close_date);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( repayment_type_is_modified )
			map.put("repayment_type",repayment_type);
		if ( repayment_num_is_modified )
			map.put("repayment_num",repayment_num);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( approver_code_is_modified )
			map.put("approver_code",approver_code);
		if ( approve_timestamp_is_modified )
			map.put("approve_timestamp",approve_timestamp);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if (map.size() == 0) {
			map.put("order_id",order_id);
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( order_categ_is_set )
				map.put("order_categ",order_categ);
			map.put("market_id",market_id);
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("limit_price",limit_price);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			map.put("received_date_time",received_date_time);
			map.put("error_reason_code",error_reason_code);
			map.put("forced_settle_reason_type",forced_settle_reason_type);
			map.put("approve_status_type",approve_status_type);
			map.put("margin_maintenance_rate",margin_maintenance_rate);
			map.put("additional_margin_date",additional_margin_date);
			map.put("additional_margin_accrued_days",additional_margin_accrued_days);
			if ( contract_id_is_set )
				map.put("contract_id",new Long(contract_id));
			if ( org_contract_quantity_is_set )
				map.put("org_contract_quantity",new Double(org_contract_quantity));
			if ( contract_quantity_is_set )
				map.put("contract_quantity",new Double(contract_quantity));
			if ( original_contract_price_is_set )
				map.put("original_contract_price",new Double(original_contract_price));
			if ( contract_price_is_set )
				map.put("contract_price",new Double(contract_price));
			if ( contract_type_is_set )
				map.put("contract_type",new Integer(contract_type));
			if ( open_date_is_set )
				map.put("open_date",open_date);
			if ( close_date_is_set )
				map.put("close_date",close_date);
			map.put("tax_type",tax_type);
			map.put("repayment_type",repayment_type);
			map.put("repayment_num",repayment_num);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("approver_code",approver_code);
			map.put("approve_timestamp",approve_timestamp);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
		}
		return map;
	}


  /** 
   * <em>forced_settle_order_inq_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getForcedSettleOrderInqId()
  {
    return forced_settle_order_inq_id;
  }


  /** 
   * <em>forced_settle_order_inq_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForcedSettleOrderInqIdIsSet() {
    return forced_settle_order_inq_id_is_set;
  }


  /** 
   * <em>forced_settle_order_inq_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForcedSettleOrderInqIdIsModified() {
    return forced_settle_order_inq_id_is_modified;
  }


  /** 
   * <em>order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderId()
  {
    return ( order_id==null? ((long)0): order_id.longValue() );
  }


  /** 
   * <em>order_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOrderIdIsNull()
  {
    return order_id == null;
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
   * <em>limit_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLimitPrice()
  {
    return ( limit_price==null? ((double)0): limit_price.doubleValue() );
  }


  /** 
   * <em>limit_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLimitPriceIsNull()
  {
    return limit_price == null;
  }


  /** 
   * <em>limit_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLimitPriceIsSet() {
    return limit_price_is_set;
  }


  /** 
   * <em>limit_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLimitPriceIsModified() {
    return limit_price_is_modified;
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
   * <em>forced_settle_reason_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getForcedSettleReasonType()
  {
    return forced_settle_reason_type;
  }


  /** 
   * <em>forced_settle_reason_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForcedSettleReasonTypeIsSet() {
    return forced_settle_reason_type_is_set;
  }


  /** 
   * <em>forced_settle_reason_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForcedSettleReasonTypeIsModified() {
    return forced_settle_reason_type_is_modified;
  }


  /** 
   * <em>approve_status_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getApproveStatusType()
  {
    return approve_status_type;
  }


  /** 
   * <em>approve_status_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApproveStatusTypeIsSet() {
    return approve_status_type_is_set;
  }


  /** 
   * <em>approve_status_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApproveStatusTypeIsModified() {
    return approve_status_type_is_modified;
  }


  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMarginMaintenanceRate()
  {
    return ( margin_maintenance_rate==null? ((double)0): margin_maintenance_rate.doubleValue() );
  }


  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMarginMaintenanceRateIsNull()
  {
    return margin_maintenance_rate == null;
  }


  /** 
   * <em>margin_maintenance_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginMaintenanceRateIsSet() {
    return margin_maintenance_rate_is_set;
  }


  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginMaintenanceRateIsModified() {
    return margin_maintenance_rate_is_modified;
  }


  /** 
   * <em>additional_margin_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAdditionalMarginDate()
  {
    return additional_margin_date;
  }


  /** 
   * <em>additional_margin_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdditionalMarginDateIsSet() {
    return additional_margin_date_is_set;
  }


  /** 
   * <em>additional_margin_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdditionalMarginDateIsModified() {
    return additional_margin_date_is_modified;
  }


  /** 
   * <em>additional_margin_accrued_days</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAdditionalMarginAccruedDays()
  {
    return ( additional_margin_accrued_days==null? ((long)0): additional_margin_accrued_days.longValue() );
  }


  /** 
   * <em>additional_margin_accrued_days</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAdditionalMarginAccruedDaysIsNull()
  {
    return additional_margin_accrued_days == null;
  }


  /** 
   * <em>additional_margin_accrued_days</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdditionalMarginAccruedDaysIsSet() {
    return additional_margin_accrued_days_is_set;
  }


  /** 
   * <em>additional_margin_accrued_days</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdditionalMarginAccruedDaysIsModified() {
    return additional_margin_accrued_days_is_modified;
  }


  /** 
   * <em>contract_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getContractId()
  {
    return contract_id;
  }


  /** 
   * <em>contract_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractIdIsSet() {
    return contract_id_is_set;
  }


  /** 
   * <em>contract_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractIdIsModified() {
    return contract_id_is_modified;
  }


  /** 
   * <em>org_contract_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOrgContractQuantity()
  {
    return org_contract_quantity;
  }


  /** 
   * <em>org_contract_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrgContractQuantityIsSet() {
    return org_contract_quantity_is_set;
  }


  /** 
   * <em>org_contract_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrgContractQuantityIsModified() {
    return org_contract_quantity_is_modified;
  }


  /** 
   * <em>contract_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getContractQuantity()
  {
    return contract_quantity;
  }


  /** 
   * <em>contract_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractQuantityIsSet() {
    return contract_quantity_is_set;
  }


  /** 
   * <em>contract_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractQuantityIsModified() {
    return contract_quantity_is_modified;
  }


  /** 
   * <em>original_contract_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOriginalContractPrice()
  {
    return original_contract_price;
  }


  /** 
   * <em>original_contract_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalContractPriceIsSet() {
    return original_contract_price_is_set;
  }


  /** 
   * <em>original_contract_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOriginalContractPriceIsModified() {
    return original_contract_price_is_modified;
  }


  /** 
   * <em>contract_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getContractPrice()
  {
    return contract_price;
  }


  /** 
   * <em>contract_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractPriceIsSet() {
    return contract_price_is_set;
  }


  /** 
   * <em>contract_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractPriceIsModified() {
    return contract_price_is_modified;
  }


  /** 
   * <em>contract_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getContractType()
  {
    return contract_type;
  }


  /** 
   * <em>contract_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractTypeIsSet() {
    return contract_type_is_set;
  }


  /** 
   * <em>contract_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractTypeIsModified() {
    return contract_type_is_modified;
  }


  /** 
   * <em>open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOpenDate()
  {
    return open_date;
  }


  /** 
   * <em>open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenDateIsSet() {
    return open_date_is_set;
  }


  /** 
   * <em>open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenDateIsModified() {
    return open_date_is_modified;
  }


  /** 
   * <em>close_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCloseDate()
  {
    return close_date;
  }


  /** 
   * <em>close_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCloseDateIsSet() {
    return close_date_is_set;
  }


  /** 
   * <em>close_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCloseDateIsModified() {
    return close_date_is_modified;
  }


  /** 
   * <em>tax_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTaxType()
  {
    return ( tax_type==null? ((int)0): tax_type.intValue() );
  }


  /** 
   * <em>tax_type</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTaxTypeIsNull()
  {
    return tax_type == null;
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
   * <em>repayment_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRepaymentType()
  {
    return repayment_type;
  }


  /** 
   * <em>repayment_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRepaymentTypeIsSet() {
    return repayment_type_is_set;
  }


  /** 
   * <em>repayment_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRepaymentTypeIsModified() {
    return repayment_type_is_modified;
  }


  /** 
   * <em>repayment_num</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getRepaymentNum()
  {
    return ( repayment_num==null? ((int)0): repayment_num.intValue() );
  }


  /** 
   * <em>repayment_num</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getRepaymentNumIsNull()
  {
    return repayment_num == null;
  }


  /** 
   * <em>repayment_num</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRepaymentNumIsSet() {
    return repayment_num_is_set;
  }


  /** 
   * <em>repayment_num</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRepaymentNumIsModified() {
    return repayment_num_is_modified;
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
   * <em>approver_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getApproverCode()
  {
    return approver_code;
  }


  /** 
   * <em>approver_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApproverCodeIsSet() {
    return approver_code_is_set;
  }


  /** 
   * <em>approver_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApproverCodeIsModified() {
    return approver_code_is_modified;
  }


  /** 
   * <em>approve_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getApproveTimestamp()
  {
    return approve_timestamp;
  }


  /** 
   * <em>approve_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApproveTimestampIsSet() {
    return approve_timestamp_is_set;
  }


  /** 
   * <em>approve_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApproveTimestampIsModified() {
    return approve_timestamp_is_modified;
  }


  /** 
   * <em>account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new ForcedSettleOrderInqPK(forced_settle_order_inq_id);
  }


  /** 
   * <em>forced_settle_order_inq_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_forcedSettleOrderInqId <em>forced_settle_order_inq_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setForcedSettleOrderInqId( long p_forcedSettleOrderInqId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    forced_settle_order_inq_id = p_forcedSettleOrderInqId;
    forced_settle_order_inq_id_is_set = true;
    forced_settle_order_inq_id_is_modified = true;
  }


  /** 
   * <em>order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderId <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = new Long(p_orderId);
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>order_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOrderId( Long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
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
   * <em>limit_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_limitPrice <em>limit_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLimitPrice( double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = new Double(p_limitPrice);
    limit_price_is_set = true;
    limit_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>limit_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLimitPrice( Double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = p_limitPrice;
    limit_price_is_set = true;
    limit_price_is_modified = true;
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
   * <em>forced_settle_reason_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_forcedSettleReasonType <em>forced_settle_reason_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setForcedSettleReasonType( String p_forcedSettleReasonType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    forced_settle_reason_type = p_forcedSettleReasonType;
    forced_settle_reason_type_is_set = true;
    forced_settle_reason_type_is_modified = true;
  }


  /** 
   * <em>approve_status_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_approveStatusType <em>approve_status_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setApproveStatusType( String p_approveStatusType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    approve_status_type = p_approveStatusType;
    approve_status_type_is_set = true;
    approve_status_type_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marginMaintenanceRate <em>margin_maintenance_rate</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMarginMaintenanceRate( double p_marginMaintenanceRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_rate = new Double(p_marginMaintenanceRate);
    margin_maintenance_rate_is_set = true;
    margin_maintenance_rate_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>margin_maintenance_rate</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMarginMaintenanceRate( Double p_marginMaintenanceRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_rate = p_marginMaintenanceRate;
    margin_maintenance_rate_is_set = true;
    margin_maintenance_rate_is_modified = true;
  }


  /** 
   * <em>additional_margin_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_additionalMarginDate <em>additional_margin_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAdditionalMarginDate( java.sql.Timestamp p_additionalMarginDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_date = p_additionalMarginDate;
    additional_margin_date_is_set = true;
    additional_margin_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>additional_margin_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAdditionalMarginDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    additional_margin_date_is_set = true;
    additional_margin_date_is_modified = true;
  }


  /** 
   * <em>additional_margin_accrued_days</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_additionalMarginAccruedDays <em>additional_margin_accrued_days</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAdditionalMarginAccruedDays( long p_additionalMarginAccruedDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_accrued_days = new Long(p_additionalMarginAccruedDays);
    additional_margin_accrued_days_is_set = true;
    additional_margin_accrued_days_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>additional_margin_accrued_days</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAdditionalMarginAccruedDays( Long p_additionalMarginAccruedDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    additional_margin_accrued_days = p_additionalMarginAccruedDays;
    additional_margin_accrued_days_is_set = true;
    additional_margin_accrued_days_is_modified = true;
  }


  /** 
   * <em>contract_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_contractId <em>contract_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setContractId( long p_contractId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_id = p_contractId;
    contract_id_is_set = true;
    contract_id_is_modified = true;
  }


  /** 
   * <em>org_contract_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orgContractQuantity <em>org_contract_quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOrgContractQuantity( double p_orgContractQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_contract_quantity = p_orgContractQuantity;
    org_contract_quantity_is_set = true;
    org_contract_quantity_is_modified = true;
  }


  /** 
   * <em>contract_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_contractQuantity <em>contract_quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setContractQuantity( double p_contractQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_quantity = p_contractQuantity;
    contract_quantity_is_set = true;
    contract_quantity_is_modified = true;
  }


  /** 
   * <em>original_contract_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_originalContractPrice <em>original_contract_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOriginalContractPrice( double p_originalContractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_contract_price = p_originalContractPrice;
    original_contract_price_is_set = true;
    original_contract_price_is_modified = true;
  }


  /** 
   * <em>contract_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_contractPrice <em>contract_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setContractPrice( double p_contractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_price = p_contractPrice;
    contract_price_is_set = true;
    contract_price_is_modified = true;
  }


  /** 
   * <em>contract_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_contractType <em>contract_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setContractType( int p_contractType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_type = p_contractType;
    contract_type_is_set = true;
    contract_type_is_modified = true;
  }


  /** 
   * <em>open_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openDate <em>open_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOpenDate( java.sql.Timestamp p_openDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_date = p_openDate;
    open_date_is_set = true;
    open_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>open_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    open_date_is_set = true;
    open_date_is_modified = true;
  }


  /** 
   * <em>close_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_closeDate <em>close_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCloseDate( java.sql.Timestamp p_closeDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_date = p_closeDate;
    close_date_is_set = true;
    close_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>close_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCloseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    close_date_is_set = true;
    close_date_is_modified = true;
  }


  /** 
   * <em>tax_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_taxType <em>tax_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setTaxType( int p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = new Integer(p_taxType);
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>tax_type</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTaxType( Integer p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>repayment_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_repaymentType <em>repayment_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRepaymentType( String p_repaymentType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_type = p_repaymentType;
    repayment_type_is_set = true;
    repayment_type_is_modified = true;
  }


  /** 
   * <em>repayment_num</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_repaymentNum <em>repayment_num</em>�J�����̒l������킷7���ȉ���int�l 
   */
  public final void setRepaymentNum( int p_repaymentNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_num = new Integer(p_repaymentNum);
    repayment_num_is_set = true;
    repayment_num_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>repayment_num</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setRepaymentNum( Integer p_repaymentNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_num = p_repaymentNum;
    repayment_num_is_set = true;
    repayment_num_is_modified = true;
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
   * <em>approver_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_approverCode <em>approver_code</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setApproverCode( String p_approverCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    approver_code = p_approverCode;
    approver_code_is_set = true;
    approver_code_is_modified = true;
  }


  /** 
   * <em>approve_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_approveTimestamp <em>approve_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setApproveTimestamp( java.sql.Timestamp p_approveTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    approve_timestamp = p_approveTimestamp;
    approve_timestamp_is_set = true;
    approve_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>approve_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setApproveTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    approve_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    approve_timestamp_is_set = true;
    approve_timestamp_is_modified = true;
  }


  /** 
   * <em>account_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountCode <em>account_code</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
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
                else if ( name.equals("approve_status_type") ) {
                    return this.approve_status_type;
                }
                else if ( name.equals("additional_margin_date") ) {
                    return this.additional_margin_date;
                }
                else if ( name.equals("additional_margin_accrued_days") ) {
                    return this.additional_margin_accrued_days;
                }
                else if ( name.equals("approver_code") ) {
                    return this.approver_code;
                }
                else if ( name.equals("approve_timestamp") ) {
                    return this.approve_timestamp;
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
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
                if ( name.equals("contract_id") ) {
                    return new Long( contract_id );
                }
                else if ( name.equals("contract_quantity") ) {
                    return new Double( contract_quantity );
                }
                else if ( name.equals("contract_price") ) {
                    return new Double( contract_price );
                }
                else if ( name.equals("contract_type") ) {
                    return new Integer( contract_type );
                }
                else if ( name.equals("close_date") ) {
                    return this.close_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                break;
            case 'f':
                if ( name.equals("forced_settle_order_inq_id") ) {
                    return new Long( forced_settle_order_inq_id );
                }
                else if ( name.equals("forced_settle_reason_type") ) {
                    return this.forced_settle_reason_type;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    return this.limit_price;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return this.market_id;
                }
                else if ( name.equals("margin_maintenance_rate") ) {
                    return this.margin_maintenance_rate;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                else if ( name.equals("order_categ") ) {
                    return this.order_categ;
                }
                else if ( name.equals("org_contract_quantity") ) {
                    return new Double( org_contract_quantity );
                }
                else if ( name.equals("original_contract_price") ) {
                    return new Double( original_contract_price );
                }
                else if ( name.equals("open_date") ) {
                    return this.open_date;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 'r':
                if ( name.equals("received_date_time") ) {
                    return this.received_date_time;
                }
                else if ( name.equals("repayment_type") ) {
                    return this.repayment_type;
                }
                else if ( name.equals("repayment_num") ) {
                    return this.repayment_num;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    return this.tax_type;
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
                else if ( name.equals("approve_status_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'approve_status_type' must be String: '"+value+"' is not." );
                    this.approve_status_type = (String) value;
                    if (this.approve_status_type_is_set)
                        this.approve_status_type_is_modified = true;
                    this.approve_status_type_is_set = true;
                    return;
                }
                else if ( name.equals("additional_margin_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'additional_margin_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.additional_margin_date = (java.sql.Timestamp) value;
                    if (this.additional_margin_date_is_set)
                        this.additional_margin_date_is_modified = true;
                    this.additional_margin_date_is_set = true;
                    return;
                }
                else if ( name.equals("additional_margin_accrued_days") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'additional_margin_accrued_days' must be Long: '"+value+"' is not." );
                    this.additional_margin_accrued_days = (Long) value;
                    if (this.additional_margin_accrued_days_is_set)
                        this.additional_margin_accrued_days_is_modified = true;
                    this.additional_margin_accrued_days_is_set = true;
                    return;
                }
                else if ( name.equals("approver_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'approver_code' must be String: '"+value+"' is not." );
                    this.approver_code = (String) value;
                    if (this.approver_code_is_set)
                        this.approver_code_is_modified = true;
                    this.approver_code_is_set = true;
                    return;
                }
                else if ( name.equals("approve_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'approve_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.approve_timestamp = (java.sql.Timestamp) value;
                    if (this.approve_timestamp_is_set)
                        this.approve_timestamp_is_modified = true;
                    this.approve_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
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
                if ( name.equals("contract_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_id' must be Long: '"+value+"' is not." );
                    this.contract_id = ((Long) value).longValue();
                    if (this.contract_id_is_set)
                        this.contract_id_is_modified = true;
                    this.contract_id_is_set = true;
                    return;
                }
                else if ( name.equals("contract_quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_quantity' must be Double: '"+value+"' is not." );
                    this.contract_quantity = ((Double) value).doubleValue();
                    if (this.contract_quantity_is_set)
                        this.contract_quantity_is_modified = true;
                    this.contract_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("contract_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_price' must be Double: '"+value+"' is not." );
                    this.contract_price = ((Double) value).doubleValue();
                    if (this.contract_price_is_set)
                        this.contract_price_is_modified = true;
                    this.contract_price_is_set = true;
                    return;
                }
                else if ( name.equals("contract_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'contract_type' must be Integer: '"+value+"' is not." );
                    this.contract_type = ((Integer) value).intValue();
                    if (this.contract_type_is_set)
                        this.contract_type_is_modified = true;
                    this.contract_type_is_set = true;
                    return;
                }
                else if ( name.equals("close_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'close_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.close_date = (java.sql.Timestamp) value;
                    if (this.close_date_is_set)
                        this.close_date_is_modified = true;
                    this.close_date_is_set = true;
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
                if ( name.equals("error_reason_code") ) {
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
                if ( name.equals("forced_settle_order_inq_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'forced_settle_order_inq_id' must be Long: '"+value+"' is not." );
                    this.forced_settle_order_inq_id = ((Long) value).longValue();
                    if (this.forced_settle_order_inq_id_is_set)
                        this.forced_settle_order_inq_id_is_modified = true;
                    this.forced_settle_order_inq_id_is_set = true;
                    return;
                }
                else if ( name.equals("forced_settle_reason_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'forced_settle_reason_type' must be String: '"+value+"' is not." );
                    this.forced_settle_reason_type = (String) value;
                    if (this.forced_settle_reason_type_is_set)
                        this.forced_settle_reason_type_is_modified = true;
                    this.forced_settle_reason_type_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'limit_price' must be Double: '"+value+"' is not." );
                    this.limit_price = (Double) value;
                    if (this.limit_price_is_set)
                        this.limit_price_is_modified = true;
                    this.limit_price_is_set = true;
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
                else if ( name.equals("margin_maintenance_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_rate' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_rate = (Double) value;
                    if (this.margin_maintenance_rate_is_set)
                        this.margin_maintenance_rate_is_modified = true;
                    this.margin_maintenance_rate_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = (Long) value;
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
                else if ( name.equals("org_contract_quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'org_contract_quantity' must be Double: '"+value+"' is not." );
                    this.org_contract_quantity = ((Double) value).doubleValue();
                    if (this.org_contract_quantity_is_set)
                        this.org_contract_quantity_is_modified = true;
                    this.org_contract_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("original_contract_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'original_contract_price' must be Double: '"+value+"' is not." );
                    this.original_contract_price = ((Double) value).doubleValue();
                    if (this.original_contract_price_is_set)
                        this.original_contract_price_is_modified = true;
                    this.original_contract_price_is_set = true;
                    return;
                }
                else if ( name.equals("open_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.open_date = (java.sql.Timestamp) value;
                    if (this.open_date_is_set)
                        this.open_date_is_modified = true;
                    this.open_date_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
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
                else if ( name.equals("repayment_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'repayment_type' must be String: '"+value+"' is not." );
                    this.repayment_type = (String) value;
                    if (this.repayment_type_is_set)
                        this.repayment_type_is_modified = true;
                    this.repayment_type_is_set = true;
                    return;
                }
                else if ( name.equals("repayment_num") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'repayment_num' must be Integer: '"+value+"' is not." );
                    this.repayment_num = (Integer) value;
                    if (this.repayment_num_is_set)
                        this.repayment_num_is_modified = true;
                    this.repayment_num_is_set = true;
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
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be Integer: '"+value+"' is not." );
                    this.tax_type = (Integer) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
