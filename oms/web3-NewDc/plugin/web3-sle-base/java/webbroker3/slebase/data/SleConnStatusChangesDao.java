head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnStatusChangesDao.java;


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
 * {@@link SleConnStatusChangesDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SleConnStatusChangesRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SleConnStatusChangesPK 
 * @@see SleConnStatusChangesRow 
 */
public class SleConnStatusChangesDao extends DataAccessObject {


  /** 
   * ����{@@link SleConnStatusChangesDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SleConnStatusChangesRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SleConnStatusChangesRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SleConnStatusChangesDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SleConnStatusChangesDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SleConnStatusChangesRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SleConnStatusChangesRow )
                return new SleConnStatusChangesDao( (SleConnStatusChangesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SleConnStatusChangesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SleConnStatusChangesRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SleConnStatusChangesRow}�I�u�W�F�N�g 
    */
    protected SleConnStatusChangesDao( SleConnStatusChangesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SleConnStatusChangesRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SleConnStatusChangesRow getSleConnStatusChangesRow() {
        return row;
    }


  /** 
   * �w���{@@link SleConnStatusChangesRow}�I�u�W�F�N�g����{@@link SleConnStatusChangesDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SleConnStatusChangesRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SleConnStatusChangesDao}�擾�̂��߂Ɏw���{@@link SleConnStatusChangesRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SleConnStatusChangesDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SleConnStatusChangesDao forRow( SleConnStatusChangesRow row ) throws java.lang.IllegalArgumentException {
        return (SleConnStatusChangesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SleConnStatusChangesRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SleConnStatusChangesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SleConnStatusChangesPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SleConnStatusChangesParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SleConnStatusChangesRow.TYPE );
    }


  /** 
   * {@@link SleConnStatusChangesRow}����ӂɓ��肷��{@@link SleConnStatusChangesPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SleConnStatusChangesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SleConnStatusChangesParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SleConnStatusChangesPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃R�������܂܂�Ă��邩�R�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SleConnStatusChangesPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SleConnStatusChangesPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_queId �����Ώۂł���p_queId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleConnStatusChangesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleConnStatusChangesRow findRowByPk( long p_queId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleConnStatusChangesPK pk = new SleConnStatusChangesPK( p_queId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SleConnStatusChangesPK�I�u�W�F�N�g����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SleConnStatusChangesPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SleConnStatusChangesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SleConnStatusChangesRow findRowByPk( SleConnStatusChangesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SleConnStatusChangesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(SleConnStatusChangesRow)}���g�p���Ă��������B 
   */
    public static SleConnStatusChangesDao findDaoByPk( long p_queId ) throws DataFindException, DataQueryException, DataNetworkException {
        SleConnStatusChangesPK pk = new SleConnStatusChangesPK( p_queId );
        SleConnStatusChangesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SleConnStatusChangesPK)}�����{@@link #forRow(SleConnStatusChangesRow)}���g�p���Ă��������B 
   */
    public static SleConnStatusChangesDao findDaoByPk( SleConnStatusChangesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SleConnStatusChangesRow row = findRowByPk( pk );
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
   * p_queId, and �ɂĎw��̒l�����ӂ�{@@link SleConnStatusChangesRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_queId �����Ώۂł���p_queId�t�B�[���h�̒l
   * 
   * @@return �����w���p_queId, and �̒l�ƈ�v����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SleConnStatusChangesRow findRowByQueId( long p_queId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SleConnStatusChangesRow.TYPE,
            "que_id=?",
            null,
            new Object[] { new Long(p_queId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SleConnStatusChangesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SleConnStatusChangesDao.findRowsByQueId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByQueId(long)}�����{@@link #forRow(SleConnStatusChangesRow)}���g�p���Ă��������B 
   */
    public static SleConnStatusChangesDao findDaoByQueId( long p_queId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByQueId( p_queId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_marketCode, p_sleConnDiv, and �ɂĎw��̒l�Ɉ�v����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_sleConnDiv �����Ώۂł���p_sleConnDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_marketCode, p_sleConnDiv, and �̒l�ƈ�v����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMarketCodeSleConnDiv( String p_marketCode, int p_sleConnDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleConnStatusChangesRow.TYPE,
            "market_code=? and sle_conn_div=?",
            null,
            new Object[] { p_marketCode, new Integer(p_sleConnDiv) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMarketCodeSleConnDiv(String, int)}�����{@@link #forRow(SleConnStatusChangesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMarketCodeSleConnDiv( String p_marketCode, int p_sleConnDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMarketCodeSleConnDiv( p_marketCode, p_sleConnDiv ) );
    }


  /** 
   * p_eventAckedDiv, and �ɂĎw��̒l�Ɉ�v����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_eventAckedDiv �����Ώۂł���p_eventAckedDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_eventAckedDiv, and �̒l�ƈ�v����{@@link SleConnStatusChangesRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByEventAckedDiv( int p_eventAckedDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SleConnStatusChangesRow.TYPE,
            "event_acked_div=?",
            null,
            new Object[] { new Integer(p_eventAckedDiv) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByEventAckedDiv(int)}�����{@@link #forRow(SleConnStatusChangesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByEventAckedDiv( int p_eventAckedDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByEventAckedDiv( p_eventAckedDiv ) );
    }

}
@
