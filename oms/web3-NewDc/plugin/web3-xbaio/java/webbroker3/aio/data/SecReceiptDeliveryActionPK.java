head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.37.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	SecReceiptDeliveryActionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * <b>SecReceiptDeliveryAction</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>SecReceiptDeliveryAction</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>SecReceiptDeliveryAction</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecReceiptDeliveryActionRow 
 */
public class SecReceiptDeliveryActionPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "sec_receipt_delivery_action";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: SecReceiptDeliveryActionRow. 
   */
  public RowType getRowType() {
    return SecReceiptDeliveryActionRow.TYPE;
  }

  /**
   * <em>sec_receipt_delivery_action_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long sec_receipt_delivery_action_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public SecReceiptDeliveryActionPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_secReceiptDeliveryActionId <em>sec_receipt_delivery_action_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public SecReceiptDeliveryActionPK( long p_secReceiptDeliveryActionId ) {
      sec_receipt_delivery_action_id = p_secReceiptDeliveryActionId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static SecReceiptDeliveryActionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SecReceiptDeliveryActionPK pk = new SecReceiptDeliveryActionPK();
    pk.sec_receipt_delivery_action_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(sec_receipt_delivery_action_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SecReceiptDeliveryActionPK) )
      return false;
    SecReceiptDeliveryActionPK o = (SecReceiptDeliveryActionPK) other;
    if ( sec_receipt_delivery_action_id != o.sec_receipt_delivery_action_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) sec_receipt_delivery_action_id)
    ;
  }

}

@