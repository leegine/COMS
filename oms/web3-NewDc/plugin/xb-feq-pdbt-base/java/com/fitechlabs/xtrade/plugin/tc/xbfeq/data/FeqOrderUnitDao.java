head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.03.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqOrderUnitDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FeqOrderUnitDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FeqOrderUnitRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FeqOrderUnitPK 
 * @@see FeqOrderUnitRow 
 */
public class FeqOrderUnitDao extends DataAccessObject {


  /** 
   * ����{@@link FeqOrderUnitDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FeqOrderUnitRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FeqOrderUnitRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FeqOrderUnitDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FeqOrderUnitDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FeqOrderUnitRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqOrderUnitRow )
                return new FeqOrderUnitDao( (FeqOrderUnitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqOrderUnitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqOrderUnitRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FeqOrderUnitRow}�I�u�W�F�N�g 
    */
    protected FeqOrderUnitDao( FeqOrderUnitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FeqOrderUnitRow getFeqOrderUnitRow() {
        return row;
    }


  /** 
   * �w���{@@link FeqOrderUnitRow}�I�u�W�F�N�g����{@@link FeqOrderUnitDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FeqOrderUnitRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FeqOrderUnitDao}�擾�̂��߂Ɏw���{@@link FeqOrderUnitRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FeqOrderUnitDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FeqOrderUnitDao forRow( FeqOrderUnitRow row ) throws java.lang.IllegalArgumentException {
        return (FeqOrderUnitDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqOrderUnitRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FeqOrderUnitRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FeqOrderUnitPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FeqOrderUnitParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqOrderUnitRow.TYPE );
    }


  /** 
   * {@@link FeqOrderUnitRow}����ӂɓ��肷��{@@link FeqOrderUnitPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FeqOrderUnitRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FeqOrderUnitParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FeqOrderUnitPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FeqOrderUnitPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FeqOrderUnitPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FeqOrderUnitRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqOrderUnitRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqOrderUnitRow findRowByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderUnitPK pk = new FeqOrderUnitPK( p_orderUnitId );
        return findRowByPk( pk );
    }


  /** 
   * �w���FeqOrderUnitPK�I�u�W�F�N�g����{@@link FeqOrderUnitRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FeqOrderUnitPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqOrderUnitRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqOrderUnitRow findRowByPk( FeqOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqOrderUnitRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static FeqOrderUnitDao findDaoByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderUnitPK pk = new FeqOrderUnitPK( p_orderUnitId );
        FeqOrderUnitRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FeqOrderUnitPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static FeqOrderUnitDao findDaoByPk( FeqOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderUnitRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link FeqOrderUnitDao}�ɕR�t��{@@link FeqOrderUnitRow}���ŊO���L�[�̊֌W������{@@link FeqProductRow}���������܂��B 
   * 
   * @@return {@@link FeqOrderUnitDao}�ƊO���L�[�̊֌W�ɂ���{@@link FeqProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public FeqProductRow fetchFeqProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        FeqProductPK pk = new FeqProductPK( row.getProductId() );
        Row row = FeqProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof FeqProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (FeqProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqProductRowViaProductId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public FeqProductDao fetchFeqProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        FeqProductPK pk = new FeqProductPK( row.getProductId() );
        DataAccessObject dao = FeqProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof FeqProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (FeqProductDao) dao;
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�ɕR�t��{@@link FeqOrderUnitRow}���ŊO���L�[�̊֌W������{@@link FeqOrderRow}���������܂��B 
   * 
   * @@return {@@link FeqOrderUnitDao}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public FeqOrderRow fetchFeqOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        FeqOrderPK pk = new FeqOrderPK( row.getOrderId() );
        Row row = FeqOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof FeqOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (FeqOrderRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqOrderRowViaOrderId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public FeqOrderDao fetchFeqOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        FeqOrderPK pk = new FeqOrderPK( row.getOrderId() );
        DataAccessObject dao = FeqOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof FeqOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (FeqOrderDao) dao;
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�ɕR�t��{@@link FeqOrderUnitRow}���ŊO���L�[�̊֌W������{@@link SubAccountRow}���������܂��B 
   * 
   * @@return {@@link FeqOrderUnitDao}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow} 
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
   * @@deprecated �����{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�ɕR�t��{@@link FeqOrderUnitRow}���ŊO���L�[�̊֌W������{@@link MarketRow}���������܂��B 
   * 
   * @@return {@@link FeqOrderUnitDao}�ƊO���L�[�̊֌W�ɂ���{@@link MarketRow}�܂��͊O���L�[ID�̒l������null�ɐݒ�̏ꍇ��null 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */	
    public MarketRow fetchMarketRowViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getMarketIdIsNull() )
            return null;
        MarketPK pk = new MarketPK( row.getMarketId() );
        Row row = MarketDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MarketRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MarketRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchMarketRowViaMarketId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getMarketIdIsNull() )
            return null;
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�ɕR�t��{@@link FeqOrderUnitRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link FeqOrderUnitDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�ɕR�t��{@@link FeqOrderUnitRow}���ŊO���L�[�̊֌W������{@@link TraderRow}���������܂��B 
   * 
   * @@return {@@link FeqOrderUnitDao}�ƊO���L�[�̊֌W�ɂ���{@@link TraderRow}�܂��͊O���L�[ID�̒l������null�ɐݒ�̏ꍇ��null 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */	
    public TraderRow fetchTraderRowViaTraderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getTraderIdIsNull() )
            return null;
        TraderPK pk = new TraderPK( row.getTraderId() );
        Row row = TraderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof TraderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (TraderRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchTraderRowViaTraderId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public TraderDao fetchTraderDaoViaTraderId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getTraderIdIsNull() )
            return null;
        TraderPK pk = new TraderPK( row.getTraderId() );
        DataAccessObject dao = TraderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TraderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TraderDao) dao;
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�ɕR�t��{@@link FeqOrderUnitRow}���ŊO���L�[�̊֌W������{@@link BranchRow}���������܂��B 
   * 
   * @@return {@@link FeqOrderUnitDao}�ƊO���L�[�̊֌W�ɂ���{@@link BranchRow} 
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
   * @@deprecated �����{@@link #fetchBranchRowViaBranchId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link FeqOrderUnitDao}�Ɋ֘A����{@@link FeqOrderUnitRow}�̊O���L�[������{@@link FeqOrderExecutionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link FeqOrderUnitDao}�Ɋ֘A����{@@link FeqOrderUnitRow}�̊O���L�[������{@@link FeqOrderExecutionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchFeqOrderExecutionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return FeqOrderExecutionDao.findRowsByOrderUnitId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqOrderExecutionRowsByOrderUnitId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public List fetchFeqOrderExecutionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return FeqOrderExecutionDao.findDaosByOrderUnitId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqOrderExecutionRowsByOrderUnitId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public List fetchFeqOrderExecutionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchFeqOrderExecutionDaosByOrderUnitId();
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�Ɋ֘A����{@@link FeqOrderUnitRow}�̊O���L�[������{@@link FeqFinTransactionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link FeqOrderUnitDao}�Ɋ֘A����{@@link FeqOrderUnitRow}�̊O���L�[������{@@link FeqFinTransactionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchFeqFinTransactionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return FeqFinTransactionDao.findRowsByOrderUnitId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqFinTransactionRowsByOrderUnitId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public List fetchFeqFinTransactionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return FeqFinTransactionDao.findDaosByOrderUnitId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqFinTransactionRowsByOrderUnitId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public List fetchFeqFinTransactionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchFeqFinTransactionDaosByOrderUnitId();
    }


  /** 
   * ����{@@link FeqOrderUnitDao}�Ɋ֘A����{@@link FeqOrderUnitRow}�̊O���L�[������{@@link FeqOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link FeqOrderUnitDao}�Ɋ֘A����{@@link FeqOrderUnitRow}�̊O���L�[������{@@link FeqOrderActionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchFeqOrderActionRowsByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return FeqOrderActionDao.findRowsByOrderUnitId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqOrderActionRowsByOrderUnitId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public List fetchFeqOrderActionDaosByOrderUnitId() throws DataNetworkException, DataQueryException  {
        return FeqOrderActionDao.findDaosByOrderUnitId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqOrderActionRowsByOrderUnitId()}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public List fetchFeqOrderActionDaosOrderUnitId() throws DataNetworkException, DataQueryException  {
        return fetchFeqOrderActionDaosByOrderUnitId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for FeqProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByProductId(FeqProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByProductId( FeqProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getFeqProductRow() );
    }


  /** 
   * {@@link FeqProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link FeqProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link FeqProductRow}�ɊO���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( FeqProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link FeqProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link FeqProductPK}�I�u�W�F�N�g 
   * @@return {@@link FeqProductPK}�ƊO���L�[����v����l������{@@link FeqOrderUnitRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( FeqProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for FeqProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(FeqProductRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( FeqProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(FeqProductRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( FeqProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(FeqProductPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( FeqProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for FeqOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByOrderId(FeqOrderRow)}���g�p���Ă��������B 
   */
    public static List findRowsByOrderId( FeqOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getFeqOrderRow() );
    }


  /** 
   * {@@link FeqOrderRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link FeqOrderRow}�I�u�W�F�N�g 
   * @@return �w���{@@link FeqOrderRow}�ɊO���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderId( FeqOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link FeqOrderPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link FeqOrderPK}�I�u�W�F�N�g 
   * @@return {@@link FeqOrderPK}�ƊO���L�[����v����l������{@@link FeqOrderUnitRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderId( FeqOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for FeqOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByOrderId(FeqOrderRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( FeqOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderId(FeqOrderRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( FeqOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderId(FeqOrderPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( FeqOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderId(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
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
   * {@@link SubAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link SubAccountRow}�ɊO���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link SubAccountPK}�I�u�W�F�N�g 
   * @@return {@@link SubAccountPK}�ƊO���L�[����v����l������{@@link FeqOrderUnitRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(long, long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Market
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByMarketId(MarketRow)}���g�p���Ă��������B 
   */
    public static List findRowsByMarketId( MarketDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( dao.getMarketRow() );
    }


  /** 
   * {@@link MarketRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MarketRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MarketRow}�ɊO���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MarketPK}�I�u�W�F�N�g 
   * @@return {@@link MarketPK}�ƊO���L�[����v����l������{@@link FeqOrderUnitRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link FeqOrderUnitRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Trader
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByTraderId(TraderRow)}���g�p���Ă��������B 
   */
    public static List findRowsByTraderId( TraderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( dao.getTraderRow() );
    }


  /** 
   * {@@link TraderRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link TraderRow}�I�u�W�F�N�g 
   * @@return �w���{@@link TraderRow}�ɊO���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( row.getTraderId() );
    }


  /** 
   * {@@link TraderPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link TraderPK}�I�u�W�F�N�g 
   * @@return {@@link TraderPK}�ƊO���L�[����v����l������{@@link FeqOrderUnitRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( pk.trader_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_traderId �����Ώۂł���p_traderId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTraderId( long p_traderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "trader_id=?",
            null,
            new Object[] { new Long(p_traderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Trader
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByTraderId(TraderRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( TraderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTraderId(TraderRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTraderId(TraderPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( pk.trader_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTraderId(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( long p_traderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( p_traderId ) );
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
   * {@@link BranchRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BranchRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BranchRow}�ɊO���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqOrderUnitRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BranchPK}�I�u�W�F�N�g 
   * @@return {@@link BranchPK}�ƊO���L�[����v����l������{@@link FeqOrderUnitRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchPK)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
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
   * p_orderUnitId, and �ɂĎw��̒l�����ӂ�{@@link FeqOrderUnitRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderUnitId, and �̒l�ƈ�v����{@@link FeqOrderUnitRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqOrderUnitRow findRowByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderUnitRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderUnitDao.findRowsByOrderUnitId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByOrderUnitId(long)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static FeqOrderUnitDao findDaoByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitId( p_orderUnitId ) );
    }


  /** 
   * p_bizDate, p_orderEmpCode, and �ɂĎw��̒l�����ӂ�{@@link FeqOrderUnitRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * @@param p_orderEmpCode �����Ώۂł���p_orderEmpCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_bizDate, p_orderEmpCode, and �̒l�ƈ�v����{@@link FeqOrderUnitRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqOrderUnitRow findRowByBizDateOrderEmpCode( String p_bizDate, String p_orderEmpCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "biz_date=? and order_emp_code=?",
            null,
            new Object[] { p_bizDate, p_orderEmpCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderUnitRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderUnitDao.findRowsByBizDateOrderEmpCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBizDateOrderEmpCode(String, String)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static FeqOrderUnitDao findDaoByBizDateOrderEmpCode( String p_bizDate, String p_orderEmpCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBizDateOrderEmpCode( p_bizDate, p_orderEmpCode ) );
    }


  /** 
   * p_orderRequestNumber, and �ɂĎw��̒l�����ӂ�{@@link FeqOrderUnitRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderRequestNumber, and �̒l�ƈ�v����{@@link FeqOrderUnitRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqOrderUnitRow findRowByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderUnitRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderUnitDao.findRowsByOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByOrderRequestNumber(String)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static FeqOrderUnitDao findDaoByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderRequestNumber( p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_orderOpenStatus, and �ɂĎw��̒l�Ɉ�v����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_orderOpenStatus �����Ώۂł���p_orderOpenStatus�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, p_orderOpenStatus, and �̒l�ƈ�v����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountIdOrderOpenStatus( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "account_id=? and sub_account_id=? and order_open_status=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), p_orderOpenStatus } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountIdOrderOpenStatus(long, long, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountIdOrderOpenStatus( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdOrderOpenStatus( p_accountId, p_subAccountId, p_orderOpenStatus ) );
    }


  /** 
   * p_institutionCode, p_branchId, p_marketId, p_bizDate, p_orderOpenStatus, and �ɂĎw��̒l�Ɉ�v����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * @@param p_orderOpenStatus �����Ώۂł���p_orderOpenStatus�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchId, p_marketId, p_bizDate, p_orderOpenStatus, and �̒l�ƈ�v����{@@link FeqOrderUnitRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchIdMarketIdBizDateOrderOpenStatus( String p_institutionCode, long p_branchId, Long p_marketId, String p_bizDate, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqOrderUnitRow.TYPE,
            "institution_code=? and branch_id=? and market_id=? and biz_date=? and order_open_status=?",
            null,
            new Object[] { p_institutionCode, new Long(p_branchId), p_marketId, p_bizDate, p_orderOpenStatus } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchIdMarketIdBizDateOrderOpenStatus(String, long, Long, String, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum)}�����{@@link #forRow(FeqOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchIdMarketIdBizDateOrderOpenStatus( String p_institutionCode, long p_branchId, Long p_marketId, String p_bizDate, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum p_orderOpenStatus ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchIdMarketIdBizDateOrderOpenStatus( p_institutionCode, p_branchId, p_marketId, p_bizDate, p_orderOpenStatus ) );
    }

}
@
