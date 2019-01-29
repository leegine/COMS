head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.12.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundTradedProductParams.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mutual_fund_traded_product�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MutualFundTradedProductRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MutualFundTradedProductParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MutualFundTradedProductParams}��{@@link MutualFundTradedProductRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundTradedProductPK 
 * @@see MutualFundTradedProductRow 
 */
public class MutualFundTradedProductParams extends Params implements MutualFundTradedProductRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mutual_fund_traded_product";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MutualFundTradedProductRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MutualFundTradedProductRow.TYPE;
  }


  /** 
   * <em>traded_product_id</em>�J�����̒l 
   */
  public  long  traded_product_id;

  /** 
   * <em>valid_for_biz_date</em>�J�����̒l 
   */
  public  String  valid_for_biz_date;

  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>order_close_time</em>�J�����̒l 
   */
  public  String  order_close_time;

  /** 
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  long  market_id;

  /** 
   * <em>last_closing_price</em>�J�����̒l 
   */
  public  Double  last_closing_price;

  /** 
   * <em>buy_possible_div</em>�J�����̒l 
   */
  public  String  buy_possible_div;

  /** 
   * <em>sell_possible_div</em>�J�����̒l 
   */
  public  String  sell_possible_div;

  /** 
   * <em>swt_possible_div</em>�J�����̒l 
   */
  public  String  swt_possible_div;

  /** 
   * <em>exec_date_shiftdays</em>�J�����̒l 
   */
  public  Integer  exec_date_shiftdays;

  /** 
   * <em>buy_undelivering_term</em>�J�����̒l 
   */
  public  Integer  buy_undelivering_term;

  /** 
   * <em>sell_undelivering_term</em>�J�����̒l 
   */
  public  Integer  sell_undelivering_term;

  /** 
   * <em>scram_biz_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  scram_biz_date;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>online_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  online_updated_timestamp;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>recruit_possible_div</em>�J�����̒l 
   */
  public  String  recruit_possible_div;

  private boolean traded_product_id_is_set = false;
  private boolean traded_product_id_is_modified = false;
  private boolean valid_for_biz_date_is_set = false;
  private boolean valid_for_biz_date_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean order_close_time_is_set = false;
  private boolean order_close_time_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean buy_possible_div_is_set = false;
  private boolean buy_possible_div_is_modified = false;
  private boolean sell_possible_div_is_set = false;
  private boolean sell_possible_div_is_modified = false;
  private boolean swt_possible_div_is_set = false;
  private boolean swt_possible_div_is_modified = false;
  private boolean exec_date_shiftdays_is_set = false;
  private boolean exec_date_shiftdays_is_modified = false;
  private boolean buy_undelivering_term_is_set = false;
  private boolean buy_undelivering_term_is_modified = false;
  private boolean sell_undelivering_term_is_set = false;
  private boolean sell_undelivering_term_is_modified = false;
  private boolean scram_biz_date_is_set = false;
  private boolean scram_biz_date_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean online_updated_timestamp_is_set = false;
  private boolean online_updated_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean recruit_possible_div_is_set = false;
  private boolean recruit_possible_div_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "traded_product_id=" + traded_product_id
      + "," + "valid_for_biz_date=" +valid_for_biz_date
      + "," + "institution_code=" +institution_code
      + "," + "order_close_time=" +order_close_time
      + "," + "product_id=" +product_id
      + "," + "market_id=" +market_id
      + "," + "last_closing_price=" +last_closing_price
      + "," + "buy_possible_div=" +buy_possible_div
      + "," + "sell_possible_div=" +sell_possible_div
      + "," + "swt_possible_div=" +swt_possible_div
      + "," + "exec_date_shiftdays=" +exec_date_shiftdays
      + "," + "buy_undelivering_term=" +buy_undelivering_term
      + "," + "sell_undelivering_term=" +sell_undelivering_term
      + "," + "scram_biz_date=" +scram_biz_date
      + "," + "last_updater=" +last_updater
      + "," + "online_updated_timestamp=" +online_updated_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "recruit_possible_div=" +recruit_possible_div
      + "}";
  }


  /** 
   * �l�����ݒ��MutualFundTradedProductParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MutualFundTradedProductParams() {
    traded_product_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MutualFundTradedProductRow�I�u�W�F�N�g�̒l�𗘗p����MutualFundTradedProductParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MutualFundTradedProductRow�I�u�W�F�N�g 
   */
  public MutualFundTradedProductParams( MutualFundTradedProductRow p_row )
  {
    traded_product_id = p_row.getTradedProductId();
    traded_product_id_is_set = p_row.getTradedProductIdIsSet();
    traded_product_id_is_modified = p_row.getTradedProductIdIsModified();
    valid_for_biz_date = p_row.getValidForBizDate();
    valid_for_biz_date_is_set = p_row.getValidForBizDateIsSet();
    valid_for_biz_date_is_modified = p_row.getValidForBizDateIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    order_close_time = p_row.getOrderCloseTime();
    order_close_time_is_set = p_row.getOrderCloseTimeIsSet();
    order_close_time_is_modified = p_row.getOrderCloseTimeIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    if ( !p_row.getLastClosingPriceIsNull() )
      last_closing_price = new Double( p_row.getLastClosingPrice() );
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    buy_possible_div = p_row.getBuyPossibleDiv();
    buy_possible_div_is_set = p_row.getBuyPossibleDivIsSet();
    buy_possible_div_is_modified = p_row.getBuyPossibleDivIsModified();
    sell_possible_div = p_row.getSellPossibleDiv();
    sell_possible_div_is_set = p_row.getSellPossibleDivIsSet();
    sell_possible_div_is_modified = p_row.getSellPossibleDivIsModified();
    swt_possible_div = p_row.getSwtPossibleDiv();
    swt_possible_div_is_set = p_row.getSwtPossibleDivIsSet();
    swt_possible_div_is_modified = p_row.getSwtPossibleDivIsModified();
    if ( !p_row.getExecDateShiftdaysIsNull() )
      exec_date_shiftdays = new Integer( p_row.getExecDateShiftdays() );
    exec_date_shiftdays_is_set = p_row.getExecDateShiftdaysIsSet();
    exec_date_shiftdays_is_modified = p_row.getExecDateShiftdaysIsModified();
    if ( !p_row.getBuyUndeliveringTermIsNull() )
      buy_undelivering_term = new Integer( p_row.getBuyUndeliveringTerm() );
    buy_undelivering_term_is_set = p_row.getBuyUndeliveringTermIsSet();
    buy_undelivering_term_is_modified = p_row.getBuyUndeliveringTermIsModified();
    if ( !p_row.getSellUndeliveringTermIsNull() )
      sell_undelivering_term = new Integer( p_row.getSellUndeliveringTerm() );
    sell_undelivering_term_is_set = p_row.getSellUndeliveringTermIsSet();
    sell_undelivering_term_is_modified = p_row.getSellUndeliveringTermIsModified();
    scram_biz_date = p_row.getScramBizDate();
    scram_biz_date_is_set = p_row.getScramBizDateIsSet();
    scram_biz_date_is_modified = p_row.getScramBizDateIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    online_updated_timestamp = p_row.getOnlineUpdatedTimestamp();
    online_updated_timestamp_is_set = p_row.getOnlineUpdatedTimestampIsSet();
    online_updated_timestamp_is_modified = p_row.getOnlineUpdatedTimestampIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    recruit_possible_div = p_row.getRecruitPossibleDiv();
    recruit_possible_div_is_set = p_row.getRecruitPossibleDivIsSet();
    recruit_possible_div_is_modified = p_row.getRecruitPossibleDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      valid_for_biz_date_is_set = true;
      valid_for_biz_date_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      order_close_time_is_set = true;
      order_close_time_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      buy_possible_div_is_set = true;
      buy_possible_div_is_modified = true;
      sell_possible_div_is_set = true;
      sell_possible_div_is_modified = true;
      swt_possible_div_is_set = true;
      swt_possible_div_is_modified = true;
      exec_date_shiftdays_is_set = true;
      exec_date_shiftdays_is_modified = true;
      buy_undelivering_term_is_set = true;
      buy_undelivering_term_is_modified = true;
      sell_undelivering_term_is_set = true;
      sell_undelivering_term_is_modified = true;
      scram_biz_date_is_set = true;
      scram_biz_date_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      online_updated_timestamp_is_set = true;
      online_updated_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      recruit_possible_div_is_set = true;
      recruit_possible_div_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MutualFundTradedProductRow ) )
       return false;
    return fieldsEqual( (MutualFundTradedProductRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MutualFundTradedProductRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MutualFundTradedProductRow row )
  {
    if ( traded_product_id != row.getTradedProductId() )
      return false;
    if ( valid_for_biz_date == null ) {
      if ( row.getValidForBizDate() != null )
        return false;
    } else if ( !valid_for_biz_date.equals( row.getValidForBizDate() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( order_close_time == null ) {
      if ( row.getOrderCloseTime() != null )
        return false;
    } else if ( !order_close_time.equals( row.getOrderCloseTime() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( last_closing_price == null ) {
      if ( !row.getLastClosingPriceIsNull() )
        return false;
    } else if ( row.getLastClosingPriceIsNull() || ( last_closing_price.doubleValue() != row.getLastClosingPrice() ) ) {
        return false;
    }
    if ( buy_possible_div == null ) {
      if ( row.getBuyPossibleDiv() != null )
        return false;
    } else if ( !buy_possible_div.equals( row.getBuyPossibleDiv() ) ) {
        return false;
    }
    if ( sell_possible_div == null ) {
      if ( row.getSellPossibleDiv() != null )
        return false;
    } else if ( !sell_possible_div.equals( row.getSellPossibleDiv() ) ) {
        return false;
    }
    if ( swt_possible_div == null ) {
      if ( row.getSwtPossibleDiv() != null )
        return false;
    } else if ( !swt_possible_div.equals( row.getSwtPossibleDiv() ) ) {
        return false;
    }
    if ( exec_date_shiftdays == null ) {
      if ( !row.getExecDateShiftdaysIsNull() )
        return false;
    } else if ( row.getExecDateShiftdaysIsNull() || ( exec_date_shiftdays.intValue() != row.getExecDateShiftdays() ) ) {
        return false;
    }
    if ( buy_undelivering_term == null ) {
      if ( !row.getBuyUndeliveringTermIsNull() )
        return false;
    } else if ( row.getBuyUndeliveringTermIsNull() || ( buy_undelivering_term.intValue() != row.getBuyUndeliveringTerm() ) ) {
        return false;
    }
    if ( sell_undelivering_term == null ) {
      if ( !row.getSellUndeliveringTermIsNull() )
        return false;
    } else if ( row.getSellUndeliveringTermIsNull() || ( sell_undelivering_term.intValue() != row.getSellUndeliveringTerm() ) ) {
        return false;
    }
    if ( scram_biz_date == null ) {
      if ( row.getScramBizDate() != null )
        return false;
    } else if ( !scram_biz_date.equals( row.getScramBizDate() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( online_updated_timestamp == null ) {
      if ( row.getOnlineUpdatedTimestamp() != null )
        return false;
    } else if ( !online_updated_timestamp.equals( row.getOnlineUpdatedTimestamp() ) ) {
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
    if ( recruit_possible_div == null ) {
      if ( row.getRecruitPossibleDiv() != null )
        return false;
    } else if ( !recruit_possible_div.equals( row.getRecruitPossibleDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) traded_product_id)
        + (valid_for_biz_date!=null? valid_for_biz_date.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (order_close_time!=null? order_close_time.hashCode(): 0) 
        + ((int) product_id)
        + ((int) market_id)
        + (last_closing_price!=null? last_closing_price.hashCode(): 0) 
        + (buy_possible_div!=null? buy_possible_div.hashCode(): 0) 
        + (sell_possible_div!=null? sell_possible_div.hashCode(): 0) 
        + (swt_possible_div!=null? swt_possible_div.hashCode(): 0) 
        + (exec_date_shiftdays!=null? exec_date_shiftdays.hashCode(): 0) 
        + (buy_undelivering_term!=null? buy_undelivering_term.hashCode(): 0) 
        + (sell_undelivering_term!=null? sell_undelivering_term.hashCode(): 0) 
        + (scram_biz_date!=null? scram_biz_date.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (online_updated_timestamp!=null? online_updated_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (recruit_possible_div!=null? recruit_possible_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
		if ( !online_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'online_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("traded_product_id",new Long(traded_product_id));
		if ( valid_for_biz_date != null )
			map.put("valid_for_biz_date",valid_for_biz_date);
		map.put("institution_code",institution_code);
		if ( order_close_time != null )
			map.put("order_close_time",order_close_time);
		map.put("product_id",new Long(product_id));
		map.put("market_id",new Long(market_id));
		if ( last_closing_price != null )
			map.put("last_closing_price",last_closing_price);
		if ( buy_possible_div != null )
			map.put("buy_possible_div",buy_possible_div);
		if ( sell_possible_div != null )
			map.put("sell_possible_div",sell_possible_div);
		if ( swt_possible_div != null )
			map.put("swt_possible_div",swt_possible_div);
		if ( exec_date_shiftdays != null )
			map.put("exec_date_shiftdays",exec_date_shiftdays);
		if ( buy_undelivering_term_is_set )
			map.put("buy_undelivering_term",buy_undelivering_term);
		if ( sell_undelivering_term_is_set )
			map.put("sell_undelivering_term",sell_undelivering_term);
		if ( scram_biz_date != null )
			map.put("scram_biz_date",scram_biz_date);
		map.put("last_updater",last_updater);
		map.put("online_updated_timestamp",online_updated_timestamp);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( recruit_possible_div != null )
			map.put("recruit_possible_div",recruit_possible_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( valid_for_biz_date_is_modified )
			map.put("valid_for_biz_date",valid_for_biz_date);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( order_close_time_is_modified )
			map.put("order_close_time",order_close_time);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",last_closing_price);
		if ( buy_possible_div_is_modified )
			map.put("buy_possible_div",buy_possible_div);
		if ( sell_possible_div_is_modified )
			map.put("sell_possible_div",sell_possible_div);
		if ( swt_possible_div_is_modified )
			map.put("swt_possible_div",swt_possible_div);
		if ( exec_date_shiftdays_is_modified )
			map.put("exec_date_shiftdays",exec_date_shiftdays);
		if ( buy_undelivering_term_is_modified )
			map.put("buy_undelivering_term",buy_undelivering_term);
		if ( sell_undelivering_term_is_modified )
			map.put("sell_undelivering_term",sell_undelivering_term);
		if ( scram_biz_date_is_modified )
			map.put("scram_biz_date",scram_biz_date);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( online_updated_timestamp_is_modified )
			map.put("online_updated_timestamp",online_updated_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( recruit_possible_div_is_modified )
			map.put("recruit_possible_div",recruit_possible_div);
		if (map.size() == 0) {
			map.put("valid_for_biz_date",valid_for_biz_date);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("order_close_time",order_close_time);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			map.put("last_closing_price",last_closing_price);
			map.put("buy_possible_div",buy_possible_div);
			map.put("sell_possible_div",sell_possible_div);
			map.put("swt_possible_div",swt_possible_div);
			map.put("exec_date_shiftdays",exec_date_shiftdays);
			if ( buy_undelivering_term_is_set )
				map.put("buy_undelivering_term",buy_undelivering_term);
			if ( sell_undelivering_term_is_set )
				map.put("sell_undelivering_term",sell_undelivering_term);
			map.put("scram_biz_date",scram_biz_date);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( online_updated_timestamp_is_set )
				map.put("online_updated_timestamp",online_updated_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("recruit_possible_div",recruit_possible_div);
		}
		return map;
	}


  /** 
   * <em>traded_product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTradedProductId()
  {
    return traded_product_id;
  }


  /** 
   * <em>traded_product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradedProductIdIsSet() {
    return traded_product_id_is_set;
  }


  /** 
   * <em>traded_product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradedProductIdIsModified() {
    return traded_product_id_is_modified;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getValidForBizDate()
  {
    return valid_for_biz_date;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidForBizDateIsSet() {
    return valid_for_biz_date_is_set;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getValidForBizDateIsModified() {
    return valid_for_biz_date_is_modified;
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
   * <em>order_close_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderCloseTime()
  {
    return order_close_time;
  }


  /** 
   * <em>order_close_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderCloseTimeIsSet() {
    return order_close_time_is_set;
  }


  /** 
   * <em>order_close_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderCloseTimeIsModified() {
    return order_close_time_is_modified;
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
   * <em>last_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLastClosingPrice()
  {
    return ( last_closing_price==null? ((double)0): last_closing_price.doubleValue() );
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLastClosingPriceIsNull()
  {
    return last_closing_price == null;
  }


  /** 
   * <em>last_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastClosingPriceIsSet() {
    return last_closing_price_is_set;
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastClosingPriceIsModified() {
    return last_closing_price_is_modified;
  }


  /** 
   * <em>buy_possible_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBuyPossibleDiv()
  {
    return buy_possible_div;
  }


  /** 
   * <em>buy_possible_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyPossibleDivIsSet() {
    return buy_possible_div_is_set;
  }


  /** 
   * <em>buy_possible_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyPossibleDivIsModified() {
    return buy_possible_div_is_modified;
  }


  /** 
   * <em>sell_possible_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSellPossibleDiv()
  {
    return sell_possible_div;
  }


  /** 
   * <em>sell_possible_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellPossibleDivIsSet() {
    return sell_possible_div_is_set;
  }


  /** 
   * <em>sell_possible_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellPossibleDivIsModified() {
    return sell_possible_div_is_modified;
  }


  /** 
   * <em>swt_possible_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSwtPossibleDiv()
  {
    return swt_possible_div;
  }


  /** 
   * <em>swt_possible_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtPossibleDivIsSet() {
    return swt_possible_div_is_set;
  }


  /** 
   * <em>swt_possible_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSwtPossibleDivIsModified() {
    return swt_possible_div_is_modified;
  }


  /** 
   * <em>exec_date_shiftdays</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getExecDateShiftdays()
  {
    return ( exec_date_shiftdays==null? ((int)0): exec_date_shiftdays.intValue() );
  }


  /** 
   * <em>exec_date_shiftdays</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getExecDateShiftdaysIsNull()
  {
    return exec_date_shiftdays == null;
  }


  /** 
   * <em>exec_date_shiftdays</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecDateShiftdaysIsSet() {
    return exec_date_shiftdays_is_set;
  }


  /** 
   * <em>exec_date_shiftdays</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getExecDateShiftdaysIsModified() {
    return exec_date_shiftdays_is_modified;
  }


  /** 
   * <em>buy_undelivering_term</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getBuyUndeliveringTerm()
  {
    return ( buy_undelivering_term==null? ((int)0): buy_undelivering_term.intValue() );
  }


  /** 
   * <em>buy_undelivering_term</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBuyUndeliveringTermIsNull()
  {
    return buy_undelivering_term == null;
  }


  /** 
   * <em>buy_undelivering_term</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyUndeliveringTermIsSet() {
    return buy_undelivering_term_is_set;
  }


  /** 
   * <em>buy_undelivering_term</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyUndeliveringTermIsModified() {
    return buy_undelivering_term_is_modified;
  }


  /** 
   * <em>sell_undelivering_term</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSellUndeliveringTerm()
  {
    return ( sell_undelivering_term==null? ((int)0): sell_undelivering_term.intValue() );
  }


  /** 
   * <em>sell_undelivering_term</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSellUndeliveringTermIsNull()
  {
    return sell_undelivering_term == null;
  }


  /** 
   * <em>sell_undelivering_term</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellUndeliveringTermIsSet() {
    return sell_undelivering_term_is_set;
  }


  /** 
   * <em>sell_undelivering_term</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellUndeliveringTermIsModified() {
    return sell_undelivering_term_is_modified;
  }


  /** 
   * <em>scram_biz_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getScramBizDate()
  {
    return scram_biz_date;
  }


  /** 
   * <em>scram_biz_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getScramBizDateIsSet() {
    return scram_biz_date_is_set;
  }


  /** 
   * <em>scram_biz_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getScramBizDateIsModified() {
    return scram_biz_date_is_modified;
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
   * <em>online_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getOnlineUpdatedTimestamp()
  {
    return online_updated_timestamp;
  }


  /** 
   * <em>online_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOnlineUpdatedTimestampIsSet() {
    return online_updated_timestamp_is_set;
  }


  /** 
   * <em>online_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOnlineUpdatedTimestampIsModified() {
    return online_updated_timestamp_is_modified;
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
   * <em>recruit_possible_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRecruitPossibleDiv()
  {
    return recruit_possible_div;
  }


  /** 
   * <em>recruit_possible_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitPossibleDivIsSet() {
    return recruit_possible_div_is_set;
  }


  /** 
   * <em>recruit_possible_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRecruitPossibleDivIsModified() {
    return recruit_possible_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MutualFundTradedProductPK(traded_product_id);
  }


  /** 
   * <em>traded_product_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTradedProductId( long p_tradedProductId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    traded_product_id = p_tradedProductId;
    traded_product_id_is_set = true;
    traded_product_id_is_modified = true;
  }


  /** 
   * <em>valid_for_biz_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_validForBizDate <em>valid_for_biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setValidForBizDate( String p_validForBizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_for_biz_date = p_validForBizDate;
    valid_for_biz_date_is_set = true;
    valid_for_biz_date_is_modified = true;
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
   * <em>order_close_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderCloseTime <em>order_close_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setOrderCloseTime( String p_orderCloseTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_close_time = p_orderCloseTime;
    order_close_time_is_set = true;
    order_close_time_is_modified = true;
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
   * <em>last_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastClosingPrice <em>last_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLastClosingPrice( double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = new Double(p_lastClosingPrice);
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>last_closing_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLastClosingPrice( Double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = p_lastClosingPrice;
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * <em>buy_possible_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyPossibleDiv <em>buy_possible_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBuyPossibleDiv( String p_buyPossibleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_possible_div = p_buyPossibleDiv;
    buy_possible_div_is_set = true;
    buy_possible_div_is_modified = true;
  }


  /** 
   * <em>sell_possible_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellPossibleDiv <em>sell_possible_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSellPossibleDiv( String p_sellPossibleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_possible_div = p_sellPossibleDiv;
    sell_possible_div_is_set = true;
    sell_possible_div_is_modified = true;
  }


  /** 
   * <em>swt_possible_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_swtPossibleDiv <em>swt_possible_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setSwtPossibleDiv( String p_swtPossibleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_possible_div = p_swtPossibleDiv;
    swt_possible_div_is_set = true;
    swt_possible_div_is_modified = true;
  }


  /** 
   * <em>exec_date_shiftdays</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_execDateShiftdays <em>exec_date_shiftdays</em>�J�����̒l������킷2���ȉ���int�l 
   */
  public final void setExecDateShiftdays( int p_execDateShiftdays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date_shiftdays = new Integer(p_execDateShiftdays);
    exec_date_shiftdays_is_set = true;
    exec_date_shiftdays_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>exec_date_shiftdays</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setExecDateShiftdays( Integer p_execDateShiftdays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date_shiftdays = p_execDateShiftdays;
    exec_date_shiftdays_is_set = true;
    exec_date_shiftdays_is_modified = true;
  }


  /** 
   * <em>buy_undelivering_term</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyUndeliveringTerm <em>buy_undelivering_term</em>�J�����̒l������킷2���ȉ���int�l 
   */
  public final void setBuyUndeliveringTerm( int p_buyUndeliveringTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_undelivering_term = new Integer(p_buyUndeliveringTerm);
    buy_undelivering_term_is_set = true;
    buy_undelivering_term_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>buy_undelivering_term</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBuyUndeliveringTerm( Integer p_buyUndeliveringTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_undelivering_term = p_buyUndeliveringTerm;
    buy_undelivering_term_is_set = true;
    buy_undelivering_term_is_modified = true;
  }


  /** 
   * <em>sell_undelivering_term</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellUndeliveringTerm <em>sell_undelivering_term</em>�J�����̒l������킷2���ȉ���int�l 
   */
  public final void setSellUndeliveringTerm( int p_sellUndeliveringTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_undelivering_term = new Integer(p_sellUndeliveringTerm);
    sell_undelivering_term_is_set = true;
    sell_undelivering_term_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sell_undelivering_term</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSellUndeliveringTerm( Integer p_sellUndeliveringTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_undelivering_term = p_sellUndeliveringTerm;
    sell_undelivering_term_is_set = true;
    sell_undelivering_term_is_modified = true;
  }


  /** 
   * <em>scram_biz_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_scramBizDate <em>scram_biz_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setScramBizDate( java.sql.Timestamp p_scramBizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    scram_biz_date = p_scramBizDate;
    scram_biz_date_is_set = true;
    scram_biz_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>scram_biz_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setScramBizDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    scram_biz_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    scram_biz_date_is_set = true;
    scram_biz_date_is_modified = true;
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
   * <em>online_updated_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_onlineUpdatedTimestamp <em>online_updated_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setOnlineUpdatedTimestamp( java.sql.Timestamp p_onlineUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = p_onlineUpdatedTimestamp;
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>online_updated_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setOnlineUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
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
   * <em>recruit_possible_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_recruitPossibleDiv <em>recruit_possible_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRecruitPossibleDiv( String p_recruitPossibleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    recruit_possible_div = p_recruitPossibleDiv;
    recruit_possible_div_is_set = true;
    recruit_possible_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("buy_possible_div") ) {
                    return this.buy_possible_div;
                }
                else if ( name.equals("buy_undelivering_term") ) {
                    return this.buy_undelivering_term;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("exec_date_shiftdays") ) {
                    return this.exec_date_shiftdays;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_closing_price") ) {
                    return this.last_closing_price;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'o':
                if ( name.equals("order_close_time") ) {
                    return this.order_close_time;
                }
                else if ( name.equals("online_updated_timestamp") ) {
                    return this.online_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 'r':
                if ( name.equals("recruit_possible_div") ) {
                    return this.recruit_possible_div;
                }
                break;
            case 's':
                if ( name.equals("sell_possible_div") ) {
                    return this.sell_possible_div;
                }
                else if ( name.equals("swt_possible_div") ) {
                    return this.swt_possible_div;
                }
                else if ( name.equals("sell_undelivering_term") ) {
                    return this.sell_undelivering_term;
                }
                else if ( name.equals("scram_biz_date") ) {
                    return this.scram_biz_date;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    return new Long( traded_product_id );
                }
                break;
            case 'v':
                if ( name.equals("valid_for_biz_date") ) {
                    return this.valid_for_biz_date;
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
                if ( name.equals("buy_possible_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_possible_div' must be String: '"+value+"' is not." );
                    this.buy_possible_div = (String) value;
                    if (this.buy_possible_div_is_set)
                        this.buy_possible_div_is_modified = true;
                    this.buy_possible_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_undelivering_term") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_undelivering_term' must be Integer: '"+value+"' is not." );
                    this.buy_undelivering_term = (Integer) value;
                    if (this.buy_undelivering_term_is_set)
                        this.buy_undelivering_term_is_modified = true;
                    this.buy_undelivering_term_is_set = true;
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
                if ( name.equals("exec_date_shiftdays") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'exec_date_shiftdays' must be Integer: '"+value+"' is not." );
                    this.exec_date_shiftdays = (Integer) value;
                    if (this.exec_date_shiftdays_is_set)
                        this.exec_date_shiftdays_is_modified = true;
                    this.exec_date_shiftdays_is_set = true;
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
                if ( name.equals("last_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_closing_price' must be Double: '"+value+"' is not." );
                    this.last_closing_price = (Double) value;
                    if (this.last_closing_price_is_set)
                        this.last_closing_price_is_modified = true;
                    this.last_closing_price_is_set = true;
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
                break;
            case 'o':
                if ( name.equals("order_close_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_close_time' must be String: '"+value+"' is not." );
                    this.order_close_time = (String) value;
                    if (this.order_close_time_is_set)
                        this.order_close_time_is_modified = true;
                    this.order_close_time_is_set = true;
                    return;
                }
                else if ( name.equals("online_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'online_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.online_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.online_updated_timestamp_is_set)
                        this.online_updated_timestamp_is_modified = true;
                    this.online_updated_timestamp_is_set = true;
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
                if ( name.equals("recruit_possible_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'recruit_possible_div' must be String: '"+value+"' is not." );
                    this.recruit_possible_div = (String) value;
                    if (this.recruit_possible_div_is_set)
                        this.recruit_possible_div_is_modified = true;
                    this.recruit_possible_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sell_possible_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_possible_div' must be String: '"+value+"' is not." );
                    this.sell_possible_div = (String) value;
                    if (this.sell_possible_div_is_set)
                        this.sell_possible_div_is_modified = true;
                    this.sell_possible_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_possible_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_possible_div' must be String: '"+value+"' is not." );
                    this.swt_possible_div = (String) value;
                    if (this.swt_possible_div_is_set)
                        this.swt_possible_div_is_modified = true;
                    this.swt_possible_div_is_set = true;
                    return;
                }
                else if ( name.equals("sell_undelivering_term") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_undelivering_term' must be Integer: '"+value+"' is not." );
                    this.sell_undelivering_term = (Integer) value;
                    if (this.sell_undelivering_term_is_set)
                        this.sell_undelivering_term_is_modified = true;
                    this.sell_undelivering_term_is_set = true;
                    return;
                }
                else if ( name.equals("scram_biz_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'scram_biz_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.scram_biz_date = (java.sql.Timestamp) value;
                    if (this.scram_biz_date_is_set)
                        this.scram_biz_date_is_modified = true;
                    this.scram_biz_date_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'traded_product_id' must be Long: '"+value+"' is not." );
                    this.traded_product_id = ((Long) value).longValue();
                    if (this.traded_product_id_is_set)
                        this.traded_product_id_is_modified = true;
                    this.traded_product_id_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_for_biz_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_for_biz_date' must be String: '"+value+"' is not." );
                    this.valid_for_biz_date = (String) value;
                    if (this.valid_for_biz_date_is_set)
                        this.valid_for_biz_date_is_modified = true;
                    this.valid_for_biz_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
