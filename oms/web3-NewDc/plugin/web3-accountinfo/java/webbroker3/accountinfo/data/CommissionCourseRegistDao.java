head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommissionCourseRegistDao.java;


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
 * {@@link CommissionCourseRegistDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CommissionCourseRegistRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CommissionCourseRegistPK 
 * @@see CommissionCourseRegistRow 
 */
public class CommissionCourseRegistDao extends DataAccessObject {


  /** 
   * ����{@@link CommissionCourseRegistDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CommissionCourseRegistRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CommissionCourseRegistRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CommissionCourseRegistDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CommissionCourseRegistDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CommissionCourseRegistRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommissionCourseRegistRow )
                return new CommissionCourseRegistDao( (CommissionCourseRegistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommissionCourseRegistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommissionCourseRegistRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g 
    */
    protected CommissionCourseRegistDao( CommissionCourseRegistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CommissionCourseRegistRow getCommissionCourseRegistRow() {
        return row;
    }


  /** 
   * �w���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g����{@@link CommissionCourseRegistDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CommissionCourseRegistRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CommissionCourseRegistDao}�擾�̂��߂Ɏw���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CommissionCourseRegistDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CommissionCourseRegistDao forRow( CommissionCourseRegistRow row ) throws java.lang.IllegalArgumentException {
        return (CommissionCourseRegistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommissionCourseRegistRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CommissionCourseRegistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CommissionCourseRegistPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CommissionCourseRegistParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommissionCourseRegistRow.TYPE );
    }


  /** 
   * {@@link CommissionCourseRegistRow}����ӂɓ��肷��{@@link CommissionCourseRegistPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CommissionCourseRegistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CommissionCourseRegistParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CommissionCourseRegistPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CommissionCourseRegistPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new CommissionCourseRegistPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_commissionCourseRegistId �����Ώۂł���p_commissionCourseRegistId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommissionCourseRegistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommissionCourseRegistRow findRowByPk( long p_commissionCourseRegistId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseRegistPK pk = new CommissionCourseRegistPK( p_commissionCourseRegistId );
        return findRowByPk( pk );
    }


  /** 
   * �w���CommissionCourseRegistPK�I�u�W�F�N�g����{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CommissionCourseRegistPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommissionCourseRegistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommissionCourseRegistRow findRowByPk( CommissionCourseRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommissionCourseRegistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static CommissionCourseRegistDao findDaoByPk( long p_commissionCourseRegistId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseRegistPK pk = new CommissionCourseRegistPK( p_commissionCourseRegistId );
        CommissionCourseRegistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CommissionCourseRegistPK)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static CommissionCourseRegistDao findDaoByPk( CommissionCourseRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseRegistRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link CommissionCourseRegistDao}�ɕR�t��{@@link CommissionCourseRegistRow}���ŊO���L�[�̊֌W������{@@link BranchRow}���������܂��B 
   * 
   * @@return {@@link CommissionCourseRegistDao}�ƊO���L�[�̊֌W�ɂ���{@@link BranchRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public BranchRow fetchBranchRowViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        Row row = BranchDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BranchRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BranchRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBranchRowViaBranchId()}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * ����{@@link CommissionCourseRegistDao}�ɕR�t��{@@link CommissionCourseRegistRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link CommissionCourseRegistDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
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
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByBranchId(BranchRow)}���g�p���Ă��������B 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}�ƊO���L�[�̊֌W�ɂ���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BranchRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BranchRow}�ɊO���L�[������{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}�ƊO���L�[�̊֌W�ɂ���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BranchPK}�I�u�W�F�N�g 
   * @@return {@@link BranchPK}�ƊO���L�[����v����l������{@@link CommissionCourseRegistRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommissionCourseRegistRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchPK)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(long)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
    }


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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link CommissionCourseRegistRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommissionCourseRegistRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
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
   * p_commissionCourseRegistId, and �ɂĎw��̒l�����ӂ�{@@link CommissionCourseRegistRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_commissionCourseRegistId �����Ώۂł���p_commissionCourseRegistId�t�B�[���h�̒l
   * 
   * @@return �����w���p_commissionCourseRegistId, and �̒l�ƈ�v����{@@link CommissionCourseRegistRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CommissionCourseRegistRow findRowByCommissionCourseRegistId( long p_commissionCourseRegistId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommissionCourseRegistRow.TYPE,
            "commission_course_regist_id=?",
            null,
            new Object[] { new Long(p_commissionCourseRegistId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommissionCourseRegistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommissionCourseRegistDao.findRowsByCommissionCourseRegistId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByCommissionCourseRegistId(long)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static CommissionCourseRegistDao findDaoByCommissionCourseRegistId( long p_commissionCourseRegistId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCommissionCourseRegistId( p_commissionCourseRegistId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchId, p_accountId, p_commProductCode, p_appliStartDatetime, and �ɂĎw��̒l�Ɉ�v����{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_appliStartDatetime �����Ώۂł���p_appliStartDatetime�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchId, p_accountId, p_commProductCode, p_appliStartDatetime, and �̒l�ƈ�v����{@@link CommissionCourseRegistRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchIdAccountIdCommProductCodeAppliStartDatetime( String p_institutionCode, long p_branchId, long p_accountId, String p_commProductCode, java.sql.Timestamp p_appliStartDatetime ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommissionCourseRegistRow.TYPE,
            "institution_code=? and branch_id=? and account_id=? and comm_product_code=? and appli_start_datetime=?",
            null,
            new Object[] { p_institutionCode, new Long(p_branchId), new Long(p_accountId), p_commProductCode, p_appliStartDatetime } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchIdAccountIdCommProductCodeAppliStartDatetime(String, long, long, String, java.sql.Timestamp)}�����{@@link #forRow(CommissionCourseRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchIdAccountIdCommProductCodeAppliStartDatetime( String p_institutionCode, long p_branchId, long p_accountId, String p_commProductCode, java.sql.Timestamp p_appliStartDatetime ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchIdAccountIdCommProductCodeAppliStartDatetime( p_institutionCode, p_branchId, p_accountId, p_commProductCode, p_appliStartDatetime ) );
    }

}
@
