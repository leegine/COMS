head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	OtherOrgInfoAdminDao.java;


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
 * {@@link OtherOrgInfoAdminDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OtherOrgInfoAdminRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OtherOrgInfoAdminPK 
 * @@see OtherOrgInfoAdminRow 
 */
public class OtherOrgInfoAdminDao extends DataAccessObject {


  /** 
   * ����{@@link OtherOrgInfoAdminDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OtherOrgInfoAdminRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OtherOrgInfoAdminRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OtherOrgInfoAdminDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OtherOrgInfoAdminDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OtherOrgInfoAdminRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrgInfoAdminRow )
                return new OtherOrgInfoAdminDao( (OtherOrgInfoAdminRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrgInfoAdminRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrgInfoAdminRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g 
    */
    protected OtherOrgInfoAdminDao( OtherOrgInfoAdminRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OtherOrgInfoAdminRow getOtherOrgInfoAdminRow() {
        return row;
    }


  /** 
   * �w���{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g����{@@link OtherOrgInfoAdminDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OtherOrgInfoAdminRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OtherOrgInfoAdminDao}�擾�̂��߂Ɏw���{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OtherOrgInfoAdminDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OtherOrgInfoAdminDao forRow( OtherOrgInfoAdminRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrgInfoAdminDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrgInfoAdminRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OtherOrgInfoAdminRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OtherOrgInfoAdminPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OtherOrgInfoAdminParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrgInfoAdminRow.TYPE );
    }


  /** 
   * {@@link OtherOrgInfoAdminRow}����ӂɓ��肷��{@@link OtherOrgInfoAdminPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OtherOrgInfoAdminRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OtherOrgInfoAdminParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OtherOrgInfoAdminPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OtherOrgInfoAdminPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_sequenceNumber �����Ώۂł���p_sequenceNumber�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OtherOrgInfoAdminRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OtherOrgInfoAdminRow findRowByPk( long p_sequenceNumber, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgInfoAdminPK pk = new OtherOrgInfoAdminPK( p_sequenceNumber, p_srvDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���OtherOrgInfoAdminPK�I�u�W�F�N�g����{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OtherOrgInfoAdminPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OtherOrgInfoAdminRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OtherOrgInfoAdminRow findRowByPk( OtherOrgInfoAdminPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrgInfoAdminRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String)}�����{@@link #forRow(OtherOrgInfoAdminRow)}���g�p���Ă��������B 
   */
    public static OtherOrgInfoAdminDao findDaoByPk( long p_sequenceNumber, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgInfoAdminPK pk = new OtherOrgInfoAdminPK( p_sequenceNumber, p_srvDiv );
        OtherOrgInfoAdminRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OtherOrgInfoAdminPK)}�����{@@link #forRow(OtherOrgInfoAdminRow)}���g�p���Ă��������B 
   */
    public static OtherOrgInfoAdminDao findDaoByPk( OtherOrgInfoAdminPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgInfoAdminRow row = findRowByPk( pk );
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
   * p_sequenceNumber, p_srvDiv, and �ɂĎw��̒l�����ӂ�{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_sequenceNumber �����Ώۂł���p_sequenceNumber�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_sequenceNumber, p_srvDiv, and �̒l�ƈ�v����{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OtherOrgInfoAdminRow findRowBySequenceNumberSrvDiv( long p_sequenceNumber, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrgInfoAdminRow.TYPE,
            "sequence_number=? and srv_div=?",
            null,
            new Object[] { new Long(p_sequenceNumber), p_srvDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrgInfoAdminRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrgInfoAdminDao.findRowsBySequenceNumberSrvDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowBySequenceNumberSrvDiv(long, String)}�����{@@link #forRow(OtherOrgInfoAdminRow)}���g�p���Ă��������B 
   */
    public static OtherOrgInfoAdminDao findDaoBySequenceNumberSrvDiv( long p_sequenceNumber, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySequenceNumberSrvDiv( p_sequenceNumber, p_srvDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode, and �ɂĎw��̒l�Ɉ�v����{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode, and �̒l�ƈ�v����{@@link OtherOrgInfoAdminRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsBySrvDivStatusInstitutionCodeBranchCodeAccountCode( String p_srvDiv, String p_status, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            OtherOrgInfoAdminRow.TYPE,
            "srv_div=? and status=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsBySrvDivStatusInstitutionCodeBranchCodeAccountCode(String, String, String, String, String)}�����{@@link #forRow(OtherOrgInfoAdminRow)}���g�p���Ă��������B 
   */
    public static List findDaosBySrvDivStatusInstitutionCodeBranchCodeAccountCode( String p_srvDiv, String p_status, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsBySrvDivStatusInstitutionCodeBranchCodeAccountCode( p_srvDiv, p_status, p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
