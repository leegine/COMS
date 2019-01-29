head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.01.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqTradedProductUpdqParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * feq_traded_product_updq�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link FeqTradedProductUpdqRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link FeqTradedProductUpdqParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link FeqTradedProductUpdqParams}��{@@link FeqTradedProductUpdqRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqTradedProductUpdqPK 
 * @@see FeqTradedProductUpdqRow 
 */
public class FeqTradedProductUpdqParams extends Params implements FeqTradedProductUpdqRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "feq_traded_product_updq";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = FeqTradedProductUpdqRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return FeqTradedProductUpdqRow.TYPE;
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
   * <em>product_id</em>�J�����̒l 
   */
  public  long  product_id;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  long  market_id;

  /** 
   * <em>list_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  list_flag;

  /** 
   * <em>listed_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  listed_date;

  /** 
   * <em>unlisted_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  unlisted_date;

  /** 
   * <em>last_closing_price</em>�J�����̒l 
   */
  public  double  last_closing_price;

  /** 
   * <em>trade_stop</em>�J�����̒l 
   */
  public  Integer  trade_stop;

  /** 
   * <em>buy_stop</em>�J�����̒l 
   */
  public  Integer  buy_stop;

  /** 
   * <em>sell_stop</em>�J�����̒l 
   */
  public  Integer  sell_stop;

  /** 
   * <em>buy_lot_size</em>�J�����̒l 
   */
  public  double  buy_lot_size;

  /** 
   * <em>buy_min_qty</em>�J�����̒l 
   */
  public  double  buy_min_qty;

  /** 
   * <em>sell_lot_size</em>�J�����̒l 
   */
  public  double  sell_lot_size;

  /** 
   * <em>sell_min_qty</em>�J�����̒l 
   */
  public  double  sell_min_qty;

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

  private boolean traded_product_id_is_set = false;
  private boolean traded_product_id_is_modified = false;
  private boolean valid_for_biz_date_is_set = false;
  private boolean valid_for_biz_date_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean list_flag_is_set = false;
  private boolean list_flag_is_modified = false;
  private boolean listed_date_is_set = false;
  private boolean listed_date_is_modified = false;
  private boolean unlisted_date_is_set = false;
  private boolean unlisted_date_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean trade_stop_is_set = false;
  private boolean trade_stop_is_modified = false;
  private boolean buy_stop_is_set = false;
  private boolean buy_stop_is_modified = false;
  private boolean sell_stop_is_set = false;
  private boolean sell_stop_is_modified = false;
  private boolean buy_lot_size_is_set = false;
  private boolean buy_lot_size_is_modified = false;
  private boolean buy_min_qty_is_set = false;
  private boolean buy_min_qty_is_modified = false;
  private boolean sell_lot_size_is_set = false;
  private boolean sell_lot_size_is_modified = false;
  private boolean sell_min_qty_is_set = false;
  private boolean sell_min_qty_is_modified = false;
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
       + "traded_product_id=" + traded_product_id
      + "," + "valid_for_biz_date=" + valid_for_biz_date
      + "," + "institution_code=" +institution_code
      + "," + "product_id=" +product_id
      + "," + "market_id=" +market_id
      + "," + "list_flag=" +list_flag
      + "," + "listed_date=" +listed_date
      + "," + "unlisted_date=" +unlisted_date
      + "," + "last_closing_price=" +last_closing_price
      + "," + "trade_stop=" +trade_stop
      + "," + "buy_stop=" +buy_stop
      + "," + "sell_stop=" +sell_stop
      + "," + "buy_lot_size=" +buy_lot_size
      + "," + "buy_min_qty=" +buy_min_qty
      + "," + "sell_lot_size=" +sell_lot_size
      + "," + "sell_min_qty=" +sell_min_qty
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��FeqTradedProductUpdqParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public FeqTradedProductUpdqParams() {
    traded_product_id_is_modified = true;
    valid_for_biz_date_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���FeqTradedProductUpdqRow�I�u�W�F�N�g�̒l�𗘗p����FeqTradedProductUpdqParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����FeqTradedProductUpdqRow�I�u�W�F�N�g 
   */
  public FeqTradedProductUpdqParams( FeqTradedProductUpdqRow p_row )
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
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    list_flag = p_row.getListFlag();
    list_flag_is_set = p_row.getListFlagIsSet();
    list_flag_is_modified = p_row.getListFlagIsModified();
    listed_date = p_row.getListedDate();
    listed_date_is_set = p_row.getListedDateIsSet();
    listed_date_is_modified = p_row.getListedDateIsModified();
    unlisted_date = p_row.getUnlistedDate();
    unlisted_date_is_set = p_row.getUnlistedDateIsSet();
    unlisted_date_is_modified = p_row.getUnlistedDateIsModified();
    last_closing_price = p_row.getLastClosingPrice();
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    if ( !p_row.getTradeStopIsNull() )
      trade_stop = new Integer( p_row.getTradeStop() );
    trade_stop_is_set = p_row.getTradeStopIsSet();
    trade_stop_is_modified = p_row.getTradeStopIsModified();
    if ( !p_row.getBuyStopIsNull() )
      buy_stop = new Integer( p_row.getBuyStop() );
    buy_stop_is_set = p_row.getBuyStopIsSet();
    buy_stop_is_modified = p_row.getBuyStopIsModified();
    if ( !p_row.getSellStopIsNull() )
      sell_stop = new Integer( p_row.getSellStop() );
    sell_stop_is_set = p_row.getSellStopIsSet();
    sell_stop_is_modified = p_row.getSellStopIsModified();
    buy_lot_size = p_row.getBuyLotSize();
    buy_lot_size_is_set = p_row.getBuyLotSizeIsSet();
    buy_lot_size_is_modified = p_row.getBuyLotSizeIsModified();
    buy_min_qty = p_row.getBuyMinQty();
    buy_min_qty_is_set = p_row.getBuyMinQtyIsSet();
    buy_min_qty_is_modified = p_row.getBuyMinQtyIsModified();
    sell_lot_size = p_row.getSellLotSize();
    sell_lot_size_is_set = p_row.getSellLotSizeIsSet();
    sell_lot_size_is_modified = p_row.getSellLotSizeIsModified();
    sell_min_qty = p_row.getSellMinQty();
    sell_min_qty_is_set = p_row.getSellMinQtyIsSet();
    sell_min_qty_is_modified = p_row.getSellMinQtyIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      list_flag_is_set = true;
      list_flag_is_modified = true;
      listed_date_is_set = true;
      listed_date_is_modified = true;
      unlisted_date_is_set = true;
      unlisted_date_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      trade_stop_is_set = true;
      trade_stop_is_modified = true;
      buy_stop_is_set = true;
      buy_stop_is_modified = true;
      sell_stop_is_set = true;
      sell_stop_is_modified = true;
      buy_lot_size_is_set = true;
      buy_lot_size_is_modified = true;
      buy_min_qty_is_set = true;
      buy_min_qty_is_modified = true;
      sell_lot_size_is_set = true;
      sell_lot_size_is_modified = true;
      sell_min_qty_is_set = true;
      sell_min_qty_is_modified = true;
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
    if ( !( other instanceof FeqTradedProductUpdqRow ) )
       return false;
    return fieldsEqual( (FeqTradedProductUpdqRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�FeqTradedProductUpdqRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( FeqTradedProductUpdqRow row )
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
    if ( product_id != row.getProductId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( list_flag == null ) {
      if ( row.getListFlag() != null )
        return false;
    } else if ( !list_flag.equals( row.getListFlag() ) ) {
        return false;
    }
    if ( listed_date == null ) {
      if ( row.getListedDate() != null )
        return false;
    } else if ( !listed_date.equals( row.getListedDate() ) ) {
        return false;
    }
    if ( unlisted_date == null ) {
      if ( row.getUnlistedDate() != null )
        return false;
    } else if ( !unlisted_date.equals( row.getUnlistedDate() ) ) {
        return false;
    }
    if ( last_closing_price != row.getLastClosingPrice() )
      return false;
    if ( trade_stop == null ) {
      if ( !row.getTradeStopIsNull() )
        return false;
    } else if ( row.getTradeStopIsNull() || ( trade_stop.intValue() != row.getTradeStop() ) ) {
        return false;
    }
    if ( buy_stop == null ) {
      if ( !row.getBuyStopIsNull() )
        return false;
    } else if ( row.getBuyStopIsNull() || ( buy_stop.intValue() != row.getBuyStop() ) ) {
        return false;
    }
    if ( sell_stop == null ) {
      if ( !row.getSellStopIsNull() )
        return false;
    } else if ( row.getSellStopIsNull() || ( sell_stop.intValue() != row.getSellStop() ) ) {
        return false;
    }
    if ( buy_lot_size != row.getBuyLotSize() )
      return false;
    if ( buy_min_qty != row.getBuyMinQty() )
      return false;
    if ( sell_lot_size != row.getSellLotSize() )
      return false;
    if ( sell_min_qty != row.getSellMinQty() )
      return false;
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
      return  ((int) traded_product_id)
        + (valid_for_biz_date!=null? valid_for_biz_date.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) product_id)
        + ((int) market_id)
        + (list_flag!=null? list_flag.hashCode(): 0) 
        + (listed_date!=null? listed_date.hashCode(): 0) 
        + (unlisted_date!=null? unlisted_date.hashCode(): 0) 
        + ((int) last_closing_price)
        + (trade_stop!=null? trade_stop.hashCode(): 0) 
        + (buy_stop!=null? buy_stop.hashCode(): 0) 
        + (sell_stop!=null? sell_stop.hashCode(): 0) 
        + ((int) buy_lot_size)
        + ((int) buy_min_qty)
        + ((int) sell_lot_size)
        + ((int) sell_min_qty)
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
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("traded_product_id",new Long(traded_product_id));
		map.put("valid_for_biz_date",valid_for_biz_date);
		map.put("institution_code",institution_code);
		map.put("product_id",new Long(product_id));
		map.put("market_id",new Long(market_id));
		if ( list_flag_is_set )
			map.put("list_flag",list_flag);
		if ( listed_date != null )
			map.put("listed_date",listed_date);
		if ( unlisted_date != null )
			map.put("unlisted_date",unlisted_date);
		if ( last_closing_price_is_set )
			map.put("last_closing_price",new Double(last_closing_price));
		if ( trade_stop != null )
			map.put("trade_stop",trade_stop);
		if ( buy_stop != null )
			map.put("buy_stop",buy_stop);
		if ( sell_stop != null )
			map.put("sell_stop",sell_stop);
		if ( buy_lot_size_is_set )
			map.put("buy_lot_size",new Double(buy_lot_size));
		if ( buy_min_qty_is_set )
			map.put("buy_min_qty",new Double(buy_min_qty));
		if ( sell_lot_size_is_set )
			map.put("sell_lot_size",new Double(sell_lot_size));
		if ( sell_min_qty_is_set )
			map.put("sell_min_qty",new Double(sell_min_qty));
		if ( last_updater != null )
			map.put("last_updater",last_updater);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( list_flag_is_modified )
			map.put("list_flag",list_flag);
		if ( listed_date_is_modified )
			map.put("listed_date",listed_date);
		if ( unlisted_date_is_modified )
			map.put("unlisted_date",unlisted_date);
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",new Double(last_closing_price));
		if ( trade_stop_is_modified )
			map.put("trade_stop",trade_stop);
		if ( buy_stop_is_modified )
			map.put("buy_stop",buy_stop);
		if ( sell_stop_is_modified )
			map.put("sell_stop",sell_stop);
		if ( buy_lot_size_is_modified )
			map.put("buy_lot_size",new Double(buy_lot_size));
		if ( buy_min_qty_is_modified )
			map.put("buy_min_qty",new Double(buy_min_qty));
		if ( sell_lot_size_is_modified )
			map.put("sell_lot_size",new Double(sell_lot_size));
		if ( sell_min_qty_is_modified )
			map.put("sell_min_qty",new Double(sell_min_qty));
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( list_flag_is_set )
				map.put("list_flag",list_flag);
			map.put("listed_date",listed_date);
			map.put("unlisted_date",unlisted_date);
			if ( last_closing_price_is_set )
				map.put("last_closing_price",new Double(last_closing_price));
			map.put("trade_stop",trade_stop);
			map.put("buy_stop",buy_stop);
			map.put("sell_stop",sell_stop);
			if ( buy_lot_size_is_set )
				map.put("buy_lot_size",new Double(buy_lot_size));
			if ( buy_min_qty_is_set )
				map.put("buy_min_qty",new Double(buy_min_qty));
			if ( sell_lot_size_is_set )
				map.put("sell_lot_size",new Double(sell_lot_size));
			if ( sell_min_qty_is_set )
				map.put("sell_min_qty",new Double(sell_min_qty));
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>list_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getListFlag()
  {
    return list_flag;
  }


  /** 
   * <em>list_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getListFlagIsSet() {
    return list_flag_is_set;
  }


  /** 
   * <em>list_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getListFlagIsModified() {
    return list_flag_is_modified;
  }


  /** 
   * <em>listed_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getListedDate()
  {
    return listed_date;
  }


  /** 
   * <em>listed_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getListedDateIsSet() {
    return listed_date_is_set;
  }


  /** 
   * <em>listed_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getListedDateIsModified() {
    return listed_date_is_modified;
  }


  /** 
   * <em>unlisted_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getUnlistedDate()
  {
    return unlisted_date;
  }


  /** 
   * <em>unlisted_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnlistedDateIsSet() {
    return unlisted_date_is_set;
  }


  /** 
   * <em>unlisted_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUnlistedDateIsModified() {
    return unlisted_date_is_modified;
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLastClosingPrice()
  {
    return last_closing_price;
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
   * <em>trade_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTradeStop()
  {
    return ( trade_stop==null? ((int)0): trade_stop.intValue() );
  }


  /** 
   * <em>trade_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getTradeStopIsNull()
  {
    return trade_stop == null;
  }


  /** 
   * <em>trade_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeStopIsSet() {
    return trade_stop_is_set;
  }


  /** 
   * <em>trade_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeStopIsModified() {
    return trade_stop_is_modified;
  }


  /** 
   * <em>buy_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getBuyStop()
  {
    return ( buy_stop==null? ((int)0): buy_stop.intValue() );
  }


  /** 
   * <em>buy_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBuyStopIsNull()
  {
    return buy_stop == null;
  }


  /** 
   * <em>buy_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyStopIsSet() {
    return buy_stop_is_set;
  }


  /** 
   * <em>buy_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyStopIsModified() {
    return buy_stop_is_modified;
  }


  /** 
   * <em>sell_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getSellStop()
  {
    return ( sell_stop==null? ((int)0): sell_stop.intValue() );
  }


  /** 
   * <em>sell_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSellStopIsNull()
  {
    return sell_stop == null;
  }


  /** 
   * <em>sell_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellStopIsSet() {
    return sell_stop_is_set;
  }


  /** 
   * <em>sell_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellStopIsModified() {
    return sell_stop_is_modified;
  }


  /** 
   * <em>buy_lot_size</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getBuyLotSize()
  {
    return buy_lot_size;
  }


  /** 
   * <em>buy_lot_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyLotSizeIsSet() {
    return buy_lot_size_is_set;
  }


  /** 
   * <em>buy_lot_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyLotSizeIsModified() {
    return buy_lot_size_is_modified;
  }


  /** 
   * <em>buy_min_qty</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getBuyMinQty()
  {
    return buy_min_qty;
  }


  /** 
   * <em>buy_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyMinQtyIsSet() {
    return buy_min_qty_is_set;
  }


  /** 
   * <em>buy_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyMinQtyIsModified() {
    return buy_min_qty_is_modified;
  }


  /** 
   * <em>sell_lot_size</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSellLotSize()
  {
    return sell_lot_size;
  }


  /** 
   * <em>sell_lot_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellLotSizeIsSet() {
    return sell_lot_size_is_set;
  }


  /** 
   * <em>sell_lot_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellLotSizeIsModified() {
    return sell_lot_size_is_modified;
  }


  /** 
   * <em>sell_min_qty</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getSellMinQty()
  {
    return sell_min_qty;
  }


  /** 
   * <em>sell_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellMinQtyIsSet() {
    return sell_min_qty_is_set;
  }


  /** 
   * <em>sell_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellMinQtyIsModified() {
    return sell_min_qty_is_modified;
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
    return new FeqTradedProductUpdqPK(traded_product_id, valid_for_biz_date);
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
   * <em>list_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_listFlag <em>list_flag</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setListFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_listFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    list_flag = p_listFlag;
    list_flag_is_set = true;
    list_flag_is_modified = true;
  }


  /** 
   * <em>listed_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_listedDate <em>listed_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setListedDate( java.sql.Timestamp p_listedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    listed_date = p_listedDate;
    listed_date_is_set = true;
    listed_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>listed_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setListedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    listed_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    listed_date_is_set = true;
    listed_date_is_modified = true;
  }


  /** 
   * <em>unlisted_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_unlistedDate <em>unlisted_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setUnlistedDate( java.sql.Timestamp p_unlistedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unlisted_date = p_unlistedDate;
    unlisted_date_is_set = true;
    unlisted_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>unlisted_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setUnlistedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unlisted_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    unlisted_date_is_set = true;
    unlisted_date_is_modified = true;
  }


  /** 
   * <em>last_closing_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastClosingPrice <em>last_closing_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLastClosingPrice( double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = p_lastClosingPrice;
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * <em>trade_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradeStop <em>trade_stop</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setTradeStop( int p_tradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop = new Integer(p_tradeStop);
    trade_stop_is_set = true;
    trade_stop_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>trade_stop</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setTradeStop( Integer p_tradeStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop = p_tradeStop;
    trade_stop_is_set = true;
    trade_stop_is_modified = true;
  }


  /** 
   * <em>buy_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyStop <em>buy_stop</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setBuyStop( int p_buyStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_stop = new Integer(p_buyStop);
    buy_stop_is_set = true;
    buy_stop_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>buy_stop</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBuyStop( Integer p_buyStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_stop = p_buyStop;
    buy_stop_is_set = true;
    buy_stop_is_modified = true;
  }


  /** 
   * <em>sell_stop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellStop <em>sell_stop</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setSellStop( int p_sellStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_stop = new Integer(p_sellStop);
    sell_stop_is_set = true;
    sell_stop_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sell_stop</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSellStop( Integer p_sellStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_stop = p_sellStop;
    sell_stop_is_set = true;
    sell_stop_is_modified = true;
  }


  /** 
   * <em>buy_lot_size</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyLotSize <em>buy_lot_size</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setBuyLotSize( double p_buyLotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_lot_size = p_buyLotSize;
    buy_lot_size_is_set = true;
    buy_lot_size_is_modified = true;
  }


  /** 
   * <em>buy_min_qty</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyMinQty <em>buy_min_qty</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setBuyMinQty( double p_buyMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_min_qty = p_buyMinQty;
    buy_min_qty_is_set = true;
    buy_min_qty_is_modified = true;
  }


  /** 
   * <em>sell_lot_size</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellLotSize <em>sell_lot_size</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSellLotSize( double p_sellLotSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_lot_size = p_sellLotSize;
    sell_lot_size_is_set = true;
    sell_lot_size_is_modified = true;
  }


  /** 
   * <em>sell_min_qty</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellMinQty <em>sell_min_qty</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setSellMinQty( double p_sellMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_qty = p_sellMinQty;
    sell_min_qty_is_set = true;
    sell_min_qty_is_modified = true;
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
            case 'b':
                if ( name.equals("buy_stop") ) {
                    return this.buy_stop;
                }
                else if ( name.equals("buy_lot_size") ) {
                    return new Double( buy_lot_size );
                }
                else if ( name.equals("buy_min_qty") ) {
                    return new Double( buy_min_qty );
                }
                break;
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
                if ( name.equals("list_flag") ) {
                    return this.list_flag;
                }
                else if ( name.equals("listed_date") ) {
                    return this.listed_date;
                }
                else if ( name.equals("last_closing_price") ) {
                    return new Double( last_closing_price );
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
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 's':
                if ( name.equals("sell_stop") ) {
                    return this.sell_stop;
                }
                else if ( name.equals("sell_lot_size") ) {
                    return new Double( sell_lot_size );
                }
                else if ( name.equals("sell_min_qty") ) {
                    return new Double( sell_min_qty );
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    return new Long( traded_product_id );
                }
                else if ( name.equals("trade_stop") ) {
                    return this.trade_stop;
                }
                break;
            case 'u':
                if ( name.equals("unlisted_date") ) {
                    return this.unlisted_date;
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
                if ( name.equals("buy_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_stop' must be Integer: '"+value+"' is not." );
                    this.buy_stop = (Integer) value;
                    if (this.buy_stop_is_set)
                        this.buy_stop_is_modified = true;
                    this.buy_stop_is_set = true;
                    return;
                }
                else if ( name.equals("buy_lot_size") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buy_lot_size' must be Double: '"+value+"' is not." );
                    this.buy_lot_size = ((Double) value).doubleValue();
                    if (this.buy_lot_size_is_set)
                        this.buy_lot_size_is_modified = true;
                    this.buy_lot_size_is_set = true;
                    return;
                }
                else if ( name.equals("buy_min_qty") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buy_min_qty' must be Double: '"+value+"' is not." );
                    this.buy_min_qty = ((Double) value).doubleValue();
                    if (this.buy_min_qty_is_set)
                        this.buy_min_qty_is_modified = true;
                    this.buy_min_qty_is_set = true;
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
                if ( name.equals("list_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'list_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.list_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.list_flag_is_set)
                        this.list_flag_is_modified = true;
                    this.list_flag_is_set = true;
                    return;
                }
                else if ( name.equals("listed_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'listed_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.listed_date = (java.sql.Timestamp) value;
                    if (this.listed_date_is_set)
                        this.listed_date_is_modified = true;
                    this.listed_date_is_set = true;
                    return;
                }
                else if ( name.equals("last_closing_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_closing_price' must be Double: '"+value+"' is not." );
                    this.last_closing_price = ((Double) value).doubleValue();
                    if (this.last_closing_price_is_set)
                        this.last_closing_price_is_modified = true;
                    this.last_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
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
            case 's':
                if ( name.equals("sell_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_stop' must be Integer: '"+value+"' is not." );
                    this.sell_stop = (Integer) value;
                    if (this.sell_stop_is_set)
                        this.sell_stop_is_modified = true;
                    this.sell_stop_is_set = true;
                    return;
                }
                else if ( name.equals("sell_lot_size") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sell_lot_size' must be Double: '"+value+"' is not." );
                    this.sell_lot_size = ((Double) value).doubleValue();
                    if (this.sell_lot_size_is_set)
                        this.sell_lot_size_is_modified = true;
                    this.sell_lot_size_is_set = true;
                    return;
                }
                else if ( name.equals("sell_min_qty") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sell_min_qty' must be Double: '"+value+"' is not." );
                    this.sell_min_qty = ((Double) value).doubleValue();
                    if (this.sell_min_qty_is_set)
                        this.sell_min_qty_is_modified = true;
                    this.sell_min_qty_is_set = true;
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
                else if ( name.equals("trade_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'trade_stop' must be Integer: '"+value+"' is not." );
                    this.trade_stop = (Integer) value;
                    if (this.trade_stop_is_set)
                        this.trade_stop_is_modified = true;
                    this.trade_stop_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unlisted_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'unlisted_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.unlisted_date = (java.sql.Timestamp) value;
                    if (this.unlisted_date_is_set)
                        this.unlisted_date_is_modified = true;
                    this.unlisted_date_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_for_biz_date") ) {
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
