head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfFixedBuyingChangeDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MfFixedBuyingChangeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MfFixedBuyingChangeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MfFixedBuyingChangePK 
 * @@see MfFixedBuyingChangeRow 
 */
public class MfFixedBuyingChangeDao extends DataAccessObject {


  /** 
   * ����{@@link MfFixedBuyingChangeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MfFixedBuyingChangeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MfFixedBuyingChangeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MfFixedBuyingChangeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MfFixedBuyingChangeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MfFixedBuyingChangeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfFixedBuyingChangeRow )
                return new MfFixedBuyingChangeDao( (MfFixedBuyingChangeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfFixedBuyingChangeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfFixedBuyingChangeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g 
    */
    protected MfFixedBuyingChangeDao( MfFixedBuyingChangeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MfFixedBuyingChangeRow getMfFixedBuyingChangeRow() {
        return row;
    }


  /** 
   * �w���{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g����{@@link MfFixedBuyingChangeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MfFixedBuyingChangeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MfFixedBuyingChangeDao}�擾�̂��߂Ɏw���{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MfFixedBuyingChangeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MfFixedBuyingChangeDao forRow( MfFixedBuyingChangeRow row ) throws java.lang.IllegalArgumentException {
        return (MfFixedBuyingChangeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfFixedBuyingChangeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MfFixedBuyingChangeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MfFixedBuyingChangePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MfFixedBuyingChangeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfFixedBuyingChangeRow.TYPE );
    }


  /** 
   * {@@link MfFixedBuyingChangeRow}����ӂɓ��肷��{@@link MfFixedBuyingChangePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MfFixedBuyingChangeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MfFixedBuyingChangeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MfFixedBuyingChangePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MfFixedBuyingChangePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_validStartDate �����Ώۂł���p_validStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfFixedBuyingChangeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfFixedBuyingChangeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingChangePK pk = new MfFixedBuyingChangePK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���MfFixedBuyingChangePK�I�u�W�F�N�g����{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MfFixedBuyingChangePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfFixedBuyingChangeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfFixedBuyingChangeRow findRowByPk( MfFixedBuyingChangePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfFixedBuyingChangeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,java.sql.Timestamp)}�����{@@link #forRow(MfFixedBuyingChangeRow)}���g�p���Ă��������B 
   */
    public static MfFixedBuyingChangeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingChangePK pk = new MfFixedBuyingChangePK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate );
        MfFixedBuyingChangeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MfFixedBuyingChangePK)}�����{@@link #forRow(MfFixedBuyingChangeRow)}���g�p���Ă��������B 
   */
    public static MfFixedBuyingChangeDao findDaoByPk( MfFixedBuyingChangePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingChangeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, and �ɂĎw��̒l�����ӂ�{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_validStartDate �����Ώۂł���p_validStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate, and �̒l�ƈ�v����{@@link MfFixedBuyingChangeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MfFixedBuyingChangeRow findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfFixedBuyingChangeRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and valid_start_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfFixedBuyingChangeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfFixedBuyingChangeDao.findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(String, String, String, String, java.sql.Timestamp)}�����{@@link #forRow(MfFixedBuyingChangeRow)}���g�p���Ă��������B 
   */
    public static MfFixedBuyingChangeDao findDaoByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_validStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
