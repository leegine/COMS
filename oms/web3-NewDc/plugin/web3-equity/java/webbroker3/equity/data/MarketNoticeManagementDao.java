head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketNoticeManagementDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MarketNoticeManagementDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MarketNoticeManagementRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MarketNoticeManagementPK 
 * @@see MarketNoticeManagementRow 
 */
public class MarketNoticeManagementDao extends DataAccessObject {


  /** 
   * ����{@@link MarketNoticeManagementDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MarketNoticeManagementRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MarketNoticeManagementRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MarketNoticeManagementDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MarketNoticeManagementDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MarketNoticeManagementRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MarketNoticeManagementRow )
                return new MarketNoticeManagementDao( (MarketNoticeManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MarketNoticeManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MarketNoticeManagementRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MarketNoticeManagementRow}�I�u�W�F�N�g 
    */
    protected MarketNoticeManagementDao( MarketNoticeManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MarketNoticeManagementRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MarketNoticeManagementRow getMarketNoticeManagementRow() {
        return row;
    }


  /** 
   * �w���{@@link MarketNoticeManagementRow}�I�u�W�F�N�g����{@@link MarketNoticeManagementDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MarketNoticeManagementRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MarketNoticeManagementDao}�擾�̂��߂Ɏw���{@@link MarketNoticeManagementRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MarketNoticeManagementDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MarketNoticeManagementDao forRow( MarketNoticeManagementRow row ) throws java.lang.IllegalArgumentException {
        return (MarketNoticeManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MarketNoticeManagementRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MarketNoticeManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MarketNoticeManagementPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MarketNoticeManagementParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MarketNoticeManagementRow.TYPE );
    }


  /** 
   * {@@link MarketNoticeManagementRow}����ӂɓ��肷��{@@link MarketNoticeManagementPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MarketNoticeManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MarketNoticeManagementParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MarketNoticeManagementPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MarketNoticeManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MarketNoticeManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_virtualServerNumberMarket �����Ώۂł���p_virtualServerNumberMarket�t�B�[���h�̒l
   * @@param p_noticeType �����Ώۂł���p_noticeType�t�B�[���h�̒l
   * @@param p_noticeNumber �����Ώۂł���p_noticeNumber�t�B�[���h�̒l
   * @@param p_frontOrderExchangeCode �����Ώۂł���p_frontOrderExchangeCode�t�B�[���h�̒l
   * @@param p_frontOrderSystemCode �����Ώۂł���p_frontOrderSystemCode�t�B�[���h�̒l
   * @@param p_bizDateCount �����Ώۂł���p_bizDateCount�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MarketNoticeManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MarketNoticeManagementRow findRowByPk( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketNoticeManagementPK pk = new MarketNoticeManagementPK( p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount );
        return findRowByPk( pk );
    }


  /** 
   * �w���MarketNoticeManagementPK�I�u�W�F�N�g����{@@link MarketNoticeManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MarketNoticeManagementPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MarketNoticeManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MarketNoticeManagementRow findRowByPk( MarketNoticeManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MarketNoticeManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,long,String,String,int)}�����{@@link #forRow(MarketNoticeManagementRow)}���g�p���Ă��������B 
   */
    public static MarketNoticeManagementDao findDaoByPk( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketNoticeManagementPK pk = new MarketNoticeManagementPK( p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount );
        MarketNoticeManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MarketNoticeManagementPK)}�����{@@link #forRow(MarketNoticeManagementRow)}���g�p���Ă��������B 
   */
    public static MarketNoticeManagementDao findDaoByPk( MarketNoticeManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MarketNoticeManagementRow row = findRowByPk( pk );
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
   * p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount, and �ɂĎw��̒l�����ӂ�{@@link MarketNoticeManagementRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_virtualServerNumberMarket �����Ώۂł���p_virtualServerNumberMarket�t�B�[���h�̒l
   * @@param p_noticeType �����Ώۂł���p_noticeType�t�B�[���h�̒l
   * @@param p_noticeNumber �����Ώۂł���p_noticeNumber�t�B�[���h�̒l
   * @@param p_frontOrderExchangeCode �����Ώۂł���p_frontOrderExchangeCode�t�B�[���h�̒l
   * @@param p_frontOrderSystemCode �����Ώۂł���p_frontOrderSystemCode�t�B�[���h�̒l
   * @@param p_bizDateCount �����Ώۂł���p_bizDateCount�t�B�[���h�̒l
   * 
   * @@return �����w���p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount, and �̒l�ƈ�v����{@@link MarketNoticeManagementRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MarketNoticeManagementRow findRowByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MarketNoticeManagementRow.TYPE,
            "virtual_server_number_market=? and notice_type=? and notice_number=? and front_order_exchange_code=? and front_order_system_code=? and biz_date_count=?",
            null,
            new Object[] { p_virtualServerNumberMarket, p_noticeType, new Long(p_noticeNumber), p_frontOrderExchangeCode, p_frontOrderSystemCode, new Integer(p_bizDateCount) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MarketNoticeManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MarketNoticeManagementDao.findRowsByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount(String, String, long, String, String, int)}�����{@@link #forRow(MarketNoticeManagementRow)}���g�p���Ă��������B 
   */
    public static MarketNoticeManagementDao findDaoByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount( String p_virtualServerNumberMarket, String p_noticeType, long p_noticeNumber, String p_frontOrderExchangeCode, String p_frontOrderSystemCode, int p_bizDateCount ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByVirtualServerNumberMarketNoticeTypeNoticeNumberFrontOrderExchangeCodeFrontOrderSystemCodeBizDateCount( p_virtualServerNumberMarket, p_noticeType, p_noticeNumber, p_frontOrderExchangeCode, p_frontOrderSystemCode, p_bizDateCount ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode, and �ɂĎw��̒l�Ɉ�v����{@@link MarketNoticeManagementRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_frontOrderExchangeCode �����Ώۂł���p_frontOrderExchangeCode�t�B�[���h�̒l
   * @@param p_frontOrderSystemCode �����Ώۂł���p_frontOrderSystemCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode, and �̒l�ƈ�v����{@@link MarketNoticeManagementRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode( String p_institutionCode, String p_frontOrderExchangeCode, String p_frontOrderSystemCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MarketNoticeManagementRow.TYPE,
            "institution_code=? and front_order_exchange_code=? and front_order_system_code=?",
            null,
            new Object[] { p_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode(String, String, String)}�����{@@link #forRow(MarketNoticeManagementRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode( String p_institutionCode, String p_frontOrderExchangeCode, String p_frontOrderSystemCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeFrontOrderExchangeCodeFrontOrderSystemCode( p_institutionCode, p_frontOrderExchangeCode, p_frontOrderSystemCode ) );
    }

}
@
