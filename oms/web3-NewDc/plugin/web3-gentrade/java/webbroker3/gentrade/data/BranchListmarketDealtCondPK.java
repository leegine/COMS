head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchListmarketDealtCondPK.java;


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
 * <b>BranchListmarketDealtCond</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>BranchListmarketDealtCond</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>BranchListmarketDealtCond</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchListmarketDealtCondRow 
 */
public class BranchListmarketDealtCondPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "branch_listmarket_dealt_cond";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: BranchListmarketDealtCondRow. 
   */
  public RowType getRowType() {
    return BranchListmarketDealtCondRow.TYPE;
  }

  /**
   * <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long branch_id;
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
   * <em>open_otc_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String open_otc_div;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public BranchListmarketDealtCondPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_listType <em>list_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_newListType <em>new_list_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_openOtcDiv <em>open_otc_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public BranchListmarketDealtCondPK( long p_branchId, long p_marketId, String p_listType, String p_newListType, String p_openOtcDiv ) {
      branch_id = p_branchId;
      market_id = p_marketId;
      list_type = p_listType;
      new_list_type = p_newListType;
      open_otc_div = p_openOtcDiv;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static BranchListmarketDealtCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BranchListmarketDealtCondPK pk = new BranchListmarketDealtCondPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.market_id = Long.valueOf(st.nextToken()).longValue();
    pk.list_type = st.nextToken();
    pk.new_list_type = st.nextToken();
    pk.open_otc_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(branch_id) + "," + String.valueOf(market_id) + "," + list_type + "," + new_list_type + "," + open_otc_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BranchListmarketDealtCondPK) )
      return false;
    BranchListmarketDealtCondPK o = (BranchListmarketDealtCondPK) other;
    if ( branch_id != o.branch_id )
      return false;
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
    if ( open_otc_div == null ) {
      if ( o.open_otc_div != null )
        return false;
    } else if ( !open_otc_div.equals( o.open_otc_div ) ) {
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
        + ((int) market_id)
        + (list_type!=null? list_type.hashCode(): 0) 
        + (new_list_type!=null? new_list_type.hashCode(): 0) 
        + (open_otc_div!=null? open_otc_div.hashCode(): 0) 
    ;
  }

}

@
