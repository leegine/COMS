head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.26.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.qtp.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link QtpRichPushHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link QtpRichPushHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see QtpRichPushHistoryPK 
 * @@see QtpRichPushHistoryRow 
 */
public class QtpRichPushHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link QtpRichPushHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private QtpRichPushHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link QtpRichPushHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link QtpRichPushHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link QtpRichPushHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link QtpRichPushHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushHistoryRow )
                return new QtpRichPushHistoryDao( (QtpRichPushHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g 
    */
    protected QtpRichPushHistoryDao( QtpRichPushHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public QtpRichPushHistoryRow getQtpRichPushHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g����{@@link QtpRichPushHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link QtpRichPushHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link QtpRichPushHistoryDao}�擾�̂��߂Ɏw���{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link QtpRichPushHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static QtpRichPushHistoryDao forRow( QtpRichPushHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link QtpRichPushHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link QtpRichPushHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link QtpRichPushHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushHistoryRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushHistoryRow}����ӂɓ��肷��{@@link QtpRichPushHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link QtpRichPushHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link QtpRichPushHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link QtpRichPushHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static QtpRichPushHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QtpRichPushHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QtpRichPushHistoryRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryPK pk = new QtpRichPushHistoryPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * �w���QtpRichPushHistoryPK�I�u�W�F�N�g����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����QtpRichPushHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QtpRichPushHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QtpRichPushHistoryRow findRowByPk( QtpRichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(QtpRichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushHistoryDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryPK pk = new QtpRichPushHistoryPK( p_serlnum );
        QtpRichPushHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(QtpRichPushHistoryPK)}�����{@@link #forRow(QtpRichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushHistoryDao findDaoByPk( QtpRichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryRow row = findRowByPk( pk );
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
   * p_serlnum, and �ɂĎw��̒l�����ӂ�{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���p_serlnum, and �̒l�ƈ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static QtpRichPushHistoryRow findRowBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "serlnum=?",
            null,
            new Object[] { new Long(p_serlnum) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QtpRichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QtpRichPushHistoryDao.findRowsBySerlnum(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowBySerlnum(long)}�����{@@link #forRow(QtpRichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushHistoryDao findDaoBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySerlnum( p_serlnum ) );
    }


  /** 
   * p_tpk, p_type, and �ɂĎw��̒l�����ӂ�{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tpk �����Ώۂł���p_tpk�t�B�[���h�̒l
   * @@param p_type �����Ώۂł���p_type�t�B�[���h�̒l
   * 
   * @@return �����w���p_tpk, p_type, and �̒l�ƈ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static QtpRichPushHistoryRow findRowByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "tpk=? and type=?",
            null,
            new Object[] { p_tpk, p_type } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QtpRichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QtpRichPushHistoryDao.findRowsByTpkType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTpkType(String, String)}�����{@@link #forRow(QtpRichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushHistoryDao findDaoByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTpkType( p_tpk, p_type ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and �ɂĎw��̒l�Ɉ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_status, and �̒l�ƈ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdStatus(long, String)}�����{@@link #forRow(QtpRichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }


  /** 
   * p_status, p_procTimestamp, p_type, and �ɂĎw��̒l�Ɉ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_procTimestamp �����Ώۂł���p_procTimestamp�t�B�[���h�̒l
   * @@param p_type �����Ώۂł���p_type�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_procTimestamp, p_type, and �̒l�ƈ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "status=? and proc_timestamp=? and type=?",
            null,
            new Object[] { p_status, p_procTimestamp, p_type } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusProcTimestampType(String, java.sql.Timestamp, String)}�����{@@link #forRow(QtpRichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusProcTimestampType( p_status, p_procTimestamp, p_type ) );
    }


  /** 
   * p_type, p_createdTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_type �����Ώۂł���p_type�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_type, p_createdTimestamp, and �̒l�ƈ�v����{@@link QtpRichPushHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "type=? and created_timestamp=?",
            null,
            new Object[] { p_type, p_createdTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTypeCreatedTimestamp(String, java.sql.Timestamp)}�����{@@link #forRow(QtpRichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTypeCreatedTimestamp( p_type, p_createdTimestamp ) );
    }

}
@
