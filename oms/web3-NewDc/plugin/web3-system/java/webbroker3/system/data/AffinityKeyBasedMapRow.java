head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * AffinityKeyBasedMapRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>affinity_key_based_map</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link AffinityKeyBasedMapRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityKeyBasedMapPK 
 */
public interface AffinityKeyBasedMapRow extends Row {


  /** 
   * ����{@@link AffinityKeyBasedMapRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "affinity_key_based_map", "rac-config" );


  /** 
   * <em>key</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getKey();


  /** 
   * <em>key</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getKeyIsSet();


  /** 
   * <em>key</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getKeyIsModified();


  /** 
   * <em>app_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAppId();


  /** 
   * <em>app_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAppIdIsSet();


  /** 
   * <em>app_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAppIdIsModified();


  /** 
   * <em>db_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDbId();


  /** 
   * <em>db_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDbIdIsSet();


  /** 
   * <em>db_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDbIdIsModified();


}
@
