head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TransferedFinInstitutionDao.java;


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
 * {@@link TransferedFinInstitutionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TransferedFinInstitutionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TransferedFinInstitutionPK 
 * @@see TransferedFinInstitutionRow 
 */
public class TransferedFinInstitutionDao extends DataAccessObject {


  /** 
   * ����{@@link TransferedFinInstitutionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TransferedFinInstitutionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TransferedFinInstitutionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TransferedFinInstitutionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TransferedFinInstitutionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TransferedFinInstitutionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TransferedFinInstitutionRow )
                return new TransferedFinInstitutionDao( (TransferedFinInstitutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TransferedFinInstitutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TransferedFinInstitutionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g 
    */
    protected TransferedFinInstitutionDao( TransferedFinInstitutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TransferedFinInstitutionRow getTransferedFinInstitutionRow() {
        return row;
    }


  /** 
   * �w���{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g����{@@link TransferedFinInstitutionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TransferedFinInstitutionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TransferedFinInstitutionDao}�擾�̂��߂Ɏw���{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TransferedFinInstitutionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TransferedFinInstitutionDao forRow( TransferedFinInstitutionRow row ) throws java.lang.IllegalArgumentException {
        return (TransferedFinInstitutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TransferedFinInstitutionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TransferedFinInstitutionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TransferedFinInstitutionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TransferedFinInstitutionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TransferedFinInstitutionRow.TYPE );
    }


  /** 
   * {@@link TransferedFinInstitutionRow}����ӂɓ��肷��{@@link TransferedFinInstitutionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TransferedFinInstitutionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TransferedFinInstitutionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TransferedFinInstitutionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TransferedFinInstitutionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_designateDiv �����Ώۂł���p_designateDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TransferedFinInstitutionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TransferedFinInstitutionRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        TransferedFinInstitutionPK pk = new TransferedFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���TransferedFinInstitutionPK�I�u�W�F�N�g����{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TransferedFinInstitutionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TransferedFinInstitutionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TransferedFinInstitutionRow findRowByPk( TransferedFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TransferedFinInstitutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(TransferedFinInstitutionRow)}���g�p���Ă��������B 
   */
    public static TransferedFinInstitutionDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        TransferedFinInstitutionPK pk = new TransferedFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv );
        TransferedFinInstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TransferedFinInstitutionPK)}�����{@@link #forRow(TransferedFinInstitutionRow)}���g�p���Ă��������B 
   */
    public static TransferedFinInstitutionDao findDaoByPk( TransferedFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TransferedFinInstitutionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, and �ɂĎw��̒l�����ӂ�{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_designateDiv �����Ώۂł���p_designateDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, and �̒l�ƈ�v����{@@link TransferedFinInstitutionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TransferedFinInstitutionRow findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TransferedFinInstitutionRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and designate_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_designateDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TransferedFinInstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TransferedFinInstitutionDao.findRowsByInstitutionCodeBranchCodeAccountCodeDesignateDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv(String, String, String, String)}�����{@@link #forRow(TransferedFinInstitutionRow)}���g�p���Ă��������B 
   */
    public static TransferedFinInstitutionDao findDaoByInstitutionCodeBranchCodeAccountCodeDesignateDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
