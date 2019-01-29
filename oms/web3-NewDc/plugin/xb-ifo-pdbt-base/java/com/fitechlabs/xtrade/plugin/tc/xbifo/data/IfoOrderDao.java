head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.08.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoOrderDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IfoOrderDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoOrderRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoOrderPK 
 * @@see IfoOrderRow 
 */
public class IfoOrderDao extends DataAccessObject {


  /** 
   * ����{@@link IfoOrderDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoOrderRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoOrderRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoOrderDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoOrderDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoOrderRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoOrderRow )
                return new IfoOrderDao( (IfoOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoOrderRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoOrderRow}�I�u�W�F�N�g 
    */
    protected IfoOrderDao( IfoOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoOrderRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoOrderRow getIfoOrderRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoOrderRow}�I�u�W�F�N�g����{@@link IfoOrderDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoOrderRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoOrderDao}�擾�̂��߂Ɏw���{@@link IfoOrderRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoOrderDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoOrderDao forRow( IfoOrderRow row ) throws java.lang.IllegalArgumentException {
        return (IfoOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoOrderRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoOrderPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoOrderParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoOrderRow.TYPE );
    }


  /** 
   * {@@link IfoOrderRow}����ӂɓ��肷��{@@link IfoOrderPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoOrderParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoOrderPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoOrderPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoOrderPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoOrderRow findRowByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderPK pk = new IfoOrderPK( p_orderId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoOrderPK�I�u�W�F�N�g����{@@link IfoOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoOrderPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoOrderRow findRowByPk( IfoOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static IfoOrderDao findDaoByPk( long p_orderId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderPK pk = new IfoOrderPK( p_orderId );
        IfoOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoOrderPK)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static IfoOrderDao findDaoByPk( IfoOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link IfoOrderDao}�ɕR�t��{@@link IfoOrderRow}���ŊO���L�[�̊֌W������{@@link SubAccountRow}���������܂��B 
   * 
   * @@return {@@link IfoOrderDao}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow} 
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
   * @@deprecated �����{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * ����{@@link IfoOrderDao}�ɕR�t��{@@link IfoOrderRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link IfoOrderDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoFinTransactionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoFinTransactionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoFinTransactionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoFinTransactionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoFinTransactionRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoFinTransactionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoFinTransactionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoFinTransactionRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoFinTransactionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchIfoFinTransactionDaosByOrderId();
    }


  /** 
   * ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoClosingContractSpecRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoClosingContractSpecRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoClosingContractSpecRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoClosingContractSpecDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoClosingContractSpecRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoClosingContractSpecDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoClosingContractSpecDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoClosingContractSpecRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoClosingContractSpecDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchIfoClosingContractSpecDaosByOrderId();
    }


  /** 
   * ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoOrderActionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoOrderActionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoOrderActionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoOrderActionRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoOrderActionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoOrderActionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoOrderActionRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoOrderActionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchIfoOrderActionDaosByOrderId();
    }


  /** 
   * ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoOrderExecutionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoOrderExecutionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoOrderExecutionRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoOrderExecutionDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoOrderExecutionRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoOrderExecutionDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoOrderExecutionDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoOrderExecutionRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoOrderExecutionDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchIfoOrderExecutionDaosByOrderId();
    }


  /** 
   * ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoOrderDao}�Ɋ֘A����{@@link IfoOrderRow}�̊O���L�[������{@@link IfoOrderUnitRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoOrderUnitRowsByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoOrderUnitDao.findRowsByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoOrderUnitRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoOrderUnitDaosByOrderId() throws DataNetworkException, DataQueryException  {
        return IfoOrderUnitDao.findDaosByOrderId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoOrderUnitRowsByOrderId()}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public List fetchIfoOrderUnitDaosOrderId() throws DataNetworkException, DataQueryException  {
        return fetchIfoOrderUnitDaosByOrderId();
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
   * {@@link SubAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link IfoOrderRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link SubAccountRow}�ɊO���L�[������{@@link IfoOrderRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link IfoOrderRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link SubAccountPK}�I�u�W�F�N�g 
   * @@return {@@link SubAccountPK}�ƊO���L�[����v����l������{@@link IfoOrderRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IfoOrderRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IfoOrderRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoOrderRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(long, long)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link IfoOrderRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link IfoOrderRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link IfoOrderRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link IfoOrderRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IfoOrderRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IfoOrderRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoOrderRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
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
   * p_orderId, and �ɂĎw��̒l�����ӂ�{@@link IfoOrderRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderId, and �̒l�ƈ�v����{@@link IfoOrderRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoOrderRow findRowByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoOrderRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoOrderDao.findRowsByOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByOrderId(long)}�����{@@link #forRow(IfoOrderRow)}���g�p���Ă��������B 
   */
    public static IfoOrderDao findDaoByOrderId( long p_orderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderId( p_orderId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@
