head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.43.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostTransferPaymentParams.java;


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
 * host_transfer_payment�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link HostTransferPaymentRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link HostTransferPaymentParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link HostTransferPaymentParams}��{@@link HostTransferPaymentRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostTransferPaymentPK 
 * @@see HostTransferPaymentRow 
 */
public class HostTransferPaymentParams extends Params implements HostTransferPaymentRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "host_transfer_payment";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = HostTransferPaymentRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return HostTransferPaymentRow.TYPE;
  }


  /** 
   * <em>rowid</em>�J�����̒l 
   */
  public  String  rowid;

  /** 
   * <em>request_code</em>�J�����̒l 
   */
  public  String  request_code;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>transfer_flag</em>�J�����̒l 
   */
  public  String  transfer_flag;

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
   * <em>commodity_div</em>�J�����̒l 
   */
  public  String  commodity_div;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>sonar_code</em>�J�����̒l 
   */
  public  String  sonar_code;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  int  quantity;

  /** 
   * <em>transfer_div</em>�J�����̒l 
   */
  public  String  transfer_div;

  /** 
   * <em>purpose_div</em>�J�����̒l 
   */
  public  String  purpose_div;

  /** 
   * <em>once_stop_div</em>�J�����̒l 
   */
  public  String  once_stop_div;

  /** 
   * <em>custody_div</em>�J�����̒l 
   */
  public  String  custody_div;

  /** 
   * <em>name_method_div</em>�J�����̒l 
   */
  public  String  name_method_div;

  /** 
   * <em>complete</em>�J�����̒l 
   */
  public  String  complete;

  /** 
   * <em>force</em>�J�����̒l 
   */
  public  String  force;

  /** 
   * <em>specific_div</em>�J�����̒l 
   */
  public  String  specific_div;

  /** 
   * <em>transfer_date</em>�J�����̒l 
   */
  public  String  transfer_date;

  /** 
   * <em>transfer_amount</em>�J�����̒l 
   */
  public  String  transfer_amount;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean transfer_flag_is_set = false;
  private boolean transfer_flag_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean commodity_div_is_set = false;
  private boolean commodity_div_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean sonar_code_is_set = false;
  private boolean sonar_code_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean transfer_div_is_set = false;
  private boolean transfer_div_is_modified = false;
  private boolean purpose_div_is_set = false;
  private boolean purpose_div_is_modified = false;
  private boolean once_stop_div_is_set = false;
  private boolean once_stop_div_is_modified = false;
  private boolean custody_div_is_set = false;
  private boolean custody_div_is_modified = false;
  private boolean name_method_div_is_set = false;
  private boolean name_method_div_is_modified = false;
  private boolean complete_is_set = false;
  private boolean complete_is_modified = false;
  private boolean force_is_set = false;
  private boolean force_is_modified = false;
  private boolean specific_div_is_set = false;
  private boolean specific_div_is_modified = false;
  private boolean transfer_date_is_set = false;
  private boolean transfer_date_is_modified = false;
  private boolean transfer_amount_is_set = false;
  private boolean transfer_amount_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "transfer_flag=" +transfer_flag
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "commodity_div=" +commodity_div
      + "," + "product_code=" +product_code
      + "," + "sonar_code=" +sonar_code
      + "," + "quantity=" +quantity
      + "," + "transfer_div=" +transfer_div
      + "," + "purpose_div=" +purpose_div
      + "," + "once_stop_div=" +once_stop_div
      + "," + "custody_div=" +custody_div
      + "," + "name_method_div=" +name_method_div
      + "," + "complete=" +complete
      + "," + "force=" +force
      + "," + "specific_div=" +specific_div
      + "," + "transfer_date=" +transfer_date
      + "," + "transfer_amount=" +transfer_amount
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��HostTransferPaymentParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public HostTransferPaymentParams() {
    rowid_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���HostTransferPaymentRow�I�u�W�F�N�g�̒l�𗘗p����HostTransferPaymentParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����HostTransferPaymentRow�I�u�W�F�N�g 
   */
  public HostTransferPaymentParams( HostTransferPaymentRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    transfer_flag = p_row.getTransferFlag();
    transfer_flag_is_set = p_row.getTransferFlagIsSet();
    transfer_flag_is_modified = p_row.getTransferFlagIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    commodity_div = p_row.getCommodityDiv();
    commodity_div_is_set = p_row.getCommodityDivIsSet();
    commodity_div_is_modified = p_row.getCommodityDivIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    sonar_code = p_row.getSonarCode();
    sonar_code_is_set = p_row.getSonarCodeIsSet();
    sonar_code_is_modified = p_row.getSonarCodeIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    transfer_div = p_row.getTransferDiv();
    transfer_div_is_set = p_row.getTransferDivIsSet();
    transfer_div_is_modified = p_row.getTransferDivIsModified();
    purpose_div = p_row.getPurposeDiv();
    purpose_div_is_set = p_row.getPurposeDivIsSet();
    purpose_div_is_modified = p_row.getPurposeDivIsModified();
    once_stop_div = p_row.getOnceStopDiv();
    once_stop_div_is_set = p_row.getOnceStopDivIsSet();
    once_stop_div_is_modified = p_row.getOnceStopDivIsModified();
    custody_div = p_row.getCustodyDiv();
    custody_div_is_set = p_row.getCustodyDivIsSet();
    custody_div_is_modified = p_row.getCustodyDivIsModified();
    name_method_div = p_row.getNameMethodDiv();
    name_method_div_is_set = p_row.getNameMethodDivIsSet();
    name_method_div_is_modified = p_row.getNameMethodDivIsModified();
    complete = p_row.getComplete();
    complete_is_set = p_row.getCompleteIsSet();
    complete_is_modified = p_row.getCompleteIsModified();
    force = p_row.getForce();
    force_is_set = p_row.getForceIsSet();
    force_is_modified = p_row.getForceIsModified();
    specific_div = p_row.getSpecificDiv();
    specific_div_is_set = p_row.getSpecificDivIsSet();
    specific_div_is_modified = p_row.getSpecificDivIsModified();
    transfer_date = p_row.getTransferDate();
    transfer_date_is_set = p_row.getTransferDateIsSet();
    transfer_date_is_modified = p_row.getTransferDateIsModified();
    transfer_amount = p_row.getTransferAmount();
    transfer_amount_is_set = p_row.getTransferAmountIsSet();
    transfer_amount_is_modified = p_row.getTransferAmountIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      transfer_flag_is_set = true;
      transfer_flag_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      commodity_div_is_set = true;
      commodity_div_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      sonar_code_is_set = true;
      sonar_code_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      transfer_div_is_set = true;
      transfer_div_is_modified = true;
      purpose_div_is_set = true;
      purpose_div_is_modified = true;
      once_stop_div_is_set = true;
      once_stop_div_is_modified = true;
      custody_div_is_set = true;
      custody_div_is_modified = true;
      name_method_div_is_set = true;
      name_method_div_is_modified = true;
      complete_is_set = true;
      complete_is_modified = true;
      force_is_set = true;
      force_is_modified = true;
      specific_div_is_set = true;
      specific_div_is_modified = true;
      transfer_date_is_set = true;
      transfer_date_is_modified = true;
      transfer_amount_is_set = true;
      transfer_amount_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostTransferPaymentRow ) )
       return false;
    return fieldsEqual( (HostTransferPaymentRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�HostTransferPaymentRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( HostTransferPaymentRow row )
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
    if ( transfer_flag == null ) {
      if ( row.getTransferFlag() != null )
        return false;
    } else if ( !transfer_flag.equals( row.getTransferFlag() ) ) {
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
    if ( commodity_div == null ) {
      if ( row.getCommodityDiv() != null )
        return false;
    } else if ( !commodity_div.equals( row.getCommodityDiv() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( sonar_code == null ) {
      if ( row.getSonarCode() != null )
        return false;
    } else if ( !sonar_code.equals( row.getSonarCode() ) ) {
        return false;
    }
    if ( quantity != row.getQuantity() )
      return false;
    if ( transfer_div == null ) {
      if ( row.getTransferDiv() != null )
        return false;
    } else if ( !transfer_div.equals( row.getTransferDiv() ) ) {
        return false;
    }
    if ( purpose_div == null ) {
      if ( row.getPurposeDiv() != null )
        return false;
    } else if ( !purpose_div.equals( row.getPurposeDiv() ) ) {
        return false;
    }
    if ( once_stop_div == null ) {
      if ( row.getOnceStopDiv() != null )
        return false;
    } else if ( !once_stop_div.equals( row.getOnceStopDiv() ) ) {
        return false;
    }
    if ( custody_div == null ) {
      if ( row.getCustodyDiv() != null )
        return false;
    } else if ( !custody_div.equals( row.getCustodyDiv() ) ) {
        return false;
    }
    if ( name_method_div == null ) {
      if ( row.getNameMethodDiv() != null )
        return false;
    } else if ( !name_method_div.equals( row.getNameMethodDiv() ) ) {
        return false;
    }
    if ( complete == null ) {
      if ( row.getComplete() != null )
        return false;
    } else if ( !complete.equals( row.getComplete() ) ) {
        return false;
    }
    if ( force == null ) {
      if ( row.getForce() != null )
        return false;
    } else if ( !force.equals( row.getForce() ) ) {
        return false;
    }
    if ( specific_div == null ) {
      if ( row.getSpecificDiv() != null )
        return false;
    } else if ( !specific_div.equals( row.getSpecificDiv() ) ) {
        return false;
    }
    if ( transfer_date == null ) {
      if ( row.getTransferDate() != null )
        return false;
    } else if ( !transfer_date.equals( row.getTransferDate() ) ) {
        return false;
    }
    if ( transfer_amount == null ) {
      if ( row.getTransferAmount() != null )
        return false;
    } else if ( !transfer_amount.equals( row.getTransferAmount() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
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
        + (transfer_flag!=null? transfer_flag.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (commodity_div!=null? commodity_div.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (sonar_code!=null? sonar_code.hashCode(): 0) 
        + ((int) quantity)
        + (transfer_div!=null? transfer_div.hashCode(): 0) 
        + (purpose_div!=null? purpose_div.hashCode(): 0) 
        + (once_stop_div!=null? once_stop_div.hashCode(): 0) 
        + (custody_div!=null? custody_div.hashCode(): 0) 
        + (name_method_div!=null? name_method_div.hashCode(): 0) 
        + (complete!=null? complete.hashCode(): 0) 
        + (force!=null? force.hashCode(): 0) 
        + (specific_div!=null? specific_div.hashCode(): 0) 
        + (transfer_date!=null? transfer_date.hashCode(): 0) 
        + (transfer_amount!=null? transfer_amount.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !transfer_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_flag' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !commodity_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'commodity_div' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !sonar_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'sonar_code' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !custody_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'custody_div' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		map.put("transfer_flag",transfer_flag);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("commodity_div",commodity_div);
		map.put("product_code",product_code);
		map.put("sonar_code",sonar_code);
		map.put("quantity",new Integer(quantity));
		if ( transfer_div != null )
			map.put("transfer_div",transfer_div);
		if ( purpose_div != null )
			map.put("purpose_div",purpose_div);
		if ( once_stop_div != null )
			map.put("once_stop_div",once_stop_div);
		map.put("custody_div",custody_div);
		if ( name_method_div != null )
			map.put("name_method_div",name_method_div);
		if ( complete != null )
			map.put("complete",complete);
		if ( force != null )
			map.put("force",force);
		if ( specific_div != null )
			map.put("specific_div",specific_div);
		if ( transfer_date != null )
			map.put("transfer_date",transfer_date);
		if ( transfer_amount != null )
			map.put("transfer_amount",transfer_amount);
		if ( status != null )
			map.put("status",status);
		map.put("created_timestamp",created_timestamp);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( transfer_flag_is_modified )
			map.put("transfer_flag",transfer_flag);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( commodity_div_is_modified )
			map.put("commodity_div",commodity_div);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( sonar_code_is_modified )
			map.put("sonar_code",sonar_code);
		if ( quantity_is_modified )
			map.put("quantity",new Integer(quantity));
		if ( transfer_div_is_modified )
			map.put("transfer_div",transfer_div);
		if ( purpose_div_is_modified )
			map.put("purpose_div",purpose_div);
		if ( once_stop_div_is_modified )
			map.put("once_stop_div",once_stop_div);
		if ( custody_div_is_modified )
			map.put("custody_div",custody_div);
		if ( name_method_div_is_modified )
			map.put("name_method_div",name_method_div);
		if ( complete_is_modified )
			map.put("complete",complete);
		if ( force_is_modified )
			map.put("force",force);
		if ( specific_div_is_modified )
			map.put("specific_div",specific_div);
		if ( transfer_date_is_modified )
			map.put("transfer_date",transfer_date);
		if ( transfer_amount_is_modified )
			map.put("transfer_amount",transfer_amount);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if (map.size() == 0) {
			map.put("request_code",request_code);
			map.put("institution_code",institution_code);
			if ( transfer_flag_is_set )
				map.put("transfer_flag",transfer_flag);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( commodity_div_is_set )
				map.put("commodity_div",commodity_div);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( sonar_code_is_set )
				map.put("sonar_code",sonar_code);
			if ( quantity_is_set )
				map.put("quantity",new Integer(quantity));
			map.put("transfer_div",transfer_div);
			map.put("purpose_div",purpose_div);
			map.put("once_stop_div",once_stop_div);
			if ( custody_div_is_set )
				map.put("custody_div",custody_div);
			map.put("name_method_div",name_method_div);
			map.put("complete",complete);
			map.put("force",force);
			map.put("specific_div",specific_div);
			map.put("transfer_date",transfer_date);
			map.put("transfer_amount",transfer_amount);
			map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
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
   * <em>transfer_flag</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTransferFlag()
  {
    return transfer_flag;
  }


  /** 
   * <em>transfer_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferFlagIsSet() {
    return transfer_flag_is_set;
  }


  /** 
   * <em>transfer_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferFlagIsModified() {
    return transfer_flag_is_modified;
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
   * <em>commodity_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommodityDiv()
  {
    return commodity_div;
  }


  /** 
   * <em>commodity_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommodityDivIsSet() {
    return commodity_div_is_set;
  }


  /** 
   * <em>commodity_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommodityDivIsModified() {
    return commodity_div_is_modified;
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
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getQuantity()
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
   * <em>transfer_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTransferDiv()
  {
    return transfer_div;
  }


  /** 
   * <em>transfer_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferDivIsSet() {
    return transfer_div_is_set;
  }


  /** 
   * <em>transfer_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferDivIsModified() {
    return transfer_div_is_modified;
  }


  /** 
   * <em>purpose_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPurposeDiv()
  {
    return purpose_div;
  }


  /** 
   * <em>purpose_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPurposeDivIsSet() {
    return purpose_div_is_set;
  }


  /** 
   * <em>purpose_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPurposeDivIsModified() {
    return purpose_div_is_modified;
  }


  /** 
   * <em>once_stop_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOnceStopDiv()
  {
    return once_stop_div;
  }


  /** 
   * <em>once_stop_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOnceStopDivIsSet() {
    return once_stop_div_is_set;
  }


  /** 
   * <em>once_stop_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOnceStopDivIsModified() {
    return once_stop_div_is_modified;
  }


  /** 
   * <em>custody_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCustodyDiv()
  {
    return custody_div;
  }


  /** 
   * <em>custody_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCustodyDivIsSet() {
    return custody_div_is_set;
  }


  /** 
   * <em>custody_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCustodyDivIsModified() {
    return custody_div_is_modified;
  }


  /** 
   * <em>name_method_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNameMethodDiv()
  {
    return name_method_div;
  }


  /** 
   * <em>name_method_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameMethodDivIsSet() {
    return name_method_div_is_set;
  }


  /** 
   * <em>name_method_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameMethodDivIsModified() {
    return name_method_div_is_modified;
  }


  /** 
   * <em>complete</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getComplete()
  {
    return complete;
  }


  /** 
   * <em>complete</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompleteIsSet() {
    return complete_is_set;
  }


  /** 
   * <em>complete</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCompleteIsModified() {
    return complete_is_modified;
  }


  /** 
   * <em>force</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getForce()
  {
    return force;
  }


  /** 
   * <em>force</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForceIsSet() {
    return force_is_set;
  }


  /** 
   * <em>force</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getForceIsModified() {
    return force_is_modified;
  }


  /** 
   * <em>specific_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSpecificDiv()
  {
    return specific_div;
  }


  /** 
   * <em>specific_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecificDivIsSet() {
    return specific_div_is_set;
  }


  /** 
   * <em>specific_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecificDivIsModified() {
    return specific_div_is_modified;
  }


  /** 
   * <em>transfer_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTransferDate()
  {
    return transfer_date;
  }


  /** 
   * <em>transfer_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferDateIsSet() {
    return transfer_date_is_set;
  }


  /** 
   * <em>transfer_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferDateIsModified() {
    return transfer_date_is_modified;
  }


  /** 
   * <em>transfer_amount</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTransferAmount()
  {
    return transfer_amount;
  }


  /** 
   * <em>transfer_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferAmountIsSet() {
    return transfer_amount_is_set;
  }


  /** 
   * <em>transfer_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferAmountIsModified() {
    return transfer_amount_is_modified;
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
   * <em>rowid</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostTransferPaymentPK(rowid);
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
   * <em>transfer_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transferFlag <em>transfer_flag</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTransferFlag( String p_transferFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_flag = p_transferFlag;
    transfer_flag_is_set = true;
    transfer_flag_is_modified = true;
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
   * <em>commodity_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commodityDiv <em>commodity_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCommodityDiv( String p_commodityDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commodity_div = p_commodityDiv;
    commodity_div_is_set = true;
    commodity_div_is_modified = true;
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
   * <em>sonar_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sonarCode <em>sonar_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setSonarCode( String p_sonarCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_code = p_sonarCode;
    sonar_code_is_set = true;
    sonar_code_is_modified = true;
  }


  /** 
   * <em>quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setQuantity( int p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>transfer_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transferDiv <em>transfer_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTransferDiv( String p_transferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_div = p_transferDiv;
    transfer_div_is_set = true;
    transfer_div_is_modified = true;
  }


  /** 
   * <em>purpose_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_purposeDiv <em>purpose_div</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setPurposeDiv( String p_purposeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    purpose_div = p_purposeDiv;
    purpose_div_is_set = true;
    purpose_div_is_modified = true;
  }


  /** 
   * <em>once_stop_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_onceStopDiv <em>once_stop_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setOnceStopDiv( String p_onceStopDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    once_stop_div = p_onceStopDiv;
    once_stop_div_is_set = true;
    once_stop_div_is_modified = true;
  }


  /** 
   * <em>custody_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_custodyDiv <em>custody_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCustodyDiv( String p_custodyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    custody_div = p_custodyDiv;
    custody_div_is_set = true;
    custody_div_is_modified = true;
  }


  /** 
   * <em>name_method_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_nameMethodDiv <em>name_method_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setNameMethodDiv( String p_nameMethodDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_method_div = p_nameMethodDiv;
    name_method_div_is_set = true;
    name_method_div_is_modified = true;
  }


  /** 
   * <em>complete</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_complete <em>complete</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setComplete( String p_complete )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    complete = p_complete;
    complete_is_set = true;
    complete_is_modified = true;
  }


  /** 
   * <em>force</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_force <em>force</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setForce( String p_force )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    force = p_force;
    force_is_set = true;
    force_is_modified = true;
  }


  /** 
   * <em>specific_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_specificDiv <em>specific_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSpecificDiv( String p_specificDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    specific_div = p_specificDiv;
    specific_div_is_set = true;
    specific_div_is_modified = true;
  }


  /** 
   * <em>transfer_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transferDate <em>transfer_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setTransferDate( String p_transferDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_date = p_transferDate;
    transfer_date_is_set = true;
    transfer_date_is_modified = true;
  }


  /** 
   * <em>transfer_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transferAmount <em>transfer_amount</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setTransferAmount( String p_transferAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_amount = p_transferAmount;
    transfer_amount_is_set = true;
    transfer_amount_is_modified = true;
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
   * <em>rowid</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rowid <em>rowid</em>�J�����̒l������킷20���ȉ���String�l�ŁA���̐��x��20���܂�
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
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
                if ( name.equals("commodity_div") ) {
                    return this.commodity_div;
                }
                else if ( name.equals("custody_div") ) {
                    return this.custody_div;
                }
                else if ( name.equals("complete") ) {
                    return this.complete;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("force") ) {
                    return this.force;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'n':
                if ( name.equals("name_method_div") ) {
                    return this.name_method_div;
                }
                break;
            case 'o':
                if ( name.equals("once_stop_div") ) {
                    return this.once_stop_div;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("purpose_div") ) {
                    return this.purpose_div;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Integer( quantity );
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("sonar_code") ) {
                    return this.sonar_code;
                }
                else if ( name.equals("specific_div") ) {
                    return this.specific_div;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("transfer_flag") ) {
                    return this.transfer_flag;
                }
                else if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("transfer_div") ) {
                    return this.transfer_div;
                }
                else if ( name.equals("transfer_date") ) {
                    return this.transfer_date;
                }
                else if ( name.equals("transfer_amount") ) {
                    return this.transfer_amount;
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
                if ( name.equals("commodity_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commodity_div' must be String: '"+value+"' is not." );
                    this.commodity_div = (String) value;
                    if (this.commodity_div_is_set)
                        this.commodity_div_is_modified = true;
                    this.commodity_div_is_set = true;
                    return;
                }
                else if ( name.equals("custody_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'custody_div' must be String: '"+value+"' is not." );
                    this.custody_div = (String) value;
                    if (this.custody_div_is_set)
                        this.custody_div_is_modified = true;
                    this.custody_div_is_set = true;
                    return;
                }
                else if ( name.equals("complete") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'complete' must be String: '"+value+"' is not." );
                    this.complete = (String) value;
                    if (this.complete_is_set)
                        this.complete_is_modified = true;
                    this.complete_is_set = true;
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
            case 'f':
                if ( name.equals("force") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'force' must be String: '"+value+"' is not." );
                    this.force = (String) value;
                    if (this.force_is_set)
                        this.force_is_modified = true;
                    this.force_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name_method_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name_method_div' must be String: '"+value+"' is not." );
                    this.name_method_div = (String) value;
                    if (this.name_method_div_is_set)
                        this.name_method_div_is_modified = true;
                    this.name_method_div_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("once_stop_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'once_stop_div' must be String: '"+value+"' is not." );
                    this.once_stop_div = (String) value;
                    if (this.once_stop_div_is_set)
                        this.once_stop_div_is_modified = true;
                    this.once_stop_div_is_set = true;
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
                else if ( name.equals("purpose_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'purpose_div' must be String: '"+value+"' is not." );
                    this.purpose_div = (String) value;
                    if (this.purpose_div_is_set)
                        this.purpose_div_is_modified = true;
                    this.purpose_div_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Integer: '"+value+"' is not." );
                    this.quantity = ((Integer) value).intValue();
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_code' must be String: '"+value+"' is not." );
                    this.sonar_code = (String) value;
                    if (this.sonar_code_is_set)
                        this.sonar_code_is_modified = true;
                    this.sonar_code_is_set = true;
                    return;
                }
                else if ( name.equals("specific_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'specific_div' must be String: '"+value+"' is not." );
                    this.specific_div = (String) value;
                    if (this.specific_div_is_set)
                        this.specific_div_is_modified = true;
                    this.specific_div_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
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
                if ( name.equals("transfer_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_flag' must be String: '"+value+"' is not." );
                    this.transfer_flag = (String) value;
                    if (this.transfer_flag_is_set)
                        this.transfer_flag_is_modified = true;
                    this.transfer_flag_is_set = true;
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
                else if ( name.equals("transfer_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_div' must be String: '"+value+"' is not." );
                    this.transfer_div = (String) value;
                    if (this.transfer_div_is_set)
                        this.transfer_div_is_modified = true;
                    this.transfer_div_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_date' must be String: '"+value+"' is not." );
                    this.transfer_date = (String) value;
                    if (this.transfer_date_is_set)
                        this.transfer_date_is_modified = true;
                    this.transfer_date_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_amount' must be String: '"+value+"' is not." );
                    this.transfer_amount = (String) value;
                    if (this.transfer_amount_is_set)
                        this.transfer_amount_is_modified = true;
                    this.transfer_amount_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
