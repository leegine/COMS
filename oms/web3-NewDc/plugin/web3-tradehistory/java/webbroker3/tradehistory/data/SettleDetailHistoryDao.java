head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.47.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	SettleDetailHistoryDao.java;


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
 * {@@link SettleDetailHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SettleDetailHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SettleDetailHistoryPK 
 * @@see SettleDetailHistoryRow 
 */
public class SettleDetailHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link SettleDetailHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SettleDetailHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SettleDetailHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SettleDetailHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SettleDetailHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SettleDetailHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SettleDetailHistoryRow )
                return new SettleDetailHistoryDao( (SettleDetailHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SettleDetailHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SettleDetailHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SettleDetailHistoryRow}�I�u�W�F�N�g 
    */
    protected SettleDetailHistoryDao( SettleDetailHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SettleDetailHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SettleDetailHistoryRow getSettleDetailHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link SettleDetailHistoryRow}�I�u�W�F�N�g����{@@link SettleDetailHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SettleDetailHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SettleDetailHistoryDao}�擾�̂��߂Ɏw���{@@link SettleDetailHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SettleDetailHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SettleDetailHistoryDao forRow( SettleDetailHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (SettleDetailHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SettleDetailHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SettleDetailHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SettleDetailHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SettleDetailHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SettleDetailHistoryRow.TYPE );
    }


  /** 
   * {@@link SettleDetailHistoryRow}����ӂɓ��肷��{@@link SettleDetailHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SettleDetailHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SettleDetailHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SettleDetailHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SettleDetailHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new SettleDetailHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SettleDetailHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_settleDetailHistoryId �����Ώۂł���p_settleDetailHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SettleDetailHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SettleDetailHistoryRow findRowByPk( long p_settleDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        SettleDetailHistoryPK pk = new SettleDetailHistoryPK( p_settleDetailHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SettleDetailHistoryPK�I�u�W�F�N�g����{@@link SettleDetailHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SettleDetailHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SettleDetailHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SettleDetailHistoryRow findRowByPk( SettleDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SettleDetailHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(SettleDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static SettleDetailHistoryDao findDaoByPk( long p_settleDetailHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        SettleDetailHistoryPK pk = new SettleDetailHistoryPK( p_settleDetailHistoryId );
        SettleDetailHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SettleDetailHistoryPK)}�����{@@link #forRow(SettleDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static SettleDetailHistoryDao findDaoByPk( SettleDetailHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SettleDetailHistoryRow row = findRowByPk( pk );
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
   * p_settleDetailHistoryId, and �ɂĎw��̒l�����ӂ�{@@link SettleDetailHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_settleDetailHistoryId �����Ώۂł���p_settleDetailHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���p_settleDetailHistoryId, and �̒l�ƈ�v����{@@link SettleDetailHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SettleDetailHistoryRow findRowBySettleDetailHistoryId( long p_settleDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SettleDetailHistoryRow.TYPE,
            "settle_detail_history_id=?",
            null,
            new Object[] { new Long(p_settleDetailHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SettleDetailHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SettleDetailHistoryDao.findRowsBySettleDetailHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowBySettleDetailHistoryId(long)}�����{@@link #forRow(SettleDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static SettleDetailHistoryDao findDaoBySettleDetailHistoryId( long p_settleDetailHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySettleDetailHistoryId( p_settleDetailHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate, and �ɂĎw��̒l�Ɉ�v����{@@link SettleDetailHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_closeContractPrice �����Ώۂł���p_closeContractPrice�t�B�[���h�̒l
   * @@param p_netAmount �����Ώۂł���p_netAmount�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_closeExecDate �����Ώۂł���p_closeExecDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate, and �̒l�ƈ�v����{@@link SettleDetailHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_closeContractPrice, Double p_netAmount, String p_status, String p_accountDiv, java.sql.Timestamp p_closeExecDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SettleDetailHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=? and product_code=? and close_contract_price=? and net_amount=? and status=? and account_div=? and close_exec_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate(String, String, String, java.sql.Timestamp, String, Double, Double, String, String, java.sql.Timestamp)}�����{@@link #forRow(SettleDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate, String p_productCode, Double p_closeContractPrice, Double p_netAmount, String p_status, String p_accountDiv, java.sql.Timestamp p_closeExecDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDateProductCodeCloseContractPriceNetAmountStatusAccountDivCloseExecDate( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, p_productCode, p_closeContractPrice, p_netAmount, p_status, p_accountDiv, p_closeExecDate ) );
    }


  /** 
   * p_deliveryDate, and �ɂĎw��̒l�Ɉ�v����{@@link SettleDetailHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_deliveryDate, and �̒l�ƈ�v����{@@link SettleDetailHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SettleDetailHistoryRow.TYPE,
            "delivery_date=?",
            null,
            new Object[] { p_deliveryDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByDeliveryDate(java.sql.Timestamp)}�����{@@link #forRow(SettleDetailHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDate( p_deliveryDate ) );
    }

}
@
