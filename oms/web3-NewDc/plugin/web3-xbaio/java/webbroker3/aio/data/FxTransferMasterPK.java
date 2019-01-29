head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.39.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FxTransferMasterPK.java;


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
 * <b>FxTransferMaster</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>FxTransferMaster</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>FxTransferMaster</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FxTransferMasterRow 
 */
public class FxTransferMasterPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "fx_transfer_master";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: FxTransferMasterRow. 
   */
  public RowType getRowType() {
    return FxTransferMasterRow.TYPE;
  }

  /**
   * <em>fx_system_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long fx_system_id;
  /**
   * <em>transfer_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String transfer_div;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public FxTransferMasterPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_fxSystemId <em>fx_system_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_transferDiv <em>transfer_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public FxTransferMasterPK( long p_fxSystemId, String p_transferDiv ) {
      fx_system_id = p_fxSystemId;
      transfer_div = p_transferDiv;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static FxTransferMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FxTransferMasterPK pk = new FxTransferMasterPK();
    int i = pkValueString.indexOf(',');
    pk.fx_system_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.transfer_div = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(fx_system_id) + "," + transfer_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FxTransferMasterPK) )
      return false;
    FxTransferMasterPK o = (FxTransferMasterPK) other;
    if ( fx_system_id != o.fx_system_id )
      return false;
    if ( transfer_div == null ) {
      if ( o.transfer_div != null )
        return false;
    } else if ( !transfer_div.equals( o.transfer_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) fx_system_id)
        + (transfer_div!=null? transfer_div.hashCode(): 0) 
    ;
  }

}

@
