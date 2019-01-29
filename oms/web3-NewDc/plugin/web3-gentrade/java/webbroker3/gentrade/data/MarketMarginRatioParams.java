head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketMarginRatioParams.java;


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
 * market_margin_ratio�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MarketMarginRatioRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MarketMarginRatioParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MarketMarginRatioParams}��{@@link MarketMarginRatioRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketMarginRatioPK 
 * @@see MarketMarginRatioRow 
 */
public class MarketMarginRatioParams extends Params implements MarketMarginRatioRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "market_margin_ratio";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MarketMarginRatioRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MarketMarginRatioRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  long  market_id;

  /** 
   * <em>list_type</em>�J�����̒l 
   */
  public  String  list_type;

  /** 
   * <em>new_list_type</em>�J�����̒l 
   */
  public  String  new_list_type;

  /** 
   * <em>securities_estimation_div</em>�J�����̒l 
   */
  public  String  securities_estimation_div;

  /** 
   * <em>priority</em>�J�����̒l 
   */
  public  double  priority;

  /** 
   * <em>margin_ratio</em>�J�����̒l 
   */
  public  double  margin_ratio;

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
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean list_type_is_set = false;
  private boolean list_type_is_modified = false;
  private boolean new_list_type_is_set = false;
  private boolean new_list_type_is_modified = false;
  private boolean securities_estimation_div_is_set = false;
  private boolean securities_estimation_div_is_modified = false;
  private boolean priority_is_set = false;
  private boolean priority_is_modified = false;
  private boolean margin_ratio_is_set = false;
  private boolean margin_ratio_is_modified = false;
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
      + "," + "market_id=" + market_id
      + "," + "list_type=" + list_type
      + "," + "new_list_type=" + new_list_type
      + "," + "securities_estimation_div=" + securities_estimation_div
      + "," + "priority=" +priority
      + "," + "margin_ratio=" +margin_ratio
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��MarketMarginRatioParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MarketMarginRatioParams() {
    institution_code_is_modified = true;
    market_id_is_modified = true;
    list_type_is_modified = true;
    new_list_type_is_modified = true;
    securities_estimation_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MarketMarginRatioRow�I�u�W�F�N�g�̒l�𗘗p����MarketMarginRatioParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MarketMarginRatioRow�I�u�W�F�N�g 
   */
  public MarketMarginRatioParams( MarketMarginRatioRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    list_type = p_row.getListType();
    list_type_is_set = p_row.getListTypeIsSet();
    list_type_is_modified = p_row.getListTypeIsModified();
    new_list_type = p_row.getNewListType();
    new_list_type_is_set = p_row.getNewListTypeIsSet();
    new_list_type_is_modified = p_row.getNewListTypeIsModified();
    securities_estimation_div = p_row.getSecuritiesEstimationDiv();
    securities_estimation_div_is_set = p_row.getSecuritiesEstimationDivIsSet();
    securities_estimation_div_is_modified = p_row.getSecuritiesEstimationDivIsModified();
    priority = p_row.getPriority();
    priority_is_set = p_row.getPriorityIsSet();
    priority_is_modified = p_row.getPriorityIsModified();
    margin_ratio = p_row.getMarginRatio();
    margin_ratio_is_set = p_row.getMarginRatioIsSet();
    margin_ratio_is_modified = p_row.getMarginRatioIsModified();
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
      priority_is_set = true;
      priority_is_modified = true;
      margin_ratio_is_set = true;
      margin_ratio_is_modified = true;
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
    if ( !( other instanceof MarketMarginRatioRow ) )
       return false;
    return fieldsEqual( (MarketMarginRatioRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MarketMarginRatioRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MarketMarginRatioRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( market_id != row.getMarketId() )
      return false;
    if ( list_type == null ) {
      if ( row.getListType() != null )
        return false;
    } else if ( !list_type.equals( row.getListType() ) ) {
        return false;
    }
    if ( new_list_type == null ) {
      if ( row.getNewListType() != null )
        return false;
    } else if ( !new_list_type.equals( row.getNewListType() ) ) {
        return false;
    }
    if ( securities_estimation_div == null ) {
      if ( row.getSecuritiesEstimationDiv() != null )
        return false;
    } else if ( !securities_estimation_div.equals( row.getSecuritiesEstimationDiv() ) ) {
        return false;
    }
    if ( priority != row.getPriority() )
      return false;
    if ( margin_ratio != row.getMarginRatio() )
      return false;
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
        + ((int) market_id)
        + (list_type!=null? list_type.hashCode(): 0) 
        + (new_list_type!=null? new_list_type.hashCode(): 0) 
        + (securities_estimation_div!=null? securities_estimation_div.hashCode(): 0) 
        + ((int) priority)
        + ((int) margin_ratio)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !priority_is_set )
			throw new IllegalArgumentException("non-nullable field 'priority' must be set before inserting.");
		if ( !margin_ratio_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_ratio' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("market_id",new Long(market_id));
		map.put("list_type",list_type);
		map.put("new_list_type",new_list_type);
		map.put("securities_estimation_div",securities_estimation_div);
		map.put("priority",new Double(priority));
		map.put("margin_ratio",new Double(margin_ratio));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( priority_is_modified )
			map.put("priority",new Double(priority));
		if ( margin_ratio_is_modified )
			map.put("margin_ratio",new Double(margin_ratio));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( priority_is_set )
				map.put("priority",new Double(priority));
			if ( margin_ratio_is_set )
				map.put("margin_ratio",new Double(margin_ratio));
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
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>list_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getListType()
  {
    return list_type;
  }


  /** 
   * <em>list_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getListTypeIsSet() {
    return list_type_is_set;
  }


  /** 
   * <em>list_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getListTypeIsModified() {
    return list_type_is_modified;
  }


  /** 
   * <em>new_list_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getNewListType()
  {
    return new_list_type;
  }


  /** 
   * <em>new_list_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewListTypeIsSet() {
    return new_list_type_is_set;
  }


  /** 
   * <em>new_list_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNewListTypeIsModified() {
    return new_list_type_is_modified;
  }


  /** 
   * <em>securities_estimation_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSecuritiesEstimationDiv()
  {
    return securities_estimation_div;
  }


  /** 
   * <em>securities_estimation_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSecuritiesEstimationDivIsSet() {
    return securities_estimation_div_is_set;
  }


  /** 
   * <em>securities_estimation_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSecuritiesEstimationDivIsModified() {
    return securities_estimation_div_is_modified;
  }


  /** 
   * <em>priority</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getPriority()
  {
    return priority;
  }


  /** 
   * <em>priority</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriorityIsSet() {
    return priority_is_set;
  }


  /** 
   * <em>priority</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriorityIsModified() {
    return priority_is_modified;
  }


  /** 
   * <em>margin_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getMarginRatio()
  {
    return margin_ratio;
  }


  /** 
   * <em>margin_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginRatioIsSet() {
    return margin_ratio_is_set;
  }


  /** 
   * <em>margin_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarginRatioIsModified() {
    return margin_ratio_is_modified;
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
    return new MarketMarginRatioPK(institution_code, market_id, list_type, new_list_type, securities_estimation_div);
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
   * <em>market_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>list_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_listType <em>list_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setListType( String p_listType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    list_type = p_listType;
    list_type_is_set = true;
    list_type_is_modified = true;
  }


  /** 
   * <em>new_list_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_newListType <em>new_list_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setNewListType( String p_newListType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_list_type = p_newListType;
    new_list_type_is_set = true;
    new_list_type_is_modified = true;
  }


  /** 
   * <em>securities_estimation_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_securitiesEstimationDiv <em>securities_estimation_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSecuritiesEstimationDiv( String p_securitiesEstimationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    securities_estimation_div = p_securitiesEstimationDiv;
    securities_estimation_div_is_set = true;
    securities_estimation_div_is_modified = true;
  }


  /** 
   * <em>priority</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_priority <em>priority</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setPriority( double p_priority )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    priority = p_priority;
    priority_is_set = true;
    priority_is_modified = true;
  }


  /** 
   * <em>margin_ratio</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marginRatio <em>margin_ratio</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setMarginRatio( double p_marginRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_ratio = p_marginRatio;
    margin_ratio_is_set = true;
    margin_ratio_is_modified = true;
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
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("list_type") ) {
                    return this.list_type;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                else if ( name.equals("margin_ratio") ) {
                    return new Double( margin_ratio );
                }
                break;
            case 'n':
                if ( name.equals("new_list_type") ) {
                    return this.new_list_type;
                }
                break;
            case 'p':
                if ( name.equals("priority") ) {
                    return new Double( priority );
                }
                break;
            case 's':
                if ( name.equals("securities_estimation_div") ) {
                    return this.securities_estimation_div;
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
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
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
                if ( name.equals("list_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'list_type' must be String: '"+value+"' is not." );
                    this.list_type = (String) value;
                    if (this.list_type_is_set)
                        this.list_type_is_modified = true;
                    this.list_type_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                else if ( name.equals("margin_ratio") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_ratio' must be Double: '"+value+"' is not." );
                    this.margin_ratio = ((Double) value).doubleValue();
                    if (this.margin_ratio_is_set)
                        this.margin_ratio_is_modified = true;
                    this.margin_ratio_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_list_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_list_type' must be String: '"+value+"' is not." );
                    this.new_list_type = (String) value;
                    if (this.new_list_type_is_set)
                        this.new_list_type_is_modified = true;
                    this.new_list_type_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("priority") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'priority' must be Double: '"+value+"' is not." );
                    this.priority = ((Double) value).doubleValue();
                    if (this.priority_is_set)
                        this.priority_is_modified = true;
                    this.priority_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("securities_estimation_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'securities_estimation_div' must be String: '"+value+"' is not." );
                    this.securities_estimation_div = (String) value;
                    if (this.securities_estimation_div_is_set)
                        this.securities_estimation_div_is_modified = true;
                    this.securities_estimation_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
