head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TradeDetailHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TradeDetailHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TradeDetailHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TradeDetailHistoryPK 
 * @@see TradeDetailHistoryRow 
 */
public class TradeDetailHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link TradeDetailHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TradeDetailHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TradeDetailHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TradeDetailHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TradeDetailHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TradeDetailHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradeDetailHistoryRow )
                return new TradeDetailHistoryDao( (TradeDetailHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradeDetailHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradeDetailHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TradeDetailHistoryRow}�I�u�W�F�N�g 
    */
    protected TradeDetailHistoryDao( TradeDetailHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TradeDetailHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TradeDetailHistoryRow getTradeDetailHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link TradeDetailHistoryRow}�I�u�W�F�N�g����{@@link TradeDetailHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TradeDetailHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TradeDetailHistoryDao}�擾�̂��߂Ɏw���{@@link TradeDetailHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TradeDetailHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TradeDetailHistoryDao forRow( TradeDetailHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TradeDetailHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradeDetailHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TradeDetailHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TradeDetailHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TradeDetailHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradeDetailHistoryRow.TYPE );
    }


  /** 
   * {@@link TradeDetailHistoryRow}����ӂɓ��肷��{@@link TradeDetailHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TradeDetailHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TradeDetailHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TradeDetailHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TradeDetailHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TradeDetailHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TradeDetailHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_tradeDetailHistoryId �����Ώۂł���p_tradeDetailHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TradeDetailHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TradeDetailHistoryRow findRowByPk( long p_tradeDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeDetailHistoryPK pk = new TradeDetailHistoryPK( p_tradeDetailHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TradeDetailHistoryPK�I�u�W�F�N�g����{@@link TradeDetailHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TradeDetailHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TradeDetailHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TradeDetailHistoryRow findRowByPk( TradeDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradeDetailHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TradeDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static TradeDetailHistoryDao findDaoByPk( long p_tradeDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeDetailHistoryPK pk = new TradeDetailHistoryPK( p_tradeDetailHistoryId );
        TradeDetailHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TradeDetailHistoryPK)}�����{@@link #forRow(TradeDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static TradeDetailHistoryDao findDaoByPk( TradeDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeDetailHistoryRow row = findRowByPk( pk );
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
   * p_tradeDetailHistoryId, and �ɂĎw��̒l�����ӂ�{@@link TradeDetailHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tradeDetailHistoryId �����Ώۂł���p_tradeDetailHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���p_tradeDetailHistoryId, and �̒l�ƈ�v����{@@link TradeDetailHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TradeDetailHistoryRow findRowByTradeDetailHistoryId( long p_tradeDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradeDetailHistoryRow.TYPE,
            "trade_detail_history_id=?",
            null,
            new Object[] { new Long(p_tradeDetailHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradeDetailHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradeDetailHistoryDao.findRowsByTradeDetailHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTradeDetailHistoryId(long)}�����{@@link #forRow(TradeDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static TradeDetailHistoryDao findDaoByTradeDetailHistoryId( long p_tradeDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradeDetailHistoryId( p_tradeDetailHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate, and �ɂĎw��̒l�Ɉ�v����{@@link TradeDetailHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_quantity �����Ώۂł���p_quantity�t�B�[���h�̒l
   * @@param p_price �����Ώۂł���p_price�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_netAmount �����Ώۂł���p_netAmount�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_execDate �����Ώۂł���p_execDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate, and �̒l�ƈ�v����{@@link TradeDetailHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_quantity, Double p_price, String p_accountDiv, Double p_netAmount, String p_status, java.sql.Timestamp p_execDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeDetailHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and product_code=? and quantity=? and price=? and account_div=? and net_amount=? and status=? and exec_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate(String, String, String, java.sql.Timestamp, String, Double, Double, String, Double, String, java.sql.Timestamp)}�����{@@link #forRow(TradeDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_quantity, Double p_price, String p_accountDiv, Double p_netAmount, String p_status, java.sql.Timestamp p_execDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeQuantityPriceAccountDivNetAmountStatusExecDate( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_quantity, p_price, p_accountDiv, p_netAmount, p_status, p_execDate ) );
    }


  /** 
   * p_deliveryDate, and �ɂĎw��̒l�Ɉ�v����{@@link TradeDetailHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_deliveryDate, and �̒l�ƈ�v����{@@link TradeDetailHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeDetailHistoryRow.TYPE,
            "delivery_date=?",
            null,
            new Object[] { p_deliveryDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByDeliveryDate(java.sql.Timestamp)}�����{@@link #forRow(TradeDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDate( p_deliveryDate ) );
    }

}
@
