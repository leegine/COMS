head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQDao.java;


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
 * {@@link SleSendQDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SleSendQRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SleSendQPK 
 * @@see SleSendQRow 
 */
public class SleSendQDao extends DataAccessObject {


  /** 
   * ����{@@link SleSendQDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SleSendQRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SleSendQRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SleSendQDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SleSendQDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SleSendQRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleSendQRow )
                return new SleSendQDao( (SleSendQRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleSendQRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleSendQRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SleSendQRow}�I�u�W�F�N�g 
    */
    protected SleSendQDao( SleSendQRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SleSendQRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SleSendQRow getSleSendQRow() {
        return row;
    }


  /** 
   * �w���{@@link SleSendQRow}�I�u�W�F�N�g����{@@link SleSendQDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SleSendQRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SleSendQDao}�擾�̂��߂Ɏw���{@@link SleSendQRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SleSendQDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SleSendQDao forRow( SleSendQRow row ) throws java.lang.IllegalArgumentException {
        return (SleSendQDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleSendQRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SleSendQRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SleSendQPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SleSendQParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleSendQRow.TYPE );
    }


  /** 
   * {@@link SleSendQRow}����ӂɓ��肷��{@@link SleSendQPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SleSendQRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SleSendQParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SleSendQPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃R�������܂܂�Ă��邩�R�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SleSendQPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleSendQPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SleSendQRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleSendQRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleSendQRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQPK pk = new SleSendQPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SleSendQPK�I�u�W�F�N�g����{@@link SleSendQRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SleSendQPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleSendQRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleSendQRow findRowByPk( SleSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleSendQRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(SleSendQRow)}���g�p���Ă��������B 
   */
    public static SleSendQDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQPK pk = new SleSendQPK( p_queueId );
        SleSendQRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SleSendQPK)}�����{@@link #forRow(SleSendQRow)}���g�p���Ă��������B 
   */
    public static SleSendQDao findDaoByPk( SleSendQPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleSendQRow row = findRowByPk( pk );
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
   * ����{@@link SleSendQDao}�Ɋ֘A����{@@link SleSendQRow}�̊O���L�[������{@@link SleSendQErrorsRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link SleSendQDao}�Ɋ֘A����{@@link SleSendQRow}�̊O���L�[������{@@link SleSendQErrorsRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchSleSendQErrorsRowsByQueueId() throws DataNetworkException, DataQueryException  {
        return SleSendQErrorsDao.findRowsByQueueId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchSleSendQErrorsRowsByQueueId()}�����{@@link #forRow(SleSendQRow)}���g�p���Ă��������B 
   */
    public List fetchSleSendQErrorsDaosByQueueId() throws DataNetworkException, DataQueryException  {
        return SleSendQErrorsDao.findDaosByQueueId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchSleSendQErrorsRowsByQueueId()}�����{@@link #forRow(SleSendQRow)}���g�p���Ă��������B 
   */
    public List fetchSleSendQErrorsDaosQueueId() throws DataNetworkException, DataQueryException  {
        return fetchSleSendQErrorsDaosByQueueId();
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
   * p_queueId, and �ɂĎw��̒l�����ӂ�{@@link SleSendQRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���p_queueId, and �̒l�ƈ�v����{@@link SleSendQRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SleSendQRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleSendQRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleSendQRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleSendQDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByQueueId(long)}�����{@@link #forRow(SleSendQRow)}���g�p���Ă��������B 
   */
    public static SleSendQDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_status, p_accountId, p_bizDate, and �ɂĎw��̒l�Ɉ�v����{@@link SleSendQRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_accountId, p_bizDate, and �̒l�ƈ�v����{@@link SleSendQRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusAccountIdBizDate( webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, long p_accountId, String p_bizDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQRow.TYPE,
            "status=? and account_id=? and biz_date=?",
            null,
            new Object[] { p_status, new Long(p_accountId), p_bizDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusAccountIdBizDate(webbroker3.slebase.enums.SleSendqProcStatusEnum, long, String)}�����{@@link #forRow(SleSendQRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusAccountIdBizDate( webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, long p_accountId, String p_bizDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusAccountIdBizDate( p_status, p_accountId, p_bizDate ) );
    }


  /** 
   * p_productCode, p_accountId, p_status, p_createdTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link SleSendQRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_productCode, p_accountId, p_status, p_createdTimestamp, and �̒l�ƈ�v����{@@link SleSendQRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductCodeAccountIdStatusCreatedTimestamp( String p_productCode, long p_accountId, webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleSendQRow.TYPE,
            "product_code=? and account_id=? and status=? and created_timestamp=?",
            null,
            new Object[] { p_productCode, new Long(p_accountId), p_status, p_createdTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductCodeAccountIdStatusCreatedTimestamp(String, long, webbroker3.slebase.enums.SleSendqProcStatusEnum, java.sql.Timestamp)}�����{@@link #forRow(SleSendQRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductCodeAccountIdStatusCreatedTimestamp( String p_productCode, long p_accountId, webbroker3.slebase.enums.SleSendqProcStatusEnum p_status, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductCodeAccountIdStatusCreatedTimestamp( p_productCode, p_accountId, p_status, p_createdTimestamp ) );
    }

}
@
