head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginWdogAttributeDao.java;


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
 * {@@link LoginWdogAttributeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link LoginWdogAttributeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see LoginWdogAttributePK 
 * @@see LoginWdogAttributeRow 
 */
public class LoginWdogAttributeDao extends DataAccessObject {


  /** 
   * ����{@@link LoginWdogAttributeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private LoginWdogAttributeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link LoginWdogAttributeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link LoginWdogAttributeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link LoginWdogAttributeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link LoginWdogAttributeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LoginWdogAttributeRow )
                return new LoginWdogAttributeDao( (LoginWdogAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LoginWdogAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LoginWdogAttributeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link LoginWdogAttributeRow}�I�u�W�F�N�g 
    */
    protected LoginWdogAttributeDao( LoginWdogAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link LoginWdogAttributeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public LoginWdogAttributeRow getLoginWdogAttributeRow() {
        return row;
    }


  /** 
   * �w���{@@link LoginWdogAttributeRow}�I�u�W�F�N�g����{@@link LoginWdogAttributeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link LoginWdogAttributeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link LoginWdogAttributeDao}�擾�̂��߂Ɏw���{@@link LoginWdogAttributeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link LoginWdogAttributeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static LoginWdogAttributeDao forRow( LoginWdogAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (LoginWdogAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LoginWdogAttributeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link LoginWdogAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link LoginWdogAttributePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link LoginWdogAttributeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LoginWdogAttributeRow.TYPE );
    }


  /** 
   * {@@link LoginWdogAttributeRow}����ӂɓ��肷��{@@link LoginWdogAttributePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link LoginWdogAttributeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link LoginWdogAttributeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link LoginWdogAttributePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static LoginWdogAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link LoginWdogAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_attributeName �����Ώۂł���p_attributeName�t�B�[���h�̒l
   * @@param p_attributeNameSerialNo �����Ώۂł���p_attributeNameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LoginWdogAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LoginWdogAttributeRow findRowByPk( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginWdogAttributePK pk = new LoginWdogAttributePK( p_institutionCode, p_attributeName, p_attributeNameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���LoginWdogAttributePK�I�u�W�F�N�g����{@@link LoginWdogAttributeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����LoginWdogAttributePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LoginWdogAttributeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LoginWdogAttributeRow findRowByPk( LoginWdogAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LoginWdogAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,int)}�����{@@link #forRow(LoginWdogAttributeRow)}���g�p���Ă��������B 
   */
    public static LoginWdogAttributeDao findDaoByPk( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginWdogAttributePK pk = new LoginWdogAttributePK( p_institutionCode, p_attributeName, p_attributeNameSerialNo );
        LoginWdogAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(LoginWdogAttributePK)}�����{@@link #forRow(LoginWdogAttributeRow)}���g�p���Ă��������B 
   */
    public static LoginWdogAttributeDao findDaoByPk( LoginWdogAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginWdogAttributeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_attributeName, p_attributeNameSerialNo, and �ɂĎw��̒l�����ӂ�{@@link LoginWdogAttributeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_attributeName �����Ώۂł���p_attributeName�t�B�[���h�̒l
   * @@param p_attributeNameSerialNo �����Ώۂł���p_attributeNameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_attributeName, p_attributeNameSerialNo, and �̒l�ƈ�v����{@@link LoginWdogAttributeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static LoginWdogAttributeRow findRowByInstitutionCodeAttributeNameAttributeNameSerialNo( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LoginWdogAttributeRow.TYPE,
            "institution_code=? and attribute_name=? and attribute_name_serial_no=?",
            null,
            new Object[] { p_institutionCode, p_attributeName, new Integer(p_attributeNameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LoginWdogAttributeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LoginWdogAttributeDao.findRowsByInstitutionCodeAttributeNameAttributeNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeAttributeNameAttributeNameSerialNo(String, String, int)}�����{@@link #forRow(LoginWdogAttributeRow)}���g�p���Ă��������B 
   */
    public static LoginWdogAttributeDao findDaoByInstitutionCodeAttributeNameAttributeNameSerialNo( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAttributeNameAttributeNameSerialNo( p_institutionCode, p_attributeName, p_attributeNameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@