head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityShortageAccountHistPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>SecurityShortageAccountHist</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>SecurityShortageAccountHist</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>SecurityShortageAccountHist</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecurityShortageAccountHistRow 
 */
public class SecurityShortageAccountHistPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "security_shortage_account_hist";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: SecurityShortageAccountHistRow. 
   */
  public RowType getRowType() {
    return SecurityShortageAccountHistRow.TYPE;
  }

  /**
   * <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long account_id;
  /**
   * <em>proc_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public String proc_date;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public SecurityShortageAccountHistPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_procDate <em>proc_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public SecurityShortageAccountHistPK( long p_accountId, String p_procDate ) {
      account_id = p_accountId;
      proc_date = p_procDate;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static SecurityShortageAccountHistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SecurityShortageAccountHistPK pk = new SecurityShortageAccountHistPK();
    int i = pkValueString.indexOf(',');
    pk.account_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.proc_date = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + proc_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SecurityShortageAccountHistPK) )
      return false;
    SecurityShortageAccountHistPK o = (SecurityShortageAccountHistPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( proc_date == null ) {
      if ( o.proc_date != null )
        return false;
    } else if ( !proc_date.equals( o.proc_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) account_id)
        + (proc_date!=null? proc_date.hashCode(): 0) 
    ;
  }

}

@
