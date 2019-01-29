head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BatchPreferencesDao.java;


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
 * {@@link BatchPreferencesDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BatchPreferencesRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BatchPreferencesPK 
 * @@see BatchPreferencesRow 
 */
public class BatchPreferencesDao extends DataAccessObject {


  /** 
   * ����{@@link BatchPreferencesDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BatchPreferencesRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BatchPreferencesRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BatchPreferencesDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BatchPreferencesDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BatchPreferencesRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatchPreferencesRow )
                return new BatchPreferencesDao( (BatchPreferencesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatchPreferencesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatchPreferencesRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BatchPreferencesRow}�I�u�W�F�N�g 
    */
    protected BatchPreferencesDao( BatchPreferencesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BatchPreferencesRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BatchPreferencesRow getBatchPreferencesRow() {
        return row;
    }


  /** 
   * �w���{@@link BatchPreferencesRow}�I�u�W�F�N�g����{@@link BatchPreferencesDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BatchPreferencesRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BatchPreferencesDao}�擾�̂��߂Ɏw���{@@link BatchPreferencesRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BatchPreferencesDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BatchPreferencesDao forRow( BatchPreferencesRow row ) throws java.lang.IllegalArgumentException {
        return (BatchPreferencesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatchPreferencesRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BatchPreferencesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BatchPreferencesPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BatchPreferencesParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatchPreferencesRow.TYPE );
    }


  /** 
   * {@@link BatchPreferencesRow}����ӂɓ��肷��{@@link BatchPreferencesPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BatchPreferencesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BatchPreferencesParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BatchPreferencesPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BatchPreferencesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BatchPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * @@param p_nameSerialNo �����Ώۂł���p_nameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BatchPreferencesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BatchPreferencesRow findRowByPk( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchPreferencesPK pk = new BatchPreferencesPK( p_institutionCode, p_branchCode, p_name, p_nameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���BatchPreferencesPK�I�u�W�F�N�g����{@@link BatchPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BatchPreferencesPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BatchPreferencesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BatchPreferencesRow findRowByPk( BatchPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatchPreferencesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,int)}�����{@@link #forRow(BatchPreferencesRow)}���g�p���Ă��������B 
   */
    public static BatchPreferencesDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchPreferencesPK pk = new BatchPreferencesPK( p_institutionCode, p_branchCode, p_name, p_nameSerialNo );
        BatchPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BatchPreferencesPK)}�����{@@link #forRow(BatchPreferencesRow)}���g�p���Ă��������B 
   */
    public static BatchPreferencesDao findDaoByPk( BatchPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatchPreferencesRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_name, p_nameSerialNo, and �ɂĎw��̒l�����ӂ�{@@link BatchPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * @@param p_nameSerialNo �����Ώۂł���p_nameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_name, p_nameSerialNo, and �̒l�ƈ�v����{@@link BatchPreferencesRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BatchPreferencesRow findRowByInstitutionCodeBranchCodeNameNameSerialNo( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatchPreferencesRow.TYPE,
            "institution_code=? and branch_code=? and name=? and name_serial_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_name, new Integer(p_nameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatchPreferencesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatchPreferencesDao.findRowsByInstitutionCodeBranchCodeNameNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeNameNameSerialNo(String, String, String, int)}�����{@@link #forRow(BatchPreferencesRow)}���g�p���Ă��������B 
   */
    public static BatchPreferencesDao findDaoByInstitutionCodeBranchCodeNameNameSerialNo( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeNameNameSerialNo( p_institutionCode, p_branchCode, p_name, p_nameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
