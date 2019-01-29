head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderExecutedCountParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * order_executed_count�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link OrderExecutedCountRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link OrderExecutedCountParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link OrderExecutedCountParams}��{@@link OrderExecutedCountRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderExecutedCountPK 
 * @@see OrderExecutedCountRow 
 */
public class OrderExecutedCountParams extends Params implements OrderExecutedCountRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "order_executed_count";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = OrderExecutedCountRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return OrderExecutedCountRow.TYPE;
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
   * <em>record_div</em>�J�����̒l 
   */
  public  String  record_div;

  /** 
   * <em>market_code</em>�J�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>product_div</em>�J�����̒l 
   */
  public  String  product_div;

  /** 
   * <em>order_chanel</em>�J�����̒l 
   */
  public  String  order_chanel;

  /** 
   * <em>order_root_div</em>�J�����̒l 
   */
  public  String  order_root_div;

  /** 
   * <em>biz_date</em>�J�����̒l 
   */
  public  String  biz_date;

  /** 
   * <em>buy_order_count</em>�J�����̒l 
   */
  public  int  buy_order_count;

  /** 
   * <em>sell_order_count</em>�J�����̒l 
   */
  public  int  sell_order_count;

  /** 
   * <em>execute_count</em>�J�����̒l 
   */
  public  int  execute_count;

  /** 
   * <em>change_count</em>�J�����̒l 
   */
  public  int  change_count;

  /** 
   * <em>cancel_count</em>�J�����̒l 
   */
  public  int  cancel_count;

  /** 
   * <em>expire_count</em>�J�����̒l 
   */
  public  int  expire_count;

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
  private boolean record_div_is_set = false;
  private boolean record_div_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean product_div_is_set = false;
  private boolean product_div_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean buy_order_count_is_set = false;
  private boolean buy_order_count_is_modified = false;
  private boolean sell_order_count_is_set = false;
  private boolean sell_order_count_is_modified = false;
  private boolean execute_count_is_set = false;
  private boolean execute_count_is_modified = false;
  private boolean change_count_is_set = false;
  private boolean change_count_is_modified = false;
  private boolean cancel_count_is_set = false;
  private boolean cancel_count_is_modified = false;
  private boolean expire_count_is_set = false;
  private boolean expire_count_is_modified = false;
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
      + "," + "record_div=" + record_div
      + "," + "market_code=" + market_code
      + "," + "product_div=" + product_div
      + "," + "order_chanel=" + order_chanel
      + "," + "order_root_div=" + order_root_div
      + "," + "biz_date=" + biz_date
      + "," + "buy_order_count=" +buy_order_count
      + "," + "sell_order_count=" +sell_order_count
      + "," + "execute_count=" +execute_count
      + "," + "change_count=" +change_count
      + "," + "cancel_count=" +cancel_count
      + "," + "expire_count=" +expire_count
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��OrderExecutedCountParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public OrderExecutedCountParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    record_div_is_modified = true;
    market_code_is_modified = true;
    product_div_is_modified = true;
    order_chanel_is_modified = true;
    order_root_div_is_modified = true;
    biz_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���OrderExecutedCountRow�I�u�W�F�N�g�̒l�𗘗p����OrderExecutedCountParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����OrderExecutedCountRow�I�u�W�F�N�g 
   */
  public OrderExecutedCountParams( OrderExecutedCountRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    record_div = p_row.getRecordDiv();
    record_div_is_set = p_row.getRecordDivIsSet();
    record_div_is_modified = p_row.getRecordDivIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    product_div = p_row.getProductDiv();
    product_div_is_set = p_row.getProductDivIsSet();
    product_div_is_modified = p_row.getProductDivIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    buy_order_count = p_row.getBuyOrderCount();
    buy_order_count_is_set = p_row.getBuyOrderCountIsSet();
    buy_order_count_is_modified = p_row.getBuyOrderCountIsModified();
    sell_order_count = p_row.getSellOrderCount();
    sell_order_count_is_set = p_row.getSellOrderCountIsSet();
    sell_order_count_is_modified = p_row.getSellOrderCountIsModified();
    execute_count = p_row.getExecuteCount();
    execute_count_is_set = p_row.getExecuteCountIsSet();
    execute_count_is_modified = p_row.getExecuteCountIsModified();
    change_count = p_row.getChangeCount();
    change_count_is_set = p_row.getChangeCountIsSet();
    change_count_is_modified = p_row.getChangeCountIsModified();
    cancel_count = p_row.getCancelCount();
    cancel_count_is_set = p_row.getCancelCountIsSet();
    cancel_count_is_modified = p_row.getCancelCountIsModified();
    expire_count = p_row.getExpireCount();
    expire_count_is_set = p_row.getExpireCountIsSet();
    expire_count_is_modified = p_row.getExpireCountIsModified();
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
      buy_order_count_is_set = true;
      buy_order_count_is_modified = true;
      sell_order_count_is_set = true;
      sell_order_count_is_modified = true;
      execute_count_is_set = true;
      execute_count_is_modified = true;
      change_count_is_set = true;
      change_count_is_modified = true;
      cancel_count_is_set = true;
      cancel_count_is_modified = true;
      expire_count_is_set = true;
      expire_count_is_modified = true;
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
    if ( !( other instanceof OrderExecutedCountRow ) )
       return false;
    return fieldsEqual( (OrderExecutedCountRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�OrderExecutedCountRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( OrderExecutedCountRow row )
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
    if ( record_div == null ) {
      if ( row.getRecordDiv() != null )
        return false;
    } else if ( !record_div.equals( row.getRecordDiv() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( product_div == null ) {
      if ( row.getProductDiv() != null )
        return false;
    } else if ( !product_div.equals( row.getProductDiv() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( buy_order_count != row.getBuyOrderCount() )
      return false;
    if ( sell_order_count != row.getSellOrderCount() )
      return false;
    if ( execute_count != row.getExecuteCount() )
      return false;
    if ( change_count != row.getChangeCount() )
      return false;
    if ( cancel_count != row.getCancelCount() )
      return false;
    if ( expire_count != row.getExpireCount() )
      return false;
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
        + (record_div!=null? record_div.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (product_div!=null? product_div.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + ((int) buy_order_count)
        + ((int) sell_order_count)
        + ((int) execute_count)
        + ((int) change_count)
        + ((int) cancel_count)
        + ((int) expire_count)
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
		if ( !buy_order_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'buy_order_count' must be set before inserting.");
		if ( !sell_order_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'sell_order_count' must be set before inserting.");
		if ( !execute_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'execute_count' must be set before inserting.");
		if ( !change_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'change_count' must be set before inserting.");
		if ( !cancel_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'cancel_count' must be set before inserting.");
		if ( !expire_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'expire_count' must be set before inserting.");
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
		map.put("record_div",record_div);
		map.put("market_code",market_code);
		map.put("product_div",product_div);
		map.put("order_chanel",order_chanel);
		map.put("order_root_div",order_root_div);
		map.put("biz_date",biz_date);
		map.put("buy_order_count",new Integer(buy_order_count));
		map.put("sell_order_count",new Integer(sell_order_count));
		map.put("execute_count",new Integer(execute_count));
		map.put("change_count",new Integer(change_count));
		map.put("cancel_count",new Integer(cancel_count));
		map.put("expire_count",new Integer(expire_count));
		if ( last_updater != null )
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
		if ( buy_order_count_is_modified )
			map.put("buy_order_count",new Integer(buy_order_count));
		if ( sell_order_count_is_modified )
			map.put("sell_order_count",new Integer(sell_order_count));
		if ( execute_count_is_modified )
			map.put("execute_count",new Integer(execute_count));
		if ( change_count_is_modified )
			map.put("change_count",new Integer(change_count));
		if ( cancel_count_is_modified )
			map.put("cancel_count",new Integer(cancel_count));
		if ( expire_count_is_modified )
			map.put("expire_count",new Integer(expire_count));
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( buy_order_count_is_set )
				map.put("buy_order_count",new Integer(buy_order_count));
			if ( sell_order_count_is_set )
				map.put("sell_order_count",new Integer(sell_order_count));
			if ( execute_count_is_set )
				map.put("execute_count",new Integer(execute_count));
			if ( change_count_is_set )
				map.put("change_count",new Integer(change_count));
			if ( cancel_count_is_set )
				map.put("cancel_count",new Integer(cancel_count));
			if ( expire_count_is_set )
				map.put("expire_count",new Integer(expire_count));
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
   * <em>record_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRecordDiv()
  {
    return record_div;
  }


  /** 
   * <em>record_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecordDivIsSet() {
    return record_div_is_set;
  }


  /** 
   * <em>record_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecordDivIsModified() {
    return record_div_is_modified;
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
   * <em>product_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductDiv()
  {
    return product_div;
  }


  /** 
   * <em>product_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductDivIsSet() {
    return product_div_is_set;
  }


  /** 
   * <em>product_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductDivIsModified() {
    return product_div_is_modified;
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
   * <em>order_root_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderRootDiv()
  {
    return order_root_div;
  }


  /** 
   * <em>order_root_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRootDivIsSet() {
    return order_root_div_is_set;
  }


  /** 
   * <em>order_root_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRootDivIsModified() {
    return order_root_div_is_modified;
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
   * <em>buy_order_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getBuyOrderCount()
  {
    return buy_order_count;
  }


  /** 
   * <em>buy_order_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyOrderCountIsSet() {
    return buy_order_count_is_set;
  }


  /** 
   * <em>buy_order_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyOrderCountIsModified() {
    return buy_order_count_is_modified;
  }


  /** 
   * <em>sell_order_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSellOrderCount()
  {
    return sell_order_count;
  }


  /** 
   * <em>sell_order_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellOrderCountIsSet() {
    return sell_order_count_is_set;
  }


  /** 
   * <em>sell_order_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellOrderCountIsModified() {
    return sell_order_count_is_modified;
  }


  /** 
   * <em>execute_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getExecuteCount()
  {
    return execute_count;
  }


  /** 
   * <em>execute_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecuteCountIsSet() {
    return execute_count_is_set;
  }


  /** 
   * <em>execute_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecuteCountIsModified() {
    return execute_count_is_modified;
  }


  /** 
   * <em>change_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getChangeCount()
  {
    return change_count;
  }


  /** 
   * <em>change_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeCountIsSet() {
    return change_count_is_set;
  }


  /** 
   * <em>change_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeCountIsModified() {
    return change_count_is_modified;
  }


  /** 
   * <em>cancel_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getCancelCount()
  {
    return cancel_count;
  }


  /** 
   * <em>cancel_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCancelCountIsSet() {
    return cancel_count_is_set;
  }


  /** 
   * <em>cancel_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCancelCountIsModified() {
    return cancel_count_is_modified;
  }


  /** 
   * <em>expire_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getExpireCount()
  {
    return expire_count;
  }


  /** 
   * <em>expire_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpireCountIsSet() {
    return expire_count_is_set;
  }


  /** 
   * <em>expire_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpireCountIsModified() {
    return expire_count_is_modified;
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
    return new OrderExecutedCountPK(institution_code, branch_code, record_div, market_code, product_div, order_chanel, order_root_div, biz_date);
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
   * <em>record_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recordDiv <em>record_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRecordDiv( String p_recordDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    record_div = p_recordDiv;
    record_div_is_set = true;
    record_div_is_modified = true;
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
   * <em>product_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productDiv <em>product_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setProductDiv( String p_productDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_div = p_productDiv;
    product_div_is_set = true;
    product_div_is_modified = true;
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
   * <em>order_root_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderRootDiv <em>order_root_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderRootDiv( String p_orderRootDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_root_div = p_orderRootDiv;
    order_root_div_is_set = true;
    order_root_div_is_modified = true;
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
   * <em>buy_order_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyOrderCount <em>buy_order_count</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setBuyOrderCount( int p_buyOrderCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_order_count = p_buyOrderCount;
    buy_order_count_is_set = true;
    buy_order_count_is_modified = true;
  }


  /** 
   * <em>sell_order_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellOrderCount <em>sell_order_count</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setSellOrderCount( int p_sellOrderCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_order_count = p_sellOrderCount;
    sell_order_count_is_set = true;
    sell_order_count_is_modified = true;
  }


  /** 
   * <em>execute_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_executeCount <em>execute_count</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setExecuteCount( int p_executeCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execute_count = p_executeCount;
    execute_count_is_set = true;
    execute_count_is_modified = true;
  }


  /** 
   * <em>change_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_changeCount <em>change_count</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setChangeCount( int p_changeCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_count = p_changeCount;
    change_count_is_set = true;
    change_count_is_modified = true;
  }


  /** 
   * <em>cancel_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cancelCount <em>cancel_count</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setCancelCount( int p_cancelCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_count = p_cancelCount;
    cancel_count_is_set = true;
    cancel_count_is_modified = true;
  }


  /** 
   * <em>expire_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_expireCount <em>expire_count</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setExpireCount( int p_expireCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expire_count = p_expireCount;
    expire_count_is_set = true;
    expire_count_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                else if ( name.equals("buy_order_count") ) {
                    return new Integer( buy_order_count );
                }
                break;
            case 'c':
                if ( name.equals("change_count") ) {
                    return new Integer( change_count );
                }
                else if ( name.equals("cancel_count") ) {
                    return new Integer( cancel_count );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("execute_count") ) {
                    return new Integer( execute_count );
                }
                else if ( name.equals("expire_count") ) {
                    return new Integer( expire_count );
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
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                break;
            case 'p':
                if ( name.equals("product_div") ) {
                    return this.product_div;
                }
                break;
            case 'r':
                if ( name.equals("record_div") ) {
                    return this.record_div;
                }
                break;
            case 's':
                if ( name.equals("sell_order_count") ) {
                    return new Integer( sell_order_count );
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
                else if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    if (this.biz_date_is_set)
                        this.biz_date_is_modified = true;
                    this.biz_date_is_set = true;
                    return;
                }
                else if ( name.equals("buy_order_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_order_count' must be Integer: '"+value+"' is not." );
                    this.buy_order_count = ((Integer) value).intValue();
                    if (this.buy_order_count_is_set)
                        this.buy_order_count_is_modified = true;
                    this.buy_order_count_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("change_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'change_count' must be Integer: '"+value+"' is not." );
                    this.change_count = ((Integer) value).intValue();
                    if (this.change_count_is_set)
                        this.change_count_is_modified = true;
                    this.change_count_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'cancel_count' must be Integer: '"+value+"' is not." );
                    this.cancel_count = ((Integer) value).intValue();
                    if (this.cancel_count_is_set)
                        this.cancel_count_is_modified = true;
                    this.cancel_count_is_set = true;
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
                if ( name.equals("execute_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'execute_count' must be Integer: '"+value+"' is not." );
                    this.execute_count = ((Integer) value).intValue();
                    if (this.execute_count_is_set)
                        this.execute_count_is_modified = true;
                    this.execute_count_is_set = true;
                    return;
                }
                else if ( name.equals("expire_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'expire_count' must be Integer: '"+value+"' is not." );
                    this.expire_count = ((Integer) value).intValue();
                    if (this.expire_count_is_set)
                        this.expire_count_is_modified = true;
                    this.expire_count_is_set = true;
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
                if ( name.equals("order_chanel") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_chanel' must be String: '"+value+"' is not." );
                    this.order_chanel = (String) value;
                    if (this.order_chanel_is_set)
                        this.order_chanel_is_modified = true;
                    this.order_chanel_is_set = true;
                    return;
                }
                else if ( name.equals("order_root_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_root_div' must be String: '"+value+"' is not." );
                    this.order_root_div = (String) value;
                    if (this.order_root_div_is_set)
                        this.order_root_div_is_modified = true;
                    this.order_root_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_div' must be String: '"+value+"' is not." );
                    this.product_div = (String) value;
                    if (this.product_div_is_set)
                        this.product_div_is_modified = true;
                    this.product_div_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("record_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'record_div' must be String: '"+value+"' is not." );
                    this.record_div = (String) value;
                    if (this.record_div_is_set)
                        this.record_div_is_modified = true;
                    this.record_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sell_order_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_order_count' must be Integer: '"+value+"' is not." );
                    this.sell_order_count = ((Integer) value).intValue();
                    if (this.sell_order_count_is_set)
                        this.sell_order_count_is_modified = true;
                    this.sell_order_count_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
