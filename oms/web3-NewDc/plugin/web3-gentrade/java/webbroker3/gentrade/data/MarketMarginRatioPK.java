head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.26.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketMarginRatioPK.java;


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
 * <b>MarketMarginRatio</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MarketMarginRatio</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MarketMarginRatio</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketMarginRatioRow 
 */
public class MarketMarginRatioPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "market_margin_ratio";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MarketMarginRatioRow. 
   */
  public RowType getRowType() {
    return MarketMarginRatioRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long market_id;
  /**
   * <em>list_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String list_type;
  /**
   * <em>new_list_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String new_list_type;
  /**
   * <em>securities_estimation_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String securities_estimation_div;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MarketMarginRatioPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_listType <em>list_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_newListType <em>new_list_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_securitiesEstimationDiv <em>securities_estimation_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public MarketMarginRatioPK( String p_institutionCode, long p_marketId, String p_listType, String p_newListType, String p_securitiesEstimationDiv ) {
      institution_code = p_institutionCode;
      market_id = p_marketId;
      list_type = p_listType;
      new_list_type = p_newListType;
      securities_estimation_div = p_securitiesEstimationDiv;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MarketMarginRatioPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MarketMarginRatioPK pk = new MarketMarginRatioPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.market_id = Long.valueOf(st.nextToken()).longValue();
    pk.list_type = st.nextToken();
    pk.new_list_type = st.nextToken();
    pk.securities_estimation_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + String.valueOf(market_id) + "," + list_type + "," + new_list_type + "," + securities_estimation_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MarketMarginRatioPK) )
      return false;
    MarketMarginRatioPK o = (MarketMarginRatioPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( market_id != o.market_id )
      return false;
    if ( list_type == null ) {
      if ( o.list_type != null )
        return false;
    } else if ( !list_type.equals( o.list_type ) ) {
        return false;
    }
    if ( new_list_type == null ) {
      if ( o.new_list_type != null )
        return false;
    } else if ( !new_list_type.equals( o.new_list_type ) ) {
        return false;
    }
    if ( securities_estimation_div == null ) {
      if ( o.securities_estimation_div != null )
        return false;
    } else if ( !securities_estimation_div.equals( o.securities_estimation_div ) ) {
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
        + ((int) market_id)
        + (list_type!=null? list_type.hashCode(): 0) 
        + (new_list_type!=null? new_list_type.hashCode(): 0) 
        + (securities_estimation_div!=null? securities_estimation_div.hashCode(): 0) 
    ;
  }

}

@
