head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BranchRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * BranchRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>branch</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link BranchRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchPK 
 */
public interface BranchRow extends Row {


  /** 
   * ����{@@link BranchRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "branch", "master" );


  /** 
   * <em>branch_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getBranchId();


  /** 
   * <em>branch_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchIdIsSet();


  /** 
   * <em>branch_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchIdIsModified();


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
   * <em>institution_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getInstitutionId();


  /** 
   * <em>institution_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionIdIsSet();


  /** 
   * <em>institution_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInstitutionIdIsModified();


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
   * <em>branch_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchName();


  /** 
   * <em>branch_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchNameIsSet();


  /** 
   * <em>branch_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchNameIsModified();


  /** 
   * <em>branch_name_alt1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBranchNameAlt1();


  /** 
   * <em>branch_name_alt1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchNameAlt1IsSet();


  /** 
   * <em>branch_name_alt1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchNameAlt1IsModified();


  /** 
   * <em>branch_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum�̒l 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum getBranchType();


  /** 
   * <em>branch_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchTypeIsSet();


  /** 
   * <em>branch_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBranchTypeIsModified();


  /** 
   * <em>max_handling_price_ind</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaxHandlingPriceInd();


  /** 
   * <em>max_handling_price_ind</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxHandlingPriceIndIsNull();


  /** 
   * <em>max_handling_price_ind</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceIndIsSet();


  /** 
   * <em>max_handling_price_ind</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceIndIsModified();


  /** 
   * <em>max_handling_price_corp</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaxHandlingPriceCorp();


  /** 
   * <em>max_handling_price_corp</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxHandlingPriceCorpIsNull();


  /** 
   * <em>max_handling_price_corp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCorpIsSet();


  /** 
   * <em>max_handling_price_corp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCorpIsModified();


  /** 
   * <em>max_handling_price_ind_option</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaxHandlingPriceIndOption();


  /** 
   * <em>max_handling_price_ind_option</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxHandlingPriceIndOptionIsNull();


  /** 
   * <em>max_handling_price_ind_option</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceIndOptionIsSet();


  /** 
   * <em>max_handling_price_ind_option</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceIndOptionIsModified();


  /** 
   * <em>max_handling_price_corp_option</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaxHandlingPriceCorpOption();


  /** 
   * <em>max_handling_price_corp_option</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxHandlingPriceCorpOptionIsNull();


  /** 
   * <em>max_handling_price_corp_option</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCorpOptionIsSet();


  /** 
   * <em>max_handling_price_corp_option</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCorpOptionIsModified();


  /** 
   * <em>max_handling_price_ind_future</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaxHandlingPriceIndFuture();


  /** 
   * <em>max_handling_price_ind_future</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxHandlingPriceIndFutureIsNull();


  /** 
   * <em>max_handling_price_ind_future</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceIndFutureIsSet();


  /** 
   * <em>max_handling_price_ind_future</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceIndFutureIsModified();


  /** 
   * <em>max_handling_price_corp_future</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getMaxHandlingPriceCorpFuture();


  /** 
   * <em>max_handling_price_corp_future</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxHandlingPriceCorpFutureIsNull();


  /** 
   * <em>max_handling_price_corp_future</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCorpFutureIsSet();


  /** 
   * <em>max_handling_price_corp_future</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCorpFutureIsModified();


  /** 
   * <em>max_cont_price_all_ind</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPriceAllInd();


  /** 
   * <em>max_cont_price_all_ind</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPriceAllIndIsNull();


  /** 
   * <em>max_cont_price_all_ind</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceAllIndIsSet();


  /** 
   * <em>max_cont_price_all_ind</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceAllIndIsModified();


  /** 
   * <em>max_cont_price_all_corp</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPriceAllCorp();


  /** 
   * <em>max_cont_price_all_corp</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPriceAllCorpIsNull();


  /** 
   * <em>max_cont_price_all_corp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceAllCorpIsSet();


  /** 
   * <em>max_cont_price_all_corp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceAllCorpIsModified();


  /** 
   * <em>max_cont_price_product_ind</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPriceProductInd();


  /** 
   * <em>max_cont_price_product_ind</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPriceProductIndIsNull();


  /** 
   * <em>max_cont_price_product_ind</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceProductIndIsSet();


  /** 
   * <em>max_cont_price_product_ind</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceProductIndIsModified();


  /** 
   * <em>max_cont_price_product_corp</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPriceProductCorp();


  /** 
   * <em>max_cont_price_product_corp</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPriceProductCorpIsNull();


  /** 
   * <em>max_cont_price_product_corp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceProductCorpIsSet();


  /** 
   * <em>max_cont_price_product_corp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPriceProductCorpIsModified();


  /** 
   * <em>max_cont_price_1day_ind</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPrice1dayInd();


  /** 
   * <em>max_cont_price_1day_ind</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPrice1dayIndIsNull();


  /** 
   * <em>max_cont_price_1day_ind</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPrice1dayIndIsSet();


  /** 
   * <em>max_cont_price_1day_ind</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPrice1dayIndIsModified();


  /** 
   * <em>max_cont_price_1day_corp</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMaxContPrice1dayCorp();


  /** 
   * <em>max_cont_price_1day_corp</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMaxContPrice1dayCorpIsNull();


  /** 
   * <em>max_cont_price_1day_corp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPrice1dayCorpIsSet();


  /** 
   * <em>max_cont_price_1day_corp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxContPrice1dayCorpIsModified();


  /** 
   * <em>handling_market_make</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getHandlingMarketMake();


  /** 
   * <em>handling_market_make</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getHandlingMarketMakeIsNull();


  /** 
   * <em>handling_market_make</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHandlingMarketMakeIsSet();


  /** 
   * <em>handling_market_make</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHandlingMarketMakeIsModified();


  /** 
   * <em>handling_not_loan_trans_stock</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getHandlingNotLoanTransStock();


  /** 
   * <em>handling_not_loan_trans_stock</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getHandlingNotLoanTransStockIsNull();


  /** 
   * <em>handling_not_loan_trans_stock</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHandlingNotLoanTransStockIsSet();


  /** 
   * <em>handling_not_loan_trans_stock</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getHandlingNotLoanTransStockIsModified();


  /** 
   * <em>fin_sales_law_execution</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFinSalesLawExecution();


  /** 
   * <em>fin_sales_law_execution</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinSalesLawExecutionIsSet();


  /** 
   * <em>fin_sales_law_execution</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFinSalesLawExecutionIsModified();


  /** 
   * <em>email_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getEmailAddress();


  /** 
   * <em>email_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressIsSet();


  /** 
   * <em>email_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getEmailAddressIsModified();


  /** 
   * <em>login_stop_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLoginStopDiv();


  /** 
   * <em>login_stop_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLoginStopDivIsSet();


  /** 
   * <em>login_stop_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLoginStopDivIsModified();


  /** 
   * <em>admin_type_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAdminTypeId();


  /** 
   * <em>admin_type_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAdminTypeIdIsNull();


  /** 
   * <em>admin_type_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminTypeIdIsSet();


  /** 
   * <em>admin_type_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAdminTypeIdIsModified();


  /** 
   * <em>trader_type_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getTraderTypeId();


  /** 
   * <em>trader_type_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getTraderTypeIdIsNull();


  /** 
   * <em>trader_type_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTraderTypeIdIsSet();


  /** 
   * <em>trader_type_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getTraderTypeIdIsModified();


  /** 
   * <em>account_type_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAccountTypeId();


  /** 
   * <em>account_type_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountTypeIdIsNull();


  /** 
   * <em>account_type_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountTypeIdIsSet();


  /** 
   * <em>account_type_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountTypeIdIsModified();


  /** 
   * <em>account_group_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public long getAccountGroupId();


  /** 
   * <em>account_group_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountGroupIdIsNull();


  /** 
   * <em>account_group_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountGroupIdIsSet();


  /** 
   * <em>account_group_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountGroupIdIsModified();


  /** 
   * <em>account_code_min</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getAccountCodeMin();


  /** 
   * <em>account_code_min</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountCodeMinIsNull();


  /** 
   * <em>account_code_min</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeMinIsSet();


  /** 
   * <em>account_code_min</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeMinIsModified();


  /** 
   * <em>account_code_max</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getAccountCodeMax();


  /** 
   * <em>account_code_max</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getAccountCodeMaxIsNull();


  /** 
   * <em>account_code_max</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeMaxIsSet();


  /** 
   * <em>account_code_max</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeMaxIsModified();


  /** 
   * <em>account_code_check_mode</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAccountCodeCheckMode();


  /** 
   * <em>account_code_check_mode</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeCheckModeIsSet();


  /** 
   * <em>account_code_check_mode</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAccountCodeCheckModeIsModified();


  /** 
   * <em>insider_default_regist_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInsiderDefaultRegistDiv();


  /** 
   * <em>insider_default_regist_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderDefaultRegistDivIsSet();


  /** 
   * <em>insider_default_regist_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInsiderDefaultRegistDivIsModified();


  /** 
   * <em>margin_sys_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginSysDiv();


  /** 
   * <em>margin_sys_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSysDivIsSet();


  /** 
   * <em>margin_sys_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSysDivIsModified();


  /** 
   * <em>margin_gen_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMarginGenDiv();


  /** 
   * <em>margin_gen_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginGenDivIsSet();


  /** 
   * <em>margin_gen_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginGenDivIsModified();


  /** 
   * <em>fstk_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFstkDiv();


  /** 
   * <em>fstk_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFstkDivIsSet();


  /** 
   * <em>fstk_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFstkDivIsModified();


  /** 
   * <em>mstk_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMstkDiv();


  /** 
   * <em>mstk_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMstkDivIsSet();


  /** 
   * <em>mstk_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMstkDivIsModified();


  /** 
   * <em>option_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOptionDiv();


  /** 
   * <em>option_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOptionDivIsSet();


  /** 
   * <em>option_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOptionDivIsModified();


  /** 
   * <em>future_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getFutureDiv();


  /** 
   * <em>future_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutureDivIsSet();


  /** 
   * <em>future_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFutureDivIsModified();


  /** 
   * <em>mf_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMfDiv();


  /** 
   * <em>mf_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMfDivIsSet();


  /** 
   * <em>mf_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMfDivIsModified();


  /** 
   * <em>ruito_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRuitoDiv();


  /** 
   * <em>ruito_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoDivIsSet();


  /** 
   * <em>ruito_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRuitoDivIsModified();


  /** 
   * <em>qualified_investor_confirm_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getQualifiedInvestorConfirmDiv();


  /** 
   * <em>qualified_investor_confirm_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQualifiedInvestorConfirmDivIsSet();


  /** 
   * <em>qualified_investor_confirm_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getQualifiedInvestorConfirmDivIsModified();


  /** 
   * <em>margin_deposit_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginDepositRate();


  /** 
   * <em>margin_deposit_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginDepositRateIsNull();


  /** 
   * <em>margin_deposit_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRateIsSet();


  /** 
   * <em>margin_deposit_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginDepositRateIsModified();


  /** 
   * <em>cash_margin_deposit_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCashMarginDepositRate();


  /** 
   * <em>cash_margin_deposit_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCashMarginDepositRateIsNull();


  /** 
   * <em>cash_margin_deposit_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDepositRateIsSet();


  /** 
   * <em>cash_margin_deposit_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCashMarginDepositRateIsModified();


  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginMaintenanceRate();


  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginMaintenanceRateIsNull();


  /** 
   * <em>margin_maintenance_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginMaintenanceRateIsSet();


  /** 
   * <em>margin_maintenance_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginMaintenanceRateIsModified();


  /** 
   * <em>min_margin_deposit</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMinMarginDeposit();


  /** 
   * <em>min_margin_deposit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMinMarginDepositIsNull();


  /** 
   * <em>min_margin_deposit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMinMarginDepositIsSet();


  /** 
   * <em>min_margin_deposit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMinMarginDepositIsModified();


  /** 
   * <em>min_ifo_deposit</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMinIfoDeposit();


  /** 
   * <em>min_ifo_deposit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMinIfoDepositIsNull();


  /** 
   * <em>min_ifo_deposit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMinIfoDepositIsSet();


  /** 
   * <em>min_ifo_deposit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMinIfoDepositIsModified();


  /** 
   * <em>calc_substitute_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getCalcSubstituteRate();


  /** 
   * <em>calc_substitute_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCalcSubstituteRateIsNull();


  /** 
   * <em>calc_substitute_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcSubstituteRateIsSet();


  /** 
   * <em>calc_substitute_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalcSubstituteRateIsModified();


  /** 
   * <em>margin_sec_check_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getMarginSecCheckRate();


  /** 
   * <em>margin_sec_check_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginSecCheckRateIsNull();


  /** 
   * <em>margin_sec_check_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecCheckRateIsSet();


  /** 
   * <em>margin_sec_check_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecCheckRateIsModified();


  /** 
   * <em>short_margin_restrain_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getShortMarginRestrainDiv();


  /** 
   * <em>short_margin_restrain_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginRestrainDivIsSet();


  /** 
   * <em>short_margin_restrain_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginRestrainDivIsModified();


  /** 
   * <em>short_margin_restrain_unit</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getShortMarginRestrainUnit();


  /** 
   * <em>short_margin_restrain_unit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortMarginRestrainUnitIsNull();


  /** 
   * <em>short_margin_restrain_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginRestrainUnitIsSet();


  /** 
   * <em>short_margin_restrain_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortMarginRestrainUnitIsModified();


  /** 
   * <em>short_sell_order_valid_minute</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getShortSellOrderValidMinute();


  /** 
   * <em>short_sell_order_valid_minute</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getShortSellOrderValidMinuteIsNull();


  /** 
   * <em>short_sell_order_valid_minute</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortSellOrderValidMinuteIsSet();


  /** 
   * <em>short_sell_order_valid_minute</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getShortSellOrderValidMinuteIsModified();


  /** 
   * <em>margin_sec_transfer_max_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getMarginSecTransferMaxCount();


  /** 
   * <em>margin_sec_transfer_max_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getMarginSecTransferMaxCountIsNull();


  /** 
   * <em>margin_sec_transfer_max_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecTransferMaxCountIsSet();


  /** 
   * <em>margin_sec_transfer_max_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMarginSecTransferMaxCountIsModified();


  /** 
   * <em>close_worng_equity_margin</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getCloseWorngEquityMargin();


  /** 
   * <em>close_worng_equity_margin</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCloseWorngEquityMarginIsNull();


  /** 
   * <em>close_worng_equity_margin</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngEquityMarginIsSet();


  /** 
   * <em>close_worng_equity_margin</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngEquityMarginIsModified();


  /** 
   * <em>close_worng_option</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getCloseWorngOption();


  /** 
   * <em>close_worng_option</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCloseWorngOptionIsNull();


  /** 
   * <em>close_worng_option</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngOptionIsSet();


  /** 
   * <em>close_worng_option</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngOptionIsModified();


  /** 
   * <em>close_worng_sys_future</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getCloseWorngSysFuture();


  /** 
   * <em>close_worng_sys_future</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCloseWorngSysFutureIsNull();


  /** 
   * <em>close_worng_sys_future</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngSysFutureIsSet();


  /** 
   * <em>close_worng_sys_future</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngSysFutureIsModified();


  /** 
   * <em>daily_interest_adjust_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getDailyInterestAdjustAmount();


  /** 
   * <em>daily_interest_adjust_amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDailyInterestAdjustAmountIsNull();


  /** 
   * <em>daily_interest_adjust_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDailyInterestAdjustAmountIsSet();


  /** 
   * <em>daily_interest_adjust_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDailyInterestAdjustAmountIsModified();


  /** 
   * <em>pay_auto_calc_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPayAutoCalcDiv();


  /** 
   * <em>pay_auto_calc_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPayAutoCalcDivIsSet();


  /** 
   * <em>pay_auto_calc_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPayAutoCalcDivIsModified();


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdaterIsModified();


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
   * <em>max_handling_price_close_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getMaxHandlingPriceCloseDiv();


  /** 
   * <em>max_handling_price_close_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCloseDivIsSet();


  /** 
   * <em>max_handling_price_close_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getMaxHandlingPriceCloseDivIsModified();


  /** 
   * <em>off_floor_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOffFloorDiv();


  /** 
   * <em>off_floor_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOffFloorDivIsSet();


  /** 
   * <em>off_floor_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOffFloorDivIsModified();


  /** 
   * <em>close_worng_feq</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getCloseWorngFeq();


  /** 
   * <em>close_worng_feq</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getCloseWorngFeqIsNull();


  /** 
   * <em>close_worng_feq</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngFeqIsSet();


  /** 
   * <em>close_worng_feq</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCloseWorngFeqIsModified();


}
@
