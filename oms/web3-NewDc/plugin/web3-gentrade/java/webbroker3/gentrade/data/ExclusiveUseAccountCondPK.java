head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExclusiveUseAccountCondPK.java;


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
 * <b>ExclusiveUseAccountCond</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>ExclusiveUseAccountCond</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>ExclusiveUseAccountCond</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ExclusiveUseAccountCondRow 
 */
public class ExclusiveUseAccountCondPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "exclusive_use_account_cond";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: ExclusiveUseAccountCondRow. 
   */
  public RowType getRowType() {
    return ExclusiveUseAccountCondRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>fin_institution_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public String fin_institution_code;
  /**
   * <em>fin_branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String fin_branch_code;
  /**
   * <em>fin_account_no</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String fin_account_no;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public ExclusiveUseAccountCondPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_finInstitutionCode <em>fin_institution_code</em>�J�����̒l������킷4���ȉ���String�l 
   * @@param p_finBranchCode <em>fin_branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_finAccountNo <em>fin_account_no</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public ExclusiveUseAccountCondPK( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) {
      institution_code = p_institutionCode;
      fin_institution_code = p_finInstitutionCode;
      fin_branch_code = p_finBranchCode;
      fin_account_no = p_finAccountNo;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static ExclusiveUseAccountCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    ExclusiveUseAccountCondPK pk = new ExclusiveUseAccountCondPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.fin_institution_code = st.nextToken();
    pk.fin_branch_code = st.nextToken();
    pk.fin_account_no = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + fin_institution_code + "," + fin_branch_code + "," + fin_account_no;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof ExclusiveUseAccountCondPK) )
      return false;
    ExclusiveUseAccountCondPK o = (ExclusiveUseAccountCondPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( fin_institution_code == null ) {
      if ( o.fin_institution_code != null )
        return false;
    } else if ( !fin_institution_code.equals( o.fin_institution_code ) ) {
        return false;
    }
    if ( fin_branch_code == null ) {
      if ( o.fin_branch_code != null )
        return false;
    } else if ( !fin_branch_code.equals( o.fin_branch_code ) ) {
        return false;
    }
    if ( fin_account_no == null ) {
      if ( o.fin_account_no != null )
        return false;
    } else if ( !fin_account_no.equals( o.fin_account_no ) ) {
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
        + (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
        + (fin_account_no!=null? fin_account_no.hashCode(): 0) 
    ;
  }

}

@
