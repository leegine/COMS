head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.22.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * affinity_range_based_map�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AffinityRangeBasedMapRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AffinityRangeBasedMapParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AffinityRangeBasedMapParams}��{@@link AffinityRangeBasedMapRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityRangeBasedMapPK 
 * @@see AffinityRangeBasedMapRow 
 */
public class AffinityRangeBasedMapParams extends Params implements AffinityRangeBasedMapRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "affinity_range_based_map";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AffinityRangeBasedMapRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AffinityRangeBasedMapRow.TYPE;
  }


  /** 
   * <em>key_start</em>�J�����̒l 
   */
  public  long  key_start;

  /** 
   * <em>key_end</em>�J�����̒l 
   */
  public  long  key_end;

  /** 
   * <em>range_order_no</em>�J�����̒l 
   */
  public  int  range_order_no;

  /** 
   * <em>server_type</em>�J�����̒l 
   */
  public  int  server_type;

  /** 
   * <em>ctx_name</em>�J�����̒l 
   */
  public  String  ctx_name;

  /** 
   * <em>server_id</em>�J�����̒l 
   */
  public  String  server_id;

  private boolean key_start_is_set = false;
  private boolean key_start_is_modified = false;
  private boolean key_end_is_set = false;
  private boolean key_end_is_modified = false;
  private boolean range_order_no_is_set = false;
  private boolean range_order_no_is_modified = false;
  private boolean server_type_is_set = false;
  private boolean server_type_is_modified = false;
  private boolean server_id_is_set = false;
  private boolean server_id_is_modified = false;
  private boolean ctx_name_is_set = false;
  private boolean ctx_name_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "key_start=" + key_start
      + "," + "key_end=" + key_end
      + "," + "range_order_no=" + range_order_no
      + "," + "server_type=" + server_type
      + "," + "ctx_name=" + ctx_name
      + "," + "server_id=" +server_id
      + "}";
  }


  /** 
   * �l�����ݒ��AffinityRangeBasedMapParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AffinityRangeBasedMapParams() {
    key_start_is_modified = true;
    key_end_is_modified = true;
    range_order_no_is_modified = true;
    server_type_is_modified = true;
    ctx_name_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AffinityRangeBasedMapRow�I�u�W�F�N�g�̒l�𗘗p����AffinityRangeBasedMapParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AffinityRangeBasedMapRow�I�u�W�F�N�g 
   */
  public AffinityRangeBasedMapParams( AffinityRangeBasedMapRow p_row )
  {
    key_start = p_row.getKeyStart();
    key_start_is_set = p_row.getKeyStartIsSet();
    key_start_is_modified = p_row.getKeyStartIsModified();
    key_end = p_row.getKeyEnd();
    key_end_is_set = p_row.getKeyEndIsSet();
    key_end_is_modified = p_row.getKeyEndIsModified();
    range_order_no = p_row.getRangeOrderNo();
    range_order_no_is_set = p_row.getRangeOrderNoIsSet();
    range_order_no_is_modified = p_row.getRangeOrderNoIsModified();
    server_type = p_row.getServerType();
    server_type_is_set = p_row.getServerTypeIsSet();
    server_type_is_modified = p_row.getServerTypeIsModified();
    ctx_name = p_row.getCtxName();
    ctx_name_is_set = p_row.getCtxNameIsSet();
    ctx_name_is_modified = p_row.getCtxNameIsModified();
    server_id = p_row.getServerId();
    server_id_is_set = p_row.getServerIdIsSet();
    server_id_is_modified = p_row.getServerIdIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      server_id_is_set = true;
      server_id_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AffinityRangeBasedMapRow ) )
       return false;
    return fieldsEqual( (AffinityRangeBasedMapRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AffinityRangeBasedMapRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AffinityRangeBasedMapRow row )
  {
    if ( key_start != row.getKeyStart() )
      return false;
    if ( key_end != row.getKeyEnd() )
      return false;
    if ( range_order_no != row.getRangeOrderNo() )
      return false;
    if ( server_type != row.getServerType() )
      return false;
    if ( server_id == null ) {
      if ( row.getServerId() != null )
        return false;
    } else if ( !server_id.equals( row.getServerId() ) ) {
        return false;
    }
    if ( ctx_name == null ) {
      if ( row.getCtxName() != null )
        return false;
    } else if ( !ctx_name.equals( row.getCtxName() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) key_start)
        + ((int) key_end)
        + ((int) range_order_no)
        + ((int) server_type)
        + (server_id!=null? server_id.hashCode(): 0) 
        + (ctx_name!=null? ctx_name.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !server_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'server_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("key_start",new Long(key_start));
		map.put("key_end",new Long(key_end));
		map.put("range_order_no",new Integer(range_order_no));
		map.put("server_type",new Integer(server_type));
		map.put("server_id",server_id);
		map.put("ctx_name",ctx_name);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( server_id_is_modified )
			map.put("server_id",server_id);
		if (map.size() == 0) {
			if ( server_id_is_set )
				map.put("server_id",server_id);
		}
		return map;
	}


  /** 
   * <em>key_start</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getKeyStart()
  {
    return key_start;
  }


  /** 
   * <em>key_start</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getKeyStartIsSet() {
    return key_start_is_set;
  }


  /** 
   * <em>key_start</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getKeyStartIsModified() {
    return key_start_is_modified;
  }


  /** 
   * <em>key_end</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getKeyEnd()
  {
    return key_end;
  }


  /** 
   * <em>key_end</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getKeyEndIsSet() {
    return key_end_is_set;
  }


  /** 
   * <em>key_end</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getKeyEndIsModified() {
    return key_end_is_modified;
  }


  /** 
   * <em>range_order_no</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getRangeOrderNo()
  {
    return range_order_no;
  }


  /** 
   * <em>range_order_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRangeOrderNoIsSet() {
    return range_order_no_is_set;
  }


  /** 
   * <em>range_order_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRangeOrderNoIsModified() {
    return range_order_no_is_modified;
  }


  /** 
   * <em>server_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getServerType()
  {
    return server_type;
  }


  /** 
   * <em>server_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServerTypeIsSet() {
    return server_type_is_set;
  }


  /** 
   * <em>server_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServerTypeIsModified() {
    return server_type_is_modified;
  }


  /** 
   * <em>server_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getServerId()
  {
    return server_id;
  }


  /** 
   * <em>server_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServerIdIsSet() {
    return server_id_is_set;
  }


  /** 
   * <em>server_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServerIdIsModified() {
    return server_id_is_modified;
  }


  /** 
   * <em>ctx_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCtxName()
  {
    return ctx_name;
  }


  /** 
   * <em>ctx_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCtxNameIsSet() {
    return ctx_name_is_set;
  }


  /** 
   * <em>ctx_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCtxNameIsModified() {
    return ctx_name_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AffinityRangeBasedMapPK(key_start, key_end, range_order_no, server_type, ctx_name);
  }


  /** 
   * <em>key_start</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_keyStart <em>key_start</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setKeyStart( long p_keyStart )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    key_start = p_keyStart;
    key_start_is_set = true;
    key_start_is_modified = true;
  }


  /** 
   * <em>key_end</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_keyEnd <em>key_end</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setKeyEnd( long p_keyEnd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    key_end = p_keyEnd;
    key_end_is_set = true;
    key_end_is_modified = true;
  }


  /** 
   * <em>range_order_no</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rangeOrderNo <em>range_order_no</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setRangeOrderNo( int p_rangeOrderNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    range_order_no = p_rangeOrderNo;
    range_order_no_is_set = true;
    range_order_no_is_modified = true;
  }


  /** 
   * <em>server_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serverType <em>server_type</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setServerType( int p_serverType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    server_type = p_serverType;
    server_type_is_set = true;
    server_type_is_modified = true;
  }


  /** 
   * <em>server_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serverId <em>server_id</em>�J�����̒l������킷18���ȉ���String�l 
   */
  public final void setServerId( String p_serverId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    server_id = p_serverId;
    server_id_is_set = true;
    server_id_is_modified = true;
  }


  /** 
   * <em>ctx_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ctxName <em>ctx_name</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public final void setCtxName( String p_ctxName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ctx_name = p_ctxName;
    ctx_name_is_set = true;
    ctx_name_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("ctx_name") ) {
                    return this.ctx_name;
                }
                break;
            case 'k':
                if ( name.equals("key_start") ) {
                    return new Long( key_start );
                }
                else if ( name.equals("key_end") ) {
                    return new Long( key_end );
                }
                break;
            case 'r':
                if ( name.equals("range_order_no") ) {
                    return new Integer( range_order_no );
                }
                break;
            case 's':
                if ( name.equals("server_type") ) {
                    return new Integer( server_type );
                }
                else if ( name.equals("server_id") ) {
                    return this.server_id;
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
                if ( name.equals("ctx_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ctx_name' must be String: '"+value+"' is not." );
                    this.ctx_name = (String) value;
                    if (this.ctx_name_is_set)
                        this.ctx_name_is_modified = true;
                    this.ctx_name_is_set = true;
                    return;
                }
                break;
            case 'k':
                if ( name.equals("key_start") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'key_start' must be Long: '"+value+"' is not." );
                    this.key_start = ((Long) value).longValue();
                    if (this.key_start_is_set)
                        this.key_start_is_modified = true;
                    this.key_start_is_set = true;
                    return;
                }
                else if ( name.equals("key_end") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'key_end' must be Long: '"+value+"' is not." );
                    this.key_end = ((Long) value).longValue();
                    if (this.key_end_is_set)
                        this.key_end_is_modified = true;
                    this.key_end_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("range_order_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'range_order_no' must be Integer: '"+value+"' is not." );
                    this.range_order_no = ((Integer) value).intValue();
                    if (this.range_order_no_is_set)
                        this.range_order_no_is_modified = true;
                    this.range_order_no_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("server_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'server_type' must be Integer: '"+value+"' is not." );
                    this.server_type = ((Integer) value).intValue();
                    if (this.server_type_is_set)
                        this.server_type_is_modified = true;
                    this.server_type_is_set = true;
                    return;
                }
                else if ( name.equals("server_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'server_id' must be String: '"+value+"' is not." );
                    this.server_id = (String) value;
                    if (this.server_id_is_set)
                        this.server_id_is_modified = true;
                    this.server_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
