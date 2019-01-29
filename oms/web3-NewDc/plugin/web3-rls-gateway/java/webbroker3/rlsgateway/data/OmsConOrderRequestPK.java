head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	OmsConOrderRequestPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>OmsConOrderRequest</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>OmsConOrderRequest</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>OmsConOrderRequest</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OmsConOrderRequestRow 
 */
public class OmsConOrderRequestPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "oms_con_order_request";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: OmsConOrderRequestRow. 
   */
  public RowType getRowType() {
    return OmsConOrderRequestRow.TYPE;
  }

  /**
   * <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long account_id;
  /**
   * <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long sub_account_id;
  /**
   * <em>order_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public int order_type;
  /**
   * <em>request_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public int request_type;
  /**
   * <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long order_id;
  /**
   * <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum product_type;
  /**
   * <em>sub_order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long sub_order_id;
  /**
   * <em>sub_product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum sub_product_type;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public OmsConOrderRequestPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_subAccountId <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_orderType <em>order_type</em>�J�����̒l������킷6���ȉ���int�l 
   * @@param p_requestType <em>request_type</em>�J�����̒l������킷6���ȉ���int�l 
   * @@param p_orderId <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_productType <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   * @@param p_subOrderId <em>sub_order_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_subProductType <em>sub_product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public OmsConOrderRequestPK( long p_accountId, long p_subAccountId, int p_orderType, int p_requestType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, long p_subOrderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_subProductType ) {
      account_id = p_accountId;
      sub_account_id = p_subAccountId;
      order_type = p_orderType;
      request_type = p_requestType;
      order_id = p_orderId;
      product_type = p_productType;
      sub_order_id = p_subOrderId;
      sub_product_type = p_subProductType;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static OmsConOrderRequestPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OmsConOrderRequestPK pk = new OmsConOrderRequestPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.sub_account_id = Long.valueOf(st.nextToken()).longValue();
    pk.order_type = Integer.valueOf(st.nextToken()).intValue();
    pk.request_type = Integer.valueOf(st.nextToken()).intValue();
    pk.order_id = Long.valueOf(st.nextToken()).longValue();
    pk.sub_order_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + String.valueOf(sub_account_id) + "," + String.valueOf(order_type) + "," + String.valueOf(request_type) + "," + String.valueOf(order_id) + "," + String.valueOf(product_type.intValue()) + "," + String.valueOf(sub_order_id) + "," + String.valueOf(sub_product_type.intValue());
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OmsConOrderRequestPK) )
      return false;
    OmsConOrderRequestPK o = (OmsConOrderRequestPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( sub_account_id != o.sub_account_id )
      return false;
    if ( order_type != o.order_type )
      return false;
    if ( request_type != o.request_type )
      return false;
    if ( order_id != o.order_id )
      return false;
    if ( product_type == null ) {
      if ( o.product_type != null )
        return false;
    } else if ( !product_type.equals( o.product_type ) ) {
        return false;
    }
    if ( sub_order_id != o.sub_order_id )
      return false;
    if ( sub_product_type == null ) {
      if ( o.sub_product_type != null )
        return false;
    } else if ( !sub_product_type.equals( o.sub_product_type ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_type)
        + ((int) request_type)
        + ((int) order_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) sub_order_id)
        + (sub_product_type!=null? sub_product_type.hashCode(): 0) 
    ;
  }

}

@
