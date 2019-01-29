head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	IfoDepositDao.java;


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
 * {@@link IfoDepositDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoDepositRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoDepositPK 
 * @@see IfoDepositRow 
 */
public class IfoDepositDao extends DataAccessObject {


  /** 
   * ����{@@link IfoDepositDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoDepositRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoDepositRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoDepositDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoDepositDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoDepositRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoDepositRow )
                return new IfoDepositDao( (IfoDepositRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoDepositRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoDepositRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoDepositRow}�I�u�W�F�N�g 
    */
    protected IfoDepositDao( IfoDepositRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoDepositRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoDepositRow getIfoDepositRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoDepositRow}�I�u�W�F�N�g����{@@link IfoDepositDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoDepositRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoDepositDao}�擾�̂��߂Ɏw���{@@link IfoDepositRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoDepositDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoDepositDao forRow( IfoDepositRow row ) throws java.lang.IllegalArgumentException {
        return (IfoDepositDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoDepositRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoDepositRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoDepositPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoDepositParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoDepositRow.TYPE );
    }


  /** 
   * {@@link IfoDepositRow}����ӂɓ��肷��{@@link IfoDepositPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoDepositRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoDepositParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoDepositPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoDepositPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoDepositRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_calcDate �����Ώۂł���p_calcDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoDepositRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoDepositRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_calcDate ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositPK pk = new IfoDepositPK( p_institutionCode, p_branchCode, p_accountCode, p_calcDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoDepositPK�I�u�W�F�N�g����{@@link IfoDepositRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoDepositPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoDepositRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoDepositRow findRowByPk( IfoDepositPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoDepositRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,java.sql.Timestamp)}�����{@@link #forRow(IfoDepositRow)}���g�p���Ă��������B 
   */
    public static IfoDepositDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_calcDate ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositPK pk = new IfoDepositPK( p_institutionCode, p_branchCode, p_accountCode, p_calcDate );
        IfoDepositRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoDepositPK)}�����{@@link #forRow(IfoDepositRow)}���g�p���Ă��������B 
   */
    public static IfoDepositDao findDaoByPk( IfoDepositPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_calcDate, and �ɂĎw��̒l�����ӂ�{@@link IfoDepositRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_calcDate �����Ώۂł���p_calcDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_calcDate, and �̒l�ƈ�v����{@@link IfoDepositRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoDepositRow findRowByInstitutionCodeBranchCodeAccountCodeCalcDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_calcDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoDepositRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and calc_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_calcDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoDepositRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoDepositDao.findRowsByInstitutionCodeBranchCodeAccountCodeCalcDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeCalcDate(String, String, String, java.sql.Timestamp)}�����{@@link #forRow(IfoDepositRow)}���g�p���Ă��������B 
   */
    public static IfoDepositDao findDaoByInstitutionCodeBranchCodeAccountCodeCalcDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_calcDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeCalcDate( p_institutionCode, p_branchCode, p_accountCode, p_calcDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
