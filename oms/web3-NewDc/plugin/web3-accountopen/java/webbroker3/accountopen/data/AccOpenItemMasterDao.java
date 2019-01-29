head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.10.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AccOpenItemMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenItemMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenItemMasterPK 
 * @@see AccOpenItemMasterRow 
 */
public class AccOpenItemMasterDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenItemMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenItemMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenItemMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenItemMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenItemMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenItemMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenItemMasterRow )
                return new AccOpenItemMasterDao( (AccOpenItemMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenItemMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenItemMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenItemMasterRow}�I�u�W�F�N�g 
    */
    protected AccOpenItemMasterDao( AccOpenItemMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenItemMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenItemMasterRow getAccOpenItemMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenItemMasterRow}�I�u�W�F�N�g����{@@link AccOpenItemMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenItemMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenItemMasterDao}�擾�̂��߂Ɏw���{@@link AccOpenItemMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenItemMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenItemMasterDao forRow( AccOpenItemMasterRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenItemMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenItemMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenItemMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenItemMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenItemMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenItemMasterRow.TYPE );
    }


  /** 
   * {@@link AccOpenItemMasterRow}����ӂɓ��肷��{@@link AccOpenItemMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenItemMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenItemMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenItemMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenItemMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenItemMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_validateType �����Ώۂł���p_validateType�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenItemMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenItemMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemMasterPK pk = new AccOpenItemMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenItemMasterPK�I�u�W�F�N�g����{@@link AccOpenItemMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenItemMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenItemMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenItemMasterRow findRowByPk( AccOpenItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenItemMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(AccOpenItemMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenItemMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemMasterPK pk = new AccOpenItemMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName );
        AccOpenItemMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenItemMasterPK)}�����{@@link #forRow(AccOpenItemMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenItemMasterDao findDaoByPk( AccOpenItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName, and �ɂĎw��̒l�����ӂ�{@@link AccOpenItemMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_validateType �����Ώۂł���p_validateType�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName, and �̒l�ƈ�v����{@@link AccOpenItemMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenItemMasterRow findRowByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenItemMasterRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and validate_type=? and item_symbol_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenItemMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenItemMasterDao.findRowsByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName(String, String, String, String, String)}�����{@@link #forRow(AccOpenItemMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenItemMasterDao findDaoByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_validateType, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivValidateTypeItemSymbolName( p_institutionCode, p_branchCode, p_accountDiv, p_validateType, p_itemSymbolName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
