head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.28.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginDetailDao.java;


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
 * {@@link TpCalcResultMarginDetailDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TpCalcResultMarginDetailRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TpCalcResultMarginDetailPK 
 * @@see TpCalcResultMarginDetailRow 
 */
public class TpCalcResultMarginDetailDao extends DataAccessObject {


  /** 
   * ����{@@link TpCalcResultMarginDetailDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TpCalcResultMarginDetailRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TpCalcResultMarginDetailRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TpCalcResultMarginDetailDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TpCalcResultMarginDetailDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TpCalcResultMarginDetailRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpCalcResultMarginDetailRow )
                return new TpCalcResultMarginDetailDao( (TpCalcResultMarginDetailRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpCalcResultMarginDetailRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpCalcResultMarginDetailRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g 
    */
    protected TpCalcResultMarginDetailDao( TpCalcResultMarginDetailRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TpCalcResultMarginDetailRow getTpCalcResultMarginDetailRow() {
        return row;
    }


  /** 
   * �w���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g����{@@link TpCalcResultMarginDetailDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TpCalcResultMarginDetailRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TpCalcResultMarginDetailDao}�擾�̂��߂Ɏw���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TpCalcResultMarginDetailDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TpCalcResultMarginDetailDao forRow( TpCalcResultMarginDetailRow row ) throws java.lang.IllegalArgumentException {
        return (TpCalcResultMarginDetailDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpCalcResultMarginDetailRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TpCalcResultMarginDetailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TpCalcResultMarginDetailPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TpCalcResultMarginDetailParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpCalcResultMarginDetailRow.TYPE );
    }


  /** 
   * {@@link TpCalcResultMarginDetailRow}����ӂɓ��肷��{@@link TpCalcResultMarginDetailPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TpCalcResultMarginDetailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TpCalcResultMarginDetailParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TpCalcResultMarginDetailPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TpCalcResultMarginDetailPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpCalcResultMarginDetailPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_calcResultMarginId �����Ώۂł���p_calcResultMarginId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCalcResultMarginDetailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCalcResultMarginDetailRow findRowByPk( long p_calcResultMarginId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultMarginDetailPK pk = new TpCalcResultMarginDetailPK( p_calcResultMarginId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TpCalcResultMarginDetailPK�I�u�W�F�N�g����{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TpCalcResultMarginDetailPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpCalcResultMarginDetailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpCalcResultMarginDetailRow findRowByPk( TpCalcResultMarginDetailPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpCalcResultMarginDetailRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static TpCalcResultMarginDetailDao findDaoByPk( long p_calcResultMarginId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultMarginDetailPK pk = new TpCalcResultMarginDetailPK( p_calcResultMarginId );
        TpCalcResultMarginDetailRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TpCalcResultMarginDetailPK)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static TpCalcResultMarginDetailDao findDaoByPk( TpCalcResultMarginDetailPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpCalcResultMarginDetailRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link TpCalcResultMarginDetailDao}�ɕR�t��{@@link TpCalcResultMarginDetailRow}���ŊO���L�[�̊֌W������{@@link TpCalcResultMarginRow}���������܂��B 
   * 
   * @@return {@@link TpCalcResultMarginDetailDao}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultMarginRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public TpCalcResultMarginRow fetchTpCalcResultMarginRowViaCalcResultMarginId() throws DataNetworkException, DataFindException, DataQueryException  {
        TpCalcResultMarginPK pk = new TpCalcResultMarginPK( row.getCalcResultMarginId() );
        Row row = TpCalcResultMarginDao.findRowByPk( pk );
        if ( row != null && !(row instanceof TpCalcResultMarginRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (TpCalcResultMarginRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchTpCalcResultMarginRowViaCalcResultMarginId()}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public TpCalcResultMarginDao fetchTpCalcResultMarginDaoViaCalcResultMarginId() throws DataNetworkException, DataFindException, DataQueryException  {
        TpCalcResultMarginPK pk = new TpCalcResultMarginPK( row.getCalcResultMarginId() );
        DataAccessObject dao = TpCalcResultMarginDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TpCalcResultMarginDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TpCalcResultMarginDao) dao;
    }


  /** 
   * ����{@@link TpCalcResultMarginDetailDao}�ɕR�t��{@@link TpCalcResultMarginDetailRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link TpCalcResultMarginDetailDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
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
    // Find Rows given foreign key values for TpCalcResultMargin
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByCalcResultMarginId(TpCalcResultMarginRow)}���g�p���Ă��������B 
   */
    public static List findRowsByCalcResultMarginId( TpCalcResultMarginDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByCalcResultMarginId( dao.getTpCalcResultMarginRow() );
    }


  /** 
   * {@@link TpCalcResultMarginRow}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link TpCalcResultMarginRow}�I�u�W�F�N�g 
   * @@return �w���{@@link TpCalcResultMarginRow}�ɊO���L�[������{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcResultMarginId( TpCalcResultMarginRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByCalcResultMarginId( row.getCalcResultMarginId() );
    }


  /** 
   * {@@link TpCalcResultMarginPK}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link TpCalcResultMarginPK}�I�u�W�F�N�g 
   * @@return {@@link TpCalcResultMarginPK}�ƊO���L�[����v����l������{@@link TpCalcResultMarginDetailRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcResultMarginId( TpCalcResultMarginPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByCalcResultMarginId( pk.calc_result_margin_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_calcResultMarginId �����Ώۂł���p_calcResultMarginId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCalcResultMarginId( long p_calcResultMarginId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultMarginDetailRow.TYPE,
            "calc_result_margin_id=?",
            null,
            new Object[] { new Long(p_calcResultMarginId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TpCalcResultMargin
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByCalcResultMarginId(TpCalcResultMarginRow)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultMarginId( TpCalcResultMarginDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultMarginId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcResultMarginId(TpCalcResultMarginRow)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultMarginId( TpCalcResultMarginRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultMarginId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcResultMarginId(TpCalcResultMarginPK)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultMarginId( TpCalcResultMarginPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultMarginId( pk.calc_result_margin_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCalcResultMarginId(long)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCalcResultMarginId( long p_calcResultMarginId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCalcResultMarginId( p_calcResultMarginId ) );
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link TpCalcResultMarginDetailRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultMarginDetailRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
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
   * p_accountId, p_createdTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_createdTimestamp, and �̒l�ƈ�v����{@@link TpCalcResultMarginDetailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpCalcResultMarginDetailRow.TYPE,
            "account_id=? and created_timestamp=?",
            null,
            new Object[] { new Long(p_accountId), p_createdTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdCreatedTimestamp(long, java.sql.Timestamp)}�����{@@link #forRow(TpCalcResultMarginDetailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdCreatedTimestamp( long p_accountId, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdCreatedTimestamp( p_accountId, p_createdTimestamp ) );
    }

}
@
