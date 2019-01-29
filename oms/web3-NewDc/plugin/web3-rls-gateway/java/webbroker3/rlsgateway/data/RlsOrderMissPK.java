head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOrderMissPK.java;


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
 * <b>RlsOrderMiss</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>RlsOrderMiss</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>RlsOrderMiss</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsOrderMissRow 
 */
public class RlsOrderMissPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rls_order_miss";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: RlsOrderMissRow. 
   */
  public RowType getRowType() {
    return RlsOrderMissRow.TYPE;
  }

  /**
   * <em>miss_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String miss_type;
  /**
   * <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long account_id;
  /**
   * <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long sub_account_id;
  /**
   * <em>order_unit_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long order_unit_id;
  /**
   * <em>order_action_serial_no</em>�J�����̒l������킷8���ȉ���int�l 
   */
  public int order_action_serial_no;
  /**
   * <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum product_type;
  /**
   * <em>oms_cond_order_type</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public int oms_cond_order_type;
  /**
   * <em>detect_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String detect_type;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public RlsOrderMissPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_missType <em>miss_type</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_subAccountId <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_orderUnitId <em>order_unit_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>�J�����̒l������킷8���ȉ���int�l 
   * @@param p_productType <em>product_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�l 
   * @@param p_omsCondOrderType <em>oms_cond_order_type</em>�J�����̒l������킷6���ȉ���int�l 
   * @@param p_detectType <em>detect_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public RlsOrderMissPK( String p_missType, long p_accountId, long p_subAccountId, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) {
      miss_type = p_missType;
      account_id = p_accountId;
      sub_account_id = p_subAccountId;
      order_unit_id = p_orderUnitId;
      order_action_serial_no = p_orderActionSerialNo;
      product_type = p_productType;
      oms_cond_order_type = p_omsCondOrderType;
      detect_type = p_detectType;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static RlsOrderMissPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsOrderMissPK pk = new RlsOrderMissPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.miss_type = st.nextToken();
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.sub_account_id = Long.valueOf(st.nextToken()).longValue();
    pk.order_unit_id = Long.valueOf(st.nextToken()).longValue();
    pk.order_action_serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.oms_cond_order_type = Integer.valueOf(st.nextToken()).intValue();
    pk.detect_type = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = miss_type + "," + String.valueOf(account_id) + "," + String.valueOf(sub_account_id) + "," + String.valueOf(order_unit_id) + "," + String.valueOf(order_action_serial_no) + "," + String.valueOf(product_type.intValue()) + "," + String.valueOf(oms_cond_order_type) + "," + detect_type;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsOrderMissPK) )
      return false;
    RlsOrderMissPK o = (RlsOrderMissPK) other;
    if ( miss_type == null ) {
      if ( o.miss_type != null )
        return false;
    } else if ( !miss_type.equals( o.miss_type ) ) {
        return false;
    }
    if ( account_id != o.account_id )
      return false;
    if ( sub_account_id != o.sub_account_id )
      return false;
    if ( order_unit_id != o.order_unit_id )
      return false;
    if ( order_action_serial_no != o.order_action_serial_no )
      return false;
    if ( product_type == null ) {
      if ( o.product_type != null )
        return false;
    } else if ( !product_type.equals( o.product_type ) ) {
        return false;
    }
    if ( oms_cond_order_type != o.oms_cond_order_type )
      return false;
    if ( detect_type == null ) {
      if ( o.detect_type != null )
        return false;
    } else if ( !detect_type.equals( o.detect_type ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (miss_type!=null? miss_type.hashCode(): 0) 
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_unit_id)
        + ((int) order_action_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) oms_cond_order_type)
        + (detect_type!=null? detect_type.hashCode(): 0) 
    ;
  }

}

@
