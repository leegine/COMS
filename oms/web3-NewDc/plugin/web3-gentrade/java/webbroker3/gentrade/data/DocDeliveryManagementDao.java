head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDeliveryManagementDao.java;


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
 * {@@link DocDeliveryManagementDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DocDeliveryManagementRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DocDeliveryManagementPK 
 * @@see DocDeliveryManagementRow 
 */
public class DocDeliveryManagementDao extends DataAccessObject {


  /** 
   * ����{@@link DocDeliveryManagementDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DocDeliveryManagementRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DocDeliveryManagementRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DocDeliveryManagementDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DocDeliveryManagementDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DocDeliveryManagementRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocDeliveryManagementRow )
                return new DocDeliveryManagementDao( (DocDeliveryManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocDeliveryManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocDeliveryManagementRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DocDeliveryManagementRow}�I�u�W�F�N�g 
    */
    protected DocDeliveryManagementDao( DocDeliveryManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DocDeliveryManagementRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DocDeliveryManagementRow getDocDeliveryManagementRow() {
        return row;
    }


  /** 
   * �w���{@@link DocDeliveryManagementRow}�I�u�W�F�N�g����{@@link DocDeliveryManagementDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DocDeliveryManagementRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DocDeliveryManagementDao}�擾�̂��߂Ɏw���{@@link DocDeliveryManagementRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DocDeliveryManagementDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DocDeliveryManagementDao forRow( DocDeliveryManagementRow row ) throws java.lang.IllegalArgumentException {
        return (DocDeliveryManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocDeliveryManagementRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DocDeliveryManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DocDeliveryManagementPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DocDeliveryManagementParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocDeliveryManagementRow.TYPE );
    }


  /** 
   * {@@link DocDeliveryManagementRow}����ӂɓ��肷��{@@link DocDeliveryManagementPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DocDeliveryManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DocDeliveryManagementParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DocDeliveryManagementPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DocDeliveryManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DocDeliveryManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocDeliveryManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocDeliveryManagementRow findRowByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementPK pk = new DocDeliveryManagementPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory );
        return findRowByPk( pk );
    }


  /** 
   * �w���DocDeliveryManagementPK�I�u�W�F�N�g����{@@link DocDeliveryManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DocDeliveryManagementPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocDeliveryManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocDeliveryManagementRow findRowByPk( DocDeliveryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocDeliveryManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,String,java.sql.Timestamp,String)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementDao findDaoByPk( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementPK pk = new DocDeliveryManagementPK( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory );
        DocDeliveryManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DocDeliveryManagementPK)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementDao findDaoByPk( DocDeliveryManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocDeliveryManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link DocDeliveryManagementDao}�ɕR�t��{@@link DocDeliveryManagementRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link DocDeliveryManagementDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public MainAccountRow fetchMainAccountRowViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        Row row = MainAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MainAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MainAccountRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for MainAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByAccountId(MainAccountRow)}���g�p���Ă��������B 
   */
    public static List findRowsByAccountId( MainAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( dao.getMainAccountRow() );
    }


  /** 
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link DocDeliveryManagementRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link DocDeliveryManagementRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link DocDeliveryManagementRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link DocDeliveryManagementRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link DocDeliveryManagementRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link DocDeliveryManagementRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DocDeliveryManagementRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and �ɂĎw��̒l�����ӂ�{@@link DocDeliveryManagementRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and �̒l�ƈ�v����{@@link DocDeliveryManagementRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocDeliveryManagementRow findRowByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementRow.TYPE,
            "account_id=? and document_div=? and product_code=? and delivery_date=? and document_category=?",
            null,
            new Object[] { new Long(p_accountId), p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementDao.findRowsByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory(long, String, String, java.sql.Timestamp, String)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementDao findDaoByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdDocumentDivProductCodeDeliveryDateDocumentCategory( p_accountId, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and �ɂĎw��̒l�����ӂ�{@@link DocDeliveryManagementRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_documentDiv �����Ώۂł���p_documentDiv�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_documentCategory �����Ώۂł���p_documentCategory�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory, and �̒l�ƈ�v����{@@link DocDeliveryManagementRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocDeliveryManagementRow findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocDeliveryManagementRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and document_div=? and product_code=? and delivery_date=? and document_category=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocDeliveryManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocDeliveryManagementDao.findRowsByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory(String, String, String, String, String, java.sql.Timestamp, String)}�����{@@link #forRow(DocDeliveryManagementRow)}���g�p���Ă��������B 
   */
    public static DocDeliveryManagementDao findDaoByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory( String p_institutionCode, String p_branchCode, String p_accountCode, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, String p_documentCategory ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDocumentDivProductCodeDeliveryDateDocumentCategory( p_institutionCode, p_branchCode, p_accountCode, p_documentDiv, p_productCode, p_deliveryDate, p_documentCategory ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
