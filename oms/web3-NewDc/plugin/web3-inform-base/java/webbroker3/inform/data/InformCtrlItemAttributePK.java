head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlItemAttributePK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>InformCtrlItemAttribute</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>InformCtrlItemAttribute</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>InformCtrlItemAttribute</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InformCtrlItemAttributeRow 
 */
public class InformCtrlItemAttributePK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "inform_ctrl_item_attribute";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: InformCtrlItemAttributeRow. 
   */
  public RowType getRowType() {
    return InformCtrlItemAttributeRow.TYPE;
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
   * <em>inform_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String inform_div;
  /**
   * <em>item_symbol_name</em>�J�����̒l������킷30���ȉ���String�l 
   */
  public String item_symbol_name;
  /**
   * <em>attribute_value</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String attribute_value;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public InformCtrlItemAttributePK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_informDiv <em>inform_div</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_itemSymbolName <em>item_symbol_name</em>�J�����̒l������킷30���ȉ���String�l 
   * @@param p_attributeValue <em>attribute_value</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public InformCtrlItemAttributePK( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      inform_div = p_informDiv;
      item_symbol_name = p_itemSymbolName;
      attribute_value = p_attributeValue;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static InformCtrlItemAttributePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    InformCtrlItemAttributePK pk = new InformCtrlItemAttributePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.inform_div = st.nextToken();
    pk.item_symbol_name = st.nextToken();
    pk.attribute_value = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + inform_div + "," + item_symbol_name + "," + attribute_value;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof InformCtrlItemAttributePK) )
      return false;
    InformCtrlItemAttributePK o = (InformCtrlItemAttributePK) other;
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
    if ( inform_div == null ) {
      if ( o.inform_div != null )
        return false;
    } else if ( !inform_div.equals( o.inform_div ) ) {
        return false;
    }
    if ( item_symbol_name == null ) {
      if ( o.item_symbol_name != null )
        return false;
    } else if ( !item_symbol_name.equals( o.item_symbol_name ) ) {
        return false;
    }
    if ( attribute_value == null ) {
      if ( o.attribute_value != null )
        return false;
    } else if ( !attribute_value.equals( o.attribute_value ) ) {
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
        + (inform_div!=null? inform_div.hashCode(): 0) 
        + (item_symbol_name!=null? item_symbol_name.hashCode(): 0) 
        + (attribute_value!=null? attribute_value.hashCode(): 0) 
    ;
  }

}

@
