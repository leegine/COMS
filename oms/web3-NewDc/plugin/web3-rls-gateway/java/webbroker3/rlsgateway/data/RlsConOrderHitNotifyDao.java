head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsConOrderHitNotifyDao.java;


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
 * {@@link RlsConOrderHitNotifyDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RlsConOrderHitNotifyRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RlsConOrderHitNotifyPK 
 * @@see RlsConOrderHitNotifyRow 
 */
public class RlsConOrderHitNotifyDao extends DataAccessObject {


  /** 
   * ����{@@link RlsConOrderHitNotifyDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RlsConOrderHitNotifyRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RlsConOrderHitNotifyRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RlsConOrderHitNotifyDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RlsConOrderHitNotifyDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RlsConOrderHitNotifyRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsConOrderHitNotifyRow )
                return new RlsConOrderHitNotifyDao( (RlsConOrderHitNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsConOrderHitNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsConOrderHitNotifyRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g 
    */
    protected RlsConOrderHitNotifyDao( RlsConOrderHitNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RlsConOrderHitNotifyRow getRlsConOrderHitNotifyRow() {
        return row;
    }


  /** 
   * �w���{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g����{@@link RlsConOrderHitNotifyDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RlsConOrderHitNotifyRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RlsConOrderHitNotifyDao}�擾�̂��߂Ɏw���{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RlsConOrderHitNotifyDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RlsConOrderHitNotifyDao forRow( RlsConOrderHitNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (RlsConOrderHitNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsConOrderHitNotifyRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RlsConOrderHitNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RlsConOrderHitNotifyPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RlsConOrderHitNotifyParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsConOrderHitNotifyRow.TYPE );
    }


  /** 
   * {@@link RlsConOrderHitNotifyRow}����ӂɓ��肷��{@@link RlsConOrderHitNotifyPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RlsConOrderHitNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RlsConOrderHitNotifyParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RlsConOrderHitNotifyPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RlsConOrderHitNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_orderType �����Ώۂł���p_orderType�t�B�[���h�̒l
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsConOrderHitNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsConOrderHitNotifyRow findRowByPk( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsConOrderHitNotifyPK pk = new RlsConOrderHitNotifyPK( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * �w���RlsConOrderHitNotifyPK�I�u�W�F�N�g����{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RlsConOrderHitNotifyPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsConOrderHitNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsConOrderHitNotifyRow findRowByPk( RlsConOrderHitNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsConOrderHitNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,long,int,long,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(RlsConOrderHitNotifyRow)}���g�p���Ă��������B 
   */
    public static RlsConOrderHitNotifyDao findDaoByPk( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsConOrderHitNotifyPK pk = new RlsConOrderHitNotifyPK( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType );
        RlsConOrderHitNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RlsConOrderHitNotifyPK)}�����{@@link #forRow(RlsConOrderHitNotifyRow)}���g�p���Ă��������B 
   */
    public static RlsConOrderHitNotifyDao findDaoByPk( RlsConOrderHitNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsConOrderHitNotifyRow row = findRowByPk( pk );
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
   * p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and �ɂĎw��̒l�����ӂ�{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_orderType �����Ώۂł���p_orderType�t�B�[���h�̒l
   * @@param p_orderId �����Ώۂł���p_orderId�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType, and �̒l�ƈ�v����{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RlsConOrderHitNotifyRow findRowByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsConOrderHitNotifyRow.TYPE,
            "account_id=? and sub_account_id=? and order_type=? and order_id=? and product_type=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), new Integer(p_orderType), new Long(p_orderId), p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsConOrderHitNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsConOrderHitNotifyDao.findRowsByAccountIdSubAccountIdOrderTypeOrderIdProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdSubAccountIdOrderTypeOrderIdProductType(long, long, int, long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(RlsConOrderHitNotifyRow)}���g�p���Ă��������B 
   */
    public static RlsConOrderHitNotifyDao findDaoByAccountIdSubAccountIdOrderTypeOrderIdProductType( long p_accountId, long p_subAccountId, int p_orderType, long p_orderId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountIdOrderTypeOrderIdProductType( p_accountId, p_subAccountId, p_orderType, p_orderId, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and �ɂĎw��̒l�Ɉ�v����{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_status, and �̒l�ƈ�v����{@@link RlsConOrderHitNotifyRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsConOrderHitNotifyRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdStatus(long, String)}�����{@@link #forRow(RlsConOrderHitNotifyRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }

}
@
