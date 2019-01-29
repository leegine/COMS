head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvWeekPK.java;


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
 * <b>OtherOrgOutOfSrvWeek</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>OtherOrgOutOfSrvWeek</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>OtherOrgOutOfSrvWeek</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgOutOfSrvWeekRow 
 */
public class OtherOrgOutOfSrvWeekPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "other_org_out_of_srv_week";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: OtherOrgOutOfSrvWeekRow. 
   */
  public RowType getRowType() {
    return OtherOrgOutOfSrvWeekRow.TYPE;
  }

  /**
   * <em>other_org_code</em>�J�����̒l������킷11���ȉ���String�l 
   */
  public String other_org_code;
  /**
   * <em>month</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String month;
  /**
   * <em>week_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String week_div;
  /**
   * <em>week_no</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String week_no;
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
  public OtherOrgOutOfSrvWeekPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_otherOrgCode <em>other_org_code</em>�J�����̒l������킷11���ȉ���String�l 
   * @@param p_month <em>month</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_weekDiv <em>week_div</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_weekNo <em>week_no</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_suspensionStartTime <em>suspension_start_time</em>�J�����̒l������킷6���ȉ���String�l 
   * @@param p_suspensionEndTime <em>suspension_end_time</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public OtherOrgOutOfSrvWeekPK( String p_otherOrgCode, String p_month, String p_weekDiv, String p_weekNo, String p_suspensionStartTime, String p_suspensionEndTime ) {
      other_org_code = p_otherOrgCode;
      month = p_month;
      week_div = p_weekDiv;
      week_no = p_weekNo;
      suspension_start_time = p_suspensionStartTime;
      suspension_end_time = p_suspensionEndTime;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static OtherOrgOutOfSrvWeekPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OtherOrgOutOfSrvWeekPK pk = new OtherOrgOutOfSrvWeekPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.other_org_code = st.nextToken();
    pk.month = st.nextToken();
    pk.week_div = st.nextToken();
    pk.week_no = st.nextToken();
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
      m_id = other_org_code + "," + month + "," + week_div + "," + week_no + "," + suspension_start_time + "," + suspension_end_time;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OtherOrgOutOfSrvWeekPK) )
      return false;
    OtherOrgOutOfSrvWeekPK o = (OtherOrgOutOfSrvWeekPK) other;
    if ( other_org_code == null ) {
      if ( o.other_org_code != null )
        return false;
    } else if ( !other_org_code.equals( o.other_org_code ) ) {
        return false;
    }
    if ( month == null ) {
      if ( o.month != null )
        return false;
    } else if ( !month.equals( o.month ) ) {
        return false;
    }
    if ( week_div == null ) {
      if ( o.week_div != null )
        return false;
    } else if ( !week_div.equals( o.week_div ) ) {
        return false;
    }
    if ( week_no == null ) {
      if ( o.week_no != null )
        return false;
    } else if ( !week_no.equals( o.week_no ) ) {
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
        + (month!=null? month.hashCode(): 0) 
        + (week_div!=null? week_div.hashCode(): 0) 
        + (week_no!=null? week_no.hashCode(): 0) 
        + (suspension_start_time!=null? suspension_start_time.hashCode(): 0) 
        + (suspension_end_time!=null? suspension_end_time.hashCode(): 0) 
    ;
  }

}

@
