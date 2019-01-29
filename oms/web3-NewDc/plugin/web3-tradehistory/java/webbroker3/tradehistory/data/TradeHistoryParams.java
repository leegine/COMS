head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TradeHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * trade_history�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link TradeHistoryRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link TradeHistoryParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link TradeHistoryParams}��{@@link TradeHistoryRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradeHistoryPK 
 * @@see TradeHistoryRow 
 */
public class TradeHistoryParams extends Params implements TradeHistoryRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "trade_history";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = TradeHistoryRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return TradeHistoryRow.TYPE;
  }


  /** 
   * <em>trade_history_id</em>�J�����̒l 
   */
  public  long  trade_history_id;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�J�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>�J�����̒l 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>�J�����̒l 
   */
  public  String  trader_code;

  /** 
   * <em>details_management_no</em>�J�����̒l 
   */
  public  Long  details_management_no;

  /** 
   * <em>delivery_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>exec_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  exec_date;

  /** 
   * <em>commodity_code</em>�J�����̒l 
   */
  public  String  commodity_code;

  /** 
   * <em>trade_code</em>�J�����̒l 
   */
  public  String  trade_code;

  /** 
   * <em>remark_code</em>�J�����̒l 
   */
  public  String  remark_code;

  /** 
   * <em>remark_name</em>�J�����̒l 
   */
  public  String  remark_name;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>product_name</em>�J�����̒l 
   */
  public  String  product_name;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  Double  quantity;

  /** 
   * <em>quantity_type</em>�J�����̒l 
   */
  public  String  quantity_type;

  /** 
   * <em>price</em>�J�����̒l 
   */
  public  Double  price;

  /** 
   * <em>account_div</em>�J�����̒l 
   */
  public  String  account_div;

  /** 
   * <em>capital_gain</em>�J�����̒l 
   */
  public  Double  capital_gain;

  /** 
   * <em>net_amount</em>�J�����̒l 
   */
  public  Double  net_amount;

  /** 
   * <em>io_div</em>�J�����̒l 
   */
  public  String  io_div;

  /** 
   * <em>buy_sell_div</em>�J�����̒l 
   */
  public  String  buy_sell_div;

  /** 
   * <em>repayment_type</em>�J�����̒l 
   */
  public  String  repayment_type;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>monetary_unit</em>�J�����̒l 
   */
  public  String  monetary_unit;

  /** 
   * <em>payment_div</em>�J�����̒l 
   */
  public  String  payment_div;

  private boolean trade_history_id_is_set = false;
  private boolean trade_history_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean details_management_no_is_set = false;
  private boolean details_management_no_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean exec_date_is_set = false;
  private boolean exec_date_is_modified = false;
  private boolean commodity_code_is_set = false;
  private boolean commodity_code_is_modified = false;
  private boolean trade_code_is_set = false;
  private boolean trade_code_is_modified = false;
  private boolean remark_code_is_set = false;
  private boolean remark_code_is_modified = false;
  private boolean remark_name_is_set = false;
  private boolean remark_name_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_name_is_set = false;
  private boolean product_name_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean quantity_type_is_set = false;
  private boolean quantity_type_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean capital_gain_is_set = false;
  private boolean capital_gain_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean io_div_is_set = false;
  private boolean io_div_is_modified = false;
  private boolean buy_sell_div_is_set = false;
  private boolean buy_sell_div_is_modified = false;
  private boolean repayment_type_is_set = false;
  private boolean repayment_type_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean monetary_unit_is_set = false;
  private boolean monetary_unit_is_modified = false;
  private boolean payment_div_is_set = false;
  private boolean payment_div_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "trade_history_id=" + trade_history_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "details_management_no=" +details_management_no
      + "," + "delivery_date=" +delivery_date
      + "," + "exec_date=" +exec_date
      + "," + "commodity_code=" +commodity_code
      + "," + "trade_code=" +trade_code
      + "," + "remark_code=" +remark_code
      + "," + "remark_name=" +remark_name
      + "," + "product_code=" +product_code
      + "," + "product_name=" +product_name
      + "," + "quantity=" +quantity
      + "," + "quantity_type=" +quantity_type
      + "," + "price=" +price
      + "," + "account_div=" +account_div
      + "," + "capital_gain=" +capital_gain
      + "," + "net_amount=" +net_amount
      + "," + "io_div=" +io_div
      + "," + "buy_sell_div=" +buy_sell_div
      + "," + "repayment_type=" +repayment_type
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "monetary_unit=" +monetary_unit
      + "," + "payment_div=" +payment_div
      + "}";
  }


  /** 
   * �l�����ݒ��TradeHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public TradeHistoryParams() {
    trade_history_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���TradeHistoryRow�I�u�W�F�N�g�̒l�𗘗p����TradeHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����TradeHistoryRow�I�u�W�F�N�g 
   */
  public TradeHistoryParams( TradeHistoryRow p_row )
  {
    trade_history_id = p_row.getTradeHistoryId();
    trade_history_id_is_set = p_row.getTradeHistoryIdIsSet();
    trade_history_id_is_modified = p_row.getTradeHistoryIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    if ( !p_row.getDetailsManagementNoIsNull() )
      details_management_no = new Long( p_row.getDetailsManagementNo() );
    details_management_no_is_set = p_row.getDetailsManagementNoIsSet();
    details_management_no_is_modified = p_row.getDetailsManagementNoIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    exec_date = p_row.getExecDate();
    exec_date_is_set = p_row.getExecDateIsSet();
    exec_date_is_modified = p_row.getExecDateIsModified();
    commodity_code = p_row.getCommodityCode();
    commodity_code_is_set = p_row.getCommodityCodeIsSet();
    commodity_code_is_modified = p_row.getCommodityCodeIsModified();
    trade_code = p_row.getTradeCode();
    trade_code_is_set = p_row.getTradeCodeIsSet();
    trade_code_is_modified = p_row.getTradeCodeIsModified();
    remark_code = p_row.getRemarkCode();
    remark_code_is_set = p_row.getRemarkCodeIsSet();
    remark_code_is_modified = p_row.getRemarkCodeIsModified();
    remark_name = p_row.getRemarkName();
    remark_name_is_set = p_row.getRemarkNameIsSet();
    remark_name_is_modified = p_row.getRemarkNameIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_name = p_row.getProductName();
    product_name_is_set = p_row.getProductNameIsSet();
    product_name_is_modified = p_row.getProductNameIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    quantity_type = p_row.getQuantityType();
    quantity_type_is_set = p_row.getQuantityTypeIsSet();
    quantity_type_is_modified = p_row.getQuantityTypeIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    if ( !p_row.getCapitalGainIsNull() )
      capital_gain = new Double( p_row.getCapitalGain() );
    capital_gain_is_set = p_row.getCapitalGainIsSet();
    capital_gain_is_modified = p_row.getCapitalGainIsModified();
    if ( !p_row.getNetAmountIsNull() )
      net_amount = new Double( p_row.getNetAmount() );
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    io_div = p_row.getIoDiv();
    io_div_is_set = p_row.getIoDivIsSet();
    io_div_is_modified = p_row.getIoDivIsModified();
    buy_sell_div = p_row.getBuySellDiv();
    buy_sell_div_is_set = p_row.getBuySellDivIsSet();
    buy_sell_div_is_modified = p_row.getBuySellDivIsModified();
    repayment_type = p_row.getRepaymentType();
    repayment_type_is_set = p_row.getRepaymentTypeIsSet();
    repayment_type_is_modified = p_row.getRepaymentTypeIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    monetary_unit = p_row.getMonetaryUnit();
    monetary_unit_is_set = p_row.getMonetaryUnitIsSet();
    monetary_unit_is_modified = p_row.getMonetaryUnitIsModified();
    payment_div = p_row.getPaymentDiv();
    payment_div_is_set = p_row.getPaymentDivIsSet();
    payment_div_is_modified = p_row.getPaymentDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      details_management_no_is_set = true;
      details_management_no_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      exec_date_is_set = true;
      exec_date_is_modified = true;
      commodity_code_is_set = true;
      commodity_code_is_modified = true;
      trade_code_is_set = true;
      trade_code_is_modified = true;
      remark_code_is_set = true;
      remark_code_is_modified = true;
      remark_name_is_set = true;
      remark_name_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      product_name_is_set = true;
      product_name_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      quantity_type_is_set = true;
      quantity_type_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      account_div_is_set = true;
      account_div_is_modified = true;
      capital_gain_is_set = true;
      capital_gain_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      io_div_is_set = true;
      io_div_is_modified = true;
      buy_sell_div_is_set = true;
      buy_sell_div_is_modified = true;
      repayment_type_is_set = true;
      repayment_type_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      monetary_unit_is_set = true;
      monetary_unit_is_modified = true;
      payment_div_is_set = true;
      payment_div_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TradeHistoryRow ) )
       return false;
    return fieldsEqual( (TradeHistoryRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�TradeHistoryRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( TradeHistoryRow row )
  {
    if ( trade_history_id != row.getTradeHistoryId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( details_management_no == null ) {
      if ( !row.getDetailsManagementNoIsNull() )
        return false;
    } else if ( row.getDetailsManagementNoIsNull() || ( details_management_no.longValue() != row.getDetailsManagementNo() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( exec_date == null ) {
      if ( row.getExecDate() != null )
        return false;
    } else if ( !exec_date.equals( row.getExecDate() ) ) {
        return false;
    }
    if ( commodity_code == null ) {
      if ( row.getCommodityCode() != null )
        return false;
    } else if ( !commodity_code.equals( row.getCommodityCode() ) ) {
        return false;
    }
    if ( trade_code == null ) {
      if ( row.getTradeCode() != null )
        return false;
    } else if ( !trade_code.equals( row.getTradeCode() ) ) {
        return false;
    }
    if ( remark_code == null ) {
      if ( row.getRemarkCode() != null )
        return false;
    } else if ( !remark_code.equals( row.getRemarkCode() ) ) {
        return false;
    }
    if ( remark_name == null ) {
      if ( row.getRemarkName() != null )
        return false;
    } else if ( !remark_name.equals( row.getRemarkName() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( product_name == null ) {
      if ( row.getProductName() != null )
        return false;
    } else if ( !product_name.equals( row.getProductName() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( quantity_type == null ) {
      if ( row.getQuantityType() != null )
        return false;
    } else if ( !quantity_type.equals( row.getQuantityType() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( capital_gain == null ) {
      if ( !row.getCapitalGainIsNull() )
        return false;
    } else if ( row.getCapitalGainIsNull() || ( capital_gain.doubleValue() != row.getCapitalGain() ) ) {
        return false;
    }
    if ( net_amount == null ) {
      if ( !row.getNetAmountIsNull() )
        return false;
    } else if ( row.getNetAmountIsNull() || ( net_amount.doubleValue() != row.getNetAmount() ) ) {
        return false;
    }
    if ( io_div == null ) {
      if ( row.getIoDiv() != null )
        return false;
    } else if ( !io_div.equals( row.getIoDiv() ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( row.getBuySellDiv() != null )
        return false;
    } else if ( !buy_sell_div.equals( row.getBuySellDiv() ) ) {
        return false;
    }
    if ( repayment_type == null ) {
      if ( row.getRepaymentType() != null )
        return false;
    } else if ( !repayment_type.equals( row.getRepaymentType() ) ) {
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
    if ( monetary_unit == null ) {
      if ( row.getMonetaryUnit() != null )
        return false;
    } else if ( !monetary_unit.equals( row.getMonetaryUnit() ) ) {
        return false;
    }
    if ( payment_div == null ) {
      if ( row.getPaymentDiv() != null )
        return false;
    } else if ( !payment_div.equals( row.getPaymentDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) trade_history_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (details_management_no!=null? details_management_no.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (exec_date!=null? exec_date.hashCode(): 0) 
        + (commodity_code!=null? commodity_code.hashCode(): 0) 
        + (trade_code!=null? trade_code.hashCode(): 0) 
        + (remark_code!=null? remark_code.hashCode(): 0) 
        + (remark_name!=null? remark_name.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (product_name!=null? product_name.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (quantity_type!=null? quantity_type.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (capital_gain!=null? capital_gain.hashCode(): 0) 
        + (net_amount!=null? net_amount.hashCode(): 0) 
        + (io_div!=null? io_div.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
        + (repayment_type!=null? repayment_type.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (monetary_unit!=null? monetary_unit.hashCode(): 0) 
        + (payment_div!=null? payment_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
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
		map.put("trade_history_id",new Long(trade_history_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( details_management_no != null )
			map.put("details_management_no",details_management_no);
		map.put("delivery_date",delivery_date);
		if ( exec_date != null )
			map.put("exec_date",exec_date);
		if ( commodity_code != null )
			map.put("commodity_code",commodity_code);
		if ( trade_code != null )
			map.put("trade_code",trade_code);
		if ( remark_code != null )
			map.put("remark_code",remark_code);
		if ( remark_name != null )
			map.put("remark_name",remark_name);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( product_name != null )
			map.put("product_name",product_name);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( quantity_type != null )
			map.put("quantity_type",quantity_type);
		if ( price != null )
			map.put("price",price);
		if ( account_div != null )
			map.put("account_div",account_div);
		if ( capital_gain != null )
			map.put("capital_gain",capital_gain);
		if ( net_amount != null )
			map.put("net_amount",net_amount);
		if ( io_div != null )
			map.put("io_div",io_div);
		if ( buy_sell_div != null )
			map.put("buy_sell_div",buy_sell_div);
		if ( repayment_type != null )
			map.put("repayment_type",repayment_type);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( monetary_unit != null )
			map.put("monetary_unit",monetary_unit);
		if ( payment_div != null )
			map.put("payment_div",payment_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( details_management_no_is_modified )
			map.put("details_management_no",details_management_no);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( exec_date_is_modified )
			map.put("exec_date",exec_date);
		if ( commodity_code_is_modified )
			map.put("commodity_code",commodity_code);
		if ( trade_code_is_modified )
			map.put("trade_code",trade_code);
		if ( remark_code_is_modified )
			map.put("remark_code",remark_code);
		if ( remark_name_is_modified )
			map.put("remark_name",remark_name);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_name_is_modified )
			map.put("product_name",product_name);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( quantity_type_is_modified )
			map.put("quantity_type",quantity_type);
		if ( price_is_modified )
			map.put("price",price);
		if ( account_div_is_modified )
			map.put("account_div",account_div);
		if ( capital_gain_is_modified )
			map.put("capital_gain",capital_gain);
		if ( net_amount_is_modified )
			map.put("net_amount",net_amount);
		if ( io_div_is_modified )
			map.put("io_div",io_div);
		if ( buy_sell_div_is_modified )
			map.put("buy_sell_div",buy_sell_div);
		if ( repayment_type_is_modified )
			map.put("repayment_type",repayment_type);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( monetary_unit_is_modified )
			map.put("monetary_unit",monetary_unit);
		if ( payment_div_is_modified )
			map.put("payment_div",payment_div);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("details_management_no",details_management_no);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			map.put("exec_date",exec_date);
			map.put("commodity_code",commodity_code);
			map.put("trade_code",trade_code);
			map.put("remark_code",remark_code);
			map.put("remark_name",remark_name);
			map.put("product_code",product_code);
			map.put("product_name",product_name);
			map.put("quantity",quantity);
			map.put("quantity_type",quantity_type);
			map.put("price",price);
			map.put("account_div",account_div);
			map.put("capital_gain",capital_gain);
			map.put("net_amount",net_amount);
			map.put("io_div",io_div);
			map.put("buy_sell_div",buy_sell_div);
			map.put("repayment_type",repayment_type);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("monetary_unit",monetary_unit);
			map.put("payment_div",payment_div);
		}
		return map;
	}


  /** 
   * <em>trade_history_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTradeHistoryId()
  {
    return trade_history_id;
  }


  /** 
   * <em>trade_history_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeHistoryIdIsSet() {
    return trade_history_id_is_set;
  }


  /** 
   * <em>trade_history_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeHistoryIdIsModified() {
    return trade_history_id_is_modified;
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
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
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
   * <em>trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>details_management_no</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getDetailsManagementNo()
  {
    return ( details_management_no==null? ((long)0): details_management_no.longValue() );
  }


  /** 
   * <em>details_management_no</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getDetailsManagementNoIsNull()
  {
    return details_management_no == null;
  }


  /** 
   * <em>details_management_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDetailsManagementNoIsSet() {
    return details_management_no_is_set;
  }


  /** 
   * <em>details_management_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDetailsManagementNoIsModified() {
    return details_management_no_is_modified;
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
   * <em>commodity_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommodityCode()
  {
    return commodity_code;
  }


  /** 
   * <em>commodity_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommodityCodeIsSet() {
    return commodity_code_is_set;
  }


  /** 
   * <em>commodity_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommodityCodeIsModified() {
    return commodity_code_is_modified;
  }


  /** 
   * <em>trade_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTradeCode()
  {
    return trade_code;
  }


  /** 
   * <em>trade_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeCodeIsSet() {
    return trade_code_is_set;
  }


  /** 
   * <em>trade_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeCodeIsModified() {
    return trade_code_is_modified;
  }


  /** 
   * <em>remark_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRemarkCode()
  {
    return remark_code;
  }


  /** 
   * <em>remark_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRemarkCodeIsSet() {
    return remark_code_is_set;
  }


  /** 
   * <em>remark_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRemarkCodeIsModified() {
    return remark_code_is_modified;
  }


  /** 
   * <em>remark_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRemarkName()
  {
    return remark_name;
  }


  /** 
   * <em>remark_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRemarkNameIsSet() {
    return remark_name_is_set;
  }


  /** 
   * <em>remark_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRemarkNameIsModified() {
    return remark_name_is_modified;
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
   * <em>product_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductName()
  {
    return product_name;
  }


  /** 
   * <em>product_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductNameIsSet() {
    return product_name_is_set;
  }


  /** 
   * <em>product_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductNameIsModified() {
    return product_name_is_modified;
  }


  /** 
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
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
   * <em>quantity_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getQuantityType()
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
   * <em>price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getPrice()
  {
    return ( price==null? ((double)0): price.doubleValue() );
  }


  /** 
   * <em>price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPriceIsNull()
  {
    return price == null;
  }


  /** 
   * <em>price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceIsSet() {
    return price_is_set;
  }


  /** 
   * <em>price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceIsModified() {
    return price_is_modified;
  }


  /** 
   * <em>account_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountDiv()
  {
    return account_div;
  }


  /** 
   * <em>account_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountDivIsSet() {
    return account_div_is_set;
  }


  /** 
   * <em>account_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountDivIsModified() {
    return account_div_is_modified;
  }


  /** 
   * <em>capital_gain</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCapitalGain()
  {
    return ( capital_gain==null? ((double)0): capital_gain.doubleValue() );
  }


  /** 
   * <em>capital_gain</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCapitalGainIsNull()
  {
    return capital_gain == null;
  }


  /** 
   * <em>capital_gain</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCapitalGainIsSet() {
    return capital_gain_is_set;
  }


  /** 
   * <em>capital_gain</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCapitalGainIsModified() {
    return capital_gain_is_modified;
  }


  /** 
   * <em>net_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getNetAmount()
  {
    return ( net_amount==null? ((double)0): net_amount.doubleValue() );
  }


  /** 
   * <em>net_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getNetAmountIsNull()
  {
    return net_amount == null;
  }


  /** 
   * <em>net_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmountIsSet() {
    return net_amount_is_set;
  }


  /** 
   * <em>net_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmountIsModified() {
    return net_amount_is_modified;
  }


  /** 
   * <em>io_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getIoDiv()
  {
    return io_div;
  }


  /** 
   * <em>io_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIoDivIsSet() {
    return io_div_is_set;
  }


  /** 
   * <em>io_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIoDivIsModified() {
    return io_div_is_modified;
  }


  /** 
   * <em>buy_sell_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBuySellDiv()
  {
    return buy_sell_div;
  }


  /** 
   * <em>buy_sell_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuySellDivIsSet() {
    return buy_sell_div_is_set;
  }


  /** 
   * <em>buy_sell_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuySellDivIsModified() {
    return buy_sell_div_is_modified;
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
   * <em>monetary_unit</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMonetaryUnit()
  {
    return monetary_unit;
  }


  /** 
   * <em>monetary_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMonetaryUnitIsSet() {
    return monetary_unit_is_set;
  }


  /** 
   * <em>monetary_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMonetaryUnitIsModified() {
    return monetary_unit_is_modified;
  }


  /** 
   * <em>payment_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPaymentDiv()
  {
    return payment_div;
  }


  /** 
   * <em>payment_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDivIsSet() {
    return payment_div_is_set;
  }


  /** 
   * <em>payment_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDivIsModified() {
    return payment_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TradeHistoryPK(trade_history_id);
  }


  /** 
   * <em>trade_history_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradeHistoryId <em>trade_history_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTradeHistoryId( long p_tradeHistoryId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_history_id = p_tradeHistoryId;
    trade_history_id_is_set = true;
    trade_history_id_is_modified = true;
  }


  /** 
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountCode <em>account_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>trader_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_traderCode <em>trader_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>details_management_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_detailsManagementNo <em>details_management_no</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setDetailsManagementNo( long p_detailsManagementNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    details_management_no = new Long(p_detailsManagementNo);
    details_management_no_is_set = true;
    details_management_no_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>details_management_no</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setDetailsManagementNo( Long p_detailsManagementNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    details_management_no = p_detailsManagementNo;
    details_management_no_is_set = true;
    details_management_no_is_modified = true;
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
   * <em>commodity_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commodityCode <em>commodity_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCommodityCode( String p_commodityCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commodity_code = p_commodityCode;
    commodity_code_is_set = true;
    commodity_code_is_modified = true;
  }


  /** 
   * <em>trade_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradeCode <em>trade_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setTradeCode( String p_tradeCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_code = p_tradeCode;
    trade_code_is_set = true;
    trade_code_is_modified = true;
  }


  /** 
   * <em>remark_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_remarkCode <em>remark_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setRemarkCode( String p_remarkCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_code = p_remarkCode;
    remark_code_is_set = true;
    remark_code_is_modified = true;
  }


  /** 
   * <em>remark_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_remarkName <em>remark_name</em>�J�����̒l������킷60���ȉ���String�l 
   */
  public final void setRemarkName( String p_remarkName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_name = p_remarkName;
    remark_name_is_set = true;
    remark_name_is_modified = true;
  }


  /** 
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>product_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productName <em>product_name</em>�J�����̒l������킷60���ȉ���String�l 
   */
  public final void setProductName( String p_productName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name = p_productName;
    product_name_is_set = true;
    product_name_is_modified = true;
  }


  /** 
   * <em>quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>quantity_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantityType <em>quantity_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setQuantityType( String p_quantityType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity_type = p_quantityType;
    quantity_type_is_set = true;
    quantity_type_is_modified = true;
  }


  /** 
   * <em>price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_price <em>price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setPrice( double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Double(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPrice( Double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>account_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountDiv <em>account_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAccountDiv( String p_accountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_div = p_accountDiv;
    account_div_is_set = true;
    account_div_is_modified = true;
  }


  /** 
   * <em>capital_gain</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_capitalGain <em>capital_gain</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCapitalGain( double p_capitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain = new Double(p_capitalGain);
    capital_gain_is_set = true;
    capital_gain_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>capital_gain</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCapitalGain( Double p_capitalGain )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain = p_capitalGain;
    capital_gain_is_set = true;
    capital_gain_is_modified = true;
  }


  /** 
   * <em>net_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_netAmount <em>net_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setNetAmount( double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = new Double(p_netAmount);
    net_amount_is_set = true;
    net_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>net_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setNetAmount( Double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = p_netAmount;
    net_amount_is_set = true;
    net_amount_is_modified = true;
  }


  /** 
   * <em>io_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ioDiv <em>io_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setIoDiv( String p_ioDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    io_div = p_ioDiv;
    io_div_is_set = true;
    io_div_is_modified = true;
  }


  /** 
   * <em>buy_sell_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buySellDiv <em>buy_sell_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBuySellDiv( String p_buySellDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_sell_div = p_buySellDiv;
    buy_sell_div_is_set = true;
    buy_sell_div_is_modified = true;
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
   * <em>monetary_unit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_monetaryUnit <em>monetary_unit</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setMonetaryUnit( String p_monetaryUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    monetary_unit = p_monetaryUnit;
    monetary_unit_is_set = true;
    monetary_unit_is_modified = true;
  }


  /** 
   * <em>payment_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentDiv <em>payment_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setPaymentDiv( String p_paymentDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_div = p_paymentDiv;
    payment_div_is_set = true;
    payment_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_div") ) {
                    return this.account_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_sell_div") ) {
                    return this.buy_sell_div;
                }
                break;
            case 'c':
                if ( name.equals("commodity_code") ) {
                    return this.commodity_code;
                }
                else if ( name.equals("capital_gain") ) {
                    return this.capital_gain;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("details_management_no") ) {
                    return this.details_management_no;
                }
                else if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("exec_date") ) {
                    return this.exec_date;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("io_div") ) {
                    return this.io_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("monetary_unit") ) {
                    return this.monetary_unit;
                }
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
                    return this.net_amount;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_name") ) {
                    return this.product_name;
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                else if ( name.equals("payment_div") ) {
                    return this.payment_div;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                else if ( name.equals("quantity_type") ) {
                    return this.quantity_type;
                }
                break;
            case 'r':
                if ( name.equals("remark_code") ) {
                    return this.remark_code;
                }
                else if ( name.equals("remark_name") ) {
                    return this.remark_name;
                }
                else if ( name.equals("repayment_type") ) {
                    return this.repayment_type;
                }
                break;
            case 't':
                if ( name.equals("trade_history_id") ) {
                    return new Long( trade_history_id );
                }
                else if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("trade_code") ) {
                    return this.trade_code;
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
                if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_div' must be String: '"+value+"' is not." );
                    this.account_div = (String) value;
                    if (this.account_div_is_set)
                        this.account_div_is_modified = true;
                    this.account_div_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("buy_sell_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_sell_div' must be String: '"+value+"' is not." );
                    this.buy_sell_div = (String) value;
                    if (this.buy_sell_div_is_set)
                        this.buy_sell_div_is_modified = true;
                    this.buy_sell_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commodity_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commodity_code' must be String: '"+value+"' is not." );
                    this.commodity_code = (String) value;
                    if (this.commodity_code_is_set)
                        this.commodity_code_is_modified = true;
                    this.commodity_code_is_set = true;
                    return;
                }
                else if ( name.equals("capital_gain") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'capital_gain' must be Double: '"+value+"' is not." );
                    this.capital_gain = (Double) value;
                    if (this.capital_gain_is_set)
                        this.capital_gain_is_modified = true;
                    this.capital_gain_is_set = true;
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
                if ( name.equals("details_management_no") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'details_management_no' must be Long: '"+value+"' is not." );
                    this.details_management_no = (Long) value;
                    if (this.details_management_no_is_set)
                        this.details_management_no_is_modified = true;
                    this.details_management_no_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_date") ) {
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
                if ( name.equals("exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_date = (java.sql.Timestamp) value;
                    if (this.exec_date_is_set)
                        this.exec_date_is_modified = true;
                    this.exec_date_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("io_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'io_div' must be String: '"+value+"' is not." );
                    this.io_div = (String) value;
                    if (this.io_div_is_set)
                        this.io_div_is_modified = true;
                    this.io_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("monetary_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'monetary_unit' must be String: '"+value+"' is not." );
                    this.monetary_unit = (String) value;
                    if (this.monetary_unit_is_set)
                        this.monetary_unit_is_modified = true;
                    this.monetary_unit_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'net_amount' must be Double: '"+value+"' is not." );
                    this.net_amount = (Double) value;
                    if (this.net_amount_is_set)
                        this.net_amount_is_modified = true;
                    this.net_amount_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name' must be String: '"+value+"' is not." );
                    this.product_name = (String) value;
                    if (this.product_name_is_set)
                        this.product_name_is_modified = true;
                    this.product_name_is_set = true;
                    return;
                }
                else if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                else if ( name.equals("payment_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_div' must be String: '"+value+"' is not." );
                    this.payment_div = (String) value;
                    if (this.payment_div_is_set)
                        this.payment_div_is_modified = true;
                    this.payment_div_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                else if ( name.equals("quantity_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'quantity_type' must be String: '"+value+"' is not." );
                    this.quantity_type = (String) value;
                    if (this.quantity_type_is_set)
                        this.quantity_type_is_modified = true;
                    this.quantity_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("remark_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark_code' must be String: '"+value+"' is not." );
                    this.remark_code = (String) value;
                    if (this.remark_code_is_set)
                        this.remark_code_is_modified = true;
                    this.remark_code_is_set = true;
                    return;
                }
                else if ( name.equals("remark_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark_name' must be String: '"+value+"' is not." );
                    this.remark_name = (String) value;
                    if (this.remark_name_is_set)
                        this.remark_name_is_modified = true;
                    this.remark_name_is_set = true;
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
                break;
            case 't':
                if ( name.equals("trade_history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trade_history_id' must be Long: '"+value+"' is not." );
                    this.trade_history_id = ((Long) value).longValue();
                    if (this.trade_history_id_is_set)
                        this.trade_history_id_is_modified = true;
                    this.trade_history_id_is_set = true;
                    return;
                }
                else if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("trade_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_code' must be String: '"+value+"' is not." );
                    this.trade_code = (String) value;
                    if (this.trade_code_is_set)
                        this.trade_code_is_modified = true;
                    this.trade_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
