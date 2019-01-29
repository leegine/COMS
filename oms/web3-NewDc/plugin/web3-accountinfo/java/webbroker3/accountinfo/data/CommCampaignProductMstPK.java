head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignProductMstPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>CommCampaignProductMst</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>CommCampaignProductMst</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>CommCampaignProductMst</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommCampaignProductMstRow 
 */
public class CommCampaignProductMstPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "comm_campaign_product_mst";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: CommCampaignProductMstRow. 
   */
  public RowType getRowType() {
    return CommCampaignProductMstRow.TYPE;
  }

  /**
   * <em>campaign_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long campaign_id;
  /**
   * <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String comm_product_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public CommCampaignProductMstPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_campaignId <em>campaign_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_commProductCode <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public CommCampaignProductMstPK( long p_campaignId, String p_commProductCode ) {
      campaign_id = p_campaignId;
      comm_product_code = p_commProductCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static CommCampaignProductMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CommCampaignProductMstPK pk = new CommCampaignProductMstPK();
    int i = pkValueString.indexOf(',');
    pk.campaign_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.comm_product_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(campaign_id) + "," + comm_product_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CommCampaignProductMstPK) )
      return false;
    CommCampaignProductMstPK o = (CommCampaignProductMstPK) other;
    if ( campaign_id != o.campaign_id )
      return false;
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) campaign_id)
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
    ;
  }

}

@
