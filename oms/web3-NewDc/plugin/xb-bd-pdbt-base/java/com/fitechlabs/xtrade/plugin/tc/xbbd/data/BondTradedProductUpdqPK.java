head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondTradedProductUpdqPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>BondTradedProductUpdq</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>BondTradedProductUpdq</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>BondTradedProductUpdq</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondTradedProductUpdqRow 
 */
public class BondTradedProductUpdqPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bond_traded_product_updq";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: BondTradedProductUpdqRow. 
   */
  public RowType getRowType() {
    return BondTradedProductUpdqRow.TYPE;
  }

  /**
   * <em>traded_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long traded_product_id;
  /**
   * <em>valid_for_biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public String valid_for_biz_date;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public BondTradedProductUpdqPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_validForBizDate <em>valid_for_biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public BondTradedProductUpdqPK( long p_tradedProductId, String p_validForBizDate ) {
      traded_product_id = p_tradedProductId;
      valid_for_biz_date = p_validForBizDate;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static BondTradedProductUpdqPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BondTradedProductUpdqPK pk = new BondTradedProductUpdqPK();
    int i = pkValueString.indexOf(',');
    pk.traded_product_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.valid_for_biz_date = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(traded_product_id) + "," + valid_for_biz_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BondTradedProductUpdqPK) )
      return false;
    BondTradedProductUpdqPK o = (BondTradedProductUpdqPK) other;
    if ( traded_product_id != o.traded_product_id )
      return false;
    if ( valid_for_biz_date == null ) {
      if ( o.valid_for_biz_date != null )
        return false;
    } else if ( !valid_for_biz_date.equals( o.valid_for_biz_date ) ) {
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
        + (valid_for_biz_date!=null? valid_for_biz_date.hashCode(): 0) 
    ;
  }

}

@
