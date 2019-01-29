head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherStatusPK.java;


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
 * <b>AccOpenVoucherStatus</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>AccOpenVoucherStatus</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>AccOpenVoucherStatus</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenVoucherStatusRow 
 */
public class AccOpenVoucherStatusPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "acc_open_voucher_status";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: AccOpenVoucherStatusRow. 
   */
  public RowType getRowType() {
    return AccOpenVoucherStatusRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>acc_open_request_number</em>�J�����̒l������킷13���ȉ���String�l 
   */
  public String acc_open_request_number;
  /**
   * <em>request_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public String request_code;
  /**
   * <em>serial_no</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String serial_no;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public AccOpenVoucherStatusPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_accOpenRequestNumber <em>acc_open_request_number</em>�J�����̒l������킷13���ȉ���String�l 
   * @@param p_requestCode <em>request_code</em>�J�����̒l������킷5���ȉ���String�l 
   * @@param p_serialNo <em>serial_no</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public AccOpenVoucherStatusPK( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) {
      institution_code = p_institutionCode;
      acc_open_request_number = p_accOpenRequestNumber;
      request_code = p_requestCode;
      serial_no = p_serialNo;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static AccOpenVoucherStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccOpenVoucherStatusPK pk = new AccOpenVoucherStatusPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.acc_open_request_number = st.nextToken();
    pk.request_code = st.nextToken();
    pk.serial_no = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + acc_open_request_number + "," + request_code + "," + serial_no;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccOpenVoucherStatusPK) )
      return false;
    AccOpenVoucherStatusPK o = (AccOpenVoucherStatusPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( acc_open_request_number == null ) {
      if ( o.acc_open_request_number != null )
        return false;
    } else if ( !acc_open_request_number.equals( o.acc_open_request_number ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( o.request_code != null )
        return false;
    } else if ( !request_code.equals( o.request_code ) ) {
        return false;
    }
    if ( serial_no == null ) {
      if ( o.serial_no != null )
        return false;
    } else if ( !serial_no.equals( o.serial_no ) ) {
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
        + (acc_open_request_number!=null? acc_open_request_number.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (serial_no!=null? serial_no.hashCode(): 0) 
    ;
  }

}

@