head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.45.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankSettleResultResponseRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * BankSettleResultResponseRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>bank_settle_result_response</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link BankSettleResultResponseRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankSettleResultResponsePK 
 */
public interface BankSettleResultResponseRow extends Row {


  /** 
   * ����{@@link BankSettleResultResponseRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "bank_settle_result_response", "session" );


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>protocol_version</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProtocolVersion();


  /** 
   * <em>protocol_version</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProtocolVersionIsSet();


  /** 
   * <em>protocol_version</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProtocolVersionIsModified();


  /** 
   * <em>linked_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLinked1();


  /** 
   * <em>linked_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLinked1IsSet();


  /** 
   * <em>linked_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLinked1IsModified();


  /** 
   * <em>shop_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShopId();


  /** 
   * <em>shop_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShopIdIsSet();


  /** 
   * <em>shop_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShopIdIsModified();


  /** 
   * <em>order_date_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderDateTime();


  /** 
   * <em>order_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderDateTimeIsSet();


  /** 
   * <em>order_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderDateTimeIsModified();


  /** 
   * <em>center_pay_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCenterPayId();


  /** 
   * <em>center_pay_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCenterPayIdIsSet();


  /** 
   * <em>center_pay_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCenterPayIdIsModified();


  /** 
   * <em>comondebi_sales_slip</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComondebiSalesSlip();


  /** 
   * <em>comondebi_sales_slip</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiSalesSlipIsSet();


  /** 
   * <em>comondebi_sales_slip</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiSalesSlipIsModified();


  /** 
   * <em>comondebi_auth_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComondebiAuthDate();


  /** 
   * <em>comondebi_auth_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiAuthDateIsSet();


  /** 
   * <em>comondebi_auth_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiAuthDateIsModified();


  /** 
   * <em>pay_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPayStatus();


  /** 
   * <em>pay_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPayStatusIsSet();


  /** 
   * <em>pay_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPayStatusIsModified();


  /** 
   * <em>comondebi_authres_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComondebiAuthresCode();


  /** 
   * <em>comondebi_authres_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiAuthresCodeIsSet();


  /** 
   * <em>comondebi_authres_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiAuthresCodeIsModified();


  /** 
   * <em>comondebi_capture_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getComondebiCaptureDate();


  /** 
   * <em>comondebi_capture_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiCaptureDateIsSet();


  /** 
   * <em>comondebi_capture_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getComondebiCaptureDateIsModified();


  /** 
   * <em>amount</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAmount();


  /** 
   * <em>amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAmountIsSet();


  /** 
   * <em>amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAmountIsModified();


  /** 
   * <em>pay_scheme_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPaySchemeId();


  /** 
   * <em>pay_scheme_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaySchemeIdIsSet();


  /** 
   * <em>pay_scheme_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPaySchemeIdIsModified();


  /** 
   * <em>access_key</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccessKey();


  /** 
   * <em>access_key</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccessKeyIsSet();


  /** 
   * <em>access_key</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccessKeyIsModified();


  /** 
   * <em>description</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDescription();


  /** 
   * <em>description</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDescriptionIsSet();


  /** 
   * <em>description</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDescriptionIsModified();


  /** 
   * <em>rowid</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRowid();


  /** 
   * <em>rowid</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRowidIsSet();


  /** 
   * <em>rowid</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRowidIsModified();


}
@
