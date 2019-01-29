head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorDao.java;


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
 * {@@link AdministratorDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AdministratorRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AdministratorPK 
 * @@see AdministratorRow 
 */
public class AdministratorDao extends DataAccessObject {


  /** 
   * ����{@@link AdministratorDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AdministratorRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AdministratorRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AdministratorDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AdministratorDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AdministratorRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorRow )
                return new AdministratorDao( (AdministratorRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AdministratorRow}�I�u�W�F�N�g 
    */
    protected AdministratorDao( AdministratorRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AdministratorRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AdministratorRow getAdministratorRow() {
        return row;
    }


  /** 
   * �w���{@@link AdministratorRow}�I�u�W�F�N�g����{@@link AdministratorDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AdministratorRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AdministratorDao}�擾�̂��߂Ɏw���{@@link AdministratorRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AdministratorDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AdministratorDao forRow( AdministratorRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AdministratorRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AdministratorPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AdministratorParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorRow.TYPE );
    }


  /** 
   * {@@link AdministratorRow}����ӂɓ��肷��{@@link AdministratorPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AdministratorRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AdministratorParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AdministratorPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AdministratorPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AdministratorPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AdministratorRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_administratorId �����Ώۂł���p_administratorId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorRow findRowByPk( long p_administratorId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorPK pk = new AdministratorPK( p_administratorId );
        return findRowByPk( pk );
    }


  /** 
   * �w���AdministratorPK�I�u�W�F�N�g����{@@link AdministratorRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AdministratorPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorRow findRowByPk( AdministratorPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(AdministratorRow)}���g�p���Ă��������B 
   */
    public static AdministratorDao findDaoByPk( long p_administratorId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorPK pk = new AdministratorPK( p_administratorId );
        AdministratorRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AdministratorPK)}�����{@@link #forRow(AdministratorRow)}���g�p���Ă��������B 
   */
    public static AdministratorDao findDaoByPk( AdministratorPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorRow row = findRowByPk( pk );
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
   * p_administratorId, and �ɂĎw��̒l�����ӂ�{@@link AdministratorRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_administratorId �����Ώۂł���p_administratorId�t�B�[���h�̒l
   * 
   * @@return �����w���p_administratorId, and �̒l�ƈ�v����{@@link AdministratorRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdministratorRow findRowByAdministratorId( long p_administratorId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorRow.TYPE,
            "administrator_id=?",
            null,
            new Object[] { new Long(p_administratorId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorDao.findRowsByAdministratorId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAdministratorId(long)}�����{@@link #forRow(AdministratorRow)}���g�p���Ă��������B 
   */
    public static AdministratorDao findDaoByAdministratorId( long p_administratorId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAdministratorId( p_administratorId ) );
    }


  /** 
   * p_institutionCode, p_administratorCode, and �ɂĎw��̒l�����ӂ�{@@link AdministratorRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_administratorCode �����Ώۂł���p_administratorCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_administratorCode, and �̒l�ƈ�v����{@@link AdministratorRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdministratorRow findRowByInstitutionCodeAdministratorCode( String p_institutionCode, String p_administratorCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorRow.TYPE,
            "institution_code=? and administrator_code=?",
            null,
            new Object[] { p_institutionCode, p_administratorCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorDao.findRowsByInstitutionCodeAdministratorCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeAdministratorCode(String, String)}�����{@@link #forRow(AdministratorRow)}���g�p���Ă��������B 
   */
    public static AdministratorDao findDaoByInstitutionCodeAdministratorCode( String p_institutionCode, String p_administratorCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAdministratorCode( p_institutionCode, p_administratorCode ) );
    }


  /** 
   * p_loginId, and �ɂĎw��̒l�����ӂ�{@@link AdministratorRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_loginId �����Ώۂł���p_loginId�t�B�[���h�̒l
   * 
   * @@return �����w���p_loginId, and �̒l�ƈ�v����{@@link AdministratorRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdministratorRow findRowByLoginId( long p_loginId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorRow.TYPE,
            "login_id=?",
            null,
            new Object[] { new Long(p_loginId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorDao.findRowsByLoginId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByLoginId(long)}�����{@@link #forRow(AdministratorRow)}���g�p���Ă��������B 
   */
    public static AdministratorDao findDaoByLoginId( long p_loginId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLoginId( p_loginId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
