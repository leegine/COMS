head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostForeignCashTransOrderParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * host_foreign_cash_trans_order�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link HostForeignCashTransOrderRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link HostForeignCashTransOrderParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link HostForeignCashTransOrderParams}��{@@link HostForeignCashTransOrderRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostForeignCashTransOrderPK 
 * @@see HostForeignCashTransOrderRow 
 */
public class HostForeignCashTransOrderParams extends Params implements HostForeignCashTransOrderRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "host_foreign_cash_trans_order";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = HostForeignCashTransOrderRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return HostForeignCashTransOrderRow.TYPE;
  }


  /** 
   * <em>request_code</em>�J�����̒l 
   */
  public  String  request_code;

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
   * <em>order_request_number</em>�J�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>trader_code</em>�J�����̒l 
   */
  public  String  trader_code;

  /** 
   * <em>currency_code</em>�J�����̒l 
   */
  public  String  currency_code;

  /** 
   * <em>order_div</em>�J�����̒l 
   */
  public  String  order_div;

  /** 
   * <em>debit_amount</em>�J�����̒l 
   */
  public  Double  debit_amount;

  /** 
   * <em>credit_amount</em>�J�����̒l 
   */
  public  Double  credit_amount;

  /** 
   * <em>remark_code</em>�J�����̒l 
   */
  public  String  remark_code;

  /** 
   * <em>debit_convert_amount</em>�J�����̒l 
   */
  public  Long  debit_convert_amount;

  /** 
   * <em>credit_convert_amount</em>�J�����̒l 
   */
  public  Long  credit_convert_amount;

  /** 
   * <em>remark_name</em>�J�����̒l 
   */
  public  String  remark_name;

  /** 
   * <em>cash_trans_date</em>�J�����̒l 
   */
  public  String  cash_trans_date;

  /** 
   * <em>credit_div</em>�J�����̒l 
   */
  public  String  credit_div;

  /** 
   * <em>force_div</em>�J�����̒l 
   */
  public  String  force_div;

  /** 
   * <em>cancel_div</em>�J�����̒l 
   */
  public  String  cancel_div;

  /** 
   * <em>cash_transfer_type</em>�J�����̒l 
   */
  public  String  cash_transfer_type;

  /** 
   * <em>sonar_code</em>�J�����̒l 
   */
  public  String  sonar_code;

  /** 
   * <em>rate</em>�J�����̒l 
   */
  public  String  rate;

  /** 
   * <em>ordered_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  ordered_timestamp;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean order_div_is_set = false;
  private boolean order_div_is_modified = false;
  private boolean debit_amount_is_set = false;
  private boolean debit_amount_is_modified = false;
  private boolean credit_amount_is_set = false;
  private boolean credit_amount_is_modified = false;
  private boolean remark_code_is_set = false;
  private boolean remark_code_is_modified = false;
  private boolean debit_convert_amount_is_set = false;
  private boolean debit_convert_amount_is_modified = false;
  private boolean credit_convert_amount_is_set = false;
  private boolean credit_convert_amount_is_modified = false;
  private boolean remark_name_is_set = false;
  private boolean remark_name_is_modified = false;
  private boolean cash_trans_date_is_set = false;
  private boolean cash_trans_date_is_modified = false;
  private boolean credit_div_is_set = false;
  private boolean credit_div_is_modified = false;
  private boolean force_div_is_set = false;
  private boolean force_div_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean cash_transfer_type_is_set = false;
  private boolean cash_transfer_type_is_modified = false;
  private boolean sonar_code_is_set = false;
  private boolean sonar_code_is_modified = false;
  private boolean rate_is_set = false;
  private boolean rate_is_modified = false;
  private boolean ordered_timestamp_is_set = false;
  private boolean ordered_timestamp_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "request_code=" + request_code
      + "," + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "order_request_number=" + order_request_number
      + "," + "trader_code=" +trader_code
      + "," + "currency_code=" +currency_code
      + "," + "order_div=" +order_div
      + "," + "debit_amount=" +debit_amount
      + "," + "credit_amount=" +credit_amount
      + "," + "remark_code=" +remark_code
      + "," + "debit_convert_amount=" +debit_convert_amount
      + "," + "credit_convert_amount=" +credit_convert_amount
      + "," + "remark_name=" +remark_name
      + "," + "cash_trans_date=" +cash_trans_date
      + "," + "credit_div=" +credit_div
      + "," + "force_div=" +force_div
      + "," + "cancel_div=" +cancel_div
      + "," + "cash_transfer_type=" +cash_transfer_type
      + "," + "sonar_code=" +sonar_code
      + "," + "rate=" +rate
      + "," + "ordered_timestamp=" +ordered_timestamp
      + "," + "status=" +status
      + "}";
  }


  /** 
   * �l�����ݒ��HostForeignCashTransOrderParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public HostForeignCashTransOrderParams() {
    request_code_is_modified = true;
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
    order_request_number_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���HostForeignCashTransOrderRow�I�u�W�F�N�g�̒l�𗘗p����HostForeignCashTransOrderParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����HostForeignCashTransOrderRow�I�u�W�F�N�g 
   */
  public HostForeignCashTransOrderParams( HostForeignCashTransOrderRow p_row )
  {
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    order_div = p_row.getOrderDiv();
    order_div_is_set = p_row.getOrderDivIsSet();
    order_div_is_modified = p_row.getOrderDivIsModified();
    if ( !p_row.getDebitAmountIsNull() )
      debit_amount = new Double( p_row.getDebitAmount() );
    debit_amount_is_set = p_row.getDebitAmountIsSet();
    debit_amount_is_modified = p_row.getDebitAmountIsModified();
    if ( !p_row.getCreditAmountIsNull() )
      credit_amount = new Double( p_row.getCreditAmount() );
    credit_amount_is_set = p_row.getCreditAmountIsSet();
    credit_amount_is_modified = p_row.getCreditAmountIsModified();
    remark_code = p_row.getRemarkCode();
    remark_code_is_set = p_row.getRemarkCodeIsSet();
    remark_code_is_modified = p_row.getRemarkCodeIsModified();
    if ( !p_row.getDebitConvertAmountIsNull() )
      debit_convert_amount = new Long( p_row.getDebitConvertAmount() );
    debit_convert_amount_is_set = p_row.getDebitConvertAmountIsSet();
    debit_convert_amount_is_modified = p_row.getDebitConvertAmountIsModified();
    if ( !p_row.getCreditConvertAmountIsNull() )
      credit_convert_amount = new Long( p_row.getCreditConvertAmount() );
    credit_convert_amount_is_set = p_row.getCreditConvertAmountIsSet();
    credit_convert_amount_is_modified = p_row.getCreditConvertAmountIsModified();
    remark_name = p_row.getRemarkName();
    remark_name_is_set = p_row.getRemarkNameIsSet();
    remark_name_is_modified = p_row.getRemarkNameIsModified();
    cash_trans_date = p_row.getCashTransDate();
    cash_trans_date_is_set = p_row.getCashTransDateIsSet();
    cash_trans_date_is_modified = p_row.getCashTransDateIsModified();
    credit_div = p_row.getCreditDiv();
    credit_div_is_set = p_row.getCreditDivIsSet();
    credit_div_is_modified = p_row.getCreditDivIsModified();
    force_div = p_row.getForceDiv();
    force_div_is_set = p_row.getForceDivIsSet();
    force_div_is_modified = p_row.getForceDivIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    cash_transfer_type = p_row.getCashTransferType();
    cash_transfer_type_is_set = p_row.getCashTransferTypeIsSet();
    cash_transfer_type_is_modified = p_row.getCashTransferTypeIsModified();
    sonar_code = p_row.getSonarCode();
    sonar_code_is_set = p_row.getSonarCodeIsSet();
    sonar_code_is_modified = p_row.getSonarCodeIsModified();
    rate = p_row.getRate();
    rate_is_set = p_row.getRateIsSet();
    rate_is_modified = p_row.getRateIsModified();
    ordered_timestamp = p_row.getOrderedTimestamp();
    ordered_timestamp_is_set = p_row.getOrderedTimestampIsSet();
    ordered_timestamp_is_modified = p_row.getOrderedTimestampIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      trader_code_is_set = true;
      trader_code_is_modified = true;
      currency_code_is_set = true;
      currency_code_is_modified = true;
      order_div_is_set = true;
      order_div_is_modified = true;
      debit_amount_is_set = true;
      debit_amount_is_modified = true;
      credit_amount_is_set = true;
      credit_amount_is_modified = true;
      remark_code_is_set = true;
      remark_code_is_modified = true;
      debit_convert_amount_is_set = true;
      debit_convert_amount_is_modified = true;
      credit_convert_amount_is_set = true;
      credit_convert_amount_is_modified = true;
      remark_name_is_set = true;
      remark_name_is_modified = true;
      cash_trans_date_is_set = true;
      cash_trans_date_is_modified = true;
      credit_div_is_set = true;
      credit_div_is_modified = true;
      force_div_is_set = true;
      force_div_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      cash_transfer_type_is_set = true;
      cash_transfer_type_is_modified = true;
      sonar_code_is_set = true;
      sonar_code_is_modified = true;
      rate_is_set = true;
      rate_is_modified = true;
      ordered_timestamp_is_set = true;
      ordered_timestamp_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostForeignCashTransOrderRow ) )
       return false;
    return fieldsEqual( (HostForeignCashTransOrderRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�HostForeignCashTransOrderRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( HostForeignCashTransOrderRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
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
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( order_div == null ) {
      if ( row.getOrderDiv() != null )
        return false;
    } else if ( !order_div.equals( row.getOrderDiv() ) ) {
        return false;
    }
    if ( debit_amount == null ) {
      if ( !row.getDebitAmountIsNull() )
        return false;
    } else if ( row.getDebitAmountIsNull() || ( debit_amount.doubleValue() != row.getDebitAmount() ) ) {
        return false;
    }
    if ( credit_amount == null ) {
      if ( !row.getCreditAmountIsNull() )
        return false;
    } else if ( row.getCreditAmountIsNull() || ( credit_amount.doubleValue() != row.getCreditAmount() ) ) {
        return false;
    }
    if ( remark_code == null ) {
      if ( row.getRemarkCode() != null )
        return false;
    } else if ( !remark_code.equals( row.getRemarkCode() ) ) {
        return false;
    }
    if ( debit_convert_amount == null ) {
      if ( !row.getDebitConvertAmountIsNull() )
        return false;
    } else if ( row.getDebitConvertAmountIsNull() || ( debit_convert_amount.longValue() != row.getDebitConvertAmount() ) ) {
        return false;
    }
    if ( credit_convert_amount == null ) {
      if ( !row.getCreditConvertAmountIsNull() )
        return false;
    } else if ( row.getCreditConvertAmountIsNull() || ( credit_convert_amount.longValue() != row.getCreditConvertAmount() ) ) {
        return false;
    }
    if ( remark_name == null ) {
      if ( row.getRemarkName() != null )
        return false;
    } else if ( !remark_name.equals( row.getRemarkName() ) ) {
        return false;
    }
    if ( cash_trans_date == null ) {
      if ( row.getCashTransDate() != null )
        return false;
    } else if ( !cash_trans_date.equals( row.getCashTransDate() ) ) {
        return false;
    }
    if ( credit_div == null ) {
      if ( row.getCreditDiv() != null )
        return false;
    } else if ( !credit_div.equals( row.getCreditDiv() ) ) {
        return false;
    }
    if ( force_div == null ) {
      if ( row.getForceDiv() != null )
        return false;
    } else if ( !force_div.equals( row.getForceDiv() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( cash_transfer_type == null ) {
      if ( row.getCashTransferType() != null )
        return false;
    } else if ( !cash_transfer_type.equals( row.getCashTransferType() ) ) {
        return false;
    }
    if ( sonar_code == null ) {
      if ( row.getSonarCode() != null )
        return false;
    } else if ( !sonar_code.equals( row.getSonarCode() ) ) {
        return false;
    }
    if ( rate == null ) {
      if ( row.getRate() != null )
        return false;
    } else if ( !rate.equals( row.getRate() ) ) {
        return false;
    }
    if ( ordered_timestamp == null ) {
      if ( row.getOrderedTimestamp() != null )
        return false;
    } else if ( !ordered_timestamp.equals( row.getOrderedTimestamp() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (order_div!=null? order_div.hashCode(): 0) 
        + (debit_amount!=null? debit_amount.hashCode(): 0) 
        + (credit_amount!=null? credit_amount.hashCode(): 0) 
        + (remark_code!=null? remark_code.hashCode(): 0) 
        + (debit_convert_amount!=null? debit_convert_amount.hashCode(): 0) 
        + (credit_convert_amount!=null? credit_convert_amount.hashCode(): 0) 
        + (remark_name!=null? remark_name.hashCode(): 0) 
        + (cash_trans_date!=null? cash_trans_date.hashCode(): 0) 
        + (credit_div!=null? credit_div.hashCode(): 0) 
        + (force_div!=null? force_div.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (cash_transfer_type!=null? cash_transfer_type.hashCode(): 0) 
        + (sonar_code!=null? sonar_code.hashCode(): 0) 
        + (rate!=null? rate.hashCode(): 0) 
        + (ordered_timestamp!=null? ordered_timestamp.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !currency_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'currency_code' must be set before inserting.");
		if ( !order_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_div' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("request_code",request_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("order_request_number",order_request_number);
		map.put("currency_code",currency_code);
		map.put("order_div",order_div);
		if ( debit_amount != null )
			map.put("debit_amount",debit_amount);
		if ( credit_amount != null )
			map.put("credit_amount",credit_amount);
		if ( remark_code != null )
			map.put("remark_code",remark_code);
		if ( debit_convert_amount != null )
			map.put("debit_convert_amount",debit_convert_amount);
		if ( credit_convert_amount != null )
			map.put("credit_convert_amount",credit_convert_amount);
		if ( remark_name != null )
			map.put("remark_name",remark_name);
		if ( cash_trans_date != null )
			map.put("cash_trans_date",cash_trans_date);
		if ( credit_div != null )
			map.put("credit_div",credit_div);
		if ( force_div != null )
			map.put("force_div",force_div);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		if ( cash_transfer_type != null )
			map.put("cash_transfer_type",cash_transfer_type);
		if ( sonar_code != null )
			map.put("sonar_code",sonar_code);
		if ( rate != null )
			map.put("rate",rate);
		if ( ordered_timestamp != null )
			map.put("ordered_timestamp",ordered_timestamp);
		map.put("status",status);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( order_div_is_modified )
			map.put("order_div",order_div);
		if ( debit_amount_is_modified )
			map.put("debit_amount",debit_amount);
		if ( credit_amount_is_modified )
			map.put("credit_amount",credit_amount);
		if ( remark_code_is_modified )
			map.put("remark_code",remark_code);
		if ( debit_convert_amount_is_modified )
			map.put("debit_convert_amount",debit_convert_amount);
		if ( credit_convert_amount_is_modified )
			map.put("credit_convert_amount",credit_convert_amount);
		if ( remark_name_is_modified )
			map.put("remark_name",remark_name);
		if ( cash_trans_date_is_modified )
			map.put("cash_trans_date",cash_trans_date);
		if ( credit_div_is_modified )
			map.put("credit_div",credit_div);
		if ( force_div_is_modified )
			map.put("force_div",force_div);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( cash_transfer_type_is_modified )
			map.put("cash_transfer_type",cash_transfer_type);
		if ( sonar_code_is_modified )
			map.put("sonar_code",sonar_code);
		if ( rate_is_modified )
			map.put("rate",rate);
		if ( ordered_timestamp_is_modified )
			map.put("ordered_timestamp",ordered_timestamp);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			if ( currency_code_is_set )
				map.put("currency_code",currency_code);
			if ( order_div_is_set )
				map.put("order_div",order_div);
			map.put("debit_amount",debit_amount);
			map.put("credit_amount",credit_amount);
			map.put("remark_code",remark_code);
			map.put("debit_convert_amount",debit_convert_amount);
			map.put("credit_convert_amount",credit_convert_amount);
			map.put("remark_name",remark_name);
			map.put("cash_trans_date",cash_trans_date);
			map.put("credit_div",credit_div);
			map.put("force_div",force_div);
			map.put("cancel_div",cancel_div);
			map.put("cash_transfer_type",cash_transfer_type);
			map.put("sonar_code",sonar_code);
			map.put("rate",rate);
			map.put("ordered_timestamp",ordered_timestamp);
			if ( status_is_set )
				map.put("status",status);
		}
		return map;
	}


  /** 
   * <em>request_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
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
   * <em>currency_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>order_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderDiv()
  {
    return order_div;
  }


  /** 
   * <em>order_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderDivIsSet() {
    return order_div_is_set;
  }


  /** 
   * <em>order_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderDivIsModified() {
    return order_div_is_modified;
  }


  /** 
   * <em>debit_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getDebitAmount()
  {
    return ( debit_amount==null? ((double)0): debit_amount.doubleValue() );
  }


  /** 
   * <em>debit_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getDebitAmountIsNull()
  {
    return debit_amount == null;
  }


  /** 
   * <em>debit_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDebitAmountIsSet() {
    return debit_amount_is_set;
  }


  /** 
   * <em>debit_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDebitAmountIsModified() {
    return debit_amount_is_modified;
  }


  /** 
   * <em>credit_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCreditAmount()
  {
    return ( credit_amount==null? ((double)0): credit_amount.doubleValue() );
  }


  /** 
   * <em>credit_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCreditAmountIsNull()
  {
    return credit_amount == null;
  }


  /** 
   * <em>credit_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreditAmountIsSet() {
    return credit_amount_is_set;
  }


  /** 
   * <em>credit_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreditAmountIsModified() {
    return credit_amount_is_modified;
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
   * <em>debit_convert_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getDebitConvertAmount()
  {
    return ( debit_convert_amount==null? ((long)0): debit_convert_amount.longValue() );
  }


  /** 
   * <em>debit_convert_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getDebitConvertAmountIsNull()
  {
    return debit_convert_amount == null;
  }


  /** 
   * <em>debit_convert_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDebitConvertAmountIsSet() {
    return debit_convert_amount_is_set;
  }


  /** 
   * <em>debit_convert_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDebitConvertAmountIsModified() {
    return debit_convert_amount_is_modified;
  }


  /** 
   * <em>credit_convert_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getCreditConvertAmount()
  {
    return ( credit_convert_amount==null? ((long)0): credit_convert_amount.longValue() );
  }


  /** 
   * <em>credit_convert_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCreditConvertAmountIsNull()
  {
    return credit_convert_amount == null;
  }


  /** 
   * <em>credit_convert_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreditConvertAmountIsSet() {
    return credit_convert_amount_is_set;
  }


  /** 
   * <em>credit_convert_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreditConvertAmountIsModified() {
    return credit_convert_amount_is_modified;
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
   * <em>cash_trans_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCashTransDate()
  {
    return cash_trans_date;
  }


  /** 
   * <em>cash_trans_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransDateIsSet() {
    return cash_trans_date_is_set;
  }


  /** 
   * <em>cash_trans_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransDateIsModified() {
    return cash_trans_date_is_modified;
  }


  /** 
   * <em>credit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCreditDiv()
  {
    return credit_div;
  }


  /** 
   * <em>credit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreditDivIsSet() {
    return credit_div_is_set;
  }


  /** 
   * <em>credit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreditDivIsModified() {
    return credit_div_is_modified;
  }


  /** 
   * <em>force_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getForceDiv()
  {
    return force_div;
  }


  /** 
   * <em>force_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForceDivIsSet() {
    return force_div_is_set;
  }


  /** 
   * <em>force_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForceDivIsModified() {
    return force_div_is_modified;
  }


  /** 
   * <em>cancel_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCancelDiv()
  {
    return cancel_div;
  }


  /** 
   * <em>cancel_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCancelDivIsSet() {
    return cancel_div_is_set;
  }


  /** 
   * <em>cancel_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCancelDivIsModified() {
    return cancel_div_is_modified;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCashTransferType()
  {
    return cash_transfer_type;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransferTypeIsSet() {
    return cash_transfer_type_is_set;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransferTypeIsModified() {
    return cash_transfer_type_is_modified;
  }


  /** 
   * <em>sonar_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSonarCode()
  {
    return sonar_code;
  }


  /** 
   * <em>sonar_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarCodeIsSet() {
    return sonar_code_is_set;
  }


  /** 
   * <em>sonar_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarCodeIsModified() {
    return sonar_code_is_modified;
  }


  /** 
   * <em>rate</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRate()
  {
    return rate;
  }


  /** 
   * <em>rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRateIsSet() {
    return rate_is_set;
  }


  /** 
   * <em>rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRateIsModified() {
    return rate_is_modified;
  }


  /** 
   * <em>ordered_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOrderedTimestamp()
  {
    return ordered_timestamp;
  }


  /** 
   * <em>ordered_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderedTimestampIsSet() {
    return ordered_timestamp_is_set;
  }


  /** 
   * <em>ordered_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderedTimestampIsModified() {
    return ordered_timestamp_is_modified;
  }


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostForeignCashTransOrderPK(request_code, institution_code, branch_code, account_code, order_request_number);
  }


  /** 
   * <em>request_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_requestCode <em>request_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
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
   * <em>currency_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currencyCode <em>currency_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>order_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderDiv <em>order_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderDiv( String p_orderDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_div = p_orderDiv;
    order_div_is_set = true;
    order_div_is_modified = true;
  }


  /** 
   * <em>debit_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_debitAmount <em>debit_amount</em>�J�����̒l������킷13���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setDebitAmount( double p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = new Double(p_debitAmount);
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>debit_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setDebitAmount( Double p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = p_debitAmount;
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * <em>credit_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_creditAmount <em>credit_amount</em>�J�����̒l������킷13���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setCreditAmount( double p_creditAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_amount = new Double(p_creditAmount);
    credit_amount_is_set = true;
    credit_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>credit_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCreditAmount( Double p_creditAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    credit_amount = p_creditAmount;
    credit_amount_is_set = true;
    credit_amount_is_modified = true;
  }


  /** 
   * <em>remark_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_remarkCode <em>remark_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setRemarkCode( String p_remarkCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_code = p_remarkCode;
    remark_code_is_set = true;
    remark_code_is_modified = true;
  }


  /** 
   * <em>debit_convert_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_debitConvertAmount <em>debit_convert_amount</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setDebitConvertAmount( long p_debitConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_convert_amount = new Long(p_debitConvertAmount);
    debit_convert_amount_is_set = true;
    debit_convert_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>debit_convert_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setDebitConvertAmount( Long p_debitConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    debit_convert_amount = p_debitConvertAmount;
    debit_convert_amount_is_set = true;
    debit_convert_amount_is_modified = true;
  }


  /** 
   * <em>credit_convert_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_creditConvertAmount <em>credit_convert_amount</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setCreditConvertAmount( long p_creditConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_convert_amount = new Long(p_creditConvertAmount);
    credit_convert_amount_is_set = true;
    credit_convert_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>credit_convert_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCreditConvertAmount( Long p_creditConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    credit_convert_amount = p_creditConvertAmount;
    credit_convert_amount_is_set = true;
    credit_convert_amount_is_modified = true;
  }


  /** 
   * <em>remark_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_remarkName <em>remark_name</em>�J�����̒l������킷23���ȉ���String�l 
   */
  public final void setRemarkName( String p_remarkName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_name = p_remarkName;
    remark_name_is_set = true;
    remark_name_is_modified = true;
  }


  /** 
   * <em>cash_trans_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashTransDate <em>cash_trans_date</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setCashTransDate( String p_cashTransDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_trans_date = p_cashTransDate;
    cash_trans_date_is_set = true;
    cash_trans_date_is_modified = true;
  }


  /** 
   * <em>credit_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_creditDiv <em>credit_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCreditDiv( String p_creditDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_div = p_creditDiv;
    credit_div_is_set = true;
    credit_div_is_modified = true;
  }


  /** 
   * <em>force_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_forceDiv <em>force_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setForceDiv( String p_forceDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    force_div = p_forceDiv;
    force_div_is_set = true;
    force_div_is_modified = true;
  }


  /** 
   * <em>cancel_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cancelDiv <em>cancel_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCancelDiv( String p_cancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_div = p_cancelDiv;
    cancel_div_is_set = true;
    cancel_div_is_modified = true;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashTransferType <em>cash_transfer_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCashTransferType( String p_cashTransferType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_transfer_type = p_cashTransferType;
    cash_transfer_type_is_set = true;
    cash_transfer_type_is_modified = true;
  }


  /** 
   * <em>sonar_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sonarCode <em>sonar_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setSonarCode( String p_sonarCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_code = p_sonarCode;
    sonar_code_is_set = true;
    sonar_code_is_modified = true;
  }


  /** 
   * <em>rate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rate <em>rate</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setRate( String p_rate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rate = p_rate;
    rate_is_set = true;
    rate_is_modified = true;
  }


  /** 
   * <em>ordered_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderedTimestamp <em>ordered_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOrderedTimestamp( java.sql.Timestamp p_orderedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ordered_timestamp = p_orderedTimestamp;
    ordered_timestamp_is_set = true;
    ordered_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>ordered_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOrderedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ordered_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ordered_timestamp_is_set = true;
    ordered_timestamp_is_modified = true;
  }


  /** 
   * <em>status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_status <em>status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
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
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("credit_amount") ) {
                    return this.credit_amount;
                }
                else if ( name.equals("credit_convert_amount") ) {
                    return this.credit_convert_amount;
                }
                else if ( name.equals("cash_trans_date") ) {
                    return this.cash_trans_date;
                }
                else if ( name.equals("credit_div") ) {
                    return this.credit_div;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                else if ( name.equals("cash_transfer_type") ) {
                    return this.cash_transfer_type;
                }
                break;
            case 'd':
                if ( name.equals("debit_amount") ) {
                    return this.debit_amount;
                }
                else if ( name.equals("debit_convert_amount") ) {
                    return this.debit_convert_amount;
                }
                break;
            case 'f':
                if ( name.equals("force_div") ) {
                    return this.force_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_div") ) {
                    return this.order_div;
                }
                else if ( name.equals("ordered_timestamp") ) {
                    return this.ordered_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("remark_code") ) {
                    return this.remark_code;
                }
                else if ( name.equals("remark_name") ) {
                    return this.remark_name;
                }
                else if ( name.equals("rate") ) {
                    return this.rate;
                }
                break;
            case 's':
                if ( name.equals("sonar_code") ) {
                    return this.sonar_code;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
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
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("credit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'credit_amount' must be Double: '"+value+"' is not." );
                    this.credit_amount = (Double) value;
                    if (this.credit_amount_is_set)
                        this.credit_amount_is_modified = true;
                    this.credit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("credit_convert_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'credit_convert_amount' must be Long: '"+value+"' is not." );
                    this.credit_convert_amount = (Long) value;
                    if (this.credit_convert_amount_is_set)
                        this.credit_convert_amount_is_modified = true;
                    this.credit_convert_amount_is_set = true;
                    return;
                }
                else if ( name.equals("cash_trans_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_trans_date' must be String: '"+value+"' is not." );
                    this.cash_trans_date = (String) value;
                    if (this.cash_trans_date_is_set)
                        this.cash_trans_date_is_modified = true;
                    this.cash_trans_date_is_set = true;
                    return;
                }
                else if ( name.equals("credit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'credit_div' must be String: '"+value+"' is not." );
                    this.credit_div = (String) value;
                    if (this.credit_div_is_set)
                        this.credit_div_is_modified = true;
                    this.credit_div_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_div' must be String: '"+value+"' is not." );
                    this.cancel_div = (String) value;
                    if (this.cancel_div_is_set)
                        this.cancel_div_is_modified = true;
                    this.cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("cash_transfer_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_transfer_type' must be String: '"+value+"' is not." );
                    this.cash_transfer_type = (String) value;
                    if (this.cash_transfer_type_is_set)
                        this.cash_transfer_type_is_modified = true;
                    this.cash_transfer_type_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("debit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount' must be Double: '"+value+"' is not." );
                    this.debit_amount = (Double) value;
                    if (this.debit_amount_is_set)
                        this.debit_amount_is_modified = true;
                    this.debit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("debit_convert_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'debit_convert_amount' must be Long: '"+value+"' is not." );
                    this.debit_convert_amount = (Long) value;
                    if (this.debit_convert_amount_is_set)
                        this.debit_convert_amount_is_modified = true;
                    this.debit_convert_amount_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("force_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'force_div' must be String: '"+value+"' is not." );
                    this.force_div = (String) value;
                    if (this.force_div_is_set)
                        this.force_div_is_modified = true;
                    this.force_div_is_set = true;
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
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("order_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_div' must be String: '"+value+"' is not." );
                    this.order_div = (String) value;
                    if (this.order_div_is_set)
                        this.order_div_is_modified = true;
                    this.order_div_is_set = true;
                    return;
                }
                else if ( name.equals("ordered_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ordered_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ordered_timestamp = (java.sql.Timestamp) value;
                    if (this.ordered_timestamp_is_set)
                        this.ordered_timestamp_is_modified = true;
                    this.ordered_timestamp_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("remark_code") ) {
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
                else if ( name.equals("rate") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rate' must be String: '"+value+"' is not." );
                    this.rate = (String) value;
                    if (this.rate_is_set)
                        this.rate_is_modified = true;
                    this.rate_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_code' must be String: '"+value+"' is not." );
                    this.sonar_code = (String) value;
                    if (this.sonar_code_is_set)
                        this.sonar_code_is_modified = true;
                    this.sonar_code_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
