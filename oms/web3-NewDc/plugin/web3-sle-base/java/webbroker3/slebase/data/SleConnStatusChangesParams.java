head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnStatusChangesParams.java;


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
 * sle_conn_status_changes�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SleConnStatusChangesParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SleConnStatusChangesParams}��{@@link SleConnStatusChangesRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleConnStatusChangesPK 
 * @@see SleConnStatusChangesRow 
 */
public class SleConnStatusChangesParams extends Params implements SleConnStatusChangesRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "sle_conn_status_changes";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SleConnStatusChangesRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SleConnStatusChangesRow.TYPE;
  }


  /** 
   * <em>que_id</em>�R�����̒l 
   */
  public  long  que_id;

  /** 
   * <em>market_code</em>�R�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>new_status</em>�R�����̒l 
   */
  public  webbroker3.slebase.enums.SleConnectionStatusEnum  new_status;

  /** 
   * <em>event_acked_div</em>�R�����̒l 
   */
  public  int  event_acked_div;

  /** 
   * <em>sle_conn_div</em>�R�����̒l 
   */
  public  int  sle_conn_div;

  /** 
   * <em>proc_status</em>�R�����̒l 
   */
  public  int  proc_status;

  /** 
   * <em>created_timestamp</em>�R�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�R�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean que_id_is_set;
  private boolean market_code_is_set;
  private boolean new_status_is_set;
  private boolean event_acked_div_is_set;
  private boolean sle_conn_div_is_set;
  private boolean proc_status_is_set;
  private boolean created_timestamp_is_set;
  private boolean last_updated_timestamp_is_set;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "que_id=" + que_id
      + "," + "market_code=" +market_code
      + "," + "new_status=" +new_status
      + "," + "event_acked_div=" +event_acked_div
      + "," + "sle_conn_div=" +sle_conn_div
      + "," + "proc_status=" +proc_status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SleConnStatusChangesParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SleConnStatusChangesParams() {
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SleConnStatusChangesRow�I�u�W�F�N�g�̒l�𗘗p����SleConnStatusChangesParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SleConnStatusChangesRow�I�u�W�F�N�g 
   */
  public SleConnStatusChangesParams( SleConnStatusChangesRow p_row )
  {
    que_id = p_row.getQueId();
    que_id_is_set = p_row.getQueIdIsSet();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    new_status = p_row.getNewStatus();
    new_status_is_set = p_row.getNewStatusIsSet();
    event_acked_div = p_row.getEventAckedDiv();
    event_acked_div_is_set = p_row.getEventAckedDivIsSet();
    sle_conn_div = p_row.getSleConnDiv();
    sle_conn_div_is_set = p_row.getSleConnDivIsSet();
    proc_status = p_row.getProcStatus();
    proc_status_is_set = p_row.getProcStatusIsSet();
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
      market_code_is_set = true;
      new_status_is_set = true;
      event_acked_div_is_set = true;
      sle_conn_div_is_set = true;
      proc_status_is_set = true;
      created_timestamp_is_set = true;
      last_updated_timestamp_is_set = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SleConnStatusChangesRow ) )
       return false;
    return fieldsEqual( (SleConnStatusChangesRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SleConnStatusChangesRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SleConnStatusChangesRow row )
  {
    if ( que_id != row.getQueId() )
      return false;
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( new_status == null ) {
      if ( row.getNewStatus() != null )
        return false;
    } else if ( !new_status.equals( row.getNewStatus() ) ) {
        return false;
    }
    if ( event_acked_div != row.getEventAckedDiv() )
      return false;
    if ( sle_conn_div != row.getSleConnDiv() )
      return false;
    if ( proc_status != row.getProcStatus() )
      return false;
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
      return  ((int) que_id)
        + (market_code!=null? market_code.hashCode(): 0) 
        + (new_status!=null? new_status.hashCode(): 0) 
        + ((int) event_acked_div)
        + ((int) sle_conn_div)
        + ((int) proc_status)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !new_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'new_status' must be set before inserting.");
		if ( !event_acked_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'event_acked_div' must be set before inserting.");
		if ( !sle_conn_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'sle_conn_div' must be set before inserting.");
		if ( !proc_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'proc_status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("que_id",new Long(que_id));
		map.put("market_code",market_code);
		map.put("new_status",new_status);
		map.put("event_acked_div",new Integer(event_acked_div));
		map.put("sle_conn_div",new Integer(sle_conn_div));
		map.put("proc_status",new Integer(proc_status));
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
		if ( market_code_is_set )
			map.put("market_code",market_code);
		if ( new_status_is_set )
			map.put("new_status",new_status);
		if ( event_acked_div_is_set )
			map.put("event_acked_div",new Integer(event_acked_div));
		if ( sle_conn_div_is_set )
			map.put("sle_conn_div",new Integer(sle_conn_div));
		if ( proc_status_is_set )
			map.put("proc_status",new Integer(proc_status));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * <em>que_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getQueId()
  {
    return que_id;
  }


  /** 
   * <em>que_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQueIdIsSet() {
    return que_id_is_set;
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
   * <em>new_status</em>�R�����̒l���擾���܂��B
   * @@return webbroker3.slebase.enums.SleConnectionStatusEnum�̒l 
   */
  public final webbroker3.slebase.enums.SleConnectionStatusEnum getNewStatus()
  {
    return new_status;
  }


  /** 
   * <em>new_status</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewStatusIsSet() {
    return new_status_is_set;
  }


  /** 
   * <em>event_acked_div</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getEventAckedDiv()
  {
    return event_acked_div;
  }


  /** 
   * <em>event_acked_div</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEventAckedDivIsSet() {
    return event_acked_div_is_set;
  }


  /** 
   * <em>sle_conn_div</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSleConnDiv()
  {
    return sle_conn_div;
  }


  /** 
   * <em>sle_conn_div</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSleConnDivIsSet() {
    return sle_conn_div_is_set;
  }


  /** 
   * <em>proc_status</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getProcStatus()
  {
    return proc_status;
  }


  /** 
   * <em>proc_status</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProcStatusIsSet() {
    return proc_status_is_set;
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
    return new SleConnStatusChangesPK(que_id);
  }


  /** 
   * <em>que_id</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_queId <em>que_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public final void setQueId( long p_queId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    que_id = p_queId;
    que_id_is_set = true;
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
   * <em>new_status</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_newStatus <em>new_status</em>�R�����̒l������킷6���ȉ���webbroker3.slebase.enums.SleConnectionStatusEnum�l 
   */
  public final void setNewStatus( webbroker3.slebase.enums.SleConnectionStatusEnum p_newStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_status = p_newStatus;
    new_status_is_set = true;
  }


  /** 
   * <em>event_acked_div</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_eventAckedDiv <em>event_acked_div</em>�R�����̒l������킷6���ȉ���int�l 
   */
  public final void setEventAckedDiv( int p_eventAckedDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    event_acked_div = p_eventAckedDiv;
    event_acked_div_is_set = true;
  }


  /** 
   * <em>sle_conn_div</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sleConnDiv <em>sle_conn_div</em>�R�����̒l������킷6���ȉ���int�l 
   */
  public final void setSleConnDiv( int p_sleConnDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sle_conn_div = p_sleConnDiv;
    sle_conn_div_is_set = true;
  }


  /** 
   * <em>proc_status</em>�R�����̒l��ݒ肵�܂��B 
   *
   * @@param p_procStatus <em>proc_status</em>�R�����̒l������킷6���ȉ���int�l 
   */
  public final void setProcStatus( int p_procStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proc_status = p_procStatus;
    proc_status_is_set = true;
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
            case 'e':
                if ( name.equals("event_acked_div") ) {
                    return new Integer( event_acked_div );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'n':
                if ( name.equals("new_status") ) {
                    return this.new_status;
                }
                break;
            case 'p':
                if ( name.equals("proc_status") ) {
                    return new Integer( proc_status );
                }
                break;
            case 'q':
                if ( name.equals("que_id") ) {
                    return new Long( que_id );
                }
                break;
            case 's':
                if ( name.equals("sle_conn_div") ) {
                    return new Integer( sle_conn_div );
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
            case 'e':
                if ( name.equals("event_acked_div") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'event_acked_div' must be Integer: '"+value+"' is not." );
                    this.event_acked_div = ((Integer) value).intValue();
                    this.event_acked_div_is_set = true;
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
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_status") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleConnectionStatusEnum) )
                        throw new IllegalArgumentException( "value for 'new_status' must be webbroker3.slebase.enums.SleConnectionStatusEnum: '"+value+"' is not." );
                    this.new_status = (webbroker3.slebase.enums.SleConnectionStatusEnum) value;
                    this.new_status_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("proc_status") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'proc_status' must be Integer: '"+value+"' is not." );
                    this.proc_status = ((Integer) value).intValue();
                    this.proc_status_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("que_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'que_id' must be Long: '"+value+"' is not." );
                    this.que_id = ((Long) value).longValue();
                    this.que_id_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sle_conn_div") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sle_conn_div' must be Integer: '"+value+"' is not." );
                    this.sle_conn_div = ((Integer) value).intValue();
                    this.sle_conn_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
