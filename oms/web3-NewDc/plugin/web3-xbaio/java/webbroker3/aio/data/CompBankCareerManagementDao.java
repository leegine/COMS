head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.41.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CompBankCareerManagementDao.java;


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
 * {@@link CompBankCareerManagementDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CompBankCareerManagementRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CompBankCareerManagementPK 
 * @@see CompBankCareerManagementRow 
 */
public class CompBankCareerManagementDao extends DataAccessObject {


  /** 
   * ����{@@link CompBankCareerManagementDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CompBankCareerManagementRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CompBankCareerManagementRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CompBankCareerManagementDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CompBankCareerManagementDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CompBankCareerManagementRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CompBankCareerManagementRow )
                return new CompBankCareerManagementDao( (CompBankCareerManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CompBankCareerManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CompBankCareerManagementRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CompBankCareerManagementRow}�I�u�W�F�N�g 
    */
    protected CompBankCareerManagementDao( CompBankCareerManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CompBankCareerManagementRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CompBankCareerManagementRow getCompBankCareerManagementRow() {
        return row;
    }


  /** 
   * �w���{@@link CompBankCareerManagementRow}�I�u�W�F�N�g����{@@link CompBankCareerManagementDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CompBankCareerManagementRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CompBankCareerManagementDao}�擾�̂��߂Ɏw���{@@link CompBankCareerManagementRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CompBankCareerManagementDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CompBankCareerManagementDao forRow( CompBankCareerManagementRow row ) throws java.lang.IllegalArgumentException {
        return (CompBankCareerManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CompBankCareerManagementRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CompBankCareerManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CompBankCareerManagementPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CompBankCareerManagementParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CompBankCareerManagementRow.TYPE );
    }


  /** 
   * {@@link CompBankCareerManagementRow}����ӂɓ��肷��{@@link CompBankCareerManagementPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CompBankCareerManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CompBankCareerManagementParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CompBankCareerManagementPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CompBankCareerManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CompBankCareerManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * @@param p_careerDiv �����Ώۂł���p_careerDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CompBankCareerManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CompBankCareerManagementRow findRowByPk( String p_institutionCode, String p_branchCode, String p_paySchemeId, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankCareerManagementPK pk = new CompBankCareerManagementPK( p_institutionCode, p_branchCode, p_paySchemeId, p_careerDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���CompBankCareerManagementPK�I�u�W�F�N�g����{@@link CompBankCareerManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CompBankCareerManagementPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CompBankCareerManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CompBankCareerManagementRow findRowByPk( CompBankCareerManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CompBankCareerManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(CompBankCareerManagementRow)}���g�p���Ă��������B 
   */
    public static CompBankCareerManagementDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_paySchemeId, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankCareerManagementPK pk = new CompBankCareerManagementPK( p_institutionCode, p_branchCode, p_paySchemeId, p_careerDiv );
        CompBankCareerManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CompBankCareerManagementPK)}�����{@@link #forRow(CompBankCareerManagementRow)}���g�p���Ă��������B 
   */
    public static CompBankCareerManagementDao findDaoByPk( CompBankCareerManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CompBankCareerManagementRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
