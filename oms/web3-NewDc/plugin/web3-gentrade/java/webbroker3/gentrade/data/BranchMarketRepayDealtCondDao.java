head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketRepayDealtCondDao.java;


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
 * {@@link BranchMarketRepayDealtCondDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BranchMarketRepayDealtCondRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BranchMarketRepayDealtCondPK 
 * @@see BranchMarketRepayDealtCondRow 
 */
public class BranchMarketRepayDealtCondDao extends DataAccessObject {


  /** 
   * ����{@@link BranchMarketRepayDealtCondDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BranchMarketRepayDealtCondRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BranchMarketRepayDealtCondRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BranchMarketRepayDealtCondDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BranchMarketRepayDealtCondDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BranchMarketRepayDealtCondRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BranchMarketRepayDealtCondRow )
                return new BranchMarketRepayDealtCondDao( (BranchMarketRepayDealtCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BranchMarketRepayDealtCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BranchMarketRepayDealtCondRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BranchMarketRepayDealtCondRow}�I�u�W�F�N�g 
    */
    protected BranchMarketRepayDealtCondDao( BranchMarketRepayDealtCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BranchMarketRepayDealtCondRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BranchMarketRepayDealtCondRow getBranchMarketRepayDealtCondRow() {
        return row;
    }


  /** 
   * �w���{@@link BranchMarketRepayDealtCondRow}�I�u�W�F�N�g����{@@link BranchMarketRepayDealtCondDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BranchMarketRepayDealtCondRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BranchMarketRepayDealtCondDao}�擾�̂��߂Ɏw���{@@link BranchMarketRepayDealtCondRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BranchMarketRepayDealtCondDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BranchMarketRepayDealtCondDao forRow( BranchMarketRepayDealtCondRow row ) throws java.lang.IllegalArgumentException {
        return (BranchMarketRepayDealtCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BranchMarketRepayDealtCondRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BranchMarketRepayDealtCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BranchMarketRepayDealtCondPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BranchMarketRepayDealtCondParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BranchMarketRepayDealtCondRow.TYPE );
    }


  /** 
   * {@@link BranchMarketRepayDealtCondRow}����ӂɓ��肷��{@@link BranchMarketRepayDealtCondPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BranchMarketRepayDealtCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BranchMarketRepayDealtCondParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BranchMarketRepayDealtCondPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BranchMarketRepayDealtCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BranchMarketRepayDealtCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_repaymentDiv �����Ώۂł���p_repaymentDiv�t�B�[���h�̒l
   * @@param p_repaymentNum �����Ώۂł���p_repaymentNum�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BranchMarketRepayDealtCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BranchMarketRepayDealtCondRow findRowByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_repaymentDiv, int p_repaymentNum ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketRepayDealtCondPK pk = new BranchMarketRepayDealtCondPK( p_institutionCode, p_branchCode, p_marketCode, p_repaymentDiv, p_repaymentNum );
        return findRowByPk( pk );
    }


  /** 
   * �w���BranchMarketRepayDealtCondPK�I�u�W�F�N�g����{@@link BranchMarketRepayDealtCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BranchMarketRepayDealtCondPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BranchMarketRepayDealtCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BranchMarketRepayDealtCondRow findRowByPk( BranchMarketRepayDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BranchMarketRepayDealtCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,int)}�����{@@link #forRow(BranchMarketRepayDealtCondRow)}���g�p���Ă��������B 
   */
    public static BranchMarketRepayDealtCondDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_repaymentDiv, int p_repaymentNum ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketRepayDealtCondPK pk = new BranchMarketRepayDealtCondPK( p_institutionCode, p_branchCode, p_marketCode, p_repaymentDiv, p_repaymentNum );
        BranchMarketRepayDealtCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BranchMarketRepayDealtCondPK)}�����{@@link #forRow(BranchMarketRepayDealtCondRow)}���g�p���Ă��������B 
   */
    public static BranchMarketRepayDealtCondDao findDaoByPk( BranchMarketRepayDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketRepayDealtCondRow row = findRowByPk( pk );
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

        // (none) 

}
@
