head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsCondOrderParams.java;


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
 * rls_cond_order�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link RlsCondOrderRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link RlsCondOrderParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link RlsCondOrderParams}��{@@link RlsCondOrderRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsCondOrderPK 
 * @@see RlsCondOrderRow 
 */
public class RlsCondOrderParams extends Params implements RlsCondOrderRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rls_cond_order";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = RlsCondOrderRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return RlsCondOrderRow.TYPE;
  }


  /** 
   * <em>id</em>�J�����̒l 
   */
  public  long  id;

  /** 
   * <em>type</em>�J�����̒l 
   */
  public  long  type;

  /** 
   * <em>parent_id</em>�J�����̒l 
   */
  public  Long  parent_id;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  int  status;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>seq_num</em>�J�����̒l 
   */
  public  long  seq_num;

  /** 
   * <em>act_ratio</em>�J�����̒l 
   */
  public  long  act_ratio;

  /** 
   * <em>tstamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  tstamp;

  private boolean id_is_set = false;
  private boolean id_is_modified = false;
  private boolean type_is_set = false;
  private boolean type_is_modified = false;
  private boolean parent_id_is_set = false;
  private boolean parent_id_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean seq_num_is_set = false;
  private boolean seq_num_is_modified = false;
  private boolean act_ratio_is_set = false;
  private boolean act_ratio_is_modified = false;
  private boolean tstamp_is_set = false;
  private boolean tstamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "id=" + id
      + "," + "type=" +type
      + "," + "parent_id=" +parent_id
      + "," + "status=" +status
      + "," + "account_id=" +account_id
      + "," + "seq_num=" +seq_num
      + "," + "act_ratio=" +act_ratio
      + "," + "tstamp=" +tstamp
      + "}";
  }


  /** 
   * �l�����ݒ��RlsCondOrderParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public RlsCondOrderParams() {
    id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���RlsCondOrderRow�I�u�W�F�N�g�̒l�𗘗p����RlsCondOrderParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����RlsCondOrderRow�I�u�W�F�N�g 
   */
  public RlsCondOrderParams( RlsCondOrderRow p_row )
  {
    id = p_row.getId();
    id_is_set = p_row.getIdIsSet();
    id_is_modified = p_row.getIdIsModified();
    type = p_row.getType();
    type_is_set = p_row.getTypeIsSet();
    type_is_modified = p_row.getTypeIsModified();
    if ( !p_row.getParentIdIsNull() )
      parent_id = new Long( p_row.getParentId() );
    parent_id_is_set = p_row.getParentIdIsSet();
    parent_id_is_modified = p_row.getParentIdIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    seq_num = p_row.getSeqNum();
    seq_num_is_set = p_row.getSeqNumIsSet();
    seq_num_is_modified = p_row.getSeqNumIsModified();
    act_ratio = p_row.getActRatio();
    act_ratio_is_set = p_row.getActRatioIsSet();
    act_ratio_is_modified = p_row.getActRatioIsModified();
    tstamp = p_row.getTstamp();
    tstamp_is_set = p_row.getTstampIsSet();
    tstamp_is_modified = p_row.getTstampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      type_is_set = true;
      type_is_modified = true;
      parent_id_is_set = true;
      parent_id_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      seq_num_is_set = true;
      seq_num_is_modified = true;
      act_ratio_is_set = true;
      act_ratio_is_modified = true;
      tstamp_is_set = true;
      tstamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RlsCondOrderRow ) )
       return false;
    return fieldsEqual( (RlsCondOrderRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�RlsCondOrderRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( RlsCondOrderRow row )
  {
    if ( id != row.getId() )
      return false;
    if ( type != row.getType() )
      return false;
    if ( parent_id == null ) {
      if ( !row.getParentIdIsNull() )
        return false;
    } else if ( row.getParentIdIsNull() || ( parent_id.longValue() != row.getParentId() ) ) {
        return false;
    }
    if ( status != row.getStatus() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( seq_num != row.getSeqNum() )
      return false;
    if ( act_ratio != row.getActRatio() )
      return false;
    if ( tstamp == null ) {
      if ( row.getTstamp() != null )
        return false;
    } else if ( !tstamp.equals( row.getTstamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) id)
        + ((int) type)
        + (parent_id!=null? parent_id.hashCode(): 0) 
        + ((int) status)
        + ((int) account_id)
        + ((int) seq_num)
        + ((int) act_ratio)
        + (tstamp!=null? tstamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !type_is_set )
			throw new IllegalArgumentException("non-nullable field 'type' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !seq_num_is_set )
			throw new IllegalArgumentException("non-nullable field 'seq_num' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("id",new Long(id));
		map.put("type",new Long(type));
		if ( parent_id != null )
			map.put("parent_id",parent_id);
		if ( status_is_set )
			map.put("status",new Integer(status));
		map.put("account_id",new Long(account_id));
		map.put("seq_num",new Long(seq_num));
		if ( act_ratio_is_set )
			map.put("act_ratio",new Long(act_ratio));
		if ( tstamp_is_set )
			map.put("tstamp",tstamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( type_is_modified )
			map.put("type",new Long(type));
		if ( parent_id_is_modified )
			map.put("parent_id",parent_id);
		if ( status_is_modified )
			map.put("status",new Integer(status));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( seq_num_is_modified )
			map.put("seq_num",new Long(seq_num));
		if ( act_ratio_is_modified )
			map.put("act_ratio",new Long(act_ratio));
		if ( tstamp_is_modified )
			map.put("tstamp",tstamp);
		if (map.size() == 0) {
			if ( type_is_set )
				map.put("type",new Long(type));
			map.put("parent_id",parent_id);
			if ( status_is_set )
				map.put("status",new Integer(status));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( seq_num_is_set )
				map.put("seq_num",new Long(seq_num));
			if ( act_ratio_is_set )
				map.put("act_ratio",new Long(act_ratio));
			if ( tstamp_is_set )
				map.put("tstamp",tstamp);
		}
		return map;
	}


  /** 
   * <em>id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getId()
  {
    return id;
  }


  /** 
   * <em>id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIdIsSet() {
    return id_is_set;
  }


  /** 
   * <em>id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIdIsModified() {
    return id_is_modified;
  }


  /** 
   * <em>type</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getType()
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
   * <em>parent_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getParentId()
  {
    return ( parent_id==null? ((long)0): parent_id.longValue() );
  }


  /** 
   * <em>parent_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getParentIdIsNull()
  {
    return parent_id == null;
  }


  /** 
   * <em>parent_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParentIdIsSet() {
    return parent_id_is_set;
  }


  /** 
   * <em>parent_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParentIdIsModified() {
    return parent_id_is_modified;
  }


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getStatus()
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
   * <em>seq_num</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSeqNum()
  {
    return seq_num;
  }


  /** 
   * <em>seq_num</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSeqNumIsSet() {
    return seq_num_is_set;
  }


  /** 
   * <em>seq_num</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSeqNumIsModified() {
    return seq_num_is_modified;
  }


  /** 
   * <em>act_ratio</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getActRatio()
  {
    return act_ratio;
  }


  /** 
   * <em>act_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getActRatioIsSet() {
    return act_ratio_is_set;
  }


  /** 
   * <em>act_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getActRatioIsModified() {
    return act_ratio_is_modified;
  }


  /** 
   * <em>tstamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getTstamp()
  {
    return tstamp;
  }


  /** 
   * <em>tstamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTstampIsSet() {
    return tstamp_is_set;
  }


  /** 
   * <em>tstamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTstampIsModified() {
    return tstamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RlsCondOrderPK(id);
  }


  /** 
   * <em>id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_id <em>id</em>�J�����̒l������킷38���ȉ���long�l 
   */
  public final void setId( long p_id )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    id = p_id;
    id_is_set = true;
    id_is_modified = true;
  }


  /** 
   * <em>type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_type <em>type</em>�J�����̒l������킷long�l
   */
  public final void setType( long p_type )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    type = p_type;
    type_is_set = true;
    type_is_modified = true;
  }


  /** 
   * <em>parent_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_parentId <em>parent_id</em>�J�����̒l������킷long�l
   */
  public final void setParentId( long p_parentId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parent_id = new Long(p_parentId);
    parent_id_is_set = true;
    parent_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>parent_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setParentId( Long p_parentId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    parent_id = p_parentId;
    parent_id_is_set = true;
    parent_id_is_modified = true;
  }


  /** 
   * <em>status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_status <em>status</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setStatus( int p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷long�l
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>seq_num</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_seqNum <em>seq_num</em>�J�����̒l������킷long�l
   */
  public final void setSeqNum( long p_seqNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    seq_num = p_seqNum;
    seq_num_is_set = true;
    seq_num_is_modified = true;
  }


  /** 
   * <em>act_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_actRatio <em>act_ratio</em>�J�����̒l������킷long�l
   */
  public final void setActRatio( long p_actRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    act_ratio = p_actRatio;
    act_ratio_is_set = true;
    act_ratio_is_modified = true;
  }


  /** 
   * <em>tstamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tstamp <em>tstamp</em>�J�����̒l������킷9���ȉ���java.sql.Timestamp�l 
   */
  public final void setTstamp( java.sql.Timestamp p_tstamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tstamp = p_tstamp;
    tstamp_is_set = true;
    tstamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>tstamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setTstamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tstamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    tstamp_is_set = true;
    tstamp_is_modified = true;
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
                else if ( name.equals("act_ratio") ) {
                    return new Long( act_ratio );
                }
                break;
            case 'i':
                if ( name.equals("id") ) {
                    return new Long( id );
                }
                break;
            case 'p':
                if ( name.equals("parent_id") ) {
                    return this.parent_id;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return new Integer( status );
                }
                else if ( name.equals("seq_num") ) {
                    return new Long( seq_num );
                }
                break;
            case 't':
                if ( name.equals("type") ) {
                    return new Long( type );
                }
                else if ( name.equals("tstamp") ) {
                    return this.tstamp;
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
                else if ( name.equals("act_ratio") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'act_ratio' must be Long: '"+value+"' is not." );
                    this.act_ratio = ((Long) value).longValue();
                    if (this.act_ratio_is_set)
                        this.act_ratio_is_modified = true;
                    this.act_ratio_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'id' must be Long: '"+value+"' is not." );
                    this.id = ((Long) value).longValue();
                    if (this.id_is_set)
                        this.id_is_modified = true;
                    this.id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("parent_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'parent_id' must be Long: '"+value+"' is not." );
                    this.parent_id = (Long) value;
                    if (this.parent_id_is_set)
                        this.parent_id_is_modified = true;
                    this.parent_id_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'status' must be Integer: '"+value+"' is not." );
                    this.status = ((Integer) value).intValue();
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("seq_num") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'seq_num' must be Long: '"+value+"' is not." );
                    this.seq_num = ((Long) value).longValue();
                    if (this.seq_num_is_set)
                        this.seq_num_is_modified = true;
                    this.seq_num_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("type") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'type' must be Long: '"+value+"' is not." );
                    this.type = ((Long) value).longValue();
                    if (this.type_is_set)
                        this.type_is_modified = true;
                    this.type_is_set = true;
                    return;
                }
                else if ( name.equals("tstamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'tstamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.tstamp = (java.sql.Timestamp) value;
                    if (this.tstamp_is_set)
                        this.tstamp_is_modified = true;
                    this.tstamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
