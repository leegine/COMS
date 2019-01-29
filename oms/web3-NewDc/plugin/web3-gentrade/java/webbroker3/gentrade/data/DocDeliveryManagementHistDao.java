head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDeliveryManagementHistDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link DocDeliveryManagementHistDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DocDeliveryManagementHistRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DocDeliveryManagementHistPK 
 * @@see DocDeliveryManagementHistRow 
 */
public class DocDeliveryManagementHistDao extends DataAccessObject {


  /** 
   * ����{@@link DocDeliveryManagementHistDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DocDeliveryManagementHistRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DocDeliveryManagementHistRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DocDeliveryManagementHistDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DocDeliveryManagementHistDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DocDeliveryManagementHistRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocDeliveryManagementHistRow )
                return new DocDeliveryManagementHistDao( (DocDeliveryManagementHistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocDeliveryManagementHistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocDeliveryManagementHistRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g 
    */
    protected DocDeliveryManagementHistDao( DocDeliveryManagementHistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DocDeliveryManagementHistRow getDocDeliveryManagementHistRow() {
        return row;
    }


  /** 
   * �w���{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g����{@@link DocDeliveryManagementHistDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DocDeliveryManagementHistRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DocDeliveryManagementHistDao}�擾�̂��߂Ɏw���{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DocDeliveryManagementHistDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DocDeliveryManagementHistDao forRow( DocDeliveryManagementHistRow row ) throws java.lang.IllegalArgumentException {
        return (DocDeliveryManagementHistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocDeliveryManagementHistRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DocDeliveryManagementHistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DocDeliveryManagementHistPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DocDeliveryManagementHistParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocDeliveryManagementHistRow.TYPE );
    }


  /** 
   * {@@link DocDeliveryManagementHistRow}����ӂɓ��肷��{@@link DocDeliveryManagementHistPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DocDeliveryManagementHistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DocDeliveryManagementHistParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DocDeliveryManagementHistPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DocDeliveryManagementHistPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocDeliveryManagementHistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocDeliveryManagementHistRow findRowByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementHistPK pk = new DocDeliveryManagementHistPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * �w���DocDeliveryManagementHistPK�I�u�W�F�N�g����{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DocDeliveryManagementHistPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocDeliveryManagementHistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocDeliveryManagementHistRow findRowByPk( DocDeliveryManagementHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocDeliveryManagementHistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,String,java.sql.Timestamp,java.sql.Timestamp,String)}�����{@@link #forRow(DocDeliveryManagementHistRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementHistDao findDaoByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementHistPK pk = new DocDeliveryManagementHistPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory );
        DocDeliveryManagementHistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DocDeliveryManagementHistPK)}�����{@@link #forRow(DocDeliveryManagementHistRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementHistDao findDaoByPk( DocDeliveryManagementHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementHistRow row = findRowByPk( pk );
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
   * p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and �ɂĎw��̒l�����ӂ�{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and �̒l�ƈ�v����{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocDeliveryManagementHistRow findRowByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementHistRow.TYPE,
            "account_id=? and document_div=? and product_code=? and delivery_date=? and created_timestamp=? and document_category=?",
            null,
            new Object[] { new Long(p_accountId), p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementHistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementHistDao.findRowsByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(long, String, String, java.sql.Timestamp, java.sql.Timestamp, String)}�����{@@link #forRow(DocDeliveryManagementHistRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementHistDao findDaoByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and �ɂĎw��̒l�����ӂ�{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_createdTimestamp �����Ώۂł���p_createdTimestamp�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory, and �̒l�ƈ�v����{@@link DocDeliveryManagementHistRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocDeliveryManagementHistRow findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementHistRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and document_div=? and product_code=? and delivery_date=? and created_timestamp=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementHistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementHistDao.findRowsByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory(String, String, String, String, String, java.sql.Timestamp, java.sql.Timestamp, String)}�����{@@link #forRow(DocDeliveryManagementHistRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementHistDao findDaoByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateCreatedTimestampDocumentCategory( p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_createdTimestamp, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
