head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqOrderChangeStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;

/** 
 * {@@link FeqOrderChangeStatusDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FeqOrderChangeStatusRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FeqOrderChangeStatusPK 
 * @@see FeqOrderChangeStatusRow 
 */
public class FeqOrderChangeStatusDao extends DataAccessObject {


  /** 
   * ����{@@link FeqOrderChangeStatusDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FeqOrderChangeStatusRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FeqOrderChangeStatusRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FeqOrderChangeStatusDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FeqOrderChangeStatusDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FeqOrderChangeStatusRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqOrderChangeStatusRow )
                return new FeqOrderChangeStatusDao( (FeqOrderChangeStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqOrderChangeStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqOrderChangeStatusRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g 
    */
    protected FeqOrderChangeStatusDao( FeqOrderChangeStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FeqOrderChangeStatusRow getFeqOrderChangeStatusRow() {
        return row;
    }


  /** 
   * �w���{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g����{@@link FeqOrderChangeStatusDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FeqOrderChangeStatusRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FeqOrderChangeStatusDao}�擾�̂��߂Ɏw���{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FeqOrderChangeStatusDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FeqOrderChangeStatusDao forRow( FeqOrderChangeStatusRow row ) throws java.lang.IllegalArgumentException {
        return (FeqOrderChangeStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqOrderChangeStatusRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FeqOrderChangeStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FeqOrderChangeStatusPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FeqOrderChangeStatusParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqOrderChangeStatusRow.TYPE );
    }


  /** 
   * {@@link FeqOrderChangeStatusRow}����ӂɓ��肷��{@@link FeqOrderChangeStatusPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FeqOrderChangeStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FeqOrderChangeStatusParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FeqOrderChangeStatusPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FeqOrderChangeStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_originalOrderId �����Ώۂł���p_originalOrderId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqOrderChangeStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqOrderChangeStatusRow findRowByPk( long p_accountId, long p_originalOrderId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderChangeStatusPK pk = new FeqOrderChangeStatusPK( p_accountId, p_originalOrderId );
        return findRowByPk( pk );
    }


  /** 
   * �w���FeqOrderChangeStatusPK�I�u�W�F�N�g����{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FeqOrderChangeStatusPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqOrderChangeStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqOrderChangeStatusRow findRowByPk( FeqOrderChangeStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqOrderChangeStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,long)}�����{@@link #forRow(FeqOrderChangeStatusRow)}���g�p���Ă��������B 
   */
    public static FeqOrderChangeStatusDao findDaoByPk( long p_accountId, long p_originalOrderId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderChangeStatusPK pk = new FeqOrderChangeStatusPK( p_accountId, p_originalOrderId );
        FeqOrderChangeStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FeqOrderChangeStatusPK)}�����{@@link #forRow(FeqOrderChangeStatusRow)}���g�p���Ă��������B 
   */
    public static FeqOrderChangeStatusDao findDaoByPk( FeqOrderChangeStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderChangeStatusRow row = findRowByPk( pk );
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
   * p_accountId, p_originalOrderId, and �ɂĎw��̒l�����ӂ�{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_originalOrderId �����Ώۂł���p_originalOrderId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_originalOrderId, and �̒l�ƈ�v����{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqOrderChangeStatusRow findRowByAccountIdOriginalOrderId( long p_accountId, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderChangeStatusRow.TYPE,
            "account_id=? and original_order_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_originalOrderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderChangeStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderChangeStatusDao.findRowsByAccountIdOriginalOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdOriginalOrderId(long, long)}�����{@@link #forRow(FeqOrderChangeStatusRow)}���g�p���Ă��������B 
   */
    public static FeqOrderChangeStatusDao findDaoByAccountIdOriginalOrderId( long p_accountId, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdOriginalOrderId( p_accountId, p_originalOrderId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_originalOrderId, and �ɂĎw��̒l�����ӂ�{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_originalOrderId �����Ώۂł���p_originalOrderId�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_originalOrderId, and �̒l�ƈ�v����{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqOrderChangeStatusRow findRowByInstitutionCodeBranchCodeAccountCodeOriginalOrderId( String p_institutionCode, String p_branchCode, String p_accountCode, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderChangeStatusRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and original_order_id=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, new Long(p_originalOrderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderChangeStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderChangeStatusDao.findRowsByInstitutionCodeBranchCodeAccountCodeOriginalOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeOriginalOrderId(String, String, String, long)}�����{@@link #forRow(FeqOrderChangeStatusRow)}���g�p���Ă��������B 
   */
    public static FeqOrderChangeStatusDao findDaoByInstitutionCodeBranchCodeAccountCodeOriginalOrderId( String p_institutionCode, String p_branchCode, String p_accountCode, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeOriginalOrderId( p_institutionCode, p_branchCode, p_accountCode, p_originalOrderId ) );
    }


  /** 
   * p_accountId, p_newOrderId, and �ɂĎw��̒l�����ӂ�{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_newOrderId �����Ώۂł���p_newOrderId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_newOrderId, and �̒l�ƈ�v����{@@link FeqOrderChangeStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqOrderChangeStatusRow findRowByAccountIdNewOrderId( long p_accountId, Long p_newOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderChangeStatusRow.TYPE,
            "account_id=? and new_order_id=?",
            null,
            new Object[] { new Long(p_accountId), p_newOrderId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderChangeStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderChangeStatusDao.findRowsByAccountIdNewOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdNewOrderId(long, Long)}�����{@@link #forRow(FeqOrderChangeStatusRow)}���g�p���Ă��������B 
   */
    public static FeqOrderChangeStatusDao findDaoByAccountIdNewOrderId( long p_accountId, Long p_newOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdNewOrderId( p_accountId, p_newOrderId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
