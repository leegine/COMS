head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleExchangeOrderKeyMngRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * SleExchangeOrderKeyMngRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>sle_exchange_order_key_mng</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link SleExchangeOrderKeyMngRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleExchangeOrderKeyMngPK 
 */
public interface SleExchangeOrderKeyMngRow extends Row {


  /** 
   * ����{@@link SleExchangeOrderKeyMngRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "sle_exchange_order_key_mng", "session" );


  /** 
   * <em>xblocks_product_type</em>�R�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getXblocksProductType();


  /** 
   * <em>xblocks_product_type</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getXblocksProductTypeIsSet();


  /** 
   * <em>order_unit_id</em>�R�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderUnitId();


  /** 
   * <em>order_unit_id</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderUnitIdIsSet();


  /** 
   * <em>exchange_order_key</em>�R�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getExchangeOrderKey();


  /** 
   * <em>created_timestamp</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>�R�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>�R�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


}
@
