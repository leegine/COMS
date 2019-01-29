head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenItemAttributeDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AccOpenItemAttributeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenItemAttributeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenItemAttributePK 
 * @@see AccOpenItemAttributeRow 
 */
public class AccOpenItemAttributeDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenItemAttributeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenItemAttributeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenItemAttributeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenItemAttributeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenItemAttributeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenItemAttributeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenItemAttributeRow )
                return new AccOpenItemAttributeDao( (AccOpenItemAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenItemAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenItemAttributeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g 
    */
    protected AccOpenItemAttributeDao( AccOpenItemAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenItemAttributeRow getAccOpenItemAttributeRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g����{@@link AccOpenItemAttributeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenItemAttributeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenItemAttributeDao}�擾�̂��߂Ɏw���{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenItemAttributeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenItemAttributeDao forRow( AccOpenItemAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenItemAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenItemAttributeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenItemAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenItemAttributePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenItemAttributeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenItemAttributeRow.TYPE );
    }


  /** 
   * {@@link AccOpenItemAttributeRow}����ӂɓ��肷��{@@link AccOpenItemAttributePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenItemAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenItemAttributeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenItemAttributePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenItemAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * @@param p_attributeValue �����Ώۂł���p_attributeValue�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenItemAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenItemAttributeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemAttributePK pk = new AccOpenItemAttributePK( p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenItemAttributePK�I�u�W�F�N�g����{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenItemAttributePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenItemAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenItemAttributeRow findRowByPk( AccOpenItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenItemAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(AccOpenItemAttributeRow)}���g�p���Ă��������B 
   */
    public static AccOpenItemAttributeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemAttributePK pk = new AccOpenItemAttributePK( p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue );
        AccOpenItemAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenItemAttributePK)}�����{@@link #forRow(AccOpenItemAttributeRow)}���g�p���Ă��������B 
   */
    public static AccOpenItemAttributeDao findDaoByPk( AccOpenItemAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenItemAttributeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue, and �ɂĎw��̒l�����ӂ�{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_itemSymbolName �����Ώۂł���p_itemSymbolName�t�B�[���h�̒l
   * @@param p_attributeValue �����Ώۂł���p_attributeValue�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue, and �̒l�ƈ�v����{@@link AccOpenItemAttributeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenItemAttributeRow findRowByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenItemAttributeRow.TYPE,
            "institution_code=? and branch_code=? and account_div=? and item_symbol_name=? and attribute_value=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenItemAttributeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenItemAttributeDao.findRowsByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue(String, String, String, String, String)}�����{@@link #forRow(AccOpenItemAttributeRow)}���g�p���Ă��������B 
   */
    public static AccOpenItemAttributeDao findDaoByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_itemSymbolName, String p_attributeValue ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDivItemSymbolNameAttributeValue( p_institutionCode, p_branchCode, p_accountDiv, p_itemSymbolName, p_attributeValue ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
