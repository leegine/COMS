head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.18.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	RsvIfoOrderActionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * rsv_ifo_order_action�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link RsvIfoOrderActionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link RsvIfoOrderActionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link RsvIfoOrderActionParams}��{@@link RsvIfoOrderActionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RsvIfoOrderActionPK 
 * @@see RsvIfoOrderActionRow 
 */
public class RsvIfoOrderActionParams extends Params implements RsvIfoOrderActionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rsv_ifo_order_action";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = RsvIfoOrderActionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return RsvIfoOrderActionRow.TYPE;
  }


  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>order_id</em>�J�����̒l 
   */
  public  long  order_id;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  double  quantity;

  /** 
   * <em>limit_price</em>�J�����̒l 
   */
  public  Double  limit_price;

  /** 
   * <em>price_adjust_value</em>�J�����̒l 
   */
  public  Double  price_adjust_value;

  /** 
   * <em>expiration_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  expiration_date;

  /** 
   * <em>order_status</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum  order_status;

  /** 
   * <em>order_open_status</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum  order_open_status;

  /** 
   * <em>expiration_status</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum  expiration_status;

  /** 
   * <em>order_action_serial_no</em>�J�����̒l 
   */
  public  int  order_action_serial_no;

  /** 
   * <em>estimated_price</em>�J�����̒l 
   */
  public  Double  estimated_price;

  /** 
   * <em>order_root_div</em>�J�����̒l 
   */
  public  String  order_root_div;

  /** 
   * <em>trader_id</em>�J�����̒l 
   */
  public  Long  trader_id;

  /** 
   * <em>sonar_trader_code</em>�J�����̒l 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>expiration_date_type</em>�J�����̒l 
   */
  public  String  expiration_date_type;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean limit_price_is_set = false;
  private boolean limit_price_is_modified = false;
  private boolean price_adjust_value_is_set = false;
  private boolean price_adjust_value_is_modified = false;
  private boolean expiration_date_is_set = false;
  private boolean expiration_date_is_modified = false;
  private boolean order_status_is_set = false;
  private boolean order_status_is_modified = false;
  private boolean order_open_status_is_set = false;
  private boolean order_open_status_is_modified = false;
  private boolean expiration_status_is_set = false;
  private boolean expiration_status_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean estimated_price_is_set = false;
  private boolean estimated_price_is_modified = false;
  private boolean order_root_div_is_set = false;
  private boolean order_root_div_is_modified = false;
  private boolean trader_id_is_set = false;
  private boolean trader_id_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean expiration_date_type_is_set = false;
  private boolean expiration_date_type_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "order_id=" +order_id
      + "," + "quantity=" +quantity
      + "," + "limit_price=" +limit_price
      + "," + "price_adjust_value=" +price_adjust_value
      + "," + "expiration_date=" +expiration_date
      + "," + "order_status=" +order_status
      + "," + "order_open_status=" +order_open_status
      + "," + "expiration_status=" +expiration_status
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "estimated_price=" +estimated_price
      + "," + "order_root_div=" +order_root_div
      + "," + "trader_id=" +trader_id
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "expiration_date_type=" +expiration_date_type
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��RsvIfoOrderActionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public RsvIfoOrderActionParams() {
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���RsvIfoOrderActionRow�I�u�W�F�N�g�̒l�𗘗p����RsvIfoOrderActionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����RsvIfoOrderActionRow�I�u�W�F�N�g 
   */
  public RsvIfoOrderActionParams( RsvIfoOrderActionRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    limit_price_is_set = p_row.getLimitPriceIsSet();
    limit_price_is_modified = p_row.getLimitPriceIsModified();
    if ( !p_row.getPriceAdjustValueIsNull() )
      price_adjust_value = new Double( p_row.getPriceAdjustValue() );
    price_adjust_value_is_set = p_row.getPriceAdjustValueIsSet();
    price_adjust_value_is_modified = p_row.getPriceAdjustValueIsModified();
    expiration_date = p_row.getExpirationDate();
    expiration_date_is_set = p_row.getExpirationDateIsSet();
    expiration_date_is_modified = p_row.getExpirationDateIsModified();
    order_status = p_row.getOrderStatus();
    order_status_is_set = p_row.getOrderStatusIsSet();
    order_status_is_modified = p_row.getOrderStatusIsModified();
    order_open_status = p_row.getOrderOpenStatus();
    order_open_status_is_set = p_row.getOrderOpenStatusIsSet();
    order_open_status_is_modified = p_row.getOrderOpenStatusIsModified();
    expiration_status = p_row.getExpirationStatus();
    expiration_status_is_set = p_row.getExpirationStatusIsSet();
    expiration_status_is_modified = p_row.getExpirationStatusIsModified();
    order_action_serial_no = p_row.getOrderActionSerialNo();
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    if ( !p_row.getEstimatedPriceIsNull() )
      estimated_price = new Double( p_row.getEstimatedPrice() );
    estimated_price_is_set = p_row.getEstimatedPriceIsSet();
    estimated_price_is_modified = p_row.getEstimatedPriceIsModified();
    order_root_div = p_row.getOrderRootDiv();
    order_root_div_is_set = p_row.getOrderRootDivIsSet();
    order_root_div_is_modified = p_row.getOrderRootDivIsModified();
    if ( !p_row.getTraderIdIsNull() )
      trader_id = new Long( p_row.getTraderId() );
    trader_id_is_set = p_row.getTraderIdIsSet();
    trader_id_is_modified = p_row.getTraderIdIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    expiration_date_type = p_row.getExpirationDateType();
    expiration_date_type_is_set = p_row.getExpirationDateTypeIsSet();
    expiration_date_type_is_modified = p_row.getExpirationDateTypeIsModified();
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
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      limit_price_is_set = true;
      limit_price_is_modified = true;
      price_adjust_value_is_set = true;
      price_adjust_value_is_modified = true;
      expiration_date_is_set = true;
      expiration_date_is_modified = true;
      order_status_is_set = true;
      order_status_is_modified = true;
      order_open_status_is_set = true;
      order_open_status_is_modified = true;
      expiration_status_is_set = true;
      expiration_status_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      estimated_price_is_set = true;
      estimated_price_is_modified = true;
      order_root_div_is_set = true;
      order_root_div_is_modified = true;
      trader_id_is_set = true;
      trader_id_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      expiration_date_type_is_set = true;
      expiration_date_type_is_modified = true;
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
    if ( !( other instanceof RsvIfoOrderActionRow ) )
       return false;
    return fieldsEqual( (RsvIfoOrderActionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�RsvIfoOrderActionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( RsvIfoOrderActionRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( order_id != row.getOrderId() )
      return false;
    if ( quantity != row.getQuantity() )
      return false;
    if ( limit_price == null ) {
      if ( !row.getLimitPriceIsNull() )
        return false;
    } else if ( row.getLimitPriceIsNull() || ( limit_price.doubleValue() != row.getLimitPrice() ) ) {
        return false;
    }
    if ( price_adjust_value == null ) {
      if ( !row.getPriceAdjustValueIsNull() )
        return false;
    } else if ( row.getPriceAdjustValueIsNull() || ( price_adjust_value.doubleValue() != row.getPriceAdjustValue() ) ) {
        return false;
    }
    if ( expiration_date == null ) {
      if ( row.getExpirationDate() != null )
        return false;
    } else if ( !expiration_date.equals( row.getExpirationDate() ) ) {
        return false;
    }
    if ( order_status == null ) {
      if ( row.getOrderStatus() != null )
        return false;
    } else if ( !order_status.equals( row.getOrderStatus() ) ) {
        return false;
    }
    if ( order_open_status == null ) {
      if ( row.getOrderOpenStatus() != null )
        return false;
    } else if ( !order_open_status.equals( row.getOrderOpenStatus() ) ) {
        return false;
    }
    if ( expiration_status == null ) {
      if ( row.getExpirationStatus() != null )
        return false;
    } else if ( !expiration_status.equals( row.getExpirationStatus() ) ) {
        return false;
    }
    if ( order_action_serial_no != row.getOrderActionSerialNo() )
      return false;
    if ( estimated_price == null ) {
      if ( !row.getEstimatedPriceIsNull() )
        return false;
    } else if ( row.getEstimatedPriceIsNull() || ( estimated_price.doubleValue() != row.getEstimatedPrice() ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( row.getOrderRootDiv() != null )
        return false;
    } else if ( !order_root_div.equals( row.getOrderRootDiv() ) ) {
        return false;
    }
    if ( trader_id == null ) {
      if ( !row.getTraderIdIsNull() )
        return false;
    } else if ( row.getTraderIdIsNull() || ( trader_id.longValue() != row.getTraderId() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( expiration_date_type == null ) {
      if ( row.getExpirationDateType() != null )
        return false;
    } else if ( !expiration_date_type.equals( row.getExpirationDateType() ) ) {
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
      return  ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_id)
        + ((int) quantity)
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (price_adjust_value!=null? price_adjust_value.hashCode(): 0) 
        + (expiration_date!=null? expiration_date.hashCode(): 0) 
        + (order_status!=null? order_status.hashCode(): 0) 
        + (order_open_status!=null? order_open_status.hashCode(): 0) 
        + (expiration_status!=null? expiration_status.hashCode(): 0) 
        + ((int) order_action_serial_no)
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (expiration_date_type!=null? expiration_date_type.hashCode(): 0) 
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
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !order_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_status' must be set before inserting.");
		if ( !order_open_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_open_status' must be set before inserting.");
		if ( !expiration_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'expiration_status' must be set before inserting.");
		if ( !order_action_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_action_serial_no' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("order_id",new Long(order_id));
		map.put("quantity",new Double(quantity));
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( price_adjust_value != null )
			map.put("price_adjust_value",price_adjust_value);
		if ( expiration_date != null )
			map.put("expiration_date",expiration_date);
		map.put("order_status",order_status);
		map.put("order_open_status",order_open_status);
		map.put("expiration_status",expiration_status);
		map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( estimated_price != null )
			map.put("estimated_price",estimated_price);
		if ( order_root_div != null )
			map.put("order_root_div",order_root_div);
		if ( trader_id != null )
			map.put("trader_id",trader_id);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( expiration_date_type != null )
			map.put("expiration_date_type",expiration_date_type);
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
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( limit_price_is_modified )
			map.put("limit_price",limit_price);
		if ( price_adjust_value_is_modified )
			map.put("price_adjust_value",price_adjust_value);
		if ( expiration_date_is_modified )
			map.put("expiration_date",expiration_date);
		if ( order_status_is_modified )
			map.put("order_status",order_status);
		if ( order_open_status_is_modified )
			map.put("order_open_status",order_open_status);
		if ( expiration_status_is_modified )
			map.put("expiration_status",expiration_status);
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( estimated_price_is_modified )
			map.put("estimated_price",estimated_price);
		if ( order_root_div_is_modified )
			map.put("order_root_div",order_root_div);
		if ( trader_id_is_modified )
			map.put("trader_id",trader_id);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( expiration_date_type_is_modified )
			map.put("expiration_date_type",expiration_date_type);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			map.put("limit_price",limit_price);
			map.put("price_adjust_value",price_adjust_value);
			map.put("expiration_date",expiration_date);
			if ( order_status_is_set )
				map.put("order_status",order_status);
			if ( order_open_status_is_set )
				map.put("order_open_status",order_open_status);
			if ( expiration_status_is_set )
				map.put("expiration_status",expiration_status);
			if ( order_action_serial_no_is_set )
				map.put("order_action_serial_no",new Integer(order_action_serial_no));
			map.put("estimated_price",estimated_price);
			map.put("order_root_div",order_root_div);
			map.put("trader_id",trader_id);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("expiration_date_type",expiration_date_type);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>sub_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
  }


  /** 
   * <em>order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderId()
  {
    return order_id;
  }


  /** 
   * <em>order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantity()
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
   * <em>limit_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLimitPrice()
  {
    return ( limit_price==null? ((double)0): limit_price.doubleValue() );
  }


  /** 
   * <em>limit_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLimitPriceIsNull()
  {
    return limit_price == null;
  }


  /** 
   * <em>limit_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLimitPriceIsSet() {
    return limit_price_is_set;
  }


  /** 
   * <em>limit_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLimitPriceIsModified() {
    return limit_price_is_modified;
  }


  /** 
   * <em>price_adjust_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getPriceAdjustValue()
  {
    return ( price_adjust_value==null? ((double)0): price_adjust_value.doubleValue() );
  }


  /** 
   * <em>price_adjust_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPriceAdjustValueIsNull()
  {
    return price_adjust_value == null;
  }


  /** 
   * <em>price_adjust_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceAdjustValueIsSet() {
    return price_adjust_value_is_set;
  }


  /** 
   * <em>price_adjust_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceAdjustValueIsModified() {
    return price_adjust_value_is_modified;
  }


  /** 
   * <em>expiration_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getExpirationDate()
  {
    return expiration_date;
  }


  /** 
   * <em>expiration_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationDateIsSet() {
    return expiration_date_is_set;
  }


  /** 
   * <em>expiration_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationDateIsModified() {
    return expiration_date_is_modified;
  }


  /** 
   * <em>order_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum getOrderStatus()
  {
    return order_status;
  }


  /** 
   * <em>order_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderStatusIsSet() {
    return order_status_is_set;
  }


  /** 
   * <em>order_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderStatusIsModified() {
    return order_status_is_modified;
  }


  /** 
   * <em>order_open_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum getOrderOpenStatus()
  {
    return order_open_status;
  }


  /** 
   * <em>order_open_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderOpenStatusIsSet() {
    return order_open_status_is_set;
  }


  /** 
   * <em>order_open_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderOpenStatusIsModified() {
    return order_open_status_is_modified;
  }


  /** 
   * <em>expiration_status</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum getExpirationStatus()
  {
    return expiration_status;
  }


  /** 
   * <em>expiration_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationStatusIsSet() {
    return expiration_status_is_set;
  }


  /** 
   * <em>expiration_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationStatusIsModified() {
    return expiration_status_is_modified;
  }


  /** 
   * <em>order_action_serial_no</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOrderActionSerialNo()
  {
    return order_action_serial_no;
  }


  /** 
   * <em>order_action_serial_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderActionSerialNoIsSet() {
    return order_action_serial_no_is_set;
  }


  /** 
   * <em>order_action_serial_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderActionSerialNoIsModified() {
    return order_action_serial_no_is_modified;
  }


  /** 
   * <em>estimated_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getEstimatedPrice()
  {
    return ( estimated_price==null? ((double)0): estimated_price.doubleValue() );
  }


  /** 
   * <em>estimated_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getEstimatedPriceIsNull()
  {
    return estimated_price == null;
  }


  /** 
   * <em>estimated_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimatedPriceIsSet() {
    return estimated_price_is_set;
  }


  /** 
   * <em>estimated_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEstimatedPriceIsModified() {
    return estimated_price_is_modified;
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
   * <em>trader_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTraderId()
  {
    return ( trader_id==null? ((long)0): trader_id.longValue() );
  }


  /** 
   * <em>trader_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTraderIdIsNull()
  {
    return trader_id == null;
  }


  /** 
   * <em>trader_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderIdIsSet() {
    return trader_id_is_set;
  }


  /** 
   * <em>trader_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTraderIdIsModified() {
    return trader_id_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>expiration_date_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getExpirationDateType()
  {
    return expiration_date_type;
  }


  /** 
   * <em>expiration_date_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationDateTypeIsSet() {
    return expiration_date_type_is_set;
  }


  /** 
   * <em>expiration_date_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExpirationDateTypeIsModified() {
    return expiration_date_type_is_modified;
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
    throw new UnsupportedOperationException("Table rsv_ifo_order_action has no primary key.");
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
   * <em>sub_account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subAccountId <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * <em>order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderId <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>limit_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_limitPrice <em>limit_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLimitPrice( double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = new Double(p_limitPrice);
    limit_price_is_set = true;
    limit_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>limit_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLimitPrice( Double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = p_limitPrice;
    limit_price_is_set = true;
    limit_price_is_modified = true;
  }


  /** 
   * <em>price_adjust_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_priceAdjustValue <em>price_adjust_value</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setPriceAdjustValue( double p_priceAdjustValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price_adjust_value = new Double(p_priceAdjustValue);
    price_adjust_value_is_set = true;
    price_adjust_value_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>price_adjust_value</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPriceAdjustValue( Double p_priceAdjustValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price_adjust_value = p_priceAdjustValue;
    price_adjust_value_is_set = true;
    price_adjust_value_is_modified = true;
  }


  /** 
   * <em>expiration_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_expirationDate <em>expiration_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setExpirationDate( java.sql.Timestamp p_expirationDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_date = p_expirationDate;
    expiration_date_is_set = true;
    expiration_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>expiration_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setExpirationDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    expiration_date_is_set = true;
    expiration_date_is_modified = true;
  }


  /** 
   * <em>order_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderStatus <em>order_status</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum�l 
   */
  public final void setOrderStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum p_orderStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_status = p_orderStatus;
    order_status_is_set = true;
    order_status_is_modified = true;
  }


  /** 
   * <em>order_open_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderOpenStatus <em>order_open_status</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum�l 
   */
  public final void setOrderOpenStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_open_status = p_orderOpenStatus;
    order_open_status_is_set = true;
    order_open_status_is_modified = true;
  }


  /** 
   * <em>expiration_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_expirationStatus <em>expiration_status</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum�l 
   */
  public final void setExpirationStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum p_expirationStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_status = p_expirationStatus;
    expiration_status_is_set = true;
    expiration_status_is_modified = true;
  }


  /** 
   * <em>order_action_serial_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setOrderActionSerialNo( int p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = p_orderActionSerialNo;
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
  }


  /** 
   * <em>estimated_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_estimatedPrice <em>estimated_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setEstimatedPrice( double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = new Double(p_estimatedPrice);
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>estimated_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setEstimatedPrice( Double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = p_estimatedPrice;
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
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
   * <em>trader_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_traderId <em>trader_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTraderId( long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = new Long(p_traderId);
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>trader_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTraderId( Long p_traderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trader_id = p_traderId;
    trader_id_is_set = true;
    trader_id_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>expiration_date_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_expirationDateType <em>expiration_date_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setExpirationDateType( String p_expirationDateType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    expiration_date_type = p_expirationDateType;
    expiration_date_type_is_set = true;
    expiration_date_type_is_modified = true;
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
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("expiration_date") ) {
                    return this.expiration_date;
                }
                else if ( name.equals("expiration_status") ) {
                    return this.expiration_status;
                }
                else if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                else if ( name.equals("expiration_date_type") ) {
                    return this.expiration_date_type;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    return this.limit_price;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_status") ) {
                    return this.order_status;
                }
                else if ( name.equals("order_open_status") ) {
                    return this.order_open_status;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return new Integer( order_action_serial_no );
                }
                else if ( name.equals("order_root_div") ) {
                    return this.order_root_div;
                }
                break;
            case 'p':
                if ( name.equals("price_adjust_value") ) {
                    return this.price_adjust_value;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    return this.trader_id;
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
                if ( name.equals("expiration_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'expiration_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.expiration_date = (java.sql.Timestamp) value;
                    if (this.expiration_date_is_set)
                        this.expiration_date_is_modified = true;
                    this.expiration_date_is_set = true;
                    return;
                }
                else if ( name.equals("expiration_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) )
                        throw new IllegalArgumentException( "value for 'expiration_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum: '"+value+"' is not." );
                    this.expiration_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum) value;
                    if (this.expiration_status_is_set)
                        this.expiration_status_is_modified = true;
                    this.expiration_status_is_set = true;
                    return;
                }
                else if ( name.equals("estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'estimated_price' must be Double: '"+value+"' is not." );
                    this.estimated_price = (Double) value;
                    if (this.estimated_price_is_set)
                        this.estimated_price_is_modified = true;
                    this.estimated_price_is_set = true;
                    return;
                }
                else if ( name.equals("expiration_date_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'expiration_date_type' must be String: '"+value+"' is not." );
                    this.expiration_date_type = (String) value;
                    if (this.expiration_date_type_is_set)
                        this.expiration_date_type_is_modified = true;
                    this.expiration_date_type_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'limit_price' must be Double: '"+value+"' is not." );
                    this.limit_price = (Double) value;
                    if (this.limit_price_is_set)
                        this.limit_price_is_modified = true;
                    this.limit_price_is_set = true;
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
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum: '"+value+"' is not." );
                    this.order_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum) value;
                    if (this.order_status_is_set)
                        this.order_status_is_modified = true;
                    this.order_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_open_status") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) )
                        throw new IllegalArgumentException( "value for 'order_open_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum: '"+value+"' is not." );
                    this.order_open_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum) value;
                    if (this.order_open_status_is_set)
                        this.order_open_status_is_modified = true;
                    this.order_open_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = ((Integer) value).intValue();
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("order_root_div") ) {
                    if ( value != null )
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
                if ( name.equals("price_adjust_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price_adjust_value' must be Double: '"+value+"' is not." );
                    this.price_adjust_value = (Double) value;
                    if (this.price_adjust_value_is_set)
                        this.price_adjust_value_is_modified = true;
                    this.price_adjust_value_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = ((Double) value).doubleValue();
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trader_id' must be Long: '"+value+"' is not." );
                    this.trader_id = (Long) value;
                    if (this.trader_id_is_set)
                        this.trader_id_is_modified = true;
                    this.trader_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
