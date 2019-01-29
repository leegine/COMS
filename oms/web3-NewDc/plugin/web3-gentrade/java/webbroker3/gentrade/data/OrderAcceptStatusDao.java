head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.37.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderAcceptStatusDao.java;


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
 * {@@link OrderAcceptStatusDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OrderAcceptStatusRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OrderAcceptStatusPK 
 * @@see OrderAcceptStatusRow 
 */
public class OrderAcceptStatusDao extends DataAccessObject {


  /** 
   * ����{@@link OrderAcceptStatusDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OrderAcceptStatusRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OrderAcceptStatusRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OrderAcceptStatusDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OrderAcceptStatusDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OrderAcceptStatusRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OrderAcceptStatusRow )
                return new OrderAcceptStatusDao( (OrderAcceptStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OrderAcceptStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OrderAcceptStatusRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OrderAcceptStatusRow}�I�u�W�F�N�g 
    */
    protected OrderAcceptStatusDao( OrderAcceptStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OrderAcceptStatusRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OrderAcceptStatusRow getOrderAcceptStatusRow() {
        return row;
    }


  /** 
   * �w���{@@link OrderAcceptStatusRow}�I�u�W�F�N�g����{@@link OrderAcceptStatusDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OrderAcceptStatusRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OrderAcceptStatusDao}�擾�̂��߂Ɏw���{@@link OrderAcceptStatusRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OrderAcceptStatusDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OrderAcceptStatusDao forRow( OrderAcceptStatusRow row ) throws java.lang.IllegalArgumentException {
        return (OrderAcceptStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OrderAcceptStatusRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OrderAcceptStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OrderAcceptStatusPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OrderAcceptStatusParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OrderAcceptStatusRow.TYPE );
    }


  /** 
   * {@@link OrderAcceptStatusRow}����ӂɓ��肷��{@@link OrderAcceptStatusPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OrderAcceptStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OrderAcceptStatusParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OrderAcceptStatusPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OrderAcceptStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OrderAcceptStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderAccProduct �����Ώۂł���p_orderAccProduct�t�B�[���h�̒l
   * @@param p_orderAccTransaction �����Ώۂł���p_orderAccTransaction�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderAcceptStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderAcceptStatusRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderAcceptStatusPK pk = new OrderAcceptStatusPK( p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction );
        return findRowByPk( pk );
    }


  /** 
   * �w���OrderAcceptStatusPK�I�u�W�F�N�g����{@@link OrderAcceptStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OrderAcceptStatusPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OrderAcceptStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OrderAcceptStatusRow findRowByPk( OrderAcceptStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OrderAcceptStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(OrderAcceptStatusRow)}���g�p���Ă��������B 
   */
    public static OrderAcceptStatusDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderAcceptStatusPK pk = new OrderAcceptStatusPK( p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction );
        OrderAcceptStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OrderAcceptStatusPK)}�����{@@link #forRow(OrderAcceptStatusRow)}���g�p���Ă��������B 
   */
    public static OrderAcceptStatusDao findDaoByPk( OrderAcceptStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OrderAcceptStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction, and �ɂĎw��̒l�����ӂ�{@@link OrderAcceptStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderAccProduct �����Ώۂł���p_orderAccProduct�t�B�[���h�̒l
   * @@param p_orderAccTransaction �����Ώۂł���p_orderAccTransaction�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction, and �̒l�ƈ�v����{@@link OrderAcceptStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OrderAcceptStatusRow findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OrderAcceptStatusRow.TYPE,
            "institution_code=? and branch_code=? and order_acc_product=? and order_acc_transaction=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OrderAcceptStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OrderAcceptStatusDao.findRowsByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(String, String, String, String)}�����{@@link #forRow(OrderAcceptStatusRow)}���g�p���Ă��������B 
   */
    public static OrderAcceptStatusDao findDaoByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction( String p_institutionCode, String p_branchCode, String p_orderAccProduct, String p_orderAccTransaction ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction( p_institutionCode, p_branchCode, p_orderAccProduct, p_orderAccTransaction ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
