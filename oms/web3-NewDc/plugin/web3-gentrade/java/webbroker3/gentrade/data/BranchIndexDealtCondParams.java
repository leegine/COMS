head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchIndexDealtCondParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * branch_index_dealt_cond�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link BranchIndexDealtCondRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link BranchIndexDealtCondParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link BranchIndexDealtCondParams}��{@@link BranchIndexDealtCondRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchIndexDealtCondPK 
 * @@see BranchIndexDealtCondRow 
 */
public class BranchIndexDealtCondParams extends Params implements BranchIndexDealtCondRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "branch_index_dealt_cond";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = BranchIndexDealtCondRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return BranchIndexDealtCondRow.TYPE;
  }


  /** 
   * <em>target_product_code</em>�J�����̒l 
   */
  public  String  target_product_code;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�J�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>market_code</em>�J�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>future_option_div</em>�J�����̒l 
   */
  public  String  future_option_div;

  /** 
   * <em>enable_order</em>�J�����̒l 
   */
  public  String  enable_order;

  /** 
   * <em>open_cont_long_order_limit</em>�J�����̒l 
   */
  public  Integer  open_cont_long_order_limit;

  /** 
   * <em>open_cont_short_order_limit</em>�J�����̒l 
   */
  public  Integer  open_cont_short_order_limit;

  /** 
   * <em>settle_cont_long_order_limit</em>�J�����̒l 
   */
  public  Integer  settle_cont_long_order_limit;

  /** 
   * <em>settle_cont_short_order_limit</em>�J�����̒l 
   */
  public  Integer  settle_cont_short_order_limit;

  /** 
   * <em>open_cont_limit</em>�J�����̒l 
   */
  public  Long  open_cont_limit;

  /** 
   * <em>ifo_deposit_per_unit0</em>�J�����̒l 
   */
  public  double  ifo_deposit_per_unit0;

  /** 
   * <em>ifo_deposit_per_unit1</em>�J�����̒l 
   */
  public  double  ifo_deposit_per_unit1;

  /** 
   * <em>ifo_deposit_per_unit0_red</em>�J�����̒l 
   */
  public  double  ifo_deposit_per_unit0_red;

  /** 
   * <em>ifo_deposit_per_unit1_red</em>�J�����̒l 
   */
  public  double  ifo_deposit_per_unit1_red;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_date;

  private boolean target_product_code_is_set = false;
  private boolean target_product_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean future_option_div_is_set = false;
  private boolean future_option_div_is_modified = false;
  private boolean enable_order_is_set = false;
  private boolean enable_order_is_modified = false;
  private boolean open_cont_long_order_limit_is_set = false;
  private boolean open_cont_long_order_limit_is_modified = false;
  private boolean open_cont_short_order_limit_is_set = false;
  private boolean open_cont_short_order_limit_is_modified = false;
  private boolean settle_cont_long_order_limit_is_set = false;
  private boolean settle_cont_long_order_limit_is_modified = false;
  private boolean settle_cont_short_order_limit_is_set = false;
  private boolean settle_cont_short_order_limit_is_modified = false;
  private boolean open_cont_limit_is_set = false;
  private boolean open_cont_limit_is_modified = false;
  private boolean ifo_deposit_per_unit0_is_set = false;
  private boolean ifo_deposit_per_unit0_is_modified = false;
  private boolean ifo_deposit_per_unit1_is_set = false;
  private boolean ifo_deposit_per_unit1_is_modified = false;
  private boolean ifo_deposit_per_unit0_red_is_set = false;
  private boolean ifo_deposit_per_unit0_red_is_modified = false;
  private boolean ifo_deposit_per_unit1_red_is_set = false;
  private boolean ifo_deposit_per_unit1_red_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_date_is_set = false;
  private boolean last_updated_date_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "target_product_code=" + target_product_code
      + "," + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "market_code=" + market_code
      + "," + "future_option_div=" + future_option_div
      + "," + "enable_order=" +enable_order
      + "," + "open_cont_long_order_limit=" +open_cont_long_order_limit
      + "," + "open_cont_short_order_limit=" +open_cont_short_order_limit
      + "," + "settle_cont_long_order_limit=" +settle_cont_long_order_limit
      + "," + "settle_cont_short_order_limit=" +settle_cont_short_order_limit
      + "," + "open_cont_limit=" +open_cont_limit
      + "," + "ifo_deposit_per_unit0=" +ifo_deposit_per_unit0
      + "," + "ifo_deposit_per_unit1=" +ifo_deposit_per_unit1
      + "," + "ifo_deposit_per_unit0_red=" +ifo_deposit_per_unit0_red
      + "," + "ifo_deposit_per_unit1_red=" +ifo_deposit_per_unit1_red
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_date=" +last_updated_date
      + "}";
  }


  /** 
   * �l�����ݒ��BranchIndexDealtCondParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public BranchIndexDealtCondParams() {
    target_product_code_is_modified = true;
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    market_code_is_modified = true;
    future_option_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���BranchIndexDealtCondRow�I�u�W�F�N�g�̒l�𗘗p����BranchIndexDealtCondParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����BranchIndexDealtCondRow�I�u�W�F�N�g 
   */
  public BranchIndexDealtCondParams( BranchIndexDealtCondRow p_row )
  {
    target_product_code = p_row.getTargetProductCode();
    target_product_code_is_set = p_row.getTargetProductCodeIsSet();
    target_product_code_is_modified = p_row.getTargetProductCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    future_option_div = p_row.getFutureOptionDiv();
    future_option_div_is_set = p_row.getFutureOptionDivIsSet();
    future_option_div_is_modified = p_row.getFutureOptionDivIsModified();
    enable_order = p_row.getEnableOrder();
    enable_order_is_set = p_row.getEnableOrderIsSet();
    enable_order_is_modified = p_row.getEnableOrderIsModified();
    if ( !p_row.getOpenContLongOrderLimitIsNull() )
      open_cont_long_order_limit = new Integer( p_row.getOpenContLongOrderLimit() );
    open_cont_long_order_limit_is_set = p_row.getOpenContLongOrderLimitIsSet();
    open_cont_long_order_limit_is_modified = p_row.getOpenContLongOrderLimitIsModified();
    if ( !p_row.getOpenContShortOrderLimitIsNull() )
      open_cont_short_order_limit = new Integer( p_row.getOpenContShortOrderLimit() );
    open_cont_short_order_limit_is_set = p_row.getOpenContShortOrderLimitIsSet();
    open_cont_short_order_limit_is_modified = p_row.getOpenContShortOrderLimitIsModified();
    if ( !p_row.getSettleContLongOrderLimitIsNull() )
      settle_cont_long_order_limit = new Integer( p_row.getSettleContLongOrderLimit() );
    settle_cont_long_order_limit_is_set = p_row.getSettleContLongOrderLimitIsSet();
    settle_cont_long_order_limit_is_modified = p_row.getSettleContLongOrderLimitIsModified();
    if ( !p_row.getSettleContShortOrderLimitIsNull() )
      settle_cont_short_order_limit = new Integer( p_row.getSettleContShortOrderLimit() );
    settle_cont_short_order_limit_is_set = p_row.getSettleContShortOrderLimitIsSet();
    settle_cont_short_order_limit_is_modified = p_row.getSettleContShortOrderLimitIsModified();
    if ( !p_row.getOpenContLimitIsNull() )
      open_cont_limit = new Long( p_row.getOpenContLimit() );
    open_cont_limit_is_set = p_row.getOpenContLimitIsSet();
    open_cont_limit_is_modified = p_row.getOpenContLimitIsModified();
    ifo_deposit_per_unit0 = p_row.getIfoDepositPerUnit0();
    ifo_deposit_per_unit0_is_set = p_row.getIfoDepositPerUnit0IsSet();
    ifo_deposit_per_unit0_is_modified = p_row.getIfoDepositPerUnit0IsModified();
    ifo_deposit_per_unit1 = p_row.getIfoDepositPerUnit1();
    ifo_deposit_per_unit1_is_set = p_row.getIfoDepositPerUnit1IsSet();
    ifo_deposit_per_unit1_is_modified = p_row.getIfoDepositPerUnit1IsModified();
    ifo_deposit_per_unit0_red = p_row.getIfoDepositPerUnit0Red();
    ifo_deposit_per_unit0_red_is_set = p_row.getIfoDepositPerUnit0RedIsSet();
    ifo_deposit_per_unit0_red_is_modified = p_row.getIfoDepositPerUnit0RedIsModified();
    ifo_deposit_per_unit1_red = p_row.getIfoDepositPerUnit1Red();
    ifo_deposit_per_unit1_red_is_set = p_row.getIfoDepositPerUnit1RedIsSet();
    ifo_deposit_per_unit1_red_is_modified = p_row.getIfoDepositPerUnit1RedIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_date = p_row.getLastUpdatedDate();
    last_updated_date_is_set = p_row.getLastUpdatedDateIsSet();
    last_updated_date_is_modified = p_row.getLastUpdatedDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      enable_order_is_set = true;
      enable_order_is_modified = true;
      open_cont_long_order_limit_is_set = true;
      open_cont_long_order_limit_is_modified = true;
      open_cont_short_order_limit_is_set = true;
      open_cont_short_order_limit_is_modified = true;
      settle_cont_long_order_limit_is_set = true;
      settle_cont_long_order_limit_is_modified = true;
      settle_cont_short_order_limit_is_set = true;
      settle_cont_short_order_limit_is_modified = true;
      open_cont_limit_is_set = true;
      open_cont_limit_is_modified = true;
      ifo_deposit_per_unit0_is_set = true;
      ifo_deposit_per_unit0_is_modified = true;
      ifo_deposit_per_unit1_is_set = true;
      ifo_deposit_per_unit1_is_modified = true;
      ifo_deposit_per_unit0_red_is_set = true;
      ifo_deposit_per_unit0_red_is_modified = true;
      ifo_deposit_per_unit1_red_is_set = true;
      ifo_deposit_per_unit1_red_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_date_is_set = true;
      last_updated_date_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BranchIndexDealtCondRow ) )
       return false;
    return fieldsEqual( (BranchIndexDealtCondRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�BranchIndexDealtCondRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( BranchIndexDealtCondRow row )
  {
    if ( target_product_code == null ) {
      if ( row.getTargetProductCode() != null )
        return false;
    } else if ( !target_product_code.equals( row.getTargetProductCode() ) ) {
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
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( row.getFutureOptionDiv() != null )
        return false;
    } else if ( !future_option_div.equals( row.getFutureOptionDiv() ) ) {
        return false;
    }
    if ( enable_order == null ) {
      if ( row.getEnableOrder() != null )
        return false;
    } else if ( !enable_order.equals( row.getEnableOrder() ) ) {
        return false;
    }
    if ( open_cont_long_order_limit == null ) {
      if ( !row.getOpenContLongOrderLimitIsNull() )
        return false;
    } else if ( row.getOpenContLongOrderLimitIsNull() || ( open_cont_long_order_limit.intValue() != row.getOpenContLongOrderLimit() ) ) {
        return false;
    }
    if ( open_cont_short_order_limit == null ) {
      if ( !row.getOpenContShortOrderLimitIsNull() )
        return false;
    } else if ( row.getOpenContShortOrderLimitIsNull() || ( open_cont_short_order_limit.intValue() != row.getOpenContShortOrderLimit() ) ) {
        return false;
    }
    if ( settle_cont_long_order_limit == null ) {
      if ( !row.getSettleContLongOrderLimitIsNull() )
        return false;
    } else if ( row.getSettleContLongOrderLimitIsNull() || ( settle_cont_long_order_limit.intValue() != row.getSettleContLongOrderLimit() ) ) {
        return false;
    }
    if ( settle_cont_short_order_limit == null ) {
      if ( !row.getSettleContShortOrderLimitIsNull() )
        return false;
    } else if ( row.getSettleContShortOrderLimitIsNull() || ( settle_cont_short_order_limit.intValue() != row.getSettleContShortOrderLimit() ) ) {
        return false;
    }
    if ( open_cont_limit == null ) {
      if ( !row.getOpenContLimitIsNull() )
        return false;
    } else if ( row.getOpenContLimitIsNull() || ( open_cont_limit.longValue() != row.getOpenContLimit() ) ) {
        return false;
    }
    if ( ifo_deposit_per_unit0 != row.getIfoDepositPerUnit0() )
      return false;
    if ( ifo_deposit_per_unit1 != row.getIfoDepositPerUnit1() )
      return false;
    if ( ifo_deposit_per_unit0_red != row.getIfoDepositPerUnit0Red() )
      return false;
    if ( ifo_deposit_per_unit1_red != row.getIfoDepositPerUnit1Red() )
      return false;
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_date == null ) {
      if ( row.getLastUpdatedDate() != null )
        return false;
    } else if ( !last_updated_date.equals( row.getLastUpdatedDate() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (target_product_code!=null? target_product_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (enable_order!=null? enable_order.hashCode(): 0) 
        + (open_cont_long_order_limit!=null? open_cont_long_order_limit.hashCode(): 0) 
        + (open_cont_short_order_limit!=null? open_cont_short_order_limit.hashCode(): 0) 
        + (settle_cont_long_order_limit!=null? settle_cont_long_order_limit.hashCode(): 0) 
        + (settle_cont_short_order_limit!=null? settle_cont_short_order_limit.hashCode(): 0) 
        + (open_cont_limit!=null? open_cont_limit.hashCode(): 0) 
        + ((int) ifo_deposit_per_unit0)
        + ((int) ifo_deposit_per_unit1)
        + ((int) ifo_deposit_per_unit0_red)
        + ((int) ifo_deposit_per_unit1_red)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_date!=null? last_updated_date.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !ifo_deposit_per_unit0_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit0' must be set before inserting.");
		if ( !ifo_deposit_per_unit1_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit1' must be set before inserting.");
		if ( !ifo_deposit_per_unit0_red_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit0_red' must be set before inserting.");
		if ( !ifo_deposit_per_unit1_red_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit1_red' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("target_product_code",target_product_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("market_code",market_code);
		map.put("future_option_div",future_option_div);
		if ( enable_order != null )
			map.put("enable_order",enable_order);
		if ( open_cont_long_order_limit != null )
			map.put("open_cont_long_order_limit",open_cont_long_order_limit);
		if ( open_cont_short_order_limit != null )
			map.put("open_cont_short_order_limit",open_cont_short_order_limit);
		if ( settle_cont_long_order_limit != null )
			map.put("settle_cont_long_order_limit",settle_cont_long_order_limit);
		if ( settle_cont_short_order_limit != null )
			map.put("settle_cont_short_order_limit",settle_cont_short_order_limit);
		if ( open_cont_limit != null )
			map.put("open_cont_limit",open_cont_limit);
		map.put("ifo_deposit_per_unit0",new Double(ifo_deposit_per_unit0));
		map.put("ifo_deposit_per_unit1",new Double(ifo_deposit_per_unit1));
		map.put("ifo_deposit_per_unit0_red",new Double(ifo_deposit_per_unit0_red));
		map.put("ifo_deposit_per_unit1_red",new Double(ifo_deposit_per_unit1_red));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_date_is_set )
			map.put("last_updated_date",last_updated_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( enable_order_is_modified )
			map.put("enable_order",enable_order);
		if ( open_cont_long_order_limit_is_modified )
			map.put("open_cont_long_order_limit",open_cont_long_order_limit);
		if ( open_cont_short_order_limit_is_modified )
			map.put("open_cont_short_order_limit",open_cont_short_order_limit);
		if ( settle_cont_long_order_limit_is_modified )
			map.put("settle_cont_long_order_limit",settle_cont_long_order_limit);
		if ( settle_cont_short_order_limit_is_modified )
			map.put("settle_cont_short_order_limit",settle_cont_short_order_limit);
		if ( open_cont_limit_is_modified )
			map.put("open_cont_limit",open_cont_limit);
		if ( ifo_deposit_per_unit0_is_modified )
			map.put("ifo_deposit_per_unit0",new Double(ifo_deposit_per_unit0));
		if ( ifo_deposit_per_unit1_is_modified )
			map.put("ifo_deposit_per_unit1",new Double(ifo_deposit_per_unit1));
		if ( ifo_deposit_per_unit0_red_is_modified )
			map.put("ifo_deposit_per_unit0_red",new Double(ifo_deposit_per_unit0_red));
		if ( ifo_deposit_per_unit1_red_is_modified )
			map.put("ifo_deposit_per_unit1_red",new Double(ifo_deposit_per_unit1_red));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_date_is_modified )
			map.put("last_updated_date",last_updated_date);
		if (map.size() == 0) {
			map.put("enable_order",enable_order);
			map.put("open_cont_long_order_limit",open_cont_long_order_limit);
			map.put("open_cont_short_order_limit",open_cont_short_order_limit);
			map.put("settle_cont_long_order_limit",settle_cont_long_order_limit);
			map.put("settle_cont_short_order_limit",settle_cont_short_order_limit);
			map.put("open_cont_limit",open_cont_limit);
			if ( ifo_deposit_per_unit0_is_set )
				map.put("ifo_deposit_per_unit0",new Double(ifo_deposit_per_unit0));
			if ( ifo_deposit_per_unit1_is_set )
				map.put("ifo_deposit_per_unit1",new Double(ifo_deposit_per_unit1));
			if ( ifo_deposit_per_unit0_red_is_set )
				map.put("ifo_deposit_per_unit0_red",new Double(ifo_deposit_per_unit0_red));
			if ( ifo_deposit_per_unit1_red_is_set )
				map.put("ifo_deposit_per_unit1_red",new Double(ifo_deposit_per_unit1_red));
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_date_is_set )
				map.put("last_updated_date",last_updated_date);
		}
		return map;
	}


  /** 
   * <em>target_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTargetProductCode()
  {
    return target_product_code;
  }


  /** 
   * <em>target_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTargetProductCodeIsSet() {
    return target_product_code_is_set;
  }


  /** 
   * <em>target_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTargetProductCodeIsModified() {
    return target_product_code_is_modified;
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
   * <em>market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>future_option_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFutureOptionDiv()
  {
    return future_option_div;
  }


  /** 
   * <em>future_option_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFutureOptionDivIsSet() {
    return future_option_div_is_set;
  }


  /** 
   * <em>future_option_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFutureOptionDivIsModified() {
    return future_option_div_is_modified;
  }


  /** 
   * <em>enable_order</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEnableOrder()
  {
    return enable_order;
  }


  /** 
   * <em>enable_order</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableOrderIsSet() {
    return enable_order_is_set;
  }


  /** 
   * <em>enable_order</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableOrderIsModified() {
    return enable_order_is_modified;
  }


  /** 
   * <em>open_cont_long_order_limit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOpenContLongOrderLimit()
  {
    return ( open_cont_long_order_limit==null? ((int)0): open_cont_long_order_limit.intValue() );
  }


  /** 
   * <em>open_cont_long_order_limit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOpenContLongOrderLimitIsNull()
  {
    return open_cont_long_order_limit == null;
  }


  /** 
   * <em>open_cont_long_order_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenContLongOrderLimitIsSet() {
    return open_cont_long_order_limit_is_set;
  }


  /** 
   * <em>open_cont_long_order_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenContLongOrderLimitIsModified() {
    return open_cont_long_order_limit_is_modified;
  }


  /** 
   * <em>open_cont_short_order_limit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOpenContShortOrderLimit()
  {
    return ( open_cont_short_order_limit==null? ((int)0): open_cont_short_order_limit.intValue() );
  }


  /** 
   * <em>open_cont_short_order_limit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOpenContShortOrderLimitIsNull()
  {
    return open_cont_short_order_limit == null;
  }


  /** 
   * <em>open_cont_short_order_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenContShortOrderLimitIsSet() {
    return open_cont_short_order_limit_is_set;
  }


  /** 
   * <em>open_cont_short_order_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenContShortOrderLimitIsModified() {
    return open_cont_short_order_limit_is_modified;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSettleContLongOrderLimit()
  {
    return ( settle_cont_long_order_limit==null? ((int)0): settle_cont_long_order_limit.intValue() );
  }


  /** 
   * <em>settle_cont_long_order_limit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSettleContLongOrderLimitIsNull()
  {
    return settle_cont_long_order_limit == null;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettleContLongOrderLimitIsSet() {
    return settle_cont_long_order_limit_is_set;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettleContLongOrderLimitIsModified() {
    return settle_cont_long_order_limit_is_modified;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSettleContShortOrderLimit()
  {
    return ( settle_cont_short_order_limit==null? ((int)0): settle_cont_short_order_limit.intValue() );
  }


  /** 
   * <em>settle_cont_short_order_limit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSettleContShortOrderLimitIsNull()
  {
    return settle_cont_short_order_limit == null;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettleContShortOrderLimitIsSet() {
    return settle_cont_short_order_limit_is_set;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSettleContShortOrderLimitIsModified() {
    return settle_cont_short_order_limit_is_modified;
  }


  /** 
   * <em>open_cont_limit</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOpenContLimit()
  {
    return ( open_cont_limit==null? ((long)0): open_cont_limit.longValue() );
  }


  /** 
   * <em>open_cont_limit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOpenContLimitIsNull()
  {
    return open_cont_limit == null;
  }


  /** 
   * <em>open_cont_limit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenContLimitIsSet() {
    return open_cont_limit_is_set;
  }


  /** 
   * <em>open_cont_limit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenContLimitIsModified() {
    return open_cont_limit_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getIfoDepositPerUnit0()
  {
    return ifo_deposit_per_unit0;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit0IsSet() {
    return ifo_deposit_per_unit0_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit0IsModified() {
    return ifo_deposit_per_unit0_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getIfoDepositPerUnit1()
  {
    return ifo_deposit_per_unit1;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit1IsSet() {
    return ifo_deposit_per_unit1_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit1IsModified() {
    return ifo_deposit_per_unit1_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getIfoDepositPerUnit0Red()
  {
    return ifo_deposit_per_unit0_red;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit0RedIsSet() {
    return ifo_deposit_per_unit0_red_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit0RedIsModified() {
    return ifo_deposit_per_unit0_red_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getIfoDepositPerUnit1Red()
  {
    return ifo_deposit_per_unit1_red;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit1RedIsSet() {
    return ifo_deposit_per_unit1_red_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoDepositPerUnit1RedIsModified() {
    return ifo_deposit_per_unit1_red_is_modified;
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
   * <em>last_updated_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedDate()
  {
    return last_updated_date;
  }


  /** 
   * <em>last_updated_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedDateIsSet() {
    return last_updated_date_is_set;
  }


  /** 
   * <em>last_updated_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedDateIsModified() {
    return last_updated_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BranchIndexDealtCondPK(target_product_code, institution_code, branch_code, market_code, future_option_div);
  }


  /** 
   * <em>target_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_targetProductCode <em>target_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setTargetProductCode( String p_targetProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_product_code = p_targetProductCode;
    target_product_code_is_set = true;
    target_product_code_is_modified = true;
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
   * <em>market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>future_option_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_futureOptionDiv <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFutureOptionDiv( String p_futureOptionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_option_div = p_futureOptionDiv;
    future_option_div_is_set = true;
    future_option_div_is_modified = true;
  }


  /** 
   * <em>enable_order</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_enableOrder <em>enable_order</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setEnableOrder( String p_enableOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_order = p_enableOrder;
    enable_order_is_set = true;
    enable_order_is_modified = true;
  }


  /** 
   * <em>open_cont_long_order_limit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openContLongOrderLimit <em>open_cont_long_order_limit</em>�J�����̒l������킷5���ȉ���int�l 
   */
  public final void setOpenContLongOrderLimit( int p_openContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_long_order_limit = new Integer(p_openContLongOrderLimit);
    open_cont_long_order_limit_is_set = true;
    open_cont_long_order_limit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>open_cont_long_order_limit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOpenContLongOrderLimit( Integer p_openContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_long_order_limit = p_openContLongOrderLimit;
    open_cont_long_order_limit_is_set = true;
    open_cont_long_order_limit_is_modified = true;
  }


  /** 
   * <em>open_cont_short_order_limit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openContShortOrderLimit <em>open_cont_short_order_limit</em>�J�����̒l������킷5���ȉ���int�l 
   */
  public final void setOpenContShortOrderLimit( int p_openContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_short_order_limit = new Integer(p_openContShortOrderLimit);
    open_cont_short_order_limit_is_set = true;
    open_cont_short_order_limit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>open_cont_short_order_limit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOpenContShortOrderLimit( Integer p_openContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_short_order_limit = p_openContShortOrderLimit;
    open_cont_short_order_limit_is_set = true;
    open_cont_short_order_limit_is_modified = true;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_settleContLongOrderLimit <em>settle_cont_long_order_limit</em>�J�����̒l������킷5���ȉ���int�l 
   */
  public final void setSettleContLongOrderLimit( int p_settleContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_long_order_limit = new Integer(p_settleContLongOrderLimit);
    settle_cont_long_order_limit_is_set = true;
    settle_cont_long_order_limit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>settle_cont_long_order_limit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSettleContLongOrderLimit( Integer p_settleContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_long_order_limit = p_settleContLongOrderLimit;
    settle_cont_long_order_limit_is_set = true;
    settle_cont_long_order_limit_is_modified = true;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_settleContShortOrderLimit <em>settle_cont_short_order_limit</em>�J�����̒l������킷5���ȉ���int�l 
   */
  public final void setSettleContShortOrderLimit( int p_settleContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_short_order_limit = new Integer(p_settleContShortOrderLimit);
    settle_cont_short_order_limit_is_set = true;
    settle_cont_short_order_limit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>settle_cont_short_order_limit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSettleContShortOrderLimit( Integer p_settleContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_short_order_limit = p_settleContShortOrderLimit;
    settle_cont_short_order_limit_is_set = true;
    settle_cont_short_order_limit_is_modified = true;
  }


  /** 
   * <em>open_cont_limit</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openContLimit <em>open_cont_limit</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOpenContLimit( long p_openContLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_limit = new Long(p_openContLimit);
    open_cont_limit_is_set = true;
    open_cont_limit_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>open_cont_limit</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOpenContLimit( Long p_openContLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_limit = p_openContLimit;
    open_cont_limit_is_set = true;
    open_cont_limit_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoDepositPerUnit0 <em>ifo_deposit_per_unit0</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setIfoDepositPerUnit0( double p_ifoDepositPerUnit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit0 = p_ifoDepositPerUnit0;
    ifo_deposit_per_unit0_is_set = true;
    ifo_deposit_per_unit0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoDepositPerUnit1 <em>ifo_deposit_per_unit1</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setIfoDepositPerUnit1( double p_ifoDepositPerUnit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit1 = p_ifoDepositPerUnit1;
    ifo_deposit_per_unit1_is_set = true;
    ifo_deposit_per_unit1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoDepositPerUnit0Red <em>ifo_deposit_per_unit0_red</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setIfoDepositPerUnit0Red( double p_ifoDepositPerUnit0Red )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit0_red = p_ifoDepositPerUnit0Red;
    ifo_deposit_per_unit0_red_is_set = true;
    ifo_deposit_per_unit0_red_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoDepositPerUnit1Red <em>ifo_deposit_per_unit1_red</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setIfoDepositPerUnit1Red( double p_ifoDepositPerUnit1Red )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit1_red = p_ifoDepositPerUnit1Red;
    ifo_deposit_per_unit1_red_is_set = true;
    ifo_deposit_per_unit1_red_is_modified = true;
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
   * <em>last_updated_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedDate <em>last_updated_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedDate( java.sql.Timestamp p_lastUpdatedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_date = p_lastUpdatedDate;
    last_updated_date_is_set = true;
    last_updated_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdatedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_date_is_set = true;
    last_updated_date_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("enable_order") ) {
                    return this.enable_order;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    return this.future_option_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ifo_deposit_per_unit0") ) {
                    return new Double( ifo_deposit_per_unit0 );
                }
                else if ( name.equals("ifo_deposit_per_unit1") ) {
                    return new Double( ifo_deposit_per_unit1 );
                }
                else if ( name.equals("ifo_deposit_per_unit0_red") ) {
                    return new Double( ifo_deposit_per_unit0_red );
                }
                else if ( name.equals("ifo_deposit_per_unit1_red") ) {
                    return new Double( ifo_deposit_per_unit1_red );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    return this.last_updated_date;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("open_cont_long_order_limit") ) {
                    return this.open_cont_long_order_limit;
                }
                else if ( name.equals("open_cont_short_order_limit") ) {
                    return this.open_cont_short_order_limit;
                }
                else if ( name.equals("open_cont_limit") ) {
                    return this.open_cont_limit;
                }
                break;
            case 's':
                if ( name.equals("settle_cont_long_order_limit") ) {
                    return this.settle_cont_long_order_limit;
                }
                else if ( name.equals("settle_cont_short_order_limit") ) {
                    return this.settle_cont_short_order_limit;
                }
                break;
            case 't':
                if ( name.equals("target_product_code") ) {
                    return this.target_product_code;
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
            case 'e':
                if ( name.equals("enable_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enable_order' must be String: '"+value+"' is not." );
                    this.enable_order = (String) value;
                    if (this.enable_order_is_set)
                        this.enable_order_is_modified = true;
                    this.enable_order_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_option_div' must be String: '"+value+"' is not." );
                    this.future_option_div = (String) value;
                    if (this.future_option_div_is_set)
                        this.future_option_div_is_modified = true;
                    this.future_option_div_is_set = true;
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
                else if ( name.equals("ifo_deposit_per_unit0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit0' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit0 = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit0_is_set)
                        this.ifo_deposit_per_unit0_is_modified = true;
                    this.ifo_deposit_per_unit0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_per_unit1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit1' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit1 = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit1_is_set)
                        this.ifo_deposit_per_unit1_is_modified = true;
                    this.ifo_deposit_per_unit1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_per_unit0_red") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit0_red' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit0_red = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit0_red_is_set)
                        this.ifo_deposit_per_unit0_red_is_modified = true;
                    this.ifo_deposit_per_unit0_red_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_per_unit1_red") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit1_red' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit1_red = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit1_red_is_set)
                        this.ifo_deposit_per_unit1_red_is_modified = true;
                    this.ifo_deposit_per_unit1_red_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_date = (java.sql.Timestamp) value;
                    if (this.last_updated_date_is_set)
                        this.last_updated_date_is_modified = true;
                    this.last_updated_date_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_cont_long_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'open_cont_long_order_limit' must be Integer: '"+value+"' is not." );
                    this.open_cont_long_order_limit = (Integer) value;
                    if (this.open_cont_long_order_limit_is_set)
                        this.open_cont_long_order_limit_is_modified = true;
                    this.open_cont_long_order_limit_is_set = true;
                    return;
                }
                else if ( name.equals("open_cont_short_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'open_cont_short_order_limit' must be Integer: '"+value+"' is not." );
                    this.open_cont_short_order_limit = (Integer) value;
                    if (this.open_cont_short_order_limit_is_set)
                        this.open_cont_short_order_limit_is_modified = true;
                    this.open_cont_short_order_limit_is_set = true;
                    return;
                }
                else if ( name.equals("open_cont_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'open_cont_limit' must be Long: '"+value+"' is not." );
                    this.open_cont_limit = (Long) value;
                    if (this.open_cont_limit_is_set)
                        this.open_cont_limit_is_modified = true;
                    this.open_cont_limit_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("settle_cont_long_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'settle_cont_long_order_limit' must be Integer: '"+value+"' is not." );
                    this.settle_cont_long_order_limit = (Integer) value;
                    if (this.settle_cont_long_order_limit_is_set)
                        this.settle_cont_long_order_limit_is_modified = true;
                    this.settle_cont_long_order_limit_is_set = true;
                    return;
                }
                else if ( name.equals("settle_cont_short_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'settle_cont_short_order_limit' must be Integer: '"+value+"' is not." );
                    this.settle_cont_short_order_limit = (Integer) value;
                    if (this.settle_cont_short_order_limit_is_set)
                        this.settle_cont_short_order_limit_is_modified = true;
                    this.settle_cont_short_order_limit_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("target_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_product_code' must be String: '"+value+"' is not." );
                    this.target_product_code = (String) value;
                    if (this.target_product_code_is_set)
                        this.target_product_code_is_modified = true;
                    this.target_product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
