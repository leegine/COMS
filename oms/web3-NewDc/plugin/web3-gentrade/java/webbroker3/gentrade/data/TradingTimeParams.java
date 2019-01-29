head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TradingTimeParams.java;


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
 * trading_time�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link TradingTimeRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link TradingTimeParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link TradingTimeParams}��{@@link TradingTimeRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradingTimePK 
 * @@see TradingTimeRow 
 */
public class TradingTimeParams extends Params implements TradingTimeRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "trading_time";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = TradingTimeRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return TradingTimeRow.TYPE;
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
   * <em>market_code</em>�J�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>trading_time_type</em>�J�����̒l 
   */
  public  String  trading_time_type;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>biz_date_type</em>�J�����̒l 
   */
  public  String  biz_date_type;

  /** 
   * <em>start_time</em>�J�����̒l 
   */
  public  String  start_time;

  /** 
   * <em>end_time</em>�J�����̒l 
   */
  public  String  end_time;

  /** 
   * <em>submit_market_trigger</em>�J�����̒l 
   */
  public  String  submit_market_trigger;

  /** 
   * <em>enable_order</em>�J�����̒l 
   */
  public  String  enable_order;

  /** 
   * <em>bizdate_calc_parameter</em>�J�����̒l 
   */
  public  String  bizdate_calc_parameter;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>session_type</em>�J�����̒l 
   */
  public  String  session_type;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean trading_time_type_is_set = false;
  private boolean trading_time_type_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean biz_date_type_is_set = false;
  private boolean biz_date_type_is_modified = false;
  private boolean start_time_is_set = false;
  private boolean start_time_is_modified = false;
  private boolean end_time_is_set = false;
  private boolean end_time_is_modified = false;
  private boolean submit_market_trigger_is_set = false;
  private boolean submit_market_trigger_is_modified = false;
  private boolean enable_order_is_set = false;
  private boolean enable_order_is_modified = false;
  private boolean bizdate_calc_parameter_is_set = false;
  private boolean bizdate_calc_parameter_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean session_type_is_set = false;
  private boolean session_type_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "market_code=" + market_code
      + "," + "trading_time_type=" + trading_time_type
      + "," + "product_code=" + product_code
      + "," + "biz_date_type=" + biz_date_type
      + "," + "start_time=" + start_time
      + "," + "end_time=" +end_time
      + "," + "submit_market_trigger=" +submit_market_trigger
      + "," + "enable_order=" +enable_order
      + "," + "bizdate_calc_parameter=" +bizdate_calc_parameter
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "session_type=" +session_type
      + "}";
  }


  /** 
   * �l�����ݒ��TradingTimeParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public TradingTimeParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    market_code_is_modified = true;
    trading_time_type_is_modified = true;
    product_code_is_modified = true;
    biz_date_type_is_modified = true;
    start_time_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���TradingTimeRow�I�u�W�F�N�g�̒l�𗘗p����TradingTimeParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����TradingTimeRow�I�u�W�F�N�g 
   */
  public TradingTimeParams( TradingTimeRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    trading_time_type = p_row.getTradingTimeType();
    trading_time_type_is_set = p_row.getTradingTimeTypeIsSet();
    trading_time_type_is_modified = p_row.getTradingTimeTypeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    biz_date_type = p_row.getBizDateType();
    biz_date_type_is_set = p_row.getBizDateTypeIsSet();
    biz_date_type_is_modified = p_row.getBizDateTypeIsModified();
    start_time = p_row.getStartTime();
    start_time_is_set = p_row.getStartTimeIsSet();
    start_time_is_modified = p_row.getStartTimeIsModified();
    end_time = p_row.getEndTime();
    end_time_is_set = p_row.getEndTimeIsSet();
    end_time_is_modified = p_row.getEndTimeIsModified();
    submit_market_trigger = p_row.getSubmitMarketTrigger();
    submit_market_trigger_is_set = p_row.getSubmitMarketTriggerIsSet();
    submit_market_trigger_is_modified = p_row.getSubmitMarketTriggerIsModified();
    enable_order = p_row.getEnableOrder();
    enable_order_is_set = p_row.getEnableOrderIsSet();
    enable_order_is_modified = p_row.getEnableOrderIsModified();
    bizdate_calc_parameter = p_row.getBizdateCalcParameter();
    bizdate_calc_parameter_is_set = p_row.getBizdateCalcParameterIsSet();
    bizdate_calc_parameter_is_modified = p_row.getBizdateCalcParameterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    session_type = p_row.getSessionType();
    session_type_is_set = p_row.getSessionTypeIsSet();
    session_type_is_modified = p_row.getSessionTypeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      end_time_is_set = true;
      end_time_is_modified = true;
      submit_market_trigger_is_set = true;
      submit_market_trigger_is_modified = true;
      enable_order_is_set = true;
      enable_order_is_modified = true;
      bizdate_calc_parameter_is_set = true;
      bizdate_calc_parameter_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      session_type_is_set = true;
      session_type_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TradingTimeRow ) )
       return false;
    return fieldsEqual( (TradingTimeRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�TradingTimeRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( TradingTimeRow row )
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
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( trading_time_type == null ) {
      if ( row.getTradingTimeType() != null )
        return false;
    } else if ( !trading_time_type.equals( row.getTradingTimeType() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( biz_date_type == null ) {
      if ( row.getBizDateType() != null )
        return false;
    } else if ( !biz_date_type.equals( row.getBizDateType() ) ) {
        return false;
    }
    if ( start_time == null ) {
      if ( row.getStartTime() != null )
        return false;
    } else if ( !start_time.equals( row.getStartTime() ) ) {
        return false;
    }
    if ( end_time == null ) {
      if ( row.getEndTime() != null )
        return false;
    } else if ( !end_time.equals( row.getEndTime() ) ) {
        return false;
    }
    if ( submit_market_trigger == null ) {
      if ( row.getSubmitMarketTrigger() != null )
        return false;
    } else if ( !submit_market_trigger.equals( row.getSubmitMarketTrigger() ) ) {
        return false;
    }
    if ( enable_order == null ) {
      if ( row.getEnableOrder() != null )
        return false;
    } else if ( !enable_order.equals( row.getEnableOrder() ) ) {
        return false;
    }
    if ( bizdate_calc_parameter == null ) {
      if ( row.getBizdateCalcParameter() != null )
        return false;
    } else if ( !bizdate_calc_parameter.equals( row.getBizdateCalcParameter() ) ) {
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
    if ( session_type == null ) {
      if ( row.getSessionType() != null )
        return false;
    } else if ( !session_type.equals( row.getSessionType() ) ) {
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
        + (market_code!=null? market_code.hashCode(): 0) 
        + (trading_time_type!=null? trading_time_type.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (biz_date_type!=null? biz_date_type.hashCode(): 0) 
        + (start_time!=null? start_time.hashCode(): 0) 
        + (end_time!=null? end_time.hashCode(): 0) 
        + (submit_market_trigger!=null? submit_market_trigger.hashCode(): 0) 
        + (enable_order!=null? enable_order.hashCode(): 0) 
        + (bizdate_calc_parameter!=null? bizdate_calc_parameter.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (session_type!=null? session_type.hashCode(): 0) 
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
		map.put("branch_code",branch_code);
		map.put("market_code",market_code);
		map.put("trading_time_type",trading_time_type);
		map.put("product_code",product_code);
		map.put("biz_date_type",biz_date_type);
		map.put("start_time",start_time);
		if ( end_time != null )
			map.put("end_time",end_time);
		if ( submit_market_trigger != null )
			map.put("submit_market_trigger",submit_market_trigger);
		if ( enable_order != null )
			map.put("enable_order",enable_order);
		if ( bizdate_calc_parameter != null )
			map.put("bizdate_calc_parameter",bizdate_calc_parameter);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( session_type != null )
			map.put("session_type",session_type);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( end_time_is_modified )
			map.put("end_time",end_time);
		if ( submit_market_trigger_is_modified )
			map.put("submit_market_trigger",submit_market_trigger);
		if ( enable_order_is_modified )
			map.put("enable_order",enable_order);
		if ( bizdate_calc_parameter_is_modified )
			map.put("bizdate_calc_parameter",bizdate_calc_parameter);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( session_type_is_modified )
			map.put("session_type",session_type);
		if (map.size() == 0) {
			map.put("end_time",end_time);
			map.put("submit_market_trigger",submit_market_trigger);
			map.put("enable_order",enable_order);
			map.put("bizdate_calc_parameter",bizdate_calc_parameter);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("session_type",session_type);
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
   * <em>market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>trading_time_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTradingTimeType()
  {
    return trading_time_type;
  }


  /** 
   * <em>trading_time_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradingTimeTypeIsSet() {
    return trading_time_type_is_set;
  }


  /** 
   * <em>trading_time_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradingTimeTypeIsModified() {
    return trading_time_type_is_modified;
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
   * <em>biz_date_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBizDateType()
  {
    return biz_date_type;
  }


  /** 
   * <em>biz_date_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizDateTypeIsSet() {
    return biz_date_type_is_set;
  }


  /** 
   * <em>biz_date_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizDateTypeIsModified() {
    return biz_date_type_is_modified;
  }


  /** 
   * <em>start_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStartTime()
  {
    return start_time;
  }


  /** 
   * <em>start_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartTimeIsSet() {
    return start_time_is_set;
  }


  /** 
   * <em>start_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStartTimeIsModified() {
    return start_time_is_modified;
  }


  /** 
   * <em>end_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEndTime()
  {
    return end_time;
  }


  /** 
   * <em>end_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndTimeIsSet() {
    return end_time_is_set;
  }


  /** 
   * <em>end_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndTimeIsModified() {
    return end_time_is_modified;
  }


  /** 
   * <em>submit_market_trigger</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSubmitMarketTrigger()
  {
    return submit_market_trigger;
  }


  /** 
   * <em>submit_market_trigger</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubmitMarketTriggerIsSet() {
    return submit_market_trigger_is_set;
  }


  /** 
   * <em>submit_market_trigger</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubmitMarketTriggerIsModified() {
    return submit_market_trigger_is_modified;
  }


  /** 
   * <em>enable_order</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEnableOrder()
  {
    return enable_order;
  }


  /** 
   * <em>enable_order</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableOrderIsSet() {
    return enable_order_is_set;
  }


  /** 
   * <em>enable_order</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEnableOrderIsModified() {
    return enable_order_is_modified;
  }


  /** 
   * <em>bizdate_calc_parameter</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBizdateCalcParameter()
  {
    return bizdate_calc_parameter;
  }


  /** 
   * <em>bizdate_calc_parameter</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizdateCalcParameterIsSet() {
    return bizdate_calc_parameter_is_set;
  }


  /** 
   * <em>bizdate_calc_parameter</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizdateCalcParameterIsModified() {
    return bizdate_calc_parameter_is_modified;
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
   * <em>session_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSessionType()
  {
    return session_type;
  }


  /** 
   * <em>session_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSessionTypeIsSet() {
    return session_type_is_set;
  }


  /** 
   * <em>session_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSessionTypeIsModified() {
    return session_type_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TradingTimePK(institution_code, branch_code, market_code, trading_time_type, product_code, biz_date_type, start_time);
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
   * <em>market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>trading_time_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradingTimeType <em>trading_time_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setTradingTimeType( String p_tradingTimeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_time_type = p_tradingTimeType;
    trading_time_type_is_set = true;
    trading_time_type_is_modified = true;
  }


  /** 
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>biz_date_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizDateType <em>biz_date_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBizDateType( String p_bizDateType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date_type = p_bizDateType;
    biz_date_type_is_set = true;
    biz_date_type_is_modified = true;
  }


  /** 
   * <em>start_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_startTime <em>start_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setStartTime( String p_startTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_time = p_startTime;
    start_time_is_set = true;
    start_time_is_modified = true;
  }


  /** 
   * <em>end_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_endTime <em>end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setEndTime( String p_endTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_time = p_endTime;
    end_time_is_set = true;
    end_time_is_modified = true;
  }


  /** 
   * <em>submit_market_trigger</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_submitMarketTrigger <em>submit_market_trigger</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSubmitMarketTrigger( String p_submitMarketTrigger )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    submit_market_trigger = p_submitMarketTrigger;
    submit_market_trigger_is_set = true;
    submit_market_trigger_is_modified = true;
  }


  /** 
   * <em>enable_order</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_enableOrder <em>enable_order</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setEnableOrder( String p_enableOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_order = p_enableOrder;
    enable_order_is_set = true;
    enable_order_is_modified = true;
  }


  /** 
   * <em>bizdate_calc_parameter</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizdateCalcParameter <em>bizdate_calc_parameter</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBizdateCalcParameter( String p_bizdateCalcParameter )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bizdate_calc_parameter = p_bizdateCalcParameter;
    bizdate_calc_parameter_is_set = true;
    bizdate_calc_parameter_is_modified = true;
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
   * <em>session_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sessionType <em>session_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSessionType( String p_sessionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    session_type = p_sessionType;
    session_type_is_set = true;
    session_type_is_modified = true;
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
                else if ( name.equals("biz_date_type") ) {
                    return this.biz_date_type;
                }
                else if ( name.equals("bizdate_calc_parameter") ) {
                    return this.bizdate_calc_parameter;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("end_time") ) {
                    return this.end_time;
                }
                else if ( name.equals("enable_order") ) {
                    return this.enable_order;
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
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 's':
                if ( name.equals("start_time") ) {
                    return this.start_time;
                }
                else if ( name.equals("submit_market_trigger") ) {
                    return this.submit_market_trigger;
                }
                else if ( name.equals("session_type") ) {
                    return this.session_type;
                }
                break;
            case 't':
                if ( name.equals("trading_time_type") ) {
                    return this.trading_time_type;
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
                else if ( name.equals("biz_date_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date_type' must be String: '"+value+"' is not." );
                    this.biz_date_type = (String) value;
                    if (this.biz_date_type_is_set)
                        this.biz_date_type_is_modified = true;
                    this.biz_date_type_is_set = true;
                    return;
                }
                else if ( name.equals("bizdate_calc_parameter") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bizdate_calc_parameter' must be String: '"+value+"' is not." );
                    this.bizdate_calc_parameter = (String) value;
                    if (this.bizdate_calc_parameter_is_set)
                        this.bizdate_calc_parameter_is_modified = true;
                    this.bizdate_calc_parameter_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("end_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'end_time' must be String: '"+value+"' is not." );
                    this.end_time = (String) value;
                    if (this.end_time_is_set)
                        this.end_time_is_modified = true;
                    this.end_time_is_set = true;
                    return;
                }
                else if ( name.equals("enable_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enable_order' must be String: '"+value+"' is not." );
                    this.enable_order = (String) value;
                    if (this.enable_order_is_set)
                        this.enable_order_is_modified = true;
                    this.enable_order_is_set = true;
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
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("start_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'start_time' must be String: '"+value+"' is not." );
                    this.start_time = (String) value;
                    if (this.start_time_is_set)
                        this.start_time_is_modified = true;
                    this.start_time_is_set = true;
                    return;
                }
                else if ( name.equals("submit_market_trigger") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'submit_market_trigger' must be String: '"+value+"' is not." );
                    this.submit_market_trigger = (String) value;
                    if (this.submit_market_trigger_is_set)
                        this.submit_market_trigger_is_modified = true;
                    this.submit_market_trigger_is_set = true;
                    return;
                }
                else if ( name.equals("session_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'session_type' must be String: '"+value+"' is not." );
                    this.session_type = (String) value;
                    if (this.session_type_is_set)
                        this.session_type_is_modified = true;
                    this.session_type_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trading_time_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_time_type' must be String: '"+value+"' is not." );
                    this.trading_time_type = (String) value;
                    if (this.trading_time_type_is_set)
                        this.trading_time_type_is_modified = true;
                    this.trading_time_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
