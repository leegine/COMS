head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoDeliveryMonthMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link IfoDeliveryMonthMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoDeliveryMonthMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoDeliveryMonthMasterPK 
 * @@see IfoDeliveryMonthMasterRow 
 */
public class IfoDeliveryMonthMasterDao extends DataAccessObject {


  /** 
   * ����{@@link IfoDeliveryMonthMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoDeliveryMonthMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoDeliveryMonthMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoDeliveryMonthMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoDeliveryMonthMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoDeliveryMonthMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoDeliveryMonthMasterRow )
                return new IfoDeliveryMonthMasterDao( (IfoDeliveryMonthMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoDeliveryMonthMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoDeliveryMonthMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g 
    */
    protected IfoDeliveryMonthMasterDao( IfoDeliveryMonthMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoDeliveryMonthMasterRow getIfoDeliveryMonthMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g����{@@link IfoDeliveryMonthMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoDeliveryMonthMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoDeliveryMonthMasterDao}�擾�̂��߂Ɏw���{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoDeliveryMonthMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoDeliveryMonthMasterDao forRow( IfoDeliveryMonthMasterRow row ) throws java.lang.IllegalArgumentException {
        return (IfoDeliveryMonthMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoDeliveryMonthMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoDeliveryMonthMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoDeliveryMonthMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoDeliveryMonthMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoDeliveryMonthMasterRow.TYPE );
    }


  /** 
   * {@@link IfoDeliveryMonthMasterRow}����ӂɓ��肷��{@@link IfoDeliveryMonthMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoDeliveryMonthMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoDeliveryMonthMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoDeliveryMonthMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoDeliveryMonthMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_underlyingProductCode �����Ώۂł���p_underlyingProductCode�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * @@param p_deliveryMonth �����Ώۂł���p_deliveryMonth�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoDeliveryMonthMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoDeliveryMonthMasterRow findRowByPk( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDeliveryMonthMasterPK pk = new IfoDeliveryMonthMasterPK( p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoDeliveryMonthMasterPK�I�u�W�F�N�g����{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoDeliveryMonthMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoDeliveryMonthMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoDeliveryMonthMasterRow findRowByPk( IfoDeliveryMonthMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoDeliveryMonthMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public static IfoDeliveryMonthMasterDao findDaoByPk( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDeliveryMonthMasterPK pk = new IfoDeliveryMonthMasterPK( p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth );
        IfoDeliveryMonthMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoDeliveryMonthMasterPK)}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public static IfoDeliveryMonthMasterDao findDaoByPk( IfoDeliveryMonthMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDeliveryMonthMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link IfoDeliveryMonthMasterDao}�ɕR�t��{@@link IfoDeliveryMonthMasterRow}���ŊO���L�[�̊֌W������{@@link IfoIndexMasterRow}���������܂��B 
   * 
   * @@return {@@link IfoDeliveryMonthMasterDao}�ƊO���L�[�̊֌W�ɂ���{@@link IfoIndexMasterRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public IfoIndexMasterRow fetchIfoIndexMasterRowViaIndexId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( row.getIndexId() );
        Row row = IfoIndexMasterDao.findRowByPk( pk );
        if ( row != null && !(row instanceof IfoIndexMasterRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (IfoIndexMasterRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoIndexMasterRowViaIndexId()}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public IfoIndexMasterDao fetchIfoIndexMasterDaoViaIndexId() throws DataNetworkException, DataFindException, DataQueryException  {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( row.getIndexId() );
        DataAccessObject dao = IfoIndexMasterDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof IfoIndexMasterDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (IfoIndexMasterDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for IfoIndexMaster
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByIndexId(IfoIndexMasterRow)}���g�p���Ă��������B 
   */
    public static List findRowsByIndexId( IfoIndexMasterDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByIndexId( dao.getIfoIndexMasterRow() );
    }


  /** 
   * {@@link IfoIndexMasterRow}�ƊO���L�[�̊֌W�ɂ���{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link IfoIndexMasterRow}�I�u�W�F�N�g 
   * @@return �w���{@@link IfoIndexMasterRow}�ɊO���L�[������{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIndexId( IfoIndexMasterRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByIndexId( row.getIndexId() );
    }


  /** 
   * {@@link IfoIndexMasterPK}�ƊO���L�[�̊֌W�ɂ���{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link IfoIndexMasterPK}�I�u�W�F�N�g 
   * @@return {@@link IfoIndexMasterPK}�ƊO���L�[����v����l������{@@link IfoDeliveryMonthMasterRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIndexId( IfoIndexMasterPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByIndexId( pk.index_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_indexId �����Ώۂł���p_indexId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByIndexId( long p_indexId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoDeliveryMonthMasterRow.TYPE,
            "index_id=?",
            null,
            new Object[] { new Long(p_indexId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for IfoIndexMaster
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByIndexId(IfoIndexMasterRow)}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIndexId( IfoIndexMasterDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIndexId(IfoIndexMasterRow)}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIndexId( IfoIndexMasterRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIndexId(IfoIndexMasterPK)}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIndexId( IfoIndexMasterPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( pk.index_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByIndexId(long)}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByIndexId( long p_indexId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByIndexId( p_indexId ) );
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
   * p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth, and �ɂĎw��̒l�����ӂ�{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_underlyingProductCode �����Ώۂł���p_underlyingProductCode�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * @@param p_deliveryMonth �����Ώۂł���p_deliveryMonth�t�B�[���h�̒l
   * 
   * @@return �����w���p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth, and �̒l�ƈ�v����{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoDeliveryMonthMasterRow findRowByUnderlyingProductCodeFutureOptionDivDeliveryMonth( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoDeliveryMonthMasterRow.TYPE,
            "underlying_product_code=? and future_option_div=? and delivery_month=?",
            null,
            new Object[] { p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoDeliveryMonthMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoDeliveryMonthMasterDao.findRowsByUnderlyingProductCodeFutureOptionDivDeliveryMonth(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByUnderlyingProductCodeFutureOptionDivDeliveryMonth(String, String, String)}�����{@@link #forRow(IfoDeliveryMonthMasterRow)}���g�p���Ă��������B 
   */
    public static IfoDeliveryMonthMasterDao findDaoByUnderlyingProductCodeFutureOptionDivDeliveryMonth( String p_underlyingProductCode, String p_futureOptionDiv, String p_deliveryMonth ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByUnderlyingProductCodeFutureOptionDivDeliveryMonth( p_underlyingProductCode, p_futureOptionDiv, p_deliveryMonth ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
