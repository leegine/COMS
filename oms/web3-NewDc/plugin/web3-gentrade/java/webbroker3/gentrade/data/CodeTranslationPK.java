head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.22.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CodeTranslationPK.java;


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
 * <b>CodeTranslation</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>CodeTranslation</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>CodeTranslation</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CodeTranslationRow 
 */
public class CodeTranslationPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "code_translation";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: CodeTranslationRow. 
   */
  public RowType getRowType() {
    return CodeTranslationRow.TYPE;
  }

  /**
   * <em>code_type</em>�J�����̒l������킷64���ȉ���String�l 
   */
  public String code_type;
  /**
   * <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>code</em>�J�����̒l������킷64���ȉ���String�l 
   */
  public String code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public CodeTranslationPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_codeType <em>code_type</em>�J�����̒l������킷64���ȉ���String�l 
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_code <em>code</em>�J�����̒l������킷64���ȉ���String�l 
   */
  public CodeTranslationPK( String p_codeType, String p_institutionCode, String p_code ) {
      code_type = p_codeType;
      institution_code = p_institutionCode;
      code = p_code;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static CodeTranslationPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CodeTranslationPK pk = new CodeTranslationPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.code_type = st.nextToken();
    pk.institution_code = st.nextToken();
    pk.code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = code_type + "," + institution_code + "," + code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CodeTranslationPK) )
      return false;
    CodeTranslationPK o = (CodeTranslationPK) other;
    if ( code_type == null ) {
      if ( o.code_type != null )
        return false;
    } else if ( !code_type.equals( o.code_type ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( code == null ) {
      if ( o.code != null )
        return false;
    } else if ( !code.equals( o.code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (code_type!=null? code_type.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (code!=null? code.hashCode(): 0) 
    ;
  }

}

@
