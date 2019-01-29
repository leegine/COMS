head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiLotInfoParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * srv_regi_lot_info�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SrvRegiLotInfoParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SrvRegiLotInfoParams}��{@@link SrvRegiLotInfoRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiLotInfoPK 
 * @@see SrvRegiLotInfoRow 
 */
public class SrvRegiLotInfoParams extends Params implements SrvRegiLotInfoRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "srv_regi_lot_info";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SrvRegiLotInfoRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SrvRegiLotInfoRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>srv_div</em>�J�����̒l 
   */
  public  String  srv_div;

  /** 
   * <em>consecutive_numbers</em>�J�����̒l 
   */
  public  long  consecutive_numbers;

  /** 
   * <em>appli_date_from</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_date_from;

  /** 
   * <em>appli_date_to</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_date_to;

  /** 
   * <em>lot_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  lot_date;

  /** 
   * <em>appli_start_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>appli_end_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>use_amt</em>�J�����̒l 
   */
  public  long  use_amt;

  /** 
   * <em>bidding_price</em>�J�����̒l 
   */
  public  Long  bidding_price;

  /** 
   * <em>payment_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  payment_date;

  /** 
   * <em>public_offering_qty</em>�J�����̒l 
   */
  public  Long  public_offering_qty;

  /** 
   * <em>invest_div</em>�J�����̒l 
   */
  public  String  invest_div;

  /** 
   * <em>high_success_bid</em>�J�����̒l 
   */
  public  Long  high_success_bid;

  /** 
   * <em>low_success_bid</em>�J�����̒l 
   */
  public  Long  low_success_bid;

  /** 
   * <em>average_success_bid</em>�J�����̒l 
   */
  public  Long  average_success_bid;

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
  private boolean srv_div_is_set = false;
  private boolean srv_div_is_modified = false;
  private boolean consecutive_numbers_is_set = false;
  private boolean consecutive_numbers_is_modified = false;
  private boolean appli_date_from_is_set = false;
  private boolean appli_date_from_is_modified = false;
  private boolean appli_date_to_is_set = false;
  private boolean appli_date_to_is_modified = false;
  private boolean lot_date_is_set = false;
  private boolean lot_date_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean use_amt_is_set = false;
  private boolean use_amt_is_modified = false;
  private boolean bidding_price_is_set = false;
  private boolean bidding_price_is_modified = false;
  private boolean payment_date_is_set = false;
  private boolean payment_date_is_modified = false;
  private boolean public_offering_qty_is_set = false;
  private boolean public_offering_qty_is_modified = false;
  private boolean invest_div_is_set = false;
  private boolean invest_div_is_modified = false;
  private boolean high_success_bid_is_set = false;
  private boolean high_success_bid_is_modified = false;
  private boolean low_success_bid_is_set = false;
  private boolean low_success_bid_is_modified = false;
  private boolean average_success_bid_is_set = false;
  private boolean average_success_bid_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
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
      + "," + "srv_div=" + srv_div
      + "," + "consecutive_numbers=" + consecutive_numbers
      + "," + "appli_date_from=" +appli_date_from
      + "," + "appli_date_to=" +appli_date_to
      + "," + "lot_date=" +lot_date
      + "," + "appli_start_date=" +appli_start_date
      + "," + "appli_end_date=" +appli_end_date
      + "," + "use_amt=" +use_amt
      + "," + "bidding_price=" +bidding_price
      + "," + "payment_date=" +payment_date
      + "," + "public_offering_qty=" +public_offering_qty
      + "," + "invest_div=" +invest_div
      + "," + "high_success_bid=" +high_success_bid
      + "," + "low_success_bid=" +low_success_bid
      + "," + "average_success_bid=" +average_success_bid
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��SrvRegiLotInfoParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SrvRegiLotInfoParams() {
    institution_code_is_modified = true;
    srv_div_is_modified = true;
    consecutive_numbers_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SrvRegiLotInfoRow�I�u�W�F�N�g�̒l�𗘗p����SrvRegiLotInfoParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SrvRegiLotInfoRow�I�u�W�F�N�g 
   */
  public SrvRegiLotInfoParams( SrvRegiLotInfoRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    srv_div = p_row.getSrvDiv();
    srv_div_is_set = p_row.getSrvDivIsSet();
    srv_div_is_modified = p_row.getSrvDivIsModified();
    consecutive_numbers = p_row.getConsecutiveNumbers();
    consecutive_numbers_is_set = p_row.getConsecutiveNumbersIsSet();
    consecutive_numbers_is_modified = p_row.getConsecutiveNumbersIsModified();
    appli_date_from = p_row.getAppliDateFrom();
    appli_date_from_is_set = p_row.getAppliDateFromIsSet();
    appli_date_from_is_modified = p_row.getAppliDateFromIsModified();
    appli_date_to = p_row.getAppliDateTo();
    appli_date_to_is_set = p_row.getAppliDateToIsSet();
    appli_date_to_is_modified = p_row.getAppliDateToIsModified();
    lot_date = p_row.getLotDate();
    lot_date_is_set = p_row.getLotDateIsSet();
    lot_date_is_modified = p_row.getLotDateIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    use_amt = p_row.getUseAmt();
    use_amt_is_set = p_row.getUseAmtIsSet();
    use_amt_is_modified = p_row.getUseAmtIsModified();
    if ( !p_row.getBiddingPriceIsNull() )
      bidding_price = new Long( p_row.getBiddingPrice() );
    bidding_price_is_set = p_row.getBiddingPriceIsSet();
    bidding_price_is_modified = p_row.getBiddingPriceIsModified();
    payment_date = p_row.getPaymentDate();
    payment_date_is_set = p_row.getPaymentDateIsSet();
    payment_date_is_modified = p_row.getPaymentDateIsModified();
    if ( !p_row.getPublicOfferingQtyIsNull() )
      public_offering_qty = new Long( p_row.getPublicOfferingQty() );
    public_offering_qty_is_set = p_row.getPublicOfferingQtyIsSet();
    public_offering_qty_is_modified = p_row.getPublicOfferingQtyIsModified();
    invest_div = p_row.getInvestDiv();
    invest_div_is_set = p_row.getInvestDivIsSet();
    invest_div_is_modified = p_row.getInvestDivIsModified();
    if ( !p_row.getHighSuccessBidIsNull() )
      high_success_bid = new Long( p_row.getHighSuccessBid() );
    high_success_bid_is_set = p_row.getHighSuccessBidIsSet();
    high_success_bid_is_modified = p_row.getHighSuccessBidIsModified();
    if ( !p_row.getLowSuccessBidIsNull() )
      low_success_bid = new Long( p_row.getLowSuccessBid() );
    low_success_bid_is_set = p_row.getLowSuccessBidIsSet();
    low_success_bid_is_modified = p_row.getLowSuccessBidIsModified();
    if ( !p_row.getAverageSuccessBidIsNull() )
      average_success_bid = new Long( p_row.getAverageSuccessBid() );
    average_success_bid_is_set = p_row.getAverageSuccessBidIsSet();
    average_success_bid_is_modified = p_row.getAverageSuccessBidIsModified();
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
      appli_date_from_is_set = true;
      appli_date_from_is_modified = true;
      appli_date_to_is_set = true;
      appli_date_to_is_modified = true;
      lot_date_is_set = true;
      lot_date_is_modified = true;
      appli_start_date_is_set = true;
      appli_start_date_is_modified = true;
      appli_end_date_is_set = true;
      appli_end_date_is_modified = true;
      use_amt_is_set = true;
      use_amt_is_modified = true;
      bidding_price_is_set = true;
      bidding_price_is_modified = true;
      payment_date_is_set = true;
      payment_date_is_modified = true;
      public_offering_qty_is_set = true;
      public_offering_qty_is_modified = true;
      invest_div_is_set = true;
      invest_div_is_modified = true;
      high_success_bid_is_set = true;
      high_success_bid_is_modified = true;
      low_success_bid_is_set = true;
      low_success_bid_is_modified = true;
      average_success_bid_is_set = true;
      average_success_bid_is_modified = true;
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
    if ( !( other instanceof SrvRegiLotInfoRow ) )
       return false;
    return fieldsEqual( (SrvRegiLotInfoRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SrvRegiLotInfoRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( SrvRegiLotInfoRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( row.getSrvDiv() != null )
        return false;
    } else if ( !srv_div.equals( row.getSrvDiv() ) ) {
        return false;
    }
    if ( consecutive_numbers != row.getConsecutiveNumbers() )
      return false;
    if ( appli_date_from == null ) {
      if ( row.getAppliDateFrom() != null )
        return false;
    } else if ( !appli_date_from.equals( row.getAppliDateFrom() ) ) {
        return false;
    }
    if ( appli_date_to == null ) {
      if ( row.getAppliDateTo() != null )
        return false;
    } else if ( !appli_date_to.equals( row.getAppliDateTo() ) ) {
        return false;
    }
    if ( lot_date == null ) {
      if ( row.getLotDate() != null )
        return false;
    } else if ( !lot_date.equals( row.getLotDate() ) ) {
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
    if ( use_amt != row.getUseAmt() )
      return false;
    if ( bidding_price == null ) {
      if ( !row.getBiddingPriceIsNull() )
        return false;
    } else if ( row.getBiddingPriceIsNull() || ( bidding_price.longValue() != row.getBiddingPrice() ) ) {
        return false;
    }
    if ( payment_date == null ) {
      if ( row.getPaymentDate() != null )
        return false;
    } else if ( !payment_date.equals( row.getPaymentDate() ) ) {
        return false;
    }
    if ( public_offering_qty == null ) {
      if ( !row.getPublicOfferingQtyIsNull() )
        return false;
    } else if ( row.getPublicOfferingQtyIsNull() || ( public_offering_qty.longValue() != row.getPublicOfferingQty() ) ) {
        return false;
    }
    if ( invest_div == null ) {
      if ( row.getInvestDiv() != null )
        return false;
    } else if ( !invest_div.equals( row.getInvestDiv() ) ) {
        return false;
    }
    if ( high_success_bid == null ) {
      if ( !row.getHighSuccessBidIsNull() )
        return false;
    } else if ( row.getHighSuccessBidIsNull() || ( high_success_bid.longValue() != row.getHighSuccessBid() ) ) {
        return false;
    }
    if ( low_success_bid == null ) {
      if ( !row.getLowSuccessBidIsNull() )
        return false;
    } else if ( row.getLowSuccessBidIsNull() || ( low_success_bid.longValue() != row.getLowSuccessBid() ) ) {
        return false;
    }
    if ( average_success_bid == null ) {
      if ( !row.getAverageSuccessBidIsNull() )
        return false;
    } else if ( row.getAverageSuccessBidIsNull() || ( average_success_bid.longValue() != row.getAverageSuccessBid() ) ) {
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
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + ((int) consecutive_numbers)
        + (appli_date_from!=null? appli_date_from.hashCode(): 0) 
        + (appli_date_to!=null? appli_date_to.hashCode(): 0) 
        + (lot_date!=null? lot_date.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + ((int) use_amt)
        + (bidding_price!=null? bidding_price.hashCode(): 0) 
        + (payment_date!=null? payment_date.hashCode(): 0) 
        + (public_offering_qty!=null? public_offering_qty.hashCode(): 0) 
        + (invest_div!=null? invest_div.hashCode(): 0) 
        + (high_success_bid!=null? high_success_bid.hashCode(): 0) 
        + (low_success_bid!=null? low_success_bid.hashCode(): 0) 
        + (average_success_bid!=null? average_success_bid.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !appli_date_from_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_date_from' must be set before inserting.");
		if ( !appli_date_to_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_date_to' must be set before inserting.");
		if ( !appli_start_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_start_date' must be set before inserting.");
		if ( !appli_end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_date' must be set before inserting.");
		if ( !invest_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'invest_div' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("srv_div",srv_div);
		map.put("consecutive_numbers",new Long(consecutive_numbers));
		map.put("appli_date_from",appli_date_from);
		map.put("appli_date_to",appli_date_to);
		if ( lot_date != null )
			map.put("lot_date",lot_date);
		map.put("appli_start_date",appli_start_date);
		map.put("appli_end_date",appli_end_date);
		if ( use_amt_is_set )
			map.put("use_amt",new Long(use_amt));
		if ( bidding_price != null )
			map.put("bidding_price",bidding_price);
		if ( payment_date != null )
			map.put("payment_date",payment_date);
		if ( public_offering_qty != null )
			map.put("public_offering_qty",public_offering_qty);
		map.put("invest_div",invest_div);
		if ( high_success_bid != null )
			map.put("high_success_bid",high_success_bid);
		if ( low_success_bid != null )
			map.put("low_success_bid",low_success_bid);
		if ( average_success_bid != null )
			map.put("average_success_bid",average_success_bid);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( appli_date_from_is_modified )
			map.put("appli_date_from",appli_date_from);
		if ( appli_date_to_is_modified )
			map.put("appli_date_to",appli_date_to);
		if ( lot_date_is_modified )
			map.put("lot_date",lot_date);
		if ( appli_start_date_is_modified )
			map.put("appli_start_date",appli_start_date);
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( use_amt_is_modified )
			map.put("use_amt",new Long(use_amt));
		if ( bidding_price_is_modified )
			map.put("bidding_price",bidding_price);
		if ( payment_date_is_modified )
			map.put("payment_date",payment_date);
		if ( public_offering_qty_is_modified )
			map.put("public_offering_qty",public_offering_qty);
		if ( invest_div_is_modified )
			map.put("invest_div",invest_div);
		if ( high_success_bid_is_modified )
			map.put("high_success_bid",high_success_bid);
		if ( low_success_bid_is_modified )
			map.put("low_success_bid",low_success_bid);
		if ( average_success_bid_is_modified )
			map.put("average_success_bid",average_success_bid);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( appli_date_from_is_set )
				map.put("appli_date_from",appli_date_from);
			if ( appli_date_to_is_set )
				map.put("appli_date_to",appli_date_to);
			map.put("lot_date",lot_date);
			if ( appli_start_date_is_set )
				map.put("appli_start_date",appli_start_date);
			if ( appli_end_date_is_set )
				map.put("appli_end_date",appli_end_date);
			if ( use_amt_is_set )
				map.put("use_amt",new Long(use_amt));
			map.put("bidding_price",bidding_price);
			map.put("payment_date",payment_date);
			map.put("public_offering_qty",public_offering_qty);
			if ( invest_div_is_set )
				map.put("invest_div",invest_div);
			map.put("high_success_bid",high_success_bid);
			map.put("low_success_bid",low_success_bid);
			map.put("average_success_bid",average_success_bid);
			if ( last_updater_is_set )
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
   * <em>srv_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSrvDiv()
  {
    return srv_div;
  }


  /** 
   * <em>srv_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvDivIsSet() {
    return srv_div_is_set;
  }


  /** 
   * <em>srv_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSrvDivIsModified() {
    return srv_div_is_modified;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getConsecutiveNumbers()
  {
    return consecutive_numbers;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsecutiveNumbersIsSet() {
    return consecutive_numbers_is_set;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConsecutiveNumbersIsModified() {
    return consecutive_numbers_is_modified;
  }


  /** 
   * <em>appli_date_from</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliDateFrom()
  {
    return appli_date_from;
  }


  /** 
   * <em>appli_date_from</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliDateFromIsSet() {
    return appli_date_from_is_set;
  }


  /** 
   * <em>appli_date_from</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliDateFromIsModified() {
    return appli_date_from_is_modified;
  }


  /** 
   * <em>appli_date_to</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getAppliDateTo()
  {
    return appli_date_to;
  }


  /** 
   * <em>appli_date_to</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliDateToIsSet() {
    return appli_date_to_is_set;
  }


  /** 
   * <em>appli_date_to</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAppliDateToIsModified() {
    return appli_date_to_is_modified;
  }


  /** 
   * <em>lot_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLotDate()
  {
    return lot_date;
  }


  /** 
   * <em>lot_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotDateIsSet() {
    return lot_date_is_set;
  }


  /** 
   * <em>lot_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLotDateIsModified() {
    return lot_date_is_modified;
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
   * <em>use_amt</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getUseAmt()
  {
    return use_amt;
  }


  /** 
   * <em>use_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUseAmtIsSet() {
    return use_amt_is_set;
  }


  /** 
   * <em>use_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUseAmtIsModified() {
    return use_amt_is_modified;
  }


  /** 
   * <em>bidding_price</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBiddingPrice()
  {
    return ( bidding_price==null? ((long)0): bidding_price.longValue() );
  }


  /** 
   * <em>bidding_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBiddingPriceIsNull()
  {
    return bidding_price == null;
  }


  /** 
   * <em>bidding_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBiddingPriceIsSet() {
    return bidding_price_is_set;
  }


  /** 
   * <em>bidding_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBiddingPriceIsModified() {
    return bidding_price_is_modified;
  }


  /** 
   * <em>payment_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getPaymentDate()
  {
    return payment_date;
  }


  /** 
   * <em>payment_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDateIsSet() {
    return payment_date_is_set;
  }


  /** 
   * <em>payment_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaymentDateIsModified() {
    return payment_date_is_modified;
  }


  /** 
   * <em>public_offering_qty</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getPublicOfferingQty()
  {
    return ( public_offering_qty==null? ((long)0): public_offering_qty.longValue() );
  }


  /** 
   * <em>public_offering_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getPublicOfferingQtyIsNull()
  {
    return public_offering_qty == null;
  }


  /** 
   * <em>public_offering_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingQtyIsSet() {
    return public_offering_qty_is_set;
  }


  /** 
   * <em>public_offering_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPublicOfferingQtyIsModified() {
    return public_offering_qty_is_modified;
  }


  /** 
   * <em>invest_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInvestDiv()
  {
    return invest_div;
  }


  /** 
   * <em>invest_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInvestDivIsSet() {
    return invest_div_is_set;
  }


  /** 
   * <em>invest_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInvestDivIsModified() {
    return invest_div_is_modified;
  }


  /** 
   * <em>high_success_bid</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getHighSuccessBid()
  {
    return ( high_success_bid==null? ((long)0): high_success_bid.longValue() );
  }


  /** 
   * <em>high_success_bid</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getHighSuccessBidIsNull()
  {
    return high_success_bid == null;
  }


  /** 
   * <em>high_success_bid</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHighSuccessBidIsSet() {
    return high_success_bid_is_set;
  }


  /** 
   * <em>high_success_bid</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHighSuccessBidIsModified() {
    return high_success_bid_is_modified;
  }


  /** 
   * <em>low_success_bid</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getLowSuccessBid()
  {
    return ( low_success_bid==null? ((long)0): low_success_bid.longValue() );
  }


  /** 
   * <em>low_success_bid</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLowSuccessBidIsNull()
  {
    return low_success_bid == null;
  }


  /** 
   * <em>low_success_bid</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLowSuccessBidIsSet() {
    return low_success_bid_is_set;
  }


  /** 
   * <em>low_success_bid</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLowSuccessBidIsModified() {
    return low_success_bid_is_modified;
  }


  /** 
   * <em>average_success_bid</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAverageSuccessBid()
  {
    return ( average_success_bid==null? ((long)0): average_success_bid.longValue() );
  }


  /** 
   * <em>average_success_bid</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAverageSuccessBidIsNull()
  {
    return average_success_bid == null;
  }


  /** 
   * <em>average_success_bid</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAverageSuccessBidIsSet() {
    return average_success_bid_is_set;
  }


  /** 
   * <em>average_success_bid</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAverageSuccessBidIsModified() {
    return average_success_bid_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SrvRegiLotInfoPK(institution_code, srv_div, consecutive_numbers);
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
   * <em>srv_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_srvDiv <em>srv_div</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setSrvDiv( String p_srvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_div = p_srvDiv;
    srv_div_is_set = true;
    srv_div_is_modified = true;
  }


  /** 
   * <em>consecutive_numbers</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_consecutiveNumbers <em>consecutive_numbers</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setConsecutiveNumbers( long p_consecutiveNumbers )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    consecutive_numbers = p_consecutiveNumbers;
    consecutive_numbers_is_set = true;
    consecutive_numbers_is_modified = true;
  }


  /** 
   * <em>appli_date_from</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliDateFrom <em>appli_date_from</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliDateFrom( java.sql.Timestamp p_appliDateFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_from = p_appliDateFrom;
    appli_date_from_is_set = true;
    appli_date_from_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_date_from</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliDateFrom( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_from = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_date_from_is_set = true;
    appli_date_from_is_modified = true;
  }


  /** 
   * <em>appli_date_to</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_appliDateTo <em>appli_date_to</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setAppliDateTo( java.sql.Timestamp p_appliDateTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_to = p_appliDateTo;
    appli_date_to_is_set = true;
    appli_date_to_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>appli_date_to</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setAppliDateTo( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_to = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_date_to_is_set = true;
    appli_date_to_is_modified = true;
  }


  /** 
   * <em>lot_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lotDate <em>lot_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLotDate( java.sql.Timestamp p_lotDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_date = p_lotDate;
    lot_date_is_set = true;
    lot_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>lot_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLotDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    lot_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    lot_date_is_set = true;
    lot_date_is_modified = true;
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
   * <em>use_amt</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_useAmt <em>use_amt</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setUseAmt( long p_useAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    use_amt = p_useAmt;
    use_amt_is_set = true;
    use_amt_is_modified = true;
  }


  /** 
   * <em>bidding_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_biddingPrice <em>bidding_price</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBiddingPrice( long p_biddingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bidding_price = new Long(p_biddingPrice);
    bidding_price_is_set = true;
    bidding_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>bidding_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBiddingPrice( Long p_biddingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bidding_price = p_biddingPrice;
    bidding_price_is_set = true;
    bidding_price_is_modified = true;
  }


  /** 
   * <em>payment_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paymentDate <em>payment_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setPaymentDate( java.sql.Timestamp p_paymentDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = p_paymentDate;
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>payment_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setPaymentDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


  /** 
   * <em>public_offering_qty</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_publicOfferingQty <em>public_offering_qty</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setPublicOfferingQty( long p_publicOfferingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_qty = new Long(p_publicOfferingQty);
    public_offering_qty_is_set = true;
    public_offering_qty_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>public_offering_qty</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setPublicOfferingQty( Long p_publicOfferingQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    public_offering_qty = p_publicOfferingQty;
    public_offering_qty_is_set = true;
    public_offering_qty_is_modified = true;
  }


  /** 
   * <em>invest_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_investDiv <em>invest_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setInvestDiv( String p_investDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    invest_div = p_investDiv;
    invest_div_is_set = true;
    invest_div_is_modified = true;
  }


  /** 
   * <em>high_success_bid</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_highSuccessBid <em>high_success_bid</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setHighSuccessBid( long p_highSuccessBid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    high_success_bid = new Long(p_highSuccessBid);
    high_success_bid_is_set = true;
    high_success_bid_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>high_success_bid</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setHighSuccessBid( Long p_highSuccessBid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    high_success_bid = p_highSuccessBid;
    high_success_bid_is_set = true;
    high_success_bid_is_modified = true;
  }


  /** 
   * <em>low_success_bid</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lowSuccessBid <em>low_success_bid</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setLowSuccessBid( long p_lowSuccessBid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    low_success_bid = new Long(p_lowSuccessBid);
    low_success_bid_is_set = true;
    low_success_bid_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>low_success_bid</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLowSuccessBid( Long p_lowSuccessBid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    low_success_bid = p_lowSuccessBid;
    low_success_bid_is_set = true;
    low_success_bid_is_modified = true;
  }


  /** 
   * <em>average_success_bid</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_averageSuccessBid <em>average_success_bid</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAverageSuccessBid( long p_averageSuccessBid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    average_success_bid = new Long(p_averageSuccessBid);
    average_success_bid_is_set = true;
    average_success_bid_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>average_success_bid</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAverageSuccessBid( Long p_averageSuccessBid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    average_success_bid = p_averageSuccessBid;
    average_success_bid_is_set = true;
    average_success_bid_is_modified = true;
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
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("appli_date_from") ) {
                    return this.appli_date_from;
                }
                else if ( name.equals("appli_date_to") ) {
                    return this.appli_date_to;
                }
                else if ( name.equals("appli_start_date") ) {
                    return this.appli_start_date;
                }
                else if ( name.equals("appli_end_date") ) {
                    return this.appli_end_date;
                }
                else if ( name.equals("average_success_bid") ) {
                    return this.average_success_bid;
                }
                break;
            case 'b':
                if ( name.equals("bidding_price") ) {
                    return this.bidding_price;
                }
                break;
            case 'c':
                if ( name.equals("consecutive_numbers") ) {
                    return new Long( consecutive_numbers );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'h':
                if ( name.equals("high_success_bid") ) {
                    return this.high_success_bid;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("invest_div") ) {
                    return this.invest_div;
                }
                break;
            case 'l':
                if ( name.equals("lot_date") ) {
                    return this.lot_date;
                }
                else if ( name.equals("low_success_bid") ) {
                    return this.low_success_bid;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("payment_date") ) {
                    return this.payment_date;
                }
                else if ( name.equals("public_offering_qty") ) {
                    return this.public_offering_qty;
                }
                break;
            case 's':
                if ( name.equals("srv_div") ) {
                    return this.srv_div;
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    return new Long( use_amt );
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
                if ( name.equals("appli_date_from") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_date_from' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_date_from = (java.sql.Timestamp) value;
                    if (this.appli_date_from_is_set)
                        this.appli_date_from_is_modified = true;
                    this.appli_date_from_is_set = true;
                    return;
                }
                else if ( name.equals("appli_date_to") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_date_to' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_date_to = (java.sql.Timestamp) value;
                    if (this.appli_date_to_is_set)
                        this.appli_date_to_is_modified = true;
                    this.appli_date_to_is_set = true;
                    return;
                }
                else if ( name.equals("appli_start_date") ) {
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
                else if ( name.equals("average_success_bid") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'average_success_bid' must be Long: '"+value+"' is not." );
                    this.average_success_bid = (Long) value;
                    if (this.average_success_bid_is_set)
                        this.average_success_bid_is_modified = true;
                    this.average_success_bid_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("bidding_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bidding_price' must be Long: '"+value+"' is not." );
                    this.bidding_price = (Long) value;
                    if (this.bidding_price_is_set)
                        this.bidding_price_is_modified = true;
                    this.bidding_price_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("consecutive_numbers") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'consecutive_numbers' must be Long: '"+value+"' is not." );
                    this.consecutive_numbers = ((Long) value).longValue();
                    if (this.consecutive_numbers_is_set)
                        this.consecutive_numbers_is_modified = true;
                    this.consecutive_numbers_is_set = true;
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
            case 'h':
                if ( name.equals("high_success_bid") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'high_success_bid' must be Long: '"+value+"' is not." );
                    this.high_success_bid = (Long) value;
                    if (this.high_success_bid_is_set)
                        this.high_success_bid_is_modified = true;
                    this.high_success_bid_is_set = true;
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
                else if ( name.equals("invest_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'invest_div' must be String: '"+value+"' is not." );
                    this.invest_div = (String) value;
                    if (this.invest_div_is_set)
                        this.invest_div_is_modified = true;
                    this.invest_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("lot_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'lot_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.lot_date = (java.sql.Timestamp) value;
                    if (this.lot_date_is_set)
                        this.lot_date_is_modified = true;
                    this.lot_date_is_set = true;
                    return;
                }
                else if ( name.equals("low_success_bid") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'low_success_bid' must be Long: '"+value+"' is not." );
                    this.low_success_bid = (Long) value;
                    if (this.low_success_bid_is_set)
                        this.low_success_bid_is_modified = true;
                    this.low_success_bid_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
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
            case 'p':
                if ( name.equals("payment_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'payment_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.payment_date = (java.sql.Timestamp) value;
                    if (this.payment_date_is_set)
                        this.payment_date_is_modified = true;
                    this.payment_date_is_set = true;
                    return;
                }
                else if ( name.equals("public_offering_qty") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'public_offering_qty' must be Long: '"+value+"' is not." );
                    this.public_offering_qty = (Long) value;
                    if (this.public_offering_qty_is_set)
                        this.public_offering_qty_is_modified = true;
                    this.public_offering_qty_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("srv_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_div' must be String: '"+value+"' is not." );
                    this.srv_div = (String) value;
                    if (this.srv_div_is_set)
                        this.srv_div_is_modified = true;
                    this.srv_div_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'use_amt' must be Long: '"+value+"' is not." );
                    this.use_amt = ((Long) value).longValue();
                    if (this.use_amt_is_set)
                        this.use_amt_is_modified = true;
                    this.use_amt_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
