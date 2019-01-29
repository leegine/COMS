head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfFixedBuyingChangeHistPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>MfFixedBuyingChangeHist</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MfFixedBuyingChangeHist</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MfFixedBuyingChangeHist</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MfFixedBuyingChangeHistRow 
 */
public class MfFixedBuyingChangeHistPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mf_fixed_buying_change_hist";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MfFixedBuyingChangeHistRow. 
   */
  public RowType getRowType() {
    return MfFixedBuyingChangeHistRow.TYPE;
  }

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
   * <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public String product_code;
  /**
   * <em>valid_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp valid_start_date;
  /**
   * <em>serial_no</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public int serial_no;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MfFixedBuyingChangeHistPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_accountCode <em>account_code</em>�J�����̒l������킷7���ȉ���String�l 
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   * @@param p_validStartDate <em>valid_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   * @@param p_serialNo <em>serial_no</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public MfFixedBuyingChangeHistPK( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate, int p_serialNo ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_code = p_accountCode;
      product_code = p_productCode;
      valid_start_date = p_validStartDate;
      serial_no = p_serialNo;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MfFixedBuyingChangeHistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MfFixedBuyingChangeHistPK pk = new MfFixedBuyingChangeHistPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.product_code = st.nextToken();
    pk.valid_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + account_code + "," + product_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(valid_start_date) + "," + String.valueOf(serial_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MfFixedBuyingChangeHistPK) )
      return false;
    MfFixedBuyingChangeHistPK o = (MfFixedBuyingChangeHistPK) other;
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
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( valid_start_date == null ) {
      if ( o.valid_start_date != null )
        return false;
    } else if ( !valid_start_date.equals( o.valid_start_date ) ) {
        return false;
    }
    if ( serial_no != o.serial_no )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (valid_start_date!=null? valid_start_date.hashCode(): 0) 
        + ((int) serial_no)
    ;
  }

}

@
