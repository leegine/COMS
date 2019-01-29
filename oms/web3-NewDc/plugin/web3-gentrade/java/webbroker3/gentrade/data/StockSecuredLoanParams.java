head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.26.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	StockSecuredLoanParams.java;


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
 * stock_secured_loan�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link StockSecuredLoanRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link StockSecuredLoanParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link StockSecuredLoanParams}��{@@link StockSecuredLoanRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see StockSecuredLoanPK 
 * @@see StockSecuredLoanRow 
 */
public class StockSecuredLoanParams extends Params implements StockSecuredLoanRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "stock_secured_loan";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = StockSecuredLoanRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return StockSecuredLoanRow.TYPE;
  }


  /** 
   * <em>stock_loan_account_code</em>�J�����̒l 
   */
  public  String  stock_loan_account_code;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

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
   * <em>account_open_status</em>�J�����̒l 
   */
  public  String  account_open_status;

  /** 
   * <em>appli_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_date;

  /** 
   * <em>account_open_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  account_open_date;

  /** 
   * <em>order_data_reception_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  order_data_reception_date;

  /** 
   * <em>cancel_data_reception_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  cancel_data_reception_date;

  /** 
   * <em>close_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  close_date;

  /** 
   * <em>y_customer_data</em>�J�����̒l 
   */
  public  String  y_customer_data;

  /** 
   * <em>examin_lock_flag</em>�J�����̒l 
   */
  public  String  examin_lock_flag;

  /** 
   * <em>branch_lock</em>�J�����̒l 
   */
  public  String  branch_lock;

  /** 
   * <em>mng_lock_flag</em>�J�����̒l 
   */
  public  String  mng_lock_flag;

  /** 
   * <em>mng_lock_flag_advance</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_advance;

  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_unpay_margin;

  /** 
   * <em>mng_lock_flag_short_security</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_short_security;

  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_unsubstit_depo;

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

  private boolean stock_loan_account_code_is_set = false;
  private boolean stock_loan_account_code_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean account_open_status_is_set = false;
  private boolean account_open_status_is_modified = false;
  private boolean appli_date_is_set = false;
  private boolean appli_date_is_modified = false;
  private boolean account_open_date_is_set = false;
  private boolean account_open_date_is_modified = false;
  private boolean order_data_reception_date_is_set = false;
  private boolean order_data_reception_date_is_modified = false;
  private boolean cancel_data_reception_date_is_set = false;
  private boolean cancel_data_reception_date_is_modified = false;
  private boolean close_date_is_set = false;
  private boolean close_date_is_modified = false;
  private boolean y_customer_data_is_set = false;
  private boolean y_customer_data_is_modified = false;
  private boolean examin_lock_flag_is_set = false;
  private boolean examin_lock_flag_is_modified = false;
  private boolean branch_lock_is_set = false;
  private boolean branch_lock_is_modified = false;
  private boolean mng_lock_flag_is_set = false;
  private boolean mng_lock_flag_is_modified = false;
  private boolean mng_lock_flag_advance_is_set = false;
  private boolean mng_lock_flag_advance_is_modified = false;
  private boolean mng_lock_flag_unpay_margin_is_set = false;
  private boolean mng_lock_flag_unpay_margin_is_modified = false;
  private boolean mng_lock_flag_short_security_is_set = false;
  private boolean mng_lock_flag_short_security_is_modified = false;
  private boolean mng_lock_flag_unsubstit_depo_is_set = false;
  private boolean mng_lock_flag_unsubstit_depo_is_modified = false;
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
       + "stock_loan_account_code=" + stock_loan_account_code
      + "," + "account_id=" +account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "account_open_status=" +account_open_status
      + "," + "appli_date=" +appli_date
      + "," + "account_open_date=" +account_open_date
      + "," + "order_data_reception_date=" +order_data_reception_date
      + "," + "cancel_data_reception_date=" +cancel_data_reception_date
      + "," + "close_date=" +close_date
      + "," + "y_customer_data=" +y_customer_data
      + "," + "examin_lock_flag=" +examin_lock_flag
      + "," + "branch_lock=" +branch_lock
      + "," + "mng_lock_flag=" +mng_lock_flag
      + "," + "mng_lock_flag_advance=" +mng_lock_flag_advance
      + "," + "mng_lock_flag_unpay_margin=" +mng_lock_flag_unpay_margin
      + "," + "mng_lock_flag_short_security=" +mng_lock_flag_short_security
      + "," + "mng_lock_flag_unsubstit_depo=" +mng_lock_flag_unsubstit_depo
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��StockSecuredLoanParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public StockSecuredLoanParams() {
    stock_loan_account_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���StockSecuredLoanRow�I�u�W�F�N�g�̒l�𗘗p����StockSecuredLoanParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����StockSecuredLoanRow�I�u�W�F�N�g 
   */
  public StockSecuredLoanParams( StockSecuredLoanRow p_row )
  {
    stock_loan_account_code = p_row.getStockLoanAccountCode();
    stock_loan_account_code_is_set = p_row.getStockLoanAccountCodeIsSet();
    stock_loan_account_code_is_modified = p_row.getStockLoanAccountCodeIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    account_open_status = p_row.getAccountOpenStatus();
    account_open_status_is_set = p_row.getAccountOpenStatusIsSet();
    account_open_status_is_modified = p_row.getAccountOpenStatusIsModified();
    appli_date = p_row.getAppliDate();
    appli_date_is_set = p_row.getAppliDateIsSet();
    appli_date_is_modified = p_row.getAppliDateIsModified();
    account_open_date = p_row.getAccountOpenDate();
    account_open_date_is_set = p_row.getAccountOpenDateIsSet();
    account_open_date_is_modified = p_row.getAccountOpenDateIsModified();
    order_data_reception_date = p_row.getOrderDataReceptionDate();
    order_data_reception_date_is_set = p_row.getOrderDataReceptionDateIsSet();
    order_data_reception_date_is_modified = p_row.getOrderDataReceptionDateIsModified();
    cancel_data_reception_date = p_row.getCancelDataReceptionDate();
    cancel_data_reception_date_is_set = p_row.getCancelDataReceptionDateIsSet();
    cancel_data_reception_date_is_modified = p_row.getCancelDataReceptionDateIsModified();
    close_date = p_row.getCloseDate();
    close_date_is_set = p_row.getCloseDateIsSet();
    close_date_is_modified = p_row.getCloseDateIsModified();
    y_customer_data = p_row.getYCustomerData();
    y_customer_data_is_set = p_row.getYCustomerDataIsSet();
    y_customer_data_is_modified = p_row.getYCustomerDataIsModified();
    examin_lock_flag = p_row.getExaminLockFlag();
    examin_lock_flag_is_set = p_row.getExaminLockFlagIsSet();
    examin_lock_flag_is_modified = p_row.getExaminLockFlagIsModified();
    branch_lock = p_row.getBranchLock();
    branch_lock_is_set = p_row.getBranchLockIsSet();
    branch_lock_is_modified = p_row.getBranchLockIsModified();
    mng_lock_flag = p_row.getMngLockFlag();
    mng_lock_flag_is_set = p_row.getMngLockFlagIsSet();
    mng_lock_flag_is_modified = p_row.getMngLockFlagIsModified();
    mng_lock_flag_advance = p_row.getMngLockFlagAdvance();
    mng_lock_flag_advance_is_set = p_row.getMngLockFlagAdvanceIsSet();
    mng_lock_flag_advance_is_modified = p_row.getMngLockFlagAdvanceIsModified();
    mng_lock_flag_unpay_margin = p_row.getMngLockFlagUnpayMargin();
    mng_lock_flag_unpay_margin_is_set = p_row.getMngLockFlagUnpayMarginIsSet();
    mng_lock_flag_unpay_margin_is_modified = p_row.getMngLockFlagUnpayMarginIsModified();
    mng_lock_flag_short_security = p_row.getMngLockFlagShortSecurity();
    mng_lock_flag_short_security_is_set = p_row.getMngLockFlagShortSecurityIsSet();
    mng_lock_flag_short_security_is_modified = p_row.getMngLockFlagShortSecurityIsModified();
    mng_lock_flag_unsubstit_depo = p_row.getMngLockFlagUnsubstitDepo();
    mng_lock_flag_unsubstit_depo_is_set = p_row.getMngLockFlagUnsubstitDepoIsSet();
    mng_lock_flag_unsubstit_depo_is_modified = p_row.getMngLockFlagUnsubstitDepoIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      account_open_status_is_set = true;
      account_open_status_is_modified = true;
      appli_date_is_set = true;
      appli_date_is_modified = true;
      account_open_date_is_set = true;
      account_open_date_is_modified = true;
      order_data_reception_date_is_set = true;
      order_data_reception_date_is_modified = true;
      cancel_data_reception_date_is_set = true;
      cancel_data_reception_date_is_modified = true;
      close_date_is_set = true;
      close_date_is_modified = true;
      y_customer_data_is_set = true;
      y_customer_data_is_modified = true;
      examin_lock_flag_is_set = true;
      examin_lock_flag_is_modified = true;
      branch_lock_is_set = true;
      branch_lock_is_modified = true;
      mng_lock_flag_is_set = true;
      mng_lock_flag_is_modified = true;
      mng_lock_flag_advance_is_set = true;
      mng_lock_flag_advance_is_modified = true;
      mng_lock_flag_unpay_margin_is_set = true;
      mng_lock_flag_unpay_margin_is_modified = true;
      mng_lock_flag_short_security_is_set = true;
      mng_lock_flag_short_security_is_modified = true;
      mng_lock_flag_unsubstit_depo_is_set = true;
      mng_lock_flag_unsubstit_depo_is_modified = true;
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
    if ( !( other instanceof StockSecuredLoanRow ) )
       return false;
    return fieldsEqual( (StockSecuredLoanRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�StockSecuredLoanRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( StockSecuredLoanRow row )
  {
    if ( stock_loan_account_code == null ) {
      if ( row.getStockLoanAccountCode() != null )
        return false;
    } else if ( !stock_loan_account_code.equals( row.getStockLoanAccountCode() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
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
    if ( account_open_status == null ) {
      if ( row.getAccountOpenStatus() != null )
        return false;
    } else if ( !account_open_status.equals( row.getAccountOpenStatus() ) ) {
        return false;
    }
    if ( appli_date == null ) {
      if ( row.getAppliDate() != null )
        return false;
    } else if ( !appli_date.equals( row.getAppliDate() ) ) {
        return false;
    }
    if ( account_open_date == null ) {
      if ( row.getAccountOpenDate() != null )
        return false;
    } else if ( !account_open_date.equals( row.getAccountOpenDate() ) ) {
        return false;
    }
    if ( order_data_reception_date == null ) {
      if ( row.getOrderDataReceptionDate() != null )
        return false;
    } else if ( !order_data_reception_date.equals( row.getOrderDataReceptionDate() ) ) {
        return false;
    }
    if ( cancel_data_reception_date == null ) {
      if ( row.getCancelDataReceptionDate() != null )
        return false;
    } else if ( !cancel_data_reception_date.equals( row.getCancelDataReceptionDate() ) ) {
        return false;
    }
    if ( close_date == null ) {
      if ( row.getCloseDate() != null )
        return false;
    } else if ( !close_date.equals( row.getCloseDate() ) ) {
        return false;
    }
    if ( y_customer_data == null ) {
      if ( row.getYCustomerData() != null )
        return false;
    } else if ( !y_customer_data.equals( row.getYCustomerData() ) ) {
        return false;
    }
    if ( examin_lock_flag == null ) {
      if ( row.getExaminLockFlag() != null )
        return false;
    } else if ( !examin_lock_flag.equals( row.getExaminLockFlag() ) ) {
        return false;
    }
    if ( branch_lock == null ) {
      if ( row.getBranchLock() != null )
        return false;
    } else if ( !branch_lock.equals( row.getBranchLock() ) ) {
        return false;
    }
    if ( mng_lock_flag == null ) {
      if ( row.getMngLockFlag() != null )
        return false;
    } else if ( !mng_lock_flag.equals( row.getMngLockFlag() ) ) {
        return false;
    }
    if ( mng_lock_flag_advance == null ) {
      if ( row.getMngLockFlagAdvance() != null )
        return false;
    } else if ( !mng_lock_flag_advance.equals( row.getMngLockFlagAdvance() ) ) {
        return false;
    }
    if ( mng_lock_flag_unpay_margin == null ) {
      if ( row.getMngLockFlagUnpayMargin() != null )
        return false;
    } else if ( !mng_lock_flag_unpay_margin.equals( row.getMngLockFlagUnpayMargin() ) ) {
        return false;
    }
    if ( mng_lock_flag_short_security == null ) {
      if ( row.getMngLockFlagShortSecurity() != null )
        return false;
    } else if ( !mng_lock_flag_short_security.equals( row.getMngLockFlagShortSecurity() ) ) {
        return false;
    }
    if ( mng_lock_flag_unsubstit_depo == null ) {
      if ( row.getMngLockFlagUnsubstitDepo() != null )
        return false;
    } else if ( !mng_lock_flag_unsubstit_depo.equals( row.getMngLockFlagUnsubstitDepo() ) ) {
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
      return  (stock_loan_account_code!=null? stock_loan_account_code.hashCode(): 0) 
        + ((int) account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (account_open_status!=null? account_open_status.hashCode(): 0) 
        + (appli_date!=null? appli_date.hashCode(): 0) 
        + (account_open_date!=null? account_open_date.hashCode(): 0) 
        + (order_data_reception_date!=null? order_data_reception_date.hashCode(): 0) 
        + (cancel_data_reception_date!=null? cancel_data_reception_date.hashCode(): 0) 
        + (close_date!=null? close_date.hashCode(): 0) 
        + (y_customer_data!=null? y_customer_data.hashCode(): 0) 
        + (examin_lock_flag!=null? examin_lock_flag.hashCode(): 0) 
        + (branch_lock!=null? branch_lock.hashCode(): 0) 
        + (mng_lock_flag!=null? mng_lock_flag.hashCode(): 0) 
        + (mng_lock_flag_advance!=null? mng_lock_flag_advance.hashCode(): 0) 
        + (mng_lock_flag_unpay_margin!=null? mng_lock_flag_unpay_margin.hashCode(): 0) 
        + (mng_lock_flag_short_security!=null? mng_lock_flag_short_security.hashCode(): 0) 
        + (mng_lock_flag_unsubstit_depo!=null? mng_lock_flag_unsubstit_depo.hashCode(): 0) 
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
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("stock_loan_account_code",stock_loan_account_code);
		map.put("account_id",new Long(account_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( account_open_status != null )
			map.put("account_open_status",account_open_status);
		if ( appli_date != null )
			map.put("appli_date",appli_date);
		if ( account_open_date != null )
			map.put("account_open_date",account_open_date);
		if ( order_data_reception_date != null )
			map.put("order_data_reception_date",order_data_reception_date);
		if ( cancel_data_reception_date != null )
			map.put("cancel_data_reception_date",cancel_data_reception_date);
		if ( close_date != null )
			map.put("close_date",close_date);
		if ( y_customer_data != null )
			map.put("y_customer_data",y_customer_data);
		if ( examin_lock_flag != null )
			map.put("examin_lock_flag",examin_lock_flag);
		if ( branch_lock != null )
			map.put("branch_lock",branch_lock);
		if ( mng_lock_flag != null )
			map.put("mng_lock_flag",mng_lock_flag);
		if ( mng_lock_flag_advance != null )
			map.put("mng_lock_flag_advance",mng_lock_flag_advance);
		if ( mng_lock_flag_unpay_margin != null )
			map.put("mng_lock_flag_unpay_margin",mng_lock_flag_unpay_margin);
		if ( mng_lock_flag_short_security != null )
			map.put("mng_lock_flag_short_security",mng_lock_flag_short_security);
		if ( mng_lock_flag_unsubstit_depo != null )
			map.put("mng_lock_flag_unsubstit_depo",mng_lock_flag_unsubstit_depo);
		if ( last_updater != null )
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
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( account_open_status_is_modified )
			map.put("account_open_status",account_open_status);
		if ( appli_date_is_modified )
			map.put("appli_date",appli_date);
		if ( account_open_date_is_modified )
			map.put("account_open_date",account_open_date);
		if ( order_data_reception_date_is_modified )
			map.put("order_data_reception_date",order_data_reception_date);
		if ( cancel_data_reception_date_is_modified )
			map.put("cancel_data_reception_date",cancel_data_reception_date);
		if ( close_date_is_modified )
			map.put("close_date",close_date);
		if ( y_customer_data_is_modified )
			map.put("y_customer_data",y_customer_data);
		if ( examin_lock_flag_is_modified )
			map.put("examin_lock_flag",examin_lock_flag);
		if ( branch_lock_is_modified )
			map.put("branch_lock",branch_lock);
		if ( mng_lock_flag_is_modified )
			map.put("mng_lock_flag",mng_lock_flag);
		if ( mng_lock_flag_advance_is_modified )
			map.put("mng_lock_flag_advance",mng_lock_flag_advance);
		if ( mng_lock_flag_unpay_margin_is_modified )
			map.put("mng_lock_flag_unpay_margin",mng_lock_flag_unpay_margin);
		if ( mng_lock_flag_short_security_is_modified )
			map.put("mng_lock_flag_short_security",mng_lock_flag_short_security);
		if ( mng_lock_flag_unsubstit_depo_is_modified )
			map.put("mng_lock_flag_unsubstit_depo",mng_lock_flag_unsubstit_depo);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("account_open_status",account_open_status);
			map.put("appli_date",appli_date);
			map.put("account_open_date",account_open_date);
			map.put("order_data_reception_date",order_data_reception_date);
			map.put("cancel_data_reception_date",cancel_data_reception_date);
			map.put("close_date",close_date);
			map.put("y_customer_data",y_customer_data);
			map.put("examin_lock_flag",examin_lock_flag);
			map.put("branch_lock",branch_lock);
			map.put("mng_lock_flag",mng_lock_flag);
			map.put("mng_lock_flag_advance",mng_lock_flag_advance);
			map.put("mng_lock_flag_unpay_margin",mng_lock_flag_unpay_margin);
			map.put("mng_lock_flag_short_security",mng_lock_flag_short_security);
			map.put("mng_lock_flag_unsubstit_depo",mng_lock_flag_unsubstit_depo);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>stock_loan_account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStockLoanAccountCode()
  {
    return stock_loan_account_code;
  }


  /** 
   * <em>stock_loan_account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStockLoanAccountCodeIsSet() {
    return stock_loan_account_code_is_set;
  }


  /** 
   * <em>stock_loan_account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStockLoanAccountCodeIsModified() {
    return stock_loan_account_code_is_modified;
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
   * <em>account_open_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountOpenStatus()
  {
    return account_open_status;
  }


  /** 
   * <em>account_open_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountOpenStatusIsSet() {
    return account_open_status_is_set;
  }


  /** 
   * <em>account_open_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountOpenStatusIsModified() {
    return account_open_status_is_modified;
  }


  /** 
   * <em>appli_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliDate()
  {
    return appli_date;
  }


  /** 
   * <em>appli_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliDateIsSet() {
    return appli_date_is_set;
  }


  /** 
   * <em>appli_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliDateIsModified() {
    return appli_date_is_modified;
  }


  /** 
   * <em>account_open_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAccountOpenDate()
  {
    return account_open_date;
  }


  /** 
   * <em>account_open_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountOpenDateIsSet() {
    return account_open_date_is_set;
  }


  /** 
   * <em>account_open_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountOpenDateIsModified() {
    return account_open_date_is_modified;
  }


  /** 
   * <em>order_data_reception_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOrderDataReceptionDate()
  {
    return order_data_reception_date;
  }


  /** 
   * <em>order_data_reception_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderDataReceptionDateIsSet() {
    return order_data_reception_date_is_set;
  }


  /** 
   * <em>order_data_reception_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderDataReceptionDateIsModified() {
    return order_data_reception_date_is_modified;
  }


  /** 
   * <em>cancel_data_reception_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCancelDataReceptionDate()
  {
    return cancel_data_reception_date;
  }


  /** 
   * <em>cancel_data_reception_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCancelDataReceptionDateIsSet() {
    return cancel_data_reception_date_is_set;
  }


  /** 
   * <em>cancel_data_reception_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCancelDataReceptionDateIsModified() {
    return cancel_data_reception_date_is_modified;
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
   * <em>y_customer_data</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getYCustomerData()
  {
    return y_customer_data;
  }


  /** 
   * <em>y_customer_data</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getYCustomerDataIsSet() {
    return y_customer_data_is_set;
  }


  /** 
   * <em>y_customer_data</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getYCustomerDataIsModified() {
    return y_customer_data_is_modified;
  }


  /** 
   * <em>examin_lock_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getExaminLockFlag()
  {
    return examin_lock_flag;
  }


  /** 
   * <em>examin_lock_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExaminLockFlagIsSet() {
    return examin_lock_flag_is_set;
  }


  /** 
   * <em>examin_lock_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExaminLockFlagIsModified() {
    return examin_lock_flag_is_modified;
  }


  /** 
   * <em>branch_lock</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchLock()
  {
    return branch_lock;
  }


  /** 
   * <em>branch_lock</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchLockIsSet() {
    return branch_lock_is_set;
  }


  /** 
   * <em>branch_lock</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchLockIsModified() {
    return branch_lock_is_modified;
  }


  /** 
   * <em>mng_lock_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMngLockFlag()
  {
    return mng_lock_flag;
  }


  /** 
   * <em>mng_lock_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagIsSet() {
    return mng_lock_flag_is_set;
  }


  /** 
   * <em>mng_lock_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagIsModified() {
    return mng_lock_flag_is_modified;
  }


  /** 
   * <em>mng_lock_flag_advance</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagAdvance()
  {
    return mng_lock_flag_advance;
  }


  /** 
   * <em>mng_lock_flag_advance</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagAdvanceIsSet() {
    return mng_lock_flag_advance_is_set;
  }


  /** 
   * <em>mng_lock_flag_advance</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagAdvanceIsModified() {
    return mng_lock_flag_advance_is_modified;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnpayMargin()
  {
    return mng_lock_flag_unpay_margin;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagUnpayMarginIsSet() {
    return mng_lock_flag_unpay_margin_is_set;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagUnpayMarginIsModified() {
    return mng_lock_flag_unpay_margin_is_modified;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagShortSecurity()
  {
    return mng_lock_flag_short_security;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagShortSecurityIsSet() {
    return mng_lock_flag_short_security_is_set;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagShortSecurityIsModified() {
    return mng_lock_flag_short_security_is_modified;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnsubstitDepo()
  {
    return mng_lock_flag_unsubstit_depo;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagUnsubstitDepoIsSet() {
    return mng_lock_flag_unsubstit_depo_is_set;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMngLockFlagUnsubstitDepoIsModified() {
    return mng_lock_flag_unsubstit_depo_is_modified;
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
    return new StockSecuredLoanPK(stock_loan_account_code);
  }


  /** 
   * <em>stock_loan_account_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_stockLoanAccountCode <em>stock_loan_account_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setStockLoanAccountCode( String p_stockLoanAccountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stock_loan_account_code = p_stockLoanAccountCode;
    stock_loan_account_code_is_set = true;
    stock_loan_account_code_is_modified = true;
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
   * <em>account_open_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountOpenStatus <em>account_open_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAccountOpenStatus( String p_accountOpenStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_status = p_accountOpenStatus;
    account_open_status_is_set = true;
    account_open_status_is_modified = true;
  }


  /** 
   * <em>appli_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliDate <em>appli_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliDate( java.sql.Timestamp p_appliDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date = p_appliDate;
    appli_date_is_set = true;
    appli_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_date_is_set = true;
    appli_date_is_modified = true;
  }


  /** 
   * <em>account_open_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountOpenDate <em>account_open_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAccountOpenDate( java.sql.Timestamp p_accountOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_date = p_accountOpenDate;
    account_open_date_is_set = true;
    account_open_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>account_open_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAccountOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    account_open_date_is_set = true;
    account_open_date_is_modified = true;
  }


  /** 
   * <em>order_data_reception_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderDataReceptionDate <em>order_data_reception_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOrderDataReceptionDate( java.sql.Timestamp p_orderDataReceptionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_data_reception_date = p_orderDataReceptionDate;
    order_data_reception_date_is_set = true;
    order_data_reception_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>order_data_reception_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOrderDataReceptionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_data_reception_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_data_reception_date_is_set = true;
    order_data_reception_date_is_modified = true;
  }


  /** 
   * <em>cancel_data_reception_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cancelDataReceptionDate <em>cancel_data_reception_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCancelDataReceptionDate( java.sql.Timestamp p_cancelDataReceptionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_data_reception_date = p_cancelDataReceptionDate;
    cancel_data_reception_date_is_set = true;
    cancel_data_reception_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>cancel_data_reception_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCancelDataReceptionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_data_reception_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    cancel_data_reception_date_is_set = true;
    cancel_data_reception_date_is_modified = true;
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
   * <em>y_customer_data</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_yCustomerData <em>y_customer_data</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setYCustomerData( String p_yCustomerData )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    y_customer_data = p_yCustomerData;
    y_customer_data_is_set = true;
    y_customer_data_is_modified = true;
  }


  /** 
   * <em>examin_lock_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_examinLockFlag <em>examin_lock_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setExaminLockFlag( String p_examinLockFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    examin_lock_flag = p_examinLockFlag;
    examin_lock_flag_is_set = true;
    examin_lock_flag_is_modified = true;
  }


  /** 
   * <em>branch_lock</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchLock <em>branch_lock</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBranchLock( String p_branchLock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_lock = p_branchLock;
    branch_lock_is_set = true;
    branch_lock_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mngLockFlag <em>mng_lock_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setMngLockFlag( String p_mngLockFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag = p_mngLockFlag;
    mng_lock_flag_is_set = true;
    mng_lock_flag_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_advance</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mngLockFlagAdvance <em>mng_lock_flag_advance</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setMngLockFlagAdvance( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagAdvance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_advance = p_mngLockFlagAdvance;
    mng_lock_flag_advance_is_set = true;
    mng_lock_flag_advance_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mngLockFlagUnpayMargin <em>mng_lock_flag_unpay_margin</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setMngLockFlagUnpayMargin( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagUnpayMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_unpay_margin = p_mngLockFlagUnpayMargin;
    mng_lock_flag_unpay_margin_is_set = true;
    mng_lock_flag_unpay_margin_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mngLockFlagShortSecurity <em>mng_lock_flag_short_security</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setMngLockFlagShortSecurity( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagShortSecurity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_short_security = p_mngLockFlagShortSecurity;
    mng_lock_flag_short_security_is_set = true;
    mng_lock_flag_short_security_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mngLockFlagUnsubstitDepo <em>mng_lock_flag_unsubstit_depo</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setMngLockFlagUnsubstitDepo( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagUnsubstitDepo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_unsubstit_depo = p_mngLockFlagUnsubstitDepo;
    mng_lock_flag_unsubstit_depo_is_set = true;
    mng_lock_flag_unsubstit_depo_is_modified = true;
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
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_open_status") ) {
                    return this.account_open_status;
                }
                else if ( name.equals("appli_date") ) {
                    return this.appli_date;
                }
                else if ( name.equals("account_open_date") ) {
                    return this.account_open_date;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("branch_lock") ) {
                    return this.branch_lock;
                }
                break;
            case 'c':
                if ( name.equals("cancel_data_reception_date") ) {
                    return this.cancel_data_reception_date;
                }
                else if ( name.equals("close_date") ) {
                    return this.close_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("examin_lock_flag") ) {
                    return this.examin_lock_flag;
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
            case 'm':
                if ( name.equals("mng_lock_flag") ) {
                    return this.mng_lock_flag;
                }
                else if ( name.equals("mng_lock_flag_advance") ) {
                    return this.mng_lock_flag_advance;
                }
                else if ( name.equals("mng_lock_flag_unpay_margin") ) {
                    return this.mng_lock_flag_unpay_margin;
                }
                else if ( name.equals("mng_lock_flag_short_security") ) {
                    return this.mng_lock_flag_short_security;
                }
                else if ( name.equals("mng_lock_flag_unsubstit_depo") ) {
                    return this.mng_lock_flag_unsubstit_depo;
                }
                break;
            case 'o':
                if ( name.equals("order_data_reception_date") ) {
                    return this.order_data_reception_date;
                }
                break;
            case 's':
                if ( name.equals("stock_loan_account_code") ) {
                    return this.stock_loan_account_code;
                }
                break;
            case 'y':
                if ( name.equals("y_customer_data") ) {
                    return this.y_customer_data;
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
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_status' must be String: '"+value+"' is not." );
                    this.account_open_status = (String) value;
                    if (this.account_open_status_is_set)
                        this.account_open_status_is_modified = true;
                    this.account_open_status_is_set = true;
                    return;
                }
                else if ( name.equals("appli_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_date = (java.sql.Timestamp) value;
                    if (this.appli_date_is_set)
                        this.appli_date_is_modified = true;
                    this.appli_date_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'account_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.account_open_date = (java.sql.Timestamp) value;
                    if (this.account_open_date_is_set)
                        this.account_open_date_is_modified = true;
                    this.account_open_date_is_set = true;
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
                else if ( name.equals("branch_lock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_lock' must be String: '"+value+"' is not." );
                    this.branch_lock = (String) value;
                    if (this.branch_lock_is_set)
                        this.branch_lock_is_modified = true;
                    this.branch_lock_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cancel_data_reception_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'cancel_data_reception_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.cancel_data_reception_date = (java.sql.Timestamp) value;
                    if (this.cancel_data_reception_date_is_set)
                        this.cancel_data_reception_date_is_modified = true;
                    this.cancel_data_reception_date_is_set = true;
                    return;
                }
                else if ( name.equals("close_date") ) {
                    if ( value != null )
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
            case 'e':
                if ( name.equals("examin_lock_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'examin_lock_flag' must be String: '"+value+"' is not." );
                    this.examin_lock_flag = (String) value;
                    if (this.examin_lock_flag_is_set)
                        this.examin_lock_flag_is_modified = true;
                    this.examin_lock_flag_is_set = true;
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
                if ( name.equals("mng_lock_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag' must be String: '"+value+"' is not." );
                    this.mng_lock_flag = (String) value;
                    if (this.mng_lock_flag_is_set)
                        this.mng_lock_flag_is_modified = true;
                    this.mng_lock_flag_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_advance") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_advance' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_advance = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_advance_is_set)
                        this.mng_lock_flag_advance_is_modified = true;
                    this.mng_lock_flag_advance_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_unpay_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_unpay_margin' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_unpay_margin = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_unpay_margin_is_set)
                        this.mng_lock_flag_unpay_margin_is_modified = true;
                    this.mng_lock_flag_unpay_margin_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_short_security") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_short_security' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_short_security = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_short_security_is_set)
                        this.mng_lock_flag_short_security_is_modified = true;
                    this.mng_lock_flag_short_security_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_unsubstit_depo") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_unsubstit_depo' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_unsubstit_depo = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_unsubstit_depo_is_set)
                        this.mng_lock_flag_unsubstit_depo_is_modified = true;
                    this.mng_lock_flag_unsubstit_depo_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_data_reception_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_data_reception_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_data_reception_date = (java.sql.Timestamp) value;
                    if (this.order_data_reception_date_is_set)
                        this.order_data_reception_date_is_modified = true;
                    this.order_data_reception_date_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("stock_loan_account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stock_loan_account_code' must be String: '"+value+"' is not." );
                    this.stock_loan_account_code = (String) value;
                    if (this.stock_loan_account_code_is_set)
                        this.stock_loan_account_code_is_modified = true;
                    this.stock_loan_account_code_is_set = true;
                    return;
                }
                break;
            case 'y':
                if ( name.equals("y_customer_data") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'y_customer_data' must be String: '"+value+"' is not." );
                    this.y_customer_data = (String) value;
                    if (this.y_customer_data_is_set)
                        this.y_customer_data_is_modified = true;
                    this.y_customer_data_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
