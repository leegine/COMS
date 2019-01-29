head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlItemMasterDao.java;


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
 * {@@link InformCtrlItemMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link InformCtrlItemMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see InformCtrlItemMasterPK 
 * @@see InformCtrlItemMasterRow 
 */
public class InformCtrlItemMasterDao extends DataAccessObject {


  /** 
   * ����{@@link InformCtrlItemMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private InformCtrlItemMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link InformCtrlItemMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link InformCtrlItemMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link InformCtrlItemMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link InformCtrlItemMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformCtrlItemMasterRow )
                return new InformCtrlItemMasterDao( (InformCtrlItemMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformCtrlItemMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformCtrlItemMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g 
    */
    protected InformCtrlItemMasterDao( InformCtrlItemMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public InformCtrlItemMasterRow getInformCtrlItemMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g����{@@link InformCtrlItemMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link InformCtrlItemMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link InformCtrlItemMasterDao}�擾�̂��߂Ɏw���{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link InformCtrlItemMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static InformCtrlItemMasterDao forRow( InformCtrlItemMasterRow row ) throws java.lang.IllegalArgumentException {
        return (InformCtrlItemMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformCtrlItemMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link InformCtrlItemMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link InformCtrlItemMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link InformCtrlItemMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformCtrlItemMasterRow.TYPE );
    }


  /** 
   * {@@link InformCtrlItemMasterRow}����ӂɓ��肷��{@@link InformCtrlItemMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link InformCtrlItemMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link InformCtrlItemMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link InformCtrlItemMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static InformCtrlItemMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformCtrlItemMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformCtrlItemMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemMasterPK pk = new InformCtrlItemMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName );
        return findRowByPk( pk );
    }


  /** 
   * �w���InformCtrlItemMasterPK�I�u�W�F�N�g����{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����InformCtrlItemMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformCtrlItemMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformCtrlItemMasterRow findRowByPk( InformCtrlItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformCtrlItemMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(InformCtrlItemMasterRow)}���g�p���Ă��������B 
   */
    public static InformCtrlItemMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemMasterPK pk = new InformCtrlItemMasterPK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName );
        InformCtrlItemMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(InformCtrlItemMasterPK)}�����{@@link #forRow(InformCtrlItemMasterRow)}���g�p���Ă��������B 
   */
    public static InformCtrlItemMasterDao findDaoByPk( InformCtrlItemMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, and �ɂĎw��̒l�����ӂ�{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, and �̒l�ƈ�v����{@@link InformCtrlItemMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static InformCtrlItemMasterRow findRowByInstitutionCodeBranchCodeInformDivItemSymbolName( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformCtrlItemMasterRow.TYPE,
            "institution_code=? and branch_code=? and inform_div=? and item_symbol_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformCtrlItemMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformCtrlItemMasterDao.findRowsByInstitutionCodeBranchCodeInformDivItemSymbolName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeInformDivItemSymbolName(String, String, String, String)}�����{@@link #forRow(InformCtrlItemMasterRow)}���g�p���Ă��������B 
   */
    public static InformCtrlItemMasterDao findDaoByInstitutionCodeBranchCodeInformDivItemSymbolName( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeInformDivItemSymbolName( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
