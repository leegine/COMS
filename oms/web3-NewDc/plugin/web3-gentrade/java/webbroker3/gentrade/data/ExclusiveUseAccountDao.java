head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExclusiveUseAccountDao.java;


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
 * {@@link ExclusiveUseAccountDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link ExclusiveUseAccountRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see ExclusiveUseAccountPK 
 * @@see ExclusiveUseAccountRow 
 */
public class ExclusiveUseAccountDao extends DataAccessObject {


  /** 
   * ����{@@link ExclusiveUseAccountDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private ExclusiveUseAccountRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link ExclusiveUseAccountRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link ExclusiveUseAccountDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link ExclusiveUseAccountDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link ExclusiveUseAccountRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExclusiveUseAccountRow )
                return new ExclusiveUseAccountDao( (ExclusiveUseAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExclusiveUseAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExclusiveUseAccountRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g 
    */
    protected ExclusiveUseAccountDao( ExclusiveUseAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g���擾���܂��B
   */
    public ExclusiveUseAccountRow getExclusiveUseAccountRow() {
        return row;
    }


  /** 
   * �w���{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g����{@@link ExclusiveUseAccountDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link ExclusiveUseAccountRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link ExclusiveUseAccountDao}�擾�̂��߂Ɏw���{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link ExclusiveUseAccountDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static ExclusiveUseAccountDao forRow( ExclusiveUseAccountRow row ) throws java.lang.IllegalArgumentException {
        return (ExclusiveUseAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExclusiveUseAccountRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link ExclusiveUseAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link ExclusiveUseAccountPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link ExclusiveUseAccountParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExclusiveUseAccountRow.TYPE );
    }


  /** 
   * {@@link ExclusiveUseAccountRow}����ӂɓ��肷��{@@link ExclusiveUseAccountPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link ExclusiveUseAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link ExclusiveUseAccountParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link ExclusiveUseAccountPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static ExclusiveUseAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new ExclusiveUseAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExclusiveUseAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExclusiveUseAccountRow findRowByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountPK pk = new ExclusiveUseAccountPK( p_accountId );
        return findRowByPk( pk );
    }


  /** 
   * �w���ExclusiveUseAccountPK�I�u�W�F�N�g����{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����ExclusiveUseAccountPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExclusiveUseAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExclusiveUseAccountRow findRowByPk( ExclusiveUseAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExclusiveUseAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(ExclusiveUseAccountRow)}���g�p���Ă��������B 
   */
    public static ExclusiveUseAccountDao findDaoByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountPK pk = new ExclusiveUseAccountPK( p_accountId );
        ExclusiveUseAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(ExclusiveUseAccountPK)}�����{@@link #forRow(ExclusiveUseAccountRow)}���g�p���Ă��������B 
   */
    public static ExclusiveUseAccountDao findDaoByPk( ExclusiveUseAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountRow row = findRowByPk( pk );
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
   * p_accountId, and �ɂĎw��̒l�����ӂ�{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, and �̒l�ƈ�v����{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static ExclusiveUseAccountRow findRowByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExclusiveUseAccountRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExclusiveUseAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExclusiveUseAccountDao.findRowsByAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountId(long)}�����{@@link #forRow(ExclusiveUseAccountRow)}���g�p���Ă��������B 
   */
    public static ExclusiveUseAccountDao findDaoByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountId( p_accountId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and �ɂĎw��̒l�Ɉ�v����{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, and �̒l�ƈ�v����{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExclusiveUseAccountRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCode(String, String, String)}�����{@@link #forRow(ExclusiveUseAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }


  /** 
   * p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo, and �ɂĎw��̒l�Ɉ�v����{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_finInstitutionCode �����Ώۂł���p_finInstitutionCode�t�B�[���h�̒l
   * @@param p_finBranchCode �����Ώۂł���p_finBranchCode�t�B�[���h�̒l
   * @@param p_finAccountNo �����Ώۂł���p_finAccountNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo, and �̒l�ƈ�v����{@@link ExclusiveUseAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExclusiveUseAccountRow.TYPE,
            "institution_code=? and fin_institution_code=? and fin_branch_code=? and fin_account_no=?",
            null,
            new Object[] { p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo(String, String, String, String)}�����{@@link #forRow(ExclusiveUseAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo ) );
    }

}
@
