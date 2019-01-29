head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailInfoDao.java;


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
 * {@@link MailInfoDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MailInfoRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MailInfoPK 
 * @@see MailInfoRow 
 */
public class MailInfoDao extends DataAccessObject {


  /** 
   * ����{@@link MailInfoDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MailInfoRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MailInfoRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MailInfoDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MailInfoDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MailInfoRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MailInfoRow )
                return new MailInfoDao( (MailInfoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MailInfoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MailInfoRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MailInfoRow}�I�u�W�F�N�g 
    */
    protected MailInfoDao( MailInfoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MailInfoRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MailInfoRow getMailInfoRow() {
        return row;
    }


  /** 
   * �w���{@@link MailInfoRow}�I�u�W�F�N�g����{@@link MailInfoDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MailInfoRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MailInfoDao}�擾�̂��߂Ɏw���{@@link MailInfoRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MailInfoDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MailInfoDao forRow( MailInfoRow row ) throws java.lang.IllegalArgumentException {
        return (MailInfoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MailInfoRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MailInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MailInfoPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MailInfoParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MailInfoRow.TYPE );
    }


  /** 
   * {@@link MailInfoRow}����ӂɓ��肷��{@@link MailInfoPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MailInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MailInfoParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MailInfoPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MailInfoPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MailInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_sendmailDiv �����Ώۂł���p_sendmailDiv�t�B�[���h�̒l
   * @@param p_discernmentId �����Ώۂł���p_discernmentId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MailInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MailInfoRow findRowByPk( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailInfoPK pk = new MailInfoPK( p_institutionCode, p_sendmailDiv, p_discernmentId );
        return findRowByPk( pk );
    }


  /** 
   * �w���MailInfoPK�I�u�W�F�N�g����{@@link MailInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MailInfoPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MailInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MailInfoRow findRowByPk( MailInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MailInfoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(MailInfoRow)}���g�p���Ă��������B 
   */
    public static MailInfoDao findDaoByPk( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailInfoPK pk = new MailInfoPK( p_institutionCode, p_sendmailDiv, p_discernmentId );
        MailInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MailInfoPK)}�����{@@link #forRow(MailInfoRow)}���g�p���Ă��������B 
   */
    public static MailInfoDao findDaoByPk( MailInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MailInfoRow row = findRowByPk( pk );
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
   * p_institutionCode, p_sendmailDiv, p_discernmentId, and �ɂĎw��̒l�����ӂ�{@@link MailInfoRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_sendmailDiv �����Ώۂł���p_sendmailDiv�t�B�[���h�̒l
   * @@param p_discernmentId �����Ώۂł���p_discernmentId�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_sendmailDiv, p_discernmentId, and �̒l�ƈ�v����{@@link MailInfoRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MailInfoRow findRowByInstitutionCodeSendmailDivDiscernmentId( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MailInfoRow.TYPE,
            "institution_code=? and sendmail_div=? and discernment_id=?",
            null,
            new Object[] { p_institutionCode, p_sendmailDiv, p_discernmentId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MailInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MailInfoDao.findRowsByInstitutionCodeSendmailDivDiscernmentId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeSendmailDivDiscernmentId(String, String, String)}�����{@@link #forRow(MailInfoRow)}���g�p���Ă��������B 
   */
    public static MailInfoDao findDaoByInstitutionCodeSendmailDivDiscernmentId( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSendmailDivDiscernmentId( p_institutionCode, p_sendmailDiv, p_discernmentId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
