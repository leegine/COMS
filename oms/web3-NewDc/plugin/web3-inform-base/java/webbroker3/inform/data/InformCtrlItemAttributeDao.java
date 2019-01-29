head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlItemAttributeDao.java;


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
 * {@@link InformCtrlItemAttributeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link InformCtrlItemAttributeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see InformCtrlItemAttributePK 
 * @@see InformCtrlItemAttributeRow 
 */
public class InformCtrlItemAttributeDao extends DataAccessObject {


  /** 
   * ����{@@link InformCtrlItemAttributeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private InformCtrlItemAttributeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link InformCtrlItemAttributeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link InformCtrlItemAttributeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link InformCtrlItemAttributeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link InformCtrlItemAttributeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformCtrlItemAttributeRow )
                return new InformCtrlItemAttributeDao( (InformCtrlItemAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformCtrlItemAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformCtrlItemAttributeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g 
    */
    protected InformCtrlItemAttributeDao( InformCtrlItemAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public InformCtrlItemAttributeRow getInformCtrlItemAttributeRow() {
        return row;
    }


  /** 
   * �w���{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g����{@@link InformCtrlItemAttributeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link InformCtrlItemAttributeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link InformCtrlItemAttributeDao}�擾�̂��߂Ɏw���{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link InformCtrlItemAttributeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static InformCtrlItemAttributeDao forRow( InformCtrlItemAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (InformCtrlItemAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformCtrlItemAttributeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link InformCtrlItemAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link InformCtrlItemAttributePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link InformCtrlItemAttributeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformCtrlItemAttributeRow.TYPE );
    }


  /** 
   * {@@link InformCtrlItemAttributeRow}����ӂɓ��肷��{@@link InformCtrlItemAttributePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link InformCtrlItemAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link InformCtrlItemAttributeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link InformCtrlItemAttributePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static InformCtrlItemAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * @@param p_attributeValue �����Ώۂł���p_attributeValue�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformCtrlItemAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformCtrlItemAttributeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemAttributePK pk = new InformCtrlItemAttributePK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue );
        return findRowByPk( pk );
    }


  /** 
   * �w���InformCtrlItemAttributePK�I�u�W�F�N�g����{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����InformCtrlItemAttributePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformCtrlItemAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformCtrlItemAttributeRow findRowByPk( InformCtrlItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformCtrlItemAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(InformCtrlItemAttributeRow)}���g�p���Ă��������B 
   */
    public static InformCtrlItemAttributeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemAttributePK pk = new InformCtrlItemAttributePK( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue );
        InformCtrlItemAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(InformCtrlItemAttributePK)}�����{@@link #forRow(InformCtrlItemAttributeRow)}���g�p���Ă��������B 
   */
    public static InformCtrlItemAttributeDao findDaoByPk( InformCtrlItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlItemAttributeRow row = findRowByPk( pk );
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


  /** 
   * p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue, and �ɂĎw��̒l�Ɉ�v����{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * @@param p_attributeValue �����Ώۂł���p_attributeValue�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue, and �̒l�ƈ�v����{@@link InformCtrlItemAttributeRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InformCtrlItemAttributeRow.TYPE,
            "institution_code=? and branch_code=? and inform_div=? and item_symbol_name=? and attribute_value=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue(String, String, String, String, String)}�����{@@link #forRow(InformCtrlItemAttributeRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_informDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeInformDivItemSymbolNameAttributeValue( p_institutionCode, p_branchCode, p_informDiv, p_itemSymbolName, p_attributeValue ) );
    }

}
@
