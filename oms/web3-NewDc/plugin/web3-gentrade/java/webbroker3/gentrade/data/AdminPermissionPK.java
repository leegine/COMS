head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.43.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdminPermissionPK.java;


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
 * <b>AdminPermission</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>AdminPermission</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>AdminPermission</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdminPermissionRow 
 */
public class AdminPermissionPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "admin_permission";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: AdminPermissionRow. 
   */
  public RowType getRowType() {
    return AdminPermissionRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>permission_level</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String permission_level;
  /**
   * <em>transaction_category</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public String transaction_category;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public AdminPermissionPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_permissionLevel <em>permission_level</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_transactionCategory <em>transaction_category</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public AdminPermissionPK( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) {
      institution_code = p_institutionCode;
      permission_level = p_permissionLevel;
      transaction_category = p_transactionCategory;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static AdminPermissionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AdminPermissionPK pk = new AdminPermissionPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.permission_level = st.nextToken();
    pk.transaction_category = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + permission_level + "," + transaction_category;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AdminPermissionPK) )
      return false;
    AdminPermissionPK o = (AdminPermissionPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( permission_level == null ) {
      if ( o.permission_level != null )
        return false;
    } else if ( !permission_level.equals( o.permission_level ) ) {
        return false;
    }
    if ( transaction_category == null ) {
      if ( o.transaction_category != null )
        return false;
    } else if ( !transaction_category.equals( o.transaction_category ) ) {
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
        + (permission_level!=null? permission_level.hashCode(): 0) 
        + (transaction_category!=null? transaction_category.hashCode(): 0) 
    ;
  }

}

@
