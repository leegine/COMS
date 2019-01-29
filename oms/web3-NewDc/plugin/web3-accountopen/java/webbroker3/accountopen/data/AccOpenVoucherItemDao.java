head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherItemDao.java;


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
 * {@@link AccOpenVoucherItemDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenVoucherItemRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenVoucherItemPK 
 * @@see AccOpenVoucherItemRow 
 */
public class AccOpenVoucherItemDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenVoucherItemDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenVoucherItemRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenVoucherItemRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenVoucherItemDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenVoucherItemDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenVoucherItemRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenVoucherItemRow )
                return new AccOpenVoucherItemDao( (AccOpenVoucherItemRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenVoucherItemRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenVoucherItemRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g 
    */
    protected AccOpenVoucherItemDao( AccOpenVoucherItemRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenVoucherItemRow getAccOpenVoucherItemRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g����{@@link AccOpenVoucherItemDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenVoucherItemRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenVoucherItemDao}�擾�̂��߂Ɏw���{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenVoucherItemDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenVoucherItemDao forRow( AccOpenVoucherItemRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenVoucherItemDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenVoucherItemRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenVoucherItemRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenVoucherItemPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenVoucherItemParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenVoucherItemRow.TYPE );
    }


  /** 
   * {@@link AccOpenVoucherItemRow}����ӂɓ��肷��{@@link AccOpenVoucherItemPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenVoucherItemRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenVoucherItemParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenVoucherItemPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenVoucherItemPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_serialNo �����Ώۂł���p_serialNo�t�B�[���h�̒l
   * @@param p_outputItemSymbolName �����Ώۂł���p_outputItemSymbolName�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenVoucherItemRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenVoucherItemRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherItemPK pk = new AccOpenVoucherItemPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenVoucherItemPK�I�u�W�F�N�g����{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenVoucherItemPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenVoucherItemRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenVoucherItemRow findRowByPk( AccOpenVoucherItemPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenVoucherItemRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String,String)}�����{@@link #forRow(AccOpenVoucherItemRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherItemDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherItemPK pk = new AccOpenVoucherItemPK( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName );
        AccOpenVoucherItemRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenVoucherItemPK)}�����{@@link #forRow(AccOpenVoucherItemRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherItemDao findDaoByPk( AccOpenVoucherItemPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherItemRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName, and �ɂĎw��̒l�����ӂ�{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_serialNo �����Ώۂł���p_serialNo�t�B�[���h�̒l
   * @@param p_outputItemSymbolName �����Ώۂł���p_outputItemSymbolName�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName, and �̒l�ƈ�v����{@@link AccOpenVoucherItemRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenVoucherItemRow findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenVoucherItemRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and request_code=? and serial_no=? and output_item_symbol_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenVoucherItemRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenVoucherItemDao.findRowsByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName(String, String, String, String, String, String)}�����{@@link #forRow(AccOpenVoucherItemRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherItemDao findDaoByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo, String p_outputItemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivRequestCodeSerialNoOutputItemSymbolName( p_institutionCode, p_branchCode, p_accountDiv, p_requestCode, p_serialNo, p_outputItemSymbolName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
