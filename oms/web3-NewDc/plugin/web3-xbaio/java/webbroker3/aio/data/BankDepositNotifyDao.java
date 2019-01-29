head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.42.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankDepositNotifyDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link BankDepositNotifyDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BankDepositNotifyRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BankDepositNotifyPK 
 * @@see BankDepositNotifyRow 
 */
public class BankDepositNotifyDao extends DataAccessObject {


  /** 
   * ����{@@link BankDepositNotifyDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BankDepositNotifyRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BankDepositNotifyRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BankDepositNotifyDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BankDepositNotifyDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BankDepositNotifyRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankDepositNotifyRow )
                return new BankDepositNotifyDao( (BankDepositNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankDepositNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankDepositNotifyRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BankDepositNotifyRow}�I�u�W�F�N�g 
    */
    protected BankDepositNotifyDao( BankDepositNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BankDepositNotifyRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BankDepositNotifyRow getBankDepositNotifyRow() {
        return row;
    }


  /** 
   * �w���{@@link BankDepositNotifyRow}�I�u�W�F�N�g����{@@link BankDepositNotifyDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BankDepositNotifyRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BankDepositNotifyDao}�擾�̂��߂Ɏw���{@@link BankDepositNotifyRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BankDepositNotifyDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BankDepositNotifyDao forRow( BankDepositNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (BankDepositNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankDepositNotifyRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BankDepositNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BankDepositNotifyPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BankDepositNotifyParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankDepositNotifyRow.TYPE );
    }


  /** 
   * {@@link BankDepositNotifyRow}����ӂɓ��肷��{@@link BankDepositNotifyPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BankDepositNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BankDepositNotifyParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BankDepositNotifyPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BankDepositNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BankDepositNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_bankDepositNotifyId �����Ώۂł���p_bankDepositNotifyId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_dataLoadDiv �����Ώۂł���p_dataLoadDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankDepositNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankDepositNotifyRow findRowByPk( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���BankDepositNotifyPK�I�u�W�F�N�g����{@@link BankDepositNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BankDepositNotifyPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankDepositNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankDepositNotifyRow findRowByPk( BankDepositNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankDepositNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,String)}�����{@@link #forRow(BankDepositNotifyRow)}���g�p���Ă��������B 
   */
    public static BankDepositNotifyDao findDaoByPk( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv );
        BankDepositNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BankDepositNotifyPK)}�����{@@link #forRow(BankDepositNotifyRow)}���g�p���Ă��������B 
   */
    public static BankDepositNotifyDao findDaoByPk( BankDepositNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositNotifyRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link BankDepositNotifyDao}�Ɋ֘A����{@@link BankDepositNotifyRow}�̊O���L�[������{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link BankDepositNotifyDao}�Ɋ֘A����{@@link BankDepositNotifyRow}�̊O���L�[������{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchBankDepositErrorHistoryRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataQueryException  {
        return BankDepositErrorHistoryDao.findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBankDepositErrorHistoryRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv()}�����{@@link #forRow(BankDepositNotifyRow)}���g�p���Ă��������B 
   */
    public List fetchBankDepositErrorHistoryDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataQueryException  {
        return BankDepositErrorHistoryDao.findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBankDepositErrorHistoryRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv()}�����{@@link #forRow(BankDepositNotifyRow)}���g�p���Ă��������B 
   */
    public List fetchBankDepositErrorHistoryDaosBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataQueryException  {
        return fetchBankDepositErrorHistoryDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv();
    }


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, and �ɂĎw��̒l�����ӂ�{@@link BankDepositNotifyRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_bankDepositNotifyId �����Ώۂł���p_bankDepositNotifyId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_dataLoadDiv �����Ώۂł���p_dataLoadDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, and �̒l�ƈ�v����{@@link BankDepositNotifyRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BankDepositNotifyRow findRowByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositNotifyRow.TYPE,
            "bank_deposit_notify_id=? and institution_code=? and data_load_div=?",
            null,
            new Object[] { new Long(p_bankDepositNotifyId), p_institutionCode, p_dataLoadDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositNotifyDao.findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBankDepositNotifyIdInstitutionCodeDataLoadDiv(long, String, String)}�����{@@link #forRow(BankDepositNotifyRow)}���g�p���Ă��������B 
   */
    public static BankDepositNotifyDao findDaoByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBankDepositNotifyIdInstitutionCodeDataLoadDiv( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv ) );
    }


  /** 
   * p_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv, and �ɂĎw��̒l�����ӂ�{@@link BankDepositNotifyRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_bankCode �����Ώۂł���p_bankCode�t�B�[���h�̒l
   * @@param p_bankBranchCode �����Ώۂł���p_bankBranchCode�t�B�[���h�̒l
   * @@param p_bankAccountNo �����Ώۂł���p_bankAccountNo�t�B�[���h�̒l
   * @@param p_depositDataReferenceNo �����Ώۂł���p_depositDataReferenceNo�t�B�[���h�̒l
   * @@param p_depositDataAccountDate �����Ώۂł���p_depositDataAccountDate�t�B�[���h�̒l
   * @@param p_dataLoadDiv �����Ώۂł���p_dataLoadDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv, and �̒l�ƈ�v����{@@link BankDepositNotifyRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BankDepositNotifyRow findRowByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv( String p_institutionCode, String p_bankCode, String p_bankBranchCode, String p_bankAccountNo, String p_depositDataReferenceNo, String p_depositDataAccountDate, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositNotifyRow.TYPE,
            "institution_code=? and bank_code=? and bank_branch_code=? and bank_account_no=? and deposit_data_reference_no=? and deposit_data_account_date=? and data_load_div=?",
            null,
            new Object[] { p_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositNotifyDao.findRowsByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv(String, String, String, String, String, String, String)}�����{@@link #forRow(BankDepositNotifyRow)}���g�p���Ă��������B 
   */
    public static BankDepositNotifyDao findDaoByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv( String p_institutionCode, String p_bankCode, String p_bankBranchCode, String p_bankAccountNo, String p_depositDataReferenceNo, String p_depositDataAccountDate, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv( p_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
