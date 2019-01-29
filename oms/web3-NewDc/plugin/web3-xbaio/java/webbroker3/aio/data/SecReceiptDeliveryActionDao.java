head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	SecReceiptDeliveryActionDao.java;


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
 * {@@link SecReceiptDeliveryActionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SecReceiptDeliveryActionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SecReceiptDeliveryActionPK 
 * @@see SecReceiptDeliveryActionRow 
 */
public class SecReceiptDeliveryActionDao extends DataAccessObject {


  /** 
   * ����{@@link SecReceiptDeliveryActionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SecReceiptDeliveryActionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SecReceiptDeliveryActionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SecReceiptDeliveryActionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SecReceiptDeliveryActionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SecReceiptDeliveryActionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SecReceiptDeliveryActionRow )
                return new SecReceiptDeliveryActionDao( (SecReceiptDeliveryActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SecReceiptDeliveryActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SecReceiptDeliveryActionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g 
    */
    protected SecReceiptDeliveryActionDao( SecReceiptDeliveryActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SecReceiptDeliveryActionRow getSecReceiptDeliveryActionRow() {
        return row;
    }


  /** 
   * �w���{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g����{@@link SecReceiptDeliveryActionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SecReceiptDeliveryActionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SecReceiptDeliveryActionDao}�擾�̂��߂Ɏw���{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SecReceiptDeliveryActionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SecReceiptDeliveryActionDao forRow( SecReceiptDeliveryActionRow row ) throws java.lang.IllegalArgumentException {
        return (SecReceiptDeliveryActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SecReceiptDeliveryActionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SecReceiptDeliveryActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SecReceiptDeliveryActionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SecReceiptDeliveryActionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SecReceiptDeliveryActionRow.TYPE );
    }


  /** 
   * {@@link SecReceiptDeliveryActionRow}����ӂɓ��肷��{@@link SecReceiptDeliveryActionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SecReceiptDeliveryActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SecReceiptDeliveryActionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SecReceiptDeliveryActionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SecReceiptDeliveryActionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SecReceiptDeliveryActionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_secReceiptDeliveryActionId �����Ώۂł���p_secReceiptDeliveryActionId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SecReceiptDeliveryActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SecReceiptDeliveryActionRow findRowByPk( long p_secReceiptDeliveryActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecReceiptDeliveryActionPK pk = new SecReceiptDeliveryActionPK( p_secReceiptDeliveryActionId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SecReceiptDeliveryActionPK�I�u�W�F�N�g����{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SecReceiptDeliveryActionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SecReceiptDeliveryActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SecReceiptDeliveryActionRow findRowByPk( SecReceiptDeliveryActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SecReceiptDeliveryActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(SecReceiptDeliveryActionRow)}���g�p���Ă��������B 
   */
    public static SecReceiptDeliveryActionDao findDaoByPk( long p_secReceiptDeliveryActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        SecReceiptDeliveryActionPK pk = new SecReceiptDeliveryActionPK( p_secReceiptDeliveryActionId );
        SecReceiptDeliveryActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SecReceiptDeliveryActionPK)}�����{@@link #forRow(SecReceiptDeliveryActionRow)}���g�p���Ă��������B 
   */
    public static SecReceiptDeliveryActionDao findDaoByPk( SecReceiptDeliveryActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SecReceiptDeliveryActionRow row = findRowByPk( pk );
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
   * p_secReceiptDeliveryActionId, and �ɂĎw��̒l�����ӂ�{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_secReceiptDeliveryActionId �����Ώۂł���p_secReceiptDeliveryActionId�t�B�[���h�̒l
   * 
   * @@return �����w���p_secReceiptDeliveryActionId, and �̒l�ƈ�v����{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SecReceiptDeliveryActionRow findRowBySecReceiptDeliveryActionId( long p_secReceiptDeliveryActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SecReceiptDeliveryActionRow.TYPE,
            "sec_receipt_delivery_action_id=?",
            null,
            new Object[] { new Long(p_secReceiptDeliveryActionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SecReceiptDeliveryActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SecReceiptDeliveryActionDao.findRowsBySecReceiptDeliveryActionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowBySecReceiptDeliveryActionId(long)}�����{@@link #forRow(SecReceiptDeliveryActionRow)}���g�p���Ă��������B 
   */
    public static SecReceiptDeliveryActionDao findDaoBySecReceiptDeliveryActionId( long p_secReceiptDeliveryActionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySecReceiptDeliveryActionId( p_secReceiptDeliveryActionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup, and �ɂĎw��̒l�Ɉ�v����{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_productGroup �����Ώۂł���p_productGroup�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_ioGroup �����Ώۂł���p_ioGroup�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup, and �̒l�ƈ�v����{@@link SecReceiptDeliveryActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productGroup, String p_productCode, String p_ioGroup ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SecReceiptDeliveryActionRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and product_group=? and product_code=? and io_group=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup(String, String, String, java.sql.Timestamp, String, String, String)}�����{@@link #forRow(SecReceiptDeliveryActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productGroup, String p_productCode, String p_ioGroup ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductGroupProductCodeIoGroup( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productGroup, p_productCode, p_ioGroup ) );
    }

}
@
