head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEquityCarryoverSkipParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_equity_carryover_skip�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link HostEquityCarryoverSkipRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link HostEquityCarryoverSkipParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link HostEquityCarryoverSkipParams}��{@@link HostEquityCarryoverSkipRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostEquityCarryoverSkipPK 
 * @@see HostEquityCarryoverSkipRow 
 */
public class HostEquityCarryoverSkipParams extends Params implements HostEquityCarryoverSkipRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "host_equity_carryover_skip";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = HostEquityCarryoverSkipRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return HostEquityCarryoverSkipRow.TYPE;
  }


  /** 
   * <em>rowid</em>�J�����̒l 
   */
  public  String  rowid;

  /** 
   * <em>request_code</em>�J�����̒l 
   */
  public  String  request_code;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>skip_market_code</em>�J�����̒l 
   */
  public  String  skip_market_code;

  /** 
   * <em>skip_regist_type</em>�J�����̒l 
   */
  public  String  skip_regist_type;

  /** 
   * <em>input_datetime</em>�J�����̒l 
   */
  public  java.sql.Timestamp  input_datetime;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean skip_market_code_is_set = false;
  private boolean skip_market_code_is_modified = false;
  private boolean skip_regist_type_is_set = false;
  private boolean skip_regist_type_is_modified = false;
  private boolean input_datetime_is_set = false;
  private boolean input_datetime_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "skip_market_code=" +skip_market_code
      + "," + "skip_regist_type=" +skip_regist_type
      + "," + "input_datetime=" +input_datetime
      + "," + "status=" +status
      + "}";
  }


  /** 
   * �l�����ݒ��HostEquityCarryoverSkipParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public HostEquityCarryoverSkipParams() {
    rowid_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���HostEquityCarryoverSkipRow�I�u�W�F�N�g�̒l�𗘗p����HostEquityCarryoverSkipParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����HostEquityCarryoverSkipRow�I�u�W�F�N�g 
   */
  public HostEquityCarryoverSkipParams( HostEquityCarryoverSkipRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    skip_market_code = p_row.getSkipMarketCode();
    skip_market_code_is_set = p_row.getSkipMarketCodeIsSet();
    skip_market_code_is_modified = p_row.getSkipMarketCodeIsModified();
    skip_regist_type = p_row.getSkipRegistType();
    skip_regist_type_is_set = p_row.getSkipRegistTypeIsSet();
    skip_regist_type_is_modified = p_row.getSkipRegistTypeIsModified();
    input_datetime = p_row.getInputDatetime();
    input_datetime_is_set = p_row.getInputDatetimeIsSet();
    input_datetime_is_modified = p_row.getInputDatetimeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      skip_market_code_is_set = true;
      skip_market_code_is_modified = true;
      skip_regist_type_is_set = true;
      skip_regist_type_is_modified = true;
      input_datetime_is_set = true;
      input_datetime_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostEquityCarryoverSkipRow ) )
       return false;
    return fieldsEqual( (HostEquityCarryoverSkipRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�HostEquityCarryoverSkipRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( HostEquityCarryoverSkipRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( skip_market_code == null ) {
      if ( row.getSkipMarketCode() != null )
        return false;
    } else if ( !skip_market_code.equals( row.getSkipMarketCode() ) ) {
        return false;
    }
    if ( skip_regist_type == null ) {
      if ( row.getSkipRegistType() != null )
        return false;
    } else if ( !skip_regist_type.equals( row.getSkipRegistType() ) ) {
        return false;
    }
    if ( input_datetime == null ) {
      if ( row.getInputDatetime() != null )
        return false;
    } else if ( !input_datetime.equals( row.getInputDatetime() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (skip_market_code!=null? skip_market_code.hashCode(): 0) 
        + (skip_regist_type!=null? skip_regist_type.hashCode(): 0) 
        + (input_datetime!=null? input_datetime.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
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
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( skip_market_code != null )
			map.put("skip_market_code",skip_market_code);
		if ( skip_regist_type != null )
			map.put("skip_regist_type",skip_regist_type);
		if ( input_datetime != null )
			map.put("input_datetime",input_datetime);
		if ( status != null )
			map.put("status",status);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( skip_market_code_is_modified )
			map.put("skip_market_code",skip_market_code);
		if ( skip_regist_type_is_modified )
			map.put("skip_regist_type",skip_regist_type);
		if ( input_datetime_is_modified )
			map.put("input_datetime",input_datetime);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			map.put("request_code",request_code);
			map.put("institution_code",institution_code);
			map.put("product_code",product_code);
			map.put("skip_market_code",skip_market_code);
			map.put("skip_regist_type",skip_regist_type);
			map.put("input_datetime",input_datetime);
			map.put("status",status);
		}
		return map;
	}


  /** 
   * <em>request_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
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
   * <em>product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>skip_market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSkipMarketCode()
  {
    return skip_market_code;
  }


  /** 
   * <em>skip_market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSkipMarketCodeIsSet() {
    return skip_market_code_is_set;
  }


  /** 
   * <em>skip_market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSkipMarketCodeIsModified() {
    return skip_market_code_is_modified;
  }


  /** 
   * <em>skip_regist_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSkipRegistType()
  {
    return skip_regist_type;
  }


  /** 
   * <em>skip_regist_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSkipRegistTypeIsSet() {
    return skip_regist_type_is_set;
  }


  /** 
   * <em>skip_regist_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSkipRegistTypeIsModified() {
    return skip_regist_type_is_modified;
  }


  /** 
   * <em>input_datetime</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getInputDatetime()
  {
    return input_datetime;
  }


  /** 
   * <em>input_datetime</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputDatetimeIsSet() {
    return input_datetime_is_set;
  }


  /** 
   * <em>input_datetime</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInputDatetimeIsModified() {
    return input_datetime_is_modified;
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
   * <em>rowid</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostEquityCarryoverSkipPK(rowid);
  }


  /** 
   * <em>request_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_requestCode <em>request_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
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
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>skip_market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_skipMarketCode <em>skip_market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setSkipMarketCode( String p_skipMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    skip_market_code = p_skipMarketCode;
    skip_market_code_is_set = true;
    skip_market_code_is_modified = true;
  }


  /** 
   * <em>skip_regist_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_skipRegistType <em>skip_regist_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSkipRegistType( String p_skipRegistType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    skip_regist_type = p_skipRegistType;
    skip_regist_type_is_set = true;
    skip_regist_type_is_modified = true;
  }


  /** 
   * <em>input_datetime</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_inputDatetime <em>input_datetime</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setInputDatetime( java.sql.Timestamp p_inputDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_datetime = p_inputDatetime;
    input_datetime_is_set = true;
    input_datetime_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>input_datetime</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setInputDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    input_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    input_datetime_is_set = true;
    input_datetime_is_modified = true;
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
   * <em>rowid</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rowid <em>rowid</em>�J�����̒l������킷20���ȉ���String�l�ŁA���̐��x��20���܂�
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("input_datetime") ) {
                    return this.input_datetime;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("skip_market_code") ) {
                    return this.skip_market_code;
                }
                else if ( name.equals("skip_regist_type") ) {
                    return this.skip_regist_type;
                }
                else if ( name.equals("status") ) {
                    return this.status;
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
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("input_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'input_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.input_datetime = (java.sql.Timestamp) value;
                    if (this.input_datetime_is_set)
                        this.input_datetime_is_modified = true;
                    this.input_datetime_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("skip_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'skip_market_code' must be String: '"+value+"' is not." );
                    this.skip_market_code = (String) value;
                    if (this.skip_market_code_is_set)
                        this.skip_market_code_is_modified = true;
                    this.skip_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("skip_regist_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'skip_regist_type' must be String: '"+value+"' is not." );
                    this.skip_regist_type = (String) value;
                    if (this.skip_regist_type_is_set)
                        this.skip_regist_type_is_modified = true;
                    this.skip_regist_type_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
