head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoDeliveryMonthMasterPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * <b>IfoDeliveryMonthMaster</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>IfoDeliveryMonthMaster</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>IfoDeliveryMonthMaster</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoDeliveryMonthMasterRow 
 */
public class IfoDeliveryMonthMasterPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ifo_delivery_month_master";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: IfoDeliveryMonthMasterRow. 
   */
  public RowType getRowType() {
    return IfoDeliveryMonthMasterRow.TYPE;
  }

  /**
   * <em>underlying_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public String underlying_product_code;
  /**
   * <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String future_option_div;
  /**
   * <em>delivery_month</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public String delivery_month;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public IfoDeliveryMonthMasterPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_underlyingProductCode <em>underlying_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   * @@param p_futureOptionDiv <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_deliveryMonth <em>delivery_month</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public IfoDeliveryMonthMasterPK( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) {
      underlying_product_code = p_underlyingProductCode;
      future_option_div = p_futureOptionDiv;
      delivery_month = p_deliveryMonth;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static IfoDeliveryMonthMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoDeliveryMonthMasterPK pk = new IfoDeliveryMonthMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.underlying_product_code = st.nextToken();
    pk.future_option_div = st.nextToken();
    pk.delivery_month = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = underlying_product_code + "," + future_option_div + "," + delivery_month;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoDeliveryMonthMasterPK) )
      return false;
    IfoDeliveryMonthMasterPK o = (IfoDeliveryMonthMasterPK) other;
    if ( underlying_product_code == null ) {
      if ( o.underlying_product_code != null )
        return false;
    } else if ( !underlying_product_code.equals( o.underlying_product_code ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( o.future_option_div != null )
        return false;
    } else if ( !future_option_div.equals( o.future_option_div ) ) {
        return false;
    }
    if ( delivery_month == null ) {
      if ( o.delivery_month != null )
        return false;
    } else if ( !delivery_month.equals( o.delivery_month ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (delivery_month!=null? delivery_month.hashCode(): 0) 
    ;
  }

}

@
