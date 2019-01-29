head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdminPermissionDao.java;


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
 * {@@link AdminPermissionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AdminPermissionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AdminPermissionPK 
 * @@see AdminPermissionRow 
 */
public class AdminPermissionDao extends DataAccessObject {


  /** 
   * ����{@@link AdminPermissionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AdminPermissionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AdminPermissionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AdminPermissionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AdminPermissionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AdminPermissionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdminPermissionRow )
                return new AdminPermissionDao( (AdminPermissionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdminPermissionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdminPermissionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AdminPermissionRow}�I�u�W�F�N�g 
    */
    protected AdminPermissionDao( AdminPermissionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AdminPermissionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AdminPermissionRow getAdminPermissionRow() {
        return row;
    }


  /** 
   * �w���{@@link AdminPermissionRow}�I�u�W�F�N�g����{@@link AdminPermissionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AdminPermissionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AdminPermissionDao}�擾�̂��߂Ɏw���{@@link AdminPermissionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AdminPermissionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AdminPermissionDao forRow( AdminPermissionRow row ) throws java.lang.IllegalArgumentException {
        return (AdminPermissionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdminPermissionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AdminPermissionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AdminPermissionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AdminPermissionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdminPermissionRow.TYPE );
    }


  /** 
   * {@@link AdminPermissionRow}����ӂɓ��肷��{@@link AdminPermissionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AdminPermissionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AdminPermissionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AdminPermissionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AdminPermissionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AdminPermissionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_permissionLevel �����Ώۂł���p_permissionLevel�t�B�[���h�̒l
   * @@param p_transactionCategory �����Ώۂł���p_transactionCategory�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdminPermissionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdminPermissionRow findRowByPk( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        AdminPermissionPK pk = new AdminPermissionPK( p_institutionCode, p_permissionLevel, p_transactionCategory );
        return findRowByPk( pk );
    }


  /** 
   * �w���AdminPermissionPK�I�u�W�F�N�g����{@@link AdminPermissionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AdminPermissionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdminPermissionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdminPermissionRow findRowByPk( AdminPermissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdminPermissionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(AdminPermissionRow)}���g�p���Ă��������B 
   */
    public static AdminPermissionDao findDaoByPk( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        AdminPermissionPK pk = new AdminPermissionPK( p_institutionCode, p_permissionLevel, p_transactionCategory );
        AdminPermissionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AdminPermissionPK)}�����{@@link #forRow(AdminPermissionRow)}���g�p���Ă��������B 
   */
    public static AdminPermissionDao findDaoByPk( AdminPermissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdminPermissionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_permissionLevel, p_transactionCategory, and �ɂĎw��̒l�����ӂ�{@@link AdminPermissionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_permissionLevel �����Ώۂł���p_permissionLevel�t�B�[���h�̒l
   * @@param p_transactionCategory �����Ώۂł���p_transactionCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_permissionLevel, p_transactionCategory, and �̒l�ƈ�v����{@@link AdminPermissionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdminPermissionRow findRowByInstitutionCodePermissionLevelTransactionCategory( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdminPermissionRow.TYPE,
            "institution_code=? and permission_level=? and transaction_category=?",
            null,
            new Object[] { p_institutionCode, p_permissionLevel, p_transactionCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdminPermissionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdminPermissionDao.findRowsByInstitutionCodePermissionLevelTransactionCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodePermissionLevelTransactionCategory(String, String, String)}�����{@@link #forRow(AdminPermissionRow)}���g�p���Ă��������B 
   */
    public static AdminPermissionDao findDaoByInstitutionCodePermissionLevelTransactionCategory( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodePermissionLevelTransactionCategory( p_institutionCode, p_permissionLevel, p_transactionCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
