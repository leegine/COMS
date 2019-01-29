head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostPostalTransVoucherPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>HostPostalTransVoucher</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>HostPostalTransVoucher</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>HostPostalTransVoucher</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostPostalTransVoucherRow 
 */
public class HostPostalTransVoucherPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "host_postal_trans_voucher";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: HostPostalTransVoucherRow. 
   */
  public RowType getRowType() {
    return HostPostalTransVoucherRow.TYPE;
  }

  /**
   * <em>order_request_number</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public String order_request_number;
  /**
   * <em>request_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public String request_code;
  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String branch_code;
  /**
   * <em>account_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String account_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public HostPostalTransVoucherPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>�J�����̒l������킷9���ȉ���String�l 
   * @@param p_requestCode <em>request_code</em>�J�����̒l������킷5���ȉ���String�l 
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_accountCode <em>account_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public HostPostalTransVoucherPK( String p_orderRequestNumber, String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode ) {
      order_request_number = p_orderRequestNumber;
      request_code = p_requestCode;
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_code = p_accountCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static HostPostalTransVoucherPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    HostPostalTransVoucherPK pk = new HostPostalTransVoucherPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.order_request_number = st.nextToken();
    pk.request_code = st.nextToken();
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = order_request_number + "," + request_code + "," + institution_code + "," + branch_code + "," + account_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof HostPostalTransVoucherPK) )
      return false;
    HostPostalTransVoucherPK o = (HostPostalTransVoucherPK) other;
    if ( order_request_number == null ) {
      if ( o.order_request_number != null )
        return false;
    } else if ( !order_request_number.equals( o.order_request_number ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( o.request_code != null )
        return false;
    } else if ( !request_code.equals( o.request_code ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( o.branch_code != null )
        return false;
    } else if ( !branch_code.equals( o.branch_code ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( o.account_code != null )
        return false;
    } else if ( !account_code.equals( o.account_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
    ;
  }

}

@
