head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RichPushHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RichPushHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RichPushHistoryPK 
 * @@see RichPushHistoryRow 
 */
public class RichPushHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link RichPushHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RichPushHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RichPushHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RichPushHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RichPushHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RichPushHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RichPushHistoryRow )
                return new RichPushHistoryDao( (RichPushHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RichPushHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RichPushHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RichPushHistoryRow}�I�u�W�F�N�g 
    */
    protected RichPushHistoryDao( RichPushHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RichPushHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RichPushHistoryRow getRichPushHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link RichPushHistoryRow}�I�u�W�F�N�g����{@@link RichPushHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RichPushHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RichPushHistoryDao}�擾�̂��߂Ɏw���{@@link RichPushHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RichPushHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RichPushHistoryDao forRow( RichPushHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (RichPushHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RichPushHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RichPushHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RichPushHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RichPushHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RichPushHistoryRow.TYPE );
    }


  /** 
   * {@@link RichPushHistoryRow}����ӂɓ��肷��{@@link RichPushHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RichPushHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RichPushHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RichPushHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RichPushHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RichPushHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RichPushHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RichPushHistoryRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryPK pk = new RichPushHistoryPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * �w���RichPushHistoryPK�I�u�W�F�N�g����{@@link RichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RichPushHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RichPushHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RichPushHistoryRow findRowByPk( RichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RichPushHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static RichPushHistoryDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryPK pk = new RichPushHistoryPK( p_serlnum );
        RichPushHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RichPushHistoryPK)}�����{@@link #forRow(RichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static RichPushHistoryDao findDaoByPk( RichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryRow row = findRowByPk( pk );
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
   * p_serlnum, and �ɂĎw��̒l�����ӂ�{@@link RichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���p_serlnum, and �̒l�ƈ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RichPushHistoryRow findRowBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "serlnum=?",
            null,
            new Object[] { new Long(p_serlnum) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RichPushHistoryDao.findRowsBySerlnum(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowBySerlnum(long)}�����{@@link #forRow(RichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static RichPushHistoryDao findDaoBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySerlnum( p_serlnum ) );
    }


  /** 
   * p_tpk, p_type, and �ɂĎw��̒l�����ӂ�{@@link RichPushHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tpk �����Ώۂł���p_tpk�t�B�[���h�̒l
   * @@param p_type �����Ώۂł���p_type�t�B�[���h�̒l
   * 
   * @@return �����w���p_tpk, p_type, and �̒l�ƈ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RichPushHistoryRow findRowByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "tpk=? and type=?",
            null,
            new Object[] { p_tpk, p_type } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RichPushHistoryDao.findRowsByTpkType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTpkType(String, String)}�����{@@link #forRow(RichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static RichPushHistoryDao findDaoByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTpkType( p_tpk, p_type ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and �ɂĎw��̒l�Ɉ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_status, and �̒l�ƈ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdStatus(long, String)}�����{@@link #forRow(RichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }


  /** 
   * p_status, p_procTimestamp, p_type, and �ɂĎw��̒l�Ɉ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_procTimestamp �����Ώۂł���p_procTimestamp�t�B�[���h�̒l
   * @@param p_type �����Ώۂł���p_type�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_procTimestamp, p_type, and �̒l�ƈ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "status=? and proc_timestamp=? and type=?",
            null,
            new Object[] { p_status, p_procTimestamp, p_type } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusProcTimestampType(String, java.sql.Timestamp, String)}�����{@@link #forRow(RichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusProcTimestampType( p_status, p_procTimestamp, p_type ) );
    }


  /** 
   * p_type, p_createdTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_type �����Ώۂł���p_type�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_type, p_createdTimestamp, and �̒l�ƈ�v����{@@link RichPushHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "type=? and created_timestamp=?",
            null,
            new Object[] { p_type, p_createdTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTypeCreatedTimestamp(String, java.sql.Timestamp)}�����{@@link #forRow(RichPushHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTypeCreatedTimestamp( p_type, p_createdTimestamp ) );
    }

}
@
