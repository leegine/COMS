head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingOrderActionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>IpoBookbuildingOrderAction</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>IpoBookbuildingOrderAction</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>IpoBookbuildingOrderAction</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoBookbuildingOrderActionRow 
 */
public class IpoBookbuildingOrderActionPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ipo_bookbuilding_order_action";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: IpoBookbuildingOrderActionRow. 
   */
  public RowType getRowType() {
    return IpoBookbuildingOrderActionRow.TYPE;
  }

  /**
   * <em>bookbuilding_order_action_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long bookbuilding_order_action_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public IpoBookbuildingOrderActionPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_bookbuildingOrderActionId <em>bookbuilding_order_action_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public IpoBookbuildingOrderActionPK( long p_bookbuildingOrderActionId ) {
      bookbuilding_order_action_id = p_bookbuildingOrderActionId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static IpoBookbuildingOrderActionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IpoBookbuildingOrderActionPK pk = new IpoBookbuildingOrderActionPK();
    pk.bookbuilding_order_action_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(bookbuilding_order_action_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IpoBookbuildingOrderActionPK) )
      return false;
    IpoBookbuildingOrderActionPK o = (IpoBookbuildingOrderActionPK) other;
    if ( bookbuilding_order_action_id != o.bookbuilding_order_action_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) bookbuilding_order_action_id)
    ;
  }

}

@
