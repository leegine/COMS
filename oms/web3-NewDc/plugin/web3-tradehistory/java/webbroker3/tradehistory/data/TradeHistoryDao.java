head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TradeHistoryDao.java;


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
 * {@@link TradeHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TradeHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TradeHistoryPK 
 * @@see TradeHistoryRow 
 */
public class TradeHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link TradeHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TradeHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TradeHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TradeHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TradeHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TradeHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradeHistoryRow )
                return new TradeHistoryDao( (TradeHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradeHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradeHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TradeHistoryRow}�I�u�W�F�N�g 
    */
    protected TradeHistoryDao( TradeHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TradeHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TradeHistoryRow getTradeHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link TradeHistoryRow}�I�u�W�F�N�g����{@@link TradeHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TradeHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TradeHistoryDao}�擾�̂��߂Ɏw���{@@link TradeHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TradeHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TradeHistoryDao forRow( TradeHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TradeHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradeHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TradeHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TradeHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TradeHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradeHistoryRow.TYPE );
    }


  /** 
   * {@@link TradeHistoryRow}����ӂɓ��肷��{@@link TradeHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TradeHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TradeHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TradeHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TradeHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TradeHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TradeHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_tradeHistoryId �����Ώۂł���p_tradeHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TradeHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TradeHistoryRow findRowByPk( long p_tradeHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeHistoryPK pk = new TradeHistoryPK( p_tradeHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TradeHistoryPK�I�u�W�F�N�g����{@@link TradeHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TradeHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TradeHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TradeHistoryRow findRowByPk( TradeHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradeHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TradeHistoryRow)}���g�p���Ă��������B 
   */
    public static TradeHistoryDao findDaoByPk( long p_tradeHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeHistoryPK pk = new TradeHistoryPK( p_tradeHistoryId );
        TradeHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TradeHistoryPK)}�����{@@link #forRow(TradeHistoryRow)}���g�p���Ă��������B 
   */
    public static TradeHistoryDao findDaoByPk( TradeHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradeHistoryRow row = findRowByPk( pk );
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
   * p_tradeHistoryId, and �ɂĎw��̒l�����ӂ�{@@link TradeHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_tradeHistoryId �����Ώۂł���p_tradeHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���p_tradeHistoryId, and �̒l�ƈ�v����{@@link TradeHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TradeHistoryRow findRowByTradeHistoryId( long p_tradeHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TradeHistoryRow.TYPE,
            "trade_history_id=?",
            null,
            new Object[] { new Long(p_tradeHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TradeHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TradeHistoryDao.findRowsByTradeHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTradeHistoryId(long)}�����{@@link #forRow(TradeHistoryRow)}���g�p���Ă��������B 
   */
    public static TradeHistoryDao findDaoByTradeHistoryId( long p_tradeHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTradeHistoryId( p_tradeHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, and �ɂĎw��̒l�Ɉ�v����{@@link TradeHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate, and �̒l�ƈ�v����{@@link TradeHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and delivery_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDate(String, String, String, java.sql.Timestamp)}�����{@@link #forRow(TradeHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDeliveryDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDeliveryDate( p_institutionCode, p_branchCode, p_accountCode, p_deliveryDate ) );
    }


  /** 
   * p_deliveryDate, and �ɂĎw��̒l�Ɉ�v����{@@link TradeHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_deliveryDate �����Ώۂł���p_deliveryDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_deliveryDate, and �̒l�ƈ�v����{@@link TradeHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradeHistoryRow.TYPE,
            "delivery_date=?",
            null,
            new Object[] { p_deliveryDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByDeliveryDate(java.sql.Timestamp)}�����{@@link #forRow(TradeHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByDeliveryDate( java.sql.Timestamp p_deliveryDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByDeliveryDate( p_deliveryDate ) );
    }

}
@
