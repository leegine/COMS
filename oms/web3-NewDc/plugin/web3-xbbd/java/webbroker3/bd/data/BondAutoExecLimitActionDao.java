head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondAutoExecLimitActionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link BondAutoExecLimitActionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BondAutoExecLimitActionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BondAutoExecLimitActionPK 
 * @@see BondAutoExecLimitActionRow 
 */
public class BondAutoExecLimitActionDao extends DataAccessObject {


  /** 
   * ����{@@link BondAutoExecLimitActionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BondAutoExecLimitActionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BondAutoExecLimitActionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BondAutoExecLimitActionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BondAutoExecLimitActionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BondAutoExecLimitActionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondAutoExecLimitActionRow )
                return new BondAutoExecLimitActionDao( (BondAutoExecLimitActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondAutoExecLimitActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondAutoExecLimitActionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g 
    */
    protected BondAutoExecLimitActionDao( BondAutoExecLimitActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BondAutoExecLimitActionRow getBondAutoExecLimitActionRow() {
        return row;
    }


  /** 
   * �w���{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g����{@@link BondAutoExecLimitActionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BondAutoExecLimitActionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BondAutoExecLimitActionDao}�擾�̂��߂Ɏw���{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BondAutoExecLimitActionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BondAutoExecLimitActionDao forRow( BondAutoExecLimitActionRow row ) throws java.lang.IllegalArgumentException {
        return (BondAutoExecLimitActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondAutoExecLimitActionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BondAutoExecLimitActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BondAutoExecLimitActionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BondAutoExecLimitActionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondAutoExecLimitActionRow.TYPE );
    }


  /** 
   * {@@link BondAutoExecLimitActionRow}����ӂɓ��肷��{@@link BondAutoExecLimitActionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BondAutoExecLimitActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BondAutoExecLimitActionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BondAutoExecLimitActionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BondAutoExecLimitActionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_executionUpdateDate �����Ώۂł���p_executionUpdateDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondAutoExecLimitActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondAutoExecLimitActionRow findRowByPk( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondAutoExecLimitActionPK pk = new BondAutoExecLimitActionPK( p_productId, p_executionUpdateDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���BondAutoExecLimitActionPK�I�u�W�F�N�g����{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BondAutoExecLimitActionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondAutoExecLimitActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondAutoExecLimitActionRow findRowByPk( BondAutoExecLimitActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondAutoExecLimitActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,java.sql.Timestamp)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static BondAutoExecLimitActionDao findDaoByPk( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondAutoExecLimitActionPK pk = new BondAutoExecLimitActionPK( p_productId, p_executionUpdateDate );
        BondAutoExecLimitActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BondAutoExecLimitActionPK)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static BondAutoExecLimitActionDao findDaoByPk( BondAutoExecLimitActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondAutoExecLimitActionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BondAutoExecLimitActionDao}�ɕR�t��{@@link BondAutoExecLimitActionRow}���ŊO���L�[�̊֌W������{@@link BondProductRow}���������܂��B 
   * 
   * @@return {@@link BondAutoExecLimitActionDao}�ƊO���L�[�̊֌W�ɂ���{@@link BondProductRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public BondProductRow fetchBondProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        Row row = BondProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondProductRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBondProductRowViaProductId()}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public BondProductDao fetchBondProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        DataAccessObject dao = BondProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondProductDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByProductId(BondProductRow)}���g�p���Ă��������B 
   */
    public static List findRowsByProductId( BondProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getBondProductRow() );
    }


  /** 
   * {@@link BondProductRow}�ƊO���L�[�̊֌W�ɂ���{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BondProductRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BondProductRow}�ɊO���L�[������{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link BondProductPK}�ƊO���L�[�̊֌W�ɂ���{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BondProductPK}�I�u�W�F�N�g 
   * @@return {@@link BondProductPK}�ƊO���L�[����v����l������{@@link BondAutoExecLimitActionRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondAutoExecLimitActionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByProductId(BondProductRow)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( BondProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(BondProductRow)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(BondProductPK)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_productId, p_executionUpdateDate, and �ɂĎw��̒l�����ӂ�{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_executionUpdateDate �����Ώۂł���p_executionUpdateDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, p_executionUpdateDate, and �̒l�ƈ�v����{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BondAutoExecLimitActionRow findRowByProductIdExecutionUpdateDate( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondAutoExecLimitActionRow.TYPE,
            "product_id=? and execution_update_date=?",
            null,
            new Object[] { new Long(p_productId), p_executionUpdateDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondAutoExecLimitActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondAutoExecLimitActionDao.findRowsByProductIdExecutionUpdateDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductIdExecutionUpdateDate(long, java.sql.Timestamp)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static BondAutoExecLimitActionDao findDaoByProductIdExecutionUpdateDate( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdExecutionUpdateDate( p_productId, p_executionUpdateDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_productId, p_onlineDispDiv, and �ɂĎw��̒l�Ɉ�v����{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_onlineDispDiv �����Ώۂł���p_onlineDispDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, p_onlineDispDiv, and �̒l�ƈ�v����{@@link BondAutoExecLimitActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductIdOnlineDispDiv( long p_productId, String p_onlineDispDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondAutoExecLimitActionRow.TYPE,
            "product_id=? and online_disp_div=?",
            null,
            new Object[] { new Long(p_productId), p_onlineDispDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductIdOnlineDispDiv(long, String)}�����{@@link #forRow(BondAutoExecLimitActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductIdOnlineDispDiv( long p_productId, String p_onlineDispDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductIdOnlineDispDiv( p_productId, p_onlineDispDiv ) );
    }

}
@
