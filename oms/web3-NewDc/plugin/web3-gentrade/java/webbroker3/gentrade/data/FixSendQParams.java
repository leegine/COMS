head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FixSendQParams.java;


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
 * fix_send_q�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FixSendQRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FixSendQParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FixSendQParams}��{@@link FixSendQRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FixSendQPK 
 * @@see FixSendQRow 
 */
public class FixSendQParams extends Params implements FixSendQRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "fix_send_q";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FixSendQRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FixSendQRow.TYPE;
  }


  /** 
   * <em>queue_id</em>�J�����̒l 
   */
  public  long  queue_id;

  /** 
   * <em>session_id</em>�J�����̒l 
   */
  public  int  session_id;

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
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>order_request_number</em>�J�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>order_action_serial_no</em>�J�����̒l 
   */
  public  int  order_action_serial_no;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>op_type</em>�J�����̒l 
   */
  public  int  op_type;

  /** 
   * <em>cl_ord_id</em>�J�����̒l 
   */
  public  String  cl_ord_id;

  /** 
   * <em>handl_inst</em>�J�����̒l 
   */
  public  String  handl_inst;

  /** 
   * <em>order_id</em>�J�����̒l 
   */
  public  String  order_id;

  /** 
   * <em>order_qty</em>�J�����̒l 
   */
  public  Double  order_qty;

  /** 
   * <em>ord_type</em>�J�����̒l 
   */
  public  String  ord_type;

  /** 
   * <em>orig_cl_ord_id</em>�J�����̒l 
   */
  public  String  orig_cl_ord_id;

  /** 
   * <em>price</em>�J�����̒l 
   */
  public  Double  price;

  /** 
   * <em>order_capacity</em>�J�����̒l 
   */
  public  String  order_capacity;

  /** 
   * <em>side</em>�J�����̒l 
   */
  public  String  side;

  /** 
   * <em>symbol</em>�J�����̒l 
   */
  public  String  symbol;

  /** 
   * <em>time_in_force</em>�J�����̒l 
   */
  public  String  time_in_force;

  /** 
   * <em>transact_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  transact_time;

  /** 
   * <em>ex_destination</em>�J�����̒l 
   */
  public  String  ex_destination;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>confirmed_by_fix_rcvd_q</em>�J�����̒l 
   */
  public  String  confirmed_by_fix_rcvd_q;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>retry_count</em>�J�����̒l 
   */
  public  Integer  retry_count;

  /** 
   * <em>current_session_id</em>�J�����̒l 
   */
  public  Integer  current_session_id;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean queue_id_is_set = false;
  private boolean queue_id_is_modified = false;
  private boolean session_id_is_set = false;
  private boolean session_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean op_type_is_set = false;
  private boolean op_type_is_modified = false;
  private boolean cl_ord_id_is_set = false;
  private boolean cl_ord_id_is_modified = false;
  private boolean handl_inst_is_set = false;
  private boolean handl_inst_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_qty_is_set = false;
  private boolean order_qty_is_modified = false;
  private boolean ord_type_is_set = false;
  private boolean ord_type_is_modified = false;
  private boolean orig_cl_ord_id_is_set = false;
  private boolean orig_cl_ord_id_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean order_capacity_is_set = false;
  private boolean order_capacity_is_modified = false;
  private boolean side_is_set = false;
  private boolean side_is_modified = false;
  private boolean symbol_is_set = false;
  private boolean symbol_is_modified = false;
  private boolean time_in_force_is_set = false;
  private boolean time_in_force_is_modified = false;
  private boolean transact_time_is_set = false;
  private boolean transact_time_is_modified = false;
  private boolean ex_destination_is_set = false;
  private boolean ex_destination_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean confirmed_by_fix_rcvd_q_is_set = false;
  private boolean confirmed_by_fix_rcvd_q_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean retry_count_is_set = false;
  private boolean retry_count_is_modified = false;
  private boolean current_session_id_is_set = false;
  private boolean current_session_id_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "queue_id=" + queue_id
      + "," + "session_id=" +session_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "account_id=" +account_id
      + "," + "order_request_number=" +order_request_number
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "product_type=" +product_type
      + "," + "op_type=" +op_type
      + "," + "cl_ord_id=" +cl_ord_id
      + "," + "handl_inst=" +handl_inst
      + "," + "order_id=" +order_id
      + "," + "order_qty=" +order_qty
      + "," + "ord_type=" +ord_type
      + "," + "orig_cl_ord_id=" +orig_cl_ord_id
      + "," + "price=" +price
      + "," + "order_capacity=" +order_capacity
      + "," + "side=" +side
      + "," + "symbol=" +symbol
      + "," + "time_in_force=" +time_in_force
      + "," + "transact_time=" +transact_time
      + "," + "ex_destination=" +ex_destination
      + "," + "status=" +status
      + "," + "confirmed_by_fix_rcvd_q=" +confirmed_by_fix_rcvd_q
      + "," + "last_updater=" +last_updater
      + "," + "retry_count=" +retry_count
      + "," + "current_session_id=" +current_session_id
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��FixSendQParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FixSendQParams() {
    queue_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FixSendQRow�I�u�W�F�N�g�̒l�𗘗p����FixSendQParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FixSendQRow�I�u�W�F�N�g 
   */
  public FixSendQParams( FixSendQRow p_row )
  {
    queue_id = p_row.getQueueId();
    queue_id_is_set = p_row.getQueueIdIsSet();
    queue_id_is_modified = p_row.getQueueIdIsModified();
    session_id = p_row.getSessionId();
    session_id_is_set = p_row.getSessionIdIsSet();
    session_id_is_modified = p_row.getSessionIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    order_action_serial_no = p_row.getOrderActionSerialNo();
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    op_type = p_row.getOpType();
    op_type_is_set = p_row.getOpTypeIsSet();
    op_type_is_modified = p_row.getOpTypeIsModified();
    cl_ord_id = p_row.getClOrdId();
    cl_ord_id_is_set = p_row.getClOrdIdIsSet();
    cl_ord_id_is_modified = p_row.getClOrdIdIsModified();
    handl_inst = p_row.getHandlInst();
    handl_inst_is_set = p_row.getHandlInstIsSet();
    handl_inst_is_modified = p_row.getHandlInstIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    if ( !p_row.getOrderQtyIsNull() )
      order_qty = new Double( p_row.getOrderQty() );
    order_qty_is_set = p_row.getOrderQtyIsSet();
    order_qty_is_modified = p_row.getOrderQtyIsModified();
    ord_type = p_row.getOrdType();
    ord_type_is_set = p_row.getOrdTypeIsSet();
    ord_type_is_modified = p_row.getOrdTypeIsModified();
    orig_cl_ord_id = p_row.getOrigClOrdId();
    orig_cl_ord_id_is_set = p_row.getOrigClOrdIdIsSet();
    orig_cl_ord_id_is_modified = p_row.getOrigClOrdIdIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    order_capacity = p_row.getOrderCapacity();
    order_capacity_is_set = p_row.getOrderCapacityIsSet();
    order_capacity_is_modified = p_row.getOrderCapacityIsModified();
    side = p_row.getSide();
    side_is_set = p_row.getSideIsSet();
    side_is_modified = p_row.getSideIsModified();
    symbol = p_row.getSymbol();
    symbol_is_set = p_row.getSymbolIsSet();
    symbol_is_modified = p_row.getSymbolIsModified();
    time_in_force = p_row.getTimeInForce();
    time_in_force_is_set = p_row.getTimeInForceIsSet();
    time_in_force_is_modified = p_row.getTimeInForceIsModified();
    transact_time = p_row.getTransactTime();
    transact_time_is_set = p_row.getTransactTimeIsSet();
    transact_time_is_modified = p_row.getTransactTimeIsModified();
    ex_destination = p_row.getExDestination();
    ex_destination_is_set = p_row.getExDestinationIsSet();
    ex_destination_is_modified = p_row.getExDestinationIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    confirmed_by_fix_rcvd_q = p_row.getConfirmedByFixRcvdQ();
    confirmed_by_fix_rcvd_q_is_set = p_row.getConfirmedByFixRcvdQIsSet();
    confirmed_by_fix_rcvd_q_is_modified = p_row.getConfirmedByFixRcvdQIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    if ( !p_row.getRetryCountIsNull() )
      retry_count = new Integer( p_row.getRetryCount() );
    retry_count_is_set = p_row.getRetryCountIsSet();
    retry_count_is_modified = p_row.getRetryCountIsModified();
    if ( !p_row.getCurrentSessionIdIsNull() )
      current_session_id = new Integer( p_row.getCurrentSessionId() );
    current_session_id_is_set = p_row.getCurrentSessionIdIsSet();
    current_session_id_is_modified = p_row.getCurrentSessionIdIsModified();
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
      session_id_is_set = true;
      session_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      op_type_is_set = true;
      op_type_is_modified = true;
      cl_ord_id_is_set = true;
      cl_ord_id_is_modified = true;
      handl_inst_is_set = true;
      handl_inst_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_qty_is_set = true;
      order_qty_is_modified = true;
      ord_type_is_set = true;
      ord_type_is_modified = true;
      orig_cl_ord_id_is_set = true;
      orig_cl_ord_id_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      order_capacity_is_set = true;
      order_capacity_is_modified = true;
      side_is_set = true;
      side_is_modified = true;
      symbol_is_set = true;
      symbol_is_modified = true;
      time_in_force_is_set = true;
      time_in_force_is_modified = true;
      transact_time_is_set = true;
      transact_time_is_modified = true;
      ex_destination_is_set = true;
      ex_destination_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      confirmed_by_fix_rcvd_q_is_set = true;
      confirmed_by_fix_rcvd_q_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      retry_count_is_set = true;
      retry_count_is_modified = true;
      current_session_id_is_set = true;
      current_session_id_is_modified = true;
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
    if ( !( other instanceof FixSendQRow ) )
       return false;
    return fieldsEqual( (FixSendQRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FixSendQRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FixSendQRow row )
  {
    if ( queue_id != row.getQueueId() )
      return false;
    if ( session_id != row.getSessionId() )
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
    if ( account_id != row.getAccountId() )
      return false;
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( order_action_serial_no != row.getOrderActionSerialNo() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( op_type != row.getOpType() )
      return false;
    if ( cl_ord_id == null ) {
      if ( row.getClOrdId() != null )
        return false;
    } else if ( !cl_ord_id.equals( row.getClOrdId() ) ) {
        return false;
    }
    if ( handl_inst == null ) {
      if ( row.getHandlInst() != null )
        return false;
    } else if ( !handl_inst.equals( row.getHandlInst() ) ) {
        return false;
    }
    if ( order_id == null ) {
      if ( row.getOrderId() != null )
        return false;
    } else if ( !order_id.equals( row.getOrderId() ) ) {
        return false;
    }
    if ( order_qty == null ) {
      if ( !row.getOrderQtyIsNull() )
        return false;
    } else if ( row.getOrderQtyIsNull() || ( order_qty.doubleValue() != row.getOrderQty() ) ) {
        return false;
    }
    if ( ord_type == null ) {
      if ( row.getOrdType() != null )
        return false;
    } else if ( !ord_type.equals( row.getOrdType() ) ) {
        return false;
    }
    if ( orig_cl_ord_id == null ) {
      if ( row.getOrigClOrdId() != null )
        return false;
    } else if ( !orig_cl_ord_id.equals( row.getOrigClOrdId() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( order_capacity == null ) {
      if ( row.getOrderCapacity() != null )
        return false;
    } else if ( !order_capacity.equals( row.getOrderCapacity() ) ) {
        return false;
    }
    if ( side == null ) {
      if ( row.getSide() != null )
        return false;
    } else if ( !side.equals( row.getSide() ) ) {
        return false;
    }
    if ( symbol == null ) {
      if ( row.getSymbol() != null )
        return false;
    } else if ( !symbol.equals( row.getSymbol() ) ) {
        return false;
    }
    if ( time_in_force == null ) {
      if ( row.getTimeInForce() != null )
        return false;
    } else if ( !time_in_force.equals( row.getTimeInForce() ) ) {
        return false;
    }
    if ( transact_time == null ) {
      if ( row.getTransactTime() != null )
        return false;
    } else if ( !transact_time.equals( row.getTransactTime() ) ) {
        return false;
    }
    if ( ex_destination == null ) {
      if ( row.getExDestination() != null )
        return false;
    } else if ( !ex_destination.equals( row.getExDestination() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( confirmed_by_fix_rcvd_q == null ) {
      if ( row.getConfirmedByFixRcvdQ() != null )
        return false;
    } else if ( !confirmed_by_fix_rcvd_q.equals( row.getConfirmedByFixRcvdQ() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( retry_count == null ) {
      if ( !row.getRetryCountIsNull() )
        return false;
    } else if ( row.getRetryCountIsNull() || ( retry_count.intValue() != row.getRetryCount() ) ) {
        return false;
    }
    if ( current_session_id == null ) {
      if ( !row.getCurrentSessionIdIsNull() )
        return false;
    } else if ( row.getCurrentSessionIdIsNull() || ( current_session_id.intValue() != row.getCurrentSessionId() ) ) {
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
        + ((int) session_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) account_id)
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + ((int) order_action_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) op_type)
        + (cl_ord_id!=null? cl_ord_id.hashCode(): 0) 
        + (handl_inst!=null? handl_inst.hashCode(): 0) 
        + (order_id!=null? order_id.hashCode(): 0) 
        + (order_qty!=null? order_qty.hashCode(): 0) 
        + (ord_type!=null? ord_type.hashCode(): 0) 
        + (orig_cl_ord_id!=null? orig_cl_ord_id.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (order_capacity!=null? order_capacity.hashCode(): 0) 
        + (side!=null? side.hashCode(): 0) 
        + (symbol!=null? symbol.hashCode(): 0) 
        + (time_in_force!=null? time_in_force.hashCode(): 0) 
        + (transact_time!=null? transact_time.hashCode(): 0) 
        + (ex_destination!=null? ex_destination.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (confirmed_by_fix_rcvd_q!=null? confirmed_by_fix_rcvd_q.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (retry_count!=null? retry_count.hashCode(): 0) 
        + (current_session_id!=null? current_session_id.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !session_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'session_id' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
		if ( !order_action_serial_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_action_serial_no' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !op_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'op_type' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("queue_id",new Long(queue_id));
		map.put("session_id",new Integer(session_id));
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		map.put("account_id",new Long(account_id));
		map.put("order_request_number",order_request_number);
		map.put("order_action_serial_no",new Integer(order_action_serial_no));
		map.put("product_type",product_type);
		map.put("op_type",new Integer(op_type));
		if ( cl_ord_id != null )
			map.put("cl_ord_id",cl_ord_id);
		if ( handl_inst != null )
			map.put("handl_inst",handl_inst);
		if ( order_id != null )
			map.put("order_id",order_id);
		if ( order_qty != null )
			map.put("order_qty",order_qty);
		if ( ord_type != null )
			map.put("ord_type",ord_type);
		if ( orig_cl_ord_id != null )
			map.put("orig_cl_ord_id",orig_cl_ord_id);
		if ( price != null )
			map.put("price",price);
		if ( order_capacity != null )
			map.put("order_capacity",order_capacity);
		if ( side != null )
			map.put("side",side);
		if ( symbol != null )
			map.put("symbol",symbol);
		if ( time_in_force != null )
			map.put("time_in_force",time_in_force);
		if ( transact_time != null )
			map.put("transact_time",transact_time);
		if ( ex_destination != null )
			map.put("ex_destination",ex_destination);
		map.put("status",status);
		if ( confirmed_by_fix_rcvd_q != null )
			map.put("confirmed_by_fix_rcvd_q",confirmed_by_fix_rcvd_q);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( retry_count != null )
			map.put("retry_count",retry_count);
		if ( current_session_id != null )
			map.put("current_session_id",current_session_id);
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
		if ( session_id_is_modified )
			map.put("session_id",new Integer(session_id));
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",new Integer(order_action_serial_no));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( op_type_is_modified )
			map.put("op_type",new Integer(op_type));
		if ( cl_ord_id_is_modified )
			map.put("cl_ord_id",cl_ord_id);
		if ( handl_inst_is_modified )
			map.put("handl_inst",handl_inst);
		if ( order_id_is_modified )
			map.put("order_id",order_id);
		if ( order_qty_is_modified )
			map.put("order_qty",order_qty);
		if ( ord_type_is_modified )
			map.put("ord_type",ord_type);
		if ( orig_cl_ord_id_is_modified )
			map.put("orig_cl_ord_id",orig_cl_ord_id);
		if ( price_is_modified )
			map.put("price",price);
		if ( order_capacity_is_modified )
			map.put("order_capacity",order_capacity);
		if ( side_is_modified )
			map.put("side",side);
		if ( symbol_is_modified )
			map.put("symbol",symbol);
		if ( time_in_force_is_modified )
			map.put("time_in_force",time_in_force);
		if ( transact_time_is_modified )
			map.put("transact_time",transact_time);
		if ( ex_destination_is_modified )
			map.put("ex_destination",ex_destination);
		if ( status_is_modified )
			map.put("status",status);
		if ( confirmed_by_fix_rcvd_q_is_modified )
			map.put("confirmed_by_fix_rcvd_q",confirmed_by_fix_rcvd_q);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( retry_count_is_modified )
			map.put("retry_count",retry_count);
		if ( current_session_id_is_modified )
			map.put("current_session_id",current_session_id);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( session_id_is_set )
				map.put("session_id",new Integer(session_id));
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			if ( order_action_serial_no_is_set )
				map.put("order_action_serial_no",new Integer(order_action_serial_no));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( op_type_is_set )
				map.put("op_type",new Integer(op_type));
			map.put("cl_ord_id",cl_ord_id);
			map.put("handl_inst",handl_inst);
			map.put("order_id",order_id);
			map.put("order_qty",order_qty);
			map.put("ord_type",ord_type);
			map.put("orig_cl_ord_id",orig_cl_ord_id);
			map.put("price",price);
			map.put("order_capacity",order_capacity);
			map.put("side",side);
			map.put("symbol",symbol);
			map.put("time_in_force",time_in_force);
			map.put("transact_time",transact_time);
			map.put("ex_destination",ex_destination);
			if ( status_is_set )
				map.put("status",status);
			map.put("confirmed_by_fix_rcvd_q",confirmed_by_fix_rcvd_q);
			map.put("last_updater",last_updater);
			map.put("retry_count",retry_count);
			map.put("current_session_id",current_session_id);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>queue_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getQueueId()
  {
    return queue_id;
  }


  /** 
   * <em>queue_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQueueIdIsSet() {
    return queue_id_is_set;
  }


  /** 
   * <em>queue_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQueueIdIsModified() {
    return queue_id_is_modified;
  }


  /** 
   * <em>session_id</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSessionId()
  {
    return session_id;
  }


  /** 
   * <em>session_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSessionIdIsSet() {
    return session_id_is_set;
  }


  /** 
   * <em>session_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSessionIdIsModified() {
    return session_id_is_modified;
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
   * <em>op_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOpType()
  {
    return op_type;
  }


  /** 
   * <em>op_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpTypeIsSet() {
    return op_type_is_set;
  }


  /** 
   * <em>op_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpTypeIsModified() {
    return op_type_is_modified;
  }


  /** 
   * <em>cl_ord_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getClOrdId()
  {
    return cl_ord_id;
  }


  /** 
   * <em>cl_ord_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getClOrdIdIsSet() {
    return cl_ord_id_is_set;
  }


  /** 
   * <em>cl_ord_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getClOrdIdIsModified() {
    return cl_ord_id_is_modified;
  }


  /** 
   * <em>handl_inst</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getHandlInst()
  {
    return handl_inst;
  }


  /** 
   * <em>handl_inst</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHandlInstIsSet() {
    return handl_inst_is_set;
  }


  /** 
   * <em>handl_inst</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHandlInstIsModified() {
    return handl_inst_is_modified;
  }


  /** 
   * <em>order_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderId()
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
   * <em>order_qty</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOrderQty()
  {
    return ( order_qty==null? ((double)0): order_qty.doubleValue() );
  }


  /** 
   * <em>order_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOrderQtyIsNull()
  {
    return order_qty == null;
  }


  /** 
   * <em>order_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderQtyIsSet() {
    return order_qty_is_set;
  }


  /** 
   * <em>order_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderQtyIsModified() {
    return order_qty_is_modified;
  }


  /** 
   * <em>ord_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrdType()
  {
    return ord_type;
  }


  /** 
   * <em>ord_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrdTypeIsSet() {
    return ord_type_is_set;
  }


  /** 
   * <em>ord_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrdTypeIsModified() {
    return ord_type_is_modified;
  }


  /** 
   * <em>orig_cl_ord_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrigClOrdId()
  {
    return orig_cl_ord_id;
  }


  /** 
   * <em>orig_cl_ord_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrigClOrdIdIsSet() {
    return orig_cl_ord_id_is_set;
  }


  /** 
   * <em>orig_cl_ord_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrigClOrdIdIsModified() {
    return orig_cl_ord_id_is_modified;
  }


  /** 
   * <em>price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getPrice()
  {
    return ( price==null? ((double)0): price.doubleValue() );
  }


  /** 
   * <em>price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPriceIsNull()
  {
    return price == null;
  }


  /** 
   * <em>price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceIsSet() {
    return price_is_set;
  }


  /** 
   * <em>price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceIsModified() {
    return price_is_modified;
  }


  /** 
   * <em>order_capacity</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderCapacity()
  {
    return order_capacity;
  }


  /** 
   * <em>order_capacity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderCapacityIsSet() {
    return order_capacity_is_set;
  }


  /** 
   * <em>order_capacity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderCapacityIsModified() {
    return order_capacity_is_modified;
  }


  /** 
   * <em>side</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSide()
  {
    return side;
  }


  /** 
   * <em>side</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSideIsSet() {
    return side_is_set;
  }


  /** 
   * <em>side</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSideIsModified() {
    return side_is_modified;
  }


  /** 
   * <em>symbol</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSymbol()
  {
    return symbol;
  }


  /** 
   * <em>symbol</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSymbolIsSet() {
    return symbol_is_set;
  }


  /** 
   * <em>symbol</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSymbolIsModified() {
    return symbol_is_modified;
  }


  /** 
   * <em>time_in_force</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTimeInForce()
  {
    return time_in_force;
  }


  /** 
   * <em>time_in_force</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTimeInForceIsSet() {
    return time_in_force_is_set;
  }


  /** 
   * <em>time_in_force</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTimeInForceIsModified() {
    return time_in_force_is_modified;
  }


  /** 
   * <em>transact_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getTransactTime()
  {
    return transact_time;
  }


  /** 
   * <em>transact_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactTimeIsSet() {
    return transact_time_is_set;
  }


  /** 
   * <em>transact_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactTimeIsModified() {
    return transact_time_is_modified;
  }


  /** 
   * <em>ex_destination</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getExDestination()
  {
    return ex_destination;
  }


  /** 
   * <em>ex_destination</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExDestinationIsSet() {
    return ex_destination_is_set;
  }


  /** 
   * <em>ex_destination</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExDestinationIsModified() {
    return ex_destination_is_modified;
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
   * <em>confirmed_by_fix_rcvd_q</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getConfirmedByFixRcvdQ()
  {
    return confirmed_by_fix_rcvd_q;
  }


  /** 
   * <em>confirmed_by_fix_rcvd_q</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConfirmedByFixRcvdQIsSet() {
    return confirmed_by_fix_rcvd_q_is_set;
  }


  /** 
   * <em>confirmed_by_fix_rcvd_q</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConfirmedByFixRcvdQIsModified() {
    return confirmed_by_fix_rcvd_q_is_modified;
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
   * <em>retry_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getRetryCount()
  {
    return ( retry_count==null? ((int)0): retry_count.intValue() );
  }


  /** 
   * <em>retry_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getRetryCountIsNull()
  {
    return retry_count == null;
  }


  /** 
   * <em>retry_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRetryCountIsSet() {
    return retry_count_is_set;
  }


  /** 
   * <em>retry_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRetryCountIsModified() {
    return retry_count_is_modified;
  }


  /** 
   * <em>current_session_id</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getCurrentSessionId()
  {
    return ( current_session_id==null? ((int)0): current_session_id.intValue() );
  }


  /** 
   * <em>current_session_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCurrentSessionIdIsNull()
  {
    return current_session_id == null;
  }


  /** 
   * <em>current_session_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentSessionIdIsSet() {
    return current_session_id_is_set;
  }


  /** 
   * <em>current_session_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentSessionIdIsModified() {
    return current_session_id_is_modified;
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
    return new FixSendQPK(queue_id);
  }


  /** 
   * <em>queue_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_queueId <em>queue_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setQueueId( long p_queueId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    queue_id = p_queueId;
    queue_id_is_set = true;
    queue_id_is_modified = true;
  }


  /** 
   * <em>session_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sessionId <em>session_id</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setSessionId( int p_sessionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    session_id = p_sessionId;
    session_id_is_set = true;
    session_id_is_modified = true;
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
   * <em>op_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_opType <em>op_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setOpType( int p_opType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    op_type = p_opType;
    op_type_is_set = true;
    op_type_is_modified = true;
  }


  /** 
   * <em>cl_ord_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_clOrdId <em>cl_ord_id</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public final void setClOrdId( String p_clOrdId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cl_ord_id = p_clOrdId;
    cl_ord_id_is_set = true;
    cl_ord_id_is_modified = true;
  }


  /** 
   * <em>handl_inst</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_handlInst <em>handl_inst</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setHandlInst( String p_handlInst )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    handl_inst = p_handlInst;
    handl_inst_is_set = true;
    handl_inst_is_modified = true;
  }


  /** 
   * <em>order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderId <em>order_id</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setOrderId( String p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>order_qty</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderQty <em>order_qty</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOrderQty( double p_orderQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_qty = new Double(p_orderQty);
    order_qty_is_set = true;
    order_qty_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>order_qty</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOrderQty( Double p_orderQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_qty = p_orderQty;
    order_qty_is_set = true;
    order_qty_is_modified = true;
  }


  /** 
   * <em>ord_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ordType <em>ord_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrdType( String p_ordType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_type = p_ordType;
    ord_type_is_set = true;
    ord_type_is_modified = true;
  }


  /** 
   * <em>orig_cl_ord_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_origClOrdId <em>orig_cl_ord_id</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public final void setOrigClOrdId( String p_origClOrdId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    orig_cl_ord_id = p_origClOrdId;
    orig_cl_ord_id_is_set = true;
    orig_cl_ord_id_is_modified = true;
  }


  /** 
   * <em>price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_price <em>price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setPrice( double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Double(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPrice( Double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>order_capacity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderCapacity <em>order_capacity</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderCapacity( String p_orderCapacity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_capacity = p_orderCapacity;
    order_capacity_is_set = true;
    order_capacity_is_modified = true;
  }


  /** 
   * <em>side</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_side <em>side</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSide( String p_side )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    side = p_side;
    side_is_set = true;
    side_is_modified = true;
  }


  /** 
   * <em>symbol</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_symbol <em>symbol</em>�J�����̒l������킷16���ȉ���String�l 
   */
  public final void setSymbol( String p_symbol )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    symbol = p_symbol;
    symbol_is_set = true;
    symbol_is_modified = true;
  }


  /** 
   * <em>time_in_force</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_timeInForce <em>time_in_force</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTimeInForce( String p_timeInForce )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    time_in_force = p_timeInForce;
    time_in_force_is_set = true;
    time_in_force_is_modified = true;
  }


  /** 
   * <em>transact_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transactTime <em>transact_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setTransactTime( java.sql.Timestamp p_transactTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transact_time = p_transactTime;
    transact_time_is_set = true;
    transact_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>transact_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setTransactTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transact_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transact_time_is_set = true;
    transact_time_is_modified = true;
  }


  /** 
   * <em>ex_destination</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_exDestination <em>ex_destination</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setExDestination( String p_exDestination )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ex_destination = p_exDestination;
    ex_destination_is_set = true;
    ex_destination_is_modified = true;
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
   * <em>confirmed_by_fix_rcvd_q</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_confirmedByFixRcvdQ <em>confirmed_by_fix_rcvd_q</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setConfirmedByFixRcvdQ( String p_confirmedByFixRcvdQ )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_by_fix_rcvd_q = p_confirmedByFixRcvdQ;
    confirmed_by_fix_rcvd_q_is_set = true;
    confirmed_by_fix_rcvd_q_is_modified = true;
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
   * <em>retry_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_retryCount <em>retry_count</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setRetryCount( int p_retryCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    retry_count = new Integer(p_retryCount);
    retry_count_is_set = true;
    retry_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>retry_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setRetryCount( Integer p_retryCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    retry_count = p_retryCount;
    retry_count_is_set = true;
    retry_count_is_modified = true;
  }


  /** 
   * <em>current_session_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currentSessionId <em>current_session_id</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setCurrentSessionId( int p_currentSessionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_session_id = new Integer(p_currentSessionId);
    current_session_id_is_set = true;
    current_session_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>current_session_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCurrentSessionId( Integer p_currentSessionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_session_id = p_currentSessionId;
    current_session_id_is_set = true;
    current_session_id_is_modified = true;
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
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("cl_ord_id") ) {
                    return this.cl_ord_id;
                }
                else if ( name.equals("confirmed_by_fix_rcvd_q") ) {
                    return this.confirmed_by_fix_rcvd_q;
                }
                else if ( name.equals("current_session_id") ) {
                    return this.current_session_id;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("ex_destination") ) {
                    return this.ex_destination;
                }
                break;
            case 'h':
                if ( name.equals("handl_inst") ) {
                    return this.handl_inst;
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
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return new Integer( order_action_serial_no );
                }
                else if ( name.equals("op_type") ) {
                    return new Integer( op_type );
                }
                else if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                else if ( name.equals("order_qty") ) {
                    return this.order_qty;
                }
                else if ( name.equals("ord_type") ) {
                    return this.ord_type;
                }
                else if ( name.equals("orig_cl_ord_id") ) {
                    return this.orig_cl_ord_id;
                }
                else if ( name.equals("order_capacity") ) {
                    return this.order_capacity;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    return new Long( queue_id );
                }
                break;
            case 'r':
                if ( name.equals("retry_count") ) {
                    return this.retry_count;
                }
                break;
            case 's':
                if ( name.equals("session_id") ) {
                    return new Integer( session_id );
                }
                else if ( name.equals("side") ) {
                    return this.side;
                }
                else if ( name.equals("symbol") ) {
                    return this.symbol;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("time_in_force") ) {
                    return this.time_in_force;
                }
                else if ( name.equals("transact_time") ) {
                    return this.transact_time;
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
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_id") ) {
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
                if ( name.equals("branch_code") ) {
                    if ( value != null )
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
                if ( name.equals("cl_ord_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cl_ord_id' must be String: '"+value+"' is not." );
                    this.cl_ord_id = (String) value;
                    if (this.cl_ord_id_is_set)
                        this.cl_ord_id_is_modified = true;
                    this.cl_ord_id_is_set = true;
                    return;
                }
                else if ( name.equals("confirmed_by_fix_rcvd_q") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'confirmed_by_fix_rcvd_q' must be String: '"+value+"' is not." );
                    this.confirmed_by_fix_rcvd_q = (String) value;
                    if (this.confirmed_by_fix_rcvd_q_is_set)
                        this.confirmed_by_fix_rcvd_q_is_modified = true;
                    this.confirmed_by_fix_rcvd_q_is_set = true;
                    return;
                }
                else if ( name.equals("current_session_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'current_session_id' must be Integer: '"+value+"' is not." );
                    this.current_session_id = (Integer) value;
                    if (this.current_session_id_is_set)
                        this.current_session_id_is_modified = true;
                    this.current_session_id_is_set = true;
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
                if ( name.equals("ex_destination") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ex_destination' must be String: '"+value+"' is not." );
                    this.ex_destination = (String) value;
                    if (this.ex_destination_is_set)
                        this.ex_destination_is_modified = true;
                    this.ex_destination_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("handl_inst") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'handl_inst' must be String: '"+value+"' is not." );
                    this.handl_inst = (String) value;
                    if (this.handl_inst_is_set)
                        this.handl_inst_is_modified = true;
                    this.handl_inst_is_set = true;
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
                else if ( name.equals("order_action_serial_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = ((Integer) value).intValue();
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("op_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'op_type' must be Integer: '"+value+"' is not." );
                    this.op_type = ((Integer) value).intValue();
                    if (this.op_type_is_set)
                        this.op_type_is_modified = true;
                    this.op_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_id' must be String: '"+value+"' is not." );
                    this.order_id = (String) value;
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'order_qty' must be Double: '"+value+"' is not." );
                    this.order_qty = (Double) value;
                    if (this.order_qty_is_set)
                        this.order_qty_is_modified = true;
                    this.order_qty_is_set = true;
                    return;
                }
                else if ( name.equals("ord_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ord_type' must be String: '"+value+"' is not." );
                    this.ord_type = (String) value;
                    if (this.ord_type_is_set)
                        this.ord_type_is_modified = true;
                    this.ord_type_is_set = true;
                    return;
                }
                else if ( name.equals("orig_cl_ord_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'orig_cl_ord_id' must be String: '"+value+"' is not." );
                    this.orig_cl_ord_id = (String) value;
                    if (this.orig_cl_ord_id_is_set)
                        this.orig_cl_ord_id_is_modified = true;
                    this.orig_cl_ord_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_capacity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_capacity' must be String: '"+value+"' is not." );
                    this.order_capacity = (String) value;
                    if (this.order_capacity_is_set)
                        this.order_capacity_is_modified = true;
                    this.order_capacity_is_set = true;
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
                else if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'queue_id' must be Long: '"+value+"' is not." );
                    this.queue_id = ((Long) value).longValue();
                    if (this.queue_id_is_set)
                        this.queue_id_is_modified = true;
                    this.queue_id_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("retry_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'retry_count' must be Integer: '"+value+"' is not." );
                    this.retry_count = (Integer) value;
                    if (this.retry_count_is_set)
                        this.retry_count_is_modified = true;
                    this.retry_count_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("session_id") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'session_id' must be Integer: '"+value+"' is not." );
                    this.session_id = ((Integer) value).intValue();
                    if (this.session_id_is_set)
                        this.session_id_is_modified = true;
                    this.session_id_is_set = true;
                    return;
                }
                else if ( name.equals("side") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'side' must be String: '"+value+"' is not." );
                    this.side = (String) value;
                    if (this.side_is_set)
                        this.side_is_modified = true;
                    this.side_is_set = true;
                    return;
                }
                else if ( name.equals("symbol") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'symbol' must be String: '"+value+"' is not." );
                    this.symbol = (String) value;
                    if (this.symbol_is_set)
                        this.symbol_is_modified = true;
                    this.symbol_is_set = true;
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
                if ( name.equals("time_in_force") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'time_in_force' must be String: '"+value+"' is not." );
                    this.time_in_force = (String) value;
                    if (this.time_in_force_is_set)
                        this.time_in_force_is_modified = true;
                    this.time_in_force_is_set = true;
                    return;
                }
                else if ( name.equals("transact_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transact_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transact_time = (java.sql.Timestamp) value;
                    if (this.transact_time_is_set)
                        this.transact_time_is_modified = true;
                    this.transact_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
