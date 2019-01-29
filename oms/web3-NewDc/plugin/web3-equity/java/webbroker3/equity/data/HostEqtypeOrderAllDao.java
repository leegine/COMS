head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeOrderAllDao.java;


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
 * {@@link HostEqtypeOrderAllDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostEqtypeOrderAllRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostEqtypeOrderAllPK 
 * @@see HostEqtypeOrderAllRow 
 */
public class HostEqtypeOrderAllDao extends DataAccessObject {


  /** 
   * ����{@@link HostEqtypeOrderAllDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostEqtypeOrderAllRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostEqtypeOrderAllRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostEqtypeOrderAllDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostEqtypeOrderAllDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostEqtypeOrderAllRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeOrderAllRow )
                return new HostEqtypeOrderAllDao( (HostEqtypeOrderAllRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeOrderAllRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeOrderAllRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g 
    */
    protected HostEqtypeOrderAllDao( HostEqtypeOrderAllRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostEqtypeOrderAllRow getHostEqtypeOrderAllRow() {
        return row;
    }


  /** 
   * �w���{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g����{@@link HostEqtypeOrderAllDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostEqtypeOrderAllRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostEqtypeOrderAllDao}�擾�̂��߂Ɏw���{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostEqtypeOrderAllDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostEqtypeOrderAllDao forRow( HostEqtypeOrderAllRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeOrderAllDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeOrderAllRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostEqtypeOrderAllRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostEqtypeOrderAllPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostEqtypeOrderAllParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeOrderAllRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeOrderAllRow}����ӂɓ��肷��{@@link HostEqtypeOrderAllPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostEqtypeOrderAllRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostEqtypeOrderAllParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostEqtypeOrderAllPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostEqtypeOrderAllPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostEqtypeOrderAllRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostEqtypeOrderAllRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAllPK pk = new HostEqtypeOrderAllPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostEqtypeOrderAllPK�I�u�W�F�N�g����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostEqtypeOrderAllPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostEqtypeOrderAllRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostEqtypeOrderAllRow findRowByPk( HostEqtypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeOrderAllRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostEqtypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static HostEqtypeOrderAllDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAllPK pk = new HostEqtypeOrderAllPK( p_rowid );
        HostEqtypeOrderAllRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostEqtypeOrderAllPK)}�����{@@link #forRow(HostEqtypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static HostEqtypeOrderAllDao findDaoByPk( HostEqtypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAllRow row = findRowByPk( pk );
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
   * p_orderActionSerialNo, p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderActionSerialNo �����Ώۂł���p_orderActionSerialNo�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderActionSerialNo, p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderActionSerialNoOrderRequestNumber( Integer p_orderActionSerialNo, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "order_action_serial_no=? and order_request_number=?",
            null,
            new Object[] { p_orderActionSerialNo, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderActionSerialNoOrderRequestNumber(Integer, String)}�����{@@link #forRow(HostEqtypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderActionSerialNoOrderRequestNumber( Integer p_orderActionSerialNo, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderActionSerialNoOrderRequestNumber( p_orderActionSerialNo, p_orderRequestNumber ) );
    }


  /** 
   * p_status, p_submitOrderRouteDiv, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_submitOrderRouteDiv �����Ώۂł���p_submitOrderRouteDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_submitOrderRouteDiv, and �̒l�ƈ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "status=? and submit_order_route_div=?",
            null,
            new Object[] { p_status, p_submitOrderRouteDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusSubmitOrderRouteDiv(String, String)}�����{@@link #forRow(HostEqtypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusSubmitOrderRouteDiv( p_status, p_submitOrderRouteDiv ) );
    }


  /** 
   * p_corpCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_corpCode �����Ώۂł���p_corpCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_corpCode, and �̒l�ƈ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "corp_code=?",
            null,
            new Object[] { p_corpCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCorpCode(String)}�����{@@link #forRow(HostEqtypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCorpCode( p_corpCode ) );
    }


  /** 
   * p_frontOrderExchangeCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_frontOrderExchangeCode �����Ώۂł���p_frontOrderExchangeCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_frontOrderExchangeCode, and �̒l�ƈ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByFrontOrderExchangeCode( String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "front_order_exchange_code=?",
            null,
            new Object[] { p_frontOrderExchangeCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByFrontOrderExchangeCode(String)}�����{@@link #forRow(HostEqtypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByFrontOrderExchangeCode( String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByFrontOrderExchangeCode( p_frontOrderExchangeCode ) );
    }


  /** 
   * p_orgCorpCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orgCorpCode �����Ώۂł���p_orgCorpCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_orgCorpCode, and �̒l�ƈ�v����{@@link HostEqtypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "org_corp_code=?",
            null,
            new Object[] { p_orgCorpCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrgCorpCode(String)}�����{@@link #forRow(HostEqtypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrgCorpCode( p_orgCorpCode ) );
    }

}
@
