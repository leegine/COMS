head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerChangePK.java;


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
 * <b>VirtualServerChange</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>VirtualServerChange</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>VirtualServerChange</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VirtualServerChangeRow 
 */
public class VirtualServerChangePK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "virtual_server_change";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: VirtualServerChangeRow. 
   */
  public RowType getRowType() {
    return VirtualServerChangeRow.TYPE;
  }

  /**
   * <em>virtual_server_number_market</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String virtual_server_number_market;
  /**
   * <em>change_req_res_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String change_req_res_div;
  /**
   * <em>notice_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String notice_type;
  /**
   * <em>front_order_exchange_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String front_order_exchange_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public VirtualServerChangePK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_virtualServerNumberMarket <em>virtual_server_number_market</em>�J�����̒l������킷7���ȉ���String�l 
   * @@param p_changeReqResDiv <em>change_req_res_div</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_noticeType <em>notice_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_frontOrderExchangeCode <em>front_order_exchange_code</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public VirtualServerChangePK( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) {
      virtual_server_number_market = p_virtualServerNumberMarket;
      change_req_res_div = p_changeReqResDiv;
      notice_type = p_noticeType;
      front_order_exchange_code = p_frontOrderExchangeCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static VirtualServerChangePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    VirtualServerChangePK pk = new VirtualServerChangePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.virtual_server_number_market = st.nextToken();
    pk.change_req_res_div = st.nextToken();
    pk.notice_type = st.nextToken();
    pk.front_order_exchange_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = virtual_server_number_market + "," + change_req_res_div + "," + notice_type + "," + front_order_exchange_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof VirtualServerChangePK) )
      return false;
    VirtualServerChangePK o = (VirtualServerChangePK) other;
    if ( virtual_server_number_market == null ) {
      if ( o.virtual_server_number_market != null )
        return false;
    } else if ( !virtual_server_number_market.equals( o.virtual_server_number_market ) ) {
        return false;
    }
    if ( change_req_res_div == null ) {
      if ( o.change_req_res_div != null )
        return false;
    } else if ( !change_req_res_div.equals( o.change_req_res_div ) ) {
        return false;
    }
    if ( notice_type == null ) {
      if ( o.notice_type != null )
        return false;
    } else if ( !notice_type.equals( o.notice_type ) ) {
        return false;
    }
    if ( front_order_exchange_code == null ) {
      if ( o.front_order_exchange_code != null )
        return false;
    } else if ( !front_order_exchange_code.equals( o.front_order_exchange_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (change_req_res_div!=null? change_req_res_div.hashCode(): 0) 
        + (notice_type!=null? notice_type.hashCode(): 0) 
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
    ;
  }

}

@
