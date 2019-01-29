head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CodeTranslationParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * code_translation�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link CodeTranslationRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link CodeTranslationParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link CodeTranslationParams}��{@@link CodeTranslationRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CodeTranslationPK 
 * @@see CodeTranslationRow 
 */
public class CodeTranslationParams extends Params implements CodeTranslationRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "code_translation";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = CodeTranslationRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return CodeTranslationRow.TYPE;
  }


  /** 
   * <em>code_type</em>�J�����̒l 
   */
  public  String  code_type;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>code</em>�J�����̒l 
   */
  public  String  code;

  /** 
   * <em>display_message</em>�J�����̒l 
   */
  public  String  display_message;

  /** 
   * <em>create_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  create_date;

  /** 
   * <em>last_update_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_update_date;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  private boolean code_type_is_set = false;
  private boolean code_type_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean code_is_set = false;
  private boolean code_is_modified = false;
  private boolean display_message_is_set = false;
  private boolean display_message_is_modified = false;
  private boolean create_date_is_set = false;
  private boolean create_date_is_modified = false;
  private boolean last_update_date_is_set = false;
  private boolean last_update_date_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "code_type=" + code_type
      + "," + "institution_code=" + institution_code
      + "," + "code=" + code
      + "," + "display_message=" +display_message
      + "," + "create_date=" +create_date
      + "," + "last_update_date=" +last_update_date
      + "," + "last_updater=" +last_updater
      + "}";
  }


  /** 
   * �l�����ݒ��CodeTranslationParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public CodeTranslationParams() {
    code_type_is_modified = true;
    institution_code_is_modified = true;
    code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���CodeTranslationRow�I�u�W�F�N�g�̒l�𗘗p����CodeTranslationParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����CodeTranslationRow�I�u�W�F�N�g 
   */
  public CodeTranslationParams( CodeTranslationRow p_row )
  {
    code_type = p_row.getCodeType();
    code_type_is_set = p_row.getCodeTypeIsSet();
    code_type_is_modified = p_row.getCodeTypeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    code = p_row.getCode();
    code_is_set = p_row.getCodeIsSet();
    code_is_modified = p_row.getCodeIsModified();
    display_message = p_row.getDisplayMessage();
    display_message_is_set = p_row.getDisplayMessageIsSet();
    display_message_is_modified = p_row.getDisplayMessageIsModified();
    create_date = p_row.getCreateDate();
    create_date_is_set = p_row.getCreateDateIsSet();
    create_date_is_modified = p_row.getCreateDateIsModified();
    last_update_date = p_row.getLastUpdateDate();
    last_update_date_is_set = p_row.getLastUpdateDateIsSet();
    last_update_date_is_modified = p_row.getLastUpdateDateIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      display_message_is_set = true;
      display_message_is_modified = true;
      create_date_is_set = true;
      create_date_is_modified = true;
      last_update_date_is_set = true;
      last_update_date_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CodeTranslationRow ) )
       return false;
    return fieldsEqual( (CodeTranslationRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�CodeTranslationRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( CodeTranslationRow row )
  {
    if ( code_type == null ) {
      if ( row.getCodeType() != null )
        return false;
    } else if ( !code_type.equals( row.getCodeType() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( code == null ) {
      if ( row.getCode() != null )
        return false;
    } else if ( !code.equals( row.getCode() ) ) {
        return false;
    }
    if ( display_message == null ) {
      if ( row.getDisplayMessage() != null )
        return false;
    } else if ( !display_message.equals( row.getDisplayMessage() ) ) {
        return false;
    }
    if ( create_date == null ) {
      if ( row.getCreateDate() != null )
        return false;
    } else if ( !create_date.equals( row.getCreateDate() ) ) {
        return false;
    }
    if ( last_update_date == null ) {
      if ( row.getLastUpdateDate() != null )
        return false;
    } else if ( !last_update_date.equals( row.getLastUpdateDate() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (code_type!=null? code_type.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (code!=null? code.hashCode(): 0) 
        + (display_message!=null? display_message.hashCode(): 0) 
        + (create_date!=null? create_date.hashCode(): 0) 
        + (last_update_date!=null? last_update_date.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("code_type",code_type);
		map.put("institution_code",institution_code);
		map.put("code",code);
		if ( display_message != null )
			map.put("display_message",display_message);
		if ( create_date != null )
			map.put("create_date",create_date);
		if ( last_update_date != null )
			map.put("last_update_date",last_update_date);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( display_message_is_modified )
			map.put("display_message",display_message);
		if ( create_date_is_modified )
			map.put("create_date",create_date);
		if ( last_update_date_is_modified )
			map.put("last_update_date",last_update_date);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if (map.size() == 0) {
			map.put("display_message",display_message);
			map.put("create_date",create_date);
			map.put("last_update_date",last_update_date);
			map.put("last_updater",last_updater);
		}
		return map;
	}


  /** 
   * <em>code_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCodeType()
  {
    return code_type;
  }


  /** 
   * <em>code_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCodeTypeIsSet() {
    return code_type_is_set;
  }


  /** 
   * <em>code_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCodeTypeIsModified() {
    return code_type_is_modified;
  }


  /** 
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCode()
  {
    return code;
  }


  /** 
   * <em>code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCodeIsSet() {
    return code_is_set;
  }


  /** 
   * <em>code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCodeIsModified() {
    return code_is_modified;
  }


  /** 
   * <em>display_message</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDisplayMessage()
  {
    return display_message;
  }


  /** 
   * <em>display_message</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDisplayMessageIsSet() {
    return display_message_is_set;
  }


  /** 
   * <em>display_message</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDisplayMessageIsModified() {
    return display_message_is_modified;
  }


  /** 
   * <em>create_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreateDate()
  {
    return create_date;
  }


  /** 
   * <em>create_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreateDateIsSet() {
    return create_date_is_set;
  }


  /** 
   * <em>create_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreateDateIsModified() {
    return create_date_is_modified;
  }


  /** 
   * <em>last_update_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdateDate()
  {
    return last_update_date;
  }


  /** 
   * <em>last_update_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdateDateIsSet() {
    return last_update_date_is_set;
  }


  /** 
   * <em>last_update_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdateDateIsModified() {
    return last_update_date_is_modified;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CodeTranslationPK(code_type, institution_code, code);
  }


  /** 
   * <em>code_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_codeType <em>code_type</em>�J�����̒l������킷64���ȉ���String�l 
   */
  public final void setCodeType( String p_codeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    code_type = p_codeType;
    code_type_is_set = true;
    code_type_is_modified = true;
  }


  /** 
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_code <em>code</em>�J�����̒l������킷64���ȉ���String�l 
   */
  public final void setCode( String p_code )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    code = p_code;
    code_is_set = true;
    code_is_modified = true;
  }


  /** 
   * <em>display_message</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_displayMessage <em>display_message</em>�J�����̒l������킷128���ȉ���String�l 
   */
  public final void setDisplayMessage( String p_displayMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_message = p_displayMessage;
    display_message_is_set = true;
    display_message_is_modified = true;
  }


  /** 
   * <em>create_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createDate <em>create_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreateDate( java.sql.Timestamp p_createDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_date = p_createDate;
    create_date_is_set = true;
    create_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>create_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    create_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    create_date_is_set = true;
    create_date_is_modified = true;
  }


  /** 
   * <em>last_update_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdateDate <em>last_update_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdateDate( java.sql.Timestamp p_lastUpdateDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = p_lastUpdateDate;
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_update_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
  }


  /** 
   * <em>last_updater</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdater <em>last_updater</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("code_type") ) {
                    return this.code_type;
                }
                else if ( name.equals("code") ) {
                    return this.code;
                }
                else if ( name.equals("create_date") ) {
                    return this.create_date;
                }
                break;
            case 'd':
                if ( name.equals("display_message") ) {
                    return this.display_message;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    return this.last_update_date;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
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
                if ( name.equals("code_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'code_type' must be String: '"+value+"' is not." );
                    this.code_type = (String) value;
                    if (this.code_type_is_set)
                        this.code_type_is_modified = true;
                    this.code_type_is_set = true;
                    return;
                }
                else if ( name.equals("code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'code' must be String: '"+value+"' is not." );
                    this.code = (String) value;
                    if (this.code_is_set)
                        this.code_is_modified = true;
                    this.code_is_set = true;
                    return;
                }
                else if ( name.equals("create_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'create_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.create_date = (java.sql.Timestamp) value;
                    if (this.create_date_is_set)
                        this.create_date_is_modified = true;
                    this.create_date_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("display_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'display_message' must be String: '"+value+"' is not." );
                    this.display_message = (String) value;
                    if (this.display_message_is_set)
                        this.display_message_is_modified = true;
                    this.display_message_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_update_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_update_date = (java.sql.Timestamp) value;
                    if (this.last_update_date_is_set)
                        this.last_update_date_is_modified = true;
                    this.last_update_date_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
