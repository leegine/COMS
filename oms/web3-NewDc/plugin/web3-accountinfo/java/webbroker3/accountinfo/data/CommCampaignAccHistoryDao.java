head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignAccHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommCampaignAccHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CommCampaignAccHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CommCampaignAccHistoryPK 
 * @@see CommCampaignAccHistoryRow 
 */
public class CommCampaignAccHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link CommCampaignAccHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CommCampaignAccHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CommCampaignAccHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CommCampaignAccHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CommCampaignAccHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CommCampaignAccHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCampaignAccHistoryRow )
                return new CommCampaignAccHistoryDao( (CommCampaignAccHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCampaignAccHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCampaignAccHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g 
    */
    protected CommCampaignAccHistoryDao( CommCampaignAccHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CommCampaignAccHistoryRow getCommCampaignAccHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g����{@@link CommCampaignAccHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CommCampaignAccHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CommCampaignAccHistoryDao}�擾�̂��߂Ɏw���{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CommCampaignAccHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CommCampaignAccHistoryDao forRow( CommCampaignAccHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (CommCampaignAccHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCampaignAccHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CommCampaignAccHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CommCampaignAccHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CommCampaignAccHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCampaignAccHistoryRow.TYPE );
    }


  /** 
   * {@@link CommCampaignAccHistoryRow}����ӂɓ��肷��{@@link CommCampaignAccHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CommCampaignAccHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CommCampaignAccHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CommCampaignAccHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CommCampaignAccHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCampaignAccHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCampaignAccHistoryRow findRowByPk( long p_accountId, long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignAccHistoryPK pk = new CommCampaignAccHistoryPK( p_accountId, p_campaignId );
        return findRowByPk( pk );
    }


  /** 
   * �w���CommCampaignAccHistoryPK�I�u�W�F�N�g����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CommCampaignAccHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCampaignAccHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCampaignAccHistoryRow findRowByPk( CommCampaignAccHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCampaignAccHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,long)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static CommCampaignAccHistoryDao findDaoByPk( long p_accountId, long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignAccHistoryPK pk = new CommCampaignAccHistoryPK( p_accountId, p_campaignId );
        CommCampaignAccHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CommCampaignAccHistoryPK)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static CommCampaignAccHistoryDao findDaoByPk( CommCampaignAccHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignAccHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link CommCampaignAccHistoryDao}�ɕR�t��{@@link CommCampaignAccHistoryRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link CommCampaignAccHistoryDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link CommCampaignAccHistoryRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
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
   * p_accountId, p_campaignId, and �ɂĎw��̒l�����ӂ�{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_campaignId, and �̒l�ƈ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CommCampaignAccHistoryRow findRowByAccountIdCampaignId( long p_accountId, long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "account_id=? and campaign_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_campaignId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCampaignAccHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCampaignAccHistoryDao.findRowsByAccountIdCampaignId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdCampaignId(long, long)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static CommCampaignAccHistoryDao findDaoByAccountIdCampaignId( long p_accountId, long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdCampaignId( p_accountId, p_campaignId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_campaignId, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * 
   * @@return �����w���p_campaignId, and �̒l�ƈ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCampaignId( long p_campaignId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "campaign_id=?",
            null,
            new Object[] { new Long(p_campaignId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCampaignId(long)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCampaignId( long p_campaignId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCampaignId( p_campaignId ) );
    }


  /** 
   * p_appliEndDate, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_appliEndDate �����Ώۂł���p_appliEndDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_appliEndDate, and �̒l�ƈ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAppliEndDate( java.sql.Timestamp p_appliEndDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "appli_end_date=?",
            null,
            new Object[] { p_appliEndDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAppliEndDate(java.sql.Timestamp)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAppliEndDate( java.sql.Timestamp p_appliEndDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAppliEndDate( p_appliEndDate ) );
    }


  /** 
   * p_lastUpdatedTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_lastUpdatedTimestamp �����Ώۂł���p_lastUpdatedTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_lastUpdatedTimestamp, and �̒l�ƈ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "last_updated_timestamp=?",
            null,
            new Object[] { p_lastUpdatedTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByLastUpdatedTimestamp(java.sql.Timestamp)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByLastUpdatedTimestamp( p_lastUpdatedTimestamp ) );
    }


  /** 
   * p_appliStartDate, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_appliStartDate, and �̒l�ƈ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAppliStartDate( java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "appli_start_date=?",
            null,
            new Object[] { p_appliStartDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAppliStartDate(java.sql.Timestamp)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAppliStartDate( java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAppliStartDate( p_appliStartDate ) );
    }


  /** 
   * p_accountCode, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountCode, and �̒l�ƈ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "account_code=?",
            null,
            new Object[] { p_accountCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountCode(String)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountCode( p_accountCode ) );
    }


  /** 
   * p_accOpenKindDiv, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accOpenKindDiv �����Ώۂł���p_accOpenKindDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_accOpenKindDiv, and �̒l�ƈ�v����{@@link CommCampaignAccHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccOpenKindDiv( String p_accOpenKindDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignAccHistoryRow.TYPE,
            "acc_open_kind_div=?",
            null,
            new Object[] { p_accOpenKindDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccOpenKindDiv(String)}�����{@@link #forRow(CommCampaignAccHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccOpenKindDiv( String p_accOpenKindDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccOpenKindDiv( p_accOpenKindDiv ) );
    }

}
@
