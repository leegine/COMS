head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondBranchRecruitLimitPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * <b>BondBranchRecruitLimit</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>BondBranchRecruitLimit</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>BondBranchRecruitLimit</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondBranchRecruitLimitRow 
 */
public class BondBranchRecruitLimitPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bond_branch_recruit_limit";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: BondBranchRecruitLimitRow. 
   */
  public RowType getRowType() {
    return BondBranchRecruitLimitRow.TYPE;
  }

  /**
   * <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long product_id;
  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String branch_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public BondBranchRecruitLimitPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_productId <em>product_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public BondBranchRecruitLimitPK( long p_productId, String p_institutionCode, String p_branchCode ) {
      product_id = p_productId;
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static BondBranchRecruitLimitPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BondBranchRecruitLimitPK pk = new BondBranchRecruitLimitPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.product_id = Long.valueOf(st.nextToken()).longValue();
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(product_id) + "," + institution_code + "," + branch_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BondBranchRecruitLimitPK) )
      return false;
    BondBranchRecruitLimitPK o = (BondBranchRecruitLimitPK) other;
    if ( product_id != o.product_id )
      return false;
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
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) product_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
    ;
  }

}

@
