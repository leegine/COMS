head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCashBalanceFrgnDao.java;


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
 * {@@link TpCashBalanceFrgnDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TpCashBalanceFrgnRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TpCashBalanceFrgnPK 
 * @@see TpCashBalanceFrgnRow 
 */
public class TpCashBalanceFrgnDao extends DataAccessObject {


  /** 
   * ����{@@link TpCashBalanceFrgnDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TpCashBalanceFrgnRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TpCashBalanceFrgnRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TpCashBalanceFrgnDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TpCashBalanceFrgnDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TpCashBalanceFrgnRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpCashBalanceFrgnRow )
                return new TpCashBalanceFrgnDao( (TpCashBalanceFrgnRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpCashBalanceFrgnRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpCashBalanceFrgnRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g 
    */
    protected TpCashBalanceFrgnDao( TpCashBalanceFrgnRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TpCashBalanceFrgnRow getTpCashBalanceFrgnRow() {
        return row;
    }


  /** 
   * �w���{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g����{@@link TpCashBalanceFrgnDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TpCashBalanceFrgnRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TpCashBalanceFrgnDao}�擾�̂��߂Ɏw���{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TpCashBalanceFrgnDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TpCashBalanceFrgnDao forRow( TpCashBalanceFrgnRow row ) throws java.lang.IllegalArgumentException {
        return (TpCashBalanceFrgnDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpCashBalanceFrgnRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TpCashBalanceFrgnRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TpCashBalanceFrgnPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TpCashBalanceFrgnParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpCashBalanceFrgnRow.TYPE );
    }


  /** 
   * {@@link TpCashBalanceFrgnRow}����ӂɓ��肷��{@@link TpCashBalanceFrgnPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TpCashBalanceFrgnRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TpCashBalanceFrgnParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TpCashBalanceFrgnPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TpCashBalanceFrgnPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpCashBalanceFrgnPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_tpCashBalanceFrgnId �����Ώۂł���p_tpCashBalanceFrgnId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCashBalanceFrgnRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCashBalanceFrgnRow findRowByPk( long p_tpCashBalanceFrgnId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalanceFrgnPK pk = new TpCashBalanceFrgnPK( p_tpCashBalanceFrgnId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TpCashBalanceFrgnPK�I�u�W�F�N�g����{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TpCashBalanceFrgnPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCashBalanceFrgnRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCashBalanceFrgnRow findRowByPk( TpCashBalanceFrgnPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpCashBalanceFrgnRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceFrgnDao findDaoByPk( long p_tpCashBalanceFrgnId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalanceFrgnPK pk = new TpCashBalanceFrgnPK( p_tpCashBalanceFrgnId );
        TpCashBalanceFrgnRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TpCashBalanceFrgnPK)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceFrgnDao findDaoByPk( TpCashBalanceFrgnPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCashBalanceFrgnRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link TpCashBalanceFrgnDao}�ɕR�t��{@@link TpCashBalanceFrgnRow}���ŊO���L�[�̊֌W������{@@link SubAccountRow}���������܂��B 
   * 
   * @@return {@@link TpCashBalanceFrgnDao}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow} 
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
   * @@deprecated �����{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
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
   * {@@link SubAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link SubAccountRow}�ɊO���L�[������{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link SubAccountPK}�I�u�W�F�N�g 
   * @@return {@@link SubAccountPK}�ƊO���L�[����v����l������{@@link TpCashBalanceFrgnRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCashBalanceFrgnRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(long, long)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
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
   * p_tpCashBalanceFrgnId, and �ɂĎw��̒l�����ӂ�{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tpCashBalanceFrgnId �����Ώۂł���p_tpCashBalanceFrgnId�t�B�[���h�̒l
   * 
   * @@return �����w���p_tpCashBalanceFrgnId, and �̒l�ƈ�v����{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TpCashBalanceFrgnRow findRowByTpCashBalanceFrgnId( long p_tpCashBalanceFrgnId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpCashBalanceFrgnRow.TYPE,
            "tp_cash_balance_frgn_id=?",
            null,
            new Object[] { new Long(p_tpCashBalanceFrgnId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpCashBalanceFrgnRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpCashBalanceFrgnDao.findRowsByTpCashBalanceFrgnId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTpCashBalanceFrgnId(long)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceFrgnDao findDaoByTpCashBalanceFrgnId( long p_tpCashBalanceFrgnId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTpCashBalanceFrgnId( p_tpCashBalanceFrgnId ) );
    }


  /** 
   * p_accountId, p_currencyCode, and �ɂĎw��̒l�����ӂ�{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_currencyCode �����Ώۂł���p_currencyCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_currencyCode, and �̒l�ƈ�v����{@@link TpCashBalanceFrgnRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TpCashBalanceFrgnRow findRowByAccountIdCurrencyCode( long p_accountId, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpCashBalanceFrgnRow.TYPE,
            "account_id=? and currency_code=?",
            null,
            new Object[] { new Long(p_accountId), p_currencyCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpCashBalanceFrgnRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpCashBalanceFrgnDao.findRowsByAccountIdCurrencyCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdCurrencyCode(long, String)}�����{@@link #forRow(TpCashBalanceFrgnRow)}���g�p���Ă��������B 
   */
    public static TpCashBalanceFrgnDao findDaoByAccountIdCurrencyCode( long p_accountId, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdCurrencyCode( p_accountId, p_currencyCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
