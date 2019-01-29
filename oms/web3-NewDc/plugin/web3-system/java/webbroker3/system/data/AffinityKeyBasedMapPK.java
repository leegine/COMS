head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>AffinityKeyBasedMap</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>AffinityKeyBasedMap</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>AffinityKeyBasedMap</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityKeyBasedMapRow 
 */
public class AffinityKeyBasedMapPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "affinity_key_based_map";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: AffinityKeyBasedMapRow. 
   */
  public RowType getRowType() {
    return AffinityKeyBasedMapRow.TYPE;
  }

  /**
   * <em>app_id</em>�J�����̒l������킷18���ȉ���String�l 
   */
  public String app_id;
  /**
   * <em>db_id</em>�J�����̒l������킷18���ȉ���String�l 
   */
  public String db_id;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public AffinityKeyBasedMapPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_appId <em>app_id</em>�J�����̒l������킷18���ȉ���String�l 
   * @@param p_dbId <em>db_id</em>�J�����̒l������킷18���ȉ���String�l 
   */
  public AffinityKeyBasedMapPK( String p_appId, String p_dbId ) {
      app_id = p_appId;
      db_id = p_dbId;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static AffinityKeyBasedMapPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AffinityKeyBasedMapPK pk = new AffinityKeyBasedMapPK();
    int i = pkValueString.indexOf(',');
    pk.app_id = pkValueString.substring(0,i);
    pk.db_id = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = app_id + "," + db_id;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AffinityKeyBasedMapPK) )
      return false;
    AffinityKeyBasedMapPK o = (AffinityKeyBasedMapPK) other;
    if ( app_id == null ) {
      if ( o.app_id != null )
        return false;
    } else if ( !app_id.equals( o.app_id ) ) {
        return false;
    }
    if ( db_id == null ) {
      if ( o.db_id != null )
        return false;
    } else if ( !db_id.equals( o.db_id ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (app_id!=null? app_id.hashCode(): 0) 
        + (db_id!=null? db_id.hashCode(): 0) 
    ;
  }

}

@
