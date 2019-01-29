head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherStatusDao.java;


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
 * {@@link AccOpenVoucherStatusDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenVoucherStatusRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenVoucherStatusPK 
 * @@see AccOpenVoucherStatusRow 
 */
public class AccOpenVoucherStatusDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenVoucherStatusDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenVoucherStatusRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenVoucherStatusRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenVoucherStatusDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenVoucherStatusDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenVoucherStatusRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenVoucherStatusRow )
                return new AccOpenVoucherStatusDao( (AccOpenVoucherStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenVoucherStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenVoucherStatusRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g 
    */
    protected AccOpenVoucherStatusDao( AccOpenVoucherStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenVoucherStatusRow getAccOpenVoucherStatusRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g����{@@link AccOpenVoucherStatusDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenVoucherStatusRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenVoucherStatusDao}�擾�̂��߂Ɏw���{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenVoucherStatusDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenVoucherStatusDao forRow( AccOpenVoucherStatusRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenVoucherStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenVoucherStatusRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenVoucherStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenVoucherStatusPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenVoucherStatusParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenVoucherStatusRow.TYPE );
    }


  /** 
   * {@@link AccOpenVoucherStatusRow}����ӂɓ��肷��{@@link AccOpenVoucherStatusPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenVoucherStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenVoucherStatusParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenVoucherStatusPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenVoucherStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accOpenRequestNumber �����Ώۂł���p_accOpenRequestNumber�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_serialNo �����Ώۂł���p_serialNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenVoucherStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenVoucherStatusRow findRowByPk( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherStatusPK pk = new AccOpenVoucherStatusPK( p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenVoucherStatusPK�I�u�W�F�N�g����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenVoucherStatusPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenVoucherStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenVoucherStatusRow findRowByPk( AccOpenVoucherStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenVoucherStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(AccOpenVoucherStatusRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherStatusDao findDaoByPk( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherStatusPK pk = new AccOpenVoucherStatusPK( p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo );
        AccOpenVoucherStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenVoucherStatusPK)}�����{@@link #forRow(AccOpenVoucherStatusRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherStatusDao findDaoByPk( AccOpenVoucherStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo, and �ɂĎw��̒l�����ӂ�{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accOpenRequestNumber �����Ώۂł���p_accOpenRequestNumber�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_serialNo �����Ώۂł���p_serialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo, and �̒l�ƈ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenVoucherStatusRow findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "institution_code=? and acc_open_request_number=? and request_code=? and serial_no=?",
            null,
            new Object[] { p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenVoucherStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenVoucherStatusDao.findRowsByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo(String, String, String, String)}�����{@@link #forRow(AccOpenVoucherStatusRow)}���g�p���Ă��������B 
   */
    public static AccOpenVoucherStatusDao findDaoByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo( p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, and �ɂĎw��̒l�Ɉ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, and �̒l�ƈ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "institution_code=?",
            null,
            new Object[] { p_institutionCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCode(String)}�����{@@link #forRow(AccOpenVoucherStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCode( p_institutionCode ) );
    }


  /** 
   * p_accOpenRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accOpenRequestNumber �����Ώۂł���p_accOpenRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_accOpenRequestNumber, and �̒l�ƈ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccOpenRequestNumber( String p_accOpenRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "acc_open_request_number=?",
            null,
            new Object[] { p_accOpenRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccOpenRequestNumber(String)}�����{@@link #forRow(AccOpenVoucherStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccOpenRequestNumber( String p_accOpenRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccOpenRequestNumber( p_accOpenRequestNumber ) );
    }


  /** 
   * p_requestCode, and �ɂĎw��̒l�Ɉ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_requestCode, and �̒l�ƈ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRequestCode( String p_requestCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "request_code=?",
            null,
            new Object[] { p_requestCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRequestCode(String)}�����{@@link #forRow(AccOpenVoucherStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRequestCode( String p_requestCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCode( p_requestCode ) );
    }


  /** 
   * p_sendTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_sendTimestamp �����Ώۂł���p_sendTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_sendTimestamp, and �̒l�ƈ�v����{@@link AccOpenVoucherStatusRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsBySendTimestamp( java.sql.Timestamp p_sendTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "send_timestamp=?",
            null,
            new Object[] { p_sendTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsBySendTimestamp(java.sql.Timestamp)}�����{@@link #forRow(AccOpenVoucherStatusRow)}���g�p���Ă��������B 
   */
    public static List findDaosBySendTimestamp( java.sql.Timestamp p_sendTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsBySendTimestamp( p_sendTimestamp ) );
    }

}
@
