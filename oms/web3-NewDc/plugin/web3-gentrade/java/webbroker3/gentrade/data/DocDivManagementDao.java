head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDivManagementDao.java;


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
 * {@@link DocDivManagementDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DocDivManagementRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DocDivManagementPK 
 * @@see DocDivManagementRow 
 */
public class DocDivManagementDao extends DataAccessObject {


  /** 
   * ����{@@link DocDivManagementDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DocDivManagementRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DocDivManagementRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DocDivManagementDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DocDivManagementDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DocDivManagementRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocDivManagementRow )
                return new DocDivManagementDao( (DocDivManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocDivManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocDivManagementRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DocDivManagementRow}�I�u�W�F�N�g 
    */
    protected DocDivManagementDao( DocDivManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DocDivManagementRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DocDivManagementRow getDocDivManagementRow() {
        return row;
    }


  /** 
   * �w���{@@link DocDivManagementRow}�I�u�W�F�N�g����{@@link DocDivManagementDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DocDivManagementRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DocDivManagementDao}�擾�̂��߂Ɏw���{@@link DocDivManagementRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DocDivManagementDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DocDivManagementDao forRow( DocDivManagementRow row ) throws java.lang.IllegalArgumentException {
        return (DocDivManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocDivManagementRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DocDivManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DocDivManagementPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DocDivManagementParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocDivManagementRow.TYPE );
    }


  /** 
   * {@@link DocDivManagementRow}����ӂɓ��肷��{@@link DocDivManagementPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DocDivManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DocDivManagementParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DocDivManagementPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DocDivManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DocDivManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocDivManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocDivManagementRow findRowByPk( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDivManagementPK pk = new DocDivManagementPK( p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * �w���DocDivManagementPK�I�u�W�F�N�g����{@@link DocDivManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DocDivManagementPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocDivManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocDivManagementRow findRowByPk( DocDivManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocDivManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(DocDivManagementRow)}���g�p���Ă��������B 
   */
    public static DocDivManagementDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDivManagementPK pk = new DocDivManagementPK( p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory );
        DocDivManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DocDivManagementPK)}�����{@@link #forRow(DocDivManagementRow)}���g�p���Ă��������B 
   */
    public static DocDivManagementDao findDaoByPk( DocDivManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDivManagementRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory, and �ɂĎw��̒l�����ӂ�{@@link DocDivManagementRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory, and �̒l�ƈ�v����{@@link DocDivManagementRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocDivManagementRow findRowByInstitutionCodeBranchCodeDocumentDivDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDivManagementRow.TYPE,
            "institution_code=? and branch_code=? and document_div=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDivManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDivManagementDao.findRowsByInstitutionCodeBranchCodeDocumentDivDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeDocumentDivDocumentCategory(String, String, String, String)}�����{@@link #forRow(DocDivManagementRow)}���g�p���Ă��������B 
   */
    public static DocDivManagementDao findDaoByInstitutionCodeBranchCodeDocumentDivDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentDiv, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDocumentDivDocumentCategory( p_institutionCode, p_branchCode, p_documentDiv, p_documentCategory ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory, and �ɂĎw��̒l�����ӂ�{@@link DocDivManagementRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_documentCheckDiv �����Ώۂł���p_documentCheckDiv�t�B�[���h�̒l
   * @@param p_documentNumber �����Ώۂł���p_documentNumber�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory, and �̒l�ƈ�v����{@@link DocDivManagementRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocDivManagementRow findRowByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCheckDiv, String p_documentNumber, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDivManagementRow.TYPE,
            "institution_code=? and branch_code=? and document_check_div=? and document_number=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDivManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDivManagementDao.findRowsByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory(String, String, String, String, String)}�����{@@link #forRow(DocDivManagementRow)}���g�p���Ă��������B 
   */
    public static DocDivManagementDao findDaoByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory( String p_institutionCode, String p_branchCode, String p_documentCheckDiv, String p_documentNumber, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeDocumentCheckDivDocumentNumberDocumentCategory( p_institutionCode, p_branchCode, p_documentCheckDiv, p_documentNumber, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@