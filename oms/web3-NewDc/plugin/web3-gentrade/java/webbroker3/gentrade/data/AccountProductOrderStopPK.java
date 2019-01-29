head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountProductOrderStopPK.java;


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
 * <b>AccountProductOrderStop</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>AccountProductOrderStop</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>AccountProductOrderStop</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountProductOrderStopRow 
 */
public class AccountProductOrderStopPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "account_product_order_stop";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: AccountProductOrderStopRow. 
   */
  public RowType getRowType() {
    return AccountProductOrderStopRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long branch_id;
  /**
   * <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long account_id;
  /**
   * <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long product_id;
  /**
   * <em>apply_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp apply_start_date;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public AccountProductOrderStopPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_productId <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_applyStartDate <em>apply_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public AccountProductOrderStopPK( String p_institutionCode, long p_branchId, long p_accountId, long p_productId, java.sql.Timestamp p_applyStartDate ) {
      institution_code = p_institutionCode;
      branch_id = p_branchId;
      account_id = p_accountId;
      product_id = p_productId;
      apply_start_date = p_applyStartDate;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static AccountProductOrderStopPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccountProductOrderStopPK pk = new AccountProductOrderStopPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.product_id = Long.valueOf(st.nextToken()).longValue();
    pk.apply_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(branch_id) + "," + String.valueOf(account_id) + "," + String.valueOf(product_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(apply_start_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccountProductOrderStopPK) )
      return false;
    AccountProductOrderStopPK o = (AccountProductOrderStopPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_id != o.branch_id )
      return false;
    if ( account_id != o.account_id )
      return false;
    if ( product_id != o.product_id )
      return false;
    if ( apply_start_date == null ) {
      if ( o.apply_start_date != null )
        return false;
    } else if ( !apply_start_date.equals( o.apply_start_date ) ) {
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
        + ((int) branch_id)
        + ((int) account_id)
        + ((int) product_id)
        + (apply_start_date!=null? apply_start_date.hashCode(): 0) 
    ;
  }

}

@
