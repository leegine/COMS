head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	ProfitLossSpecDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ProfitLossSpecDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link ProfitLossSpecRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see ProfitLossSpecPK 
 * @@see ProfitLossSpecRow 
 */
public class ProfitLossSpecDao extends DataAccessObject {


  /** 
   * ����{@@link ProfitLossSpecDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private ProfitLossSpecRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link ProfitLossSpecRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link ProfitLossSpecDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link ProfitLossSpecDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link ProfitLossSpecRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ProfitLossSpecRow )
                return new ProfitLossSpecDao( (ProfitLossSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ProfitLossSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ProfitLossSpecRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link ProfitLossSpecRow}�I�u�W�F�N�g 
    */
    protected ProfitLossSpecDao( ProfitLossSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link ProfitLossSpecRow}�I�u�W�F�N�g���擾���܂��B
   */
    public ProfitLossSpecRow getProfitLossSpecRow() {
        return row;
    }


  /** 
   * �w���{@@link ProfitLossSpecRow}�I�u�W�F�N�g����{@@link ProfitLossSpecDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link ProfitLossSpecRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link ProfitLossSpecDao}�擾�̂��߂Ɏw���{@@link ProfitLossSpecRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link ProfitLossSpecDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static ProfitLossSpecDao forRow( ProfitLossSpecRow row ) throws java.lang.IllegalArgumentException {
        return (ProfitLossSpecDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ProfitLossSpecRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link ProfitLossSpecRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link ProfitLossSpecPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link ProfitLossSpecParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ProfitLossSpecRow.TYPE );
    }


  /** 
   * {@@link ProfitLossSpecRow}����ӂɓ��肷��{@@link ProfitLossSpecPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link ProfitLossSpecRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link ProfitLossSpecParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link ProfitLossSpecPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static ProfitLossSpecPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new ProfitLossSpecPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link ProfitLossSpecRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_profitLossSpecId �����Ώۂł���p_profitLossSpecId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ProfitLossSpecRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ProfitLossSpecRow findRowByPk( long p_profitLossSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProfitLossSpecPK pk = new ProfitLossSpecPK( p_profitLossSpecId );
        return findRowByPk( pk );
    }


  /** 
   * �w���ProfitLossSpecPK�I�u�W�F�N�g����{@@link ProfitLossSpecRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����ProfitLossSpecPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ProfitLossSpecRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ProfitLossSpecRow findRowByPk( ProfitLossSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ProfitLossSpecRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(ProfitLossSpecRow)}���g�p���Ă��������B 
   */
    public static ProfitLossSpecDao findDaoByPk( long p_profitLossSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProfitLossSpecPK pk = new ProfitLossSpecPK( p_profitLossSpecId );
        ProfitLossSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(ProfitLossSpecPK)}�����{@@link #forRow(ProfitLossSpecRow)}���g�p���Ă��������B 
   */
    public static ProfitLossSpecDao findDaoByPk( ProfitLossSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ProfitLossSpecRow row = findRowByPk( pk );
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
   * p_profitLossSpecId, and �ɂĎw��̒l�����ӂ�{@@link ProfitLossSpecRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_profitLossSpecId �����Ώۂł���p_profitLossSpecId�t�B�[���h�̒l
   * 
   * @@return �����w���p_profitLossSpecId, and �̒l�ƈ�v����{@@link ProfitLossSpecRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static ProfitLossSpecRow findRowByProfitLossSpecId( long p_profitLossSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ProfitLossSpecRow.TYPE,
            "profit_loss_spec_id=?",
            null,
            new Object[] { new Long(p_profitLossSpecId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ProfitLossSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ProfitLossSpecDao.findRowsByProfitLossSpecId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProfitLossSpecId(long)}�����{@@link #forRow(ProfitLossSpecRow)}���g�p���Ă��������B 
   */
    public static ProfitLossSpecDao findDaoByProfitLossSpecId( long p_profitLossSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProfitLossSpecId( p_profitLossSpecId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_recDiv, and �ɂĎw��̒l�Ɉ�v����{@@link ProfitLossSpecRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_recDiv �����Ώۂł���p_recDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_recDiv, and �̒l�ƈ�v����{@@link ProfitLossSpecRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeRecDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_recDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ProfitLossSpecRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and rec_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_recDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeRecDiv(String, String, String, String)}�����{@@link #forRow(ProfitLossSpecRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeRecDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_recDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeRecDiv( p_institutionCode, p_branchCode, p_accountCode, p_recDiv ) );
    }

}
@
