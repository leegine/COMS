head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommRevMstPK.java;


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
 * <b>EquityCommRevMst</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>EquityCommRevMst</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>EquityCommRevMst</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EquityCommRevMstRow 
 */
public class EquityCommRevMstPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "equity_comm_rev_mst";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: EquityCommRevMstRow. 
   */
  public RowType getRowType() {
    return EquityCommRevMstRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String comm_product_code;
  /**
   * <em>appli_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp appli_start_date;
  /**
   * <em>order_channel</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String order_channel;
  /**
   * <em>transaction_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String transaction_type;
  /**
   * <em>exec_cond_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String exec_cond_type;
  /**
   * <em>pay_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String pay_type;
  /**
   * <em>sonar_market_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String sonar_market_code;
  /**
   * <em>underlying_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public String underlying_product_code;
  /**
   * <em>day_trade_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String day_trade_type;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public EquityCommRevMstPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_commProductCode <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_appliStartDate <em>appli_start_date</em>�J�����̒l������킷java.sql.Timestamp�l
   * @@param p_orderChannel <em>order_channel</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_transactionType <em>transaction_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_execCondType <em>exec_cond_type</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_payType <em>pay_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_sonarMarketCode <em>sonar_market_code</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_underlyingProductCode <em>underlying_product_code</em>�J�����̒l������킷4���ȉ���String�l 
   * @@param p_dayTradeType <em>day_trade_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public EquityCommRevMstPK( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) {
      institution_code = p_institutionCode;
      comm_product_code = p_commProductCode;
      appli_start_date = p_appliStartDate;
      order_channel = p_orderChannel;
      transaction_type = p_transactionType;
      exec_cond_type = p_execCondType;
      pay_type = p_payType;
      sonar_market_code = p_sonarMarketCode;
      underlying_product_code = p_underlyingProductCode;
      day_trade_type = p_dayTradeType;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static EquityCommRevMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    EquityCommRevMstPK pk = new EquityCommRevMstPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.comm_product_code = st.nextToken();
    pk.appli_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.order_channel = st.nextToken();
    pk.transaction_type = st.nextToken();
    pk.exec_cond_type = st.nextToken();
    pk.pay_type = st.nextToken();
    pk.sonar_market_code = st.nextToken();
    pk.underlying_product_code = st.nextToken();
    pk.day_trade_type = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + comm_product_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(appli_start_date) + "," + order_channel + "," + transaction_type + "," + exec_cond_type + "," + pay_type + "," + sonar_market_code + "," + underlying_product_code + "," + day_trade_type;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof EquityCommRevMstPK) )
      return false;
    EquityCommRevMstPK o = (EquityCommRevMstPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
        return false;
    }
    if ( order_channel == null ) {
      if ( o.order_channel != null )
        return false;
    } else if ( !order_channel.equals( o.order_channel ) ) {
        return false;
    }
    if ( transaction_type == null ) {
      if ( o.transaction_type != null )
        return false;
    } else if ( !transaction_type.equals( o.transaction_type ) ) {
        return false;
    }
    if ( exec_cond_type == null ) {
      if ( o.exec_cond_type != null )
        return false;
    } else if ( !exec_cond_type.equals( o.exec_cond_type ) ) {
        return false;
    }
    if ( pay_type == null ) {
      if ( o.pay_type != null )
        return false;
    } else if ( !pay_type.equals( o.pay_type ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( o.sonar_market_code != null )
        return false;
    } else if ( !sonar_market_code.equals( o.sonar_market_code ) ) {
        return false;
    }
    if ( underlying_product_code == null ) {
      if ( o.underlying_product_code != null )
        return false;
    } else if ( !underlying_product_code.equals( o.underlying_product_code ) ) {
        return false;
    }
    if ( day_trade_type == null ) {
      if ( o.day_trade_type != null )
        return false;
    } else if ( !day_trade_type.equals( o.day_trade_type ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (order_channel!=null? order_channel.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
        + (exec_cond_type!=null? exec_cond_type.hashCode(): 0) 
        + (pay_type!=null? pay_type.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (underlying_product_code!=null? underlying_product_code.hashCode(): 0) 
        + (day_trade_type!=null? day_trade_type.hashCode(): 0) 
    ;
  }

}

@
