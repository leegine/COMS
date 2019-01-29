head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TradingTimeDao.java;


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
 * {@@link TradingTimeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TradingTimeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TradingTimePK 
 * @@see TradingTimeRow 
 */
public class TradingTimeDao extends DataAccessObject {


  /** 
   * ����{@@link TradingTimeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TradingTimeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TradingTimeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TradingTimeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TradingTimeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TradingTimeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TradingTimeRow )
                return new TradingTimeDao( (TradingTimeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TradingTimeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TradingTimeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TradingTimeRow}�I�u�W�F�N�g 
    */
    protected TradingTimeDao( TradingTimeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TradingTimeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TradingTimeRow getTradingTimeRow() {
        return row;
    }


  /** 
   * �w���{@@link TradingTimeRow}�I�u�W�F�N�g����{@@link TradingTimeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TradingTimeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TradingTimeDao}�擾�̂��߂Ɏw���{@@link TradingTimeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TradingTimeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TradingTimeDao forRow( TradingTimeRow row ) throws java.lang.IllegalArgumentException {
        return (TradingTimeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TradingTimeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TradingTimeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TradingTimePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TradingTimeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TradingTimeRow.TYPE );
    }


  /** 
   * {@@link TradingTimeRow}����ӂɓ��肷��{@@link TradingTimePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TradingTimeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TradingTimeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TradingTimePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TradingTimePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TradingTimeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_tradingTimeType �����Ώۂł���p_tradingTimeType�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_bizDateType �����Ώۂł���p_bizDateType�t�B�[���h�̒l
   * @@param p_startTime �����Ώۂł���p_startTime�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TradingTimeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TradingTimeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataFindException, DataQueryException, DataNetworkException {
        TradingTimePK pk = new TradingTimePK( p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime );
        return findRowByPk( pk );
    }


  /** 
   * �w���TradingTimePK�I�u�W�F�N�g����{@@link TradingTimeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TradingTimePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TradingTimeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TradingTimeRow findRowByPk( TradingTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TradingTimeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String,String,String)}�����{@@link #forRow(TradingTimeRow)}���g�p���Ă��������B 
   */
    public static TradingTimeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataFindException, DataQueryException, DataNetworkException {
        TradingTimePK pk = new TradingTimePK( p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime );
        TradingTimeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TradingTimePK)}�����{@@link #forRow(TradingTimeRow)}���g�p���Ă��������B 
   */
    public static TradingTimeDao findDaoByPk( TradingTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TradingTimeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime, and �ɂĎw��̒l�Ɉ�v����{@@link TradingTimeRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_tradingTimeType �����Ώۂł���p_tradingTimeType�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_bizDateType �����Ώۂł���p_bizDateType�t�B�[���h�̒l
   * @@param p_startTime �����Ώۂł���p_startTime�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime, and �̒l�ƈ�v����{@@link TradingTimeRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TradingTimeRow.TYPE,
            "institution_code=? and branch_code=? and market_code=? and trading_time_type=? and product_code=? and biz_date_type=? and start_time=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime(String, String, String, String, String, String, String)}�����{@@link #forRow(TradingTimeRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime( String p_institutionCode, String p_branchCode, String p_marketCode, String p_tradingTimeType, String p_productCode, String p_bizDateType, String p_startTime ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeMarketCodeTradingTimeTypeProductCodeBizDateTypeStartTime( p_institutionCode, p_branchCode, p_marketCode, p_tradingTimeType, p_productCode, p_bizDateType, p_startTime ) );
    }

}
@
