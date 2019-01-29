head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityDetailDao.java;


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
 * {@@link TpCalcResultEquityDetailDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TpCalcResultEquityDetailRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TpCalcResultEquityDetailPK 
 * @@see TpCalcResultEquityDetailRow 
 */
public class TpCalcResultEquityDetailDao extends DataAccessObject {


  /** 
   * ����{@@link TpCalcResultEquityDetailDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TpCalcResultEquityDetailRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TpCalcResultEquityDetailRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TpCalcResultEquityDetailDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TpCalcResultEquityDetailDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TpCalcResultEquityDetailRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpCalcResultEquityDetailRow )
                return new TpCalcResultEquityDetailDao( (TpCalcResultEquityDetailRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpCalcResultEquityDetailRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpCalcResultEquityDetailRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g 
    */
    protected TpCalcResultEquityDetailDao( TpCalcResultEquityDetailRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TpCalcResultEquityDetailRow getTpCalcResultEquityDetailRow() {
        return row;
    }


  /** 
   * �w���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g����{@@link TpCalcResultEquityDetailDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TpCalcResultEquityDetailRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TpCalcResultEquityDetailDao}�擾�̂��߂Ɏw���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TpCalcResultEquityDetailDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TpCalcResultEquityDetailDao forRow( TpCalcResultEquityDetailRow row ) throws java.lang.IllegalArgumentException {
        return (TpCalcResultEquityDetailDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpCalcResultEquityDetailRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TpCalcResultEquityDetailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TpCalcResultEquityDetailPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TpCalcResultEquityDetailParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpCalcResultEquityDetailRow.TYPE );
    }


  /** 
   * {@@link TpCalcResultEquityDetailRow}����ӂɓ��肷��{@@link TpCalcResultEquityDetailPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TpCalcResultEquityDetailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TpCalcResultEquityDetailParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TpCalcResultEquityDetailPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TpCalcResultEquityDetailPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpCalcResultEquityDetailPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_calcResultEquityId �����Ώۂł���p_calcResultEquityId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCalcResultEquityDetailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCalcResultEquityDetailRow findRowByPk( long p_calcResultEquityId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultEquityDetailPK pk = new TpCalcResultEquityDetailPK( p_calcResultEquityId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TpCalcResultEquityDetailPK�I�u�W�F�N�g����{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TpCalcResultEquityDetailPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCalcResultEquityDetailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCalcResultEquityDetailRow findRowByPk( TpCalcResultEquityDetailPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpCalcResultEquityDetailRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static TpCalcResultEquityDetailDao findDaoByPk( long p_calcResultEquityId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultEquityDetailPK pk = new TpCalcResultEquityDetailPK( p_calcResultEquityId );
        TpCalcResultEquityDetailRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TpCalcResultEquityDetailPK)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static TpCalcResultEquityDetailDao findDaoByPk( TpCalcResultEquityDetailPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultEquityDetailRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link TpCalcResultEquityDetailDao}�ɕR�t��{@@link TpCalcResultEquityDetailRow}���ŊO���L�[�̊֌W������{@@link TpCalcResultEquityRow}���������܂��B 
   * 
   * @@return {@@link TpCalcResultEquityDetailDao}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultEquityRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public TpCalcResultEquityRow fetchTpCalcResultEquityRowViaCalcResultEquityId() throws DataNetworkException, DataFindException, DataQueryException  {
        TpCalcResultEquityPK pk = new TpCalcResultEquityPK( row.getCalcResultEquityId() );
        Row row = TpCalcResultEquityDao.findRowByPk( pk );
        if ( row != null && !(row instanceof TpCalcResultEquityRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (TpCalcResultEquityRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchTpCalcResultEquityRowViaCalcResultEquityId()}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public TpCalcResultEquityDao fetchTpCalcResultEquityDaoViaCalcResultEquityId() throws DataNetworkException, DataFindException, DataQueryException  {
        TpCalcResultEquityPK pk = new TpCalcResultEquityPK( row.getCalcResultEquityId() );
        DataAccessObject dao = TpCalcResultEquityDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TpCalcResultEquityDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TpCalcResultEquityDao) dao;
    }


  /** 
   * ����{@@link TpCalcResultEquityDetailDao}�ɕR�t��{@@link TpCalcResultEquityDetailRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link TpCalcResultEquityDetailDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
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
    // Find Rows given foreign key values for TpCalcResultEquity
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByCalcResultEquityId(TpCalcResultEquityRow)}���g�p���Ă��������B 
   */
    public static List findRowsByCalcResultEquityId( TpCalcResultEquityDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByCalcResultEquityId( dao.getTpCalcResultEquityRow() );
    }


  /** 
   * {@@link TpCalcResultEquityRow}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link TpCalcResultEquityRow}�I�u�W�F�N�g 
   * @@return �w���{@@link TpCalcResultEquityRow}�ɊO���L�[������{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcResultEquityId( TpCalcResultEquityRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByCalcResultEquityId( row.getCalcResultEquityId() );
    }


  /** 
   * {@@link TpCalcResultEquityPK}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link TpCalcResultEquityPK}�I�u�W�F�N�g 
   * @@return {@@link TpCalcResultEquityPK}�ƊO���L�[����v����l������{@@link TpCalcResultEquityDetailRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcResultEquityId( TpCalcResultEquityPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByCalcResultEquityId( pk.calc_result_equity_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_calcResultEquityId �����Ώۂł���p_calcResultEquityId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcResultEquityId( long p_calcResultEquityId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultEquityDetailRow.TYPE,
            "calc_result_equity_id=?",
            null,
            new Object[] { new Long(p_calcResultEquityId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TpCalcResultEquity
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByCalcResultEquityId(TpCalcResultEquityRow)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultEquityId( TpCalcResultEquityDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultEquityId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcResultEquityId(TpCalcResultEquityRow)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultEquityId( TpCalcResultEquityRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultEquityId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcResultEquityId(TpCalcResultEquityPK)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultEquityId( TpCalcResultEquityPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultEquityId( pk.calc_result_equity_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcResultEquityId(long)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultEquityId( long p_calcResultEquityId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultEquityId( p_calcResultEquityId ) );
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link TpCalcResultEquityDetailRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultEquityDetailRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_createdTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_createdTimestamp, and �̒l�ƈ�v����{@@link TpCalcResultEquityDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultEquityDetailRow.TYPE,
            "account_id=? and created_timestamp=?",
            null,
            new Object[] { new Long(p_accountId), p_createdTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdCreatedTimestamp(long, java.sql.Timestamp)}�����{@@link #forRow(TpCalcResultEquityDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdCreatedTimestamp( p_accountId, p_createdTimestamp ) );
    }

}
@
