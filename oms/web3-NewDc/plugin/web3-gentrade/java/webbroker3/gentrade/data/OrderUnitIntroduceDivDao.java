head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderUnitIntroduceDivDao.java;


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
 * {@@link OrderUnitIntroduceDivDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OrderUnitIntroduceDivRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OrderUnitIntroduceDivPK 
 * @@see OrderUnitIntroduceDivRow 
 */
public class OrderUnitIntroduceDivDao extends DataAccessObject {


  /** 
   * ����{@@link OrderUnitIntroduceDivDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OrderUnitIntroduceDivRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OrderUnitIntroduceDivRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OrderUnitIntroduceDivDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OrderUnitIntroduceDivDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OrderUnitIntroduceDivRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderUnitIntroduceDivRow )
                return new OrderUnitIntroduceDivDao( (OrderUnitIntroduceDivRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderUnitIntroduceDivRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderUnitIntroduceDivRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g 
    */
    protected OrderUnitIntroduceDivDao( OrderUnitIntroduceDivRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OrderUnitIntroduceDivRow getOrderUnitIntroduceDivRow() {
        return row;
    }


  /** 
   * �w���{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g����{@@link OrderUnitIntroduceDivDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OrderUnitIntroduceDivRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OrderUnitIntroduceDivDao}�擾�̂��߂Ɏw���{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OrderUnitIntroduceDivDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OrderUnitIntroduceDivDao forRow( OrderUnitIntroduceDivRow row ) throws java.lang.IllegalArgumentException {
        return (OrderUnitIntroduceDivDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderUnitIntroduceDivRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OrderUnitIntroduceDivRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OrderUnitIntroduceDivPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OrderUnitIntroduceDivParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderUnitIntroduceDivRow.TYPE );
    }


  /** 
   * {@@link OrderUnitIntroduceDivRow}����ӂɓ��肷��{@@link OrderUnitIntroduceDivPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OrderUnitIntroduceDivRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OrderUnitIntroduceDivParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OrderUnitIntroduceDivPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OrderUnitIntroduceDivPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderUnitIntroduceDivRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderUnitIntroduceDivRow findRowByPk( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderUnitIntroduceDivPK pk = new OrderUnitIntroduceDivPK( p_orderUnitId, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * �w���OrderUnitIntroduceDivPK�I�u�W�F�N�g����{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OrderUnitIntroduceDivPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderUnitIntroduceDivRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderUnitIntroduceDivRow findRowByPk( OrderUnitIntroduceDivPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderUnitIntroduceDivRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(OrderUnitIntroduceDivRow)}���g�p���Ă��������B 
   */
    public static OrderUnitIntroduceDivDao findDaoByPk( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderUnitIntroduceDivPK pk = new OrderUnitIntroduceDivPK( p_orderUnitId, p_productType );
        OrderUnitIntroduceDivRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OrderUnitIntroduceDivPK)}�����{@@link #forRow(OrderUnitIntroduceDivRow)}���g�p���Ă��������B 
   */
    public static OrderUnitIntroduceDivDao findDaoByPk( OrderUnitIntroduceDivPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderUnitIntroduceDivRow row = findRowByPk( pk );
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
   * p_orderUnitId, p_productType, and �ɂĎw��̒l�����ӂ�{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderUnitId, p_productType, and �̒l�ƈ�v����{@@link OrderUnitIntroduceDivRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OrderUnitIntroduceDivRow findRowByOrderUnitIdProductType( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderUnitIntroduceDivRow.TYPE,
            "order_unit_id=? and product_type=?",
            null,
            new Object[] { new Long(p_orderUnitId), p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderUnitIntroduceDivRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderUnitIntroduceDivDao.findRowsByOrderUnitIdProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByOrderUnitIdProductType(long, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(OrderUnitIntroduceDivRow)}���g�p���Ă��������B 
   */
    public static OrderUnitIntroduceDivDao findDaoByOrderUnitIdProductType( long p_orderUnitId, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrderUnitIdProductType( p_orderUnitId, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
