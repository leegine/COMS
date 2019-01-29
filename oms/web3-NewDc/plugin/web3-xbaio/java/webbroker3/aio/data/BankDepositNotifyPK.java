head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.43.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankDepositNotifyPK.java;


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
 * <b>BankDepositNotify</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>BankDepositNotify</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>BankDepositNotify</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankDepositNotifyRow 
 */
public class BankDepositNotifyPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bank_deposit_notify";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: BankDepositNotifyRow. 
   */
  public RowType getRowType() {
    return BankDepositNotifyRow.TYPE;
  }

  /**
   * <em>bank_deposit_notify_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long bank_deposit_notify_id;
  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>data_load_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String data_load_div;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public BankDepositNotifyPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_bankDepositNotifyId <em>bank_deposit_notify_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_dataLoadDiv <em>data_load_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public BankDepositNotifyPK( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) {
      bank_deposit_notify_id = p_bankDepositNotifyId;
      institution_code = p_institutionCode;
      data_load_div = p_dataLoadDiv;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static BankDepositNotifyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BankDepositNotifyPK pk = new BankDepositNotifyPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.bank_deposit_notify_id = Long.valueOf(st.nextToken()).longValue();
    pk.institution_code = st.nextToken();
    pk.data_load_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(bank_deposit_notify_id) + "," + institution_code + "," + data_load_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BankDepositNotifyPK) )
      return false;
    BankDepositNotifyPK o = (BankDepositNotifyPK) other;
    if ( bank_deposit_notify_id != o.bank_deposit_notify_id )
      return false;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( data_load_div == null ) {
      if ( o.data_load_div != null )
        return false;
    } else if ( !data_load_div.equals( o.data_load_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) bank_deposit_notify_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (data_load_div!=null? data_load_div.hashCode(): 0) 
    ;
  }

}

@
