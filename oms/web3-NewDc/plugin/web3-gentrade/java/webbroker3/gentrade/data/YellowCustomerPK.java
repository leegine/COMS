head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.26.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	YellowCustomerPK.java;


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
 * <b>YellowCustomer</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>YellowCustomer</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>YellowCustomer</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see YellowCustomerRow 
 */
public class YellowCustomerPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "yellow_customer";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: YellowCustomerRow. 
   */
  public RowType getRowType() {
    return YellowCustomerRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>era_born</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String era_born;
  /**
   * <em>born_date</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public String born_date;
  /**
   * <em>name_kana</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public String name_kana;
  /**
   * <em>name</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public String name;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public YellowCustomerPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_eraBorn <em>era_born</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_bornDate <em>born_date</em>�J�����̒l������킷6���ȉ���String�l 
   * @@param p_nameKana <em>name_kana</em>�J�����̒l������킷40���ȉ���String�l 
   * @@param p_name <em>name</em>�J�����̒l������킷40���ȉ���String�l 
   */
  public YellowCustomerPK( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) {
      institution_code = p_institutionCode;
      era_born = p_eraBorn;
      born_date = p_bornDate;
      name_kana = p_nameKana;
      name = p_name;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static YellowCustomerPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    YellowCustomerPK pk = new YellowCustomerPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.era_born = st.nextToken();
    pk.born_date = st.nextToken();
    pk.name_kana = st.nextToken();
    pk.name = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + era_born + "," + born_date + "," + name_kana + "," + name;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof YellowCustomerPK) )
      return false;
    YellowCustomerPK o = (YellowCustomerPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( era_born == null ) {
      if ( o.era_born != null )
        return false;
    } else if ( !era_born.equals( o.era_born ) ) {
        return false;
    }
    if ( born_date == null ) {
      if ( o.born_date != null )
        return false;
    } else if ( !born_date.equals( o.born_date ) ) {
        return false;
    }
    if ( name_kana == null ) {
      if ( o.name_kana != null )
        return false;
    } else if ( !name_kana.equals( o.name_kana ) ) {
        return false;
    }
    if ( name == null ) {
      if ( o.name != null )
        return false;
    } else if ( !name.equals( o.name ) ) {
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
        + (era_born!=null? era_born.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (name_kana!=null? name_kana.hashCode(): 0) 
        + (name!=null? name.hashCode(): 0) 
    ;
  }

}

@
