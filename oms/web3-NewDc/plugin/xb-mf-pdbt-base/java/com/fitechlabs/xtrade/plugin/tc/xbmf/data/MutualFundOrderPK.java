head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.13.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundOrderPK.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MutualFundOrder</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MutualFundOrder</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MutualFundOrder</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundOrderRow 
 */
public class MutualFundOrderPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mutual_fund_order";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MutualFundOrderRow. 
   */
  public RowType getRowType() {
    return MutualFundOrderRow.TYPE;
  }

  /**
   * <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long order_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MutualFundOrderPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_orderId <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public MutualFundOrderPK( long p_orderId ) {
      order_id = p_orderId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MutualFundOrderPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MutualFundOrderPK pk = new MutualFundOrderPK();
    pk.order_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(order_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MutualFundOrderPK) )
      return false;
    MutualFundOrderPK o = (MutualFundOrderPK) other;
    if ( order_id != o.order_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) order_id)
    ;
  }

}

@