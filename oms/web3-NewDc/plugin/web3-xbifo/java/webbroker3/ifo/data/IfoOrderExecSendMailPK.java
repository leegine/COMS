head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoOrderExecSendMailPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * <b>IfoOrderExecSendMail</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>IfoOrderExecSendMail</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>IfoOrderExecSendMail</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoOrderExecSendMailRow 
 */
public class IfoOrderExecSendMailPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ifo_order_exec_send_mail";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: IfoOrderExecSendMailRow. 
   */
  public RowType getRowType() {
    return IfoOrderExecSendMailRow.TYPE;
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
   * <em>order_request_number</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public String order_request_number;
  /**
   * <em>order_exec_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public String order_exec_status;
  /**
   * <em>order_action_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long order_action_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public IfoOrderExecSendMailPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_orderRequestNumber <em>order_request_number</em>�J�����̒l������킷9���ȉ���String�l 
   * @@param p_orderExecStatus <em>order_exec_status</em>�J�����̒l������킷1���ȉ���String�l 
   * @@param p_orderActionId <em>order_action_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public IfoOrderExecSendMailPK( String p_institutionCode, String p_branchCode, String p_orderRequestNumber, String p_orderExecStatus, long p_orderActionId ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      order_request_number = p_orderRequestNumber;
      order_exec_status = p_orderExecStatus;
      order_action_id = p_orderActionId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static IfoOrderExecSendMailPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IfoOrderExecSendMailPK pk = new IfoOrderExecSendMailPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.order_request_number = st.nextToken();
    pk.order_exec_status = st.nextToken();
    pk.order_action_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + order_request_number + "," + order_exec_status + "," + String.valueOf(order_action_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IfoOrderExecSendMailPK) )
      return false;
    IfoOrderExecSendMailPK o = (IfoOrderExecSendMailPK) other;
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
    if ( order_request_number == null ) {
      if ( o.order_request_number != null )
        return false;
    } else if ( !order_request_number.equals( o.order_request_number ) ) {
        return false;
    }
    if ( order_exec_status == null ) {
      if ( o.order_exec_status != null )
        return false;
    } else if ( !order_exec_status.equals( o.order_exec_status ) ) {
        return false;
    }
    if ( order_action_id != o.order_action_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (order_exec_status!=null? order_exec_status.hashCode(): 0) 
        + ((int) order_action_id)
    ;
  }

}

@
