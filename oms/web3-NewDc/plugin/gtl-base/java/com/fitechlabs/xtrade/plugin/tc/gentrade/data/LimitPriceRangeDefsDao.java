head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	LimitPriceRangeDefsDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link LimitPriceRangeDefsDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link LimitPriceRangeDefsRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see LimitPriceRangeDefsPK 
 * @@see LimitPriceRangeDefsRow 
 */
public class LimitPriceRangeDefsDao extends DataAccessObject {


  /** 
   * ����{@@link LimitPriceRangeDefsDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private LimitPriceRangeDefsRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link LimitPriceRangeDefsRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link LimitPriceRangeDefsDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link LimitPriceRangeDefsDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link LimitPriceRangeDefsRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LimitPriceRangeDefsRow )
                return new LimitPriceRangeDefsDao( (LimitPriceRangeDefsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LimitPriceRangeDefsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LimitPriceRangeDefsRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g 
    */
    protected LimitPriceRangeDefsDao( LimitPriceRangeDefsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g���擾���܂��B
   */
    public LimitPriceRangeDefsRow getLimitPriceRangeDefsRow() {
        return row;
    }


  /** 
   * �w���{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g����{@@link LimitPriceRangeDefsDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link LimitPriceRangeDefsRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link LimitPriceRangeDefsDao}�擾�̂��߂Ɏw���{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link LimitPriceRangeDefsDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static LimitPriceRangeDefsDao forRow( LimitPriceRangeDefsRow row ) throws java.lang.IllegalArgumentException {
        return (LimitPriceRangeDefsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LimitPriceRangeDefsRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link LimitPriceRangeDefsRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link LimitPriceRangeDefsPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link LimitPriceRangeDefsParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LimitPriceRangeDefsRow.TYPE );
    }


  /** 
   * {@@link LimitPriceRangeDefsRow}����ӂɓ��肷��{@@link LimitPriceRangeDefsPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link LimitPriceRangeDefsRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link LimitPriceRangeDefsParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link LimitPriceRangeDefsPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static LimitPriceRangeDefsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new LimitPriceRangeDefsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_limitPriceRangeDefsId �����Ώۂł���p_limitPriceRangeDefsId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LimitPriceRangeDefsRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LimitPriceRangeDefsRow findRowByPk( long p_limitPriceRangeDefsId ) throws DataFindException, DataQueryException, DataNetworkException {
        LimitPriceRangeDefsPK pk = new LimitPriceRangeDefsPK( p_limitPriceRangeDefsId );
        return findRowByPk( pk );
    }


  /** 
   * �w���LimitPriceRangeDefsPK�I�u�W�F�N�g����{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����LimitPriceRangeDefsPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LimitPriceRangeDefsRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LimitPriceRangeDefsRow findRowByPk( LimitPriceRangeDefsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LimitPriceRangeDefsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
   */
    public static LimitPriceRangeDefsDao findDaoByPk( long p_limitPriceRangeDefsId ) throws DataFindException, DataQueryException, DataNetworkException {
        LimitPriceRangeDefsPK pk = new LimitPriceRangeDefsPK( p_limitPriceRangeDefsId );
        LimitPriceRangeDefsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(LimitPriceRangeDefsPK)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
   */
    public static LimitPriceRangeDefsDao findDaoByPk( LimitPriceRangeDefsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LimitPriceRangeDefsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link LimitPriceRangeDefsDao}�ɕR�t��{@@link LimitPriceRangeDefsRow}���ŊO���L�[�̊֌W������{@@link MarketRow}���������܂��B 
   * 
   * @@return {@@link LimitPriceRangeDefsDao}�ƊO���L�[�̊֌W�ɂ���{@@link MarketRow} 
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
   * @@deprecated �����{@@link #fetchMarketRowViaMarketId()}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
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
    // Find Rows given foreign key values for Market
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByMarketId(MarketRow)}���g�p���Ă��������B 
   */
    public static List findRowsByMarketId( MarketDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( dao.getMarketRow() );
    }


  /** 
   * {@@link MarketRow}�ƊO���L�[�̊֌W�ɂ���{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MarketRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MarketRow}�ɊO���L�[������{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}�ƊO���L�[�̊֌W�ɂ���{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MarketPK}�I�u�W�F�N�g 
   * @@return {@@link MarketPK}�ƊO���L�[����v����l������{@@link LimitPriceRangeDefsRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LimitPriceRangeDefsRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketPK)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(long)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
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
   * p_limitPriceRangeDefsId, and �ɂĎw��̒l�����ӂ�{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_limitPriceRangeDefsId �����Ώۂł���p_limitPriceRangeDefsId�t�B�[���h�̒l
   * 
   * @@return �����w���p_limitPriceRangeDefsId, and �̒l�ƈ�v����{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static LimitPriceRangeDefsRow findRowByLimitPriceRangeDefsId( long p_limitPriceRangeDefsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LimitPriceRangeDefsRow.TYPE,
            "limit_price_range_defs_id=?",
            null,
            new Object[] { new Long(p_limitPriceRangeDefsId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LimitPriceRangeDefsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LimitPriceRangeDefsDao.findRowsByLimitPriceRangeDefsId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByLimitPriceRangeDefsId(long)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
   */
    public static LimitPriceRangeDefsDao findDaoByLimitPriceRangeDefsId( long p_limitPriceRangeDefsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLimitPriceRangeDefsId( p_limitPriceRangeDefsId ) );
    }


  /** 
   * p_marketId, p_capPrice, and �ɂĎw��̒l�����ӂ�{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * @@param p_capPrice �����Ώۂł���p_capPrice�t�B�[���h�̒l
   * 
   * @@return �����w���p_marketId, p_capPrice, and �̒l�ƈ�v����{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static LimitPriceRangeDefsRow findRowByMarketIdCapPrice( long p_marketId, double p_capPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LimitPriceRangeDefsRow.TYPE,
            "market_id=? and cap_price=?",
            null,
            new Object[] { new Long(p_marketId), new Double(p_capPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LimitPriceRangeDefsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LimitPriceRangeDefsDao.findRowsByMarketIdCapPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByMarketIdCapPrice(long, double)}�����{@@link #forRow(LimitPriceRangeDefsRow)}���g�p���Ă��������B 
   */
    public static LimitPriceRangeDefsDao findDaoByMarketIdCapPrice( long p_marketId, double p_capPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketIdCapPrice( p_marketId, p_capPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
