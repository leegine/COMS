head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.57.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitionMarginDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpoweradmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link PaymentRequisitionMarginDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link PaymentRequisitionMarginRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see PaymentRequisitionMarginPK 
 * @@see PaymentRequisitionMarginRow 
 */
public class PaymentRequisitionMarginDao extends DataAccessObject {


  /** 
   * ����{@@link PaymentRequisitionMarginDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private PaymentRequisitionMarginRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link PaymentRequisitionMarginRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link PaymentRequisitionMarginDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link PaymentRequisitionMarginDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link PaymentRequisitionMarginRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PaymentRequisitionMarginRow )
                return new PaymentRequisitionMarginDao( (PaymentRequisitionMarginRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PaymentRequisitionMarginRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PaymentRequisitionMarginRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g 
    */
    protected PaymentRequisitionMarginDao( PaymentRequisitionMarginRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g���擾���܂��B
   */
    public PaymentRequisitionMarginRow getPaymentRequisitionMarginRow() {
        return row;
    }


  /** 
   * �w���{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g����{@@link PaymentRequisitionMarginDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link PaymentRequisitionMarginRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link PaymentRequisitionMarginDao}�擾�̂��߂Ɏw���{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link PaymentRequisitionMarginDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static PaymentRequisitionMarginDao forRow( PaymentRequisitionMarginRow row ) throws java.lang.IllegalArgumentException {
        return (PaymentRequisitionMarginDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PaymentRequisitionMarginRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link PaymentRequisitionMarginRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link PaymentRequisitionMarginPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link PaymentRequisitionMarginParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PaymentRequisitionMarginRow.TYPE );
    }


  /** 
   * {@@link PaymentRequisitionMarginRow}����ӂɓ��肷��{@@link PaymentRequisitionMarginPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link PaymentRequisitionMarginRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link PaymentRequisitionMarginParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link PaymentRequisitionMarginPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static PaymentRequisitionMarginPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_calcDate �����Ώۂł���p_calcDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PaymentRequisitionMarginRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PaymentRequisitionMarginRow findRowByPk( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitionMarginPK pk = new PaymentRequisitionMarginPK( p_accountId, p_calcDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���PaymentRequisitionMarginPK�I�u�W�F�N�g����{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����PaymentRequisitionMarginPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PaymentRequisitionMarginRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PaymentRequisitionMarginRow findRowByPk( PaymentRequisitionMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PaymentRequisitionMarginRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,java.sql.Timestamp)}�����{@@link #forRow(PaymentRequisitionMarginRow)}���g�p���Ă��������B 
   */
    public static PaymentRequisitionMarginDao findDaoByPk( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitionMarginPK pk = new PaymentRequisitionMarginPK( p_accountId, p_calcDate );
        PaymentRequisitionMarginRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(PaymentRequisitionMarginPK)}�����{@@link #forRow(PaymentRequisitionMarginRow)}���g�p���Ă��������B 
   */
    public static PaymentRequisitionMarginDao findDaoByPk( PaymentRequisitionMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitionMarginRow row = findRowByPk( pk );
        return forRow( row );
    }


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
   * p_calcResultMarginId, and �ɂĎw��̒l�Ɉ�v����{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_calcResultMarginId �����Ώۂł���p_calcResultMarginId�t�B�[���h�̒l
   * 
   * @@return �����w���p_calcResultMarginId, and �̒l�ƈ�v����{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcResultMarginId( long p_calcResultMarginId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PaymentRequisitionMarginRow.TYPE,
            "calc_result_margin_id=?",
            null,
            new Object[] { new Long(p_calcResultMarginId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcResultMarginId(long)}�����{@@link #forRow(PaymentRequisitionMarginRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultMarginId( long p_calcResultMarginId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCalcResultMarginId( p_calcResultMarginId ) );
    }


  /** 
   * p_calcDate, p_institutionCode, p_branchCode, p_accountCode, and �ɂĎw��̒l�Ɉ�v����{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_calcDate �����Ώۂł���p_calcDate�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_calcDate, p_institutionCode, p_branchCode, p_accountCode, and �̒l�ƈ�v����{@@link PaymentRequisitionMarginRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcDateInstitutionCodeBranchCodeAccountCode( java.sql.Timestamp p_calcDate, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PaymentRequisitionMarginRow.TYPE,
            "calc_date=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_calcDate, p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcDateInstitutionCodeBranchCodeAccountCode(java.sql.Timestamp, String, String, String)}�����{@@link #forRow(PaymentRequisitionMarginRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcDateInstitutionCodeBranchCodeAccountCode( java.sql.Timestamp p_calcDate, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCalcDateInstitutionCodeBranchCodeAccountCode( p_calcDate, p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
