head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleRcvdQDao.java;


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
 * {@@link SleRcvdQDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SleRcvdQRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SleRcvdQPK 
 * @@see SleRcvdQRow 
 */
public class SleRcvdQDao extends DataAccessObject {


  /** 
   * ����{@@link SleRcvdQDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SleRcvdQRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SleRcvdQRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SleRcvdQDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SleRcvdQDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SleRcvdQRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleRcvdQRow )
                return new SleRcvdQDao( (SleRcvdQRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleRcvdQRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleRcvdQRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SleRcvdQRow}�I�u�W�F�N�g 
    */
    protected SleRcvdQDao( SleRcvdQRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SleRcvdQRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SleRcvdQRow getSleRcvdQRow() {
        return row;
    }


  /** 
   * �w���{@@link SleRcvdQRow}�I�u�W�F�N�g����{@@link SleRcvdQDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SleRcvdQRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SleRcvdQDao}�擾�̂��߂Ɏw���{@@link SleRcvdQRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SleRcvdQDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SleRcvdQDao forRow( SleRcvdQRow row ) throws java.lang.IllegalArgumentException {
        return (SleRcvdQDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleRcvdQRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SleRcvdQRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SleRcvdQPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SleRcvdQParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleRcvdQRow.TYPE );
    }


  /** 
   * {@@link SleRcvdQRow}����ӂɓ��肷��{@@link SleRcvdQPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SleRcvdQRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SleRcvdQParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SleRcvdQPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃R�������܂܂�Ă��邩�R�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SleRcvdQPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleRcvdQPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SleRcvdQRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleRcvdQRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleRcvdQRow findRowByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleRcvdQPK pk = new SleRcvdQPK( p_queueId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SleRcvdQPK�I�u�W�F�N�g����{@@link SleRcvdQRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SleRcvdQPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleRcvdQRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleRcvdQRow findRowByPk( SleRcvdQPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleRcvdQRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(SleRcvdQRow)}���g�p���Ă��������B 
   */
    public static SleRcvdQDao findDaoByPk( long p_queueId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleRcvdQPK pk = new SleRcvdQPK( p_queueId );
        SleRcvdQRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SleRcvdQPK)}�����{@@link #forRow(SleRcvdQRow)}���g�p���Ă��������B 
   */
    public static SleRcvdQDao findDaoByPk( SleRcvdQPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleRcvdQRow row = findRowByPk( pk );
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
   * p_queueId, and �ɂĎw��̒l�����ӂ�{@@link SleRcvdQRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_queueId �����Ώۂł���p_queueId�t�B�[���h�̒l
   * 
   * @@return �����w���p_queueId, and �̒l�ƈ�v����{@@link SleRcvdQRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SleRcvdQRow findRowByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleRcvdQRow.TYPE,
            "queue_id=?",
            null,
            new Object[] { new Long(p_queueId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleRcvdQRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleRcvdQDao.findRowsByQueueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByQueueId(long)}�����{@@link #forRow(SleRcvdQRow)}���g�p���Ă��������B 
   */
    public static SleRcvdQDao findDaoByQueueId( long p_queueId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueueId( p_queueId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and �ɂĎw��̒l�Ɉ�v����{@@link SleRcvdQRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_status, and �̒l�ƈ�v����{@@link SleRcvdQRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, webbroker3.slebase.enums.SleRcvdqProcStatusEnum p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleRcvdQRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdStatus(long, webbroker3.slebase.enums.SleRcvdqProcStatusEnum)}�����{@@link #forRow(SleRcvdQRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, webbroker3.slebase.enums.SleRcvdqProcStatusEnum p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }

}
@
