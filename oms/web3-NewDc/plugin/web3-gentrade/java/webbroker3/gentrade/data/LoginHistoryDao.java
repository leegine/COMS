head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginHistoryDao.java;


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
 * {@@link LoginHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link LoginHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see LoginHistoryPK 
 * @@see LoginHistoryRow 
 */
public class LoginHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link LoginHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private LoginHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link LoginHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link LoginHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link LoginHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link LoginHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LoginHistoryRow )
                return new LoginHistoryDao( (LoginHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LoginHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LoginHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link LoginHistoryRow}�I�u�W�F�N�g 
    */
    protected LoginHistoryDao( LoginHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link LoginHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public LoginHistoryRow getLoginHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link LoginHistoryRow}�I�u�W�F�N�g����{@@link LoginHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link LoginHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link LoginHistoryDao}�擾�̂��߂Ɏw���{@@link LoginHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link LoginHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static LoginHistoryDao forRow( LoginHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (LoginHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LoginHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link LoginHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link LoginHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link LoginHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LoginHistoryRow.TYPE );
    }


  /** 
   * {@@link LoginHistoryRow}����ӂɓ��肷��{@@link LoginHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link LoginHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link LoginHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link LoginHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static LoginHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new LoginHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link LoginHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_loginHistoryId �����Ώۂł���p_loginHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LoginHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LoginHistoryRow findRowByPk( long p_loginHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginHistoryPK pk = new LoginHistoryPK( p_loginHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���LoginHistoryPK�I�u�W�F�N�g����{@@link LoginHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����LoginHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link LoginHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static LoginHistoryRow findRowByPk( LoginHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LoginHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(LoginHistoryRow)}���g�p���Ă��������B 
   */
    public static LoginHistoryDao findDaoByPk( long p_loginHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginHistoryPK pk = new LoginHistoryPK( p_loginHistoryId );
        LoginHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(LoginHistoryPK)}�����{@@link #forRow(LoginHistoryRow)}���g�p���Ă��������B 
   */
    public static LoginHistoryDao findDaoByPk( LoginHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginHistoryRow row = findRowByPk( pk );
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
   * p_loginHistoryId, and �ɂĎw��̒l�����ӂ�{@@link LoginHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_loginHistoryId �����Ώۂł���p_loginHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���p_loginHistoryId, and �̒l�ƈ�v����{@@link LoginHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static LoginHistoryRow findRowByLoginHistoryId( long p_loginHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LoginHistoryRow.TYPE,
            "login_history_id=?",
            null,
            new Object[] { new Long(p_loginHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LoginHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LoginHistoryDao.findRowsByLoginHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByLoginHistoryId(long)}�����{@@link #forRow(LoginHistoryRow)}���g�p���Ă��������B 
   */
    public static LoginHistoryDao findDaoByLoginHistoryId( long p_loginHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLoginHistoryId( p_loginHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress, and �ɂĎw��̒l�Ɉ�v����{@@link LoginHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_loginTimestamp �����Ώۂł���p_loginTimestamp�t�B�[���h�̒l
   * @@param p_loginFailure �����Ώۂł���p_loginFailure�t�B�[���h�̒l
   * @@param p_ipAddress �����Ώۂł���p_ipAddress�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress, and �̒l�ƈ�v����{@@link LoginHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeLoginTimestampLoginFailureIpAddress( String p_institutionCode, java.sql.Timestamp p_loginTimestamp, String p_loginFailure, String p_ipAddress ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LoginHistoryRow.TYPE,
            "institution_code=? and login_timestamp=? and login_failure=? and ip_address=?",
            null,
            new Object[] { p_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeLoginTimestampLoginFailureIpAddress(String, java.sql.Timestamp, String, String)}�����{@@link #forRow(LoginHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeLoginTimestampLoginFailureIpAddress( String p_institutionCode, java.sql.Timestamp p_loginTimestamp, String p_loginFailure, String p_ipAddress ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeLoginTimestampLoginFailureIpAddress( p_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress ) );
    }

}
@
