head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostCashTransOrderDao.java;


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
 * {@@link HostCashTransOrderDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostCashTransOrderRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostCashTransOrderPK 
 * @@see HostCashTransOrderRow 
 */
public class HostCashTransOrderDao extends DataAccessObject {


  /** 
   * ����{@@link HostCashTransOrderDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostCashTransOrderRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostCashTransOrderRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostCashTransOrderDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostCashTransOrderDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostCashTransOrderRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostCashTransOrderRow )
                return new HostCashTransOrderDao( (HostCashTransOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostCashTransOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostCashTransOrderRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostCashTransOrderRow}�I�u�W�F�N�g 
    */
    protected HostCashTransOrderDao( HostCashTransOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostCashTransOrderRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostCashTransOrderRow getHostCashTransOrderRow() {
        return row;
    }


  /** 
   * �w���{@@link HostCashTransOrderRow}�I�u�W�F�N�g����{@@link HostCashTransOrderDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostCashTransOrderRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostCashTransOrderDao}�擾�̂��߂Ɏw���{@@link HostCashTransOrderRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostCashTransOrderDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostCashTransOrderDao forRow( HostCashTransOrderRow row ) throws java.lang.IllegalArgumentException {
        return (HostCashTransOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostCashTransOrderRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostCashTransOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostCashTransOrderPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostCashTransOrderParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostCashTransOrderRow.TYPE );
    }


  /** 
   * {@@link HostCashTransOrderRow}����ӂɓ��肷��{@@link HostCashTransOrderPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostCashTransOrderRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostCashTransOrderParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostCashTransOrderPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostCashTransOrderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostCashTransOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostCashTransOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostCashTransOrderRow findRowByPk( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostCashTransOrderPK pk = new HostCashTransOrderPK( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostCashTransOrderPK�I�u�W�F�N�g����{@@link HostCashTransOrderRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostCashTransOrderPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostCashTransOrderRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostCashTransOrderRow findRowByPk( HostCashTransOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostCashTransOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(HostCashTransOrderRow)}���g�p���Ă��������B 
   */
    public static HostCashTransOrderDao findDaoByPk( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostCashTransOrderPK pk = new HostCashTransOrderPK( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        HostCashTransOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostCashTransOrderPK)}�����{@@link #forRow(HostCashTransOrderRow)}���g�p���Ă��������B 
   */
    public static HostCashTransOrderDao findDaoByPk( HostCashTransOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostCashTransOrderRow row = findRowByPk( pk );
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
   * p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and �ɂĎw��̒l�����ӂ�{@@link HostCashTransOrderRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostCashTransOrderRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static HostCashTransOrderRow findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostCashTransOrderRow.TYPE,
            "request_code=? and institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostCashTransOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostCashTransOrderDao.findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String, String)}�����{@@link #forRow(HostCashTransOrderRow)}���g�p���Ă��������B 
   */
    public static HostCashTransOrderDao findDaoByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
