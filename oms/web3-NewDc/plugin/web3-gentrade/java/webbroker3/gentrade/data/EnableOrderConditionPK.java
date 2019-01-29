head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EnableOrderConditionPK.java;


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
 * <b>EnableOrderCondition</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>EnableOrderCondition</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>EnableOrderCondition</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EnableOrderConditionRow 
 */
public class EnableOrderConditionPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "enable_order_condition";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: EnableOrderConditionRow. 
   */
  public RowType getRowType() {
    return EnableOrderConditionRow.TYPE;
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
   * <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String future_option_div;
  /**
   * <em>margin_trading_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String margin_trading_div;
  /**
   * <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String market_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public EnableOrderConditionPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_productType <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   * @@param p_futureOptionDiv <em>future_option_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_marginTradingDiv <em>margin_trading_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public EnableOrderConditionPK( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_marginTradingDiv, String p_marketCode ) {
      institution_code = p_institutionCode;
      product_type = p_productType;
      future_option_div = p_futureOptionDiv;
      margin_trading_div = p_marginTradingDiv;
      market_code = p_marketCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static EnableOrderConditionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    EnableOrderConditionPK pk = new EnableOrderConditionPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.future_option_div = st.nextToken();
    pk.margin_trading_div = st.nextToken();
    pk.market_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(product_type.intValue()) + "," + future_option_div + "," + margin_trading_div + "," + market_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof EnableOrderConditionPK) )
      return false;
    EnableOrderConditionPK o = (EnableOrderConditionPK) other;
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
    if ( future_option_div == null ) {
      if ( o.future_option_div != null )
        return false;
    } else if ( !future_option_div.equals( o.future_option_div ) ) {
        return false;
    }
    if ( margin_trading_div == null ) {
      if ( o.margin_trading_div != null )
        return false;
    } else if ( !margin_trading_div.equals( o.margin_trading_div ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
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
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (margin_trading_div!=null? margin_trading_div.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
    ;
  }

}

@