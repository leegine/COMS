head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorTypeDao.java;


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
 * {@@link AdministratorTypeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AdministratorTypeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AdministratorTypePK 
 * @@see AdministratorTypeRow 
 */
public class AdministratorTypeDao extends DataAccessObject {


  /** 
   * ����{@@link AdministratorTypeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AdministratorTypeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AdministratorTypeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AdministratorTypeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AdministratorTypeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AdministratorTypeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorTypeRow )
                return new AdministratorTypeDao( (AdministratorTypeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorTypeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorTypeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AdministratorTypeRow}�I�u�W�F�N�g 
    */
    protected AdministratorTypeDao( AdministratorTypeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AdministratorTypeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AdministratorTypeRow getAdministratorTypeRow() {
        return row;
    }


  /** 
   * �w���{@@link AdministratorTypeRow}�I�u�W�F�N�g����{@@link AdministratorTypeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AdministratorTypeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AdministratorTypeDao}�擾�̂��߂Ɏw���{@@link AdministratorTypeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AdministratorTypeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AdministratorTypeDao forRow( AdministratorTypeRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorTypeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorTypeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AdministratorTypeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AdministratorTypePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AdministratorTypeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorTypeRow.TYPE );
    }


  /** 
   * {@@link AdministratorTypeRow}����ӂɓ��肷��{@@link AdministratorTypePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AdministratorTypeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AdministratorTypeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AdministratorTypePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AdministratorTypePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AdministratorTypeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_permissionLevel �����Ώۂł���p_permissionLevel�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorTypeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorTypeRow findRowByPk( String p_institutionCode, String p_permissionLevel ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorTypePK pk = new AdministratorTypePK( p_institutionCode, p_permissionLevel );
        return findRowByPk( pk );
    }


  /** 
   * �w���AdministratorTypePK�I�u�W�F�N�g����{@@link AdministratorTypeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AdministratorTypePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorTypeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorTypeRow findRowByPk( AdministratorTypePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorTypeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(AdministratorTypeRow)}���g�p���Ă��������B 
   */
    public static AdministratorTypeDao findDaoByPk( String p_institutionCode, String p_permissionLevel ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorTypePK pk = new AdministratorTypePK( p_institutionCode, p_permissionLevel );
        AdministratorTypeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AdministratorTypePK)}�����{@@link #forRow(AdministratorTypeRow)}���g�p���Ă��������B 
   */
    public static AdministratorTypeDao findDaoByPk( AdministratorTypePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorTypeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_permissionLevel, and �ɂĎw��̒l�����ӂ�{@@link AdministratorTypeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_permissionLevel �����Ώۂł���p_permissionLevel�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_permissionLevel, and �̒l�ƈ�v����{@@link AdministratorTypeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdministratorTypeRow findRowByInstitutionCodePermissionLevel( String p_institutionCode, String p_permissionLevel ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorTypeRow.TYPE,
            "institution_code=? and permission_level=?",
            null,
            new Object[] { p_institutionCode, p_permissionLevel } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorTypeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorTypeDao.findRowsByInstitutionCodePermissionLevel(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodePermissionLevel(String, String)}�����{@@link #forRow(AdministratorTypeRow)}���g�p���Ă��������B 
   */
    public static AdministratorTypeDao findDaoByInstitutionCodePermissionLevel( String p_institutionCode, String p_permissionLevel ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodePermissionLevel( p_institutionCode, p_permissionLevel ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
