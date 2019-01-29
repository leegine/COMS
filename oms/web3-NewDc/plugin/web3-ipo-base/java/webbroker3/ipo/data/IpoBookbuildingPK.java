head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>IpoBookbuilding</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>IpoBookbuilding</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>IpoBookbuilding</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoBookbuildingRow 
 */
public class IpoBookbuildingPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ipo_bookbuilding";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: IpoBookbuildingRow. 
   */
  public RowType getRowType() {
    return IpoBookbuildingRow.TYPE;
  }

  /**
   * <em>ipo_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long ipo_product_id;
  /**
   * <em>bb_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String bb_div;
  /**
   * <em>bb_seq</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long bb_seq;
  /**
   * <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long branch_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public IpoBookbuildingPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_ipoProductId <em>ipo_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_bbDiv <em>bb_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_bbSeq <em>bb_seq</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public IpoBookbuildingPK( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) {
      ipo_product_id = p_ipoProductId;
      bb_div = p_bbDiv;
      bb_seq = p_bbSeq;
      branch_id = p_branchId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static IpoBookbuildingPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IpoBookbuildingPK pk = new IpoBookbuildingPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.ipo_product_id = Long.valueOf(st.nextToken()).longValue();
    pk.bb_div = st.nextToken();
    pk.bb_seq = Long.valueOf(st.nextToken()).longValue();
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(ipo_product_id) + "," + bb_div + "," + String.valueOf(bb_seq) + "," + String.valueOf(branch_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IpoBookbuildingPK) )
      return false;
    IpoBookbuildingPK o = (IpoBookbuildingPK) other;
    if ( ipo_product_id != o.ipo_product_id )
      return false;
    if ( bb_div == null ) {
      if ( o.bb_div != null )
        return false;
    } else if ( !bb_div.equals( o.bb_div ) ) {
        return false;
    }
    if ( bb_seq != o.bb_seq )
      return false;
    if ( branch_id != o.branch_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) ipo_product_id)
        + (bb_div!=null? bb_div.hashCode(): 0) 
        + ((int) bb_seq)
        + ((int) branch_id)
    ;
  }

}

@
