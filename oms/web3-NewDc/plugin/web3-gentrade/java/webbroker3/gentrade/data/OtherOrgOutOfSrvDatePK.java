head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.37.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvDatePK.java;


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
 * <b>OtherOrgOutOfSrvDate</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>OtherOrgOutOfSrvDate</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>OtherOrgOutOfSrvDate</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgOutOfSrvDateRow 
 */
public class OtherOrgOutOfSrvDatePK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "other_org_out_of_srv_date";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: OtherOrgOutOfSrvDateRow. 
   */
  public RowType getRowType() {
    return OtherOrgOutOfSrvDateRow.TYPE;
  }

  /**
   * <em>other_org_code</em>�J�����̒l������킷11���ȉ���String�l 
   */
  public String other_org_code;
  /**
   * <em>suspension_date</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public String suspension_date;
  /**
   * <em>suspension_start_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public String suspension_start_time;
  /**
   * <em>suspension_end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public String suspension_end_time;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public OtherOrgOutOfSrvDatePK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_otherOrgCode <em>other_org_code</em>�J�����̒l������킷11���ȉ���String�l 
   * @@param p_suspensionDate <em>suspension_date</em>�J�����̒l������킷4���ȉ���String�l 
   * @@param p_suspensionStartTime <em>suspension_start_time</em>�J�����̒l������킷6���ȉ���String�l 
   * @@param p_suspensionEndTime <em>suspension_end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public OtherOrgOutOfSrvDatePK( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) {
      other_org_code = p_otherOrgCode;
      suspension_date = p_suspensionDate;
      suspension_start_time = p_suspensionStartTime;
      suspension_end_time = p_suspensionEndTime;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static OtherOrgOutOfSrvDatePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OtherOrgOutOfSrvDatePK pk = new OtherOrgOutOfSrvDatePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.other_org_code = st.nextToken();
    pk.suspension_date = st.nextToken();
    pk.suspension_start_time = st.nextToken();
    pk.suspension_end_time = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = other_org_code + "," + suspension_date + "," + suspension_start_time + "," + suspension_end_time;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OtherOrgOutOfSrvDatePK) )
      return false;
    OtherOrgOutOfSrvDatePK o = (OtherOrgOutOfSrvDatePK) other;
    if ( other_org_code == null ) {
      if ( o.other_org_code != null )
        return false;
    } else if ( !other_org_code.equals( o.other_org_code ) ) {
        return false;
    }
    if ( suspension_date == null ) {
      if ( o.suspension_date != null )
        return false;
    } else if ( !suspension_date.equals( o.suspension_date ) ) {
        return false;
    }
    if ( suspension_start_time == null ) {
      if ( o.suspension_start_time != null )
        return false;
    } else if ( !suspension_start_time.equals( o.suspension_start_time ) ) {
        return false;
    }
    if ( suspension_end_time == null ) {
      if ( o.suspension_end_time != null )
        return false;
    } else if ( !suspension_end_time.equals( o.suspension_end_time ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (other_org_code!=null? other_org_code.hashCode(): 0) 
        + (suspension_date!=null? suspension_date.hashCode(): 0) 
        + (suspension_start_time!=null? suspension_start_time.hashCode(): 0) 
        + (suspension_end_time!=null? suspension_end_time.hashCode(): 0) 
    ;
  }

}

@
