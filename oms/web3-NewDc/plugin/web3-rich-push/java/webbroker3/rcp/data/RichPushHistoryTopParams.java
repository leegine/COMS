head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushHistoryTopParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * rich_push_history_top�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link RichPushHistoryTopRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link RichPushHistoryTopParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link RichPushHistoryTopParams}��{@@link RichPushHistoryTopRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RichPushHistoryTopPK 
 * @@see RichPushHistoryTopRow 
 */
public class RichPushHistoryTopParams extends Params implements RichPushHistoryTopRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rich_push_history_top";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = RichPushHistoryTopRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return RichPushHistoryTopRow.TYPE;
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

  private boolean tpk_is_set = false;
  private boolean tpk_is_modified = false;
  private boolean type_is_set = false;
  private boolean type_is_modified = false;
  private boolean serlnum_is_set = false;
  private boolean serlnum_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "serlnum=" + serlnum
      + "," + "tpk=" +tpk
      + "," + "type=" +type
      + "}";
  }


  /** 
   * �l�����ݒ��RichPushHistoryTopParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public RichPushHistoryTopParams() {
    serlnum_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���RichPushHistoryTopRow�I�u�W�F�N�g�̒l�𗘗p����RichPushHistoryTopParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����RichPushHistoryTopRow�I�u�W�F�N�g 
   */
  public RichPushHistoryTopParams( RichPushHistoryTopRow p_row )
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
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RichPushHistoryTopRow ) )
       return false;
    return fieldsEqual( (RichPushHistoryTopRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�RichPushHistoryTopRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( RichPushHistoryTopRow row )
  {
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
    if ( serlnum != row.getSerlnum() )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (tpk!=null? tpk.hashCode(): 0) 
        + (type!=null? type.hashCode(): 0) 
        + ((int) serlnum)
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
		map.put("tpk",tpk);
		map.put("type",type);
		map.put("serlnum",new Long(serlnum));
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
		if (map.size() == 0) {
			if ( tpk_is_set )
				map.put("tpk",tpk);
			if ( type_is_set )
				map.put("type",type);
		}
		return map;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RichPushHistoryTopPK(serlnum);
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
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 's':
                if ( name.equals("serlnum") ) {
                    return new Long( serlnum );
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
