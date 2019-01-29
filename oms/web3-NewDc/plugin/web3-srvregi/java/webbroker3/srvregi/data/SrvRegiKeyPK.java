head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiKeyPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>SrvRegiKey</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>SrvRegiKey</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>SrvRegiKey</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiKeyRow 
 */
public class SrvRegiKeyPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "srv_regi_key";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: SrvRegiKeyRow. 
   */
  public RowType getRowType() {
    return SrvRegiKeyRow.TYPE;
  }

  /**
   * <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String institution_code;
  /**
   * <em>srv_div</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public String srv_div;
  /**
   * <em>srv_use_key_type</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public String srv_use_key_type;
  /**
   * <em>srv_use_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long srv_use_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public SrvRegiKeyPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_srvDiv <em>srv_div</em>�J�����̒l������킷4���ȉ���String�l 
   * @@param p_srvUseKeyType <em>srv_use_key_type</em>�J�����̒l������킷2���ȉ���String�l 
   * @@param p_srvUseId <em>srv_use_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public SrvRegiKeyPK( String p_institutionCode, String p_srvDiv, String p_srvUseKeyType, long p_srvUseId ) {
      institution_code = p_institutionCode;
      srv_div = p_srvDiv;
      srv_use_key_type = p_srvUseKeyType;
      srv_use_id = p_srvUseId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static SrvRegiKeyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SrvRegiKeyPK pk = new SrvRegiKeyPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.srv_div = st.nextToken();
    pk.srv_use_key_type = st.nextToken();
    pk.srv_use_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + srv_div + "," + srv_use_key_type + "," + String.valueOf(srv_use_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SrvRegiKeyPK) )
      return false;
    SrvRegiKeyPK o = (SrvRegiKeyPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( o.srv_div != null )
        return false;
    } else if ( !srv_div.equals( o.srv_div ) ) {
        return false;
    }
    if ( srv_use_key_type == null ) {
      if ( o.srv_use_key_type != null )
        return false;
    } else if ( !srv_use_key_type.equals( o.srv_use_key_type ) ) {
        return false;
    }
    if ( srv_use_id != o.srv_use_id )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + (srv_use_key_type!=null? srv_use_key_type.hashCode(): 0) 
        + ((int) srv_use_id)
    ;
  }

}

@
