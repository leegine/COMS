head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CooperateBankMasterPK.java;


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
 * <b>CooperateBankMaster</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>CooperateBankMaster</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>CooperateBankMaster</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CooperateBankMasterRow 
 */
public class CooperateBankMasterPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "cooperate_bank_master";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: CooperateBankMasterRow. 
   */
  public RowType getRowType() {
    return CooperateBankMasterRow.TYPE;
  }

  /**
   * <em>pay_scheme_id</em>�J�����̒l������킷11���ȉ���String�l 
   */
  public String pay_scheme_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public CooperateBankMasterPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_paySchemeId <em>pay_scheme_id</em>�J�����̒l������킷11���ȉ���String�l 
   */
  public CooperateBankMasterPK( String p_paySchemeId ) {
      pay_scheme_id = p_paySchemeId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static CooperateBankMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new CooperateBankMasterPK( pkValueString );
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    return pay_scheme_id;
  }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CooperateBankMasterPK) )
      return false;
    CooperateBankMasterPK o = (CooperateBankMasterPK) other;
    if ( pay_scheme_id == null ) {
      if ( o.pay_scheme_id != null )
        return false;
    } else if ( !pay_scheme_id.equals( o.pay_scheme_id ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
    ;
  }

}

@
