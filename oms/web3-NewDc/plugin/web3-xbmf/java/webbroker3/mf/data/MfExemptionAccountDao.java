head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfExemptionAccountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MfExemptionAccountDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MfExemptionAccountRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MfExemptionAccountPK 
 * @@see MfExemptionAccountRow 
 */
public class MfExemptionAccountDao extends DataAccessObject {


  /** 
   * ����{@@link MfExemptionAccountDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MfExemptionAccountRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MfExemptionAccountRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MfExemptionAccountDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MfExemptionAccountDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MfExemptionAccountRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfExemptionAccountRow )
                return new MfExemptionAccountDao( (MfExemptionAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfExemptionAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfExemptionAccountRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MfExemptionAccountRow}�I�u�W�F�N�g 
    */
    protected MfExemptionAccountDao( MfExemptionAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MfExemptionAccountRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MfExemptionAccountRow getMfExemptionAccountRow() {
        return row;
    }


  /** 
   * �w���{@@link MfExemptionAccountRow}�I�u�W�F�N�g����{@@link MfExemptionAccountDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MfExemptionAccountRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MfExemptionAccountDao}�擾�̂��߂Ɏw���{@@link MfExemptionAccountRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MfExemptionAccountDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MfExemptionAccountDao forRow( MfExemptionAccountRow row ) throws java.lang.IllegalArgumentException {
        return (MfExemptionAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfExemptionAccountRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MfExemptionAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MfExemptionAccountPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MfExemptionAccountParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfExemptionAccountRow.TYPE );
    }


  /** 
   * {@@link MfExemptionAccountRow}����ӂɓ��肷��{@@link MfExemptionAccountPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MfExemptionAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MfExemptionAccountParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MfExemptionAccountPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MfExemptionAccountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MfExemptionAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_exemptionDiv �����Ώۂł���p_exemptionDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfExemptionAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfExemptionAccountRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MfExemptionAccountPK pk = new MfExemptionAccountPK( p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���MfExemptionAccountPK�I�u�W�F�N�g����{@@link MfExemptionAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MfExemptionAccountPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfExemptionAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfExemptionAccountRow findRowByPk( MfExemptionAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfExemptionAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(MfExemptionAccountRow)}���g�p���Ă��������B 
   */
    public static MfExemptionAccountDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        MfExemptionAccountPK pk = new MfExemptionAccountPK( p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv );
        MfExemptionAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MfExemptionAccountPK)}�����{@@link #forRow(MfExemptionAccountRow)}���g�p���Ă��������B 
   */
    public static MfExemptionAccountDao findDaoByPk( MfExemptionAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfExemptionAccountRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv, and �ɂĎw��̒l�����ӂ�{@@link MfExemptionAccountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_exemptionDiv �����Ώۂł���p_exemptionDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv, and �̒l�ƈ�v����{@@link MfExemptionAccountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MfExemptionAccountRow findRowByInstitutionCodeBranchCodeAccountCodeExemptionDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfExemptionAccountRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and exemption_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfExemptionAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfExemptionAccountDao.findRowsByInstitutionCodeBranchCodeAccountCodeExemptionDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeExemptionDiv(String, String, String, String)}�����{@@link #forRow(MfExemptionAccountRow)}���g�p���Ă��������B 
   */
    public static MfExemptionAccountDao findDaoByInstitutionCodeBranchCodeAccountCodeExemptionDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_exemptionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeExemptionDiv( p_institutionCode, p_branchCode, p_accountCode, p_exemptionDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
