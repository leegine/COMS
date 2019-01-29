head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	LimitPriceRangeDefsPK.java;


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
 * <b>LimitPriceRangeDefs</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>LimitPriceRangeDefs</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>LimitPriceRangeDefs</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LimitPriceRangeDefsRow 
 */
public class LimitPriceRangeDefsPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "limit_price_range_defs";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: LimitPriceRangeDefsRow. 
   */
  public RowType getRowType() {
    return LimitPriceRangeDefsRow.TYPE;
  }

  /**
   * <em>limit_price_range_defs_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long limit_price_range_defs_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public LimitPriceRangeDefsPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_limitPriceRangeDefsId <em>limit_price_range_defs_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public LimitPriceRangeDefsPK( long p_limitPriceRangeDefsId ) {
      limit_price_range_defs_id = p_limitPriceRangeDefsId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static LimitPriceRangeDefsPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    LimitPriceRangeDefsPK pk = new LimitPriceRangeDefsPK();
    pk.limit_price_range_defs_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(limit_price_range_defs_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof LimitPriceRangeDefsPK) )
      return false;
    LimitPriceRangeDefsPK o = (LimitPriceRangeDefsPK) other;
    if ( limit_price_range_defs_id != o.limit_price_range_defs_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) limit_price_range_defs_id)
    ;
  }

}

@