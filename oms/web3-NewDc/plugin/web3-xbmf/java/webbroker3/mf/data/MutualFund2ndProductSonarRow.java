head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFund2ndProductSonarRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFund2ndProductSonarRow�C���^�t�F�[�X�͕ύX�s�Ń��[�h�I�����[�ł���<em>mutual_fund_2nd_product_sonar</em>�f�[�^�x�[�X�e�[�u�����R�[�h�̃X�i�b�v�V���b�g��\�����܂��B 
 * <p> 
 * �ʏ�A{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}�I�u�W�F�N�g�̃��\�b�h�𗘗p����{@@link MutualFund2ndProductSonarRow}�C���^�t�F�[�X�̎����I�u�W�F�N�g���������܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFund2ndProductSonarPK 
 */
public interface MutualFund2ndProductSonarRow extends Row {


  /** 
   * ����{@@link MutualFund2ndProductSonarRow}�N���X�ɕR�t���e�[�u��������킷{@@link com.fitechlabs.dbind.RowType}�I�u�W�F�N�g 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_2nd_product_sonar", "master" );


  /** 
   * <em>product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductCode();


  /** 
   * <em>product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductCodeIsSet();


  /** 
   * <em>product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductCodeIsModified();


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
   * <em>work_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getWorkDate();


  /** 
   * <em>work_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWorkDateIsSet();


  /** 
   * <em>work_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getWorkDateIsModified();


  /** 
   * <em>reg_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getRegDate();


  /** 
   * <em>reg_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegDateIsSet();


  /** 
   * <em>reg_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegDateIsModified();


  /** 
   * <em>last_update_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getLastUpdateDate();


  /** 
   * <em>last_update_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdateDateIsSet();


  /** 
   * <em>last_update_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getLastUpdateDateIsModified();


  /** 
   * <em>reg_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRegDiv();


  /** 
   * <em>reg_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegDivIsSet();


  /** 
   * <em>reg_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRegDivIsModified();


  /** 
   * <em>product_name_kana</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductNameKana();


  /** 
   * <em>product_name_kana</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanaIsSet();


  /** 
   * <em>product_name_kana</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanaIsModified();


  /** 
   * <em>product_name_kanji</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getProductNameKanji();


  /** 
   * <em>product_name_kanji</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanjiIsSet();


  /** 
   * <em>product_name_kanji</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProductNameKanjiIsModified();


  /** 
   * <em>recruit_unit</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRecruitUnit();


  /** 
   * <em>recruit_unit</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitUnitIsNull();


  /** 
   * <em>recruit_unit</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitIsSet();


  /** 
   * <em>recruit_unit</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitIsModified();


  /** 
   * <em>dealing_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getDealingUnitQty();


  /** 
   * <em>dealing_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getDealingUnitQtyIsNull();


  /** 
   * <em>dealing_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDealingUnitQtyIsSet();


  /** 
   * <em>dealing_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDealingUnitQtyIsModified();


  /** 
   * <em>recruit_min_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRecruitMinQty();


  /** 
   * <em>recruit_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitMinQtyIsNull();


  /** 
   * <em>recruit_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinQtyIsSet();


  /** 
   * <em>recruit_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinQtyIsModified();


  /** 
   * <em>buy_min_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getBuyMinQty();


  /** 
   * <em>buy_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyMinQtyIsNull();


  /** 
   * <em>buy_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyMinQtyIsSet();


  /** 
   * <em>buy_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyMinQtyIsModified();


  /** 
   * <em>sell_min_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSellMinQty();


  /** 
   * <em>sell_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellMinQtyIsNull();


  /** 
   * <em>sell_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinQtyIsSet();


  /** 
   * <em>sell_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinQtyIsModified();


  /** 
   * <em>swt_min_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSwtMinQty();


  /** 
   * <em>swt_min_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtMinQtyIsNull();


  /** 
   * <em>swt_min_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinQtyIsSet();


  /** 
   * <em>swt_min_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinQtyIsModified();


  /** 
   * <em>recruit_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRecruitUnitQty();


  /** 
   * <em>recruit_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitUnitQtyIsNull();


  /** 
   * <em>recruit_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitQtyIsSet();


  /** 
   * <em>recruit_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitQtyIsModified();


  /** 
   * <em>buy_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getBuyUnitQty();


  /** 
   * <em>buy_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyUnitQtyIsNull();


  /** 
   * <em>buy_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyUnitQtyIsSet();


  /** 
   * <em>buy_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyUnitQtyIsModified();


  /** 
   * <em>sell_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSellUnitQty();


  /** 
   * <em>sell_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellUnitQtyIsNull();


  /** 
   * <em>sell_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitQtyIsSet();


  /** 
   * <em>sell_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitQtyIsModified();


  /** 
   * <em>swt_unit_qty</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSwtUnitQty();


  /** 
   * <em>swt_unit_qty</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtUnitQtyIsNull();


  /** 
   * <em>swt_unit_qty</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitQtyIsSet();


  /** 
   * <em>swt_unit_qty</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitQtyIsModified();


  /** 
   * <em>recruit_min_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRecruitMinAmt();


  /** 
   * <em>recruit_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitMinAmtIsNull();


  /** 
   * <em>recruit_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinAmtIsSet();


  /** 
   * <em>recruit_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitMinAmtIsModified();


  /** 
   * <em>buy_min_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getBuyMinAmt();


  /** 
   * <em>buy_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyMinAmtIsNull();


  /** 
   * <em>buy_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyMinAmtIsSet();


  /** 
   * <em>buy_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyMinAmtIsModified();


  /** 
   * <em>sell_min_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSellMinAmt();


  /** 
   * <em>sell_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellMinAmtIsNull();


  /** 
   * <em>sell_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinAmtIsSet();


  /** 
   * <em>sell_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellMinAmtIsModified();


  /** 
   * <em>swt_min_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSwtMinAmt();


  /** 
   * <em>swt_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtMinAmtIsNull();


  /** 
   * <em>swt_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinAmtIsSet();


  /** 
   * <em>swt_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtMinAmtIsModified();


  /** 
   * <em>recruit_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getRecruitUnitAmt();


  /** 
   * <em>recruit_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRecruitUnitAmtIsNull();


  /** 
   * <em>recruit_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitAmtIsSet();


  /** 
   * <em>recruit_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitUnitAmtIsModified();


  /** 
   * <em>buy_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getBuyUnitAmt();


  /** 
   * <em>buy_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getBuyUnitAmtIsNull();


  /** 
   * <em>buy_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyUnitAmtIsSet();


  /** 
   * <em>buy_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyUnitAmtIsModified();


  /** 
   * <em>sell_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSellUnitAmt();


  /** 
   * <em>sell_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSellUnitAmtIsNull();


  /** 
   * <em>sell_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitAmtIsSet();


  /** 
   * <em>sell_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUnitAmtIsModified();


  /** 
   * <em>swt_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getSwtUnitAmt();


  /** 
   * <em>swt_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getSwtUnitAmtIsNull();


  /** 
   * <em>swt_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitAmtIsSet();


  /** 
   * <em>swt_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtUnitAmtIsModified();


  /** 
   * <em>recruit_qty_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitQtySpecDiv();


  /** 
   * <em>recruit_qty_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitQtySpecDivIsSet();


  /** 
   * <em>recruit_qty_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitQtySpecDivIsModified();


  /** 
   * <em>recruit_amt_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitAmtSpecDiv();


  /** 
   * <em>recruit_amt_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitAmtSpecDivIsSet();


  /** 
   * <em>recruit_amt_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitAmtSpecDivIsModified();


  /** 
   * <em>buy_qty_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyQtySpecDiv();


  /** 
   * <em>buy_qty_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyQtySpecDivIsSet();


  /** 
   * <em>buy_qty_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyQtySpecDivIsModified();


  /** 
   * <em>buy_amt_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyAmtSpecDiv();


  /** 
   * <em>buy_amt_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyAmtSpecDivIsSet();


  /** 
   * <em>buy_amt_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyAmtSpecDivIsModified();


  /** 
   * <em>sell_qty_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellQtySpecDiv();


  /** 
   * <em>sell_qty_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellQtySpecDivIsSet();


  /** 
   * <em>sell_qty_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellQtySpecDivIsModified();


  /** 
   * <em>sell_amt_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellAmtSpecDiv();


  /** 
   * <em>sell_amt_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellAmtSpecDivIsSet();


  /** 
   * <em>sell_amt_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellAmtSpecDivIsModified();


  /** 
   * <em>swt_qty_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSwtQtySpecDiv();


  /** 
   * <em>swt_qty_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtQtySpecDivIsSet();


  /** 
   * <em>swt_qty_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtQtySpecDivIsModified();


  /** 
   * <em>swt_amt_spec_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSwtAmtSpecDiv();


  /** 
   * <em>swt_amt_spec_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtAmtSpecDivIsSet();


  /** 
   * <em>swt_amt_spec_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtAmtSpecDivIsModified();


  /** 
   * <em>input_unit_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getInputUnitDiv();


  /** 
   * <em>input_unit_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInputUnitDivIsSet();


  /** 
   * <em>input_unit_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getInputUnitDivIsModified();


  /** 
   * <em>cal_price_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCalPriceDiv();


  /** 
   * <em>cal_price_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalPriceDivIsSet();


  /** 
   * <em>cal_price_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCalPriceDivIsModified();


  /** 
   * <em>sell_exception_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellExceptionDiv();


  /** 
   * <em>sell_exception_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellExceptionDivIsSet();


  /** 
   * <em>sell_exception_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellExceptionDivIsModified();


  /** 
   * <em>swt_trade_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSwtTradeDiv();


  /** 
   * <em>swt_trade_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtTradeDivIsSet();


  /** 
   * <em>swt_trade_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtTradeDivIsModified();


  /** 
   * <em>swt_group</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSwtGroup();


  /** 
   * <em>swt_group</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtGroupIsSet();


  /** 
   * <em>swt_group</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtGroupIsModified();


  /** 
   * <em>swt_companion_subject_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSwtCompanionSubjectDiv();


  /** 
   * <em>swt_companion_subject_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtCompanionSubjectDivIsSet();


  /** 
   * <em>swt_companion_subject_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtCompanionSubjectDivIsModified();


  /** 
   * <em>buy_disable_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyDisableDiv();


  /** 
   * <em>buy_disable_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyDisableDivIsSet();


  /** 
   * <em>buy_disable_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyDisableDivIsModified();


  /** 
   * <em>swt_perference_enable_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSwtPerferenceEnableDiv();


  /** 
   * <em>swt_perference_enable_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtPerferenceEnableDivIsSet();


  /** 
   * <em>swt_perference_enable_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSwtPerferenceEnableDivIsModified();


  /** 
   * <em>redemption_perference_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getRedemptionPerferencePrice();


  /** 
   * <em>redemption_perference_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRedemptionPerferencePriceIsNull();


  /** 
   * <em>redemption_perference_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionPerferencePriceIsSet();


  /** 
   * <em>redemption_perference_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionPerferencePriceIsModified();


  /** 
   * <em>redemption_commission_rate</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public double getRedemptionCommissionRate();


  /** 
   * <em>redemption_commission_rate</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getRedemptionCommissionRateIsNull();


  /** 
   * <em>redemption_commission_rate</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionCommissionRateIsSet();


  /** 
   * <em>redemption_commission_rate</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRedemptionCommissionRateIsModified();


  /** 
   * <em>pre_redemption_begin_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getPreRedemptionBeginDate();


  /** 
   * <em>pre_redemption_begin_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPreRedemptionBeginDateIsSet();


  /** 
   * <em>pre_redemption_begin_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPreRedemptionBeginDateIsModified();


  /** 
   * <em>closing_date_cal_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getClosingDateCalDiv();


  /** 
   * <em>closing_date_cal_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingDateCalDivIsSet();


  /** 
   * <em>closing_date_cal_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingDateCalDivIsModified();


  /** 
   * <em>closing_spec_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getClosingSpecDate();


  /** 
   * <em>closing_spec_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingSpecDateIsSet();


  /** 
   * <em>closing_spec_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getClosingSpecDateIsModified();


  /** 
   * <em>annual_profit_qty_times</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getAnnualProfitQtyTimes();


  /** 
   * <em>annual_profit_qty_times</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualProfitQtyTimesIsSet();


  /** 
   * <em>annual_profit_qty_times</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getAnnualProfitQtyTimesIsModified();


  /** 
   * <em>sell_advance_order_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellAdvanceOrderDiv();


  /** 
   * <em>sell_advance_order_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellAdvanceOrderDivIsSet();


  /** 
   * <em>sell_advance_order_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellAdvanceOrderDivIsModified();


  /** 
   * <em>buy_advance_order_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyAdvanceOrderDiv();


  /** 
   * <em>buy_advance_order_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyAdvanceOrderDivIsSet();


  /** 
   * <em>buy_advance_order_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyAdvanceOrderDivIsModified();


  /** 
   * <em>sell_undelivering_term</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSellUndeliveringTerm();


  /** 
   * <em>sell_undelivering_term</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUndeliveringTermIsSet();


  /** 
   * <em>sell_undelivering_term</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSellUndeliveringTermIsModified();


  /** 
   * <em>buy_undelivering_term</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBuyUndeliveringTerm();


  /** 
   * <em>buy_undelivering_term</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyUndeliveringTermIsSet();


  /** 
   * <em>buy_undelivering_term</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBuyUndeliveringTermIsModified();


  /** 
   * <em>recruit_stop_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getRecruitStopDiv();


  /** 
   * <em>recruit_stop_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStopDivIsSet();


  /** 
   * <em>recruit_stop_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getRecruitStopDivIsModified();


  /** 
   * <em>dealing_stop_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getDealingStopDiv();


  /** 
   * <em>dealing_stop_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDealingStopDivIsSet();


  /** 
   * <em>dealing_stop_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getDealingStopDivIsModified();


  /** 
   * <em>storage_stop_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getStorageStopDiv();


  /** 
   * <em>storage_stop_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStorageStopDivIsSet();


  /** 
   * <em>storage_stop_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getStorageStopDivIsModified();


  /** 
   * <em>specific_corpus_app</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getSpecificCorpusApp();


  /** 
   * <em>specific_corpus_app</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpecificCorpusAppIsSet();


  /** 
   * <em>specific_corpus_app</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getSpecificCorpusAppIsModified();


  /** 
   * <em>perference_qualify</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getPerferenceQualify();


  /** 
   * <em>perference_qualify</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPerferenceQualifyIsSet();


  /** 
   * <em>perference_qualify</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getPerferenceQualifyIsModified();


  /** 
   * <em>collateral_qualify</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getCollateralQualify();


  /** 
   * <em>collateral_qualify</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralQualifyIsSet();


  /** 
   * <em>collateral_qualify</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getCollateralQualifyIsModified();


  /** 
   * <em>operate_report_send_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOperateReportSendDiv();


  /** 
   * <em>operate_report_send_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOperateReportSendDivIsSet();


  /** 
   * <em>operate_report_send_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOperateReportSendDivIsModified();


  /** 
   * <em>operate_report_send_month1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOperateReportSendMonth1();


  /** 
   * <em>operate_report_send_month1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOperateReportSendMonth1IsSet();


  /** 
   * <em>operate_report_send_month1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOperateReportSendMonth1IsModified();


  /** 
   * <em>operate_report_send_month2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getOperateReportSendMonth2();


  /** 
   * <em>operate_report_send_month2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOperateReportSendMonth2IsSet();


  /** 
   * <em>operate_report_send_month2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getOperateReportSendMonth2IsModified();


  /** 
   * <em>biz_asset_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBizAssetCode();


  /** 
   * <em>biz_asset_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetCodeIsSet();


  /** 
   * <em>biz_asset_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetCodeIsModified();


  /** 
   * <em>biz_asset_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public String getBizAssetName();


  /** 
   * <em>biz_asset_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetNameIsSet();


  /** 
   * <em>biz_asset_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getBizAssetNameIsModified();


  /** 
   * <em>prospectus_conversion_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public java.sql.Timestamp getProspectusConversionDate();


  /** 
   * <em>prospectus_conversion_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProspectusConversionDateIsSet();


  /** 
   * <em>prospectus_conversion_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getProspectusConversionDateIsModified();


  /** 
   * <em>frgn_buy_min_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getFrgnBuyMinAmt();


  /** 
   * <em>frgn_buy_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnBuyMinAmtIsNull();


  /** 
   * <em>frgn_buy_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnBuyMinAmtIsSet();


  /** 
   * <em>frgn_buy_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnBuyMinAmtIsModified();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getFrgnSellMinAmt();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnSellMinAmtIsNull();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellMinAmtIsSet();


  /** 
   * <em>frgn_sell_min_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellMinAmtIsModified();


  /** 
   * <em>frgn_buy_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getFrgnBuyUnitAmt();


  /** 
   * <em>frgn_buy_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnBuyUnitAmtIsNull();


  /** 
   * <em>frgn_buy_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnBuyUnitAmtIsSet();


  /** 
   * <em>frgn_buy_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnBuyUnitAmtIsModified();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public int getFrgnSellUnitAmt();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public  boolean  getFrgnSellUnitAmtIsNull();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellUnitAmtIsSet();


  /** 
   * <em>frgn_sell_unit_amt</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public  boolean  getFrgnSellUnitAmtIsModified();


}
@
