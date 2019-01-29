head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfFixedBuyingChangeParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * mf_fixed_buying_change�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MfFixedBuyingChangeParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MfFixedBuyingChangeParams}��{@@link MfFixedBuyingChangeRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MfFixedBuyingChangePK 
 * @@see MfFixedBuyingChangeRow 
 */
public class MfFixedBuyingChangeParams extends Params implements MfFixedBuyingChangeRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mf_fixed_buying_change";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MfFixedBuyingChangeRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MfFixedBuyingChangeRow.TYPE;
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
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>valid_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  valid_start_date;

  /** 
   * <em>monthly_buy_amount</em>�J�����̒l 
   */
  public  Double  monthly_buy_amount;

  /** 
   * <em>increase_buy_amount</em>�J�����̒l 
   */
  public  Double  increase_buy_amount;

  /** 
   * <em>fin_draw_increase_buy_amount</em>�J�����̒l 
   */
  public  Double  fin_draw_increase_buy_amount;

  /** 
   * <em>change_div</em>�J�����̒l 
   */
  public  String  change_div;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>biz_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  biz_date;

  /** 
   * <em>order_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  order_timestamp;

  /** 
   * <em>order_chanel</em>�J�����̒l 
   */
  public  String  order_chanel;

  /** 
   * <em>delete_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

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
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean valid_start_date_is_set = false;
  private boolean valid_start_date_is_modified = false;
  private boolean monthly_buy_amount_is_set = false;
  private boolean monthly_buy_amount_is_modified = false;
  private boolean increase_buy_amount_is_set = false;
  private boolean increase_buy_amount_is_modified = false;
  private boolean fin_draw_increase_buy_amount_is_set = false;
  private boolean fin_draw_increase_buy_amount_is_modified = false;
  private boolean change_div_is_set = false;
  private boolean change_div_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean order_timestamp_is_set = false;
  private boolean order_timestamp_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
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
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "product_code=" + product_code
      + "," + "valid_start_date=" + valid_start_date
      + "," + "monthly_buy_amount=" +monthly_buy_amount
      + "," + "increase_buy_amount=" +increase_buy_amount
      + "," + "fin_draw_increase_buy_amount=" +fin_draw_increase_buy_amount
      + "," + "change_div=" +change_div
      + "," + "status=" +status
      + "," + "biz_date=" +biz_date
      + "," + "order_timestamp=" +order_timestamp
      + "," + "order_chanel=" +order_chanel
      + "," + "delete_flag=" +delete_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��MfFixedBuyingChangeParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MfFixedBuyingChangeParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
    product_code_is_modified = true;
    valid_start_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MfFixedBuyingChangeRow�I�u�W�F�N�g�̒l�𗘗p����MfFixedBuyingChangeParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MfFixedBuyingChangeRow�I�u�W�F�N�g 
   */
  public MfFixedBuyingChangeParams( MfFixedBuyingChangeRow p_row )
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
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    valid_start_date = p_row.getValidStartDate();
    valid_start_date_is_set = p_row.getValidStartDateIsSet();
    valid_start_date_is_modified = p_row.getValidStartDateIsModified();
    if ( !p_row.getMonthlyBuyAmountIsNull() )
      monthly_buy_amount = new Double( p_row.getMonthlyBuyAmount() );
    monthly_buy_amount_is_set = p_row.getMonthlyBuyAmountIsSet();
    monthly_buy_amount_is_modified = p_row.getMonthlyBuyAmountIsModified();
    if ( !p_row.getIncreaseBuyAmountIsNull() )
      increase_buy_amount = new Double( p_row.getIncreaseBuyAmount() );
    increase_buy_amount_is_set = p_row.getIncreaseBuyAmountIsSet();
    increase_buy_amount_is_modified = p_row.getIncreaseBuyAmountIsModified();
    if ( !p_row.getFinDrawIncreaseBuyAmountIsNull() )
      fin_draw_increase_buy_amount = new Double( p_row.getFinDrawIncreaseBuyAmount() );
    fin_draw_increase_buy_amount_is_set = p_row.getFinDrawIncreaseBuyAmountIsSet();
    fin_draw_increase_buy_amount_is_modified = p_row.getFinDrawIncreaseBuyAmountIsModified();
    change_div = p_row.getChangeDiv();
    change_div_is_set = p_row.getChangeDivIsSet();
    change_div_is_modified = p_row.getChangeDivIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    order_timestamp = p_row.getOrderTimestamp();
    order_timestamp_is_set = p_row.getOrderTimestampIsSet();
    order_timestamp_is_modified = p_row.getOrderTimestampIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      monthly_buy_amount_is_set = true;
      monthly_buy_amount_is_modified = true;
      increase_buy_amount_is_set = true;
      increase_buy_amount_is_modified = true;
      fin_draw_increase_buy_amount_is_set = true;
      fin_draw_increase_buy_amount_is_modified = true;
      change_div_is_set = true;
      change_div_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      order_timestamp_is_set = true;
      order_timestamp_is_modified = true;
      order_chanel_is_set = true;
      order_chanel_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
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
    if ( !( other instanceof MfFixedBuyingChangeRow ) )
       return false;
    return fieldsEqual( (MfFixedBuyingChangeRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MfFixedBuyingChangeRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MfFixedBuyingChangeRow row )
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
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( valid_start_date == null ) {
      if ( row.getValidStartDate() != null )
        return false;
    } else if ( !valid_start_date.equals( row.getValidStartDate() ) ) {
        return false;
    }
    if ( monthly_buy_amount == null ) {
      if ( !row.getMonthlyBuyAmountIsNull() )
        return false;
    } else if ( row.getMonthlyBuyAmountIsNull() || ( monthly_buy_amount.doubleValue() != row.getMonthlyBuyAmount() ) ) {
        return false;
    }
    if ( increase_buy_amount == null ) {
      if ( !row.getIncreaseBuyAmountIsNull() )
        return false;
    } else if ( row.getIncreaseBuyAmountIsNull() || ( increase_buy_amount.doubleValue() != row.getIncreaseBuyAmount() ) ) {
        return false;
    }
    if ( fin_draw_increase_buy_amount == null ) {
      if ( !row.getFinDrawIncreaseBuyAmountIsNull() )
        return false;
    } else if ( row.getFinDrawIncreaseBuyAmountIsNull() || ( fin_draw_increase_buy_amount.doubleValue() != row.getFinDrawIncreaseBuyAmount() ) ) {
        return false;
    }
    if ( change_div == null ) {
      if ( row.getChangeDiv() != null )
        return false;
    } else if ( !change_div.equals( row.getChangeDiv() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( order_timestamp == null ) {
      if ( row.getOrderTimestamp() != null )
        return false;
    } else if ( !order_timestamp.equals( row.getOrderTimestamp() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (valid_start_date!=null? valid_start_date.hashCode(): 0) 
        + (monthly_buy_amount!=null? monthly_buy_amount.hashCode(): 0) 
        + (increase_buy_amount!=null? increase_buy_amount.hashCode(): 0) 
        + (fin_draw_increase_buy_amount!=null? fin_draw_increase_buy_amount.hashCode(): 0) 
        + (change_div!=null? change_div.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + (order_timestamp!=null? order_timestamp.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
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
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("product_code",product_code);
		map.put("valid_start_date",valid_start_date);
		if ( monthly_buy_amount != null )
			map.put("monthly_buy_amount",monthly_buy_amount);
		if ( increase_buy_amount != null )
			map.put("increase_buy_amount",increase_buy_amount);
		if ( fin_draw_increase_buy_amount != null )
			map.put("fin_draw_increase_buy_amount",fin_draw_increase_buy_amount);
		if ( change_div != null )
			map.put("change_div",change_div);
		if ( status != null )
			map.put("status",status);
		if ( biz_date != null )
			map.put("biz_date",biz_date);
		if ( order_timestamp != null )
			map.put("order_timestamp",order_timestamp);
		if ( order_chanel != null )
			map.put("order_chanel",order_chanel);
		if ( delete_flag != null )
			map.put("delete_flag",delete_flag);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( monthly_buy_amount_is_modified )
			map.put("monthly_buy_amount",monthly_buy_amount);
		if ( increase_buy_amount_is_modified )
			map.put("increase_buy_amount",increase_buy_amount);
		if ( fin_draw_increase_buy_amount_is_modified )
			map.put("fin_draw_increase_buy_amount",fin_draw_increase_buy_amount);
		if ( change_div_is_modified )
			map.put("change_div",change_div);
		if ( status_is_modified )
			map.put("status",status);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( order_timestamp_is_modified )
			map.put("order_timestamp",order_timestamp);
		if ( order_chanel_is_modified )
			map.put("order_chanel",order_chanel);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("monthly_buy_amount",monthly_buy_amount);
			map.put("increase_buy_amount",increase_buy_amount);
			map.put("fin_draw_increase_buy_amount",fin_draw_increase_buy_amount);
			map.put("change_div",change_div);
			map.put("status",status);
			map.put("biz_date",biz_date);
			map.put("order_timestamp",order_timestamp);
			map.put("order_chanel",order_chanel);
			map.put("delete_flag",delete_flag);
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
   * <em>valid_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getValidStartDate()
  {
    return valid_start_date;
  }


  /** 
   * <em>valid_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidStartDateIsSet() {
    return valid_start_date_is_set;
  }


  /** 
   * <em>valid_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidStartDateIsModified() {
    return valid_start_date_is_modified;
  }


  /** 
   * <em>monthly_buy_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMonthlyBuyAmount()
  {
    return ( monthly_buy_amount==null? ((double)0): monthly_buy_amount.doubleValue() );
  }


  /** 
   * <em>monthly_buy_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMonthlyBuyAmountIsNull()
  {
    return monthly_buy_amount == null;
  }


  /** 
   * <em>monthly_buy_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMonthlyBuyAmountIsSet() {
    return monthly_buy_amount_is_set;
  }


  /** 
   * <em>monthly_buy_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMonthlyBuyAmountIsModified() {
    return monthly_buy_amount_is_modified;
  }


  /** 
   * <em>increase_buy_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getIncreaseBuyAmount()
  {
    return ( increase_buy_amount==null? ((double)0): increase_buy_amount.doubleValue() );
  }


  /** 
   * <em>increase_buy_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getIncreaseBuyAmountIsNull()
  {
    return increase_buy_amount == null;
  }


  /** 
   * <em>increase_buy_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIncreaseBuyAmountIsSet() {
    return increase_buy_amount_is_set;
  }


  /** 
   * <em>increase_buy_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIncreaseBuyAmountIsModified() {
    return increase_buy_amount_is_modified;
  }


  /** 
   * <em>fin_draw_increase_buy_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getFinDrawIncreaseBuyAmount()
  {
    return ( fin_draw_increase_buy_amount==null? ((double)0): fin_draw_increase_buy_amount.doubleValue() );
  }


  /** 
   * <em>fin_draw_increase_buy_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getFinDrawIncreaseBuyAmountIsNull()
  {
    return fin_draw_increase_buy_amount == null;
  }


  /** 
   * <em>fin_draw_increase_buy_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinDrawIncreaseBuyAmountIsSet() {
    return fin_draw_increase_buy_amount_is_set;
  }


  /** 
   * <em>fin_draw_increase_buy_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinDrawIncreaseBuyAmountIsModified() {
    return fin_draw_increase_buy_amount_is_modified;
  }


  /** 
   * <em>change_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getChangeDiv()
  {
    return change_div;
  }


  /** 
   * <em>change_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeDivIsSet() {
    return change_div_is_set;
  }


  /** 
   * <em>change_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeDivIsModified() {
    return change_div_is_modified;
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
   * <em>biz_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getBizDate()
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
   * <em>order_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOrderTimestamp()
  {
    return order_timestamp;
  }


  /** 
   * <em>order_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderTimestampIsSet() {
    return order_timestamp_is_set;
  }


  /** 
   * <em>order_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderTimestampIsModified() {
    return order_timestamp_is_modified;
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
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
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
    return new MfFixedBuyingChangePK(institution_code, branch_code, account_code, product_code, valid_start_date);
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
   * <em>valid_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_validStartDate <em>valid_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setValidStartDate( java.sql.Timestamp p_validStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_start_date = p_validStartDate;
    valid_start_date_is_set = true;
    valid_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>valid_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setValidStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    valid_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    valid_start_date_is_set = true;
    valid_start_date_is_modified = true;
  }


  /** 
   * <em>monthly_buy_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_monthlyBuyAmount <em>monthly_buy_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMonthlyBuyAmount( double p_monthlyBuyAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    monthly_buy_amount = new Double(p_monthlyBuyAmount);
    monthly_buy_amount_is_set = true;
    monthly_buy_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>monthly_buy_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMonthlyBuyAmount( Double p_monthlyBuyAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    monthly_buy_amount = p_monthlyBuyAmount;
    monthly_buy_amount_is_set = true;
    monthly_buy_amount_is_modified = true;
  }


  /** 
   * <em>increase_buy_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_increaseBuyAmount <em>increase_buy_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setIncreaseBuyAmount( double p_increaseBuyAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    increase_buy_amount = new Double(p_increaseBuyAmount);
    increase_buy_amount_is_set = true;
    increase_buy_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>increase_buy_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setIncreaseBuyAmount( Double p_increaseBuyAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    increase_buy_amount = p_increaseBuyAmount;
    increase_buy_amount_is_set = true;
    increase_buy_amount_is_modified = true;
  }


  /** 
   * <em>fin_draw_increase_buy_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finDrawIncreaseBuyAmount <em>fin_draw_increase_buy_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setFinDrawIncreaseBuyAmount( double p_finDrawIncreaseBuyAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_draw_increase_buy_amount = new Double(p_finDrawIncreaseBuyAmount);
    fin_draw_increase_buy_amount_is_set = true;
    fin_draw_increase_buy_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>fin_draw_increase_buy_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setFinDrawIncreaseBuyAmount( Double p_finDrawIncreaseBuyAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    fin_draw_increase_buy_amount = p_finDrawIncreaseBuyAmount;
    fin_draw_increase_buy_amount_is_set = true;
    fin_draw_increase_buy_amount_is_modified = true;
  }


  /** 
   * <em>change_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_changeDiv <em>change_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setChangeDiv( String p_changeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_div = p_changeDiv;
    change_div_is_set = true;
    change_div_is_modified = true;
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
   * <em>biz_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizDate <em>biz_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setBizDate( java.sql.Timestamp p_bizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date = p_bizDate;
    biz_date_is_set = true;
    biz_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>biz_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setBizDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    biz_date_is_set = true;
    biz_date_is_modified = true;
  }


  /** 
   * <em>order_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderTimestamp <em>order_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOrderTimestamp( java.sql.Timestamp p_orderTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_timestamp = p_orderTimestamp;
    order_timestamp_is_set = true;
    order_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>order_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOrderTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_timestamp_is_set = true;
    order_timestamp_is_modified = true;
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
   * <em>delete_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deleteFlag <em>delete_flag</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                break;
            case 'c':
                if ( name.equals("change_div") ) {
                    return this.change_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'f':
                if ( name.equals("fin_draw_increase_buy_amount") ) {
                    return this.fin_draw_increase_buy_amount;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("increase_buy_amount") ) {
                    return this.increase_buy_amount;
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
                if ( name.equals("monthly_buy_amount") ) {
                    return this.monthly_buy_amount;
                }
                break;
            case 'o':
                if ( name.equals("order_timestamp") ) {
                    return this.order_timestamp;
                }
                else if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 'v':
                if ( name.equals("valid_start_date") ) {
                    return this.valid_start_date;
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
                else if ( name.equals("biz_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.biz_date = (java.sql.Timestamp) value;
                    if (this.biz_date_is_set)
                        this.biz_date_is_modified = true;
                    this.biz_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("change_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_div' must be String: '"+value+"' is not." );
                    this.change_div = (String) value;
                    if (this.change_div_is_set)
                        this.change_div_is_modified = true;
                    this.change_div_is_set = true;
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
                if ( name.equals("delete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fin_draw_increase_buy_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'fin_draw_increase_buy_amount' must be Double: '"+value+"' is not." );
                    this.fin_draw_increase_buy_amount = (Double) value;
                    if (this.fin_draw_increase_buy_amount_is_set)
                        this.fin_draw_increase_buy_amount_is_modified = true;
                    this.fin_draw_increase_buy_amount_is_set = true;
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
                else if ( name.equals("increase_buy_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'increase_buy_amount' must be Double: '"+value+"' is not." );
                    this.increase_buy_amount = (Double) value;
                    if (this.increase_buy_amount_is_set)
                        this.increase_buy_amount_is_modified = true;
                    this.increase_buy_amount_is_set = true;
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
            case 'm':
                if ( name.equals("monthly_buy_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'monthly_buy_amount' must be Double: '"+value+"' is not." );
                    this.monthly_buy_amount = (Double) value;
                    if (this.monthly_buy_amount_is_set)
                        this.monthly_buy_amount_is_modified = true;
                    this.monthly_buy_amount_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_timestamp = (java.sql.Timestamp) value;
                    if (this.order_timestamp_is_set)
                        this.order_timestamp_is_modified = true;
                    this.order_timestamp_is_set = true;
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
                break;
            case 's':
                if ( name.equals("status") ) {
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
            case 'v':
                if ( name.equals("valid_start_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'valid_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.valid_start_date = (java.sql.Timestamp) value;
                    if (this.valid_start_date_is_set)
                        this.valid_start_date_is_modified = true;
                    this.valid_start_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
