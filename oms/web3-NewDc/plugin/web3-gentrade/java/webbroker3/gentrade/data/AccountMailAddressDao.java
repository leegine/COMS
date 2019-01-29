head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountMailAddressDao.java;


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
 * {@@link AccountMailAddressDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccountMailAddressRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccountMailAddressPK 
 * @@see AccountMailAddressRow 
 */
public class AccountMailAddressDao extends DataAccessObject {


  /** 
   * ����{@@link AccountMailAddressDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccountMailAddressRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccountMailAddressRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccountMailAddressDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccountMailAddressDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccountMailAddressRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccountMailAddressRow )
                return new AccountMailAddressDao( (AccountMailAddressRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccountMailAddressRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccountMailAddressRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccountMailAddressRow}�I�u�W�F�N�g 
    */
    protected AccountMailAddressDao( AccountMailAddressRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccountMailAddressRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccountMailAddressRow getAccountMailAddressRow() {
        return row;
    }


  /** 
   * �w���{@@link AccountMailAddressRow}�I�u�W�F�N�g����{@@link AccountMailAddressDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccountMailAddressRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccountMailAddressDao}�擾�̂��߂Ɏw���{@@link AccountMailAddressRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccountMailAddressDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccountMailAddressDao forRow( AccountMailAddressRow row ) throws java.lang.IllegalArgumentException {
        return (AccountMailAddressDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccountMailAddressRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccountMailAddressRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccountMailAddressPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccountMailAddressParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccountMailAddressRow.TYPE );
    }


  /** 
   * {@@link AccountMailAddressRow}����ӂɓ��肷��{@@link AccountMailAddressPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccountMailAddressRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccountMailAddressParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccountMailAddressPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccountMailAddressPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccountMailAddressRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_emailAddressNumber �����Ώۂł���p_emailAddressNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccountMailAddressRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccountMailAddressRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountMailAddressPK pk = new AccountMailAddressPK( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccountMailAddressPK�I�u�W�F�N�g����{@@link AccountMailAddressRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccountMailAddressPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccountMailAddressRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccountMailAddressRow findRowByPk( AccountMailAddressPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccountMailAddressRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,long)}�����{@@link #forRow(AccountMailAddressRow)}���g�p���Ă��������B 
   */
    public static AccountMailAddressDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountMailAddressPK pk = new AccountMailAddressPK( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber );
        AccountMailAddressRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccountMailAddressPK)}�����{@@link #forRow(AccountMailAddressRow)}���g�p���Ă��������B 
   */
    public static AccountMailAddressDao findDaoByPk( AccountMailAddressPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountMailAddressRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, and �ɂĎw��̒l�����ӂ�{@@link AccountMailAddressRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_emailAddressNumber �����Ώۂł���p_emailAddressNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber, and �̒l�ƈ�v����{@@link AccountMailAddressRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccountMailAddressRow findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccountMailAddressRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and email_address_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, new Long(p_emailAddressNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccountMailAddressRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccountMailAddressDao.findRowsByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber(String, String, String, long)}�����{@@link #forRow(AccountMailAddressRow)}���g�p���Ă��������B 
   */
    public static AccountMailAddressDao findDaoByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber( String p_institutionCode, String p_branchCode, String p_accountCode, long p_emailAddressNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeEmailAddressNumber( p_institutionCode, p_branchCode, p_accountCode, p_emailAddressNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
