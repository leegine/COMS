head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvAppliAttributeDao.java;


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
 * {@@link SrvAppliAttributeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SrvAppliAttributeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SrvAppliAttributePK 
 * @@see SrvAppliAttributeRow 
 */
public class SrvAppliAttributeDao extends DataAccessObject {


  /** 
   * ����{@@link SrvAppliAttributeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SrvAppliAttributeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SrvAppliAttributeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SrvAppliAttributeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SrvAppliAttributeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SrvAppliAttributeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvAppliAttributeRow )
                return new SrvAppliAttributeDao( (SrvAppliAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvAppliAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvAppliAttributeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SrvAppliAttributeRow}�I�u�W�F�N�g 
    */
    protected SrvAppliAttributeDao( SrvAppliAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SrvAppliAttributeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SrvAppliAttributeRow getSrvAppliAttributeRow() {
        return row;
    }


  /** 
   * �w���{@@link SrvAppliAttributeRow}�I�u�W�F�N�g����{@@link SrvAppliAttributeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SrvAppliAttributeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SrvAppliAttributeDao}�擾�̂��߂Ɏw���{@@link SrvAppliAttributeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SrvAppliAttributeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SrvAppliAttributeDao forRow( SrvAppliAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (SrvAppliAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvAppliAttributeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SrvAppliAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SrvAppliAttributePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SrvAppliAttributeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvAppliAttributeRow.TYPE );
    }


  /** 
   * {@@link SrvAppliAttributeRow}����ӂɓ��肷��{@@link SrvAppliAttributePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SrvAppliAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SrvAppliAttributeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SrvAppliAttributePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SrvAppliAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SrvAppliAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvAppliAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvAppliAttributeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvAppliAttributePK pk = new SrvAppliAttributePK( p_institutionCode, p_branchCode, p_accountCode, p_srvDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���SrvAppliAttributePK�I�u�W�F�N�g����{@@link SrvAppliAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SrvAppliAttributePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvAppliAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvAppliAttributeRow findRowByPk( SrvAppliAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvAppliAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(SrvAppliAttributeRow)}���g�p���Ă��������B 
   */
    public static SrvAppliAttributeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvAppliAttributePK pk = new SrvAppliAttributePK( p_institutionCode, p_branchCode, p_accountCode, p_srvDiv );
        SrvAppliAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SrvAppliAttributePK)}�����{@@link #forRow(SrvAppliAttributeRow)}���g�p���Ă��������B 
   */
    public static SrvAppliAttributeDao findDaoByPk( SrvAppliAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvAppliAttributeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_srvDiv, and �ɂĎw��̒l�����ӂ�{@@link SrvAppliAttributeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_srvDiv, and �̒l�ƈ�v����{@@link SrvAppliAttributeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SrvAppliAttributeRow findRowByInstitutionCodeBranchCodeAccountCodeSrvDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvAppliAttributeRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and srv_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_srvDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvAppliAttributeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvAppliAttributeDao.findRowsByInstitutionCodeBranchCodeAccountCodeSrvDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeSrvDiv(String, String, String, String)}�����{@@link #forRow(SrvAppliAttributeRow)}���g�p���Ă��������B 
   */
    public static SrvAppliAttributeDao findDaoByInstitutionCodeBranchCodeAccountCodeSrvDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeSrvDiv( p_institutionCode, p_branchCode, p_accountCode, p_srvDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
