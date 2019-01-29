head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.38.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CompBankConditionDao.java;


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
 * {@@link CompBankConditionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CompBankConditionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CompBankConditionPK 
 * @@see CompBankConditionRow 
 */
public class CompBankConditionDao extends DataAccessObject {


  /** 
   * ����{@@link CompBankConditionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CompBankConditionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CompBankConditionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CompBankConditionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CompBankConditionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CompBankConditionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CompBankConditionRow )
                return new CompBankConditionDao( (CompBankConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CompBankConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CompBankConditionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CompBankConditionRow}�I�u�W�F�N�g 
    */
    protected CompBankConditionDao( CompBankConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CompBankConditionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CompBankConditionRow getCompBankConditionRow() {
        return row;
    }


  /** 
   * �w���{@@link CompBankConditionRow}�I�u�W�F�N�g����{@@link CompBankConditionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CompBankConditionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CompBankConditionDao}�擾�̂��߂Ɏw���{@@link CompBankConditionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CompBankConditionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CompBankConditionDao forRow( CompBankConditionRow row ) throws java.lang.IllegalArgumentException {
        return (CompBankConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CompBankConditionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CompBankConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CompBankConditionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CompBankConditionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CompBankConditionRow.TYPE );
    }


  /** 
   * {@@link CompBankConditionRow}����ӂɓ��肷��{@@link CompBankConditionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CompBankConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CompBankConditionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CompBankConditionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CompBankConditionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CompBankConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CompBankConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CompBankConditionRow findRowByPk( String p_institutionCode, String p_branchCode, String p_paySchemeId ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankConditionPK pk = new CompBankConditionPK( p_institutionCode, p_branchCode, p_paySchemeId );
        return findRowByPk( pk );
    }


  /** 
   * �w���CompBankConditionPK�I�u�W�F�N�g����{@@link CompBankConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CompBankConditionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CompBankConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CompBankConditionRow findRowByPk( CompBankConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CompBankConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
   */
    public static CompBankConditionDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_paySchemeId ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankConditionPK pk = new CompBankConditionPK( p_institutionCode, p_branchCode, p_paySchemeId );
        CompBankConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CompBankConditionPK)}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
   */
    public static CompBankConditionDao findDaoByPk( CompBankConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link CompBankConditionDao}�ɕR�t��{@@link CompBankConditionRow}���ŊO���L�[�̊֌W������{@@link CooperateBankMasterRow}���������܂��B 
   * 
   * @@return {@@link CompBankConditionDao}�ƊO���L�[�̊֌W�ɂ���{@@link CooperateBankMasterRow} 
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
   * @@deprecated �����{@@link #fetchCooperateBankMasterRowViaPaySchemeId()}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
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
   * {@@link CooperateBankMasterRow}�ƊO���L�[�̊֌W�ɂ���{@@link CompBankConditionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link CooperateBankMasterRow}�I�u�W�F�N�g 
   * @@return �w���{@@link CooperateBankMasterRow}�ɊO���L�[������{@@link CompBankConditionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( row.getPaySchemeId() );
    }


  /** 
   * {@@link CooperateBankMasterPK}�ƊO���L�[�̊֌W�ɂ���{@@link CompBankConditionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link CooperateBankMasterPK}�I�u�W�F�N�g 
   * @@return {@@link CooperateBankMasterPK}�ƊO���L�[����v����l������{@@link CompBankConditionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( pk.pay_scheme_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link CompBankConditionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link CompBankConditionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByPaySchemeId( String p_paySchemeId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CompBankConditionRow.TYPE,
            "pay_scheme_id=?",
            null,
            new Object[] { p_paySchemeId } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for CooperateBankMaster
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByPaySchemeId(CooperateBankMasterPK)}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( pk.pay_scheme_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByPaySchemeId(String)}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
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
   * p_institutionCode, p_branchCode, p_paySchemeId, and �ɂĎw��̒l�����ӂ�{@@link CompBankConditionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_paySchemeId, and �̒l�ƈ�v����{@@link CompBankConditionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CompBankConditionRow findRowByInstitutionCodeBranchCodePaySchemeId( String p_institutionCode, String p_branchCode, String p_paySchemeId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CompBankConditionRow.TYPE,
            "institution_code=? and branch_code=? and pay_scheme_id=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_paySchemeId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CompBankConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CompBankConditionDao.findRowsByInstitutionCodeBranchCodePaySchemeId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodePaySchemeId(String, String, String)}�����{@@link #forRow(CompBankConditionRow)}���g�p���Ă��������B 
   */
    public static CompBankConditionDao findDaoByInstitutionCodeBranchCodePaySchemeId( String p_institutionCode, String p_branchCode, String p_paySchemeId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodePaySchemeId( p_institutionCode, p_branchCode, p_paySchemeId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
