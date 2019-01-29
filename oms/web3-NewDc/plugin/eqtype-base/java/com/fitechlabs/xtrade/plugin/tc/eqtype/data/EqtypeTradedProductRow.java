head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeTradedProductRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * EqtypeTradedProductRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>eqtype_traded_product</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link EqtypeTradedProductRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeTradedProductPK 
 */
public interface EqtypeTradedProductRow extends Row {


  /** 
   * ����{@@link EqtypeTradedProductRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "eqtype_traded_product", "master" );


  /** 
   * <em>traded_product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getTradedProductId();


  /** 
   * <em>traded_product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradedProductIdIsSet();


  /** 
   * <em>traded_product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTradedProductIdIsModified();


  /** 
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsModified();


  /** 
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarketId();


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsSet();


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsModified();


  /** 
   * <em>valid_until_biz_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getValidUntilBizDate();


  /** 
   * <em>valid_until_biz_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getValidUntilBizDateIsSet();


  /** 
   * <em>valid_until_biz_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getValidUntilBizDateIsModified();


  /** 
   * <em>list_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getListFlag();


  /** 
   * <em>list_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListFlagIsSet();


  /** 
   * <em>list_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListFlagIsModified();


  /** 
   * <em>list_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getListType();


  /** 
   * <em>list_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListTypeIsSet();


  /** 
   * <em>list_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListTypeIsModified();


  /** 
   * <em>new_list_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getNewListType();


  /** 
   * <em>new_list_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewListTypeIsSet();


  /** 
   * <em>new_list_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNewListTypeIsModified();


  /** 
   * <em>listed_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getListedDate();


  /** 
   * <em>listed_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListedDateIsSet();


  /** 
   * <em>listed_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getListedDateIsModified();


  /** 
   * <em>unlisted_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getUnlistedDate();


  /** 
   * <em>unlisted_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnlistedDateIsSet();


  /** 
   * <em>unlisted_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getUnlistedDateIsModified();


  /** 
   * <em>marginable_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMarginableFlag();


  /** 
   * <em>marginable_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginableFlagIsSet();


  /** 
   * <em>marginable_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginableFlagIsModified();


  /** 
   * <em>shortable_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getShortableFlag();


  /** 
   * <em>shortable_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortableFlagIsSet();


  /** 
   * <em>shortable_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortableFlagIsModified();


  /** 
   * <em>open_otc_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOpenOtcDiv();


  /** 
   * <em>open_otc_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenOtcDivIsSet();


  /** 
   * <em>open_otc_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOpenOtcDivIsModified();


  /** 
   * <em>margin_sys_product_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginSysProductType();


  /** 
   * <em>margin_sys_product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSysProductTypeIsSet();


  /** 
   * <em>margin_sys_product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSysProductTypeIsModified();


  /** 
   * <em>margin_gen_product_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginGenProductType();


  /** 
   * <em>margin_gen_product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginGenProductTypeIsSet();


  /** 
   * <em>margin_gen_product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginGenProductTypeIsModified();


  /** 
   * <em>mini_stock_can_dealt</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMiniStockCanDealt();


  /** 
   * <em>mini_stock_can_dealt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockCanDealtIsSet();


  /** 
   * <em>mini_stock_can_dealt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockCanDealtIsModified();


  /** 
   * <em>buy_cash_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getBuyCashStop();


  /** 
   * <em>buy_cash_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyCashStopIsNull();


  /** 
   * <em>buy_cash_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyCashStopIsSet();


  /** 
   * <em>buy_cash_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyCashStopIsModified();


  /** 
   * <em>sell_cash_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSellCashStop();


  /** 
   * <em>sell_cash_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellCashStopIsNull();


  /** 
   * <em>sell_cash_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellCashStopIsSet();


  /** 
   * <em>sell_cash_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellCashStopIsModified();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getBuySpotMarketOrdDesStop();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuySpotMarketOrdDesStopIsNull();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuySpotMarketOrdDesStopIsSet();


  /** 
   * <em>buy_spot_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuySpotMarketOrdDesStopIsModified();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSellSpotMarketOrdDesStop();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellSpotMarketOrdDesStopIsNull();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSpotMarketOrdDesStopIsSet();


  /** 
   * <em>sell_spot_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellSpotMarketOrdDesStopIsModified();


  /** 
   * <em>long_margin_sys_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongMarginSysStop();


  /** 
   * <em>long_margin_sys_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongMarginSysStopIsNull();


  /** 
   * <em>long_margin_sys_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMarginSysStopIsSet();


  /** 
   * <em>long_margin_sys_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMarginSysStopIsModified();


  /** 
   * <em>short_margin_sys_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortMarginSysStop();


  /** 
   * <em>short_margin_sys_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortMarginSysStopIsNull();


  /** 
   * <em>short_margin_sys_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginSysStopIsSet();


  /** 
   * <em>short_margin_sys_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginSysStopIsModified();


  /** 
   * <em>long_ms_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongMsMarketOrdDesStop();


  /** 
   * <em>long_ms_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongMsMarketOrdDesStopIsNull();


  /** 
   * <em>long_ms_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMsMarketOrdDesStopIsSet();


  /** 
   * <em>long_ms_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMsMarketOrdDesStopIsModified();


  /** 
   * <em>short_ms_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortMsMarketOrdDesStop();


  /** 
   * <em>short_ms_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortMsMarketOrdDesStopIsNull();


  /** 
   * <em>short_ms_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMsMarketOrdDesStopIsSet();


  /** 
   * <em>short_ms_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMsMarketOrdDesStopIsModified();


  /** 
   * <em>long_margin_gen_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongMarginGenStop();


  /** 
   * <em>long_margin_gen_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongMarginGenStopIsNull();


  /** 
   * <em>long_margin_gen_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMarginGenStopIsSet();


  /** 
   * <em>long_margin_gen_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMarginGenStopIsModified();


  /** 
   * <em>short_margin_gen_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortMarginGenStop();


  /** 
   * <em>short_margin_gen_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortMarginGenStopIsNull();


  /** 
   * <em>short_margin_gen_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginGenStopIsSet();


  /** 
   * <em>short_margin_gen_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginGenStopIsModified();


  /** 
   * <em>long_mg_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongMgMarketOrdDesStop();


  /** 
   * <em>long_mg_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongMgMarketOrdDesStopIsNull();


  /** 
   * <em>long_mg_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMgMarketOrdDesStopIsSet();


  /** 
   * <em>long_mg_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMgMarketOrdDesStopIsModified();


  /** 
   * <em>short_mg_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortMgMarketOrdDesStop();


  /** 
   * <em>short_mg_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortMgMarketOrdDesStopIsNull();


  /** 
   * <em>short_mg_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMgMarketOrdDesStopIsSet();


  /** 
   * <em>short_mg_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMgMarketOrdDesStopIsModified();


  /** 
   * <em>long_close_margin_sys_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongCloseMarginSysStop();


  /** 
   * <em>long_close_margin_sys_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongCloseMarginSysStopIsNull();


  /** 
   * <em>long_close_margin_sys_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCloseMarginSysStopIsSet();


  /** 
   * <em>long_close_margin_sys_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCloseMarginSysStopIsModified();


  /** 
   * <em>short_close_margin_sys_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortCloseMarginSysStop();


  /** 
   * <em>short_close_margin_sys_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortCloseMarginSysStopIsNull();


  /** 
   * <em>short_close_margin_sys_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCloseMarginSysStopIsSet();


  /** 
   * <em>short_close_margin_sys_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCloseMarginSysStopIsModified();


  /** 
   * <em>long_cms_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongCmsMarketOrdDesStop();


  /** 
   * <em>long_cms_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongCmsMarketOrdDesStopIsNull();


  /** 
   * <em>long_cms_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCmsMarketOrdDesStopIsSet();


  /** 
   * <em>long_cms_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCmsMarketOrdDesStopIsModified();


  /** 
   * <em>short_cms_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortCmsMarketOrdDesStop();


  /** 
   * <em>short_cms_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortCmsMarketOrdDesStopIsNull();


  /** 
   * <em>short_cms_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCmsMarketOrdDesStopIsSet();


  /** 
   * <em>short_cms_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCmsMarketOrdDesStopIsModified();


  /** 
   * <em>long_close_margin_gen_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongCloseMarginGenStop();


  /** 
   * <em>long_close_margin_gen_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongCloseMarginGenStopIsNull();


  /** 
   * <em>long_close_margin_gen_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCloseMarginGenStopIsSet();


  /** 
   * <em>long_close_margin_gen_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCloseMarginGenStopIsModified();


  /** 
   * <em>short_close_margin_gen_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortCloseMarginGenStop();


  /** 
   * <em>short_close_margin_gen_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortCloseMarginGenStopIsNull();


  /** 
   * <em>short_close_margin_gen_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCloseMarginGenStopIsSet();


  /** 
   * <em>short_close_margin_gen_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCloseMarginGenStopIsModified();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongCmgMarketOrdDesStop();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongCmgMarketOrdDesStopIsNull();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCmgMarketOrdDesStopIsSet();


  /** 
   * <em>long_cmg_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCmgMarketOrdDesStopIsModified();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortCmgMarketOrdDesStop();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortCmgMarketOrdDesStopIsNull();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCmgMarketOrdDesStopIsSet();


  /** 
   * <em>short_cmg_market_ord_des_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCmgMarketOrdDesStopIsModified();


  /** 
   * <em>long_swap_margin_sys_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongSwapMarginSysStop();


  /** 
   * <em>long_swap_margin_sys_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongSwapMarginSysStopIsNull();


  /** 
   * <em>long_swap_margin_sys_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongSwapMarginSysStopIsSet();


  /** 
   * <em>long_swap_margin_sys_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongSwapMarginSysStopIsModified();


  /** 
   * <em>short_swap_margin_sys_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortSwapMarginSysStop();


  /** 
   * <em>short_swap_margin_sys_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortSwapMarginSysStopIsNull();


  /** 
   * <em>short_swap_margin_sys_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortSwapMarginSysStopIsSet();


  /** 
   * <em>short_swap_margin_sys_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortSwapMarginSysStopIsModified();


  /** 
   * <em>long_swap_margin_gen_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getLongSwapMarginGenStop();


  /** 
   * <em>long_swap_margin_gen_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongSwapMarginGenStopIsNull();


  /** 
   * <em>long_swap_margin_gen_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongSwapMarginGenStopIsSet();


  /** 
   * <em>long_swap_margin_gen_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongSwapMarginGenStopIsModified();


  /** 
   * <em>short_swap_margin_gen_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortSwapMarginGenStop();


  /** 
   * <em>short_swap_margin_gen_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortSwapMarginGenStopIsNull();


  /** 
   * <em>short_swap_margin_gen_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortSwapMarginGenStopIsSet();


  /** 
   * <em>short_swap_margin_gen_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortSwapMarginGenStopIsModified();


  /** 
   * <em>buy_mini_stock_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getBuyMiniStockStop();


  /** 
   * <em>buy_mini_stock_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyMiniStockStopIsNull();


  /** 
   * <em>buy_mini_stock_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyMiniStockStopIsSet();


  /** 
   * <em>buy_mini_stock_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyMiniStockStopIsModified();


  /** 
   * <em>sell_mini_stock_stop</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSellMiniStockStop();


  /** 
   * <em>sell_mini_stock_stop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellMiniStockStopIsNull();


  /** 
   * <em>sell_mini_stock_stop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMiniStockStopIsSet();


  /** 
   * <em>sell_mini_stock_stop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMiniStockStopIsModified();


  /** 
   * <em>lot_size</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getLotSize();


  /** 
   * <em>lot_size</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotSizeIsSet();


  /** 
   * <em>lot_size</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLotSizeIsModified();


  /** 
   * <em>long_margin_deposit_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getLongMarginDepositRate();


  /** 
   * <em>long_margin_deposit_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongMarginDepositRateIsNull();


  /** 
   * <em>long_margin_deposit_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMarginDepositRateIsSet();


  /** 
   * <em>long_margin_deposit_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongMarginDepositRateIsModified();


  /** 
   * <em>short_margin_deposit_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getShortMarginDepositRate();


  /** 
   * <em>short_margin_deposit_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortMarginDepositRateIsNull();


  /** 
   * <em>short_margin_deposit_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginDepositRateIsSet();


  /** 
   * <em>short_margin_deposit_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginDepositRateIsModified();


  /** 
   * <em>long_cash_margin_deposit_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getLongCashMarginDepositRate();


  /** 
   * <em>long_cash_margin_deposit_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLongCashMarginDepositRateIsNull();


  /** 
   * <em>long_cash_margin_deposit_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCashMarginDepositRateIsSet();


  /** 
   * <em>long_cash_margin_deposit_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLongCashMarginDepositRateIsModified();


  /** 
   * <em>short_cash_margin_deposit_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getShortCashMarginDepositRate();


  /** 
   * <em>short_cash_margin_deposit_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortCashMarginDepositRateIsNull();


  /** 
   * <em>short_cash_margin_deposit_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCashMarginDepositRateIsSet();


  /** 
   * <em>short_cash_margin_deposit_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortCashMarginDepositRateIsModified();


  /** 
   * <em>last_closing_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getLastClosingPrice();


  /** 
   * <em>last_closing_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastClosingPriceIsSet();


  /** 
   * <em>last_closing_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastClosingPriceIsModified();


  /** 
   * <em>price_range_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPriceRangeType();


  /** 
   * <em>price_range_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceRangeTypeIsSet();


  /** 
   * <em>price_range_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceRangeTypeIsModified();


  /** 
   * <em>price_range_unit_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPriceRangeUnitType();


  /** 
   * <em>price_range_unit_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceRangeUnitTypeIsSet();


  /** 
   * <em>price_range_unit_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceRangeUnitTypeIsModified();


  /** 
   * <em>high_compulsive_price_range</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getHighCompulsivePriceRange();


  /** 
   * <em>high_compulsive_price_range</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getHighCompulsivePriceRangeIsNull();


  /** 
   * <em>high_compulsive_price_range</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHighCompulsivePriceRangeIsSet();


  /** 
   * <em>high_compulsive_price_range</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHighCompulsivePriceRangeIsModified();


  /** 
   * <em>low_compulsive_price_range</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getLowCompulsivePriceRange();


  /** 
   * <em>low_compulsive_price_range</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getLowCompulsivePriceRangeIsNull();


  /** 
   * <em>low_compulsive_price_range</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLowCompulsivePriceRangeIsSet();


  /** 
   * <em>low_compulsive_price_range</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLowCompulsivePriceRangeIsModified();


  /** 
   * <em>stop_high_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getStopHighPrice();


  /** 
   * <em>stop_high_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getStopHighPriceIsNull();


  /** 
   * <em>stop_high_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStopHighPriceIsSet();


  /** 
   * <em>stop_high_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStopHighPriceIsModified();


  /** 
   * <em>stop_low_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getStopLowPrice();


  /** 
   * <em>stop_low_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getStopLowPriceIsNull();


  /** 
   * <em>stop_low_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStopLowPriceIsSet();


  /** 
   * <em>stop_low_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStopLowPriceIsModified();


  /** 
   * <em>price_range_ratio</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPriceRangeRatio();


  /** 
   * <em>price_range_ratio</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPriceRangeRatioIsNull();


  /** 
   * <em>price_range_ratio</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceRangeRatioIsSet();


  /** 
   * <em>price_range_ratio</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceRangeRatioIsModified();


  /** 
   * <em>compulsive_limited_unit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getCompulsiveLimitedUnit();


  /** 
   * <em>compulsive_limited_unit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCompulsiveLimitedUnitIsNull();


  /** 
   * <em>compulsive_limited_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompulsiveLimitedUnitIsSet();


  /** 
   * <em>compulsive_limited_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCompulsiveLimitedUnitIsModified();


  /** 
   * <em>mini_stock_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMiniStockFlag();


  /** 
   * <em>mini_stock_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockFlagIsSet();


  /** 
   * <em>mini_stock_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMiniStockFlagIsModified();


  /** 
   * <em>today_dep_fund_reg</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getTodayDepFundReg();


  /** 
   * <em>today_dep_fund_reg</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRegIsSet();


  /** 
   * <em>today_dep_fund_reg</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTodayDepFundRegIsModified();


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsModified();


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>base_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getBasePrice();


  /** 
   * <em>base_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBasePriceIsSet();


  /** 
   * <em>base_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBasePriceIsModified();


}
@
