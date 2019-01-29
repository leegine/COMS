head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	IfoDepositParams.java;


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
 * ifo_deposit�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link IfoDepositRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link IfoDepositParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link IfoDepositParams}��{@@link IfoDepositRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDepositPK 
 * @@see IfoDepositRow 
 */
public class IfoDepositParams extends Params implements IfoDepositRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ifo_deposit";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = IfoDepositRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return IfoDepositRow.TYPE;
  }


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
   * <em>calc_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  calc_date;

  /** 
   * <em>mail_div</em>�J�����̒l 
   */
  public  String  mail_div;

  /** 
   * <em>ifo_deposit_req_amount</em>�J�����̒l 
   */
  public  Long  ifo_deposit_req_amount;

  /** 
   * <em>contract_profit_loss</em>�J�����̒l 
   */
  public  Long  contract_profit_loss;

  /** 
   * <em>future_settle_amount</em>�J�����̒l 
   */
  public  Long  future_settle_amount;

  /** 
   * <em>option_settle_amount</em>�J�����̒l 
   */
  public  Long  option_settle_amount;

  /** 
   * <em>ifo_deposit_paid_cash</em>�J�����̒l 
   */
  public  Long  ifo_deposit_paid_cash;

  /** 
   * <em>ifo_deposit_paid_securities</em>�J�����̒l 
   */
  public  Long  ifo_deposit_paid_securities;

  /** 
   * <em>net_amout_cash</em>�J�����̒l 
   */
  public  Long  net_amout_cash;

  /** 
   * <em>net_amount_securities</em>�J�����̒l 
   */
  public  Long  net_amount_securities;

  /** 
   * <em>delete_flag</em>�J�����̒l 
   */
  public  String  delete_flag;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean mail_div_is_set = false;
  private boolean mail_div_is_modified = false;
  private boolean calc_date_is_set = false;
  private boolean calc_date_is_modified = false;
  private boolean ifo_deposit_req_amount_is_set = false;
  private boolean ifo_deposit_req_amount_is_modified = false;
  private boolean contract_profit_loss_is_set = false;
  private boolean contract_profit_loss_is_modified = false;
  private boolean future_settle_amount_is_set = false;
  private boolean future_settle_amount_is_modified = false;
  private boolean option_settle_amount_is_set = false;
  private boolean option_settle_amount_is_modified = false;
  private boolean ifo_deposit_paid_cash_is_set = false;
  private boolean ifo_deposit_paid_cash_is_modified = false;
  private boolean ifo_deposit_paid_securities_is_set = false;
  private boolean ifo_deposit_paid_securities_is_modified = false;
  private boolean net_amout_cash_is_set = false;
  private boolean net_amout_cash_is_modified = false;
  private boolean net_amount_securities_is_set = false;
  private boolean net_amount_securities_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "calc_date=" + calc_date
      + "," + "mail_div=" +mail_div
      + "," + "ifo_deposit_req_amount=" +ifo_deposit_req_amount
      + "," + "contract_profit_loss=" +contract_profit_loss
      + "," + "future_settle_amount=" +future_settle_amount
      + "," + "option_settle_amount=" +option_settle_amount
      + "," + "ifo_deposit_paid_cash=" +ifo_deposit_paid_cash
      + "," + "ifo_deposit_paid_securities=" +ifo_deposit_paid_securities
      + "," + "net_amout_cash=" +net_amout_cash
      + "," + "net_amount_securities=" +net_amount_securities
      + "," + "delete_flag=" +delete_flag
      + "}";
  }


  /** 
   * �l�����ݒ��IfoDepositParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public IfoDepositParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
    calc_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���IfoDepositRow�I�u�W�F�N�g�̒l�𗘗p����IfoDepositParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����IfoDepositRow�I�u�W�F�N�g 
   */
  public IfoDepositParams( IfoDepositRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    calc_date = p_row.getCalcDate();
    calc_date_is_set = p_row.getCalcDateIsSet();
    calc_date_is_modified = p_row.getCalcDateIsModified();
    mail_div = p_row.getMailDiv();
    mail_div_is_set = p_row.getMailDivIsSet();
    mail_div_is_modified = p_row.getMailDivIsModified();
    if ( !p_row.getIfoDepositReqAmountIsNull() )
      ifo_deposit_req_amount = new Long( p_row.getIfoDepositReqAmount() );
    ifo_deposit_req_amount_is_set = p_row.getIfoDepositReqAmountIsSet();
    ifo_deposit_req_amount_is_modified = p_row.getIfoDepositReqAmountIsModified();
    if ( !p_row.getContractProfitLossIsNull() )
      contract_profit_loss = new Long( p_row.getContractProfitLoss() );
    contract_profit_loss_is_set = p_row.getContractProfitLossIsSet();
    contract_profit_loss_is_modified = p_row.getContractProfitLossIsModified();
    if ( !p_row.getFutureSettleAmountIsNull() )
      future_settle_amount = new Long( p_row.getFutureSettleAmount() );
    future_settle_amount_is_set = p_row.getFutureSettleAmountIsSet();
    future_settle_amount_is_modified = p_row.getFutureSettleAmountIsModified();
    if ( !p_row.getOptionSettleAmountIsNull() )
      option_settle_amount = new Long( p_row.getOptionSettleAmount() );
    option_settle_amount_is_set = p_row.getOptionSettleAmountIsSet();
    option_settle_amount_is_modified = p_row.getOptionSettleAmountIsModified();
    if ( !p_row.getIfoDepositPaidCashIsNull() )
      ifo_deposit_paid_cash = new Long( p_row.getIfoDepositPaidCash() );
    ifo_deposit_paid_cash_is_set = p_row.getIfoDepositPaidCashIsSet();
    ifo_deposit_paid_cash_is_modified = p_row.getIfoDepositPaidCashIsModified();
    if ( !p_row.getIfoDepositPaidSecuritiesIsNull() )
      ifo_deposit_paid_securities = new Long( p_row.getIfoDepositPaidSecurities() );
    ifo_deposit_paid_securities_is_set = p_row.getIfoDepositPaidSecuritiesIsSet();
    ifo_deposit_paid_securities_is_modified = p_row.getIfoDepositPaidSecuritiesIsModified();
    if ( !p_row.getNetAmoutCashIsNull() )
      net_amout_cash = new Long( p_row.getNetAmoutCash() );
    net_amout_cash_is_set = p_row.getNetAmoutCashIsSet();
    net_amout_cash_is_modified = p_row.getNetAmoutCashIsModified();
    if ( !p_row.getNetAmountSecuritiesIsNull() )
      net_amount_securities = new Long( p_row.getNetAmountSecurities() );
    net_amount_securities_is_set = p_row.getNetAmountSecuritiesIsSet();
    net_amount_securities_is_modified = p_row.getNetAmountSecuritiesIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      mail_div_is_set = true;
      mail_div_is_modified = true;
      ifo_deposit_req_amount_is_set = true;
      ifo_deposit_req_amount_is_modified = true;
      contract_profit_loss_is_set = true;
      contract_profit_loss_is_modified = true;
      future_settle_amount_is_set = true;
      future_settle_amount_is_modified = true;
      option_settle_amount_is_set = true;
      option_settle_amount_is_modified = true;
      ifo_deposit_paid_cash_is_set = true;
      ifo_deposit_paid_cash_is_modified = true;
      ifo_deposit_paid_securities_is_set = true;
      ifo_deposit_paid_securities_is_modified = true;
      net_amout_cash_is_set = true;
      net_amout_cash_is_modified = true;
      net_amount_securities_is_set = true;
      net_amount_securities_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof IfoDepositRow ) )
       return false;
    return fieldsEqual( (IfoDepositRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�IfoDepositRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( IfoDepositRow row )
  {
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
    if ( mail_div == null ) {
      if ( row.getMailDiv() != null )
        return false;
    } else if ( !mail_div.equals( row.getMailDiv() ) ) {
        return false;
    }
    if ( calc_date == null ) {
      if ( row.getCalcDate() != null )
        return false;
    } else if ( !calc_date.equals( row.getCalcDate() ) ) {
        return false;
    }
    if ( ifo_deposit_req_amount == null ) {
      if ( !row.getIfoDepositReqAmountIsNull() )
        return false;
    } else if ( row.getIfoDepositReqAmountIsNull() || ( ifo_deposit_req_amount.longValue() != row.getIfoDepositReqAmount() ) ) {
        return false;
    }
    if ( contract_profit_loss == null ) {
      if ( !row.getContractProfitLossIsNull() )
        return false;
    } else if ( row.getContractProfitLossIsNull() || ( contract_profit_loss.longValue() != row.getContractProfitLoss() ) ) {
        return false;
    }
    if ( future_settle_amount == null ) {
      if ( !row.getFutureSettleAmountIsNull() )
        return false;
    } else if ( row.getFutureSettleAmountIsNull() || ( future_settle_amount.longValue() != row.getFutureSettleAmount() ) ) {
        return false;
    }
    if ( option_settle_amount == null ) {
      if ( !row.getOptionSettleAmountIsNull() )
        return false;
    } else if ( row.getOptionSettleAmountIsNull() || ( option_settle_amount.longValue() != row.getOptionSettleAmount() ) ) {
        return false;
    }
    if ( ifo_deposit_paid_cash == null ) {
      if ( !row.getIfoDepositPaidCashIsNull() )
        return false;
    } else if ( row.getIfoDepositPaidCashIsNull() || ( ifo_deposit_paid_cash.longValue() != row.getIfoDepositPaidCash() ) ) {
        return false;
    }
    if ( ifo_deposit_paid_securities == null ) {
      if ( !row.getIfoDepositPaidSecuritiesIsNull() )
        return false;
    } else if ( row.getIfoDepositPaidSecuritiesIsNull() || ( ifo_deposit_paid_securities.longValue() != row.getIfoDepositPaidSecurities() ) ) {
        return false;
    }
    if ( net_amout_cash == null ) {
      if ( !row.getNetAmoutCashIsNull() )
        return false;
    } else if ( row.getNetAmoutCashIsNull() || ( net_amout_cash.longValue() != row.getNetAmoutCash() ) ) {
        return false;
    }
    if ( net_amount_securities == null ) {
      if ( !row.getNetAmountSecuritiesIsNull() )
        return false;
    } else if ( row.getNetAmountSecuritiesIsNull() || ( net_amount_securities.longValue() != row.getNetAmountSecurities() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (mail_div!=null? mail_div.hashCode(): 0) 
        + (calc_date!=null? calc_date.hashCode(): 0) 
        + (ifo_deposit_req_amount!=null? ifo_deposit_req_amount.hashCode(): 0) 
        + (contract_profit_loss!=null? contract_profit_loss.hashCode(): 0) 
        + (future_settle_amount!=null? future_settle_amount.hashCode(): 0) 
        + (option_settle_amount!=null? option_settle_amount.hashCode(): 0) 
        + (ifo_deposit_paid_cash!=null? ifo_deposit_paid_cash.hashCode(): 0) 
        + (ifo_deposit_paid_securities!=null? ifo_deposit_paid_securities.hashCode(): 0) 
        + (net_amout_cash!=null? net_amout_cash.hashCode(): 0) 
        + (net_amount_securities!=null? net_amount_securities.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
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
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( mail_div != null )
			map.put("mail_div",mail_div);
		map.put("calc_date",calc_date);
		if ( ifo_deposit_req_amount != null )
			map.put("ifo_deposit_req_amount",ifo_deposit_req_amount);
		if ( contract_profit_loss != null )
			map.put("contract_profit_loss",contract_profit_loss);
		if ( future_settle_amount != null )
			map.put("future_settle_amount",future_settle_amount);
		if ( option_settle_amount != null )
			map.put("option_settle_amount",option_settle_amount);
		if ( ifo_deposit_paid_cash != null )
			map.put("ifo_deposit_paid_cash",ifo_deposit_paid_cash);
		if ( ifo_deposit_paid_securities != null )
			map.put("ifo_deposit_paid_securities",ifo_deposit_paid_securities);
		if ( net_amout_cash != null )
			map.put("net_amout_cash",net_amout_cash);
		if ( net_amount_securities != null )
			map.put("net_amount_securities",net_amount_securities);
		if ( delete_flag != null )
			map.put("delete_flag",delete_flag);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( mail_div_is_modified )
			map.put("mail_div",mail_div);
		if ( ifo_deposit_req_amount_is_modified )
			map.put("ifo_deposit_req_amount",ifo_deposit_req_amount);
		if ( contract_profit_loss_is_modified )
			map.put("contract_profit_loss",contract_profit_loss);
		if ( future_settle_amount_is_modified )
			map.put("future_settle_amount",future_settle_amount);
		if ( option_settle_amount_is_modified )
			map.put("option_settle_amount",option_settle_amount);
		if ( ifo_deposit_paid_cash_is_modified )
			map.put("ifo_deposit_paid_cash",ifo_deposit_paid_cash);
		if ( ifo_deposit_paid_securities_is_modified )
			map.put("ifo_deposit_paid_securities",ifo_deposit_paid_securities);
		if ( net_amout_cash_is_modified )
			map.put("net_amout_cash",net_amout_cash);
		if ( net_amount_securities_is_modified )
			map.put("net_amount_securities",net_amount_securities);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if (map.size() == 0) {
			map.put("mail_div",mail_div);
			map.put("ifo_deposit_req_amount",ifo_deposit_req_amount);
			map.put("contract_profit_loss",contract_profit_loss);
			map.put("future_settle_amount",future_settle_amount);
			map.put("option_settle_amount",option_settle_amount);
			map.put("ifo_deposit_paid_cash",ifo_deposit_paid_cash);
			map.put("ifo_deposit_paid_securities",ifo_deposit_paid_securities);
			map.put("net_amout_cash",net_amout_cash);
			map.put("net_amount_securities",net_amount_securities);
			map.put("delete_flag",delete_flag);
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
   * <em>mail_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMailDiv()
  {
    return mail_div;
  }


  /** 
   * <em>mail_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailDivIsSet() {
    return mail_div_is_set;
  }


  /** 
   * <em>mail_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailDivIsModified() {
    return mail_div_is_modified;
  }


  /** 
   * <em>calc_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCalcDate()
  {
    return calc_date;
  }


  /** 
   * <em>calc_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcDateIsSet() {
    return calc_date_is_set;
  }


  /** 
   * <em>calc_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCalcDateIsModified() {
    return calc_date_is_modified;
  }


  /** 
   * <em>ifo_deposit_req_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getIfoDepositReqAmount()
  {
    return ( ifo_deposit_req_amount==null? ((long)0): ifo_deposit_req_amount.longValue() );
  }


  /** 
   * <em>ifo_deposit_req_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getIfoDepositReqAmountIsNull()
  {
    return ifo_deposit_req_amount == null;
  }


  /** 
   * <em>ifo_deposit_req_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositReqAmountIsSet() {
    return ifo_deposit_req_amount_is_set;
  }


  /** 
   * <em>ifo_deposit_req_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositReqAmountIsModified() {
    return ifo_deposit_req_amount_is_modified;
  }


  /** 
   * <em>contract_profit_loss</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getContractProfitLoss()
  {
    return ( contract_profit_loss==null? ((long)0): contract_profit_loss.longValue() );
  }


  /** 
   * <em>contract_profit_loss</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getContractProfitLossIsNull()
  {
    return contract_profit_loss == null;
  }


  /** 
   * <em>contract_profit_loss</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractProfitLossIsSet() {
    return contract_profit_loss_is_set;
  }


  /** 
   * <em>contract_profit_loss</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractProfitLossIsModified() {
    return contract_profit_loss_is_modified;
  }


  /** 
   * <em>future_settle_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getFutureSettleAmount()
  {
    return ( future_settle_amount==null? ((long)0): future_settle_amount.longValue() );
  }


  /** 
   * <em>future_settle_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getFutureSettleAmountIsNull()
  {
    return future_settle_amount == null;
  }


  /** 
   * <em>future_settle_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFutureSettleAmountIsSet() {
    return future_settle_amount_is_set;
  }


  /** 
   * <em>future_settle_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFutureSettleAmountIsModified() {
    return future_settle_amount_is_modified;
  }


  /** 
   * <em>option_settle_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOptionSettleAmount()
  {
    return ( option_settle_amount==null? ((long)0): option_settle_amount.longValue() );
  }


  /** 
   * <em>option_settle_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOptionSettleAmountIsNull()
  {
    return option_settle_amount == null;
  }


  /** 
   * <em>option_settle_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOptionSettleAmountIsSet() {
    return option_settle_amount_is_set;
  }


  /** 
   * <em>option_settle_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOptionSettleAmountIsModified() {
    return option_settle_amount_is_modified;
  }


  /** 
   * <em>ifo_deposit_paid_cash</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getIfoDepositPaidCash()
  {
    return ( ifo_deposit_paid_cash==null? ((long)0): ifo_deposit_paid_cash.longValue() );
  }


  /** 
   * <em>ifo_deposit_paid_cash</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getIfoDepositPaidCashIsNull()
  {
    return ifo_deposit_paid_cash == null;
  }


  /** 
   * <em>ifo_deposit_paid_cash</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPaidCashIsSet() {
    return ifo_deposit_paid_cash_is_set;
  }


  /** 
   * <em>ifo_deposit_paid_cash</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPaidCashIsModified() {
    return ifo_deposit_paid_cash_is_modified;
  }


  /** 
   * <em>ifo_deposit_paid_securities</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getIfoDepositPaidSecurities()
  {
    return ( ifo_deposit_paid_securities==null? ((long)0): ifo_deposit_paid_securities.longValue() );
  }


  /** 
   * <em>ifo_deposit_paid_securities</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getIfoDepositPaidSecuritiesIsNull()
  {
    return ifo_deposit_paid_securities == null;
  }


  /** 
   * <em>ifo_deposit_paid_securities</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPaidSecuritiesIsSet() {
    return ifo_deposit_paid_securities_is_set;
  }


  /** 
   * <em>ifo_deposit_paid_securities</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPaidSecuritiesIsModified() {
    return ifo_deposit_paid_securities_is_modified;
  }


  /** 
   * <em>net_amout_cash</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getNetAmoutCash()
  {
    return ( net_amout_cash==null? ((long)0): net_amout_cash.longValue() );
  }


  /** 
   * <em>net_amout_cash</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getNetAmoutCashIsNull()
  {
    return net_amout_cash == null;
  }


  /** 
   * <em>net_amout_cash</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmoutCashIsSet() {
    return net_amout_cash_is_set;
  }


  /** 
   * <em>net_amout_cash</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmoutCashIsModified() {
    return net_amout_cash_is_modified;
  }


  /** 
   * <em>net_amount_securities</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getNetAmountSecurities()
  {
    return ( net_amount_securities==null? ((long)0): net_amount_securities.longValue() );
  }


  /** 
   * <em>net_amount_securities</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getNetAmountSecuritiesIsNull()
  {
    return net_amount_securities == null;
  }


  /** 
   * <em>net_amount_securities</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmountSecuritiesIsSet() {
    return net_amount_securities_is_set;
  }


  /** 
   * <em>net_amount_securities</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmountSecuritiesIsModified() {
    return net_amount_securities_is_modified;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDeleteFlag()
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new IfoDepositPK(institution_code, branch_code, account_code, calc_date);
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
   * <em>mail_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailDiv <em>mail_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setMailDiv( String p_mailDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_div = p_mailDiv;
    mail_div_is_set = true;
    mail_div_is_modified = true;
  }


  /** 
   * <em>calc_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_calcDate <em>calc_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCalcDate( java.sql.Timestamp p_calcDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = p_calcDate;
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>calc_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCalcDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_req_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoDepositReqAmount <em>ifo_deposit_req_amount</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setIfoDepositReqAmount( long p_ifoDepositReqAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_req_amount = new Long(p_ifoDepositReqAmount);
    ifo_deposit_req_amount_is_set = true;
    ifo_deposit_req_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>ifo_deposit_req_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setIfoDepositReqAmount( Long p_ifoDepositReqAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_req_amount = p_ifoDepositReqAmount;
    ifo_deposit_req_amount_is_set = true;
    ifo_deposit_req_amount_is_modified = true;
  }


  /** 
   * <em>contract_profit_loss</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_contractProfitLoss <em>contract_profit_loss</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setContractProfitLoss( long p_contractProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_loss = new Long(p_contractProfitLoss);
    contract_profit_loss_is_set = true;
    contract_profit_loss_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>contract_profit_loss</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setContractProfitLoss( Long p_contractProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_loss = p_contractProfitLoss;
    contract_profit_loss_is_set = true;
    contract_profit_loss_is_modified = true;
  }


  /** 
   * <em>future_settle_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_futureSettleAmount <em>future_settle_amount</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setFutureSettleAmount( long p_futureSettleAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_settle_amount = new Long(p_futureSettleAmount);
    future_settle_amount_is_set = true;
    future_settle_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>future_settle_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setFutureSettleAmount( Long p_futureSettleAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    future_settle_amount = p_futureSettleAmount;
    future_settle_amount_is_set = true;
    future_settle_amount_is_modified = true;
  }


  /** 
   * <em>option_settle_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_optionSettleAmount <em>option_settle_amount</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setOptionSettleAmount( long p_optionSettleAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    option_settle_amount = new Long(p_optionSettleAmount);
    option_settle_amount_is_set = true;
    option_settle_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>option_settle_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOptionSettleAmount( Long p_optionSettleAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    option_settle_amount = p_optionSettleAmount;
    option_settle_amount_is_set = true;
    option_settle_amount_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_paid_cash</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoDepositPaidCash <em>ifo_deposit_paid_cash</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setIfoDepositPaidCash( long p_ifoDepositPaidCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_paid_cash = new Long(p_ifoDepositPaidCash);
    ifo_deposit_paid_cash_is_set = true;
    ifo_deposit_paid_cash_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>ifo_deposit_paid_cash</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setIfoDepositPaidCash( Long p_ifoDepositPaidCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_paid_cash = p_ifoDepositPaidCash;
    ifo_deposit_paid_cash_is_set = true;
    ifo_deposit_paid_cash_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_paid_securities</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoDepositPaidSecurities <em>ifo_deposit_paid_securities</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setIfoDepositPaidSecurities( long p_ifoDepositPaidSecurities )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_paid_securities = new Long(p_ifoDepositPaidSecurities);
    ifo_deposit_paid_securities_is_set = true;
    ifo_deposit_paid_securities_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>ifo_deposit_paid_securities</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setIfoDepositPaidSecurities( Long p_ifoDepositPaidSecurities )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_paid_securities = p_ifoDepositPaidSecurities;
    ifo_deposit_paid_securities_is_set = true;
    ifo_deposit_paid_securities_is_modified = true;
  }


  /** 
   * <em>net_amout_cash</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_netAmoutCash <em>net_amout_cash</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setNetAmoutCash( long p_netAmoutCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amout_cash = new Long(p_netAmoutCash);
    net_amout_cash_is_set = true;
    net_amout_cash_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>net_amout_cash</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setNetAmoutCash( Long p_netAmoutCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    net_amout_cash = p_netAmoutCash;
    net_amout_cash_is_set = true;
    net_amout_cash_is_modified = true;
  }


  /** 
   * <em>net_amount_securities</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_netAmountSecurities <em>net_amount_securities</em>�J�����̒l������킷12���ȉ���long�l 
   */
  public final void setNetAmountSecurities( long p_netAmountSecurities )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount_securities = new Long(p_netAmountSecurities);
    net_amount_securities_is_set = true;
    net_amount_securities_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>net_amount_securities</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setNetAmountSecurities( Long p_netAmountSecurities )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount_securities = p_netAmountSecurities;
    net_amount_securities_is_set = true;
    net_amount_securities_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deleteFlag <em>delete_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setDeleteFlag( String p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
                if ( name.equals("calc_date") ) {
                    return this.calc_date;
                }
                else if ( name.equals("contract_profit_loss") ) {
                    return this.contract_profit_loss;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'f':
                if ( name.equals("future_settle_amount") ) {
                    return this.future_settle_amount;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ifo_deposit_req_amount") ) {
                    return this.ifo_deposit_req_amount;
                }
                else if ( name.equals("ifo_deposit_paid_cash") ) {
                    return this.ifo_deposit_paid_cash;
                }
                else if ( name.equals("ifo_deposit_paid_securities") ) {
                    return this.ifo_deposit_paid_securities;
                }
                break;
            case 'm':
                if ( name.equals("mail_div") ) {
                    return this.mail_div;
                }
                break;
            case 'n':
                if ( name.equals("net_amout_cash") ) {
                    return this.net_amout_cash;
                }
                else if ( name.equals("net_amount_securities") ) {
                    return this.net_amount_securities;
                }
                break;
            case 'o':
                if ( name.equals("option_settle_amount") ) {
                    return this.option_settle_amount;
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
                if ( name.equals("calc_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'calc_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.calc_date = (java.sql.Timestamp) value;
                    if (this.calc_date_is_set)
                        this.calc_date_is_modified = true;
                    this.calc_date_is_set = true;
                    return;
                }
                else if ( name.equals("contract_profit_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_profit_loss' must be Long: '"+value+"' is not." );
                    this.contract_profit_loss = (Long) value;
                    if (this.contract_profit_loss_is_set)
                        this.contract_profit_loss_is_modified = true;
                    this.contract_profit_loss_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be String: '"+value+"' is not." );
                    this.delete_flag = (String) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_settle_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'future_settle_amount' must be Long: '"+value+"' is not." );
                    this.future_settle_amount = (Long) value;
                    if (this.future_settle_amount_is_set)
                        this.future_settle_amount_is_modified = true;
                    this.future_settle_amount_is_set = true;
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
                else if ( name.equals("ifo_deposit_req_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_req_amount' must be Long: '"+value+"' is not." );
                    this.ifo_deposit_req_amount = (Long) value;
                    if (this.ifo_deposit_req_amount_is_set)
                        this.ifo_deposit_req_amount_is_modified = true;
                    this.ifo_deposit_req_amount_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_paid_cash") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_paid_cash' must be Long: '"+value+"' is not." );
                    this.ifo_deposit_paid_cash = (Long) value;
                    if (this.ifo_deposit_paid_cash_is_set)
                        this.ifo_deposit_paid_cash_is_modified = true;
                    this.ifo_deposit_paid_cash_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_paid_securities") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_paid_securities' must be Long: '"+value+"' is not." );
                    this.ifo_deposit_paid_securities = (Long) value;
                    if (this.ifo_deposit_paid_securities_is_set)
                        this.ifo_deposit_paid_securities_is_modified = true;
                    this.ifo_deposit_paid_securities_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("mail_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mail_div' must be String: '"+value+"' is not." );
                    this.mail_div = (String) value;
                    if (this.mail_div_is_set)
                        this.mail_div_is_modified = true;
                    this.mail_div_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("net_amout_cash") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'net_amout_cash' must be Long: '"+value+"' is not." );
                    this.net_amout_cash = (Long) value;
                    if (this.net_amout_cash_is_set)
                        this.net_amout_cash_is_modified = true;
                    this.net_amout_cash_is_set = true;
                    return;
                }
                else if ( name.equals("net_amount_securities") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'net_amount_securities' must be Long: '"+value+"' is not." );
                    this.net_amount_securities = (Long) value;
                    if (this.net_amount_securities_is_set)
                        this.net_amount_securities_is_modified = true;
                    this.net_amount_securities_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("option_settle_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'option_settle_amount' must be Long: '"+value+"' is not." );
                    this.option_settle_amount = (Long) value;
                    if (this.option_settle_amount_is_set)
                        this.option_settle_amount_is_modified = true;
                    this.option_settle_amount_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
