head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CompBankCareerManagementParams.java;


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
 * comp_bank_career_management�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link CompBankCareerManagementRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link CompBankCareerManagementParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link CompBankCareerManagementParams}��{@@link CompBankCareerManagementRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CompBankCareerManagementPK 
 * @@see CompBankCareerManagementRow 
 */
public class CompBankCareerManagementParams extends Params implements CompBankCareerManagementRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "comp_bank_career_management";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = CompBankCareerManagementRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return CompBankCareerManagementRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�J�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>pay_scheme_id</em>�J�����̒l 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>career_div</em>�J�����̒l 
   */
  public  String  career_div;

  /** 
   * <em>mg_flg</em>�J�����̒l 
   */
  public  String  mg_flg;

  /** 
   * <em>last_update_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_update_timestamp;

  /** 
   * <em>last_update_user</em>�J�����̒l 
   */
  public  String  last_update_user;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean career_div_is_set = false;
  private boolean career_div_is_modified = false;
  private boolean mg_flg_is_set = false;
  private boolean mg_flg_is_modified = false;
  private boolean last_update_timestamp_is_set = false;
  private boolean last_update_timestamp_is_modified = false;
  private boolean last_update_user_is_set = false;
  private boolean last_update_user_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "pay_scheme_id=" + pay_scheme_id
      + "," + "career_div=" + career_div
      + "," + "mg_flg=" +mg_flg
      + "," + "last_update_timestamp=" +last_update_timestamp
      + "," + "last_update_user=" +last_update_user
      + "}";
  }


  /** 
   * �l�����ݒ��CompBankCareerManagementParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public CompBankCareerManagementParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    pay_scheme_id_is_modified = true;
    career_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���CompBankCareerManagementRow�I�u�W�F�N�g�̒l�𗘗p����CompBankCareerManagementParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����CompBankCareerManagementRow�I�u�W�F�N�g 
   */
  public CompBankCareerManagementParams( CompBankCareerManagementRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    career_div = p_row.getCareerDiv();
    career_div_is_set = p_row.getCareerDivIsSet();
    career_div_is_modified = p_row.getCareerDivIsModified();
    mg_flg = p_row.getMgFlg();
    mg_flg_is_set = p_row.getMgFlgIsSet();
    mg_flg_is_modified = p_row.getMgFlgIsModified();
    last_update_timestamp = p_row.getLastUpdateTimestamp();
    last_update_timestamp_is_set = p_row.getLastUpdateTimestampIsSet();
    last_update_timestamp_is_modified = p_row.getLastUpdateTimestampIsModified();
    last_update_user = p_row.getLastUpdateUser();
    last_update_user_is_set = p_row.getLastUpdateUserIsSet();
    last_update_user_is_modified = p_row.getLastUpdateUserIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      mg_flg_is_set = true;
      mg_flg_is_modified = true;
      last_update_timestamp_is_set = true;
      last_update_timestamp_is_modified = true;
      last_update_user_is_set = true;
      last_update_user_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CompBankCareerManagementRow ) )
       return false;
    return fieldsEqual( (CompBankCareerManagementRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�CompBankCareerManagementRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( CompBankCareerManagementRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( career_div == null ) {
      if ( row.getCareerDiv() != null )
        return false;
    } else if ( !career_div.equals( row.getCareerDiv() ) ) {
        return false;
    }
    if ( mg_flg == null ) {
      if ( row.getMgFlg() != null )
        return false;
    } else if ( !mg_flg.equals( row.getMgFlg() ) ) {
        return false;
    }
    if ( last_update_timestamp == null ) {
      if ( row.getLastUpdateTimestamp() != null )
        return false;
    } else if ( !last_update_timestamp.equals( row.getLastUpdateTimestamp() ) ) {
        return false;
    }
    if ( last_update_user == null ) {
      if ( row.getLastUpdateUser() != null )
        return false;
    } else if ( !last_update_user.equals( row.getLastUpdateUser() ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (career_div!=null? career_div.hashCode(): 0) 
        + (mg_flg!=null? mg_flg.hashCode(): 0) 
        + (last_update_timestamp!=null? last_update_timestamp.hashCode(): 0) 
        + (last_update_user!=null? last_update_user.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !mg_flg_is_set )
			throw new IllegalArgumentException("non-nullable field 'mg_flg' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("pay_scheme_id",pay_scheme_id);
		map.put("career_div",career_div);
		map.put("mg_flg",mg_flg);
		if ( last_update_timestamp != null )
			map.put("last_update_timestamp",last_update_timestamp);
		if ( last_update_user != null )
			map.put("last_update_user",last_update_user);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( mg_flg_is_modified )
			map.put("mg_flg",mg_flg);
		if ( last_update_timestamp_is_modified )
			map.put("last_update_timestamp",last_update_timestamp);
		if ( last_update_user_is_modified )
			map.put("last_update_user",last_update_user);
		if (map.size() == 0) {
			if ( mg_flg_is_set )
				map.put("mg_flg",mg_flg);
			map.put("last_update_timestamp",last_update_timestamp);
			map.put("last_update_user",last_update_user);
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
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
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
   * <em>career_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCareerDiv()
  {
    return career_div;
  }


  /** 
   * <em>career_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCareerDivIsSet() {
    return career_div_is_set;
  }


  /** 
   * <em>career_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCareerDivIsModified() {
    return career_div_is_modified;
  }


  /** 
   * <em>mg_flg</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMgFlg()
  {
    return mg_flg;
  }


  /** 
   * <em>mg_flg</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMgFlgIsSet() {
    return mg_flg_is_set;
  }


  /** 
   * <em>mg_flg</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMgFlgIsModified() {
    return mg_flg_is_modified;
  }


  /** 
   * <em>last_update_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdateTimestamp()
  {
    return last_update_timestamp;
  }


  /** 
   * <em>last_update_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdateTimestampIsSet() {
    return last_update_timestamp_is_set;
  }


  /** 
   * <em>last_update_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdateTimestampIsModified() {
    return last_update_timestamp_is_modified;
  }


  /** 
   * <em>last_update_user</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLastUpdateUser()
  {
    return last_update_user;
  }


  /** 
   * <em>last_update_user</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdateUserIsSet() {
    return last_update_user_is_set;
  }


  /** 
   * <em>last_update_user</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdateUserIsModified() {
    return last_update_user_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CompBankCareerManagementPK(institution_code, branch_code, pay_scheme_id, career_div);
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
   * <em>branch_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
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
   * <em>career_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_careerDiv <em>career_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCareerDiv( String p_careerDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    career_div = p_careerDiv;
    career_div_is_set = true;
    career_div_is_modified = true;
  }


  /** 
   * <em>mg_flg</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mgFlg <em>mg_flg</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setMgFlg( String p_mgFlg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mg_flg = p_mgFlg;
    mg_flg_is_set = true;
    mg_flg_is_modified = true;
  }


  /** 
   * <em>last_update_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdateTimestamp <em>last_update_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdateTimestamp( java.sql.Timestamp p_lastUpdateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_timestamp = p_lastUpdateTimestamp;
    last_update_timestamp_is_set = true;
    last_update_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_update_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_update_timestamp_is_set = true;
    last_update_timestamp_is_modified = true;
  }


  /** 
   * <em>last_update_user</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdateUser <em>last_update_user</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setLastUpdateUser( String p_lastUpdateUser )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_user = p_lastUpdateUser;
    last_update_user_is_set = true;
    last_update_user_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("career_div") ) {
                    return this.career_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_update_timestamp") ) {
                    return this.last_update_timestamp;
                }
                else if ( name.equals("last_update_user") ) {
                    return this.last_update_user;
                }
                break;
            case 'm':
                if ( name.equals("mg_flg") ) {
                    return this.mg_flg;
                }
                break;
            case 'p':
                if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
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
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("career_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'career_div' must be String: '"+value+"' is not." );
                    this.career_div = (String) value;
                    if (this.career_div_is_set)
                        this.career_div_is_modified = true;
                    this.career_div_is_set = true;
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
                if ( name.equals("last_update_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_update_timestamp = (java.sql.Timestamp) value;
                    if (this.last_update_timestamp_is_set)
                        this.last_update_timestamp_is_modified = true;
                    this.last_update_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("last_update_user") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_update_user' must be String: '"+value+"' is not." );
                    this.last_update_user = (String) value;
                    if (this.last_update_user_is_set)
                        this.last_update_user_is_modified = true;
                    this.last_update_user_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("mg_flg") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mg_flg' must be String: '"+value+"' is not." );
                    this.mg_flg = (String) value;
                    if (this.mg_flg_is_set)
                        this.mg_flg_is_modified = true;
                    this.mg_flg_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
