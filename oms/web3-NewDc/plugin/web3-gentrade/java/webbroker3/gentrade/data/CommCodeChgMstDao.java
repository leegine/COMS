head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CommCodeChgMstDao.java;


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
 * {@@link CommCodeChgMstDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CommCodeChgMstRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CommCodeChgMstPK 
 * @@see CommCodeChgMstRow 
 */
public class CommCodeChgMstDao extends DataAccessObject {


  /** 
   * ����{@@link CommCodeChgMstDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CommCodeChgMstRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CommCodeChgMstRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CommCodeChgMstDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CommCodeChgMstDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CommCodeChgMstRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCodeChgMstRow )
                return new CommCodeChgMstDao( (CommCodeChgMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCodeChgMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCodeChgMstRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CommCodeChgMstRow}�I�u�W�F�N�g 
    */
    protected CommCodeChgMstDao( CommCodeChgMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CommCodeChgMstRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CommCodeChgMstRow getCommCodeChgMstRow() {
        return row;
    }


  /** 
   * �w���{@@link CommCodeChgMstRow}�I�u�W�F�N�g����{@@link CommCodeChgMstDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CommCodeChgMstRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CommCodeChgMstDao}�擾�̂��߂Ɏw���{@@link CommCodeChgMstRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CommCodeChgMstDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CommCodeChgMstDao forRow( CommCodeChgMstRow row ) throws java.lang.IllegalArgumentException {
        return (CommCodeChgMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCodeChgMstRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CommCodeChgMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CommCodeChgMstPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CommCodeChgMstParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCodeChgMstRow.TYPE );
    }


  /** 
   * {@@link CommCodeChgMstRow}����ӂɓ��肷��{@@link CommCodeChgMstPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CommCodeChgMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CommCodeChgMstParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CommCodeChgMstPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CommCodeChgMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CommCodeChgMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_commissionNo �����Ώۂł���p_commissionNo�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCodeChgMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCodeChgMstRow findRowByPk( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCodeChgMstPK pk = new CommCodeChgMstPK( p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���CommCodeChgMstPK�I�u�W�F�N�g����{@@link CommCodeChgMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CommCodeChgMstPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCodeChgMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCodeChgMstRow findRowByPk( CommCodeChgMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCodeChgMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,String,String)}�����{@@link #forRow(CommCodeChgMstRow)}���g�p���Ă��������B 
   */
    public static CommCodeChgMstDao findDaoByPk( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCodeChgMstPK pk = new CommCodeChgMstPK( p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate );
        CommCodeChgMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CommCodeChgMstPK)}�����{@@link #forRow(CommCodeChgMstRow)}���g�p���Ă��������B 
   */
    public static CommCodeChgMstDao findDaoByPk( CommCodeChgMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCodeChgMstRow row = findRowByPk( pk );
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
   * p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate, and �ɂĎw��̒l�����ӂ�{@@link CommCodeChgMstRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_commissionNo �����Ώۂł���p_commissionNo�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate, and �̒l�ƈ�v����{@@link CommCodeChgMstRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CommCodeChgMstRow findRowByBranchIdCommProductCodeCommissionNoAppliStartDate( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCodeChgMstRow.TYPE,
            "branch_id=? and comm_product_code=? and commission_no=? and appli_start_date=?",
            null,
            new Object[] { new Long(p_branchId), p_commProductCode, p_commissionNo, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCodeChgMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCodeChgMstDao.findRowsByBranchIdCommProductCodeCommissionNoAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBranchIdCommProductCodeCommissionNoAppliStartDate(long, String, String, String)}�����{@@link #forRow(CommCodeChgMstRow)}���g�p���Ă��������B 
   */
    public static CommCodeChgMstDao findDaoByBranchIdCommProductCodeCommissionNoAppliStartDate( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchIdCommProductCodeCommissionNoAppliStartDate( p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
