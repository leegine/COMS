head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderSwitchingDao.java;


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
 * {@@link OrderSwitchingDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OrderSwitchingRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OrderSwitchingPK 
 * @@see OrderSwitchingRow 
 */
public class OrderSwitchingDao extends DataAccessObject {


  /** 
   * ����{@@link OrderSwitchingDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OrderSwitchingRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OrderSwitchingRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OrderSwitchingDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OrderSwitchingDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OrderSwitchingRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderSwitchingRow )
                return new OrderSwitchingDao( (OrderSwitchingRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderSwitchingRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderSwitchingRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OrderSwitchingRow}�I�u�W�F�N�g 
    */
    protected OrderSwitchingDao( OrderSwitchingRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OrderSwitchingRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OrderSwitchingRow getOrderSwitchingRow() {
        return row;
    }


  /** 
   * �w���{@@link OrderSwitchingRow}�I�u�W�F�N�g����{@@link OrderSwitchingDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OrderSwitchingRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OrderSwitchingDao}�擾�̂��߂Ɏw���{@@link OrderSwitchingRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OrderSwitchingDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OrderSwitchingDao forRow( OrderSwitchingRow row ) throws java.lang.IllegalArgumentException {
        return (OrderSwitchingDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderSwitchingRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OrderSwitchingRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OrderSwitchingPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OrderSwitchingParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderSwitchingRow.TYPE );
    }


  /** 
   * {@@link OrderSwitchingRow}����ӂɓ��肷��{@@link OrderSwitchingPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OrderSwitchingRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OrderSwitchingParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OrderSwitchingPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OrderSwitchingPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OrderSwitchingRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_submitOrderRouteDiv �����Ώۂł���p_submitOrderRouteDiv�t�B�[���h�̒l
   * @@param p_frontOrderSystemCode �����Ώۂł���p_frontOrderSystemCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderSwitchingRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderSwitchingRow findRowByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderSwitchingPK pk = new OrderSwitchingPK( p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���OrderSwitchingPK�I�u�W�F�N�g����{@@link OrderSwitchingRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OrderSwitchingPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderSwitchingRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderSwitchingRow findRowByPk( OrderSwitchingPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderSwitchingRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,String,String,String)}�����{@@link #forRow(OrderSwitchingRow)}���g�p���Ă��������B 
   */
    public static OrderSwitchingDao findDaoByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderSwitchingPK pk = new OrderSwitchingPK( p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode );
        OrderSwitchingRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OrderSwitchingPK)}�����{@@link #forRow(OrderSwitchingRow)}���g�p���Ă��������B 
   */
    public static OrderSwitchingDao findDaoByPk( OrderSwitchingPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderSwitchingRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode, and �ɂĎw��̒l�����ӂ�{@@link OrderSwitchingRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_submitOrderRouteDiv �����Ώۂł���p_submitOrderRouteDiv�t�B�[���h�̒l
   * @@param p_frontOrderSystemCode �����Ώۂł���p_frontOrderSystemCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode, and �̒l�ƈ�v����{@@link OrderSwitchingRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OrderSwitchingRow findRowByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderSwitchingRow.TYPE,
            "institution_code=? and product_type=? and market_code=? and submit_order_route_div=? and front_order_system_code=?",
            null,
            new Object[] { p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderSwitchingRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderSwitchingDao.findRowsByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String, String, String)}�����{@@link #forRow(OrderSwitchingRow)}���g�p���Ă��������B 
   */
    public static OrderSwitchingDao findDaoByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_marketCode, String p_submitOrderRouteDiv, String p_frontOrderSystemCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductTypeMarketCodeSubmitOrderRouteDivFrontOrderSystemCode( p_institutionCode, p_productType, p_marketCode, p_submitOrderRouteDiv, p_frontOrderSystemCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
