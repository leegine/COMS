head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCommissionPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MutualFundInstCommission</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MutualFundInstCommission</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MutualFundInstCommission</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundInstCommissionRow 
 */
public class MutualFundInstCommissionPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mutual_fund_inst_commission";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MutualFundInstCommissionRow. 
   */
  public RowType getRowType() {
    return MutualFundInstCommissionRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public String product_code;
  /**
   * <em>deal_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String deal_div;
  /**
   * <em>order_chanel</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String order_chanel;
  /**
   * <em>valid_date_from</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp valid_date_from;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MutualFundInstCommissionPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   * @@param p_dealDiv <em>deal_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_orderChanel <em>order_chanel</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_validDateFrom <em>valid_date_from</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public MutualFundInstCommissionPK( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) {
      institution_code = p_institutionCode;
      product_code = p_productCode;
      deal_div = p_dealDiv;
      order_chanel = p_orderChanel;
      valid_date_from = p_validDateFrom;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MutualFundInstCommissionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MutualFundInstCommissionPK pk = new MutualFundInstCommissionPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.product_code = st.nextToken();
    pk.deal_div = st.nextToken();
    pk.order_chanel = st.nextToken();
    pk.valid_date_from = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + product_code + "," + deal_div + "," + order_chanel + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(valid_date_from);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MutualFundInstCommissionPK) )
      return false;
    MutualFundInstCommissionPK o = (MutualFundInstCommissionPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( deal_div == null ) {
      if ( o.deal_div != null )
        return false;
    } else if ( !deal_div.equals( o.deal_div ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( o.order_chanel != null )
        return false;
    } else if ( !order_chanel.equals( o.order_chanel ) ) {
        return false;
    }
    if ( valid_date_from == null ) {
      if ( o.valid_date_from != null )
        return false;
    } else if ( !valid_date_from.equals( o.valid_date_from ) ) {
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
        + (product_code!=null? product_code.hashCode(): 0) 
        + (deal_div!=null? deal_div.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (valid_date_from!=null? valid_date_from.hashCode(): 0) 
    ;
  }

}

@
