head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	SpecialProductForeignCostParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * special_product_foreign_cost�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SpecialProductForeignCostParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SpecialProductForeignCostParams}��{@@link SpecialProductForeignCostRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SpecialProductForeignCostPK 
 * @@see SpecialProductForeignCostRow 
 */
public class SpecialProductForeignCostParams extends Params implements SpecialProductForeignCostRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "special_product_foreign_cost";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SpecialProductForeignCostRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SpecialProductForeignCostRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>market_code</em>�J�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>offshore_product_code</em>�J�����̒l 
   */
  public  String  offshore_product_code;

  /** 
   * <em>cost_div</em>�J�����̒l 
   */
  public  String  cost_div;

  /** 
   * <em>appli_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>amount_from</em>�J�����̒l 
   */
  public  double  amount_from;

  /** 
   * <em>side_div</em>�J�����̒l 
   */
  public  String  side_div;

  /** 
   * <em>appli_end_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>amount_to</em>�J�����̒l 
   */
  public  Double  amount_to;

  /** 
   * <em>commision_rate</em>�J�����̒l 
   */
  public  Double  commision_rate;

  /** 
   * <em>add_amount</em>�J�����̒l 
   */
  public  Double  add_amount;

  /** 
   * <em>scale</em>�J�����̒l 
   */
  public  int  scale;

  /** 
   * <em>round_div</em>�J�����̒l 
   */
  public  String  round_div;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

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
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean offshore_product_code_is_set = false;
  private boolean offshore_product_code_is_modified = false;
  private boolean cost_div_is_set = false;
  private boolean cost_div_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean amount_from_is_set = false;
  private boolean amount_from_is_modified = false;
  private boolean amount_to_is_set = false;
  private boolean amount_to_is_modified = false;
  private boolean commision_rate_is_set = false;
  private boolean commision_rate_is_modified = false;
  private boolean add_amount_is_set = false;
  private boolean add_amount_is_modified = false;
  private boolean scale_is_set = false;
  private boolean scale_is_modified = false;
  private boolean round_div_is_set = false;
  private boolean round_div_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean side_div_is_set = false;
  private boolean side_div_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "market_code=" + market_code
      + "," + "product_id=" + product_id
      + "," + "offshore_product_code=" + offshore_product_code
      + "," + "cost_div=" + cost_div
      + "," + "appli_start_date=" + appli_start_date
      + "," + "amount_from=" + amount_from
      + "," + "side_div=" + side_div
      + "," + "appli_end_date=" +appli_end_date
      + "," + "amount_to=" +amount_to
      + "," + "commision_rate=" +commision_rate
      + "," + "add_amount=" +add_amount
      + "," + "scale=" +scale
      + "," + "round_div=" +round_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SpecialProductForeignCostParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SpecialProductForeignCostParams() {
    institution_code_is_modified = true;
    market_code_is_modified = true;
    product_id_is_modified = true;
    offshore_product_code_is_modified = true;
    cost_div_is_modified = true;
    appli_start_date_is_modified = true;
    amount_from_is_modified = true;
    side_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SpecialProductForeignCostRow�I�u�W�F�N�g�̒l�𗘗p����SpecialProductForeignCostParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SpecialProductForeignCostRow�I�u�W�F�N�g 
   */
  public SpecialProductForeignCostParams( SpecialProductForeignCostRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    offshore_product_code = p_row.getOffshoreProductCode();
    offshore_product_code_is_set = p_row.getOffshoreProductCodeIsSet();
    offshore_product_code_is_modified = p_row.getOffshoreProductCodeIsModified();
    cost_div = p_row.getCostDiv();
    cost_div_is_set = p_row.getCostDivIsSet();
    cost_div_is_modified = p_row.getCostDivIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    amount_from = p_row.getAmountFrom();
    amount_from_is_set = p_row.getAmountFromIsSet();
    amount_from_is_modified = p_row.getAmountFromIsModified();
    side_div = p_row.getSideDiv();
    side_div_is_set = p_row.getSideDivIsSet();
    side_div_is_modified = p_row.getSideDivIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    if ( !p_row.getAmountToIsNull() )
      amount_to = new Double( p_row.getAmountTo() );
    amount_to_is_set = p_row.getAmountToIsSet();
    amount_to_is_modified = p_row.getAmountToIsModified();
    if ( !p_row.getCommisionRateIsNull() )
      commision_rate = new Double( p_row.getCommisionRate() );
    commision_rate_is_set = p_row.getCommisionRateIsSet();
    commision_rate_is_modified = p_row.getCommisionRateIsModified();
    if ( !p_row.getAddAmountIsNull() )
      add_amount = new Double( p_row.getAddAmount() );
    add_amount_is_set = p_row.getAddAmountIsSet();
    add_amount_is_modified = p_row.getAddAmountIsModified();
    scale = p_row.getScale();
    scale_is_set = p_row.getScaleIsSet();
    scale_is_modified = p_row.getScaleIsModified();
    round_div = p_row.getRoundDiv();
    round_div_is_set = p_row.getRoundDivIsSet();
    round_div_is_modified = p_row.getRoundDivIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      amount_to_is_set = true;
      amount_to_is_modified = true;
      commision_rate_is_set = true;
      commision_rate_is_modified = true;
      add_amount_is_set = true;
      add_amount_is_modified = true;
      scale_is_set = true;
      scale_is_modified = true;
      round_div_is_set = true;
      round_div_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof SpecialProductForeignCostRow ) )
       return false;
    return fieldsEqual( (SpecialProductForeignCostRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SpecialProductForeignCostRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SpecialProductForeignCostRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( offshore_product_code == null ) {
      if ( row.getOffshoreProductCode() != null )
        return false;
    } else if ( !offshore_product_code.equals( row.getOffshoreProductCode() ) ) {
        return false;
    }
    if ( cost_div == null ) {
      if ( row.getCostDiv() != null )
        return false;
    } else if ( !cost_div.equals( row.getCostDiv() ) ) {
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
    if ( amount_from != row.getAmountFrom() )
      return false;
    if ( amount_to == null ) {
      if ( !row.getAmountToIsNull() )
        return false;
    } else if ( row.getAmountToIsNull() || ( amount_to.doubleValue() != row.getAmountTo() ) ) {
        return false;
    }
    if ( commision_rate == null ) {
      if ( !row.getCommisionRateIsNull() )
        return false;
    } else if ( row.getCommisionRateIsNull() || ( commision_rate.doubleValue() != row.getCommisionRate() ) ) {
        return false;
    }
    if ( add_amount == null ) {
      if ( !row.getAddAmountIsNull() )
        return false;
    } else if ( row.getAddAmountIsNull() || ( add_amount.doubleValue() != row.getAddAmount() ) ) {
        return false;
    }
    if ( scale != row.getScale() )
      return false;
    if ( round_div == null ) {
      if ( row.getRoundDiv() != null )
        return false;
    } else if ( !round_div.equals( row.getRoundDiv() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
    if ( side_div == null ) {
      if ( row.getSideDiv() != null )
        return false;
    } else if ( !side_div.equals( row.getSideDiv() ) ) {
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
        + (market_code!=null? market_code.hashCode(): 0) 
        + ((int) product_id)
        + (offshore_product_code!=null? offshore_product_code.hashCode(): 0) 
        + (cost_div!=null? cost_div.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + ((int) amount_from)
        + (amount_to!=null? amount_to.hashCode(): 0) 
        + (commision_rate!=null? commision_rate.hashCode(): 0) 
        + (add_amount!=null? add_amount.hashCode(): 0) 
        + ((int) scale)
        + (round_div!=null? round_div.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (side_div!=null? side_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !scale_is_set )
			throw new IllegalArgumentException("non-nullable field 'scale' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("market_code",market_code);
		map.put("product_id",new Long(product_id));
		map.put("offshore_product_code",offshore_product_code);
		map.put("cost_div",cost_div);
		map.put("appli_start_date",appli_start_date);
		if ( appli_end_date != null )
			map.put("appli_end_date",appli_end_date);
		map.put("amount_from",new Double(amount_from));
		if ( amount_to != null )
			map.put("amount_to",amount_to);
		if ( commision_rate != null )
			map.put("commision_rate",commision_rate);
		if ( add_amount != null )
			map.put("add_amount",add_amount);
		map.put("scale",new Integer(scale));
		if ( round_div_is_set )
			map.put("round_div",round_div);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("side_div",side_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( amount_to_is_modified )
			map.put("amount_to",amount_to);
		if ( commision_rate_is_modified )
			map.put("commision_rate",commision_rate);
		if ( add_amount_is_modified )
			map.put("add_amount",add_amount);
		if ( scale_is_modified )
			map.put("scale",new Integer(scale));
		if ( round_div_is_modified )
			map.put("round_div",round_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("appli_end_date",appli_end_date);
			map.put("amount_to",amount_to);
			map.put("commision_rate",commision_rate);
			map.put("add_amount",add_amount);
			if ( scale_is_set )
				map.put("scale",new Integer(scale));
			if ( round_div_is_set )
				map.put("round_div",round_div);
			map.put("last_updater",last_updater);
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
   * <em>product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
  }


  /** 
   * <em>offshore_product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOffshoreProductCode()
  {
    return offshore_product_code;
  }


  /** 
   * <em>offshore_product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOffshoreProductCodeIsSet() {
    return offshore_product_code_is_set;
  }


  /** 
   * <em>offshore_product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOffshoreProductCodeIsModified() {
    return offshore_product_code_is_modified;
  }


  /** 
   * <em>cost_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCostDiv()
  {
    return cost_div;
  }


  /** 
   * <em>cost_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCostDivIsSet() {
    return cost_div_is_set;
  }


  /** 
   * <em>cost_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCostDivIsModified() {
    return cost_div_is_modified;
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
   * <em>amount_from</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAmountFrom()
  {
    return amount_from;
  }


  /** 
   * <em>amount_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountFromIsSet() {
    return amount_from_is_set;
  }


  /** 
   * <em>amount_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountFromIsModified() {
    return amount_from_is_modified;
  }


  /** 
   * <em>amount_to</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAmountTo()
  {
    return ( amount_to==null? ((double)0): amount_to.doubleValue() );
  }


  /** 
   * <em>amount_to</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAmountToIsNull()
  {
    return amount_to == null;
  }


  /** 
   * <em>amount_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountToIsSet() {
    return amount_to_is_set;
  }


  /** 
   * <em>amount_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountToIsModified() {
    return amount_to_is_modified;
  }


  /** 
   * <em>commision_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCommisionRate()
  {
    return ( commision_rate==null? ((double)0): commision_rate.doubleValue() );
  }


  /** 
   * <em>commision_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCommisionRateIsNull()
  {
    return commision_rate == null;
  }


  /** 
   * <em>commision_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommisionRateIsSet() {
    return commision_rate_is_set;
  }


  /** 
   * <em>commision_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCommisionRateIsModified() {
    return commision_rate_is_modified;
  }


  /** 
   * <em>add_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAddAmount()
  {
    return ( add_amount==null? ((double)0): add_amount.doubleValue() );
  }


  /** 
   * <em>add_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAddAmountIsNull()
  {
    return add_amount == null;
  }


  /** 
   * <em>add_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAddAmountIsSet() {
    return add_amount_is_set;
  }


  /** 
   * <em>add_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAddAmountIsModified() {
    return add_amount_is_modified;
  }


  /** 
   * <em>scale</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getScale()
  {
    return scale;
  }


  /** 
   * <em>scale</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getScaleIsSet() {
    return scale_is_set;
  }


  /** 
   * <em>scale</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getScaleIsModified() {
    return scale_is_modified;
  }


  /** 
   * <em>round_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRoundDiv()
  {
    return round_div;
  }


  /** 
   * <em>round_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRoundDivIsSet() {
    return round_div_is_set;
  }


  /** 
   * <em>round_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRoundDivIsModified() {
    return round_div_is_modified;
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
   * <em>side_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSideDiv()
  {
    return side_div;
  }


  /** 
   * <em>side_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSideDivIsSet() {
    return side_div_is_set;
  }


  /** 
   * <em>side_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSideDivIsModified() {
    return side_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SpecialProductForeignCostPK(institution_code, market_code, product_id, offshore_product_code, cost_div, appli_start_date, amount_from, side_div);
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
   * <em>product_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productId <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>offshore_product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_offshoreProductCode <em>offshore_product_code</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setOffshoreProductCode( String p_offshoreProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    offshore_product_code = p_offshoreProductCode;
    offshore_product_code_is_set = true;
    offshore_product_code_is_modified = true;
  }


  /** 
   * <em>cost_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_costDiv <em>cost_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setCostDiv( String p_costDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cost_div = p_costDiv;
    cost_div_is_set = true;
    cost_div_is_modified = true;
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
   * <em>amount_from</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_amountFrom <em>amount_from</em>�J�����̒l������킷13���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setAmountFrom( double p_amountFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from = p_amountFrom;
    amount_from_is_set = true;
    amount_from_is_modified = true;
  }


  /** 
   * <em>amount_to</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_amountTo <em>amount_to</em>�J�����̒l������킷13���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public final void setAmountTo( double p_amountTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to = new Double(p_amountTo);
    amount_to_is_set = true;
    amount_to_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>amount_to</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAmountTo( Double p_amountTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to = p_amountTo;
    amount_to_is_set = true;
    amount_to_is_modified = true;
  }


  /** 
   * <em>commision_rate</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_commisionRate <em>commision_rate</em>�J�����̒l������킷8���ȉ���double�l�ŁA���̐��x��5���܂�
   */
  public final void setCommisionRate( double p_commisionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commision_rate = new Double(p_commisionRate);
    commision_rate_is_set = true;
    commision_rate_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>commision_rate</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCommisionRate( Double p_commisionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commision_rate = p_commisionRate;
    commision_rate_is_set = true;
    commision_rate_is_modified = true;
  }


  /** 
   * <em>add_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_addAmount <em>add_amount</em>�J�����̒l������킷14���ȉ���double�l�ŁA���̐��x��5���܂�
   */
  public final void setAddAmount( double p_addAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    add_amount = new Double(p_addAmount);
    add_amount_is_set = true;
    add_amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>add_amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAddAmount( Double p_addAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    add_amount = p_addAmount;
    add_amount_is_set = true;
    add_amount_is_modified = true;
  }


  /** 
   * <em>scale</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_scale <em>scale</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setScale( int p_scale )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    scale = p_scale;
    scale_is_set = true;
    scale_is_modified = true;
  }


  /** 
   * <em>round_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_roundDiv <em>round_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRoundDiv( String p_roundDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    round_div = p_roundDiv;
    round_div_is_set = true;
    round_div_is_modified = true;
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
   * <em>side_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sideDiv <em>side_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSideDiv( String p_sideDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    side_div = p_sideDiv;
    side_div_is_set = true;
    side_div_is_modified = true;
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
                else if ( name.equals("amount_from") ) {
                    return new Double( amount_from );
                }
                else if ( name.equals("amount_to") ) {
                    return this.amount_to;
                }
                else if ( name.equals("add_amount") ) {
                    return this.add_amount;
                }
                break;
            case 'c':
                if ( name.equals("cost_div") ) {
                    return this.cost_div;
                }
                else if ( name.equals("commision_rate") ) {
                    return this.commision_rate;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("offshore_product_code") ) {
                    return this.offshore_product_code;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 'r':
                if ( name.equals("round_div") ) {
                    return this.round_div;
                }
                break;
            case 's':
                if ( name.equals("scale") ) {
                    return new Integer( scale );
                }
                else if ( name.equals("side_div") ) {
                    return this.side_div;
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
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_date = (java.sql.Timestamp) value;
                    if (this.appli_end_date_is_set)
                        this.appli_end_date_is_modified = true;
                    this.appli_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'amount_from' must be Double: '"+value+"' is not." );
                    this.amount_from = ((Double) value).doubleValue();
                    if (this.amount_from_is_set)
                        this.amount_from_is_modified = true;
                    this.amount_from_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'amount_to' must be Double: '"+value+"' is not." );
                    this.amount_to = (Double) value;
                    if (this.amount_to_is_set)
                        this.amount_to_is_modified = true;
                    this.amount_to_is_set = true;
                    return;
                }
                else if ( name.equals("add_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'add_amount' must be Double: '"+value+"' is not." );
                    this.add_amount = (Double) value;
                    if (this.add_amount_is_set)
                        this.add_amount_is_modified = true;
                    this.add_amount_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cost_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cost_div' must be String: '"+value+"' is not." );
                    this.cost_div = (String) value;
                    if (this.cost_div_is_set)
                        this.cost_div_is_modified = true;
                    this.cost_div_is_set = true;
                    return;
                }
                else if ( name.equals("commision_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commision_rate' must be Double: '"+value+"' is not." );
                    this.commision_rate = (Double) value;
                    if (this.commision_rate_is_set)
                        this.commision_rate_is_modified = true;
                    this.commision_rate_is_set = true;
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
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
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
            case 'o':
                if ( name.equals("offshore_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'offshore_product_code' must be String: '"+value+"' is not." );
                    this.offshore_product_code = (String) value;
                    if (this.offshore_product_code_is_set)
                        this.offshore_product_code_is_modified = true;
                    this.offshore_product_code_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("round_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'round_div' must be String: '"+value+"' is not." );
                    this.round_div = (String) value;
                    if (this.round_div_is_set)
                        this.round_div_is_modified = true;
                    this.round_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("scale") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'scale' must be Integer: '"+value+"' is not." );
                    this.scale = ((Integer) value).intValue();
                    if (this.scale_is_set)
                        this.scale_is_modified = true;
                    this.scale_is_set = true;
                    return;
                }
                else if ( name.equals("side_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'side_div' must be String: '"+value+"' is not." );
                    this.side_div = (String) value;
                    if (this.side_div_is_set)
                        this.side_div_is_modified = true;
                    this.side_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
