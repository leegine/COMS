head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapParams.java;


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
 * affinity_key_based_map�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AffinityKeyBasedMapRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AffinityKeyBasedMapParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AffinityKeyBasedMapParams}��{@@link AffinityKeyBasedMapRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityKeyBasedMapPK 
 * @@see AffinityKeyBasedMapRow 
 */
public class AffinityKeyBasedMapParams extends Params implements AffinityKeyBasedMapRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "affinity_key_based_map";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AffinityKeyBasedMapRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AffinityKeyBasedMapRow.TYPE;
  }


  /** 
   * <em>app_id</em>�J�����̒l 
   */
  public  String  app_id;

  /** 
   * <em>db_id</em>�J�����̒l 
   */
  public  String  db_id;

  /** 
   * <em>key</em>�J�����̒l 
   */
  public  String  key;

  private boolean key_is_set = false;
  private boolean key_is_modified = false;
  private boolean app_id_is_set = false;
  private boolean app_id_is_modified = false;
  private boolean db_id_is_set = false;
  private boolean db_id_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "app_id=" + app_id
      + "," + "db_id=" + db_id
      + "," + "key=" +key
      + "}";
  }


  /** 
   * �l�����ݒ��AffinityKeyBasedMapParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AffinityKeyBasedMapParams() {
    app_id_is_modified = true;
    db_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AffinityKeyBasedMapRow�I�u�W�F�N�g�̒l�𗘗p����AffinityKeyBasedMapParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AffinityKeyBasedMapRow�I�u�W�F�N�g 
   */
  public AffinityKeyBasedMapParams( AffinityKeyBasedMapRow p_row )
  {
    app_id = p_row.getAppId();
    app_id_is_set = p_row.getAppIdIsSet();
    app_id_is_modified = p_row.getAppIdIsModified();
    db_id = p_row.getDbId();
    db_id_is_set = p_row.getDbIdIsSet();
    db_id_is_modified = p_row.getDbIdIsModified();
    key = p_row.getKey();
    key_is_set = p_row.getKeyIsSet();
    key_is_modified = p_row.getKeyIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      key_is_set = true;
      key_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AffinityKeyBasedMapRow ) )
       return false;
    return fieldsEqual( (AffinityKeyBasedMapRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AffinityKeyBasedMapRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AffinityKeyBasedMapRow row )
  {
    if ( key == null ) {
      if ( row.getKey() != null )
        return false;
    } else if ( !key.equals( row.getKey() ) ) {
        return false;
    }
    if ( app_id == null ) {
      if ( row.getAppId() != null )
        return false;
    } else if ( !app_id.equals( row.getAppId() ) ) {
        return false;
    }
    if ( db_id == null ) {
      if ( row.getDbId() != null )
        return false;
    } else if ( !db_id.equals( row.getDbId() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (key!=null? key.hashCode(): 0) 
        + (app_id!=null? app_id.hashCode(): 0) 
        + (db_id!=null? db_id.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !key_is_set )
			throw new IllegalArgumentException("non-nullable field 'key' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("key",key);
		map.put("app_id",app_id);
		map.put("db_id",db_id);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( key_is_modified )
			map.put("key",key);
		if (map.size() == 0) {
			if ( key_is_set )
				map.put("key",key);
		}
		return map;
	}


  /** 
   * <em>key</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getKey()
  {
    return key;
  }


  /** 
   * <em>key</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getKeyIsSet() {
    return key_is_set;
  }


  /** 
   * <em>key</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getKeyIsModified() {
    return key_is_modified;
  }


  /** 
   * <em>app_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAppId()
  {
    return app_id;
  }


  /** 
   * <em>app_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppIdIsSet() {
    return app_id_is_set;
  }


  /** 
   * <em>app_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppIdIsModified() {
    return app_id_is_modified;
  }


  /** 
   * <em>db_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDbId()
  {
    return db_id;
  }


  /** 
   * <em>db_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDbIdIsSet() {
    return db_id_is_set;
  }


  /** 
   * <em>db_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDbIdIsModified() {
    return db_id_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AffinityKeyBasedMapPK(app_id, db_id);
  }


  /** 
   * <em>key</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_key <em>key</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setKey( String p_key )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    key = p_key;
    key_is_set = true;
    key_is_modified = true;
  }


  /** 
   * <em>app_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appId <em>app_id</em>�J�����̒l������킷18���ȉ���String�l 
   */
  public final void setAppId( String p_appId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    app_id = p_appId;
    app_id_is_set = true;
    app_id_is_modified = true;
  }


  /** 
   * <em>db_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dbId <em>db_id</em>�J�����̒l������킷18���ȉ���String�l 
   */
  public final void setDbId( String p_dbId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    db_id = p_dbId;
    db_id_is_set = true;
    db_id_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("app_id") ) {
                    return this.app_id;
                }
                break;
            case 'd':
                if ( name.equals("db_id") ) {
                    return this.db_id;
                }
                break;
            case 'k':
                if ( name.equals("key") ) {
                    return this.key;
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
                if ( name.equals("app_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'app_id' must be String: '"+value+"' is not." );
                    this.app_id = (String) value;
                    if (this.app_id_is_set)
                        this.app_id_is_modified = true;
                    this.app_id_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("db_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'db_id' must be String: '"+value+"' is not." );
                    this.db_id = (String) value;
                    if (this.db_id_is_set)
                        this.db_id_is_modified = true;
                    this.db_id_is_set = true;
                    return;
                }
                break;
            case 'k':
                if ( name.equals("key") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'key' must be String: '"+value+"' is not." );
                    this.key = (String) value;
                    if (this.key_is_set)
                        this.key_is_modified = true;
                    this.key_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
