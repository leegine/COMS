head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.20.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherMasterDao.java;


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
 * {@@link AccOpenVoucherMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenVoucherMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenVoucherMasterPK 
 * @@see AccOpenVoucherMasterRow 
 */
public class AccOpenVoucherMasterDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenVoucherMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenVoucherMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenVoucherMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenVoucherMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenVoucherMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenVoucherMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenVoucherMasterRow )
                return new AccOpenVoucherMasterDao( (AccOpenVoucherMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenVoucherMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenVoucherMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g 
    */
    protected AccOpenVoucherMasterDao( AccOpenVoucherMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenVoucherMasterRow getAccOpenVoucherMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g����{@@link AccOpenVoucherMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenVoucherMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenVoucherMasterDao}�擾�̂��߂Ɏw���{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenVoucherMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenVoucherMasterDao forRow( AccOpenVoucherMasterRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenVoucherMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenVoucherMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenVoucherMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenVoucherMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenVoucherMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenVoucherMasterRow.TYPE );
    }


  /** 
   * {@@link AccOpenVoucherMasterRow}����ӂɓ��肷��{@@link AccOpenVoucherMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenVoucherMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenVoucherMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenVoucherMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenVoucherMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_serialNo �����Ώۂł���p_serialNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenVoucherMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenVoucherMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherMasterPK pk = new AccOpenVoucherMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenVoucherMasterPK�I�u�W�F�N�g����{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenVoucherMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenVoucherMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenVoucherMasterRow findRowByPk( AccOpenVoucherMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenVoucherMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(AccOpenVoucherMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherMasterPK pk = new AccOpenVoucherMasterPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo );
        AccOpenVoucherMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenVoucherMasterPK)}�����{@@link #forRow(AccOpenVoucherMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherMasterDao findDaoByPk( AccOpenVoucherMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, and �ɂĎw��̒l�����ӂ�{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_serialNo �����Ώۂł���p_serialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, and �̒l�ƈ�v����{@@link AccOpenVoucherMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenVoucherMasterRow findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenVoucherMasterRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and request_code=? and serial_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenVoucherMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenVoucherMasterDao.findRowsByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo(String, String, String, String, String)}�����{@@link #forRow(AccOpenVoucherMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherMasterDao findDaoByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNo( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
