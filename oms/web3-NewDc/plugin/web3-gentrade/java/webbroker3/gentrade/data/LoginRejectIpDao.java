head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.41.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginRejectIpDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link LoginRejectIpDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link LoginRejectIpRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see LoginRejectIpPK 
 * @@see LoginRejectIpRow 
 */
public class LoginRejectIpDao extends DataAccessObject {


  /** 
   * ����{@@link LoginRejectIpDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private LoginRejectIpRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link LoginRejectIpRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link LoginRejectIpDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link LoginRejectIpDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link LoginRejectIpRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LoginRejectIpRow )
                return new LoginRejectIpDao( (LoginRejectIpRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LoginRejectIpRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LoginRejectIpRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link LoginRejectIpRow}�I�u�W�F�N�g 
    */
    protected LoginRejectIpDao( LoginRejectIpRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link LoginRejectIpRow}�I�u�W�F�N�g���擾���܂��B
   */
    public LoginRejectIpRow getLoginRejectIpRow() {
        return row;
    }


  /** 
   * �w���{@@link LoginRejectIpRow}�I�u�W�F�N�g����{@@link LoginRejectIpDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link LoginRejectIpRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link LoginRejectIpDao}�擾�̂��߂Ɏw���{@@link LoginRejectIpRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link LoginRejectIpDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static LoginRejectIpDao forRow( LoginRejectIpRow row ) throws java.lang.IllegalArgumentException {
        return (LoginRejectIpDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LoginRejectIpRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link LoginRejectIpRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link LoginRejectIpPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link LoginRejectIpParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LoginRejectIpRow.TYPE );
    }


  /** 
   * {@@link LoginRejectIpRow}����ӂɓ��肷��{@@link LoginRejectIpPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link LoginRejectIpRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link LoginRejectIpParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link LoginRejectIpPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static LoginRejectIpPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new LoginRejectIpPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link LoginRejectIpRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_loginRejectId �����Ώۂł���p_loginRejectId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LoginRejectIpRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LoginRejectIpRow findRowByPk( long p_loginRejectId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginRejectIpPK pk = new LoginRejectIpPK( p_loginRejectId );
        return findRowByPk( pk );
    }


  /** 
   * �w���LoginRejectIpPK�I�u�W�F�N�g����{@@link LoginRejectIpRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����LoginRejectIpPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LoginRejectIpRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LoginRejectIpRow findRowByPk( LoginRejectIpPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LoginRejectIpRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(LoginRejectIpRow)}���g�p���Ă��������B 
   */
    public static LoginRejectIpDao findDaoByPk( long p_loginRejectId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginRejectIpPK pk = new LoginRejectIpPK( p_loginRejectId );
        LoginRejectIpRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(LoginRejectIpPK)}�����{@@link #forRow(LoginRejectIpRow)}���g�p���Ă��������B 
   */
    public static LoginRejectIpDao findDaoByPk( LoginRejectIpPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginRejectIpRow row = findRowByPk( pk );
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
   * p_loginRejectId, and �ɂĎw��̒l�����ӂ�{@@link LoginRejectIpRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_loginRejectId �����Ώۂł���p_loginRejectId�t�B�[���h�̒l
   * 
   * @@return �����w���p_loginRejectId, and �̒l�ƈ�v����{@@link LoginRejectIpRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static LoginRejectIpRow findRowByLoginRejectId( long p_loginRejectId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LoginRejectIpRow.TYPE,
            "login_reject_id=?",
            null,
            new Object[] { new Long(p_loginRejectId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LoginRejectIpRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LoginRejectIpDao.findRowsByLoginRejectId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByLoginRejectId(long)}�����{@@link #forRow(LoginRejectIpRow)}���g�p���Ă��������B 
   */
    public static LoginRejectIpDao findDaoByLoginRejectId( long p_loginRejectId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLoginRejectId( p_loginRejectId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link LoginRejectIpRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_ipAddress �����Ώۂł���p_ipAddress�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_appliStartTimestamp �����Ώۂł���p_appliStartTimestamp�t�B�[���h�̒l
   * @@param p_appliEndTimestamp �����Ώۂł���p_appliEndTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp, and �̒l�ƈ�v����{@@link LoginRejectIpRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp( String p_institutionCode, String p_ipAddress, String p_status, java.sql.Timestamp p_appliStartTimestamp, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LoginRejectIpRow.TYPE,
            "institution_code=? and ip_address=? and status=? and appli_start_timestamp=? and appli_end_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp(String, String, String, java.sql.Timestamp, java.sql.Timestamp)}�����{@@link #forRow(LoginRejectIpRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp( String p_institutionCode, String p_ipAddress, String p_status, java.sql.Timestamp p_appliStartTimestamp, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp( p_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp ) );
    }


  /** 
   * p_institutionCode, p_status, p_appliEndTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link LoginRejectIpRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_appliEndTimestamp �����Ώۂł���p_appliEndTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_status, p_appliEndTimestamp, and �̒l�ƈ�v����{@@link LoginRejectIpRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeStatusAppliEndTimestamp( String p_institutionCode, String p_status, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LoginRejectIpRow.TYPE,
            "institution_code=? and status=? and appli_end_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_status, p_appliEndTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeStatusAppliEndTimestamp(String, String, java.sql.Timestamp)}�����{@@link #forRow(LoginRejectIpRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeStatusAppliEndTimestamp( String p_institutionCode, String p_status, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeStatusAppliEndTimestamp( p_institutionCode, p_status, p_appliEndTimestamp ) );
    }

}
@
