head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	BankDepositErrorHistoryDao.java;


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
 * {@@link BankDepositErrorHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BankDepositErrorHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BankDepositErrorHistoryPK 
 * @@see BankDepositErrorHistoryRow 
 */
public class BankDepositErrorHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link BankDepositErrorHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BankDepositErrorHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BankDepositErrorHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BankDepositErrorHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BankDepositErrorHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BankDepositErrorHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankDepositErrorHistoryRow )
                return new BankDepositErrorHistoryDao( (BankDepositErrorHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankDepositErrorHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankDepositErrorHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g 
    */
    protected BankDepositErrorHistoryDao( BankDepositErrorHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BankDepositErrorHistoryRow getBankDepositErrorHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g����{@@link BankDepositErrorHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BankDepositErrorHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BankDepositErrorHistoryDao}�擾�̂��߂Ɏw���{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BankDepositErrorHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BankDepositErrorHistoryDao forRow( BankDepositErrorHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (BankDepositErrorHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankDepositErrorHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BankDepositErrorHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BankDepositErrorHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BankDepositErrorHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankDepositErrorHistoryRow.TYPE );
    }


  /** 
   * {@@link BankDepositErrorHistoryRow}����ӂɓ��肷��{@@link BankDepositErrorHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BankDepositErrorHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BankDepositErrorHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BankDepositErrorHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BankDepositErrorHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BankDepositErrorHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_bankDepositErrorHistoryId �����Ώۂł���p_bankDepositErrorHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankDepositErrorHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankDepositErrorHistoryRow findRowByPk( long p_bankDepositErrorHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositErrorHistoryPK pk = new BankDepositErrorHistoryPK( p_bankDepositErrorHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���BankDepositErrorHistoryPK�I�u�W�F�N�g����{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BankDepositErrorHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankDepositErrorHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankDepositErrorHistoryRow findRowByPk( BankDepositErrorHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankDepositErrorHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static BankDepositErrorHistoryDao findDaoByPk( long p_bankDepositErrorHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositErrorHistoryPK pk = new BankDepositErrorHistoryPK( p_bankDepositErrorHistoryId );
        BankDepositErrorHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BankDepositErrorHistoryPK)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static BankDepositErrorHistoryDao findDaoByPk( BankDepositErrorHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositErrorHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BankDepositErrorHistoryDao}�ɕR�t��{@@link BankDepositErrorHistoryRow}���ŊO���L�[�̊֌W������{@@link BankDepositNotifyRow}���������܂��B 
   * 
   * @@return {@@link BankDepositErrorHistoryDao}�ƊO���L�[�̊֌W�ɂ���{@@link BankDepositNotifyRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public BankDepositNotifyRow fetchBankDepositNotifyRowViaBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataFindException, DataQueryException  {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( row.getBankDepositNotifyId(), row.getInstitutionCode(), row.getDataLoadDiv() );
        Row row = BankDepositNotifyDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BankDepositNotifyRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BankDepositNotifyRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBankDepositNotifyRowViaBankDepositNotifyIdInstitutionCodeDataLoadDiv()}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public BankDepositNotifyDao fetchBankDepositNotifyDaoViaBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataFindException, DataQueryException  {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( row.getBankDepositNotifyId(), row.getInstitutionCode(), row.getDataLoadDiv() );
        DataAccessObject dao = BankDepositNotifyDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BankDepositNotifyDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BankDepositNotifyDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for BankDepositNotify
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyRow)}���g�p���Ă��������B 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( dao.getBankDepositNotifyRow() );
    }


  /** 
   * {@@link BankDepositNotifyRow}�ƊO���L�[�̊֌W�ɂ���{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BankDepositNotifyRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BankDepositNotifyRow}�ɊO���L�[������{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row.getBankDepositNotifyId(), row.getInstitutionCode(), row.getDataLoadDiv() );
    }


  /** 
   * {@@link BankDepositNotifyPK}�ƊO���L�[�̊֌W�ɂ���{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BankDepositNotifyPK}�I�u�W�F�N�g 
   * @@return {@@link BankDepositNotifyPK}�ƊO���L�[����v����l������{@@link BankDepositErrorHistoryRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( pk.bank_deposit_notify_id, pk.institution_code, pk.data_load_div );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_bankDepositNotifyId �����Ώۂł���p_bankDepositNotifyId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_dataLoadDiv �����Ώۂł���p_dataLoadDiv�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BankDepositErrorHistoryRow.TYPE,
            "bank_deposit_notify_id=? and institution_code=? and data_load_div=?",
            null,
            new Object[] { new Long(p_bankDepositNotifyId), p_institutionCode, p_dataLoadDiv } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BankDepositNotify
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyRow)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyRow)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(BankDepositNotifyPK)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( BankDepositNotifyPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( pk.bank_deposit_notify_id, pk.institution_code, pk.data_load_div ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(long, String, String)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv ) );
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
   * p_bankDepositErrorHistoryId, and �ɂĎw��̒l�����ӂ�{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_bankDepositErrorHistoryId �����Ώۂł���p_bankDepositErrorHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���p_bankDepositErrorHistoryId, and �̒l�ƈ�v����{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BankDepositErrorHistoryRow findRowByBankDepositErrorHistoryId( long p_bankDepositErrorHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositErrorHistoryRow.TYPE,
            "bank_deposit_error_history_id=?",
            null,
            new Object[] { new Long(p_bankDepositErrorHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositErrorHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositErrorHistoryDao.findRowsByBankDepositErrorHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBankDepositErrorHistoryId(long)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static BankDepositErrorHistoryDao findDaoByBankDepositErrorHistoryId( long p_bankDepositErrorHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBankDepositErrorHistoryId( p_bankDepositErrorHistoryId ) );
    }


  /** 
   * p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, p_serialNo, and �ɂĎw��̒l�����ӂ�{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_bankDepositNotifyId �����Ώۂł���p_bankDepositNotifyId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_dataLoadDiv �����Ώۂł���p_dataLoadDiv�t�B�[���h�̒l
   * @@param p_serialNo �����Ώۂł���p_serialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, p_serialNo, and �̒l�ƈ�v����{@@link BankDepositErrorHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BankDepositErrorHistoryRow findRowByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv, int p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositErrorHistoryRow.TYPE,
            "bank_deposit_notify_id=? and institution_code=? and data_load_div=? and serial_no=?",
            null,
            new Object[] { new Long(p_bankDepositNotifyId), p_institutionCode, p_dataLoadDiv, new Integer(p_serialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositErrorHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositErrorHistoryDao.findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo(long, String, String, int)}�����{@@link #forRow(BankDepositErrorHistoryRow)}���g�p���Ă��������B 
   */
    public static BankDepositErrorHistoryDao findDaoByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv, int p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBankDepositNotifyIdInstitutionCodeDataLoadDivSerialNo( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
