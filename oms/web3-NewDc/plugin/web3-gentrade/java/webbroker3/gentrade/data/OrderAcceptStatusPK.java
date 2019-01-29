head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderAcceptStatusPK.java;


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
 * <b>OrderAcceptStatus</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>OrderAcceptStatus</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>OrderAcceptStatus</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderAcceptStatusRow 
 */
public class OrderAcceptStatusPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "order_accept_status";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: OrderAcceptStatusRow. 
   */
  public RowType getRowType() {
    return OrderAcceptStatusRow.TYPE;
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
   * <em>order_acc_product</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String order_acc_product;
  /**
   * <em>order_acc_transaction</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String order_acc_transaction;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public OrderAcceptStatusPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_orderAccProduct <em>order_acc_product</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_orderAccTransaction <em>order_acc_transaction</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public OrderAcceptStatusPK( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      order_acc_product = p_orderAccProduct;
      order_acc_transaction = p_orderAccTransaction;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static OrderAcceptStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OrderAcceptStatusPK pk = new OrderAcceptStatusPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.order_acc_product = st.nextToken();
    pk.order_acc_transaction = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + order_acc_product + "," + order_acc_transaction;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OrderAcceptStatusPK) )
      return false;
    OrderAcceptStatusPK o = (OrderAcceptStatusPK) other;
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
    if ( order_acc_product == null ) {
      if ( o.order_acc_product != null )
        return false;
    } else if ( !order_acc_product.equals( o.order_acc_product ) ) {
        return false;
    }
    if ( order_acc_transaction == null ) {
      if ( o.order_acc_transaction != null )
        return false;
    } else if ( !order_acc_transaction.equals( o.order_acc_transaction ) ) {
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
        + (order_acc_product!=null? order_acc_product.hashCode(): 0) 
        + (order_acc_transaction!=null? order_acc_transaction.hashCode(): 0) 
    ;
  }

}

@
