head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoInstBranchPrefDao.java;


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
 * {@@link BatoInstBranchPrefDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BatoInstBranchPrefRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BatoInstBranchPrefPK 
 * @@see BatoInstBranchPrefRow 
 */
public class BatoInstBranchPrefDao extends DataAccessObject {


  /** 
   * ����{@@link BatoInstBranchPrefDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BatoInstBranchPrefRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BatoInstBranchPrefRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BatoInstBranchPrefDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BatoInstBranchPrefDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BatoInstBranchPrefRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatoInstBranchPrefRow )
                return new BatoInstBranchPrefDao( (BatoInstBranchPrefRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatoInstBranchPrefRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatoInstBranchPrefRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g 
    */
    protected BatoInstBranchPrefDao( BatoInstBranchPrefRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BatoInstBranchPrefRow getBatoInstBranchPrefRow() {
        return row;
    }


  /** 
   * �w���{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g����{@@link BatoInstBranchPrefDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BatoInstBranchPrefRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BatoInstBranchPrefDao}�擾�̂��߂Ɏw���{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BatoInstBranchPrefDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BatoInstBranchPrefDao forRow( BatoInstBranchPrefRow row ) throws java.lang.IllegalArgumentException {
        return (BatoInstBranchPrefDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatoInstBranchPrefRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BatoInstBranchPrefRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BatoInstBranchPrefPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BatoInstBranchPrefParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatoInstBranchPrefRow.TYPE );
    }


  /** 
   * {@@link BatoInstBranchPrefRow}����ӂɓ��肷��{@@link BatoInstBranchPrefPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BatoInstBranchPrefRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BatoInstBranchPrefParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BatoInstBranchPrefPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BatoInstBranchPrefPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BatoInstBranchPrefRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BatoInstBranchPrefRow findRowByPk( String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoInstBranchPrefPK pk = new BatoInstBranchPrefPK( p_institutionCode, p_branchCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���BatoInstBranchPrefPK�I�u�W�F�N�g����{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BatoInstBranchPrefPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BatoInstBranchPrefRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BatoInstBranchPrefRow findRowByPk( BatoInstBranchPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatoInstBranchPrefRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(BatoInstBranchPrefRow)}���g�p���Ă��������B 
   */
    public static BatoInstBranchPrefDao findDaoByPk( String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoInstBranchPrefPK pk = new BatoInstBranchPrefPK( p_institutionCode, p_branchCode );
        BatoInstBranchPrefRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BatoInstBranchPrefPK)}�����{@@link #forRow(BatoInstBranchPrefRow)}���g�p���Ă��������B 
   */
    public static BatoInstBranchPrefDao findDaoByPk( BatoInstBranchPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoInstBranchPrefRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, and �ɂĎw��̒l�����ӂ�{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, and �̒l�ƈ�v����{@@link BatoInstBranchPrefRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BatoInstBranchPrefRow findRowByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatoInstBranchPrefRow.TYPE,
            "institution_code=? and branch_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatoInstBranchPrefRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatoInstBranchPrefDao.findRowsByInstitutionCodeBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCode(String, String)}�����{@@link #forRow(BatoInstBranchPrefRow)}���g�p���Ă��������B 
   */
    public static BatoInstBranchPrefDao findDaoByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCode( p_institutionCode, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
