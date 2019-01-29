head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.39.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CooperateBankMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * cooperate_bank_master�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link CooperateBankMasterRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link CooperateBankMasterParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link CooperateBankMasterParams}��{@@link CooperateBankMasterRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CooperateBankMasterPK 
 * @@see CooperateBankMasterRow 
 */
public class CooperateBankMasterParams extends Params implements CooperateBankMasterRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "cooperate_bank_master";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = CooperateBankMasterRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return CooperateBankMasterRow.TYPE;
  }


  /** 
   * <em>pay_scheme_id</em>�J�����̒l 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>name</em>�J�����̒l 
   */
  public  String  name;

  /** 
   * <em>short_name</em>�J�����̒l 
   */
  public  String  short_name;

  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean name_is_set = false;
  private boolean name_is_modified = false;
  private boolean short_name_is_set = false;
  private boolean short_name_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "pay_scheme_id=" + pay_scheme_id
      + "," + "name=" +name
      + "," + "short_name=" +short_name
      + "}";
  }


  /** 
   * �l�����ݒ��CooperateBankMasterParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public CooperateBankMasterParams() {
    pay_scheme_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���CooperateBankMasterRow�I�u�W�F�N�g�̒l�𗘗p����CooperateBankMasterParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����CooperateBankMasterRow�I�u�W�F�N�g 
   */
  public CooperateBankMasterParams( CooperateBankMasterRow p_row )
  {
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    name = p_row.getName();
    name_is_set = p_row.getNameIsSet();
    name_is_modified = p_row.getNameIsModified();
    short_name = p_row.getShortName();
    short_name_is_set = p_row.getShortNameIsSet();
    short_name_is_modified = p_row.getShortNameIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      name_is_set = true;
      name_is_modified = true;
      short_name_is_set = true;
      short_name_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CooperateBankMasterRow ) )
       return false;
    return fieldsEqual( (CooperateBankMasterRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�CooperateBankMasterRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( CooperateBankMasterRow row )
  {
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( name == null ) {
      if ( row.getName() != null )
        return false;
    } else if ( !name.equals( row.getName() ) ) {
        return false;
    }
    if ( short_name == null ) {
      if ( row.getShortName() != null )
        return false;
    } else if ( !short_name.equals( row.getShortName() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (name!=null? name.hashCode(): 0) 
        + (short_name!=null? short_name.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !name_is_set )
			throw new IllegalArgumentException("non-nullable field 'name' must be set before inserting.");
		if ( !short_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'short_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("pay_scheme_id",pay_scheme_id);
		map.put("name",name);
		map.put("short_name",short_name);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( name_is_modified )
			map.put("name",name);
		if ( short_name_is_modified )
			map.put("short_name",short_name);
		if (map.size() == 0) {
			if ( name_is_set )
				map.put("name",name);
			if ( short_name_is_set )
				map.put("short_name",short_name);
		}
		return map;
	}


  /** 
   * <em>pay_scheme_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPaySchemeId()
  {
    return pay_scheme_id;
  }


  /** 
   * <em>pay_scheme_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaySchemeIdIsSet() {
    return pay_scheme_id_is_set;
  }


  /** 
   * <em>pay_scheme_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaySchemeIdIsModified() {
    return pay_scheme_id_is_modified;
  }


  /** 
   * <em>name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getName()
  {
    return name;
  }


  /** 
   * <em>name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameIsSet() {
    return name_is_set;
  }


  /** 
   * <em>name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNameIsModified() {
    return name_is_modified;
  }


  /** 
   * <em>short_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getShortName()
  {
    return short_name;
  }


  /** 
   * <em>short_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getShortNameIsSet() {
    return short_name_is_set;
  }


  /** 
   * <em>short_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getShortNameIsModified() {
    return short_name_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CooperateBankMasterPK(pay_scheme_id);
  }


  /** 
   * <em>pay_scheme_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paySchemeId <em>pay_scheme_id</em>�J�����̒l������킷11���ȉ���String�l 
   */
  public final void setPaySchemeId( String p_paySchemeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_scheme_id = p_paySchemeId;
    pay_scheme_id_is_set = true;
    pay_scheme_id_is_modified = true;
  }


  /** 
   * <em>name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_name <em>name</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public final void setName( String p_name )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name = p_name;
    name_is_set = true;
    name_is_modified = true;
  }


  /** 
   * <em>short_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_shortName <em>short_name</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public final void setShortName( String p_shortName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    short_name = p_shortName;
    short_name_is_set = true;
    short_name_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'n':
                if ( name.equals("name") ) {
                    return this.name;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
                }
                break;
            case 's':
                if ( name.equals("short_name") ) {
                    return this.short_name;
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
            case 'n':
                if ( name.equals("name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name' must be String: '"+value+"' is not." );
                    this.name = (String) value;
                    if (this.name_is_set)
                        this.name_is_modified = true;
                    this.name_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_scheme_id' must be String: '"+value+"' is not." );
                    this.pay_scheme_id = (String) value;
                    if (this.pay_scheme_id_is_set)
                        this.pay_scheme_id_is_modified = true;
                    this.pay_scheme_id_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("short_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'short_name' must be String: '"+value+"' is not." );
                    this.short_name = (String) value;
                    if (this.short_name_is_set)
                        this.short_name_is_modified = true;
                    this.short_name_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
