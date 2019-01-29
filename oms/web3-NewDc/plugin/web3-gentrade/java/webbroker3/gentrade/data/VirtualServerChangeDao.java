head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerChangeDao.java;


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
 * {@@link VirtualServerChangeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link VirtualServerChangeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see VirtualServerChangePK 
 * @@see VirtualServerChangeRow 
 */
public class VirtualServerChangeDao extends DataAccessObject {


  /** 
   * ����{@@link VirtualServerChangeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private VirtualServerChangeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link VirtualServerChangeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link VirtualServerChangeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link VirtualServerChangeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link VirtualServerChangeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof VirtualServerChangeRow )
                return new VirtualServerChangeDao( (VirtualServerChangeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a VirtualServerChangeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link VirtualServerChangeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link VirtualServerChangeRow}�I�u�W�F�N�g 
    */
    protected VirtualServerChangeDao( VirtualServerChangeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link VirtualServerChangeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public VirtualServerChangeRow getVirtualServerChangeRow() {
        return row;
    }


  /** 
   * �w���{@@link VirtualServerChangeRow}�I�u�W�F�N�g����{@@link VirtualServerChangeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link VirtualServerChangeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link VirtualServerChangeDao}�擾�̂��߂Ɏw���{@@link VirtualServerChangeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link VirtualServerChangeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static VirtualServerChangeDao forRow( VirtualServerChangeRow row ) throws java.lang.IllegalArgumentException {
        return (VirtualServerChangeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link VirtualServerChangeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link VirtualServerChangeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link VirtualServerChangePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link VirtualServerChangeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( VirtualServerChangeRow.TYPE );
    }


  /** 
   * {@@link VirtualServerChangeRow}����ӂɓ��肷��{@@link VirtualServerChangePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link VirtualServerChangeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link VirtualServerChangeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link VirtualServerChangePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static VirtualServerChangePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link VirtualServerChangeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_virtualServerNumberMarket �����Ώۂł���p_virtualServerNumberMarket�t�B�[���h�̒l
   * @@param p_changeReqResDiv �����Ώۂł���p_changeReqResDiv�t�B�[���h�̒l
   * @@param p_noticeType �����Ώۂł���p_noticeType�t�B�[���h�̒l
   * @@param p_frontOrderExchangeCode �����Ώۂł���p_frontOrderExchangeCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link VirtualServerChangeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static VirtualServerChangeRow findRowByPk( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerChangePK pk = new VirtualServerChangePK( p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���VirtualServerChangePK�I�u�W�F�N�g����{@@link VirtualServerChangeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����VirtualServerChangePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link VirtualServerChangeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static VirtualServerChangeRow findRowByPk( VirtualServerChangePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (VirtualServerChangeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(VirtualServerChangeRow)}���g�p���Ă��������B 
   */
    public static VirtualServerChangeDao findDaoByPk( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerChangePK pk = new VirtualServerChangePK( p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode );
        VirtualServerChangeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(VirtualServerChangePK)}�����{@@link #forRow(VirtualServerChangeRow)}���g�p���Ă��������B 
   */
    public static VirtualServerChangeDao findDaoByPk( VirtualServerChangePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerChangeRow row = findRowByPk( pk );
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
   * p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode, and �ɂĎw��̒l�Ɉ�v����{@@link VirtualServerChangeRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_virtualServerNumberMarket �����Ώۂł���p_virtualServerNumberMarket�t�B�[���h�̒l
   * @@param p_changeReqResDiv �����Ώۂł���p_changeReqResDiv�t�B�[���h�̒l
   * @@param p_noticeType �����Ώۂł���p_noticeType�t�B�[���h�̒l
   * @@param p_frontOrderExchangeCode �����Ώۂł���p_frontOrderExchangeCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode, and �̒l�ƈ�v����{@@link VirtualServerChangeRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            VirtualServerChangeRow.TYPE,
            "virtual_server_number_market=? and change_req_res_div=? and notice_type=? and front_order_exchange_code=?",
            null,
            new Object[] { p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode(String, String, String, String)}�����{@@link #forRow(VirtualServerChangeRow)}���g�p���Ă��������B 
   */
    public static List findDaosByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode( String p_virtualServerNumberMarket, String p_changeReqResDiv, String p_noticeType, String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByVirtualServerNumberMarketChangeReqResDivNoticeTypeFrontOrderExchangeCode( p_virtualServerNumberMarket, p_changeReqResDiv, p_noticeType, p_frontOrderExchangeCode ) );
    }

}
@
