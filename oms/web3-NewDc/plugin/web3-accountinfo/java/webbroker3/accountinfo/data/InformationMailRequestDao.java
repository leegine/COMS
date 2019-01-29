head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	InformationMailRequestDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link InformationMailRequestDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link InformationMailRequestRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see InformationMailRequestPK 
 * @@see InformationMailRequestRow 
 */
public class InformationMailRequestDao extends DataAccessObject {


  /** 
   * ����{@@link InformationMailRequestDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private InformationMailRequestRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link InformationMailRequestRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link InformationMailRequestDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link InformationMailRequestDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link InformationMailRequestRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformationMailRequestRow )
                return new InformationMailRequestDao( (InformationMailRequestRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformationMailRequestRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformationMailRequestRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link InformationMailRequestRow}�I�u�W�F�N�g 
    */
    protected InformationMailRequestDao( InformationMailRequestRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link InformationMailRequestRow}�I�u�W�F�N�g���擾���܂��B
   */
    public InformationMailRequestRow getInformationMailRequestRow() {
        return row;
    }


  /** 
   * �w���{@@link InformationMailRequestRow}�I�u�W�F�N�g����{@@link InformationMailRequestDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link InformationMailRequestRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link InformationMailRequestDao}�擾�̂��߂Ɏw���{@@link InformationMailRequestRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link InformationMailRequestDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static InformationMailRequestDao forRow( InformationMailRequestRow row ) throws java.lang.IllegalArgumentException {
        return (InformationMailRequestDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformationMailRequestRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link InformationMailRequestRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link InformationMailRequestPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link InformationMailRequestParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformationMailRequestRow.TYPE );
    }


  /** 
   * {@@link InformationMailRequestRow}����ӂɓ��肷��{@@link InformationMailRequestPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link InformationMailRequestRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link InformationMailRequestParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link InformationMailRequestPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static InformationMailRequestPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new InformationMailRequestPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link InformationMailRequestRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_informationMailRequestId �����Ώۂł���p_informationMailRequestId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformationMailRequestRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformationMailRequestRow findRowByPk( long p_informationMailRequestId ) throws DataFindException, DataQueryException, DataNetworkException {
        InformationMailRequestPK pk = new InformationMailRequestPK( p_informationMailRequestId );
        return findRowByPk( pk );
    }


  /** 
   * �w���InformationMailRequestPK�I�u�W�F�N�g����{@@link InformationMailRequestRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����InformationMailRequestPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformationMailRequestRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformationMailRequestRow findRowByPk( InformationMailRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformationMailRequestRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(InformationMailRequestRow)}���g�p���Ă��������B 
   */
    public static InformationMailRequestDao findDaoByPk( long p_informationMailRequestId ) throws DataFindException, DataQueryException, DataNetworkException {
        InformationMailRequestPK pk = new InformationMailRequestPK( p_informationMailRequestId );
        InformationMailRequestRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(InformationMailRequestPK)}�����{@@link #forRow(InformationMailRequestRow)}���g�p���Ă��������B 
   */
    public static InformationMailRequestDao findDaoByPk( InformationMailRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformationMailRequestRow row = findRowByPk( pk );
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
   * p_informationMailRequestId, and �ɂĎw��̒l�����ӂ�{@@link InformationMailRequestRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_informationMailRequestId �����Ώۂł���p_informationMailRequestId�t�B�[���h�̒l
   * 
   * @@return �����w���p_informationMailRequestId, and �̒l�ƈ�v����{@@link InformationMailRequestRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static InformationMailRequestRow findRowByInformationMailRequestId( long p_informationMailRequestId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformationMailRequestRow.TYPE,
            "information_mail_request_id=?",
            null,
            new Object[] { new Long(p_informationMailRequestId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformationMailRequestRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformationMailRequestDao.findRowsByInformationMailRequestId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInformationMailRequestId(long)}�����{@@link #forRow(InformationMailRequestRow)}���g�p���Ă��������B 
   */
    public static InformationMailRequestDao findDaoByInformationMailRequestId( long p_informationMailRequestId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInformationMailRequestId( p_informationMailRequestId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_sendmailDev, p_requestTimestamp, and �ɂĎw��̒l�Ɉ�v����{@@link InformationMailRequestRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_sendmailDev �����Ώۂł���p_sendmailDev�t�B�[���h�̒l
   * @@param p_requestTimestamp �����Ώۂł���p_requestTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_sendmailDev, p_requestTimestamp, and �̒l�ƈ�v����{@@link InformationMailRequestRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeSendmailDevRequestTimestamp( String p_institutionCode, String p_sendmailDev, java.sql.Timestamp p_requestTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InformationMailRequestRow.TYPE,
            "institution_code=? and sendmail_dev=? and request_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_sendmailDev, p_requestTimestamp } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeSendmailDevRequestTimestamp(String, String, java.sql.Timestamp)}�����{@@link #forRow(InformationMailRequestRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeSendmailDevRequestTimestamp( String p_institutionCode, String p_sendmailDev, java.sql.Timestamp p_requestTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeSendmailDevRequestTimestamp( p_institutionCode, p_sendmailDev, p_requestTimestamp ) );
    }

}
@
