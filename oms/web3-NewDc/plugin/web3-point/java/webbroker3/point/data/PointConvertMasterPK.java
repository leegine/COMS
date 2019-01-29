head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointConvertMasterPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>PointConvertMaster</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>PointConvertMaster</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>PointConvertMaster</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointConvertMasterRow 
 */
public class PointConvertMasterPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "point_convert_master";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: PointConvertMasterRow. 
   */
  public RowType getRowType() {
    return PointConvertMasterRow.TYPE;
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
   * <em>fund_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String fund_type;
  /**
   * <em>tran_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String tran_type;
  /**
   * <em>buy_sell_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String buy_sell_div;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public PointConvertMasterPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_fundType <em>fund_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_tranType <em>tran_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_buySellDiv <em>buy_sell_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public PointConvertMasterPK( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      fund_type = p_fundType;
      tran_type = p_tranType;
      buy_sell_div = p_buySellDiv;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static PointConvertMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    PointConvertMasterPK pk = new PointConvertMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.fund_type = st.nextToken();
    pk.tran_type = st.nextToken();
    pk.buy_sell_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + fund_type + "," + tran_type + "," + buy_sell_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof PointConvertMasterPK) )
      return false;
    PointConvertMasterPK o = (PointConvertMasterPK) other;
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
    if ( fund_type == null ) {
      if ( o.fund_type != null )
        return false;
    } else if ( !fund_type.equals( o.fund_type ) ) {
        return false;
    }
    if ( tran_type == null ) {
      if ( o.tran_type != null )
        return false;
    } else if ( !tran_type.equals( o.tran_type ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( o.buy_sell_div != null )
        return false;
    } else if ( !buy_sell_div.equals( o.buy_sell_div ) ) {
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
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (tran_type!=null? tran_type.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
    ;
  }

}

@
