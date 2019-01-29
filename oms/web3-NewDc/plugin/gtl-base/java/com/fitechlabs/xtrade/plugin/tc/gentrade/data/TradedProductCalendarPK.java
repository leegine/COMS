head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TradedProductCalendarPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>TradedProductCalendar</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>TradedProductCalendar</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>TradedProductCalendar</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradedProductCalendarRow 
 */
public class TradedProductCalendarPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "traded_product_calendar";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: TradedProductCalendarRow. 
   */
  public RowType getRowType() {
    return TradedProductCalendarRow.TYPE;
  }

  /**
   * <em>traded_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long traded_product_id;
  /**
   * <em>trade_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp trade_date;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public TradedProductCalendarPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_tradeDate <em>trade_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public TradedProductCalendarPK( long p_tradedProductId, java.sql.Timestamp p_tradeDate ) {
      traded_product_id = p_tradedProductId;
      trade_date = p_tradeDate;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static TradedProductCalendarPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TradedProductCalendarPK pk = new TradedProductCalendarPK();
    int i = pkValueString.indexOf(',');
    pk.traded_product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.trade_date = java.sql.Timestamp.valueOf(pkValueString.substring(i+1));
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(traded_product_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(trade_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TradedProductCalendarPK) )
      return false;
    TradedProductCalendarPK o = (TradedProductCalendarPK) other;
    if ( traded_product_id != o.traded_product_id )
      return false;
    if ( trade_date == null ) {
      if ( o.trade_date != null )
        return false;
    } else if ( !trade_date.equals( o.trade_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) traded_product_id)
        + (trade_date!=null? trade_date.hashCode(): 0) 
    ;
  }

}

@
