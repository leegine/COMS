head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.14.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenAccountCodeDao.java;


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
 * {@@link AccOpenAccountCodeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenAccountCodeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenAccountCodePK 
 * @@see AccOpenAccountCodeRow 
 */
public class AccOpenAccountCodeDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenAccountCodeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenAccountCodeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenAccountCodeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenAccountCodeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenAccountCodeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenAccountCodeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenAccountCodeRow )
                return new AccOpenAccountCodeDao( (AccOpenAccountCodeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenAccountCodeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenAccountCodeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g 
    */
    protected AccOpenAccountCodeDao( AccOpenAccountCodeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenAccountCodeRow getAccOpenAccountCodeRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g����{@@link AccOpenAccountCodeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenAccountCodeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenAccountCodeDao}�擾�̂��߂Ɏw���{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenAccountCodeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenAccountCodeDao forRow( AccOpenAccountCodeRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenAccountCodeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenAccountCodeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenAccountCodeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenAccountCodePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenAccountCodeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenAccountCodeRow.TYPE );
    }


  /** 
   * {@@link AccOpenAccountCodeRow}����ӂɓ��肷��{@@link AccOpenAccountCodePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenAccountCodeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenAccountCodeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenAccountCodePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenAccountCodePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenAccountCodeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenAccountCodeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenAccountCodePK pk = new AccOpenAccountCodePK( p_institutionCode, p_branchCode, p_accountDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenAccountCodePK�I�u�W�F�N�g����{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenAccountCodePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenAccountCodeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenAccountCodeRow findRowByPk( AccOpenAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenAccountCodeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(AccOpenAccountCodeRow)}���g�p���Ă��������B 
   */
    public static AccOpenAccountCodeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenAccountCodePK pk = new AccOpenAccountCodePK( p_institutionCode, p_branchCode, p_accountDiv );
        AccOpenAccountCodeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenAccountCodePK)}�����{@@link #forRow(AccOpenAccountCodeRow)}���g�p���Ă��������B 
   */
    public static AccOpenAccountCodeDao findDaoByPk( AccOpenAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenAccountCodeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, and �ɂĎw��̒l�����ӂ�{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountDiv, and �̒l�ƈ�v����{@@link AccOpenAccountCodeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenAccountCodeRow findRowByInstitutionCodeBranchCodeAccountDiv( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and account_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenAccountCodeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenAccountCodeDao.findRowsByInstitutionCodeBranchCodeAccountDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountDiv(String, String, String)}�����{@@link #forRow(AccOpenAccountCodeRow)}���g�p���Ă��������B 
   */
    public static AccOpenAccountCodeDao findDaoByInstitutionCodeBranchCodeAccountDiv( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDiv( p_institutionCode, p_branchCode, p_accountDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
