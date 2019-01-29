head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsPriceCondPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>RlsPriceCond</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>RlsPriceCond</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>RlsPriceCond</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsPriceCondRow 
 */
public class RlsPriceCondPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rls_price_cond";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: RlsPriceCondRow. 
   */
  public RowType getRowType() {
    return RlsPriceCondRow.TYPE;
  }

  /**
   * <em>cond_ord_id</em>�J�����̒l������킷long�l
   */
  public long cond_ord_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public RlsPriceCondPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_condOrdId <em>cond_ord_id</em>�J�����̒l������킷long�l
   */
  public RlsPriceCondPK( long p_condOrdId ) {
      cond_ord_id = p_condOrdId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static RlsPriceCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsPriceCondPK pk = new RlsPriceCondPK();
    pk.cond_ord_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(cond_ord_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsPriceCondPK) )
      return false;
    RlsPriceCondPK o = (RlsPriceCondPK) other;
    if ( cond_ord_id != o.cond_ord_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) cond_ord_id)
    ;
  }

}

@
