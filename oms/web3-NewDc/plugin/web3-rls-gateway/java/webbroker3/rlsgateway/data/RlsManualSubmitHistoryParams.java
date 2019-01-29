head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsManualSubmitHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * rls_manual_submit_history�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link RlsManualSubmitHistoryParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link RlsManualSubmitHistoryParams}��{@@link RlsManualSubmitHistoryRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsManualSubmitHistoryPK 
 * @@see RlsManualSubmitHistoryRow 
 */
public class RlsManualSubmitHistoryParams extends Params implements RlsManualSubmitHistoryRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rls_manual_submit_history";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = RlsManualSubmitHistoryRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return RlsManualSubmitHistoryRow.TYPE;
  }


  /** 
   * <em>history_id</em>�J�����̒l 
   */
  public  long  history_id;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>order_type</em>�J�����̒l 
   */
  public  int  order_type;

  /** 
   * <em>order_id</em>�J�����̒l 
   */
  public  long  order_id;

  /** 
   * <em>product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>notify_type</em>�J�����̒l 
   */
  public  String  notify_type;

  /** 
   * <em>parent_order_id</em>�J�����̒l 
   */
  public  Long  parent_order_id;

  /** 
   * <em>parent_product_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  parent_product_type;

  /** 
   * <em>serial_no_in_parent</em>�J�����̒l 
   */
  public  int  serial_no_in_parent;

  /** 
   * <em>order_submit_error_code</em>�J�����̒l 
   */
  public  String  order_submit_error_code;

  /** 
   * <em>hit_tick_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  hit_tick_timestamp;

  /** 
   * <em>rls_hit_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  rls_hit_timestamp;

  /** 
   * <em>order_submit_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  order_submit_timestamp;

  /** 
   * <em>submitter_login_id</em>�J�����̒l 
   */
  public  Long  submitter_login_id;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean history_id_is_set = false;
  private boolean history_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean notify_type_is_set = false;
  private boolean notify_type_is_modified = false;
  private boolean parent_order_id_is_set = false;
  private boolean parent_order_id_is_modified = false;
  private boolean parent_product_type_is_set = false;
  private boolean parent_product_type_is_modified = false;
  private boolean serial_no_in_parent_is_set = false;
  private boolean serial_no_in_parent_is_modified = false;
  private boolean order_submit_error_code_is_set = false;
  private boolean order_submit_error_code_is_modified = false;
  private boolean hit_tick_timestamp_is_set = false;
  private boolean hit_tick_timestamp_is_modified = false;
  private boolean rls_hit_timestamp_is_set = false;
  private boolean rls_hit_timestamp_is_modified = false;
  private boolean order_submit_timestamp_is_set = false;
  private boolean order_submit_timestamp_is_modified = false;
  private boolean submitter_login_id_is_set = false;
  private boolean submitter_login_id_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "history_id=" + history_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "order_type=" +order_type
      + "," + "order_id=" +order_id
      + "," + "product_type=" +product_type
      + "," + "status=" +status
      + "," + "notify_type=" +notify_type
      + "," + "parent_order_id=" +parent_order_id
      + "," + "parent_product_type=" +parent_product_type
      + "," + "serial_no_in_parent=" +serial_no_in_parent
      + "," + "order_submit_error_code=" +order_submit_error_code
      + "," + "hit_tick_timestamp=" +hit_tick_timestamp
      + "," + "rls_hit_timestamp=" +rls_hit_timestamp
      + "," + "order_submit_timestamp=" +order_submit_timestamp
      + "," + "submitter_login_id=" +submitter_login_id
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��RlsManualSubmitHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public RlsManualSubmitHistoryParams() {
    history_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���RlsManualSubmitHistoryRow�I�u�W�F�N�g�̒l�𗘗p����RlsManualSubmitHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����RlsManualSubmitHistoryRow�I�u�W�F�N�g 
   */
  public RlsManualSubmitHistoryParams( RlsManualSubmitHistoryRow p_row )
  {
    history_id = p_row.getHistoryId();
    history_id_is_set = p_row.getHistoryIdIsSet();
    history_id_is_modified = p_row.getHistoryIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    notify_type = p_row.getNotifyType();
    notify_type_is_set = p_row.getNotifyTypeIsSet();
    notify_type_is_modified = p_row.getNotifyTypeIsModified();
    if ( !p_row.getParentOrderIdIsNull() )
      parent_order_id = new Long( p_row.getParentOrderId() );
    parent_order_id_is_set = p_row.getParentOrderIdIsSet();
    parent_order_id_is_modified = p_row.getParentOrderIdIsModified();
    parent_product_type = p_row.getParentProductType();
    parent_product_type_is_set = p_row.getParentProductTypeIsSet();
    parent_product_type_is_modified = p_row.getParentProductTypeIsModified();
    serial_no_in_parent = p_row.getSerialNoInParent();
    serial_no_in_parent_is_set = p_row.getSerialNoInParentIsSet();
    serial_no_in_parent_is_modified = p_row.getSerialNoInParentIsModified();
    order_submit_error_code = p_row.getOrderSubmitErrorCode();
    order_submit_error_code_is_set = p_row.getOrderSubmitErrorCodeIsSet();
    order_submit_error_code_is_modified = p_row.getOrderSubmitErrorCodeIsModified();
    hit_tick_timestamp = p_row.getHitTickTimestamp();
    hit_tick_timestamp_is_set = p_row.getHitTickTimestampIsSet();
    hit_tick_timestamp_is_modified = p_row.getHitTickTimestampIsModified();
    rls_hit_timestamp = p_row.getRlsHitTimestamp();
    rls_hit_timestamp_is_set = p_row.getRlsHitTimestampIsSet();
    rls_hit_timestamp_is_modified = p_row.getRlsHitTimestampIsModified();
    order_submit_timestamp = p_row.getOrderSubmitTimestamp();
    order_submit_timestamp_is_set = p_row.getOrderSubmitTimestampIsSet();
    order_submit_timestamp_is_modified = p_row.getOrderSubmitTimestampIsModified();
    if ( !p_row.getSubmitterLoginIdIsNull() )
      submitter_login_id = new Long( p_row.getSubmitterLoginId() );
    submitter_login_id_is_set = p_row.getSubmitterLoginIdIsSet();
    submitter_login_id_is_modified = p_row.getSubmitterLoginIdIsModified();
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
      order_type_is_set = true;
      order_type_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      notify_type_is_set = true;
      notify_type_is_modified = true;
      parent_order_id_is_set = true;
      parent_order_id_is_modified = true;
      parent_product_type_is_set = true;
      parent_product_type_is_modified = true;
      serial_no_in_parent_is_set = true;
      serial_no_in_parent_is_modified = true;
      order_submit_error_code_is_set = true;
      order_submit_error_code_is_modified = true;
      hit_tick_timestamp_is_set = true;
      hit_tick_timestamp_is_modified = true;
      rls_hit_timestamp_is_set = true;
      rls_hit_timestamp_is_modified = true;
      order_submit_timestamp_is_set = true;
      order_submit_timestamp_is_modified = true;
      submitter_login_id_is_set = true;
      submitter_login_id_is_modified = true;
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
    if ( !( other instanceof RlsManualSubmitHistoryRow ) )
       return false;
    return fieldsEqual( (RlsManualSubmitHistoryRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�RlsManualSubmitHistoryRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( RlsManualSubmitHistoryRow row )
  {
    if ( history_id != row.getHistoryId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( order_type != row.getOrderType() )
      return false;
    if ( order_id != row.getOrderId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( notify_type == null ) {
      if ( row.getNotifyType() != null )
        return false;
    } else if ( !notify_type.equals( row.getNotifyType() ) ) {
        return false;
    }
    if ( parent_order_id == null ) {
      if ( !row.getParentOrderIdIsNull() )
        return false;
    } else if ( row.getParentOrderIdIsNull() || ( parent_order_id.longValue() != row.getParentOrderId() ) ) {
        return false;
    }
    if ( parent_product_type == null ) {
      if ( row.getParentProductType() != null )
        return false;
    } else if ( !parent_product_type.equals( row.getParentProductType() ) ) {
        return false;
    }
    if ( serial_no_in_parent != row.getSerialNoInParent() )
      return false;
    if ( order_submit_error_code == null ) {
      if ( row.getOrderSubmitErrorCode() != null )
        return false;
    } else if ( !order_submit_error_code.equals( row.getOrderSubmitErrorCode() ) ) {
        return false;
    }
    if ( hit_tick_timestamp == null ) {
      if ( row.getHitTickTimestamp() != null )
        return false;
    } else if ( !hit_tick_timestamp.equals( row.getHitTickTimestamp() ) ) {
        return false;
    }
    if ( rls_hit_timestamp == null ) {
      if ( row.getRlsHitTimestamp() != null )
        return false;
    } else if ( !rls_hit_timestamp.equals( row.getRlsHitTimestamp() ) ) {
        return false;
    }
    if ( order_submit_timestamp == null ) {
      if ( row.getOrderSubmitTimestamp() != null )
        return false;
    } else if ( !order_submit_timestamp.equals( row.getOrderSubmitTimestamp() ) ) {
        return false;
    }
    if ( submitter_login_id == null ) {
      if ( !row.getSubmitterLoginIdIsNull() )
        return false;
    } else if ( row.getSubmitterLoginIdIsNull() || ( submitter_login_id.longValue() != row.getSubmitterLoginId() ) ) {
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
      return  ((int) history_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_type)
        + ((int) order_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (notify_type!=null? notify_type.hashCode(): 0) 
        + (parent_order_id!=null? parent_order_id.hashCode(): 0) 
        + (parent_product_type!=null? parent_product_type.hashCode(): 0) 
        + ((int) serial_no_in_parent)
        + (order_submit_error_code!=null? order_submit_error_code.hashCode(): 0) 
        + (hit_tick_timestamp!=null? hit_tick_timestamp.hashCode(): 0) 
        + (rls_hit_timestamp!=null? rls_hit_timestamp.hashCode(): 0) 
        + (order_submit_timestamp!=null? order_submit_timestamp.hashCode(): 0) 
        + (submitter_login_id!=null? submitter_login_id.hashCode(): 0) 
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
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("history_id",new Long(history_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("order_type",new Integer(order_type));
		map.put("order_id",new Long(order_id));
		map.put("product_type",product_type);
		if ( status_is_set )
			map.put("status",status);
		if ( notify_type_is_set )
			map.put("notify_type",notify_type);
		if ( parent_order_id != null )
			map.put("parent_order_id",parent_order_id);
		if ( parent_product_type != null )
			map.put("parent_product_type",parent_product_type);
		if ( serial_no_in_parent_is_set )
			map.put("serial_no_in_parent",new Integer(serial_no_in_parent));
		if ( order_submit_error_code != null )
			map.put("order_submit_error_code",order_submit_error_code);
		if ( hit_tick_timestamp != null )
			map.put("hit_tick_timestamp",hit_tick_timestamp);
		if ( rls_hit_timestamp != null )
			map.put("rls_hit_timestamp",rls_hit_timestamp);
		if ( order_submit_timestamp != null )
			map.put("order_submit_timestamp",order_submit_timestamp);
		if ( submitter_login_id_is_set )
			map.put("submitter_login_id",submitter_login_id);
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
		if ( order_type_is_modified )
			map.put("order_type",new Integer(order_type));
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( status_is_modified )
			map.put("status",status);
		if ( notify_type_is_modified )
			map.put("notify_type",notify_type);
		if ( parent_order_id_is_modified )
			map.put("parent_order_id",parent_order_id);
		if ( parent_product_type_is_modified )
			map.put("parent_product_type",parent_product_type);
		if ( serial_no_in_parent_is_modified )
			map.put("serial_no_in_parent",new Integer(serial_no_in_parent));
		if ( order_submit_error_code_is_modified )
			map.put("order_submit_error_code",order_submit_error_code);
		if ( hit_tick_timestamp_is_modified )
			map.put("hit_tick_timestamp",hit_tick_timestamp);
		if ( rls_hit_timestamp_is_modified )
			map.put("rls_hit_timestamp",rls_hit_timestamp);
		if ( order_submit_timestamp_is_modified )
			map.put("order_submit_timestamp",order_submit_timestamp);
		if ( submitter_login_id_is_modified )
			map.put("submitter_login_id",submitter_login_id);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( order_type_is_set )
				map.put("order_type",new Integer(order_type));
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( status_is_set )
				map.put("status",status);
			if ( notify_type_is_set )
				map.put("notify_type",notify_type);
			map.put("parent_order_id",parent_order_id);
			map.put("parent_product_type",parent_product_type);
			if ( serial_no_in_parent_is_set )
				map.put("serial_no_in_parent",new Integer(serial_no_in_parent));
			map.put("order_submit_error_code",order_submit_error_code);
			map.put("hit_tick_timestamp",hit_tick_timestamp);
			map.put("rls_hit_timestamp",rls_hit_timestamp);
			map.put("order_submit_timestamp",order_submit_timestamp);
			if ( submitter_login_id_is_set )
				map.put("submitter_login_id",submitter_login_id);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>history_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getHistoryId()
  {
    return history_id;
  }


  /** 
   * <em>history_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHistoryIdIsSet() {
    return history_id_is_set;
  }


  /** 
   * <em>history_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHistoryIdIsModified() {
    return history_id_is_modified;
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
   * <em>order_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getOrderType()
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
   * <em>notify_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNotifyType()
  {
    return notify_type;
  }


  /** 
   * <em>notify_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNotifyTypeIsSet() {
    return notify_type_is_set;
  }


  /** 
   * <em>notify_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNotifyTypeIsModified() {
    return notify_type_is_modified;
  }


  /** 
   * <em>parent_order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getParentOrderId()
  {
    return ( parent_order_id==null? ((long)0): parent_order_id.longValue() );
  }


  /** 
   * <em>parent_order_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getParentOrderIdIsNull()
  {
    return parent_order_id == null;
  }


  /** 
   * <em>parent_order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParentOrderIdIsSet() {
    return parent_order_id_is_set;
  }


  /** 
   * <em>parent_order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParentOrderIdIsModified() {
    return parent_order_id_is_modified;
  }


  /** 
   * <em>parent_product_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getParentProductType()
  {
    return parent_product_type;
  }


  /** 
   * <em>parent_product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParentProductTypeIsSet() {
    return parent_product_type_is_set;
  }


  /** 
   * <em>parent_product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParentProductTypeIsModified() {
    return parent_product_type_is_modified;
  }


  /** 
   * <em>serial_no_in_parent</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSerialNoInParent()
  {
    return serial_no_in_parent;
  }


  /** 
   * <em>serial_no_in_parent</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSerialNoInParentIsSet() {
    return serial_no_in_parent_is_set;
  }


  /** 
   * <em>serial_no_in_parent</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSerialNoInParentIsModified() {
    return serial_no_in_parent_is_modified;
  }


  /** 
   * <em>order_submit_error_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderSubmitErrorCode()
  {
    return order_submit_error_code;
  }


  /** 
   * <em>order_submit_error_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderSubmitErrorCodeIsSet() {
    return order_submit_error_code_is_set;
  }


  /** 
   * <em>order_submit_error_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderSubmitErrorCodeIsModified() {
    return order_submit_error_code_is_modified;
  }


  /** 
   * <em>hit_tick_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getHitTickTimestamp()
  {
    return hit_tick_timestamp;
  }


  /** 
   * <em>hit_tick_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHitTickTimestampIsSet() {
    return hit_tick_timestamp_is_set;
  }


  /** 
   * <em>hit_tick_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHitTickTimestampIsModified() {
    return hit_tick_timestamp_is_modified;
  }


  /** 
   * <em>rls_hit_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getRlsHitTimestamp()
  {
    return rls_hit_timestamp;
  }


  /** 
   * <em>rls_hit_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRlsHitTimestampIsSet() {
    return rls_hit_timestamp_is_set;
  }


  /** 
   * <em>rls_hit_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRlsHitTimestampIsModified() {
    return rls_hit_timestamp_is_modified;
  }


  /** 
   * <em>order_submit_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOrderSubmitTimestamp()
  {
    return order_submit_timestamp;
  }


  /** 
   * <em>order_submit_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderSubmitTimestampIsSet() {
    return order_submit_timestamp_is_set;
  }


  /** 
   * <em>order_submit_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderSubmitTimestampIsModified() {
    return order_submit_timestamp_is_modified;
  }


  /** 
   * <em>submitter_login_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubmitterLoginId()
  {
    return ( submitter_login_id==null? ((long)0): submitter_login_id.longValue() );
  }


  /** 
   * <em>submitter_login_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubmitterLoginIdIsNull()
  {
    return submitter_login_id == null;
  }


  /** 
   * <em>submitter_login_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubmitterLoginIdIsSet() {
    return submitter_login_id_is_set;
  }


  /** 
   * <em>submitter_login_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubmitterLoginIdIsModified() {
    return submitter_login_id_is_modified;
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
    return new RlsManualSubmitHistoryPK(history_id);
  }


  /** 
   * <em>history_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_historyId <em>history_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setHistoryId( long p_historyId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    history_id = p_historyId;
    history_id_is_set = true;
    history_id_is_modified = true;
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
   * <em>order_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderType <em>order_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setOrderType( int p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
    order_type_is_modified = true;
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
   * <em>notify_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_notifyType <em>notify_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setNotifyType( String p_notifyType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    notify_type = p_notifyType;
    notify_type_is_set = true;
    notify_type_is_modified = true;
  }


  /** 
   * <em>parent_order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_parentOrderId <em>parent_order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setParentOrderId( long p_parentOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parent_order_id = new Long(p_parentOrderId);
    parent_order_id_is_set = true;
    parent_order_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>parent_order_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setParentOrderId( Long p_parentOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    parent_order_id = p_parentOrderId;
    parent_order_id_is_set = true;
    parent_order_id_is_modified = true;
  }


  /** 
   * <em>parent_product_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_parentProductType <em>parent_product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public final void setParentProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_parentProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parent_product_type = p_parentProductType;
    parent_product_type_is_set = true;
    parent_product_type_is_modified = true;
  }


  /** 
   * <em>serial_no_in_parent</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serialNoInParent <em>serial_no_in_parent</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public final void setSerialNoInParent( int p_serialNoInParent )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no_in_parent = p_serialNoInParent;
    serial_no_in_parent_is_set = true;
    serial_no_in_parent_is_modified = true;
  }


  /** 
   * <em>order_submit_error_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderSubmitErrorCode <em>order_submit_error_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setOrderSubmitErrorCode( String p_orderSubmitErrorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_submit_error_code = p_orderSubmitErrorCode;
    order_submit_error_code_is_set = true;
    order_submit_error_code_is_modified = true;
  }


  /** 
   * <em>hit_tick_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_hitTickTimestamp <em>hit_tick_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setHitTickTimestamp( java.sql.Timestamp p_hitTickTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hit_tick_timestamp = p_hitTickTimestamp;
    hit_tick_timestamp_is_set = true;
    hit_tick_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>hit_tick_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setHitTickTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    hit_tick_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    hit_tick_timestamp_is_set = true;
    hit_tick_timestamp_is_modified = true;
  }


  /** 
   * <em>rls_hit_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rlsHitTimestamp <em>rls_hit_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setRlsHitTimestamp( java.sql.Timestamp p_rlsHitTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rls_hit_timestamp = p_rlsHitTimestamp;
    rls_hit_timestamp_is_set = true;
    rls_hit_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>rls_hit_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setRlsHitTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    rls_hit_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    rls_hit_timestamp_is_set = true;
    rls_hit_timestamp_is_modified = true;
  }


  /** 
   * <em>order_submit_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderSubmitTimestamp <em>order_submit_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOrderSubmitTimestamp( java.sql.Timestamp p_orderSubmitTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_submit_timestamp = p_orderSubmitTimestamp;
    order_submit_timestamp_is_set = true;
    order_submit_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>order_submit_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOrderSubmitTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_submit_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_submit_timestamp_is_set = true;
    order_submit_timestamp_is_modified = true;
  }


  /** 
   * <em>submitter_login_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_submitterLoginId <em>submitter_login_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubmitterLoginId( long p_submitterLoginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    submitter_login_id = new Long(p_submitterLoginId);
    submitter_login_id_is_set = true;
    submitter_login_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>submitter_login_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubmitterLoginId( Long p_submitterLoginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    submitter_login_id = p_submitterLoginId;
    submitter_login_id_is_set = true;
    submitter_login_id_is_modified = true;
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
            case 'h':
                if ( name.equals("history_id") ) {
                    return new Long( history_id );
                }
                else if ( name.equals("hit_tick_timestamp") ) {
                    return this.hit_tick_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("notify_type") ) {
                    return this.notify_type;
                }
                break;
            case 'o':
                if ( name.equals("order_type") ) {
                    return new Integer( order_type );
                }
                else if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_submit_error_code") ) {
                    return this.order_submit_error_code;
                }
                else if ( name.equals("order_submit_timestamp") ) {
                    return this.order_submit_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("parent_order_id") ) {
                    return this.parent_order_id;
                }
                else if ( name.equals("parent_product_type") ) {
                    return this.parent_product_type;
                }
                break;
            case 'r':
                if ( name.equals("rls_hit_timestamp") ) {
                    return this.rls_hit_timestamp;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("serial_no_in_parent") ) {
                    return new Integer( serial_no_in_parent );
                }
                else if ( name.equals("submitter_login_id") ) {
                    return this.submitter_login_id;
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
            case 'h':
                if ( name.equals("history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'history_id' must be Long: '"+value+"' is not." );
                    this.history_id = ((Long) value).longValue();
                    if (this.history_id_is_set)
                        this.history_id_is_modified = true;
                    this.history_id_is_set = true;
                    return;
                }
                else if ( name.equals("hit_tick_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'hit_tick_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.hit_tick_timestamp = (java.sql.Timestamp) value;
                    if (this.hit_tick_timestamp_is_set)
                        this.hit_tick_timestamp_is_modified = true;
                    this.hit_tick_timestamp_is_set = true;
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
            case 'n':
                if ( name.equals("notify_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'notify_type' must be String: '"+value+"' is not." );
                    this.notify_type = (String) value;
                    if (this.notify_type_is_set)
                        this.notify_type_is_modified = true;
                    this.notify_type_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_type' must be Integer: '"+value+"' is not." );
                    this.order_type = ((Integer) value).intValue();
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
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
                else if ( name.equals("order_submit_error_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_submit_error_code' must be String: '"+value+"' is not." );
                    this.order_submit_error_code = (String) value;
                    if (this.order_submit_error_code_is_set)
                        this.order_submit_error_code_is_modified = true;
                    this.order_submit_error_code_is_set = true;
                    return;
                }
                else if ( name.equals("order_submit_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_submit_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_submit_timestamp = (java.sql.Timestamp) value;
                    if (this.order_submit_timestamp_is_set)
                        this.order_submit_timestamp_is_modified = true;
                    this.order_submit_timestamp_is_set = true;
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
                else if ( name.equals("parent_order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'parent_order_id' must be Long: '"+value+"' is not." );
                    this.parent_order_id = (Long) value;
                    if (this.parent_order_id_is_set)
                        this.parent_order_id_is_modified = true;
                    this.parent_order_id_is_set = true;
                    return;
                }
                else if ( name.equals("parent_product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'parent_product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.parent_product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.parent_product_type_is_set)
                        this.parent_product_type_is_modified = true;
                    this.parent_product_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("rls_hit_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'rls_hit_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.rls_hit_timestamp = (java.sql.Timestamp) value;
                    if (this.rls_hit_timestamp_is_set)
                        this.rls_hit_timestamp_is_modified = true;
                    this.rls_hit_timestamp_is_set = true;
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
                else if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("serial_no_in_parent") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'serial_no_in_parent' must be Integer: '"+value+"' is not." );
                    this.serial_no_in_parent = ((Integer) value).intValue();
                    if (this.serial_no_in_parent_is_set)
                        this.serial_no_in_parent_is_modified = true;
                    this.serial_no_in_parent_is_set = true;
                    return;
                }
                else if ( name.equals("submitter_login_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'submitter_login_id' must be Long: '"+value+"' is not." );
                    this.submitter_login_id = (Long) value;
                    if (this.submitter_login_id_is_set)
                        this.submitter_login_id_is_modified = true;
                    this.submitter_login_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
