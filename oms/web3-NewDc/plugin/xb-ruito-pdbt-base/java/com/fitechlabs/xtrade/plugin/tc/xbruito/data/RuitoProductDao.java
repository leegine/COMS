head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.28.09.09.29;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5644d9050285d38;
filename	RuitoProductDao.java;

1.1
date	2011.03.22.09.17.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoProductDao.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link RuitoProductDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RuitoProductRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RuitoProductPK 
 * @@see RuitoProductRow 
 */
public class RuitoProductDao extends DataAccessObject {


  /** 
   * ����{@@link RuitoProductDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RuitoProductRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RuitoProductRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RuitoProductDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RuitoProductDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RuitoProductRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RuitoProductRow )
                return new RuitoProductDao( (RuitoProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RuitoProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RuitoProductRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RuitoProductRow}�I�u�W�F�N�g 
    */
    protected RuitoProductDao( RuitoProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RuitoProductRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RuitoProductRow getRuitoProductRow() {
        return row;
    }


  /** 
   * �w���{@@link RuitoProductRow}�I�u�W�F�N�g����{@@link RuitoProductDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RuitoProductRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RuitoProductDao}�擾�̂��߂Ɏw���{@@link RuitoProductRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RuitoProductDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RuitoProductDao forRow( RuitoProductRow row ) throws java.lang.IllegalArgumentException {
        return (RuitoProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RuitoProductRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RuitoProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RuitoProductPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RuitoProductParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RuitoProductRow.TYPE );
    }


  /** 
   * {@@link RuitoProductRow}����ӂɓ��肷��{@@link RuitoProductPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RuitoProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RuitoProductParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RuitoProductPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RuitoProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RuitoProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RuitoProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RuitoProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RuitoProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoProductPK pk = new RuitoProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * �w���RuitoProductPK�I�u�W�F�N�g����{@@link RuitoProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RuitoProductPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RuitoProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RuitoProductRow findRowByPk( RuitoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RuitoProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public static RuitoProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoProductPK pk = new RuitoProductPK( p_productId );
        RuitoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RuitoProductPK)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public static RuitoProductDao findDaoByPk( RuitoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link RuitoProductDao}�ɕR�t��{@@link RuitoProductRow}���ŊO���L�[�̊֌W������{@@link ProductRow}���������܂��B 
   * 
   * @@return {@@link RuitoProductDao}�ƊO���L�[�̊֌W�ɂ���{@@link ProductRow} 
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
   * @@deprecated �����{@@link #fetchProductRowViaProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
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
   * ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchRuitoTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public List fetchRuitoTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public List fetchRuitoTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductUpdqDaosByProductId();
    }


  /** 
   * ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchRuitoTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRuitoTradedProductRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public List fetchRuitoTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRuitoTradedProductRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public List fetchRuitoTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductDaosByProductId();
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
   * {@@link ProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link RuitoProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link ProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link ProductRow}�ɊO���L�[������{@@link RuitoProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link RuitoProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link ProductPK}�I�u�W�F�N�g 
   * @@return {@@link ProductPK}�ƊO���L�[����v����l������{@@link RuitoProductRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link RuitoProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link RuitoProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductPK)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
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
   * p_productId, and �ɂĎw��̒l�����ӂ�{@@link RuitoProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, and �̒l�ƈ�v����{@@link RuitoProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RuitoProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RuitoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RuitoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RuitoProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductId(long)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public static RuitoProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_institutionCode, p_productCode, p_productIssueCode, and �ɂĎw��̒l�����ӂ�{@@link RuitoProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_productIssueCode �����Ώۂł���p_productIssueCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_productIssueCode, and �̒l�ƈ�v����{@@link RuitoProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RuitoProductRow findRowByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RuitoProductRow.TYPE,
            "institution_code=? and product_code=? and product_issue_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_productIssueCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RuitoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RuitoProductDao.findRowsByInstitutionCodeProductCodeProductIssueCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductCodeProductIssueCode(String, String, String)}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
   */
    public static RuitoProductDao findDaoByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeProductIssueCode( p_institutionCode, p_productCode, p_productIssueCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@


1.1
log
@*** empty log message ***
@
text
@d221 1
a221 1
   * ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
d223 1
a223 1
   * @@return ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductRow}�I�u�W�F�N�g��{@@link List}
d227 2
a228 2
    public List fetchRuitoTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findRowsByProductId( row );
d233 1
a233 1
   * @@deprecated �����{@@link #fetchRuitoTradedProductRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
d235 2
a236 2
    public List fetchRuitoTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findDaosByProductId( row );
d241 1
a241 1
   * @@deprecated �����{@@link #fetchRuitoTradedProductRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
d243 2
a244 2
    public List fetchRuitoTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductDaosByProductId();
d249 1
a249 1
   * ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
d251 1
a251 1
   * @@return ����{@@link RuitoProductDao}�Ɋ֘A����{@@link RuitoProductRow}�̊O���L�[������{@@link RuitoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}
d255 2
a256 2
    public List fetchRuitoTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findRowsByProductId( row );
d261 1
a261 1
   * @@deprecated �����{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
d263 2
a264 2
    public List fetchRuitoTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findDaosByProductId( row );
d269 1
a269 1
   * @@deprecated �����{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}�����{@@link #forRow(RuitoProductRow)}���g�p���Ă��������B 
d271 2
a272 2
    public List fetchRuitoTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductUpdqDaosByProductId();
@

