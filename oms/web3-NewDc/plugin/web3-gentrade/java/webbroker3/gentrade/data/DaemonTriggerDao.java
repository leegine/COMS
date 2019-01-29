head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DaemonTriggerDao.java;


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
 * {@@link DaemonTriggerDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DaemonTriggerRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DaemonTriggerPK 
 * @@see DaemonTriggerRow 
 */
public class DaemonTriggerDao extends DataAccessObject {


  /** 
   * ����{@@link DaemonTriggerDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DaemonTriggerRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DaemonTriggerRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DaemonTriggerDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DaemonTriggerDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DaemonTriggerRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DaemonTriggerRow )
                return new DaemonTriggerDao( (DaemonTriggerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DaemonTriggerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DaemonTriggerRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DaemonTriggerRow}�I�u�W�F�N�g 
    */
    protected DaemonTriggerDao( DaemonTriggerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DaemonTriggerRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DaemonTriggerRow getDaemonTriggerRow() {
        return row;
    }


  /** 
   * �w���{@@link DaemonTriggerRow}�I�u�W�F�N�g����{@@link DaemonTriggerDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DaemonTriggerRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DaemonTriggerDao}�擾�̂��߂Ɏw���{@@link DaemonTriggerRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DaemonTriggerDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DaemonTriggerDao forRow( DaemonTriggerRow row ) throws java.lang.IllegalArgumentException {
        return (DaemonTriggerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DaemonTriggerRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DaemonTriggerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DaemonTriggerPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DaemonTriggerParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DaemonTriggerRow.TYPE );
    }


  /** 
   * {@@link DaemonTriggerRow}����ӂɓ��肷��{@@link DaemonTriggerPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DaemonTriggerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DaemonTriggerParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DaemonTriggerPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DaemonTriggerPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new DaemonTriggerPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DaemonTriggerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_threadNo �����Ώۂł���p_threadNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DaemonTriggerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DaemonTriggerRow findRowByPk( long p_threadNo ) throws DataFindException, DataQueryException, DataNetworkException {
        DaemonTriggerPK pk = new DaemonTriggerPK( p_threadNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���DaemonTriggerPK�I�u�W�F�N�g����{@@link DaemonTriggerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DaemonTriggerPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DaemonTriggerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DaemonTriggerRow findRowByPk( DaemonTriggerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DaemonTriggerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(DaemonTriggerRow)}���g�p���Ă��������B 
   */
    public static DaemonTriggerDao findDaoByPk( long p_threadNo ) throws DataFindException, DataQueryException, DataNetworkException {
        DaemonTriggerPK pk = new DaemonTriggerPK( p_threadNo );
        DaemonTriggerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DaemonTriggerPK)}�����{@@link #forRow(DaemonTriggerRow)}���g�p���Ă��������B 
   */
    public static DaemonTriggerDao findDaoByPk( DaemonTriggerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DaemonTriggerRow row = findRowByPk( pk );
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
   * p_threadNo, and �ɂĎw��̒l�����ӂ�{@@link DaemonTriggerRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_threadNo �����Ώۂł���p_threadNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_threadNo, and �̒l�ƈ�v����{@@link DaemonTriggerRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DaemonTriggerRow findRowByThreadNo( long p_threadNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DaemonTriggerRow.TYPE,
            "thread_no=?",
            null,
            new Object[] { new Long(p_threadNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DaemonTriggerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DaemonTriggerDao.findRowsByThreadNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByThreadNo(long)}�����{@@link #forRow(DaemonTriggerRow)}���g�p���Ă��������B 
   */
    public static DaemonTriggerDao findDaoByThreadNo( long p_threadNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByThreadNo( p_threadNo ) );
    }


  /** 
   * p_triggerType, p_orderRequestNumber, p_rangeFrom, p_rangeTo, and �ɂĎw��̒l�����ӂ�{@@link DaemonTriggerRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_triggerType �����Ώۂł���p_triggerType�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * @@param p_rangeFrom �����Ώۂł���p_rangeFrom�t�B�[���h�̒l
   * @@param p_rangeTo �����Ώۂł���p_rangeTo�t�B�[���h�̒l
   * 
   * @@return �����w���p_triggerType, p_orderRequestNumber, p_rangeFrom, p_rangeTo, and �̒l�ƈ�v����{@@link DaemonTriggerRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DaemonTriggerRow findRowByTriggerTypeOrderRequestNumberRangeFromRangeTo( String p_triggerType, String p_orderRequestNumber, long p_rangeFrom, long p_rangeTo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DaemonTriggerRow.TYPE,
            "trigger_type=? and order_request_number=? and range_from=? and range_to=?",
            null,
            new Object[] { p_triggerType, p_orderRequestNumber, new Long(p_rangeFrom), new Long(p_rangeTo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DaemonTriggerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DaemonTriggerDao.findRowsByTriggerTypeOrderRequestNumberRangeFromRangeTo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTriggerTypeOrderRequestNumberRangeFromRangeTo(String, String, long, long)}�����{@@link #forRow(DaemonTriggerRow)}���g�p���Ă��������B 
   */
    public static DaemonTriggerDao findDaoByTriggerTypeOrderRequestNumberRangeFromRangeTo( String p_triggerType, String p_orderRequestNumber, long p_rangeFrom, long p_rangeTo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTriggerTypeOrderRequestNumberRangeFromRangeTo( p_triggerType, p_orderRequestNumber, p_rangeFrom, p_rangeTo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
