head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.30.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushIfoOrderAcceptPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>RichPushIfoOrderAccept</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>RichPushIfoOrderAccept</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>RichPushIfoOrderAccept</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RichPushIfoOrderAcceptRow 
 */
public class RichPushIfoOrderAcceptPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rich_push_ifo_order_accept";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: RichPushIfoOrderAcceptRow. 
   */
  public RowType getRowType() {
    return RichPushIfoOrderAcceptRow.TYPE;
  }

  /**
   * <em>serlnum</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long serlnum;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public RichPushIfoOrderAcceptPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_serlnum <em>serlnum</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public RichPushIfoOrderAcceptPK( long p_serlnum ) {
      serlnum = p_serlnum;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static RichPushIfoOrderAcceptPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RichPushIfoOrderAcceptPK pk = new RichPushIfoOrderAcceptPK();
    pk.serlnum = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(serlnum);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RichPushIfoOrderAcceptPK) )
      return false;
    RichPushIfoOrderAcceptPK o = (RichPushIfoOrderAcceptPK) other;
    if ( serlnum != o.serlnum )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) serlnum)
    ;
  }

}

@
