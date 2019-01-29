head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	FxAccountDao.java;


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
 * {@@link FxAccountDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FxAccountRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FxAccountPK 
 * @@see FxAccountRow 
 */
public class FxAccountDao extends DataAccessObject {


  /** 
   * ����{@@link FxAccountDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FxAccountRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FxAccountRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FxAccountDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FxAccountDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FxAccountRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxAccountRow )
                return new FxAccountDao( (FxAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxAccountRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FxAccountRow}�I�u�W�F�N�g 
    */
    protected FxAccountDao( FxAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FxAccountRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FxAccountRow getFxAccountRow() {
        return row;
    }


  /** 
   * �w���{@@link FxAccountRow}�I�u�W�F�N�g����{@@link FxAccountDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FxAccountRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FxAccountDao}�擾�̂��߂Ɏw���{@@link FxAccountRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FxAccountDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FxAccountDao forRow( FxAccountRow row ) throws java.lang.IllegalArgumentException {
        return (FxAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxAccountRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FxAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FxAccountPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FxAccountParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxAccountRow.TYPE );
    }


  /** 
   * {@@link FxAccountRow}����ӂɓ��肷��{@@link FxAccountPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FxAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FxAccountParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FxAccountPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FxAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FxAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FxAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_fxAccountId �����Ώۂł���p_fxAccountId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxAccountRow findRowByPk( long p_fxAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountPK pk = new FxAccountPK( p_fxAccountId );
        return findRowByPk( pk );
    }


  /** 
   * �w���FxAccountPK�I�u�W�F�N�g����{@@link FxAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FxAccountPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxAccountRow findRowByPk( FxAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(FxAccountRow)}���g�p���Ă��������B 
   */
    public static FxAccountDao findDaoByPk( long p_fxAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountPK pk = new FxAccountPK( p_fxAccountId );
        FxAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FxAccountPK)}�����{@@link #forRow(FxAccountRow)}���g�p���Ă��������B 
   */
    public static FxAccountDao findDaoByPk( FxAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountRow row = findRowByPk( pk );
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
   * p_fxAccountId, and �ɂĎw��̒l�����ӂ�{@@link FxAccountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_fxAccountId �����Ώۂł���p_fxAccountId�t�B�[���h�̒l
   * 
   * @@return �����w���p_fxAccountId, and �̒l�ƈ�v����{@@link FxAccountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FxAccountRow findRowByFxAccountId( long p_fxAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxAccountRow.TYPE,
            "fx_account_id=?",
            null,
            new Object[] { new Long(p_fxAccountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxAccountDao.findRowsByFxAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByFxAccountId(long)}�����{@@link #forRow(FxAccountRow)}���g�p���Ă��������B 
   */
    public static FxAccountDao findDaoByFxAccountId( long p_fxAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFxAccountId( p_fxAccountId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and �ɂĎw��̒l�����ӂ�{@@link FxAccountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_fxSystemCode �����Ώۂł���p_fxSystemCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and �̒l�ƈ�v����{@@link FxAccountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FxAccountRow findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxAccountRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxAccountDao.findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode(String, String, String, String)}�����{@@link #forRow(FxAccountRow)}���g�p���Ă��������B 
   */
    public static FxAccountDao findDaoByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
