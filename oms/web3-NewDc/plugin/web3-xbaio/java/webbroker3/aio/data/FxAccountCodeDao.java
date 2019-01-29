head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	FxAccountCodeDao.java;


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
 * {@@link FxAccountCodeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FxAccountCodeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FxAccountCodePK 
 * @@see FxAccountCodeRow 
 */
public class FxAccountCodeDao extends DataAccessObject {


  /** 
   * ����{@@link FxAccountCodeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FxAccountCodeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FxAccountCodeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FxAccountCodeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FxAccountCodeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FxAccountCodeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxAccountCodeRow )
                return new FxAccountCodeDao( (FxAccountCodeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxAccountCodeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxAccountCodeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FxAccountCodeRow}�I�u�W�F�N�g 
    */
    protected FxAccountCodeDao( FxAccountCodeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FxAccountCodeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FxAccountCodeRow getFxAccountCodeRow() {
        return row;
    }


  /** 
   * �w���{@@link FxAccountCodeRow}�I�u�W�F�N�g����{@@link FxAccountCodeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FxAccountCodeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FxAccountCodeDao}�擾�̂��߂Ɏw���{@@link FxAccountCodeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FxAccountCodeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FxAccountCodeDao forRow( FxAccountCodeRow row ) throws java.lang.IllegalArgumentException {
        return (FxAccountCodeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxAccountCodeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FxAccountCodeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FxAccountCodePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FxAccountCodeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxAccountCodeRow.TYPE );
    }


  /** 
   * {@@link FxAccountCodeRow}����ӂɓ��肷��{@@link FxAccountCodePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FxAccountCodeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FxAccountCodeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FxAccountCodePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FxAccountCodePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FxAccountCodeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_fxSystemCode �����Ώۂł���p_fxSystemCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_fxCourseDiv �����Ώۂł���p_fxCourseDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxAccountCodeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxAccountCodeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountCodePK pk = new FxAccountCodePK( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���FxAccountCodePK�I�u�W�F�N�g����{@@link FxAccountCodeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FxAccountCodePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxAccountCodeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxAccountCodeRow findRowByPk( FxAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxAccountCodeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(FxAccountCodeRow)}���g�p���Ă��������B 
   */
    public static FxAccountCodeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountCodePK pk = new FxAccountCodePK( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv );
        FxAccountCodeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FxAccountCodePK)}�����{@@link #forRow(FxAccountCodeRow)}���g�p���Ă��������B 
   */
    public static FxAccountCodeDao findDaoByPk( FxAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountCodeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv, and �ɂĎw��̒l�����ӂ�{@@link FxAccountCodeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_fxSystemCode �����Ώۂł���p_fxSystemCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_fxCourseDiv �����Ώۂł���p_fxCourseDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv, and �̒l�ƈ�v����{@@link FxAccountCodeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FxAccountCodeRow findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and account_code=? and fx_course_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxAccountCodeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxAccountCodeDao.findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv(String, String, String, String, String)}�����{@@link #forRow(FxAccountCodeRow)}���g�p���Ă��������B 
   */
    public static FxAccountCodeDao findDaoByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCodeFxCourseDiv( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, p_fxCourseDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode, and �ɂĎw��̒l�Ɉ�v����{@@link FxAccountCodeRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_fxSystemCode �����Ώۂł���p_fxSystemCode�t�B�[���h�̒l
   * @@param p_fxAccountCode �����Ώۂł���p_fxAccountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode, and �̒l�ƈ�v����{@@link FxAccountCodeRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_fxAccountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FxAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and fx_account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode(String, String, String, String)}�����{@@link #forRow(FxAccountCodeRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_fxAccountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeFxSystemCodeFxAccountCode( p_institutionCode, p_branchCode, p_fxSystemCode, p_fxAccountCode ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and �ɂĎw��̒l�Ɉ�v����{@@link FxAccountCodeRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_fxSystemCode �����Ώۂł���p_fxSystemCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and �̒l�ƈ�v����{@@link FxAccountCodeRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FxAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode(String, String, String, String)}�����{@@link #forRow(FxAccountCodeRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode ) );
    }

}
@
