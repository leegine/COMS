head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	VariousInformDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.inform.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link VariousInformDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link VariousInformRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see VariousInformPK 
 * @@see VariousInformRow 
 */
public class VariousInformDao extends DataAccessObject {


  /** 
   * ����{@@link VariousInformDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private VariousInformRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link VariousInformRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link VariousInformDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link VariousInformDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link VariousInformRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof VariousInformRow )
                return new VariousInformDao( (VariousInformRow) row );
            throw new java.lang.IllegalArgumentException( "Not a VariousInformRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link VariousInformRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link VariousInformRow}�I�u�W�F�N�g 
    */
    protected VariousInformDao( VariousInformRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link VariousInformRow}�I�u�W�F�N�g���擾���܂��B
   */
    public VariousInformRow getVariousInformRow() {
        return row;
    }


  /** 
   * �w���{@@link VariousInformRow}�I�u�W�F�N�g����{@@link VariousInformDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link VariousInformRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link VariousInformDao}�擾�̂��߂Ɏw���{@@link VariousInformRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link VariousInformDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static VariousInformDao forRow( VariousInformRow row ) throws java.lang.IllegalArgumentException {
        return (VariousInformDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link VariousInformRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link VariousInformRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link VariousInformPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link VariousInformParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( VariousInformRow.TYPE );
    }


  /** 
   * {@@link VariousInformRow}����ӂɓ��肷��{@@link VariousInformPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link VariousInformRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link VariousInformParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link VariousInformPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static VariousInformPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link VariousInformRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_requestNumber �����Ώۂł���p_requestNumber�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link VariousInformRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static VariousInformRow findRowByPk( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VariousInformPK pk = new VariousInformPK( p_institutionCode, p_informDiv, p_requestNumber, p_branchCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���VariousInformPK�I�u�W�F�N�g����{@@link VariousInformRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����VariousInformPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link VariousInformRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static VariousInformRow findRowByPk( VariousInformPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (VariousInformRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(VariousInformRow)}���g�p���Ă��������B 
   */
    public static VariousInformDao findDaoByPk( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VariousInformPK pk = new VariousInformPK( p_institutionCode, p_informDiv, p_requestNumber, p_branchCode );
        VariousInformRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(VariousInformPK)}�����{@@link #forRow(VariousInformRow)}���g�p���Ă��������B 
   */
    public static VariousInformDao findDaoByPk( VariousInformPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        VariousInformRow row = findRowByPk( pk );
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
   * p_institutionCode, p_informDiv, p_requestNumber, p_branchCode, and �ɂĎw��̒l�����ӂ�{@@link VariousInformRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_requestNumber �����Ώۂł���p_requestNumber�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_informDiv, p_requestNumber, p_branchCode, and �̒l�ƈ�v����{@@link VariousInformRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static VariousInformRow findRowByInstitutionCodeInformDivRequestNumberBranchCode( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            VariousInformRow.TYPE,
            "institution_code=? and inform_div=? and request_number=? and branch_code=?",
            null,
            new Object[] { p_institutionCode, p_informDiv, p_requestNumber, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (VariousInformRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query VariousInformDao.findRowsByInstitutionCodeInformDivRequestNumberBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeInformDivRequestNumberBranchCode(String, String, String, String)}�����{@@link #forRow(VariousInformRow)}���g�p���Ă��������B 
   */
    public static VariousInformDao findDaoByInstitutionCodeInformDivRequestNumberBranchCode( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeInformDivRequestNumberBranchCode( p_institutionCode, p_informDiv, p_requestNumber, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@