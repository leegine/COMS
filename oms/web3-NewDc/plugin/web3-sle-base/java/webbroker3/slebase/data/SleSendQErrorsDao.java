head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQErrorsDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.slebase.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * {@@link SleSendQErrorsDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SleSendQErrorsRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SleSendQErrorsPK 
 * @@see SleSendQErrorsRow 
 */
public class SleSendQErrorsDao extends DataAccessObject {


  /** 
   * ����{@@link SleSendQErrorsDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SleSendQErrorsRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SleSendQErrorsRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SleSendQErrorsDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SleSendQErrorsDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SleSendQErrorsRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleSendQErrorsRow )
                return new SleSendQErrorsDao( (SleSendQErrorsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleSendQErrorsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleSendQErrorsRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SleSendQErrorsRow}�I�u�W�F�N�g 
    */
    protected SleSendQErrorsDao( SleSendQErrorsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SleSendQErrorsRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SleSendQErrorsRow getSleSendQErrorsRow() {
        return row;
    }


  /** 
   * �w���{@@link SleSendQErrorsRow}�I�u�W�F�N�g����{@@link SleSendQErrorsDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SleSendQErrorsRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SleSendQErrorsDao}�擾�̂��߂Ɏw���{@@link SleSendQErrorsRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SleSendQErrorsDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SleSendQErrorsDao forRow( SleSendQErrorsRow row ) throws java.lang.IllegalArgumentException {
        return (SleSendQErrorsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleSendQErrorsRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SleSendQErrorsRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SleSendQErrorsPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SleSendQErrorsParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleSendQErrorsRow.TYPE );
    }


  /** 
   * {@@link SleSendQErrorsRow}����ӂɓ��肷��{@@link SleSendQErrorsPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SleSendQErrorsRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SleSendQErrorsParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SleSendQErrorsPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃R�������܂܂�Ă��邩�R�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SleSendQErrorsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleSendQErrorsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SleSendQErrorsRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleSendQErrorsRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleSendQErrorsRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQErrorsPK pk = new SleSendQErrorsPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SleSendQErrorsPK�I�u�W�F�N�g����{@@link SleSendQErrorsRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SleSendQErrorsPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleSendQErrorsRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleSendQErrorsRow findRowByPk( SleSendQErrorsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleSendQErrorsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static SleSendQErrorsDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQErrorsPK pk = new SleSendQErrorsPK( p_queueId );
        SleSendQErrorsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SleSendQErrorsPK)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static SleSendQErrorsDao findDaoByPk( SleSendQErrorsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQErrorsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link SleSendQErrorsDao}�ɕR�t��{@@link SleSendQErrorsRow}���ŊO���L�[�̊֌W������{@@link SleSendQRow}���������܂��B 
   * 
   * @@return {@@link SleSendQErrorsDao}�ƊO���L�[�̊֌W�ɂ���{@@link SleSendQRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public SleSendQRow fetchSleSendQRowViaQueueId() throws DataNetworkException, DataFindException, DataQueryException  {
        SleSendQPK pk = new SleSendQPK( row.getQueueId() );
        Row row = SleSendQDao.findRowByPk( pk );
        if ( row != null && !(row instanceof SleSendQRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (SleSendQRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchSleSendQRowViaQueueId()}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public SleSendQDao fetchSleSendQDaoViaQueueId() throws DataNetworkException, DataFindException, DataQueryException  {
        SleSendQPK pk = new SleSendQPK( row.getQueueId() );
        DataAccessObject dao = SleSendQDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof SleSendQDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (SleSendQDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for SleSendQ
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByQueueId(SleSendQRow)}���g�p���Ă��������B 
   */
    public static List findRowsByQueueId( SleSendQDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByQueueId( dao.getSleSendQRow() );
    }


  /** 
   * {@@link SleSendQRow}�ƊO���L�[�̊֌W�ɂ���{@@link SleSendQErrorsRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link SleSendQRow}�I�u�W�F�N�g 
   * @@return �w���{@@link SleSendQRow}�ɊO���L�[������{@@link SleSendQErrorsRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByQueueId( SleSendQRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByQueueId( row.getQueueId() );
    }


  /** 
   * {@@link SleSendQPK}�ƊO���L�[�̊֌W�ɂ���{@@link SleSendQErrorsRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link SleSendQPK}�I�u�W�F�N�g 
   * @@return {@@link SleSendQPK}�ƊO���L�[����v����l������{@@link SleSendQErrorsRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByQueueId( SleSendQPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByQueueId( pk.queue_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link SleSendQErrorsRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link SleSendQErrorsRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByQueueId( long p_queueId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQErrorsRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for SleSendQ
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByQueueId(SleSendQRow)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByQueueId( SleSendQDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByQueueId(SleSendQRow)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByQueueId( SleSendQRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByQueueId(SleSendQPK)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByQueueId( SleSendQPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( pk.queue_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByQueueId(long)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByQueueId( long p_queueId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByQueueId( p_queueId ) );
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
   * p_queueId, and �ɂĎw��̒l�����ӂ�{@@link SleSendQErrorsRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���p_queueId, and �̒l�ƈ�v����{@@link SleSendQErrorsRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SleSendQErrorsRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleSendQErrorsRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleSendQErrorsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleSendQErrorsDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByQueueId(long)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static SleSendQErrorsDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_openStatus, and �ɂĎw��̒l�Ɉ�v����{@@link SleSendQErrorsRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_openStatus �����Ώۂł���p_openStatus�t�B�[���h�̒l
   * 
   * @@return �����w���p_openStatus, and �̒l�ƈ�v����{@@link SleSendQErrorsRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOpenStatus( webbroker3.slebase.enums.SleOpenStatusEnum p_openStatus ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQErrorsRow.TYPE,
            "open_status=?",
            null,
            new Object[] { p_openStatus } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOpenStatus(webbroker3.slebase.enums.SleOpenStatusEnum)}�����{@@link #forRow(SleSendQErrorsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOpenStatus( webbroker3.slebase.enums.SleOpenStatusEnum p_openStatus ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOpenStatus( p_openStatus ) );
    }

}
@
