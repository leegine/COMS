head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsPriceCondDao.java;


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
 * {@@link RlsPriceCondDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RlsPriceCondRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RlsPriceCondPK 
 * @@see RlsPriceCondRow 
 */
public class RlsPriceCondDao extends DataAccessObject {


  /** 
   * ����{@@link RlsPriceCondDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RlsPriceCondRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RlsPriceCondRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RlsPriceCondDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RlsPriceCondDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RlsPriceCondRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsPriceCondRow )
                return new RlsPriceCondDao( (RlsPriceCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsPriceCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsPriceCondRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RlsPriceCondRow}�I�u�W�F�N�g 
    */
    protected RlsPriceCondDao( RlsPriceCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RlsPriceCondRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RlsPriceCondRow getRlsPriceCondRow() {
        return row;
    }


  /** 
   * �w���{@@link RlsPriceCondRow}�I�u�W�F�N�g����{@@link RlsPriceCondDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RlsPriceCondRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RlsPriceCondDao}�擾�̂��߂Ɏw���{@@link RlsPriceCondRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RlsPriceCondDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RlsPriceCondDao forRow( RlsPriceCondRow row ) throws java.lang.IllegalArgumentException {
        return (RlsPriceCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsPriceCondRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RlsPriceCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RlsPriceCondPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RlsPriceCondParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsPriceCondRow.TYPE );
    }


  /** 
   * {@@link RlsPriceCondRow}����ӂɓ��肷��{@@link RlsPriceCondPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RlsPriceCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RlsPriceCondParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RlsPriceCondPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RlsPriceCondPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsPriceCondPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RlsPriceCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_condOrdId �����Ώۂł���p_condOrdId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsPriceCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsPriceCondRow findRowByPk( long p_condOrdId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsPriceCondPK pk = new RlsPriceCondPK( p_condOrdId );
        return findRowByPk( pk );
    }


  /** 
   * �w���RlsPriceCondPK�I�u�W�F�N�g����{@@link RlsPriceCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RlsPriceCondPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsPriceCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsPriceCondRow findRowByPk( RlsPriceCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsPriceCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public static RlsPriceCondDao findDaoByPk( long p_condOrdId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsPriceCondPK pk = new RlsPriceCondPK( p_condOrdId );
        RlsPriceCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RlsPriceCondPK)}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public static RlsPriceCondDao findDaoByPk( RlsPriceCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsPriceCondRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link RlsPriceCondDao}�ɕR�t��{@@link RlsPriceCondRow}���ŊO���L�[�̊֌W������{@@link RlsCondOrderRow}���������܂��B 
   * 
   * @@return {@@link RlsPriceCondDao}�ƊO���L�[�̊֌W�ɂ���{@@link RlsCondOrderRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public RlsCondOrderRow fetchRlsCondOrderRowViaCondOrdId() throws DataNetworkException, DataFindException, DataQueryException  {
        RlsCondOrderPK pk = new RlsCondOrderPK( row.getCondOrdId() );
        Row row = RlsCondOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RlsCondOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RlsCondOrderRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchRlsCondOrderRowViaCondOrdId()}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public RlsCondOrderDao fetchRlsCondOrderDaoViaCondOrdId() throws DataNetworkException, DataFindException, DataQueryException  {
        RlsCondOrderPK pk = new RlsCondOrderPK( row.getCondOrdId() );
        DataAccessObject dao = RlsCondOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RlsCondOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RlsCondOrderDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for RlsCondOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByCondOrdId(RlsCondOrderRow)}���g�p���Ă��������B 
   */
    public static List findRowsByCondOrdId( RlsCondOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( dao.getRlsCondOrderRow() );
    }


  /** 
   * {@@link RlsCondOrderRow}�ƊO���L�[�̊֌W�ɂ���{@@link RlsPriceCondRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link RlsCondOrderRow}�I�u�W�F�N�g 
   * @@return �w���{@@link RlsCondOrderRow}�ɊO���L�[������{@@link RlsPriceCondRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCondOrdId( RlsCondOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( row.getId() );
    }


  /** 
   * {@@link RlsCondOrderPK}�ƊO���L�[�̊֌W�ɂ���{@@link RlsPriceCondRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link RlsCondOrderPK}�I�u�W�F�N�g 
   * @@return {@@link RlsCondOrderPK}�ƊO���L�[����v����l������{@@link RlsPriceCondRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCondOrdId( RlsCondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( pk.id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link RlsPriceCondRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_id �����Ώۂł���p_id�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link RlsPriceCondRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCondOrdId( long p_id  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsPriceCondRow.TYPE,
            "cond_ord_id=?",
            null,
            new Object[] { new Long(p_id) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RlsCondOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByCondOrdId(RlsCondOrderRow)}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCondOrdId( RlsCondOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCondOrdId(RlsCondOrderRow)}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCondOrdId( RlsCondOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCondOrdId(RlsCondOrderPK)}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCondOrdId( RlsCondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( pk.id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCondOrdId(long)}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCondOrdId( long p_id ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( p_id ) );
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
   * p_condOrdId, and �ɂĎw��̒l�����ӂ�{@@link RlsPriceCondRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_condOrdId �����Ώۂł���p_condOrdId�t�B�[���h�̒l
   * 
   * @@return �����w���p_condOrdId, and �̒l�ƈ�v����{@@link RlsPriceCondRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RlsPriceCondRow findRowByCondOrdId( long p_condOrdId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsPriceCondRow.TYPE,
            "cond_ord_id=?",
            null,
            new Object[] { new Long(p_condOrdId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsPriceCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsPriceCondDao.findRowsByCondOrdId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByCondOrdId(long)}�����{@@link #forRow(RlsPriceCondRow)}���g�p���Ă��������B 
   */
    public static RlsPriceCondDao findDaoByCondOrdId( long p_condOrdId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCondOrdId( p_condOrdId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
