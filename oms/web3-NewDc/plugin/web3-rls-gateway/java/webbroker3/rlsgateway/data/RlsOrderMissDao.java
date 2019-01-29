head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOrderMissDao.java;


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
 * {@@link RlsOrderMissDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RlsOrderMissRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RlsOrderMissPK 
 * @@see RlsOrderMissRow 
 */
public class RlsOrderMissDao extends DataAccessObject {


  /** 
   * ����{@@link RlsOrderMissDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RlsOrderMissRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RlsOrderMissRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RlsOrderMissDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RlsOrderMissDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RlsOrderMissRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsOrderMissRow )
                return new RlsOrderMissDao( (RlsOrderMissRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsOrderMissRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsOrderMissRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RlsOrderMissRow}�I�u�W�F�N�g 
    */
    protected RlsOrderMissDao( RlsOrderMissRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RlsOrderMissRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RlsOrderMissRow getRlsOrderMissRow() {
        return row;
    }


  /** 
   * �w���{@@link RlsOrderMissRow}�I�u�W�F�N�g����{@@link RlsOrderMissDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RlsOrderMissRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RlsOrderMissDao}�擾�̂��߂Ɏw���{@@link RlsOrderMissRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RlsOrderMissDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RlsOrderMissDao forRow( RlsOrderMissRow row ) throws java.lang.IllegalArgumentException {
        return (RlsOrderMissDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsOrderMissRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RlsOrderMissRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RlsOrderMissPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RlsOrderMissParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsOrderMissRow.TYPE );
    }


  /** 
   * {@@link RlsOrderMissRow}����ӂɓ��肷��{@@link RlsOrderMissPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RlsOrderMissRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RlsOrderMissParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RlsOrderMissPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RlsOrderMissPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RlsOrderMissRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_missType �����Ώۂł���p_missType�t�B�[���h�̒l
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * @@param p_orderActionSerialNo �����Ώۂł���p_orderActionSerialNo�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_omsCondOrderType �����Ώۂł���p_omsCondOrderType�t�B�[���h�̒l
   * @@param p_detectType �����Ώۂł���p_detectType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsOrderMissRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsOrderMissRow findRowByPk( String p_missType, long p_accountId, long p_subAccountId, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOrderMissPK pk = new RlsOrderMissPK( p_missType, p_accountId, p_subAccountId, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType );
        return findRowByPk( pk );
    }


  /** 
   * �w���RlsOrderMissPK�I�u�W�F�N�g����{@@link RlsOrderMissRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RlsOrderMissPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsOrderMissRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsOrderMissRow findRowByPk( RlsOrderMissPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsOrderMissRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,long,long,long,int,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,int,String)}�����{@@link #forRow(RlsOrderMissRow)}���g�p���Ă��������B 
   */
    public static RlsOrderMissDao findDaoByPk( String p_missType, long p_accountId, long p_subAccountId, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOrderMissPK pk = new RlsOrderMissPK( p_missType, p_accountId, p_subAccountId, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType );
        RlsOrderMissRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RlsOrderMissPK)}�����{@@link #forRow(RlsOrderMissRow)}���g�p���Ă��������B 
   */
    public static RlsOrderMissDao findDaoByPk( RlsOrderMissPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOrderMissRow row = findRowByPk( pk );
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
   * p_accountId, p_subAccountId, p_missType, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType, and �ɂĎw��̒l�����ӂ�{@@link RlsOrderMissRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_missType �����Ώۂł���p_missType�t�B�[���h�̒l
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * @@param p_orderActionSerialNo �����Ώۂł���p_orderActionSerialNo�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_omsCondOrderType �����Ώۂł���p_omsCondOrderType�t�B�[���h�̒l
   * @@param p_detectType �����Ώۂł���p_detectType�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, p_missType, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType, and �̒l�ƈ�v����{@@link RlsOrderMissRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RlsOrderMissRow findRowByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType( long p_accountId, long p_subAccountId, String p_missType, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsOrderMissRow.TYPE,
            "account_id=? and sub_account_id=? and miss_type=? and order_unit_id=? and order_action_serial_no=? and product_type=? and oms_cond_order_type=? and detect_type=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), p_missType, new Long(p_orderUnitId), new Integer(p_orderActionSerialNo), p_productType, new Integer(p_omsCondOrderType), p_detectType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsOrderMissRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsOrderMissDao.findRowsByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType(long, long, String, long, int, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, int, String)}�����{@@link #forRow(RlsOrderMissRow)}���g�p���Ă��������B 
   */
    public static RlsOrderMissDao findDaoByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType( long p_accountId, long p_subAccountId, String p_missType, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdMissTypeOrderUnitIdOrderActionSerialNoProductTypeOmsCondOrderTypeDetectType( p_accountId, p_subAccountId, p_missType, p_orderUnitId, p_orderActionSerialNo, p_productType, p_omsCondOrderType, p_detectType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_orderId, p_productType, and �ɂĎw��̒l�Ɉ�v����{@@link RlsOrderMissRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderId, p_productType, and �̒l�ƈ�v����{@@link RlsOrderMissRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderIdProductType( long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsOrderMissRow.TYPE,
            "order_id=? and product_type=?",
            null,
            new Object[] { new Long(p_orderId), p_productType } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderIdProductType(long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(RlsOrderMissRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderIdProductType( long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderIdProductType( p_orderId, p_productType ) );
    }

}
@
