head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.26.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * qtp_rich_push_history�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link QtpRichPushHistoryParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link QtpRichPushHistoryParams}��{@@link QtpRichPushHistoryRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QtpRichPushHistoryPK 
 * @@see QtpRichPushHistoryRow 
 */
public class QtpRichPushHistoryParams extends Params implements QtpRichPushHistoryRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "qtp_rich_push_history";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = QtpRichPushHistoryRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return QtpRichPushHistoryRow.TYPE;
  }


  /** 
   * <em>serlnum</em>�J�����̒l 
   */
  public  long  serlnum;

  /** 
   * <em>tpk</em>�J�����̒l 
   */
  public  String  tpk;

  /** 
   * <em>type</em>�J�����̒l 
   */
  public  String  type;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>proc_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  proc_timestamp;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean serlnum_is_set = false;
  private boolean serlnum_is_modified = false;
  private boolean tpk_is_set = false;
  private boolean tpk_is_modified = false;
  private boolean type_is_set = false;
  private boolean type_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean proc_timestamp_is_set = false;
  private boolean proc_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "serlnum=" + serlnum
      + "," + "tpk=" +tpk
      + "," + "type=" +type
      + "," + "account_id=" +account_id
      + "," + "status=" +status
      + "," + "proc_timestamp=" +proc_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��QtpRichPushHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public QtpRichPushHistoryParams() {
    serlnum_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���QtpRichPushHistoryRow�I�u�W�F�N�g�̒l�𗘗p����QtpRichPushHistoryParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����QtpRichPushHistoryRow�I�u�W�F�N�g 
   */
  public QtpRichPushHistoryParams( QtpRichPushHistoryRow p_row )
  {
    serlnum = p_row.getSerlnum();
    serlnum_is_set = p_row.getSerlnumIsSet();
    serlnum_is_modified = p_row.getSerlnumIsModified();
    tpk = p_row.getTpk();
    tpk_is_set = p_row.getTpkIsSet();
    tpk_is_modified = p_row.getTpkIsModified();
    type = p_row.getType();
    type_is_set = p_row.getTypeIsSet();
    type_is_modified = p_row.getTypeIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    proc_timestamp = p_row.getProcTimestamp();
    proc_timestamp_is_set = p_row.getProcTimestampIsSet();
    proc_timestamp_is_modified = p_row.getProcTimestampIsModified();
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
      tpk_is_set = true;
      tpk_is_modified = true;
      type_is_set = true;
      type_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      proc_timestamp_is_set = true;
      proc_timestamp_is_modified = true;
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
    if ( !( other instanceof QtpRichPushHistoryRow ) )
       return false;
    return fieldsEqual( (QtpRichPushHistoryRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�QtpRichPushHistoryRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( QtpRichPushHistoryRow row )
  {
    if ( serlnum != row.getSerlnum() )
      return false;
    if ( tpk == null ) {
      if ( row.getTpk() != null )
        return false;
    } else if ( !tpk.equals( row.getTpk() ) ) {
        return false;
    }
    if ( type == null ) {
      if ( row.getType() != null )
        return false;
    } else if ( !type.equals( row.getType() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
      return false;
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( proc_timestamp == null ) {
      if ( row.getProcTimestamp() != null )
        return false;
    } else if ( !proc_timestamp.equals( row.getProcTimestamp() ) ) {
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
      return  ((int) serlnum)
        + (tpk!=null? tpk.hashCode(): 0) 
        + (type!=null? type.hashCode(): 0) 
        + ((int) account_id)
        + (status!=null? status.hashCode(): 0) 
        + (proc_timestamp!=null? proc_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !tpk_is_set )
			throw new IllegalArgumentException("non-nullable field 'tpk' must be set before inserting.");
		if ( !type_is_set )
			throw new IllegalArgumentException("non-nullable field 'type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("serlnum",new Long(serlnum));
		map.put("tpk",tpk);
		map.put("type",type);
		if ( account_id_is_set )
			map.put("account_id",new Long(account_id));
		if ( status_is_set )
			map.put("status",status);
		if ( proc_timestamp_is_set )
			map.put("proc_timestamp",proc_timestamp);
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
		if ( tpk_is_modified )
			map.put("tpk",tpk);
		if ( type_is_modified )
			map.put("type",type);
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( status_is_modified )
			map.put("status",status);
		if ( proc_timestamp_is_modified )
			map.put("proc_timestamp",proc_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( tpk_is_set )
				map.put("tpk",tpk);
			if ( type_is_set )
				map.put("type",type);
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( status_is_set )
				map.put("status",status);
			if ( proc_timestamp_is_set )
				map.put("proc_timestamp",proc_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>serlnum</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSerlnum()
  {
    return serlnum;
  }


  /** 
   * <em>serlnum</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSerlnumIsSet() {
    return serlnum_is_set;
  }


  /** 
   * <em>serlnum</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSerlnumIsModified() {
    return serlnum_is_modified;
  }


  /** 
   * <em>tpk</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTpk()
  {
    return tpk;
  }


  /** 
   * <em>tpk</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpkIsSet() {
    return tpk_is_set;
  }


  /** 
   * <em>tpk</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTpkIsModified() {
    return tpk_is_modified;
  }


  /** 
   * <em>type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getType()
  {
    return type;
  }


  /** 
   * <em>type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTypeIsSet() {
    return type_is_set;
  }


  /** 
   * <em>type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTypeIsModified() {
    return type_is_modified;
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
   * <em>proc_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getProcTimestamp()
  {
    return proc_timestamp;
  }


  /** 
   * <em>proc_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProcTimestampIsSet() {
    return proc_timestamp_is_set;
  }


  /** 
   * <em>proc_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProcTimestampIsModified() {
    return proc_timestamp_is_modified;
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
    return new QtpRichPushHistoryPK(serlnum);
  }


  /** 
   * <em>serlnum</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serlnum <em>serlnum</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSerlnum( long p_serlnum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serlnum = p_serlnum;
    serlnum_is_set = true;
    serlnum_is_modified = true;
  }


  /** 
   * <em>tpk</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tpk <em>tpk</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setTpk( String p_tpk )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tpk = p_tpk;
    tpk_is_set = true;
    tpk_is_modified = true;
  }


  /** 
   * <em>type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_type <em>type</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setType( String p_type )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    type = p_type;
    type_is_set = true;
    type_is_modified = true;
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
   * <em>proc_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_procTimestamp <em>proc_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setProcTimestamp( java.sql.Timestamp p_procTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proc_timestamp = p_procTimestamp;
    proc_timestamp_is_set = true;
    proc_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>proc_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setProcTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    proc_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    proc_timestamp_is_set = true;
    proc_timestamp_is_modified = true;
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
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("proc_timestamp") ) {
                    return this.proc_timestamp;
                }
                break;
            case 's':
                if ( name.equals("serlnum") ) {
                    return new Long( serlnum );
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("tpk") ) {
                    return this.tpk;
                }
                else if ( name.equals("type") ) {
                    return this.type;
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
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("proc_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'proc_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.proc_timestamp = (java.sql.Timestamp) value;
                    if (this.proc_timestamp_is_set)
                        this.proc_timestamp_is_modified = true;
                    this.proc_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("serlnum") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'serlnum' must be Long: '"+value+"' is not." );
                    this.serlnum = ((Long) value).longValue();
                    if (this.serlnum_is_set)
                        this.serlnum_is_modified = true;
                    this.serlnum_is_set = true;
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
                if ( name.equals("tpk") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tpk' must be String: '"+value+"' is not." );
                    this.tpk = (String) value;
                    if (this.tpk_is_set)
                        this.tpk_is_modified = true;
                    this.tpk_is_set = true;
                    return;
                }
                else if ( name.equals("type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'type' must be String: '"+value+"' is not." );
                    this.type = (String) value;
                    if (this.type_is_set)
                        this.type_is_modified = true;
                    this.type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
