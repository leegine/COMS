head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.41.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostTransferOrderDao.java;


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
 * {@@link HostTransferOrderDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostTransferOrderRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostTransferOrderPK 
 * @@see HostTransferOrderRow 
 */
public class HostTransferOrderDao extends DataAccessObject {


  /** 
   * ����{@@link HostTransferOrderDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostTransferOrderRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostTransferOrderRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostTransferOrderDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostTransferOrderDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostTransferOrderRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostTransferOrderRow )
                return new HostTransferOrderDao( (HostTransferOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostTransferOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostTransferOrderRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostTransferOrderRow}�I�u�W�F�N�g 
    */
    protected HostTransferOrderDao( HostTransferOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostTransferOrderRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostTransferOrderRow getHostTransferOrderRow() {
        return row;
    }


  /** 
   * �w���{@@link HostTransferOrderRow}�I�u�W�F�N�g����{@@link HostTransferOrderDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostTransferOrderRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostTransferOrderDao}�擾�̂��߂Ɏw���{@@link HostTransferOrderRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostTransferOrderDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostTransferOrderDao forRow( HostTransferOrderRow row ) throws java.lang.IllegalArgumentException {
        return (HostTransferOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostTransferOrderRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostTransferOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostTransferOrderPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostTransferOrderParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostTransferOrderRow.TYPE );
    }


  /** 
   * {@@link HostTransferOrderRow}����ӂɓ��肷��{@@link HostTransferOrderPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostTransferOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostTransferOrderParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostTransferOrderPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostTransferOrderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostTransferOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostTransferOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostTransferOrderRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferOrderPK pk = new HostTransferOrderPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostTransferOrderPK�I�u�W�F�N�g����{@@link HostTransferOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostTransferOrderPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostTransferOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostTransferOrderRow findRowByPk( HostTransferOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostTransferOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(HostTransferOrderRow)}���g�p���Ă��������B 
   */
    public static HostTransferOrderDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferOrderPK pk = new HostTransferOrderPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        HostTransferOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostTransferOrderPK)}�����{@@link #forRow(HostTransferOrderRow)}���g�p���Ă��������B 
   */
    public static HostTransferOrderDao findDaoByPk( HostTransferOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostTransferOrderRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and �ɂĎw��̒l�����ӂ�{@@link HostTransferOrderRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostTransferOrderRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static HostTransferOrderRow findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostTransferOrderRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostTransferOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostTransferOrderDao.findRowsByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String)}�����{@@link #forRow(HostTransferOrderRow)}���g�p���Ă��������B 
   */
    public static HostTransferOrderDao findDaoByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@