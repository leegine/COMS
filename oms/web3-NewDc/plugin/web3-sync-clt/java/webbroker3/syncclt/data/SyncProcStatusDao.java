head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.26.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d7db4ef1a89;
filename	SyncProcStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.syncclt.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.syncclt.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link SyncProcStatusDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SyncProcStatusRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SyncProcStatusPK 
 * @@see SyncProcStatusRow 
 */
public class SyncProcStatusDao extends DataAccessObject {


  /** 
   * ����{@@link SyncProcStatusDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SyncProcStatusRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SyncProcStatusRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SyncProcStatusDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SyncProcStatusDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SyncProcStatusRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SyncProcStatusRow )
                return new SyncProcStatusDao( (SyncProcStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SyncProcStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SyncProcStatusRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SyncProcStatusRow}�I�u�W�F�N�g 
    */
    protected SyncProcStatusDao( SyncProcStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SyncProcStatusRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SyncProcStatusRow getSyncProcStatusRow() {
        return row;
    }


  /** 
   * �w���{@@link SyncProcStatusRow}�I�u�W�F�N�g����{@@link SyncProcStatusDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SyncProcStatusRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SyncProcStatusDao}�擾�̂��߂Ɏw���{@@link SyncProcStatusRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SyncProcStatusDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SyncProcStatusDao forRow( SyncProcStatusRow row ) throws java.lang.IllegalArgumentException {
        return (SyncProcStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SyncProcStatusRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SyncProcStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SyncProcStatusPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SyncProcStatusParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SyncProcStatusRow.TYPE );
    }


  /** 
   * {@@link SyncProcStatusRow}����ӂɓ��肷��{@@link SyncProcStatusPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SyncProcStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SyncProcStatusParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SyncProcStatusPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SyncProcStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SyncProcStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_serviceName �����Ώۂł���p_serviceName�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SyncProcStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SyncProcStatusRow findRowByPk( String p_serviceName ) throws DataFindException, DataQueryException, DataNetworkException {
        SyncProcStatusPK pk = new SyncProcStatusPK( p_serviceName );
        return findRowByPk( pk );
    }


  /** 
   * �w���SyncProcStatusPK�I�u�W�F�N�g����{@@link SyncProcStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SyncProcStatusPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SyncProcStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SyncProcStatusRow findRowByPk( SyncProcStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SyncProcStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(SyncProcStatusRow)}���g�p���Ă��������B 
   */
    public static SyncProcStatusDao findDaoByPk( String p_serviceName ) throws DataFindException, DataQueryException, DataNetworkException {
        SyncProcStatusPK pk = new SyncProcStatusPK( p_serviceName );
        SyncProcStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SyncProcStatusPK)}�����{@@link #forRow(SyncProcStatusRow)}���g�p���Ă��������B 
   */
    public static SyncProcStatusDao findDaoByPk( SyncProcStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SyncProcStatusRow row = findRowByPk( pk );
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
   * p_serviceName, and �ɂĎw��̒l�����ӂ�{@@link SyncProcStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_serviceName �����Ώۂł���p_serviceName�t�B�[���h�̒l
   * 
   * @@return �����w���p_serviceName, and �̒l�ƈ�v����{@@link SyncProcStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SyncProcStatusRow findRowByServiceName( String p_serviceName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SyncProcStatusRow.TYPE,
            "service_name=?",
            null,
            new Object[] { p_serviceName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SyncProcStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SyncProcStatusDao.findRowsByServiceName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByServiceName(String)}�����{@@link #forRow(SyncProcStatusRow)}���g�p���Ă��������B 
   */
    public static SyncProcStatusDao findDaoByServiceName( String p_serviceName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByServiceName( p_serviceName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
