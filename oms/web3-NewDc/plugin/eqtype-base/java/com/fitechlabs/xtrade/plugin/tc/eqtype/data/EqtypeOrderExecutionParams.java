head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeOrderExecutionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * eqtype_order_execution�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link EqtypeOrderExecutionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link EqtypeOrderExecutionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link EqtypeOrderExecutionParams}��{@@link EqtypeOrderExecutionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeOrderExecutionPK 
 * @@see EqtypeOrderExecutionRow 
 */
public class EqtypeOrderExecutionParams extends Params implements EqtypeOrderExecutionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "eqtype_order_execution";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = EqtypeOrderExecutionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return EqtypeOrderExecutionRow.TYPE;
  }


  /** 
   * <em>order_execution_id</em>�J�����̒l 
   */
  public  long  order_execution_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>trader_id</em>�J�����̒l 
   */
  public  Long  trader_id;

  /** 
   * <em>order_id</em>�J�����̒l 
   */
  public  long  order_id;

  /** 
   * <em>order_unit_id</em>�J�����̒l 
   */
  public  long  order_unit_id;

  /** 
   * <em>order_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  Long  market_id;

  /** 
   * <em>delivery_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>exec_serial_no</em>�J�����̒l 
   */
  public  int  exec_serial_no;

  /** 
   * <em>exec_price</em>�J�����̒l 
   */
  public  Double  exec_price;

  /** 
   * <em>exec_quantity</em>�J�����̒l 
   */
  public  double  exec_quantity;

  /** 
   * <em>exec_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  exec_timestamp;

  /** 
   * <em>delete_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>biz_date</em>�J�����̒l 
   */
  public  String  biz_date;

  /** 
   * <em>quantity_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum  quantity_type;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>order_request_number</em>�J�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean order_execution_id_is_set = false;
  private boolean order_execution_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean trader_id_is_set = false;
  private boolean trader_id_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean exec_serial_no_is_set = false;
  private boolean exec_serial_no_is_modified = false;
  private boolean exec_price_is_set = false;
  private boolean exec_price_is_modified = false;
  private boolean exec_quantity_is_set = false;
  private boolean exec_quantity_is_modified = false;
  private boolean exec_timestamp_is_set = false;
  private boolean exec_timestamp_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean quantity_type_is_set = false;
  private boolean quantity_type_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "order_execution_id=" + order_execution_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "branch_id=" +branch_id
      + "," + "trader_id=" +trader_id
      + "," + "order_id=" +order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "order_type=" +order_type
      + "," + "product_type=" +product_type
      + "," + "market_id=" +market_id
      + "," + "delivery_date=" +delivery_date
      + "," + "exec_serial_no=" +exec_serial_no
      + "," + "exec_price=" +exec_price
      + "," + "exec_quantity=" +exec_quantity
      + "," + "exec_timestamp=" +exec_timestamp
      + "," + "delete_flag=" +delete_flag
      + "," + "biz_date=" +biz_date
      + "," + "quantity_type=" +quantity_type
      + "," + "product_id=" +product_id
      + "," + "order_request_number=" +order_request_number
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��EqtypeOrderExecutionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public EqtypeOrderExecutionParams() {
    order_execution_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���EqtypeOrderExecutionRow�I�u�W�F�N�g�̒l�𗘗p����EqtypeOrderExecutionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����EqtypeOrderExecutionRow�I�u�W�F�N�g 
   */
  public EqtypeOrderExecutionParams( EqtypeOrderExecutionRow p_row )
  {
    order_execution_id = p_row.getOrderExecutionId();
    order_execution_id_is_set = p_row.getOrderExecutionIdIsSet();
    order_execution_id_is_modified = p_row.getOrderExecutionIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    if ( !p_row.getTraderIdIsNull() )
      trader_id = new Long( p_row.getTraderId() );
    trader_id_is_set = p_row.getTraderIdIsSet();
    trader_id_is_modified = p_row.getTraderIdIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    order_unit_id = p_row.getOrderUnitId();
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getMarketIdIsNull() )
      market_id = new Long( p_row.getMarketId() );
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    exec_serial_no = p_row.getExecSerialNo();
    exec_serial_no_is_set = p_row.getExecSerialNoIsSet();
    exec_serial_no_is_modified = p_row.getExecSerialNoIsModified();
    if ( !p_row.getExecPriceIsNull() )
      exec_price = new Double( p_row.getExecPrice() );
    exec_price_is_set = p_row.getExecPriceIsSet();
    exec_price_is_modified = p_row.getExecPriceIsModified();
    exec_quantity = p_row.getExecQuantity();
    exec_quantity_is_set = p_row.getExecQuantityIsSet();
    exec_quantity_is_modified = p_row.getExecQuantityIsModified();
    exec_timestamp = p_row.getExecTimestamp();
    exec_timestamp_is_set = p_row.getExecTimestampIsSet();
    exec_timestamp_is_modified = p_row.getExecTimestampIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
    quantity_type = p_row.getQuantityType();
    quantity_type_is_set = p_row.getQuantityTypeIsSet();
    quantity_type_is_modified = p_row.getQuantityTypeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
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
      branch_id_is_set = true;
      branch_id_is_modified = true;
      trader_id_is_set = true;
      trader_id_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_unit_id_is_set = true;
      order_unit_id_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      exec_serial_no_is_set = true;
      exec_serial_no_is_modified = true;
      exec_price_is_set = true;
      exec_price_is_modified = true;
      exec_quantity_is_set = true;
      exec_quantity_is_modified = true;
      exec_timestamp_is_set = true;
      exec_timestamp_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
      quantity_type_is_set = true;
      quantity_type_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
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
    if ( !( other instanceof EqtypeOrderExecutionRow ) )
       return false;
    return fieldsEqual( (EqtypeOrderExecutionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�EqtypeOrderExecutionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( EqtypeOrderExecutionRow row )
  {
    if ( order_execution_id != row.getOrderExecutionId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( trader_id == null ) {
      if ( !row.getTraderIdIsNull() )
        return false;
    } else if ( row.getTraderIdIsNull() || ( trader_id.longValue() != row.getTraderId() ) ) {
        return false;
    }
    if ( order_id != row.getOrderId() )
      return false;
    if ( order_unit_id != row.getOrderUnitId() )
      return false;
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( market_id == null ) {
      if ( !row.getMarketIdIsNull() )
        return false;
    } else if ( row.getMarketIdIsNull() || ( market_id.longValue() != row.getMarketId() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( exec_serial_no != row.getExecSerialNo() )
      return false;
    if ( exec_price == null ) {
      if ( !row.getExecPriceIsNull() )
        return false;
    } else if ( row.getExecPriceIsNull() || ( exec_price.doubleValue() != row.getExecPrice() ) ) {
        return false;
    }
    if ( exec_quantity != row.getExecQuantity() )
      return false;
    if ( exec_timestamp == null ) {
      if ( row.getExecTimestamp() != null )
        return false;
    } else if ( !exec_timestamp.equals( row.getExecTimestamp() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( quantity_type == null ) {
      if ( row.getQuantityType() != null )
        return false;
    } else if ( !quantity_type.equals( row.getQuantityType() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
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
      return  ((int) order_execution_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) branch_id)
        + (trader_id!=null? trader_id.hashCode(): 0) 
        + ((int) order_id)
        + ((int) order_unit_id)
        + (order_type!=null? order_type.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + ((int) exec_serial_no)
        + (exec_price!=null? exec_price.hashCode(): 0) 
        + ((int) exec_quantity)
        + (exec_timestamp!=null? exec_timestamp.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + (quantity_type!=null? quantity_type.hashCode(): 0) 
        + ((int) product_id)
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
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
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !order_unit_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_unit_id' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !exec_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'exec_serial_no' must be set before inserting.");
		if ( !exec_quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'exec_quantity' must be set before inserting.");
		if ( !exec_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'exec_timestamp' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
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
		map.put("order_execution_id",new Long(order_execution_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("branch_id",new Long(branch_id));
		if ( trader_id != null )
			map.put("trader_id",trader_id);
		map.put("order_id",new Long(order_id));
		map.put("order_unit_id",new Long(order_unit_id));
		map.put("order_type",order_type);
		map.put("product_type",product_type);
		if ( market_id != null )
			map.put("market_id",market_id);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		map.put("exec_serial_no",new Integer(exec_serial_no));
		if ( exec_price != null )
			map.put("exec_price",exec_price);
		map.put("exec_quantity",new Double(exec_quantity));
		map.put("exec_timestamp",exec_timestamp);
		if ( delete_flag_is_set )
			map.put("delete_flag",delete_flag);
		map.put("biz_date",biz_date);
		if ( quantity_type_is_set )
			map.put("quantity_type",quantity_type);
		map.put("product_id",new Long(product_id));
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		map.put("created_timestamp",created_timestamp);
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
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( trader_id_is_modified )
			map.put("trader_id",trader_id);
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( order_unit_id_is_modified )
			map.put("order_unit_id",new Long(order_unit_id));
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( exec_serial_no_is_modified )
			map.put("exec_serial_no",new Integer(exec_serial_no));
		if ( exec_price_is_modified )
			map.put("exec_price",exec_price);
		if ( exec_quantity_is_modified )
			map.put("exec_quantity",new Double(exec_quantity));
		if ( exec_timestamp_is_modified )
			map.put("exec_timestamp",exec_timestamp);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( quantity_type_is_modified )
			map.put("quantity_type",quantity_type);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			map.put("trader_id",trader_id);
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( order_unit_id_is_set )
				map.put("order_unit_id",new Long(order_unit_id));
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("market_id",market_id);
			map.put("delivery_date",delivery_date);
			if ( exec_serial_no_is_set )
				map.put("exec_serial_no",new Integer(exec_serial_no));
			map.put("exec_price",exec_price);
			if ( exec_quantity_is_set )
				map.put("exec_quantity",new Double(exec_quantity));
			if ( exec_timestamp_is_set )
				map.put("exec_timestamp",exec_timestamp);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( quantity_type_is_set )
				map.put("quantity_type",quantity_type);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			map.put("order_request_number",order_request_number);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>order_execution_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderExecutionId()
  {
    return order_execution_id;
  }


  /** 
   * <em>order_execution_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderExecutionIdIsSet() {
    return order_execution_id_is_set;
  }


  /** 
   * <em>order_execution_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderExecutionIdIsModified() {
    return order_execution_id_is_modified;
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
   * <em>order_unit_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderUnitId()
  {
    return order_unit_id;
  }


  /** 
   * <em>order_unit_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderUnitIdIsSet() {
    return order_unit_id_is_set;
  }


  /** 
   * <em>order_unit_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderUnitIdIsModified() {
    return order_unit_id_is_modified;
  }


  /** 
   * <em>order_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType()
  {
    return order_type;
  }


  /** 
   * <em>order_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderTypeIsSet() {
    return order_type_is_set;
  }


  /** 
   * <em>order_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderTypeIsModified() {
    return order_type_is_modified;
  }


  /** 
   * <em>product_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMarketId()
  {
    return ( market_id==null? ((long)0): market_id.longValue() );
  }


  /** 
   * <em>market_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMarketIdIsNull()
  {
    return market_id == null;
  }


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>exec_serial_no</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getExecSerialNo()
  {
    return exec_serial_no;
  }


  /** 
   * <em>exec_serial_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecSerialNoIsSet() {
    return exec_serial_no_is_set;
  }


  /** 
   * <em>exec_serial_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecSerialNoIsModified() {
    return exec_serial_no_is_modified;
  }


  /** 
   * <em>exec_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getExecPrice()
  {
    return ( exec_price==null? ((double)0): exec_price.doubleValue() );
  }


  /** 
   * <em>exec_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getExecPriceIsNull()
  {
    return exec_price == null;
  }


  /** 
   * <em>exec_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecPriceIsSet() {
    return exec_price_is_set;
  }


  /** 
   * <em>exec_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecPriceIsModified() {
    return exec_price_is_modified;
  }


  /** 
   * <em>exec_quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getExecQuantity()
  {
    return exec_quantity;
  }


  /** 
   * <em>exec_quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecQuantityIsSet() {
    return exec_quantity_is_set;
  }


  /** 
   * <em>exec_quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecQuantityIsModified() {
    return exec_quantity_is_modified;
  }


  /** 
   * <em>exec_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getExecTimestamp()
  {
    return exec_timestamp;
  }


  /** 
   * <em>exec_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecTimestampIsSet() {
    return exec_timestamp_is_set;
  }


  /** 
   * <em>exec_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecTimestampIsModified() {
    return exec_timestamp_is_modified;
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
   * <em>quantity_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum getQuantityType()
  {
    return quantity_type;
  }


  /** 
   * <em>quantity_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityTypeIsSet() {
    return quantity_type_is_set;
  }


  /** 
   * <em>quantity_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityTypeIsModified() {
    return quantity_type_is_modified;
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
    return new EqtypeOrderExecutionPK(order_execution_id);
  }


  /** 
   * <em>order_execution_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderExecutionId <em>order_execution_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderExecutionId( long p_orderExecutionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_execution_id = p_orderExecutionId;
    order_execution_id_is_set = true;
    order_execution_id_is_modified = true;
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
   * <em>order_unit_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = p_orderUnitId;
    order_unit_id_is_set = true;
    order_unit_id_is_modified = true;
  }


  /** 
   * <em>order_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderType <em>order_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�l 
   */
  public final void setOrderType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
    order_type_is_modified = true;
  }


  /** 
   * <em>product_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productType <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>market_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = new Long(p_marketId);
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>market_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMarketId( Long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deliveryDate <em>delivery_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>delivery_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>exec_serial_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execSerialNo <em>exec_serial_no</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setExecSerialNo( int p_execSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_serial_no = p_execSerialNo;
    exec_serial_no_is_set = true;
    exec_serial_no_is_modified = true;
  }


  /** 
   * <em>exec_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execPrice <em>exec_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setExecPrice( double p_execPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_price = new Double(p_execPrice);
    exec_price_is_set = true;
    exec_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>exec_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setExecPrice( Double p_execPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_price = p_execPrice;
    exec_price_is_set = true;
    exec_price_is_modified = true;
  }


  /** 
   * <em>exec_quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execQuantity <em>exec_quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setExecQuantity( double p_execQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_quantity = p_execQuantity;
    exec_quantity_is_set = true;
    exec_quantity_is_modified = true;
  }


  /** 
   * <em>exec_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execTimestamp <em>exec_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setExecTimestamp( java.sql.Timestamp p_execTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = p_execTimestamp;
    exec_timestamp_is_set = true;
    exec_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>exec_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setExecTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_timestamp_is_set = true;
    exec_timestamp_is_modified = true;
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
   * <em>quantity_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantityType <em>quantity_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum�l 
   */
  public final void setQuantityType( com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum p_quantityType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity_type = p_quantityType;
    quantity_type_is_set = true;
    quantity_type_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'e':
                if ( name.equals("exec_serial_no") ) {
                    return new Integer( exec_serial_no );
                }
                else if ( name.equals("exec_price") ) {
                    return this.exec_price;
                }
                else if ( name.equals("exec_quantity") ) {
                    return new Double( exec_quantity );
                }
                else if ( name.equals("exec_timestamp") ) {
                    return this.exec_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return this.market_id;
                }
                break;
            case 'o':
                if ( name.equals("order_execution_id") ) {
                    return new Long( order_execution_id );
                }
                else if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_unit_id") ) {
                    return new Long( order_unit_id );
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 'q':
                if ( name.equals("quantity_type") ) {
                    return this.quantity_type;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
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
                else if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    if (this.biz_date_is_set)
                        this.biz_date_is_modified = true;
                    this.biz_date_is_set = true;
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
            case 'd':
                if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exec_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'exec_serial_no' must be Integer: '"+value+"' is not." );
                    this.exec_serial_no = ((Integer) value).intValue();
                    if (this.exec_serial_no_is_set)
                        this.exec_serial_no_is_modified = true;
                    this.exec_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("exec_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_price' must be Double: '"+value+"' is not." );
                    this.exec_price = (Double) value;
                    if (this.exec_price_is_set)
                        this.exec_price_is_modified = true;
                    this.exec_price_is_set = true;
                    return;
                }
                else if ( name.equals("exec_quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exec_quantity' must be Double: '"+value+"' is not." );
                    this.exec_quantity = ((Double) value).doubleValue();
                    if (this.exec_quantity_is_set)
                        this.exec_quantity_is_modified = true;
                    this.exec_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("exec_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_timestamp = (java.sql.Timestamp) value;
                    if (this.exec_timestamp_is_set)
                        this.exec_timestamp_is_modified = true;
                    this.exec_timestamp_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("market_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = (Long) value;
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_execution_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_execution_id' must be Long: '"+value+"' is not." );
                    this.order_execution_id = ((Long) value).longValue();
                    if (this.order_execution_id_is_set)
                        this.order_execution_id_is_modified = true;
                    this.order_execution_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_unit_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = ((Long) value).longValue();
                    if (this.order_unit_id_is_set)
                        this.order_unit_id_is_modified = true;
                    this.order_unit_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
                    if ( value != null )
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
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum) )
                        throw new IllegalArgumentException( "value for 'quantity_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum: '"+value+"' is not." );
                    this.quantity_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum) value;
                    if (this.quantity_type_is_set)
                        this.quantity_type_is_modified = true;
                    this.quantity_type_is_set = true;
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
