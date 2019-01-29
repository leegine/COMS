head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	SpecialProductForeignCostPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>SpecialProductForeignCost</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>SpecialProductForeignCost</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>SpecialProductForeignCost</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SpecialProductForeignCostRow 
 */
public class SpecialProductForeignCostPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "special_product_foreign_cost";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: SpecialProductForeignCostRow. 
   */
  public RowType getRowType() {
    return SpecialProductForeignCostRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String market_code;
  /**
   * <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long product_id;
  /**
   * <em>offshore_product_code</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public String offshore_product_code;
  /**
   * <em>cost_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String cost_div;
  /**
   * <em>appli_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp appli_start_date;
  /**
   * <em>amount_from</em>�J�����̒l������킷13���ȉ���double�l�ŁA���̐��x��2���܂�
   */
  public double amount_from;
  /**
   * <em>side_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String side_div;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public SpecialProductForeignCostPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_productId <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_offshoreProductCode <em>offshore_product_code</em>�J�����̒l������킷9���ȉ���String�l 
   * @@param p_costDiv <em>cost_div</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_appliStartDate <em>appli_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   * @@param p_amountFrom <em>amount_from</em>�J�����̒l������킷13���ȉ���double�l�ŁA���̐��x��2���܂�
   * @@param p_sideDiv <em>side_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public SpecialProductForeignCostPK( String p_institutionCode, String p_marketCode, long p_productId, String p_offshoreProductCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) {
      institution_code = p_institutionCode;
      market_code = p_marketCode;
      product_id = p_productId;
      offshore_product_code = p_offshoreProductCode;
      cost_div = p_costDiv;
      appli_start_date = p_appliStartDate;
      amount_from = p_amountFrom;
      side_div = p_sideDiv;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static SpecialProductForeignCostPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SpecialProductForeignCostPK pk = new SpecialProductForeignCostPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.product_id = Long.valueOf(st.nextToken()).longValue();
    pk.offshore_product_code = st.nextToken();
    pk.cost_div = st.nextToken();
    pk.appli_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.amount_from = Double.valueOf(st.nextToken()).doubleValue();
    pk.side_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + market_code + "," + String.valueOf(product_id) + "," + offshore_product_code + "," + cost_div + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(appli_start_date) + "," + amount_fromFormat.format(amount_from) + "," + side_div;
    return m_id;
  }

  private String m_id = null;
  private static final java.text.DecimalFormat amount_fromFormat = new java.text.DecimalFormat("#.##");

  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SpecialProductForeignCostPK) )
      return false;
    SpecialProductForeignCostPK o = (SpecialProductForeignCostPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( product_id != o.product_id )
      return false;
    if ( offshore_product_code == null ) {
      if ( o.offshore_product_code != null )
        return false;
    } else if ( !offshore_product_code.equals( o.offshore_product_code ) ) {
        return false;
    }
    if ( cost_div == null ) {
      if ( o.cost_div != null )
        return false;
    } else if ( !cost_div.equals( o.cost_div ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
        return false;
    }
    if ( amount_from != o.amount_from )
      return false;
    if ( side_div == null ) {
      if ( o.side_div != null )
        return false;
    } else if ( !side_div.equals( o.side_div ) ) {
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
        + (market_code!=null? market_code.hashCode(): 0) 
        + ((int) product_id)
        + (offshore_product_code!=null? offshore_product_code.hashCode(): 0) 
        + (cost_div!=null? cost_div.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + ((int) amount_from)
        + (side_div!=null? side_div.hashCode(): 0) 
    ;
  }

}

@
