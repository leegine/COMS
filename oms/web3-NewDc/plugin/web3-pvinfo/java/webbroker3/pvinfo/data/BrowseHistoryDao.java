head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.12.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	BrowseHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.pvinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BrowseHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BrowseHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BrowseHistoryPK 
 * @@see BrowseHistoryRow 
 */
public class BrowseHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link BrowseHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BrowseHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BrowseHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BrowseHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BrowseHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BrowseHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BrowseHistoryRow )
                return new BrowseHistoryDao( (BrowseHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BrowseHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BrowseHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BrowseHistoryRow}�I�u�W�F�N�g 
    */
    protected BrowseHistoryDao( BrowseHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BrowseHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BrowseHistoryRow getBrowseHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link BrowseHistoryRow}�I�u�W�F�N�g����{@@link BrowseHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BrowseHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BrowseHistoryDao}�擾�̂��߂Ɏw���{@@link BrowseHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BrowseHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BrowseHistoryDao forRow( BrowseHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (BrowseHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BrowseHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BrowseHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BrowseHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BrowseHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BrowseHistoryRow.TYPE );
    }


  /** 
   * {@@link BrowseHistoryRow}����ӂɓ��肷��{@@link BrowseHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BrowseHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BrowseHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BrowseHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BrowseHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BrowseHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BrowseHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_browseHistoryId �����Ώۂł���p_browseHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BrowseHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BrowseHistoryRow findRowByPk( long p_browseHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BrowseHistoryPK pk = new BrowseHistoryPK( p_browseHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���BrowseHistoryPK�I�u�W�F�N�g����{@@link BrowseHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BrowseHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BrowseHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BrowseHistoryRow findRowByPk( BrowseHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BrowseHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static BrowseHistoryDao findDaoByPk( long p_browseHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        BrowseHistoryPK pk = new BrowseHistoryPK( p_browseHistoryId );
        BrowseHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BrowseHistoryPK)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static BrowseHistoryDao findDaoByPk( BrowseHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BrowseHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BrowseHistoryDao}�ɕR�t��{@@link BrowseHistoryRow}���ŊO���L�[�̊֌W������{@@link DisplayContentsRow}���������܂��B 
   * 
   * @@return {@@link BrowseHistoryDao}�ƊO���L�[�̊֌W�ɂ���{@@link DisplayContentsRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public DisplayContentsRow fetchDisplayContentsRowViaDisplayContentsId() throws DataNetworkException, DataFindException, DataQueryException  {
        DisplayContentsPK pk = new DisplayContentsPK( row.getDisplayContentsId() );
        Row row = DisplayContentsDao.findRowByPk( pk );
        if ( row != null && !(row instanceof DisplayContentsRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (DisplayContentsRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchDisplayContentsRowViaDisplayContentsId()}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public DisplayContentsDao fetchDisplayContentsDaoViaDisplayContentsId() throws DataNetworkException, DataFindException, DataQueryException  {
        DisplayContentsPK pk = new DisplayContentsPK( row.getDisplayContentsId() );
        DataAccessObject dao = DisplayContentsDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof DisplayContentsDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (DisplayContentsDao) dao;
    }


  /** 
   * ����{@@link BrowseHistoryDao}�ɕR�t��{@@link BrowseHistoryRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link BrowseHistoryDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow}�܂��͊O���L�[ID�̒l������null�ɐݒ�̏ꍇ��null 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */	
    public MainAccountRow fetchMainAccountRowViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getAccountIdIsNull() )
            return null;
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        Row row = MainAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MainAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MainAccountRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getAccountIdIsNull() )
            return null;
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
    // Find Rows given foreign key values for DisplayContents
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByDisplayContentsId(DisplayContentsRow)}���g�p���Ă��������B 
   */
    public static List findRowsByDisplayContentsId( DisplayContentsDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByDisplayContentsId( dao.getDisplayContentsRow() );
    }


  /** 
   * {@@link DisplayContentsRow}�ƊO���L�[�̊֌W�ɂ���{@@link BrowseHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link DisplayContentsRow}�I�u�W�F�N�g 
   * @@return �w���{@@link DisplayContentsRow}�ɊO���L�[������{@@link BrowseHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByDisplayContentsId( DisplayContentsRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByDisplayContentsId( row.getDisplayContentsId() );
    }


  /** 
   * {@@link DisplayContentsPK}�ƊO���L�[�̊֌W�ɂ���{@@link BrowseHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link DisplayContentsPK}�I�u�W�F�N�g 
   * @@return {@@link DisplayContentsPK}�ƊO���L�[����v����l������{@@link BrowseHistoryRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByDisplayContentsId( DisplayContentsPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByDisplayContentsId( pk.display_contents_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BrowseHistoryRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_displayContentsId �����Ώۂł���p_displayContentsId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BrowseHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByDisplayContentsId( long p_displayContentsId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "display_contents_id=?",
            null,
            new Object[] { new Long(p_displayContentsId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for DisplayContents
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByDisplayContentsId(DisplayContentsRow)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDisplayContentsId( DisplayContentsDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByDisplayContentsId(DisplayContentsRow)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDisplayContentsId( DisplayContentsRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByDisplayContentsId(DisplayContentsPK)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDisplayContentsId( DisplayContentsPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( pk.display_contents_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByDisplayContentsId(long)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDisplayContentsId( long p_displayContentsId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByDisplayContentsId( p_displayContentsId ) );
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link BrowseHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link BrowseHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link BrowseHistoryRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link BrowseHistoryRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BrowseHistoryRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BrowseHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
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
   * p_browseHistoryId, and �ɂĎw��̒l�����ӂ�{@@link BrowseHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_browseHistoryId �����Ώۂł���p_browseHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���p_browseHistoryId, and �̒l�ƈ�v����{@@link BrowseHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BrowseHistoryRow findRowByBrowseHistoryId( long p_browseHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "browse_history_id=?",
            null,
            new Object[] { new Long(p_browseHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BrowseHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BrowseHistoryDao.findRowsByBrowseHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBrowseHistoryId(long)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static BrowseHistoryDao findDaoByBrowseHistoryId( long p_browseHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBrowseHistoryId( p_browseHistoryId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_displayContentsId, p_accountCode, and �ɂĎw��̒l�����ӂ�{@@link BrowseHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_displayContentsId �����Ώۂł���p_displayContentsId�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_displayContentsId, p_accountCode, and �̒l�ƈ�v����{@@link BrowseHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BrowseHistoryRow findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode( String p_institutionCode, String p_branchCode, long p_displayContentsId, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "institution_code=? and branch_code=? and display_contents_id=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, new Long(p_displayContentsId), p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BrowseHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BrowseHistoryDao.findRowsByInstitutionCodeBranchCodeDisplayContentsIdAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode(String, String, long, String)}�����{@@link #forRow(BrowseHistoryRow)}���g�p���Ă��������B 
   */
    public static BrowseHistoryDao findDaoByInstitutionCodeBranchCodeDisplayContentsIdAccountCode( String p_institutionCode, String p_branchCode, long p_displayContentsId, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode( p_institutionCode, p_branchCode, p_displayContentsId, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
