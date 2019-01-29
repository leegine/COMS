head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	MobileOfficeInfoRegistPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MobileOfficeInfoRegist</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MobileOfficeInfoRegist</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MobileOfficeInfoRegist</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MobileOfficeInfoRegistRow 
 */
public class MobileOfficeInfoRegistPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mobile_office_info_regist";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MobileOfficeInfoRegistRow. 
   */
  public RowType getRowType() {
    return MobileOfficeInfoRegistRow.TYPE;
  }

  /**
   * <em>mobile_office_info_regist_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long mobile_office_info_regist_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MobileOfficeInfoRegistPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_mobileOfficeInfoRegistId <em>mobile_office_info_regist_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public MobileOfficeInfoRegistPK( long p_mobileOfficeInfoRegistId ) {
      mobile_office_info_regist_id = p_mobileOfficeInfoRegistId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MobileOfficeInfoRegistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MobileOfficeInfoRegistPK pk = new MobileOfficeInfoRegistPK();
    pk.mobile_office_info_regist_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(mobile_office_info_regist_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MobileOfficeInfoRegistPK) )
      return false;
    MobileOfficeInfoRegistPK o = (MobileOfficeInfoRegistPK) other;
    if ( mobile_office_info_regist_id != o.mobile_office_info_regist_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) mobile_office_info_regist_id)
    ;
  }

}

@