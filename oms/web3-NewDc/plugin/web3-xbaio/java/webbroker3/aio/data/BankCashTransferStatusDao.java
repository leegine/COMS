head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.45.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankCashTransferStatusDao.java;


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
 * {@@link BankCashTransferStatusDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BankCashTransferStatusRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BankCashTransferStatusPK 
 * @@see BankCashTransferStatusRow 
 */
public class BankCashTransferStatusDao extends DataAccessObject {


  /** 
   * ����{@@link BankCashTransferStatusDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BankCashTransferStatusRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BankCashTransferStatusRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BankCashTransferStatusDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BankCashTransferStatusDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BankCashTransferStatusRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankCashTransferStatusRow )
                return new BankCashTransferStatusDao( (BankCashTransferStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankCashTransferStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankCashTransferStatusRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BankCashTransferStatusRow}�I�u�W�F�N�g 
    */
    protected BankCashTransferStatusDao( BankCashTransferStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BankCashTransferStatusRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BankCashTransferStatusRow getBankCashTransferStatusRow() {
        return row;
    }


  /** 
   * �w���{@@link BankCashTransferStatusRow}�I�u�W�F�N�g����{@@link BankCashTransferStatusDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BankCashTransferStatusRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BankCashTransferStatusDao}�擾�̂��߂Ɏw���{@@link BankCashTransferStatusRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BankCashTransferStatusDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BankCashTransferStatusDao forRow( BankCashTransferStatusRow row ) throws java.lang.IllegalArgumentException {
        return (BankCashTransferStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankCashTransferStatusRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BankCashTransferStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BankCashTransferStatusPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BankCashTransferStatusParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankCashTransferStatusRow.TYPE );
    }


  /** 
   * {@@link BankCashTransferStatusRow}����ӂɓ��肷��{@@link BankCashTransferStatusPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BankCashTransferStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BankCashTransferStatusParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BankCashTransferStatusPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BankCashTransferStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BankCashTransferStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankCashTransferStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankCashTransferStatusRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        BankCashTransferStatusPK pk = new BankCashTransferStatusPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���BankCashTransferStatusPK�I�u�W�F�N�g����{@@link BankCashTransferStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BankCashTransferStatusPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankCashTransferStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankCashTransferStatusRow findRowByPk( BankCashTransferStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankCashTransferStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static BankCashTransferStatusDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        BankCashTransferStatusPK pk = new BankCashTransferStatusPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        BankCashTransferStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BankCashTransferStatusPK)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static BankCashTransferStatusDao findDaoByPk( BankCashTransferStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankCashTransferStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BankCashTransferStatusDao}�ɕR�t��{@@link BankCashTransferStatusRow}���ŊO���L�[�̊֌W������{@@link CooperateBankMasterRow}���������܂��B 
   * 
   * @@return {@@link BankCashTransferStatusDao}�ƊO���L�[�̊֌W�ɂ���{@@link CooperateBankMasterRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public CooperateBankMasterRow fetchCooperateBankMasterRowViaPaySchemeId() throws DataNetworkException, DataFindException, DataQueryException  {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( row.getPaySchemeId() );
        Row row = CooperateBankMasterDao.findRowByPk( pk );
        if ( row != null && !(row instanceof CooperateBankMasterRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (CooperateBankMasterRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchCooperateBankMasterRowViaPaySchemeId()}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public CooperateBankMasterDao fetchCooperateBankMasterDaoViaPaySchemeId() throws DataNetworkException, DataFindException, DataQueryException  {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( row.getPaySchemeId() );
        DataAccessObject dao = CooperateBankMasterDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof CooperateBankMasterDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (CooperateBankMasterDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for CooperateBankMaster
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}���g�p���Ă��������B 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( dao.getCooperateBankMasterRow() );
    }


  /** 
   * {@@link CooperateBankMasterRow}�ƊO���L�[�̊֌W�ɂ���{@@link BankCashTransferStatusRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link CooperateBankMasterRow}�I�u�W�F�N�g 
   * @@return �w���{@@link CooperateBankMasterRow}�ɊO���L�[������{@@link BankCashTransferStatusRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( row.getPaySchemeId() );
    }


  /** 
   * {@@link CooperateBankMasterPK}�ƊO���L�[�̊֌W�ɂ���{@@link BankCashTransferStatusRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link CooperateBankMasterPK}�I�u�W�F�N�g 
   * @@return {@@link CooperateBankMasterPK}�ƊO���L�[����v����l������{@@link BankCashTransferStatusRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( pk.pay_scheme_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BankCashTransferStatusRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BankCashTransferStatusRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByPaySchemeId( String p_paySchemeId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BankCashTransferStatusRow.TYPE,
            "pay_scheme_id=?",
            null,
            new Object[] { p_paySchemeId } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for CooperateBankMaster
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByPaySchemeId(CooperateBankMasterPK)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( pk.pay_scheme_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByPaySchemeId(String)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPaySchemeId( String p_paySchemeId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( p_paySchemeId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_orderRequestNumber, and �ɂĎw��̒l�����ӂ�{@@link BankCashTransferStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_orderRequestNumber, and �̒l�ƈ�v����{@@link BankCashTransferStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BankCashTransferStatusRow findRowByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankCashTransferStatusRow.TYPE,
            "institution_code=? and branch_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankCashTransferStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankCashTransferStatusDao.findRowsByInstitutionCodeBranchCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeOrderRequestNumber(String, String, String)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static BankCashTransferStatusDao findDaoByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate, and �ɂĎw��̒l�Ɉ�v����{@@link BankCashTransferStatusRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * @@param p_baseDate �����Ώۂł���p_baseDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate, and �̒l�ƈ�v����{@@link BankCashTransferStatusRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_paySchemeId, java.sql.Timestamp p_baseDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BankCashTransferStatusRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and pay_scheme_id=? and base_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate(String, String, String, String, java.sql.Timestamp)}�����{@@link #forRow(BankCashTransferStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_paySchemeId, java.sql.Timestamp p_baseDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate( p_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate ) );
    }

}
@
