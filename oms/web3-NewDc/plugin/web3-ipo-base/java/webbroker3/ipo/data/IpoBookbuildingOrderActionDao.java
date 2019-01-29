head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingOrderActionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ipo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IpoBookbuildingOrderActionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IpoBookbuildingOrderActionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IpoBookbuildingOrderActionPK 
 * @@see IpoBookbuildingOrderActionRow 
 */
public class IpoBookbuildingOrderActionDao extends DataAccessObject {


  /** 
   * ����{@@link IpoBookbuildingOrderActionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IpoBookbuildingOrderActionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IpoBookbuildingOrderActionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IpoBookbuildingOrderActionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IpoBookbuildingOrderActionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IpoBookbuildingOrderActionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IpoBookbuildingOrderActionRow )
                return new IpoBookbuildingOrderActionDao( (IpoBookbuildingOrderActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IpoBookbuildingOrderActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IpoBookbuildingOrderActionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g 
    */
    protected IpoBookbuildingOrderActionDao( IpoBookbuildingOrderActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IpoBookbuildingOrderActionRow getIpoBookbuildingOrderActionRow() {
        return row;
    }


  /** 
   * �w���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g����{@@link IpoBookbuildingOrderActionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IpoBookbuildingOrderActionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IpoBookbuildingOrderActionDao}�擾�̂��߂Ɏw���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IpoBookbuildingOrderActionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IpoBookbuildingOrderActionDao forRow( IpoBookbuildingOrderActionRow row ) throws java.lang.IllegalArgumentException {
        return (IpoBookbuildingOrderActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IpoBookbuildingOrderActionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IpoBookbuildingOrderActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IpoBookbuildingOrderActionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IpoBookbuildingOrderActionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IpoBookbuildingOrderActionRow.TYPE );
    }


  /** 
   * {@@link IpoBookbuildingOrderActionRow}����ӂɓ��肷��{@@link IpoBookbuildingOrderActionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IpoBookbuildingOrderActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IpoBookbuildingOrderActionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IpoBookbuildingOrderActionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IpoBookbuildingOrderActionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IpoBookbuildingOrderActionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_bookbuildingOrderActionId �����Ώۂł���p_bookbuildingOrderActionId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IpoBookbuildingOrderActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IpoBookbuildingOrderActionRow findRowByPk( long p_bookbuildingOrderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingOrderActionPK pk = new IpoBookbuildingOrderActionPK( p_bookbuildingOrderActionId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IpoBookbuildingOrderActionPK�I�u�W�F�N�g����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IpoBookbuildingOrderActionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IpoBookbuildingOrderActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IpoBookbuildingOrderActionRow findRowByPk( IpoBookbuildingOrderActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IpoBookbuildingOrderActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static IpoBookbuildingOrderActionDao findDaoByPk( long p_bookbuildingOrderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingOrderActionPK pk = new IpoBookbuildingOrderActionPK( p_bookbuildingOrderActionId );
        IpoBookbuildingOrderActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IpoBookbuildingOrderActionPK)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static IpoBookbuildingOrderActionDao findDaoByPk( IpoBookbuildingOrderActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingOrderActionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link IpoBookbuildingOrderActionDao}�ɕR�t��{@@link IpoBookbuildingOrderActionRow}���ŊO���L�[�̊֌W������{@@link IpoOrderRow}���������܂��B 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link IpoOrderRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public IpoOrderRow fetchIpoOrderRowViaIpoOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoOrderPK pk = new IpoOrderPK( row.getIpoOrderId() );
        Row row = IpoOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IpoOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IpoOrderRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchIpoOrderRowViaIpoOrderId()}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public IpoOrderDao fetchIpoOrderDaoViaIpoOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoOrderPK pk = new IpoOrderPK( row.getIpoOrderId() );
        DataAccessObject dao = IpoOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IpoOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IpoOrderDao) dao;
    }


  /** 
   * ����{@@link IpoBookbuildingOrderActionDao}�ɕR�t��{@@link IpoBookbuildingOrderActionRow}���ŊO���L�[�̊֌W������{@@link IpoProductRow}���������܂��B 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link IpoProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public IpoProductRow fetchIpoProductRowViaIpoProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoProductPK pk = new IpoProductPK( row.getIpoProductId() );
        Row row = IpoProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IpoProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IpoProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchIpoProductRowViaIpoProductId()}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public IpoProductDao fetchIpoProductDaoViaIpoProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IpoProductPK pk = new IpoProductPK( row.getIpoProductId() );
        DataAccessObject dao = IpoProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IpoProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IpoProductDao) dao;
    }


  /** 
   * ����{@@link IpoBookbuildingOrderActionDao}�ɕR�t��{@@link IpoBookbuildingOrderActionRow}���ŊO���L�[�̊֌W������{@@link BranchRow}���������܂��B 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link BranchRow} 
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
   * @@deprecated �����{@@link #fetchBranchRowViaBranchId()}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * ����{@@link IpoBookbuildingOrderActionDao}�ɕR�t��{@@link IpoBookbuildingOrderActionRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * ����{@@link IpoBookbuildingOrderActionDao}�ɕR�t��{@@link IpoBookbuildingOrderActionRow}���ŊO���L�[�̊֌W������{@@link SubAccountRow}���������܂��B 
   * 
   * @@return {@@link IpoBookbuildingOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow} 
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
   * @@deprecated �����{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
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
    // Find Rows given foreign key values for IpoOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByIpoOrderId(IpoOrderRow)}���g�p���Ă��������B 
   */
    public static List findRowsByIpoOrderId( IpoOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoOrderId( dao.getIpoOrderRow() );
    }


  /** 
   * {@@link IpoOrderRow}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link IpoOrderRow}�I�u�W�F�N�g 
   * @@return �w���{@@link IpoOrderRow}�ɊO���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIpoOrderId( IpoOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoOrderId( row.getIpoOrderId() );
    }


  /** 
   * {@@link IpoOrderPK}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link IpoOrderPK}�I�u�W�F�N�g 
   * @@return {@@link IpoOrderPK}�ƊO���L�[����v����l������{@@link IpoBookbuildingOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIpoOrderId( IpoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoOrderId( pk.ipo_order_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_ipoOrderId �����Ώۂł���p_ipoOrderId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIpoOrderId( long p_ipoOrderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "ipo_order_id=?",
            null,
            new Object[] { new Long(p_ipoOrderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IpoOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByIpoOrderId(IpoOrderRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoOrderId( IpoOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIpoOrderId(IpoOrderRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoOrderId( IpoOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIpoOrderId(IpoOrderPK)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoOrderId( IpoOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( pk.ipo_order_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIpoOrderId(long)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoOrderId( long p_ipoOrderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoOrderId( p_ipoOrderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for IpoProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByIpoProductId(IpoProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByIpoProductId( IpoProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoProductId( dao.getIpoProductRow() );
    }


  /** 
   * {@@link IpoProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link IpoProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link IpoProductRow}�ɊO���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIpoProductId( IpoProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoProductId( row.getIpoProductId() );
    }


  /** 
   * {@@link IpoProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link IpoProductPK}�I�u�W�F�N�g 
   * @@return {@@link IpoProductPK}�ƊO���L�[����v����l������{@@link IpoBookbuildingOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIpoProductId( IpoProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByIpoProductId( pk.ipo_product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_ipoProductId �����Ώۂł���p_ipoProductId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIpoProductId( long p_ipoProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "ipo_product_id=?",
            null,
            new Object[] { new Long(p_ipoProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IpoProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByIpoProductId(IpoProductRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoProductId( IpoProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIpoProductId(IpoProductRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoProductId( IpoProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIpoProductId(IpoProductPK)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoProductId( IpoProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( pk.ipo_product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIpoProductId(long)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIpoProductId( long p_ipoProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIpoProductId( p_ipoProductId ) );
    }


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
   * {@@link BranchRow}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BranchRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BranchRow}�ɊO���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BranchPK}�I�u�W�F�N�g 
   * @@return {@@link BranchPK}�ƊO���L�[����v����l������{@@link IpoBookbuildingOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchPK)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(long)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link IpoBookbuildingOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
    }


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
   * {@@link SubAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link SubAccountRow}�ɊO���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link SubAccountPK}�I�u�W�F�N�g 
   * @@return {@@link SubAccountPK}�ƊO���L�[����v����l������{@@link IpoBookbuildingOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(long, long)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
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
   * p_bookbuildingOrderActionId, and �ɂĎw��̒l�����ӂ�{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_bookbuildingOrderActionId �����Ώۂł���p_bookbuildingOrderActionId�t�B�[���h�̒l
   * 
   * @@return �����w���p_bookbuildingOrderActionId, and �̒l�ƈ�v����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IpoBookbuildingOrderActionRow findRowByBookbuildingOrderActionId( long p_bookbuildingOrderActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "bookbuilding_order_action_id=?",
            null,
            new Object[] { new Long(p_bookbuildingOrderActionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoBookbuildingOrderActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoBookbuildingOrderActionDao.findRowsByBookbuildingOrderActionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBookbuildingOrderActionId(long)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static IpoBookbuildingOrderActionDao findDaoByBookbuildingOrderActionId( long p_bookbuildingOrderActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBookbuildingOrderActionId( p_bookbuildingOrderActionId ) );
    }


  /** 
   * p_ipoOrderId, p_orderActionSerialNo, and �ɂĎw��̒l�����ӂ�{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_ipoOrderId �����Ώۂł���p_ipoOrderId�t�B�[���h�̒l
   * @@param p_orderActionSerialNo �����Ώۂł���p_orderActionSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_ipoOrderId, p_orderActionSerialNo, and �̒l�ƈ�v����{@@link IpoBookbuildingOrderActionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IpoBookbuildingOrderActionRow findRowByIpoOrderIdOrderActionSerialNo( long p_ipoOrderId, int p_orderActionSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoBookbuildingOrderActionRow.TYPE,
            "ipo_order_id=? and order_action_serial_no=?",
            null,
            new Object[] { new Long(p_ipoOrderId), new Integer(p_orderActionSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoBookbuildingOrderActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoBookbuildingOrderActionDao.findRowsByIpoOrderIdOrderActionSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByIpoOrderIdOrderActionSerialNo(long, int)}�����{@@link #forRow(IpoBookbuildingOrderActionRow)}���g�p���Ă��������B 
   */
    public static IpoBookbuildingOrderActionDao findDaoByIpoOrderIdOrderActionSerialNo( long p_ipoOrderId, int p_orderActionSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByIpoOrderIdOrderActionSerialNo( p_ipoOrderId, p_orderActionSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
