head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.37.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqtypeTradedProductUpdqDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EqtypeTradedProductUpdqDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link EqtypeTradedProductUpdqRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see EqtypeTradedProductUpdqPK 
 * @@see EqtypeTradedProductUpdqRow 
 */
public class EqtypeTradedProductUpdqDao extends DataAccessObject {


  /** 
   * ����{@@link EqtypeTradedProductUpdqDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private EqtypeTradedProductUpdqRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link EqtypeTradedProductUpdqRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link EqtypeTradedProductUpdqDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link EqtypeTradedProductUpdqDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link EqtypeTradedProductUpdqRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeTradedProductUpdqRow )
                return new EqtypeTradedProductUpdqDao( (EqtypeTradedProductUpdqRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeTradedProductUpdqRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeTradedProductUpdqRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g 
    */
    protected EqtypeTradedProductUpdqDao( EqtypeTradedProductUpdqRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���擾���܂��B
   */
    public EqtypeTradedProductUpdqRow getEqtypeTradedProductUpdqRow() {
        return row;
    }


  /** 
   * �w���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g����{@@link EqtypeTradedProductUpdqDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link EqtypeTradedProductUpdqRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link EqtypeTradedProductUpdqDao}�擾�̂��߂Ɏw���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link EqtypeTradedProductUpdqDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static EqtypeTradedProductUpdqDao forRow( EqtypeTradedProductUpdqRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeTradedProductUpdqDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeTradedProductUpdqRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link EqtypeTradedProductUpdqRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link EqtypeTradedProductUpdqPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link EqtypeTradedProductUpdqParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeTradedProductUpdqRow.TYPE );
    }


  /** 
   * {@@link EqtypeTradedProductUpdqRow}����ӂɓ��肷��{@@link EqtypeTradedProductUpdqPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link EqtypeTradedProductUpdqRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link EqtypeTradedProductUpdqParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link EqtypeTradedProductUpdqPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static EqtypeTradedProductUpdqPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_tradedProductId �����Ώۂł���p_tradedProductId�t�B�[���h�̒l
   * @@param p_validUntilBizDate �����Ώۂł���p_validUntilBizDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EqtypeTradedProductUpdqRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EqtypeTradedProductUpdqRow findRowByPk( long p_tradedProductId, String p_validUntilBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeTradedProductUpdqPK pk = new EqtypeTradedProductUpdqPK( p_tradedProductId, p_validUntilBizDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���EqtypeTradedProductUpdqPK�I�u�W�F�N�g����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����EqtypeTradedProductUpdqPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EqtypeTradedProductUpdqRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EqtypeTradedProductUpdqRow findRowByPk( EqtypeTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeTradedProductUpdqRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static EqtypeTradedProductUpdqDao findDaoByPk( long p_tradedProductId, String p_validUntilBizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeTradedProductUpdqPK pk = new EqtypeTradedProductUpdqPK( p_tradedProductId, p_validUntilBizDate );
        EqtypeTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(EqtypeTradedProductUpdqPK)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static EqtypeTradedProductUpdqDao findDaoByPk( EqtypeTradedProductUpdqPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeTradedProductUpdqRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link EqtypeTradedProductUpdqDao}�ɕR�t��{@@link EqtypeTradedProductUpdqRow}���ŊO���L�[�̊֌W������{@@link TradedProductRow}���������܂��B 
   * 
   * @@return {@@link EqtypeTradedProductUpdqDao}�ƊO���L�[�̊֌W�ɂ���{@@link TradedProductRow} 
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
   * @@deprecated �����{@@link #fetchTradedProductRowViaTradedProductId()}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public TradedProductDao fetchTradedProductDaoViaTradedProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        TradedProductPK pk = new TradedProductPK( row.getTradedProductId() );
        DataAccessObject dao = TradedProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof TradedProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (TradedProductDao) dao;
    }


  /** 
   * ����{@@link EqtypeTradedProductUpdqDao}�ɕR�t��{@@link EqtypeTradedProductUpdqRow}���ŊO���L�[�̊֌W������{@@link EqtypeProductRow}���������܂��B 
   * 
   * @@return {@@link EqtypeTradedProductUpdqDao}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public EqtypeProductRow fetchEqtypeProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeProductPK pk = new EqtypeProductPK( row.getProductId() );
        Row row = EqtypeProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof EqtypeProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (EqtypeProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchEqtypeProductRowViaProductId()}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public EqtypeProductDao fetchEqtypeProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        EqtypeProductPK pk = new EqtypeProductPK( row.getProductId() );
        DataAccessObject dao = EqtypeProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof EqtypeProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (EqtypeProductDao) dao;
    }


  /** 
   * ����{@@link EqtypeTradedProductUpdqDao}�ɕR�t��{@@link EqtypeTradedProductUpdqRow}���ŊO���L�[�̊֌W������{@@link MarketRow}���������܂��B 
   * 
   * @@return {@@link EqtypeTradedProductUpdqDao}�ƊO���L�[�̊֌W�ɂ���{@@link MarketRow} 
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
   * @@deprecated �����{@@link #fetchMarketRowViaMarketId()}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public MarketDao fetchMarketDaoViaMarketId() throws DataNetworkException, DataFindException, DataQueryException  {
        MarketPK pk = new MarketPK( row.getMarketId() );
        DataAccessObject dao = MarketDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MarketDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MarketDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

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
   * {@@link TradedProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link TradedProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link TradedProductRow}�ɊO���L�[������{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( row.getTradedProductId() );
    }


  /** 
   * {@@link TradedProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link TradedProductPK}�I�u�W�F�N�g 
   * @@return {@@link TradedProductPK}�ƊO���L�[����v����l������{@@link EqtypeTradedProductUpdqRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTradedProductId( pk.traded_product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_tradedProductId �����Ώۂł���p_tradedProductId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTradedProductId( long p_tradedProductId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "traded_product_id=?",
            null,
            new Object[] { new Long(p_tradedProductId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for TradedProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByTradedProductId(TradedProductRow)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( TradedProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTradedProductId(TradedProductRow)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( TradedProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTradedProductId(TradedProductPK)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( TradedProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( pk.traded_product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTradedProductId(long)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductId( long p_tradedProductId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTradedProductId( p_tradedProductId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for EqtypeProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByProductId(EqtypeProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByProductId( EqtypeProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getEqtypeProductRow() );
    }


  /** 
   * {@@link EqtypeProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link EqtypeProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link EqtypeProductRow}�ɊO���L�[������{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( EqtypeProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link EqtypeProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link EqtypeProductPK}�I�u�W�F�N�g 
   * @@return {@@link EqtypeProductPK}�ƊO���L�[����v����l������{@@link EqtypeTradedProductUpdqRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( EqtypeProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for EqtypeProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(EqtypeProductRow)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( EqtypeProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(EqtypeProductRow)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( EqtypeProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(EqtypeProductPK)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( EqtypeProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * {@@link MarketRow}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MarketRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MarketRow}�ɊO���L�[������{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MarketPK}�I�u�W�F�N�g 
   * @@return {@@link MarketPK}�ƊO���L�[����v����l������{@@link EqtypeTradedProductUpdqRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketPK)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(long)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
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
   * p_tradedProductId, p_validUntilBizDate, and �ɂĎw��̒l�����ӂ�{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tradedProductId �����Ώۂł���p_tradedProductId�t�B�[���h�̒l
   * @@param p_validUntilBizDate �����Ώۂł���p_validUntilBizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_tradedProductId, p_validUntilBizDate, and �̒l�ƈ�v����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EqtypeTradedProductUpdqRow findRowByTradedProductIdValidUntilBizDate( long p_tradedProductId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "traded_product_id=? and valid_until_biz_date=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_validUntilBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeTradedProductUpdqDao.findRowsByTradedProductIdValidUntilBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTradedProductIdValidUntilBizDate(long, String)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static EqtypeTradedProductUpdqDao findDaoByTradedProductIdValidUntilBizDate( long p_tradedProductId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradedProductIdValidUntilBizDate( p_tradedProductId, p_validUntilBizDate ) );
    }


  /** 
   * p_productId, p_marketId, p_validUntilBizDate, and �ɂĎw��̒l�����ӂ�{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * @@param p_validUntilBizDate �����Ώۂł���p_validUntilBizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, p_marketId, p_validUntilBizDate, and �̒l�ƈ�v����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EqtypeTradedProductUpdqRow findRowByProductIdMarketIdValidUntilBizDate( long p_productId, long p_marketId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "product_id=? and market_id=? and valid_until_biz_date=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId), p_validUntilBizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeTradedProductUpdqRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeTradedProductUpdqDao.findRowsByProductIdMarketIdValidUntilBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductIdMarketIdValidUntilBizDate(long, long, String)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static EqtypeTradedProductUpdqDao findDaoByProductIdMarketIdValidUntilBizDate( long p_productId, long p_marketId, String p_validUntilBizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketIdValidUntilBizDate( p_productId, p_marketId, p_validUntilBizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_tradedProductId, p_institutionCode, p_productId, p_marketId, and �ɂĎw��̒l�Ɉ�v����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_tradedProductId �����Ώۂł���p_tradedProductId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �����w���p_tradedProductId, p_institutionCode, p_productId, p_marketId, and �̒l�ƈ�v����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTradedProductIdInstitutionCodeProductIdMarketId( long p_tradedProductId, String p_institutionCode, long p_productId, long p_marketId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "traded_product_id=? and institution_code=? and product_id=? and market_id=?",
            null,
            new Object[] { new Long(p_tradedProductId), p_institutionCode, new Long(p_productId), new Long(p_marketId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTradedProductIdInstitutionCodeProductIdMarketId(long, String, long, long)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTradedProductIdInstitutionCodeProductIdMarketId( long p_tradedProductId, String p_institutionCode, long p_productId, long p_marketId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTradedProductIdInstitutionCodeProductIdMarketId( p_tradedProductId, p_institutionCode, p_productId, p_marketId ) );
    }


  /** 
   * p_institutionCode, p_productId, p_validUntilBizDate, and �ɂĎw��̒l�Ɉ�v����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_validUntilBizDate �����Ώۂł���p_validUntilBizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productId, p_validUntilBizDate, and �̒l�ƈ�v����{@@link EqtypeTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeProductIdValidUntilBizDate( String p_institutionCode, long p_productId, String p_validUntilBizDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "institution_code=? and product_id=? and valid_until_biz_date=?",
            null,
            new Object[] { p_institutionCode, new Long(p_productId), p_validUntilBizDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeProductIdValidUntilBizDate(String, long, String)}�����{@@link #forRow(EqtypeTradedProductUpdqRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeProductIdValidUntilBizDate( String p_institutionCode, long p_productId, String p_validUntilBizDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductIdValidUntilBizDate( p_institutionCode, p_productId, p_validUntilBizDate ) );
    }

}
@
