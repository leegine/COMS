head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondProductDao.java;


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
 * {@@link BondProductDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BondProductRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BondProductPK 
 * @@see BondProductRow 
 */
public class BondProductDao extends DataAccessObject {


  /** 
   * ����{@@link BondProductDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BondProductRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BondProductRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BondProductDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BondProductDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BondProductRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondProductRow )
                return new BondProductDao( (BondProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondProductRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BondProductRow}�I�u�W�F�N�g 
    */
    protected BondProductDao( BondProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BondProductRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BondProductRow getBondProductRow() {
        return row;
    }


  /** 
   * �w���{@@link BondProductRow}�I�u�W�F�N�g����{@@link BondProductDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BondProductRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BondProductDao}�擾�̂��߂Ɏw���{@@link BondProductRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BondProductDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BondProductDao forRow( BondProductRow row ) throws java.lang.IllegalArgumentException {
        return (BondProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondProductRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BondProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BondProductPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BondProductParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondProductRow.TYPE );
    }


  /** 
   * {@@link BondProductRow}����ӂɓ��肷��{@@link BondProductPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BondProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BondProductParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BondProductPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BondProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BondProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BondProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondProductPK pk = new BondProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * �w���BondProductPK�I�u�W�F�N�g����{@@link BondProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BondProductPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondProductRow findRowByPk( BondProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static BondProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondProductPK pk = new BondProductPK( p_productId );
        BondProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BondProductPK)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static BondProductDao findDaoByPk( BondProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BondProductDao}�ɕR�t��{@@link BondProductRow}���ŊO���L�[�̊֌W������{@@link ProductRow}���������܂��B 
   * 
   * @@return {@@link BondProductDao}�ƊO���L�[�̊֌W�ɂ���{@@link ProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public ProductRow fetchProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        ProductPK pk = new ProductPK( row.getProductId() );
        Row row = ProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof ProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (ProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchProductRowViaProductId()}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public ProductDao fetchProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        ProductPK pk = new ProductPK( row.getProductId() );
        DataAccessObject dao = ProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof ProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (ProductDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link BondProductDao}�Ɋ֘A����{@@link BondProductRow}�̊O���L�[������{@@link BondTradedProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link BondProductDao}�Ɋ֘A����{@@link BondProductRow}�̊O���L�[������{@@link BondTradedProductRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchBondTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBondTradedProductRowsByProductId()}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public List fetchBondTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBondTradedProductRowsByProductId()}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public List fetchBondTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchBondTradedProductDaosByProductId();
    }


  /** 
   * ����{@@link BondProductDao}�Ɋ֘A����{@@link BondProductRow}�̊O���L�[������{@@link BondTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link BondProductDao}�Ɋ֘A����{@@link BondProductRow}�̊O���L�[������{@@link BondTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchBondTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBondTradedProductUpdqRowsByProductId()}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public List fetchBondTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return BondTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchBondTradedProductUpdqRowsByProductId()}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public List fetchBondTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchBondTradedProductUpdqDaosByProductId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Product
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByProductId(ProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByProductId( ProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getProductRow() );
    }


  /** 
   * {@@link ProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link ProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link ProductRow}�ɊO���L�[������{@@link BondProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link ProductPK}�I�u�W�F�N�g 
   * @@return {@@link ProductPK}�ƊO���L�[����v����l������{@@link BondProductRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductPK)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * p_productId, and �ɂĎw��̒l�����ӂ�{@@link BondProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, and �̒l�ƈ�v����{@@link BondProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BondProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductId(long)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static BondProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_institutionCode, p_productCode, p_productIssueCode, and �ɂĎw��̒l�����ӂ�{@@link BondProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_productIssueCode �����Ώۂł���p_productIssueCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_productIssueCode, and �̒l�ƈ�v����{@@link BondProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BondProductRow findRowByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and product_code=? and product_issue_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_productIssueCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondProductDao.findRowsByInstitutionCodeProductCodeProductIssueCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductCodeProductIssueCode(String, String, String)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static BondProductDao findDaoByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeProductIssueCode( p_institutionCode, p_productCode, p_productIssueCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_bondType, and �ɂĎw��̒l�Ɉ�v����{@@link BondProductRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_bondType �����Ώۂł���p_bondType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_bondType, and �̒l�ƈ�v����{@@link BondProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBondType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum p_bondType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and bond_type=?",
            null,
            new Object[] { p_institutionCode, p_bondType } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBondType(String, com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBondType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum p_bondType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBondType( p_institutionCode, p_bondType ) );
    }


  /** 
   * p_institutionCode, p_tradeHandleDiv, p_tradeType, and �ɂĎw��̒l�Ɉ�v����{@@link BondProductRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_tradeHandleDiv �����Ώۂł���p_tradeHandleDiv�t�B�[���h�̒l
   * @@param p_tradeType �����Ώۂł���p_tradeType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_tradeHandleDiv, p_tradeType, and �̒l�ƈ�v����{@@link BondProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeTradeHandleDivTradeType( String p_institutionCode, String p_tradeHandleDiv, String p_tradeType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and trade_handle_div=? and trade_type=?",
            null,
            new Object[] { p_institutionCode, p_tradeHandleDiv, p_tradeType } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeTradeHandleDivTradeType(String, String, String)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeTradeHandleDivTradeType( String p_institutionCode, String p_tradeHandleDiv, String p_tradeType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeTradeHandleDivTradeType( p_institutionCode, p_tradeHandleDiv, p_tradeType ) );
    }


  /** 
   * p_institutionCode, p_currencyCode, and �ɂĎw��̒l�Ɉ�v����{@@link BondProductRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_currencyCode �����Ώۂł���p_currencyCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_currencyCode, and �̒l�ƈ�v����{@@link BondProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondProductRow.TYPE,
            "institution_code=? and currency_code=?",
            null,
            new Object[] { p_institutionCode, p_currencyCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeCurrencyCode(String, String)}�����{@@link #forRow(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeCurrencyCode( p_institutionCode, p_currencyCode ) );
    }

}
@
