head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * sle_send_q�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SleSendQRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SleSendQParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SleSendQParams}��{@@link SleSendQRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleSendQPK 
 * @@see SleSendQRow 
 */
public class SleSendQParams extends Params implements SleSendQRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "sle_send_q";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SleSendQRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SleSendQRow.TYPE;
  }


  /** 
   * <em>queue_id</em>�R�����̒l 
   */
  public  long  queue_id;

  /** 
   * <em>product_type</em>�R�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_code</em>�R�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>broker_name</em>�R�����̒l 
   */
  public  String  broker_name;

  /** 
   * <em>institution_code</em>�R�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�R�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>product_code</em>�R�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>order_id</em>�R�����̒l 
   */
  public  Long  order_id;

  /** 
   * <em>order_unit_id</em>�R�����̒l 
   */
  public  Long  order_unit_id;

  /** 
   * <em>biz_date</em>�R�����̒l 
   */
  public  String  biz_date;

  /** 
   * <em>op_type</em>�R�����̒l 
   */
  public  webbroker3.slebase.enums.SleSendqOpTypeEnum  op_type;

  /** 
   * <em>order_type</em>�R�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>limit_price</em>�R�����̒l 
   */
  public  Double  limit_price;

  /** 
   * <em>quantity</em>�R�����̒l 
   */
  public  Double  quantity;

  /** 
   * <em>change_quantity</em>�R�����̒l 
   */
  public  Double  change_quantity;

  /** 
   * <em>change_limit_price</em>�R�����̒l 
   */
  public  Double  change_limit_price;

  /** 
   * <em>already_execd_quantity</em>�R�����̒l 
   */
  public  Double  already_execd_quantity;

  /** 
   * <em>account_id</em>�R�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>account_code</em>�R�����̒l 
   */
  public  String  account_code;

  /** 
   * <em>sub_account_id</em>�R�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>status</em>�R�����̒l 
   */
  public  webbroker3.slebase.enums.SleSendqProcStatusEnum  status;

  /** 
   * <em>confirmed_by_sle_rcvd_q</em>�R�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  confirmed_by_sle_rcvd_q;

  /** 
   * <em>order_emp_code</em>�R�����̒l 
   */
  public  String  order_emp_code;

  /** 
   * <em>order_request_number</em>�R�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>send_process_date_time</em>�R�����̒l 
   */
  public  java.sql.Timestamp  send_process_date_time;

  /** 
   * <em>created_timestamp</em>�R�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�R�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean queue_id_is_set;
  private boolean product_type_is_set;
  private boolean market_code_is_set;
  private boolean broker_name_is_set;
  private boolean institution_code_is_set;
  private boolean branch_code_is_set;
  private boolean biz_date_is_set;
  private boolean op_type_is_set;
  private boolean order_type_is_set;
  private boolean account_id_is_set;
  private boolean account_code_is_set;
  private boolean sub_account_id_is_set;
  private boolean status_is_set;
  private boolean confirmed_by_sle_rcvd_q_is_set;
  private boolean order_emp_code_is_set;
  private boolean order_request_number_is_set;
  private boolean created_timestamp_is_set;
  private boolean last_updated_timestamp_is_set;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "queue_id=" + queue_id
      + "," + "product_type=" +product_type
      + "," + "market_code=" +market_code
      + "," + "broker_name=" +broker_name
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "product_code=" +product_code
      + "," + "order_id=" +order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "biz_date=" +biz_date
      + "," + "op_type=" +op_type
      + "," + "order_type=" +order_type
      + "," + "limit_price=" +limit_price
      + "," + "quantity=" +quantity
      + "," + "change_quantity=" +change_quantity
      + "," + "change_limit_price=" +change_limit_price
      + "," + "already_execd_quantity=" +already_execd_quantity
      + "," + "account_id=" +account_id
      + "," + "account_code=" +account_code
      + "," + "sub_account_id=" +sub_account_id
      + "," + "status=" +status
      + "," + "confirmed_by_sle_rcvd_q=" +confirmed_by_sle_rcvd_q
      + "," + "order_emp_code=" +order_emp_code
      + "," + "order_request_number=" +order_request_number
      + "," + "send_process_date_time=" +send_process_date_time
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SleSendQParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SleSendQParams() {
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SleSendQRow�I�u�W�F�N�g�̒l�𗘗p����SleSendQParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SleSendQRow�I�u�W�F�N�g 
   */
  public SleSendQParams( SleSendQRow p_row )
  {
    queue_id = p_row.getQueueId();
    queue_id_is_set = p_row.getQueueIdIsSet();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    broker_name = p_row.getBrokerName();
    broker_name_is_set = p_row.getBrokerNameIsSet();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    product_code = p_row.getProductCode();
    if ( !p_row.getOrderIdIsNull() )
      order_id = new Long( p_row.getOrderId() );
    if ( !p_row.getOrderUnitIdIsNull() )
      order_unit_id = new Long( p_row.getOrderUnitId() );
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    op_type = p_row.getOpType();
    op_type_is_set = p_row.getOpTypeIsSet();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    if ( !p_row.getChangeQuantityIsNull() )
      change_quantity = new Double( p_row.getChangeQuantity() );
    if ( !p_row.getChangeLimitPriceIsNull() )
      change_limit_price = new Double( p_row.getChangeLimitPrice() );
    if ( !p_row.getAlreadyExecdQuantityIsNull() )
      already_execd_quantity = new Double( p_row.getAlreadyExecdQuantity() );
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    confirmed_by_sle_rcvd_q = p_row.getConfirmedBySleRcvdQ();
    confirmed_by_sle_rcvd_q_is_set = p_row.getConfirmedBySleRcvdQIsSet();
    order_emp_code = p_row.getOrderEmpCode();
    order_emp_code_is_set = p_row.getOrderEmpCodeIsSet();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    send_process_date_time = p_row.getSendProcessDateTime();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      product_type_is_set = true;
      market_code_is_set = true;
      broker_name_is_set = true;
      institution_code_is_set = true;
      branch_code_is_set = true;
      biz_date_is_set = true;
      op_type_is_set = true;
      order_type_is_set = true;
      account_id_is_set = true;
      account_code_is_set = true;
      sub_account_id_is_set = true;
      status_is_set = true;
      confirmed_by_sle_rcvd_q_is_set = true;
      order_emp_code_is_set = true;
      order_request_number_is_set = true;
      created_timestamp_is_set = true;
      last_updated_timestamp_is_set = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SleSendQRow ) )
       return false;
    return fieldsEqual( (SleSendQRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SleSendQRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SleSendQRow row )
  {
    if ( queue_id != row.getQueueId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( broker_name == null ) {
      if ( row.getBrokerName() != null )
        return false;
    } else if ( !broker_name.equals( row.getBrokerName() ) ) {
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
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( order_id == null ) {
      if ( !row.getOrderIdIsNull() )
        return false;
    } else if ( row.getOrderIdIsNull() || ( order_id.longValue() != row.getOrderId() ) ) {
        return false;
    }
    if ( order_unit_id == null ) {
      if ( !row.getOrderUnitIdIsNull() )
        return false;
    } else if ( row.getOrderUnitIdIsNull() || ( order_unit_id.longValue() != row.getOrderUnitId() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( op_type == null ) {
      if ( row.getOpType() != null )
        return false;
    } else if ( !op_type.equals( row.getOpType() ) ) {
        return false;
    }
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( limit_price == null ) {
      if ( !row.getLimitPriceIsNull() )
        return false;
    } else if ( row.getLimitPriceIsNull() || ( limit_price.doubleValue() != row.getLimitPrice() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( change_quantity == null ) {
      if ( !row.getChangeQuantityIsNull() )
        return false;
    } else if ( row.getChangeQuantityIsNull() || ( change_quantity.doubleValue() != row.getChangeQuantity() ) ) {
        return false;
    }
    if ( change_limit_price == null ) {
      if ( !row.getChangeLimitPriceIsNull() )
        return false;
    } else if ( row.getChangeLimitPriceIsNull() || ( change_limit_price.doubleValue() != row.getChangeLimitPrice() ) ) {
        return false;
    }
    if ( already_execd_quantity == null ) {
      if ( !row.getAlreadyExecdQuantityIsNull() )
        return false;
    } else if ( row.getAlreadyExecdQuantityIsNull() || ( already_execd_quantity.doubleValue() != row.getAlreadyExecdQuantity() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
      return false;
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( confirmed_by_sle_rcvd_q == null ) {
      if ( row.getConfirmedBySleRcvdQ() != null )
        return false;
    } else if ( !confirmed_by_sle_rcvd_q.equals( row.getConfirmedBySleRcvdQ() ) ) {
        return false;
    }
    if ( order_emp_code == null ) {
      if ( row.getOrderEmpCode() != null )
        return false;
    } else if ( !order_emp_code.equals( row.getOrderEmpCode() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( send_process_date_time == null ) {
      if ( row.getSendProcessDateTime() != null )
        return false;
    } else if ( !send_process_date_time.equals( row.getSendProcessDateTime() ) ) {
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
      return  ((int) queue_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (broker_name!=null? broker_name.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (order_id!=null? order_id.hashCode(): 0) 
        + (order_unit_id!=null? order_unit_id.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + (op_type!=null? op_type.hashCode(): 0) 
        + (order_type!=null? order_type.hashCode(): 0) 
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (change_quantity!=null? change_quantity.hashCode(): 0) 
        + (change_limit_price!=null? change_limit_price.hashCode(): 0) 
        + (already_execd_quantity!=null? already_execd_quantity.hashCode(): 0) 
        + ((int) account_id)
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) sub_account_id)
        + (status!=null? status.hashCode(): 0) 
        + (confirmed_by_sle_rcvd_q!=null? confirmed_by_sle_rcvd_q.hashCode(): 0) 
        + (order_emp_code!=null? order_emp_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (send_process_date_time!=null? send_process_date_time.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !broker_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'broker_name' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !op_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'op_type' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
		if ( !confirmed_by_sle_rcvd_q_is_set )
			throw new IllegalArgumentException("non-nullable field 'confirmed_by_sle_rcvd_q' must be set before inserting.");
		if ( !order_emp_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_emp_code' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("queue_id",new Long(queue_id));
		map.put("product_type",product_type);
		map.put("market_code",market_code);
		map.put("broker_name",broker_name);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( order_id != null )
			map.put("order_id",order_id);
		if ( order_unit_id != null )
			map.put("order_unit_id",order_unit_id);
		map.put("biz_date",biz_date);
		map.put("op_type",op_type);
		map.put("order_type",order_type);
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( change_quantity != null )
			map.put("change_quantity",change_quantity);
		if ( change_limit_price != null )
			map.put("change_limit_price",change_limit_price);
		if ( already_execd_quantity != null )
			map.put("already_execd_quantity",already_execd_quantity);
		map.put("account_id",new Long(account_id));
		map.put("account_code",account_code);
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("status",status);
		map.put("confirmed_by_sle_rcvd_q",confirmed_by_sle_rcvd_q);
		map.put("order_emp_code",order_emp_code);
		map.put("order_request_number",order_request_number);
		if ( send_process_date_time != null )
			map.put("send_process_date_time",send_process_date_time);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( product_type_is_set )
			map.put("product_type",product_type);
		if ( market_code_is_set )
			map.put("market_code",market_code);
		if ( broker_name_is_set )
			map.put("broker_name",broker_name);
		if ( institution_code_is_set )
			map.put("institution_code",institution_code);
		if ( branch_code_is_set )
			map.put("branch_code",branch_code);
		map.put("product_code",product_code);
		map.put("order_id",order_id);
		map.put("order_unit_id",order_unit_id);
		if ( biz_date_is_set )
			map.put("biz_date",biz_date);
		if ( op_type_is_set )
			map.put("op_type",op_type);
		if ( order_type_is_set )
			map.put("order_type",order_type);
		map.put("limit_price",limit_price);
		map.put("quantity",quantity);
		map.put("change_quantity",change_quantity);
		map.put("change_limit_price",change_limit_price);
		map.put("already_execd_quantity",already_execd_quantity);
		if ( account_id_is_set )
			map.put("account_id",new Long(account_id));
		if ( account_code_is_set )
			map.put("account_code",account_code);
		if ( sub_account_id_is_set )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( status_is_set )
			map.put("status",status);
		if ( confirmed_by_sle_rcvd_q_is_set )
			map.put("confirmed_by_sle_rcvd_q",confirmed_by_sle_rcvd_q);
		if ( order_emp_code_is_set )
			map.put("order_emp_code",order_emp_code);
		if ( order_request_number_is_set )
			map.put("order_request_number",order_request_number);
		map.put("send_process_date_time",send_process_date_time);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * <em>queue_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getQueueId()
  {
    return queue_id;
  }


  /** 
   * <em>queue_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQueueIdIsSet() {
    return queue_id_is_set;
  }


  /** 
   * <em>product_type</em>�R�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>market_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>broker_name</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBrokerName()
  {
    return broker_name;
  }


  /** 
   * <em>broker_name</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBrokerNameIsSet() {
    return broker_name_is_set;
  }


  /** 
   * <em>institution_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>branch_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>product_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>order_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderId()
  {
    return ( order_id==null? ((long)0): order_id.longValue() );
  }


  /** 
   * <em>order_id</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOrderIdIsNull()
  {
    return order_id == null;
  }


  /** 
   * <em>order_unit_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderUnitId()
  {
    return ( order_unit_id==null? ((long)0): order_unit_id.longValue() );
  }


  /** 
   * <em>order_unit_id</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOrderUnitIdIsNull()
  {
    return order_unit_id == null;
  }


  /** 
   * <em>biz_date</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBizDate()
  {
    return biz_date;
  }


  /** 
   * <em>biz_date</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizDateIsSet() {
    return biz_date_is_set;
  }


  /** 
   * <em>op_type</em>�R�����̒l���擾���܂��B
   * @@return webbroker3.slebase.enums.SleSendqOpTypeEnum�̒l 
   */
  public final webbroker3.slebase.enums.SleSendqOpTypeEnum getOpType()
  {
    return op_type;
  }


  /** 
   * <em>op_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpTypeIsSet() {
    return op_type_is_set;
  }


  /** 
   * <em>order_type</em>�R�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType()
  {
    return order_type;
  }


  /** 
   * <em>order_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderTypeIsSet() {
    return order_type_is_set;
  }


  /** 
   * <em>limit_price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLimitPrice()
  {
    return ( limit_price==null? ((double)0): limit_price.doubleValue() );
  }


  /** 
   * <em>limit_price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLimitPriceIsNull()
  {
    return limit_price == null;
  }


  /** 
   * <em>quantity</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
  }


  /** 
   * <em>change_quantity</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getChangeQuantity()
  {
    return ( change_quantity==null? ((double)0): change_quantity.doubleValue() );
  }


  /** 
   * <em>change_quantity</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public final boolean getChangeQuantityIsNull()
  {
    return change_quantity == null;
  }


  /** 
   * <em>change_limit_price</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getChangeLimitPrice()
  {
    return ( change_limit_price==null? ((double)0): change_limit_price.doubleValue() );
  }


  /** 
   * <em>change_limit_price</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public final boolean getChangeLimitPriceIsNull()
  {
    return change_limit_price == null;
  }


  /** 
   * <em>already_execd_quantity</em>�R�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAlreadyExecdQuantity()
  {
    return ( already_execd_quantity==null? ((double)0): already_execd_quantity.doubleValue() );
  }


  /** 
   * <em>already_execd_quantity</em>�R�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�R�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAlreadyExecdQuantityIsNull()
  {
    return already_execd_quantity == null;
  }


  /** 
   * <em>account_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>sub_account_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>status</em>�R�����̒l���擾���܂��B
   * @@return webbroker3.slebase.enums.SleSendqProcStatusEnum�̒l 
   */
  public final webbroker3.slebase.enums.SleSendqProcStatusEnum getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>�R�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getConfirmedBySleRcvdQ()
  {
    return confirmed_by_sle_rcvd_q;
  }


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConfirmedBySleRcvdQIsSet() {
    return confirmed_by_sle_rcvd_q_is_set;
  }


  /** 
   * <em>order_emp_code</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderEmpCode()
  {
    return order_emp_code;
  }


  /** 
   * <em>order_emp_code</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderEmpCodeIsSet() {
    return order_emp_code_is_set;
  }


  /** 
   * <em>order_request_number</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>send_process_date_time</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSendProcessDateTime()
  {
    return send_process_date_time;
  }


  /** 
   * <em>created_timestamp</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SleSendQPK(queue_id);
  }


  /** 
   * <em>queue_id</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_queueId <em>queue_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public final void setQueueId( long p_queueId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    queue_id = p_queueId;
    queue_id_is_set = true;
  }


  /** 
   * <em>product_type</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productType <em>product_type</em>�R�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
  }


  /** 
   * <em>market_code</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketCode <em>market_code</em>�R�����̒l������킷20���ȉ���String�l 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
  }


  /** 
   * <em>broker_name</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_brokerName <em>broker_name</em>�R�����̒l������킷50���ȉ���String�l 
   */
  public final void setBrokerName( String p_brokerName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    broker_name = p_brokerName;
    broker_name_is_set = true;
  }


  /** 
   * <em>institution_code</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�R�����̒l������킷3���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
  }


  /** 
   * <em>branch_code</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchCode <em>branch_code</em>�R�����̒l������킷3���ȉ���String�l 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
  }


  /** 
   * <em>product_code</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�R�����̒l������킷20���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
  }


  /** 
   * <em>order_id</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderId <em>order_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = new Long(p_orderId);
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>order_id</em>�R�����ɒl��ݒ肵�܂��B 
   */
  public final void setOrderId( Long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
  }


  /** 
   * <em>order_unit_id</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = new Long(p_orderUnitId);
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>order_unit_id</em>�R�����ɒl��ݒ肵�܂��B 
   */
  public final void setOrderUnitId( Long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = p_orderUnitId;
  }


  /** 
   * <em>biz_date</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizDate <em>biz_date</em>�R�����̒l������킷8���ȉ���String�l 
   */
  public final void setBizDate( String p_bizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date = p_bizDate;
    biz_date_is_set = true;
  }


  /** 
   * <em>op_type</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_opType <em>op_type</em>�R�����̒l������킷6���ȉ���webbroker3.slebase.enums.SleSendqOpTypeEnum�l 
   */
  public final void setOpType( webbroker3.slebase.enums.SleSendqOpTypeEnum p_opType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    op_type = p_opType;
    op_type_is_set = true;
  }


  /** 
   * <em>order_type</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderType <em>order_type</em>�R�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum�l 
   */
  public final void setOrderType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
  }


  /** 
   * <em>limit_price</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_limitPrice <em>limit_price</em>�R�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLimitPrice( double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = new Double(p_limitPrice);
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>limit_price</em>�R�����ɒl��ݒ肵�܂��B 
   */
  public final void setLimitPrice( Double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = p_limitPrice;
  }


  /** 
   * <em>quantity</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�R�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>quantity</em>�R�����ɒl��ݒ肵�܂��B 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
  }


  /** 
   * <em>change_quantity</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_changeQuantity <em>change_quantity</em>�R�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setChangeQuantity( double p_changeQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_quantity = new Double(p_changeQuantity);
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>change_quantity</em>�R�����ɒl��ݒ肵�܂��B 
   */
  public final void setChangeQuantity( Double p_changeQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change_quantity = p_changeQuantity;
  }


  /** 
   * <em>change_limit_price</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_changeLimitPrice <em>change_limit_price</em>�R�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setChangeLimitPrice( double p_changeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_limit_price = new Double(p_changeLimitPrice);
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>change_limit_price</em>�R�����ɒl��ݒ肵�܂��B 
   */
  public final void setChangeLimitPrice( Double p_changeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change_limit_price = p_changeLimitPrice;
  }


  /** 
   * <em>already_execd_quantity</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_alreadyExecdQuantity <em>already_execd_quantity</em>�R�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAlreadyExecdQuantity( double p_alreadyExecdQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    already_execd_quantity = new Double(p_alreadyExecdQuantity);
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>already_execd_quantity</em>�R�����ɒl��ݒ肵�܂��B 
   */
  public final void setAlreadyExecdQuantity( Double p_alreadyExecdQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    already_execd_quantity = p_alreadyExecdQuantity;
  }


  /** 
   * <em>account_id</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountId <em>account_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
  }


  /** 
   * <em>account_code</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountCode <em>account_code</em>�R�����̒l������킷20���ȉ���String�l 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
  }


  /** 
   * <em>sub_account_id</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subAccountId <em>sub_account_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
  }


  /** 
   * <em>status</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_status <em>status</em>�R�����̒l������킷6���ȉ���webbroker3.slebase.enums.SleSendqProcStatusEnum�l 
   */
  public final void setStatus( webbroker3.slebase.enums.SleSendqProcStatusEnum p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
  }


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_confirmedBySleRcvdQ <em>confirmed_by_sle_rcvd_q</em>�R�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setConfirmedBySleRcvdQ( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_confirmedBySleRcvdQ )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_by_sle_rcvd_q = p_confirmedBySleRcvdQ;
    confirmed_by_sle_rcvd_q_is_set = true;
  }


  /** 
   * <em>order_emp_code</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderEmpCode <em>order_emp_code</em>�R�����̒l������킷7���ȉ���String�l 
   */
  public final void setOrderEmpCode( String p_orderEmpCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_emp_code = p_orderEmpCode;
    order_emp_code_is_set = true;
  }


  /** 
   * <em>order_request_number</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>�R�����̒l������킷9���ȉ���String�l 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
  }


  /** 
   * <em>send_process_date_time</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sendProcessDateTime <em>send_process_date_time</em>�R�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSendProcessDateTime( java.sql.Timestamp p_sendProcessDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_process_date_time = p_sendProcessDateTime;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>send_process_date_time</em>�R�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSendProcessDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    send_process_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>created_timestamp</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�R�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�R�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
  }


  /** 
   * <em>last_updated_timestamp</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>�R�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_timestamp</em>�R�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("already_execd_quantity") ) {
                    return this.already_execd_quantity;
                }
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("broker_name") ) {
                    return this.broker_name;
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                break;
            case 'c':
                if ( name.equals("change_quantity") ) {
                    return this.change_quantity;
                }
                else if ( name.equals("change_limit_price") ) {
                    return this.change_limit_price;
                }
                else if ( name.equals("confirmed_by_sle_rcvd_q") ) {
                    return this.confirmed_by_sle_rcvd_q;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
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
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                else if ( name.equals("order_unit_id") ) {
                    return this.order_unit_id;
                }
                else if ( name.equals("op_type") ) {
                    return this.op_type;
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                else if ( name.equals("order_emp_code") ) {
                    return this.order_emp_code;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    return new Long( queue_id );
                }
                else if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("send_process_date_time") ) {
                    return this.send_process_date_time;
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
                if ( name.equals("already_execd_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'already_execd_quantity' must be Double: '"+value+"' is not." );
                    this.already_execd_quantity = (Double) value;
                    return;
                }
                else if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    this.account_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("broker_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'broker_name' must be String: '"+value+"' is not." );
                    this.broker_name = (String) value;
                    this.broker_name_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    this.biz_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("change_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change_quantity' must be Double: '"+value+"' is not." );
                    this.change_quantity = (Double) value;
                    return;
                }
                else if ( name.equals("change_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change_limit_price' must be Double: '"+value+"' is not." );
                    this.change_limit_price = (Double) value;
                    return;
                }
                else if ( name.equals("confirmed_by_sle_rcvd_q") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'confirmed_by_sle_rcvd_q' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.confirmed_by_sle_rcvd_q = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    this.confirmed_by_sle_rcvd_q_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'limit_price' must be Double: '"+value+"' is not." );
                    this.limit_price = (Double) value;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = (Long) value;
                    return;
                }
                else if ( name.equals("order_unit_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = (Long) value;
                    return;
                }
                else if ( name.equals("op_type") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleSendqOpTypeEnum) )
                        throw new IllegalArgumentException( "value for 'op_type' must be webbroker3.slebase.enums.SleSendqOpTypeEnum: '"+value+"' is not." );
                    this.op_type = (webbroker3.slebase.enums.SleSendqOpTypeEnum) value;
                    this.op_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    this.order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_emp_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_emp_code' must be String: '"+value+"' is not." );
                    this.order_emp_code = (String) value;
                    this.order_emp_code_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    this.order_request_number_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'queue_id' must be Long: '"+value+"' is not." );
                    this.queue_id = ((Long) value).longValue();
                    this.queue_id_is_set = true;
                    return;
                }
                else if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    this.sub_account_id_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleSendqProcStatusEnum) )
                        throw new IllegalArgumentException( "value for 'status' must be webbroker3.slebase.enums.SleSendqProcStatusEnum: '"+value+"' is not." );
                    this.status = (webbroker3.slebase.enums.SleSendqProcStatusEnum) value;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("send_process_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'send_process_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.send_process_date_time = (java.sql.Timestamp) value;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
