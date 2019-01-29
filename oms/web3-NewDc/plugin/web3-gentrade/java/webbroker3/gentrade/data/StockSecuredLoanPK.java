head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	StockSecuredLoanPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>StockSecuredLoan</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>StockSecuredLoan</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>StockSecuredLoan</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see StockSecuredLoanRow 
 */
public class StockSecuredLoanPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "stock_secured_loan";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: StockSecuredLoanRow. 
   */
  public RowType getRowType() {
    return StockSecuredLoanRow.TYPE;
  }

  /**
   * <em>stock_loan_account_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public String stock_loan_account_code;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public StockSecuredLoanPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_stockLoanAccountCode <em>stock_loan_account_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public StockSecuredLoanPK( String p_stockLoanAccountCode ) {
      stock_loan_account_code = p_stockLoanAccountCode;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static StockSecuredLoanPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new StockSecuredLoanPK( pkValueString );
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    return stock_loan_account_code;
  }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof StockSecuredLoanPK) )
      return false;
    StockSecuredLoanPK o = (StockSecuredLoanPK) other;
    if ( stock_loan_account_code == null ) {
      if ( o.stock_loan_account_code != null )
        return false;
    } else if ( !stock_loan_account_code.equals( o.stock_loan_account_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (stock_loan_account_code!=null? stock_loan_account_code.hashCode(): 0) 
    ;
  }

}

@
