head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	Web3QuoteProtoDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.quoteadaptor.prototype.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link Web3QuoteProtoDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link Web3QuoteProtoRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see Web3QuoteProtoPK 
 * @@see Web3QuoteProtoRow 
 */
public class Web3QuoteProtoDao extends DataAccessObject {


  /** 
   * ����{@@link Web3QuoteProtoDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private Web3QuoteProtoRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link Web3QuoteProtoRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link Web3QuoteProtoDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link Web3QuoteProtoDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link Web3QuoteProtoRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof Web3QuoteProtoRow )
                return new Web3QuoteProtoDao( (Web3QuoteProtoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a Web3QuoteProtoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link Web3QuoteProtoRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link Web3QuoteProtoRow}�I�u�W�F�N�g 
    */
    protected Web3QuoteProtoDao( Web3QuoteProtoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link Web3QuoteProtoRow}�I�u�W�F�N�g���擾���܂��B
   */
    public Web3QuoteProtoRow getWeb3QuoteProtoRow() {
        return row;
    }


  /** 
   * �w���{@@link Web3QuoteProtoRow}�I�u�W�F�N�g����{@@link Web3QuoteProtoDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link Web3QuoteProtoRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link Web3QuoteProtoDao}�擾�̂��߂Ɏw���{@@link Web3QuoteProtoRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link Web3QuoteProtoDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static Web3QuoteProtoDao forRow( Web3QuoteProtoRow row ) throws java.lang.IllegalArgumentException {
        return (Web3QuoteProtoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link Web3QuoteProtoRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link Web3QuoteProtoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link Web3QuoteProtoPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link Web3QuoteProtoParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( Web3QuoteProtoRow.TYPE );
    }


  /** 
   * {@@link Web3QuoteProtoRow}����ӂɓ��肷��{@@link Web3QuoteProtoPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link Web3QuoteProtoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link Web3QuoteProtoParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link Web3QuoteProtoPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static Web3QuoteProtoPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new Web3QuoteProtoPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_quoteDataId �����Ώۂł���p_quoteDataId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link Web3QuoteProtoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static Web3QuoteProtoRow findRowByPk( long p_quoteDataId ) throws DataFindException, DataQueryException, DataNetworkException {
        Web3QuoteProtoPK pk = new Web3QuoteProtoPK( p_quoteDataId );
        return findRowByPk( pk );
    }


  /** 
   * �w���Web3QuoteProtoPK�I�u�W�F�N�g����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����Web3QuoteProtoPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link Web3QuoteProtoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static Web3QuoteProtoRow findRowByPk( Web3QuoteProtoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (Web3QuoteProtoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(Web3QuoteProtoRow)}���g�p���Ă��������B 
   */
    public static Web3QuoteProtoDao findDaoByPk( long p_quoteDataId ) throws DataFindException, DataQueryException, DataNetworkException {
        Web3QuoteProtoPK pk = new Web3QuoteProtoPK( p_quoteDataId );
        Web3QuoteProtoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(Web3QuoteProtoPK)}�����{@@link #forRow(Web3QuoteProtoRow)}���g�p���Ă��������B 
   */
    public static Web3QuoteProtoDao findDaoByPk( Web3QuoteProtoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        Web3QuoteProtoRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_realType, p_dataType, p_marketCode, p_productCode, and �ɂĎw��̒l�Ɉ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_realType �����Ώۂł���p_realType�t�B�[���h�̒l
   * @@param p_dataType �����Ώۂł���p_dataType�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_realType, p_dataType, p_marketCode, p_productCode, and �̒l�ƈ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRealTypeDataTypeMarketCodeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and market_code=? and product_code=?",
            null,
            new Object[] { p_realType, p_dataType, p_marketCode, p_productCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRealTypeDataTypeMarketCodeProductCode(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String, String)}�����{@@link #forRow(Web3QuoteProtoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRealTypeDataTypeMarketCodeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeMarketCodeProductCode( p_realType, p_dataType, p_marketCode, p_productCode ) );
    }


  /** 
   * p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, and �ɂĎw��̒l�Ɉ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_realType �����Ώۂł���p_realType�t�B�[���h�̒l
   * @@param p_dataType �����Ώۂł���p_dataType�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_contractMonth �����Ώۂł���p_contractMonth�t�B�[���h�̒l
   * 
   * @@return �����w���p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, and �̒l�ƈ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonth( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and market_code=? and product_code=? and contract_month=?",
            null,
            new Object[] { p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonth(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String, String, String)}�����{@@link #forRow(Web3QuoteProtoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRealTypeDataTypeMarketCodeProductCodeContractMonth( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonth( p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth ) );
    }


  /** 
   * p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice, and �ɂĎw��̒l�Ɉ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_realType �����Ώۂł���p_realType�t�B�[���h�̒l
   * @@param p_dataType �����Ώۂł���p_dataType�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_contractMonth �����Ώۂł���p_contractMonth�t�B�[���h�̒l
   * @@param p_putAndCall �����Ώۂł���p_putAndCall�t�B�[���h�̒l
   * @@param p_strikePrice �����Ώۂł���p_strikePrice�t�B�[���h�̒l
   * 
   * @@return �����w���p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice, and �̒l�ƈ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth, String p_putAndCall, Double p_strikePrice ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and market_code=? and product_code=? and contract_month=? and put_and_call=? and strike_price=?",
            null,
            new Object[] { p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String, String, String, String, Double)}�����{@@link #forRow(Web3QuoteProtoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_marketCode, String p_productCode, String p_contractMonth, String p_putAndCall, Double p_strikePrice ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeMarketCodeProductCodeContractMonthPutAndCallStrikePrice( p_realType, p_dataType, p_marketCode, p_productCode, p_contractMonth, p_putAndCall, p_strikePrice ) );
    }


  /** 
   * p_realType, p_dataType, p_productCode, and �ɂĎw��̒l�Ɉ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_realType �����Ώۂł���p_realType�t�B�[���h�̒l
   * @@param p_dataType �����Ώۂł���p_dataType�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_realType, p_dataType, p_productCode, and �̒l�ƈ�v����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRealTypeDataTypeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            Web3QuoteProtoRow.TYPE,
            "real_type=? and data_type=? and product_code=?",
            null,
            new Object[] { p_realType, p_dataType, p_productCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRealTypeDataTypeProductCode(webbroker3.quoteadaptor.RealType, webbroker3.quoteadaptor.DataType, String)}�����{@@link #forRow(Web3QuoteProtoRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRealTypeDataTypeProductCode( webbroker3.quoteadaptor.RealType p_realType, webbroker3.quoteadaptor.DataType p_dataType, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRealTypeDataTypeProductCode( p_realType, p_dataType, p_productCode ) );
    }

}
@
