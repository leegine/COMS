head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoIndexMasterDao.java;


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
 * {@@link IfoIndexMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoIndexMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoIndexMasterPK 
 * @@see IfoIndexMasterRow 
 */
public class IfoIndexMasterDao extends DataAccessObject {


  /** 
   * ����{@@link IfoIndexMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoIndexMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoIndexMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoIndexMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoIndexMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoIndexMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoIndexMasterRow )
                return new IfoIndexMasterDao( (IfoIndexMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoIndexMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoIndexMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoIndexMasterRow}�I�u�W�F�N�g 
    */
    protected IfoIndexMasterDao( IfoIndexMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoIndexMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoIndexMasterRow getIfoIndexMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoIndexMasterRow}�I�u�W�F�N�g����{@@link IfoIndexMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoIndexMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoIndexMasterDao}�擾�̂��߂Ɏw���{@@link IfoIndexMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoIndexMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoIndexMasterDao forRow( IfoIndexMasterRow row ) throws java.lang.IllegalArgumentException {
        return (IfoIndexMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoIndexMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoIndexMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoIndexMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoIndexMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoIndexMasterRow.TYPE );
    }


  /** 
   * {@@link IfoIndexMasterRow}����ӂɓ��肷��{@@link IfoIndexMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoIndexMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoIndexMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoIndexMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoIndexMasterPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoIndexMasterPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoIndexMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_indexId �����Ώۂł���p_indexId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoIndexMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoIndexMasterRow findRowByPk( long p_indexId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( p_indexId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoIndexMasterPK�I�u�W�F�N�g����{@@link IfoIndexMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoIndexMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoIndexMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoIndexMasterRow findRowByPk( IfoIndexMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoIndexMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(IfoIndexMasterRow)}���g�p���Ă��������B 
   */
    public static IfoIndexMasterDao findDaoByPk( long p_indexId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoIndexMasterPK pk = new IfoIndexMasterPK( p_indexId );
        IfoIndexMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoIndexMasterPK)}�����{@@link #forRow(IfoIndexMasterRow)}���g�p���Ă��������B 
   */
    public static IfoIndexMasterDao findDaoByPk( IfoIndexMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoIndexMasterRow row = findRowByPk( pk );
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
   * ����{@@link IfoIndexMasterDao}�Ɋ֘A����{@@link IfoIndexMasterRow}�̊O���L�[������{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link IfoIndexMasterDao}�Ɋ֘A����{@@link IfoIndexMasterRow}�̊O���L�[������{@@link IfoDeliveryMonthMasterRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchIfoDeliveryMonthMasterRowsByIndexId() throws DataNetworkException, DataQueryException  {
        return IfoDeliveryMonthMasterDao.findRowsByIndexId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoDeliveryMonthMasterRowsByIndexId()}�����{@@link #forRow(IfoIndexMasterRow)}���g�p���Ă��������B 
   */
    public List fetchIfoDeliveryMonthMasterDaosByIndexId() throws DataNetworkException, DataQueryException  {
        return IfoDeliveryMonthMasterDao.findDaosByIndexId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchIfoDeliveryMonthMasterRowsByIndexId()}�����{@@link #forRow(IfoIndexMasterRow)}���g�p���Ă��������B 
   */
    public List fetchIfoDeliveryMonthMasterDaosIndexId() throws DataNetworkException, DataQueryException  {
        return fetchIfoDeliveryMonthMasterDaosByIndexId();
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
   * p_underlyingProductCode, p_futureOptionDiv, and �ɂĎw��̒l�����ӂ�{@@link IfoIndexMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_underlyingProductCode �����Ώۂł���p_underlyingProductCode�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_underlyingProductCode, p_futureOptionDiv, and �̒l�ƈ�v����{@@link IfoIndexMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoIndexMasterRow findRowByUnderlyingProductCodeFutureOptionDiv( String p_underlyingProductCode, String p_futureOptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoIndexMasterRow.TYPE,
            "underlying_product_code=? and future_option_div=?",
            null,
            new Object[] { p_underlyingProductCode, p_futureOptionDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoIndexMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoIndexMasterDao.findRowsByUnderlyingProductCodeFutureOptionDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByUnderlyingProductCodeFutureOptionDiv(String, String)}�����{@@link #forRow(IfoIndexMasterRow)}���g�p���Ă��������B 
   */
    public static IfoIndexMasterDao findDaoByUnderlyingProductCodeFutureOptionDiv( String p_underlyingProductCode, String p_futureOptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByUnderlyingProductCodeFutureOptionDiv( p_underlyingProductCode, p_futureOptionDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
