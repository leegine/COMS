head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfFixedBuyingCondDao.java;


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
 * {@@link MfFixedBuyingCondDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MfFixedBuyingCondRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MfFixedBuyingCondPK 
 * @@see MfFixedBuyingCondRow 
 */
public class MfFixedBuyingCondDao extends DataAccessObject {


  /** 
   * ����{@@link MfFixedBuyingCondDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MfFixedBuyingCondRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MfFixedBuyingCondRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MfFixedBuyingCondDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MfFixedBuyingCondDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MfFixedBuyingCondRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfFixedBuyingCondRow )
                return new MfFixedBuyingCondDao( (MfFixedBuyingCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfFixedBuyingCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfFixedBuyingCondRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g 
    */
    protected MfFixedBuyingCondDao( MfFixedBuyingCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MfFixedBuyingCondRow getMfFixedBuyingCondRow() {
        return row;
    }


  /** 
   * �w���{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g����{@@link MfFixedBuyingCondDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MfFixedBuyingCondRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MfFixedBuyingCondDao}�擾�̂��߂Ɏw���{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MfFixedBuyingCondDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MfFixedBuyingCondDao forRow( MfFixedBuyingCondRow row ) throws java.lang.IllegalArgumentException {
        return (MfFixedBuyingCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfFixedBuyingCondRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MfFixedBuyingCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MfFixedBuyingCondPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MfFixedBuyingCondParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfFixedBuyingCondRow.TYPE );
    }


  /** 
   * {@@link MfFixedBuyingCondRow}����ӂɓ��肷��{@@link MfFixedBuyingCondPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MfFixedBuyingCondRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MfFixedBuyingCondParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MfFixedBuyingCondPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MfFixedBuyingCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_drawDate �����Ώۂł���p_drawDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfFixedBuyingCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfFixedBuyingCondRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingCondPK pk = new MfFixedBuyingCondPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���MfFixedBuyingCondPK�I�u�W�F�N�g����{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MfFixedBuyingCondPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfFixedBuyingCondRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfFixedBuyingCondRow findRowByPk( MfFixedBuyingCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfFixedBuyingCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,java.sql.Timestamp)}�����{@@link #forRow(MfFixedBuyingCondRow)}���g�p���Ă��������B 
   */
    public static MfFixedBuyingCondDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingCondPK pk = new MfFixedBuyingCondPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate );
        MfFixedBuyingCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MfFixedBuyingCondPK)}�����{@@link #forRow(MfFixedBuyingCondRow)}���g�p���Ă��������B 
   */
    public static MfFixedBuyingCondDao findDaoByPk( MfFixedBuyingCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingCondRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate, and �ɂĎw��̒l�����ӂ�{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_drawDate �����Ώۂł���p_drawDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate, and �̒l�ƈ�v����{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MfFixedBuyingCondRow findRowByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfFixedBuyingCondRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and draw_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfFixedBuyingCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfFixedBuyingCondDao.findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate(String, String, String, String, java.sql.Timestamp)}�����{@@link #forRow(MfFixedBuyingCondRow)}���g�p���Ă��������B 
   */
    public static MfFixedBuyingCondDao findDaoByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeProductCodeDrawDate( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_drawDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_drawDate, and �ɂĎw��̒l�Ɉ�v����{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_drawDate �����Ώۂł���p_drawDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_drawDate, and �̒l�ƈ�v����{@@link MfFixedBuyingCondRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MfFixedBuyingCondRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and draw_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_drawDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeDrawDate(String, String, String, java.sql.Timestamp)}�����{@@link #forRow(MfFixedBuyingCondRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeDrawDate( String p_institutionCode, String p_branchCode, String p_accountCode, java.sql.Timestamp p_drawDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeDrawDate( p_institutionCode, p_branchCode, p_accountCode, p_drawDate ) );
    }

}
@
