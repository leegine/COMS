head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleExchangeOrderKeyMngPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * <b>SleExchangeOrderKeyMng</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>SleExchangeOrderKeyMng</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>SleExchangeOrderKeyMng</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleExchangeOrderKeyMngRow 
 */
public class SleExchangeOrderKeyMngPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "sle_exchange_order_key_mng";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: SleExchangeOrderKeyMngRow. 
   */
  public RowType getRowType() {
    return SleExchangeOrderKeyMngRow.TYPE;
  }

  /**
   * <em>xblocks_product_type</em>�R�����̒l������킷6���ȉ���int�l 
   */
  public int xblocks_product_type;
  /**
   * <em>order_unit_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public long order_unit_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public SleExchangeOrderKeyMngPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_xblocksProductType <em>xblocks_product_type</em>�R�����̒l������킷6���ȉ���int�l 
   * @@param p_orderUnitId <em>order_unit_id</em>�R�����̒l������킷18���ȉ���long�l 
   */
  public SleExchangeOrderKeyMngPK( int p_xblocksProductType, long p_orderUnitId ) {
      xblocks_product_type = p_xblocksProductType;
      order_unit_id = p_orderUnitId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static SleExchangeOrderKeyMngPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SleExchangeOrderKeyMngPK pk = new SleExchangeOrderKeyMngPK();
    int i = pkValueString.indexOf(',');
    pk.xblocks_product_type = Integer.valueOf(pkValueString.substring(0,i)).intValue();
    pk.order_unit_id = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(xblocks_product_type) + "," + String.valueOf(order_unit_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SleExchangeOrderKeyMngPK) )
      return false;
    SleExchangeOrderKeyMngPK o = (SleExchangeOrderKeyMngPK) other;
    if ( xblocks_product_type != o.xblocks_product_type )
      return false;
    if ( order_unit_id != o.order_unit_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) xblocks_product_type)
        + ((int) order_unit_id)
    ;
  }

}

@
