head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommRevMstParams.java;


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
 * equity_comm_rev_mst�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link EquityCommRevMstRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link EquityCommRevMstParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link EquityCommRevMstParams}��{@@link EquityCommRevMstRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EquityCommRevMstPK 
 * @@see EquityCommRevMstRow 
 */
public class EquityCommRevMstParams extends Params implements EquityCommRevMstRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "equity_comm_rev_mst";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = EquityCommRevMstRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return EquityCommRevMstRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>comm_product_code</em>�J�����̒l 
   */
  public  String  comm_product_code;

  /** 
   * <em>appli_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>order_channel</em>�J�����̒l 
   */
  public  String  order_channel;

  /** 
   * <em>transaction_type</em>�J�����̒l 
   */
  public  String  transaction_type;

  /** 
   * <em>exec_cond_type</em>�J�����̒l 
   */
  public  String  exec_cond_type;

  /** 
   * <em>pay_type</em>�J�����̒l 
   */
  public  String  pay_type;

  /** 
   * <em>sonar_market_code</em>�J�����̒l 
   */
  public  String  sonar_market_code;

  /** 
   * <em>underlying_product_code</em>�J�����̒l 
   */
  public  String  underlying_product_code;

  /** 
   * <em>day_trade_type</em>�J�����̒l 
   */
  public  String  day_trade_type;

  /** 
   * <em>appli_end_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>revision</em>�J�����̒l 
   */
  public  String  revision;

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
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean order_channel_is_set = false;
  private boolean order_channel_is_modified = false;
  private boolean transaction_type_is_set = false;
  private boolean transaction_type_is_modified = false;
  private boolean exec_cond_type_is_set = false;
  private boolean exec_cond_type_is_modified = false;
  private boolean revision_is_set = false;
  private boolean revision_is_modified = false;
  private boolean pay_type_is_set = false;
  private boolean pay_type_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean underlying_product_code_is_set = false;
  private boolean underlying_product_code_is_modified = false;
  private boolean day_trade_type_is_set = false;
  private boolean day_trade_type_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "comm_product_code=" + comm_product_code
      + "," + "appli_start_date=" + appli_start_date
      + "," + "order_channel=" + order_channel
      + "," + "transaction_type=" + transaction_type
      + "," + "exec_cond_type=" + exec_cond_type
      + "," + "pay_type=" + pay_type
      + "," + "sonar_market_code=" + sonar_market_code
      + "," + "underlying_product_code=" + underlying_product_code
      + "," + "day_trade_type=" + day_trade_type
      + "," + "appli_end_date=" +appli_end_date
      + "," + "revision=" +revision
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��EquityCommRevMstParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public EquityCommRevMstParams() {
    institution_code_is_modified = true;
    comm_product_code_is_modified = true;
    appli_start_date_is_modified = true;
    order_channel_is_modified = true;
    transaction_type_is_modified = true;
    exec_cond_type_is_modified = true;
    pay_type_is_modified = true;
    sonar_market_code_is_modified = true;
    underlying_product_code_is_modified = true;
    day_trade_type_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���EquityCommRevMstRow�I�u�W�F�N�g�̒l�𗘗p����EquityCommRevMstParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����EquityCommRevMstRow�I�u�W�F�N�g 
   */
  public EquityCommRevMstParams( EquityCommRevMstRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    order_channel = p_row.getOrderChannel();
    order_channel_is_set = p_row.getOrderChannelIsSet();
    order_channel_is_modified = p_row.getOrderChannelIsModified();
    transaction_type = p_row.getTransactionType();
    transaction_type_is_set = p_row.getTransactionTypeIsSet();
    transaction_type_is_modified = p_row.getTransactionTypeIsModified();
    exec_cond_type = p_row.getExecCondType();
    exec_cond_type_is_set = p_row.getExecCondTypeIsSet();
    exec_cond_type_is_modified = p_row.getExecCondTypeIsModified();
    pay_type = p_row.getPayType();
    pay_type_is_set = p_row.getPayTypeIsSet();
    pay_type_is_modified = p_row.getPayTypeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    underlying_product_code = p_row.getUnderlyingProductCode();
    underlying_product_code_is_set = p_row.getUnderlyingProductCodeIsSet();
    underlying_product_code_is_modified = p_row.getUnderlyingProductCodeIsModified();
    day_trade_type = p_row.getDayTradeType();
    day_trade_type_is_set = p_row.getDayTradeTypeIsSet();
    day_trade_type_is_modified = p_row.getDayTradeTypeIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    revision = p_row.getRevision();
    revision_is_set = p_row.getRevisionIsSet();
    revision_is_modified = p_row.getRevisionIsModified();
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
      appli_end_date_is_set = true;
      appli_end_date_is_modified = true;
      revision_is_set = true;
      revision_is_modified = true;
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
    if ( !( other instanceof EquityCommRevMstRow ) )
       return false;
    return fieldsEqual( (EquityCommRevMstRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�EquityCommRevMstRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( EquityCommRevMstRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( row.getCommProductCode() != null )
        return false;
    } else if ( !comm_product_code.equals( row.getCommProductCode() ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( row.getAppliStartDate() != null )
        return false;
    } else if ( !appli_start_date.equals( row.getAppliStartDate() ) ) {
        return false;
    }
    if ( appli_end_date == null ) {
      if ( row.getAppliEndDate() != null )
        return false;
    } else if ( !appli_end_date.equals( row.getAppliEndDate() ) ) {
        return false;
    }
    if ( order_channel == null ) {
      if ( row.getOrderChannel() != null )
        return false;
    } else if ( !order_channel.equals( row.getOrderChannel() ) ) {
        return false;
    }
    if ( transaction_type == null ) {
      if ( row.getTransactionType() != null )
        return false;
    } else if ( !transaction_type.equals( row.getTransactionType() ) ) {
        return false;
    }
    if ( exec_cond_type == null ) {
      if ( row.getExecCondType() != null )
        return false;
    } else if ( !exec_cond_type.equals( row.getExecCondType() ) ) {
        return false;
    }
    if ( revision == null ) {
      if ( row.getRevision() != null )
        return false;
    } else if ( !revision.equals( row.getRevision() ) ) {
        return false;
    }
    if ( pay_type == null ) {
      if ( row.getPayType() != null )
        return false;
    } else if ( !pay_type.equals( row.getPayType() ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
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
    if ( underlying_product_code == null ) {
      if ( row.getUnderlyingProductCode() != null )
        return false;
    } else if ( !underlying_product_code.equals( row.getUnderlyingProductCode() ) ) {
        return false;
    }
    if ( day_trade_type == null ) {
      if ( row.getDayTradeType() != null )
        return false;
    } else if ( !day_trade_type.equals( row.getDayTradeType() ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + (order_channel!=null? order_channel.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
        + (exec_cond_type!=null? exec_cond_type.hashCode(): 0) 
        + (revision!=null? revision.hashCode(): 0) 
        + (pay_type!=null? pay_type.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (day_trade_type!=null? day_trade_type.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !appli_end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_date' must be set before inserting.");
		if ( !revision_is_set )
			throw new IllegalArgumentException("non-nullable field 'revision' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("comm_product_code",comm_product_code);
		map.put("appli_start_date",appli_start_date);
		map.put("appli_end_date",appli_end_date);
		map.put("order_channel",order_channel);
		map.put("transaction_type",transaction_type);
		map.put("exec_cond_type",exec_cond_type);
		map.put("revision",revision);
		map.put("pay_type",pay_type);
		map.put("sonar_market_code",sonar_market_code);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("underlying_product_code",underlying_product_code);
		map.put("day_trade_type",day_trade_type);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( revision_is_modified )
			map.put("revision",revision);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( appli_end_date_is_set )
				map.put("appli_end_date",appli_end_date);
			if ( revision_is_set )
				map.put("revision",revision);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
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
   * <em>comm_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCommProductCode()
  {
    return comm_product_code;
  }


  /** 
   * <em>comm_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommProductCodeIsSet() {
    return comm_product_code_is_set;
  }


  /** 
   * <em>comm_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommProductCodeIsModified() {
    return comm_product_code_is_modified;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliStartDate()
  {
    return appli_start_date;
  }


  /** 
   * <em>appli_start_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateIsSet() {
    return appli_start_date_is_set;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliStartDateIsModified() {
    return appli_start_date_is_modified;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliEndDate()
  {
    return appli_end_date;
  }


  /** 
   * <em>appli_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndDateIsSet() {
    return appli_end_date_is_set;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliEndDateIsModified() {
    return appli_end_date_is_modified;
  }


  /** 
   * <em>order_channel</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderChannel()
  {
    return order_channel;
  }


  /** 
   * <em>order_channel</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderChannelIsSet() {
    return order_channel_is_set;
  }


  /** 
   * <em>order_channel</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderChannelIsModified() {
    return order_channel_is_modified;
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
   * <em>exec_cond_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getExecCondType()
  {
    return exec_cond_type;
  }


  /** 
   * <em>exec_cond_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecCondTypeIsSet() {
    return exec_cond_type_is_set;
  }


  /** 
   * <em>exec_cond_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecCondTypeIsModified() {
    return exec_cond_type_is_modified;
  }


  /** 
   * <em>revision</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRevision()
  {
    return revision;
  }


  /** 
   * <em>revision</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRevisionIsSet() {
    return revision_is_set;
  }


  /** 
   * <em>revision</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRevisionIsModified() {
    return revision_is_modified;
  }


  /** 
   * <em>pay_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPayType()
  {
    return pay_type;
  }


  /** 
   * <em>pay_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPayTypeIsSet() {
    return pay_type_is_set;
  }


  /** 
   * <em>pay_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPayTypeIsModified() {
    return pay_type_is_modified;
  }


  /** 
   * <em>sonar_market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSonarMarketCode()
  {
    return sonar_market_code;
  }


  /** 
   * <em>sonar_market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarMarketCodeIsSet() {
    return sonar_market_code_is_set;
  }


  /** 
   * <em>sonar_market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSonarMarketCodeIsModified() {
    return sonar_market_code_is_modified;
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
   * <em>underlying_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getUnderlyingProductCode()
  {
    return underlying_product_code;
  }


  /** 
   * <em>underlying_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnderlyingProductCodeIsSet() {
    return underlying_product_code_is_set;
  }


  /** 
   * <em>underlying_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnderlyingProductCodeIsModified() {
    return underlying_product_code_is_modified;
  }


  /** 
   * <em>day_trade_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDayTradeType()
  {
    return day_trade_type;
  }


  /** 
   * <em>day_trade_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDayTradeTypeIsSet() {
    return day_trade_type_is_set;
  }


  /** 
   * <em>day_trade_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDayTradeTypeIsModified() {
    return day_trade_type_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EquityCommRevMstPK(institution_code, comm_product_code, appli_start_date, order_channel, transaction_type, exec_cond_type, pay_type, sonar_market_code, underlying_product_code, day_trade_type);
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
   * <em>comm_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commProductCode <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCommProductCode( String p_commProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_product_code = p_commProductCode;
    comm_product_code_is_set = true;
    comm_product_code_is_modified = true;
  }


  /** 
   * <em>appli_start_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliStartDate <em>appli_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliStartDate( java.sql.Timestamp p_appliStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = p_appliStartDate;
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_start_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


  /** 
   * <em>appli_end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliEndDate <em>appli_end_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliEndDate( java.sql.Timestamp p_appliEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = p_appliEndDate;
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_end_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


  /** 
   * <em>order_channel</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderChannel <em>order_channel</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderChannel( String p_orderChannel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_channel = p_orderChannel;
    order_channel_is_set = true;
    order_channel_is_modified = true;
  }


  /** 
   * <em>transaction_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transactionType <em>transaction_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setTransactionType( String p_transactionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_type = p_transactionType;
    transaction_type_is_set = true;
    transaction_type_is_modified = true;
  }


  /** 
   * <em>exec_cond_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execCondType <em>exec_cond_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setExecCondType( String p_execCondType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_cond_type = p_execCondType;
    exec_cond_type_is_set = true;
    exec_cond_type_is_modified = true;
  }


  /** 
   * <em>revision</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_revision <em>revision</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRevision( String p_revision )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    revision = p_revision;
    revision_is_set = true;
    revision_is_modified = true;
  }


  /** 
   * <em>pay_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_payType <em>pay_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setPayType( String p_payType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_type = p_payType;
    pay_type_is_set = true;
    pay_type_is_modified = true;
  }


  /** 
   * <em>sonar_market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sonarMarketCode <em>sonar_market_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSonarMarketCode( String p_sonarMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_market_code = p_sonarMarketCode;
    sonar_market_code_is_set = true;
    sonar_market_code_is_modified = true;
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
   * <em>underlying_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_underlyingProductCode <em>underlying_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setUnderlyingProductCode( String p_underlyingProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    underlying_product_code = p_underlyingProductCode;
    underlying_product_code_is_set = true;
    underlying_product_code_is_modified = true;
  }


  /** 
   * <em>day_trade_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dayTradeType <em>day_trade_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setDayTradeType( String p_dayTradeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_type = p_dayTradeType;
    day_trade_type_is_set = true;
    day_trade_type_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("appli_start_date") ) {
                    return this.appli_start_date;
                }
                else if ( name.equals("appli_end_date") ) {
                    return this.appli_end_date;
                }
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("day_trade_type") ) {
                    return this.day_trade_type;
                }
                break;
            case 'e':
                if ( name.equals("exec_cond_type") ) {
                    return this.exec_cond_type;
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
            case 'o':
                if ( name.equals("order_channel") ) {
                    return this.order_channel;
                }
                break;
            case 'p':
                if ( name.equals("pay_type") ) {
                    return this.pay_type;
                }
                break;
            case 'r':
                if ( name.equals("revision") ) {
                    return this.revision;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                break;
            case 't':
                if ( name.equals("transaction_type") ) {
                    return this.transaction_type;
                }
                break;
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    return this.underlying_product_code;
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
                if ( name.equals("appli_start_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_date = (java.sql.Timestamp) value;
                    if (this.appli_start_date_is_set)
                        this.appli_start_date_is_modified = true;
                    this.appli_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_date = (java.sql.Timestamp) value;
                    if (this.appli_end_date_is_set)
                        this.appli_end_date_is_modified = true;
                    this.appli_end_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_product_code' must be String: '"+value+"' is not." );
                    this.comm_product_code = (String) value;
                    if (this.comm_product_code_is_set)
                        this.comm_product_code_is_modified = true;
                    this.comm_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("day_trade_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'day_trade_type' must be String: '"+value+"' is not." );
                    this.day_trade_type = (String) value;
                    if (this.day_trade_type_is_set)
                        this.day_trade_type_is_modified = true;
                    this.day_trade_type_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exec_cond_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_cond_type' must be String: '"+value+"' is not." );
                    this.exec_cond_type = (String) value;
                    if (this.exec_cond_type_is_set)
                        this.exec_cond_type_is_modified = true;
                    this.exec_cond_type_is_set = true;
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
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_channel") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_channel' must be String: '"+value+"' is not." );
                    this.order_channel = (String) value;
                    if (this.order_channel_is_set)
                        this.order_channel_is_modified = true;
                    this.order_channel_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_type' must be String: '"+value+"' is not." );
                    this.pay_type = (String) value;
                    if (this.pay_type_is_set)
                        this.pay_type_is_modified = true;
                    this.pay_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("revision") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'revision' must be String: '"+value+"' is not." );
                    this.revision = (String) value;
                    if (this.revision_is_set)
                        this.revision_is_modified = true;
                    this.revision_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_market_code' must be String: '"+value+"' is not." );
                    this.sonar_market_code = (String) value;
                    if (this.sonar_market_code_is_set)
                        this.sonar_market_code_is_modified = true;
                    this.sonar_market_code_is_set = true;
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
            case 'u':
                if ( name.equals("underlying_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'underlying_product_code' must be String: '"+value+"' is not." );
                    this.underlying_product_code = (String) value;
                    if (this.underlying_product_code_is_set)
                        this.underlying_product_code_is_modified = true;
                    this.underlying_product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
