head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoOrderExecSendMailDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link IfoOrderExecSendMailDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoOrderExecSendMailRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoOrderExecSendMailPK 
 * @@see IfoOrderExecSendMailRow 
 */
public class IfoOrderExecSendMailDao extends DataAccessObject {


  /** 
   * ����{@@link IfoOrderExecSendMailDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoOrderExecSendMailRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoOrderExecSendMailRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoOrderExecSendMailDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoOrderExecSendMailDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoOrderExecSendMailRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoOrderExecSendMailRow )
                return new IfoOrderExecSendMailDao( (IfoOrderExecSendMailRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoOrderExecSendMailRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoOrderExecSendMailRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g 
    */
    protected IfoOrderExecSendMailDao( IfoOrderExecSendMailRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoOrderExecSendMailRow getIfoOrderExecSendMailRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g����{@@link IfoOrderExecSendMailDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoOrderExecSendMailRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoOrderExecSendMailDao}�擾�̂��߂Ɏw���{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoOrderExecSendMailDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoOrderExecSendMailDao forRow( IfoOrderExecSendMailRow row ) throws java.lang.IllegalArgumentException {
        return (IfoOrderExecSendMailDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoOrderExecSendMailRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoOrderExecSendMailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoOrderExecSendMailPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoOrderExecSendMailParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoOrderExecSendMailRow.TYPE );
    }


  /** 
   * {@@link IfoOrderExecSendMailRow}����ӂɓ��肷��{@@link IfoOrderExecSendMailPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoOrderExecSendMailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoOrderExecSendMailParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoOrderExecSendMailPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoOrderExecSendMailPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * @@param p_orderExecStatus �����Ώۂł���p_orderExecStatus�t�B�[���h�̒l
   * @@param p_orderActionId �����Ώۂł���p_orderActionId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoOrderExecSendMailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoOrderExecSendMailRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber, String p_orderExecStatus, long p_orderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderExecSendMailPK pk = new IfoOrderExecSendMailPK( p_institutionCode, p_branchCode, p_orderRequestNumber, p_orderExecStatus, p_orderActionId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoOrderExecSendMailPK�I�u�W�F�N�g����{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoOrderExecSendMailPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoOrderExecSendMailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoOrderExecSendMailRow findRowByPk( IfoOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoOrderExecSendMailRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,long)}�����{@@link #forRow(IfoOrderExecSendMailRow)}���g�p���Ă��������B 
   */
    public static IfoOrderExecSendMailDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber, String p_orderExecStatus, long p_orderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderExecSendMailPK pk = new IfoOrderExecSendMailPK( p_institutionCode, p_branchCode, p_orderRequestNumber, p_orderExecStatus, p_orderActionId );
        IfoOrderExecSendMailRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoOrderExecSendMailPK)}�����{@@link #forRow(IfoOrderExecSendMailRow)}���g�p���Ă��������B 
   */
    public static IfoOrderExecSendMailDao findDaoByPk( IfoOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderExecSendMailRow row = findRowByPk( pk );
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
   * p_status, and �ɂĎw��̒l�Ɉ�v����{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, and �̒l�ƈ�v����{@@link IfoOrderExecSendMailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoOrderExecSendMailRow.TYPE,
            "status=?",
            null,
            new Object[] { p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatus(String)}�����{@@link #forRow(IfoOrderExecSendMailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatus( p_status ) );
    }

}
@
