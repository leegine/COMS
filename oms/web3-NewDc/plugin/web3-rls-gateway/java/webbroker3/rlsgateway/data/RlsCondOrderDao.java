head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsCondOrderDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rlsgateway.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RlsCondOrderDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RlsCondOrderRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RlsCondOrderPK 
 * @@see RlsCondOrderRow 
 */
public class RlsCondOrderDao extends DataAccessObject {


  /** 
   * ����{@@link RlsCondOrderDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RlsCondOrderRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RlsCondOrderRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RlsCondOrderDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RlsCondOrderDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RlsCondOrderRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsCondOrderRow )
                return new RlsCondOrderDao( (RlsCondOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsCondOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsCondOrderRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RlsCondOrderRow}�I�u�W�F�N�g 
    */
    protected RlsCondOrderDao( RlsCondOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RlsCondOrderRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RlsCondOrderRow getRlsCondOrderRow() {
        return row;
    }


  /** 
   * �w���{@@link RlsCondOrderRow}�I�u�W�F�N�g����{@@link RlsCondOrderDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RlsCondOrderRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RlsCondOrderDao}�擾�̂��߂Ɏw���{@@link RlsCondOrderRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RlsCondOrderDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RlsCondOrderDao forRow( RlsCondOrderRow row ) throws java.lang.IllegalArgumentException {
        return (RlsCondOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsCondOrderRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RlsCondOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RlsCondOrderPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RlsCondOrderParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsCondOrderRow.TYPE );
    }


  /** 
   * {@@link RlsCondOrderRow}����ӂɓ��肷��{@@link RlsCondOrderPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RlsCondOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RlsCondOrderParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RlsCondOrderPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RlsCondOrderPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsCondOrderPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RlsCondOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_id �����Ώۂł���p_id�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsCondOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsCondOrderRow findRowByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsCondOrderPK pk = new RlsCondOrderPK( p_id );
        return findRowByPk( pk );
    }


  /** 
   * �w���RlsCondOrderPK�I�u�W�F�N�g����{@@link RlsCondOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RlsCondOrderPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsCondOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsCondOrderRow findRowByPk( RlsCondOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsCondOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public static RlsCondOrderDao findDaoByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsCondOrderPK pk = new RlsCondOrderPK( p_id );
        RlsCondOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RlsCondOrderPK)}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public static RlsCondOrderDao findDaoByPk( RlsCondOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsCondOrderRow row = findRowByPk( pk );
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
   * ����{@@link RlsCondOrderDao}�Ɋ֘A����{@@link RlsCondOrderRow}�̊O���L�[������{@@link RlsOmsOrderRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link RlsCondOrderDao}�Ɋ֘A����{@@link RlsCondOrderRow}�̊O���L�[������{@@link RlsOmsOrderRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchRlsOmsOrderRowsByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsOmsOrderDao.findRowsByCondOrdId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRlsOmsOrderRowsByCondOrdId()}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public List fetchRlsOmsOrderDaosByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsOmsOrderDao.findDaosByCondOrdId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRlsOmsOrderRowsByCondOrdId()}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public List fetchRlsOmsOrderDaosCondOrdId() throws DataNetworkException, DataQueryException  {
        return fetchRlsOmsOrderDaosByCondOrdId();
    }


  /** 
   * ����{@@link RlsCondOrderDao}�Ɋ֘A����{@@link RlsCondOrderRow}�̊O���L�[������{@@link RlsPriceCondRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link RlsCondOrderDao}�Ɋ֘A����{@@link RlsCondOrderRow}�̊O���L�[������{@@link RlsPriceCondRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchRlsPriceCondRowsByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsPriceCondDao.findRowsByCondOrdId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRlsPriceCondRowsByCondOrdId()}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public List fetchRlsPriceCondDaosByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsPriceCondDao.findDaosByCondOrdId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchRlsPriceCondRowsByCondOrdId()}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public List fetchRlsPriceCondDaosCondOrdId() throws DataNetworkException, DataQueryException  {
        return fetchRlsPriceCondDaosByCondOrdId();
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
   * p_id, and �ɂĎw��̒l�����ӂ�{@@link RlsCondOrderRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_id �����Ώۂł���p_id�t�B�[���h�̒l
   * 
   * @@return �����w���p_id, and �̒l�ƈ�v����{@@link RlsCondOrderRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RlsCondOrderRow findRowById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsCondOrderRow.TYPE,
            "id=?",
            null,
            new Object[] { new Long(p_id) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsCondOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsCondOrderDao.findRowsById(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowById(long)}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public static RlsCondOrderDao findDaoById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowById( p_id ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, and �ɂĎw��̒l�Ɉ�v����{@@link RlsCondOrderRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, and �̒l�ƈ�v����{@@link RlsCondOrderRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsCondOrderRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountId( p_accountId ) );
    }


  /** 
   * p_parentId, and �ɂĎw��̒l�Ɉ�v����{@@link RlsCondOrderRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_parentId �����Ώۂł���p_parentId�t�B�[���h�̒l
   * 
   * @@return �����w���p_parentId, and �̒l�ƈ�v����{@@link RlsCondOrderRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByParentId( Long p_parentId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsCondOrderRow.TYPE,
            "parent_id=?",
            null,
            new Object[] { p_parentId } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByParentId(Long)}�����{@@link #forRow(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public static List findDaosByParentId( Long p_parentId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByParentId( p_parentId ) );
    }

}
@
