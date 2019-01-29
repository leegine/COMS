head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TransactionHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TransactionHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TransactionHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TransactionHistoryPK 
 * @@see TransactionHistoryRow 
 */
public class TransactionHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link TransactionHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TransactionHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TransactionHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TransactionHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TransactionHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TransactionHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TransactionHistoryRow )
                return new TransactionHistoryDao( (TransactionHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TransactionHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TransactionHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TransactionHistoryRow}�I�u�W�F�N�g 
    */
    protected TransactionHistoryDao( TransactionHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TransactionHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TransactionHistoryRow getTransactionHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link TransactionHistoryRow}�I�u�W�F�N�g����{@@link TransactionHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TransactionHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TransactionHistoryDao}�擾�̂��߂Ɏw���{@@link TransactionHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TransactionHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TransactionHistoryDao forRow( TransactionHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TransactionHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TransactionHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TransactionHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TransactionHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TransactionHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TransactionHistoryRow.TYPE );
    }


  /** 
   * {@@link TransactionHistoryRow}����ӂɓ��肷��{@@link TransactionHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TransactionHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TransactionHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TransactionHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TransactionHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TransactionHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TransactionHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_transactionHistoryId �����Ώۂł���p_transactionHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TransactionHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TransactionHistoryRow findRowByPk( long p_transactionHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TransactionHistoryPK pk = new TransactionHistoryPK( p_transactionHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TransactionHistoryPK�I�u�W�F�N�g����{@@link TransactionHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TransactionHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TransactionHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TransactionHistoryRow findRowByPk( TransactionHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TransactionHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TransactionHistoryRow)}���g�p���Ă��������B 
   */
    public static TransactionHistoryDao findDaoByPk( long p_transactionHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TransactionHistoryPK pk = new TransactionHistoryPK( p_transactionHistoryId );
        TransactionHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TransactionHistoryPK)}�����{@@link #forRow(TransactionHistoryRow)}���g�p���Ă��������B 
   */
    public static TransactionHistoryDao findDaoByPk( TransactionHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TransactionHistoryRow row = findRowByPk( pk );
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


  /** 
   * p_transactionHistoryId, and �ɂĎw��̒l�����ӂ�{@@link TransactionHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_transactionHistoryId �����Ώۂł���p_transactionHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���p_transactionHistoryId, and �̒l�ƈ�v����{@@link TransactionHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TransactionHistoryRow findRowByTransactionHistoryId( long p_transactionHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TransactionHistoryRow.TYPE,
            "transaction_history_id=?",
            null,
            new Object[] { new Long(p_transactionHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TransactionHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TransactionHistoryDao.findRowsByTransactionHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTransactionHistoryId(long)}�����{@@link #forRow(TransactionHistoryRow)}���g�p���Ă��������B 
   */
    public static TransactionHistoryDao findDaoByTransactionHistoryId( long p_transactionHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTransactionHistoryId( p_transactionHistoryId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv, and �ɂĎw��̒l�����ӂ�{@@link TransactionHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_depositMarginDiv �����Ώۂł���p_depositMarginDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv, and �̒l�ƈ�v����{@@link TransactionHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TransactionHistoryRow findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TransactionHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and deposit_margin_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TransactionHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TransactionHistoryDao.findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv(String, String, String, java.sql.Timestamp, String)}�����{@@link #forRow(TransactionHistoryRow)}���g�p���Ă��������B 
   */
    public static TransactionHistoryDao findDaoByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_depositMarginDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_deliveryDate, p_depositMarginDiv, and �ɂĎw��̒l�Ɉ�v����{@@link TransactionHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_depositMarginDiv �����Ώۂł���p_depositMarginDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_deliveryDate, p_depositMarginDiv, and �̒l�ƈ�v����{@@link TransactionHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByDeliveryDateDepositMarginDiv( java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TransactionHistoryRow.TYPE,
            "delivery_date=? and deposit_margin_div=?",
            null,
            new Object[] { p_deliveryDate, p_depositMarginDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByDeliveryDateDepositMarginDiv(java.sql.Timestamp, String)}�����{@@link #forRow(TransactionHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDeliveryDateDepositMarginDiv( java.sql.Timestamp p_deliveryDate, String p_depositMarginDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDateDepositMarginDiv( p_deliveryDate, p_depositMarginDiv ) );
    }

}
@
