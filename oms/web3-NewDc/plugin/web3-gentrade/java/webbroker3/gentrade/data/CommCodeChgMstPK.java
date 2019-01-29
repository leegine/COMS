head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CommCodeChgMstPK.java;


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
 * <b>CommCodeChgMst</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>CommCodeChgMst</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>CommCodeChgMst</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommCodeChgMstRow 
 */
public class CommCodeChgMstPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "comm_code_chg_mst";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: CommCodeChgMstRow. 
   */
  public RowType getRowType() {
    return CommCodeChgMstRow.TYPE;
  }

  /**
   * <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long branch_id;
  /**
   * <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String comm_product_code;
  /**
   * <em>commission_no</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String commission_no;
  /**
   * <em>appli_start_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public String appli_start_date;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public CommCodeChgMstPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_commProductCode <em>comm_product_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_commissionNo <em>commission_no</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_appliStartDate <em>appli_start_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public CommCodeChgMstPK( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) {
      branch_id = p_branchId;
      comm_product_code = p_commProductCode;
      commission_no = p_commissionNo;
      appli_start_date = p_appliStartDate;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static CommCodeChgMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CommCodeChgMstPK pk = new CommCodeChgMstPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.comm_product_code = st.nextToken();
    pk.commission_no = st.nextToken();
    pk.appli_start_date = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(branch_id) + "," + comm_product_code + "," + commission_no + "," + appli_start_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CommCodeChgMstPK) )
      return false;
    CommCodeChgMstPK o = (CommCodeChgMstPK) other;
    if ( branch_id != o.branch_id )
      return false;
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    if ( commission_no == null ) {
      if ( o.commission_no != null )
        return false;
    } else if ( !commission_no.equals( o.commission_no ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) branch_id)
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (commission_no!=null? commission_no.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
    ;
  }

}

@
