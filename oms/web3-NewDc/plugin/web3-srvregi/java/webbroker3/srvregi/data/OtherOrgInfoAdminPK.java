head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.43.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	OtherOrgInfoAdminPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OtherOrgInfoAdmin</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>OtherOrgInfoAdmin</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>OtherOrgInfoAdmin</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgInfoAdminRow 
 */
public class OtherOrgInfoAdminPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "other_org_info_admin";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: OtherOrgInfoAdminRow. 
   */
  public RowType getRowType() {
    return OtherOrgInfoAdminRow.TYPE;
  }

  /**
   * <em>sequence_number</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long sequence_number;
  /**
   * <em>srv_div</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public String srv_div;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public OtherOrgInfoAdminPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_sequenceNumber <em>sequence_number</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_srvDiv <em>srv_div</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public OtherOrgInfoAdminPK( long p_sequenceNumber, String p_srvDiv ) {
      sequence_number = p_sequenceNumber;
      srv_div = p_srvDiv;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static OtherOrgInfoAdminPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OtherOrgInfoAdminPK pk = new OtherOrgInfoAdminPK();
    int i = pkValueString.indexOf(',');
    pk.sequence_number = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.srv_div = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(sequence_number) + "," + srv_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OtherOrgInfoAdminPK) )
      return false;
    OtherOrgInfoAdminPK o = (OtherOrgInfoAdminPK) other;
    if ( sequence_number != o.sequence_number )
      return false;
    if ( srv_div == null ) {
      if ( o.srv_div != null )
        return false;
    } else if ( !srv_div.equals( o.srv_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) sequence_number)
        + (srv_div!=null? srv_div.hashCode(): 0) 
    ;
  }

}

@
