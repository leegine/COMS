head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.57.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondOrderActionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BondOrderActionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BondOrderActionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BondOrderActionPK 
 * @@see BondOrderActionRow 
 */
public class BondOrderActionDao extends DataAccessObject {


  /** 
   * ����{@@link BondOrderActionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BondOrderActionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BondOrderActionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BondOrderActionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BondOrderActionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BondOrderActionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondOrderActionRow )
                return new BondOrderActionDao( (BondOrderActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondOrderActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondOrderActionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BondOrderActionRow}�I�u�W�F�N�g 
    */
    protected BondOrderActionDao( BondOrderActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BondOrderActionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BondOrderActionRow getBondOrderActionRow() {
        return row;
    }


  /** 
   * �w���{@@link BondOrderActionRow}�I�u�W�F�N�g����{@@link BondOrderActionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BondOrderActionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BondOrderActionDao}�擾�̂��߂Ɏw���{@@link BondOrderActionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BondOrderActionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BondOrderActionDao forRow( BondOrderActionRow row ) throws java.lang.IllegalArgumentException {
        return (BondOrderActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondOrderActionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BondOrderActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BondOrderActionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BondOrderActionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondOrderActionRow.TYPE );
    }


  /** 
   * {@@link BondOrderActionRow}����ӂɓ��肷��{@@link BondOrderActionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BondOrderActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BondOrderActionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BondOrderActionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BondOrderActionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BondOrderActionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BondOrderActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_orderActionId �����Ώۂł���p_orderActionId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondOrderActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondOrderActionRow findRowByPk( long p_orderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderActionPK pk = new BondOrderActionPK( p_orderActionId );
        return findRowByPk( pk );
    }


  /** 
   * �w���BondOrderActionPK�I�u�W�F�N�g����{@@link BondOrderActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BondOrderActionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondOrderActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondOrderActionRow findRowByPk( BondOrderActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondOrderActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static BondOrderActionDao findDaoByPk( long p_orderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderActionPK pk = new BondOrderActionPK( p_orderActionId );
        BondOrderActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BondOrderActionPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static BondOrderActionDao findDaoByPk( BondOrderActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderActionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BondOrderActionDao}�ɕR�t��{@@link BondOrderActionRow}���ŊO���L�[�̊֌W������{@@link BondProductRow}���������܂��B 
   * 
   * @@return {@@link BondOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link BondProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public BondProductRow fetchBondProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        Row row = BondProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBondProductRowViaProductId()}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public BondProductDao fetchBondProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        DataAccessObject dao = BondProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondProductDao) dao;
    }


  /** 
   * ����{@@link BondOrderActionDao}�ɕR�t��{@@link BondOrderActionRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link BondOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
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
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


  /** 
   * ����{@@link BondOrderActionDao}�ɕR�t��{@@link BondOrderActionRow}���ŊO���L�[�̊֌W������{@@link MarketRow}���������܂��B 
   * 
   * @@return {@@link BondOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link MarketRow}�܂��͊O���L�[ID�̒l������null�ɐݒ�̏ꍇ��null 
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
   * @@deprecated �����{@@link #fetchMarketRowViaMarketId()}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
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
   * ����{@@link BondOrderActionDao}�ɕR�t��{@@link BondOrderActionRow}���ŊO���L�[�̊֌W������{@@link SubAccountRow}���������܂��B 
   * 
   * @@return {@@link BondOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow} 
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
   * @@deprecated �����{@@link #fetchSubAccountRowViaAccountIdSubAccountId()}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public SubAccountDao fetchSubAccountDaoViaAccountIdSubAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        SubAccountPK pk = new SubAccountPK( row.getAccountId(), row.getSubAccountId() );
        DataAccessObject dao = SubAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SubAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SubAccountDao) dao;
    }


  /** 
   * ����{@@link BondOrderActionDao}�ɕR�t��{@@link BondOrderActionRow}���ŊO���L�[�̊֌W������{@@link TraderRow}���������܂��B 
   * 
   * @@return {@@link BondOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link TraderRow}�܂��͊O���L�[ID�̒l������null�ɐݒ�̏ꍇ��null 
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
   * @@deprecated �����{@@link #fetchTraderRowViaTraderId()}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
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
   * ����{@@link BondOrderActionDao}�ɕR�t��{@@link BondOrderActionRow}���ŊO���L�[�̊֌W������{@@link BondOrderUnitRow}���������܂��B 
   * 
   * @@return {@@link BondOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderUnitRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public BondOrderUnitRow fetchBondOrderUnitRowViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondOrderUnitPK pk = new BondOrderUnitPK( row.getOrderUnitId() );
        Row row = BondOrderUnitDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondOrderUnitRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondOrderUnitRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBondOrderUnitRowViaOrderUnitId()}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public BondOrderUnitDao fetchBondOrderUnitDaoViaOrderUnitId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondOrderUnitPK pk = new BondOrderUnitPK( row.getOrderUnitId() );
        DataAccessObject dao = BondOrderUnitDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondOrderUnitDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondOrderUnitDao) dao;
    }


  /** 
   * ����{@@link BondOrderActionDao}�ɕR�t��{@@link BondOrderActionRow}���ŊO���L�[�̊֌W������{@@link BondOrderRow}���������܂��B 
   * 
   * @@return {@@link BondOrderActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public BondOrderRow fetchBondOrderRowViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondOrderPK pk = new BondOrderPK( row.getOrderId() );
        Row row = BondOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondOrderRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBondOrderRowViaOrderId()}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public BondOrderDao fetchBondOrderDaoViaOrderId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondOrderPK pk = new BondOrderPK( row.getOrderId() );
        DataAccessObject dao = BondOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondOrderDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByProductId(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByProductId( BondProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getBondProductRow() );
    }


  /** 
   * {@@link BondProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BondProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BondProductRow}�ɊO���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link BondProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BondProductPK}�I�u�W�F�N�g 
   * @@return {@@link BondProductPK}�ƊO���L�[����v����l������{@@link BondOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(BondProductRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( BondProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(BondProductRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(BondProductPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link BondOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
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
   * {@@link MarketRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MarketRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MarketRow}�ɊO���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( row.getMarketId() );
    }


  /** 
   * {@@link MarketPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MarketPK}�I�u�W�F�N�g 
   * @@return {@@link MarketPK}�ƊO���L�[����v����l������{@@link BondOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByMarketId( pk.market_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketId( long p_marketId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "market_id=?",
            null,
            new Object[] { new Long(p_marketId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Market
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(MarketPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( MarketPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( pk.market_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketId(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketId( long p_marketId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByMarketId( p_marketId ) );
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
   * {@@link SubAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link SubAccountRow}�ɊO���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( row.getAccountId(), row.getSubAccountId() );
    }


  /** 
   * {@@link SubAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link SubAccountPK}�I�u�W�F�N�g 
   * @@return {@@link SubAccountPK}�ƊO���L�[����v����l������{@@link BondOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountId( long p_accountId, long p_subAccountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SubAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(SubAccountPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( SubAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( pk.account_id, pk.sub_account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountId(long, long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
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
   * {@@link TraderRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link TraderRow}�I�u�W�F�N�g 
   * @@return �w���{@@link TraderRow}�ɊO���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( row.getTraderId() );
    }


  /** 
   * {@@link TraderPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link TraderPK}�I�u�W�F�N�g 
   * @@return {@@link TraderPK}�ƊO���L�[����v����l������{@@link BondOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByTraderId( pk.trader_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_traderId �����Ώۂł���p_traderId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTraderId( long p_traderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "trader_id=?",
            null,
            new Object[] { new Long(p_traderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Trader
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByTraderId(TraderRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( TraderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTraderId(TraderRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( TraderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTraderId(TraderPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( TraderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( pk.trader_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTraderId(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTraderId( long p_traderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByTraderId( p_traderId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondOrderUnit
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByOrderUnitId(BondOrderUnitRow)}���g�p���Ă��������B 
   */
    public static List findRowsByOrderUnitId( BondOrderUnitDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( dao.getBondOrderUnitRow() );
    }


  /** 
   * {@@link BondOrderUnitRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BondOrderUnitRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BondOrderUnitRow}�ɊO���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderUnitId( BondOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( row.getOrderUnitId() );
    }


  /** 
   * {@@link BondOrderUnitPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BondOrderUnitPK}�I�u�W�F�N�g 
   * @@return {@@link BondOrderUnitPK}�ƊO���L�[����v����l������{@@link BondOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderUnitId( BondOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderUnitId( pk.order_unit_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderUnitId( long p_orderUnitId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "order_unit_id=?",
            null,
            new Object[] { new Long(p_orderUnitId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondOrderUnit
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByOrderUnitId(BondOrderUnitRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderUnitId( BondOrderUnitDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderUnitId(BondOrderUnitRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderUnitId( BondOrderUnitRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderUnitId(BondOrderUnitPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderUnitId( BondOrderUnitPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( pk.order_unit_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderUnitId(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderUnitId( long p_orderUnitId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderUnitId( p_orderUnitId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByOrderId(BondOrderRow)}���g�p���Ă��������B 
   */
    public static List findRowsByOrderId( BondOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( dao.getBondOrderRow() );
    }


  /** 
   * {@@link BondOrderRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BondOrderRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BondOrderRow}�ɊO���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderId( BondOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( row.getOrderId() );
    }


  /** 
   * {@@link BondOrderPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondOrderActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BondOrderPK}�I�u�W�F�N�g 
   * @@return {@@link BondOrderPK}�ƊO���L�[����v����l������{@@link BondOrderActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderId( BondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByOrderId( pk.order_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondOrderActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondOrderActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderId( long p_orderId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "order_id=?",
            null,
            new Object[] { new Long(p_orderId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByOrderId(BondOrderRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( BondOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderId(BondOrderRow)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( BondOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderId(BondOrderPK)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( BondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( pk.order_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderId(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderId( long p_orderId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByOrderId( p_orderId ) );
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
   * p_orderActionId, and �ɂĎw��̒l�����ӂ�{@@link BondOrderActionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_orderActionId �����Ώۂł���p_orderActionId�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderActionId, and �̒l�ƈ�v����{@@link BondOrderActionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BondOrderActionRow findRowByOrderActionId( long p_orderActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondOrderActionRow.TYPE,
            "order_action_id=?",
            null,
            new Object[] { new Long(p_orderActionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondOrderActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondOrderActionDao.findRowsByOrderActionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByOrderActionId(long)}�����{@@link #forRow(BondOrderActionRow)}���g�p���Ă��������B 
   */
    public static BondOrderActionDao findDaoByOrderActionId( long p_orderActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderActionId( p_orderActionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

}
@
