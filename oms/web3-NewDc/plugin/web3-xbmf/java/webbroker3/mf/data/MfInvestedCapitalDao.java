head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfInvestedCapitalDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MfInvestedCapitalDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MfInvestedCapitalRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MfInvestedCapitalPK 
 * @@see MfInvestedCapitalRow 
 */
public class MfInvestedCapitalDao extends DataAccessObject {


  /** 
   * ����{@@link MfInvestedCapitalDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MfInvestedCapitalRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MfInvestedCapitalRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MfInvestedCapitalDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MfInvestedCapitalDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MfInvestedCapitalRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfInvestedCapitalRow )
                return new MfInvestedCapitalDao( (MfInvestedCapitalRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfInvestedCapitalRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfInvestedCapitalRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MfInvestedCapitalRow}�I�u�W�F�N�g 
    */
    protected MfInvestedCapitalDao( MfInvestedCapitalRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MfInvestedCapitalRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MfInvestedCapitalRow getMfInvestedCapitalRow() {
        return row;
    }


  /** 
   * �w���{@@link MfInvestedCapitalRow}�I�u�W�F�N�g����{@@link MfInvestedCapitalDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MfInvestedCapitalRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MfInvestedCapitalDao}�擾�̂��߂Ɏw���{@@link MfInvestedCapitalRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MfInvestedCapitalDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MfInvestedCapitalDao forRow( MfInvestedCapitalRow row ) throws java.lang.IllegalArgumentException {
        return (MfInvestedCapitalDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfInvestedCapitalRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MfInvestedCapitalRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MfInvestedCapitalPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MfInvestedCapitalParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfInvestedCapitalRow.TYPE );
    }


  /** 
   * {@@link MfInvestedCapitalRow}����ӂɓ��肷��{@@link MfInvestedCapitalPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MfInvestedCapitalRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MfInvestedCapitalParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MfInvestedCapitalPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃R�������܂܂�Ă��邩�R�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MfInvestedCapitalPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MfInvestedCapitalRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_taxType �����Ώۂł���p_taxType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfInvestedCapitalRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfInvestedCapitalRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataFindException, DataQueryException, DataNetworkException {
        MfInvestedCapitalPK pk = new MfInvestedCapitalPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType );
        return findRowByPk( pk );
    }


  /** 
   * �w���MfInvestedCapitalPK�I�u�W�F�N�g����{@@link MfInvestedCapitalRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MfInvestedCapitalPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfInvestedCapitalRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfInvestedCapitalRow findRowByPk( MfInvestedCapitalPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfInvestedCapitalRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,int)}�����{@@link #forRow(MfInvestedCapitalRow)}���g�p���Ă��������B 
   */
    public static MfInvestedCapitalDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataFindException, DataQueryException, DataNetworkException {
        MfInvestedCapitalPK pk = new MfInvestedCapitalPK( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType );
        MfInvestedCapitalRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MfInvestedCapitalPK)}�����{@@link #forRow(MfInvestedCapitalRow)}���g�p���Ă��������B 
   */
    public static MfInvestedCapitalDao findDaoByPk( MfInvestedCapitalPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfInvestedCapitalRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, and �ɂĎw��̒l�����ӂ�{@@link MfInvestedCapitalRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_taxType �����Ώۂł���p_taxType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType, and �̒l�ƈ�v����{@@link MfInvestedCapitalRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MfInvestedCapitalRow findRowByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfInvestedCapitalRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and product_code=? and tax_type=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_productCode, new Integer(p_taxType) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfInvestedCapitalRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfInvestedCapitalDao.findRowsByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType(String, String, String, String, int)}�����{@@link #forRow(MfInvestedCapitalRow)}���g�p���Ă��������B 
   */
    public static MfInvestedCapitalDao findDaoByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, int p_taxType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeProductCodeTaxType( p_institutionCode, p_branchCode, p_accountCode, p_productCode, p_taxType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
