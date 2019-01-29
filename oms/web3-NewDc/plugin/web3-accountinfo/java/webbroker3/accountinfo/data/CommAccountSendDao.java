head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommAccountSendDao.java;


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
 * {@@link CommAccountSendDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CommAccountSendRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CommAccountSendPK 
 * @@see CommAccountSendRow 
 */
public class CommAccountSendDao extends DataAccessObject {


  /** 
   * ����{@@link CommAccountSendDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CommAccountSendRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CommAccountSendRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CommAccountSendDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CommAccountSendDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CommAccountSendRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommAccountSendRow )
                return new CommAccountSendDao( (CommAccountSendRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommAccountSendRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommAccountSendRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CommAccountSendRow}�I�u�W�F�N�g 
    */
    protected CommAccountSendDao( CommAccountSendRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CommAccountSendRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CommAccountSendRow getCommAccountSendRow() {
        return row;
    }


  /** 
   * �w���{@@link CommAccountSendRow}�I�u�W�F�N�g����{@@link CommAccountSendDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CommAccountSendRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CommAccountSendDao}�擾�̂��߂Ɏw���{@@link CommAccountSendRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CommAccountSendDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CommAccountSendDao forRow( CommAccountSendRow row ) throws java.lang.IllegalArgumentException {
        return (CommAccountSendDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommAccountSendRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CommAccountSendRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CommAccountSendPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CommAccountSendParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommAccountSendRow.TYPE );
    }


  /** 
   * {@@link CommAccountSendRow}����ӂɓ��肷��{@@link CommAccountSendPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CommAccountSendRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CommAccountSendParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CommAccountSendPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CommAccountSendPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CommAccountSendRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommAccountSendRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommAccountSendRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommAccountSendPK pk = new CommAccountSendPK( p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���CommAccountSendPK�I�u�W�F�N�g����{@@link CommAccountSendRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CommAccountSendPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommAccountSendRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommAccountSendRow findRowByPk( CommAccountSendPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommAccountSendRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(CommAccountSendRow)}���g�p���Ă��������B 
   */
    public static CommAccountSendDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommAccountSendPK pk = new CommAccountSendPK( p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate );
        CommAccountSendRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CommAccountSendPK)}�����{@@link #forRow(CommAccountSendRow)}���g�p���Ă��������B 
   */
    public static CommAccountSendDao findDaoByPk( CommAccountSendPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommAccountSendRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate, and �ɂĎw��̒l�����ӂ�{@@link CommAccountSendRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate, and �̒l�ƈ�v����{@@link CommAccountSendRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CommAccountSendRow findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommAccountSendRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and comm_product_code=? and appli_start_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommAccountSendRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommAccountSendDao.findRowsByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate(String, String, String, String, String)}�����{@@link #forRow(CommAccountSendRow)}���g�p���Ă��������B 
   */
    public static CommAccountSendDao findDaoByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commProductCode, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate( p_institutionCode, p_branchCode, p_accountCode, p_commProductCode, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
