head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FinInstitutionParams.java;


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
 * fin_institution�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FinInstitutionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FinInstitutionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FinInstitutionParams}��{@@link FinInstitutionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FinInstitutionPK 
 * @@see FinInstitutionRow 
 */
public class FinInstitutionParams extends Params implements FinInstitutionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "fin_institution";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FinInstitutionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FinInstitutionRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>fin_institution_code</em>�J�����̒l 
   */
  public  String  fin_institution_code;

  /** 
   * <em>fin_institution_name_kanji</em>�J�����̒l 
   */
  public  String  fin_institution_name_kanji;

  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l 
   */
  public  String  fin_institution_name_kana;

  /** 
   * <em>cash_transfer_type</em>�J�����̒l 
   */
  public  String  cash_transfer_type;

  /** 
   * <em>cash_transfer_sonar_code</em>�J�����̒l 
   */
  public  String  cash_transfer_sonar_code;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean fin_institution_code_is_set = false;
  private boolean fin_institution_code_is_modified = false;
  private boolean fin_institution_name_kanji_is_set = false;
  private boolean fin_institution_name_kanji_is_modified = false;
  private boolean fin_institution_name_kana_is_set = false;
  private boolean fin_institution_name_kana_is_modified = false;
  private boolean cash_transfer_type_is_set = false;
  private boolean cash_transfer_type_is_modified = false;
  private boolean cash_transfer_sonar_code_is_set = false;
  private boolean cash_transfer_sonar_code_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "fin_institution_code=" + fin_institution_code
      + "," + "fin_institution_name_kanji=" +fin_institution_name_kanji
      + "," + "fin_institution_name_kana=" +fin_institution_name_kana
      + "," + "cash_transfer_type=" +cash_transfer_type
      + "," + "cash_transfer_sonar_code=" +cash_transfer_sonar_code
      + "}";
  }


  /** 
   * �l�����ݒ��FinInstitutionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FinInstitutionParams() {
    institution_code_is_modified = true;
    fin_institution_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FinInstitutionRow�I�u�W�F�N�g�̒l�𗘗p����FinInstitutionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FinInstitutionRow�I�u�W�F�N�g 
   */
  public FinInstitutionParams( FinInstitutionRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    fin_institution_code = p_row.getFinInstitutionCode();
    fin_institution_code_is_set = p_row.getFinInstitutionCodeIsSet();
    fin_institution_code_is_modified = p_row.getFinInstitutionCodeIsModified();
    fin_institution_name_kanji = p_row.getFinInstitutionNameKanji();
    fin_institution_name_kanji_is_set = p_row.getFinInstitutionNameKanjiIsSet();
    fin_institution_name_kanji_is_modified = p_row.getFinInstitutionNameKanjiIsModified();
    fin_institution_name_kana = p_row.getFinInstitutionNameKana();
    fin_institution_name_kana_is_set = p_row.getFinInstitutionNameKanaIsSet();
    fin_institution_name_kana_is_modified = p_row.getFinInstitutionNameKanaIsModified();
    cash_transfer_type = p_row.getCashTransferType();
    cash_transfer_type_is_set = p_row.getCashTransferTypeIsSet();
    cash_transfer_type_is_modified = p_row.getCashTransferTypeIsModified();
    cash_transfer_sonar_code = p_row.getCashTransferSonarCode();
    cash_transfer_sonar_code_is_set = p_row.getCashTransferSonarCodeIsSet();
    cash_transfer_sonar_code_is_modified = p_row.getCashTransferSonarCodeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      fin_institution_name_kanji_is_set = true;
      fin_institution_name_kanji_is_modified = true;
      fin_institution_name_kana_is_set = true;
      fin_institution_name_kana_is_modified = true;
      cash_transfer_type_is_set = true;
      cash_transfer_type_is_modified = true;
      cash_transfer_sonar_code_is_set = true;
      cash_transfer_sonar_code_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof FinInstitutionRow ) )
       return false;
    return fieldsEqual( (FinInstitutionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FinInstitutionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FinInstitutionRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( fin_institution_code == null ) {
      if ( row.getFinInstitutionCode() != null )
        return false;
    } else if ( !fin_institution_code.equals( row.getFinInstitutionCode() ) ) {
        return false;
    }
    if ( fin_institution_name_kanji == null ) {
      if ( row.getFinInstitutionNameKanji() != null )
        return false;
    } else if ( !fin_institution_name_kanji.equals( row.getFinInstitutionNameKanji() ) ) {
        return false;
    }
    if ( fin_institution_name_kana == null ) {
      if ( row.getFinInstitutionNameKana() != null )
        return false;
    } else if ( !fin_institution_name_kana.equals( row.getFinInstitutionNameKana() ) ) {
        return false;
    }
    if ( cash_transfer_type == null ) {
      if ( row.getCashTransferType() != null )
        return false;
    } else if ( !cash_transfer_type.equals( row.getCashTransferType() ) ) {
        return false;
    }
    if ( cash_transfer_sonar_code == null ) {
      if ( row.getCashTransferSonarCode() != null )
        return false;
    } else if ( !cash_transfer_sonar_code.equals( row.getCashTransferSonarCode() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_institution_name_kanji!=null? fin_institution_name_kanji.hashCode(): 0) 
        + (fin_institution_name_kana!=null? fin_institution_name_kana.hashCode(): 0) 
        + (cash_transfer_type!=null? cash_transfer_type.hashCode(): 0) 
        + (cash_transfer_sonar_code!=null? cash_transfer_sonar_code.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !fin_institution_name_kanji_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_institution_name_kanji' must be set before inserting.");
		if ( !fin_institution_name_kana_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_institution_name_kana' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("fin_institution_code",fin_institution_code);
		map.put("fin_institution_name_kanji",fin_institution_name_kanji);
		map.put("fin_institution_name_kana",fin_institution_name_kana);
		if ( cash_transfer_type != null )
			map.put("cash_transfer_type",cash_transfer_type);
		if ( cash_transfer_sonar_code != null )
			map.put("cash_transfer_sonar_code",cash_transfer_sonar_code);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( fin_institution_name_kanji_is_modified )
			map.put("fin_institution_name_kanji",fin_institution_name_kanji);
		if ( fin_institution_name_kana_is_modified )
			map.put("fin_institution_name_kana",fin_institution_name_kana);
		if ( cash_transfer_type_is_modified )
			map.put("cash_transfer_type",cash_transfer_type);
		if ( cash_transfer_sonar_code_is_modified )
			map.put("cash_transfer_sonar_code",cash_transfer_sonar_code);
		if (map.size() == 0) {
			if ( fin_institution_name_kanji_is_set )
				map.put("fin_institution_name_kanji",fin_institution_name_kanji);
			if ( fin_institution_name_kana_is_set )
				map.put("fin_institution_name_kana",fin_institution_name_kana);
			map.put("cash_transfer_type",cash_transfer_type);
			map.put("cash_transfer_sonar_code",cash_transfer_sonar_code);
		}
		return map;
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
   * <em>fin_institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinInstitutionCode()
  {
    return fin_institution_code;
  }


  /** 
   * <em>fin_institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionCodeIsSet() {
    return fin_institution_code_is_set;
  }


  /** 
   * <em>fin_institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionCodeIsModified() {
    return fin_institution_code_is_modified;
  }


  /** 
   * <em>fin_institution_name_kanji</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinInstitutionNameKanji()
  {
    return fin_institution_name_kanji;
  }


  /** 
   * <em>fin_institution_name_kanji</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameKanjiIsSet() {
    return fin_institution_name_kanji_is_set;
  }


  /** 
   * <em>fin_institution_name_kanji</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameKanjiIsModified() {
    return fin_institution_name_kanji_is_modified;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFinInstitutionNameKana()
  {
    return fin_institution_name_kana;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameKanaIsSet() {
    return fin_institution_name_kana_is_set;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFinInstitutionNameKanaIsModified() {
    return fin_institution_name_kana_is_modified;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCashTransferType()
  {
    return cash_transfer_type;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransferTypeIsSet() {
    return cash_transfer_type_is_set;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransferTypeIsModified() {
    return cash_transfer_type_is_modified;
  }


  /** 
   * <em>cash_transfer_sonar_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCashTransferSonarCode()
  {
    return cash_transfer_sonar_code;
  }


  /** 
   * <em>cash_transfer_sonar_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransferSonarCodeIsSet() {
    return cash_transfer_sonar_code_is_set;
  }


  /** 
   * <em>cash_transfer_sonar_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCashTransferSonarCodeIsModified() {
    return cash_transfer_sonar_code_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new FinInstitutionPK(institution_code, fin_institution_code);
  }


  /** 
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>fin_institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finInstitutionCode <em>fin_institution_code</em>�J�����̒l������킷15���ȉ���String�l 
   */
  public final void setFinInstitutionCode( String p_finInstitutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_code = p_finInstitutionCode;
    fin_institution_code_is_set = true;
    fin_institution_code_is_modified = true;
  }


  /** 
   * <em>fin_institution_name_kanji</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finInstitutionNameKanji <em>fin_institution_name_kanji</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setFinInstitutionNameKanji( String p_finInstitutionNameKanji )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_name_kanji = p_finInstitutionNameKanji;
    fin_institution_name_kanji_is_set = true;
    fin_institution_name_kanji_is_modified = true;
  }


  /** 
   * <em>fin_institution_name_kana</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_finInstitutionNameKana <em>fin_institution_name_kana</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setFinInstitutionNameKana( String p_finInstitutionNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_name_kana = p_finInstitutionNameKana;
    fin_institution_name_kana_is_set = true;
    fin_institution_name_kana_is_modified = true;
  }


  /** 
   * <em>cash_transfer_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashTransferType <em>cash_transfer_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCashTransferType( String p_cashTransferType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_transfer_type = p_cashTransferType;
    cash_transfer_type_is_set = true;
    cash_transfer_type_is_modified = true;
  }


  /** 
   * <em>cash_transfer_sonar_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_cashTransferSonarCode <em>cash_transfer_sonar_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCashTransferSonarCode( String p_cashTransferSonarCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_transfer_sonar_code = p_cashTransferSonarCode;
    cash_transfer_sonar_code_is_set = true;
    cash_transfer_sonar_code_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("cash_transfer_type") ) {
                    return this.cash_transfer_type;
                }
                else if ( name.equals("cash_transfer_sonar_code") ) {
                    return this.cash_transfer_sonar_code;
                }
                break;
            case 'f':
                if ( name.equals("fin_institution_code") ) {
                    return this.fin_institution_code;
                }
                else if ( name.equals("fin_institution_name_kanji") ) {
                    return this.fin_institution_name_kanji;
                }
                else if ( name.equals("fin_institution_name_kana") ) {
                    return this.fin_institution_name_kana;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
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
                if ( name.equals("cash_transfer_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_transfer_type' must be String: '"+value+"' is not." );
                    this.cash_transfer_type = (String) value;
                    if (this.cash_transfer_type_is_set)
                        this.cash_transfer_type_is_modified = true;
                    this.cash_transfer_type_is_set = true;
                    return;
                }
                else if ( name.equals("cash_transfer_sonar_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_transfer_sonar_code' must be String: '"+value+"' is not." );
                    this.cash_transfer_sonar_code = (String) value;
                    if (this.cash_transfer_sonar_code_is_set)
                        this.cash_transfer_sonar_code_is_modified = true;
                    this.cash_transfer_sonar_code_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fin_institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_code' must be String: '"+value+"' is not." );
                    this.fin_institution_code = (String) value;
                    if (this.fin_institution_code_is_set)
                        this.fin_institution_code_is_modified = true;
                    this.fin_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_name_kanji") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_name_kanji' must be String: '"+value+"' is not." );
                    this.fin_institution_name_kanji = (String) value;
                    if (this.fin_institution_name_kanji_is_set)
                        this.fin_institution_name_kanji_is_modified = true;
                    this.fin_institution_name_kanji_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_name_kana") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_name_kana' must be String: '"+value+"' is not." );
                    this.fin_institution_name_kana = (String) value;
                    if (this.fin_institution_name_kana_is_set)
                        this.fin_institution_name_kana_is_modified = true;
                    this.fin_institution_name_kana_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
