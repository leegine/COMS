head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountTransferPK.java;


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
 * <b>AccountTransfer</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>AccountTransfer</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>AccountTransfer</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountTransferRow 
 */
public class AccountTransferPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "account_transfer";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: AccountTransferRow. 
   */
  public RowType getRowType() {
    return AccountTransferRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>work_day</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp work_day;
  /**
   * <em>branch_code_old</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String branch_code_old;
  /**
   * <em>account_code_old</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String account_code_old;
  /**
   * <em>transfer_tbl</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public String transfer_tbl;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public AccountTransferPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_workDay <em>work_day</em>�J�����̒l������킷java.sql.Timestamp�l
   * @@param p_branchCodeOld <em>branch_code_old</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_accountCodeOld <em>account_code_old</em>�J�����̒l������킷7���ȉ���String�l 
   * @@param p_transferTbl <em>transfer_tbl</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public AccountTransferPK( String p_institutionCode, java.sql.Timestamp p_workDay, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) {
      institution_code = p_institutionCode;
      work_day = p_workDay;
      branch_code_old = p_branchCodeOld;
      account_code_old = p_accountCodeOld;
      transfer_tbl = p_transferTbl;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static AccountTransferPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccountTransferPK pk = new AccountTransferPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.work_day = java.sql.Timestamp.valueOf(st.nextToken());
    pk.branch_code_old = st.nextToken();
    pk.account_code_old = st.nextToken();
    pk.transfer_tbl = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(work_day) + "," + branch_code_old + "," + account_code_old + "," + transfer_tbl;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccountTransferPK) )
      return false;
    AccountTransferPK o = (AccountTransferPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( work_day == null ) {
      if ( o.work_day != null )
        return false;
    } else if ( !work_day.equals( o.work_day ) ) {
        return false;
    }
    if ( branch_code_old == null ) {
      if ( o.branch_code_old != null )
        return false;
    } else if ( !branch_code_old.equals( o.branch_code_old ) ) {
        return false;
    }
    if ( account_code_old == null ) {
      if ( o.account_code_old != null )
        return false;
    } else if ( !account_code_old.equals( o.account_code_old ) ) {
        return false;
    }
    if ( transfer_tbl == null ) {
      if ( o.transfer_tbl != null )
        return false;
    } else if ( !transfer_tbl.equals( o.transfer_tbl ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (work_day!=null? work_day.hashCode(): 0) 
        + (branch_code_old!=null? branch_code_old.hashCode(): 0) 
        + (account_code_old!=null? account_code_old.hashCode(): 0) 
        + (transfer_tbl!=null? transfer_tbl.hashCode(): 0) 
    ;
  }

}

@
