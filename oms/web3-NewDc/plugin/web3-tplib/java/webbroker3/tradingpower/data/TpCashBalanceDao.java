head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCashBalanceDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TpCashBalanceDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TpCashBalanceRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TpCashBalancePK 
 * @@see TpCashBalanceRow 
 */
public class TpCashBalanceDao extends DataAccessObject {


  /** 
   * ����{@@link TpCashBalanceDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TpCashBalanceRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TpCashBalanceRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TpCashBalanceDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TpCashBalanceDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TpCashBalanceRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpCashBalanceRow )
                return new TpCashBalanceDao( (TpCashBalanceRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpCashBalanceRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpCashBalanceRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TpCashBalanceRow}�I�u�W�F�N�g 
    */
    protected TpCashBalanceDao( TpCashBalanceRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TpCashBalanceRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TpCashBalanceRow getTpCashBalanceRow() {
        return row;
    }


  /** 
   * �w���{@@link TpCashBalanceRow}�I�u�W�F�N�g����{@@link TpCashBalanceDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TpCashBalanceRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TpCashBalanceDao}�擾�̂��߂Ɏw���{@@link TpCashBalanceRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TpCashBalanceDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TpCashBalanceDao forRow( TpCashBalanceRow row ) throws java.lang.IllegalArgumentException {
        return (TpCashBalanceDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpCashBalanceRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TpCashBalanceRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TpCashBalancePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TpCashBalanceParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpCashBalanceRow.TYPE );
    }


  /** 
   * {@@link TpCashBalanceRow}����ӂɓ��肷��{@@link TpCashBalancePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TpCashBalanceRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TpCashBalanceParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TpCashBalancePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TpCashBalancePK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpCashBalancePK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TpCashBalanceRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_tpCashBalanceId �����Ώۂł���p_tpCashBalanceId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCashBalanceRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCashBalanceRow findRowByPk( long p_tpCashBalanceId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalancePK pk = new TpCashBalancePK( p_tpCashBalanceId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TpCashBalancePK�I�u�W�F�N�g����{@@link TpCashBalanceRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TpCashBalancePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCashBalanceRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCashBalanceRow findRowByPk( TpCashBalancePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpCashBalanceRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceDao findDaoByPk( long p_tpCashBalanceId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalancePK pk = new TpCashBalancePK( p_tpCashBalanceId );
        TpCashBalanceRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TpCashBalancePK)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceDao findDaoByPk( TpCashBalancePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalanceRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link TpCashBalanceDao}�ɕR�t��{@@link TpCashBalanceRow}���ŊO���L�[�̊֌W������{@@link SubAccountRow}���������܂��B 
   * 
   * @@return {@@link TpCashBalanceDao}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public SubAccountRow fetchSubAccountRowViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        Row row = SubAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof SubAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (SubAccountRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for SubAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( dao.getSubAccountRow() );
    }


  /** 
   * {@@link SubAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link TpCashBalanceRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link SubAccountRow}�ɊO���L�[������{@@link TpCashBalanceRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link TpCashBalanceRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link SubAccountPK}�I�u�W�F�N�g 
   * @@return {@@link SubAccountPK}�ƊO���L�[����v����l������{@@link TpCashBalanceRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link TpCashBalanceRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link TpCashBalanceRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCashBalanceRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(long, long)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * p_tpCashBalanceId, and �ɂĎw��̒l�����ӂ�{@@link TpCashBalanceRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tpCashBalanceId �����Ώۂł���p_tpCashBalanceId�t�B�[���h�̒l
   * 
   * @@return �����w���p_tpCashBalanceId, and �̒l�ƈ�v����{@@link TpCashBalanceRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TpCashBalanceRow findRowByTpCashBalanceId( long p_tpCashBalanceId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpCashBalanceRow.TYPE,
            "tp_cash_balance_id=?",
            null,
            new Object[] { new Long(p_tpCashBalanceId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpCashBalanceRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpCashBalanceDao.findRowsByTpCashBalanceId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTpCashBalanceId(long)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceDao findDaoByTpCashBalanceId( long p_tpCashBalanceId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTpCashBalanceId( p_tpCashBalanceId ) );
    }


  /** 
   * p_accountId, p_subAccountId, and �ɂĎw��̒l�����ӂ�{@@link TpCashBalanceRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, and �̒l�ƈ�v����{@@link TpCashBalanceRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TpCashBalanceRow findRowByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpCashBalanceRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpCashBalanceRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpCashBalanceDao.findRowsByAccountIdSubAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdSubAccountId(long, long)}�����{@@link #forRow(TpCashBalanceRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceDao findDaoByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
