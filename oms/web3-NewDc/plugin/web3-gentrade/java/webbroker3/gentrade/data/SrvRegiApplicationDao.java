head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SrvRegiApplicationDao.java;


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
 * {@@link SrvRegiApplicationDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SrvRegiApplicationRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SrvRegiApplicationPK 
 * @@see SrvRegiApplicationRow 
 */
public class SrvRegiApplicationDao extends DataAccessObject {


  /** 
   * ����{@@link SrvRegiApplicationDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SrvRegiApplicationRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SrvRegiApplicationRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SrvRegiApplicationDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SrvRegiApplicationDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SrvRegiApplicationRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiApplicationRow )
                return new SrvRegiApplicationDao( (SrvRegiApplicationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiApplicationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiApplicationRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SrvRegiApplicationRow}�I�u�W�F�N�g 
    */
    protected SrvRegiApplicationDao( SrvRegiApplicationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SrvRegiApplicationRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SrvRegiApplicationRow getSrvRegiApplicationRow() {
        return row;
    }


  /** 
   * �w���{@@link SrvRegiApplicationRow}�I�u�W�F�N�g����{@@link SrvRegiApplicationDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SrvRegiApplicationRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SrvRegiApplicationDao}�擾�̂��߂Ɏw���{@@link SrvRegiApplicationRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SrvRegiApplicationDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SrvRegiApplicationDao forRow( SrvRegiApplicationRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiApplicationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiApplicationRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SrvRegiApplicationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SrvRegiApplicationPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SrvRegiApplicationParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiApplicationRow.TYPE );
    }


  /** 
   * {@@link SrvRegiApplicationRow}����ӂɓ��肷��{@@link SrvRegiApplicationPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SrvRegiApplicationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SrvRegiApplicationParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SrvRegiApplicationPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SrvRegiApplicationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SrvRegiApplicationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_registId �����Ώۂł���p_registId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiApplicationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiApplicationRow findRowByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiApplicationPK pk = new SrvRegiApplicationPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SrvRegiApplicationPK�I�u�W�F�N�g����{@@link SrvRegiApplicationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SrvRegiApplicationPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiApplicationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiApplicationRow findRowByPk( SrvRegiApplicationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiApplicationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,long)}�����{@@link #forRow(SrvRegiApplicationRow)}���g�p���Ă��������B 
   */
    public static SrvRegiApplicationDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiApplicationPK pk = new SrvRegiApplicationPK( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId );
        SrvRegiApplicationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SrvRegiApplicationPK)}�����{@@link #forRow(SrvRegiApplicationRow)}���g�p���Ă��������B 
   */
    public static SrvRegiApplicationDao findDaoByPk( SrvRegiApplicationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiApplicationRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId, and �ɂĎw��̒l�����ӂ�{@@link SrvRegiApplicationRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_registId �����Ώۂł���p_registId�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId, and �̒l�ƈ�v����{@@link SrvRegiApplicationRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SrvRegiApplicationRow findRowByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiApplicationRow.TYPE,
            "institution_code=? and branch_code=? and srv_div=? and account_code=? and regist_id=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, new Long(p_registId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiApplicationRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiApplicationDao.findRowsByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId(String, String, String, String, long)}�����{@@link #forRow(SrvRegiApplicationRow)}���g�p���Ă��������B 
   */
    public static SrvRegiApplicationDao findDaoByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId( String p_institutionCode, String p_branchCode, String p_srvDiv, String p_accountCode, long p_registId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSrvDivAccountCodeRegistId( p_institutionCode, p_branchCode, p_srvDiv, p_accountCode, p_registId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
