head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostRuitoSellRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * HostRuitoSellRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>host_ruito_sell</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link HostRuitoSellRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostRuitoSellPK 
 */
public interface HostRuitoSellRow extends Row {


  /** 
   * ����{@@link HostRuitoSellRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "host_ruito_sell", "session" );


  /** 
   * <em>request_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRequestCode();


  /** 
   * <em>request_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRequestCodeIsSet();


  /** 
   * <em>request_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRequestCodeIsModified();


  /** 
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>trader_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTraderCode();


  /** 
   * <em>trader_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTraderCodeIsSet();


  /** 
   * <em>trader_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTraderCodeIsModified();


  /** 
   * <em>order_request_number</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderRequestNumberIsModified();


  /** 
   * <em>course</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCourse();


  /** 
   * <em>course</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCourseIsSet();


  /** 
   * <em>course</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCourseIsModified();


  /** 
   * <em>plan</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPlan();


  /** 
   * <em>plan</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPlanIsSet();


  /** 
   * <em>plan</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPlanIsModified();


  /** 
   * <em>loan_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLoanDiv();


  /** 
   * <em>loan_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLoanDivIsSet();


  /** 
   * <em>loan_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLoanDivIsModified();


  /** 
   * <em>product_issue_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductIssueCode();


  /** 
   * <em>product_issue_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIssueCodeIsSet();


  /** 
   * <em>product_issue_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIssueCodeIsModified();


  /** 
   * <em>tax_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getTaxDiv();


  /** 
   * <em>tax_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxDivIsSet();


  /** 
   * <em>tax_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxDivIsModified();


  /** 
   * <em>return_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getReturnDiv();


  /** 
   * <em>return_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReturnDivIsSet();


  /** 
   * <em>return_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getReturnDivIsModified();


  /** 
   * <em>amount</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAmount();


  /** 
   * <em>amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAmountIsNull();


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
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getQuantity();


  /** 
   * <em>quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getQuantityIsNull();


  /** 
   * <em>quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuantityIsSet();


  /** 
   * <em>quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQuantityIsModified();


  /** 
   * <em>mortgage_sell</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMortgageSell();


  /** 
   * <em>mortgage_sell</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMortgageSellIsSet();


  /** 
   * <em>mortgage_sell</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMortgageSellIsModified();


  /** 
   * <em>delivery_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getDeliveryDate();


  /** 
   * <em>delivery_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeliveryDateIsSet();


  /** 
   * <em>delivery_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeliveryDateIsModified();


  /** 
   * <em>order_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getOrderDate();


  /** 
   * <em>order_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderDateIsSet();


  /** 
   * <em>order_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderDateIsModified();


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStatus();


  /** 
   * <em>status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStatusIsModified();


}
@
