head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountProductOrderStopParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * account_product_order_stop�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AccountProductOrderStopRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AccountProductOrderStopParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AccountProductOrderStopParams}��{@@link AccountProductOrderStopRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountProductOrderStopPK 
 * @@see AccountProductOrderStopRow 
 */
public class AccountProductOrderStopParams extends Params implements AccountProductOrderStopRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "account_product_order_stop";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AccountProductOrderStopRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AccountProductOrderStopRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>apply_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  apply_start_date;

  /** 
   * <em>apply_end_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  apply_end_date;

  /** 
   * <em>stop_trade_reason</em>�J�����̒l 
   */
  public  String  stop_trade_reason;

  /** 
   * <em>stop_trade_div_buy_cash</em>�J�����̒l 
   */
  public  String  stop_trade_div_buy_cash;

  /** 
   * <em>stop_trade_div_sell_cash</em>�J�����̒l 
   */
  public  String  stop_trade_div_sell_cash;

  /** 
   * <em>stop_trade_div_long_margin</em>�J�����̒l 
   */
  public  String  stop_trade_div_long_margin;

  /** 
   * <em>stop_trade_div_short_margin</em>�J�����̒l 
   */
  public  String  stop_trade_div_short_margin;

  /** 
   * <em>stop_div_long_close_margin</em>�J�����̒l 
   */
  public  String  stop_div_long_close_margin;

  /** 
   * <em>stop_div_short_close_margin</em>�J�����̒l 
   */
  public  String  stop_div_short_close_margin;

  /** 
   * <em>stop_div_long_swap_margin</em>�J�����̒l 
   */
  public  String  stop_div_long_swap_margin;

  /** 
   * <em>stop_div_short_swap_margin</em>�J�����̒l 
   */
  public  String  stop_div_short_swap_margin;

  /** 
   * <em>stop_div_buy_mini_stock</em>�J�����̒l 
   */
  public  String  stop_div_buy_mini_stock;

  /** 
   * <em>stop_div_sell_mini_stock</em>�J�����̒l 
   */
  public  String  stop_div_sell_mini_stock;

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

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean apply_start_date_is_set = false;
  private boolean apply_start_date_is_modified = false;
  private boolean apply_end_date_is_set = false;
  private boolean apply_end_date_is_modified = false;
  private boolean stop_trade_reason_is_set = false;
  private boolean stop_trade_reason_is_modified = false;
  private boolean stop_trade_div_buy_cash_is_set = false;
  private boolean stop_trade_div_buy_cash_is_modified = false;
  private boolean stop_trade_div_sell_cash_is_set = false;
  private boolean stop_trade_div_sell_cash_is_modified = false;
  private boolean stop_trade_div_long_margin_is_set = false;
  private boolean stop_trade_div_long_margin_is_modified = false;
  private boolean stop_trade_div_short_margin_is_set = false;
  private boolean stop_trade_div_short_margin_is_modified = false;
  private boolean stop_div_long_close_margin_is_set = false;
  private boolean stop_div_long_close_margin_is_modified = false;
  private boolean stop_div_short_close_margin_is_set = false;
  private boolean stop_div_short_close_margin_is_modified = false;
  private boolean stop_div_long_swap_margin_is_set = false;
  private boolean stop_div_long_swap_margin_is_modified = false;
  private boolean stop_div_short_swap_margin_is_set = false;
  private boolean stop_div_short_swap_margin_is_modified = false;
  private boolean stop_div_buy_mini_stock_is_set = false;
  private boolean stop_div_buy_mini_stock_is_modified = false;
  private boolean stop_div_sell_mini_stock_is_set = false;
  private boolean stop_div_sell_mini_stock_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_id=" + branch_id
      + "," + "account_id=" + account_id
      + "," + "product_id=" + product_id
      + "," + "apply_start_date=" + apply_start_date
      + "," + "apply_end_date=" +apply_end_date
      + "," + "stop_trade_reason=" +stop_trade_reason
      + "," + "stop_trade_div_buy_cash=" +stop_trade_div_buy_cash
      + "," + "stop_trade_div_sell_cash=" +stop_trade_div_sell_cash
      + "," + "stop_trade_div_long_margin=" +stop_trade_div_long_margin
      + "," + "stop_trade_div_short_margin=" +stop_trade_div_short_margin
      + "," + "stop_div_long_close_margin=" +stop_div_long_close_margin
      + "," + "stop_div_short_close_margin=" +stop_div_short_close_margin
      + "," + "stop_div_long_swap_margin=" +stop_div_long_swap_margin
      + "," + "stop_div_short_swap_margin=" +stop_div_short_swap_margin
      + "," + "stop_div_buy_mini_stock=" +stop_div_buy_mini_stock
      + "," + "stop_div_sell_mini_stock=" +stop_div_sell_mini_stock
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��AccountProductOrderStopParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AccountProductOrderStopParams() {
    institution_code_is_modified = true;
    branch_id_is_modified = true;
    account_id_is_modified = true;
    product_id_is_modified = true;
    apply_start_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AccountProductOrderStopRow�I�u�W�F�N�g�̒l�𗘗p����AccountProductOrderStopParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AccountProductOrderStopRow�I�u�W�F�N�g 
   */
  public AccountProductOrderStopParams( AccountProductOrderStopRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    apply_start_date = p_row.getApplyStartDate();
    apply_start_date_is_set = p_row.getApplyStartDateIsSet();
    apply_start_date_is_modified = p_row.getApplyStartDateIsModified();
    apply_end_date = p_row.getApplyEndDate();
    apply_end_date_is_set = p_row.getApplyEndDateIsSet();
    apply_end_date_is_modified = p_row.getApplyEndDateIsModified();
    stop_trade_reason = p_row.getStopTradeReason();
    stop_trade_reason_is_set = p_row.getStopTradeReasonIsSet();
    stop_trade_reason_is_modified = p_row.getStopTradeReasonIsModified();
    stop_trade_div_buy_cash = p_row.getStopTradeDivBuyCash();
    stop_trade_div_buy_cash_is_set = p_row.getStopTradeDivBuyCashIsSet();
    stop_trade_div_buy_cash_is_modified = p_row.getStopTradeDivBuyCashIsModified();
    stop_trade_div_sell_cash = p_row.getStopTradeDivSellCash();
    stop_trade_div_sell_cash_is_set = p_row.getStopTradeDivSellCashIsSet();
    stop_trade_div_sell_cash_is_modified = p_row.getStopTradeDivSellCashIsModified();
    stop_trade_div_long_margin = p_row.getStopTradeDivLongMargin();
    stop_trade_div_long_margin_is_set = p_row.getStopTradeDivLongMarginIsSet();
    stop_trade_div_long_margin_is_modified = p_row.getStopTradeDivLongMarginIsModified();
    stop_trade_div_short_margin = p_row.getStopTradeDivShortMargin();
    stop_trade_div_short_margin_is_set = p_row.getStopTradeDivShortMarginIsSet();
    stop_trade_div_short_margin_is_modified = p_row.getStopTradeDivShortMarginIsModified();
    stop_div_long_close_margin = p_row.getStopDivLongCloseMargin();
    stop_div_long_close_margin_is_set = p_row.getStopDivLongCloseMarginIsSet();
    stop_div_long_close_margin_is_modified = p_row.getStopDivLongCloseMarginIsModified();
    stop_div_short_close_margin = p_row.getStopDivShortCloseMargin();
    stop_div_short_close_margin_is_set = p_row.getStopDivShortCloseMarginIsSet();
    stop_div_short_close_margin_is_modified = p_row.getStopDivShortCloseMarginIsModified();
    stop_div_long_swap_margin = p_row.getStopDivLongSwapMargin();
    stop_div_long_swap_margin_is_set = p_row.getStopDivLongSwapMarginIsSet();
    stop_div_long_swap_margin_is_modified = p_row.getStopDivLongSwapMarginIsModified();
    stop_div_short_swap_margin = p_row.getStopDivShortSwapMargin();
    stop_div_short_swap_margin_is_set = p_row.getStopDivShortSwapMarginIsSet();
    stop_div_short_swap_margin_is_modified = p_row.getStopDivShortSwapMarginIsModified();
    stop_div_buy_mini_stock = p_row.getStopDivBuyMiniStock();
    stop_div_buy_mini_stock_is_set = p_row.getStopDivBuyMiniStockIsSet();
    stop_div_buy_mini_stock_is_modified = p_row.getStopDivBuyMiniStockIsModified();
    stop_div_sell_mini_stock = p_row.getStopDivSellMiniStock();
    stop_div_sell_mini_stock_is_set = p_row.getStopDivSellMiniStockIsSet();
    stop_div_sell_mini_stock_is_modified = p_row.getStopDivSellMiniStockIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      apply_end_date_is_set = true;
      apply_end_date_is_modified = true;
      stop_trade_reason_is_set = true;
      stop_trade_reason_is_modified = true;
      stop_trade_div_buy_cash_is_set = true;
      stop_trade_div_buy_cash_is_modified = true;
      stop_trade_div_sell_cash_is_set = true;
      stop_trade_div_sell_cash_is_modified = true;
      stop_trade_div_long_margin_is_set = true;
      stop_trade_div_long_margin_is_modified = true;
      stop_trade_div_short_margin_is_set = true;
      stop_trade_div_short_margin_is_modified = true;
      stop_div_long_close_margin_is_set = true;
      stop_div_long_close_margin_is_modified = true;
      stop_div_short_close_margin_is_set = true;
      stop_div_short_close_margin_is_modified = true;
      stop_div_long_swap_margin_is_set = true;
      stop_div_long_swap_margin_is_modified = true;
      stop_div_short_swap_margin_is_set = true;
      stop_div_short_swap_margin_is_modified = true;
      stop_div_buy_mini_stock_is_set = true;
      stop_div_buy_mini_stock_is_modified = true;
      stop_div_sell_mini_stock_is_set = true;
      stop_div_sell_mini_stock_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AccountProductOrderStopRow ) )
       return false;
    return fieldsEqual( (AccountProductOrderStopRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AccountProductOrderStopRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AccountProductOrderStopRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_id != row.getBranchId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( apply_start_date == null ) {
      if ( row.getApplyStartDate() != null )
        return false;
    } else if ( !apply_start_date.equals( row.getApplyStartDate() ) ) {
        return false;
    }
    if ( apply_end_date == null ) {
      if ( row.getApplyEndDate() != null )
        return false;
    } else if ( !apply_end_date.equals( row.getApplyEndDate() ) ) {
        return false;
    }
    if ( stop_trade_reason == null ) {
      if ( row.getStopTradeReason() != null )
        return false;
    } else if ( !stop_trade_reason.equals( row.getStopTradeReason() ) ) {
        return false;
    }
    if ( stop_trade_div_buy_cash == null ) {
      if ( row.getStopTradeDivBuyCash() != null )
        return false;
    } else if ( !stop_trade_div_buy_cash.equals( row.getStopTradeDivBuyCash() ) ) {
        return false;
    }
    if ( stop_trade_div_sell_cash == null ) {
      if ( row.getStopTradeDivSellCash() != null )
        return false;
    } else if ( !stop_trade_div_sell_cash.equals( row.getStopTradeDivSellCash() ) ) {
        return false;
    }
    if ( stop_trade_div_long_margin == null ) {
      if ( row.getStopTradeDivLongMargin() != null )
        return false;
    } else if ( !stop_trade_div_long_margin.equals( row.getStopTradeDivLongMargin() ) ) {
        return false;
    }
    if ( stop_trade_div_short_margin == null ) {
      if ( row.getStopTradeDivShortMargin() != null )
        return false;
    } else if ( !stop_trade_div_short_margin.equals( row.getStopTradeDivShortMargin() ) ) {
        return false;
    }
    if ( stop_div_long_close_margin == null ) {
      if ( row.getStopDivLongCloseMargin() != null )
        return false;
    } else if ( !stop_div_long_close_margin.equals( row.getStopDivLongCloseMargin() ) ) {
        return false;
    }
    if ( stop_div_short_close_margin == null ) {
      if ( row.getStopDivShortCloseMargin() != null )
        return false;
    } else if ( !stop_div_short_close_margin.equals( row.getStopDivShortCloseMargin() ) ) {
        return false;
    }
    if ( stop_div_long_swap_margin == null ) {
      if ( row.getStopDivLongSwapMargin() != null )
        return false;
    } else if ( !stop_div_long_swap_margin.equals( row.getStopDivLongSwapMargin() ) ) {
        return false;
    }
    if ( stop_div_short_swap_margin == null ) {
      if ( row.getStopDivShortSwapMargin() != null )
        return false;
    } else if ( !stop_div_short_swap_margin.equals( row.getStopDivShortSwapMargin() ) ) {
        return false;
    }
    if ( stop_div_buy_mini_stock == null ) {
      if ( row.getStopDivBuyMiniStock() != null )
        return false;
    } else if ( !stop_div_buy_mini_stock.equals( row.getStopDivBuyMiniStock() ) ) {
        return false;
    }
    if ( stop_div_sell_mini_stock == null ) {
      if ( row.getStopDivSellMiniStock() != null )
        return false;
    } else if ( !stop_div_sell_mini_stock.equals( row.getStopDivSellMiniStock() ) ) {
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
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) branch_id)
        + ((int) account_id)
        + ((int) product_id)
        + (apply_start_date!=null? apply_start_date.hashCode(): 0) 
        + (apply_end_date!=null? apply_end_date.hashCode(): 0) 
        + (stop_trade_reason!=null? stop_trade_reason.hashCode(): 0) 
        + (stop_trade_div_buy_cash!=null? stop_trade_div_buy_cash.hashCode(): 0) 
        + (stop_trade_div_sell_cash!=null? stop_trade_div_sell_cash.hashCode(): 0) 
        + (stop_trade_div_long_margin!=null? stop_trade_div_long_margin.hashCode(): 0) 
        + (stop_trade_div_short_margin!=null? stop_trade_div_short_margin.hashCode(): 0) 
        + (stop_div_long_close_margin!=null? stop_div_long_close_margin.hashCode(): 0) 
        + (stop_div_short_close_margin!=null? stop_div_short_close_margin.hashCode(): 0) 
        + (stop_div_long_swap_margin!=null? stop_div_long_swap_margin.hashCode(): 0) 
        + (stop_div_short_swap_margin!=null? stop_div_short_swap_margin.hashCode(): 0) 
        + (stop_div_buy_mini_stock!=null? stop_div_buy_mini_stock.hashCode(): 0) 
        + (stop_div_sell_mini_stock!=null? stop_div_sell_mini_stock.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !apply_end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'apply_end_date' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_id",new Long(branch_id));
		map.put("account_id",new Long(account_id));
		map.put("product_id",new Long(product_id));
		map.put("apply_start_date",apply_start_date);
		map.put("apply_end_date",apply_end_date);
		if ( stop_trade_reason != null )
			map.put("stop_trade_reason",stop_trade_reason);
		if ( stop_trade_div_buy_cash != null )
			map.put("stop_trade_div_buy_cash",stop_trade_div_buy_cash);
		if ( stop_trade_div_sell_cash != null )
			map.put("stop_trade_div_sell_cash",stop_trade_div_sell_cash);
		if ( stop_trade_div_long_margin != null )
			map.put("stop_trade_div_long_margin",stop_trade_div_long_margin);
		if ( stop_trade_div_short_margin != null )
			map.put("stop_trade_div_short_margin",stop_trade_div_short_margin);
		if ( stop_div_long_close_margin != null )
			map.put("stop_div_long_close_margin",stop_div_long_close_margin);
		if ( stop_div_short_close_margin != null )
			map.put("stop_div_short_close_margin",stop_div_short_close_margin);
		if ( stop_div_long_swap_margin != null )
			map.put("stop_div_long_swap_margin",stop_div_long_swap_margin);
		if ( stop_div_short_swap_margin != null )
			map.put("stop_div_short_swap_margin",stop_div_short_swap_margin);
		if ( stop_div_buy_mini_stock != null )
			map.put("stop_div_buy_mini_stock",stop_div_buy_mini_stock);
		if ( stop_div_sell_mini_stock != null )
			map.put("stop_div_sell_mini_stock",stop_div_sell_mini_stock);
		map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( apply_end_date_is_modified )
			map.put("apply_end_date",apply_end_date);
		if ( stop_trade_reason_is_modified )
			map.put("stop_trade_reason",stop_trade_reason);
		if ( stop_trade_div_buy_cash_is_modified )
			map.put("stop_trade_div_buy_cash",stop_trade_div_buy_cash);
		if ( stop_trade_div_sell_cash_is_modified )
			map.put("stop_trade_div_sell_cash",stop_trade_div_sell_cash);
		if ( stop_trade_div_long_margin_is_modified )
			map.put("stop_trade_div_long_margin",stop_trade_div_long_margin);
		if ( stop_trade_div_short_margin_is_modified )
			map.put("stop_trade_div_short_margin",stop_trade_div_short_margin);
		if ( stop_div_long_close_margin_is_modified )
			map.put("stop_div_long_close_margin",stop_div_long_close_margin);
		if ( stop_div_short_close_margin_is_modified )
			map.put("stop_div_short_close_margin",stop_div_short_close_margin);
		if ( stop_div_long_swap_margin_is_modified )
			map.put("stop_div_long_swap_margin",stop_div_long_swap_margin);
		if ( stop_div_short_swap_margin_is_modified )
			map.put("stop_div_short_swap_margin",stop_div_short_swap_margin);
		if ( stop_div_buy_mini_stock_is_modified )
			map.put("stop_div_buy_mini_stock",stop_div_buy_mini_stock);
		if ( stop_div_sell_mini_stock_is_modified )
			map.put("stop_div_sell_mini_stock",stop_div_sell_mini_stock);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( apply_end_date_is_set )
				map.put("apply_end_date",apply_end_date);
			map.put("stop_trade_reason",stop_trade_reason);
			map.put("stop_trade_div_buy_cash",stop_trade_div_buy_cash);
			map.put("stop_trade_div_sell_cash",stop_trade_div_sell_cash);
			map.put("stop_trade_div_long_margin",stop_trade_div_long_margin);
			map.put("stop_trade_div_short_margin",stop_trade_div_short_margin);
			map.put("stop_div_long_close_margin",stop_div_long_close_margin);
			map.put("stop_div_short_close_margin",stop_div_short_close_margin);
			map.put("stop_div_long_swap_margin",stop_div_long_swap_margin);
			map.put("stop_div_short_swap_margin",stop_div_short_swap_margin);
			map.put("stop_div_buy_mini_stock",stop_div_buy_mini_stock);
			map.put("stop_div_sell_mini_stock",stop_div_sell_mini_stock);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>apply_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getApplyStartDate()
  {
    return apply_start_date;
  }


  /** 
   * <em>apply_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyStartDateIsSet() {
    return apply_start_date_is_set;
  }


  /** 
   * <em>apply_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyStartDateIsModified() {
    return apply_start_date_is_modified;
  }


  /** 
   * <em>apply_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getApplyEndDate()
  {
    return apply_end_date;
  }


  /** 
   * <em>apply_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyEndDateIsSet() {
    return apply_end_date_is_set;
  }


  /** 
   * <em>apply_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getApplyEndDateIsModified() {
    return apply_end_date_is_modified;
  }


  /** 
   * <em>stop_trade_reason</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopTradeReason()
  {
    return stop_trade_reason;
  }


  /** 
   * <em>stop_trade_reason</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeReasonIsSet() {
    return stop_trade_reason_is_set;
  }


  /** 
   * <em>stop_trade_reason</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeReasonIsModified() {
    return stop_trade_reason_is_modified;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopTradeDivBuyCash()
  {
    return stop_trade_div_buy_cash;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivBuyCashIsSet() {
    return stop_trade_div_buy_cash_is_set;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivBuyCashIsModified() {
    return stop_trade_div_buy_cash_is_modified;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopTradeDivSellCash()
  {
    return stop_trade_div_sell_cash;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivSellCashIsSet() {
    return stop_trade_div_sell_cash_is_set;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivSellCashIsModified() {
    return stop_trade_div_sell_cash_is_modified;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopTradeDivLongMargin()
  {
    return stop_trade_div_long_margin;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivLongMarginIsSet() {
    return stop_trade_div_long_margin_is_set;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivLongMarginIsModified() {
    return stop_trade_div_long_margin_is_modified;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopTradeDivShortMargin()
  {
    return stop_trade_div_short_margin;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivShortMarginIsSet() {
    return stop_trade_div_short_margin_is_set;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopTradeDivShortMarginIsModified() {
    return stop_trade_div_short_margin_is_modified;
  }


  /** 
   * <em>stop_div_long_close_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopDivLongCloseMargin()
  {
    return stop_div_long_close_margin;
  }


  /** 
   * <em>stop_div_long_close_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivLongCloseMarginIsSet() {
    return stop_div_long_close_margin_is_set;
  }


  /** 
   * <em>stop_div_long_close_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivLongCloseMarginIsModified() {
    return stop_div_long_close_margin_is_modified;
  }


  /** 
   * <em>stop_div_short_close_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopDivShortCloseMargin()
  {
    return stop_div_short_close_margin;
  }


  /** 
   * <em>stop_div_short_close_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivShortCloseMarginIsSet() {
    return stop_div_short_close_margin_is_set;
  }


  /** 
   * <em>stop_div_short_close_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivShortCloseMarginIsModified() {
    return stop_div_short_close_margin_is_modified;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopDivLongSwapMargin()
  {
    return stop_div_long_swap_margin;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivLongSwapMarginIsSet() {
    return stop_div_long_swap_margin_is_set;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivLongSwapMarginIsModified() {
    return stop_div_long_swap_margin_is_modified;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopDivShortSwapMargin()
  {
    return stop_div_short_swap_margin;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivShortSwapMarginIsSet() {
    return stop_div_short_swap_margin_is_set;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivShortSwapMarginIsModified() {
    return stop_div_short_swap_margin_is_modified;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopDivBuyMiniStock()
  {
    return stop_div_buy_mini_stock;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivBuyMiniStockIsSet() {
    return stop_div_buy_mini_stock_is_set;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivBuyMiniStockIsModified() {
    return stop_div_buy_mini_stock_is_modified;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStopDivSellMiniStock()
  {
    return stop_div_sell_mini_stock;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivSellMiniStockIsSet() {
    return stop_div_sell_mini_stock_is_set;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStopDivSellMiniStockIsModified() {
    return stop_div_sell_mini_stock_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AccountProductOrderStopPK(institution_code, branch_id, account_id, product_id, apply_start_date);
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
   * <em>apply_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyStartDate <em>apply_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setApplyStartDate( java.sql.Timestamp p_applyStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_start_date = p_applyStartDate;
    apply_start_date_is_set = true;
    apply_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>apply_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setApplyStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    apply_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    apply_start_date_is_set = true;
    apply_start_date_is_modified = true;
  }


  /** 
   * <em>apply_end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_applyEndDate <em>apply_end_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setApplyEndDate( java.sql.Timestamp p_applyEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_end_date = p_applyEndDate;
    apply_end_date_is_set = true;
    apply_end_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>apply_end_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setApplyEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    apply_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    apply_end_date_is_set = true;
    apply_end_date_is_modified = true;
  }


  /** 
   * <em>stop_trade_reason</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopTradeReason <em>stop_trade_reason</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setStopTradeReason( String p_stopTradeReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_reason = p_stopTradeReason;
    stop_trade_reason_is_set = true;
    stop_trade_reason_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopTradeDivBuyCash <em>stop_trade_div_buy_cash</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopTradeDivBuyCash( String p_stopTradeDivBuyCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_buy_cash = p_stopTradeDivBuyCash;
    stop_trade_div_buy_cash_is_set = true;
    stop_trade_div_buy_cash_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopTradeDivSellCash <em>stop_trade_div_sell_cash</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopTradeDivSellCash( String p_stopTradeDivSellCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_sell_cash = p_stopTradeDivSellCash;
    stop_trade_div_sell_cash_is_set = true;
    stop_trade_div_sell_cash_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopTradeDivLongMargin <em>stop_trade_div_long_margin</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopTradeDivLongMargin( String p_stopTradeDivLongMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_long_margin = p_stopTradeDivLongMargin;
    stop_trade_div_long_margin_is_set = true;
    stop_trade_div_long_margin_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopTradeDivShortMargin <em>stop_trade_div_short_margin</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopTradeDivShortMargin( String p_stopTradeDivShortMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_short_margin = p_stopTradeDivShortMargin;
    stop_trade_div_short_margin_is_set = true;
    stop_trade_div_short_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_long_close_margin</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopDivLongCloseMargin <em>stop_div_long_close_margin</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopDivLongCloseMargin( String p_stopDivLongCloseMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_long_close_margin = p_stopDivLongCloseMargin;
    stop_div_long_close_margin_is_set = true;
    stop_div_long_close_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_short_close_margin</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopDivShortCloseMargin <em>stop_div_short_close_margin</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopDivShortCloseMargin( String p_stopDivShortCloseMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_short_close_margin = p_stopDivShortCloseMargin;
    stop_div_short_close_margin_is_set = true;
    stop_div_short_close_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopDivLongSwapMargin <em>stop_div_long_swap_margin</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopDivLongSwapMargin( String p_stopDivLongSwapMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_long_swap_margin = p_stopDivLongSwapMargin;
    stop_div_long_swap_margin_is_set = true;
    stop_div_long_swap_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopDivShortSwapMargin <em>stop_div_short_swap_margin</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopDivShortSwapMargin( String p_stopDivShortSwapMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_short_swap_margin = p_stopDivShortSwapMargin;
    stop_div_short_swap_margin_is_set = true;
    stop_div_short_swap_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopDivBuyMiniStock <em>stop_div_buy_mini_stock</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopDivBuyMiniStock( String p_stopDivBuyMiniStock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_buy_mini_stock = p_stopDivBuyMiniStock;
    stop_div_buy_mini_stock_is_set = true;
    stop_div_buy_mini_stock_is_modified = true;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stopDivSellMiniStock <em>stop_div_sell_mini_stock</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStopDivSellMiniStock( String p_stopDivSellMiniStock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_sell_mini_stock = p_stopDivSellMiniStock;
    stop_div_sell_mini_stock_is_set = true;
    stop_div_sell_mini_stock_is_modified = true;
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
                else if ( name.equals("apply_start_date") ) {
                    return this.apply_start_date;
                }
                else if ( name.equals("apply_end_date") ) {
                    return this.apply_end_date;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 's':
                if ( name.equals("stop_trade_reason") ) {
                    return this.stop_trade_reason;
                }
                else if ( name.equals("stop_trade_div_buy_cash") ) {
                    return this.stop_trade_div_buy_cash;
                }
                else if ( name.equals("stop_trade_div_sell_cash") ) {
                    return this.stop_trade_div_sell_cash;
                }
                else if ( name.equals("stop_trade_div_long_margin") ) {
                    return this.stop_trade_div_long_margin;
                }
                else if ( name.equals("stop_trade_div_short_margin") ) {
                    return this.stop_trade_div_short_margin;
                }
                else if ( name.equals("stop_div_long_close_margin") ) {
                    return this.stop_div_long_close_margin;
                }
                else if ( name.equals("stop_div_short_close_margin") ) {
                    return this.stop_div_short_close_margin;
                }
                else if ( name.equals("stop_div_long_swap_margin") ) {
                    return this.stop_div_long_swap_margin;
                }
                else if ( name.equals("stop_div_short_swap_margin") ) {
                    return this.stop_div_short_swap_margin;
                }
                else if ( name.equals("stop_div_buy_mini_stock") ) {
                    return this.stop_div_buy_mini_stock;
                }
                else if ( name.equals("stop_div_sell_mini_stock") ) {
                    return this.stop_div_sell_mini_stock;
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
                else if ( name.equals("apply_start_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'apply_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.apply_start_date = (java.sql.Timestamp) value;
                    if (this.apply_start_date_is_set)
                        this.apply_start_date_is_modified = true;
                    this.apply_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("apply_end_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'apply_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.apply_end_date = (java.sql.Timestamp) value;
                    if (this.apply_end_date_is_set)
                        this.apply_end_date_is_modified = true;
                    this.apply_end_date_is_set = true;
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
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
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
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
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
                break;
            case 's':
                if ( name.equals("stop_trade_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_reason' must be String: '"+value+"' is not." );
                    this.stop_trade_reason = (String) value;
                    if (this.stop_trade_reason_is_set)
                        this.stop_trade_reason_is_modified = true;
                    this.stop_trade_reason_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_buy_cash") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_buy_cash' must be String: '"+value+"' is not." );
                    this.stop_trade_div_buy_cash = (String) value;
                    if (this.stop_trade_div_buy_cash_is_set)
                        this.stop_trade_div_buy_cash_is_modified = true;
                    this.stop_trade_div_buy_cash_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_sell_cash") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_sell_cash' must be String: '"+value+"' is not." );
                    this.stop_trade_div_sell_cash = (String) value;
                    if (this.stop_trade_div_sell_cash_is_set)
                        this.stop_trade_div_sell_cash_is_modified = true;
                    this.stop_trade_div_sell_cash_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_long_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_long_margin' must be String: '"+value+"' is not." );
                    this.stop_trade_div_long_margin = (String) value;
                    if (this.stop_trade_div_long_margin_is_set)
                        this.stop_trade_div_long_margin_is_modified = true;
                    this.stop_trade_div_long_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_short_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_short_margin' must be String: '"+value+"' is not." );
                    this.stop_trade_div_short_margin = (String) value;
                    if (this.stop_trade_div_short_margin_is_set)
                        this.stop_trade_div_short_margin_is_modified = true;
                    this.stop_trade_div_short_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_long_close_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_long_close_margin' must be String: '"+value+"' is not." );
                    this.stop_div_long_close_margin = (String) value;
                    if (this.stop_div_long_close_margin_is_set)
                        this.stop_div_long_close_margin_is_modified = true;
                    this.stop_div_long_close_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_short_close_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_short_close_margin' must be String: '"+value+"' is not." );
                    this.stop_div_short_close_margin = (String) value;
                    if (this.stop_div_short_close_margin_is_set)
                        this.stop_div_short_close_margin_is_modified = true;
                    this.stop_div_short_close_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_long_swap_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_long_swap_margin' must be String: '"+value+"' is not." );
                    this.stop_div_long_swap_margin = (String) value;
                    if (this.stop_div_long_swap_margin_is_set)
                        this.stop_div_long_swap_margin_is_modified = true;
                    this.stop_div_long_swap_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_short_swap_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_short_swap_margin' must be String: '"+value+"' is not." );
                    this.stop_div_short_swap_margin = (String) value;
                    if (this.stop_div_short_swap_margin_is_set)
                        this.stop_div_short_swap_margin_is_modified = true;
                    this.stop_div_short_swap_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_buy_mini_stock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_buy_mini_stock' must be String: '"+value+"' is not." );
                    this.stop_div_buy_mini_stock = (String) value;
                    if (this.stop_div_buy_mini_stock_is_set)
                        this.stop_div_buy_mini_stock_is_modified = true;
                    this.stop_div_buy_mini_stock_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_sell_mini_stock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_sell_mini_stock' must be String: '"+value+"' is not." );
                    this.stop_div_sell_mini_stock = (String) value;
                    if (this.stop_div_sell_mini_stock_is_set)
                        this.stop_div_sell_mini_stock_is_modified = true;
                    this.stop_div_sell_mini_stock_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
