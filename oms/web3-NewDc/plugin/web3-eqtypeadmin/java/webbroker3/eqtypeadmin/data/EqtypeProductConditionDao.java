head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EqtypeProductConditionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.eqtypeadmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EqtypeProductConditionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link EqtypeProductConditionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see EqtypeProductConditionPK 
 * @@see EqtypeProductConditionRow 
 */
public class EqtypeProductConditionDao extends DataAccessObject {


  /** 
   * ����{@@link EqtypeProductConditionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private EqtypeProductConditionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link EqtypeProductConditionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link EqtypeProductConditionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link EqtypeProductConditionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link EqtypeProductConditionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeProductConditionRow )
                return new EqtypeProductConditionDao( (EqtypeProductConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeProductConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeProductConditionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link EqtypeProductConditionRow}�I�u�W�F�N�g 
    */
    protected EqtypeProductConditionDao( EqtypeProductConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public EqtypeProductConditionRow getEqtypeProductConditionRow() {
        return row;
    }


  /** 
   * �w���{@@link EqtypeProductConditionRow}�I�u�W�F�N�g����{@@link EqtypeProductConditionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link EqtypeProductConditionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link EqtypeProductConditionDao}�擾�̂��߂Ɏw���{@@link EqtypeProductConditionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link EqtypeProductConditionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static EqtypeProductConditionDao forRow( EqtypeProductConditionRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeProductConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeProductConditionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link EqtypeProductConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link EqtypeProductConditionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link EqtypeProductConditionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeProductConditionRow.TYPE );
    }


  /** 
   * {@@link EqtypeProductConditionRow}����ӂɓ��肷��{@@link EqtypeProductConditionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link EqtypeProductConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link EqtypeProductConditionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link EqtypeProductConditionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static EqtypeProductConditionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new EqtypeProductConditionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_eqtypeProductConditionId �����Ώۂł���p_eqtypeProductConditionId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EqtypeProductConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EqtypeProductConditionRow findRowByPk( long p_eqtypeProductConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductConditionPK pk = new EqtypeProductConditionPK( p_eqtypeProductConditionId );
        return findRowByPk( pk );
    }


  /** 
   * �w���EqtypeProductConditionPK�I�u�W�F�N�g����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����EqtypeProductConditionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EqtypeProductConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EqtypeProductConditionRow findRowByPk( EqtypeProductConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeProductConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static EqtypeProductConditionDao findDaoByPk( long p_eqtypeProductConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductConditionPK pk = new EqtypeProductConditionPK( p_eqtypeProductConditionId );
        EqtypeProductConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(EqtypeProductConditionPK)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static EqtypeProductConditionDao findDaoByPk( EqtypeProductConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeProductConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link EqtypeProductConditionDao}�ɕR�t��{@@link EqtypeProductConditionRow}���ŊO���L�[�̊֌W������{@@link ProductRow}���������܂��B 
   * 
   * @@return {@@link EqtypeProductConditionDao}�ƊO���L�[�̊֌W�ɂ���{@@link ProductRow} 
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
   * @@deprecated �����{@@link #fetchProductRowViaProductId()}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
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
   * {@@link ProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link ProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link ProductRow}�ɊO���L�[������{@@link EqtypeProductConditionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link ProductPK}�I�u�W�F�N�g 
   * @@return {@@link ProductPK}�ƊO���L�[����v����l������{@@link EqtypeProductConditionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link EqtypeProductConditionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductRow)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(ProductPK)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
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
   * p_eqtypeProductConditionId, and �ɂĎw��̒l�����ӂ�{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_eqtypeProductConditionId �����Ώۂł���p_eqtypeProductConditionId�t�B�[���h�̒l
   * 
   * @@return �����w���p_eqtypeProductConditionId, and �̒l�ƈ�v����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EqtypeProductConditionRow findRowByEqtypeProductConditionId( long p_eqtypeProductConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "eqtype_product_condition_id=?",
            null,
            new Object[] { new Long(p_eqtypeProductConditionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductConditionDao.findRowsByEqtypeProductConditionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByEqtypeProductConditionId(long)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static EqtypeProductConditionDao findDaoByEqtypeProductConditionId( long p_eqtypeProductConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByEqtypeProductConditionId( p_eqtypeProductConditionId ) );
    }


  /** 
   * p_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and �ɂĎw��̒l�����ӂ�{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_largeItemDiv �����Ώۂł���p_largeItemDiv�t�B�[���h�̒l
   * @@param p_smallItemDiv �����Ώۂł���p_smallItemDiv�t�B�[���h�̒l
   * @@param p_deleteFlg �����Ώۂł���p_deleteFlg�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and �̒l�ƈ�v����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EqtypeProductConditionRow findRowByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg( String p_institutionCode, String p_productCode, String p_marketCode, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "institution_code=? and product_code=? and market_code=? and large_item_div=? and small_item_div=? and delete_flg=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductConditionDao.findRowsByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg(String, String, String, String, String, String)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static EqtypeProductConditionDao findDaoByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg( String p_institutionCode, String p_productCode, String p_marketCode, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeMarketCodeLargeItemDivSmallItemDivDeleteFlg( p_institutionCode, p_productCode, p_marketCode, p_largeItemDiv, p_smallItemDiv, p_deleteFlg ) );
    }


  /** 
   * p_productId, p_marketId, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and �ɂĎw��̒l�����ӂ�{@@link EqtypeProductConditionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_marketId �����Ώۂł���p_marketId�t�B�[���h�̒l
   * @@param p_largeItemDiv �����Ώۂł���p_largeItemDiv�t�B�[���h�̒l
   * @@param p_smallItemDiv �����Ώۂł���p_smallItemDiv�t�B�[���h�̒l
   * @@param p_deleteFlg �����Ώۂł���p_deleteFlg�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, p_marketId, p_largeItemDiv, p_smallItemDiv, p_deleteFlg, and �̒l�ƈ�v����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EqtypeProductConditionRow findRowByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg( long p_productId, long p_marketId, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "product_id=? and market_id=? and large_item_div=? and small_item_div=? and delete_flg=?",
            null,
            new Object[] { new Long(p_productId), new Long(p_marketId), p_largeItemDiv, p_smallItemDiv, p_deleteFlg } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EqtypeProductConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EqtypeProductConditionDao.findRowsByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg(long, long, String, String, String)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static EqtypeProductConditionDao findDaoByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg( long p_productId, long p_marketId, String p_largeItemDiv, String p_smallItemDiv, String p_deleteFlg ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdMarketIdLargeItemDivSmallItemDivDeleteFlg( p_productId, p_marketId, p_largeItemDiv, p_smallItemDiv, p_deleteFlg ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productCode, p_deleteFlg, p_marketCode, and �ɂĎw��̒l�Ɉ�v����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_deleteFlg �����Ώۂł���p_deleteFlg�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_deleteFlg, p_marketCode, and �̒l�ƈ�v����{@@link EqtypeProductConditionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeProductCodeDeleteFlgMarketCode( String p_institutionCode, String p_productCode, String p_deleteFlg, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeProductConditionRow.TYPE,
            "institution_code=? and product_code=? and delete_flg=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_deleteFlg, p_marketCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeProductCodeDeleteFlgMarketCode(String, String, String, String)}�����{@@link #forRow(EqtypeProductConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeProductCodeDeleteFlgMarketCode( String p_institutionCode, String p_productCode, String p_deleteFlg, String p_marketCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductCodeDeleteFlgMarketCode( p_institutionCode, p_productCode, p_deleteFlg, p_marketCode ) );
    }

}
@
