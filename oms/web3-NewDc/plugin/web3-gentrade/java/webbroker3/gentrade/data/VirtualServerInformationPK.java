head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerInformationPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>VirtualServerInformation</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>VirtualServerInformation</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>VirtualServerInformation</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VirtualServerInformationRow 
 */
public class VirtualServerInformationPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "virtual_server_information";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: VirtualServerInformationRow. 
   */
  public RowType getRowType() {
    return VirtualServerInformationRow.TYPE;
  }

  /**
   * <em>virtual_server_number_jsoes</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String virtual_server_number_jsoes;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public VirtualServerInformationPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_virtualServerNumberJsoes <em>virtual_server_number_jsoes</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public VirtualServerInformationPK( String p_virtualServerNumberJsoes ) {
      virtual_server_number_jsoes = p_virtualServerNumberJsoes;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static VirtualServerInformationPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new VirtualServerInformationPK( pkValueString );
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    return virtual_server_number_jsoes;
  }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof VirtualServerInformationPK) )
      return false;
    VirtualServerInformationPK o = (VirtualServerInformationPK) other;
    if ( virtual_server_number_jsoes == null ) {
      if ( o.virtual_server_number_jsoes != null )
        return false;
    } else if ( !virtual_server_number_jsoes.equals( o.virtual_server_number_jsoes ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (virtual_server_number_jsoes!=null? virtual_server_number_jsoes.hashCode(): 0) 
    ;
  }

}

@
