head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.07.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoTradedProductUpdqDao.java;


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
 * {@@link IfoTradedProductUpdqDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoTradedProductUpdqRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoTradedProductUpdqPK 
 * @@see IfoTradedProductUpdqRow 
 */
public class IfoTradedProductUpdqDao extends DataAccessObject {


  /** 
   * ����{@@link IfoTradedProductUpdqDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoTradedProductUpdqRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoTradedProductUpdqRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoTradedProductUpdqDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoTradedProductUpdqDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoTradedProductUpdqRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoTradedProductUpdqRow )
                return new IfoTradedProductUpdqDao( (IfoTradedProductUpdqRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoTradedProductUpdqRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoTradedProductUpdqRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g 
    */
    protected IfoTradedProductUpdqDao( IfoTradedProductUpdqRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoTradedProductUpdqRow getIfoTradedProductUpdqRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g����{@@link IfoTradedProductUpdqDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoTradedProductUpdqRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoTradedProductUpdqDao}�擾�̂��߂Ɏw���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoTradedProductUpdqDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoTradedProductUpdqDao forRow( IfoTradedProductUpdqRow row ) throws java.lang.IllegalArgumentException {
        return (IfoTradedProductUpdqDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoTradedProductUpdqRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoTradedProductUpdqRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoTradedProductUpdqPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoTradedProductUpdqParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoTradedProductUpdqRow.TYPE );
    }


  /** 
   * {@@link IfoTradedProductUpdqRow}����ӂɓ��肷��{@@link IfoTradedProductUpdqPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoTradedProductUpdqRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoTradedProductUpdqParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoTradedProductUpdqPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoTradedProductUpdqPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_tradedProductId �����Ώۂł���p_tradedProductId�t�B�[���h�̒l
   * @@param p_validForBizDate �����Ώۂł���p_validForBizDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoTradedProductUpdqRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoTradedProductUpdqRow findRowByPk( long p_tradedProductId, String p_validForBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoTradedProductUpdqPK pk = new IfoTradedProductUpdqPK( p_tradedProductId, p_validForBizDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoTradedProductUpdqPK�I�u�W�F�N�g����{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoTradedProductUpdqPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoTradedProductUpdqRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoTradedProductUpdqRow findRowByPk( IfoTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoTradedProductUpdqRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static IfoTradedProductUpdqDao findDaoByPk( long p_tradedProductId, String p_validForBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoTradedProductUpdqPK pk = new IfoTradedProductUpdqPK( p_tradedProductId, p_validForBizDate );
        IfoTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoTradedProductUpdqPK)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static IfoTradedProductUpdqDao findDaoByPk( IfoTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link IfoTradedProductUpdqDao}�ɕR�t��{@@link IfoTradedProductUpdqRow}���ŊO���L�[�̊֌W������{@@link MarketRow}���������܂��B 
   * 
   * @@return {@@link IfoTradedProductUpdqDao}�ƊO���L�[�̊֌W�ɂ���{@@link MarketRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public MarketRow fetchMarketRowViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        Row row = MarketDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MarketRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MarketRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchMarketRowViaMarketId()}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


  /** 
   * ����{@@link IfoTradedProductUpdqDao}�ɕR�t��{@@link IfoTradedProductUpdqRow}���ŊO���L�[�̊֌W������{@@link IfoProductRow}���������܂��B 
   * 
   * @@return {@@link IfoTradedProductUpdqDao}�ƊO���L�[�̊֌W�ɂ���{@@link IfoProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public IfoProductRow fetchIfoProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoProductPK pk = new IfoProductPK( row.getProductId() );
        Row row = IfoProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IfoProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IfoProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoProductRowViaProductId()}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public IfoProductDao fetchIfoProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoProductPK pk = new IfoProductPK( row.getProductId() );
        DataAccessObject dao = IfoProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoProductDao) dao;
    }


  /** 
   * ����{@@link IfoTradedProductUpdqDao}�ɕR�t��{@@link IfoTradedProductUpdqRow}���ŊO���L�[�̊֌W������{@@link TradedProductRow}���������܂��B 
   * 
   * @@return {@@link IfoTradedProductUpdqDao}�ƊO���L�[�̊֌W�ɂ���{@@link TradedProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public TradedProductRow fetchTradedProductRowViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        Row row = TradedProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof TradedProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (TradedProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductRowViaTradedProductId()}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public TradedProductDao fetchTradedProductDaoViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        DataAccessObject dao = TradedProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TradedProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TradedProductDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

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
   * {@@link MarketRow}�ƊO���L�[�̊֌W�ɂ���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MarketRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MarketRow}�ɊO���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}�ƊO���L�[�̊֌W�ɂ���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MarketPK}�I�u�W�F�N�g 
   * @@return {@@link MarketPK}�ƊO���L�[����v����l������{@@link IfoTradedProductUpdqRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoTradedProductUpdqRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketPK)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(long)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for IfoProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByProductId(IfoProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByProductId( IfoProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getIfoProductRow() );
    }


  /** 
   * {@@link IfoProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link IfoProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link IfoProductRow}�ɊO���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( IfoProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link IfoProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link IfoProductPK}�I�u�W�F�N�g 
   * @@return {@@link IfoProductPK}�ƊO���L�[����v����l������{@@link IfoTradedProductUpdqRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( IfoProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoTradedProductUpdqRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(IfoProductRow)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( IfoProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(IfoProductRow)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( IfoProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(IfoProductPK)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( IfoProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for TradedProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByTradedProductId(TradedProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByTradedProductId( TradedProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( dao.getTradedProductRow() );
    }


  /** 
   * {@@link TradedProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link TradedProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link TradedProductRow}�ɊO���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( row.getTradedProductId() );
    }


  /** 
   * {@@link TradedProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link TradedProductPK}�I�u�W�F�N�g 
   * @@return {@@link TradedProductPK}�ƊO���L�[����v����l������{@@link IfoTradedProductUpdqRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( pk.traded_product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_tradedProductId �����Ώۂł���p_tradedProductId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTradedProductId( long p_tradedProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoTradedProductUpdqRow.TYPE,
            "traded_product_id=?",
            null,
            new Object[] { new Long(p_tradedProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TradedProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByTradedProductId(TradedProductRow)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( TradedProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTradedProductId(TradedProductRow)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTradedProductId(TradedProductPK)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( pk.traded_product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTradedProductId(long)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( p_tradedProductId ) );
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
   * p_tradedProductId, p_validForBizDate, and �ɂĎw��̒l�����ӂ�{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tradedProductId �����Ώۂł���p_tradedProductId�t�B�[���h�̒l
   * @@param p_validForBizDate �����Ώۂł���p_validForBizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_tradedProductId, p_validForBizDate, and �̒l�ƈ�v����{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoTradedProductUpdqRow findRowByTradedProductIdValidForBizDate( long p_tradedProductId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoTradedProductUpdqRow.TYPE,
            "traded_product_id=? and valid_for_biz_date=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_validForBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoTradedProductUpdqDao.findRowsByTradedProductIdValidForBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTradedProductIdValidForBizDate(long, String)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static IfoTradedProductUpdqDao findDaoByTradedProductIdValidForBizDate( long p_tradedProductId, String p_validForBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradedProductIdValidForBizDate( p_tradedProductId, p_validForBizDate ) );
    }


  /** 
   * p_validForBizDate, p_institutionCode, p_marketId, p_productId, and �ɂĎw��̒l�����ӂ�{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_validForBizDate �����Ώۂł���p_validForBizDate�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���p_validForBizDate, p_institutionCode, p_marketId, p_productId, and �̒l�ƈ�v����{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoTradedProductUpdqRow findRowByValidForBizDateInstitutionCodeMarketIdProductId( String p_validForBizDate, String p_institutionCode, long p_marketId, long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoTradedProductUpdqRow.TYPE,
            "valid_for_biz_date=? and institution_code=? and market_id=? and product_id=?",
            null,
            new Object[] { p_validForBizDate, p_institutionCode, new Long(p_marketId), new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoTradedProductUpdqDao.findRowsByValidForBizDateInstitutionCodeMarketIdProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByValidForBizDateInstitutionCodeMarketIdProductId(String, String, long, long)}�����{@@link #forRow(IfoTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static IfoTradedProductUpdqDao findDaoByValidForBizDateInstitutionCodeMarketIdProductId( String p_validForBizDate, String p_institutionCode, long p_marketId, long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByValidForBizDateInstitutionCodeMarketIdProductId( p_validForBizDate, p_institutionCode, p_marketId, p_productId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
