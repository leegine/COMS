head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DailyAssetDao.java;


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
 * {@@link DailyAssetDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DailyAssetRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DailyAssetPK 
 * @@see DailyAssetRow 
 */
public class DailyAssetDao extends DataAccessObject {


  /** 
   * ����{@@link DailyAssetDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DailyAssetRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DailyAssetRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DailyAssetDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DailyAssetDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DailyAssetRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DailyAssetRow )
                return new DailyAssetDao( (DailyAssetRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DailyAssetRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DailyAssetRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DailyAssetRow}�I�u�W�F�N�g 
    */
    protected DailyAssetDao( DailyAssetRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DailyAssetRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DailyAssetRow getDailyAssetRow() {
        return row;
    }


  /** 
   * �w���{@@link DailyAssetRow}�I�u�W�F�N�g����{@@link DailyAssetDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DailyAssetRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DailyAssetDao}�擾�̂��߂Ɏw���{@@link DailyAssetRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DailyAssetDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DailyAssetDao forRow( DailyAssetRow row ) throws java.lang.IllegalArgumentException {
        return (DailyAssetDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DailyAssetRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DailyAssetRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DailyAssetPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DailyAssetParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DailyAssetRow.TYPE );
    }


  /** 
   * {@@link DailyAssetRow}����ӂɓ��肷��{@@link DailyAssetPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DailyAssetRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DailyAssetParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DailyAssetPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DailyAssetPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DailyAssetRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_taxType �����Ώۂł���p_taxType�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_miniStockDiv �����Ώۂł���p_miniStockDiv�t�B�[���h�̒l
   * @@param p_splitNewStockDiv �����Ώۂł���p_splitNewStockDiv�t�B�[���h�̒l
   * @@param p_originalExecDate �����Ώۂł���p_originalExecDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DailyAssetRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DailyAssetRow findRowByPk( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataFindException, DataQueryException, DataNetworkException {
        DailyAssetPK pk = new DailyAssetPK( p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���DailyAssetPK�I�u�W�F�N�g����{@@link DailyAssetRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DailyAssetPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DailyAssetRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DailyAssetRow findRowByPk( DailyAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DailyAssetRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,long,long,com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum,java.sql.Timestamp,com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum,String,java.sql.Timestamp)}�����{@@link #forRow(DailyAssetRow)}���g�p���Ă��������B 
   */
    public static DailyAssetDao findDaoByPk( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataFindException, DataQueryException, DataNetworkException {
        DailyAssetPK pk = new DailyAssetPK( p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate );
        DailyAssetRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DailyAssetPK)}�����{@@link #forRow(DailyAssetRow)}���g�p���Ă��������B 
   */
    public static DailyAssetDao findDaoByPk( DailyAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DailyAssetRow row = findRowByPk( pk );
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
   * p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate, and �ɂĎw��̒l�����ӂ�{@@link DailyAssetRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_taxType �����Ώۂł���p_taxType�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_miniStockDiv �����Ώۂł���p_miniStockDiv�t�B�[���h�̒l
   * @@param p_splitNewStockDiv �����Ώۂł���p_splitNewStockDiv�t�B�[���h�̒l
   * @@param p_originalExecDate �����Ώۂł���p_originalExecDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate, and �̒l�ƈ�v����{@@link DailyAssetRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DailyAssetRow findRowByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DailyAssetRow.TYPE,
            "account_id=? and sub_account_id=? and product_id=? and tax_type=? and delivery_date=? and mini_stock_div=? and split_new_stock_div=? and original_exec_date=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Long(p_productId), p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DailyAssetRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DailyAssetDao.findRowsByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate(long, long, long, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum, java.sql.Timestamp, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum, String, java.sql.Timestamp)}�����{@@link #forRow(DailyAssetRow)}���g�p���Ă��������B 
   */
    public static DailyAssetDao findDaoByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdProductIdTaxTypeDeliveryDateMiniStockDivSplitNewStockDivOriginalExecDate( p_accountId, p_subAccountId, p_productId, p_taxType, p_deliveryDate, p_miniStockDiv, p_splitNewStockDiv, p_originalExecDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, and �ɂĎw��̒l�Ɉ�v����{@@link DailyAssetRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, and �̒l�ƈ�v����{@@link DailyAssetRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DailyAssetRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(DailyAssetRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountId( p_accountId ) );
    }

}
@
