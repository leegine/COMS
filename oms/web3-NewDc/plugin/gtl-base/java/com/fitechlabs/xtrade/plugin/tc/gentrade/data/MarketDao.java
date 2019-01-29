head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketDao.java;


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
 * {@@link MarketDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MarketRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MarketPK 
 * @@see MarketRow 
 */
public class MarketDao extends DataAccessObject {


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MarketRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MarketRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MarketDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MarketDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MarketRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MarketRow )
                return new MarketDao( (MarketRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MarketRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MarketRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MarketRow}�I�u�W�F�N�g 
    */
    protected MarketDao( MarketRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MarketRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MarketRow getMarketRow() {
        return row;
    }


  /** 
   * �w���{@@link MarketRow}�I�u�W�F�N�g����{@@link MarketDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MarketRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MarketDao}�擾�̂��߂Ɏw���{@@link MarketRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MarketDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MarketDao forRow( MarketRow row ) throws java.lang.IllegalArgumentException {
        return (MarketDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MarketRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MarketRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MarketPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MarketParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MarketRow.TYPE );
    }


  /** 
   * {@@link MarketRow}����ӂɓ��肷��{@@link MarketPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MarketRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MarketParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MarketPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MarketPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MarketPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MarketRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MarketRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MarketRow findRowByPk( long p_marketId ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketPK pk = new MarketPK( p_marketId );
        return findRowByPk( pk );
    }


  /** 
   * �w���MarketPK�I�u�W�F�N�g����{@@link MarketRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MarketPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MarketRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MarketRow findRowByPk( MarketPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MarketRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public static MarketDao findDaoByPk( long p_marketId ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketPK pk = new MarketPK( p_marketId );
        MarketRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MarketPK)}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public static MarketDao findDaoByPk( MarketPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TradedProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TradedProductRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTradedProductRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductDaosByMarketId();
    }


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TickValuesDefsRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TickValuesDefsRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTickValuesDefsRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTickValuesDefsRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTickValuesDefsDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTickValuesDefsRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTickValuesDefsDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTickValuesDefsDaosByMarketId();
    }


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link MarketCalendarRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link MarketCalendarRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchMarketCalendarRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketCalendarDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMarketCalendarRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchMarketCalendarDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketCalendarDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMarketCalendarRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchMarketCalendarDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchMarketCalendarDaosByMarketId();
    }


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTradedProductUpdqRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductUpdqRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductUpdqDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductUpdqRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductUpdqDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductUpdqDaosByMarketId();
    }


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link MarketPreferencesRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link MarketPreferencesRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchMarketPreferencesRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketPreferencesDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMarketPreferencesRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchMarketPreferencesDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return MarketPreferencesDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMarketPreferencesRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchMarketPreferencesDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchMarketPreferencesDaosByMarketId();
    }


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TradedProductCalendarRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link TradedProductCalendarRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTradedProductCalendarRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductCalendarRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductCalendarDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductCalendarRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductCalendarDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductCalendarDaosByMarketId();
    }


  /** 
   * ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link MarketDao}�Ɋ֘A����{@@link MarketRow}�̊O���L�[������{@@link LimitPriceRangeDefsRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchLimitPriceRangeDefsRowsByMarketId() throws DataNetworkException, DataQueryException  {
        return LimitPriceRangeDefsDao.findRowsByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchLimitPriceRangeDefsRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchLimitPriceRangeDefsDaosByMarketId() throws DataNetworkException, DataQueryException  {
        return LimitPriceRangeDefsDao.findDaosByMarketId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchLimitPriceRangeDefsRowsByMarketId()}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public List fetchLimitPriceRangeDefsDaosMarketId() throws DataNetworkException, DataQueryException  {
        return fetchLimitPriceRangeDefsDaosByMarketId();
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
   * p_marketId, and �ɂĎw��̒l�����ӂ�{@@link MarketRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �����w���p_marketId, and �̒l�ƈ�v����{@@link MarketRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MarketRow findRowByMarketId( long p_marketId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketDao.findRowsByMarketId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByMarketId(long)}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public static MarketDao findDaoByMarketId( long p_marketId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketId( p_marketId ) );
    }


  /** 
   * p_institutionCode, p_marketCode, and �ɂĎw��̒l�����ӂ�{@@link MarketRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_marketCode, and �̒l�ƈ�v����{@@link MarketRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MarketRow findRowByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketRow.TYPE,
            "institution_code=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_marketCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketDao.findRowsByInstitutionCodeMarketCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeMarketCode(String, String)}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public static MarketDao findDaoByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCode( p_institutionCode, p_marketCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_marketId, p_institutionCode, p_marketCode, and �ɂĎw��̒l�Ɉ�v����{@@link MarketRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_marketId, p_institutionCode, p_marketCode, and �̒l�ƈ�v����{@@link MarketRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketIdInstitutionCodeMarketCode( long p_marketId, String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketRow.TYPE,
            "market_id=? and institution_code=? and market_code=?",
            null,
            new Object[] { new Long(p_marketId), p_institutionCode, p_marketCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketIdInstitutionCodeMarketCode(long, String, String)}�����{@@link #forRow(MarketRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketIdInstitutionCodeMarketCode( long p_marketId, String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMarketIdInstitutionCodeMarketCode( p_marketId, p_institutionCode, p_marketCode ) );
    }

}
@
