head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FTransFinInstitutionDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FTransFinInstitutionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FTransFinInstitutionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FTransFinInstitutionPK 
 * @@see FTransFinInstitutionRow 
 */
public class FTransFinInstitutionDao extends DataAccessObject {


  /** 
   * ����{@@link FTransFinInstitutionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FTransFinInstitutionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FTransFinInstitutionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FTransFinInstitutionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FTransFinInstitutionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FTransFinInstitutionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FTransFinInstitutionRow )
                return new FTransFinInstitutionDao( (FTransFinInstitutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FTransFinInstitutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FTransFinInstitutionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FTransFinInstitutionRow}�I�u�W�F�N�g 
    */
    protected FTransFinInstitutionDao( FTransFinInstitutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FTransFinInstitutionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FTransFinInstitutionRow getFTransFinInstitutionRow() {
        return row;
    }


  /** 
   * �w���{@@link FTransFinInstitutionRow}�I�u�W�F�N�g����{@@link FTransFinInstitutionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FTransFinInstitutionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FTransFinInstitutionDao}�擾�̂��߂Ɏw���{@@link FTransFinInstitutionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FTransFinInstitutionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FTransFinInstitutionDao forRow( FTransFinInstitutionRow row ) throws java.lang.IllegalArgumentException {
        return (FTransFinInstitutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FTransFinInstitutionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FTransFinInstitutionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FTransFinInstitutionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FTransFinInstitutionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FTransFinInstitutionRow.TYPE );
    }


  /** 
   * {@@link FTransFinInstitutionRow}����ӂɓ��肷��{@@link FTransFinInstitutionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FTransFinInstitutionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FTransFinInstitutionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FTransFinInstitutionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FTransFinInstitutionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FTransFinInstitutionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_currencyCode �����Ώۂł���p_currencyCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FTransFinInstitutionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FTransFinInstitutionRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FTransFinInstitutionPK pk = new FTransFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_currencyCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���FTransFinInstitutionPK�I�u�W�F�N�g����{@@link FTransFinInstitutionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FTransFinInstitutionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FTransFinInstitutionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FTransFinInstitutionRow findRowByPk( FTransFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FTransFinInstitutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(FTransFinInstitutionRow)}���g�p���Ă��������B 
   */
    public static FTransFinInstitutionDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FTransFinInstitutionPK pk = new FTransFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_currencyCode );
        FTransFinInstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FTransFinInstitutionPK)}�����{@@link #forRow(FTransFinInstitutionRow)}���g�p���Ă��������B 
   */
    public static FTransFinInstitutionDao findDaoByPk( FTransFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FTransFinInstitutionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_currencyCode, and �ɂĎw��̒l�����ӂ�{@@link FTransFinInstitutionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_currencyCode �����Ώۂł���p_currencyCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_currencyCode, and �̒l�ƈ�v����{@@link FTransFinInstitutionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FTransFinInstitutionRow findRowByInstitutionCodeBranchCodeAccountCodeCurrencyCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FTransFinInstitutionRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and currency_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_currencyCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FTransFinInstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FTransFinInstitutionDao.findRowsByInstitutionCodeBranchCodeAccountCodeCurrencyCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeCurrencyCode(String, String, String, String)}�����{@@link #forRow(FTransFinInstitutionRow)}���g�p���Ă��������B 
   */
    public static FTransFinInstitutionDao findDaoByInstitutionCodeBranchCodeAccountCodeCurrencyCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeCurrencyCode( p_institutionCode, p_branchCode, p_accountCode, p_currencyCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
