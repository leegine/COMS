head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EqtypeOrderExecSendMailDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link EqtypeOrderExecSendMailDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link EqtypeOrderExecSendMailRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see EqtypeOrderExecSendMailPK 
 * @@see EqtypeOrderExecSendMailRow 
 */
public class EqtypeOrderExecSendMailDao extends DataAccessObject {


  /** 
   * ����{@@link EqtypeOrderExecSendMailDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private EqtypeOrderExecSendMailRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link EqtypeOrderExecSendMailRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link EqtypeOrderExecSendMailDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link EqtypeOrderExecSendMailDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link EqtypeOrderExecSendMailRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeOrderExecSendMailRow )
                return new EqtypeOrderExecSendMailDao( (EqtypeOrderExecSendMailRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeOrderExecSendMailRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeOrderExecSendMailRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g 
    */
    protected EqtypeOrderExecSendMailDao( EqtypeOrderExecSendMailRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g���擾���܂��B
   */
    public EqtypeOrderExecSendMailRow getEqtypeOrderExecSendMailRow() {
        return row;
    }


  /** 
   * �w���{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g����{@@link EqtypeOrderExecSendMailDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link EqtypeOrderExecSendMailRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link EqtypeOrderExecSendMailDao}�擾�̂��߂Ɏw���{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link EqtypeOrderExecSendMailDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static EqtypeOrderExecSendMailDao forRow( EqtypeOrderExecSendMailRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeOrderExecSendMailDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeOrderExecSendMailRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link EqtypeOrderExecSendMailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link EqtypeOrderExecSendMailPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link EqtypeOrderExecSendMailParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeOrderExecSendMailRow.TYPE );
    }


  /** 
   * {@@link EqtypeOrderExecSendMailRow}����ӂɓ��肷��{@@link EqtypeOrderExecSendMailPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link EqtypeOrderExecSendMailRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link EqtypeOrderExecSendMailParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link EqtypeOrderExecSendMailPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static EqtypeOrderExecSendMailPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * @@param p_orderActionId �����Ώۂł���p_orderActionId�t�B�[���h�̒l
   * @@param p_orderExecStatus �����Ώۂł���p_orderExecStatus�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EqtypeOrderExecSendMailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EqtypeOrderExecSendMailRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber, long p_orderActionId, String p_orderExecStatus ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecSendMailPK pk = new EqtypeOrderExecSendMailPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, p_orderActionId, p_orderExecStatus );
        return findRowByPk( pk );
    }


  /** 
   * �w���EqtypeOrderExecSendMailPK�I�u�W�F�N�g����{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����EqtypeOrderExecSendMailPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EqtypeOrderExecSendMailRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EqtypeOrderExecSendMailRow findRowByPk( EqtypeOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeOrderExecSendMailRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,long,String)}�����{@@link #forRow(EqtypeOrderExecSendMailRow)}���g�p���Ă��������B 
   */
    public static EqtypeOrderExecSendMailDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber, long p_orderActionId, String p_orderExecStatus ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecSendMailPK pk = new EqtypeOrderExecSendMailPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, p_orderActionId, p_orderExecStatus );
        EqtypeOrderExecSendMailRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(EqtypeOrderExecSendMailPK)}�����{@@link #forRow(EqtypeOrderExecSendMailRow)}���g�p���Ă��������B 
   */
    public static EqtypeOrderExecSendMailDao findDaoByPk( EqtypeOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecSendMailRow row = findRowByPk( pk );
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
   * p_status, and �ɂĎw��̒l�Ɉ�v����{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, and �̒l�ƈ�v����{@@link EqtypeOrderExecSendMailRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecSendMailRow.TYPE,
            "status=?",
            null,
            new Object[] { p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatus(String)}�����{@@link #forRow(EqtypeOrderExecSendMailRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatus( p_status ) );
    }

}
@
