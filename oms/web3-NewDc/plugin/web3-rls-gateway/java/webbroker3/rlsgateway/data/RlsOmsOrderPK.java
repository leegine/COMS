head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOmsOrderPK.java;


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
 * <b>RlsOmsOrder</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>RlsOmsOrder</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>RlsOmsOrder</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsOmsOrderRow 
 */
public class RlsOmsOrderPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rls_oms_order";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: RlsOmsOrderRow. 
   */
  public RowType getRowType() {
    return RlsOmsOrderRow.TYPE;
  }

  /**
   * <em>ord_id</em>�J�����̒l������킷long�l
   */
  public long ord_id;
  /**
   * <em>ord_type</em>�J�����̒l������킷long�l
   */
  public long ord_type;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public RlsOmsOrderPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_ordId <em>ord_id</em>�J�����̒l������킷long�l
   * @@param p_ordType <em>ord_type</em>�J�����̒l������킷long�l
   */
  public RlsOmsOrderPK( long p_ordId, long p_ordType ) {
      ord_id = p_ordId;
      ord_type = p_ordType;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static RlsOmsOrderPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsOmsOrderPK pk = new RlsOmsOrderPK();
    int i = pkValueString.indexOf(',');
    pk.ord_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.ord_type = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(ord_id) + "," + String.valueOf(ord_type);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsOmsOrderPK) )
      return false;
    RlsOmsOrderPK o = (RlsOmsOrderPK) other;
    if ( ord_id != o.ord_id )
      return false;
    if ( ord_type != o.ord_type )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) ord_id)
        + ((int) ord_type)
    ;
  }

}

@
