head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommRevMstDao.java;


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
 * {@@link EquityCommRevMstDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link EquityCommRevMstRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see EquityCommRevMstPK 
 * @@see EquityCommRevMstRow 
 */
public class EquityCommRevMstDao extends DataAccessObject {


  /** 
   * ����{@@link EquityCommRevMstDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private EquityCommRevMstRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link EquityCommRevMstRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link EquityCommRevMstDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link EquityCommRevMstDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link EquityCommRevMstRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityCommRevMstRow )
                return new EquityCommRevMstDao( (EquityCommRevMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityCommRevMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityCommRevMstRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link EquityCommRevMstRow}�I�u�W�F�N�g 
    */
    protected EquityCommRevMstDao( EquityCommRevMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link EquityCommRevMstRow}�I�u�W�F�N�g���擾���܂��B
   */
    public EquityCommRevMstRow getEquityCommRevMstRow() {
        return row;
    }


  /** 
   * �w���{@@link EquityCommRevMstRow}�I�u�W�F�N�g����{@@link EquityCommRevMstDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link EquityCommRevMstRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link EquityCommRevMstDao}�擾�̂��߂Ɏw���{@@link EquityCommRevMstRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link EquityCommRevMstDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static EquityCommRevMstDao forRow( EquityCommRevMstRow row ) throws java.lang.IllegalArgumentException {
        return (EquityCommRevMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityCommRevMstRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link EquityCommRevMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link EquityCommRevMstPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link EquityCommRevMstParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityCommRevMstRow.TYPE );
    }


  /** 
   * {@@link EquityCommRevMstRow}����ӂɓ��肷��{@@link EquityCommRevMstPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link EquityCommRevMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link EquityCommRevMstParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link EquityCommRevMstPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static EquityCommRevMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link EquityCommRevMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * @@param p_orderChannel �����Ώۂł���p_orderChannel�t�B�[���h�̒l
   * @@param p_transactionType �����Ώۂł���p_transactionType�t�B�[���h�̒l
   * @@param p_execCondType �����Ώۂł���p_execCondType�t�B�[���h�̒l
   * @@param p_payType �����Ώۂł���p_payType�t�B�[���h�̒l
   * @@param p_sonarMarketCode �����Ώۂł���p_sonarMarketCode�t�B�[���h�̒l
   * @@param p_underlyingProductCode �����Ώۂł���p_underlyingProductCode�t�B�[���h�̒l
   * @@param p_dayTradeType �����Ώۂł���p_dayTradeType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EquityCommRevMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EquityCommRevMstRow findRowByPk( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommRevMstPK pk = new EquityCommRevMstPK( p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType );
        return findRowByPk( pk );
    }


  /** 
   * �w���EquityCommRevMstPK�I�u�W�F�N�g����{@@link EquityCommRevMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����EquityCommRevMstPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EquityCommRevMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EquityCommRevMstRow findRowByPk( EquityCommRevMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityCommRevMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,java.sql.Timestamp,String,String,String,String,String,String,String)}�����{@@link #forRow(EquityCommRevMstRow)}���g�p���Ă��������B 
   */
    public static EquityCommRevMstDao findDaoByPk( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommRevMstPK pk = new EquityCommRevMstPK( p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType );
        EquityCommRevMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(EquityCommRevMstPK)}�����{@@link #forRow(EquityCommRevMstRow)}���g�p���Ă��������B 
   */
    public static EquityCommRevMstDao findDaoByPk( EquityCommRevMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommRevMstRow row = findRowByPk( pk );
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
   * p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType, and �ɂĎw��̒l�����ӂ�{@@link EquityCommRevMstRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * @@param p_orderChannel �����Ώۂł���p_orderChannel�t�B�[���h�̒l
   * @@param p_transactionType �����Ώۂł���p_transactionType�t�B�[���h�̒l
   * @@param p_execCondType �����Ώۂł���p_execCondType�t�B�[���h�̒l
   * @@param p_payType �����Ώۂł���p_payType�t�B�[���h�̒l
   * @@param p_sonarMarketCode �����Ώۂł���p_sonarMarketCode�t�B�[���h�̒l
   * @@param p_underlyingProductCode �����Ώۂł���p_underlyingProductCode�t�B�[���h�̒l
   * @@param p_dayTradeType �����Ώۂł���p_dayTradeType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType, and �̒l�ƈ�v����{@@link EquityCommRevMstRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EquityCommRevMstRow findRowByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityCommRevMstRow.TYPE,
            "institution_code=? and comm_product_code=? and appli_start_date=? and order_channel=? and transaction_type=? and exec_cond_type=? and pay_type=? and sonar_market_code=? and underlying_product_code=? and day_trade_type=?",
            null,
            new Object[] { p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityCommRevMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityCommRevMstDao.findRowsByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType(String, String, java.sql.Timestamp, String, String, String, String, String, String, String)}�����{@@link #forRow(EquityCommRevMstRow)}���g�p���Ă��������B 
   */
    public static EquityCommRevMstDao findDaoByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType( String p_institutionCode, String p_commProductCode, java.sql.Timestamp p_appliStartDate, String p_orderChannel, String p_transactionType, String p_execCondType, String p_payType, String p_sonarMarketCode, String p_underlyingProductCode, String p_dayTradeType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCommProductCodeAppliStartDateOrderChannelTransactionTypeExecCondTypePayTypeSonarMarketCodeUnderlyingProductCodeDayTradeType( p_institutionCode, p_commProductCode, p_appliStartDate, p_orderChannel, p_transactionType, p_execCondType, p_payType, p_sonarMarketCode, p_underlyingProductCode, p_dayTradeType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
