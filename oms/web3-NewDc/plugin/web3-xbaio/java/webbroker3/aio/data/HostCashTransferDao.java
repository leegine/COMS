head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.54.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostCashTransferDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link HostCashTransferDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostCashTransferRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostCashTransferPK 
 * @@see HostCashTransferRow 
 */
public class HostCashTransferDao extends DataAccessObject {


  /** 
   * ����{@@link HostCashTransferDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostCashTransferRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostCashTransferRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostCashTransferDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostCashTransferDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostCashTransferRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostCashTransferRow )
                return new HostCashTransferDao( (HostCashTransferRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostCashTransferRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostCashTransferRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostCashTransferRow}�I�u�W�F�N�g 
    */
    protected HostCashTransferDao( HostCashTransferRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostCashTransferRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostCashTransferRow getHostCashTransferRow() {
        return row;
    }


  /** 
   * �w���{@@link HostCashTransferRow}�I�u�W�F�N�g����{@@link HostCashTransferDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostCashTransferRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostCashTransferDao}�擾�̂��߂Ɏw���{@@link HostCashTransferRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostCashTransferDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostCashTransferDao forRow( HostCashTransferRow row ) throws java.lang.IllegalArgumentException {
        return (HostCashTransferDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostCashTransferRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostCashTransferRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostCashTransferPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostCashTransferParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostCashTransferRow.TYPE );
    }


  /** 
   * {@@link HostCashTransferRow}����ӂɓ��肷��{@@link HostCashTransferPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostCashTransferRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostCashTransferParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostCashTransferPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostCashTransferPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostCashTransferRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostCashTransferRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostCashTransferRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostCashTransferPK pk = new HostCashTransferPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostCashTransferPK�I�u�W�F�N�g����{@@link HostCashTransferRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostCashTransferPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostCashTransferRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostCashTransferRow findRowByPk( HostCashTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostCashTransferRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostCashTransferRow)}���g�p���Ă��������B 
   */
    public static HostCashTransferDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostCashTransferPK pk = new HostCashTransferPK( p_rowid );
        HostCashTransferRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostCashTransferPK)}�����{@@link #forRow(HostCashTransferRow)}���g�p���Ă��������B 
   */
    public static HostCashTransferDao findDaoByPk( HostCashTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostCashTransferRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_requestCode, p_status, and �ɂĎw��̒l�Ɉ�v����{@@link HostCashTransferRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_requestCode, p_status, and �̒l�ƈ�v����{@@link HostCashTransferRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostCashTransferRow.TYPE,
            "request_code=? and status=?",
            null,
            new Object[] { p_requestCode, p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRequestCodeStatus(String, String)}�����{@@link #forRow(HostCashTransferRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeStatus( p_requestCode, p_status ) );
    }

}
@