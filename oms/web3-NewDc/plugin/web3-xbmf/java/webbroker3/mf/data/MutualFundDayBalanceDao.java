head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundDayBalanceDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFundDayBalanceDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MutualFundDayBalanceRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundDayBalancePK 
 * @@see MutualFundDayBalanceRow 
 */
public class MutualFundDayBalanceDao extends DataAccessObject {


  /** 
   * ����{@@link MutualFundDayBalanceDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MutualFundDayBalanceRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MutualFundDayBalanceRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MutualFundDayBalanceDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MutualFundDayBalanceDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MutualFundDayBalanceRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundDayBalanceRow )
                return new MutualFundDayBalanceDao( (MutualFundDayBalanceRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundDayBalanceRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundDayBalanceRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MutualFundDayBalanceRow}�I�u�W�F�N�g 
    */
    protected MutualFundDayBalanceDao( MutualFundDayBalanceRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MutualFundDayBalanceRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MutualFundDayBalanceRow getMutualFundDayBalanceRow() {
        return row;
    }


  /** 
   * �w���{@@link MutualFundDayBalanceRow}�I�u�W�F�N�g����{@@link MutualFundDayBalanceDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MutualFundDayBalanceRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MutualFundDayBalanceDao}�擾�̂��߂Ɏw���{@@link MutualFundDayBalanceRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MutualFundDayBalanceDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MutualFundDayBalanceDao forRow( MutualFundDayBalanceRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundDayBalanceDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate, and �ɂĎw��̒l�Ɉ�v����{@@link MutualFundDayBalanceRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_taxType �����Ώۂł���p_taxType�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate, and �̒l�ƈ�v����{@@link MutualFundDayBalanceRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, String p_taxType, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MutualFundDayBalanceRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and tax_type=? and delivery_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate(String, String, String, String, String, java.sql.Timestamp)}�����{@@link #forRow(MutualFundDayBalanceRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, String p_taxType, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxTypeDeliveryDate( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, p_deliveryDate ) );
    }

}
@
