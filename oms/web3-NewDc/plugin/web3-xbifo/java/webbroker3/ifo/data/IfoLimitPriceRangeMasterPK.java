head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoLimitPriceRangeMasterPK.java;


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
 * <b>IfoLimitPriceRangeMaster</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>IfoLimitPriceRangeMaster</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>IfoLimitPriceRangeMaster</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoLimitPriceRangeMasterRow 
 */
public class IfoLimitPriceRangeMasterPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ifo_limit_price_range_master";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: IfoLimitPriceRangeMasterRow. 
   */
  public RowType getRowType() {
    return IfoLimitPriceRangeMasterRow.TYPE;
  }

  /**
   * <em>target_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public String target_product_code;
  /**
   * <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String future_option_div;
  /**
   * <em>low_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public double low_price;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public IfoLimitPriceRangeMasterPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_targetProductCode <em>target_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   * @@param p_futureOptionDiv <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_lowPrice <em>low_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public IfoLimitPriceRangeMasterPK( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) {
      target_product_code = p_targetProductCode;
      future_option_div = p_futureOptionDiv;
      low_price = p_lowPrice;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static IfoLimitPriceRangeMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoLimitPriceRangeMasterPK pk = new IfoLimitPriceRangeMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.target_product_code = st.nextToken();
    pk.future_option_div = st.nextToken();
    pk.low_price = Double.valueOf(st.nextToken()).doubleValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = target_product_code + "," + future_option_div + "," + low_priceFormat.format(low_price);
    return m_id;
  }

  private String m_id = null;
  private static final java.text.DecimalFormat low_priceFormat = new java.text.DecimalFormat("#.######");

  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoLimitPriceRangeMasterPK) )
      return false;
    IfoLimitPriceRangeMasterPK o = (IfoLimitPriceRangeMasterPK) other;
    if ( target_product_code == null ) {
      if ( o.target_product_code != null )
        return false;
    } else if ( !target_product_code.equals( o.target_product_code ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( o.future_option_div != null )
        return false;
    } else if ( !future_option_div.equals( o.future_option_div ) ) {
        return false;
    }
    if ( low_price != o.low_price )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (target_product_code!=null? target_product_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + ((int) low_price)
    ;
  }

}

@
