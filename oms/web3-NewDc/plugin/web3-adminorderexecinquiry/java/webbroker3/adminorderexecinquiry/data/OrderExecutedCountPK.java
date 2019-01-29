head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderExecutedCountPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OrderExecutedCount</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>OrderExecutedCount</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>OrderExecutedCount</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderExecutedCountRow 
 */
public class OrderExecutedCountPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "order_executed_count";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: OrderExecutedCountRow. 
   */
  public RowType getRowType() {
    return OrderExecutedCountRow.TYPE;
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
   * <em>record_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String record_div;
  /**
   * <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String market_code;
  /**
   * <em>product_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String product_div;
  /**
   * <em>order_chanel</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String order_chanel;
  /**
   * <em>order_root_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String order_root_div;
  /**
   * <em>biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public String biz_date;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public OrderExecutedCountPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_recordDiv <em>record_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_productDiv <em>product_div</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_orderChanel <em>order_chanel</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_orderRootDiv <em>order_root_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_bizDate <em>biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public OrderExecutedCountPK( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      record_div = p_recordDiv;
      market_code = p_marketCode;
      product_div = p_productDiv;
      order_chanel = p_orderChanel;
      order_root_div = p_orderRootDiv;
      biz_date = p_bizDate;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static OrderExecutedCountPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OrderExecutedCountPK pk = new OrderExecutedCountPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.record_div = st.nextToken();
    pk.market_code = st.nextToken();
    pk.product_div = st.nextToken();
    pk.order_chanel = st.nextToken();
    pk.order_root_div = st.nextToken();
    pk.biz_date = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + record_div + "," + market_code + "," + product_div + "," + order_chanel + "," + order_root_div + "," + biz_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OrderExecutedCountPK) )
      return false;
    OrderExecutedCountPK o = (OrderExecutedCountPK) other;
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
    if ( record_div == null ) {
      if ( o.record_div != null )
        return false;
    } else if ( !record_div.equals( o.record_div ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( product_div == null ) {
      if ( o.product_div != null )
        return false;
    } else if ( !product_div.equals( o.product_div ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( o.order_chanel != null )
        return false;
    } else if ( !order_chanel.equals( o.order_chanel ) ) {
        return false;
    }
    if ( order_root_div == null ) {
      if ( o.order_root_div != null )
        return false;
    } else if ( !order_root_div.equals( o.order_root_div ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( o.biz_date != null )
        return false;
    } else if ( !biz_date.equals( o.biz_date ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (record_div!=null? record_div.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (product_div!=null? product_div.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (order_root_div!=null? order_root_div.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
    ;
  }

}

@
