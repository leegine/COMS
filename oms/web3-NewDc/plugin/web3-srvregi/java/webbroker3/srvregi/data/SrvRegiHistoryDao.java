head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.srvregi.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SrvRegiHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SrvRegiHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SrvRegiHistoryPK 
 * @@see SrvRegiHistoryRow 
 */
public class SrvRegiHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link SrvRegiHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SrvRegiHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SrvRegiHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SrvRegiHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SrvRegiHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SrvRegiHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiHistoryRow )
                return new SrvRegiHistoryDao( (SrvRegiHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SrvRegiHistoryRow}�I�u�W�F�N�g 
    */
    protected SrvRegiHistoryDao( SrvRegiHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SrvRegiHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SrvRegiHistoryRow getSrvRegiHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link SrvRegiHistoryRow}�I�u�W�F�N�g����{@@link SrvRegiHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SrvRegiHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SrvRegiHistoryDao}�擾�̂��߂Ɏw���{@@link SrvRegiHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SrvRegiHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SrvRegiHistoryDao forRow( SrvRegiHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SrvRegiHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SrvRegiHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SrvRegiHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiHistoryRow.TYPE );
    }


  /** 
   * {@@link SrvRegiHistoryRow}����ӂɓ��肷��{@@link SrvRegiHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SrvRegiHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SrvRegiHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SrvRegiHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SrvRegiHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SrvRegiHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiHistoryRow findRowByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiHistoryPK pk = new SrvRegiHistoryPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���SrvRegiHistoryPK�I�u�W�F�N�g����{@@link SrvRegiHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SrvRegiHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiHistoryRow findRowByPk( SrvRegiHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(SrvRegiHistoryRow)}���g�p���Ă��������B 
   */
    public static SrvRegiHistoryDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiHistoryPK pk = new SrvRegiHistoryPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode );
        SrvRegiHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SrvRegiHistoryPK)}�����{@@link #forRow(SrvRegiHistoryRow)}���g�p���Ă��������B 
   */
    public static SrvRegiHistoryDao findDaoByPk( SrvRegiHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiHistoryRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, and �ɂĎw��̒l�����ӂ�{@@link SrvRegiHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, and �̒l�ƈ�v����{@@link SrvRegiHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SrvRegiHistoryRow findRowByInstitutionCodeBranchCodeSrvDivAccountCode( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiHistoryRow.TYPE,
            "institution_code=? and branch_code=? and srv_div=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_srvDiv, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiHistoryDao.findRowsByInstitutionCodeBranchCodeSrvDivAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeSrvDivAccountCode(String, String, String, String)}�����{@@link #forRow(SrvRegiHistoryRow)}���g�p���Ă��������B 
   */
    public static SrvRegiHistoryDao findDaoByInstitutionCodeBranchCodeSrvDivAccountCode( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSrvDivAccountCode( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
