head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFund2ndProductSonarPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MutualFund2ndProductSonar</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>MutualFund2ndProductSonar</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>MutualFund2ndProductSonar</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFund2ndProductSonarRow 
 */
public class MutualFund2ndProductSonarPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mutual_fund_2nd_product_sonar";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: MutualFund2ndProductSonarRow. 
   */
  public RowType getRowType() {
    return MutualFund2ndProductSonarRow.TYPE;
  }

  /**
   * <em>product_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public String product_code;
  /**
   * <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String institution_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public MutualFund2ndProductSonarPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷7���ȉ���String�l 
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public MutualFund2ndProductSonarPK( String p_productCode, String p_institutionCode ) {
      product_code = p_productCode;
      institution_code = p_institutionCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static MutualFund2ndProductSonarPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MutualFund2ndProductSonarPK pk = new MutualFund2ndProductSonarPK();
    int i = pkValueString.indexOf(',');
    pk.product_code = pkValueString.substring(0,i);
    pk.institution_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = product_code + "," + institution_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MutualFund2ndProductSonarPK) )
      return false;
    MutualFund2ndProductSonarPK o = (MutualFund2ndProductSonarPK) other;
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (product_code!=null? product_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
    ;
  }

}

@
