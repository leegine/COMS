head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapRow.java;


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
 * AffinityRangeBasedMapRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>affinity_range_based_map</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link AffinityRangeBasedMapRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityRangeBasedMapPK 
 */
public interface AffinityRangeBasedMapRow extends Row {


  /** 
   * ����{@@link AffinityRangeBasedMapRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "affinity_range_based_map", "rac-config" );


  /** 
   * <em>key_start</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getKeyStart();


  /** 
   * <em>key_start</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getKeyStartIsSet();


  /** 
   * <em>key_start</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getKeyStartIsModified();


  /** 
   * <em>key_end</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getKeyEnd();


  /** 
   * <em>key_end</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getKeyEndIsSet();


  /** 
   * <em>key_end</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getKeyEndIsModified();


  /** 
   * <em>range_order_no</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRangeOrderNo();


  /** 
   * <em>range_order_no</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRangeOrderNoIsSet();


  /** 
   * <em>range_order_no</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRangeOrderNoIsModified();


  /** 
   * <em>server_type</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getServerType();


  /** 
   * <em>server_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getServerTypeIsSet();


  /** 
   * <em>server_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getServerTypeIsModified();


  /** 
   * <em>server_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getServerId();


  /** 
   * <em>server_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getServerIdIsSet();


  /** 
   * <em>server_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getServerIdIsModified();


  /** 
   * <em>ctx_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCtxName();


  /** 
   * <em>ctx_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCtxNameIsSet();


  /** 
   * <em>ctx_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCtxNameIsModified();


}
@
