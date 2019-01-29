head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFotypeOrderAllDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link HostFotypeOrderAllDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostFotypeOrderAllRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostFotypeOrderAllPK 
 * @@see HostFotypeOrderAllRow 
 */
public class HostFotypeOrderAllDao extends DataAccessObject {


  /** 
   * ����{@@link HostFotypeOrderAllDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostFotypeOrderAllRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostFotypeOrderAllRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostFotypeOrderAllDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostFotypeOrderAllDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostFotypeOrderAllRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFotypeOrderAllRow )
                return new HostFotypeOrderAllDao( (HostFotypeOrderAllRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFotypeOrderAllRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFotypeOrderAllRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g 
    */
    protected HostFotypeOrderAllDao( HostFotypeOrderAllRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostFotypeOrderAllRow getHostFotypeOrderAllRow() {
        return row;
    }


  /** 
   * �w���{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g����{@@link HostFotypeOrderAllDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostFotypeOrderAllRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostFotypeOrderAllDao}�擾�̂��߂Ɏw���{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostFotypeOrderAllDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostFotypeOrderAllDao forRow( HostFotypeOrderAllRow row ) throws java.lang.IllegalArgumentException {
        return (HostFotypeOrderAllDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFotypeOrderAllRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostFotypeOrderAllRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostFotypeOrderAllPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostFotypeOrderAllParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFotypeOrderAllRow.TYPE );
    }


  /** 
   * {@@link HostFotypeOrderAllRow}����ӂɓ��肷��{@@link HostFotypeOrderAllPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostFotypeOrderAllRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostFotypeOrderAllParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostFotypeOrderAllPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostFotypeOrderAllPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostFotypeOrderAllRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostFotypeOrderAllRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderAllPK pk = new HostFotypeOrderAllPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostFotypeOrderAllPK�I�u�W�F�N�g����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostFotypeOrderAllPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostFotypeOrderAllRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostFotypeOrderAllRow findRowByPk( HostFotypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFotypeOrderAllRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostFotypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static HostFotypeOrderAllDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderAllPK pk = new HostFotypeOrderAllPK( p_rowid );
        HostFotypeOrderAllRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostFotypeOrderAllPK)}�����{@@link #forRow(HostFotypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static HostFotypeOrderAllDao findDaoByPk( HostFotypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderAllRow row = findRowByPk( pk );
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
   * p_status, p_submitOrderRouteDiv, and �ɂĎw��̒l�Ɉ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_submitOrderRouteDiv �����Ώۂł���p_submitOrderRouteDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_submitOrderRouteDiv, and �̒l�ƈ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "status=? and submit_order_route_div=?",
            null,
            new Object[] { p_status, p_submitOrderRouteDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusSubmitOrderRouteDiv(String, String)}�����{@@link #forRow(HostFotypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusSubmitOrderRouteDiv( p_status, p_submitOrderRouteDiv ) );
    }


  /** 
   * p_corpCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_corpCode �����Ώۂł���p_corpCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_corpCode, and �̒l�ƈ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "corp_code=?",
            null,
            new Object[] { p_corpCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCorpCode(String)}�����{@@link #forRow(HostFotypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCorpCode( p_corpCode ) );
    }


  /** 
   * p_accountId, p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdOrderRequestNumber( Long p_accountId, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "account_id=? and order_request_number=?",
            null,
            new Object[] { p_accountId, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdOrderRequestNumber(Long, String)}�����{@@link #forRow(HostFotypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdOrderRequestNumber( Long p_accountId, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdOrderRequestNumber( p_accountId, p_orderRequestNumber ) );
    }


  /** 
   * p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderRequestNumber(String)}�����{@@link #forRow(HostFotypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }


  /** 
   * p_orgCorpCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orgCorpCode �����Ώۂł���p_orgCorpCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_orgCorpCode, and �̒l�ƈ�v����{@@link HostFotypeOrderAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "org_corp_code=?",
            null,
            new Object[] { p_orgCorpCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrgCorpCode(String)}�����{@@link #forRow(HostFotypeOrderAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrgCorpCode( p_orgCorpCode ) );
    }

}
@
