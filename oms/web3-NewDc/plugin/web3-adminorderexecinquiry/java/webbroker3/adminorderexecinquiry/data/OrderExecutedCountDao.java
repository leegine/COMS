head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderExecutedCountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.adminorderexecinquiry.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OrderExecutedCountDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OrderExecutedCountRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OrderExecutedCountPK 
 * @@see OrderExecutedCountRow 
 */
public class OrderExecutedCountDao extends DataAccessObject {


  /** 
   * ����{@@link OrderExecutedCountDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OrderExecutedCountRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OrderExecutedCountRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OrderExecutedCountDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OrderExecutedCountDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OrderExecutedCountRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderExecutedCountRow )
                return new OrderExecutedCountDao( (OrderExecutedCountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderExecutedCountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderExecutedCountRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OrderExecutedCountRow}�I�u�W�F�N�g 
    */
    protected OrderExecutedCountDao( OrderExecutedCountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OrderExecutedCountRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OrderExecutedCountRow getOrderExecutedCountRow() {
        return row;
    }


  /** 
   * �w���{@@link OrderExecutedCountRow}�I�u�W�F�N�g����{@@link OrderExecutedCountDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OrderExecutedCountRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OrderExecutedCountDao}�擾�̂��߂Ɏw���{@@link OrderExecutedCountRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OrderExecutedCountDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OrderExecutedCountDao forRow( OrderExecutedCountRow row ) throws java.lang.IllegalArgumentException {
        return (OrderExecutedCountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderExecutedCountRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OrderExecutedCountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OrderExecutedCountPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OrderExecutedCountParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderExecutedCountRow.TYPE );
    }


  /** 
   * {@@link OrderExecutedCountRow}����ӂɓ��肷��{@@link OrderExecutedCountPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OrderExecutedCountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OrderExecutedCountParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OrderExecutedCountPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OrderExecutedCountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OrderExecutedCountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_recordDiv �����Ώۂł���p_recordDiv�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productDiv �����Ώۂł���p_productDiv�t�B�[���h�̒l
   * @@param p_orderChanel �����Ώۂł���p_orderChanel�t�B�[���h�̒l
   * @@param p_orderRootDiv �����Ώۂł���p_orderRootDiv�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderExecutedCountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderExecutedCountRow findRowByPk( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderExecutedCountPK pk = new OrderExecutedCountPK( p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���OrderExecutedCountPK�I�u�W�F�N�g����{@@link OrderExecutedCountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OrderExecutedCountPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderExecutedCountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderExecutedCountRow findRowByPk( OrderExecutedCountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderExecutedCountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String,String,String,String)}�����{@@link #forRow(OrderExecutedCountRow)}���g�p���Ă��������B 
   */
    public static OrderExecutedCountDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderExecutedCountPK pk = new OrderExecutedCountPK( p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate );
        OrderExecutedCountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OrderExecutedCountPK)}�����{@@link #forRow(OrderExecutedCountRow)}���g�p���Ă��������B 
   */
    public static OrderExecutedCountDao findDaoByPk( OrderExecutedCountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderExecutedCountRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate, and �ɂĎw��̒l�����ӂ�{@@link OrderExecutedCountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_recordDiv �����Ώۂł���p_recordDiv�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productDiv �����Ώۂł���p_productDiv�t�B�[���h�̒l
   * @@param p_orderChanel �����Ώۂł���p_orderChanel�t�B�[���h�̒l
   * @@param p_orderRootDiv �����Ώۂł���p_orderRootDiv�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate, and �̒l�ƈ�v����{@@link OrderExecutedCountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OrderExecutedCountRow findRowByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderExecutedCountRow.TYPE,
            "institution_code=? and branch_code=? and record_div=? and market_code=? and product_div=? and order_chanel=? and order_root_div=? and biz_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderExecutedCountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderExecutedCountDao.findRowsByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate(String, String, String, String, String, String, String, String)}�����{@@link #forRow(OrderExecutedCountRow)}���g�p���Ă��������B 
   */
    public static OrderExecutedCountDao findDaoByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate( String p_institutionCode, String p_branchCode, String p_recordDiv, String p_marketCode, String p_productDiv, String p_orderChanel, String p_orderRootDiv, String p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeRecordDivMarketCodeProductDivOrderChanelOrderRootDivBizDate( p_institutionCode, p_branchCode, p_recordDiv, p_marketCode, p_productDiv, p_orderChanel, p_orderRootDiv, p_bizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
