head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketRepayDealtCondPK.java;


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
 * <b>BranchMarketRepayDealtCond</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>BranchMarketRepayDealtCond</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>BranchMarketRepayDealtCond</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchMarketRepayDealtCondRow 
 */
public class BranchMarketRepayDealtCondPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "branch_market_repay_dealt_cond";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: BranchMarketRepayDealtCondRow. 
   */
  public RowType getRowType() {
    return BranchMarketRepayDealtCondRow.TYPE;
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
   * <em>repayment_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String repayment_div;
  /**
   * <em>repayment_num</em>�J�����̒l������킷7���ȉ���int�l 
   */
  public int repayment_num;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public BranchMarketRepayDealtCondPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_repaymentDiv <em>repayment_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_repaymentNum <em>repayment_num</em>�J�����̒l������킷7���ȉ���int�l 
   */
  public BranchMarketRepayDealtCondPK( String p_institutionCode, String p_branchCode, String p_marketCode, String p_repaymentDiv, int p_repaymentNum ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      market_code = p_marketCode;
      repayment_div = p_repaymentDiv;
      repayment_num = p_repaymentNum;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static BranchMarketRepayDealtCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BranchMarketRepayDealtCondPK pk = new BranchMarketRepayDealtCondPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.repayment_div = st.nextToken();
    pk.repayment_num = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + market_code + "," + repayment_div + "," + String.valueOf(repayment_num);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BranchMarketRepayDealtCondPK) )
      return false;
    BranchMarketRepayDealtCondPK o = (BranchMarketRepayDealtCondPK) other;
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
    if ( repayment_div == null ) {
      if ( o.repayment_div != null )
        return false;
    } else if ( !repayment_div.equals( o.repayment_div ) ) {
        return false;
    }
    if ( repayment_num != o.repayment_num )
      return false;
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
        + (repayment_div!=null? repayment_div.hashCode(): 0) 
        + ((int) repayment_num)
    ;
  }

}

@
