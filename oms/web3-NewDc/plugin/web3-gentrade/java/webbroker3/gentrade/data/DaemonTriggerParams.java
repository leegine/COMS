head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DaemonTriggerParams.java;


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
 * daemon_trigger�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link DaemonTriggerRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link DaemonTriggerParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link DaemonTriggerParams}��{@@link DaemonTriggerRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DaemonTriggerPK 
 * @@see DaemonTriggerRow 
 */
public class DaemonTriggerParams extends Params implements DaemonTriggerRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "daemon_trigger";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = DaemonTriggerRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return DaemonTriggerRow.TYPE;
  }


  /** 
   * <em>thread_no</em>�J�����̒l 
   */
  public  long  thread_no;

  /** 
   * <em>trigger_type</em>�J�����̒l 
   */
  public  String  trigger_type;

  /** 
   * <em>order_request_number</em>�J�����̒l 
   */
  public  String  order_request_number;

  /** 
   * <em>range_from</em>�J�����̒l 
   */
  public  long  range_from;

  /** 
   * <em>range_to</em>�J�����̒l 
   */
  public  long  range_to;

  /** 
   * <em>trigger_status</em>�J�����̒l 
   */
  public  String  trigger_status;

  /** 
   * <em>trigger_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  trigger_date;

  private boolean trigger_type_is_set = false;
  private boolean trigger_type_is_modified = false;
  private boolean thread_no_is_set = false;
  private boolean thread_no_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean range_from_is_set = false;
  private boolean range_from_is_modified = false;
  private boolean range_to_is_set = false;
  private boolean range_to_is_modified = false;
  private boolean trigger_status_is_set = false;
  private boolean trigger_status_is_modified = false;
  private boolean trigger_date_is_set = false;
  private boolean trigger_date_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "thread_no=" + thread_no
      + "," + "trigger_type=" +trigger_type
      + "," + "order_request_number=" +order_request_number
      + "," + "range_from=" +range_from
      + "," + "range_to=" +range_to
      + "," + "trigger_status=" +trigger_status
      + "," + "trigger_date=" +trigger_date
      + "}";
  }


  /** 
   * �l�����ݒ��DaemonTriggerParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public DaemonTriggerParams() {
    thread_no_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���DaemonTriggerRow�I�u�W�F�N�g�̒l�𗘗p����DaemonTriggerParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����DaemonTriggerRow�I�u�W�F�N�g 
   */
  public DaemonTriggerParams( DaemonTriggerRow p_row )
  {
    thread_no = p_row.getThreadNo();
    thread_no_is_set = p_row.getThreadNoIsSet();
    thread_no_is_modified = p_row.getThreadNoIsModified();
    trigger_type = p_row.getTriggerType();
    trigger_type_is_set = p_row.getTriggerTypeIsSet();
    trigger_type_is_modified = p_row.getTriggerTypeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    range_from = p_row.getRangeFrom();
    range_from_is_set = p_row.getRangeFromIsSet();
    range_from_is_modified = p_row.getRangeFromIsModified();
    range_to = p_row.getRangeTo();
    range_to_is_set = p_row.getRangeToIsSet();
    range_to_is_modified = p_row.getRangeToIsModified();
    trigger_status = p_row.getTriggerStatus();
    trigger_status_is_set = p_row.getTriggerStatusIsSet();
    trigger_status_is_modified = p_row.getTriggerStatusIsModified();
    trigger_date = p_row.getTriggerDate();
    trigger_date_is_set = p_row.getTriggerDateIsSet();
    trigger_date_is_modified = p_row.getTriggerDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      trigger_type_is_set = true;
      trigger_type_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      range_from_is_set = true;
      range_from_is_modified = true;
      range_to_is_set = true;
      range_to_is_modified = true;
      trigger_status_is_set = true;
      trigger_status_is_modified = true;
      trigger_date_is_set = true;
      trigger_date_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof DaemonTriggerRow ) )
       return false;
    return fieldsEqual( (DaemonTriggerRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�DaemonTriggerRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( DaemonTriggerRow row )
  {
    if ( trigger_type == null ) {
      if ( row.getTriggerType() != null )
        return false;
    } else if ( !trigger_type.equals( row.getTriggerType() ) ) {
        return false;
    }
    if ( thread_no != row.getThreadNo() )
      return false;
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( range_from != row.getRangeFrom() )
      return false;
    if ( range_to != row.getRangeTo() )
      return false;
    if ( trigger_status == null ) {
      if ( row.getTriggerStatus() != null )
        return false;
    } else if ( !trigger_status.equals( row.getTriggerStatus() ) ) {
        return false;
    }
    if ( trigger_date == null ) {
      if ( row.getTriggerDate() != null )
        return false;
    } else if ( !trigger_date.equals( row.getTriggerDate() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (trigger_type!=null? trigger_type.hashCode(): 0) 
        + ((int) thread_no)
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + ((int) range_from)
        + ((int) range_to)
        + (trigger_status!=null? trigger_status.hashCode(): 0) 
        + (trigger_date!=null? trigger_date.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !trigger_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'trigger_type' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("trigger_type",trigger_type);
		map.put("thread_no",new Long(thread_no));
		map.put("order_request_number",order_request_number);
		if ( range_from_is_set )
			map.put("range_from",new Long(range_from));
		if ( range_to_is_set )
			map.put("range_to",new Long(range_to));
		if ( trigger_status_is_set )
			map.put("trigger_status",trigger_status);
		if ( trigger_date != null )
			map.put("trigger_date",trigger_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( trigger_type_is_modified )
			map.put("trigger_type",trigger_type);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( range_from_is_modified )
			map.put("range_from",new Long(range_from));
		if ( range_to_is_modified )
			map.put("range_to",new Long(range_to));
		if ( trigger_status_is_modified )
			map.put("trigger_status",trigger_status);
		if ( trigger_date_is_modified )
			map.put("trigger_date",trigger_date);
		if (map.size() == 0) {
			if ( trigger_type_is_set )
				map.put("trigger_type",trigger_type);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			if ( range_from_is_set )
				map.put("range_from",new Long(range_from));
			if ( range_to_is_set )
				map.put("range_to",new Long(range_to));
			if ( trigger_status_is_set )
				map.put("trigger_status",trigger_status);
			map.put("trigger_date",trigger_date);
		}
		return map;
	}


  /** 
   * <em>trigger_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTriggerType()
  {
    return trigger_type;
  }


  /** 
   * <em>trigger_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerTypeIsSet() {
    return trigger_type_is_set;
  }


  /** 
   * <em>trigger_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerTypeIsModified() {
    return trigger_type_is_modified;
  }


  /** 
   * <em>thread_no</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getThreadNo()
  {
    return thread_no;
  }


  /** 
   * <em>thread_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getThreadNoIsSet() {
    return thread_no_is_set;
  }


  /** 
   * <em>thread_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getThreadNoIsModified() {
    return thread_no_is_modified;
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
   * <em>range_from</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getRangeFrom()
  {
    return range_from;
  }


  /** 
   * <em>range_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRangeFromIsSet() {
    return range_from_is_set;
  }


  /** 
   * <em>range_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRangeFromIsModified() {
    return range_from_is_modified;
  }


  /** 
   * <em>range_to</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getRangeTo()
  {
    return range_to;
  }


  /** 
   * <em>range_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRangeToIsSet() {
    return range_to_is_set;
  }


  /** 
   * <em>range_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRangeToIsModified() {
    return range_to_is_modified;
  }


  /** 
   * <em>trigger_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTriggerStatus()
  {
    return trigger_status;
  }


  /** 
   * <em>trigger_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerStatusIsSet() {
    return trigger_status_is_set;
  }


  /** 
   * <em>trigger_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerStatusIsModified() {
    return trigger_status_is_modified;
  }


  /** 
   * <em>trigger_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getTriggerDate()
  {
    return trigger_date;
  }


  /** 
   * <em>trigger_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerDateIsSet() {
    return trigger_date_is_set;
  }


  /** 
   * <em>trigger_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTriggerDateIsModified() {
    return trigger_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new DaemonTriggerPK(thread_no);
  }


  /** 
   * <em>trigger_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_triggerType <em>trigger_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTriggerType( String p_triggerType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_type = p_triggerType;
    trigger_type_is_set = true;
    trigger_type_is_modified = true;
  }


  /** 
   * <em>thread_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_threadNo <em>thread_no</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setThreadNo( long p_threadNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    thread_no = p_threadNo;
    thread_no_is_set = true;
    thread_no_is_modified = true;
  }


  /** 
   * <em>order_request_number</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>range_from</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rangeFrom <em>range_from</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setRangeFrom( long p_rangeFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_from = p_rangeFrom;
    range_from_is_set = true;
    range_from_is_modified = true;
  }


  /** 
   * <em>range_to</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rangeTo <em>range_to</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setRangeTo( long p_rangeTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_to = p_rangeTo;
    range_to_is_set = true;
    range_to_is_modified = true;
  }


  /** 
   * <em>trigger_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_triggerStatus <em>trigger_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTriggerStatus( String p_triggerStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_status = p_triggerStatus;
    trigger_status_is_set = true;
    trigger_status_is_modified = true;
  }


  /** 
   * <em>trigger_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_triggerDate <em>trigger_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setTriggerDate( java.sql.Timestamp p_triggerDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_date = p_triggerDate;
    trigger_date_is_set = true;
    trigger_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>trigger_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setTriggerDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trigger_date_is_set = true;
    trigger_date_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'r':
                if ( name.equals("range_from") ) {
                    return new Long( range_from );
                }
                else if ( name.equals("range_to") ) {
                    return new Long( range_to );
                }
                break;
            case 't':
                if ( name.equals("trigger_type") ) {
                    return this.trigger_type;
                }
                else if ( name.equals("thread_no") ) {
                    return new Long( thread_no );
                }
                else if ( name.equals("trigger_status") ) {
                    return this.trigger_status;
                }
                else if ( name.equals("trigger_date") ) {
                    return this.trigger_date;
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
            case 'r':
                if ( name.equals("range_from") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'range_from' must be Long: '"+value+"' is not." );
                    this.range_from = ((Long) value).longValue();
                    if (this.range_from_is_set)
                        this.range_from_is_modified = true;
                    this.range_from_is_set = true;
                    return;
                }
                else if ( name.equals("range_to") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'range_to' must be Long: '"+value+"' is not." );
                    this.range_to = ((Long) value).longValue();
                    if (this.range_to_is_set)
                        this.range_to_is_modified = true;
                    this.range_to_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trigger_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trigger_type' must be String: '"+value+"' is not." );
                    this.trigger_type = (String) value;
                    if (this.trigger_type_is_set)
                        this.trigger_type_is_modified = true;
                    this.trigger_type_is_set = true;
                    return;
                }
                else if ( name.equals("thread_no") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'thread_no' must be Long: '"+value+"' is not." );
                    this.thread_no = ((Long) value).longValue();
                    if (this.thread_no_is_set)
                        this.thread_no_is_modified = true;
                    this.thread_no_is_set = true;
                    return;
                }
                else if ( name.equals("trigger_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trigger_status' must be String: '"+value+"' is not." );
                    this.trigger_status = (String) value;
                    if (this.trigger_status_is_set)
                        this.trigger_status_is_modified = true;
                    this.trigger_status_is_set = true;
                    return;
                }
                else if ( name.equals("trigger_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trigger_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trigger_date = (java.sql.Timestamp) value;
                    if (this.trigger_date_is_set)
                        this.trigger_date_is_modified = true;
                    this.trigger_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
