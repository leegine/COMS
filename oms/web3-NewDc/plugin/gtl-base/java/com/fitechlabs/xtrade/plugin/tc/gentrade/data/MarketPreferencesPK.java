head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketPreferencesPK.java;


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
 * <b>MarketPreferences</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MarketPreferences</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MarketPreferences</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketPreferencesRow 
 */
public class MarketPreferencesPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "market_preferences";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MarketPreferencesRow. 
   */
  public RowType getRowType() {
    return MarketPreferencesRow.TYPE;
  }

  /**
   * <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long market_id;
  /**
   * <em>name</em>�J�����̒l������킷80���ȉ���String�l 
   */
  public String name;
  /**
   * <em>name_serial_no</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public int name_serial_no;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MarketPreferencesPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_name <em>name</em>�J�����̒l������킷80���ȉ���String�l 
   * @@param p_nameSerialNo <em>name_serial_no</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public MarketPreferencesPK( long p_marketId, String p_name, int p_nameSerialNo ) {
      market_id = p_marketId;
      name = p_name;
      name_serial_no = p_nameSerialNo;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MarketPreferencesPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MarketPreferencesPK pk = new MarketPreferencesPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.market_id = Long.valueOf(st.nextToken()).longValue();
    pk.name = st.nextToken();
    pk.name_serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(market_id) + "," + name + "," + String.valueOf(name_serial_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MarketPreferencesPK) )
      return false;
    MarketPreferencesPK o = (MarketPreferencesPK) other;
    if ( market_id != o.market_id )
      return false;
    if ( name == null ) {
      if ( o.name != null )
        return false;
    } else if ( !name.equals( o.name ) ) {
        return false;
    }
    if ( name_serial_no != o.name_serial_no )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) market_id)
        + (name!=null? name.hashCode(): 0) 
        + ((int) name_serial_no)
    ;
  }

}

@
