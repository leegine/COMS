head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiDealingCommDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.srvregi.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SrvRegiDealingCommDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SrvRegiDealingCommRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SrvRegiDealingCommPK 
 * @@see SrvRegiDealingCommRow 
 */
public class SrvRegiDealingCommDao extends DataAccessObject {


  /** 
   * ����{@@link SrvRegiDealingCommDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SrvRegiDealingCommRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SrvRegiDealingCommRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SrvRegiDealingCommDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SrvRegiDealingCommDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SrvRegiDealingCommRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiDealingCommRow )
                return new SrvRegiDealingCommDao( (SrvRegiDealingCommRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiDealingCommRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiDealingCommRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g 
    */
    protected SrvRegiDealingCommDao( SrvRegiDealingCommRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SrvRegiDealingCommRow getSrvRegiDealingCommRow() {
        return row;
    }


  /** 
   * �w���{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g����{@@link SrvRegiDealingCommDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SrvRegiDealingCommRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SrvRegiDealingCommDao}�擾�̂��߂Ɏw���{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SrvRegiDealingCommDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SrvRegiDealingCommDao forRow( SrvRegiDealingCommRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiDealingCommDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiDealingCommRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SrvRegiDealingCommRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SrvRegiDealingCommPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SrvRegiDealingCommParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiDealingCommRow.TYPE );
    }


  /** 
   * {@@link SrvRegiDealingCommRow}����ӂɓ��肷��{@@link SrvRegiDealingCommPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SrvRegiDealingCommRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SrvRegiDealingCommParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SrvRegiDealingCommPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SrvRegiDealingCommPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_accumulateTerm �����Ώۂł���p_accumulateTerm�t�B�[���h�̒l
   * @@param p_orderAccProduct �����Ώۂł���p_orderAccProduct�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiDealingCommRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiDealingCommRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiDealingCommPK pk = new SrvRegiDealingCommPK( p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct );
        return findRowByPk( pk );
    }


  /** 
   * �w���SrvRegiDealingCommPK�I�u�W�F�N�g����{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SrvRegiDealingCommPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiDealingCommRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiDealingCommRow findRowByPk( SrvRegiDealingCommPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiDealingCommRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(SrvRegiDealingCommRow)}���g�p���Ă��������B 
   */
    public static SrvRegiDealingCommDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiDealingCommPK pk = new SrvRegiDealingCommPK( p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct );
        SrvRegiDealingCommRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SrvRegiDealingCommPK)}�����{@@link #forRow(SrvRegiDealingCommRow)}���g�p���Ă��������B 
   */
    public static SrvRegiDealingCommDao findDaoByPk( SrvRegiDealingCommPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiDealingCommRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct, and �ɂĎw��̒l�����ӂ�{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_accumulateTerm �����Ώۂł���p_accumulateTerm�t�B�[���h�̒l
   * @@param p_orderAccProduct �����Ώۂł���p_orderAccProduct�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct, and �̒l�ƈ�v����{@@link SrvRegiDealingCommRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SrvRegiDealingCommRow findRowByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiDealingCommRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and accumulate_term=? and order_acc_product=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiDealingCommRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiDealingCommDao.findRowsByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct(String, String, String, String, String)}�����{@@link #forRow(SrvRegiDealingCommRow)}���g�p���Ă��������B 
   */
    public static SrvRegiDealingCommDao findDaoByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct( String p_institutionCode, String p_branchCode, String p_accountCode, String p_accumulateTerm, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeAccumulateTermOrderAccProduct( p_institutionCode, p_branchCode, p_accountCode, p_accumulateTerm, p_orderAccProduct ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
