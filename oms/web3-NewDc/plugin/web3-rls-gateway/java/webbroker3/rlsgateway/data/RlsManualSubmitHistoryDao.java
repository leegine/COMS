head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsManualSubmitHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rlsgateway.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RlsManualSubmitHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RlsManualSubmitHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RlsManualSubmitHistoryPK 
 * @@see RlsManualSubmitHistoryRow 
 */
public class RlsManualSubmitHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link RlsManualSubmitHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RlsManualSubmitHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RlsManualSubmitHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RlsManualSubmitHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RlsManualSubmitHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RlsManualSubmitHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsManualSubmitHistoryRow )
                return new RlsManualSubmitHistoryDao( (RlsManualSubmitHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsManualSubmitHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsManualSubmitHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g 
    */
    protected RlsManualSubmitHistoryDao( RlsManualSubmitHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RlsManualSubmitHistoryRow getRlsManualSubmitHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g����{@@link RlsManualSubmitHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RlsManualSubmitHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RlsManualSubmitHistoryDao}�擾�̂��߂Ɏw���{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RlsManualSubmitHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RlsManualSubmitHistoryDao forRow( RlsManualSubmitHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (RlsManualSubmitHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsManualSubmitHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RlsManualSubmitHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RlsManualSubmitHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RlsManualSubmitHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsManualSubmitHistoryRow.TYPE );
    }


  /** 
   * {@@link RlsManualSubmitHistoryRow}����ӂɓ��肷��{@@link RlsManualSubmitHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RlsManualSubmitHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RlsManualSubmitHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RlsManualSubmitHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RlsManualSubmitHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsManualSubmitHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_historyId �����Ώۂł���p_historyId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsManualSubmitHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsManualSubmitHistoryRow findRowByPk( long p_historyId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsManualSubmitHistoryPK pk = new RlsManualSubmitHistoryPK( p_historyId );
        return findRowByPk( pk );
    }


  /** 
   * �w���RlsManualSubmitHistoryPK�I�u�W�F�N�g����{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RlsManualSubmitHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsManualSubmitHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsManualSubmitHistoryRow findRowByPk( RlsManualSubmitHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsManualSubmitHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RlsManualSubmitHistoryRow)}���g�p���Ă��������B 
   */
    public static RlsManualSubmitHistoryDao findDaoByPk( long p_historyId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsManualSubmitHistoryPK pk = new RlsManualSubmitHistoryPK( p_historyId );
        RlsManualSubmitHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RlsManualSubmitHistoryPK)}�����{@@link #forRow(RlsManualSubmitHistoryRow)}���g�p���Ă��������B 
   */
    public static RlsManualSubmitHistoryDao findDaoByPk( RlsManualSubmitHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsManualSubmitHistoryRow row = findRowByPk( pk );
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
   * p_historyId, and �ɂĎw��̒l�����ӂ�{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_historyId �����Ώۂł���p_historyId�t�B�[���h�̒l
   * 
   * @@return �����w���p_historyId, and �̒l�ƈ�v����{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RlsManualSubmitHistoryRow findRowByHistoryId( long p_historyId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsManualSubmitHistoryRow.TYPE,
            "history_id=?",
            null,
            new Object[] { new Long(p_historyId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsManualSubmitHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsManualSubmitHistoryDao.findRowsByHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByHistoryId(long)}�����{@@link #forRow(RlsManualSubmitHistoryRow)}���g�p���Ă��������B 
   */
    public static RlsManualSubmitHistoryDao findDaoByHistoryId( long p_historyId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByHistoryId( p_historyId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and �ɂĎw��̒l�Ɉ�v����{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_orderType �����Ώۂł���p_orderType�t�B�[���h�̒l
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and �̒l�ƈ�v����{@@link RlsManualSubmitHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsManualSubmitHistoryRow.TYPE,
            "account_id=? and sub_account_id=? and order_type=? and order_id=? and product_type=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Integer(p_orderType), new Long(p_orderId), p_productType } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType(long, long, int, long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(RlsManualSubmitHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType ) );
    }

}
@
