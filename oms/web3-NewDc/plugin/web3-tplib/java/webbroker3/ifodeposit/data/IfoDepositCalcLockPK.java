head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcLockPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>IfoDepositCalcLock</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>IfoDepositCalcLock</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>IfoDepositCalcLock</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDepositCalcLockRow 
 */
public class IfoDepositCalcLockPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ifo_deposit_calc_lock";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: IfoDepositCalcLockRow. 
   */
  public RowType getRowType() {
    return IfoDepositCalcLockRow.TYPE;
  }

  /**
   * <em>service_name</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public String service_name;
  /**
   * <em>thread_no</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long thread_no;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public IfoDepositCalcLockPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_serviceName <em>service_name</em>�J�����̒l������킷100���ȉ���String�l 
   * @@param p_threadNo <em>thread_no</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public IfoDepositCalcLockPK( String p_serviceName, long p_threadNo ) {
      service_name = p_serviceName;
      thread_no = p_threadNo;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static IfoDepositCalcLockPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoDepositCalcLockPK pk = new IfoDepositCalcLockPK();
    int i = pkValueString.indexOf(',');
    pk.service_name = pkValueString.substring(0,i);
    pk.thread_no = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = service_name + "," + String.valueOf(thread_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoDepositCalcLockPK) )
      return false;
    IfoDepositCalcLockPK o = (IfoDepositCalcLockPK) other;
    if ( service_name == null ) {
      if ( o.service_name != null )
        return false;
    } else if ( !service_name.equals( o.service_name ) ) {
        return false;
    }
    if ( thread_no != o.thread_no )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (service_name!=null? service_name.hashCode(): 0) 
        + ((int) thread_no)
    ;
  }

}

@
