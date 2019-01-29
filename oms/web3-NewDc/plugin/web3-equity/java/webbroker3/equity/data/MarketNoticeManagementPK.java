head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketNoticeManagementPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>MarketNoticeManagement</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MarketNoticeManagement</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MarketNoticeManagement</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketNoticeManagementRow 
 */
public class MarketNoticeManagementPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "market_notice_management";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MarketNoticeManagementRow. 
   */
  public RowType getRowType() {
    return MarketNoticeManagementRow.TYPE;
  }

  /**
   * <em>virtual_server_number_market</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String virtual_server_number_market;
  /**
   * <em>notice_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String notice_type;
  /**
   * <em>notice_number</em>�J�����̒l������킷10���ȉ���long�l 
   */
  public long notice_number;
  /**
   * <em>front_order_exchange_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String front_order_exchange_code;
  /**
   * <em>front_order_system_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String front_order_system_code;
  /**
   * <em>biz_date_count</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public int biz_date_count;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MarketNoticeManagementPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_virtualServerNumberMarket <em>virtual_server_number_market</em>�J�����̒l������킷7���ȉ���String�l 
   * @@param p_noticeType <em>notice_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_noticeNumber <em>notice_number</em>�J�����̒l������킷10���ȉ���long�l 
   * @@param p_frontOrderExchangeCode <em>front_order_exchange_code</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_frontOrderSystemCode <em>front_order_system_code</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_bizDateCount <em>biz_date_count</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public MarketNoticeManagementPK( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) {
      virtual_server_number_market = p_virtualServerNumberMarket;
      notice_type = p_noticeType;
      notice_number = p_noticeNumber;
      front_order_exchange_code = p_frontOrderExchangeCode;
      front_order_system_code = p_frontOrderSystemCode;
      biz_date_count = p_bizDateCount;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MarketNoticeManagementPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MarketNoticeManagementPK pk = new MarketNoticeManagementPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.virtual_server_number_market = st.nextToken();
    pk.notice_type = st.nextToken();
    pk.notice_number = Long.valueOf(st.nextToken()).longValue();
    pk.front_order_exchange_code = st.nextToken();
    pk.front_order_system_code = st.nextToken();
    pk.biz_date_count = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = virtual_server_number_market + "," + notice_type + "," + String.valueOf(notice_number) + "," + front_order_exchange_code + "," + front_order_system_code + "," + String.valueOf(biz_date_count);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MarketNoticeManagementPK) )
      return false;
    MarketNoticeManagementPK o = (MarketNoticeManagementPK) other;
    if ( virtual_server_number_market == null ) {
      if ( o.virtual_server_number_market != null )
        return false;
    } else if ( !virtual_server_number_market.equals( o.virtual_server_number_market ) ) {
        return false;
    }
    if ( notice_type == null ) {
      if ( o.notice_type != null )
        return false;
    } else if ( !notice_type.equals( o.notice_type ) ) {
        return false;
    }
    if ( notice_number != o.notice_number )
      return false;
    if ( front_order_exchange_code == null ) {
      if ( o.front_order_exchange_code != null )
        return false;
    } else if ( !front_order_exchange_code.equals( o.front_order_exchange_code ) ) {
        return false;
    }
    if ( front_order_system_code == null ) {
      if ( o.front_order_system_code != null )
        return false;
    } else if ( !front_order_system_code.equals( o.front_order_system_code ) ) {
        return false;
    }
    if ( biz_date_count != o.biz_date_count )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (notice_type!=null? notice_type.hashCode(): 0) 
        + ((int) notice_number)
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
        + ((int) biz_date_count)
    ;
  }

}

@
