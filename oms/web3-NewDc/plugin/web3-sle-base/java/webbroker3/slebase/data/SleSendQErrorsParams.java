head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQErrorsParams.java;


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
 * sle_send_q_errors�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SleSendQErrorsRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SleSendQErrorsParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SleSendQErrorsParams}��{@@link SleSendQErrorsRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleSendQErrorsPK 
 * @@see SleSendQErrorsRow 
 */
public class SleSendQErrorsParams extends Params implements SleSendQErrorsRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "sle_send_q_errors";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SleSendQErrorsRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SleSendQErrorsRow.TYPE;
  }


  /** 
   * <em>queue_id</em>�R�����̒l 
   */
  public  long  queue_id;

  /** 
   * <em>order_id</em>�R�����̒l 
   */
  public  long  order_id;

  /** 
   * <em>open_status</em>�R�����̒l 
   */
  public  webbroker3.slebase.enums.SleOpenStatusEnum  open_status;

  /** 
   * <em>created_timestamp</em>�R�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�R�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean queue_id_is_set;
  private boolean order_id_is_set;
  private boolean open_status_is_set;
  private boolean created_timestamp_is_set;
  private boolean last_updated_timestamp_is_set;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "queue_id=" + queue_id
      + "," + "order_id=" +order_id
      + "," + "open_status=" +open_status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SleSendQErrorsParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SleSendQErrorsParams() {
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SleSendQErrorsRow�I�u�W�F�N�g�̒l�𗘗p����SleSendQErrorsParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SleSendQErrorsRow�I�u�W�F�N�g 
   */
  public SleSendQErrorsParams( SleSendQErrorsRow p_row )
  {
    queue_id = p_row.getQueueId();
    queue_id_is_set = p_row.getQueueIdIsSet();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    open_status = p_row.getOpenStatus();
    open_status_is_set = p_row.getOpenStatusIsSet();
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
      order_id_is_set = true;
      open_status_is_set = true;
      created_timestamp_is_set = true;
      last_updated_timestamp_is_set = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SleSendQErrorsRow ) )
       return false;
    return fieldsEqual( (SleSendQErrorsRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SleSendQErrorsRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SleSendQErrorsRow row )
  {
    if ( queue_id != row.getQueueId() )
      return false;
    if ( order_id != row.getOrderId() )
      return false;
    if ( open_status == null ) {
      if ( row.getOpenStatus() != null )
        return false;
    } else if ( !open_status.equals( row.getOpenStatus() ) ) {
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
        + ((int) order_id)
        + (open_status!=null? open_status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !open_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'open_status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("queue_id",new Long(queue_id));
		map.put("order_id",new Long(order_id));
		map.put("open_status",open_status);
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
		if ( order_id_is_set )
			map.put("order_id",new Long(order_id));
		if ( open_status_is_set )
			map.put("open_status",open_status);
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
   * <em>order_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderId()
  {
    return order_id;
  }


  /** 
   * <em>order_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>open_status</em>�R�����̒l���擾���܂��B
   * @@return webbroker3.slebase.enums.SleOpenStatusEnum�̒l 
   */
  public final webbroker3.slebase.enums.SleOpenStatusEnum getOpenStatus()
  {
    return open_status;
  }


  /** 
   * <em>open_status</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenStatusIsSet() {
    return open_status_is_set;
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
    return new SleSendQErrorsPK(queue_id);
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
   * <em>order_id</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderId <em>order_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
  }


  /** 
   * <em>open_status</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openStatus <em>open_status</em>�R�����̒l������킷6���ȉ���webbroker3.slebase.enums.SleOpenStatusEnum�l 
   */
  public final void setOpenStatus( webbroker3.slebase.enums.SleOpenStatusEnum p_openStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_status = p_openStatus;
    open_status_is_set = true;
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("open_status") ) {
                    return this.open_status;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    return new Long( queue_id );
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("open_status") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleOpenStatusEnum) )
                        throw new IllegalArgumentException( "value for 'open_status' must be webbroker3.slebase.enums.SleOpenStatusEnum: '"+value+"' is not." );
                    this.open_status = (webbroker3.slebase.enums.SleOpenStatusEnum) value;
                    this.open_status_is_set = true;
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
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
