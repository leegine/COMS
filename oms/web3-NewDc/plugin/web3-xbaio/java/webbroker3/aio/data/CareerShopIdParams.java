head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.38.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CareerShopIdParams.java;


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
 * career_shop_id�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link CareerShopIdRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link CareerShopIdParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link CareerShopIdParams}��{@@link CareerShopIdRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CareerShopIdPK 
 * @@see CareerShopIdRow 
 */
public class CareerShopIdParams extends Params implements CareerShopIdRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "career_shop_id";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = CareerShopIdRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return CareerShopIdRow.TYPE;
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
   * <em>career_div</em>�J�����̒l 
   */
  public  String  career_div;

  /** 
   * <em>shop_id</em>�J�����̒l 
   */
  public  String  shop_id;

  /** 
   * <em>pf_url</em>�J�����̒l 
   */
  public  String  pf_url;

  /** 
   * <em>return_url</em>�J�����̒l 
   */
  public  String  return_url;

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
  private boolean career_div_is_set = false;
  private boolean career_div_is_modified = false;
  private boolean shop_id_is_set = false;
  private boolean shop_id_is_modified = false;
  private boolean pf_url_is_set = false;
  private boolean pf_url_is_modified = false;
  private boolean return_url_is_set = false;
  private boolean return_url_is_modified = false;
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
      + "," + "career_div=" + career_div
      + "," + "shop_id=" +shop_id
      + "," + "pf_url=" +pf_url
      + "," + "return_url=" +return_url
      + "," + "last_update_timestamp=" +last_update_timestamp
      + "," + "last_update_user=" +last_update_user
      + "}";
  }


  /** 
   * �l�����ݒ��CareerShopIdParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public CareerShopIdParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    career_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���CareerShopIdRow�I�u�W�F�N�g�̒l�𗘗p����CareerShopIdParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����CareerShopIdRow�I�u�W�F�N�g 
   */
  public CareerShopIdParams( CareerShopIdRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    career_div = p_row.getCareerDiv();
    career_div_is_set = p_row.getCareerDivIsSet();
    career_div_is_modified = p_row.getCareerDivIsModified();
    shop_id = p_row.getShopId();
    shop_id_is_set = p_row.getShopIdIsSet();
    shop_id_is_modified = p_row.getShopIdIsModified();
    pf_url = p_row.getPfUrl();
    pf_url_is_set = p_row.getPfUrlIsSet();
    pf_url_is_modified = p_row.getPfUrlIsModified();
    return_url = p_row.getReturnUrl();
    return_url_is_set = p_row.getReturnUrlIsSet();
    return_url_is_modified = p_row.getReturnUrlIsModified();
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
      shop_id_is_set = true;
      shop_id_is_modified = true;
      pf_url_is_set = true;
      pf_url_is_modified = true;
      return_url_is_set = true;
      return_url_is_modified = true;
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
    if ( !( other instanceof CareerShopIdRow ) )
       return false;
    return fieldsEqual( (CareerShopIdRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�CareerShopIdRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( CareerShopIdRow row )
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
    if ( career_div == null ) {
      if ( row.getCareerDiv() != null )
        return false;
    } else if ( !career_div.equals( row.getCareerDiv() ) ) {
        return false;
    }
    if ( shop_id == null ) {
      if ( row.getShopId() != null )
        return false;
    } else if ( !shop_id.equals( row.getShopId() ) ) {
        return false;
    }
    if ( pf_url == null ) {
      if ( row.getPfUrl() != null )
        return false;
    } else if ( !pf_url.equals( row.getPfUrl() ) ) {
        return false;
    }
    if ( return_url == null ) {
      if ( row.getReturnUrl() != null )
        return false;
    } else if ( !return_url.equals( row.getReturnUrl() ) ) {
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
        + (career_div!=null? career_div.hashCode(): 0) 
        + (shop_id!=null? shop_id.hashCode(): 0) 
        + (pf_url!=null? pf_url.hashCode(): 0) 
        + (return_url!=null? return_url.hashCode(): 0) 
        + (last_update_timestamp!=null? last_update_timestamp.hashCode(): 0) 
        + (last_update_user!=null? last_update_user.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !shop_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'shop_id' must be set before inserting.");
		if ( !pf_url_is_set )
			throw new IllegalArgumentException("non-nullable field 'pf_url' must be set before inserting.");
		if ( !return_url_is_set )
			throw new IllegalArgumentException("non-nullable field 'return_url' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("career_div",career_div);
		map.put("shop_id",shop_id);
		map.put("pf_url",pf_url);
		map.put("return_url",return_url);
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
		if ( shop_id_is_modified )
			map.put("shop_id",shop_id);
		if ( pf_url_is_modified )
			map.put("pf_url",pf_url);
		if ( return_url_is_modified )
			map.put("return_url",return_url);
		if ( last_update_timestamp_is_modified )
			map.put("last_update_timestamp",last_update_timestamp);
		if ( last_update_user_is_modified )
			map.put("last_update_user",last_update_user);
		if (map.size() == 0) {
			if ( shop_id_is_set )
				map.put("shop_id",shop_id);
			if ( pf_url_is_set )
				map.put("pf_url",pf_url);
			if ( return_url_is_set )
				map.put("return_url",return_url);
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
   * <em>shop_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getShopId()
  {
    return shop_id;
  }


  /** 
   * <em>shop_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getShopIdIsSet() {
    return shop_id_is_set;
  }


  /** 
   * <em>shop_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getShopIdIsModified() {
    return shop_id_is_modified;
  }


  /** 
   * <em>pf_url</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPfUrl()
  {
    return pf_url;
  }


  /** 
   * <em>pf_url</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPfUrlIsSet() {
    return pf_url_is_set;
  }


  /** 
   * <em>pf_url</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPfUrlIsModified() {
    return pf_url_is_modified;
  }


  /** 
   * <em>return_url</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getReturnUrl()
  {
    return return_url;
  }


  /** 
   * <em>return_url</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getReturnUrlIsSet() {
    return return_url_is_set;
  }


  /** 
   * <em>return_url</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getReturnUrlIsModified() {
    return return_url_is_modified;
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
    return new CareerShopIdPK(institution_code, branch_code, career_div);
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
   * <em>shop_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_shopId <em>shop_id</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setShopId( String p_shopId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    shop_id = p_shopId;
    shop_id_is_set = true;
    shop_id_is_modified = true;
  }


  /** 
   * <em>pf_url</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_pfUrl <em>pf_url</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setPfUrl( String p_pfUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pf_url = p_pfUrl;
    pf_url_is_set = true;
    pf_url_is_modified = true;
  }


  /** 
   * <em>return_url</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_returnUrl <em>return_url</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setReturnUrl( String p_returnUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    return_url = p_returnUrl;
    return_url_is_set = true;
    return_url_is_modified = true;
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
            case 'p':
                if ( name.equals("pf_url") ) {
                    return this.pf_url;
                }
                break;
            case 'r':
                if ( name.equals("return_url") ) {
                    return this.return_url;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    return this.shop_id;
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
            case 'p':
                if ( name.equals("pf_url") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pf_url' must be String: '"+value+"' is not." );
                    this.pf_url = (String) value;
                    if (this.pf_url_is_set)
                        this.pf_url_is_modified = true;
                    this.pf_url_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("return_url") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'return_url' must be String: '"+value+"' is not." );
                    this.return_url = (String) value;
                    if (this.return_url_is_set)
                        this.return_url_is_modified = true;
                    this.return_url_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'shop_id' must be String: '"+value+"' is not." );
                    this.shop_id = (String) value;
                    if (this.shop_id_is_set)
                        this.shop_id_is_modified = true;
                    this.shop_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
