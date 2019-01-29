head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.37.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostSpsecTransNotifyParams.java;


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
 * host_spsec_trans_notify�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link HostSpsecTransNotifyRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link HostSpsecTransNotifyParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link HostSpsecTransNotifyParams}��{@@link HostSpsecTransNotifyRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostSpsecTransNotifyPK 
 * @@see HostSpsecTransNotifyRow 
 */
public class HostSpsecTransNotifyParams extends Params implements HostSpsecTransNotifyRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "host_spsec_trans_notify";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = HostSpsecTransNotifyRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return HostSpsecTransNotifyRow.TYPE;
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
   * <em>trader_code</em>�J�����̒l 
   */
  public  String  trader_code;

  /** 
   * <em>transfer_flag</em>�J�����̒l 
   */
  public  String  transfer_flag;

  /** 
   * <em>commodity_div</em>�J�����̒l 
   */
  public  String  commodity_div;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  Integer  quantity;

  /** 
   * <em>custody_div</em>�J�����̒l 
   */
  public  String  custody_div;

  /** 
   * <em>title_div</em>�J�����̒l 
   */
  public  String  title_div;

  /** 
   * <em>deliv_type</em>�J�����̒l 
   */
  public  String  deliv_type;

  /** 
   * <em>transfer_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  transfer_time;

  /** 
   * <em>order_request_number</em>�J�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>specific_account_div</em>�J�����̒l 
   */
  public  String  specific_account_div;

  /** 
   * <em>mini_stock_div</em>�J�����̒l 
   */
  public  String  mini_stock_div;

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
  private boolean transfer_flag_is_set = false;
  private boolean transfer_flag_is_modified = false;
  private boolean commodity_div_is_set = false;
  private boolean commodity_div_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean custody_div_is_set = false;
  private boolean custody_div_is_modified = false;
  private boolean title_div_is_set = false;
  private boolean title_div_is_modified = false;
  private boolean deliv_type_is_set = false;
  private boolean deliv_type_is_modified = false;
  private boolean transfer_time_is_set = false;
  private boolean transfer_time_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean specific_account_div_is_set = false;
  private boolean specific_account_div_is_modified = false;
  private boolean mini_stock_div_is_set = false;
  private boolean mini_stock_div_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "transfer_flag=" +transfer_flag
      + "," + "commodity_div=" +commodity_div
      + "," + "product_code=" +product_code
      + "," + "quantity=" +quantity
      + "," + "custody_div=" +custody_div
      + "," + "title_div=" +title_div
      + "," + "deliv_type=" +deliv_type
      + "," + "transfer_time=" +transfer_time
      + "," + "order_request_number=" +order_request_number
      + "," + "specific_account_div=" +specific_account_div
      + "," + "mini_stock_div=" +mini_stock_div
      + "," + "status=" +status
      + "}";
  }


  /** 
   * �l�����ݒ��HostSpsecTransNotifyParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public HostSpsecTransNotifyParams() {
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���HostSpsecTransNotifyRow�I�u�W�F�N�g�̒l�𗘗p����HostSpsecTransNotifyParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����HostSpsecTransNotifyRow�I�u�W�F�N�g 
   */
  public HostSpsecTransNotifyParams( HostSpsecTransNotifyRow p_row )
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
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    transfer_flag = p_row.getTransferFlag();
    transfer_flag_is_set = p_row.getTransferFlagIsSet();
    transfer_flag_is_modified = p_row.getTransferFlagIsModified();
    commodity_div = p_row.getCommodityDiv();
    commodity_div_is_set = p_row.getCommodityDivIsSet();
    commodity_div_is_modified = p_row.getCommodityDivIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Integer( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    custody_div = p_row.getCustodyDiv();
    custody_div_is_set = p_row.getCustodyDivIsSet();
    custody_div_is_modified = p_row.getCustodyDivIsModified();
    title_div = p_row.getTitleDiv();
    title_div_is_set = p_row.getTitleDivIsSet();
    title_div_is_modified = p_row.getTitleDivIsModified();
    deliv_type = p_row.getDelivType();
    deliv_type_is_set = p_row.getDelivTypeIsSet();
    deliv_type_is_modified = p_row.getDelivTypeIsModified();
    transfer_time = p_row.getTransferTime();
    transfer_time_is_set = p_row.getTransferTimeIsSet();
    transfer_time_is_modified = p_row.getTransferTimeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    specific_account_div = p_row.getSpecificAccountDiv();
    specific_account_div_is_set = p_row.getSpecificAccountDivIsSet();
    specific_account_div_is_modified = p_row.getSpecificAccountDivIsModified();
    mini_stock_div = p_row.getMiniStockDiv();
    mini_stock_div_is_set = p_row.getMiniStockDivIsSet();
    mini_stock_div_is_modified = p_row.getMiniStockDivIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
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
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      transfer_flag_is_set = true;
      transfer_flag_is_modified = true;
      commodity_div_is_set = true;
      commodity_div_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      custody_div_is_set = true;
      custody_div_is_modified = true;
      title_div_is_set = true;
      title_div_is_modified = true;
      deliv_type_is_set = true;
      deliv_type_is_modified = true;
      transfer_time_is_set = true;
      transfer_time_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      specific_account_div_is_set = true;
      specific_account_div_is_modified = true;
      mini_stock_div_is_set = true;
      mini_stock_div_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostSpsecTransNotifyRow ) )
       return false;
    return fieldsEqual( (HostSpsecTransNotifyRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�HostSpsecTransNotifyRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( HostSpsecTransNotifyRow row )
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
    if ( transfer_flag == null ) {
      if ( row.getTransferFlag() != null )
        return false;
    } else if ( !transfer_flag.equals( row.getTransferFlag() ) ) {
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
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.intValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( custody_div == null ) {
      if ( row.getCustodyDiv() != null )
        return false;
    } else if ( !custody_div.equals( row.getCustodyDiv() ) ) {
        return false;
    }
    if ( title_div == null ) {
      if ( row.getTitleDiv() != null )
        return false;
    } else if ( !title_div.equals( row.getTitleDiv() ) ) {
        return false;
    }
    if ( deliv_type == null ) {
      if ( row.getDelivType() != null )
        return false;
    } else if ( !deliv_type.equals( row.getDelivType() ) ) {
        return false;
    }
    if ( transfer_time == null ) {
      if ( row.getTransferTime() != null )
        return false;
    } else if ( !transfer_time.equals( row.getTransferTime() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( specific_account_div == null ) {
      if ( row.getSpecificAccountDiv() != null )
        return false;
    } else if ( !specific_account_div.equals( row.getSpecificAccountDiv() ) ) {
        return false;
    }
    if ( mini_stock_div == null ) {
      if ( row.getMiniStockDiv() != null )
        return false;
    } else if ( !mini_stock_div.equals( row.getMiniStockDiv() ) ) {
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
        + (transfer_flag!=null? transfer_flag.hashCode(): 0) 
        + (commodity_div!=null? commodity_div.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (custody_div!=null? custody_div.hashCode(): 0) 
        + (title_div!=null? title_div.hashCode(): 0) 
        + (deliv_type!=null? deliv_type.hashCode(): 0) 
        + (transfer_time!=null? transfer_time.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (specific_account_div!=null? specific_account_div.hashCode(): 0) 
        + (mini_stock_div!=null? mini_stock_div.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !transfer_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_flag' must be set before inserting.");
		if ( !commodity_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'commodity_div' must be set before inserting.");
		if ( !custody_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'custody_div' must be set before inserting.");
		if ( !title_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'title_div' must be set before inserting.");
		if ( !deliv_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'deliv_type' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
		if ( !specific_account_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'specific_account_div' must be set before inserting.");
		if ( !mini_stock_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'mini_stock_div' must be set before inserting.");
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
		map.put("transfer_flag",transfer_flag);
		map.put("commodity_div",commodity_div);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( quantity != null )
			map.put("quantity",quantity);
		map.put("custody_div",custody_div);
		map.put("title_div",title_div);
		map.put("deliv_type",deliv_type);
		if ( transfer_time != null )
			map.put("transfer_time",transfer_time);
		map.put("order_request_number",order_request_number);
		map.put("specific_account_div",specific_account_div);
		map.put("mini_stock_div",mini_stock_div);
		map.put("status",status);
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
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( transfer_flag_is_modified )
			map.put("transfer_flag",transfer_flag);
		if ( commodity_div_is_modified )
			map.put("commodity_div",commodity_div);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( custody_div_is_modified )
			map.put("custody_div",custody_div);
		if ( title_div_is_modified )
			map.put("title_div",title_div);
		if ( deliv_type_is_modified )
			map.put("deliv_type",deliv_type);
		if ( transfer_time_is_modified )
			map.put("transfer_time",transfer_time);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( specific_account_div_is_modified )
			map.put("specific_account_div",specific_account_div);
		if ( mini_stock_div_is_modified )
			map.put("mini_stock_div",mini_stock_div);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( transfer_flag_is_set )
				map.put("transfer_flag",transfer_flag);
			if ( commodity_div_is_set )
				map.put("commodity_div",commodity_div);
			map.put("product_code",product_code);
			map.put("quantity",quantity);
			if ( custody_div_is_set )
				map.put("custody_div",custody_div);
			if ( title_div_is_set )
				map.put("title_div",title_div);
			if ( deliv_type_is_set )
				map.put("deliv_type",deliv_type);
			map.put("transfer_time",transfer_time);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			if ( specific_account_div_is_set )
				map.put("specific_account_div",specific_account_div);
			if ( mini_stock_div_is_set )
				map.put("mini_stock_div",mini_stock_div);
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
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getQuantity()
  {
    return ( quantity==null? ((int)0): quantity.intValue() );
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
   * <em>title_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTitleDiv()
  {
    return title_div;
  }


  /** 
   * <em>title_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTitleDivIsSet() {
    return title_div_is_set;
  }


  /** 
   * <em>title_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTitleDivIsModified() {
    return title_div_is_modified;
  }


  /** 
   * <em>deliv_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDelivType()
  {
    return deliv_type;
  }


  /** 
   * <em>deliv_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDelivTypeIsSet() {
    return deliv_type_is_set;
  }


  /** 
   * <em>deliv_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDelivTypeIsModified() {
    return deliv_type_is_modified;
  }


  /** 
   * <em>transfer_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getTransferTime()
  {
    return transfer_time;
  }


  /** 
   * <em>transfer_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferTimeIsSet() {
    return transfer_time_is_set;
  }


  /** 
   * <em>transfer_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferTimeIsModified() {
    return transfer_time_is_modified;
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
   * <em>specific_account_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSpecificAccountDiv()
  {
    return specific_account_div;
  }


  /** 
   * <em>specific_account_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecificAccountDivIsSet() {
    return specific_account_div_is_set;
  }


  /** 
   * <em>specific_account_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSpecificAccountDivIsModified() {
    return specific_account_div_is_modified;
  }


  /** 
   * <em>mini_stock_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMiniStockDiv()
  {
    return mini_stock_div;
  }


  /** 
   * <em>mini_stock_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockDivIsSet() {
    return mini_stock_div_is_set;
  }


  /** 
   * <em>mini_stock_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMiniStockDivIsModified() {
    return mini_stock_div_is_modified;
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
    throw new UnsupportedOperationException("Table host_spsec_trans_notify has no primary key.");
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
   * <em>quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�J�����̒l������킷9���ȉ���int�l 
   */
  public final void setQuantity( int p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Integer(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setQuantity( Integer p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
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
   * <em>title_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_titleDiv <em>title_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTitleDiv( String p_titleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    title_div = p_titleDiv;
    title_div_is_set = true;
    title_div_is_modified = true;
  }


  /** 
   * <em>deliv_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_delivType <em>deliv_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setDelivType( String p_delivType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deliv_type = p_delivType;
    deliv_type_is_set = true;
    deliv_type_is_modified = true;
  }


  /** 
   * <em>transfer_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transferTime <em>transfer_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setTransferTime( java.sql.Timestamp p_transferTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_time = p_transferTime;
    transfer_time_is_set = true;
    transfer_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>transfer_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setTransferTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transfer_time_is_set = true;
    transfer_time_is_modified = true;
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
   * <em>specific_account_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_specificAccountDiv <em>specific_account_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSpecificAccountDiv( String p_specificAccountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    specific_account_div = p_specificAccountDiv;
    specific_account_div_is_set = true;
    specific_account_div_is_modified = true;
  }


  /** 
   * <em>mini_stock_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_miniStockDiv <em>mini_stock_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setMiniStockDiv( String p_miniStockDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_div = p_miniStockDiv;
    mini_stock_div_is_set = true;
    mini_stock_div_is_modified = true;
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
                if ( name.equals("commodity_div") ) {
                    return this.commodity_div;
                }
                else if ( name.equals("custody_div") ) {
                    return this.custody_div;
                }
                break;
            case 'd':
                if ( name.equals("deliv_type") ) {
                    return this.deliv_type;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'm':
                if ( name.equals("mini_stock_div") ) {
                    return this.mini_stock_div;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 's':
                if ( name.equals("specific_account_div") ) {
                    return this.specific_account_div;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("transfer_flag") ) {
                    return this.transfer_flag;
                }
                else if ( name.equals("title_div") ) {
                    return this.title_div;
                }
                else if ( name.equals("transfer_time") ) {
                    return this.transfer_time;
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
                break;
            case 'd':
                if ( name.equals("deliv_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deliv_type' must be String: '"+value+"' is not." );
                    this.deliv_type = (String) value;
                    if (this.deliv_type_is_set)
                        this.deliv_type_is_modified = true;
                    this.deliv_type_is_set = true;
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
            case 'm':
                if ( name.equals("mini_stock_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mini_stock_div' must be String: '"+value+"' is not." );
                    this.mini_stock_div = (String) value;
                    if (this.mini_stock_div_is_set)
                        this.mini_stock_div_is_modified = true;
                    this.mini_stock_div_is_set = true;
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
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Integer: '"+value+"' is not." );
                    this.quantity = (Integer) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
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
                break;
            case 's':
                if ( name.equals("specific_account_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'specific_account_div' must be String: '"+value+"' is not." );
                    this.specific_account_div = (String) value;
                    if (this.specific_account_div_is_set)
                        this.specific_account_div_is_modified = true;
                    this.specific_account_div_is_set = true;
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
                else if ( name.equals("transfer_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_flag' must be String: '"+value+"' is not." );
                    this.transfer_flag = (String) value;
                    if (this.transfer_flag_is_set)
                        this.transfer_flag_is_modified = true;
                    this.transfer_flag_is_set = true;
                    return;
                }
                else if ( name.equals("title_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'title_div' must be String: '"+value+"' is not." );
                    this.title_div = (String) value;
                    if (this.title_div_is_set)
                        this.title_div_is_modified = true;
                    this.title_div_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transfer_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transfer_time = (java.sql.Timestamp) value;
                    if (this.transfer_time_is_set)
                        this.transfer_time_is_modified = true;
                    this.transfer_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
