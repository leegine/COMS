head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.03.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqProductDao.java;


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
 * {@@link FeqProductDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FeqProductRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FeqProductPK 
 * @@see FeqProductRow 
 */
public class FeqProductDao extends DataAccessObject {


  /** 
   * ����{@@link FeqProductDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FeqProductRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FeqProductRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FeqProductDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FeqProductDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FeqProductRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqProductRow )
                return new FeqProductDao( (FeqProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqProductRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FeqProductRow}�I�u�W�F�N�g 
    */
    protected FeqProductDao( FeqProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FeqProductRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FeqProductRow getFeqProductRow() {
        return row;
    }


  /** 
   * �w���{@@link FeqProductRow}�I�u�W�F�N�g����{@@link FeqProductDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FeqProductRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FeqProductDao}�擾�̂��߂Ɏw���{@@link FeqProductRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FeqProductDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FeqProductDao forRow( FeqProductRow row ) throws java.lang.IllegalArgumentException {
        return (FeqProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqProductRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FeqProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FeqProductPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FeqProductParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqProductRow.TYPE );
    }


  /** 
   * {@@link FeqProductRow}����ӂɓ��肷��{@@link FeqProductPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FeqProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FeqProductParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FeqProductPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FeqProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FeqProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FeqProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqProductPK pk = new FeqProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * �w���FeqProductPK�I�u�W�F�N�g����{@@link FeqProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FeqProductPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqProductRow findRowByPk( FeqProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static FeqProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqProductPK pk = new FeqProductPK( p_productId );
        FeqProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FeqProductPK)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static FeqProductDao findDaoByPk( FeqProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link FeqProductDao}�ɕR�t��{@@link FeqProductRow}���ŊO���L�[�̊֌W������{@@link ProductRow}���������܂��B 
   * 
   * @@return {@@link FeqProductDao}�ƊO���L�[�̊֌W�ɂ���{@@link ProductRow} 
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
   * @@deprecated �����{@@link #fetchProductRowViaProductId()}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
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
   * ����{@@link FeqProductDao}�Ɋ֘A����{@@link FeqProductRow}�̊O���L�[������{@@link FeqTradedProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link FeqProductDao}�Ɋ֘A����{@@link FeqProductRow}�̊O���L�[������{@@link FeqTradedProductRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchFeqTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return FeqTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqTradedProductRowsByProductId()}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public List fetchFeqTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return FeqTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqTradedProductRowsByProductId()}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public List fetchFeqTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchFeqTradedProductDaosByProductId();
    }


  /** 
   * ����{@@link FeqProductDao}�Ɋ֘A����{@@link FeqProductRow}�̊O���L�[������{@@link FeqTradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link FeqProductDao}�Ɋ֘A����{@@link FeqProductRow}�̊O���L�[������{@@link FeqTradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchFeqTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return FeqTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqTradedProductUpdqRowsByProductId()}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public List fetchFeqTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return FeqTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchFeqTradedProductUpdqRowsByProductId()}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public List fetchFeqTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchFeqTradedProductUpdqDaosByProductId();
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
   * {@@link ProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link FeqProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link ProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link ProductRow}�ɊO���L�[������{@@link FeqProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link FeqProductRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link ProductPK}�I�u�W�F�N�g 
   * @@return {@@link ProductPK}�ƊO���L�[����v����l������{@@link FeqProductRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link FeqProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link FeqProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductPK)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
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
   * p_productId, and �ɂĎw��̒l�����ӂ�{@@link FeqProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, and �̒l�ƈ�v����{@@link FeqProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductId(long)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static FeqProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_institutionCode, p_productCode, and �ɂĎw��̒l�����ӂ�{@@link FeqProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, and �̒l�ƈ�v����{@@link FeqProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqProductRow findRowByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqProductRow.TYPE,
            "institution_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqProductDao.findRowsByInstitutionCodeProductCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductCode(String, String)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static FeqProductDao findDaoByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCode( p_institutionCode, p_productCode ) );
    }


  /** 
   * p_institutionCode, p_marketCode, p_productCode, and �ɂĎw��̒l�����ӂ�{@@link FeqProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_marketCode, p_productCode, and �̒l�ƈ�v����{@@link FeqProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqProductRow findRowByInstitutionCodeMarketCodeProductCode( String p_institutionCode, String p_marketCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqProductRow.TYPE,
            "institution_code=? and market_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_marketCode, p_productCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqProductDao.findRowsByInstitutionCodeMarketCodeProductCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeMarketCodeProductCode(String, String, String)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static FeqProductDao findDaoByInstitutionCodeMarketCodeProductCode( String p_institutionCode, String p_marketCode, String p_productCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCodeProductCode( p_institutionCode, p_marketCode, p_productCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productCode, p_offshoreProductCode, and �ɂĎw��̒l�Ɉ�v����{@@link FeqProductRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_offshoreProductCode �����Ώۂł���p_offshoreProductCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_offshoreProductCode, and �̒l�ƈ�v����{@@link FeqProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeProductCodeOffshoreProductCode( String p_institutionCode, String p_productCode, String p_offshoreProductCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqProductRow.TYPE,
            "institution_code=? and product_code=? and offshore_product_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_offshoreProductCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeProductCodeOffshoreProductCode(String, String, String)}�����{@@link #forRow(FeqProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeProductCodeOffshoreProductCode( String p_institutionCode, String p_productCode, String p_offshoreProductCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductCodeOffshoreProductCode( p_institutionCode, p_productCode, p_offshoreProductCode ) );
    }

}
@
