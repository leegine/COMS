head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocCategoryManagementDao.java;


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
 * {@@link DocCategoryManagementDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DocCategoryManagementRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DocCategoryManagementPK 
 * @@see DocCategoryManagementRow 
 */
public class DocCategoryManagementDao extends DataAccessObject {


  /** 
   * ����{@@link DocCategoryManagementDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DocCategoryManagementRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DocCategoryManagementRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DocCategoryManagementDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DocCategoryManagementDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DocCategoryManagementRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocCategoryManagementRow )
                return new DocCategoryManagementDao( (DocCategoryManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocCategoryManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocCategoryManagementRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DocCategoryManagementRow}�I�u�W�F�N�g 
    */
    protected DocCategoryManagementDao( DocCategoryManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DocCategoryManagementRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DocCategoryManagementRow getDocCategoryManagementRow() {
        return row;
    }


  /** 
   * �w���{@@link DocCategoryManagementRow}�I�u�W�F�N�g����{@@link DocCategoryManagementDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DocCategoryManagementRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DocCategoryManagementDao}�擾�̂��߂Ɏw���{@@link DocCategoryManagementRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DocCategoryManagementDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DocCategoryManagementDao forRow( DocCategoryManagementRow row ) throws java.lang.IllegalArgumentException {
        return (DocCategoryManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocCategoryManagementRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DocCategoryManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DocCategoryManagementPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DocCategoryManagementParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocCategoryManagementRow.TYPE );
    }


  /** 
   * {@@link DocCategoryManagementRow}����ӂɓ��肷��{@@link DocCategoryManagementPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DocCategoryManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DocCategoryManagementParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DocCategoryManagementPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DocCategoryManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DocCategoryManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocCategoryManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocCategoryManagementRow findRowByPk( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocCategoryManagementPK pk = new DocCategoryManagementPK( p_institutionCode, p_branchCode, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * �w���DocCategoryManagementPK�I�u�W�F�N�g����{@@link DocCategoryManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DocCategoryManagementPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocCategoryManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocCategoryManagementRow findRowByPk( DocCategoryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocCategoryManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(DocCategoryManagementRow)}���g�p���Ă��������B 
   */
    public static DocCategoryManagementDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocCategoryManagementPK pk = new DocCategoryManagementPK( p_institutionCode, p_branchCode, p_documentCategory );
        DocCategoryManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DocCategoryManagementPK)}�����{@@link #forRow(DocCategoryManagementRow)}���g�p���Ă��������B 
   */
    public static DocCategoryManagementDao findDaoByPk( DocCategoryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocCategoryManagementRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_documentCategory, and �ɂĎw��̒l�����ӂ�{@@link DocCategoryManagementRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_documentCategory, and �̒l�ƈ�v����{@@link DocCategoryManagementRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocCategoryManagementRow findRowByInstitutionCodeBranchCodeDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocCategoryManagementRow.TYPE,
            "institution_code=? and branch_code=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocCategoryManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocCategoryManagementDao.findRowsByInstitutionCodeBranchCodeDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeDocumentCategory(String, String, String)}�����{@@link #forRow(DocCategoryManagementRow)}���g�p���Ă��������B 
   */
    public static DocCategoryManagementDao findDaoByInstitutionCodeBranchCodeDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDocumentCategory( p_institutionCode, p_branchCode, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
