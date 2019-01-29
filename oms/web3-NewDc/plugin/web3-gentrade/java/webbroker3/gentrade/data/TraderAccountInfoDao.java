head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TraderAccountInfoDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TraderAccountInfoDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TraderAccountInfoRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TraderAccountInfoPK 
 * @@see TraderAccountInfoRow 
 */
public class TraderAccountInfoDao extends DataAccessObject {


  /** 
   * ����{@@link TraderAccountInfoDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TraderAccountInfoRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TraderAccountInfoRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TraderAccountInfoDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TraderAccountInfoDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TraderAccountInfoRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TraderAccountInfoRow )
                return new TraderAccountInfoDao( (TraderAccountInfoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TraderAccountInfoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TraderAccountInfoRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TraderAccountInfoRow}�I�u�W�F�N�g 
    */
    protected TraderAccountInfoDao( TraderAccountInfoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TraderAccountInfoRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TraderAccountInfoRow getTraderAccountInfoRow() {
        return row;
    }


  /** 
   * �w���{@@link TraderAccountInfoRow}�I�u�W�F�N�g����{@@link TraderAccountInfoDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TraderAccountInfoRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TraderAccountInfoDao}�擾�̂��߂Ɏw���{@@link TraderAccountInfoRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TraderAccountInfoDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TraderAccountInfoDao forRow( TraderAccountInfoRow row ) throws java.lang.IllegalArgumentException {
        return (TraderAccountInfoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TraderAccountInfoRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TraderAccountInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TraderAccountInfoPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TraderAccountInfoParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TraderAccountInfoRow.TYPE );
    }


  /** 
   * {@@link TraderAccountInfoRow}����ӂɓ��肷��{@@link TraderAccountInfoPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TraderAccountInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TraderAccountInfoParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TraderAccountInfoPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TraderAccountInfoPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TraderAccountInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_traderId �����Ώۂł���p_traderId�t�B�[���h�̒l
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TraderAccountInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TraderAccountInfoRow findRowByPk( long p_traderId, long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        TraderAccountInfoPK pk = new TraderAccountInfoPK( p_traderId, p_accountId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TraderAccountInfoPK�I�u�W�F�N�g����{@@link TraderAccountInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TraderAccountInfoPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TraderAccountInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TraderAccountInfoRow findRowByPk( TraderAccountInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TraderAccountInfoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,long)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static TraderAccountInfoDao findDaoByPk( long p_traderId, long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        TraderAccountInfoPK pk = new TraderAccountInfoPK( p_traderId, p_accountId );
        TraderAccountInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TraderAccountInfoPK)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static TraderAccountInfoDao findDaoByPk( TraderAccountInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TraderAccountInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link TraderAccountInfoDao}�ɕR�t��{@@link TraderAccountInfoRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link TraderAccountInfoDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public MainAccountRow fetchMainAccountRowViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        Row row = MainAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MainAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MainAccountRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for MainAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByAccountId(MainAccountRow)}���g�p���Ă��������B 
   */
    public static List findRowsByAccountId( MainAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( dao.getMainAccountRow() );
    }


  /** 
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link TraderAccountInfoRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link TraderAccountInfoRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link TraderAccountInfoRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link TraderAccountInfoRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link TraderAccountInfoRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link TraderAccountInfoRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TraderAccountInfoRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
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
   * p_traderId, p_accountId, and �ɂĎw��̒l�����ӂ�{@@link TraderAccountInfoRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_traderId �����Ώۂł���p_traderId�t�B�[���h�̒l
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �����w���p_traderId, p_accountId, and �̒l�ƈ�v����{@@link TraderAccountInfoRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TraderAccountInfoRow findRowByTraderIdAccountId( long p_traderId, long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TraderAccountInfoRow.TYPE,
            "trader_id=? and account_id=?",
            null,
            new Object[] { new Long(p_traderId), new Long(p_accountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TraderAccountInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TraderAccountInfoDao.findRowsByTraderIdAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTraderIdAccountId(long, long)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static TraderAccountInfoDao findDaoByTraderIdAccountId( long p_traderId, long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTraderIdAccountId( p_traderId, p_accountId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_traderId, and �ɂĎw��̒l�Ɉ�v����{@@link TraderAccountInfoRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_traderId �����Ώۂł���p_traderId�t�B�[���h�̒l
   * 
   * @@return �����w���p_traderId, and �̒l�ƈ�v����{@@link TraderAccountInfoRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTraderId( long p_traderId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TraderAccountInfoRow.TYPE,
            "trader_id=?",
            null,
            new Object[] { new Long(p_traderId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTraderId(long)}�����{@@link #forRow(TraderAccountInfoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( long p_traderId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTraderId( p_traderId ) );
    }

}
@
