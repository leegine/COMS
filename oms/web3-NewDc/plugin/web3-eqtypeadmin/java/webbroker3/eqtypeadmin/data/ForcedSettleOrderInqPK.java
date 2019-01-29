head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ForcedSettleOrderInqPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * <b>ForcedSettleOrderInq</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>ForcedSettleOrderInq</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>ForcedSettleOrderInq</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ForcedSettleOrderInqRow 
 */
public class ForcedSettleOrderInqPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "forced_settle_order_inq";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: ForcedSettleOrderInqRow. 
   */
  public RowType getRowType() {
    return ForcedSettleOrderInqRow.TYPE;
  }

  /**
   * <em>forced_settle_order_inq_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long forced_settle_order_inq_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public ForcedSettleOrderInqPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_forcedSettleOrderInqId <em>forced_settle_order_inq_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public ForcedSettleOrderInqPK( long p_forcedSettleOrderInqId ) {
      forced_settle_order_inq_id = p_forcedSettleOrderInqId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static ForcedSettleOrderInqPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    ForcedSettleOrderInqPK pk = new ForcedSettleOrderInqPK();
    pk.forced_settle_order_inq_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(forced_settle_order_inq_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof ForcedSettleOrderInqPK) )
      return false;
    ForcedSettleOrderInqPK o = (ForcedSettleOrderInqPK) other;
    if ( forced_settle_order_inq_id != o.forced_settle_order_inq_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) forced_settle_order_inq_id)
    ;
  }

}

@
