head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	AmountRangeParams.java;


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
 * amount_range�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AmountRangeRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AmountRangeParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AmountRangeParams}��{@@link AmountRangeRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AmountRangePK 
 * @@see AmountRangeRow 
 */
public class AmountRangeParams extends Params implements AmountRangeRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "amount_range";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AmountRangeRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AmountRangeRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>fund_type</em>�J�����̒l 
   */
  public  String  fund_type;

  /** 
   * <em>transaction_type</em>�J�����̒l 
   */
  public  String  transaction_type;

  /** 
   * <em>max_amount</em>�J�����̒l 
   */
  public  Long  max_amount;

  /** 
   * <em>min_amount</em>�J�����̒l 
   */
  public  Long  min_amount;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean fund_type_is_set = false;
  private boolean fund_type_is_modified = false;
  private boolean transaction_type_is_set = false;
  private boolean transaction_type_is_modified = false;
  private boolean max_amount_is_set = false;
  private boolean max_amount_is_modified = false;
  private boolean min_amount_is_set = false;
  private boolean min_amount_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "fund_type=" + fund_type
      + "," + "transaction_type=" + transaction_type
      + "," + "max_amount=" +max_amount
      + "," + "min_amount=" +min_amount
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��AmountRangeParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AmountRangeParams() {
    institution_code_is_modified = true;
    fund_type_is_modified = true;
    transaction_type_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AmountRangeRow�I�u�W�F�N�g�̒l�𗘗p����AmountRangeParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AmountRangeRow�I�u�W�F�N�g 
   */
  public AmountRangeParams( AmountRangeRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    fund_type = p_row.getFundType();
    fund_type_is_set = p_row.getFundTypeIsSet();
    fund_type_is_modified = p_row.getFundTypeIsModified();
    transaction_type = p_row.getTransactionType();
    transaction_type_is_set = p_row.getTransactionTypeIsSet();
    transaction_type_is_modified = p_row.getTransactionTypeIsModified();
    if ( !p_row.getMaxAmountIsNull() )
      max_amount = new Long( p_row.getMaxAmount() );
    max_amount_is_set = p_row.getMaxAmountIsSet();
    max_amount_is_modified = p_row.getMaxAmountIsModified();
    if ( !p_row.getMinAmountIsNull() )
      min_amount = new Long( p_row.getMinAmount() );
    min_amount_is_set = p_row.getMinAmountIsSet();
    min_amount_is_modified = p_row.getMinAmountIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      max_amount_is_set = true;
      max_amount_is_modified = true;
      min_amount_is_set = true;
      min_amount_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AmountRangeRow ) )
       return false;
    return fieldsEqual( (AmountRangeRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AmountRangeRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AmountRangeRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( fund_type == null ) {
      if ( row.getFundType() != null )
        return false;
    } else if ( !fund_type.equals( row.getFundType() ) ) {
        return false;
    }
    if ( transaction_type == null ) {
      if ( row.getTransactionType() != null )
        return false;
    } else if ( !transaction_type.equals( row.getTransactionType() ) ) {
        return false;
    }
    if ( max_amount == null ) {
      if ( !row.getMaxAmountIsNull() )
        return false;
    } else if ( row.getMaxAmountIsNull() || ( max_amount.longValue() != row.getMaxAmount() ) ) {
        return false;
    }
    if ( min_amount == null ) {
      if ( !row.getMinAmountIsNull() )
        return false;
    } else if ( row.getMinAmountIsNull() || ( min_amount.longValue() != row.getMinAmount() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
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
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
        + (max_amount!=null? max_amount.hashCode(): 0) 
        + (min_amount!=null? min_amount.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
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
		map.put("institution_code",institution_code);
		map.put("fund_type",fund_type);
		map.put("transaction_type",transaction_type);
		if ( max_amount != null )
			map.put("max_amount",max_amount);
		if ( min_amount != null )
			map.put("min_amount",min_amount);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( max_amount_is_modified )
			map.put("max_amount",max_amount);
		if ( min_amount_is_modified )
			map.put("min_amount",min_amount);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("max_amount",max_amount);
			map.put("min_amount",min_amount);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>fund_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFundType()
  {
    return fund_type;
  }


  /** 
   * <em>fund_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundTypeIsSet() {
    return fund_type_is_set;
  }


  /** 
   * <em>fund_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundTypeIsModified() {
    return fund_type_is_modified;
  }


  /** 
   * <em>transaction_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTransactionType()
  {
    return transaction_type;
  }


  /** 
   * <em>transaction_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionTypeIsSet() {
    return transaction_type_is_set;
  }


  /** 
   * <em>transaction_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionTypeIsModified() {
    return transaction_type_is_modified;
  }


  /** 
   * <em>max_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMaxAmount()
  {
    return ( max_amount==null? ((long)0): max_amount.longValue() );
  }


  /** 
   * <em>max_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMaxAmountIsNull()
  {
    return max_amount == null;
  }


  /** 
   * <em>max_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMaxAmountIsSet() {
    return max_amount_is_set;
  }


  /** 
   * <em>max_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMaxAmountIsModified() {
    return max_amount_is_modified;
  }


  /** 
   * <em>min_amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMinAmount()
  {
    return ( min_amount==null? ((long)0): min_amount.longValue() );
  }


  /** 
   * <em>min_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getMinAmountIsNull()
  {
    return min_amount == null;
  }


  /** 
   * <em>min_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMinAmountIsSet() {
    return min_amount_is_set;
  }


  /** 
   * <em>min_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMinAmountIsModified() {
    return min_amount_is_modified;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AmountRangePK(institution_code, fund_type, transaction_type);
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
   * <em>fund_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fundType <em>fund_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setFundType( String p_fundType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_type = p_fundType;
    fund_type_is_set = true;
    fund_type_is_modified = true;
  }


  /** 
   * <em>transaction_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transactionType <em>transaction_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setTransactionType( String p_transactionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_type = p_transactionType;
    transaction_type_is_set = true;
    transaction_type_is_modified = true;
  }


  /** 
   * <em>max_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_maxAmount <em>max_amount</em>�J�����̒l������킷11���ȉ���long�l 
   */
  public final void setMaxAmount( long p_maxAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount = new Long(p_maxAmount);
    max_amount_is_set = true;
    max_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>max_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMaxAmount( Long p_maxAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount = p_maxAmount;
    max_amount_is_set = true;
    max_amount_is_modified = true;
  }


  /** 
   * <em>min_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_minAmount <em>min_amount</em>�J�����̒l������킷11���ȉ���long�l 
   */
  public final void setMinAmount( long p_minAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_amount = new Long(p_minAmount);
    min_amount_is_set = true;
    min_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>min_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setMinAmount( Long p_minAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    min_amount = p_minAmount;
    min_amount_is_set = true;
    min_amount_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    return this.fund_type;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("max_amount") ) {
                    return this.max_amount;
                }
                else if ( name.equals("min_amount") ) {
                    return this.min_amount;
                }
                break;
            case 't':
                if ( name.equals("transaction_type") ) {
                    return this.transaction_type;
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
                if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_type' must be String: '"+value+"' is not." );
                    this.fund_type = (String) value;
                    if (this.fund_type_is_set)
                        this.fund_type_is_modified = true;
                    this.fund_type_is_set = true;
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
                if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("max_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_amount' must be Long: '"+value+"' is not." );
                    this.max_amount = (Long) value;
                    if (this.max_amount_is_set)
                        this.max_amount_is_modified = true;
                    this.max_amount_is_set = true;
                    return;
                }
                else if ( name.equals("min_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'min_amount' must be Long: '"+value+"' is not." );
                    this.min_amount = (Long) value;
                    if (this.min_amount_is_set)
                        this.min_amount_is_modified = true;
                    this.min_amount_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transaction_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transaction_type' must be String: '"+value+"' is not." );
                    this.transaction_type = (String) value;
                    if (this.transaction_type_is_set)
                        this.transaction_type_is_modified = true;
                    this.transaction_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
