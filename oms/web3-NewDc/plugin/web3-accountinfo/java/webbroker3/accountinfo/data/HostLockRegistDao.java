head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.15.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	HostLockRegistDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link HostLockRegistDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostLockRegistRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostLockRegistPK 
 * @@see HostLockRegistRow 
 */
public class HostLockRegistDao extends DataAccessObject {


  /** 
   * ����{@@link HostLockRegistDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostLockRegistRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostLockRegistRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostLockRegistDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostLockRegistDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostLockRegistRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostLockRegistRow )
                return new HostLockRegistDao( (HostLockRegistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostLockRegistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostLockRegistRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostLockRegistRow}�I�u�W�F�N�g 
    */
    protected HostLockRegistDao( HostLockRegistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostLockRegistRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostLockRegistRow getHostLockRegistRow() {
        return row;
    }


  /** 
   * �w���{@@link HostLockRegistRow}�I�u�W�F�N�g����{@@link HostLockRegistDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostLockRegistRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostLockRegistDao}�擾�̂��߂Ɏw���{@@link HostLockRegistRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostLockRegistDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostLockRegistDao forRow( HostLockRegistRow row ) throws java.lang.IllegalArgumentException {
        return (HostLockRegistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostLockRegistRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostLockRegistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostLockRegistPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostLockRegistParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostLockRegistRow.TYPE );
    }


  /** 
   * {@@link HostLockRegistRow}����ӂɓ��肷��{@@link HostLockRegistPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostLockRegistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostLockRegistParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostLockRegistPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostLockRegistPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostLockRegistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostLockRegistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostLockRegistRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegistPK pk = new HostLockRegistPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostLockRegistPK�I�u�W�F�N�g����{@@link HostLockRegistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostLockRegistPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostLockRegistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostLockRegistRow findRowByPk( HostLockRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostLockRegistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostLockRegistRow)}���g�p���Ă��������B 
   */
    public static HostLockRegistDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegistPK pk = new HostLockRegistPK( p_rowid );
        HostLockRegistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostLockRegistPK)}�����{@@link #forRow(HostLockRegistRow)}���g�p���Ă��������B 
   */
    public static HostLockRegistDao findDaoByPk( HostLockRegistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostLockRegistRow row = findRowByPk( pk );
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
   * p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link HostLockRegistRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_lastUpdatedTimestamp �����Ώۂł���p_lastUpdatedTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp, and �̒l�ƈ�v����{@@link HostLockRegistRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostLockRegistRow.TYPE,
            "request_code=? and institution_code=? and branch_code=? and account_code=? and last_updated_timestamp=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp(String, String, String, String, java.sql.Timestamp)}�����{@@link #forRow(HostLockRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_lastUpdatedTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeLastUpdatedTimestamp( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_lastUpdatedTimestamp ) );
    }


  /** 
   * p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link HostLockRegistRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostLockRegistRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostLockRegistRow.TYPE,
            "request_code=? and institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String, String)}�����{@@link #forRow(HostLockRegistRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_requestCode, String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

}
@
