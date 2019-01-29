head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.06.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoProductDao.java;


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
 * {@@link IfoProductDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoProductRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoProductPK 
 * @@see IfoProductRow 
 */
public class IfoProductDao extends DataAccessObject {


  /** 
   * ����{@@link IfoProductDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoProductRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoProductRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoProductDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoProductDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoProductRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoProductRow )
                return new IfoProductDao( (IfoProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoProductRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoProductRow}�I�u�W�F�N�g 
    */
    protected IfoProductDao( IfoProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoProductRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoProductRow getIfoProductRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoProductRow}�I�u�W�F�N�g����{@@link IfoProductDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoProductRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoProductDao}�擾�̂��߂Ɏw���{@@link IfoProductRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoProductDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoProductDao forRow( IfoProductRow row ) throws java.lang.IllegalArgumentException {
        return (IfoProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoProductRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoProductPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoProductParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoProductRow.TYPE );
    }


  /** 
   * {@@link IfoProductRow}����ӂɓ��肷��{@@link IfoProductPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoProductParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoProductPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoProductPK pk = new IfoProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoProductPK�I�u�W�F�N�g����{@@link IfoProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoProductPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoProductRow findRowByPk( IfoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static IfoProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoProductPK pk = new IfoProductPK( p_productId );
        IfoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoProductPK)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static IfoProductDao findDaoByPk( IfoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link IfoProductDao}�ɕR�t��{@@link IfoProductRow}���ŊO���L�[�̊֌W������{@@link ProductRow}���������܂��B 
   * 
   * @@return {@@link IfoProductDao}�ƊO���L�[�̊֌W�ɂ���{@@link ProductRow} 
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
   * @@deprecated �����{@@link #fetchProductRowViaProductId()}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
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
   * ����{@@link IfoProductDao}�Ɋ֘A����{@@link IfoProductRow}�̊O���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoProductDao}�Ɋ֘A����{@@link IfoProductRow}�̊O���L�[������{@@link IfoTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoTradedProductUpdqRowsByProductId()}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public List fetchIfoTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoTradedProductUpdqRowsByProductId()}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public List fetchIfoTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchIfoTradedProductUpdqDaosByProductId();
    }


  /** 
   * ����{@@link IfoProductDao}�Ɋ֘A����{@@link IfoProductRow}�̊O���L�[������{@@link IfoTradedProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoProductDao}�Ɋ֘A����{@@link IfoProductRow}�̊O���L�[������{@@link IfoTradedProductRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoTradedProductRowsByProductId()}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public List fetchIfoTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoTradedProductRowsByProductId()}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public List fetchIfoTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchIfoTradedProductDaosByProductId();
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
   * {@@link ProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link IfoProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link ProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link ProductRow}�ɊO���L�[������{@@link IfoProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link IfoProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link ProductPK}�I�u�W�F�N�g 
   * @@return {@@link ProductPK}�ƊO���L�[����v����l������{@@link IfoProductRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IfoProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IfoProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductPK)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
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
   * p_productId, and �ɂĎw��̒l�����ӂ�{@@link IfoProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, and �̒l�ƈ�v����{@@link IfoProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductId(long)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static IfoProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_productCode, p_institutionCode, and �ɂĎw��̒l�����ӂ�{@@link IfoProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_productCode, p_institutionCode, and �̒l�ƈ�v����{@@link IfoProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoProductRow findRowByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "product_code=? and institution_code=?",
            null,
            new Object[] { p_productCode, p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoProductDao.findRowsByProductCodeInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductCodeInstitutionCode(String, String)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static IfoProductDao findDaoByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductCodeInstitutionCode( p_productCode, p_institutionCode ) );
    }


  /** 
   * p_institutionCode, p_underlyingProductCode, p_derivativeType, p_strikePrice, p_monthOfDelivery, p_splitType, and �ɂĎw��̒l�����ӂ�{@@link IfoProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_underlyingProductCode �����Ώۂł���p_underlyingProductCode�t�B�[���h�̒l
   * @@param p_derivativeType �����Ώۂł���p_derivativeType�t�B�[���h�̒l
   * @@param p_strikePrice �����Ώۂł���p_strikePrice�t�B�[���h�̒l
   * @@param p_monthOfDelivery �����Ώۂł���p_monthOfDelivery�t�B�[���h�̒l
   * @@param p_splitType �����Ώۂł���p_splitType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_underlyingProductCode, p_derivativeType, p_strikePrice, p_monthOfDelivery, p_splitType, and �̒l�ƈ�v����{@@link IfoProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoProductRow findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, double p_strikePrice, String p_monthOfDelivery, String p_splitType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "institution_code=? and underlying_product_code=? and derivative_type=? and strike_price=? and month_of_delivery=? and split_type=?",
            null,
            new Object[] { p_institutionCode, p_underlyingProductCode, p_derivativeType, new Double(p_strikePrice), p_monthOfDelivery, p_splitType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoProductDao.findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType(String, String, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum, double, String, String)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static IfoProductDao findDaoByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, double p_strikePrice, String p_monthOfDelivery, String p_splitType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType( p_institutionCode, p_underlyingProductCode, p_derivativeType, p_strikePrice, p_monthOfDelivery, p_splitType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery, and �ɂĎw��̒l�Ɉ�v����{@@link IfoProductRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_underlyingProductCode �����Ώۂł���p_underlyingProductCode�t�B�[���h�̒l
   * @@param p_derivativeType �����Ώۂł���p_derivativeType�t�B�[���h�̒l
   * @@param p_monthOfDelivery �����Ώۂł���p_monthOfDelivery�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery, and �̒l�ƈ�v����{@@link IfoProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, String p_monthOfDelivery ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "institution_code=? and underlying_product_code=? and derivative_type=? and month_of_delivery=?",
            null,
            new Object[] { p_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery(String, String, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum, String)}�����{@@link #forRow(IfoProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, String p_monthOfDelivery ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery( p_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery ) );
    }

}
@
