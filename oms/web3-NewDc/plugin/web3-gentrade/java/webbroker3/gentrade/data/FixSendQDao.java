head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FixSendQDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FixSendQDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FixSendQRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FixSendQPK 
 * @@see FixSendQRow 
 */
public class FixSendQDao extends DataAccessObject {


  /** 
   * ����{@@link FixSendQDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FixSendQRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FixSendQRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FixSendQDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FixSendQDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FixSendQRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FixSendQRow )
                return new FixSendQDao( (FixSendQRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FixSendQRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FixSendQRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FixSendQRow}�I�u�W�F�N�g 
    */
    protected FixSendQDao( FixSendQRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FixSendQRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FixSendQRow getFixSendQRow() {
        return row;
    }


  /** 
   * �w���{@@link FixSendQRow}�I�u�W�F�N�g����{@@link FixSendQDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FixSendQRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FixSendQDao}�擾�̂��߂Ɏw���{@@link FixSendQRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FixSendQDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FixSendQDao forRow( FixSendQRow row ) throws java.lang.IllegalArgumentException {
        return (FixSendQDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FixSendQRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FixSendQRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FixSendQPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FixSendQParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FixSendQRow.TYPE );
    }


  /** 
   * {@@link FixSendQRow}����ӂɓ��肷��{@@link FixSendQPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FixSendQRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FixSendQParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FixSendQPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FixSendQPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FixSendQPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FixSendQRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FixSendQRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FixSendQRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        FixSendQPK pk = new FixSendQPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * �w���FixSendQPK�I�u�W�F�N�g����{@@link FixSendQRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FixSendQPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FixSendQRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FixSendQRow findRowByPk( FixSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FixSendQRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(FixSendQRow)}���g�p���Ă��������B 
   */
    public static FixSendQDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        FixSendQPK pk = new FixSendQPK( p_queueId );
        FixSendQRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FixSendQPK)}�����{@@link #forRow(FixSendQRow)}���g�p���Ă��������B 
   */
    public static FixSendQDao findDaoByPk( FixSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FixSendQRow row = findRowByPk( pk );
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
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_queueId, and �ɂĎw��̒l�����ӂ�{@@link FixSendQRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���p_queueId, and �̒l�ƈ�v����{@@link FixSendQRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FixSendQRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FixSendQRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FixSendQDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByQueueId(long)}�����{@@link #forRow(FixSendQRow)}���g�p���Ă��������B 
   */
    public static FixSendQDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_clOrdId, and �ɂĎw��̒l�Ɉ�v����{@@link FixSendQRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_clOrdId �����Ώۂł���p_clOrdId�t�B�[���h�̒l
   * 
   * @@return �����w���p_clOrdId, and �̒l�ƈ�v����{@@link FixSendQRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByClOrdId( String p_clOrdId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "cl_ord_id=?",
            null,
            new Object[] { p_clOrdId } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByClOrdId(String)}�����{@@link #forRow(FixSendQRow)}���g�p���Ă��������B 
   */
    public static List findDaosByClOrdId( String p_clOrdId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByClOrdId( p_clOrdId ) );
    }


  /** 
   * p_status, p_sessionId, p_productType, p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link FixSendQRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_sessionId �����Ώۂł���p_sessionId�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_sessionId, p_productType, p_orderRequestNumber, and �̒l�ƈ�v����{@@link FixSendQRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusSessionIdProductTypeOrderRequestNumber( String p_status, int p_sessionId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "status=? and session_id=? and product_type=? and order_request_number=?",
            null,
            new Object[] { p_status, new Integer(p_sessionId), p_productType, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusSessionIdProductTypeOrderRequestNumber(String, int, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String)}�����{@@link #forRow(FixSendQRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusSessionIdProductTypeOrderRequestNumber( String p_status, int p_sessionId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusSessionIdProductTypeOrderRequestNumber( p_status, p_sessionId, p_productType, p_orderRequestNumber ) );
    }


  /** 
   * p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link FixSendQRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderRequestNumber, and �̒l�ƈ�v����{@@link FixSendQRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FixSendQRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderRequestNumber(String)}�����{@@link #forRow(FixSendQRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }

}
@
