head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoProductManagementPK.java;


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
 * <b>BatoProductManagement</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>BatoProductManagement</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>BatoProductManagement</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BatoProductManagementRow 
 */
public class BatoProductManagementPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bato_product_management";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: BatoProductManagementRow. 
   */
  public RowType getRowType() {
    return BatoProductManagementRow.TYPE;
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
   * <em>document_div</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String document_div;
  /**
   * <em>bato_product_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String bato_product_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public BatoProductManagementPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_documentDiv <em>document_div</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_batoProductCode <em>bato_product_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public BatoProductManagementPK( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_batoProductCode ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      document_div = p_documentDiv;
      bato_product_code = p_batoProductCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static BatoProductManagementPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BatoProductManagementPK pk = new BatoProductManagementPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.document_div = st.nextToken();
    pk.bato_product_code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + document_div + "," + bato_product_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BatoProductManagementPK) )
      return false;
    BatoProductManagementPK o = (BatoProductManagementPK) other;
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
    if ( document_div == null ) {
      if ( o.document_div != null )
        return false;
    } else if ( !document_div.equals( o.document_div ) ) {
        return false;
    }
    if ( bato_product_code == null ) {
      if ( o.bato_product_code != null )
        return false;
    } else if ( !bato_product_code.equals( o.bato_product_code ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (document_div!=null? document_div.hashCode(): 0) 
        + (bato_product_code!=null? bato_product_code.hashCode(): 0) 
    ;
  }

}

@
