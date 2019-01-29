head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MainAccountAllPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MainAccountAll</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MainAccountAll</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MainAccountAll</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountAllRow 
 */
public class MainAccountAllPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "main_account_all";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MainAccountAllRow. 
   */
  public RowType getRowType() {
    return MainAccountAllRow.TYPE;
  }

  /**
   * <em>comp_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String comp_code;
  /**
   * <em>br_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String br_code;
  /**
   * <em>cust_code</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public String cust_code;
  /**
   * <em>cust_code_cd</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String cust_code_cd;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MainAccountAllPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_compCode <em>comp_code</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_brCode <em>br_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_custCode <em>cust_code</em>�J�����̒l������킷6���ȉ���String�l 
   * @@param p_custCodeCd <em>cust_code_cd</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public MainAccountAllPK( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) {
      comp_code = p_compCode;
      br_code = p_brCode;
      cust_code = p_custCode;
      cust_code_cd = p_custCodeCd;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MainAccountAllPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MainAccountAllPK pk = new MainAccountAllPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.comp_code = st.nextToken();
    pk.br_code = st.nextToken();
    pk.cust_code = st.nextToken();
    pk.cust_code_cd = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = comp_code + "," + br_code + "," + cust_code + "," + cust_code_cd;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MainAccountAllPK) )
      return false;
    MainAccountAllPK o = (MainAccountAllPK) other;
    if ( comp_code == null ) {
      if ( o.comp_code != null )
        return false;
    } else if ( !comp_code.equals( o.comp_code ) ) {
        return false;
    }
    if ( br_code == null ) {
      if ( o.br_code != null )
        return false;
    } else if ( !br_code.equals( o.br_code ) ) {
        return false;
    }
    if ( cust_code == null ) {
      if ( o.cust_code != null )
        return false;
    } else if ( !cust_code.equals( o.cust_code ) ) {
        return false;
    }
    if ( cust_code_cd == null ) {
      if ( o.cust_code_cd != null )
        return false;
    } else if ( !cust_code_cd.equals( o.cust_code_cd ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (comp_code!=null? comp_code.hashCode(): 0) 
        + (br_code!=null? br_code.hashCode(): 0) 
        + (cust_code!=null? cust_code.hashCode(): 0) 
        + (cust_code_cd!=null? cust_code_cd.hashCode(): 0) 
    ;
  }

}

@
