head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountTransferDao.java;


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
 * {@@link AccountTransferDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccountTransferRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccountTransferPK 
 * @@see AccountTransferRow 
 */
public class AccountTransferDao extends DataAccessObject {


  /** 
   * ����{@@link AccountTransferDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccountTransferRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccountTransferRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccountTransferDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccountTransferDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccountTransferRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccountTransferRow )
                return new AccountTransferDao( (AccountTransferRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccountTransferRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccountTransferRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccountTransferRow}�I�u�W�F�N�g 
    */
    protected AccountTransferDao( AccountTransferRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccountTransferRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccountTransferRow getAccountTransferRow() {
        return row;
    }


  /** 
   * �w���{@@link AccountTransferRow}�I�u�W�F�N�g����{@@link AccountTransferDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccountTransferRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccountTransferDao}�擾�̂��߂Ɏw���{@@link AccountTransferRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccountTransferDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccountTransferDao forRow( AccountTransferRow row ) throws java.lang.IllegalArgumentException {
        return (AccountTransferDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccountTransferRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccountTransferRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccountTransferPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccountTransferParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccountTransferRow.TYPE );
    }


  /** 
   * {@@link AccountTransferRow}����ӂɓ��肷��{@@link AccountTransferPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccountTransferRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccountTransferParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccountTransferPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccountTransferPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccountTransferRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_workDay �����Ώۂł���p_workDay�t�B�[���h�̒l
   * @@param p_branchCodeOld �����Ώۂł���p_branchCodeOld�t�B�[���h�̒l
   * @@param p_accountCodeOld �����Ώۂł���p_accountCodeOld�t�B�[���h�̒l
   * @@param p_transferTbl �����Ώۂł���p_transferTbl�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccountTransferRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccountTransferRow findRowByPk( String p_institutionCode, java.sql.Timestamp p_workDay, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountTransferPK pk = new AccountTransferPK( p_institutionCode, p_workDay, p_branchCodeOld, p_accountCodeOld, p_transferTbl );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccountTransferPK�I�u�W�F�N�g����{@@link AccountTransferRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccountTransferPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccountTransferRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccountTransferRow findRowByPk( AccountTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccountTransferRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,java.sql.Timestamp,String,String,String)}�����{@@link #forRow(AccountTransferRow)}���g�p���Ă��������B 
   */
    public static AccountTransferDao findDaoByPk( String p_institutionCode, java.sql.Timestamp p_workDay, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountTransferPK pk = new AccountTransferPK( p_institutionCode, p_workDay, p_branchCodeOld, p_accountCodeOld, p_transferTbl );
        AccountTransferRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccountTransferPK)}�����{@@link #forRow(AccountTransferRow)}���g�p���Ă��������B 
   */
    public static AccountTransferDao findDaoByPk( AccountTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountTransferRow row = findRowByPk( pk );
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
   * p_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl, and �ɂĎw��̒l�����ӂ�{@@link AccountTransferRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_workDay �����Ώۂł���p_workDay�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCodeOld �����Ώۂł���p_branchCodeOld�t�B�[���h�̒l
   * @@param p_accountCodeOld �����Ώۂł���p_accountCodeOld�t�B�[���h�̒l
   * @@param p_transferTbl �����Ώۂł���p_transferTbl�t�B�[���h�̒l
   * 
   * @@return �����w���p_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl, and �̒l�ƈ�v����{@@link AccountTransferRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccountTransferRow findRowByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl( java.sql.Timestamp p_workDay, String p_institutionCode, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccountTransferRow.TYPE,
            "work_day=? and institution_code=? and branch_code_old=? and account_code_old=? and transfer_tbl=?",
            null,
            new Object[] { p_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccountTransferRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccountTransferDao.findRowsByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl(java.sql.Timestamp, String, String, String, String)}�����{@@link #forRow(AccountTransferRow)}���g�p���Ă��������B 
   */
    public static AccountTransferDao findDaoByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl( java.sql.Timestamp p_workDay, String p_institutionCode, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl( p_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_proDiv, and �ɂĎw��̒l�Ɉ�v����{@@link AccountTransferRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_proDiv �����Ώۂł���p_proDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_proDiv, and �̒l�ƈ�v����{@@link AccountTransferRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProDiv( String p_proDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccountTransferRow.TYPE,
            "pro_div=?",
            null,
            new Object[] { p_proDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProDiv(String)}�����{@@link #forRow(AccountTransferRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProDiv( String p_proDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProDiv( p_proDiv ) );
    }

}
@
