head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderSwitchingPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OrderSwitching</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>OrderSwitching</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>OrderSwitching</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderSwitchingRow 
 */
public class OrderSwitchingPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "order_switching";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: OrderSwitchingRow. 
   */
  public RowType getRowType() {
    return OrderSwitchingRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum product_type;
  /**
   * <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String market_code;
  /**
   * <em>submit_order_route_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String submit_order_route_div;
  /**
   * <em>front_order_system_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String front_order_system_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public OrderSwitchingPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_productType <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_submitOrderRouteDiv <em>submit_order_route_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_frontOrderSystemCode <em>front_order_system_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public OrderSwitchingPK( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) {
      institution_code = p_institutionCode;
      product_type = p_productType;
      market_code = p_marketCode;
      submit_order_route_div = p_submitOrderRouteDiv;
      front_order_system_code = p_frontOrderSystemCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static OrderSwitchingPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OrderSwitchingPK pk = new OrderSwitchingPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.submit_order_route_div = st.nextToken();
    pk.front_order_system_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(product_type.intValue()) + "," + market_code + "," + submit_order_route_div + "," + front_order_system_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OrderSwitchingPK) )
      return false;
    OrderSwitchingPK o = (OrderSwitchingPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( o.product_type != null )
        return false;
    } else if ( !product_type.equals( o.product_type ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( submit_order_route_div == null ) {
      if ( o.submit_order_route_div != null )
        return false;
    } else if ( !submit_order_route_div.equals( o.submit_order_route_div ) ) {
        return false;
    }
    if ( front_order_system_code == null ) {
      if ( o.front_order_system_code != null )
        return false;
    } else if ( !front_order_system_code.equals( o.front_order_system_code ) ) {
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
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (submit_order_route_div!=null? submit_order_route_div.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
    ;
  }

}

@
