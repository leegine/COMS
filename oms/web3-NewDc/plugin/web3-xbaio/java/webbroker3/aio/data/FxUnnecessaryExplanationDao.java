head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.45.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FxUnnecessaryExplanationDao.java;


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
 * {@@link FxUnnecessaryExplanationDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FxUnnecessaryExplanationRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FxUnnecessaryExplanationPK 
 * @@see FxUnnecessaryExplanationRow 
 */
public class FxUnnecessaryExplanationDao extends DataAccessObject {


  /** 
   * ����{@@link FxUnnecessaryExplanationDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FxUnnecessaryExplanationRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FxUnnecessaryExplanationRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FxUnnecessaryExplanationDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FxUnnecessaryExplanationDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FxUnnecessaryExplanationRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxUnnecessaryExplanationRow )
                return new FxUnnecessaryExplanationDao( (FxUnnecessaryExplanationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxUnnecessaryExplanationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxUnnecessaryExplanationRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g 
    */
    protected FxUnnecessaryExplanationDao( FxUnnecessaryExplanationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FxUnnecessaryExplanationRow getFxUnnecessaryExplanationRow() {
        return row;
    }


  /** 
   * �w���{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g����{@@link FxUnnecessaryExplanationDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FxUnnecessaryExplanationRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FxUnnecessaryExplanationDao}�擾�̂��߂Ɏw���{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FxUnnecessaryExplanationDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FxUnnecessaryExplanationDao forRow( FxUnnecessaryExplanationRow row ) throws java.lang.IllegalArgumentException {
        return (FxUnnecessaryExplanationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxUnnecessaryExplanationRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FxUnnecessaryExplanationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FxUnnecessaryExplanationPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FxUnnecessaryExplanationParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxUnnecessaryExplanationRow.TYPE );
    }


  /** 
   * {@@link FxUnnecessaryExplanationRow}����ӂɓ��肷��{@@link FxUnnecessaryExplanationPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FxUnnecessaryExplanationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FxUnnecessaryExplanationParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FxUnnecessaryExplanationPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FxUnnecessaryExplanationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_fxSerialNo �����Ώۂł���p_fxSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxUnnecessaryExplanationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxUnnecessaryExplanationRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        FxUnnecessaryExplanationPK pk = new FxUnnecessaryExplanationPK( p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���FxUnnecessaryExplanationPK�I�u�W�F�N�g����{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FxUnnecessaryExplanationPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxUnnecessaryExplanationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxUnnecessaryExplanationRow findRowByPk( FxUnnecessaryExplanationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxUnnecessaryExplanationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,int)}�����{@@link #forRow(FxUnnecessaryExplanationRow)}���g�p���Ă��������B 
   */
    public static FxUnnecessaryExplanationDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        FxUnnecessaryExplanationPK pk = new FxUnnecessaryExplanationPK( p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo );
        FxUnnecessaryExplanationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FxUnnecessaryExplanationPK)}�����{@@link #forRow(FxUnnecessaryExplanationRow)}���g�p���Ă��������B 
   */
    public static FxUnnecessaryExplanationDao findDaoByPk( FxUnnecessaryExplanationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxUnnecessaryExplanationRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo, and �ɂĎw��̒l�����ӂ�{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_fxSerialNo �����Ώۂł���p_fxSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo, and �̒l�ƈ�v����{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FxUnnecessaryExplanationRow findRowByInstitutionCodeBranchCodeAccountCodeFxSerialNo( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxUnnecessaryExplanationRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and fx_serial_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, new Integer(p_fxSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxUnnecessaryExplanationRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxUnnecessaryExplanationDao.findRowsByInstitutionCodeBranchCodeAccountCodeFxSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeFxSerialNo(String, String, String, int)}�����{@@link #forRow(FxUnnecessaryExplanationRow)}���g�p���Ă��������B 
   */
    public static FxUnnecessaryExplanationDao findDaoByInstitutionCodeBranchCodeAccountCodeFxSerialNo( String p_institutionCode, String p_branchCode, String p_accountCode, int p_fxSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeFxSerialNo( p_institutionCode, p_branchCode, p_accountCode, p_fxSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and �ɂĎw��̒l�Ɉ�v����{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, and �̒l�ƈ�v����{@@link FxUnnecessaryExplanationRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FxUnnecessaryExplanationRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCode(String, String, String)}�����{@@link #forRow(FxUnnecessaryExplanationRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
