head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ProductDao.java;


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
 * {@@link ProductDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link ProductRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see ProductPK 
 * @@see ProductRow 
 */
public class ProductDao extends DataAccessObject {


  /** 
   * ����{@@link ProductDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private ProductRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link ProductRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link ProductDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link ProductDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link ProductRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ProductRow )
                return new ProductDao( (ProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ProductRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link ProductRow}�I�u�W�F�N�g 
    */
    protected ProductDao( ProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link ProductRow}�I�u�W�F�N�g���擾���܂��B
   */
    public ProductRow getProductRow() {
        return row;
    }


  /** 
   * �w���{@@link ProductRow}�I�u�W�F�N�g����{@@link ProductDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link ProductRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link ProductDao}�擾�̂��߂Ɏw���{@@link ProductRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link ProductDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static ProductDao forRow( ProductRow row ) throws java.lang.IllegalArgumentException {
        return (ProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ProductRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link ProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link ProductPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link ProductParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ProductRow.TYPE );
    }


  /** 
   * {@@link ProductRow}����ӂɓ��肷��{@@link ProductPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link ProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link ProductParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link ProductPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static ProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new ProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link ProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductPK pk = new ProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * �w���ProductPK�I�u�W�F�N�g����{@@link ProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����ProductPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ProductRow findRowByPk( ProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public static ProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductPK pk = new ProductPK( p_productId );
        ProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(ProductPK)}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public static ProductDao findDaoByPk( ProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductRow row = findRowByPk( pk );
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
   * ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TradedProductUpdqRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TradedProductUpdqRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductUpdqRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductUpdqRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductUpdqDaosByProductId();
    }


  /** 
   * ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TradedProductRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TradedProductRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductDaosByProductId();
    }


  /** 
   * ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TradedProductCalendarRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TradedProductCalendarRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTradedProductCalendarRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductCalendarRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductCalendarDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTradedProductCalendarRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTradedProductCalendarDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductCalendarDaosByProductId();
    }


  /** 
   * ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TickValuesDefsRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link ProductDao}�Ɋ֘A����{@@link ProductRow}�̊O���L�[������{@@link TickValuesDefsRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTickValuesDefsRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTickValuesDefsRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTickValuesDefsDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTickValuesDefsRowsByProductId()}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public List fetchTickValuesDefsDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTickValuesDefsDaosByProductId();
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
   * p_productId, and �ɂĎw��̒l�����ӂ�{@@link ProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, and �̒l�ƈ�v����{@@link ProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static ProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductId(long)}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public static ProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productType, and �ɂĎw��̒l�Ɉ�v����{@@link ProductRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productType, and �̒l�ƈ�v����{@@link ProductRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeProductType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ProductRow.TYPE,
            "institution_code=? and product_type=?",
            null,
            new Object[] { p_institutionCode, p_productType } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeProductType(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(ProductRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeProductType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductType( p_institutionCode, p_productType ) );
    }

}
@
