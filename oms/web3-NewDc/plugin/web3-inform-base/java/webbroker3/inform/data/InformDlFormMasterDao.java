head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.59.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformDlFormMasterDao.java;


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
 * {@@link InformDlFormMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link InformDlFormMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see InformDlFormMasterPK 
 * @@see InformDlFormMasterRow 
 */
public class InformDlFormMasterDao extends DataAccessObject {


  /** 
   * ����{@@link InformDlFormMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private InformDlFormMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link InformDlFormMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link InformDlFormMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link InformDlFormMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link InformDlFormMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformDlFormMasterRow )
                return new InformDlFormMasterDao( (InformDlFormMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformDlFormMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformDlFormMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link InformDlFormMasterRow}�I�u�W�F�N�g 
    */
    protected InformDlFormMasterDao( InformDlFormMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link InformDlFormMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public InformDlFormMasterRow getInformDlFormMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link InformDlFormMasterRow}�I�u�W�F�N�g����{@@link InformDlFormMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link InformDlFormMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link InformDlFormMasterDao}�擾�̂��߂Ɏw���{@@link InformDlFormMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link InformDlFormMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static InformDlFormMasterDao forRow( InformDlFormMasterRow row ) throws java.lang.IllegalArgumentException {
        return (InformDlFormMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformDlFormMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link InformDlFormMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link InformDlFormMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link InformDlFormMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformDlFormMasterRow.TYPE );
    }


  /** 
   * {@@link InformDlFormMasterRow}����ӂɓ��肷��{@@link InformDlFormMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link InformDlFormMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link InformDlFormMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link InformDlFormMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static InformDlFormMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link InformDlFormMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_columnNumber �����Ώۂł���p_columnNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformDlFormMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformDlFormMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        InformDlFormMasterPK pk = new InformDlFormMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_columnNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���InformDlFormMasterPK�I�u�W�F�N�g����{@@link InformDlFormMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����InformDlFormMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformDlFormMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformDlFormMasterRow findRowByPk( InformDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformDlFormMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,int)}�����{@@link #forRow(InformDlFormMasterRow)}���g�p���Ă��������B 
   */
    public static InformDlFormMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        InformDlFormMasterPK pk = new InformDlFormMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_columnNumber );
        InformDlFormMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(InformDlFormMasterPK)}�����{@@link #forRow(InformDlFormMasterRow)}���g�p���Ă��������B 
   */
    public static InformDlFormMasterDao findDaoByPk( InformDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformDlFormMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_informDiv, p_columnNumber, and �ɂĎw��̒l�����ӂ�{@@link InformDlFormMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_columnNumber �����Ώۂł���p_columnNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_informDiv, p_columnNumber, and �̒l�ƈ�v����{@@link InformDlFormMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static InformDlFormMasterRow findRowByInstitutionCodeBranchCodeInformDivColumnNumber( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformDlFormMasterRow.TYPE,
            "institution_code=? and branch_code=? and inform_div=? and column_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_informDiv, new Integer(p_columnNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformDlFormMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformDlFormMasterDao.findRowsByInstitutionCodeBranchCodeInformDivColumnNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeInformDivColumnNumber(String, String, String, int)}�����{@@link #forRow(InformDlFormMasterRow)}���g�p���Ă��������B 
   */
    public static InformDlFormMasterDao findDaoByInstitutionCodeBranchCodeInformDivColumnNumber( String p_institutionCode, String p_branchCode, String p_informDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeInformDivColumnNumber( p_institutionCode, p_branchCode, p_informDiv, p_columnNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
