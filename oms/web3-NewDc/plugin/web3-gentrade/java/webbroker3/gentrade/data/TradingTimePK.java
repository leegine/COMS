head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TradingTimePK.java;


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
 * <b>TradingTime</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>TradingTime</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>TradingTime</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradingTimeRow 
 */
public class TradingTimePK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "trading_time";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: TradingTimeRow. 
   */
  public RowType getRowType() {
    return TradingTimeRow.TYPE;
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
   * <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String market_code;
  /**
   * <em>trading_time_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String trading_time_type;
  /**
   * <em>product_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String product_code;
  /**
   * <em>biz_date_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String biz_date_type;
  /**
   * <em>start_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public String start_time;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public TradingTimePK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_tradingTimeType <em>trading_time_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷7���ȉ���String�l 
   * @@param p_bizDateType <em>biz_date_type</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_startTime <em>start_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public TradingTimePK( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      market_code = p_marketCode;
      trading_time_type = p_tradingTimeType;
      product_code = p_productCode;
      biz_date_type = p_bizDateType;
      start_time = p_startTime;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static TradingTimePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TradingTimePK pk = new TradingTimePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.trading_time_type = st.nextToken();
    pk.product_code = st.nextToken();
    pk.biz_date_type = st.nextToken();
    pk.start_time = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + market_code + "," + trading_time_type + "," + product_code + "," + biz_date_type + "," + start_time;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TradingTimePK) )
      return false;
    TradingTimePK o = (TradingTimePK) other;
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
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( trading_time_type == null ) {
      if ( o.trading_time_type != null )
        return false;
    } else if ( !trading_time_type.equals( o.trading_time_type ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( biz_date_type == null ) {
      if ( o.biz_date_type != null )
        return false;
    } else if ( !biz_date_type.equals( o.biz_date_type ) ) {
        return false;
    }
    if ( start_time == null ) {
      if ( o.start_time != null )
        return false;
    } else if ( !start_time.equals( o.start_time ) ) {
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
        + (market_code!=null? market_code.hashCode(): 0) 
        + (trading_time_type!=null? trading_time_type.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (biz_date_type!=null? biz_date_type.hashCode(): 0) 
        + (start_time!=null? start_time.hashCode(): 0) 
    ;
  }

}

@
