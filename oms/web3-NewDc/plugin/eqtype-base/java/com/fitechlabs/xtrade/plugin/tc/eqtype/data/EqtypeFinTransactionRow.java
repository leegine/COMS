head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeFinTransactionRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * EqtypeFinTransactionRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>eqtype_fin_transaction</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link EqtypeFinTransactionRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeFinTransactionPK 
 */
public interface EqtypeFinTransactionRow extends Row {


  /** 
   * ����{@@link EqtypeFinTransactionRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "eqtype_fin_transaction", "account" );


  /** 
   * <em>fin_transaction_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getFinTransactionId();


  /** 
   * <em>fin_transaction_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionIdIsSet();


  /** 
   * <em>fin_transaction_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionIdIsModified();


  /** 
   * <em>account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountIdIsModified();


  /** 
   * <em>sub_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getSubAccountId();


  /** 
   * <em>sub_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubAccountIdIsSet();


  /** 
   * <em>sub_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSubAccountIdIsModified();


  /** 
   * <em>product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductIdIsModified();


  /** 
   * <em>fin_transaction_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType getFinTransactionType();


  /** 
   * <em>fin_transaction_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionTypeIsSet();


  /** 
   * <em>fin_transaction_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionTypeIsModified();


  /** 
   * <em>fin_transaction_categ</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg getFinTransactionCateg();


  /** 
   * <em>fin_transaction_categ</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionCategIsSet();


  /** 
   * <em>fin_transaction_categ</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionCategIsModified();


  /** 
   * <em>fin_transaction_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getFinTransactionTimestamp();


  /** 
   * <em>fin_transaction_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionTimestampIsSet();


  /** 
   * <em>fin_transaction_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinTransactionTimestampIsModified();


  /** 
   * <em>tax_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType();


  /** 
   * <em>tax_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeIsSet();


  /** 
   * <em>tax_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTaxTypeIsModified();


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
   * <em>net_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getNetAmount();


  /** 
   * <em>net_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetAmountIsSet();


  /** 
   * <em>net_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getNetAmountIsModified();


  /** 
   * <em>product_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductTypeIsSet();


  /** 
   * <em>product_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductTypeIsModified();


  /** 
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMarketId();


  /** 
   * <em>market_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarketIdIsNull();


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsSet();


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarketIdIsModified();


  /** 
   * <em>price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getPrice();


  /** 
   * <em>price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getPriceIsNull();


  /** 
   * <em>price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceIsSet();


  /** 
   * <em>price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPriceIsModified();


  /** 
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getQuantity();


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
   * <em>order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderId();


  /** 
   * <em>order_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOrderIdIsNull();


  /** 
   * <em>order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderIdIsSet();


  /** 
   * <em>order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderIdIsModified();


  /** 
   * <em>order_unit_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderUnitId();


  /** 
   * <em>order_unit_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOrderUnitIdIsNull();


  /** 
   * <em>order_unit_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderUnitIdIsSet();


  /** 
   * <em>order_unit_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderUnitIdIsModified();


  /** 
   * <em>order_execution_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getOrderExecutionId();


  /** 
   * <em>order_execution_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getOrderExecutionIdIsNull();


  /** 
   * <em>order_execution_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderExecutionIdIsSet();


  /** 
   * <em>order_execution_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOrderExecutionIdIsModified();


  /** 
   * <em>commission_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCommissionFee();


  /** 
   * <em>commission_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCommissionFeeIsSet();


  /** 
   * <em>commission_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCommissionFeeIsModified();


  /** 
   * <em>commission_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCommissionFeeTax();


  /** 
   * <em>commission_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCommissionFeeTaxIsSet();


  /** 
   * <em>commission_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCommissionFeeTaxIsModified();


  /** 
   * <em>asset_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAssetId();


  /** 
   * <em>asset_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAssetIdIsNull();


  /** 
   * <em>asset_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetIdIsSet();


  /** 
   * <em>asset_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAssetIdIsModified();


  /** 
   * <em>imported_paid_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedPaidValue();


  /** 
   * <em>imported_paid_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedPaidValueIsSet();


  /** 
   * <em>imported_paid_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedPaidValueIsModified();


  /** 
   * <em>imported_setup_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedSetupFee();


  /** 
   * <em>imported_setup_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedSetupFeeIsSet();


  /** 
   * <em>imported_setup_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedSetupFeeIsModified();


  /** 
   * <em>imported_setup_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedSetupFeeTax();


  /** 
   * <em>imported_setup_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedSetupFeeTaxIsSet();


  /** 
   * <em>imported_setup_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedSetupFeeTaxIsModified();


  /** 
   * <em>imported_name_transfer_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedNameTransferFee();


  /** 
   * <em>imported_name_transfer_fee</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getImportedNameTransferFeeIsNull();


  /** 
   * <em>imported_name_transfer_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedNameTransferFeeIsSet();


  /** 
   * <em>imported_name_transfer_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedNameTransferFeeIsModified();


  /** 
   * <em>imported_name_transfer_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedNameTransferFeeTax();


  /** 
   * <em>imported_name_transfer_fee_tax</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getImportedNameTransferFeeTaxIsNull();


  /** 
   * <em>imported_name_transfer_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedNameTransferFeeTaxIsSet();


  /** 
   * <em>imported_name_transfer_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedNameTransferFeeTaxIsModified();


  /** 
   * <em>capital_gain</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCapitalGain();


  /** 
   * <em>capital_gain</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCapitalGainIsSet();


  /** 
   * <em>capital_gain</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCapitalGainIsModified();


  /** 
   * <em>capital_gain_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCapitalGainTax();


  /** 
   * <em>capital_gain_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCapitalGainTaxIsSet();


  /** 
   * <em>capital_gain_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCapitalGainTaxIsModified();


  /** 
   * <em>contract_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getContractId();


  /** 
   * <em>contract_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getContractIdIsNull();


  /** 
   * <em>contract_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractIdIsSet();


  /** 
   * <em>contract_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getContractIdIsModified();


  /** 
   * <em>imported_management_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedManagementFee();


  /** 
   * <em>imported_management_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedManagementFeeIsSet();


  /** 
   * <em>imported_management_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedManagementFeeIsModified();


  /** 
   * <em>imported_management_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedManagementFeeTax();


  /** 
   * <em>imported_management_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedManagementFeeTaxIsSet();


  /** 
   * <em>imported_management_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedManagementFeeTaxIsModified();


  /** 
   * <em>imported_interest_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedInterestFee();


  /** 
   * <em>imported_interest_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedInterestFeeIsSet();


  /** 
   * <em>imported_interest_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedInterestFeeIsModified();


  /** 
   * <em>imported_interest_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedInterestFeeTax();


  /** 
   * <em>imported_interest_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedInterestFeeTaxIsSet();


  /** 
   * <em>imported_interest_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedInterestFeeTaxIsModified();


  /** 
   * <em>imported_pay_interest_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedPayInterestFee();


  /** 
   * <em>imported_pay_interest_fee</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getImportedPayInterestFeeIsNull();


  /** 
   * <em>imported_pay_interest_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedPayInterestFeeIsSet();


  /** 
   * <em>imported_pay_interest_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedPayInterestFeeIsModified();


  /** 
   * <em>imported_pay_interest_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedPayInterestFeeTax();


  /** 
   * <em>imported_pay_interest_fee_tax</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getImportedPayInterestFeeTaxIsNull();


  /** 
   * <em>imported_pay_interest_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedPayInterestFeeTaxIsSet();


  /** 
   * <em>imported_pay_interest_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedPayInterestFeeTaxIsModified();


  /** 
   * <em>imported_loan_equity_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedLoanEquityFee();


  /** 
   * <em>imported_loan_equity_fee</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getImportedLoanEquityFeeIsNull();


  /** 
   * <em>imported_loan_equity_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedLoanEquityFeeIsSet();


  /** 
   * <em>imported_loan_equity_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedLoanEquityFeeIsModified();


  /** 
   * <em>imported_other</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getImportedOther();


  /** 
   * <em>imported_other</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getImportedOtherIsNull();


  /** 
   * <em>imported_other</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedOtherIsSet();


  /** 
   * <em>imported_other</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getImportedOtherIsModified();


  /** 
   * <em>transfered_asset_mng_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTransferedAssetMngFee();


  /** 
   * <em>transfered_asset_mng_fee</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTransferedAssetMngFeeIsNull();


  /** 
   * <em>transfered_asset_mng_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetMngFeeIsSet();


  /** 
   * <em>transfered_asset_mng_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetMngFeeIsModified();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTransferedAssetMngFeeTax();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTransferedAssetMngFeeTaxIsNull();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetMngFeeTaxIsSet();


  /** 
   * <em>transfered_asset_mng_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetMngFeeTaxIsModified();


  /** 
   * <em>transfered_asset_setup_fee</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTransferedAssetSetupFee();


  /** 
   * <em>transfered_asset_setup_fee</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTransferedAssetSetupFeeIsNull();


  /** 
   * <em>transfered_asset_setup_fee</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetSetupFeeIsSet();


  /** 
   * <em>transfered_asset_setup_fee</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetSetupFeeIsModified();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTransferedAssetSetupFeeTax();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTransferedAssetSetupFeeTaxIsNull();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetSetupFeeTaxIsSet();


  /** 
   * <em>transfered_asset_setup_fee_tax</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetSetupFeeTaxIsModified();


  /** 
   * <em>transfered_asset_book_value</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getTransferedAssetBookValue();


  /** 
   * <em>transfered_asset_book_value</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTransferedAssetBookValueIsNull();


  /** 
   * <em>transfered_asset_book_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetBookValueIsSet();


  /** 
   * <em>transfered_asset_book_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTransferedAssetBookValueIsModified();


  /** 
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag();


  /** 
   * <em>delete_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeleteFlagIsSet();


  /** 
   * <em>delete_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDeleteFlagIsModified();


  /** 
   * <em>accrued_interest</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getAccruedInterest();


  /** 
   * <em>accrued_interest</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccruedInterestIsNull();


  /** 
   * <em>accrued_interest</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccruedInterestIsSet();


  /** 
   * <em>accrued_interest</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccruedInterestIsModified();


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getCreatedTimestamp();


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
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>capital_gain_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCapitalGainStatus();


  /** 
   * <em>capital_gain_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCapitalGainStatusIsSet();


  /** 
   * <em>capital_gain_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCapitalGainStatusIsModified();


}
@
